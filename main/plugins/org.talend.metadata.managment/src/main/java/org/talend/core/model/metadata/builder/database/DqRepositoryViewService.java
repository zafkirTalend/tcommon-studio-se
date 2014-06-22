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

import java.io.UnsupportedEncodingException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import metadata.managment.i18n.Messages;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.talend.commons.exception.PersistenceException;
import org.talend.core.model.metadata.MetadataFillFactory;
import org.talend.core.model.metadata.builder.connection.Connection;
import org.talend.core.model.metadata.builder.connection.DatabaseConnection;
import org.talend.core.model.metadata.builder.util.MetadataConnectionUtils;
import org.talend.core.model.properties.Property;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryViewObject;
import org.talend.core.repository.model.ProxyRepositoryFactory;
import org.talend.cwm.helper.CatalogHelper;
import org.talend.cwm.helper.ColumnSetHelper;
import org.talend.cwm.helper.ConnectionHelper;
import org.talend.cwm.helper.PackageHelper;
import org.talend.cwm.helper.SchemaHelper;
import org.talend.cwm.helper.TaggedValueHelper;
import org.talend.cwm.relational.TdColumn;
import org.talend.cwm.relational.TdTable;
import org.talend.cwm.relational.TdView;
import org.talend.cwm.xml.TdXmlElementType;
import org.talend.utils.sql.ConnectionUtils;
import org.talend.utils.sql.metadata.constants.TableType;
import org.talend.utils.string.AsciiUtils;
import org.talend.utils.sugars.TypedReturnCode;
import orgomg.cwm.objectmodel.core.Package;
import orgomg.cwm.objectmodel.core.TaggedValue;
import orgomg.cwm.resource.relational.Catalog;
import orgomg.cwm.resource.relational.ColumnSet;
import orgomg.cwm.resource.relational.Schema;

/**
 * @author scorreia
 * 
 * Services for the DQ Repository view.
 */
public final class DqRepositoryViewService {

    private static Logger log = Logger.getLogger(DqRepositoryViewService.class);

    private static final SimpleDateFormat SMPL_DATE_FMT = new SimpleDateFormat("yyyyMMddhhmmss"); //$NON-NLS-1$

    /**
     * if true, the catalogs (and schemas) are stored in the same file as the data provider. Used for tests only.
     * 
     * TODO scorreia (saving catalog outside data provider's file) set it to false for big databases.
     * 
     * In case when optimization is needed: set this boolean to false and correct code so that everything works as
     * before (DQ Repository view must not show catalog's files and Catalogs must still be children of the Data
     * provider). Check also that old files (.prv) are still readable by the application.
     */

    private DqRepositoryViewService() {
    }

    private static final String CHARS_TO_REMOVE = "/"; //$NON-NLS-1$

    private static final String REPLACEMENT_CHARS = "_"; //$NON-NLS-1$

    /**
     * Method "createTechnicalName" creates a technical name used for file system storage. MOD mzhao make this method as
     * public access.
     * 
     * @param functionalName the user friendly name
     * @return the technical name created from the user given name.
     */
    public static String createTechnicalName(final String functionalName) {
        String techname = "no_name"; //$NON-NLS-1$
        if (functionalName == null) {
            log.warn("A functional name should not be null");
            return techname;
        }
        // encode in base 64 so that all characters such white spaces, accents,
        // everything that is dangerous when used
        // for file names are removed
        try {
            // encode
            String b64 = new String(Base64.encodeBase64(functionalName.getBytes()), "UTF-8"); //$NON-NLS-1$
            // replace special characters
            String date = SMPL_DATE_FMT.format(new Date(System.currentTimeMillis()));
            techname = AsciiUtils.replaceCharacters(b64, CHARS_TO_REMOVE, REPLACEMENT_CHARS) + date;
        } catch (UnsupportedEncodingException e) {
            log.error(e, e);
        } // .replaceAll(B64ID, PREFIX);
        if (log.isDebugEnabled()) {
            log.debug("Functional name: " + functionalName + " -> techname: " + techname);
        }
        return techname;
    }

