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
package org.talend.cwm.management.connection;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverPropertyInfo;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.talend.cwm.constants.SoftwareSystemConstants;
import org.talend.cwm.helper.ColumnHelper;
import org.talend.cwm.helper.ColumnSetHelper;
import org.talend.cwm.helper.DataProviderHelper;
import org.talend.cwm.helper.TableHelper;
import org.talend.cwm.helper.TaggedValueHelper;
import org.talend.cwm.helper.ViewHelper;
import org.talend.cwm.relational.RelationalFactory;
import org.talend.cwm.relational.TdCatalog;
import org.talend.cwm.relational.TdColumn;
import org.talend.cwm.relational.TdSchema;
import org.talend.cwm.relational.TdSqlDataType;
import org.talend.cwm.relational.TdTable;
import org.talend.cwm.relational.TdView;
import org.talend.cwm.softwaredeployment.SoftwaredeploymentFactory;
import org.talend.cwm.softwaredeployment.TdDataProvider;
import org.talend.cwm.softwaredeployment.TdProviderConnection;
import org.talend.cwm.softwaredeployment.TdSoftwareSystem;
import org.talend.utils.collections.MultiMapHelper;
import org.talend.utils.sql.metadata.constants.GetColumn;
import org.talend.utils.sql.metadata.constants.GetTable;
import org.talend.utils.sql.metadata.constants.MetaDataConstants;
import org.talend.utils.sql.metadata.constants.TableType;
import org.talend.utils.sql.metadata.constants.TypeInfoColumns;
import orgomg.cwm.foundation.typemapping.TypeSystem;
import orgomg.cwm.foundation.typemapping.TypemappingFactory;
import orgomg.cwm.objectmodel.core.TaggedValue;
import orgomg.cwm.resource.relational.Column;
import orgomg.cwm.resource.relational.QueryColumnSet;
import orgomg.cwm.resource.relational.enumerations.NullableType;

/**
 * @author scorreia
 * 
 * This utility class creates CWM object from a java.sql classes (e.g. Connection, Driver...)
 */
public final class DatabaseContentRetriever {

    private static Logger log = Logger.getLogger(DatabaseContentRetriever.class);

    private static final String[] TABLETYPE = new String[] { TableType.TABLE.toString() };

    private static final String[] VIEWTYPE = new String[] { TableType.VIEW.toString() };

    private static final String NO_COLUMN_PATTERN = "apeorjfldsnfmlskdfjaerjfdlksnfmdslkfgjpareoijf";

    private DatabaseContentRetriever() {
    }

    /**
     * Method "getCatalogs".
     * 
     * @param connection the connection
     * @return a map [name of catalog, catalog]
     * @throws SQLException
     */
    public static Map<String, TdCatalog> getCatalogs(Connection connection) throws SQLException {

        Map<String, TdCatalog> name2catalog = new HashMap<String, TdCatalog>();
        ResultSet catalogNames = getConnectionMetadata(connection).getCatalogs();
        if (catalogNames == null) {
            String currentCatalogName = connection.getCatalog();
            if (currentCatalogName == null) {
                if (log.isInfoEnabled()) {
                    log.info("No catalog found in connection " + getConnectionInformations(connection));
                }
            } else { // got the current catalog name, create a Catalog
                TdCatalog catalog = createCatalog(currentCatalogName);
                name2catalog.put(currentCatalogName, catalog);
            }
        } else {
            // else DB support getCatalogs() method
            while (catalogNames.next()) {
                String catalogName = catalogNames.getString(MetaDataConstants.TABLE_CAT.name());
                assert catalogName != null : "This should not happen: Catalog name is null with connection "
                        + getConnectionInformations(connection);
                TdCatalog catalog = createCatalog(catalogName);
                name2catalog.put(catalogName, catalog);
            }
        }
        // --- release the result set.
        catalogNames.close();
        return name2catalog;
    }

