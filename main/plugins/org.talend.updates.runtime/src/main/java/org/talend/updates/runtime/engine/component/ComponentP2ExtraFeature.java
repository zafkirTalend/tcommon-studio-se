// ============================================================================
//
// Copyright (C) 2006-2017 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.updates.runtime.engine.component;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.equinox.p2.core.IProvisioningAgent;
import org.eclipse.equinox.p2.core.IProvisioningAgentProvider;
import org.eclipse.equinox.p2.core.ProvisionException;
import org.eclipse.equinox.p2.engine.IPhaseSet;
import org.eclipse.equinox.p2.engine.PhaseSetFactory;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.operations.InstallOperation;
import org.eclipse.equinox.p2.operations.ProfileModificationJob;
import org.eclipse.equinox.p2.operations.ProvisioningSession;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.talend.commons.runtime.utils.io.FileCopyUtils;
import org.talend.commons.utils.VersionUtils;
import org.talend.commons.utils.resource.FileExtensions;
import org.talend.core.runtime.maven.MavenArtifact;
import org.talend.core.runtime.maven.MavenUrlHelper;
import org.talend.librariesmanager.prefs.LibrariesManagerUtils;
import org.talend.updates.runtime.i18n.Messages;
import org.talend.updates.runtime.maven.MavenRepoSynchronizer;
import org.talend.updates.runtime.model.P2ExtraFeature;
import org.talend.updates.runtime.model.P2ExtraFeatureException;
import org.talend.updates.runtime.model.UpdateSiteLocationType;
import org.talend.utils.files.FileUtils;
import org.talend.utils.io.FilesUtils;

/**
 * created by ycbai on 2017年5月18日 Detailled comment
 *
 */
public class ComponentP2ExtraFeature extends P2ExtraFeature {

    public static final String INDEX = "index"; //$NON-NLS-1$

    public static final String FOLDER_COMPS = "components"; //$NON-NLS-1$

    public static final String FOLDER_M2_REPOSITORY = "m2/repository"; //$NON-NLS-1$

    public static final String COMPONENT_GROUP_ID = "org.talend.components"; //$NON-NLS-1$

    private String product, mvnURI;

    private boolean isLogin;

    private File tmpM2RepoFolder;

    public ComponentP2ExtraFeature() {
        //
    }

    public ComponentP2ExtraFeature(String name, String version, String description, String product, String mvnURI,
            String p2IuId) {
        this.name = name;
        this.version = version;
        this.description = description;
        this.product = product;
        this.mvnURI = mvnURI;
        this.p2IuId = p2IuId;

        this.useLegacyP2Install = true; // enable to modify the config.ini
        this.mustBeInstalled = false;
    }

    @Override
    public EnumSet<UpdateSiteLocationType> getUpdateSiteCompatibleTypes() {
        return EnumSet.of(UpdateSiteLocationType.DEFAULT_REPO);
    }

    public MavenArtifact getIndexArtifact() {
        MavenArtifact artifact = new MavenArtifact();
        artifact.setGroupId(COMPONENT_GROUP_ID);
        artifact.setArtifactId(INDEX);
        artifact.setVersion(getTalendVersionStr());
        artifact.setType(FileExtensions.XML_EXTENSION);
        return artifact;
    }

    public MavenArtifact getArtifact() {
        return MavenUrlHelper.parseMvnUrl(mvnURI);
    }

    protected String getTalendVersionStr() {
        org.osgi.framework.Version studioVersion = new org.osgi.framework.Version(VersionUtils.getTalendVersion());

        StringBuffer result = new StringBuffer();
        result.append(studioVersion.getMajor());
        result.append('.');
        result.append(studioVersion.getMinor());
        result.append('.');
        result.append(studioVersion.getMicro());

        return result.toString();
    }

    public String getProduct() {
        return product;
    }

    public String getMvnURI() {
        return mvnURI;
    }

    public void setLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

    @Override
    public IStatus install(IProgressMonitor progress, List<URI> allRepoUris) throws P2ExtraFeatureException {
        IStatus doInstallStatus = null;
        File configIniBackupFile = null;
        try {
            if (!useLegacyP2Install) {
                // backup the config.ini
                configIniBackupFile = copyConfigFile(null);
            } // else legacy p2 install will update the config.ini
            doInstallStatus = doInstall(progress, allRepoUris);
        } catch (IOException e) {
            throw new P2ExtraFeatureException(
                    new ProvisionException(Messages.createErrorStatus(e, "ExtraFeaturesFactory.restore.config.error"))); //$NON-NLS-1$
        } finally {
            if (doInstallStatus != null && doInstallStatus.isOK()) {
                afterInstall(allRepoUris);
                storeInstalledFeatureMessage();
            }
            // restore the config.ini
            if (configIniBackupFile != null) { // must existed backup file.
                try {
                    copyConfigFile(configIniBackupFile);
                } catch (IOException e) {
                    throw new P2ExtraFeatureException(
                            new ProvisionException(Messages.createErrorStatus(e, "ExtraFeaturesFactory.back.config.error"))); //$NON-NLS-1$
                }
            }
        }
        return doInstallStatus;
    }

