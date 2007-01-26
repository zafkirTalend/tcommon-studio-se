// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
//
// ============================================================================
package org.talend.core.model.metadata.builder.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.talend.core.i18n.Messages;
import org.talend.core.model.metadata.IMetadataConnection;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.MetadataTable;
import org.talend.core.model.metadata.MetadataTalendType;
import org.talend.core.model.metadata.builder.connection.ConnectionFactory;
import org.talend.core.model.metadata.builder.connection.MetadataColumn;

/**
 * DOC cantoine. Extract Meta Data Table. Contains all the Table and Metadata about a DB Connection. <br/>
 * 
 * $Id$
 * 
 */
public class ExtractMetaDataFromDataBase {

    private static Logger log = Logger.getLogger(ExtractMetaDataFromDataBase.class);

    /**
     * This map represents sets of table type and table name key value pair.
     */
    private static Map<String, String> tableTypeMap = new Hashtable<String, String>();

    /**
     * DOC cantoine. Method to return a Collection of Tables for a DB connection.
     * 
     * @param DatabaseMetaData dbMetaData
     * @return Collection of MetadataTable
     */
    public static List<IMetadataTable> extractTablesFromDB(DatabaseMetaData dbMetaData) {

        List<IMetadataTable> medataTables = new ArrayList<IMetadataTable>();

        try {

            String[] tableTypes = { "TABLE", "VIEW", "SYNONYM" };
            ResultSet rsTables = null;
            rsTables = dbMetaData.getTables(null, ExtractMetaDataUtils.schema, null, tableTypes);
            while (rsTables.next()) {
                MetadataTable medataTable = new MetadataTable();
                medataTable.setId(medataTables.size() + 1 + "");
                medataTable.setLabel(rsTables.getString("TABLE_NAME"));
                medataTable.setTableName(medataTable.getLabel());
                medataTable.setDescription(ExtractMetaDataUtils.getStringMetaDataInfo(rsTables, "REMARKS"));
                tableTypeMap.put(medataTable.getLabel(), rsTables.getString("TABLE_TYPE"));
                medataTables.add(medataTable);
            }
            rsTables.close();

        } catch (SQLException e) {
            log.error(e.toString());
            throw new RuntimeException(e);
        } catch (Exception e) {
            log.error(e.toString());
            throw new RuntimeException(e);
        }
        return medataTables;
    }

    /**
     * DOC cantoine. Method to return a Collection with One Table for a DB connection.
     * 
     * @param DatabaseMetaData dbMetaData
     * @param String tableLabel
     * @return Collection of MetadataTable Object, Normally 1 Element in Collection except when tableLabel is Null
     */
    // public static List<IMetadataTable> returnMetadataTable(DatabaseMetaData dbMetaData, String tableLabel) {
    //
    // List<IMetadataTable> medataTables = new ArrayList<IMetadataTable>();
    //
    // try {
    //
    // String[] tableTypes = { "TABLE", "VIEW", "SYNONYM" };
    // ResultSet rsTables = null;
    // rsTables = dbMetaData.getTables(null, ExtractMetaDataUtils.schema, tableLabel, tableTypes);
    // while (rsTables.next()) {
    // MetadataTable medataTable = new MetadataTable();
    // medataTable.setId(medataTables.size() + 1 + "");
    // medataTable.setLabel(rsTables.getString("TABLE_NAME"));
    // medataTable.setTableType(rsTables.getString("TABLE_TYPE"));
    // medataTable.setTableName(medataTable.getLabel());
    // medataTable.setDescription(ExtractMetaDataUtils.getStringMetaDataInfo(rsTables, "REMARKS"));
    // medataTables.add(medataTable);
    // }
    // rsTables.close();
    //
    // } catch (SQLException e) {
    // log.error(e.toString());
    // throw new RuntimeException(e);
    // } catch (Exception e) {
    // log.error(e.toString());
    // throw new RuntimeException(e);
    // }
    // return medataTables;
    // }
    /**
     * DOC cantoine. Method to return a Collection of Column about a Table for a DB connection.
     * 
     * @param IMetadataConnection iMetadataConnection
     * @param String tableLabel
     * @return Collection of MetadataColumn Object of a Table
     */
    public static List<MetadataColumn> returnMetadataColumnsFormTable(IMetadataConnection iMetadataConnection,
            String tableLabel) {

        List<MetadataColumn> metadataColumns = new ArrayList<MetadataColumn>();

        try {
            // WARNING Schema equals sid or database
            ExtractMetaDataUtils.getConnection(iMetadataConnection.getDbType(), iMetadataConnection.getUrl(),
                    iMetadataConnection.getUsername(), iMetadataConnection.getPassword(), iMetadataConnection
                            .getDatabase(), iMetadataConnection.getSchema());
            DatabaseMetaData dbMetaData = ExtractMetaDataUtils.getDatabaseMetaData(ExtractMetaDataUtils.conn);

            List<IMetadataTable> metadataTables = ExtractMetaDataFromDataBase.extractTablesFromDB(dbMetaData);
            Iterator iterate = metadataTables.iterator();
            IMetadataTable metaTable1 = new MetadataTable();
            while (iterate.hasNext()) {
                IMetadataTable metaTable = (IMetadataTable) iterate.next();
                if (metaTable.getLabel().equals(tableLabel)) {
                    metaTable1 = metaTable;
                }
            }

            metadataColumns = ExtractMetaDataFromDataBase.extractMetadataColumnsFormTable(dbMetaData, metaTable1,
                    iMetadataConnection.getDbType());

            ExtractMetaDataUtils.closeConnection();

        } catch (Exception e) {
            log.error(e.toString());
            throw new RuntimeException(e);
        }

        return metadataColumns;
    }

