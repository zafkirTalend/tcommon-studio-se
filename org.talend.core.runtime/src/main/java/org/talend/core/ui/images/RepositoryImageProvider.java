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
package org.talend.core.ui.images;

import org.talend.commons.ui.image.EImage;
import org.talend.commons.ui.image.IImage;
import org.talend.core.model.repository.ERepositoryObjectType;

/**
 * ggu class global comment. Detailled comment
 */
public class RepositoryImageProvider {

    public static IImage getIcon(ERepositoryObjectType type) {
        switch (type) {
        case SVN_ROOT:
            return ECoreImage.SVN_ROOT_ICON;
        case BUSINESS_PROCESS:
            return ECoreImage.BUSINESS_PROCESS_ICON;
        case PROCESS:
            return ECoreImage.PROCESS_ICON;
        case JOBLET:
            return ECoreImage.JOBLET_ICON;
        case CONTEXT:
            return ECoreImage.CONTEXT_ICON;
        case ROUTINES:
            return ECoreImage.ROUTINE_ICON;
        case JOB_SCRIPT:
            return ECoreImage.JOB_SCRIPTS_ICON;
        case SNIPPETS:
            return ECoreImage.SNIPPETS_ICON;
        case DOCUMENTATION:
        case JOB_DOC:
        case JOBLET_DOC:
            return ECoreImage.DOCUMENTATION_ICON;
        case METADATA:
            return ECoreImage.METADATA_ICON;
        case METADATA_CONNECTIONS:
            return ECoreImage.METADATA_CONNECTION_ICON;
        case METADATA_SAPCONNECTIONS:
        case METADATA_SAP_FUNCTION:
            return ECoreImage.METADATA_SAPCONNECTION_ICON;
        case SQLPATTERNS:
            return ECoreImage.METADATA_SQLPATTERN_ICON;
        case METADATA_CON_TABLE:
            return ECoreImage.METADATA_TABLE_ICON;
        case METADATA_CON_QUERY:
            return ECoreImage.METADATA_QUERY_ICON;
        case METADATA_CON_VIEW:
            return ECoreImage.METADATA_VIEW_ICON;
        case METADATA_CON_SYNONYM:
            return ECoreImage.METADATA_SYNONYM_ICON;
        case METADATA_FILE_DELIMITED:
            return ECoreImage.METADATA_FILE_DELIMITED_ICON;
        case METADATA_FILE_POSITIONAL:
            return ECoreImage.METADATA_FILE_POSITIONAL_ICON;
        case METADATA_FILE_REGEXP:
            return ECoreImage.METADATA_FILE_REGEXP_ICON;
        case METADATA_FILE_XML:
            return ECoreImage.METADATA_FILE_XML_ICON;
        case METADATA_FILE_EXCEL:
            return ECoreImage.METADATA_FILE_EXCEL_ICON;
        case METADATA_FILE_LDIF:
            return ECoreImage.METADATA_FILE_LDIF_ICON;
        case FOLDER:
            return ECoreImage.FOLDER_OPEN_ICON;
        case REFERENCED_PROJECTS:
            return ECoreImage.REFERENCED_ICON;
        case METADATA_GENERIC_SCHEMA:
            return ECoreImage.METADATA_GENERIC_ICON;
        case METADATA_FILE_FTP:
            return ECoreImage.FTP_ICON;
        case METADATA_LDAP_SCHEMA:
            return ECoreImage.METADATA_LDAP_SCHEMA_ICON;
        case METADATA_WSDL_SCHEMA:
            return ECoreImage.METADATA_WSDL_SCHEMA_ICON;
        case METADATA_SALESFORCE_SCHEMA:
            return ECoreImage.METADATA_SALESFORCE_SCHEMA_ICON;
        case METADATA_FILE_EBCDIC:
            return ECoreImage.METADATA_EBCDIC_CONNECTION_ICON;
        case METADATA_FILE_HL7:
            return ECoreImage.METADATA_HL7_CONNECTION_ICON;
        case METADATA_FILE_BRMS:
            return ECoreImage.METADATA_BRMS_CONNECTION_ICON;
        case METADATA_MDMCONNECTION:
            return ECoreImage.METADATA_MDM_CONNECTION_ICON;
        case METADATA_FILE_RULES:
            return ECoreImage.METADATA_RULES_ICON;
        case METADATA_FILE_LINKRULES:
            return ECoreImage.METADATA_RULES_ICON;
        case RECYCLE_BIN:
            return ECoreImage.RECYCLE_BIN_EMPTY_ICON;
        default:
            return EImage.DEFAULT_IMAGE;
        }
    }
}
