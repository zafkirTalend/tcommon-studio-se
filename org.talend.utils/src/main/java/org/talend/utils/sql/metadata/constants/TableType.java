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
package org.talend.utils.sql.metadata.constants;

/**
 * @author scorreia
 * 
 * Types used in {@link java.sql.DatabaseMetaData#getTables(String, String, String, String[])} for the table type.
 * @see GetTable#TABLE_TYPE
 */
public enum TableType {
    TABLE,
    VIEW,
    SYSTEM_TABLE,
    GLOBAL_TEMPORARY,
    LOCAL_TEMPORARY,
    ALIAS,
    SYNONYM;

    @Override
    public String toString() {
        switch (this) {
        case SYSTEM_TABLE:
            return "SYSTEM TABLE";
        case GLOBAL_TEMPORARY:
            return "GLOBAL TEMPORARY";
        case LOCAL_TEMPORARY:
            return "LOCAL TEMPORARY";
        default:
            return this.name();
        }
    }
}
