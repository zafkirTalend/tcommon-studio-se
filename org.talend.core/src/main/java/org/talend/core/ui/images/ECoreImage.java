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

    PROJECT_ICON("/icons/prj_obj.gif"), //$NON-NLS-1$

    TALEND_PICTO("/icons/talend-picto-small.gif"), //$NON-NLS-1$

    DEFAULT_WIZ("/icons/defaultWizard.png"), //$NON-NLS-1$

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
    PROCESS_TEMPLATE_ICON("/icons/process_icon.gif"), //$NON-NLS-1$
    PROCESS_TEMPLATE_WIZ("/icons/process_template_wiz.png"), //$NON-NLS-1$
    JOBLET_ICON("/icons/joblet_icon.png"), //$NON-NLS-1$
    CONTEXT_ICON("/icons/context_icon.gif"), //$NON-NLS-1$
    CONTEXT_CONF_ICON("/icons/context_conf_icon.gif"), //$NON-NLS-1$
    CONTEXT_WIZ("/icons/context_wiz.png"), //$NON-NLS-1$
    CODE_ICON("/icons/code.png"), //$NON-NLS-1$
    ROUTINE_ICON("/icons/routine.gif"), //$NON-NLS-1$
    ROUTINE_WIZ("/icons/routine_wiz.png"), //$NON-NLS-1$
    SNIPPETS_ICON("/icons/snippet.png"), //$NON-NLS-1$
    // SUBROUTINE_ICON("/icons/subroutine.gif"),
    DOCUMENTATION_ICON("/icons/documentation.gif"), //$NON-NLS-1$
    DOCUMENTATION_SINGLE_ICON("/icons/Documentation_feuillet.png"), //$NON-NLS-1$
    DOCUMENTATION_WIZ("/icons/documentation_wiz.png"), //$NON-NLS-1$

    METADATA_ICON("/icons/metadata.png"), //$NON-NLS-1$
    METADATA_CONNECTION_ICON("/icons/connection.gif"), //$NON-NLS-1$
    METADATA_CONNECTION_WIZ("/icons/connection_wiz.png"), //$NON-NLS-1$
    METADATA_CDC_CONN_ICON("/icons/cdc_conn.png"), //$NON-NLS-1$
    
    METADATA_SAPCONNECTION_ICON("/icons/sapconnection.gif"),
    METADATA_SAPCONNECTION_WIZ("/icons/connection_wiz.gif"),

    METADATA_SQLPATTERN_ICON("/icons/SQLPattern.png"), //$NON-NLS-1$
    METADATA_SQLPATTERN_WIZ("/icons/defaultWizard.png"), //$NON-NLS-1$

    METADATA_TABLE_ICON("/icons/table.gif"), //$NON-NLS-1$
    METADATA_TABLE_WIZ("/icons/table_wiz.png"), //$NON-NLS-1$
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
    METADATA_FILE_EXCEL_ICON("/icons/fileexcel.gif"), //$NON-NLS-1$
    METADATA_FILE_EXCEL_WIZ("/icons/excel_wiz.png"), //$NON-NLS-1$
    METADATA_FILE_LDIF_ICON("/icons/fileldif.gif"), //$NON-NLS-1$
    METADATA_FILE_LDIF_WIZ("/icons/ldif_wiz.png"), //$NON-NLS-1$
    METADATA_GENERIC_ICON("/icons/genericSchema_icon.png"), //$NON-NLS-1$
    METADATA_LDAP_SCHEMA_ICON("/icons/ldap_icon.png"), //$NON-NLS-1$
    METADATA_WSDL_SCHEMA_ICON("/icons/wsdl_icon.png"), //$NON-NLS-1$
    METADATA_SALESFORCE_SCHEMA_ICON("/icons/salesforce_icon.png"), //$NON-NLS-1$

    MODULE_INSTALLED_ICON("/icons/checked.gif"), //$NON-NLS-1$
    MODULE_ERROR_ICON("/icons/error.gif"), //$NON-NLS-1$
    MODULE_WARNING_ICON("/icons/warning.gif"), //$NON-NLS-1$
    MODULE_UNKNOWN_ICON("/icons/unknown.gif"), //$NON-NLS-1$
    MODULE_REQUIRED_ICON("/icons/checked.gif"), //$NON-NLS-1$
    MODULE_NOTREQUIRED_ICON("/icons/empty.gif"), //$NON-NLS-1$

    LOCKED_USER_OVERLAY("/icons/locked_green_overlay.gif"), //$NON-NLS-1$
    LOCKED_OTHER_OVERLAY("/icons/locked_red_overlay.gif"), //$NON-NLS-1$
    NEW_OVERLAY("/icons/new_overlay.gif"), //$NON-NLS-1$
    MASTER_OVERLAY("/icons/etoile-master-job1.gif"), //$NON-NLS-1$
    DOC_PPT_OVERLAY("/icons/docs/doc_ppt_overlay.gif"), //$NON-NLS-1$
    DOC_PDF_OVERLAY("/icons/docs/doc_pdf_overlay.gif"), //$NON-NLS-1$
    DOC_EXCEL_OVERLAY("/icons/docs/doc_excel_overlay.gif"), //$NON-NLS-1$
    DOC_WORD_OVERLAY("/icons/docs/doc_word_overlay.gif"), //$NON-NLS-1$
    RECYCLE_BIN_OVERLAY("/icons/recycle_bin_overlay.gif"), //$NON-NLS-1$
    DELETED_OVERLAY("/icons/deleted_overlay.gif"), //$NON-NLS-1$
    IMPORT_JAR("/icons/importjar.gif"), //$NON-NLS-1$
    REFERENCED_ICON("/icons/referenced.png"), //$NON-NLS-1$

    CDC_ADDED_OVERLAY("/icons/cdc_added_overlay.png"),
    CDC_ACTIVATED_OVERLAY("/icons/cdc_activated_overlay.png"),

    ERROR_OVERLAY("/icons/error_co.gif"), //$NON-NLS-1$
    WARN_OVERLAY("/icons/warning_co.gif"), //$NON-NLS-1$

    UNKNOWN();

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