    /**
     * Method "refreshDataProvider" reload database structure. Existing elements (catalogs, tables...) must not be
     * replaced by new elements. Only their content must be updated because these elements can be refered to by
     * analysis.
     * 
     * @param dataProvider
     * @param catalogPattern the catalogs to load (can be null, meaning all are loaded)
     * @param schemaPattern the schema to load (can be null, meaning all are loaded)
     * @return true if the catalog have been reload
     */
    public static boolean refreshDataProvider(Connection dataProvider, String catalogPattern, String schemaPattern) {
        // TODO scorreia implement me
        return false;
    }

    /**
     * DOC scorreia Comment method "refreshTables".
     * 
     * @param schema
     * @param tablePattern
     * @return
     */
    public static boolean refreshTables(Package schema, String tablePattern) {
        // TODO scorreia implement me
        return false;
    }

    /**
     * DOC scorreia Comment method "refreshViews".
     * 
     * @param schema
     * @param viewPattern
     * @return
     */
    public static boolean refreshViews(Package schema, String viewPattern) {
        // TODO scorreia implement me
        return false;
    }

    /**
     * DOC scorreia Comment method "refreshColumns".
     * 
     * @param table
     * @param columnPattern
     * @return
     */
    public static boolean refreshColumns(ColumnSet table, String columnPattern) {
        // TODO scorreia implement me
        return false;
    }

    /**
     * Method "getTables" loads the tables from the database or get the tables from the catalog .
     * 
     * @param dataProvider the data provider
     * @param catalog the catalog (must not be null)
     * @param tablePattern the pattern of the tables to be loaded (from the DB)
     * @param loadFromDB true if we want to load tables from the database. false when the tables are already in the
     * catalog.
     * @return the list of tables. Theses tables are not added to the given catalog. It must be done by the caller.
     * @throws Exception
     */
    public static List<TdTable> getTables(Connection dataProvider, Catalog catalog, String tablePattern, boolean loadFromDB)
            throws Exception {
        if (loadFromDB) {
            return loadTables(dataProvider, catalog, tablePattern);
        } else {
            return CatalogHelper.getTables(catalog);
        }
    }

    public static List<TdTable> getTables(Connection dataProvider, Schema schema, String tablePattern, boolean loadFromDB)
            throws Exception {
        if (loadFromDB) {
            final Catalog parentCatalog = CatalogHelper.getParentCatalog(schema);
            return loadTables(dataProvider, parentCatalog, schema, tablePattern);
        } else {
            return SchemaHelper.getTables(schema);
        }
    }

    public static List<TdTable> getTables(Connection dataProvider, Package pack, String tablePattern, boolean loadFromDB)
            throws Exception {
        if (pack instanceof Schema) {
            return getTables(dataProvider, (Schema) pack, tablePattern, loadFromDB);
        } else if (pack instanceof Catalog) {
            return getTables(dataProvider, (Catalog) pack, tablePattern, loadFromDB);
        }
        return new ArrayList<TdTable>();

    }

    public static List<TdView> getViews(Connection dataProvider, Catalog catalog, String viewPattern, boolean loadFromDB)
            throws Exception {
        if (loadFromDB) {
            return loadViews(dataProvider, catalog, null, viewPattern);
        } else {
            return CatalogHelper.getViews(catalog);
        }
    }

    public static List<TdView> getViews(Connection dataProvider, Schema schema, String viewPattern, boolean loadFromDB)
            throws Exception {
        if (loadFromDB) {
            // get catalog is exists
            final Catalog parentCatalog = CatalogHelper.getParentCatalog(schema);
            return loadViews(dataProvider, parentCatalog, schema, viewPattern);
        } else {
            return SchemaHelper.getViews(schema);
        }
    }

