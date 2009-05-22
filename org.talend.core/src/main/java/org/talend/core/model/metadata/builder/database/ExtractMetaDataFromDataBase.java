// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.model.metadata.builder.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.core.CorePlugin;
import org.talend.core.database.EDatabaseTypeName;
import org.talend.core.i18n.Messages;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.language.LanguageManager;
import org.talend.core.model.metadata.IMetadataConnection;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.MappingTypeRetriever;
import org.talend.core.model.metadata.MetadataTable;
import org.talend.core.model.metadata.MetadataTalendType;
import org.talend.core.model.metadata.builder.ConvertionHelper;
import org.talend.core.model.metadata.builder.connection.ConnectionFactory;
import org.talend.core.model.metadata.builder.connection.MetadataColumn;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.repository.model.IRepositoryService;

/**
 * DOC cantoine. Extract Meta Data Table. Contains all the Table and Metadata about a DB Connection. <br/>
 * 
 * $Id$
 * 
 */
public class ExtractMetaDataFromDataBase {

    /**
     * qzhang TableInfoParameters class global comment. Detailled comment <br/>
     * 
     */
    public enum ETableTypes {
        TABLETYPE_TABLE("TABLE"), //$NON-NLS-1$
        TABLETYPE_VIEW("VIEW"), //$NON-NLS-1$
        TABLETYPE_SYNONYM("SYNONYM"); //$NON-NLS-1$

        private final String name;

        /**
         * qzhang TableInfoParameters constructor comment.
         */
        ETableTypes(String name) {
            this.name = name;
        }

        /**
         * Getter for name.
         * 
         * @return the name
         */
        public String getName() {
            return this.name;
        }

        public static ETableTypes getTableTypeFromName(String name) {
            for (ETableTypes type : values()) {
                if (type.name.equals(name)) {
                    return type;
                }
            }
            return null;
        }
    }

    private static Logger log = Logger.getLogger(ExtractMetaDataFromDataBase.class);

    private static int columnIndex;

    private static String driveClass = null;

    /**
     * This map represents sets of table type and table name key value pair.
     */
    private static Map<String, String> tableTypeMap = new Hashtable<String, String>();

    private static Map<String, String> tableSchemaMap = new Hashtable<String, String>();

    private static MappingTypeRetriever mappingTypeRetriever;

    /**
     * DOC cantoine. Method to return a Collection of Tables for a DB connection.
     * 
     * @param DatabaseMetaData dbMetaData
     * @return Collection of MetadataTable
     */
    public static List<IMetadataTable> extractTablesFromDB(DatabaseMetaData dbMetaData, String schema, int... limit) {
        List<IMetadataTable> medataTables = new ArrayList<IMetadataTable>();

        try {
            ResultSet rsTables = null, rsTableTypes = null;
            rsTableTypes = dbMetaData.getTableTypes();
            Set<String> availableTableTypes = new HashSet<String>();
            String[] neededTableTypes = { ETableTypes.TABLETYPE_TABLE.getName(), ETableTypes.TABLETYPE_VIEW.getName(),
                    ETableTypes.TABLETYPE_SYNONYM.getName() };
            while (rsTableTypes.next()) {
                // StringUtils.trimToEmpty(name) is because bug 4547
                String currentTableType = StringUtils.trimToEmpty(rsTableTypes.getString("TABLE_TYPE")); //$NON-NLS-1$
                if (ArrayUtils.contains(neededTableTypes, currentTableType)) {
                    availableTableTypes.add(currentTableType);
                }
            }
            rsTableTypes.close();// See bug 5029 Avoid "Invalid cursor exception"

            if (dbMetaData.supportsSchemasInTableDefinitions() && !schema.equals("")) { //$NON-NLS-1$
                rsTables = dbMetaData.getTables(null, schema, null, availableTableTypes.toArray(new String[] {}));
            } else {
                rsTables = dbMetaData.getTables(null, null, null, availableTableTypes.toArray(new String[] {}));
            }
            getMetadataTables(medataTables, rsTables, dbMetaData.supportsSchemasInTableDefinitions(), limit);
            rsTables.close();
        } catch (SQLException e) {
            // e.printStackTrace();
            ExceptionHandler.process(e);
            log.error(e.toString());
            throw new RuntimeException(e);
        } catch (Exception e) {
            // e.printStackTrace();
            ExceptionHandler.process(e);
            log.error(e.toString());
            throw new RuntimeException(e);
        }
        return medataTables;
    }

