// ============================================================================
//
// Copyright (C) 2006-2012 Talend Inc. - www.talend.com
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
import java.sql.ResultSetMetaData;
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
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.common.util.EList;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.commons.utils.workbench.extensions.ExtensionImplementationProvider;
import org.talend.commons.utils.workbench.extensions.ExtensionPointLimiterImpl;
import org.talend.commons.utils.workbench.extensions.IExtensionPointLimiter;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.ICoreService;
import org.talend.core.database.EDatabase4DriverClassName;
import org.talend.core.database.EDatabaseTypeName;
import org.talend.core.database.conn.template.EDatabaseConnTemplate;
import org.talend.core.database.utils.ManagementTextUtils;
import org.talend.core.i18n.Messages;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.language.LanguageManager;
import org.talend.core.model.metadata.IMetadataConnection;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.MappingTypeRetriever;
import org.talend.core.model.metadata.MetadataFillFactory;
import org.talend.core.model.metadata.MetadataTable;
import org.talend.core.model.metadata.MetadataTalendType;
import org.talend.core.model.metadata.MetadataToolHelper;
import org.talend.core.model.metadata.builder.ConvertionHelper;
import org.talend.core.model.metadata.builder.connection.DatabaseConnection;
import org.talend.core.model.metadata.builder.connection.MetadataColumn;
import org.talend.core.model.metadata.builder.database.extractots.DBMetadataProviderObject;
import org.talend.core.model.metadata.builder.database.extractots.IDBMetadataProviderObject;
import org.talend.core.model.metadata.builder.util.DatabaseConstant;
import org.talend.core.model.metadata.builder.util.MetadataConnectionUtils;
import org.talend.core.model.metadata.builder.util.TDColumnAttributeHelper;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.repository.ConnectionStatus;
import org.talend.core.repository.IDBMetadataProvider;
import org.talend.core.runtime.CoreRuntimePlugin;
import org.talend.cwm.helper.CatalogHelper;
import org.talend.cwm.helper.ColumnHelper;
import org.talend.cwm.helper.ColumnSetHelper;
import org.talend.cwm.helper.ConnectionHelper;
import org.talend.cwm.relational.RelationalFactory;
import org.talend.cwm.relational.TdColumn;
import org.talend.repository.model.IRepositoryService;
import org.talend.utils.sql.metadata.constants.GetColumn;
import org.talend.utils.sql.metadata.constants.GetTable;
import orgomg.cwm.objectmodel.core.ModelElement;
import orgomg.cwm.resource.relational.Catalog;
import orgomg.cwm.resource.relational.NamedColumnSet;
import orgomg.cwm.resource.relational.Schema;

/**
 * DOC cantoine. Extract Meta Data Table. Contains all the Table and Metadata about a DB Connection. <br/>
 * 
 * $Id: ExtractMetaDataFromDataBase.java 44018 2010-06-12 02:22:29Z zli $
 * 
 */
public class ExtractMetaDataFromDataBase {

    public static List<IDBMetadataProviderObject> providerObjects = null;

    private static ICoreService coreService = (ICoreService) GlobalServiceRegister.getDefault().getService(ICoreService.class);

    public static Map<String, String> tableCommentsMap = new HashMap<String, String>();

    /**
     * 
     */
    private static final String GET_ALL_SYNONYMS = "select distinct synonym_name\n" + //$NON-NLS-1$
            "from all_synonyms, all_tab_columns\n" + //$NON-NLS-1$
            "where all_synonyms.table_name = all_tab_columns.table_name"; //$NON-NLS-1$

    /**
     * qzhang TableInfoParameters class global comment. Detailled comment <br/>
     * 
     */
    public enum ETableTypes {
        TABLETYPE_TABLE("TABLE"), //$NON-NLS-1$
        TABLETYPE_VIEW("VIEW"), //$NON-NLS-1$
        TABLETYPE_SYNONYM("SYNONYM"), //$NON-NLS-1$
        TABLETYPE_ALL_SYNONYM("ALL_SYNONYM"), //$NON-NLS-1$
        TABLETYPE_ALIAS("ALIAS");

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

    private static DatabaseMetaData oldMetadata = null;

    private static String oldSchema = null;

    private static int[] oldLimit;

    private static boolean oldUseAllSynonyms = false;

    private static List<IMetadataTable> oldMetadataRetrieved = null;

    private static List<String> filterTablesName = new ArrayList<String>();

