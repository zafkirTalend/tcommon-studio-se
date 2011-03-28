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
package org.talend.core.model.metadata;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.talend.commons.utils.data.list.ListUtils;
import org.talend.core.database.EDatabaseTypeName;
import org.talend.core.database.conn.version.EDatabaseVersion4Drivers;
import org.talend.core.model.metadata.builder.connection.Connection;
import org.talend.core.model.metadata.builder.connection.ConnectionFactory;
import org.talend.core.model.metadata.builder.connection.DatabaseConnection;
import org.talend.core.model.metadata.builder.connection.MetadataTable;
import org.talend.core.model.metadata.builder.database.ExtractMetaDataFromDataBase.ETableTypes;
import org.talend.core.model.metadata.builder.database.ExtractMetaDataUtils;
import org.talend.core.model.metadata.builder.util.DatabaseConstant;
import org.talend.core.model.metadata.builder.util.MetadataConnectionUtils;
import org.talend.cwm.helper.CatalogHelper;
import org.talend.cwm.helper.ColumnHelper;
import org.talend.cwm.helper.ColumnSetHelper;
import org.talend.cwm.helper.ConnectionHelper;
import org.talend.cwm.helper.PackageHelper;
import org.talend.cwm.helper.SchemaHelper;
import org.talend.cwm.helper.SwitchHelpers;
import org.talend.cwm.helper.TableHelper;
import org.talend.cwm.i18n.Messages;
import org.talend.cwm.relational.RelationalFactory;
import org.talend.cwm.relational.TdColumn;
import org.talend.cwm.relational.TdExpression;
import org.talend.cwm.relational.TdSqlDataType;
import org.talend.cwm.relational.TdTable;
import org.talend.cwm.relational.TdView;
import org.talend.cwm.softwaredeployment.TdSoftwareSystem;
import org.talend.utils.sql.ConnectionUtils;
import org.talend.utils.sql.metadata.constants.GetColumn;
import org.talend.utils.sql.metadata.constants.GetForeignKey;
import org.talend.utils.sql.metadata.constants.GetPrimaryKey;
import org.talend.utils.sql.metadata.constants.GetTable;
import org.talend.utils.sql.metadata.constants.MetaDataConstants;
import org.talend.utils.sql.metadata.constants.TableType;
import org.talend.utils.sugars.ReturnCode;
import org.talend.utils.sugars.TypedReturnCode;
import orgomg.cwm.objectmodel.core.Package;
import orgomg.cwm.resource.relational.Catalog;
import orgomg.cwm.resource.relational.ColumnSet;
import orgomg.cwm.resource.relational.ForeignKey;
import orgomg.cwm.resource.relational.PrimaryKey;
import orgomg.cwm.resource.relational.Schema;
import orgomg.cwm.resource.relational.enumerations.NullableType;
import orgomg.cwm.resource.relational.impl.CatalogImpl;

/**
 * @author zshen
 * 
 */
public class DBConnectionFillerImpl extends MetadataFillerImpl {

    private static Logger log = Logger.getLogger(DBConnectionFillerImpl.class);

    private java.sql.Connection sqlConnection = null;

