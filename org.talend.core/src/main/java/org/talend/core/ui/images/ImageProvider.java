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
package org.talend.core.ui.images;

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

    private static Map<String, ImageDescriptor> cacheDescriptors = new HashMap<String, ImageDescriptor>();

    private static Map<String, Image> cacheImages = new HashMap<String, Image>();

    public static Image getImage(EImage image) {
        Image toReturn = cacheImages.get(image.getPath());
        if (toReturn == null) {
            ImageDescriptor desc = getImageDesc(image);
            toReturn = desc.createImage();
            cacheImages.put(image.getPath(), toReturn);
        }
        return toReturn;
    }

    public static ImageDescriptor getImageDesc(EImage image) {
        switch (image) {
        case DEFAULT_IMAGE:
            return PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_ELEMENT);
        default:
            ImageDescriptor toReturn = cacheDescriptors.get(image.getPath());
            if (toReturn == null) {
                toReturn = ImageDescriptor.createFromFile(CorePlugin.class, image.getPath());
                cacheDescriptors.put(image.getPath(), toReturn);
            }
            return toReturn;
        }
    }

    public static String getImageCache() {
        return cacheImages.keySet().toString();
    }

    public static String getImageDescCache() {
        return cacheDescriptors.keySet().toString();
    }

    public static Image getImage(ERepositoryObjectType type) {
        return getImage(getIcon(type));
    }

    public static ImageDescriptor getImageDesc(ERepositoryObjectType type) {
        return getImageDesc(getIcon(type));
    }

    private static EImage getIcon(ERepositoryObjectType type) {
        switch (type) {
        case BUSINESS_PROCESS:
            return EImage.BUSINESS_PROCESS_ICON;
        case PROCESS:
            return EImage.PROCESS_ICON;
        case ROUTINES:
            return EImage.ROUTINE_ICON;
        case SNIPPETS:
            return EImage.SNIPPETS_ICON;
        case DOCUMENTATION:
            return EImage.DOCUMENTATION_ICON;
        case METADATA:
            return EImage.METADATA_ICON;
        case METADATA_CONNECTIONS:
            return EImage.METADATA_CONNECTION_ICON;
        case METADATA_CON_TABLE:
            return EImage.METADATA_TABLE_ICON;
        case METADATA_FILE_DELIMITED:
            return EImage.METADATA_FILE_DELIMITED_ICON;
        case METADATA_FILE_POSITIONAL:
            return EImage.METADATA_FILE_POSITIONAL_ICON;
        case METADATA_FILE_REGEXP:
            return EImage.METADATA_FILE_REGEXP_ICON;
        case METADATA_FILE_XML:
            return EImage.METADATA_FILE_XML_ICON;
        case METADATA_FILE_LDIF:
            return EImage.METADATA_FILE_LDIF_ICON;
        case RECYCLE_BIN:
            return EImage.RECYCLE_BIN_EMPTY_ICON;
        case FOLDER:
            return EImage.FOLDER_OPEN_ICON;
        default:
            return EImage.DEFAULT_IMAGE;
        }
    }

    public static Image getImage(String extOriginal) {
        if (extOriginal != null) {
            String ext = extOriginal.toLowerCase();
            if (Arrays.asList(new String[] { "doc", "dot" }).contains(ext)) {
                return ImageProvider.getImage(EImage.DOC_WORD_ICON);
            } else if (Arrays.asList(new String[] { "xls", "xlt" }).contains(ext)) {
                return ImageProvider.getImage(EImage.DOC_EXCEL_ICON);
            } else if (Arrays.asList(new String[] { "ppt", "pps", "pot" }).contains(ext)) {
                return ImageProvider.getImage(EImage.DOC_POWERPOINT_ICON);
            } else if (Arrays.asList(new String[] { "pps" }).contains(ext)) {
                return ImageProvider.getImage(EImage.DOC_PDF_ICON);
            } else if (Arrays.asList(new String[] { "gif", "jpg", "jpeg", "bmp", "gif", "png" }).contains(ext)) {
                return ImageProvider.getImage(EImage.DOC_IMAGE_ICON);
            }
        }
        return ImageProvider.getImage(ERepositoryObjectType.DOCUMENTATION);
    }
}
