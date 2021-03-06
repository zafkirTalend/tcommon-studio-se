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
package org.talend.librariesmanager.utils.nexus;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.talend.commons.utils.workbench.resources.ResourceUtils;
import org.talend.core.download.DownloadListener;
import org.talend.core.download.IDownloadHelper;
import org.talend.core.model.general.ModuleNeeded.ELibraryInstallStatus;
import org.talend.core.model.general.ModuleStatusProvider;
import org.talend.core.model.general.Project;
import org.talend.core.nexus.NexusServerBean;
import org.talend.core.nexus.NexusServerUtils;
import org.talend.core.nexus.TalendLibsServerManager;
import org.talend.core.runtime.maven.MavenArtifact;
import org.talend.core.runtime.maven.MavenConstants;
import org.talend.core.runtime.maven.MavenUrlHelper;
import org.talend.designer.maven.utils.PomUtil;
import org.talend.librariesmanager.maven.ArtifactsDeployer;
import org.talend.repository.ProjectManager;
import org.talend.utils.io.FilesUtils;

/**
 * created by wchen on Apr 24, 2015 Detailled comment
 *
 */
public class NexusDownloader implements IDownloadHelper {

    private List<DownloadListener> fListeners = new ArrayList<DownloadListener>();

    private boolean fCancel = false;

    private static final int BUFFER_SIZE = 8192;

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.download.IDownloadHelper#download(java.net.URL, java.io.File)
     */
    @Override
    public void download(URL url, File desc) throws Exception {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        File tempFolder = null;
        try {
            TalendLibsServerManager manager = TalendLibsServerManager.getInstance();
            final NexusServerBean talendlibServer = manager.getLibrariesNexusServer();
            String mavenUri = url.toExternalForm();
            MavenArtifact parseMvnUrl = MavenUrlHelper.parseMvnUrl(mavenUri);
            if (parseMvnUrl != null) {
                String reletivePath = PomUtil.getArtifactPath(parseMvnUrl);
                String tempPath = getTmpFolderPath();
                File createTempFile = File.createTempFile("talend_official", "");
                createTempFile.delete();
                tempFolder = new File(tempPath + File.separator + createTempFile.getName());
                if (tempFolder.exists()) {
                    tempFolder.delete();
                }
                tempFolder.mkdirs();

                String name = parseMvnUrl.getArtifactId();
                String type = parseMvnUrl.getType();
                if (type == null || "".equals(type)) {
                    type = MavenConstants.PACKAGING_JAR;
                }
                name = name + "." + type;
                File destination = new File(tempFolder, name);
                HttpURLConnection connection = getHttpURLConnection(talendlibServer.getServer(),
                        talendlibServer.getRepositoryId(), reletivePath, talendlibServer.getUserName(),
                        talendlibServer.getPassword());

                InputStream inputStream = connection.getInputStream();
                bis = new BufferedInputStream(inputStream);
                bos = new BufferedOutputStream(new FileOutputStream(destination));
                int contentLength = connection.getContentLength();
                fireDownloadStart(contentLength);

                int bytesDownloaded = 0;
                byte[] buf = new byte[BUFFER_SIZE];
                int bytesRead = -1;
                while ((bytesRead = bis.read(buf)) != -1) {
                    bos.write(buf, 0, bytesRead);
                    fireDownloadProgress(bytesRead);
                    bytesDownloaded += bytesRead;
                    if (isCancel()) {
                        return;
                    }
                }
                bos.flush();
                bos.close();
                if (bytesDownloaded == contentLength) {
                    ArtifactsDeployer deployer = new ArtifactsDeployer();
                    deployer.deployToLocalMaven(destination.getAbsolutePath(), mavenUri);
                    // update module status
                    final Map<String, ELibraryInstallStatus> statusMap = ModuleStatusProvider.getStatusMap();
                    statusMap.put(mavenUri, ELibraryInstallStatus.INSTALLED);
                    ModuleStatusProvider.getDeployStatusMap().put(mavenUri, ELibraryInstallStatus.DEPLOYED);
                }
            }
            fireDownloadComplete();
        } finally {
            if (bis != null) {
                bis.close();
            }
            if (bos != null) {
                bos.close();
            }
            if (tempFolder != null) {
                FilesUtils.deleteFile(tempFolder, true);
            }
        }

    }

    private String getTmpFolderPath() {
        Project project = ProjectManager.getInstance().getCurrentProject();
        String tmpFolder;
        try {
            IProject physProject = ResourceUtils.getProject(project);
            tmpFolder = physProject.getFolder("temp").getLocation().toPortableString(); //$NON-NLS-1$
        } catch (Exception e) {
            tmpFolder = System.getProperty("user.dir"); //$NON-NLS-1$
        }
        return tmpFolder;
    }

    private HttpURLConnection getHttpURLConnection(String nexusUrl, String repositoryId, String relativePath, String userName,
            String password) throws Exception {
        return NexusServerUtils.getHttpURLConnection(nexusUrl, repositoryId, relativePath, userName, password);
    }

    /**
     * Return true if the user cancel download process.
     * 
     * @return the cancel
     */
    public boolean isCancel() {
        return fCancel;
    }

    /**
     * Set true if the user cacel download process.
     * 
     * @param cancel the cancel to set
     */
    @Override
    public void setCancel(boolean cancel) {
        fCancel = cancel;
    }

    /**
     * Notify listeners about progress.
     * 
     * @param bytesRead
     */
    private void fireDownloadProgress(int bytesRead) {
        for (DownloadListener listener : fListeners) {
            listener.downloadProgress(this, bytesRead);
        }
    }

    /**
     * Notify listeners at the end of download process.
     */
    private void fireDownloadComplete() {
        for (DownloadListener listener : fListeners) {
            listener.downloadComplete();
        }
    }

    /**
     * Notify listeners at the begining of download process.
     */
    private void fireDownloadStart(int contentLength) {
        for (DownloadListener listener : fListeners) {
            listener.downloadStart(contentLength);
        }

    }

    /**
     * Add listener to observe the download process.
     * 
     * @param listener
     */
    public void addDownloadListener(DownloadListener listener) {
        fListeners.add(listener);
    }

    public void removeDownloadListener(DownloadListener listener) {
        fListeners.remove(listener);
    }

}
