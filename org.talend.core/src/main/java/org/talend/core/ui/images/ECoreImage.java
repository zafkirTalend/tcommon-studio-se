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

import org.talend.commons.ui.image.IImage;
import org.talend.core.CorePlugin;

/**
 * 
 * DOC smallet ImageProvider class global comment. Detailled comment <br/>
 * 
 * $Id: ImageProvider.java 418 2006-11-13 16:01:26 +0000 (lun., 13 nov. 2006) cantoine $
 * 
 */
public enum ECoreImage implements IImage {

    PROJECT_WIZ("/icons/project_wiz.png"),

    RECYCLE_BIN_EMPTY_ICON("/icons/recyclebinempty.png"),
    RECYCLE_BIN_FULL_ICON("/icons/recyclebinfull.png"),
    FOLDER_OPEN_ICON("/icons/folder_open.gif"),
    FOLDER_CLOSE_ICON("/icons/folder_close.png"),
    FOLDER_WIZ("/icons/folder_wiz.gif"),

    BUSINESS_PROCESS_ICON("/icons/businessProcess.png"),
    BUSINESS_PROCESS_WIZ("/icons/business_wiz.png"),
    PROCESS_ICON("/icons/process_icon.png"),
    PROCESS_WIZ("/icons/process_wiz.png"),
    CODE_ICON("/icons/code.png"),
    ROUTINE_ICON("/icons/routine.png"),
    SNIPPETS_ICON("/icons/snippet.png"),
    // SUBROUTINE_ICON("/icons/subroutine.gif"),
    DOCUMENTATION_ICON("/icons/documentation.png"),
    DOCUMENTATION_WIZ("/icons/documentation_wiz.png"),
    DOC_WORD_ICON("/icons/docs/word.png"),
    DOC_EXCEL_ICON("/icons/docs/excel.png"),
    DOC_POWERPOINT_ICON("/icons/docs/powerpoint.png"),
    DOC_PDF_ICON("/icons/docs/pdf.png"),
    DOC_IMAGE_ICON("/icons/docs/image.png"),

    METADATA_ICON("/icons/metadata.png"),
    METADATA_CONNECTION_ICON("/icons/connection.png"),
    METADATA_CONNECTION_WIZ("/icons/connection_wiz.png"),
    METADATA_TABLE_ICON("/icons/table.gif"),
    METADATA_QUERY_ICON("/icons/query.gif"),
    METADATA_FILE_DELIMITED_ICON("/icons/filedelimited.png"),
    METADATA_FILE_DELIMITED_WIZ("/icons/delimited_wiz.png"),
    METADATA_FILE_POSITIONAL_ICON("/icons/filepositional.png"),
    METADATA_FILE_POSITIONAL_WIZ("/icons/positional_wiz.png"),
    METADATA_FILE_REGEXP_ICON("/icons/fileregexp.png"),
    METADATA_FILE_REGEXP_WIZ("/icons/regexp_wiz.png"),
    METADATA_FILE_XML_ICON("/icons/filexml.png"),
    METADATA_FILE_XML_WIZ("/icons/xml_wiz.png"),
    METADATA_FILE_LDIF_ICON("/icons/fileldif.png"),
    METADATA_FILE_LDIF_WIZ("/icons/empty.gif"),

    MODULE_INSTALLED_ICON("/icons/checked.gif"),
    MODULE_ERROR_ICON("/icons/error.gif"),
    MODULE_WARNING_ICON("/icons/warning.gif"),
    MODULE_UNKNOWN_ICON("/icons/unknown.gif"),
    MODULE_REQUIRED_ICON("/icons/checked.gif"),
    MODULE_NOTREQUIRED_ICON("/icons/empty.gif"),

    LOCKED_USER_OVERLAY("/icons/locked_green_overlay.gif"),
    LOCKED_OTHER_OVERLAY("/icons/locked_red_overlay.gif"),
    NEW_OVERLAY("/icons/new_overlay.gif");

    private String path;

    ECoreImage() {
        this.path = "/icons/unknown.gif";
    }

    ECoreImage(String path) {
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

    /**
     * Getter for clazz.
     * 
     * @return the clazz
     */
    public Class getLocation() {
        return CorePlugin.class;
    }

}