    /**
     * DOC qzhang Comment method "getMetadataTables".
     * 
     * @param medataTables
     * @param rsTables
     * @throws SQLException
     */
    private static void getMetadataTables(List<IMetadataTable> medataTables, ResultSet rsTables, boolean supportSchema,
            int... limit) throws SQLException {
        if (rsTables == null) {
            return;
        }
        // hyWang add varribles limitNum and index for bug 7147
        int limitNum = -1;
        long index = 0;
        if (limit != null && limit.length > 0) {
            limitNum = limit[0];
        }
        while (rsTables.next()) {
            MetadataTable medataTable = new MetadataTable();
            medataTable.setId(medataTables.size() + 1 + ""); //$NON-NLS-1$
            // See bug 5029 In some Linux odbc driver for MS SQL, their columns in ResultSet have not alias names.
            // Must use column index to fetch values.

            String tableName = ExtractMetaDataUtils.getStringMetaDataInfo(rsTables, "TABLE_NAME", null); //$NON-NLS-1$
            if (tableName == null) {
                tableName = ExtractMetaDataUtils.getStringMetaDataInfo(rsTables, 3);
            }
            if (tableName == null) {
                System.out.println(Messages.getString("ExtractMetaDataFromDataBase.nameNull")); //$NON-NLS-1$
            }

            medataTable.setLabel(tableName); //$NON-NLS-1$
            medataTable.setTableName(medataTable.getLabel());

            medataTable.setDescription(ExtractMetaDataUtils.getStringMetaDataInfo(rsTables, "REMARKS", null)); //$NON-NLS-1$

            String schema = ExtractMetaDataUtils.getStringMetaDataInfo(rsTables, "TABLE_SCHEM", null); //$NON-NLS-1$
            if (schema == null) {
                schema = ExtractMetaDataUtils.getStringMetaDataInfo(rsTables, 2);
            }
            if (supportSchema && schema != null) {
                tableSchemaMap.put(medataTable.getLabel(), schema);
            }

            String tableType = ExtractMetaDataUtils.getStringMetaDataInfo(rsTables, "TABLE_TYPE", null); //$NON-NLS-1$
            if (tableType == null) {
                tableType = ExtractMetaDataUtils.getStringMetaDataInfo(rsTables, 4);
            }
            if (tableType.equals("T")) { //$NON-NLS-1$
                tableType = ETableTypes.TABLETYPE_TABLE.getName();
            }
            if (tableType.equals("V")) { //$NON-NLS-1$
                tableType = ETableTypes.TABLETYPE_VIEW.getName();
            }

            try {
                tableTypeMap.put(medataTable.getLabel(), tableType); //$NON-NLS-1$    
            } catch (Exception e) {
                tableTypeMap.put(medataTable.getLabel(), "TABLE"); //$NON-NLS-1$
            }
            medataTables.add(medataTable);
            index++;
            if (limitNum > 0 && index > limitNum) {
                break;
            }
        }
    }

    /**
     * DOC cantoine. Method to return a Collection of Column about a Table for a DB connection.
     * 
     * @param IMetadataConnection iMetadataConnection
     * @param String tableLabel
     * @return Collection of MetadataColumn Object of a Table
     */
    public static synchronized List<MetadataColumn> returnMetadataColumnsFormTable(IMetadataConnection iMetadataConnection,
            String tableLabel, boolean... dontCreateClose) {

        List<MetadataColumn> metadataColumns = new ArrayList<MetadataColumn>();

        boolean needCreateAndClose = dontCreateClose.length == 0 || !dontCreateClose[0];

        try {
            // WARNING Schema equals sid or database
            if (needCreateAndClose || ExtractMetaDataUtils.conn == null || ExtractMetaDataUtils.conn.isClosed()) {
                ExtractMetaDataUtils.getConnection(iMetadataConnection.getDbType(), iMetadataConnection.getUrl(),
                        iMetadataConnection.getUsername(), iMetadataConnection.getPassword(), iMetadataConnection.getDatabase(),
                        iMetadataConnection.getSchema(), iMetadataConnection.getDriverClass(), iMetadataConnection
                                .getDriverJarPath(), iMetadataConnection.getDbVersionString());
            }
            String dbType = iMetadataConnection.getDbType();
            DatabaseMetaData dbMetaData = ExtractMetaDataUtils.getDatabaseMetaData(ExtractMetaDataUtils.conn, dbType);

            List<IMetadataTable> metadataTables = ExtractMetaDataFromDataBase.extractTablesFromDB(dbMetaData, iMetadataConnection
                    .getSchema());
            Iterator iterate = metadataTables.iterator();
            IMetadataTable metaTable1 = new MetadataTable();
            while (iterate.hasNext()) {
                IMetadataTable metaTable = (IMetadataTable) iterate.next();
                if (metaTable.getLabel().equals(tableLabel)) {
                    metaTable1 = metaTable;
                }
            }

            String name = getTableTypeByTableName(metaTable1.getLabel());

            // StringUtils.trimToEmpty(name) is because bug 4547
            if (name != null && StringUtils.trimToEmpty(name).equals(ETableTypes.TABLETYPE_SYNONYM.getName())) {
                String tableName = getTableNameBySynonym(ExtractMetaDataUtils.conn, metaTable1.getTableName());
                metaTable1.setLabel(tableName);
                metaTable1.setTableName(tableName);
            }

            metadataColumns = ExtractMetaDataFromDataBase.extractMetadataColumnsFormTable(dbMetaData, metaTable1,
                    iMetadataConnection, dbType);

            if (needCreateAndClose) {
                ExtractMetaDataUtils.closeConnection();
            }
        } catch (Exception e) {
            log.error(e.toString());
            throw new RuntimeException(e);
        }

        return metadataColumns;
    }

