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

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.talend.commons.ui.image.EImage;
import org.talend.commons.ui.image.IImage;
import org.talend.commons.ui.image.ImageProvider;
import org.talend.core.model.repository.ERepositoryObjectType;


/**
 * DOC amaumont  class global comment. Detailled comment
 * <br/>
 *
 * $Id$
 *
 */
public class CoreImageProvider {

    public static Image getImage(ERepositoryObjectType type) {
        return ImageProvider.getImage(getIcon(type));
    }

    public static ImageDescriptor getImageDesc(ERepositoryObjectType type) {
        return ImageProvider.getImageDesc(getIcon(type));
    }

    public static IImage getIcon(ERepositoryObjectType type) {
        switch (type) {
        case BUSINESS_PROCESS:
            return ECoreImage.BUSINESS_PROCESS_ICON;
        case PROCESS:
            return ECoreImage.PROCESS_ICON;
        case ROUTINES:
            return ECoreImage.ROUTINE_ICON;
        case SNIPPETS:
            return ECoreImage.SNIPPETS_ICON;
        case DOCUMENTATION:
            return ECoreImage.DOCUMENTATION_ICON;
        case METADATA:
            return ECoreImage.METADATA_ICON;
        case METADATA_CONNECTIONS:
            return ECoreImage.METADATA_CONNECTION_ICON;
        case METADATA_CON_TABLE:
            return ECoreImage.METADATA_TABLE_ICON;
        case METADATA_FILE_DELIMITED:
            return ECoreImage.METADATA_FILE_DELIMITED_ICON;
        case METADATA_FILE_POSITIONAL:
            return ECoreImage.METADATA_FILE_POSITIONAL_ICON;
        case METADATA_FILE_REGEXP:
            return ECoreImage.METADATA_FILE_REGEXP_ICON;
        case METADATA_FILE_XML:
            return ECoreImage.METADATA_FILE_XML_ICON;
        case METADATA_FILE_LDIF:
            return ECoreImage.METADATA_FILE_LDIF_ICON;
        case FOLDER:
            return ECoreImage.FOLDER_OPEN_ICON;
        default:
            return EImage.DEFAULT_IMAGE;
        }
    }

    public static Image getImage(String extOriginal) {
        if (extOriginal != null) {
            String ext = extOriginal.toLowerCase();
            if (Arrays.asList(new String[] { "doc", "dot" }).contains(ext)) {
                return ImageProvider.getImage(ECoreImage.DOC_WORD_ICON);
            } else if (Arrays.asList(new String[] { "xls", "xlt" }).contains(ext)) {
                return ImageProvider.getImage(ECoreImage.DOC_EXCEL_ICON);
            } else if (Arrays.asList(new String[] { "ppt", "pps", "pot" }).contains(ext)) {
                return ImageProvider.getImage(ECoreImage.DOC_POWERPOINT_ICON);
            } else if (Arrays.asList(new String[] { "pps" }).contains(ext)) {
                return ImageProvider.getImage(ECoreImage.DOC_PDF_ICON);
            } else if (Arrays.asList(new String[] { "gif", "jpg", "jpeg", "bmp", "gif", "png" }).contains(ext)) {
                return ImageProvider.getImage(ECoreImage.DOC_IMAGE_ICON);
            }
        }
        return CoreImageProvider.getImage(ERepositoryObjectType.DOCUMENTATION);
    }

}
