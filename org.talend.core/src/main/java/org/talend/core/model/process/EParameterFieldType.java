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
package org.talend.core.model.process;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public enum EParameterFieldType {
    TEXT,
    MEMO_SQL,
    MEMO_PERL,
    MEMO_JAVA,
    CLOSED_LIST,
    CHECK,
    MEMO,
    SCHEMA_TYPE,
    QUERYSTORE_TYPE,
    PROPERTY_TYPE,
    EXTERNAL,
    FILE,
    VERSION,
    TABLE,
    DIRECTORY,
    PROCESS_TYPE,
    IMAGE,
    COLUMN_LIST,
    CONNECTION_LIST,
    PREV_COLUMN_LIST,
    CONTEXT_PARAM_NAME_LIST,
    LOOKUP_COLUMN_LIST,
    TECHNICAL, // means field not displayed directly
    ENCODING_TYPE,
    COMPONENT_LIST,
    MAPPING_TYPE,
    COLOR,
    DBTABLE,
    DATE,
    DBTYPE_LIST,
    LABEL,
    MODULE_LIST;

    public String getName() {
        return toString();
    }

    public static EParameterFieldType getFieldTypeByName(String name) {
        for (int i = 0; i < EParameterFieldType.values().length; i++) {
            if (EParameterFieldType.values()[i].getName().equals(name)) {
                return EParameterFieldType.values()[i];
            }
        }
        return TEXT; // Default Value
    }
}
