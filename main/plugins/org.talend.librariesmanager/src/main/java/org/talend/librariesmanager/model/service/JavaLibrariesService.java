// ============================================================================
//
// Copyright (C) 2006-2016 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.librariesmanager.model.service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.baseadaptor.BaseData;
import org.eclipse.osgi.baseadaptor.bundlefile.BundleFile;
import org.eclipse.osgi.framework.adaptor.BundleData;
import org.eclipse.osgi.framework.internal.core.BundleHost;
import org.osgi.framework.Bundle;
import org.talend.commons.utils.generation.JavaUtils;
import org.talend.commons.utils.io.FilesUtils;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.ILibraryManagerService;
import org.talend.core.ILibraryManagerUIService;
import org.talend.core.PluginChecker;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.model.components.IComponentsService;
import org.talend.core.model.general.ModuleNeeded;
import org.talend.core.model.general.ModuleNeeded.ELibraryInstallStatus;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.routines.IRoutinesProvider;
import org.talend.core.utils.TalendCacheUtils;
import org.talend.designer.codegen.PigTemplate;
import org.talend.librariesmanager.i18n.Messages;
import org.talend.librariesmanager.model.ModulesNeededProvider;
import org.talend.librariesmanager.prefs.LibrariesManagerUtils;
import org.talend.repository.ProjectManager;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class JavaLibrariesService extends AbstractLibrariesService {

    private static Logger log = Logger.getLogger(JavaLibrariesService.class);

    private static ILibraryManagerService repositoryBundleService = (ILibraryManagerService) GlobalServiceRegister.getDefault()
            .getService(ILibraryManagerService.class);

    public static final String SOURCE_JAVA_ROUTINES_FOLDER = "routines"; //$NON-NLS-1$

    public static final String SOURCE_JAVA_PIGUDF_FOLDER = "pigudf"; //$NON-NLS-1$

    public static final String SOURCE_JAVA_BEANS_FOLDER = "beans"; //$NON-NLS-1$

    private static boolean isLibSynchronized;

    @Override
    public URL getRoutineTemplate() {
        return Platform.getBundle(LibrariesManagerUtils.BUNDLE_DI).getEntry(
                "resources/java/" + SOURCE_JAVA_ROUTINES_FOLDER + "/__TEMPLATE__.java"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public URL getPigudfTemplate(PigTemplate template) {
        Bundle bundle = Platform.getBundle(LibrariesManagerUtils.BUNDLE_DI);
        return bundle.getEntry("templates/" + SOURCE_JAVA_PIGUDF_FOLDER + "/" + template.getFileName() + ".java"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }

    @Override
    public URL getBeanTemplate() {
        return Platform.getBundle(LibrariesManagerUtils.BUNDLE_DI).getEntry(
                "resources/java/" + SOURCE_JAVA_BEANS_FOLDER + "/__BEAN_TEMPLATE__.java"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.general.ILibrariesService#getSqlPatternTemplate()
     */
    @Override
    public URL getSqlPatternTemplate() {
        return Platform.getBundle(LibrariesManagerUtils.BUNDLE_DI).getEntry(
                "resources/java/" + SOURCE_SQLPATTERN_FOLDER + "/__TEMPLATE__" + TEMPLATE_SUFFIX); //$NON-NLS-1$ //$NON-NLS-2$
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.general.ILibrariesService#getSystemRoutines()
     */
    @Override
    public List<URL> getSystemRoutines() {
        List<URL> toReturn = new ArrayList<URL>();
        if (GlobalServiceRegister.getDefault().isServiceRegistered(ILibraryManagerUIService.class)) {
            ILibraryManagerUIService libUiService = (ILibraryManagerUIService) GlobalServiceRegister.getDefault().getService(
                    ILibraryManagerUIService.class);

            for (IRoutinesProvider routineProvider : libUiService.getRoutinesProviders(ECodeLanguage.JAVA)) {
                toReturn.addAll(routineProvider.getSystemRoutines());
            }
        }
        return toReturn;
    }

    @Override
    public List<URL> getSystemSQLPatterns() {
        return FilesUtils.getFilesFromFolder(Platform.getBundle(LibrariesManagerUtils.BUNDLE_DI),
                "resources/java/" + SOURCE_SQLPATTERN_FOLDER, //$NON-NLS-1$
                SQLPATTERN_FILE_SUFFIX, false, true);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.general.ILibrariesService#getTalendRoutines()
     */
    @Override
    public List<URL> getTalendRoutinesFolder() throws IOException {
        List<URL> toReturn = new ArrayList<URL>();
        if (GlobalServiceRegister.getDefault().isServiceRegistered(ILibraryManagerUIService.class)) {
            ILibraryManagerUIService libUiService = (ILibraryManagerUIService) GlobalServiceRegister.getDefault().getService(
                    ILibraryManagerUIService.class);
            for (IRoutinesProvider routineProvider : libUiService.getRoutinesProviders(ECodeLanguage.JAVA)) {
                toReturn.add(routineProvider.getTalendRoutinesFolder());
            }
        }

        return toReturn;
    }

    @Override
    public List<URL> getTalendBeansFolder() throws IOException {
        List<URL> toReturn = new ArrayList<URL>();
        if (GlobalServiceRegister.getDefault().isServiceRegistered(ILibraryManagerUIService.class)) {
            ILibraryManagerUIService libUiService = (ILibraryManagerUIService) GlobalServiceRegister.getDefault().getService(
                    ILibraryManagerUIService.class);
            for (IRoutinesProvider routineProvider : libUiService.getRoutinesProviders(ECodeLanguage.JAVA)) {
                toReturn.add(routineProvider.getTalendRoutinesFolder());
            }
        }
        return toReturn;
    }

    @Override
    public List<URL> getTalendRoutines() {
        List<URL> toReturn = new ArrayList<URL>();
        if (GlobalServiceRegister.getDefault().isServiceRegistered(ILibraryManagerUIService.class)) {
            ILibraryManagerUIService libUiService = (ILibraryManagerUIService) GlobalServiceRegister.getDefault().getService(
                    ILibraryManagerUIService.class);
            for (IRoutinesProvider routineProvider : libUiService.getRoutinesProviders(ECodeLanguage.JAVA)) {
                toReturn.addAll(routineProvider.getTalendRoutines());
            }
        }

        return toReturn;
    }

    @Override
    public void checkInstalledLibraries() {
        Set<String> existLibraries = repositoryBundleService.list();
        Set<String> existDllLibraries = repositoryBundleService.listAllDllFiles();
        existLibraries.addAll(existDllLibraries);
        List<ModuleNeeded> toCheck = ModulesNeededProvider.getModulesNeeded();
        for (ModuleNeeded current : toCheck) {
            if (existLibraries.contains(current.getModuleName())) {
                current.setStatus(ELibraryInstallStatus.INSTALLED);
            } else {
                current.setStatus(ELibraryInstallStatus.NOT_INSTALLED);
            }
        }

        List<String> modulesNeededNames = ModulesNeededProvider.getModulesNeededNames();
        ModulesNeededProvider.getUnUsedModules().clear();
        for (String library : existLibraries) {
            if (!modulesNeededNames.contains(library)) {
                ModulesNeededProvider.userAddUnusedModules("Unknown", library); //$NON-NLS-1$
            }
        }
    }

    @Override
    public void syncLibrariesFromApp(IProgressMonitor... monitorWrap) {
        List<ModuleNeeded> modulesNeededForApplication = ModulesNeededProvider.getModulesNeededForApplication();
        if (!repositoryBundleService.isInitialized()) {
            repositoryBundleService.deploy(modulesNeededForApplication, monitorWrap);
            repositoryBundleService.setInitialized();
        }
    }

    @Override
    public void syncLibraries(IProgressMonitor... monitorWrap) {
        // 1. Talend libraries:
        // File talendLibraries = new File(FileLocator
        //                    .resolve(Platform.getBundle(BUNDLE_DI).getEntry("resources/java/lib/")).getFile()); //$NON-NLS-1$
        // repositoryBundleService.deploy(talendLibraries.toURI(), monitorWrap);

        if (TalendCacheUtils.cleanComponentCache()) {
            repositoryBundleService.clearCache();
        }
        // Add a new system file, if exists, means all components libs are already setup, so no need to do again.
        // if clean the component cache, it will automatically recheck all libs still.
        if (!repositoryBundleService.isInitialized()) {
            // 2. Components libraries
            if (GlobalServiceRegister.getDefault().isServiceRegistered(IComponentsService.class)) {
                IComponentsService service = (IComponentsService) GlobalServiceRegister.getDefault().getService(
                        IComponentsService.class);
                repositoryBundleService.deploy(service.getComponentsFactory().getComponents(), monitorWrap);
                Map<String, File> componentsFolders = service.getComponentsFactory().getComponentsProvidersFolder();
                Set<String> contributeIdSet = componentsFolders.keySet();
                for (String contributeID : contributeIdSet) {
                    repositoryBundleService.deploy(componentsFolders.get(contributeID).toURI(), monitorWrap);
                }

                // / for test
                syncLibrariesFromApp(monitorWrap);

                repositoryBundleService.setInitialized();
            }
        }

        // 3. system routine libraries
        // Map<String, List<URI>> routineAndJars = RoutineLibraryMananger.getInstance().getRoutineAndJars();
        // Iterator<Entry<String, List<URI>>> rjsIter = routineAndJars.entrySet().iterator();
        // while (rjsIter.hasNext()) {
        // Map.Entry<String, List<URI>> entry = rjsIter.next();
        // repositoryBundleService.deploy(entry.getValue(), monitorWrap);
        // }
        // 3. deploy system routine libraries
        if (GlobalServiceRegister.getDefault().isServiceRegistered(ILibraryManagerUIService.class)) {
            ILibraryManagerUIService libUiService = (ILibraryManagerUIService) GlobalServiceRegister.getDefault().getService(
                    ILibraryManagerUIService.class);
            libUiService.initializeSystemLibs();
        }

        // 4. check in the libs directory of the project and add the jar with other ones.
        syncLibrariesFromLibs(monitorWrap);

        checkInstalledLibraries();

        // for AMC libraries

        // check if org.talend.amc.libraries exists
        if (GlobalServiceRegister.getDefault().isServiceRegistered(ILibraryManagerService.class)) {
            ILibraryManagerService libManager = (ILibraryManagerService) GlobalServiceRegister.getDefault().getService(
                    ILibraryManagerService.class);
            Bundle bundle = Platform.getBundle("org.talend.amc.libraries"); //$NON-NLS-1$
            if (bundle instanceof BundleHost) {
                BundleHost bundleHost = (BundleHost) bundle;
                final BundleData bundleData = bundleHost.getBundleData();
                if (bundleData instanceof BaseData) {
                    BaseData baseData = (BaseData) bundleData;
                    final BundleFile bundleFile = baseData.getBundleFile();
                    final File baseFile = bundleFile.getBaseFile();
                    final File file = new File(baseFile.getAbsolutePath() + File.separator + JavaUtils.JAVA_LIB_DIRECTORY);
                    String[] allNeededModuls = { "db2jcc.jar", "db2jcc_license_cu.jar", "jconn3.jar", "ifxjdbcx.jar", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ 
                            "ifxlang.jar", "ifxlsupp.jar", "ifxsqlj.jar", "ifxtools.jar", "ifxjdbc.jar" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
                    for (String allNeededModul : allNeededModuls) {
                        String name = allNeededModul;
                        if (!libManager.contains(name)) {
                            continue;
                        }
                        if (file.exists() && file.isDirectory()) {
                            libManager.retrieve(name, file.getAbsolutePath(), new NullProgressMonitor());
                        }
                    }
                }
            }
        }
        // clean the temp library of job needed in .java\lib
        cleanTempProLib();

        log.debug(Messages.getString("JavaLibrariesService.synchronization")); //$NON-NLS-1$
        isLibSynchronized = true;
    }

    protected void updateProjectLib(String libName) {

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.general.ILibrariesService#isLibSynchronized()
     */
    @Override
    public boolean isLibSynchronized() {
        return this.isLibSynchronized;

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.general.ILibrariesService#getPerlLibrariesPath()
     */
    @Override
    public String getPerlLibrariesPath() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.talend.core.model.general.ILibrariesService#syncLibrariesFromLibs(org.eclipse.core.runtime.IProgressMonitor
     * [])
     */
    @Override
    public void syncLibrariesFromLibs(IProgressMonitor... monitorWrap) {
        // for feature 12877
        if (PluginChecker.isSVNProviderPluginLoaded()) {
            String path = new Path(Platform.getInstanceLocation().getURL().getPath()).toFile().getPath();
            String projectLabel = ProjectManager.getInstance().getCurrentProject().getTechnicalLabel();
            path = path + File.separatorChar + projectLabel + File.separatorChar
                    + ERepositoryObjectType.getFolderName(ERepositoryObjectType.LIBS);
            File libsTargetFile = new File(path);
            repositoryBundleService.deploy(libsTargetFile.toURI(), monitorWrap);
        }
    }

    public void cleanTempProLib() {
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        IProject prj = root.getProject(JavaUtils.JAVA_PROJECT_NAME);
        if (prj.exists()) {
            IPath libPath = prj.getLocation().append(JavaUtils.JAVA_LIB_DIRECTORY);
            if (libPath.toFile().exists()) {
                FilesUtils.emptyFolder(libPath.toFile());
            }
        }
    }

}
