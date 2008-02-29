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
package org.talend.cwm.helper.factories;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.talend.cwm.helper.ColumnSetHelper;

import orgomg.cwm.resource.relational.Column;
import orgomg.cwm.resource.relational.QueryColumnSet;
import orgomg.cwm.resource.relational.RelationalFactory;

/**
 * DOC scorreia class global comment. Detailled comment
 */
public final class ColumnSetFactory {

    private ColumnSetFactory() {
    }

    public static QueryColumnSet createQueryColumnSet(ResultSetMetaData metaData) throws SQLException {
        QueryColumnSet columnSet = RelationalFactory.eINSTANCE.createQueryColumnSet();
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i);
            String columnClassName = metaData.getColumnClassName(i);
            // TODO add other informations
            Column column = ColumnFactory.createColumn(columnName);
            ColumnSetHelper.addColumn(column, columnSet);
        }

        return columnSet;
    }

}