    @Override
    public Connection fillUIConnParams(IMetadataConnection metadataBean, Connection connection) {
        if (connection == null) {
            connection = ConnectionFactory.eINSTANCE.createDatabaseConnection();
        }
        if (super.fillUIConnParams(metadataBean, connection) == null) {
            return null;
        }

        DatabaseConnection dbconn = (DatabaseConnection) connection;
        dbconn.setDriverJarPath(metadataBean.getDriverJarPath());
        dbconn.setProductId(metadataBean.getProduct());
        dbconn.setDbmsId(metadataBean.getMapping());
        dbconn.setAdditionalParams(metadataBean.getAdditionalParams());
        dbconn.setDriverClass(metadataBean.getDriverClass());
        dbconn.setDatabaseType(metadataBean.getDbType());
        dbconn.setName(metadataBean.getLabel());
        dbconn.setLabel(metadataBean.getLabel());
        dbconn.setVersion(metadataBean.getVersion());
        dbconn.setUiSchema(metadataBean.getSchema());

        try {
            if (sqlConnection == null || sqlConnection.isClosed()) {
                this.checkConnection(metadataBean);
            }
            MetadataConnectionUtils.setMetadataCon(metadataBean);
            // fill some base parameter
            // fillMetadataParams(metadataBean, connection);
            // software
            DatabaseMetaData dbMetadata = MetadataConnectionUtils.getConnectionMetadata(sqlConnection);
            TdSoftwareSystem softwareSystem = MetadataConnectionUtils.getSoftwareSystem(sqlConnection);
            if (softwareSystem != null) {
                ConnectionHelper.setSoftwareSystem(dbconn, softwareSystem);
            }
            // identifierQuote
            String identifierQuote = dbMetadata.getIdentifierQuoteString();
            ConnectionHelper.setIdentifierQuoteString(identifierQuote == null ? "" : identifierQuote, dbconn);
            // dbversion
            int versionNum = 0;
            try {
                versionNum = sqlConnection.getMetaData().getDatabaseMajorVersion();
            } catch (RuntimeException e) {
                // happens for Sybase for example
                if (log.isDebugEnabled()) {
                    log.debug(e, e);
                }
            }
            String connectionDbType = metadataBean.getDbType();
            List<EDatabaseVersion4Drivers> dbTypeList = EDatabaseVersion4Drivers.indexOfByDbType(connectionDbType);
            if (dbTypeList.size() == 1) {
                dbconn.setDbVersionString(dbTypeList.get(0).getVersionValue());
            } else if (dbTypeList.size() > 1) {
                for (EDatabaseVersion4Drivers eDatabaseVersion : dbTypeList) {
                    String[] strArray = eDatabaseVersion.getVersionValue().split("_");
                    if (strArray.length > 1 && strArray[1].startsWith(Integer.toString(versionNum))) {
                        dbconn.setDbVersionString(eDatabaseVersion.getVersionValue());
                        break;
                    }
                }
            }
            // uiSchema
            EDatabaseTypeName edatabasetypeInstance = EDatabaseTypeName.getTypeFromDisplayName(connectionDbType);
            if (edatabasetypeInstance.isNeedSchema() && StringUtils.isEmpty(dbconn.getUiSchema())) {
                this.setLinked(false);
                List<Schema> schemata = ListUtils.castList(Schema.class,
                        this.fillSchemas(connection, sqlConnection.getMetaData(), null));
                List<Catalog> catalogs = this.fillCatalogs(connection, sqlConnection.getMetaData(), null);
                this.setLinked(true);
                if (schemata.size() == 0 && catalogs.size() > 0) {
                    schemata = CatalogHelper
                            .getSchemas(SwitchHelpers.CATALOG_SWITCH.doSwitch((CatalogImpl) catalogs.toArray()[0]));
                }

                // FIXME Bzhou why here need to add a default schema?
                // if (edatabasetypeInstance.getSchemaMappingField() == EDatabaseSchemaOrCatalogMapping.Schema
                // && schemata.size() > 0) {
                // Iterator<Schema> iter = schemata.iterator();
                // while (iter.hasNext()) {
                // String uischema = iter.next().getName();
                // dbconn.setUiSchema(uischema);
                // break;
                // }
                //
                // }
            }
        } catch (SQLException e) {
            log.error(e, e);
        } finally {
            ConnectionUtils.closeConnection(sqlConnection);
        }
        return connection;
    }

    @Override
    public ReturnCode checkConnection(IMetadataConnection metadataBean) {
        ReturnCode rc = null;
        rc = MetadataConnectionUtils.checkConnection(metadataBean);
        if (rc instanceof TypedReturnCode) {
            this.sqlConnection = (java.sql.Connection) ((TypedReturnCode<?>) rc).getObject();
        }
        return rc;
    }

    public List<Package> fillSchemas(Connection dbConn, DatabaseMetaData dbJDBCMetadata, List<String> schemaFilter) {

        List<Schema> returnSchemas = new ArrayList<Schema>();
        if (dbJDBCMetadata == null || (dbConn != null && ConnectionHelper.getCatalogs(dbConn).size() > 0)) {
            return null;
        }
        ResultSet schemas = null;
        try {
            if (dbConn != null && EDatabaseTypeName.ACCESS.getProduct().equals(((DatabaseConnection) dbConn).getProductId())) {
                return null;
            }
            schemas = dbJDBCMetadata.getSchemas();
        } catch (SQLException e) {
            log.error("This database don't contian construct of schema.");
            return null;
        }
        try {
            if (schemas != null) {

                boolean hasSchema = false;
                String schemaName = null;
                while (schemas.next()) {

                    schemaName = schemas.getString(MetaDataConstants.TABLE_SCHEM.name());
                    if (schemaName == null) {
                        schemaName = schemas.getString(DatabaseConstant.ODBC_ORACLE_SCHEMA_NAME);
                    }
                    if (schemaName == null) {
                        // try to get first column
                        schemaName = schemas.getString(1);
                    }
                    hasSchema = true;
                    if (!filterMetadaElement(schemaFilter, schemaName)) {
                        continue;
                    }
                    Schema schema = SchemaHelper.createSchema(schemaName);
                    returnSchemas.add(schema);

                }
                schemas.close();
                // handle case of SQLite (no schema no catalog)
                ResultSet catalogs = dbJDBCMetadata.getCatalogs();
                if (!hasSchema) {
                    // create a fake schema with an empty name (otherwise queries will use the name and will fail)
                    Schema schema = SchemaHelper.createSchema(" "); //$NON-NLS-1$
                    returnSchemas.add(schema);
                }
                catalogs.close();
                if (isLinked() && returnSchemas.size() > 0) {
                    ConnectionHelper.addSchemas(returnSchemas, dbConn);
                }
            }
        } catch (SQLException e) {
            log.error(e, e);
        }
        return ListUtils.castList(Package.class, returnSchemas);
    }

