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
package org.talend.cwm.db.connection;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.talend.commons.emf.FactoriesUtil;
import org.talend.cwm.helper.CatalogHelper;
import org.talend.cwm.helper.DataProviderHelper;
import org.talend.cwm.helper.SchemaHelper;
import org.talend.cwm.management.connection.ConnectionParameters;
import org.talend.cwm.management.connection.DatabaseContentRetriever;
import org.talend.cwm.management.connection.JavaSqlFactory;
import org.talend.cwm.relational.RelationalPackage;
import org.talend.cwm.relational.TdCatalog;
import org.talend.cwm.relational.TdSchema;
import org.talend.cwm.relational.TdTable;
import org.talend.cwm.softwaredeployment.TdDataProvider;
import org.talend.cwm.softwaredeployment.TdProviderConnection;
import org.talend.utils.properties.PropertiesLoader;
import org.talend.utils.properties.TypedProperties;
import org.talend.utils.sugars.TypedReturnCode;
import org.talend.utils.time.TimeTracer;
import orgomg.cwm.objectmodel.core.ModelElement;

/**
 * DOC scorreia class global comment. Detailled comment
 */
public final class TalendCwmFactory {

    private static final Class<TalendCwmFactory> THAT = TalendCwmFactory.class;

    private static Logger log = Logger.getLogger(THAT);

    private TalendCwmFactory() {
    }

    /**
     * Method "initializeConnection" initializes objects, close connection and serializes objects.
     * 
     * @param connector
     * @param folderProvider
     * @throws SQLException
     */
    public static void initializeConnection(DBConnect connector, FolderProvider folderProvider) throws SQLException {
        createDataProvider(connector, folderProvider);

        // --- close connection now
        connector.closeConnection();

        // --- save on disk
        connector.saveInFiles();
    }

    /**
     * Method "createDataProvider" create the data provider, the catalogs and the schemas. The created data provider and
     * its related Catalog and Schemas are stored in the DBConnect class. In order to finally serialize them in a file,
     * the method {@link DBConnect#saveInFiles()} must be called.
     * 
     * @param connector the helper for building CWM objects from a connection
     * @param folderProvider contains the path where the file will be stored.
     * @return the data provider
     * @throws SQLException
     */
    public static TdDataProvider createDataProvider(DBConnect connector, FolderProvider folderProvider)
            throws SQLException {
        // --- connect and check the connection
        checkConnection(connector);

        // --- get data provider
        TdDataProvider dataProvider = getTdDataProvider(connector);
        // --- get the connection provider
        TdProviderConnection providerConnection = connector.getProviderConnection();
        
        // --- get database structure informations
        Collection<TdCatalog> catalogs = getCatalogs(connector);
        Collection<TdSchema> schemata = getSchemata(connector);

        // --- link everything
        DataProviderHelper.addProviderConnection(providerConnection, dataProvider);
        boolean allAdded = false;
        // TODO scorreia probably add only when catalogs is empty.
        if (catalogs.isEmpty()) {
            allAdded = DataProviderHelper.addSchemas(schemata, dataProvider);
            if (log.isInfoEnabled()) {
                log.info("all " + schemata.size() + " schemata added: " + allAdded);
            }
        } else {
            allAdded = DataProviderHelper.addCatalogs(catalogs, dataProvider);
            if (log.isInfoEnabled()) {
                log.info("all " + catalogs.size() + " catalogs added: " + allAdded);
            }
        }

        // --- print some informations
        if (log.isDebugEnabled()) {
            printInformations(catalogs, schemata);
        }

        String folder = ((folderProvider != null) && folderProvider.getFolder() != null) ? folderProvider.getFolder()
                .getAbsolutePath() : null;
        if (folder == null) { // do not serialize data
            log.info("Data provider not serialized: no folder given.");
            return dataProvider;
        }

        // --- add resources in resource set
        addInSoftwareSystemResourceSet(folder, connector, dataProvider);
        // The provider connection is stored in the dataprovider because of the containment relation.
        // addInSoftwareSystemResourceSet(folder, connector, providerConnection);

        addInRelationResourceSet(folder, connector, catalogs);
        addInRelationResourceSet(folder, connector, schemata);
        return dataProvider;
    }

    
    