    public static List<TdView> getViews(Connection dataProvider, Package pack, String viewPattern, boolean loadFromDB)
            throws Exception {
        if (pack instanceof Schema) {
            return getViews(dataProvider, (Schema) pack, viewPattern, loadFromDB);
        } else if (pack instanceof Catalog) {
            return getViews(dataProvider, (Catalog) pack, viewPattern, loadFromDB);
        }
        return new ArrayList<TdView>();
    }

    /**
     * Method "getColumns". The link between the column set and its columns is set in this method when required.
     * 
     * @param dataProvider the data provider for connecting to database (can be null when the columns are not loaded
     * from the database)
     * @param columnSet a column set (Table or View)
     * @param columnPattern the pattern of the columns to get (can be null)
     * @param loadFromDB true if columns must be loaded from the database
     * @return
     * @throws Exception
     */
    public static List<TdColumn> getColumns(Connection dataProvider, ColumnSet columnSet, String columnPattern, boolean loadFromDB)
            throws Exception {
        if (loadFromDB) {
            // MOD by zshen use new API to fill Columns
            List<TdColumn> columnList = new ArrayList<TdColumn>();
            TypedReturnCode<java.sql.Connection> rcConn = MetadataConnectionUtils
                    .checkConnection((DatabaseConnection) dataProvider);
            if (!rcConn.isOk()) {
                log.error(rcConn.getMessage()); // scorreia show error to the
                                                // user
                throw new Exception(rcConn.getMessage());
            }
            java.sql.Connection connection = rcConn.getObject();
            try {
                DatabaseMetaData dm = ExtractMetaDataUtils.getDatabaseMetaData(connection, (DatabaseConnection) dataProvider);
                columnList = MetadataFillFactory.getDBInstance().fillColumns(columnSet, dm, null, null);
            } finally {
                ConnectionUtils.closeConnection(connection);
            }
            return columnList;
            // ~
            // return loadColumns(dataProvider, columnSet, columnPattern);
        } else {
            return ColumnSetHelper.getColumns(columnSet);
        }
    }

    // private static String getName(ModelElement element) {
    // return element != null ? element.getName() : null;
    // }

    /**
     * Method "createFilename".
     * 
     * @param folder the folder
     * @param basename the filename without extension
     * @param extension the extension of the file
     * @return the path "folder/basename.extension"
     */
    public static String createFilename(String basename, String extension) {
        return createTechnicalName(basename) + "." + extension;//$NON-NLS-1$
    }