    @Override
    protected IStatus doInstall(IProgressMonitor progress, List<URI> allRepoUris) throws P2ExtraFeatureException {
        SubMonitor subMonitor = SubMonitor.convert(progress, 5);
        subMonitor.setTaskName(Messages.getString("ComponentP2ExtraFeature.installing.components", getName())); //$NON-NLS-1$
        // reset isInstalled to make is compute the next time is it used
        isInstalled = null;
        // we are not using this bundles context caus it fails to be aquired in junit test
        Bundle bundle = FrameworkUtil.getBundle(org.eclipse.equinox.p2.query.QueryUtil.class);
        BundleContext context = bundle.getBundleContext();

        ServiceReference sr = context.getServiceReference(IProvisioningAgentProvider.SERVICE_NAME);
        if (sr == null) {
            return Messages.createErrorStatus(null, "ExtraFeature.p2.agent.service.not.found", getName(), //$NON-NLS-1$
                    getVersion());
        }
        IProvisioningAgentProvider agentProvider = (IProvisioningAgentProvider) context.getService(sr);
        if (agentProvider == null) {
            return Messages.createErrorStatus(null, "ExtraFeature.p2.agent.provider.not.found", getName(), getVersion()); //$NON-NLS-1$
        }

        IProvisioningAgent agent = null;
        try {
            agent = agentProvider.createAgent(getP2AgentUri());

            updateRoamingProp(agent, agentProvider);

            Set<IInstallableUnit> toInstall = getInstallableIU(agent, allRepoUris, subMonitor.newChild(1));
            if (subMonitor.isCanceled()) {
                return Messages.createCancelStatus("ComponentP2ExtraFeature.user.cancel.installation.of.components", //$NON-NLS-1$
                        getName(), getVersion());
            }
            // show the installation unit
            log.debug("ius to be installed:" + toInstall); //$NON-NLS-1$
            if (toInstall.isEmpty()) {
                return Messages.createErrorStatus(null, "ComponentP2ExtraFeature.could.not.find.components", getName(), //$NON-NLS-1$
                        getP2IuId(),
                        Arrays.toString(allRepoUris.toArray(new URI[allRepoUris.size()])));
            }

            // install
            InstallOperation installOperation = new InstallOperation(new ProvisioningSession(agent), toInstall);
            installOperation.setProfileId(getP2ProfileId());
            IStatus installResolvedStatus = installOperation.resolveModal(subMonitor.newChild(1));
            if (subMonitor.isCanceled()) {
                return Messages.createCancelStatus("ComponentP2ExtraFeature.user.cancel.installation.of.components", //$NON-NLS-1$
                        getName(), getVersion());
            }
            if (installResolvedStatus.getSeverity() == IStatus.ERROR) {
                return Messages.createErrorStatus(null, "ComponentP2ExtraFeature.error.installing.new.components", getName(), //$NON-NLS-1$
                        getVersion(), installOperation.getResolutionDetails());
            } // else perform the installlation
            IPhaseSet talendPhaseSet = PhaseSetFactory
                    .createDefaultPhaseSetExcluding(new String[] { PhaseSetFactory.PHASE_CHECK_TRUST });

            ProfileModificationJob provisioningJob = (ProfileModificationJob) installOperation
                    .getProvisioningJob(subMonitor.newChild(1));
            if (subMonitor.isCanceled()) {
                return Messages.createCancelStatus("ComponentP2ExtraFeature.user.cancel.installation.of.components", //$NON-NLS-1$
                        getName(), getVersion());
            }
            if (provisioningJob == null) {
                return Messages.createErrorStatus(null, "ComponentP2ExtraFeature.error.installing.new.components", //$NON-NLS-1$
                        getName(), getVersion(), installOperation.getResolutionDetails());
            }
            provisioningJob.setPhaseSet(talendPhaseSet);
            IStatus status = provisioningJob.run(subMonitor.newChild(1));
            if (subMonitor.isCanceled()) {
                return Messages.createCancelStatus("ComponentP2ExtraFeature.user.cancel.installation.of.components", //$NON-NLS-1$
                        getName(), getVersion());
            }

            log.debug("installed new components " + getName() + " (" + getVersion() + ") with status :" + status); //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
        } catch (URISyntaxException e) {
            return Messages.createErrorStatus(e, "ComponentP2ExtraFeature.error.installing.components.uri.exception", getName(), //$NON-NLS-1$
                    getVersion());
        } catch (ProvisionException e) {
            return Messages.createErrorStatus(e, "ComponentP2ExtraFeature.error.installing.components.uri.exception", getName(), //$NON-NLS-1$
                    getVersion());
        } finally {
            if (agent != null) {// agent creation did not fail
                removeAllRepositories(agent, allRepoUris);
                agent.stop();
            }
        }
        return Messages.createOkStatus("sucessfull.install.of.components", getName(), getVersion()); //$NON-NLS-1$
    }

