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
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.talend.cwm.management.connection.DatabaseContentRetriever;
import org.talend.cwm.relational.TdCatalog;
import org.talend.cwm.relational.TdSchema;
import org.talend.cwm.relational.TdTable;

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
     * CatalogBuilder constructor. Catalogs and/or schemata are initialized, but not the lower structure such as the
     * table, trigger, procedures...
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
    }

    /**
     * DOC scorreia Comment method "initializeTables".
     * 
     * @throws SQLException
     */
    protected void initializeTables() throws SQLException {
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
        List<TdTable> tablesWithColumns = DatabaseContentRetriever.getTablesWithAllColumns(catName, schemaName,
                connection);
        // --- store table in Catalog or in Schema.
        if (schemaName != null) {
            TdSchema schema = name2schema.get(schemaName);
            schema.getOwnedElement().addAll(tablesWithColumns);
        } else {
            // store table in catalog
            // TODO scorreia what do we do when there is no schema
            TdCatalog cat = name2catalog.get(catName);
            if (cat != null) {
                cat.getOwnedElement().addAll(tablesWithColumns);
            } else {
                log.error("No schema nor catalog found for " + catName);
            }
        }
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
        name2catalog.putAll(DatabaseContentRetriever.getCatalogs(connection));
    }

    private void initializeSchema() throws SQLException {

        // initialize the catalog if not already done
        if (!catalogsInitialized) {
            initializeCatalog();
        }

        Map<String, List<TdSchema>> catalog2schemas = DatabaseContentRetriever.getSchemas(connection);

        // store schemas in catalogs
        Set<String> catNames = catalog2schemas.keySet();
        for (String catName : catNames) {
            TdCatalog catalog = name2catalog.get(catName);
            catalog.getOwnedElement().addAll(catalog2schemas.get(catName));
        }

        // set the flag to initialized and return the created catalog
        schemaInitialized = true;

    }

}
