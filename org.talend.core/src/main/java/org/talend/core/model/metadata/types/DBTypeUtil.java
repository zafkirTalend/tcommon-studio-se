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
package org.talend.core.model.metadata.types;

/**
 * DOC talend class global comment. Detailled comment
 */
public class DBTypeUtil {

    private final String dbTypeName;

    private final boolean isDefault;

    public DBTypeUtil(String dbTypeName, boolean isDefault) {
        this.dbTypeName = dbTypeName;
        this.isDefault = isDefault;
    }

    public String getDbTypeName() {
        return this.dbTypeName;
    }

    public boolean isDefault() {
        return this.isDefault;
    }

}