    /**
     * DOC cantoine. Method to return a Collection of Column description(metadata) for a DB connection.
     * 
     * @param DatabaseMetaData dbMetaData
     * @param MetadataTable medataTable
     * @return Collection of MetadataColumn Object
     */
    public static List<MetadataColumn> extractMetadataColumnsFormTable(DatabaseMetaData dbMetaData,
            IMetadataTable medataTable, String dbms) {

        List<MetadataColumn> metadataColumns = new ArrayList<MetadataColumn>();

        HashMap<String, String> primaryKeys = new HashMap<String, String>();

        try {

            try {
                ResultSet keys = dbMetaData.getPrimaryKeys(null, ExtractMetaDataUtils.schema, medataTable.getLabel());
                primaryKeys.clear();
                while (keys.next()) {
                    primaryKeys.put(keys.getString("COLUMN_NAME"), "PRIMARY KEY");
                }
                keys.close();
            } catch (Exception e) {
                log.error(e.toString());
            }

            ResultSet columns = dbMetaData.getColumns(null, ExtractMetaDataUtils.schema, medataTable.getTableName(),
                    null);
            while (columns.next()) {

                MetadataColumn metadataColumn = ConnectionFactory.eINSTANCE.createMetadataColumn();
                metadataColumn.setLabel(ExtractMetaDataUtils.getStringMetaDataInfo(columns, "COLUMN_NAME"));
                metadataColumn.setOriginalField(metadataColumn.getLabel());
                if (primaryKeys != null && !primaryKeys.isEmpty() && primaryKeys.get(metadataColumn.getLabel()) != null) {
                    metadataColumn.setKey(true);
                } else {
                    metadataColumn.setKey(false);
                }
                metadataColumn.setSourceType(ExtractMetaDataUtils.getStringMetaDataInfo(columns, "TYPE_NAME"));
                metadataColumn.setNullable(new Boolean(ExtractMetaDataUtils.getBooleanMetaDataInfo(columns,
                        "IS_NULLABLE")).booleanValue());
                metadataColumn.setLength(ExtractMetaDataUtils.getIntMetaDataInfo(columns, "COLUMN_SIZE"));
                // Convert dbmsType to TalendType
                String talendType = MetadataTalendType.loadTalendType(metadataColumn.getSourceType(), dbms, false);
                metadataColumn.setTalendType(talendType);
                // System.out.println("COMMENT :
                // "+ExtractMetaDataUtils.getStringMetaDataInfo(columns,
                // "TABLE_REMARKS"));
                metadataColumn.setPrecision(ExtractMetaDataUtils.getIntMetaDataInfo(columns, "DECIMAL_DIGITS"));

                // PTODO cantoine : patch to fix 0x0 pb cause by Bad Schema description
                String stringMetaDataInfo = ExtractMetaDataUtils.getStringMetaDataInfo(columns, "COLUMN_DEF");
                if (stringMetaDataInfo != null && stringMetaDataInfo.length() > 0
                        && stringMetaDataInfo.charAt(0) == 0x0) {
                    stringMetaDataInfo = "\\0";
                }
                metadataColumn.setDefaultValue(stringMetaDataInfo);

                metadataColumn.setComment(ExtractMetaDataUtils.getStringMetaDataInfo(columns, "REMARKS"));
                metadataColumns.add(metadataColumn);

            }
            columns.close();
        } catch (SQLException e) {
            log.error(e.toString());
            throw new RuntimeException(e);
        } catch (Exception e) {
            log.error(e.toString());
            throw new RuntimeException(e);
        }

        return metadataColumns;

    }

    /**
     * DOC cantoine. Method to test DataBaseConnection.
     * 
     * @param String driverClass
     * @param String urlString pwd
     * @param String username
     * @param String pwd
     * @return ConnectionStatus : the result of connection(boolean Result, String messageException)
     */
    public static ConnectionStatus testConnection(String dbType, String url, String username, String pwd, String schema) {

        Connection connection;
        ConnectionStatus connectionStatus = new ConnectionStatus();
        connectionStatus.setResult(false);
        try {
            Class.forName(ExtractMetaDataUtils.getDriverClassByDbType(dbType)).newInstance();
            connection = DriverManager.getConnection(url, username, pwd);
            if ((schema != null) && (schema.compareTo("") != 0)) {
                // We have to check schema
                if (!checkSchemaConnection(schema, connection)) {
                    connectionStatus.setMessageException(Messages
                            .getString("ExtractMetaDataFromDataBase.SchemaNoPresent"));
                    return connectionStatus;
                }
            }
            connection.close();
            connectionStatus.setResult(true);
            connectionStatus
                    .setMessageException(Messages.getString("ExtractMetaDataFromDataBase.connectionSuccessful"));
        } catch (SQLException e) {
            connectionStatus.setMessageException(e.getMessage());
        } catch (Exception e) {
            connectionStatus.setMessageException(e.getMessage());
        }
        return connectionStatus;
    }