    static {
        providerObjects = new ArrayList<IDBMetadataProviderObject>();
        IExtensionPointLimiter actionExtensionPoint = new ExtensionPointLimiterImpl(
                "org.talend.core.repository.metadata_provider", //$NON-NLS-1$
                "DBMetadataProvider"); //$NON-NLS-1$
        List<IConfigurationElement> extension = ExtensionImplementationProvider.getInstanceV2(actionExtensionPoint);
        for (IConfigurationElement element : extension) {
            try {
                String dbType = element.getAttribute("dbType");
                String dbVersion = element.getAttribute("dbVersion");
                String useJDBC = element.getAttribute("supportJDBC");
                IDBMetadataProvider provider = (IDBMetadataProvider) element.createExecutableExtension("class");
                IDBMetadataProviderObject object = new DBMetadataProviderObject();
                object.setProvider(provider);
                object.setDbType(dbType);
                object.setDbVersion(dbVersion);
                object.setSupportJDBC(Boolean.valueOf(useJDBC));
                providerObjects.add(object);
            } catch (CoreException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * DOC cantoine. Method to return a Collection of Tables for a DB connection.
     * 
     * @param DatabaseMetaData dbMetaData
     * @return Collection of MetadataTable
     */
    public static List<IMetadataTable> extractTablesFromDB(DatabaseMetaData dbMetaData, IMetadataConnection iMetadataConnection,
            int... limit) {
        String schema = iMetadataConnection.getSchema();
        final boolean as400 = EDatabaseTypeName.AS400.getProduct().equals(iMetadataConnection.getProduct());
        if (schema == null || "".equals(schema)) {
            if (EDatabaseTypeName.TERADATA.getProduct().equals(iMetadataConnection.getProduct())) {
                schema = iMetadataConnection.getDatabase();
            } else if (EDatabaseTypeName.IBMDB2.getProduct().equals(iMetadataConnection.getProduct())) {
                // for bug 13592
                String username = iMetadataConnection.getUsername();
                if (username != null) {
                    schema = username.toUpperCase();
                    iMetadataConnection.setSchema(schema);
                }
            } else if (as400) {
                schema = ExtractMetaDataUtils.retrieveSchemaPatternForAS400(iMetadataConnection.getUrl());
            }
        }
        if (dbMetaData.equals(oldMetadata) && schema.equals(oldSchema) && limit.equals(oldLimit)
                && (oldUseAllSynonyms == ExtractMetaDataUtils.isUseAllSynonyms())) {
            return oldMetadataRetrieved;
        }
        List<String> tablesToFilter = new ArrayList<String>();
        if (EDatabaseTypeName.ORACLEFORSID.getDisplayName().equals(iMetadataConnection.getDbType())
                || EDatabaseTypeName.ORACLESN.getDisplayName().equals(iMetadataConnection.getDbType())
                || EDatabaseTypeName.ORACLE_RAC.getDisplayName().equals(iMetadataConnection.getDbType())
                || EDatabaseTypeName.ORACLE_OCI.getDisplayName().equals(iMetadataConnection.getDbType())) {
            Statement stmt;
            try {
                stmt = ExtractMetaDataUtils.conn.createStatement();
                ExtractMetaDataUtils.setQueryStatementTimeout(stmt);
                if (iMetadataConnection.getDbVersionString() != null
                        && !iMetadataConnection.getDbVersionString().equals("ORACLE_8")) {
                    ResultSet rsTables = stmt.executeQuery(TableInfoParameters.ORACLE_10G_RECBIN_SQL);
                    tablesToFilter = getTableNamesFromQuery(rsTables);
                    rsTables.close();
                }
                stmt.close();
            } catch (SQLException e) {
                ExceptionHandler.process(e);
            }
        } else {
            // for bug 11052
            ExtractMetaDataUtils.setUseAllSynonyms(false);
        }

        List<IMetadataTable> medataTables = new ArrayList<IMetadataTable>();
        try {
            ResultSet rsTables = null, rsTableTypes = null;
            if (!ExtractMetaDataUtils.isUseAllSynonyms()) {
                rsTableTypes = dbMetaData.getTableTypes();
                Set<String> availableTableTypes = new HashSet<String>();
                String[] neededTableTypes = { ETableTypes.TABLETYPE_TABLE.getName(), ETableTypes.TABLETYPE_VIEW.getName(),
                        ETableTypes.TABLETYPE_SYNONYM.getName() };

                while (rsTableTypes.next()) {
                    // StringUtils.trimToEmpty(name) is because bug 4547
                    String currentTableType = StringUtils.trimToEmpty(rsTableTypes.getString("TABLE_TYPE")); //$NON-NLS-1$
                    // Because BASE TABLE which UniJDBCClientResultSet gets is not the support type of
                    // UniJDBCDatabaseMetaData, do this so as to dispose bug 17281.
                    if ("BASE TABLE".equalsIgnoreCase(currentTableType)) { //$NON-NLS-1$
                        currentTableType = ETableTypes.TABLETYPE_TABLE.getName();
                    }
                    if (ArrayUtils.contains(neededTableTypes, currentTableType)) {
                        availableTableTypes.add(currentTableType);
                    }
                }
                rsTableTypes.close();// See bug 5029 Avoid "Invalid cursor exception"

                if (dbMetaData.supportsSchemasInTableDefinitions() && schema != null && !schema.equals("")) { //$NON-NLS-1$
                    if (as400) {
                        String[] multiSchems = ExtractMetaDataUtils.getMultiSchems(schema);
                        if (multiSchems != null) {
                            for (String s : multiSchems) {
                                rsTables = dbMetaData.getTables(null, s.trim(), null,
                                        availableTableTypes.toArray(new String[] {}));
                                getMetadataTables(medataTables, rsTables, dbMetaData.supportsSchemasInTableDefinitions(),
                                        tablesToFilter, limit);
                                rsTables.close();
                            }
                            rsTables = null;
                        }
                    } else {
                        rsTables = dbMetaData.getTables(null, schema, null, availableTableTypes.toArray(new String[] {}));
                    }
                } else if (schema != null && !schema.equals("")) {
                    rsTables = dbMetaData.getTables(null, schema, null, availableTableTypes.toArray(new String[] {}));
                } else {

                    rsTables = dbMetaData.getTables(null, null, null, availableTableTypes.toArray(new String[] {}));
                }
            } else {
                String sql = GET_ALL_SYNONYMS;
                Statement stmt = ExtractMetaDataUtils.conn.createStatement();
                ExtractMetaDataUtils.setQueryStatementTimeout(stmt);
                rsTables = stmt.executeQuery(sql);
            }
            if (rsTables != null) {
                getMetadataTables(medataTables, rsTables, dbMetaData.supportsSchemasInTableDefinitions(), tablesToFilter, limit);
                rsTables.close();
            }

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
        oldMetadata = dbMetaData;
        oldSchema = schema;
        oldLimit = limit;
        oldUseAllSynonyms = ExtractMetaDataUtils.isUseAllSynonyms();
        oldMetadataRetrieved = medataTables;
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
            List<String> tablesToFilter, int... limit) throws SQLException {
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
            boolean isSynonym = false;
            MetadataTable medataTable = new MetadataTable();
            medataTable.setId(medataTables.size() + 1 + ""); //$NON-NLS-1$
            // See bug 5029 In some Linux odbc driver for MS SQL, their columns in ResultSet have not alias names.
            // Must use column index to fetch values.

            String tableName = ExtractMetaDataUtils.getStringMetaDataInfo(rsTables, "TABLE_NAME", null); //$NON-NLS-1$
            if (tableName == null) {
                tableName = ExtractMetaDataUtils.getStringMetaDataInfo(rsTables, 3);
            }
            if (tableName == null) {
                // in case it's in fact a synonym
                tableName = ExtractMetaDataUtils.getStringMetaDataInfo(rsTables, "SYNONYM_NAME", null); //$NON-NLS-1$
                isSynonym = true;
            }
            if (tableName == null || tablesToFilter.contains(tableName) || tableName.startsWith("/")) {
                continue;
            }

            medataTable.setLabel(tableName); //$NON-NLS-1$
            medataTable.setTableName(medataTable.getLabel());

            medataTable.setComment(ExtractMetaDataUtils.getStringMetaDataInfo(rsTables, "REMARKS", null)); //$NON-NLS-1$

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
            // if (tableType.startsWith("A")) {
            // System.out.println("AA");
            // }
            if ("T".equals(tableType)) { //$NON-NLS-1$
                tableType = ETableTypes.TABLETYPE_TABLE.getName();
            }
            if ("V".equals(tableType)) { //$NON-NLS-1$
                tableType = ETableTypes.TABLETYPE_VIEW.getName();
            }

            if (isSynonym) {
                tableType = "SYNONYM"; //$NON-NLS-1$
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

    public static synchronized List<TdColumn> returnColumns(IMetadataConnection iMetadataConnection, TableNode tableNode,
            boolean... dontCreateClose) {
        List<TdColumn> metadataColumns = new ArrayList<TdColumn>();
        boolean needCreateAndClose = dontCreateClose.length == 0 || !dontCreateClose[0];
        if (tableNode.getType() != TableNode.TABLE) {
            return metadataColumns;
        }
        NamedColumnSet table = tableNode.getTable();
        if (table == null) {
            table = tableNode.getView();
        }
        if (table == null) {
            return metadataColumns;
        }

        DriverShim wapperDriver = null;
        String dbType = "";
        try {
            // WARNING Schema equals sid or database
            if (needCreateAndClose || ExtractMetaDataUtils.conn == null || ExtractMetaDataUtils.conn.isClosed()) {
                List list = ExtractMetaDataUtils.getConnection(iMetadataConnection.getDbType(), iMetadataConnection.getUrl(),
                        iMetadataConnection.getUsername(), iMetadataConnection.getPassword(), iMetadataConnection.getDatabase(),
                        iMetadataConnection.getSchema(), iMetadataConnection.getDriverClass(),
                        iMetadataConnection.getDriverJarPath(), iMetadataConnection.getDbVersionString(),
                        iMetadataConnection.getAdditionalParams());
                if (list != null && list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i) instanceof DriverShim) {
                            wapperDriver = (DriverShim) list.get(i);
                        }
                    }
                }
            }
            dbType = iMetadataConnection.getDbType();
            DatabaseMetaData dbMetaData = ExtractMetaDataUtils.getDatabaseMetaData(ExtractMetaDataUtils.conn, dbType,
                    iMetadataConnection.isSqlMode(), iMetadataConnection.getDatabase());

            String tableLabel = tableNode.getValue();
            TableNode newNode = tableNode;
            String name = newNode.getItemType();

            // StringUtils.trimToEmpty(name) is because bug 4547
            if (name != null && StringUtils.trimToEmpty(name).equals(ETableTypes.TABLETYPE_SYNONYM.getName())) {
                String tableName = getTableNameBySynonym(ExtractMetaDataUtils.conn, newNode.getValue());
                if (tableName != null && tableName.contains("/")) {
                    tableName = tableName.replace("/", "");
                }
                // bug TDI-19547
                if (ExtractMetaDataUtils.conn.getMetaData().getDatabaseProductName().equals("DB2/NT")) {
                    fillSynonmsForDB2(iMetadataConnection, metadataColumns, table, tableName);
                } else if (ExtractMetaDataUtils.conn.getMetaData().getDatabaseProductName().equals("Microsoft SQL Server")) {
                    // TDI-19758
                    fillSynonmsForMSSQL(iMetadataConnection, metadataColumns, table, tableName, dbMetaData);
                } else {
                    // bug TDI-19382
                    if (iMetadataConnection.getDbType().equals(EDatabaseTypeName.ORACLEFORSID.getDisplayName())
                            || iMetadataConnection.getDbType().equals(EDatabaseTypeName.ORACLESN.getDisplayName())
                            || iMetadataConnection.getDbType().equals(EDatabaseTypeName.ORACLE_OCI.getDisplayName())
                            || iMetadataConnection.getDbType().equals(EDatabaseTypeName.ORACLE_RAC.getDisplayName())) {
                        tableName = newNode.getValue();
                    }
                    newNode.setValue(tableName);
                    fillSynonmsForOracle(iMetadataConnection, metadataColumns, table, tableName);
                }
            } else {
                if (tableLabel.contains("/")) {
                    tableLabel = tableLabel.replace("/", "");
                }
                newNode.setValue(tableLabel);
                metadataColumns = MetadataFillFactory.getDBInstance().fillColumns(table, iMetadataConnection, dbMetaData, null);
            }

            // metadataColumns = ExtractMetaDataFromDataBase.extractColumns(dbMetaData, newNode, iMetadataConnection,
            // dbType);

            ColumnSetHelper.addColumns(table, metadataColumns);

            if (needCreateAndClose) {
                ExtractMetaDataUtils.closeConnection();
            }
        } catch (Exception e) {
            ExceptionHandler.process(e);
            log.error(e);
        } finally {
            // bug 22619
            if (dbType != null
                    && (dbType.equals(EDatabaseTypeName.HSQLDB.getDisplayName())
                            || dbType.equals(EDatabaseTypeName.HSQLDB_SERVER.getDisplayName())
                            || dbType.equals(EDatabaseTypeName.HSQLDB_WEBSERVER.getDisplayName()) || dbType
                            .equals(EDatabaseTypeName.HSQLDB_IN_PROGRESS.getDisplayName()))) {
                ExtractMetaDataUtils.closeConnection();
            }
            if (wapperDriver != null
                    && (iMetadataConnection.getDriverClass().equals(EDatabase4DriverClassName.JAVADB_EMBEDED.getDriverClass())
                            || dbType.equals(EDatabaseTypeName.JAVADB_EMBEDED.getDisplayName())
                            || dbType.equals(EDatabaseTypeName.JAVADB_DERBYCLIENT.getDisplayName())
                            || dbType.equals(EDatabaseTypeName.JAVADB_JCCJDBC.getDisplayName()) || dbType
                            .equals(EDatabaseTypeName.HSQLDB_IN_PROGRESS.getDisplayName()))) {
                try {
                    wapperDriver.connect("jdbc:derby:;shutdown=true", null); //$NON-NLS-1$
                } catch (SQLException e) {
                    // exception of shutdown success. no need to catch.
                }
            }

        }

        return metadataColumns;
    }

    /* fill the columns for synonm,can get column name,column data type,length,precision,nullable */
    private static void fillSynonmsForOracle(IMetadataConnection iMetadataConnection, List<TdColumn> metadataColumns,
            NamedColumnSet table, String tableName) throws SQLException {
        // need to retrieve columns of synonym by useing sql rather than get them from jdbc metadata
        String synSQL = "SELECT all_tab_columns.*\n" + "FROM all_tab_columns\n" + "LEFT OUTER JOIN all_synonyms\n"
                + "ON all_tab_columns.TABLE_NAME = all_synonyms.TABLE_NAME\n"
                + "AND ALL_SYNONYMS.TABLE_OWNER = all_tab_columns.OWNER\n" + "WHERE all_synonyms.SYNONYM_NAME =" + "\'"
                + tableName + "\'\n";
        // bug TDI-19382
        if (!("").equals(iMetadataConnection.getSchema())) {
            synSQL += "and all_synonyms.OWNER =\'" + iMetadataConnection.getSchema() + "\'";
        } else if (table.eContainer() instanceof Schema) {
            Schema schema = (Schema) table.eContainer();
            synSQL += "and all_synonyms.OWNER =\'" + schema.getName() + "\'";
        }
        Statement sta = ExtractMetaDataUtils.conn.createStatement();
        ExtractMetaDataUtils.setQueryStatementTimeout(sta);
        ResultSet columns = sta.executeQuery(synSQL);
        String typeName = null;
        int index = 0;
        while (columns.next()) {
            long numPrecRadix = 0;
            String columnName = columns.getString(GetColumn.COLUMN_NAME.name());
            TdColumn column = ColumnHelper.createTdColumn(columnName);

            String label = column.getLabel();
            label = ManagementTextUtils.filterSpecialChar(label);
            String sub = ""; //$NON-NLS-1$
            String sub2 = ""; //$NON-NLS-1$
            String label2 = label;
            if (label != null && label.length() > 0 && label.startsWith("_")) { //$NON-NLS-1$
                sub = label.substring(1);
                if (sub != null && sub.length() > 0) {
                    sub2 = sub.substring(1);
                }
            }
            ICoreService coreService = CoreRuntimePlugin.getInstance().getCoreService();
            if (coreService.isKeyword(label) || coreService.isKeyword(sub) || coreService.isKeyword(sub2)) {
                label = "_" + label; //$NON-NLS-1$
            }

            label = MetadataToolHelper.validateColumnName(label, index);
            column.setLabel(label);
            column.setOriginalField(label2);

            if (!ExtractMetaDataUtils.needFakeDatabaseMetaData(iMetadataConnection.getDbType(), iMetadataConnection.isSqlMode())) {
                // dataType = columns.getInt(GetColumn.DATA_TYPE.name());
                typeName = columns.getString(GetColumn.DATA_TYPE.name());
            }
            try {
                int column_size = columns.getInt("DATA_LENGTH");
                column.setLength(column_size);
                numPrecRadix = columns.getLong("DATA_PRECISION");//$NON-NLS-N$
                column.setPrecision(numPrecRadix);
            } catch (Exception e1) {
                log.warn(e1, e1);
            }

            DatabaseConnection dbConnection = (DatabaseConnection) ConnectionHelper.getConnection(table);
            String dbmsId = dbConnection == null ? null : dbConnection.getDbmsId();
            if (dbmsId != null) {
                MappingTypeRetriever mappingTypeRetriever = MetadataTalendType.getMappingTypeRetriever(dbmsId);
                String talendType = mappingTypeRetriever.getDefaultSelectedTalendType(typeName, ExtractMetaDataUtils
                        .getIntMetaDataInfo(columns, "DATA_LENGTH"), ExtractMetaDataUtils.getIntMetaDataInfo(columns, //$NON-NLS-1$
                        "DATA_PRECISION")); //$NON-NLS-1$
                column.setTalendType(talendType);
                String defaultSelectedDbType = MetadataTalendType.getMappingTypeRetriever(dbConnection.getDbmsId())
                        .getDefaultSelectedDbType(talendType);
                column.setSourceType(defaultSelectedDbType);
            }
            try {
                column.setNullable("Y".equals(columns.getString(GetColumn.NULLABLE.name()))); //$NON-NLS-1$
            } catch (Exception e) {
                log.error(e);
            }
            metadataColumns.add(column);
            index++;

        }
        columns.close();
    }

    /* fill the columns for synonm,can get column name,column data type,length,precision,nullable */
    private static void fillSynonmsForDB2(IMetadataConnection iMetadataConnection, List<TdColumn> metadataColumns,
            NamedColumnSet table, String tableName) throws SQLException {
        // need to retrieve columns of synonym by useing sql rather than get them from jdbc metadata
        String synSQL = "SELECT a.*\n" + "FROM SYSCAT.COLUMNS a\n" + "LEFT OUTER JOIN SYSIBM.SYSTABLES b\n"
                + "ON a.TABNAME = b.NAME\n" + "AND a.TABSCHEMA = b.CREATOR\n" + "where a.TABNAME =" + "\'" + tableName + "\'\n";
        if (!("").equals(iMetadataConnection.getSchema())) {
            synSQL += "AND b.CREATOR =\'" + iMetadataConnection.getSchema() + "\'";
        }
        synSQL += "ORDER BY a.COLNO";
        Statement sta = ExtractMetaDataUtils.conn.createStatement();
        ExtractMetaDataUtils.setQueryStatementTimeout(sta);
        ResultSet columns = sta.executeQuery(synSQL);
        String typeName = null;
        int index = 0;
        while (columns.next()) {
            long numPrecRadix = 0;
            String columnName = columns.getString("COLNAME");
            TdColumn column = ColumnHelper.createTdColumn(columnName);

            String label = column.getLabel();
            label = ManagementTextUtils.filterSpecialChar(label);
            String sub = ""; //$NON-NLS-1$
            String sub2 = ""; //$NON-NLS-1$
            String label2 = label;
            if (label != null && label.length() > 0 && label.startsWith("_")) { //$NON-NLS-1$
                sub = label.substring(1);
                if (sub != null && sub.length() > 0) {
                    sub2 = sub.substring(1);
                }
            }
            ICoreService coreService = CoreRuntimePlugin.getInstance().getCoreService();
            if (coreService.isKeyword(label) || coreService.isKeyword(sub) || coreService.isKeyword(sub2)) {
                label = "_" + label; //$NON-NLS-1$
            }

            label = MetadataToolHelper.validateColumnName(label, index);
            column.setLabel(label);
            column.setOriginalField(label2);

            if (!ExtractMetaDataUtils.needFakeDatabaseMetaData(iMetadataConnection.getDbType(), iMetadataConnection.isSqlMode())) {
                // dataType = columns.getInt(GetColumn.DATA_TYPE.name());
                typeName = columns.getString("TYPENAME");
            }
            try {
                int column_size = columns.getInt("LENGTH");
                column.setLength(column_size);
                numPrecRadix = columns.getLong("SCALE");//$NON-NLS-N$
                column.setPrecision(numPrecRadix);
            } catch (Exception e1) {
                log.warn(e1, e1);
            }

            DatabaseConnection dbConnection = (DatabaseConnection) ConnectionHelper.getConnection(table);
            String dbmsId = dbConnection == null ? null : dbConnection.getDbmsId();
            if (dbmsId != null) {
                MappingTypeRetriever mappingTypeRetriever = MetadataTalendType.getMappingTypeRetriever(dbmsId);
                String talendType = mappingTypeRetriever.getDefaultSelectedTalendType(typeName, ExtractMetaDataUtils
                        .getIntMetaDataInfo(columns, "LENGTH"), ExtractMetaDataUtils.getIntMetaDataInfo(columns, //$NON-NLS-1$
                        "SCALE")); //$NON-NLS-1$
                column.setTalendType(talendType);
                String defaultSelectedDbType = MetadataTalendType.getMappingTypeRetriever(dbConnection.getDbmsId())
                        .getDefaultSelectedDbType(talendType);
                column.setSourceType(defaultSelectedDbType);
            }
            try {
                column.setNullable("Y".equals(columns.getString("NULLS"))); //$NON-NLS-1$
            } catch (Exception e) {
                log.error(e);
            }
            metadataColumns.add(column);
            index++;

        }
        columns.close();
    }

    /* fill the columns for synonm,can get column name,column data type,length,precision,nullable */
    private static void fillSynonmsForMSSQL(IMetadataConnection iMetadataConnection, List<TdColumn> metadataColumns,
            NamedColumnSet table, String tableName, DatabaseMetaData dbMetaData) throws SQLException {
        // get TABLE_CATALOG ,TABLE_SCHEMA ,TABLE_NAME from base_object_name of sys.synonyms
        String str = tableName;
        String TABLE_SCHEMA = null;
        String TABLE_NAME = null;
        String splitstr = str;
        int position = 0;
        int count = 0;
        if (tableName != null) {
            while (str.contains(".")) {
                count++;
                splitstr = splitstr.substring(splitstr.indexOf("]") + 3, splitstr.length());
            }
            if (str.indexOf("[") == 0) {
                TABLE_NAME = str.substring(1, str.length() - 1);
            } else if (str.indexOf("[") == -1) {
                TABLE_NAME = tableName;
            } else {
                if (count == 2) {
                    str = str.substring(str.indexOf("]") + 3, str.length());
                }
                position = str.indexOf("]");
                TABLE_SCHEMA = str.substring(0, position);
                TABLE_NAME = str.substring(position + 3, str.length());
            }
        }
        // need to retrieve columns of synonym by useing sql rather than get them from jdbc metadata
        String synSQL = "select * from INFORMATION_SCHEMA.COLUMNS where  TABLE_NAME =\'" + TABLE_NAME + "\'";
        if (null != TABLE_SCHEMA) {
            synSQL += "\nand TABLE_SCHEMA =\'" + TABLE_SCHEMA + "\'";
        }
        if (!("").equals(iMetadataConnection.getDatabase())) {
            synSQL += "\nand TABLE_CATALOG =\'" + iMetadataConnection.getDatabase() + "\'";
        }
        Statement sta = ExtractMetaDataUtils.conn.createStatement();
        ExtractMetaDataUtils.setQueryStatementTimeout(sta);
        ResultSet columns = sta.executeQuery(synSQL);
        String typeName = null;
        int index = 0;
        while (columns.next()) {
            int column_size = 0;
            String lenString = null;
            long numPrecRadix = 0;
            String columnName = columns.getString(GetColumn.COLUMN_NAME.name());
            TdColumn column = ColumnHelper.createTdColumn(columnName);

            String label = column.getLabel();
            label = ManagementTextUtils.filterSpecialChar(label);
            String sub = ""; //$NON-NLS-1$
            String sub2 = ""; //$NON-NLS-1$
            String label2 = label;
            if (label != null && label.length() > 0 && label.startsWith("_")) { //$NON-NLS-1$
                sub = label.substring(1);
                if (sub != null && sub.length() > 0) {
                    sub2 = sub.substring(1);
                }
            }
            ICoreService coreService = CoreRuntimePlugin.getInstance().getCoreService();
            if (coreService.isKeyword(label) || coreService.isKeyword(sub) || coreService.isKeyword(sub2)) {
                label = "_" + label; //$NON-NLS-1$
            }

            label = MetadataToolHelper.validateColumnName(label, index);
            column.setLabel(label);
            column.setOriginalField(label2);

            if (!ExtractMetaDataUtils.needFakeDatabaseMetaData(iMetadataConnection.getDbType(), iMetadataConnection.isSqlMode())) {
                // dataType = columns.getInt(GetColumn.DATA_TYPE.name());
                typeName = columns.getString(GetColumn.DATA_TYPE.name());
            }
            try {
                lenString = "NUMERIC_PRECISION";
                column_size = columns.getInt("NUMERIC_PRECISION");
                if (columns.getString("CHARACTER_MAXIMUM_LENGTH") != null) {
                    column_size = columns.getInt("CHARACTER_MAXIMUM_LENGTH");
                    lenString = "CHARACTER_MAXIMUM_LENGTH";
                }
                column.setLength(column_size);
                numPrecRadix = columns.getLong("NUMERIC_SCALE");//$NON-NLS-N$
                column.setPrecision(numPrecRadix);
            } catch (Exception e1) {
                log.warn(e1, e1);
            }

            DatabaseConnection dbConnection = (DatabaseConnection) ConnectionHelper.getConnection(table);
            String dbmsId = dbConnection == null ? null : dbConnection.getDbmsId();
            if (dbmsId != null) {
                MappingTypeRetriever mappingTypeRetriever = MetadataTalendType.getMappingTypeRetriever(dbmsId);
                String talendType = mappingTypeRetriever.getDefaultSelectedTalendType(typeName,
                        ExtractMetaDataUtils.getIntMetaDataInfo(columns, lenString),
                        ExtractMetaDataUtils.getIntMetaDataInfo(columns, //$NON-NLS-1$
                                "NUMERIC_SCALE")); //$NON-NLS-1$
                column.setTalendType(talendType);
                String defaultSelectedDbType = MetadataTalendType.getMappingTypeRetriever(dbConnection.getDbmsId())
                        .getDefaultSelectedDbType(talendType);
                column.setSourceType(defaultSelectedDbType);
            }
            try {
                column.setNullable("YES".equals(columns.getString("IS_NULLABLE"))); //$NON-NLS-1$
            } catch (Exception e) {
                log.error(e);
            }
            metadataColumns.add(column);
            index++;
        }
        columns.close();
    }

    /**
     * DOC cantoine. Method to return a Collection of Column about a Table for a DB connection.
     * 
     * @param IMetadataConnection iMetadataConnection
     * @param String tableLabel
     * @return Collection of MetadataColumn Object of a Table
     * @deprecated
     */
    public static synchronized List<TdColumn> returnMetadataColumnsFormTable(IMetadataConnection iMetadataConnection,
            String tableLabel, boolean... dontCreateClose) {

        List<TdColumn> metadataColumns = new ArrayList<TdColumn>();

        boolean needCreateAndClose = dontCreateClose.length == 0 || !dontCreateClose[0];

        // bug 17980
        DriverShim wapperDriver = null;
        String dbType = "";

        try {
            // WARNING Schema equals sid or database
            if (needCreateAndClose || ExtractMetaDataUtils.conn == null || ExtractMetaDataUtils.conn.isClosed()) {
                List list = ExtractMetaDataUtils.getConnection(iMetadataConnection.getDbType(), iMetadataConnection.getUrl(),
                        iMetadataConnection.getUsername(), iMetadataConnection.getPassword(), iMetadataConnection.getDatabase(),
                        iMetadataConnection.getSchema(), iMetadataConnection.getDriverClass(),
                        iMetadataConnection.getDriverJarPath(), iMetadataConnection.getDbVersionString(),
                        iMetadataConnection.getAdditionalParams());
                if (list != null && list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i) instanceof DriverShim) {
                            wapperDriver = (DriverShim) list.get(i);
                        }
                    }
                }
            }
            dbType = iMetadataConnection.getDbType();
            DatabaseMetaData dbMetaData = ExtractMetaDataUtils.getDatabaseMetaData(ExtractMetaDataUtils.conn, dbType,
                    iMetadataConnection.isSqlMode(), iMetadataConnection.getDatabase());

            String name = getTableTypeByTableName(tableLabel);
            boolean isAccess = EDatabaseTypeName.ACCESS.getDisplayName().equals(iMetadataConnection.getDbType());
            if (name != null && StringUtils.trimToEmpty(name).equals(ETableTypes.TABLETYPE_SYNONYM.getName()) && !isAccess) {
                String tableName = getTableNameBySynonym(ExtractMetaDataUtils.conn, tableLabel);
                if (tableName.contains("/")) {
                    tableName = tableName.replace("/", "");
                }
                tableLabel = tableName;
            }

            List<String> cataAndShema = getTableCatalogAndSchema((DatabaseConnection) iMetadataConnection.getCurrentConnection(),
                    tableLabel);
            metadataColumns = extractColumns(dbMetaData, iMetadataConnection, dbType, cataAndShema.get(0), cataAndShema.get(1),
                    tableLabel);

            if (needCreateAndClose) {
                ExtractMetaDataUtils.closeConnection();
            }
        } catch (Exception e) {
            log.error(e.toString());
            throw new RuntimeException(e);
        } finally {
            if (wapperDriver != null
                    && (iMetadataConnection.getDriverClass().equals(EDatabase4DriverClassName.JAVADB_EMBEDED.getDriverClass())
                            || dbType.equals(EDatabaseTypeName.JAVADB_EMBEDED.getDisplayName())
                            || dbType.equals(EDatabaseTypeName.JAVADB_DERBYCLIENT.getDisplayName())
                            || dbType.equals(EDatabaseTypeName.JAVADB_JCCJDBC.getDisplayName()) || dbType
                            .equals(EDatabaseTypeName.HSQLDB_IN_PROGRESS.getDisplayName()))) {
                try {
                    wapperDriver.connect("jdbc:derby:;shutdown=true", null); //$NON-NLS-1$
                } catch (SQLException e) {
                    // exception of shutdown success. no need to catch.
                }
            }

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
        Statement sta = null;
        ResultSet resultSet = null;

        try {
            if (conn.getMetaData().getDatabaseProductName().equals("DB2/NT")) {
                String sql = "SELECT NAME,BASE_NAME FROM SYSIBM.SYSTABLES where TYPE='A' and  name ='" + name + "'";
                sta = conn.createStatement();
                ExtractMetaDataUtils.setQueryStatementTimeout(sta);
                resultSet = sta.executeQuery(sql);
                while (resultSet.next()) {
                    String baseName = resultSet.getString("base_name").trim();
                    return baseName;

                }
            }
        } catch (SQLException e) {
            log.error(e.toString());
            throw new RuntimeException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (sta != null) {
                    sta.close();
                }
            } catch (SQLException e) {
                log.equals(e.toString());
            }
        }

        try {
            if (conn.getMetaData().getDatabaseProductName().equals("Microsoft SQL Server")) {
                String sql = "SELECT object_id ,parent_object_id as parentid, name AS object_name ,   base_object_name as base_name from sys.synonyms where  name ='"
                        + name + "'";
                sta = conn.createStatement();
                ExtractMetaDataUtils.setQueryStatementTimeout(sta);
                resultSet = sta.executeQuery(sql);
                while (resultSet.next()) {
                    String baseName = resultSet.getString("base_name").trim();
                    if (baseName.contains(".") && baseName.length() > 2) {
                        return baseName.substring(baseName.indexOf(".") + 2, baseName.length() - 1);
                    }
                    return baseName;
                }
            }
        } catch (SQLException e) {
            log.error(e.toString());
            throw new RuntimeException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (sta != null) {
                    sta.close();
                }
            } catch (SQLException e) {
                log.equals(e.toString());
            }
        }