    public static QueryColumnSet getQueryColumnSet(ResultSetMetaData metaData) throws SQLException {
        QueryColumnSet columnSet = ColumnSetHelper.createQueryColumnSet();
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i);
            String columnClassName = metaData.getColumnClassName(i);
            // TODO add other informations
            Column column = ColumnHelper.createColumn(columnName);
            ColumnSetHelper.addColumn(column, columnSet);
        }

        return columnSet;
    }

    /**
     * Method "getSchemas" returns a map of catalogs to schemas. Warning: if no catalog is found, catalog name (i.e. key
     * of the map) can be null.
     * 
     * @param connection the connection
     * @return a map [catalog's name -> list of Schemas ].
     * @throws SQLException
     */
    public static Map<String, List<TdSchema>> getSchemas(Connection connection) throws SQLException {
        Map<String, List<TdSchema>> catalogName2schemas = new HashMap<String, List<TdSchema>>();
        ResultSet schemas = getConnectionMetadata(connection).getSchemas();
        if (schemas != null) {
            // uncomment code for debug purpose
            // if (log.isDebugEnabled()) {
            // ResultSetUtils.printResultSet(schemas, 40);
            // }

            // --- check whether the result set has two columns (Oracle and Sybase only return 1 column)
            int columnCount = schemas.getMetaData().getColumnCount();

            // TODO scorreia MODSCA20080118 do we need to create a default catalog when there is none?
            // String defaultCatName = "My Default Catalog";
            // if (columnCount == 1) {
            // // TODO scorreia create a default catalog
            // if (name2catalog.isEmpty()) {
            // createCatalog(defaultCatName);
            // }
            // }

            while (schemas.next()) {
                // create the schemata
                String schemaName = schemas.getString(MetaDataConstants.TABLE_SCHEM.name());
                TdSchema schema = createSchema(schemaName);

                // set link Catalog -> Schema if exists
                if (columnCount > 1) {
                    // TODO scorreia handle sybase case: no catalog name, only one column in this result set.

                    String catName = schemas.getString(MetaDataConstants.TABLE_CATALOG.name());
                    MultiMapHelper.addUniqueObjectToListMap(catName, schema, catalogName2schemas);
                }

                // TODO scorreia MODSCA20080118 do we need to create a default catalog when there is none?
                // else {
                // TdCatalog cat = name2catalog.get(defaultCatName);
                // cat.getOwnedElement().add(schema);
                // }

            }

            // --- release JDBC resources
            schemas.close();
        }

        // if no schema exist in catalog, do not create a default one.
        // The tables will be added directly to the catalog.

        return catalogName2schemas;
    }

    public static List<TdTable> getTablesWithColumns(String catalogName, String schemaPattern, String tablePattern,
            Connection connection) throws SQLException {
        return getTables(catalogName, schemaPattern, tablePattern, null, connection);
    }

    /**
     * Method "getTablesWithColumns".
     * 
     * @param catalogName the name of the Catalog
     * @param schemaPattern the schema pattern
     * @param connection
     * @return
     * @throws SQLException
     */
    public static List<TdTable> getTablesWithAllColumns(String catalogName, String schemaPattern, Connection connection)
            throws SQLException {
        return getTables(catalogName, schemaPattern, null, null, connection);
    }

    public static List<TdTable> getTablesWithoutColumns(String catalogName, String schemaPattern, String tablePattern,
            Connection connection) throws SQLException {
        return getTables(catalogName, schemaPattern, tablePattern, NO_COLUMN_PATTERN, connection);
    }

    public static List<TdView> getViewsWithColumns(String catalogName, String schemaPattern, String viewPattern,
            Connection connection) throws SQLException {
        return getViews(catalogName, schemaPattern, viewPattern, null, connection);
    }

    public static TdDataProvider getDataProvider(Driver driver, String databaseUrl, Properties driverProperties)
            throws SQLException {
        TdDataProvider provider = DataProviderHelper.createTdDataProvider(driver.getClass().getName()); // TODO scorreia
        // should data
        // provider
        // name be something else?

        // print driver properties
        // TODO scorreia adapt this code in order to store information in CWM ????
        DriverPropertyInfo[] driverProps = driver.getPropertyInfo(databaseUrl, driverProperties);
        for (int i = 0; i < driverProps.length; i++) {
            DriverPropertyInfo prop = driverProps[i];

            if (log.isDebugEnabled()) { // TODO use logger here
                log.debug("Prop description = " + prop.description);
                log.debug(prop.name + "=" + prop.value);
            }

            TaggedValue taggedValue = TaggedValueHelper.createTaggedValue(prop.name, prop.value);
            provider.getTaggedValue().add(taggedValue);

            if (log.isDebugEnabled()) {
                if (prop.choices != null) {
                    for (int j = 0; j < prop.choices.length; j++) {
                        log.debug("prop choice " + j + " = " + prop.choices[j]);
                    }
                }
            }
        }

        return provider;
    }

    public static TdProviderConnection getProviderConnection(String dbUrl, String driverClassName, Properties props,
            Connection connection) throws SQLException {
        TdProviderConnection prov = SoftwaredeploymentFactory.eINSTANCE.createTdProviderConnection();
        prov.setName(driverClassName + EcoreUtil.generateUUID()); // TODO scorreia change default name of provider
        // connection
        prov.setDriverClassName(driverClassName);
        prov.setConnectionString(dbUrl);
        prov.setIsReadOnly(connection.isReadOnly());

        // ---add properties as tagged value of the provider connection.
        Enumeration<?> propertyNames = props.propertyNames();
        while (propertyNames.hasMoreElements()) {
            String key = propertyNames.nextElement().toString();
            String property = props.getProperty(key);
            TaggedValue taggedValue = TaggedValueHelper.createTaggedValue(key, property);
            prov.getTaggedValue().add(taggedValue);
        }

        // TODO scorreia set name? or let it be set outside of this class?

        return prov;
    }

    public static TdSoftwareSystem getSoftwareSystem(Connection connection) throws SQLException {
        DatabaseMetaData databaseMetadata = connection.getMetaData();
        // --- get informations
        String databaseProductName = databaseMetadata.getDatabaseProductName();
        String databaseProductVersion = databaseMetadata.getDatabaseProductVersion();
        try {
            int databaseMinorVersion = databaseMetadata.getDatabaseMinorVersion();
            int databaseMajorVersion = databaseMetadata.getDatabaseMajorVersion();
            String version = Integer.toString(databaseMajorVersion) + "." + databaseMinorVersion;
            if (log.isDebugEnabled()) {
                log.debug("Database=" + databaseProductName + " | " + databaseProductVersion + " " + version);
            }
            // TODO scorreia see if store in CWM structure is done elsewhere
        } catch (RuntimeException e) {
            // happens for Sybase ASE for example
        }

        // --- create and fill the software system
        TdSoftwareSystem system = SoftwaredeploymentFactory.eINSTANCE.createTdSoftwareSystem();
        system.setName(databaseProductName); // TODO scorreia find the name!
        system.setType(SoftwareSystemConstants.DBMS.toString());
        system.setSubtype(databaseProductName);
        system.setVersion(databaseProductVersion);

        return system;
    }

    public static TypeSystem getTypeSystem(Connection connection) throws SQLException {
        DatabaseMetaData databaseMetadata = connection.getMetaData();
        // --- get the types supported by the system
        ResultSet typeInfo = databaseMetadata.getTypeInfo();
        // int columnCount = typeInfo.getMetaData().getColumnCount();

        TypeSystem typeSystem = TypemappingFactory.eINSTANCE.createTypeSystem();
        typeSystem.setName("System type"); // FIXME scorreia put another name?
        while (typeInfo.next()) {
            // --- store the information in CWM structure
            // TODO scorreia change to SQLSimpleType ?
            TdSqlDataType dataType = RelationalFactory.eINSTANCE.createTdSqlDataType();
            dataType.setName(typeInfo.getString(TypeInfoColumns.TYPE_NAME.name()));
            dataType.setJavaDataType(typeInfo.getInt(TypeInfoColumns.DATA_TYPE.name()));
            try {
                dataType.setNumericPrecision(typeInfo.getInt(TypeInfoColumns.PRECISION.name()));
            } catch (Exception e) {
                // some db do not support this method.
                if (log.isDebugEnabled()) {
                    log.debug("precision type skipped");
                }
            }

            dataType.setNullable(typeInfo.getShort(TypeInfoColumns.NULLABLE.name()));
            dataType.setCaseSensitive(typeInfo.getBoolean(TypeInfoColumns.CASE_SENSITIVE.name()));
            dataType.setSearchable(typeInfo.getShort(TypeInfoColumns.SEARCHABLE.name()));
            dataType.setUnsignedAttribute(typeInfo.getBoolean(TypeInfoColumns.UNSIGNED_ATTRIBUTE.name()));
            dataType.setAutoIncrement(typeInfo.getBoolean(TypeInfoColumns.AUTO_INCREMENT.name()));
            dataType.setLocalTypeName(typeInfo.getString(TypeInfoColumns.LOCAL_TYPE_NAME.name()));
            dataType.setNumericPrecisionRadix(typeInfo.getInt(TypeInfoColumns.NUM_PREC_RADIX.name()));

            // --- get the informations form the DB
            // TODO scorreia store these informations
            // String literalPrefix = typeInfo.getString(TypeInfoColumns.LITERAL_PREFIX.name());
            // String literalsuffix = typeInfo.getString(TypeInfoColumns.LITERAL_SUFFIX.name());
            // String createparams = typeInfo.getString(TypeInfoColumns.CREATE_PARAMS.name());
            // boolean fixedprecscale = typeInfo.getBoolean(TypeInfoColumns.FIXED_PREC_SCALE.name());
            // short minimumscale = typeInfo.getShort(TypeInfoColumns.MINIMUM_SCALE.name());
            // short maximumscale = typeInfo.getShort(TypeInfoColumns.MAXIMUM_SCALE.name());
            // int sqldatatype = typeInfo.getInt(TypeInfoColumns.SQL_DATA_TYPE.name());
            // int sqldatetimesub = typeInfo.getInt(TypeInfoColumns.SQL_DATETIME_SUB.name());

            // System.out.println(ResultSetUtils.formatRow(typeInfo, columnCount, 10));

            // --- add the element to the list
            typeSystem.getOwnedElement().add(dataType);

            // --- create the mapping with the java language
            // TODO scorreia TypeMapping typeMapping = TypemappingFactory.eINSTANCE.createTypeMapping();
            // typeMapping.

        } // end of loop on typeinfo rows
        return typeSystem;
    }

    /**
     * Method "storeTable".
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
    public static List<TdColumn> getColumns(String catalogName, String schemaPattern, String tablePattern,
            String columnPattern, Connection connection) throws SQLException {
        List<TdColumn> tableColumns = new ArrayList<TdColumn>();

        // --- add columns to table
        ResultSet columns = getConnectionMetadata(connection).getColumns(catalogName, schemaPattern, tablePattern,
                columnPattern);
        while (columns.next()) {
            TdColumn column = ColumnHelper.createTdColumn(columns.getString(GetColumn.COLUMN_NAME.name()));
            column.setLength(columns.getInt(GetColumn.COLUMN_SIZE.name()));
            column.setIsNullable(NullableType.get(columns.getInt(GetColumn.NULLABLE.name())));
            // TODO columns.getString(GetColumn.TYPE_NAME.name());

            // TODO get column description (comment)

            // TODO scorreia other informations for columns can be retrieved here

            // --- create and set type of column
            TdSqlDataType sqlDataType = createDataType(columns);
            column.setType(sqlDataType);
            tableColumns.add(column);
        }

        // release JDBC resources
        columns.close();

        return tableColumns;
    }

    /**
     * Method "getTables".
     * 
     * @param catalogName a catalog name; must match the catalog name as it is stored in the database; "" retrieves
     * those without a catalog; null means that the catalog name should not be used to narrow the search
     * @param schemaPattern a schema name pattern; must match the schema name as it is stored in the database; ""
     * retrieves those without a schema; null means that the schema name should not be used to narrow the search
     * @param tablePattern a table name pattern; must match the table name as it is stored in the database
     * @param columnPattern a column name pattern; must match the column name as it is stored in the database
     * @param connection the connection
     * @return the tables with for the given catalog, schemas, table name pattern.
     * @throws SQLException
     */
    private static List<TdTable> getTables(String catalogName, String schemaPattern, String tablePattern,
            String columnPattern, Connection connection) throws SQLException {
        List<TdTable> tables = new ArrayList<TdTable>();

        ResultSet tablesSet = getConnectionMetadata(connection).getTables(catalogName, schemaPattern, tablePattern,
                TABLETYPE);
        while (tablesSet.next()) {
            TdTable table = createTable(catalogName, schemaPattern, columnPattern, connection, tablesSet);
            tables.add(table);
        }
        // release JDBC resources
        tablesSet.close();

        return tables;
    }

    /**
     * Method "getViews".
     * 
     * @param catalogName a catalog name; must match the catalog name as it is stored in the database; "" retrieves
     * those without a catalog; null means that the catalog name should not be used to narrow the search
     * @param schemaPattern a schema name pattern; must match the schema name as it is stored in the database; ""
     * retrieves those without a schema; null means that the schema name should not be used to narrow the search
     * @param viewPattern a view name pattern; must match the view name as it is stored in the database
     * @param columnPattern a column name pattern; must match the column name as it is stored in the database
     * @param connection the connection
     * @return the views with for the given catalog, schemas, view name pattern.
     * @throws SQLException
     */
    private static List<TdView> getViews(String catalogName, String schemaPattern, String viewPattern,
            String columnPattern, Connection connection) throws SQLException {
        List<TdView> tables = new ArrayList<TdView>();

        ResultSet viewSet = getConnectionMetadata(connection).getTables(catalogName, schemaPattern, viewPattern,
                VIEWTYPE);
        while (viewSet.next()) {
            TdView table = createView(catalogName, schemaPattern, viewPattern, columnPattern, connection, viewSet);
            tables.add(table);
        }
        // release JDBC resources
        viewSet.close();

        return tables;
    }

    private static TdView createView(String catalogName, String schemaPattern, String viewPattern,
            String columnPattern, Connection connection, ResultSet viewsSet) throws SQLException {
        TdView view = RelationalFactory.eINSTANCE.createTdView();
        String viewName = viewsSet.getString(GetTable.TABLE_NAME.name());
        view.setName(viewName);
        List<TdColumn> columns = getColumns(catalogName, schemaPattern, viewPattern, columnPattern, connection);
        ViewHelper.addColumns(view, columns);
        return view;
    }

    /**
     * Method "createTableWithColumns" create a Table with its columns (given the column pattern).
     * 
     * @param catalogName
     * @param schemaPattern
     * @param columnPattern the pattern of the columns to get (null means all columns, "" means no column, other means
     * specific columns)
     * @param connection
     * @param tablesSet
     * @return
     * @throws SQLException
     */
    private static TdTable createTable(String catalogName, String schemaPattern, String columnPattern,
            Connection connection, ResultSet tablesSet) throws SQLException {
        String tableName = tablesSet.getString(GetTable.TABLE_NAME.name());
        // --- create a table and add columns
        TdTable table = RelationalFactory.eINSTANCE.createTdTable();
        table.setName(tableName);
        List<TdColumn> columns = getColumns(catalogName, schemaPattern, tableName, columnPattern, connection);
        TableHelper.addColumns(table, columns);
        return table;
    }

    private static DatabaseMetaData getConnectionMetadata(Connection connection) throws SQLException {
        assert connection != null : "Connection should not be null in DatabaseContentRetriever.getConnectionMetadata() "
                + getConnectionInformations(connection);
        return connection.getMetaData();
    }

    private static TdSqlDataType createDataType(ResultSet columns) throws SQLException {
        if (true)
            return null; // FIXME scorreia remove this patch
        // this patch is here because the created sql data type is not stored in a resource set.
        TdSqlDataType sqlDataType = RelationalFactory.eINSTANCE.createTdSqlDataType();
        sqlDataType.setJavaDataType(columns.getInt(GetColumn.DATA_TYPE.name()));
        sqlDataType.setNumericPrecision(columns.getInt(GetColumn.DECIMAL_DIGITS.name()));
        sqlDataType.setNumericPrecisionRadix(columns.getInt(GetColumn.NUM_PREC_RADIX.name()));
        return sqlDataType;
    }

    /**
     * Method "createCatalog" creates a Catalog with the given name.
     * 
     * @param name the name of the catalog
     * @return the newly created catalog
     */
    private static TdCatalog createCatalog(String name) {
        if (name == null) {
            return null;
        }
        TdCatalog cat = RelationalFactory.eINSTANCE.createTdCatalog();
        cat.setName(name);

        // --- TODO set attributes of catalog
        return cat;
    }

    private static String getConnectionInformations(Connection connection) {
        return connection.toString(); // TODO scorreia give more user friendly informations.
    }

    /**
     * Method "createSchema" creates a schema if the given name is not null.
     * 
     * @param name a schema name (or null)
     * @return the created schema or null
     */
    private static TdSchema createSchema(String name) {
        if (name == null) {
            return null;
        }
        TdSchema schema = RelationalFactory.eINSTANCE.createTdSchema();
        schema.setName(name);
        return schema;
    }
}
