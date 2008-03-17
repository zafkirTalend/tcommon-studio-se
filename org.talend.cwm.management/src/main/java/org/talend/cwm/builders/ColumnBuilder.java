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
package org.talend.cwm.builders;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;

import org.talend.cwm.management.connection.DatabaseContentRetriever;
import org.talend.cwm.relational.TdColumn;

/**
 * @author scorreia
 * 
 * This class creates TdColumn objects from a connection. The connection must be closed by the caller. It will not be
 * closed by the ColumnBuilder.
 */
public class ColumnBuilder extends CwmBuilder {

    /**
     * DOC scorreia ColumnBuilder constructor comment.
     * 
     * @param conn
     * @throws SQLException
     */
    public ColumnBuilder(Connection conn) {
        super(conn);
    }

    /**
     * Method "getColumns".
     * 
     * @param catalogName a catalog name; must match the catalog name as it is stored in the database; "" retrieves
     * those without a catalog; null means that the catalog name should not be used to narrow the search
     * @param schemaPattern a schema name pattern; must match the schema name as it is stored in the database; ""
     * retrieves those without a schema; null means that the schema name should not be used to narrow the search
     * @param tablePattern a table name pattern; must match the table name as it is stored in the database
     * @param columnPattern a column name pattern; must match the column name as it is stored in the database
     * @throws SQLException
     * @see DatabaseMetaData#getColumns(String, String, String, String)
     */
    public List<TdColumn> getColumns(String catalogName, String schemaPattern, String tablePattern, String columnPattern)
            throws SQLException {
        return DatabaseContentRetriever.getColumns(catalogName, schemaPattern, tablePattern, columnPattern, connection);
    }
}
