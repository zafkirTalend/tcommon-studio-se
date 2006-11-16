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
        case METADATA_FILE_XML:
            return ImageProvider.getImage(EImage.METADATA_FILE_XML_ICON);
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
        case METADATA_FILE_XML:
            return ImageProvider.getImageDesc(EImage.METADATA_FILE_XML_ICON);
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
