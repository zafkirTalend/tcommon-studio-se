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
package org.talend.repository.items.importexport.handlers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Iterator;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.talend.commons.utils.io.FileCopyUtils;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.PropertiesPackage;
import org.talend.core.repository.constants.FileConstants;
import org.talend.repository.items.importexport.handlers.model.ImportItem;
import org.talend.repository.items.importexport.manager.ResourcesManager;

/**
 * DOC ggu class global comment. Detailled comment
 */
public final class HandlerUtil {

    public static URI getURI(IPath path) {
        return URI.createURI(path.lastSegment());
    }

    public static IPath getItemPath(IPath path, Item item) {
        IPath removeFileExtension = path.removeFileExtension();
        if (!item.isNeedVersion()) {
            String portableString = removeFileExtension.toPortableString();
            String substring = portableString.substring(0, portableString.lastIndexOf('_'));
            removeFileExtension = new Path(substring);
        }
        if (item.getFileExtension() != null) {
            return removeFileExtension.addFileExtension(item.getFileExtension());
        } else {
            return removeFileExtension.addFileExtension(FileConstants.ITEM_EXTENSION);
        }
    }

    public static IPath getReferenceItemPath(IPath path, String extension) {
        return path.removeFileExtension().addFileExtension(extension);
    }

    public static IPath getValidProjectFilePath(ResourcesManager collector, IPath path) {
        IPath projectFilePath = path.removeLastSegments(1);

        while (projectFilePath.lastSegment() != null
                && !collector.getPaths().contains(projectFilePath.append(FileConstants.LOCAL_PROJECT_FILENAME))) {
            projectFilePath = projectFilePath.removeLastSegments(1);
        }
        if (collector.getPaths().contains(projectFilePath.append(FileConstants.LOCAL_PROJECT_FILENAME))) {
            return projectFilePath.append(FileConstants.LOCAL_PROJECT_FILENAME);
        }
        return null;
    }

    // added by dlin 2011-7-25 don't like .item and .property ,just copy .screenshot file will be ok
    public static void copyScreenshotFile(ResourcesManager manager, ImportItem itemRecord) throws IOException {
        int id = itemRecord.getItem().eClass().getClassifierID();
        if (id != PropertiesPackage.PROCESS_ITEM && id != PropertiesPackage.JOBLET_PROCESS_ITEM) {
            return;
        }
        OutputStream os = null;
        InputStream is = null;
        try {
            URI propertyResourceURI = EcoreUtil.getURI(itemRecord.getItem().getProperty());
            URI relativePlateformDestUri = propertyResourceURI.trimFileExtension().appendFileExtension(
                    FileConstants.SCREENSHOT_EXTENSION);
            URL fileURL = FileLocator.toFileURL(new java.net.URL(
                    "platform:/resource" + relativePlateformDestUri.toPlatformString(true))); //$NON-NLS-1$
            // for migration task ,there is not .screeenshot file in preceding version - begin
            boolean hasScreenshotFile = false;
            Iterator it = manager.getPaths().iterator();
            IPath screenshotNeeded = itemRecord.getPath().removeFileExtension()
                    .addFileExtension(FileConstants.SCREENSHOT_EXTENSION);
            while (it.hasNext()) {
                IPath path = (IPath) it.next();
                if (path.equals(screenshotNeeded)) {
                    hasScreenshotFile = true;
                    break;
                }
            }
            if (!hasScreenshotFile) {
                return;
            }
            // for migration task ,there is not .screeenshot file in preceding version - begin
            os = new FileOutputStream(fileURL.getFile());
            manager.getPaths().iterator().next();
            is = manager.getStream(screenshotNeeded);
            FileCopyUtils.copyStreams(is, os);
        } finally {
            if (os != null) {
                os.close();
            }
            if (is != null) {
                is.close();
            }
        }
    }

    /**
     * get relative Path of the Item
     */
    public static IPath getValidItemRelativePath(ResourcesManager resManager, IPath path) {
        IPath projectFilePath = HandlerUtil.getValidProjectFilePath(resManager, path);
        if (projectFilePath != null) {
            // remove the last segments "talend.project"
            IPath projectRootPath = projectFilePath.removeLastSegments(1);
            // relative to import project
            return path.makeRelativeTo(projectRootPath);
        }
        return null;
    }
}