    /**
     * Method "loadTables".
     * 
     * @param dataProvider
     * @param catalog (must not be null)
     * @param tablePattern
     * @return the list of tables matching the given pattern
     * @throws Exception
     */
    private static List<TdTable> loadTables(Connection dataProvider, Catalog catalog, String tablePattern) throws Exception {
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

    private static List<TdTable> loadTables(Connection dataPloadTablesrovider, Catalog catalog, Schema schema, String tablePattern)
            throws Exception {
        List<TdTable> tables = new ArrayList<TdTable>();
        // PTODO scorreia check return code
        // MOD by zshen use new API to fill tables

        TypedReturnCode<java.sql.Connection> rcConn = MetadataConnectionUtils
                .checkConnection((DatabaseConnection) dataPloadTablesrovider);
        if (!rcConn.isOk()) {
            log.error(rcConn.getMessage());
            throw new Exception(rcConn.getMessage());
        }

        java.sql.Connection connection = rcConn.getObject();
        String[] tableType = new String[] { TableType.TABLE.toString() };
        try {
            DatabaseMetaData dm = ExtractMetaDataUtils.getDatabaseMetaData(connection,
                    (DatabaseConnection) dataPloadTablesrovider, false);
            // MOD msjian 2011-10-9 TDQ-3566: do not fill tables after existing
            // MOD gdbu 2011-10-25 TDQ-3816 : If tables exists, will no longer be added.(compare with tables , not all
            // element)
            if (schema != null) {
                if (PackageHelper.getTables(schema).size() == 0) {
                    tables = MetadataFillFactory.getDBInstance().fillTables(schema, dm, null, tablePattern, tableType);
                } else {
                    MetadataFillFactory.getDBInstance().setLinked(false);
                    tables = MetadataFillFactory.getDBInstance().fillTables(schema, dm, null, tablePattern, tableType);
                    MetadataFillFactory.getDBInstance().setLinked(true);
                }
            } else {
                if (PackageHelper.getTables(catalog).size() == 0) {
                    tables = MetadataFillFactory.getDBInstance().fillTables(catalog, dm, null, tablePattern, tableType);
                } else {
                    MetadataFillFactory.getDBInstance().setLinked(false);
                    tables = MetadataFillFactory.getDBInstance().fillTables(catalog, dm, null, tablePattern, tableType);
                    MetadataFillFactory.getDBInstance().setLinked(true);
                }
            }
            // TDQ-3816
            // TDQ-3566 ~
        } finally {
            ConnectionUtils.closeConnection(connection);
        }
        // ~
        // loadColumnSets(dataProvider, catalog, schema, tablePattern,
        // RelationalPackage.TD_TABLE, tables);
        return tables;
    }

    private static List<TdView> loadViews(Connection dataProvider, Catalog catalog, Schema schema, String viewPattern)
            throws Exception {
        List<TdView> views = new ArrayList<TdView>();
        // PTODO scorreia check return code
        TypedReturnCode<java.sql.Connection> rcConn = MetadataConnectionUtils.checkConnection((DatabaseConnection) dataProvider);
        if (!rcConn.isOk()) {
            log.error(rcConn.getMessage());
            throw new Exception(rcConn.getMessage());
        }

        java.sql.Connection connection = rcConn.getObject();
        DatabaseConnection databaseConnection = (DatabaseConnection) dataProvider;
        DatabaseMetaData dm = ExtractMetaDataUtils.getDatabaseMetaData(connection, databaseConnection, false);
        try {
            // MOD msjian 2011-10-9 TDQ-3566: do not fill views after existing
            // MOD gdbu 2011-10-25 TDQ-3816 : If views exists, will no longer be added.(compare with views , not all
            // element)
            if (schema != null) {
                if (PackageHelper.getViews(schema).size() == 0) {
                    views = MetadataFillFactory.getDBInstance().fillViews(schema, dm, null, viewPattern);
                } else {
                    MetadataFillFactory.getDBInstance().setLinked(false);
                    views = MetadataFillFactory.getDBInstance().fillViews(schema, dm, null, viewPattern);
                    MetadataFillFactory.getDBInstance().setLinked(true);
                }
            } else {
                if (PackageHelper.getViews(catalog).size() == 0) {
                    views = MetadataFillFactory.getDBInstance().fillViews(catalog, dm, null, viewPattern);
                } else {
                    MetadataFillFactory.getDBInstance().setLinked(false);
                    views = MetadataFillFactory.getDBInstance().fillViews(catalog, dm, null, viewPattern);
                    MetadataFillFactory.getDBInstance().setLinked(true);
                }
            }
            // TDQ-3816
            // TDQ-3566 ~
        } finally {
            ConnectionUtils.closeConnection(connection);
        }
        // loadColumnSets(dataProvider, catalog, schema, viewPattern,
        // RelationalPackage.TD_VIEW, views);
        return views;
    }

    /**
     * Method "readFromFile".
     * 
     * @param file the file to read
     * @return the Data provider if found.
     * @deprecated use repository API or use resourceFileMap instead it
     */
    @Deprecated
    public static TypedReturnCode<Connection> readFromFile(IFile file) throws NoSuchElementException {
        TypedReturnCode<Connection> rc = new TypedReturnCode<Connection>();
        URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(), false);
        Resource r = ProxyRepositoryFactory.getInstance().getRepositoryFactoryFromProvider().getResourceManager().resourceSet
                .getResource(uri, true);
        Collection<Connection> tdDataProviders = ConnectionHelper.getTdDataProviders(r.getContents());
        if (tdDataProviders.isEmpty()) {
            rc.setReturnCode(
                    Messages.getString("DqRepositoryViewService.NoDataProviderFound", file.getFullPath().toString()), false); //$NON-NLS-1$
        } else if (tdDataProviders.size() > 1) {
            rc.setReturnCode(Messages.getString("DqRepositoryViewService.FoundTooManyDataProvider", tdDataProviders.size(), //$NON-NLS-1$
                    file.getFullPath().toString()), false);
        } else {
            Connection prov = tdDataProviders.iterator().next();
            rc.setObject(prov);
        }
        return rc;
    }

