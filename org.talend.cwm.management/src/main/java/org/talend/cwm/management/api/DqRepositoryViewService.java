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
package org.talend.cwm.management.api;

import java.io.File;
import java.io.FilenameFilter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.talend.commons.emf.EMFUtil;
import org.talend.commons.emf.FactoriesUtil;
import org.talend.cwm.db.connection.FolderProvider;
import org.talend.cwm.helper.CatalogHelper;
import org.talend.cwm.helper.DataProviderHelper;
import org.talend.cwm.helper.SchemaHelper;
import org.talend.cwm.management.connection.DatabaseContentRetriever;
import org.talend.cwm.management.connection.JavaSqlFactory;
import org.talend.cwm.relational.TdTable;
import org.talend.cwm.softwaredeployment.TdDataProvider;
import org.talend.cwm.softwaredeployment.TdProviderConnection;
import org.talend.utils.sql.ConnectionUtils;
import org.talend.utils.sugars.ReturnCode;
import org.talend.utils.sugars.TypedReturnCode;
import orgomg.cwm.objectmodel.core.ModelElement;
import orgomg.cwm.resource.relational.Catalog;
import orgomg.cwm.resource.relational.Schema;

/**
 * @author scorreia
 * 
 * Services for the DQ Repository view.
 */
public final class DqRepositoryViewService {

    private static Logger log = Logger.getLogger(DqRepositoryViewService.class);

    private DqRepositoryViewService() {
    }

    /**
     * Filter on the files with extension meaning data provider.
     */
    private static final FilenameFilter PRV_FILTER = new FilenameFilter() {

        public boolean accept(File dir, String name) {
            return name != null && name.endsWith(FactoriesUtil.PROV);
        }
    };

    /**
     * Method "listTdDataProviders" list all the connections in the given folder.
     * 
     * @param folder the path to the folder containing TdDataProviders
     * @return the list of all TdDataProviders in the folder (never null).
     */
    public static List<TdDataProvider> listTdDataProviders(File folder) {
        ArrayList<TdDataProvider> providers = new ArrayList<TdDataProvider>();
        File[] providerFiles = folder.listFiles(PRV_FILTER);
        for (File file : providerFiles) {
            TypedReturnCode<TdDataProvider> rc = readFromFile(file);
            if (rc.isOk()) {
                TdDataProvider dataProvider = rc.getObject();
                providers.add(dataProvider);
            } else {
                log.warn(rc.getMessage());
            }
        }
        return providers;
    }

    /**
     * Method "getTables" loads or get from catalog the tables and columns.
     * 
     * @param dataProvider the data provider
     * @param catalog the catalog
     * @param tablePattern the pattern of the tables to be loaded (from the DB)
     * @param loadFromDB true if we want to load tables from the database. false when the tables are already in the
     * catalog.
     * @return the list of tables.
     */
    public static List<TdTable> getTables(TdDataProvider dataProvider, Catalog catalog, String tablePattern,
            boolean loadFromDB) {
        if (loadFromDB) {
            return loadTables(dataProvider, catalog, tablePattern);
        } else {
            return CatalogHelper.getTables(catalog);
        }
    }

    public static List<TdTable> getTables(TdDataProvider dataProvider, Schema catalog, String tablePattern,
            boolean loadFromDB) {
        if (loadFromDB) {
            return loadTables(dataProvider, catalog, tablePattern);
        } else {
            return SchemaHelper.getTables(catalog);
        }
    }

    /**
     * Method "saveDataProviderAndStructure" will save the data provider in the given folder.
     * 
     * @param dataProvider the data provider to save
     * @param folderProvider provides the path where to save the data provider and related elements.
     * @return
     */
    public static boolean saveDataProviderAndStructure(TdDataProvider dataProvider, FolderProvider folderProvider) {
        assert dataProvider != null;
        assert folderProvider != null;

        String folder = ((folderProvider != null) && folderProvider.getFolder() != null) ? folderProvider.getFolder()
                .getAbsolutePath() : null;
        if (folder == null) { // do not serialize data
            log.info("Data provider not serialized: no folder given.");
            return false;
        }

        // --- add resources in resource set
        EMFUtil util = new EMFUtil();
        ResourceSet resourceSet = util.getResourceSet();
        URI uri = URI.createFileURI(createFilename(folder, dataProvider.getName(), FactoriesUtil.PROV));
        final Resource resource = resourceSet.createResource(uri);
        if (resource == null) {
            return false;
        }
        boolean ok = resource.getContents().add(dataProvider);
        if (log.isDebugEnabled()) {
            log.debug("Data provider added " + ok);
        }

        ok = resource.getContents().addAll(dataProvider.getDescription());
        if (log.isDebugEnabled()) {
            log.debug("Data provider descriptions added " + ok);
        }

        // The provider connection is stored in the dataprovider because of the containment relation.
        // addInSoftwareSystemResourceSet(folder, connector, providerConnection);

        Collection<? extends ModelElement> catalogs = DataProviderHelper.getTdCatalogs(dataProvider);
        ok = resource.getContents().addAll(catalogs);
        if (log.isDebugEnabled()) {
            log.debug("Catalogs added " + ok);
        }

        Collection<? extends ModelElement> schemata = DataProviderHelper.getTdSchema(dataProvider);
        ok = resource.getContents().addAll(schemata);
        if (log.isDebugEnabled()) {
            log.debug("Schema added " + ok);
        }

        return util.save();
    }

