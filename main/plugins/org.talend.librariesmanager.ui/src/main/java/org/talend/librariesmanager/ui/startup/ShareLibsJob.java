// ============================================================================
//
// Copyright (C) 2006-2014 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.librariesmanager.ui.startup;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.utils.io.FilesUtils;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.ILibraryManagerService;
import org.talend.core.ISVNProviderServiceInCoreRuntime;
import org.talend.core.PluginChecker;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.model.general.ModuleNeeded;
import org.talend.core.nexus.NexusServerBean;
import org.talend.core.runtime.CoreRuntimePlugin;
import org.talend.core.runtime.maven.MavenArtifact;
import org.talend.core.runtime.maven.MavenConstants;
import org.talend.core.runtime.maven.MavenUrlHelper;
import org.talend.librariesmanager.maven.ArtifactsDeployer;
import org.talend.librariesmanager.maven.TalendLibsServerManager;
import org.talend.librariesmanager.model.ModulesNeededProvider;
import org.talend.librariesmanager.prefs.LibrariesManagerUtils;
import org.talend.librariesmanager.ui.i18n.Messages;
import org.talend.repository.model.IProxyRepositoryFactory;

/**
 * created by wchen on 2015年6月15日 Detailled comment
 *
 */
public class ShareLibsJob extends Job {

    private final String TYPE_NEXUS = "nexus";

    private final String TYPE_SVN = "svn";

    /**
     * DOC Talend ShareLibsJob constructor comment.
     * 
     * @param name
     */
    public ShareLibsJob() {
        super("");
        // TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    protected IStatus run(IProgressMonitor monitor) {

        if (PluginChecker.isSVNProviderPluginLoaded()) {
            try {
                IProxyRepositoryFactory factory = CoreRuntimePlugin.getInstance().getProxyRepositoryFactory();
                if (!factory.isLocalConnectionProvider() && factory.getRepositoryContext() != null
                        && !factory.getRepositoryContext().isOffline()) {
                    ISVNProviderServiceInCoreRuntime service = (ISVNProviderServiceInCoreRuntime) GlobalServiceRegister
                            .getDefault().getService(ISVNProviderServiceInCoreRuntime.class);

                    if (service != null && service.isSvnLibSetupOnTAC()) {
                        setName(Messages.getString("ShareLibsJob.message", TYPE_SVN));
                        int shareLimit = 5;
                        // share to svn lib
                        int limit = shareLimit;
                        Map<ModuleNeeded, File> filesToShare = getFilesToShare(monitor);
                        if (filesToShare == null) {
                            return Status.CANCEL_STATUS;
                        }
                        SubMonitor mainSubMonitor = SubMonitor.convert(monitor, filesToShare.size());
                        Iterator<ModuleNeeded> iterator = filesToShare.keySet().iterator();
                        int index = 0;
                        while (iterator.hasNext()) {
                            if (monitor.isCanceled()) {
                                return Status.CANCEL_STATUS;
                            }
                            List<String> jars = new ArrayList<String>();
                            String jarName = "";
                            while (index < limit && index < filesToShare.size()) {
                                ModuleNeeded next = iterator.next();
                                File file = filesToShare.get(next);
                                String installLocation = getStorageDirectory().getAbsolutePath();
                                File target = new File(installLocation, next.getModuleName());
                                // if already eixst in lib svn , don't replace it .
                                if (!target.exists()) {
                                    FilesUtils.copyFile(file, target);
                                    jars.add(target.getAbsolutePath());
                                    jarName += next.getModuleName();
                                    if (index < limit - 1) {
                                        jarName += ",";
                                    }
                                }
                                index++;
                            }
                            limit += shareLimit;
                            try {
                                if (jars.size() > 0) {
                                    mainSubMonitor.setTaskName(Messages.getString("ShareLibsJob.sharingLibraries", jarName)); //$NON-NLS-1$
                                    service.deployNewJar(jars);
                                }
                                mainSubMonitor.worked(limit);
                            } catch (Exception e) {
                                ExceptionHandler.process(new Exception("Share libraries :" + jarName + " failed !", e));
                                continue;
                            }
                        }
                    } else {
                        // share to custom nexus
                        int searchLimit = 50;
                        setName(Messages.getString("ShareLibsJob.message", TYPE_NEXUS));
                        TalendLibsServerManager instance = TalendLibsServerManager.getInstance();
                        NexusServerBean customServer = instance.getCustomNexusServer();
                        if (customServer != null) {
                            ArtifactsDeployer deployer = new ArtifactsDeployer();
                            Map<ModuleNeeded, File> filesToShare = getFilesToShare(monitor);
                            if (filesToShare == null) {
                                return Status.CANCEL_STATUS;
                            }
                            SubMonitor mainSubMonitor = SubMonitor.convert(monitor, filesToShare.size());
                            Iterator<ModuleNeeded> iterator = filesToShare.keySet().iterator();

                            Iterator<ModuleNeeded> searchIter = filesToShare.keySet().iterator();

                            int index = 0;
                            int limit = searchLimit;
                            int shareIndex = 0;
                            while (iterator.hasNext()) {
                                if (monitor.isCanceled()) {
                                    return Status.CANCEL_STATUS;
                                }
                                String jarsToCheck = "";
                                while (shareIndex < limit && index < limit && index < filesToShare.size()) {
                                    ModuleNeeded toCheck = searchIter.next();
                                    String jarName = toCheck.getModuleName();
                                    String artifactId = jarName;
                                    int lastIndexOf = jarName.lastIndexOf(".");
                                    if (lastIndexOf != -1) {
                                        artifactId = jarName.substring(0, lastIndexOf);
                                    }
                                    jarsToCheck += artifactId;
                                    index++;
                                    if (index < limit) {
                                        jarsToCheck += ",";
                                    }
                                }
                                shareIndex++;
                                if (shareIndex == limit) {
                                    limit += searchLimit;
                                }

                                final List<MavenArtifact> searchResults = instance.search(customServer.getServer(),
                                        customServer.getUserName(), customServer.getPassword(), customServer.getRepositoryId(),
                                        MavenConstants.DEFAULT_LIB_GROUP_ID, jarsToCheck, null);
                                ModuleNeeded next = iterator.next();
                                File file = filesToShare.get(next);
                                String pomPath = file.getParent();
                                String name = file.getName();
                                mainSubMonitor.setTaskName(Messages.getString("ShareLibsJob.sharingLibraries", name));
                                MavenArtifact artifact = MavenUrlHelper.parseMvnUrl(next.getMavenUriSnapshot());
                                if (artifact == null) {
                                    continue;
                                }
                                boolean eixst = false;
                                for (MavenArtifact remoteAtifact : searchResults) {
                                    String groupId = artifact.getGroupId();
                                    String artifactId = artifact.getArtifactId();
                                    String version = artifact.getVersion();
                                    String rGroup = remoteAtifact.getGroupId();
                                    String rArtifact = remoteAtifact.getArtifactId();
                                    String rVersion = remoteAtifact.getVersion();
                                    if (groupId != null && artifactId != null && version != null && groupId.equals(rGroup)
                                            && artifactId.equals(rArtifact) && version.equals(rVersion)) {
                                        eixst = true;
                                        break;
                                    }
                                }
                                if (eixst) {
                                    continue;
                                }
                                int indexOf = name.lastIndexOf(".");
                                if (indexOf != -1) {
                                    pomPath = pomPath + "/" + name.substring(0, indexOf) + "." + MavenConstants.PACKAGING_POM;
                                }
                                try {
                                    deployer.installToRemote(file, artifact, artifact.getType());
                                    File pomFile = new File(pomPath);
                                    if (pomFile.exists()) {
                                        deployer.installToRemote(pomFile, artifact, MavenConstants.PACKAGING_POM);
                                    }
                                    mainSubMonitor.worked(1);
                                } catch (Exception e) {
                                    ExceptionHandler.process(new Exception("Share libraries :" + name + " failed !", e));
                                    continue;
                                }
                            }
                        }

                    }
                }
            } catch (Exception e) {
                ExceptionHandler.process(e);
            }
        }
        return Status.OK_STATUS;
    }