    /**
     * Retrieves table name by synonym, this method is only for Oracle as we cannot get column informations by metadata
     * in Oracle.
     * 
     * @param connection
     * 
     * @param name synonym
     * @param tableType
     * @return table name
     */
    public static String getTableNameBySynonym(Connection conn, String name) {
        try {
            String sql = "select TABLE_NAME from USER_SYNONYMS where SYNONYM_NAME = '" + name + "'"; //$NON-NLS-1$ //$NON-NLS-2$ 
            Statement sta;
            sta = conn.createStatement();
            ExtractMetaDataUtils.setQueryStatementTimeout(sta);
            ResultSet resultSet = sta.executeQuery(sql);
            while (resultSet.next()) {
                return resultSet.getString("TABLE_NAME"); //$NON-NLS-1$
            }
            resultSet.close();
            sta.close();
        } catch (SQLException e) {
            log.error(e.toString());
            throw new RuntimeException(e);
        }
        return null;
    }

    private static String filterTableNameWithDBRule(final String tablename, final String escape) {
        String table = tablename;
        return table;
        // return table.replaceAll("_", "/_");
    }

    /**
     * DOC cantoine. Method to return a Collection of Column description(metadata) for a DB connection.
     * 
     * @param DatabaseMetaData dbMetaData
     * @param MetadataTable medataTable
     * @return Collection of MetadataColumn Object
     */
    public static List<MetadataColumn> extractMetadataColumnsFormTable(DatabaseMetaData dbMetaData, IMetadataTable medataTable,
            IMetadataConnection metadataConnection, String databaseType) {
        columnIndex = 0;

        List<MetadataColumn> metadataColumns = new ArrayList<MetadataColumn>();

        HashMap<String, String> primaryKeys = new HashMap<String, String>();

        try {
            // qli modified to fix the bug 6850.
            String originSchema = null;
            if (!"".equals(medataTable.getLabel()) && medataTable.getLabel() != null) {//$NON-NLS-1$
                originSchema = tableSchemaMap.get(medataTable.getLabel());
            }
            if (!"".equals(metadataConnection.getSchema()) && (metadataConnection.getSchema() != null)) { //$NON-NLS-1$
                originSchema = metadataConnection.getSchema();
            }

            try {
                ResultSet keys;
                if (dbMetaData.supportsSchemasInDataManipulation() && (originSchema != null)) {
                    keys = dbMetaData.getPrimaryKeys(null, originSchema, medataTable.getLabel());
                } else {
                    keys = dbMetaData.getPrimaryKeys(null, null, medataTable.getLabel());
                    // keys = dbMetaData.getIndexInfo(null, null, medataTable.getLabel(), true, true);
                    // String keyname = null;
                    // // get the primary key information
                    // while (keys.next()) {
                    // // System.out.println("-----------");
                    // // for (int i = 1; i < 14; i++) {
                    // // System.out.println("" + i + "=>" + keys.getString(i));
                    // // }
                    // // System.out.println("-----------");
                    // String idx = keys.getString(6);
                    // if (idx != null) {
                    // // Note: index "PrimaryKey" is Access DB specific
                    // // other db server has diff. index syntax.
                    // if (idx.equalsIgnoreCase("PrimaryKey")) {
                    // keyname = keys.getString(9);
                    // }
                    // }
                    // }
                }
                primaryKeys.clear();
                while (keys.next()) {
                    primaryKeys.put(keys.getString("COLUMN_NAME"), "PRIMARY KEY"); //$NON-NLS-1$ //$NON-NLS-2$
                }
                keys.close();
            } catch (Exception e) {
                log.error(e.toString());
            }

            // PTODO ftang: should to support all kinds of databases not only for Mysql.
            Connection connection = dbMetaData.getConnection();
            checkUniqueKeyConstraint(medataTable, primaryKeys, connection);
            String tableName = medataTable.getTableName();
            ResultSet columns;
            if (dbMetaData.supportsSchemasInDataManipulation() && (originSchema != null)) {
                columns = dbMetaData.getColumns(null, originSchema, tableName, null);
            } else {
                columns = dbMetaData.getColumns(null, null, tableName, null);
            }
            IRepositoryService repositoryService = CorePlugin.getDefault().getRepositoryService();
            while (columns.next()) {
                String fetchTableName = ExtractMetaDataUtils.getStringMetaDataInfo(columns, "TABLE_NAME", null); //$NON-NLS-1$
                if (fetchTableName.equals(tableName) || databaseType.equals(EDatabaseTypeName.SQLITE.getDisplayName())) {
                    MetadataColumn metadataColumn = ConnectionFactory.eINSTANCE.createMetadataColumn();
                    metadataColumn.setLabel(ExtractMetaDataUtils.getStringMetaDataInfo(columns, "COLUMN_NAME", null)); //$NON-NLS-1$
                    metadataColumn.setOriginalField(metadataColumn.getLabel());

                    // Validate the column if it contains space or illegal
                    // characters.
                    if (repositoryService != null) {
                        //metadataColumn.setDisplayField(repositoryService.validateColumnName(metadataColumn.getLabel(),
                        // columnIndex));
                        metadataColumn.setLabel(repositoryService.validateColumnName(metadataColumn.getLabel(), columnIndex));
                    }
                    columnIndex++;

                    if (primaryKeys != null && !primaryKeys.isEmpty()
                            && primaryKeys.get(metadataColumn.getOriginalField()) != null) {
                        metadataColumn.setKey(true);
                    } else {
                        metadataColumn.setKey(false);
                    }

                    String dbType = ExtractMetaDataUtils.getStringMetaDataInfo(columns, "TYPE_NAME", null).toUpperCase(); //$NON-NLS-1$
                    dbType = handleDBtype(dbType);
                    metadataColumn.setSourceType(dbType);

                    metadataColumn.setLength(ExtractMetaDataUtils.getIntMetaDataInfo(columns, "COLUMN_SIZE")); //$NON-NLS-1$
                    // Convert dbmsType to TalendType

                    String talendType = null;

                    // qli modified to fix the bug 6654.
                    if (metadataConnection.getMapping() != null) {
                        mappingTypeRetriever = MetadataTalendType.getMappingTypeRetriever(metadataConnection.getMapping());
                    }
                    talendType = mappingTypeRetriever.getDefaultSelectedTalendType(dbType, ExtractMetaDataUtils
                            .getIntMetaDataInfo(columns, "COLUMN_SIZE"), ExtractMetaDataUtils.getIntMetaDataInfo(columns, //$NON-NLS-1$
                            "DECIMAL_DIGITS")); //$NON-NLS-1$
                    if (talendType == null) {
                        if (LanguageManager.getCurrentLanguage() == ECodeLanguage.JAVA) {
                            talendType = JavaTypesManager.getDefaultJavaType().getId();
                            log.warn(Messages.getString("ExtractMetaDataFromDataBase.dbTypeNotFound", dbType)); //$NON-NLS-1$
                        } else {
                            talendType = ""; //$NON-NLS-1$
                            log.warn(Messages.getString("ExtractMetaDataFromDataBase.dbTypeNotFound", dbType)); //$NON-NLS-1$
                        }
                    } else {
                        // to remove when the new perl type will be added in talend.
                        // talendType = TypesManager.getTalendTypeFromXmlType(talendType);
                    }

                    metadataColumn.setTalendType(talendType);
                    metadataColumn.setPrecision(ExtractMetaDataUtils.getIntMetaDataInfo(columns, "DECIMAL_DIGITS")); //$NON-NLS-1$

                    boolean isNullable = ExtractMetaDataUtils.getBooleanMetaDataInfo(columns, "IS_NULLABLE"); //$NON-NLS-1$
                    metadataColumn.setNullable(isNullable);

                    // gcui:see bug 6450, if in the commentInfo have some invalid character then will remove it.
                    String commentInfo = ExtractMetaDataUtils.getStringMetaDataInfo(columns, "REMARKS", null); //$NON-NLS-1$
                    if (commentInfo != null && commentInfo.length() > 0) {
                        for (int i = 0; i < commentInfo.length(); i++) {
                            if (commentInfo.codePointAt(i) == 0x0) {
                                String commentSub1 = commentInfo.substring(0, i);
                                String commentSub2 = commentInfo.substring(i + 1);
                                commentInfo = commentSub1 + commentSub2;
                            }
                        }
                    }
                    // gcui:if not oracle database use "REMARKS" select comments
                    metadataColumn.setComment(commentInfo); //$NON-NLS-1$
                    metadataColumns.add(metadataColumn);

                    // cantoine : patch to fix 0x0 pb cause by Bad Schema
                    // description
                    String stringMetaDataInfo = ExtractMetaDataUtils.getStringMetaDataInfo(columns, "COLUMN_DEF", dbMetaData); //$NON-NLS-1$
                    if (stringMetaDataInfo != null && stringMetaDataInfo.length() > 0 && stringMetaDataInfo.charAt(0) == 0x0) {
                        stringMetaDataInfo = "\\0"; //$NON-NLS-1$
                    }
                    metadataColumn.setDefaultValue(stringMetaDataInfo);

                    // for bug 6919, oracle driver doesn't give correctly the length for timestamp
                    if (EDatabaseTypeName.ORACLEFORSID.getDisplayName().equals(metadataConnection.getDbType())
                            || EDatabaseTypeName.ORACLESN.getDisplayName().equals(metadataConnection.getDbType())) {
                        if (dbType.equals("TIMESTAMP")) { //$NON-NLS-1$
                            metadataColumn.setLength(metadataColumn.getPrecision());
                            metadataColumn.setPrecision(-1);
                        }
                    }
                }
            }

            // gcui:if it is oracle database, use this SQL select comments.
            // there will do one query to retrieve all comments on the table.

            if (EDatabaseTypeName.ORACLEFORSID.getDisplayName().equals(metadataConnection.getDbType())
                    || EDatabaseTypeName.ORACLESN.getDisplayName().equals(metadataConnection.getDbType())) {
                try {
                    PreparedStatement statement = ExtractMetaDataUtils.conn
                            .prepareStatement("SELECT COMMENTS FROM USER_COL_COMMENTS WHERE TABLE_NAME='" //$NON-NLS-1$
                                    + medataTable.getLabel() + "'"); //$NON-NLS-1$ //$NON-NLS-2$
                    ResultSet keys = null;
                    ExtractMetaDataUtils.setQueryStatementTimeout(statement);
                    if (statement.execute()) {
                        keys = statement.getResultSet();
                        int i = 0;
                        while (keys.next()) {
                            MetadataColumn metadataColumn = (MetadataColumn) metadataColumns.get(i++);
                            metadataColumn.setComment(keys.getString("COMMENTS")); //$NON-NLS-1$
                        }
                    }
                    keys.close();
                    statement.close();

                } catch (Exception e) {
                    log.error(e.toString());
                }
            }

            columns.close();
        } catch (SQLException e) {
            log.error(e.toString());
            throw new RuntimeException(e);
        } catch (Exception e) {
            // e.printStackTrace();
            ExceptionHandler.process(e);
            log.error(e.toString());
            throw new RuntimeException(e);
        }

        return metadataColumns;

    }