    /**
     * DOC bZhou Comment method "getAllRepositoryResourceObjects".
     * 
     * Use this method to get all repository view objects used in TDQ Repository.
     * 
     * @param withDeleted
     * @return
     * @throws PersistenceException
     */
    public static List<IRepositoryViewObject> getAllRepositoryResourceObjects(boolean withDeleted) throws PersistenceException {
        List<IRepositoryViewObject> allObjectList = new ArrayList<IRepositoryViewObject>();

        for (ERepositoryObjectType type : (ERepositoryObjectType[]) ERepositoryObjectType.values()) {
            if (type.isDQItemType() && type.isResourceItem()) {
                allObjectList.addAll(ProxyRepositoryFactory.getInstance().getAll(type, withDeleted));
            }
        }

        return allObjectList;
    }

    /**
     * DOC bZhou Comment method "buildElementName".
     * 
     * @param element
     * @return
     */
    public static String buildElementName(Property property) {

        String elementName = "Unknown Label";
        if (property != null) {
            elementName = property.getDisplayName() + " " + property.getVersion();
        }

        return elementName;
    }

    public static Boolean hasChildren(TdXmlElementType element) {
        XMLSchemaBuilder xmlScheBuilder = XMLSchemaBuilder.getSchemaBuilder(element.getOwnedDocument());
        return xmlScheBuilder.isLeafNode(element).isOk();
    }

    /**
     * ADD gdbu 2011-7-25 bug : 23220
     * 
     * DOC gdbu Comment method "isContainsTable".This method used to connect to database to check if this schema has
     * tables.
     * 
     * @param dataProvider
     * @param catalog
     * @param tablePattern
     * @return
     * @throws Exception
     */
    public static boolean isContainsTable(Connection dataProvider, Catalog catalog, String tablePattern) throws Exception {
        TypedReturnCode<java.sql.Connection> rcConn = MetadataConnectionUtils.checkConnection((DatabaseConnection) dataProvider);
        if (!rcConn.isOk()) {
            log.error(rcConn.getMessage());
            throw new Exception(rcConn.getMessage());
        }
        java.sql.Connection connection = rcConn.getObject();
        String[] tableType = new String[] { TableType.TABLE.toString() };
        DatabaseMetaData dbJDBCMetadata = null;
        if (dataProvider instanceof DatabaseConnection) {
            // String databaseType = ((DatabaseConnection) dataProvider).getDatabaseType();
            // EDatabaseTypeName dbType = EDatabaseTypeName.getTypeFromDbType(databaseType);
            // if (dbType == EDatabaseTypeName.TERADATA) {
            // IMetadataConnection metadataConnection = ConvertionHelper.convert(dataProvider);
            // //MOD by zshen use sql mode to get table is slow so avoid it.
            // metadataConnection.setSqlMode(false);
            // ExtractMetaDataUtils.metadataCon = metadataConnection;
            // }
            dbJDBCMetadata = ExtractMetaDataUtils.getDatabaseMetaData(connection, (DatabaseConnection) dataProvider, false);
        } else {
            TaggedValue taggedValue = TaggedValueHelper.getTaggedValue(TaggedValueHelper.DBTYPE, dataProvider.getTaggedValue());
            dbJDBCMetadata = ExtractMetaDataUtils.getDatabaseMetaData(connection,
                    taggedValue == null ? "default" : taggedValue.getValue());//$NON-NLS-1$
        }
        Package catalogOrSchema = PackageHelper.getCatalogOrSchema(catalog);
        ResultSet tables = dbJDBCMetadata.getTables(catalogOrSchema.getName(), null, tablePattern, tableType);
        // MOD msjian TDQ-1806: fixed "Too many connections"
        try {
            if (tables.next()) {
                return true;
            } else {
                return false;
            }
        } finally {
            if (!connection.isClosed()) {
                connection.close();
            }
        }
    }