    public List<Catalog> fillCatalogs(Connection dbConn, DatabaseMetaData dbJDBCMetadata, List<String> catalogFilter) {
        List<Catalog> catalogList = new ArrayList<Catalog>();
        if (dbJDBCMetadata == null) {
            return null;
        }
        try {
            if (dbJDBCMetadata.getDatabaseProductName() != null
                    && dbJDBCMetadata.getDatabaseProductName().indexOf(EDatabaseTypeName.ORACLEFORSID.getProduct()) > -1) {
                return catalogList;
            }
            ResultSet catalogNames = null;
            catalogNames = dbJDBCMetadata.getCatalogs();

            if (catalogNames != null) {

                // else DB support getCatalogs() method
                while (catalogNames.next()) {
                    // MOD xqliu 2009-11-03 bug 9841
                    String catalogName = null;
                    try {
                        String temp = MetadataConnectionUtils.isOdbcPostgresql(dbJDBCMetadata.getConnection()) ? DatabaseConstant.ODBC_POSTGRESQL_CATALOG_NAME
                                : MetaDataConstants.TABLE_CAT.name();
                        catalogName = catalogNames.getString(temp);
                        // MOD zshen filter ODBC catalog
                        if (!MetadataConnectionUtils.isODBCCatalog(catalogName, dbJDBCMetadata.getConnection())) {
                            continue;
                        }
                    } catch (Exception e) {
                        log.warn(e, e);
                        if (dbJDBCMetadata.getDatabaseProductName() != null
                                && dbJDBCMetadata.getDatabaseProductName().toLowerCase()
                                        .indexOf(DatabaseConstant.POSTGRESQL_PRODUCT_NAME) > -1) {
                            catalogName = "";
                        }
                    }
                    // ~
                    assert catalogName != null : Messages.getString("CatalogBuilder.CatalogNameNull",//$NON-NLS-1$
                            dbJDBCMetadata.getConnection().toString()); // FIXME assertion string must not be
                                                                        // externalized
                    // MOD xqliu 2010-03-03 feature 11412
                    Catalog catalog = null;
                    if (filterMetadaElement(catalogFilter, catalogName)) {
                        // give a sid for TOS if the attribute can't be set by user on UI.
                        // if (StringUtils.isEmpty(((DatabaseConnection) dbConn).getSID())) {
                        // ((DatabaseConnection) dbConn).setSID(catalogName);
                        // }
                        catalog = CatalogHelper.createCatalog(catalogName);
                        catalogList.add(catalog);
                    } else {
                        continue;
                    }

                    List<String> filterList = null;
                    DatabaseConnection dbConnection = (DatabaseConnection) dbConn;
                    if (!StringUtils.isBlank(dbConnection.getUiSchema())) {
                        filterList = new ArrayList<String>();
                        filterList.add(dbConnection.getUiSchema());
                    }
                    List<Schema> schemaList = fillSchemaToCatalog(dbConn, dbJDBCMetadata, catalog, filterList);
                    CatalogHelper.addSchemas(schemaList, catalog);

                    // ~11412
                }
                // --- release the result set.
                catalogNames.close();
                if (this.isLinked() && catalogList.size() > 0) {
                    ConnectionHelper.addCatalogs(catalogList, dbConn);
                }
            }
        } catch (SQLException e) {
            log.warn("JDBC getCatalogs() method is not available with this driver.", e);
        }

        return catalogList;
    }