    /**
     * DOC Administrator Comment method "checkUniqueKeyConstraint".
     * 
     * @param medataTable
     * @param primaryKeys
     * @param connection
     */
    private static void checkUniqueKeyConstraint(IMetadataTable medataTable, HashMap<String, String> primaryKeys,
            Connection connection) {
        if (connection instanceof com.mysql.jdbc.Connection) {// MySql
            try {
                PreparedStatement statement = ExtractMetaDataUtils.conn.prepareStatement("SHOW INDEX FROM `" //$NON-NLS-1$
                        + medataTable.getLabel() + "` WHERE Non_unique=0 AND Key_name != \'PRIMARY\';"); //$NON-NLS-1$
                ResultSet keys = null;
                ExtractMetaDataUtils.setQueryStatementTimeout(statement);
                if (statement.execute()) {
                    keys = statement.getResultSet();
                    while (keys.next()) {
                        String field = keys.getString("COLUMN_NAME"); //$NON-NLS-1$
                        primaryKeys.put(field, "PRIMARY KEY"); //$NON-NLS-1$
                    }
                }
                keys.close();
                statement.close();
            } catch (Exception e) {
                log.error(e.toString());
            }
        }
        // SQL Server
        else if (connection instanceof net.sourceforge.jtds.jdbc.ConnectionJDBC2) {
            // try {
            // String query = "SELECT "
            // + " Field=a.name,"
            // + " Flag=case when exists(SELECT 1 FROM sysobjects where xtype=\'UQ\' and name in ( "
            // + " SELECT name FROM sysindexes WHERE indid in( "
            // + " SELECT indid FROM sysindexkeys WHERE id = a.id AND colid=a.colid "
            // + " ))) then \'true\' else \'false\' end "
            // + " FROM syscolumns a "
            // + " inner join sysobjects d on a.id=d.id and d.xtype=\'U\' and d.name<>\'dtproperties\' "
            // + " where d.name=\'" + medataTable.getLabel() + "\' order by a.name;";
            // PreparedStatement statement = ExtractMetaDataUtils.conn.prepareStatement(query);
            //
            // ResultSet keys = null;
            // if (statement.execute()) {
            // keys = statement.getResultSet();
            // while (keys.next()) {
            // String unique = keys.getString("Flag"); //$NON-NLS-1$
            // String field = keys.getString("Field"); //$NON-NLS-1$
            // if ("true".equals(unique)) { //$NON-NLS-1$
            // primaryKeys.put(field, "PRIMARY KEY"); //$NON-NLS-1$
            // }
            // }
            // }
            // keys.close();
            // } catch (Exception e) {
            // log.error(e.toString());
            // }
        }
        // PTODO ftang: should continue to handle all kinds of databases in this case.
        // else if (connection instanceof java.sql.Connection) // SQL Server
    }