    /**
     //FIXME  rli want to save the .relational files, but can't save all the table/column information on the .relational file.
      * so, copy the main method to here. But the code will happen the driver class not found exception. Later, will change this method.
     * @param connector
     * @param folderProvider
     * @return
     */
    public static TdDataProvider createDataProvider2(DBConnect connector, FolderProvider folderProvider) throws SQLException {

        TdDataProvider dataProvider = null;

        checkConnection(connector);

        // --- get data provider
        dataProvider = getTdDataProvider(connector);
        // --- get the connection provider
        TdProviderConnection providerConnection = connector.getProviderConnection();

        // --- now create the lower structure (tables, columns)
        // recreate a connection from the TdProviderConnection
        TypedReturnCode<Connection> rc = JavaSqlFactory.createConnection(providerConnection);
//        if (!rc.isOk()) {
//            log.error(rc.getMessage());
//            return null;
//        }
        boolean ok = false;
        Collection<TdCatalog> catalogs = connector.getCatalogs();
        Connection connection = rc.getObject();
        for (TdCatalog tdCatalog : catalogs) {
            List<TdSchema> schemas = CatalogHelper.getSchemas(tdCatalog);
            for (TdSchema tdSchema : schemas) {
                List<TdTable> tables = SchemaHelper.getTables(tdSchema);
                if (tables.isEmpty()) {
                    // TODO try to load them from DB.
                    List<TdTable> tablesWithAllColumns = DatabaseContentRetriever.getTablesWithAllColumns(tdCatalog.getName(),
                            tdSchema.getName(), connection);
                    ok = SchemaHelper.addTables(tablesWithAllColumns, tdSchema);

                }
            }
            // first try to get the columns
            List<TdTable> tables = CatalogHelper.getTables(tdCatalog);
            if (tables.isEmpty()) {
                // TODO try to load them from DB.
                List<TdTable> tablesWithAllColumns = DatabaseContentRetriever.getTablesWithAllColumns(tdCatalog.getName(), null,
                        connection);
                ok = CatalogHelper.addTables(tablesWithAllColumns, tdCatalog);
            }
        }
        if (!ok) {
            log.error("Tables not retrieved.");
        } else {
            log.info("table retrieved.");

        }

        connection.close();

        // --- save on disk
        connector.saveInFiles();
        return dataProvider;
    }
    
    /**
     * Method "getTdDataProvider" simply tries to instantiate a data provider from the given connection. The connector
     * should have already open its connection. If not, this method tries to open a connection. The caller should close
     * the connection.
     * 
     * @param connector the database connector
     * @return the DataProvider
     * @throws SQLException
     */
    public static TdDataProvider getTdDataProvider(DBConnect connector) throws SQLException {
        checkConnection(connector);
        boolean driverInfoRetrieved = connector.retrieveDriverInformations();
        if (!driverInfoRetrieved) {
            log.error("Could not retrieve the driver informations");
            return null; // stop here
        }

        return connector.getDataProvider();
    }

    /**
     * Method "getCatalogs". the connector should have already open its connection. If not, this method tries to open a
     * connection. The caller should close the connection.
     * 
     * @param connector the database connector
     * @return the catalogs (never null but could be empty depending on the database type)
     * @throws SQLException
     */
    public static Collection<TdCatalog> getCatalogs(DBConnect connector) throws SQLException {
        checkConnection(connector);
        boolean dbStructureRetrieved = connector.retrieveDatabaseStructure();
        if (!dbStructureRetrieved) {
            log.error("Could not retrieve the database structure");
            return Collections.emptyList();
        }
        return connector.getCatalogs();
    }

    /**
     * Method "getSchemata". the connector should have already open its connection. If not, this method tries to open a
     * connection. The caller should close the connection.
     * 
     * @param connector the database connector
     * @return the schemas (never null but could be empty depending on the database type)
     * @throws SQLException
     */
    public static Collection<TdSchema> getSchemata(DBConnect connector) throws SQLException {
        checkConnection(connector);
        boolean dbStructureRetrieved = connector.retrieveDatabaseStructure();
        if (!dbStructureRetrieved) {
            log.error("Could not retrieve the database structure");
            return Collections.emptyList();
        }
        return connector.getSchemata();
    }

    /**
     * DOC scorreia Comment method "createTechnicalName".
     * 
     * @param connectionName
     * @return
     */
    private static String createTechnicalName(String connectionName) {
        // TODO scorreia create utility class for this
        return connectionName;
    }

    private static String getDriverClassName(ConnectionParameters connectionParams) {
        // TODO scorreia create the utility class for this
        return null;
    }

