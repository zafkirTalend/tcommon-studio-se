// ============================================================================
//
// Copyright (C) 2006-2014 Talend Inc. - www.talend.com
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * created by hwang on Sep 18, 2015 Detailled comment
 *
 */
public class EXASOLDatabaseMetaData extends PackageFakeDatabaseMetadata {

    private static Logger log = Logger.getLogger(EXASOLDatabaseMetaData.class);

    private static final String[] TABLE_META = { "TABLE_TYPE", "TABLE_NAME", "TABLE_SCHEM", "REMARKS" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

    /**
     * DOC Talend EXASOLDatabaseMetaData constructor comment.
     * 
     * @param connection
     * @throws SQLException
     */
    public EXASOLDatabaseMetaData(Connection connection) throws SQLException {
        super(connection);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.fakejdbc.FakeDatabaseMetaData#getTables(java.lang.String, java.lang.String, java.lang.String,
     * java.lang.String[])
     */
    @Override
    public ResultSet getTables(String catalog, String schemaPattern, String tableNamePattern, String[] types) throws SQLException {
        ResultSet tables = super.getTables(catalog, schemaPattern, tableNamePattern, types);
        List<String[]> list = getTableList(tables);
        if (list.isEmpty()) {
            String sql = "SELECT  * FROM CAT"; //$NON-NLS-1$
            ResultSet rs = null;
            PreparedStatement stmt = null;
            try {
                stmt = connection.prepareStatement(sql);
                if (!StringUtils.isEmpty(tableNamePattern)) {
                    stmt.setString(1, tableNamePattern);
                }// ~
                rs = stmt.executeQuery();
                list = getTableList(rs);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    rs.close();
                    stmt.close();
                } catch (Exception e) {
                }
            }
        }
        EXASOLResultSet tableResultSet = new EXASOLResultSet();
        tableResultSet.setMetadata(TABLE_META);
        tableResultSet.setData(list);
        return tableResultSet;
    }

    private List<String[]> getTableList(ResultSet rs) throws SQLException {
        List<String[]> list = new ArrayList<String[]>();
        if (rs == null) {
            return list;
        }
        while (rs.next()) {
            String table_type = getStringFromResultSet(rs, "TABLE_TYPE"); //$NON-NLS-1$
            String table_name = getStringFromResultSet(rs, "TABLE_NAME"); //$NON-NLS-1$
            String table_schem = getStringFromResultSet(rs, "TABLE_SCHEM");//$NON-NLS-1$
            String table_remarks = getStringFromResultSet(rs, "REMARKS"); //$NON-NLS-1$
            String[] r = new String[] { table_type, table_name, table_schem, table_remarks };
            list.add(r);
        }
        return list;
    }

    private String getStringFromResultSet(ResultSet resultSet, String nameOfString) {
        String valueOfString = null;
        try {
            valueOfString = resultSet.getString(nameOfString);
        } catch (SQLException e) {
            log.warn(e, e);
        }
        return valueOfString;
    }

}
