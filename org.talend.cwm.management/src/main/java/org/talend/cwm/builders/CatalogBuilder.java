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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.talend.cwm.relational.RelationalFactory;
import org.talend.cwm.relational.TdCatalog;
import org.talend.cwm.relational.TdColumn;
import org.talend.cwm.relational.TdSchema;
import org.talend.cwm.relational.TdSqlDataType;
import org.talend.cwm.relational.TdTable;
import org.talend.utils.sql.metadata.constants.GetColumn;
import org.talend.utils.sql.metadata.constants.GetTable;
import org.talend.utils.sql.metadata.constants.MetaDataConstants;
import org.talend.utils.sql.metadata.constants.TableType;

import orgomg.cwm.resource.relational.enumerations.NullableType;

/**
 * @author scorreia
 * 
 * This class builds relational CWM objects from a connection.
 */
public class CatalogBuilder extends CwmBuilder {

    private static Logger log = Logger.getLogger(CatalogBuilder.class);

    private final Map<String, TdCatalog> name2catalog = new HashMap<String, TdCatalog>();

    private final Map<String, TdSchema> name2schema = new HashMap<String, TdSchema>();

    private boolean catalogsInitialized = false;

    private boolean schemaInitialized = false;

    /**
     * CatalogBuilder constructor.
     * 
     * @param connection2 the sql connection
     * @throws SQLException
     */
    public CatalogBuilder(Connection conn) throws SQLException {
        super(conn);
        try {
            printMetadata();
        } catch (RuntimeException e) {
            log.error(e);
        }

        initializeCatalog();
        initializeSchema();
        initializeTables();
    }

    /**
     * DOC scorreia Comment method "initializeTables".
     * 
     * @throws SQLException
     */
    private void initializeTables() throws SQLException {
        if (!catalogsInitialized) {
            initializeCatalog();
        }
        if (!schemaInitialized) {
            initializeSchema();
        }

        if (name2catalog.isEmpty()) { // we got only schemata
            for (TdSchema schema : name2schema.values()) {
                setTablesIntoStructure(null, schema.getName());
            }
        } else if (name2schema.isEmpty()) { // we got only catalogs
            for (TdCatalog cat : name2catalog.values()) {
                setTablesIntoStructure(cat.getName(), null);
            }

        } else { // we got schemata and catalogs
            for (TdCatalog cat : name2catalog.values()) {
                for (TdSchema schema : name2schema.values()) {
                    setTablesIntoStructure(cat.getName(), schema.getName());
                }
            }
        }
    } // eom initializeTables

    private void setTablesIntoStructure(String catName, String schemaName) throws SQLException {
        ResultSet tablesSet = databaseMetadata.getTables(catName, schemaName, null, new String[] { TableType.TABLE
                .toString() });

        // ResultSetUtils.printResultSet(tablesSet, 20);

        while (tablesSet.next()) {
            String tableName = tablesSet.getString(GetTable.TABLE_NAME.name());
            // --- create a table
            TdTable table = createTable(tableName);
            fillAndStoreTable(table, catName, schemaName);
        }
        // release JDBC resources
        tablesSet.close();
    }

    /**
     * DOC scorreia Comment method "storeTable".
     * 
     * @param table
     * @param catName
     * @param schemaName
     * @throws SQLException
     */
    private void fillAndStoreTable(TdTable table, String catName, String schemaName) throws SQLException {
        // --- add columns to table
        ResultSet columns = databaseMetadata.getColumns(catName, schemaName, table.getName(), null);
        while (columns.next()) {
            TdColumn column = RelationalFactory.eINSTANCE.createTdColumn();
            column.setName(columns.getString(GetColumn.COLUMN_NAME.name()));
            column.setLength(columns.getInt(GetColumn.COLUMN_SIZE.name()));
            column.setIsNullable(NullableType.get(columns.getInt(GetColumn.NULLABLE.name())));
            // TODO columns.getString(GetColumn.TYPE_NAME.name());

            // TODO get column description (comment)

            // TODO scorreia other informations for columns can be retrieved here

            // --- create and set type of column
            TdSqlDataType sqlDataType = createDataType(columns);
            column.setType(sqlDataType);

            table.getOwnedElement().add(column);
        }

        // release JDBC resources
        columns.close();

        // --- store table in Catalog or in Schema.
        if (schemaName != null) {
            TdSchema schema = name2schema.get(schemaName);
            schema.getOwnedElement().add(table);
        } else {
            // store table in catalog
            // TODO scorreia what do we do when there is no schema
            TdCatalog cat = name2catalog.get(catName);
            if (cat != null) {
                cat.getOwnedElement().add(table);
            } else {
                log.error("No schema nor catalog found for " + catName);
            }
        }
    }

    private TdSqlDataType createDataType(ResultSet columns) throws SQLException {
        if (true)
            return null; // FIXME scorreia remove this patch
        TdSqlDataType sqlDataType = RelationalFactory.eINSTANCE.createTdSqlDataType();
        sqlDataType.setJavaDataType(columns.getInt(GetColumn.DATA_TYPE.name()));
        sqlDataType.setNumericPrecision(columns.getInt(GetColumn.DECIMAL_DIGITS.name()));
        sqlDataType.setNumericPrecisionRadix(columns.getInt(GetColumn.NUM_PREC_RADIX.name()));
        return sqlDataType;
    }

    /**
     * DOC scorreia Comment method "createColumn".
     * 
     * @param name
     * @param length
     * @return
     */
    private TdColumn createColumn(String name, int length) {
        TdColumn column = RelationalFactory.eINSTANCE.createTdColumn();
        column.setName(name);
        column.setLength(length);
        return column;
    }