    public List<Schema> fillSchemaToCatalog(Connection dbConn, DatabaseMetaData dbJDBCMetadata, Catalog catalog,
            List<String> schemaFilter) {

        ResultSet schemaRs = null;
        try {
            // Case of JDK 1.6
            if (dbJDBCMetadata.getDriverName().equals(DatabaseConstant.MSSQL_DRIVER_NAME_JDBC2_0)) {
                Method getSchemaMethod = dbJDBCMetadata.getClass().getMethod("getSchemas", String.class, String.class);
                schemaRs = (ResultSet) getSchemaMethod.invoke(dbJDBCMetadata, catalog.getName(), null);
            }
        } catch (SecurityException e) {
            // Case of JDK1.5
        } catch (NoSuchMethodException e) {
        } catch (IllegalArgumentException e) {
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e) {
        } catch (SQLException e) {
            log.error(e, e);
        }

        if (schemaRs == null) {
            try {
                schemaRs = dbJDBCMetadata.getSchemas();
            } catch (SQLException e) {
                if (log.isDebugEnabled()) {
                    log.debug(e, e);
                }
            }
        }

        List<String> schemaNameCacheTmp = new ArrayList<String>();
        List<Schema> schemaList = new ArrayList<Schema>();
        try {
            while (schemaRs.next()) {
                String schemaName = null;
                String catalogName = null;
                try {
                    schemaName = schemaRs.getString(MetaDataConstants.TABLE_SCHEM.name());
                    // if (schemaName.equals("Base")) {
                    // System.out.println("aa");
                    // executeGetSchemas(dbJDBCMetadata);
                    // }
                    catalogName = schemaRs.getString(MetaDataConstants.TABLE_CATALOG.name());
                    // if (catalogName.equals("new_Base")) {
                    // System.out.println("aa");
                    // }
                } catch (Exception e) {
                    log.warn(e.getMessage(), e);
                }
                // the case for mssql
                if (MetadataConnectionUtils.isMssql(dbJDBCMetadata.getConnection())) {
                    if (catalogName == null) {
                        continue;
                    }
                    schemaName = catalogName;
                } else if (schemaName == null || catalogName != null && !catalogName.equals(catalog.getName())) {// the
                                                                                                                 // case
                                                                                                                 // for
                                                                                                                 // olap
                    // if (schemaName == null) {
                    continue;
                }
                // MOD mzhao bug 9606 filter duplicated schemas.
                if (!schemaNameCacheTmp.contains(schemaName)) {
                    schemaNameCacheTmp.add(schemaName);
                    Schema schema = SchemaHelper.createSchema(schemaName);
                    if (!filterMetadaElement(schemaFilter, schemaName)) {
                        continue;
                    }
                    schemaList.add(schema);
                }

            }
            schemaRs.close();
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug(e, e);
            }
        }

