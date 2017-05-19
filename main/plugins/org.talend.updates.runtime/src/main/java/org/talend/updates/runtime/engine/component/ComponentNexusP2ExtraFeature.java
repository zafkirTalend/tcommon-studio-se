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
package org.talend.updates.runtime.engine.component;

import java.io.File;
import java.net.URI;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.talend.designer.maven.utils.PomUtil;
import org.talend.updates.runtime.i18n.Messages;
import org.talend.updates.runtime.model.P2ExtraFeatureException;
import org.talend.utils.io.FilesUtils;

/**
 * DOC ggu class global comment. Detailled comment
 */
public class ComponentNexusP2ExtraFeature extends ComponentP2ExtraFeature {

    public static final String PROP_KEY_NEXUS_URL = "components.nexus.url"; //$NON-NLS-1$

    public static final String PROP_KEY_NEXUS_USER = "components.nexus.user"; //$NON-NLS-1$

    public static final String PROP_KEY_NEXUS_PASS = "components.nexus.pass"; //$NON-NLS-1$

    private boolean upgrade;

    private String nexusURL, nexusUser;

    private char[] nexusPass;

    protected File downloadFolder;

    protected boolean restartable;

    protected boolean keepDownloadFile = false;

    public ComponentNexusP2ExtraFeature() {
        super();
    }

    public ComponentNexusP2ExtraFeature(String name, String version, String description, String product, String mvnURI,
            String p2IuId) {
        super(name, version, description, product, mvnURI, p2IuId);
    }

    public String getNexusURL() {
        if (nexusURL == null) {
            setNexusURL(System.getProperty(PROP_KEY_NEXUS_URL));
        }
        return nexusURL;
    }

    public void setNexusURL(String nexusURL) {
        this.nexusURL = nexusURL;
        this.baseRepoUriStr = nexusURL; // same url
    }

    public String getNexusUser() {
        if (nexusUser == null) {
            setNexusUser(System.getProperty(PROP_KEY_NEXUS_USER));
        }
        return nexusUser;
    }

    public void setNexusUser(String nexusUser) {
        this.nexusUser = nexusUser;
    }

    public char[] getNexusPass() {
        if (nexusPass == null) {
            final String pass = System.getProperty(PROP_KEY_NEXUS_PASS);
            if (StringUtils.isNotEmpty(pass)) {
                setNexusPass(pass.toCharArray());
            }
        }
        return nexusPass;
    }

    public void setNexusPass(char[] nexusPass) {
        this.nexusPass = nexusPass;
    }

    public void setNexusPass(String nexusPass) {
        if (StringUtils.isNotEmpty(nexusPass)) {
            setNexusPass(nexusPass.toCharArray());
        } else {
            setNexusPass((char[]) null);
        }
    }

    public File getDownloadFolder() {
        return downloadFolder;
    }

    public void setDownloadFolder(File downloadFolder) {
        this.downloadFolder = downloadFolder;
    }

    public boolean isKeepDownloadFile() {
        return keepDownloadFile;
    }

    public void setKeepDownloadFile(boolean keepDownloadFile) {
        this.keepDownloadFile = keepDownloadFile;
    }

    public boolean isUpgrade() {
        return upgrade;
    }

    @Override
    public boolean needRestart() {
        return restartable;
    }

    @Override
    public IStatus install(IProgressMonitor progress, List<URI> allRepoUris) throws P2ExtraFeatureException {
        if (progress.isCanceled()) {
            throw new OperationCanceledException();
        }
        final File workFolder = getDownloadFolder();
        if (!isKeepDownloadFile()) {
            FilesUtils.deleteFolder(workFolder, false); // empty the folder
        }
        if (!workFolder.exists()) {
            workFolder.mkdirs();
        }

        String reletivePath = PomUtil.getArtifactPath(getArtifact());
        if (reletivePath == null) {
            return Messages.createErrorStatus(null, "Can't install"); //$NON-NLS-1$
        }

        String compFileName = new Path(reletivePath).lastSegment();
        final File target = new File(workFolder, compFileName);

        try {
            ComponentsNexusTransport transport = new ComponentsNexusTransport(getNexusURL(), getNexusUser(), getNexusPass());
            transport.downloadFile(progress, getMvnURI(), target);

            if (progress.isCanceled()) {
                throw new OperationCanceledException();
            }
            if (!target.exists()) {
                return Messages.createErrorStatus(null, "failed.install.of.feature", "Download the failure for " + getMvnURI()); //$NON-NLS-1$ //$NON-NLS-2$
            }

            return super.install(progress, allRepoUris);
        } catch (Exception e) {
            return Messages.createErrorStatus(e);
        } finally {
            if (!isKeepDownloadFile() && target.exists()) {
                target.delete();
            }
        }
    }

}