    private static void addInRelationResourceSet(String folder, DBConnect connector,
            Collection<? extends ModelElement> elements) {
        for (ModelElement elt : elements) {
            addInResourceSet(folder, connector, elt, RelationalPackage.eNAME);
        }
    }

    private static void addInSoftwareSystemResourceSet(String folder, DBConnect connector, ModelElement elt) {
        addInResourceSet(folder, connector, elt, FactoriesUtil.PROV);
        // ORIG addInResourceSet(folder, connector, elt, SoftwaredeploymentPackage.eNAME);
    }

    private static void addInResourceSet(String folder, DBConnect connector, ModelElement pack, String extension) {
        if (pack != null) {
            String filename = createFilename(folder, pack.getName(), extension);
            connector.storeInResourceSet(pack, filename);
        }
    }

    /**
     * Method "checkConnection" checks whether the connection is open. If not, tries to connect.
     * 
     * @param connector
     * @throws SQLException
     */
    private static void checkConnection(DBConnect connector) throws SQLException {
        if (!connector.isConnected()) {
            boolean connected = connector.connect();
            if (!connected) {
                throw new SQLException("Connection failed for " + connector.getDatabaseUrl());
            }
        }
    }

    /**
     * Method "createFilename".
     * 
     * @param folder the folder
     * @param basename the filename without extension
     * @param extension the extension of the file
     * @return the path "folder/basename.extension"
     */
    private static String createFilename(String folder, String basename, String extension) {
        return folder + File.separator + basename + "." + extension;
    }

    /**
     * Method "printInformations" only for test purposes.
     * 
     * @param catalogs
     * @param schemata
     */
    private static void printInformations(Collection<TdCatalog> catalogs, Collection<TdSchema> schemata) {
        for (TdCatalog tdCatalog : catalogs) {
            System.out.println("Catalog = " + tdCatalog);
        }
        for (TdSchema tdSchema : schemata) {
            System.out.println("Schema = " + tdSchema + " in catalog " + tdSchema.getNamespace());
        }
    }

    public static void main(String[] args) {

        // --- load connection parameters from properties file
        TypedProperties connectionParams = PropertiesLoader.getProperties(THAT, "db.properties");

        String driverClassName = connectionParams.getProperty("driver");
        String dbUrl = connectionParams.getProperty("url");

        DBConnect connector = new DBConnect(dbUrl, driverClassName, connectionParams);
        try {
            TimeTracer tt = new TimeTracer("DB CONNECT", log);
            tt.start();

            // --- set where to save the files
            FolderProvider folderProvider = new FolderProvider();
            folderProvider.setFolder(new File("out"));
            initializeConnection(connector, folderProvider);
            tt.end("Everything saved.");

            // --- now create the lower structure (tables, columns)
            // recreate a connection from the TdProviderConnection
            TdProviderConnection providerConnection = connector.getProviderConnection();
            TypedReturnCode<Connection> rc = JavaSqlFactory.createConnection(providerConnection);
            if (!rc.isOk()) {
                log.error(rc.getMessage());
                return;
            }
            boolean ok = false;
            Collection<TdCatalog> catalogs = connector.getCatalogs();
            Connection connection = rc.getObject();
            for (TdCatalog tdCatalog : catalogs) {
                List<TdSchema> schemas = CatalogHelper.getSchemas(tdCatalog);
                for (TdSchema tdSchema : schemas) {
                    List<TdTable> tables = SchemaHelper.getTables(tdSchema);
                    if (tables.isEmpty()) {
                        // TODO try to load them from DB.
                        List<TdTable> tablesWithAllColumns = DatabaseContentRetriever.getTablesWithAllColumns(tdCatalog
                                .getName(), tdSchema.getName(), connection);
                        ok = SchemaHelper.addTables(tablesWithAllColumns, tdSchema);

                    }
                }
                // first try to get the columns
                List<TdTable> tables = CatalogHelper.getTables(tdCatalog);
                if (tables.isEmpty()) {
                    // TODO try to load them from DB.
                    List<TdTable> tablesWithAllColumns = DatabaseContentRetriever.getTablesWithAllColumns(tdCatalog
                            .getName(), null, connection);
                    ok = CatalogHelper.addTables(tablesWithAllColumns, tdCatalog);
                }
            }
            if (!ok) {
                log.error("Tables not retrieved.");
            } else {
                log.info("table retrieved.");

            }

            connection.close();

            // --- save on disk
            connector.saveInFiles();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
