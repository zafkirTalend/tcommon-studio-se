// ============================================================================
//
// Copyright (C) 2006-2015 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.model.metadata.query.generator;

import org.talend.core.database.EDatabaseTypeName;
import org.talend.core.model.metadata.query.AbstractQueryGenerator;

/**
 * @author Marvin Wang
 * @version 1.0 jdk1.6
 * @date Sep 4, 2012
 */
public class HiveQueryGenerator extends AbstractQueryGenerator {

    /**
     * DOC Marvin HiveQueryGenerator constructor comment.
     * 
     * @param dbType
     */
    public HiveQueryGenerator(EDatabaseTypeName dbType) {
        super(dbType);
    }

    protected boolean forceAddQuote() {
        return false;
    }

    protected String addQuotesForSQL(String field) {
        if (forceAddQuote()) {
            return super.addQuotesForSQL(field);
        }
        return field;
    }

    /**
     * For Hive it does need dbName.
     */
    protected String getTableNameWithDBAndSchema(final String dbName, final String schema, String tableName) {
        return super.getTableNameWithDBAndSchema(null, schema, tableName);
    }
}