    /**
     * Method "saveOpenDataProvider" saves a Data provider which has already a resource (has already been saved once).
     * 
     * @param dataProvider the data provider to save
     * @return true if saved without any problem.
     */
    public static ReturnCode saveOpenDataProvider(TdDataProvider dataProvider) {
        assert dataProvider != null;
        Resource resource = dataProvider.eResource();

        // // get all content
        // try {
        // TreeIterator<EObject> it = EcoreUtil.getAllContents(dataProvider, true);
        // while (it.hasNext()) {
        // EObject nextObj = it.next();
        // resource.getContents().add(nextObj);
        // }
        // } catch (RuntimeException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        // System.out.println("Sortie");

        ReturnCode rc = new ReturnCode();
        if (resource == null) {
            rc.setReturnCode("No resource in given Data provider " + dataProvider.getName()
                    + ". Data provider must be saved first.", false);
        } else {
            rc.setOk(EMFUtil.saveResource(resource));
        }
        return rc;
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
        return folder + File.separator + createTechnicalName(basename) + "." + extension;
    }

    /**
     * Method "createTechnicalName" creates a technical name used for file system storage.
     * 
     * @param functionalName the user friendly name
     * @return the technical name created from the user given name.
     */
    public static String createTechnicalName(String functionalName) {
        if (functionalName == null) {
            log.warn("A functional name should not be null");
            return "no_name";
        }
        // TODO scorreia remove all characters such white spaces, accents, everything that is dangerous when used for
        // file names...

        return functionalName;
    }

    /**
     * Method "loadTables".
     * 
     * @param dataProvider
     * @param catalog (must not be null)
     * @param tablePattern
     * @return the list of tables matching the given pattern
     */
    private static List<TdTable> loadTables(TdDataProvider dataProvider, Catalog catalog, String tablePattern) {
        List<TdTable> tables = new ArrayList<TdTable>();
        assert dataProvider != null;
        assert catalog != null;

        String catalogName = catalog.getName();
        if (catalogName == null) {
            log.error("No catalog given. Cannot retrieve tables!");
            return tables;
        }
        return loadTables(dataProvider, catalog, null, tablePattern);
    }

    private static List<TdTable> loadTables(TdDataProvider dataProvider, Schema schema, String tablePattern) {
        List<TdTable> tables = new ArrayList<TdTable>();
        assert dataProvider != null;
        assert schema != null;

        String schemaName = schema.getName();
        if (schemaName == null) {
            log.error("No schema given. Cannot retrieve tables!");
            return tables;
        }
        return loadTables(dataProvider, null, schema, tablePattern);
    }

    private static List<TdTable> loadTables(TdDataProvider dataProvider, Catalog catalog, Schema schema,
            String tablePattern) {
        List<TdTable> tables = new ArrayList<TdTable>();
        assert dataProvider != null;

        String schemaName = (schema == null) ? null : schema.getName();
        String catalogName = (catalog == null) ? null : catalog.getName();

        TypedReturnCode<TdProviderConnection> rc = DataProviderHelper.getTdProviderConnection(dataProvider);
        if (!rc.isOk()) {
            log.error(rc.getMessage());
            return tables;
        }

        TypedReturnCode<Connection> rcConn = JavaSqlFactory.createConnection(rc.getObject());
        if (!rc.isOk()) {
            log.error(rc.getMessage());
            return tables;
        }
        Connection connection = rcConn.getObject();

        try {
            return DatabaseContentRetriever.getTablesWithColumns(catalogName, schemaName, tablePattern, connection);
        } catch (SQLException e) {
            log.error(e);
        } finally {
            if (connection != null) {
                ConnectionUtils.closeConnection(connection);
            }
        }
        return tables;
    }

    /**
     * Method "readFromFile".
     * 
     * @param file the file to read
     * @return the Data provider if found.
     */
    private static TypedReturnCode<TdDataProvider> readFromFile(File file) {
        TypedReturnCode<TdDataProvider> rc = new TypedReturnCode<TdDataProvider>();
        EMFUtil util = new EMFUtil();

        ResourceSet rs = util.getResourceSet();
        Resource r = rs.getResource(URI.createFileURI(file.getAbsolutePath()), true);
        Collection<TdDataProvider> tdDataProviders = DataProviderHelper.getTdDataProviders(r.getContents());
        if (tdDataProviders.isEmpty()) {
            rc.setReturnCode("No Data Provider found in " + file.getAbsolutePath(), false);
        }
        if (tdDataProviders.size() > 1) {
            rc.setReturnCode("Found too many DataProvider (" + tdDataProviders.size() + ") in file "
                    + file.getAbsolutePath(), false);
        }
        TdDataProvider prov = tdDataProviders.iterator().next();
        rc.setObject(prov);
        return rc;
    }

}