    /**
     * ADD gdbu 2011-7-25 bug : 23220
     * 
     * DOC gdbu Comment method "isContainsTable".This method used to connect to database to check if this schema has
     * tables.
     * 
     * @param dataProvider
     * @param schema
     * @param tablePattern
     * @return
     * @throws Exception
     */
    public static boolean isContainsTable(Connection dataProvider, Schema schema, String tablePattern) throws Exception {
        TypedReturnCode<java.sql.Connection> rcConn = MetadataConnectionUtils.checkConnection((DatabaseConnection) dataProvider);
        if (!rcConn.isOk()) {
            log.error(rcConn.getMessage());
            throw new Exception(rcConn.getMessage());
        }
        java.sql.Connection connection = rcConn.getObject();
        String[] tableType = new String[] { TableType.TABLE.toString() };

        DatabaseMetaData dbJDBCMetadata = null;
        if (dataProvider instanceof DatabaseConnection) {
            // String databaseType = ((DatabaseConnection) dataProvider).getDatabaseType();
            // EDatabaseTypeName dbType = EDatabaseTypeName.getTypeFromDbType(databaseType);
            // if (dbType == EDatabaseTypeName.TERADATA) {
            // IMetadataConnection metadataConnection = ConvertionHelper.convert(dataProvider);
            // //MOD by zshen use sql mode to get table is slow so avoid it.
            // metadataConnection.setSqlMode(false);
            // ExtractMetaDataUtils.metadataCon = metadataConnection;
            // }
            dbJDBCMetadata = ExtractMetaDataUtils.getDatabaseMetaData(connection, (DatabaseConnection) dataProvider, false);
        } else {
            TaggedValue taggedValue = TaggedValueHelper.getTaggedValue(TaggedValueHelper.DBTYPE, dataProvider.getTaggedValue());
            dbJDBCMetadata = ExtractMetaDataUtils.getDatabaseMetaData(connection,
                    taggedValue == null ? "default" : taggedValue.getValue());//$NON-NLS-1$
        }
        Package catalogOrSchema = PackageHelper.getCatalogOrSchema(schema);

        Package parentCatalog = PackageHelper.getParentPackage(catalogOrSchema);
        String schemaPattern = catalogOrSchema.getName();
        String catalogName = parentCatalog == null ? null : parentCatalog.getName();

        ResultSet tables = dbJDBCMetadata.getTables(catalogName, schemaPattern, tablePattern, tableType);
        // MOD msjian TDQ-1806: fixed "Too many connections"
        try {
            if (tables.next()) {
                return true;
            } else {
                return false;
            }
        } finally {
            if (!connection.isClosed()) {
                connection.close();
            }
        }
    }