    /**
     * Get the database meta data.
     * 
     * @param schema
     * @param connection
     * @return
     * @throws SQLException
     */
    private static boolean checkSchemaConnection(String schema, Connection connection) throws SQLException {
        DatabaseMetaData dbMetaData = ExtractMetaDataUtils.getDatabaseMetaData(connection);
        if (dbMetaData != null) {
            ResultSet rs = dbMetaData.getSchemas();
            while (rs.next()) {
                if (rs.getString(1).compareTo(schema) == 0) {
                    rs.close();
                    return (true);
                }
            }
            rs.close();
        }
        return false;
    }

    /**
     * DOC cantoine. Method to save a DataBaseConnection with the EMF Shema.
     * 
     * @param version2
     * 
     * @param String allParameters to Save (String Version)
     * @return IMetadataConnection : this object was returned to the factory who Save in a XML representation
     */
    // public static IMetadataConnection saveDbConnection(String id, String
    // name, String comment, String dbType, String url,
    // String port, String username, String pwd, String sidOrDatabase, String
    // schemaOracle, String sqlSyntax,
    // String stringQuote, String nullChar, String serverName, String
    // dataSourceName, String fileFieldName, String version) {
    //
    // int i = version.indexOf(".");
    // int minor = new Integer(version.substring(0, i));
    // int major = new Integer(version.substring(i + 1, version.length()));
    //
    // return saveDbConnection(id, name, comment, dbType, url, port, username,
    // pwd, sidOrDatabase, schemaOracle, sqlSyntax,
    // stringQuote, nullChar, serverName, dataSourceName, fileFieldName, new
    // Version(minor, major));
    // }
    /**
     * DOC cantoine. Method to save a DataBaseConnection with the EMF Shema.
     * 
     * @param String allParameters to Save (org.talend.core.model.version.Version Version)
     * @return IMetadataConnection : this object was returned to the factory who Save in a XML representation
     */
    // public static IMetadataConnection saveDbConnection(String id, String
    // name, String comment, String dbType, String url,
    // String port, String username, String pwd, String sidOrDatabase, String
    // schemaOracle, String sqlSyntax,
    // String stringQuote, String nullChar, String serverName, String
    // dataSourceName, String fileFieldName, Version version) {
    //
    // IMetadataConnection metadataConnection = new MetadataConnection();
    //
    // try {
    // metadataConnection.setId(id);
    // metadataConnection.setLabel(name);
    // metadataConnection.setVersion(version);
    // metadataConnection.setDescription(comment);
    // metadataConnection.setDbType(dbType);
    // metadataConnection.setDriverClass(ExtractMetaDataUtils.getDriverClassByDbType(dbType));
    // metadataConnection.setUrl(url);
    // metadataConnection.setPort(port);
    // metadataConnection.setUsername(username);
    // metadataConnection.setPassword(pwd);
    // metadataConnection.setServerName(serverName);
    // metadataConnection.setDataSourceName(dataSourceName);
    // metadataConnection.setDatabase(sidOrDatabase);
    // metadataConnection.setSchema(schemaOracle);
    // metadataConnection.setSqlSyntax(sqlSyntax);
    // metadataConnection.setStringQuote(stringQuote);
    // metadataConnection.setNullChar(nullChar);
    // metadataConnection.setFileFieldName(fileFieldName);
    //
    // } catch (Exception e) {
    // log.error(e.toString());
    // throw new RuntimeException(e);
    // }
    // return metadataConnection;
    // }
    /**
     * DOC cantoine.
     * 
     * @param IMetadataConnection iMetadataConnection
     * @return Collection : return a String's collection of Table Name of a DB Connection
     */
    public static List<String> returnTablesFormConnection(IMetadataConnection iMetadataConnection) {
        List<String> itemTablesName = new ArrayList<String>();

        ExtractMetaDataUtils.getConnection(iMetadataConnection.getDbType(), iMetadataConnection.getUrl(),
                iMetadataConnection.getUsername(), iMetadataConnection.getPassword(),
                iMetadataConnection.getDatabase(), iMetadataConnection.getSchema());

        DatabaseMetaData dbMetaData = ExtractMetaDataUtils.getDatabaseMetaData(ExtractMetaDataUtils.conn);

        List<IMetadataTable> metadataTables = ExtractMetaDataFromDataBase.extractTablesFromDB(dbMetaData);
        ExtractMetaDataUtils.closeConnection();

        Iterator<IMetadataTable> iterate = metadataTables.iterator();
        while (iterate.hasNext()) {
            IMetadataTable metadataTable = iterate.next();
            itemTablesName.add(metadataTable.getLabel());
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
}