    private File getStorageDirectory() {
        String librariesPath = LibrariesManagerUtils.getLibrariesPath(ECodeLanguage.JAVA);
        File storageDir = new File(librariesPath);
        return storageDir;
    }

    private Map<ModuleNeeded, File> getFilesToShare(IProgressMonitor monitor) {
        SubMonitor mainSubMonitor = SubMonitor.convert(monitor, 1);
        mainSubMonitor.setTaskName(Messages.getString("ShareLibsJob.getFilesToShare")); //$NON-NLS-1$
        final List<ModuleNeeded> modulesNeeded = ModulesNeededProvider.getModulesNeeded();
        ILibraryManagerService librariesService = (ILibraryManagerService) GlobalServiceRegister.getDefault().getService(
                ILibraryManagerService.class);
        Map<ModuleNeeded, File> files = new HashMap<ModuleNeeded, File>();
        Set<String> filePaths = new HashSet<String>();
        for (ModuleNeeded module : modulesNeeded) {
            if (monitor.isCanceled()) {
                return null;
            }

            // only deploy libraries with group id org.talend.libraries
            MavenArtifact parseMvnUrl = MavenUrlHelper.parseMvnUrl(module.getMavenUriSnapshot());
            if (parseMvnUrl == null || !MavenConstants.DEFAULT_LIB_GROUP_ID.equals(parseMvnUrl.getGroupId())) {
                continue;
            }

            final String jarPathFromMaven = librariesService.getJarPathFromMaven(module.getMavenUriSnapshot());
            if (jarPathFromMaven == null) {
                continue;
            }
            File jarFile = new File(jarPathFromMaven);
            if (jarFile.exists()) {
                if (!filePaths.contains(jarPathFromMaven)) {
                    files.put(module, jarFile);
                }
                filePaths.add(jarPathFromMaven);
            }
        }
        mainSubMonitor.worked(1);
        return files;
    }
}
