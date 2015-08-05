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
package org.talend.core.model.metadata.builder.database.hive;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.talend.commons.utils.database.AbstractFakeDatabaseMetaData;
import org.talend.commons.utils.database.EmbeddedHiveResultSet;
import org.talend.core.database.EDatabaseTypeName;
import org.talend.core.model.metadata.builder.database.HotClassLoader;
import org.talend.core.model.metadata.builder.database.JDBCDriverLoader;
import org.talend.utils.sql.metadata.constants.GetTable;
import org.talend.utils.sql.metadata.constants.MetaDataConstants;

/**
 * DOC ggu class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 55206 2011-02-15 17:32:14Z mhirt $
 * 
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class EmbeddedHiveDataBaseMetadata extends AbstractFakeDatabaseMetaData {

    private static final String TABLE_TYPE = "TABLE";

    private static final String HIVE_SCHEMA_DEFAULT = "default";

    private Object hiveObject;

    /**
     * DOC ggu HiveDataBaseMetadata constructor comment.
     */
    public EmbeddedHiveDataBaseMetadata(Connection connection) {
        super(connection);

    }

    private void init() throws SQLException {
        // System.setProperty("hive.metastore.uris", "thrift://192.168.30.128:9083");
        // System.setProperty("hive.metastore.local", "false");
        if (hiveObject == null) {
            JDBCDriverLoader loader = new JDBCDriverLoader();
            HotClassLoader classLoader = loader.getHotClassLoaderFromCache(EDatabaseTypeName.HIVE.getXmlName(), "EMBEDDED");

            try {
                Class calss = Class.forName("org.apache.hadoop.hive.ql.metadata.Hive", true, classLoader); //$NON-NLS-1$
                Method closeCurrentMethod = calss.getDeclaredMethod("closeCurrent"); //$NON-NLS-1$
                closeCurrentMethod.invoke(null);

                Method hiveGetMethod = calss.getDeclaredMethod("get"); //$NON-NLS-1$

                hiveObject = hiveGetMethod.invoke(null);
            } catch (Exception e) {
                throw new SQLException(e);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.utils.database.FakeDatabaseMetaData#getSchemas()
     */
    @Override
    public ResultSet getSchemas() throws SQLException {
        return new EmbeddedHiveResultSet();
    }

    @Override
    public String getDatabaseProductName() throws SQLException {
        // TODO Auto-generated method stub
        return EDatabaseTypeName.HIVE.getDisplayName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.fakejdbc.FakeDatabaseMetaData#getCatalogs()
     */
    @Override
    public ResultSet getCatalogs() throws SQLException {
        EmbeddedHiveResultSet resultSet = new EmbeddedHiveResultSet();
        resultSet.setMetadata(new String[] { MetaDataConstants.TABLE_CAT.name() });
        List<String[]> list = new ArrayList<String[]>();
        list.add(new String[] { HIVE_SCHEMA_DEFAULT });
        resultSet.setData(list);
        return resultSet;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.utils.database.FakeDatabaseMetaData#getPrimaryKeys(java.lang.String, java.lang.String,
     * java.lang.String)
     */
    @Override
    public ResultSet getPrimaryKeys(String catalog, String schema, String table) throws SQLException {
        return new EmbeddedHiveResultSet();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.utils.database.FakeDatabaseMetaData#getTableTypes()
     */
    @Override
    public ResultSet getTableTypes() throws SQLException {

        List<String[]> list = new ArrayList<String[]>();
        list.add(new String[] { TABLE_TYPE });

        EmbeddedHiveResultSet tableResultSet = new EmbeddedHiveResultSet();
        tableResultSet.setMetadata(new String[] { GetTable.TABLE_TYPE.name() });
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
        return new EmbeddedHiveResultSet();
    }

    @Override
    public boolean supportsSchemasInDataManipulation() throws SQLException {
        return false; // FIXME, need check
    }

    @Override
    public boolean supportsSchemasInTableDefinitions() throws SQLException {
        return false; // FIXME, need check
    }

    @Override
    public boolean supportsSchemasInIndexDefinitions() throws SQLException {
        return false; // FIXME, need check
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.utils.database.FakeDatabaseMetaData#getTables(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String[])
     */
    @Override
    public ResultSet getTables(String catalog, String schema, String tableNamePattern, String[] types) throws SQLException {
        init();

        EmbeddedHiveResultSet tableResultSet = new EmbeddedHiveResultSet();
        tableResultSet.setMetadata(TABLE_META);
        List<String[]> list = new ArrayList<String[]>();
        tableResultSet.setData(list);

        if (hiveObject != null) { // got the hive object
            try {
                Class hiveClass = hiveObject.getClass();
                Method getAllTablesMethod = hiveClass.getDeclaredMethod("getAllTables");//$NON-NLS-1$ 
                Object tables = getAllTablesMethod.invoke(hiveObject);
                if (tables instanceof List) {
                    List<String> tableList = (List<String>) tables;
                    for (String tableName : tableList) {
                        String[] array = new String[] { "", HIVE_SCHEMA_DEFAULT, tableName, TABLE_TYPE, "" };
                        list.add(array);
                    }
                }
            } catch (Exception e) {
                throw new SQLException(e);
            }

        }
        return tableResultSet;
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
        init();

        EmbeddedHiveResultSet tableResultSet = new EmbeddedHiveResultSet();
        tableResultSet.setMetadata(COLUMN_META);
        List<String[]> list = new ArrayList<String[]>();
        tableResultSet.setData(list);

        if (hiveObject != null) { // got the hive object
            try {
                Class hiveClass = hiveObject.getClass();
                Method getTableMethod = hiveClass.getDeclaredMethod("getTable", String.class);//$NON-NLS-1$ 
                Object table = getTableMethod.invoke(hiveObject, tableNamePattern);

                if (table != null) {
                    Class tableClass = table.getClass();
                    Method getAllColsMethod = tableClass.getDeclaredMethod("getAllCols");//$NON-NLS-1$ 
                    Object cols = getAllColsMethod.invoke(table);
                    if (cols instanceof List) {
                        List colsList = (List) cols;
                        for (Object colObj : colsList) {
                            Class fieldSchemaClass = colObj.getClass();

                            // col name
                            Method getNameMethod = fieldSchemaClass.getDeclaredMethod("getName");
                            Object nameObj = getNameMethod.invoke(colObj);
                            final String colName = nameObj != null ? nameObj.toString() : "";

                            // col type
                            Method getTypeMethod = fieldSchemaClass.getDeclaredMethod("getType");
                            Object typeObj = getTypeMethod.invoke(colObj);
                            final String coltype = typeObj != null ? typeObj.toString() : "";

                            // col comment
                            Method getCommentMethod = fieldSchemaClass.getDeclaredMethod("getComment");
                            Object commentObj = getCommentMethod.invoke(colObj);
                            final String colComment = commentObj != null ? commentObj.toString() : "";

                            final int colSize = -1; // FIXME, need check.
                            final int colPrecision = 0; // FIXME, need check.
                            final int colRedix = 0; // FIXME, need check.
                            String[] array = new String[] { tableNamePattern, colName, coltype, String.valueOf(colSize),
                                    String.valueOf(colPrecision), String.valueOf(colRedix), "NO", colComment, "" };
                            list.add(array);
                        }
                    }
                }

            } catch (Exception e) {
                throw new SQLException(e);
            }
        }
        return tableResultSet;
    }
}