    protected void afterInstall(List<URI> allRepoUris) throws P2ExtraFeatureException {
        if (allRepoUris == null || allRepoUris.size() == 0) {
            return;
        }
        try {
            // try to move install success to installed folder
            File installedFolder = new File(Platform.getConfigurationLocation().getDataArea(FOLDER_COMPS).getPath());
            final File installedComponentFolder = new File(installedFolder, FOLDER_COMPS);
            if (!installedComponentFolder.exists()) {
                installedComponentFolder.mkdirs();
            }
            for (URI uri : allRepoUris) {
                File compFile = new File(uri);
                if (compFile.exists()) {
                    // sync the component libraries
                    File tempUpdateSiteFolder = getTempUpdateSiteFolder();
                    FilesUtils.unzip(compFile.getAbsolutePath(), tempUpdateSiteFolder.getAbsolutePath());
                    syncLibraries(tempUpdateSiteFolder);
                    syncM2Repository(tempUpdateSiteFolder);
                    // TODO: deploy to local nexus
                    FilesUtils.copyFile(compFile, new File(installedComponentFolder, compFile.getName()));
                    compFile.delete(); // delete original file.
                }
            }
        } catch (Exception e) {
            throw new P2ExtraFeatureException(e);
        }
    }

    protected void syncLibraries(File updatesiteFolder) throws IOException {
        if (updatesiteFolder.exists() && updatesiteFolder.isDirectory()) {
            // sync to product lib/java
            final File productLibFolder = new File(LibrariesManagerUtils.getLibrariesPath());
            File updatesiteLibFolder = new File(updatesiteFolder, LibrariesManagerUtils.LIB_JAVA_SUB_FOLDER);
            if (updatesiteLibFolder.exists()) {
                final File[] listFiles = updatesiteLibFolder.listFiles();
                if (listFiles != null && listFiles.length > 0) {
                    if (!productLibFolder.exists()) {
                        productLibFolder.mkdirs();
                    }
                    FilesUtils.copyFolder(updatesiteLibFolder, productLibFolder, false, null, null, false);
                }
            }
        }
    }

    protected void syncM2Repository(File updatesiteFolder) throws IOException {
        // sync to the local m2 repository, if need try to deploy to remote TAC Nexus.
        File updatesiteLibFolder = new File(updatesiteFolder, FOLDER_M2_REPOSITORY); // m2/repositroy
        if (updatesiteLibFolder.exists() && updatesiteFolder.isDirectory()) {
            final File[] listFiles = updatesiteLibFolder.listFiles();
            if (listFiles != null && listFiles.length > 0) {
                // if have remote nexus, install component too early and before logon project , will cause the problem
                // (TUP-17604)
                if (isLogin) {
                    // prepare to install lib after logon. so copy all to temp folder
                    FileCopyUtils.copyFolder(updatesiteLibFolder, new File(getTempM2RepoFolder(), FOLDER_M2_REPOSITORY));
                } else {
                    // install to local and try to deploy to remote nexus
                    MavenRepoSynchronizer synchronizer = new MavenRepoSynchronizer(updatesiteLibFolder);
                    synchronizer.sync();
                }
            }
        }
    }

    protected File getTempUpdateSiteFolder() {
        return FileUtils.createTmpFolder("p2updatesite", null); //$NON-NLS-1$
    }

    protected File getTempM2RepoFolder() {
        if (tmpM2RepoFolder == null) {
            tmpM2RepoFolder = new File(getTmpFolder(), "m2temp"); //$NON-NLS-1$
        }
        return tmpM2RepoFolder;
    }

    protected File getTmpFolder() {
        return new File(System.getProperty("user.dir") + '/' + FOLDER_COMPS); //$NON-NLS-1$
    }

}
