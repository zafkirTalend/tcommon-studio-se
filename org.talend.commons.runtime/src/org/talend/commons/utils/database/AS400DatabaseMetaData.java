// ============================================================================
//
// Copyright (C) 2006-2013 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.commons.utils.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * created by zshen on Apr 12, 2013 Detailled comment
 * 
 */
public class AS400DatabaseMetaData extends PackageFakeDatabaseMetadata {

    public AS400DatabaseMetaData(Connection conn) throws SQLException {
        super(conn);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.fakejdbc.FakeDatabaseMetaData#getCatalogs()
     */
    @Override
    public ResultSet getCatalogs() throws SQLException {
        // TODO Auto-generated method stub
        return super.getCatalogs();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.fakejdbc.FakeDatabaseMetaData#getColumns(java.lang.String, java.lang.String, java.lang.String,
     * java.lang.String)
     */
    @Override
    public ResultSet getColumns(String catalog, String schemaPattern, String tableNamePattern, String columnNamePattern)
            throws SQLException {
        // TODO Auto-generated method stub
        return super.getColumns(catalog, schemaPattern, tableNamePattern, columnNamePattern);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.fakejdbc.FakeDatabaseMetaData#getConnection()
     */
    @Override
    public Connection getConnection() {
        // TODO Auto-generated method stub
        return super.getConnection();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.fakejdbc.FakeDatabaseMetaData#getTables(java.lang.String, java.lang.String, java.lang.String,
     * java.lang.String[])
     */
    @Override
    public ResultSet getTables(String catalog, String schemaPattern, String tableNamePattern, String[] types) throws SQLException {

        return super.getTables(catalog, schemaPattern, tableNamePattern, types);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.fakejdbc.FakeDatabaseMetaData#getSchemas(java.lang.String, java.lang.String)
     */
    @Override
    public ResultSet getSchemas(String catalog, String schemaPattern) throws SQLException {
        ResultSet resultSet = null;
        try {
            resultSet = this.metaData.getSchemas(catalog, schemaPattern);
        } catch (SQLException e) {
            // get resultSet of schema by sql mode
            resultSet = this.metaData.getTables(catalog, schemaPattern, null, null);
        }
        return resultSet;
    }

}
