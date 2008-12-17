// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.model.components;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;
import org.talend.commons.CommonsPlugin;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.utils.io.FilesUtils;

/***/
public abstract class AbstractComponentsProvider {

    private static Logger logger = Logger.getLogger(AbstractComponentsProvider.class);

    private String id;

    private String folderName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getComponentsLocation() {
        return new Path(IComponentsFactory.COMPONENTS_INNER_FOLDER).append(
                IComponentsFactory.EXTERNAL_COMPONENTS_INNER_FOLDER).append(folderName).toString();
    }

    public void preComponentsLoad() throws IOException {
        File installationFolder = getInstallationFolder();

        // clean folder
        if (installationFolder.exists()) {
            FilesUtils.removeFolder(installationFolder, true);
        }
        FilesUtils.createFoldersIfNotExists(installationFolder.getAbsolutePath(), false);

        File externalComponentsLocation = getExternalComponentsLocation();
        if (externalComponentsLocation != null) {
            if (externalComponentsLocation.exists()) {
                try {
                    FilesUtils.copyFolder(externalComponentsLocation, installationFolder, false, null, null, true);
                } catch (IOException e) {
                    ExceptionHandler.process(e);
                }
            } else {
                logger.warn("Folder " + externalComponentsLocation.toString() + " does not exist.");
            }
        }
    }

    protected abstract File getExternalComponentsLocation();

    public File getInstallationFolder() throws IOException {
        Bundle b = Platform.getBundle(IComponentsFactory.COMPONENTS_LOCATION);

        File installationFolder = null;
        IPath nullPath = new Path("");
        URL url = FileLocator.find(b, nullPath, null);
        URL fileUrl = FileLocator.toFileURL(url);
        File bundleFolder = new File(fileUrl.getPath());

        IPath path = new Path(IComponentsFactory.COMPONENTS_INNER_FOLDER).append(
                IComponentsFactory.EXTERNAL_COMPONENTS_INNER_FOLDER);
        
        // bug fix : several headless instance should not use the same folder
        if (CommonsPlugin.isHeadless()) {
            String workspaceName = ResourcesPlugin.getWorkspace().getRoot().getLocation().lastSegment();
            path = path.append(folderName + "-" + workspaceName);
        } else {
            path = path.append(folderName);
        }
        
        installationFolder = new File(bundleFolder, path.toOSString());

        return installationFolder;
    }
}