    /**
     * ADD gdbu 2011-7-25 bug : 23220
     * 
     * DOC gdbu Comment method "isContainsView". This method used to connect to database to check if this catalog has
     * views.
     * 
     * @param dataProvider
     * @param catalog
     * @param viewPattern
     * @return
     * @throws Exception
     */
    public static boolean isContainsView(Connection dataProvider, Catalog catalog, String viewPattern) throws Exception {
        TypedReturnCode<java.sql.Connection> rcConn = MetadataConnectionUtils.checkConnection((DatabaseConnection) dataProvider);
        if (!rcConn.isOk()) {
            log.error(rcConn.getMessage());
            throw new Exception(rcConn.getMessage());
        }
        java.sql.Connection connection = rcConn.getObject();
        String[] tableType = new String[] { TableType.VIEW.toString() };
        DatabaseMetaData dbJDBCMetadata = null;
        if (dataProvider instanceof DatabaseConnection) {
            dbJDBCMetadata = ExtractMetaDataUtils.getDatabaseMetaData(connection, (DatabaseConnection) dataProvider, false);
        } else {
            TaggedValue taggedValue = TaggedValueHelper.getTaggedValue(TaggedValueHelper.DBTYPE, dataProvider.getTaggedValue());
            dbJDBCMetadata = ExtractMetaDataUtils.getDatabaseMetaData(connection,
                    taggedValue == null ? "default" : taggedValue.getValue());//$NON-NLS-1$
        }
        Package catalogOrSchema = PackageHelper.getCatalogOrSchema(catalog);
        ResultSet tables = dbJDBCMetadata.getTables(catalogOrSchema.getName(), null, viewPattern, tableType);
        // MOD msjian TDQ-1806: fixed "Too many connections"
        try {
            if (tables.next()) {
                return true;
            } else {
                return false;
            }
        } finally {
            if (!connection.isClosed()) {
                connection.close();
            }
        }
    }

    /**
     * ADD gdbu 2011-7-25 bug : 23220
     * 
     * DOC gdbu Comment method "isContainsView".This method used to connect to database to check if this schema has
     * views.
     * 
     * @param dataProvider
     * @param schema
     * @param viewPattern
     * @return
     * @throws Exception
     */
    public static boolean isContainsView(Connection dataProvider, Schema schema, String viewPattern) throws Exception {
        TypedReturnCode<java.sql.Connection> rcConn = MetadataConnectionUtils.checkConnection((DatabaseConnection) dataProvider);
        if (!rcConn.isOk()) {
            log.error(rcConn.getMessage());
            throw new Exception(rcConn.getMessage());
        }
        java.sql.Connection connection = rcConn.getObject();
        String[] tableType = new String[] { TableType.VIEW.toString() };
        DatabaseMetaData dbJDBCMetadata = null;
        if (dataProvider instanceof DatabaseConnection) {
            // String databaseType = ((DatabaseConnection) dataProvider).getDatabaseType();
            // EDatabaseTypeName dbType = EDatabaseTypeName.getTypeFromDbType(databaseType);
            // if (dbType == EDatabaseTypeName.TERADATA) {
            // IMetadataConnection metadataConnection = ConvertionHelper.convert(dataProvider);
            // //MOD by zshen use sql mode to get table is slow so avoid it.
            // metadataConnection.setSqlMode(false);
            // ExtractMetaDataUtils.metadataCon = metadataConnection;
            // }
            dbJDBCMetadata = ExtractMetaDataUtils.getDatabaseMetaData(connection, (DatabaseConnection) dataProvider, false);
        } else {
            TaggedValue taggedValue = TaggedValueHelper.getTaggedValue(TaggedValueHelper.DBTYPE, dataProvider.getTaggedValue());
            dbJDBCMetadata = ExtractMetaDataUtils.getDatabaseMetaData(connection,
                    taggedValue == null ? "default" : taggedValue.getValue());//$NON-NLS-1$
        }
        // DatabaseMetaData dbJDBCMetadata = ExtractMetaDataUtils.getDatabaseMetaData(connection,
        // ((DatabaseConnection) dataProvider).getDatabaseType(),((DatabaseConnection)
        // dataProvider).isSQLMode(),((DatabaseConnection) dataProvider).getSID());
        Package catalogOrSchema = PackageHelper.getCatalogOrSchema(schema);

        Package parentCatalog = PackageHelper.getParentPackage(catalogOrSchema);
        String schemaPattern = catalogOrSchema.getName();
        String catalogName = parentCatalog == null ? null : parentCatalog.getName();

        ResultSet tables = dbJDBCMetadata.getTables(catalogName, schemaPattern, viewPattern, tableType);
        // MOD msjian TDQ-1806: fixed "Too many connections"
        try {
            if (tables.next()) {
                return true;
            } else {
                return false;
            }
        } finally {
            if (!connection.isClosed()) {
                connection.close();
            }
        }
    }
}
