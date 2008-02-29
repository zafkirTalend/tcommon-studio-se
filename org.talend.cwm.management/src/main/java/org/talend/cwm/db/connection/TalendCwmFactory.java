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
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;

import org.apache.log4j.Logger;
import org.talend.cwm.relational.RelationalPackage;
import org.talend.cwm.relational.TdCatalog;
import org.talend.cwm.relational.TdSchema;
import org.talend.cwm.softwaredeployment.SoftwaredeploymentPackage;
import org.talend.cwm.softwaredeployment.TdDataProvider;
import org.talend.cwm.softwaredeployment.TdProviderConnection;
import org.talend.utils.properties.PropertiesLoader;
import org.talend.utils.properties.TypedProperties;
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
     * Method "createDataProvider" create the data provider, the catalogs and the schemas.
     * 
     * @param connector
     * @param folderProvider
     * @return
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
        if ((dataProvider != null) && (providerConnection != null)) {
            // this relation is a reference to a contained object.
            dataProvider.getResourceConnection().add(providerConnection);
            // this relation is a reference to a non contained object.
            // dataProvider.getClientConnection().add(providerConnection);
        }
        boolean allAdded = dataProvider.getDataPackage().addAll(schemata);
        if (log.isInfoEnabled()) {
            log.info("all " + schemata.size() + " schemata added: " + allAdded);
        }
        allAdded = dataProvider.getDataPackage().addAll(catalogs);
        if (log.isInfoEnabled()) {
            log.info("all " + catalogs.size() + " catalogs added: " + allAdded);
        }

        // --- print some informations
        if (log.isDebugEnabled()) {
            printInformations(catalogs, schemata);
        }

        String folder = folderProvider.getFolder().getAbsolutePath();
        if (folder == null) { // do not serialize data
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
     * Method "getTdDataProvider". the connector should have already open its connection. If not, this method tries to
     * open a connection. The caller should close the connection.
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
        addInResourceSet(folder, connector, elt, SoftwaredeploymentPackage.eNAME);
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
            FolderProvider folderProvider = new FolderProvider();
            folderProvider.setFolder(new File("out"));
            initializeConnection(connector, folderProvider);
            tt.end("Everything saved.");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
