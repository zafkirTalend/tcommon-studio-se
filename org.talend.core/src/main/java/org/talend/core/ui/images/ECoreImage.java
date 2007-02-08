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

    PROJECT_WIZ("/icons/project_wiz.png"), //$NON-NLS-1$

    RECYCLE_BIN_EMPTY_ICON("/icons/recyclebinempty.png"), //$NON-NLS-1$
    RECYCLE_BIN_FULL_ICON("/icons/recyclebinfull.png"), //$NON-NLS-1$
    FOLDER_OPEN_ICON("/icons/folder_open.gif"), //$NON-NLS-1$
    FOLDER_CLOSE_ICON("/icons/folder_close.png"), //$NON-NLS-1$
    FOLDER_WIZ("/icons/folder_wiz.gif"), //$NON-NLS-1$

    BUSINESS_PROCESS_ICON("/icons/businessProcess.gif"), //$NON-NLS-1$
    BUSINESS_PROCESS_WIZ("/icons/business_wiz.png"), //$NON-NLS-1$
    PROCESS_ICON("/icons/process_icon.gif"), //$NON-NLS-1$
    PROCESS_WIZ("/icons/process_wiz.png"), //$NON-NLS-1$
    CODE_ICON("/icons/code.png"), //$NON-NLS-1$
    ROUTINE_ICON("/icons/routine.gif"), //$NON-NLS-1$
    SNIPPETS_ICON("/icons/snippet.png"), //$NON-NLS-1$
    // SUBROUTINE_ICON("/icons/subroutine.gif"),
    DOCUMENTATION_ICON("/icons/documentation.gif"), //$NON-NLS-1$
    DOCUMENTATION_SINGLE_ICON("/icons/Documentation_feuillet.png"), //$NON-NLS-1$
    DOCUMENTATION_WIZ("/icons/documentation_wiz.png"), //$NON-NLS-1$

    METADATA_ICON("/icons/metadata.png"), //$NON-NLS-1$
    METADATA_CONNECTION_ICON("/icons/connection.gif"), //$NON-NLS-1$
    METADATA_CONNECTION_WIZ("/icons/connection_wiz.png"), //$NON-NLS-1$
    METADATA_TABLE_ICON("/icons/table.gif"), //$NON-NLS-1$
    METADATA_VIEW_ICON("/icons/view.gif"), //$NON-NLS-1$
    METADATA_SYNONYM_ICON("/icons/synonym.gif"), //$NON-NLS-1$
    METADATA_QUERY_ICON("/icons/query.gif"), //$NON-NLS-1$
    METADATA_FILE_DELIMITED_ICON("/icons/filedelimited.gif"), //$NON-NLS-1$
    METADATA_FILE_DELIMITED_WIZ("/icons/delimited_wiz.png"), //$NON-NLS-1$
    METADATA_FILE_POSITIONAL_ICON("/icons/filepositional.gif"), //$NON-NLS-1$
    METADATA_FILE_POSITIONAL_WIZ("/icons/positional_wiz.png"), //$NON-NLS-1$
    METADATA_FILE_REGEXP_ICON("/icons/fileregexp.gif"), //$NON-NLS-1$
    METADATA_FILE_REGEXP_WIZ("/icons/regexp_wiz.png"), //$NON-NLS-1$
    METADATA_FILE_XML_ICON("/icons/filexml.gif"), //$NON-NLS-1$
    METADATA_FILE_XML_WIZ("/icons/xml_wiz.png"), //$NON-NLS-1$
    METADATA_FILE_LDIF_ICON("/icons/fileldif.gif"), //$NON-NLS-1$
    METADATA_FILE_LDIF_WIZ("/icons/empty.gif"), //$NON-NLS-1$

    MODULE_INSTALLED_ICON("/icons/checked.gif"), //$NON-NLS-1$
    MODULE_ERROR_ICON("/icons/error.gif"), //$NON-NLS-1$
    MODULE_WARNING_ICON("/icons/warning.gif"), //$NON-NLS-1$
    MODULE_UNKNOWN_ICON("/icons/unknown.gif"), //$NON-NLS-1$
    MODULE_REQUIRED_ICON("/icons/checked.gif"), //$NON-NLS-1$
    MODULE_NOTREQUIRED_ICON("/icons/empty.gif"), //$NON-NLS-1$

    LOCKED_USER_OVERLAY("/icons/locked_green_overlay.gif"), //$NON-NLS-1$
    LOCKED_OTHER_OVERLAY("/icons/locked_red_overlay.gif"), //$NON-NLS-1$
    NEW_OVERLAY("/icons/new_overlay.gif"), //$NON-NLS-1$
    DOC_PPT_OVERLAY("/icons/docs/doc_ppt_overlay.gif"), //$NON-NLS-1$
    DOC_PDF_OVERLAY("/icons/docs/doc_pdf_overlay.gif"), //$NON-NLS-1$
    DOC_EXCEL_OVERLAY("/icons/docs/doc_excel_overlay.gif"), //$NON-NLS-1$
    DOC_WORD_OVERLAY("/icons/docs/doc_word_overlay.gif"); //$NON-NLS-1$

    private String path;

    ECoreImage() {
        this.path = "/icons/unknown.gif"; //$NON-NLS-1$
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