    /**
     * DOC scorreia Comment method "createTable".
     * 
     * @param tableName
     * @return
     */
    private TdTable createTable(String tableName) {
        TdTable table = RelationalFactory.eINSTANCE.createTdTable();
        table.setName(tableName);
        return table;
    }

    /**
     * Method "printMetadata" for debug or test purpose only.
     * 
     * @throws SQLException
     */
    private void printMetadata() throws SQLException {
        int databaseMinorVersion = databaseMetadata.getDatabaseMinorVersion();
        int databaseMajorVersion = databaseMetadata.getDatabaseMajorVersion();
        String databaseProductName = databaseMetadata.getDatabaseProductName();
        String databaseProductVersion = databaseMetadata.getDatabaseProductVersion();
        print("Database=", databaseProductName + " " + databaseProductVersion + " " + databaseMajorVersion + "."
                + databaseMinorVersion);

        String driverName = databaseMetadata.getDriverName();
        String driverVersion = databaseMetadata.getDriverVersion();
        int driverMajorVersion = databaseMetadata.getDriverMajorVersion();
        int driverMinorVersion = databaseMetadata.getDriverMinorVersion();
        print("Driver=", driverName + " " + driverVersion + " " + driverMajorVersion + "." + driverMinorVersion);

        int majorVersion = databaseMetadata.getJDBCMajorVersion();
        int minorVersion = databaseMetadata.getJDBCMinorVersion();
        print("JDBC=", "" + majorVersion + "." + minorVersion);

        String identifierQuoteString = databaseMetadata.getIdentifierQuoteString();
        print("quote=", identifierQuoteString);

        String extraNameCharacters = databaseMetadata.getExtraNameCharacters();
        print("extra=", extraNameCharacters);

        String procedureTerm = databaseMetadata.getProcedureTerm();
        print("proc=", procedureTerm);

        String url = databaseMetadata.getURL();
        print("DB url=", url);
        String userName = databaseMetadata.getUserName();
        print("user=", userName);

        String catalogTerm = databaseMetadata.getCatalogTerm();
        print("Catalog term=", catalogTerm);

        String catalogSeparator = databaseMetadata.getCatalogSeparator();
        print("Catalog sep=", catalogSeparator);
        String keywords = databaseMetadata.getSQLKeywords();
        print("keywords=", keywords);

        String schemaTerm = databaseMetadata.getSchemaTerm();
        print("Schema term=", schemaTerm);

    }

    /**
     * Method "getCatalogs".
     * 
     * @return the catalogs initialized with the metadata (The list could be empty)
     */
    public Collection<TdCatalog> getCatalogs() {
        return this.name2catalog.values();
    }

    /**
     * Method "getSchemata".
     * 
     * @return the schemata initialized with the metadata
     */
    public Collection<TdSchema> getSchemata() {
        return this.name2schema.values();
    }

    private void initializeCatalog() throws SQLException {

        ResultSet catalogs = databaseMetadata.getCatalogs();
        if (catalogs == null) {
            String currentCatalog = connection.getCatalog();
            // got the current catalog name, create a Catalog
            createCatalog(currentCatalog);
        } else {
            // else DB support getCatalogs() method
            while (catalogs.next()) {
                String catName = catalogs.getString(MetaDataConstants.TABLE_CAT.name());
                createCatalog(catName);
            }
        }
        catalogsInitialized = true;

        catalogs.close();
    }

    private void initializeSchema() throws SQLException {

        ResultSet schemas = databaseMetadata.getSchemas();
        if (schemas != null) {
            // debug
            // if (log.isDebugEnabled()) {
            // ResultSetUtils.printResultSet(schemas, 40);

            // }

            while (schemas.next()) {
                // create the schemata
                String schemaName = schemas.getString(MetaDataConstants.TABLE_SCHEM.name());
                TdSchema schema = createSchema(schemaName);

                // set link Catalog -> Schema
                // TODO scorreia handle sybase case: no catalog name, only one column in this result set.
                try {
                    String catName = schemas.getString(MetaDataConstants.TABLE_CATALOG.name());
                    if (catName != null) {
                        // initialize the catalog if not already done
                        if (!catalogsInitialized) {
                            initializeCatalog();
                        }
                        // get the catalog and add the schema
                        TdCatalog catalog = name2catalog.get(catName);
                        catalog.getOwnedElement().add(schema);
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            // release JDBC resources
            schemas.close();
        }

        // if no schema exist in catalog, do not create a default one.
        // The tables will be added directly to the catalog.

        // set the flag to initialized and return the created catalog
        schemaInitialized = true;

    }

    /**
     * Method "createCatalog" creates a Catalog with the given name.
     * 
     * @param name the name of the catalog
     * @return the newly created catalog
     */
    private TdCatalog createCatalog(String name) {
        if (name == null) {
            return null;
        }
        TdCatalog cat = RelationalFactory.eINSTANCE.createTdCatalog();
        cat.setName(name);
        name2catalog.put(name, cat);

        // --- TODO set attributes of catalog
        return cat;
    }

    // utility

    /**
     * Method "createSchema" creates a schema if the given name is not null.
     * 
     * @param name a schema name (or null)
     * @return the created schema or null
     */
    private TdSchema createSchema(String name) {
        if (name == null) {
            return null;
        }
        TdSchema schema = RelationalFactory.eINSTANCE.createTdSchema();
        schema.setName(name);
        name2schema.put(name, schema);
        return schema;
    }

}