        try {
            if (conn.getMetaData().getDatabaseProductName().equals("Oracle")) {
                String sql = "select TABLE_NAME from ALL_SYNONYMS where SYNONYM_NAME = '" + name + "'"; //$NON-NLS-1$ //$NON-NLS-2$ 
                // String sql = "select * from all_tab_columns where upper(table_name)='" + name +
                // "' order by column_id";
                // Statement sta;
                sta = conn.createStatement();
                ExtractMetaDataUtils.setQueryStatementTimeout(sta);
                resultSet = sta.executeQuery(sql);
                while (resultSet.next()) {
                    return resultSet.getString("TABLE_NAME"); //$NON-NLS-1$
                }
            }
        } catch (SQLException e) {
            log.error(e.toString());
            throw new RuntimeException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (sta != null) {
                    sta.close();
                }
            } catch (SQLException e) {
                log.equals(e.toString());
            }
        }
        return null;
    }

    private static String filterTableNameWithDBRule(final String tablename, final String escape) {
        String table = tablename;
        return table;
        // return table.replaceAll("_", "/_");
    }

    public static List<TdColumn> extractColumns(DatabaseMetaData dbMetaData, IMetadataConnection metadataConnection,
            String databaseType, String catalogName, String schemaName, String tableName) {
        columnIndex = 0;
        List<TdColumn> metadataColumns = new ArrayList<TdColumn>();
        HashMap<String, String> primaryKeys = new HashMap<String, String>();
        ResultSet columns = null;
        Statement stmt = null;

        try {
            boolean isAccess = EDatabaseTypeName.ACCESS.getDisplayName().equals(metadataConnection.getDbType());
            boolean isHive = MetadataConnectionUtils.isHive(dbMetaData);
            if (!isHive) {
                try {
                    ResultSet keys;
                    if (!isAccess) {
                        keys = dbMetaData.getPrimaryKeys(catalogName, schemaName, tableName);
                    } else {
                        keys = dbMetaData.getIndexInfo(catalogName, schemaName, tableName, true, true);
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
                checkUniqueKeyConstraint(tableName, primaryKeys, connection);
            }

            if (ExtractMetaDataUtils.isUseAllSynonyms()) {
                String sql = "select * from all_tab_columns where table_name='" + tableName + "' "; //$NON-NLS-1$ //$NON-NLS-2$
                stmt = ExtractMetaDataUtils.conn.createStatement();
                ExtractMetaDataUtils.setQueryStatementTimeout(stmt);
                columns = stmt.executeQuery(sql);
            } else {
                if (isAccess && schemaName != null) {
                    // The Access's schema should be null here otherwise the columns will be null
                    columns = dbMetaData.getColumns(catalogName, null, tableName, null);
                } else {
                    columns = dbMetaData.getColumns(catalogName, schemaName, tableName, null);
                }
            }

            // boolean isMYSQL = EDatabaseTypeName.MYSQL.getDisplayName().equals(metadataConnection.getDbType());
            // ResultSetMetaData resultMetadata = null;
            // if (isMYSQL) {
            // Statement statement = connection.createStatement();
            // String query = "SELECT * FROM " + tableName + " limit 1";
            // ResultSet resultSet = statement.executeQuery(query);
            // resultMetadata = resultSet.getMetaData();
            // }
            IRepositoryService repositoryService = CoreRuntimePlugin.getInstance().getRepositoryService();
            boolean isMSSQL = EDatabaseTypeName.MSSQL.getDisplayName().equals(metadataConnection.getDbType());
            while (columns.next()) {
                Boolean b = false;
                String fetchTableName = ExtractMetaDataUtils.getStringMetaDataInfo(columns, "TABLE_NAME", null); //$NON-NLS-1$
                fetchTableName = ManagementTextUtils.filterSpecialChar(fetchTableName); // for 8115
                if (fetchTableName.equals(tableName) || databaseType.equals(EDatabaseTypeName.SQLITE.getDisplayName())) {
                    TdColumn metadataColumn = RelationalFactory.eINSTANCE.createTdColumn();
                    String label = ExtractMetaDataUtils.getStringMetaDataInfo(columns, "COLUMN_NAME", null); //$NON-NLS-1$
                    label = ManagementTextUtils.filterSpecialChar(label);
                    String sub = ""; //$NON-NLS-1$
                    String sub2 = ""; //$NON-NLS-1$
                    String label2 = label;
                    // label = label.replaceAll("[^_a-zA-Z0-9]", "_");
                    if (label != null && label.length() > 0 && label.startsWith("_")) { //$NON-NLS-1$
                        sub = label.substring(1);
                        if (sub != null && sub.length() > 0) {
                            sub2 = sub.substring(1);
                        }
                    }
                    if (coreService.isKeyword(label) || coreService.isKeyword(sub) || coreService.isKeyword(sub2)) {
                        label = "_" + label; //$NON-NLS-1$
                        b = true;
                    }
                    metadataColumn.setLabel(label); //$NON-NLS-1$
                    //                    if (label2 != null && label2.length() > 0 && label2.startsWith("_")) { //$NON-NLS-1$
                    // String substring = label2.substring(1);
                    // if (b
                    //                                && label2.startsWith("_") //$NON-NLS-1$
                    // && (coreService.isKeyword(substring) || coreService.isKeyword(sub) ||
                    // coreService.isKeyword(sub2))) {
                    // label2 = substring;
                    // }
                    // }
                    metadataColumn.setOriginalField(label2);

                    // Validate the column if it contains space or illegal
                    // characters.
                    if (repositoryService != null) {
                        // metadataColumn.setDisplayField(repositoryService.validateColumnName(metadataColumn.getLabel(),
                        // columnIndex));
                        label = repositoryService.validateColumnName(label, columnIndex);
                        metadataColumn.setLabel(label);
                    }
                    columnIndex++;

                    if (primaryKeys != null && !primaryKeys.isEmpty()
                            && primaryKeys.get(metadataColumn.getOriginalField()) != null) {
                        metadataColumn.setKey(true);
                    } else {
                        metadataColumn.setKey(false);
                    }

                    String typeName = "TYPE_NAME"; //$NON-NLS-1$
                    if (ExtractMetaDataUtils.isUseAllSynonyms()) {
                        typeName = "DATA_TYPE"; //$NON-NLS-1$
                    }
                    String dbType = ExtractMetaDataUtils.getStringMetaDataInfo(columns, typeName, null).toUpperCase(); //$NON-NLS-1$
                    // For sometime the dbType will return one more space character at the end.So need to trim,comment
                    // for bug 17509
                    dbType = dbType.trim();
                    dbType = ManagementTextUtils.filterSpecialChar(dbType);
                    dbType = handleDBtype(dbType);
                    metadataColumn.setSourceType(dbType);
                    Integer columnSize;
                    // if (isMYSQL) {
                    // columnSize = ExtractMetaDataUtils.getMysqlIntMetaDataInfo(resultMetadata, columnIndex);
                    // } else {
                    columnSize = ExtractMetaDataUtils.getIntMetaDataInfo(columns, "COLUMN_SIZE");
                    // }
                    metadataColumn.setLength(columnSize); //$NON-NLS-1$
                    // Convert dbmsType to TalendType

                    String talendType = null;

                    // qli modified to fix the bug 6654.
                    if (metadataConnection.getMapping() != null) {
                        mappingTypeRetriever = MetadataTalendType.getMappingTypeRetriever(metadataConnection.getMapping());
                    }
                    Integer intMetaDataInfo = ExtractMetaDataUtils.getIntMetaDataInfo(columns, "DECIMAL_DIGITS");
                    talendType = mappingTypeRetriever.getDefaultSelectedTalendType(dbType, columnSize, intMetaDataInfo); //$NON-NLS-1$
                    talendType = ManagementTextUtils.filterSpecialChar(talendType);
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
                    // for bug 13078

                    boolean isNullable = ExtractMetaDataUtils.getBooleanMetaDataInfo(columns, "IS_NULLABLE"); //$NON-NLS-1$ 
                    metadataColumn.setNullable(isNullable);

                    // gcui:see bug 6450, if in the commentInfo have some invalid character then will remove it.
                    String commentInfo = ExtractMetaDataUtils.getStringMetaDataInfo(columns, "REMARKS", null); //$NON-NLS-1$
                    if (commentInfo != null && commentInfo.length() > 0) {
                        commentInfo = ManagementTextUtils.filterSpecialChar(commentInfo);
                    }
                    // gcui:if not oracle database use "REMARKS" select comments
                    metadataColumn.setComment(commentInfo); //$NON-NLS-1$
                    if (!isAccess) { // jdbc-odbc driver won't apply methods for access
                        TDColumnAttributeHelper.addColumnAttribute(label, label2, dbType, columnSize, intMetaDataInfo,
                                commentInfo, columns, metadataColumn,
                                (DatabaseConnection) metadataConnection.getCurrentConnection());// columnName,
                    }

                    if (isMSSQL && "INT IDENTITY".equals(dbType)) { //$NON-NLS-1$  // && metadataColumn.isKey()

                        // for MSSQL bug16852
                        // get inent_seed get_incr for schema's length, precision.
                        Integer ident1 = 0;
                        Integer ident2 = 0;
                        try {
                            PreparedStatement statement = ExtractMetaDataUtils.conn
                                    .prepareStatement(" select IDENT_SEED ( '" + tableName + "')," + "IDENT_INCR ( '" //$NON-NLS-N$ //$NON-NLS-N$ //$NON-NLS-N$
                                            + tableName + "')"); //$NON-NLS-1$ 
                            ResultSet resultSet = null;
                            ExtractMetaDataUtils.setQueryStatementTimeout(statement);
                            if (statement.execute()) {
                                resultSet = statement.getResultSet();
                                while (resultSet.next()) {
                                    String st1 = resultSet.getString(1);
                                    String st2 = resultSet.getString(2);
                                    Integer valueOf1 = Integer.valueOf(st1);
                                    if (valueOf1 != null) {
                                        ident1 = valueOf1;
                                    }
                                    Integer valueOf2 = Integer.valueOf(st2);
                                    if (valueOf2 != null) {
                                        ident2 = valueOf2;
                                    }

                                }
                            }
                            resultSet.close();
                            statement.close();

                        } catch (Exception e) {
                            log.error(e.toString());
                        }

                        metadataColumn.setLength(ident1);
                        metadataColumn.setPrecision(ident2);
                    } else {
                        metadataColumn.setPrecision(intMetaDataInfo);
                    }

                    // cantoine : patch to fix 0x0 pb cause by Bad Schema
                    // description
                    String stringMetaDataInfo = ExtractMetaDataUtils.getStringMetaDataInfo(columns, "COLUMN_DEF", dbMetaData); //$NON-NLS-1$
                    if (stringMetaDataInfo != null && stringMetaDataInfo.length() > 0 && stringMetaDataInfo.charAt(0) == 0x0) {
                        stringMetaDataInfo = "\\0"; //$NON-NLS-1$
                    }
                    stringMetaDataInfo = ManagementTextUtils.filterSpecialChar(stringMetaDataInfo);
                    metadataColumn.setDefaultValue(stringMetaDataInfo);

                    // for bug 6919, oracle driver doesn't give correctly the length for timestamp
                    if (EDatabaseTypeName.ORACLEFORSID.getDisplayName().equals(metadataConnection.getDbType())
                            || EDatabaseTypeName.ORACLESN.getDisplayName().equals(metadataConnection.getDbType())
                            || EDatabaseTypeName.ORACLE_RAC.getDisplayName().equals(metadataConnection.getDbType())
                            || EDatabaseTypeName.ORACLE_OCI.getDisplayName().equals(metadataConnection.getDbType())) {
                        if (dbType.equals("TIMESTAMP")) { //$NON-NLS-1$
                            metadataColumn.setLength(metadataColumn.getPrecision());
                            metadataColumn.setPrecision(-1);
                        }
                    }
                    metadataColumns.add(metadataColumn);
                }
            }

            // gcui:if it is oracle database, use this SQL select comments.
            // there will do one query to retrieve all comments on the table.

            if (EDatabaseTypeName.ORACLEFORSID.getDisplayName().equals(metadataConnection.getDbType())
                    || EDatabaseTypeName.ORACLESN.getDisplayName().equals(metadataConnection.getDbType())
                    || EDatabaseTypeName.ORACLE_RAC.getDisplayName().equals(metadataConnection.getDbType())
                    || EDatabaseTypeName.ORACLE_OCI.getDisplayName().equals(metadataConnection.getDbType())) {
                try {
                    PreparedStatement statement = ExtractMetaDataUtils.conn
                            .prepareStatement("SELECT COMMENTS FROM USER_COL_COMMENTS WHERE TABLE_NAME='" //$NON-NLS-1$
                                    + tableName + "'"); //$NON-NLS-1$ //$NON-NLS-2$
                    ResultSet keys = null;
                    ExtractMetaDataUtils.setQueryStatementTimeout(statement);
                    if (statement.execute()) {
                        keys = statement.getResultSet();
                        int i = 0;
                        while (keys.next()) {
                            MetadataColumn metadataColumn = (MetadataColumn) metadataColumns.get(i++);
                            metadataColumn.setComment(ManagementTextUtils.filterSpecialChar(keys.getString("COMMENTS"))); //$NON-NLS-1$
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
            e.printStackTrace();
        } catch (Exception e) {
            // e.printStackTrace();
            ExceptionHandler.process(e);
            log.error(e.toString());
            throw new RuntimeException(e);
        } finally {
            try {
                if (columns != null) {
                    columns.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                log.equals(e.toString());
            }
        }

        return metadataColumns;
    }

    public static List<TdColumn> extractColumns(DatabaseMetaData dbMetaData, TableNode tableNode,
            IMetadataConnection metadataConnection, String databaseType) {
        columnIndex = 0;
        List<TdColumn> metadataColumns = new ArrayList<TdColumn>();
        HashMap<String, String> primaryKeys = new HashMap<String, String>();

        try {
            String catalogName = null;
            String schemaName = null;

            TableNode parent = tableNode.getParent();
            if (parent != null) {
                if (parent.getType() == TableNode.CATALOG) {
                    catalogName = parent.getValue();
                } else if (parent.getType() == TableNode.SCHEMA) {
                    schemaName = parent.getValue();
                    TableNode catalogNode = parent.getParent();
                    if (catalogNode != null) {
                        catalogName = catalogNode.getValue();
                    }
                }
            }
            boolean isAccess = EDatabaseTypeName.ACCESS.getDisplayName().equals(metadataConnection.getDbType());
            boolean isHive = MetadataConnectionUtils.isHive(dbMetaData);
            if (!isHive) {
                try {
                    ResultSet keys;
                    if (!isAccess) {
                        keys = dbMetaData.getPrimaryKeys(catalogName, schemaName, tableNode.getValue());
                    } else {
                        keys = dbMetaData.getIndexInfo(catalogName, schemaName, tableNode.getValue(), true, true);
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
                checkUniqueKeyConstraint(tableNode.getValue(), primaryKeys, connection);
            }

            String tableName = tableNode.getValue();
            ResultSet columns;

            if (ExtractMetaDataUtils.isUseAllSynonyms()) {
                String sql = "select * from all_tab_columns where table_name='" + tableName + "' "; //$NON-NLS-1$ //$NON-NLS-2$
                Statement stmt = ExtractMetaDataUtils.conn.createStatement();
                ExtractMetaDataUtils.setQueryStatementTimeout(stmt);
                columns = stmt.executeQuery(sql);
            } else {
                columns = dbMetaData.getColumns(catalogName, schemaName, tableName, null);
            }

            // boolean isMYSQL = EDatabaseTypeName.MYSQL.getDisplayName().equals(metadataConnection.getDbType());
            // ResultSetMetaData resultMetadata = null;
            // if (isMYSQL) {
            // Statement statement = connection.createStatement();
            // String query = "SELECT * FROM " + tableName + " limit 1";
            // ResultSet resultSet = statement.executeQuery(query);
            // resultMetadata = resultSet.getMetaData();
            // }
            IRepositoryService repositoryService = CoreRuntimePlugin.getInstance().getRepositoryService();
            boolean isMSSQL = EDatabaseTypeName.MSSQL.getDisplayName().equals(metadataConnection.getDbType());
            while (columns.next()) {
                Boolean b = false;
                String fetchTableName = ExtractMetaDataUtils.getStringMetaDataInfo(columns, "TABLE_NAME", null); //$NON-NLS-1$
                fetchTableName = ManagementTextUtils.filterSpecialChar(fetchTableName); // for 8115
                if (fetchTableName.equals(tableName) || databaseType.equals(EDatabaseTypeName.SQLITE.getDisplayName())) {
                    TdColumn metadataColumn = RelationalFactory.eINSTANCE.createTdColumn();
                    String label = ExtractMetaDataUtils.getStringMetaDataInfo(columns, "COLUMN_NAME", null); //$NON-NLS-1$
                    label = ManagementTextUtils.filterSpecialChar(label);
                    String sub = ""; //$NON-NLS-1$
                    String sub2 = ""; //$NON-NLS-1$
                    String label2 = label;
                    // label = label.replaceAll("[^_a-zA-Z0-9]", "_");
                    if (label != null && label.length() > 0 && label.startsWith("_")) { //$NON-NLS-1$
                        sub = label.substring(1);
                        if (sub != null && sub.length() > 0) {
                            sub2 = sub.substring(1);
                        }
                    }
                    if (coreService.isKeyword(label) || coreService.isKeyword(sub) || coreService.isKeyword(sub2)) {
                        label = "_" + label; //$NON-NLS-1$
                        b = true;
                    }
                    metadataColumn.setLabel(label); //$NON-NLS-1$
                    //                    if (label2 != null && label2.length() > 0 && label2.startsWith("_")) { //$NON-NLS-1$
                    // String substring = label2.substring(1);
                    // if (b
                    //                                && label2.startsWith("_") //$NON-NLS-1$
                    // && (coreService.isKeyword(substring) || coreService.isKeyword(sub) ||
                    // coreService.isKeyword(sub2))) {
                    // label2 = substring;
                    // }
                    // }
                    metadataColumn.setOriginalField(label2);

                    // Validate the column if it contains space or illegal
                    // characters.
                    if (repositoryService != null) {
                        // metadataColumn.setDisplayField(repositoryService.validateColumnName(metadataColumn.getLabel(),
                        // columnIndex));
                        label = repositoryService.validateColumnName(label, columnIndex);
                        metadataColumn.setLabel(label);
                    }
                    columnIndex++;

                    if (primaryKeys != null && !primaryKeys.isEmpty()
                            && primaryKeys.get(metadataColumn.getOriginalField()) != null) {
                        metadataColumn.setKey(true);
                    } else {
                        metadataColumn.setKey(false);
                    }

                    String typeName = "TYPE_NAME"; //$NON-NLS-1$
                    if (ExtractMetaDataUtils.isUseAllSynonyms()) {
                        typeName = "DATA_TYPE"; //$NON-NLS-1$
                    }
                    String dbType = ExtractMetaDataUtils.getStringMetaDataInfo(columns, typeName, null).toUpperCase(); //$NON-NLS-1$
                    // For sometime the dbType will return one more space character at the end.So need to trim,comment
                    // for bug 17509
                    dbType = dbType.trim();
                    dbType = ManagementTextUtils.filterSpecialChar(dbType);
                    dbType = handleDBtype(dbType);
                    metadataColumn.setSourceType(dbType);
                    Integer columnSize;
                    // if (isMYSQL) {
                    // columnSize = ExtractMetaDataUtils.getMysqlIntMetaDataInfo(resultMetadata, columnIndex);
                    // } else {
                    columnSize = ExtractMetaDataUtils.getIntMetaDataInfo(columns, "COLUMN_SIZE");
                    // }
                    metadataColumn.setLength(columnSize); //$NON-NLS-1$
                    // Convert dbmsType to TalendType

                    String talendType = null;

                    // qli modified to fix the bug 6654.
                    if (metadataConnection.getMapping() != null) {
                        mappingTypeRetriever = MetadataTalendType.getMappingTypeRetriever(metadataConnection.getMapping());
                    }
                    Integer intMetaDataInfo = ExtractMetaDataUtils.getIntMetaDataInfo(columns, "DECIMAL_DIGITS");
                    talendType = mappingTypeRetriever.getDefaultSelectedTalendType(dbType, columnSize, intMetaDataInfo); //$NON-NLS-1$
                    talendType = ManagementTextUtils.filterSpecialChar(talendType);
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
                    // for bug 13078

                    boolean isNullable = ExtractMetaDataUtils.getBooleanMetaDataInfo(columns, "IS_NULLABLE"); //$NON-NLS-1$ 
                    metadataColumn.setNullable(isNullable);

                    // gcui:see bug 6450, if in the commentInfo have some invalid character then will remove it.
                    String commentInfo = ExtractMetaDataUtils.getStringMetaDataInfo(columns, "REMARKS", null); //$NON-NLS-1$
                    if (commentInfo != null && commentInfo.length() > 0) {
                        commentInfo = ManagementTextUtils.filterSpecialChar(commentInfo);
                    }
                    // gcui:if not oracle database use "REMARKS" select comments
                    metadataColumn.setComment(commentInfo); //$NON-NLS-1$
                    if (!isAccess) { // jdbc-odbc driver won't apply methods for access
                        TDColumnAttributeHelper.addColumnAttribute(label, label2, dbType, columnSize, intMetaDataInfo,
                                commentInfo, columns, metadataColumn,
                                (DatabaseConnection) metadataConnection.getCurrentConnection());// columnName,
                    }

                    if (isMSSQL && "INT IDENTITY".equals(dbType)) { //$NON-NLS-1$  // && metadataColumn.isKey()

                        // for MSSQL bug16852
                        // get inent_seed get_incr for schema's length, precision.
                        Integer ident1 = 0;
                        Integer ident2 = 0;
                        try {
                            PreparedStatement statement = ExtractMetaDataUtils.conn
                                    .prepareStatement(" select IDENT_SEED ( '" + tableNode.getValue() + "')," + "IDENT_INCR ( '" //$NON-NLS-N$ //$NON-NLS-N$ //$NON-NLS-N$
                                            + tableNode.getValue() + "')"); //$NON-NLS-1$ 
                            ResultSet resultSet = null;
                            ExtractMetaDataUtils.setQueryStatementTimeout(statement);
                            if (statement.execute()) {
                                resultSet = statement.getResultSet();
                                while (resultSet.next()) {
                                    String st1 = resultSet.getString(1);
                                    String st2 = resultSet.getString(2);
                                    Integer valueOf1 = Integer.valueOf(st1);
                                    if (valueOf1 != null) {
                                        ident1 = valueOf1;
                                    }
                                    Integer valueOf2 = Integer.valueOf(st2);
                                    if (valueOf2 != null) {
                                        ident2 = valueOf2;
                                    }

                                }
                            }
                            resultSet.close();
                            statement.close();

                        } catch (Exception e) {
                            log.error(e.toString());
                        }

                        metadataColumn.setLength(ident1);
                        metadataColumn.setPrecision(ident2);
                    } else {
                        metadataColumn.setPrecision(intMetaDataInfo);
                    }

                    // cantoine : patch to fix 0x0 pb cause by Bad Schema
                    // description
                    String stringMetaDataInfo = ExtractMetaDataUtils.getStringMetaDataInfo(columns, "COLUMN_DEF", dbMetaData); //$NON-NLS-1$
                    if (stringMetaDataInfo != null && stringMetaDataInfo.length() > 0 && stringMetaDataInfo.charAt(0) == 0x0) {
                        stringMetaDataInfo = "\\0"; //$NON-NLS-1$
                    }
                    stringMetaDataInfo = ManagementTextUtils.filterSpecialChar(stringMetaDataInfo);
                    metadataColumn.setDefaultValue(stringMetaDataInfo);

                    // for bug 6919, oracle driver doesn't give correctly the length for timestamp
                    if (EDatabaseTypeName.ORACLEFORSID.getDisplayName().equals(metadataConnection.getDbType())
                            || EDatabaseTypeName.ORACLESN.getDisplayName().equals(metadataConnection.getDbType())) {
                        if (dbType.equals("TIMESTAMP")) { //$NON-NLS-1$
                            metadataColumn.setLength(metadataColumn.getPrecision());
                            metadataColumn.setPrecision(-1);
                        }
                    }
                    metadataColumns.add(metadataColumn);
                }
            }

            // gcui:if it is oracle database, use this SQL select comments.
            // there will do one query to retrieve all comments on the table.

            if (EDatabaseTypeName.ORACLEFORSID.getDisplayName().equals(metadataConnection.getDbType())
                    || EDatabaseTypeName.ORACLESN.getDisplayName().equals(metadataConnection.getDbType())) {
                try {
                    PreparedStatement statement = ExtractMetaDataUtils.conn
                            .prepareStatement("SELECT COMMENTS FROM USER_COL_COMMENTS WHERE TABLE_NAME='" //$NON-NLS-1$
                                    + tableNode.getValue() + "'"); //$NON-NLS-1$ //$NON-NLS-2$
                    ResultSet keys = null;
                    ExtractMetaDataUtils.setQueryStatementTimeout(statement);
                    if (statement.execute()) {
                        keys = statement.getResultSet();
                        int i = 0;
                        while (keys.next()) {
                            MetadataColumn metadataColumn = (MetadataColumn) metadataColumns.get(i++);
                            metadataColumn.setComment(ManagementTextUtils.filterSpecialChar(keys.getString("COMMENTS"))); //$NON-NLS-1$
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
            e.printStackTrace();
        } catch (Exception e) {
            // e.printStackTrace();
            ExceptionHandler.process(e);
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
     * @deprecated
     */
    public static List<TdColumn> extractMetadataColumnsFormTable(DatabaseMetaData dbMetaData, String medataLabel,
            IMetadataConnection metadataConnection, String databaseType) {
        columnIndex = 0;
        List<TdColumn> metadataColumns = new ArrayList<TdColumn>();
        HashMap<String, String> primaryKeys = new HashMap<String, String>();

        try {
            // qli modified to fix the bug 6850.
            String originSchema = null;
            if (!"".equals(medataLabel) && medataLabel != null) {//$NON-NLS-1$
                originSchema = tableSchemaMap.get(medataLabel);
            }
            if (!"".equals(metadataConnection.getSchema()) && (metadataConnection.getSchema() != null)) { //$NON-NLS-1$
                originSchema = metadataConnection.getSchema();
            }
            boolean isAccess = EDatabaseTypeName.ACCESS.getDisplayName().equals(metadataConnection.getDbType());

            try {
                ResultSet keys;
                if (dbMetaData.supportsSchemasInDataManipulation() && (originSchema != null)) {
                    if (!isAccess) {
                        keys = dbMetaData.getPrimaryKeys(null, originSchema, medataLabel);
                    } else {
                        keys = dbMetaData.getIndexInfo(null, originSchema, medataLabel, true, true);
                    }
                } else {
                    if (!isAccess) {
                        keys = dbMetaData.getPrimaryKeys(null, null, medataLabel);
                    } else {
                        keys = dbMetaData.getIndexInfo(null, null, medataLabel, true, true);
                    }

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
            checkUniqueKeyConstraint(medataLabel, primaryKeys, connection);
            String tableName = medataLabel;
            ResultSet columns;
            ResultSetMetaData resultMetadata = null;
            if (ExtractMetaDataUtils.isUseAllSynonyms()) {
                String sql = "select * from all_tab_columns where table_name='" + tableName + "' "; //$NON-NLS-1$ //$NON-NLS-2$
                Statement stmt = ExtractMetaDataUtils.conn.createStatement();
                ExtractMetaDataUtils.setQueryStatementTimeout(stmt);
                columns = stmt.executeQuery(sql);
            } else {
                if (dbMetaData.supportsSchemasInDataManipulation() && (originSchema != null)) {
                    columns = dbMetaData.getColumns(null, originSchema, tableName, null);
                } else {
                    columns = dbMetaData.getColumns(null, null, tableName, null);
                }
            }
            boolean isMYSQL = EDatabaseTypeName.MYSQL.getDisplayName().equals(metadataConnection.getDbType());

            if (isMYSQL) {
                Statement statement = connection.createStatement();
                String query = "SELECT * FROM `" + tableName + "` limit 1"; //$NON-NLS-1$ //$NON-NLS-2$
                ResultSet resultSet = statement.executeQuery(query);
                resultMetadata = resultSet.getMetaData();
            }
            IRepositoryService repositoryService = CoreRuntimePlugin.getInstance().getRepositoryService();
            boolean isMSSQL = EDatabaseTypeName.MSSQL.getDisplayName().equals(metadataConnection.getDbType());
            while (columns.next()) {
                Boolean b = false;
                String fetchTableName = ExtractMetaDataUtils.getStringMetaDataInfo(columns, "TABLE_NAME", null); //$NON-NLS-1$
                fetchTableName = ManagementTextUtils.filterSpecialChar(fetchTableName); // for 8115
                if (fetchTableName.equals(tableName) || databaseType.equals(EDatabaseTypeName.SQLITE.getDisplayName())) {
                    TdColumn metadataColumn = RelationalFactory.eINSTANCE.createTdColumn();
                    String label = ExtractMetaDataUtils.getStringMetaDataInfo(columns, "COLUMN_NAME", null); //$NON-NLS-1$
                    label = ManagementTextUtils.filterSpecialChar(label);
                    String sub = ""; //$NON-NLS-1$
                    String sub2 = ""; //$NON-NLS-1$
                    String label2 = label;
                    // label = label.replaceAll("[^_a-zA-Z0-9]", "_");
                    if (label != null && label.length() > 0 && label.startsWith("_")) { //$NON-NLS-1$
                        sub = label.substring(1);
                        if (sub != null && sub.length() > 0) {
                            sub2 = sub.substring(1);
                        }
                    }
                    if (coreService.isKeyword(label) || coreService.isKeyword(sub) || coreService.isKeyword(sub2)) {
                        label = "_" + label; //$NON-NLS-1$
                        b = true;
                    }

                    metadataColumn.setLabel(label); //$NON-NLS-1$
                    // String label2 = metadataColumn.getLabel();
                    //                    if (label2 != null && label2.length() > 0 && label2.startsWith("_")) { //$NON-NLS-1$
                    // String substring = label2.substring(1);
                    // if (b
                    //                                && label2.startsWith("_") //$NON-NLS-1$
                    // && (coreService.isKeyword(substring) || coreService.isKeyword(sub) ||
                    // coreService.isKeyword(sub2))) {
                    // label2 = substring;
                    // }
                    // }
                    metadataColumn.setOriginalField(label2);

                    // Validate the column if it contains space or illegal
                    // characters.
                    if (repositoryService != null) {
                        // metadataColumn.setDisplayField(repositoryService.validateColumnName(metadataColumn.getLabel(),
                        // columnIndex));
                        label = repositoryService.validateColumnName(label, columnIndex);
                        metadataColumn.setLabel(label);
                    }
                    columnIndex++;

                    if (primaryKeys != null && !primaryKeys.isEmpty()
                            && primaryKeys.get(metadataColumn.getOriginalField()) != null) {
                        metadataColumn.setKey(true);
                    } else {
                        metadataColumn.setKey(false);
                    }

                    String typeName = "TYPE_NAME"; //$NON-NLS-1$
                    if (ExtractMetaDataUtils.isUseAllSynonyms()) {
                        typeName = "DATA_TYPE"; //$NON-NLS-1$
                    }
                    String dbType = ExtractMetaDataUtils.getStringMetaDataInfo(columns, typeName, null).toUpperCase(); //$NON-NLS-1$
                    // For sometime the dbType will return one more space character at the end.So need to trim,comment
                    // for bug 17509
                    dbType = dbType.trim();
                    dbType = ManagementTextUtils.filterSpecialChar(dbType);
                    dbType = handleDBtype(dbType);
                    metadataColumn.setSourceType(dbType);
                    Integer columnSize;
                    if (ExtractMetaDataUtils.isUseAllSynonyms()) {
                        columnSize = ExtractMetaDataUtils.getOracleIntMatadataInfo(columns, "DATA_LENGTH");
                    } else {
                        if (isMYSQL) {
                            columnSize = ExtractMetaDataUtils.getMysqlIntMetaDataInfo(resultMetadata, columnIndex);
                        } else {
                            columnSize = ExtractMetaDataUtils.getIntMetaDataInfo(columns, "COLUMN_SIZE");
                        }
                    }
                    metadataColumn.setLength(columnSize); //$NON-NLS-1$
                    // Convert dbmsType to TalendType

                    String talendType = null;

                    // qli modified to fix the bug 6654.
                    if (metadataConnection.getMapping() != null) {
                        mappingTypeRetriever = MetadataTalendType.getMappingTypeRetriever(metadataConnection.getMapping());
                    }
                    Integer intMetaDataInfo = ExtractMetaDataUtils.getIntMetaDataInfo(columns, "DECIMAL_DIGITS");
                    talendType = mappingTypeRetriever.getDefaultSelectedTalendType(dbType, columnSize, intMetaDataInfo); //$NON-NLS-1$
                    talendType = ManagementTextUtils.filterSpecialChar(talendType);
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
                    // for bug 13078

                    boolean isNullable = ExtractMetaDataUtils.getBooleanMetaDataInfo(columns, "IS_NULLABLE"); //$NON-NLS-1$ 
                    metadataColumn.setNullable(isNullable);

                    // gcui:see bug 6450, if in the commentInfo have some invalid character then will remove it.
                    String commentInfo = ExtractMetaDataUtils.getStringMetaDataInfo(columns, "REMARKS", null); //$NON-NLS-1$
                    // if (commentInfo != null && commentInfo.length() > 0) {
                    // for (int i = 0; i < commentInfo.length(); i++) {
                    // if (commentInfo.codePointAt(i) == 0x0) {
                    // String commentSub1 = commentInfo.substring(0, i);
                    // String commentSub2 = commentInfo.substring(i + 1);
                    // commentInfo = commentSub1 + commentSub2;
                    // }
                    // }
                    if (commentInfo != null && commentInfo.length() > 0) {
                        commentInfo = ManagementTextUtils.filterSpecialChar(commentInfo);
                    }
                    // gcui:if not oracle database use "REMARKS" select comments
                    metadataColumn.setComment(commentInfo); //$NON-NLS-1$
                    if (!isAccess) { // jdbc-odbc driver won't apply methods for access
                        TDColumnAttributeHelper.addColumnAttribute(label, label2, dbType, columnSize, intMetaDataInfo,
                                commentInfo, columns, metadataColumn,
                                (DatabaseConnection) metadataConnection.getCurrentConnection());// columnName,
                    }

                    if (isMSSQL && "INT IDENTITY".equals(dbType)) { //$NON-NLS-1$  // && metadataColumn.isKey()

                        // for MSSQL bug16852
                        // get inent_seed get_incr for schema's length, precision.
                        Integer ident1 = 0;
                        Integer ident2 = 0;
                        try {
                            PreparedStatement statement = ExtractMetaDataUtils.conn
                                    .prepareStatement(" select IDENT_SEED ( '" + medataLabel + "')," + "IDENT_INCR ( '" //$NON-NLS-N$ //$NON-NLS-N$ //$NON-NLS-N$
                                            + medataLabel + "')"); //$NON-NLS-1$ 
                            ResultSet resultSet = null;
                            ExtractMetaDataUtils.setQueryStatementTimeout(statement);
                            if (statement.execute()) {
                                resultSet = statement.getResultSet();
                                while (resultSet.next()) {
                                    String st1 = resultSet.getString(1);
                                    String st2 = resultSet.getString(2);
                                    Integer valueOf1 = Integer.valueOf(st1);
                                    if (valueOf1 != null) {
                                        ident1 = valueOf1;
                                    }
                                    Integer valueOf2 = Integer.valueOf(st2);
                                    if (valueOf2 != null) {
                                        ident2 = valueOf2;
                                    }

                                }
                            }
                            resultSet.close();
                            statement.close();

                        } catch (Exception e) {
                            log.error(e.toString());
                        }

                        metadataColumn.setLength(ident1);
                        metadataColumn.setPrecision(ident2);
                    } else {
                        metadataColumn.setPrecision(intMetaDataInfo);
                    }

                    metadataColumns.add(metadataColumn);
                    // cantoine : patch to fix 0x0 pb cause by Bad Schema
                    // description
                    String stringMetaDataInfo = ExtractMetaDataUtils.getStringMetaDataInfo(columns, "COLUMN_DEF", dbMetaData); //$NON-NLS-1$
                    if (stringMetaDataInfo != null && stringMetaDataInfo.length() > 0 && stringMetaDataInfo.charAt(0) == 0x0) {
                        stringMetaDataInfo = "\\0"; //$NON-NLS-1$
                    }
                    stringMetaDataInfo = ManagementTextUtils.filterSpecialChar(stringMetaDataInfo);
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
                                    + medataLabel + "'"); //$NON-NLS-1$ //$NON-NLS-2$
                    ResultSet keys = null;
                    ExtractMetaDataUtils.setQueryStatementTimeout(statement);
                    if (statement.execute()) {
                        keys = statement.getResultSet();
                        int i = 0;
                        while (keys.next()) {
                            MetadataColumn metadataColumn = (MetadataColumn) metadataColumns.get(i++);
                            metadataColumn.setComment(ManagementTextUtils.filterSpecialChar(keys.getString("COMMENTS"))); //$NON-NLS-1$
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
    private static void checkUniqueKeyConstraint(String lable, HashMap<String, String> primaryKeys, Connection connection) {
        try {
            if (connection.getMetaData().getDriverName().contains(DatabaseConstant.MYSQL_PRODUCT_NAME)) {// MySql
                try {
                    PreparedStatement statement = ExtractMetaDataUtils.conn.prepareStatement("SHOW INDEX FROM `" //$NON-NLS-1$
                            + lable + "` WHERE Non_unique=0 AND Key_name != \'PRIMARY\';"); //$NON-NLS-1$
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
        } catch (SQLException e) {
            ExceptionHandler.process(e);
        }
        // SQL Server
        // else if (connection instanceof net.sourceforge.jtds.jdbc.ConnectionJDBC2) {
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
        // }
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
            final String driverClassName, final String driverJarPath, String dbVersionString, String additionalParam) {

        Connection connection = null;
        ConnectionStatus connectionStatus = new ConnectionStatus();
        connectionStatus.setResult(false);
        DriverShim wapperDriver = null;
        try {
            List list = new ArrayList();

            list = ExtractMetaDataUtils.connect(dbType, url, username, pwd, driverClassName, driverJarPath, dbVersionString,
                    additionalParam);
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) instanceof Connection) {
                        connection = (Connection) list.get(i);
                    }
                    if (list.get(i) instanceof DriverShim) {
                        wapperDriver = (DriverShim) list.get(i);
                    }
                }
            }
            if ((schema != null) && (schema.compareTo("") != 0)) { //$NON-NLS-1$
                final String product = EDatabaseTypeName.getTypeFromDisplayName(dbType).getProduct();
                final boolean equals = EDatabaseTypeName.ORACLEFORSID.getProduct().equals(product)
                        || EDatabaseTypeName.TERADATA.getProduct().equals(product);
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
            // zli
            if (wapperDriver != null
                    && (driverClassName.equals(EDatabase4DriverClassName.JAVADB_EMBEDED.getDriverClass())
                            || dbType.equals(EDatabaseTypeName.JAVADB_EMBEDED.getDisplayName())
                            || dbType.equals(EDatabaseTypeName.JAVADB_DERBYCLIENT.getDisplayName())
                            || dbType.equals(EDatabaseTypeName.JAVADB_JCCJDBC.getDisplayName()) || dbType
                            .equals(EDatabaseTypeName.HSQLDB_IN_PROGRESS.getDisplayName()))) {
                try {
                    wapperDriver.connect("jdbc:derby:;shutdown=true", null); //$NON-NLS-1$
                } catch (SQLException e) {
                    // exception of shutdown success. no need to catch.
                }
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
        // add by wzhang
        // ExtractMetaDataUtils.metadataCon = iMetadataConnection;
        // end
        // bug 9162 bug 12888
        String dbType = iMetadataConnection.getDbType();
        String url = iMetadataConnection.getUrl();

        String generalJDBCDisplayName = EDatabaseConnTemplate.GENERAL_JDBC.getDBDisplayName();
        if (dbType.equals(generalJDBCDisplayName) && url.contains("oracle")) {//$NON-NLS-1$
            iMetadataConnection.setSchema(iMetadataConnection.getUsername().toUpperCase());
        }

        List list = ExtractMetaDataUtils.getConnection(iMetadataConnection.getDbType(), url, iMetadataConnection.getUsername(),
                iMetadataConnection.getPassword(), iMetadataConnection.getDatabase(), iMetadataConnection.getSchema(),
                iMetadataConnection.getDriverClass(), iMetadataConnection.getDriverJarPath(),
                iMetadataConnection.getDbVersionString(), iMetadataConnection.getAdditionalParams());
        Connection conn = null;
        DriverShim wapperDriver = null;

        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) instanceof Connection) {
                    conn = (Connection) list.get(i);
                }
                if (list.get(i) instanceof DriverShim) {
                    wapperDriver = (DriverShim) list.get(i);
                }
            }
        }

        DatabaseMetaData dbMetaData = ExtractMetaDataUtils.getDatabaseMetaData(ExtractMetaDataUtils.conn, dbType,
                iMetadataConnection.isSqlMode(), iMetadataConnection.getDatabase());
        List<IMetadataTable> metadataTables = ExtractMetaDataFromDataBase.extractTablesFromDB(dbMetaData, iMetadataConnection,
                limit);

        ExtractMetaDataUtils.closeConnection();
        if ((dbType.equals(EDatabaseTypeName.JAVADB_EMBEDED.getDisplayName())
                || dbType.equals(EDatabaseTypeName.JAVADB_DERBYCLIENT.getDisplayName())
                || dbType.equals(EDatabaseTypeName.JAVADB_JCCJDBC.getDisplayName())
                || dbType.equals(EDatabaseTypeName.HSQLDB_IN_PROGRESS.getDisplayName()) || dbType.equals(generalJDBCDisplayName))
                && (url != null && url.contains("jdbc:derby")) && wapperDriver != null) { //$NON-NLS-1$ //$NON-NLS-2$
            try {
                wapperDriver.connect("jdbc:derby:;shutdown=true", null); //$NON-NLS-1$
            } catch (SQLException e) {
                // exception of shutdown success. no need to catch.
            }
        }

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
            IMetadataConnection iMetadataConnection, int limit) {
        List<org.talend.core.model.metadata.builder.connection.MetadataTable> itemTablesName = new ArrayList<org.talend.core.model.metadata.builder.connection.MetadataTable>();

        List list = ExtractMetaDataUtils.getConnection(iMetadataConnection.getDbType(), iMetadataConnection.getUrl(),
                iMetadataConnection.getUsername(), iMetadataConnection.getPassword(), iMetadataConnection.getDatabase(),
                iMetadataConnection.getSchema(), iMetadataConnection.getDriverClass(), iMetadataConnection.getDriverJarPath(),
                iMetadataConnection.getDbVersionString(), iMetadataConnection.getAdditionalParams());
        DriverShim wapperDriver = null;
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) instanceof DriverShim) {
                    wapperDriver = (DriverShim) list.get(i);
                }
            }
        }
        String dbType = iMetadataConnection.getDbType();
        DatabaseMetaData dbMetaData = ExtractMetaDataUtils.getDatabaseMetaData(ExtractMetaDataUtils.conn, dbType,
                iMetadataConnection.isSqlMode(), iMetadataConnection.getDatabase());

        List<IMetadataTable> metadataTables = null;
        if (limit > 0) {
            metadataTables = ExtractMetaDataFromDataBase.extractTablesFromDB(dbMetaData, iMetadataConnection, limit);
        } else {
            metadataTables = ExtractMetaDataFromDataBase.extractTablesFromDB(dbMetaData, iMetadataConnection);
        }
        ExtractMetaDataUtils.closeConnection();

        if (wapperDriver != null
                && (iMetadataConnection.getDriverClass().equals(EDatabase4DriverClassName.JAVADB_EMBEDED.getDriverClass())
                        || dbType.equals(EDatabaseTypeName.JAVADB_EMBEDED.getDisplayName())
                        || dbType.equals(EDatabaseTypeName.JAVADB_DERBYCLIENT.getDisplayName())
                        || dbType.equals(EDatabaseTypeName.JAVADB_JCCJDBC.getDisplayName()) || dbType
                        .equals(EDatabaseTypeName.HSQLDB_IN_PROGRESS.getDisplayName()))) {
            try {
                wapperDriver.connect("jdbc:derby:;shutdown=true", null); //$NON-NLS-1$
            } catch (SQLException e) {
                // exception of shutdown success. no need to catch.
            }
        }

        Iterator<IMetadataTable> iterate = metadataTables.iterator();
        while (iterate.hasNext()) {
            IMetadataTable metadataTable = iterate.next();
            itemTablesName.add(ConvertionHelper.convert(metadataTable));
        }

        return itemTablesName;
    }

    public static List<org.talend.core.model.metadata.builder.connection.MetadataTable> returnMetaTablesFormConnection(
            IMetadataConnection iMetadataConnection) {
        return returnMetaTablesFormConnection(iMetadataConnection, -1);

    }

    /**
     * This method is used for getting table type(table,view or synonym etc.) by table name.
     * 
     * @param tableName a string representing table name
     * @return a string representing table type
     */
    public static String getTableTypeByTableName(String tableName) {
        if (!"".equals(tableName) && tableName != null) { //$NON-NLS-1$
            if (tableTypeMap.containsKey(tableName)) {
                return tableTypeMap.get(tableName);
            }
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
        filterTablesName.clear();
        // add by wzhang
        // ExtractMetaDataUtils.metadataCon = iMetadataConnection;
        // end

        String dbType = iMetadataConnection.getDbType();
        List connList = ExtractMetaDataUtils.getConnection(iMetadataConnection.getDbType(), iMetadataConnection.getUrl(),
                iMetadataConnection.getUsername(), iMetadataConnection.getPassword(), iMetadataConnection.getDatabase(),
                iMetadataConnection.getSchema(), iMetadataConnection.getDriverClass(), iMetadataConnection.getDriverJarPath(),
                iMetadataConnection.getDbVersionString(), iMetadataConnection.getAdditionalParams());
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
                // if want to get all tables and synonyms,need to get the value of the public_synonym_checken botton
                if (ExtractMetaDataUtils.conn != null && ExtractMetaDataUtils.conn.toString().contains("oracle.jdbc.driver") //$NON-NLS-1$
                        && ExtractMetaDataUtils.isUseAllSynonyms()) {

                    Statement stmt = ExtractMetaDataUtils.conn.createStatement();
                    ExtractMetaDataUtils.setQueryStatementTimeout(stmt);

                    StringBuffer filters = new StringBuffer();
                    if (!nameFiters.isEmpty()) {
                        filters.append(" and ("); //$NON-NLS-1$
                        final String tStr = " all_synonyms.synonym_name like '"; //$NON-NLS-1$
                        int i = 0;
                        for (String s : nameFiters) {
                            if (i != 0) {
                                filters.append(" or "); //$NON-NLS-1$
                            }
                            filters.append(tStr);
                            filters.append(s);
                            filters.append('\'');
                            i++;

                        }
                        filters.append(')');
                    }
                    ResultSet rsTables = stmt.executeQuery(GET_ALL_SYNONYMS + filters.toString());
                    itemTablesName = getTableNamesFromQuery(rsTables);
                    rsTables.close();
                    stmt.close();

                    // tableTypeMap.clear();
                    for (String synonymName : itemTablesName) {
                        tableTypeMap.put(synonymName, ETableTypes.TABLETYPE_SYNONYM.getName());
                    }

                } else {
                    if (nameFiters.isEmpty()) {
                        itemTablesName = getTableNamesFromTablesForMultiSchema(tableInfoParameters, "", iMetadataConnection); //$NON-NLS-1$
                    } else {
                        for (String s : nameFiters) {
                            List<String> tableNamesFromTables = getTableNamesFromTablesForMultiSchema(tableInfoParameters, s,
                                    iMetadataConnection);
                            for (String string : tableNamesFromTables) {
                                if (!itemTablesName.contains(string)) {
                                    itemTablesName.add(string);
                                }
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
        if (EDatabaseTypeName.ORACLEFORSID.getProduct().equals(iMetadataConnection.getProduct())
                && iMetadataConnection.getDbVersionString() != null
                && !iMetadataConnection.getDbVersionString().equals("ORACLE_8")) {
            filterTablesFromRecycleBin(itemTablesName);
        }
        ExtractMetaDataUtils.closeConnection();
        // for buy 15042
        DriverShim wapperDriver = null;
        if (connList != null && connList.size() > 0) {
            for (int i = 0; i < connList.size(); i++) {
                if (connList.get(i) instanceof DriverShim) {
                    wapperDriver = (DriverShim) connList.get(i);
                }
            }
        }
        String driverClassName = iMetadataConnection.getDriverClass();

        // added for retrieve schema derby close
        if (wapperDriver != null
                && (driverClassName.equals(EDatabase4DriverClassName.JAVADB_EMBEDED.getDriverClass())
                        || dbType.equals(EDatabaseTypeName.JAVADB_EMBEDED.getDisplayName())
                        || dbType.equals(EDatabaseTypeName.JAVADB_DERBYCLIENT.getDisplayName())
                        || dbType.equals(EDatabaseTypeName.JAVADB_JCCJDBC.getDisplayName()) || dbType
                        .equals(EDatabaseTypeName.HSQLDB_IN_PROGRESS.getDisplayName()))) {
            try {
                wapperDriver.connect("jdbc:derby:;shutdown=true", null); //$NON-NLS-1$
            } catch (SQLException e) {
                // exception of shutdown success. no need to catch.
            }
        }

        filterTablesName = itemTablesName;
        return itemTablesName;
    }

    public static List<String> getFilterTablesName() {
        return filterTablesName;
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
     * 
     * cli Comment method "getTableNamesFromTablesForMultiSchema".
     * 
     * bug 12179
     */
    private static List<String> getTableNamesFromTablesForMultiSchema(TableInfoParameters tableInfo, String namePattern,
            IMetadataConnection iMetadataConnection) throws SQLException {

        String[] multiSchemas = ExtractMetaDataUtils.getMultiSchems(ExtractMetaDataUtils.schema);
        List<String> tableNames = new ArrayList<String>();
        if (multiSchemas != null) {
            for (String s : multiSchemas) {
                List<String> tableNamesFromTables = getTableNamesFromTables(
                        getResultSetFromTableInfo(tableInfo, namePattern, iMetadataConnection, s.trim()), iMetadataConnection);
                tableNames.addAll(tableNamesFromTables);
            }
        } else {
            List<String> tableNamesFromTables = getTableNamesFromTables(
                    getResultSetFromTableInfo(tableInfo, namePattern, iMetadataConnection, null), iMetadataConnection);
            tableNames.addAll(tableNamesFromTables);
        }
        return tableNames;
    }

    /**
     * DOC qzhang Comment method "getTableNamesFromQuery".
     * 
     * @param rsTables
     * 
     * @return
     * @throws SQLException
     */
    public static List<String> getTableNamesFromQuery(ResultSet resultSet) throws SQLException {
        List<String> itemTablesName = new ArrayList<String>();
        ExtractMetaDataFromDataBase.tableCommentsMap.clear();
        while (resultSet.next()) {
            String nameKey = resultSet.getString(1).trim();
            String tableComment = getTableComment(nameKey, resultSet, false);
            if (ExtractMetaDataFromDataBase.tableCommentsMap.containsKey(nameKey)) {
                if (tableComment == null) {
                    tableComment = "";
                }
                tableCommentsMap.remove(nameKey);
                tableCommentsMap.put(nameKey, tableComment);
            }
            itemTablesName.add(nameKey);
            tableCommentsMap.put(nameKey, tableComment);
        }
        return itemTablesName;
    }

    public static String handleDBtype(String dbtype) {
        if (dbtype.startsWith("TIMESTAMP(") && dbtype.endsWith(")")) { //$NON-NLS-1$ //$NON-NLS-2$
            dbtype = "TIMESTAMP"; //$NON-NLS-1$
        }
        return coreService.validateValueForDBType(dbtype);
    }

    /**
     * DOC qzhang Comment method "getTableNamesFromQuery".
     * 
     * @param rsTables
     * 
     * @return
     * @throws SQLException
     */
    private static List<String> getTableNamesFromTables(ResultSet resultSet, IMetadataConnection iMetadataConnection)
            throws SQLException {
        List<String> itemTablesName = new ArrayList<String>();
        ExtractMetaDataFromDataBase.tableCommentsMap.clear();
        if (resultSet != null) {

            while (resultSet.next()) {
                String nameKey = resultSet.getString(GetTable.TABLE_NAME.name());
                boolean isDerby = false;
                if (iMetadataConnection != null) {
                    String dbType = iMetadataConnection.getDbType();
                    if (dbType.equals("General JDBC")) {//$NON-NLS-N$
                        String driverClassName = iMetadataConnection.getDriverClass();
                        String driverJar = iMetadataConnection.getDriverJarPath();
                        if (driverJar != null && !"".equals(driverJar)) {
                            if (driverJar.contains("\\")) {//$NON-NLS-1$
                                driverJar = driverJar.substring(driverJar.lastIndexOf("\\") + 1, driverJar.length());//$NON-NLS-1$
                            }
                        }
                        if (driverJar != null && !"".equals(driverJar)) {//$NON-NLS-1$
                            dbType = ExtractMetaDataUtils.getDbTypeByClassNameAndDriverJar(driverClassName, driverJar);
                        } else {
                            dbType = ExtractMetaDataUtils.getDbTypeByClassName(driverClassName);
                        }
                    }
                    isDerby = "JavaDB Embeded".equals(dbType) || "HSQLDB In-Process".equals(dbType);//$NON-NLS-1$//$NON-NLS-2$
                }
                String colComment = "";//$NON-NLS-N$
                if (!isDerby) {
                    if (EDatabaseTypeName.ORACLEFORSID.getProduct().equals(iMetadataConnection.getProduct())
                            && iMetadataConnection.getDbVersionString() != null
                            && iMetadataConnection.getDbVersionString().equals("ORACLE_8")
                            || EDatabaseTypeName.GENERAL_JDBC.getProduct().equals(iMetadataConnection.getProduct())
                            && iMetadataConnection.getDriverJarPath().contains("ojdbc12-8i.jar")) {
                        colComment = getTableComment(nameKey, resultSet, false);
                    } else {
                        colComment = getTableComment(nameKey, resultSet, true);
                    }
                }
                itemTablesName.add(nameKey); //$NON-NLS-1$
                if (ExtractMetaDataFromDataBase.tableCommentsMap.containsKey(nameKey)) {
                    if (colComment == null) {
                        colComment = "";
                    }
                    tableCommentsMap.remove(nameKey);
                    tableCommentsMap.put(nameKey, colComment);
                }
                tableCommentsMap.put(nameKey, colComment);
                // bug 0017782 ,db2's SYNONYM need to convert to ALIAS;
                if (tableTypeMap.containsKey(nameKey)) {
                    tableTypeMap.remove(nameKey);
                    tableTypeMap.put(nameKey, resultSet.getString("TABLE_TYPE"));
                } else
                    tableTypeMap.put(nameKey, resultSet.getString("TABLE_TYPE"));
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
    private static ResultSet getResultSetFromTableInfo(TableInfoParameters tableInfo, String namePattern,
            IMetadataConnection iMetadataConnection, String schema) throws SQLException {
        ResultSet rsTables = null;
        String tableNamePattern = "".equals(namePattern) ? null : namePattern; //$NON-NLS-1$
        String[] types = new String[tableInfo.getTypes().size()];
        for (int i = 0; i < types.length; i++) {
            final String selectedTypeName = tableInfo.getTypes().get(i).getName();
            // bug 0017782 ,db2's SYNONYM need to convert to ALIAS;
            if ("SYNONYM".equals(selectedTypeName)
                    && iMetadataConnection.getDbType().equals(EDatabaseTypeName.IBMDB2.getDisplayName())) {
                types[i] = "ALIAS";
            } else
                types[i] = selectedTypeName;
        }
        DatabaseMetaData dbMetaData = ExtractMetaDataUtils.getDatabaseMetaData(ExtractMetaDataUtils.conn,
                iMetadataConnection.getDbType(), iMetadataConnection.isSqlMode(), iMetadataConnection.getDatabase());
        // rsTables = dbMetaData.getTables(null, ExtractMetaDataUtils.schema, tableNamePattern, types);
        ResultSet rsTableTypes = null;
        rsTableTypes = dbMetaData.getTableTypes();
        Set<String> availableTableTypes = new HashSet<String>();
        String[] neededTableTypes = { ETableTypes.TABLETYPE_TABLE.getName(), ETableTypes.TABLETYPE_VIEW.getName(),
                ETableTypes.TABLETYPE_SYNONYM.getName() };
        while (rsTableTypes.next()) {
            String currentTableType = StringUtils.trimToEmpty(rsTableTypes.getString("TABLE_TYPE")); //$NON-NLS-1$
            if (ArrayUtils.contains(neededTableTypes, currentTableType)) {
                availableTableTypes.add(currentTableType);
            }
        }
        rsTableTypes.close();
        rsTables = dbMetaData.getTables(null, schema, tableNamePattern, types);
        return rsTables;
    }

    private static String getTableComment(String tableName, ResultSet tablesSet, boolean needRemark) throws SQLException {
        String tableComment = "";
        if (needRemark) {
            tableComment = tablesSet.getString(GetTable.REMARKS.name());
        }
        if (StringUtils.isBlank(tableComment)) {
            String selectRemarkOnTable = getSelectRemarkOnTable(tableName);
            if (selectRemarkOnTable != null && ExtractMetaDataUtils.conn != null) {
                tableComment = executeGetCommentStatement(selectRemarkOnTable, ExtractMetaDataUtils.conn);
            }
        }
        return tableComment;
    }

    private static String getSelectRemarkOnTable(String tableName) {
        return "SELECT TABLE_COMMENT FROM information_schema.TABLES WHERE TABLE_NAME='" + tableName + "'"; //$NON-NLS-1$ //$NON-NLS-2$
    }

    private static String executeGetCommentStatement(String queryStmt, Connection connection) {
        String comment = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            statement.execute(queryStmt);

            // get the results
            resultSet = statement.getResultSet();
            if (resultSet != null) {
                while (resultSet.next()) {
                    comment = (String) resultSet.getObject(1);
                }
            }
        } catch (SQLException e) {
            // do nothing here
        } finally {
            // -- release resources
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    log.error(e, e);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    log.error(e, e);
                }
            }
        }
        return comment;
    }

    public static List<String> getTableCatalogAndSchema(DatabaseConnection dbConnection, String tableName) {
        String catalogName = null;
        String schemaName = null;
        List<String> catalogAndSchema = new ArrayList<String>();
        List<Catalog> catalogs = ConnectionHelper.getCatalogs(dbConnection);
        if (catalogs.isEmpty()) {
            List<Schema> schemas = ConnectionHelper.getSchema(dbConnection);
            if (schemas.isEmpty()) {

            } else {
                for (Schema s : schemas) {
                    EList<ModelElement> ownedElement = null;
                    if (s != null) {
                        ownedElement = s.getOwnedElement();
                        if (ownedElement != null) {
                            for (ModelElement m : ownedElement) {
                                if (m instanceof org.talend.core.model.metadata.builder.connection.MetadataTable) {
                                    String label = ((org.talend.core.model.metadata.builder.connection.MetadataTable) m)
                                            .getLabel();
                                    if (label.equals(tableName)) {
                                        schemaName = s.getName();
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            for (Catalog c : catalogs) {
                EList<ModelElement> ownedElement = null;
                List<Schema> schemas = CatalogHelper.getSchemas(c);
                if (schemas.isEmpty()) {
                    if (c != null) {
                        ownedElement = c.getOwnedElement();
                        if (ownedElement != null) {
                            for (ModelElement m : ownedElement) {
                                if (m instanceof org.talend.core.model.metadata.builder.connection.MetadataTable) {
                                    String label = ((org.talend.core.model.metadata.builder.connection.MetadataTable) m)
                                            .getLabel();
                                    if (label.equals(tableName)) {
                                        catalogName = c.getName();
                                        break;
                                    }
                                }
                            }
                        }
                    }
                } else {
                    for (Schema s : schemas) {
                        if (s != null) {
                            ownedElement = s.getOwnedElement();
                            if (ownedElement != null) {
                                for (ModelElement m : ownedElement) {
                                    if (m instanceof org.talend.core.model.metadata.builder.connection.MetadataTable) {
                                        String label = ((org.talend.core.model.metadata.builder.connection.MetadataTable) m)
                                                .getLabel();
                                        if (label.equals(tableName)) {
                                            catalogName = c.getName();
                                            schemaName = s.getName();
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        catalogAndSchema.add(catalogName);
        catalogAndSchema.add(" ".equals(schemaName) ? null : schemaName); //$NON-NLS-1$
        return catalogAndSchema;
    }

    public static IDBMetadataProvider getProviderByDbType(String dbTypeString) {
        for (IDBMetadataProviderObject extractorObject : providerObjects) {
            if (extractorObject.getDbType().equals(dbTypeString)) {
                return extractorObject.getProvider();
            }
        }
        return null;
    }

    public static IDBMetadataProviderObject getProviderObjectByDbType(String dbTypeString) {
        for (IDBMetadataProviderObject extractorObject : providerObjects) {
            if (extractorObject.getDbType().equals(dbTypeString)) {
                return extractorObject;
            }
        }
        return null;
    }
}