        return schemaList;
    }

    public List<MetadataTable> fillAll(Package pack, DatabaseMetaData dbJDBCMetadata, List<String> tableFilter,
            String tablePattern, String[] tableType) {
        List<MetadataTable> list = new ArrayList<MetadataTable>();
        if (dbJDBCMetadata == null) {
            return null;
        }
        Package catalogOrSchema = PackageHelper.getCatalogOrSchema(pack);
        String catalogName = null;
        String schemaPattern = null;

        if (catalogOrSchema != null) {
            // catalog
            if (catalogOrSchema instanceof Catalog) {
                catalogName = catalogOrSchema.getName();
            } else {// schema
                Package parentCatalog = PackageHelper.getParentPackage(catalogOrSchema);
                schemaPattern = catalogOrSchema.getName();
                catalogName = parentCatalog == null ? null : parentCatalog.getName();
            }
        }
        try {
            ResultSet tables = dbJDBCMetadata.getTables(catalogName, schemaPattern, tablePattern, tableType);
            String productName = dbJDBCMetadata.getDatabaseProductName();
            while (tables.next()) {
                // tableCatalog never called and will cause problem for specific db.
                // String tableCatalog = tables.getString(GetTable.TABLE_CAT.name());
                String tableSchema = tables.getString(GetTable.TABLE_SCHEM.name());
                String tableName = tables.getString(GetTable.TABLE_NAME.name());
                String temptableType = tables.getString(GetTable.TABLE_TYPE.name());

                // for special db. teradata_sql_model/db2_zos
                if (temptableType != null) {
                    if ("T".equals(temptableType)) { //$NON-NLS-1$
                        temptableType = ETableTypes.TABLETYPE_TABLE.getName();
                    }
                    if ("V".equals(temptableType)) { //$NON-NLS-1$
                        temptableType = ETableTypes.TABLETYPE_VIEW.getName();
                    }
                    if ("A".equals(temptableType)) { //$NON-NLS-1$
                        temptableType = ETableTypes.TABLETYPE_ALIAS.getName();
                    }
                }
                // for
                if (!filterMetadaElement(tableFilter, tableName)) {
                    continue;
                }
                String tableOwner = null;
                if (MetadataConnectionUtils.isSybase(dbJDBCMetadata.getConnection())) {
                    tableOwner = tableSchema;
                }
                // common
                boolean flag = true;
                String tableComment = null;
                if (pack != null) {
                    Connection c = ConnectionHelper.getConnection(pack);
                    flag = MetadataConnectionUtils.isOracle8i(c);
                }
                if (!flag) {
                    tableComment = tables.getString(GetTable.REMARKS.name());
                    if (StringUtils.isBlank(tableComment)) {
                        String selectRemarkOnTable = MetadataConnectionUtils.getCommonQueryStr(productName, tableName);
                        if (selectRemarkOnTable != null) {
                            tableComment = executeGetCommentStatement(selectRemarkOnTable, dbJDBCMetadata.getConnection());
                        }
                    }
                }
                MetadataTable metadatatable = null;
                if (TableType.VIEW.toString().equals(temptableType)) {
                    metadatatable = RelationalFactory.eINSTANCE.createTdView();
                } else {
                    metadatatable = RelationalFactory.eINSTANCE.createTdTable();
                }

                metadatatable.setName(tableName);
                metadatatable.setTableType(temptableType);
                metadatatable.setLabel(metadatatable.getName());
                if (tableOwner != null) {
                    ColumnSetHelper.setTableOwner(tableOwner, metadatatable);
                }
                if (tableComment != null) {
                    metadatatable.setComment(tableComment);
                    ColumnSetHelper.setComment(tableComment, metadatatable);
                }
                list.add(metadatatable);
            }
            if (isLinked()) {
                PackageHelper.addMetadataTable(ListUtils.castList(MetadataTable.class, list), pack);
            }
        } catch (SQLException e) {
            log.error(e, e);
        }

        return list;
    }

    public List<TdTable> fillTables(Package pack, DatabaseMetaData dbJDBCMetadata, List<String> tableFilter, String tablePattern,
            String[] tableType) {
        List<TdTable> tableList = new ArrayList<TdTable>();
        if (dbJDBCMetadata == null) {
            return null;
        }
        Package catalogOrSchema = PackageHelper.getCatalogOrSchema(pack);
        String catalogName = null;
        String schemaPattern = null;

        // String[] tableType = new String[] { TableType.TABLE.toString() };
        if (catalogOrSchema != null) {
            // catalog
            if (catalogOrSchema instanceof Catalog) {
                catalogName = catalogOrSchema.getName();
            } else {// schema
                Package parentCatalog = PackageHelper.getParentPackage(catalogOrSchema);
                schemaPattern = catalogOrSchema.getName();
                catalogName = parentCatalog == null ? null : parentCatalog.getName();
            }
        }
        try {

            ResultSet tables = dbJDBCMetadata.getTables(catalogName, schemaPattern, tablePattern, tableType);
            String productName = dbJDBCMetadata.getDatabaseProductName();
            while (tables.next()) {
                // tableCatalog never called and will cause problem for specific db.
                // String tableCatalog = tables.getString(GetTable.TABLE_CAT.name());
                String tableSchema = tables.getString(GetTable.TABLE_SCHEM.name());
                String tableName = tables.getString(GetTable.TABLE_NAME.name());
                String temptableType = tables.getString(GetTable.TABLE_TYPE.name());
                // if TableType is view type don't create it at here.
                if (TableType.VIEW.toString().equals(temptableType)) {
                    continue;
                }

                // for
                if (!filterMetadaElement(tableFilter, tableName)) {
                    continue;
                }
                String tableOwner = null;
                if (MetadataConnectionUtils.isSybase(dbJDBCMetadata.getConnection())) {
                    tableOwner = tableSchema;
                }
                // common
                boolean flag = true;
                String tableComment = null;
                if (pack != null) {
                    Connection c = ConnectionHelper.getConnection(pack);
                    flag = MetadataConnectionUtils.isOracle8i(c);
                }
                if (!flag) {
                    tableComment = tables.getString(GetTable.REMARKS.name());
                    if (StringUtils.isBlank(tableComment)) {
                        String selectRemarkOnTable = MetadataConnectionUtils.getCommonQueryStr(productName, tableName);
                        if (selectRemarkOnTable != null) {
                            tableComment = executeGetCommentStatement(selectRemarkOnTable, dbJDBCMetadata.getConnection());
                        }
                    }
                }
                // create table
                TdTable table = RelationalFactory.eINSTANCE.createTdTable();
                table.setName(tableName);
                table.setTableType(temptableType);
                table.setLabel(table.getName());
                if (tableOwner != null) {
                    ColumnSetHelper.setTableOwner(tableOwner, table);
                }
                if (tableComment != null) {
                    ColumnSetHelper.setComment(tableComment, table);
                }
                tableList.add(table);
            }
            if (isLinked()) {
                PackageHelper.addMetadataTable(ListUtils.castList(MetadataTable.class, tableList), pack);
            }

        } catch (SQLException e) {
            log.error(e, e);
        }
        return tableList;
    }

    @Override
    public List<TdView> fillViews(Package pack, DatabaseMetaData dbJDBCMetadata, List<String> viewFilter, String viewPattern) {
        List<TdView> viewList = new ArrayList<TdView>();
        if (dbJDBCMetadata == null) {
            return null;
        }
        Package catalogOrSchema = PackageHelper.getCatalogOrSchema(pack);
        String catalogName = null;
        String schemaPattern = null;

        String[] tableType = new String[] { TableType.VIEW.toString() };
        if (catalogOrSchema != null) {
            // catalog
            if (catalogOrSchema instanceof Catalog) {
                catalogName = catalogOrSchema.getName();
            } else {// schema
                Package parentCatalog = PackageHelper.getParentPackage(catalogOrSchema);
                schemaPattern = catalogOrSchema.getName();
                catalogName = parentCatalog == null ? null : parentCatalog.getName();
            }
        }
        try {

            ResultSet tables = dbJDBCMetadata.getTables(catalogName, schemaPattern, viewPattern, tableType);
            String productName = dbJDBCMetadata.getDatabaseProductName();
            while (tables.next()) {

                String tableName = tables.getString(GetTable.TABLE_NAME.name());
                String type = tables.getString(GetTable.TABLE_TYPE.name());
                if (!filterMetadaElement(viewFilter, tableName)) {
                    continue;
                }
                String tableOwner = null;
                if (MetadataConnectionUtils.isSybase(dbJDBCMetadata.getConnection())) {
                    tableOwner = tables.getString(GetTable.TABLE_SCHEM.name());
                }
                // common
                boolean flag = true;
                String tableComment = null;
                if (pack != null) {
                    Connection c = ConnectionHelper.getConnection(pack);
                    flag = MetadataConnectionUtils.isOracle8i(c);
                }
                if (!flag) {
                    tableComment = tables.getString(GetTable.REMARKS.name());
                    if (StringUtils.isBlank(tableComment)) {
                        String selectRemarkOnTable = MetadataConnectionUtils.getCommonQueryStr(productName, tableName);
                        if (selectRemarkOnTable != null) {
                            tableComment = executeGetCommentStatement(selectRemarkOnTable, dbJDBCMetadata.getConnection());
                        }
                    }
                }
                // create table
                TdView table = RelationalFactory.eINSTANCE.createTdView();
                table.setName(tableName);
                table.setTableType(type);
                table.setLabel(table.getName());
                if (tableOwner != null) {
                    ColumnSetHelper.setTableOwner(tableOwner, table);
                }
                if (tableComment != null) {
                    ColumnSetHelper.setComment(tableComment, table);
                }
                viewList.add(table);
            }
            if (isLinked()) {
                PackageHelper.addMetadataTable(ListUtils.castList(MetadataTable.class, viewList), pack);
            }
        } catch (SQLException e) {
            log.error(e, e);
        }
        return viewList;
    }

    @Override
    public List<TdColumn> fillColumns(ColumnSet colSet, DatabaseMetaData dbJDBCMetadata, List<String> columnFilter,
            String columnPattern) {
        if (colSet == null || dbJDBCMetadata == null) {
            return null;
        }
        List<TdColumn> returnColumns = new ArrayList<TdColumn>();
        Map<String, TdColumn> columnMap = new HashMap<String, TdColumn>();
        String typeName = null;
        try {
            java.sql.Connection sqlConnection = dbJDBCMetadata.getConnection();

            String catalogName = getName(CatalogHelper.getParentCatalog(colSet));
            Schema schema = SchemaHelper.getParentSchema(colSet);
            if (catalogName == null && schema != null) {
                catalogName = getName(CatalogHelper.getParentCatalog(schema));
            }
            String schemaPattern = getName(schema);
            String tablePattern = getName(colSet);
            // MOD zshen bug 11934 to add schemaPattern by owner of table
            if (MetadataConnectionUtils.isSybase(sqlConnection)) {
                schemaPattern = ColumnSetHelper.getTableOwner(colSet);
            }
            // --- add columns to table
            ResultSet columns = dbJDBCMetadata.getColumns(catalogName, schemaPattern, tablePattern, columnPattern);
            while (columns.next()) {
                int decimalDigits = 0;
                int numPrecRadix = 0;
                String columnName = columns.getString(GetColumn.COLUMN_NAME.name());
                TdColumn column = ColumnHelper.createTdColumn(columnName);

                int dataType = 0;

                dataType = columns.getInt(GetColumn.DATA_TYPE.name());
                // MOD scorreia 2010-07-24 removed the call to column.getSQLDataType() here because obviously the sql
                // data type it is null and results in a NPE
                typeName = columns.getString(GetColumn.TYPE_NAME.name());
                if (MetadataConnectionUtils.isMssql(dbJDBCMetadata.getConnection())) {
                    if (typeName.toLowerCase().equals("date")) {
                        dataType = 91;
                        // MOD scorreia 2010-07-24 removed the call to column.getSQLDataType() here because obviously
                        // the sql
                        // data type it is null and results in a NPE
                    } else if (typeName.toLowerCase().equals("time")) {
                        dataType = 92;
                        // MOD scorreia 2010-07-24 removed the call to column.getSQLDataType() here because obviously
                        // the sql
                        // data type it is null and results in a NPE
                    }
                }
                int column_size = columns.getInt(GetColumn.COLUMN_SIZE.name());
                column.setLength(column_size);
                decimalDigits = columns.getInt(GetColumn.DECIMAL_DIGITS.name());
                numPrecRadix = columns.getInt(GetColumn.NUM_PREC_RADIX.name());

                // SqlDataType
                TdSqlDataType sqlDataType = MetadataConnectionUtils.createDataType(dataType, typeName, decimalDigits,
                        numPrecRadix);
                column.setSqlDataType(sqlDataType);

                // Null able
                int nullable = columns.getInt(GetColumn.NULLABLE.name());
                column.getSqlDataType().setNullable(NullableType.get(nullable));

                // Comment
                String colComment = columns.getString(GetColumn.REMARKS.name());
                if (colComment == null) {
                    colComment = "";
                }
                ColumnHelper.setComment(colComment, column);

                // TdExpression
                Object defaultvalue = null;
                try {
                    defaultvalue = columns.getObject(GetColumn.COLUMN_DEF.name());
                } catch (Exception e1) {
                    log.warn(e1, e1);
                }
                String defaultStr = (defaultvalue != null) ? String.valueOf(defaultvalue) : null;
                TdExpression defExpression = createTdExpression(GetColumn.COLUMN_DEF.name(), defaultStr);
                column.setInitialValue(defExpression);

                DatabaseConnection dbConnection = (DatabaseConnection) ConnectionHelper.getConnection(colSet);
                String dbmsId = dbConnection == null ? null : dbConnection.getDbmsId();
                if (dbmsId != null) {
                    MappingTypeRetriever mappingTypeRetriever = MetadataTalendType.getMappingTypeRetriever(dbmsId);
                    String talendType = mappingTypeRetriever.getDefaultSelectedTalendType(typeName, ExtractMetaDataUtils
                            .getIntMetaDataInfo(columns, "COLUMN_SIZE"), ExtractMetaDataUtils.getIntMetaDataInfo(columns, //$NON-NLS-1$
                            "DECIMAL_DIGITS")); //$NON-NLS-1$
                    column.setTalendType(talendType);
                    column.setSourceType(MetadataTalendType.getMappingTypeRetriever(dbConnection.getDbmsId())
                            .getDefaultSelectedDbType(talendType));
                }
                try {
                    column.setNullable("YES".equals(columns.getString(GetColumn.IS_NULLABLE.name()))); //$NON-NLS-1$
                } catch (Exception e) {
                    // do nothing
                }
                returnColumns.add(column);
                columnMap.put(columnName, column);

            }
            columns.close();
            if (isLinked()) {
                ColumnSetHelper.addColumns(colSet, returnColumns);
            }
            fillPkandFk(colSet, columnMap, dbJDBCMetadata, catalogName, schemaPattern, tablePattern);

        } catch (Exception e) {

            log.error(e, e);
        }

        // ~
        return returnColumns;
    }

    /**
     * DOC scorreia Comment method "executeGetCommentStatement".
     * 
     * @param queryStmt
     * @return
     */
    private String executeGetCommentStatement(String queryStmt, java.sql.Connection connection) {
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

    private void fillPkandFk(ColumnSet colSet, Map<String, TdColumn> columnMap, DatabaseMetaData dbJDBCMetadata,
            String catalogName, String schemaName, String tableName) throws Exception {
        if (columnMap.size() > 0) {

            Map<String, ForeignKey> foreignKeysMap = new HashMap<String, ForeignKey>();
            if (orgomg.cwm.resource.relational.RelationalPackage.eINSTANCE.getTable().isSuperTypeOf(colSet.eClass())) {
                try {
                    // primary key
                    // MOD qiongli 2011-2-21,bug 18828 ,Access database dosen't support 'getPrimaryKeys(...)'.
                    if (MetadataConnectionUtils.isOdbcExcel(dbJDBCMetadata.getConnection())
                            || MetadataConnectionUtils.isAccess(dbJDBCMetadata.getConnection())) {
                        log.info("This database don't support primary key and foreign key");
                        return;
                    }
                    ResultSet pkResult = dbJDBCMetadata.getPrimaryKeys(catalogName, schemaName, tableName);
                    PrimaryKey primaryKey = null;
                    while (pkResult.next()) {
                        String colName = pkResult.getString(GetPrimaryKey.COLUMN_NAME.name());
                        String pkName = pkResult.getString(GetPrimaryKey.PK_NAME.name());
                        if (pkName == null) {
                            continue;
                        }
                        if (primaryKey == null) {
                            primaryKey = orgomg.cwm.resource.relational.RelationalFactory.eINSTANCE.createPrimaryKey();
                            primaryKey.setName(pkName);
                        } else if (!pkName.equals(primaryKey.getName())) {
                            throw new Exception("the table" + colSet + " have two or more primaryKeys");
                        }
                        columnMap.get(colName).getUniqueKey().add(primaryKey);
                        columnMap.get(colName).setKey(true);
                        TableHelper.addPrimaryKey((TdTable) colSet, primaryKey);
                    }
                    pkResult.close();
                    // foreign key
                    ForeignKey foreignKey = null;
                    ResultSet fkResult = dbJDBCMetadata.getImportedKeys(catalogName, schemaName, tableName);
                    while (fkResult.next()) {
                        String fkname = fkResult.getString(GetForeignKey.FK_NAME.name());
                        String colName = fkResult.getString(GetForeignKey.FKCOLUMN_NAME.name());
                        if (foreignKey == null || foreignKeysMap.get(fkname) == null) {
                            foreignKey = orgomg.cwm.resource.relational.RelationalFactory.eINSTANCE.createForeignKey();
                            foreignKey.setName(fkname);
                            foreignKeysMap.put(fkname, foreignKey);
                        }
                        columnMap.get(colName).getKeyRelationship().add(foreignKey);
                        columnMap.get(colName).setKey(true);

                    }
                    fkResult.close();
                    TableHelper.addForeignKeys((TdTable) colSet,
                            Arrays.asList(foreignKeysMap.values().toArray(new ForeignKey[foreignKeysMap.values().size()])));
                } catch (SQLException e) {
                    log.error(e, e);
                }
            }
        }
    }

    /**
     * 
     * zshen Comment method "createTdExpression". from BooleanExpressionHelper.
     * 
     * @param language
     * @param body
     * @return
     */
    private TdExpression createTdExpression(String language, String body) {
        TdExpression expression = RelationalFactory.eINSTANCE.createTdExpression();
        expression.setBody(body);
        expression.setLanguage(language);
        return expression;
    }

    // public static void executeGetSchemas(DatabaseMetaData dbmd) {
    // try {
    //
    // ResultSet rs = dbmd.getSchemas();
    // ResultSetMetaData rsmd = rs.getMetaData();
    //
    // // Display the result set data.
    // int cols = rsmd.getColumnCount();
    // while (rs.next()) {
    // for (int i = 1; i <= cols; i++) {
    // System.out.println(rs.getString(i));
    // }
    // }
    // rs.close();
    // }
    //
    // catch (Exception e) {
    // e.printStackTrace();
    // }
    // }

}
