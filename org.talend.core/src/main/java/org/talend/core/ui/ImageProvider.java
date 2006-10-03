// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
//
// ============================================================================
package org.talend.core.ui;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.talend.core.CorePlugin;
import org.talend.core.model.repository.ERepositoryObjectType;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class ImageProvider {

    /**
     * 
     * DOC smallet ImageProvider class global comment. Detailled comment <br/>
     * 
     * $Id$
     * 
     */
    public enum EImage {
        DEFAULT_IMAGE,
        PROJECT_WIZ("/icons/project_wiz.png"),
        BUSINESS_PROCESS_ICON(IImageConstants.BUSINESS_PROCESS_ICON),
        BUSINESS_PROCESS_WIZ("/icons/business_wiz.png"),
        PROCESS_ICON(IImageConstants.PROCESS_ICON),
        PROCESS_WIZ("/icons/process_wiz.png"),
        ROUTINE_ICON(IImageConstants.ROUTINE_ICON),
        SNIPPETS_ICON(IImageConstants.SNIPPET_ICON),
        SUBROUTINE_ICON(IImageConstants.ICONS_SUBROUTINE_GIF),
        DOCUMENTATION_ICON(IImageConstants.DOCUMENTATION_ICON),
        DOCUMENTATION_WIZ("/icons/documentation_wiz.png"),
        METADATA_ICON(IImageConstants.METADATA_ICON),
        METADATA_CONNECTION_ICON(IImageConstants.CONNECTION_ICON),
        METADATA_CONNECTION_WIZ("/icons/connection_wiz.png"),
        METADATA_TABLE_ICON(IImageConstants.TABLE_ICON),
        METADATA_FILE_DELIMITED_ICON(IImageConstants.FILE_DELIMITED_ICON),
        METADATA_FILE_DELIMITED_WIZ("/icons/delimited_wiz.png"),
        METADATA_FILE_POSITIONAL_ICON(IImageConstants.FILE_POSITIONAL_ICON),
        METADATA_FILE_POSITIONAL_WIZ("/icons/positional_wiz.png"),
        METADATA_FILE_REGEXP_ICON(IImageConstants.FILE_REGEXP_ICON),
        METADATA_FILE_REGEXP_WIZ("/icons/regexp_wiz.png"),
        RECYCLE_BIN_EMPTY_ICON(IImageConstants.RECYCLE_BIN_EMPTY_ICON),
        RECYCLE_BIN_FULL_ICON(IImageConstants.RECYCLE_BIN_FULL_ICON),
        FOLDER_OPEN_ICON(IImageConstants.FOLDER_OPEN_ICON),
        FOLDER_CLOSE_ICON(IImageConstants.FOLDER_CLOSE_ICON),
        FOLDER_WIZ("/icons/folder_wiz.png"),
        SAVE_ICON(IImageConstants.SAVE_ICON),
        COPY_ICON(IImageConstants.COPY_ICON),
        PASTE_ICON(IImageConstants.PASTE_ICON),
        CUT_ICON(IImageConstants.CUT_ICON),
        DELETE_ICON(IImageConstants.DELETE_ICON),
        RESTORE_ICON(IImageConstants.ADD_ICON),
        LOAD_ICON(IImageConstants.LOAD_ICON),
        EXPORT_ICON(IImageConstants.EXPORT_ICON),
        LOCK_ICON(IImageConstants.LOCK_ICON),
        REFRESH_ICON(IImageConstants.REFRESH_ICON),
        UP_ICON(IImageConstants.UP_ICON),
        DOWN_ICON(IImageConstants.DOWN_ICON),
        ADD_ICON(IImageConstants.ADD_ICON),
        MINUS_ICON(IImageConstants.MINUS_ICON),
        KEY(IImageConstants.KEY_ICON),
        EMPTY16(IImageConstants.EMPTY16_ICON),
        DOC_WORD_ICON(IImageConstants.DOC_WORD_ICON),
        DOC_EXCEL_ICON(IImageConstants.DOC_EXCEL_ICON),
        DOC_POWERPOINT_ICON(IImageConstants.DOC_POWERPOINT_ICON),
        DOC_PDF_ICON(IImageConstants.DOC_PDF_ICON),
        DOC_IMAGE_ICON(IImageConstants.DOC_IMAGE_ICON),
        ERROR_ICON("/icons/error.gif"),
        MODULE_INSTALLED_ICON("/icons/checked.gif"),
        MODULE_ERROR_ICON("/icons/error.gif"),
        MODULE_WARNING_ICON("/icons/warning.gif"),
        MODULE_UNKNOWN_ICON("/icons/unknown.gif"),
        MODULE_REQUIRED_ICON("/icons/add.png"),
        MODULE_NOTREQUIRED_ICON("/icons/minus.png");

        private String path;

        EImage() {
        }

        EImage(String path) {
            this.path = path;
        }

        /**
         * Getter for path.
         * 
         * @return the path
         */
        public String getPath() {
            return this.path;
        }
    }

    public static Image getImage(ImageDescriptor desc) {
        return desc.createImage();
    }

    private static Map<EImage, ImageDescriptor> cacheDescriptors = new HashMap<EImage, ImageDescriptor>();

    private static Map<EImage, Image> cacheImages = new HashMap<EImage, Image>();

    public static Image getImage(EImage image) {
        Image toReturn = cacheImages.get(image);
        if (toReturn == null) {
            ImageDescriptor desc = getImageDesc(image);
            toReturn = desc.createImage();
            cacheImages.put(image, toReturn);
        }
        return toReturn;
    }

    public static ImageDescriptor getImageDesc(EImage image) {
        switch (image) {
        case DEFAULT_IMAGE:
            return PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_ELEMENT);
        default:
            ImageDescriptor toReturn = cacheDescriptors.get(image);
            if (toReturn == null) {
                toReturn = ImageDescriptor.createFromFile(CorePlugin.class, image.getPath());
                cacheDescriptors.put(image, toReturn);
            }
            return toReturn;
        }
    }

    public static Image getImage(ERepositoryObjectType type) {
        switch (type) {
        case BUSINESS_PROCESS:
            return ImageProvider.getImage(EImage.BUSINESS_PROCESS_ICON);
        case PROCESS:
            return ImageProvider.getImage(EImage.PROCESS_ICON);
        case ROUTINES:
            return ImageProvider.getImage(EImage.ROUTINE_ICON);
        case SNIPPETS:
            return ImageProvider.getImage(EImage.SNIPPETS_ICON);
        case DOCUMENTATION:
            return ImageProvider.getImage(EImage.DOCUMENTATION_ICON);
        case METADATA:
            return ImageProvider.getImage(EImage.METADATA_ICON);
        case METADATA_CONNECTIONS:
            return ImageProvider.getImage(EImage.METADATA_CONNECTION_ICON);
        case METADATA_CON_TABLE:
            return ImageProvider.getImage(EImage.METADATA_TABLE_ICON);
        case METADATA_FILE_DELIMITED:
            return ImageProvider.getImage(EImage.METADATA_FILE_DELIMITED_ICON);
        case METADATA_FILE_POSITIONAL:
            return ImageProvider.getImage(EImage.METADATA_FILE_POSITIONAL_ICON);
        case METADATA_FILE_REGEXP:
            return ImageProvider.getImage(EImage.METADATA_FILE_REGEXP_ICON);
        case RECYCLE_BIN:
            return ImageProvider.getImage(EImage.RECYCLE_BIN_EMPTY_ICON);
        case FOLDER:
            return ImageProvider.getImage(EImage.FOLDER_OPEN_ICON);
        default:
            return ImageProvider.getImage(EImage.DEFAULT_IMAGE);
        }
    }

    public static ImageDescriptor getImageDesc(ERepositoryObjectType type) {
        switch (type) {
        case BUSINESS_PROCESS:
            return ImageProvider.getImageDesc(EImage.BUSINESS_PROCESS_ICON);
        case PROCESS:
            return ImageProvider.getImageDesc(EImage.PROCESS_ICON);
        case ROUTINES:
            return ImageProvider.getImageDesc(EImage.ROUTINE_ICON);
        case DOCUMENTATION:
            return ImageProvider.getImageDesc(EImage.DOCUMENTATION_ICON);
        case METADATA:
            return ImageProvider.getImageDesc(EImage.METADATA_ICON);
        case METADATA_CONNECTIONS:
            return ImageProvider.getImageDesc(EImage.METADATA_CONNECTION_ICON);
        case METADATA_CON_TABLE:
            return ImageProvider.getImageDesc(EImage.METADATA_TABLE_ICON);
        case METADATA_FILE_DELIMITED:
            return ImageProvider.getImageDesc(EImage.METADATA_FILE_DELIMITED_ICON);
        case METADATA_FILE_POSITIONAL:
            return ImageProvider.getImageDesc(EImage.METADATA_FILE_POSITIONAL_ICON);
        case METADATA_FILE_REGEXP:
            return ImageProvider.getImageDesc(EImage.METADATA_FILE_REGEXP_ICON);
        case RECYCLE_BIN:
            return ImageProvider.getImageDesc(EImage.RECYCLE_BIN_EMPTY_ICON);
        default:
            return ImageProvider.getImageDesc(EImage.DEFAULT_IMAGE);
        }
    }

    public static Image getImage(String extOriginal) {
        if (extOriginal != null) {
            String ext = extOriginal.toLowerCase();
            if (Arrays.asList(new String[] { "doc", "dot" }).contains(ext)) {
                return getImage(EImage.DOC_WORD_ICON);
            } else if (Arrays.asList(new String[] { "xls", "xlt" }).contains(ext)) {
                return getImage(EImage.DOC_EXCEL_ICON);
            } else if (Arrays.asList(new String[] { "ppt", "pps", "pot" }).contains(ext)) {
                return getImage(EImage.DOC_POWERPOINT_ICON);
            } else if (Arrays.asList(new String[] { "pps" }).contains(ext)) {
                return getImage(EImage.DOC_PDF_ICON);
            } else if (Arrays.asList(new String[] { "gif", "jpg", "jpeg", "bmp", "gif", "png" }).contains(ext)) {
                return getImage(EImage.DOC_IMAGE_ICON);
            }
        }
        return getImage(ERepositoryObjectType.DOCUMENTATION);
    }
}
