// ============================================================================
//
// Copyright (C) 2006-2010 Talend Inc. - www.talend.com
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
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.commons.utils.io.FilesUtils;
import org.talend.core.i18n.Messages;

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
        return new Path(IComponentsFactory.COMPONENTS_INNER_FOLDER).append(IComponentsFactory.EXTERNAL_COMPONENTS_INNER_FOLDER)
                .append(ComponentUtilities.getExtFolder(folderName)).toString();
    }

    public void preComponentsLoad() throws IOException {
        File installationFolder = getInstallationFolder();

        FilesUtils.createFoldersIfNotExists(installationFolder.getAbsolutePath(), false);

        File externalComponentsLocation = getExternalComponentsLocation();
        if (externalComponentsLocation != null) {
            if (externalComponentsLocation.exists()) {
                try {
                    FileFilter ff = new FileFilter() {

                        public boolean accept(File pathname) {
                            if (pathname.getName().equals(".svn")) {
                                return false;
                            }
                            return true;
                        }

                    };
                    FilesUtils.copyFolder(externalComponentsLocation, installationFolder, false, ff, null, true, true);
                } catch (IOException e) {
                    ExceptionHandler.process(e);
                }
            } else {
                logger.warn(Messages
                        .getString("AbstractComponentsProvider.folderNotExist", externalComponentsLocation.toString())); //$NON-NLS-1$
            }
        }
    }

    protected abstract File getExternalComponentsLocation();

    public File getInstallationFolder() throws IOException {
        Bundle b = Platform.getBundle(IComponentsFactory.COMPONENTS_LOCATION);

        File installationFolder = null;
        IPath nullPath = new Path(""); //$NON-NLS-1$
        URL url = FileLocator.find(b, nullPath, null);
        URL fileUrl = FileLocator.toFileURL(url);
        File bundleFolder = new File(fileUrl.getPath());

        IPath path = new Path(IComponentsFactory.COMPONENTS_INNER_FOLDER)
                .append(IComponentsFactory.EXTERNAL_COMPONENTS_INNER_FOLDER);

        path = path.append(ComponentUtilities.getExtFolder(folderName));

        installationFolder = new File(bundleFolder, path.toOSString());

        return installationFolder;
    }

    public String getFamilyTranslation(String text) {
        return null;
    }
}
