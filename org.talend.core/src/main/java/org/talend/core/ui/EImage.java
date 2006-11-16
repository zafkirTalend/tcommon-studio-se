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

/**
 * 
 * DOC smallet ImageProvider class global comment. Detailled comment <br/>
 * 
 * $Id: ImageProvider.java 418 2006-11-13 16:01:26 +0000 (lun., 13 nov. 2006) cantoine $
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
    METADATA_FILE_XML_ICON(IImageConstants.FILE_XML_PNG),
    METADATA_FILE_XML_WIZ("/icons/xml_wiz.png"),
    METADATA_FILE_LDIF_ICON("/icons/file.png"),
    METADATA_FILE_LDIF_WIZ(IImageConstants.EMPTY_ICON),
    RECYCLE_BIN_EMPTY_ICON(IImageConstants.RECYCLE_BIN_EMPTY_ICON),
    RECYCLE_BIN_FULL_ICON(IImageConstants.RECYCLE_BIN_FULL_ICON),
    FOLDER_OPEN_ICON(IImageConstants.FOLDER_OPEN_ICON),
    FOLDER_CLOSE_ICON(IImageConstants.FOLDER_CLOSE_ICON),
    FOLDER_WIZ("/icons/folder_wiz.gif"),
    SAVE_ICON(IImageConstants.SAVE_ICON),
    COPY_ICON(IImageConstants.COPY_ICON),
    PASTE_ICON(IImageConstants.PASTE_ICON),
    CUT_ICON(IImageConstants.CUT_ICON),
    DELETE_ICON(IImageConstants.DELETE_ICON),
    RESTORE_ICON(IImageConstants.ADD_ICON),
    IMPORT_ICON(IImageConstants.IMPORT_ICON),
    EXPORT_ICON(IImageConstants.EXPORT_ICON),
    LOCK_ICON(IImageConstants.LOCK_ICON),
    REFRESH_ICON(IImageConstants.REFRESH_ICON),
    UP_ICON(IImageConstants.UP_ICON),
    DOWN_ICON(IImageConstants.DOWN_ICON),
    LEFT_ICON("/icons/left.gif"),
    RIGHT_ICON("/icons/right.gif"),
    ADD_ICON(IImageConstants.ADD_ICON),
    MINUS_ICON(IImageConstants.MINUS_ICON),
    KEY_ICON("/icons/key.gif"),
    EMPTY(IImageConstants.EMPTY_ICON),
    DOC_WORD_ICON(IImageConstants.DOC_WORD_ICON),
    DOC_EXCEL_ICON(IImageConstants.DOC_EXCEL_ICON),
    DOC_POWERPOINT_ICON(IImageConstants.DOC_POWERPOINT_ICON),
    DOC_PDF_ICON(IImageConstants.DOC_PDF_ICON),
    DOC_IMAGE_ICON(IImageConstants.DOC_IMAGE_ICON),
    ERROR_ICON("/icons/error.gif"),
    WARNING_ICON("/icons/warning.gif"),
    ERROR_SMALL("/icons/error_small.gif"),
    WARNING_SMALL("/icons/warning_small.gif"),
    MODULE_INSTALLED_ICON("/icons/checked.gif"),
    MODULE_ERROR_ICON("/icons/error.gif"),
    MODULE_WARNING_ICON("/icons/warning.gif"),
    MODULE_UNKNOWN_ICON("/icons/unknown.gif"),
    MODULE_REQUIRED_ICON("/icons/checked.gif"),
    MODULE_NOTREQUIRED_ICON(IImageConstants.EMPTY_ICON),
    HIERARCHY_ICON("/icons/hierarchicalLayout.gif");

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
