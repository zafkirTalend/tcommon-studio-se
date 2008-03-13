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
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.talend.cwm.helper.CatalogHelper;
import org.talend.cwm.management.connection.DatabaseContentRetriever;
import org.talend.cwm.relational.RelationalFactory;
import org.talend.cwm.relational.TdCatalog;
import org.talend.cwm.relational.TdSchema;
import org.talend.utils.sql.metadata.constants.MetaDataConstants;

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
    public CatalogBuilder(Connection conn) {
        super(conn);
        try {
            if (log.isDebugEnabled()) {
                printMetadata();
            }
        } catch (RuntimeException e) {
            log.error(e, e);
        } catch (SQLException e) {
            log.error(e, e);
        }

    }

    /**
     * Method "printMetadata" for debug or test purpose only.
     * 
     * @throws SQLException
     */
    private void printMetadata() throws SQLException {
        DatabaseMetaData databaseMetadata = getConnectionMetadata(connection);
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
        if (!catalogsInitialized) {
            initializeCatalog();
        }
        return this.name2catalog.values();
    }

    /**
     * Method "getSchemata".
     * 
     * @return the schemata initialized with the metadata
     */
    public Collection<TdSchema> getSchemata() {
        if (!schemaInitialized) {
            initializeSchema();
        }
        return this.name2schema.values();
    }

    private void initializeCatalog() {
        try {
            initializeCatalogLow();
        } catch (SQLException e) {
            log.error(e, e);
        }
    }

    private void initializeCatalogLow() throws SQLException {
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
        catalogsInitialized = true;
    }

    private void initializeSchema() {
        try {
            initializeSchemaLow();
        } catch (SQLException e) {
            log.error(e, e);
        }
    }

    private void initializeSchemaLow() throws SQLException {

        // initialize the catalog if not already done
        if (!catalogsInitialized) {
            initializeCatalog();
        }

        Map<String, List<TdSchema>> catalog2schemas = DatabaseContentRetriever.getSchemas(connection);

        // store schemas in catalogs
        Set<String> catNames = catalog2schemas.keySet();
        for (String catName : catNames) {
            if (catName != null) {
                TdCatalog catalog = name2catalog.get(catName);
                CatalogHelper.addSchemas(catalog2schemas.get(catName), catalog);
            }
        }

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

        // --- TODO set attributes of catalog
        return cat;
    }
}