    /**
     * DOC cantoine. Method to test DataBaseConnection.
     * 
     * @param dbVersionString
     * 
     * @param String driverClass
     * @param String urlString pwd
     * @param String username
     * @param String pwd
     * @return ConnectionStatus : the result of connection(boolean Result, String messageException)
     */
    public static ConnectionStatus testConnection(String dbType, String url, String username, String pwd, String schema,
            final String driverClassName, final String driverJarPath, String dbVersionString) {

        Connection connection = null;
        ConnectionStatus connectionStatus = new ConnectionStatus();
        connectionStatus.setResult(false);
        try {
            connection = ExtractMetaDataUtils
                    .connect(dbType, url, username, pwd, driverClassName, driverJarPath, dbVersionString);

            if ((schema != null) && (schema.compareTo("") != 0)) { //$NON-NLS-1$
                final String product = EDatabaseTypeName.getTypeFromDisplayName(dbType).getProduct();
                final boolean equals = product.equals(EDatabaseTypeName.ORACLEFORSID.getProduct());
                // We have to check schema
                if (!checkSchemaConnection(schema, connection, equals, dbType)) {
                    connectionStatus.setMessageException(Messages.getString("ExtractMetaDataFromDataBase.SchemaNoPresent")); //$NON-NLS-1$
                    return connectionStatus;
                }
            }

            connectionStatus.setResult(true);
            connectionStatus.setMessageException(Messages.getString("ExtractMetaDataFromDataBase.connectionSuccessful")); //$NON-NLS-1$
        } catch (SQLException e) {
            ExceptionHandler.process(e);
            connectionStatus.setMessageException(e.getMessage());
        } catch (Exception e) {
            ExceptionHandler.process(e);
            connectionStatus.setMessageException(e.getMessage());
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                //
            }
        }
        return connectionStatus;
    }

    /**
     * 
     * DOC YeXiaowei Comment method "isValidJarFile".
     * 
     * @param driverJarFilePath
     * @return
     */
    private static boolean isValidJarFile(final String driverJarFilePath) {
        if (driverJarFilePath == null || driverJarFilePath.equals("")) { //$NON-NLS-1$
            return false;
        }
        // return true;
        File jarFile = new File(driverJarFilePath);
        return jarFile.exists() && jarFile.isFile();
    }

    /**
     * Get the database meta data.
     * 
     * @param schema
     * @param connection
     * @return
     * @throws SQLException
     */
    public static boolean checkSchemaConnection(String schema, Connection connection, boolean notCaseSensitive, String dbType)
            throws SQLException {
        DatabaseMetaData dbMetaData = ExtractMetaDataUtils.getDatabaseMetaData(connection, dbType);
        if (dbMetaData != null) {
            ResultSet rs = dbMetaData.getSchemas();
            while (rs.next()) {
                if (notCaseSensitive) {
                    if (rs.getString(1).toLowerCase().compareTo(schema.toLowerCase()) == 0) {
                        ExtractMetaDataUtils.schema = rs.getString(1);
                        rs.close();
                        return (true);
                    }
                } else {
                    if (rs.getString(1).compareTo(schema) == 0) {
                        ExtractMetaDataUtils.schema = schema;
                        rs.close();
                        return (true);
                    }
                }
            }
            rs.close();
        }
        return false;
    }

    /**
     * DOC cantoine.
     * 
     * @param IMetadataConnection iMetadataConnection
     * @return Collection : return a String's collection of Table Name of a DB Connection
     */
    public static List<String> returnTablesFormConnection(IMetadataConnection iMetadataConnection, int... limit) {
        List<String> itemTablesName = new ArrayList<String>();

        ExtractMetaDataUtils.getConnection(iMetadataConnection.getDbType(), iMetadataConnection.getUrl(), iMetadataConnection
                .getUsername(), iMetadataConnection.getPassword(), iMetadataConnection.getDatabase(), iMetadataConnection
                .getSchema(), iMetadataConnection.getDriverClass(), iMetadataConnection.getDriverJarPath(), iMetadataConnection
                .getDbVersionString());
        String dbType = iMetadataConnection.getDbType();
        DatabaseMetaData dbMetaData = ExtractMetaDataUtils.getDatabaseMetaData(ExtractMetaDataUtils.conn, dbType);

        List<IMetadataTable> metadataTables = ExtractMetaDataFromDataBase.extractTablesFromDB(dbMetaData, iMetadataConnection
                .getSchema(), limit);
        ExtractMetaDataUtils.closeConnection();

        Iterator<IMetadataTable> iterate = metadataTables.iterator();
        while (iterate.hasNext()) {
            IMetadataTable metadataTable = iterate.next();
            itemTablesName.add(metadataTable.getLabel());
        }

        return itemTablesName;
    }

    /**
     * DOC qiang.zhang Comment method "returnMetaTablesFormConnection".
     * 
     * @param iMetadataConnection
     * @return
     */
    public static List<org.talend.core.model.metadata.builder.connection.MetadataTable> returnMetaTablesFormConnection(
            IMetadataConnection iMetadataConnection) {
        List<org.talend.core.model.metadata.builder.connection.MetadataTable> itemTablesName = new ArrayList<org.talend.core.model.metadata.builder.connection.MetadataTable>();

        ExtractMetaDataUtils.getConnection(iMetadataConnection.getDbType(), iMetadataConnection.getUrl(), iMetadataConnection
                .getUsername(), iMetadataConnection.getPassword(), iMetadataConnection.getDatabase(), iMetadataConnection
                .getSchema(), iMetadataConnection.getDriverClass(), iMetadataConnection.getDriverJarPath(), iMetadataConnection
                .getDbVersionString());
        String dbType = iMetadataConnection.getDbType();
        DatabaseMetaData dbMetaData = ExtractMetaDataUtils.getDatabaseMetaData(ExtractMetaDataUtils.conn, dbType);

        List<IMetadataTable> metadataTables = ExtractMetaDataFromDataBase.extractTablesFromDB(dbMetaData, iMetadataConnection
                .getSchema());
        ExtractMetaDataUtils.closeConnection();

        Iterator<IMetadataTable> iterate = metadataTables.iterator();
        while (iterate.hasNext()) {
            IMetadataTable metadataTable = iterate.next();
            itemTablesName.add(ConvertionHelper.convert(metadataTable));
        }

        return itemTablesName;
    }

    /**
     * This method is used for getting table type(table,view or synonym etc.) by table name.
     * 
     * @param tableName a string representing table name
     * @return a string representing table type
     */
    public static String getTableTypeByTableName(String tableName) {
        if (tableTypeMap.containsKey(tableName)) {
            return tableTypeMap.get(tableName);
        }
        return null;
    }

    /**
     * Sets the driveClass.
     * 
     * @param driveClass the driveClass to set
     */
    public static void setDriveClass(String driveClass) {
        ExtractMetaDataFromDataBase.driveClass = driveClass;
    }

    /**
     * DOC qzhang Comment method "returnTablesFormConnection".
     * 
     * @param metadataConnection
     * @param tableInfoParameters
     * @return
     */
    public static List<String> returnTablesFormConnection(IMetadataConnection iMetadataConnection,
            TableInfoParameters tableInfoParameters) {
        List<String> itemTablesName = new ArrayList<String>();

        ExtractMetaDataUtils.getConnection(iMetadataConnection.getDbType(), iMetadataConnection.getUrl(), iMetadataConnection
                .getUsername(), iMetadataConnection.getPassword(), iMetadataConnection.getDatabase(), iMetadataConnection
                .getSchema(), iMetadataConnection.getDriverClass(), iMetadataConnection.getDriverJarPath(), iMetadataConnection
                .getDbVersionString());
        try {
            if (!tableInfoParameters.isUsedName()) {
                if (tableInfoParameters.getSqlFiter() != null && !"".equals(tableInfoParameters.getSqlFiter())) { //$NON-NLS-1$
                    Statement stmt = ExtractMetaDataUtils.conn.createStatement();
                    ExtractMetaDataUtils.setQueryStatementTimeout(stmt);
                    ResultSet rsTables = stmt.executeQuery(tableInfoParameters.getSqlFiter());
                    itemTablesName = getTableNamesFromQuery(rsTables);
                    rsTables.close();
                    stmt.close();
                }
            } else {
                Set<String> nameFiters = tableInfoParameters.getNameFilters();
                if (nameFiters.isEmpty()) {
                    itemTablesName = getTableNamesFromTables(getResultSetFromTableInfo(tableInfoParameters,
                            "", iMetadataConnection.getDbType())); //$NON-NLS-1$
                } else {
                    for (String s : nameFiters) {
                        List<String> tableNamesFromTables = getTableNamesFromTables(getResultSetFromTableInfo(
                                tableInfoParameters, s, iMetadataConnection.getDbType()));
                        for (String string : tableNamesFromTables) {
                            if (!itemTablesName.contains(string)) {
                                itemTablesName.add(string);
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            log.error(e.toString());
            throw new RuntimeException(e);
        } catch (Exception e) {
            log.error(e.toString());
            throw new RuntimeException(e);
        }
        // filter tables or viewer from the recyclebin in the Oracle 10g.
        if (EDatabaseTypeName.ORACLEFORSID.getProduct().equals(iMetadataConnection.getProduct())) {
            filterTablesFromRecycleBin(itemTablesName);
        }
        ExtractMetaDataUtils.closeConnection();

        return itemTablesName;
    }

    /**
     * DOC qzhang Comment method "filterTablesFromRecycleBin".
     * 
     * @param itemTablesName
     */
    private static void filterTablesFromRecycleBin(List<String> itemTablesName) {
        try {
            Statement stmt = ExtractMetaDataUtils.conn.createStatement();
            ExtractMetaDataUtils.setQueryStatementTimeout(stmt);
            ResultSet rsTables = stmt.executeQuery(TableInfoParameters.ORACLE_10G_RECBIN_SQL);
            itemTablesName.removeAll(getTableNamesFromQuery(rsTables));
            rsTables.close();
            stmt.close();
        } catch (SQLException e) {
            // do nothing.
        }
    }

    /**
     * DOC qzhang Comment method "getTableNamesFromQuery".
     * 
     * @param rsTables
     * 
     * @return
     * @throws SQLException
     */
    private static List<String> getTableNamesFromQuery(ResultSet resultSet) throws SQLException {
        List<String> itemTablesName = new ArrayList<String>();
        while (resultSet.next()) {
            itemTablesName.add(resultSet.getString(1));
        }
        return itemTablesName;
    }

    private static String handleDBtype(String dbtype) {
        if (dbtype.startsWith("TIMESTAMP(") && dbtype.endsWith(")")) { //$NON-NLS-1$ //$NON-NLS-2$
            dbtype = "TIMESTAMP"; //$NON-NLS-1$
        }
        return dbtype;
    }

    /**
     * DOC qzhang Comment method "getTableNamesFromQuery".
     * 
     * @param rsTables
     * 
     * @return
     * @throws SQLException
     */
    private static List<String> getTableNamesFromTables(ResultSet resultSet) throws SQLException {
        List<String> itemTablesName = new ArrayList<String>();
        if (resultSet != null) {
            while (resultSet.next()) {
                itemTablesName.add(resultSet.getString("TABLE_NAME")); //$NON-NLS-1$
            }
            resultSet.close();
        }
        return itemTablesName;
    }

    /**
     * DOC qzhang Comment method "getResultSetFromTableInfo".
     * 
     * @param dbMetaData
     * @return
     * @throws SQLException
     */
    private static ResultSet getResultSetFromTableInfo(TableInfoParameters tableInfo, String namePattern, String dbType)
            throws SQLException {
        ResultSet rsTables = null;
        String tableNamePattern = "".equals(namePattern) ? null : namePattern; //$NON-NLS-1$
        String[] types = new String[tableInfo.getTypes().size()];
        for (int i = 0; i < types.length; i++) {
            types[i] = tableInfo.getTypes().get(i).getName();
        }
        DatabaseMetaData dbMetaData = ExtractMetaDataUtils.getDatabaseMetaData(ExtractMetaDataUtils.conn, dbType);
        rsTables = dbMetaData.getTables(null, ExtractMetaDataUtils.schema, tableNamePattern, types);
        return rsTables;
    }

}
