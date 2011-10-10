// ============================================================================
//
// Copyright (C) 2006-2011 Talend Inc. - www.talend.com
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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.talend.fakejdbc.FakeDatabaseMetaData;

/**
 * DOC bqian class global comment. Detailled comment
 */
public class DB2ForZosDataBaseMetadata extends FakeDatabaseMetaData {

    private static final String[] TABLE_META = { "ID", "TABLE_SCHEM", "TABLE_NAME", "TABLE_TYPE", "REMARKS" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$

    private static final String[] COLUMN_META = { "TABLE_NAME", "COLUMN_NAME", "TYPE_NAME", "COLUMN_SIZE", "DECIMAL_DIGITS", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
            "IS_NULLABLE", "REMARKS", "COLUMN_DEF" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

    private Connection connection;

    /**
     * DOC bqian DB2ForZosDataBaseMetadata constructor comment.
     * 
     * @param metaData
     */
    public DB2ForZosDataBaseMetadata(Connection connection) {
        this.connection = connection;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.utils.database.FakeDatabaseMetaData#getConnection()
     */
    @Override
    public Connection getConnection() throws SQLException {
        return connection;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.utils.database.FakeDatabaseMetaData#getSchemas()
     */
    @Override
    public ResultSet getSchemas() throws SQLException {
        // see the feature 5827
        String sql = "SELECT DISTINCT CREATOR FROM SYSIBM.SYSTABLES"; //$NON-NLS-1$
        ResultSet rs = null;
        Statement stmt = null;
        List<String[]> list = new ArrayList<String[]>();
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String creator = rs.getString("CREATOR"); //$NON-NLS-1$

                String[] r = new String[] { creator.trim() }; //$NON-NLS-1$ //$NON-NLS-2$
                list.add(r);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                rs.close();
                stmt.close();
            } catch (Exception e) {
            }
        }

        DB2ForZosResultSet tableResultSet = new DB2ForZosResultSet();
        tableResultSet.setMetadata(new String[] { "TABLE_SCHEM" }); //$NON-NLS-1$
        tableResultSet.setData(list);
        return tableResultSet;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.utils.database.FakeDatabaseMetaData#getPrimaryKeys(java.lang.String, java.lang.String,
     * java.lang.String)
     */
    @Override
    public ResultSet getPrimaryKeys(String catalog, String schema, String table) throws SQLException {
        return new DB2ForZosResultSet();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.utils.database.FakeDatabaseMetaData#getTableTypes()
     */
    @Override
    public ResultSet getTableTypes() throws SQLException {
        String[] s1 = new String[] { "TABLE" }; //$NON-NLS-1$
        String[] s2 = new String[] { "VIEW" }; //$NON-NLS-1$
        String[] s3 = new String[] { "SYNONYM" }; //$NON-NLS-1$

        List<String[]> list = new ArrayList<String[]>();

        list.add(s1);
        list.add(s2);
        list.add(s3);

        DB2ForZosResultSet tableResultSet = new DB2ForZosResultSet();
        tableResultSet.setMetadata(new String[] { "TABLE_TYPE" }); //$NON-NLS-1$
        tableResultSet.setData(list);

        return tableResultSet;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.utils.database.FakeDatabaseMetaData#getExportedKeys(java.lang.String, java.lang.String,
     * java.lang.String)
     */
    @Override
    public ResultSet getExportedKeys(String catalog, String schema, String table) throws SQLException {
        return new DB2ForZosResultSet();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.utils.database.FakeDatabaseMetaData#getTables(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String[])
     */
    @Override
    public ResultSet getTables(String catalog, String schema, String tableNamePattern, String[] types) throws SQLException {
        String sql;
        if (schema != null) {
            sql = "SELECT * FROM SYSIBM.SYSTABLES where CREATOR = '" + schema + "'"; //$NON-NLS-1$ //$NON-NLS-2$
        } else {
            sql = "SELECT * FROM SYSIBM.SYSTABLES"; //$NON-NLS-1$
        }
        ResultSet rs = null;
        Statement stmt = null;
        List<String[]> list = new ArrayList<String[]>();
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String name = rs.getString("NAME"); //$NON-NLS-1$
                String creator = rs.getString("CREATOR"); //$NON-NLS-1$
                String type = rs.getString("TYPE"); //$NON-NLS-1$
                // String dbname = rs.getString("DBNAME");

                String[] r = new String[] { "", creator, name, type, "" }; //$NON-NLS-1$ //$NON-NLS-2$
                list.add(r);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                rs.close();
                stmt.close();
            } catch (Exception e) {
            }
        }

        DB2ForZosResultSet tableResultSet = new DB2ForZosResultSet();
        tableResultSet.setMetadata(TABLE_META);
        tableResultSet.setData(list);
        return tableResultSet;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.utils.database.FakeDatabaseMetaData#supportsSchemasInDataManipulation()
     */
    @Override
    public boolean supportsSchemasInDataManipulation() throws SQLException {
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.utils.database.FakeDatabaseMetaData#supportsSchemasInTableDefinitions()
     */
    @Override
    public boolean supportsSchemasInTableDefinitions() throws SQLException {
        return true;
    }

    @Override
    public String getIdentifierQuoteString() throws SQLException {
        return "\""; //$NON-NLS-1$ 
    }

    @Override
    public boolean supportsSchemasInIndexDefinitions() throws SQLException {
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.utils.database.FakeDatabaseMetaData#getColumns(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String)
     */
    @Override
    public ResultSet getColumns(String catalog, String schemaPattern, String tableNamePattern, String columnNamePattern)
            throws SQLException {
        // for real
        String sql = "SELECT * FROM SYSIBM.SYSCOLUMNS where TBNAME='" + tableNamePattern + "' AND  TBCREATOR = '" //$NON-NLS-1$ //$NON-NLS-2$
                + schemaPattern + "' ORDER BY TBCREATOR, TBNAME, COLNO"; //$NON-NLS-1$

        // for test
        // String sql = "SELECT * FROM SYSIBM.SYSCOLUMNS where NAME='NAME'";

        ResultSet rs = null;
        Statement stmt = null;
        List<String[]> list = new ArrayList<String[]>();
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {

                // For real db2 for zos, should use these code.
                String tableName = rs.getString("TBNAME"); //$NON-NLS-1$
                String columnName = rs.getString("NAME"); //$NON-NLS-1$
                String typeName = rs.getString("COLTYPE"); //$NON-NLS-1$
                String columnSize = rs.getString("LENGTH"); //$NON-NLS-1$
                String decimalDigits = rs.getString("SCALE"); //$NON-NLS-1$
                String isNullable;
                if (rs.getString("NULLS").equals("Y")) { //$NON-NLS-1$ //$NON-NLS-2$
                    isNullable = "YES"; //$NON-NLS-1$
                } else {
                    isNullable = rs.getString("NULLS"); //$NON-NLS-1$
                }
                // String keys = rs.getString("keys");
                String remarks = ""; //$NON-NLS-1$
                String columnDef = ""; //$NON-NLS-1$

                // this is for testing with DB2 on windows
                // String tableName = tableNamePattern;
                // String columnName = rs.getString("NAME");
                // String typeName = "datatype";
                // String columnSize = "10";
                // String decimalDigits = "10";
                // String isNullable = "true";
                // String remarks = "remarks";
                // String columnDef = "columnDef";

                String[] r = new String[] { tableName, columnName, typeName, columnSize, decimalDigits, isNullable, // keys
                        // ,
                        remarks, columnDef };
                list.add(r);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                rs.close();
                stmt.close();
            } catch (Exception e) {
            }
        }

        DB2ForZosResultSet tableResultSet = new DB2ForZosResultSet();
        tableResultSet.setMetadata(COLUMN_META);
        tableResultSet.setData(list);
        return tableResultSet;
    }

}
