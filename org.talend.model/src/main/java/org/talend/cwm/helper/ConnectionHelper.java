// ============================================================================
//
// Copyright (C) 2006-2010 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.cwm.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.talend.core.model.metadata.builder.connection.Connection;
import org.talend.core.model.metadata.builder.connection.ConnectionFactory;
import org.talend.core.model.metadata.builder.connection.DatabaseConnection;
import org.talend.core.model.metadata.builder.connection.MDMConnection;
import org.talend.core.model.metadata.builder.connection.MetadataTable;
import org.talend.cwm.relational.TdColumn;
import org.talend.cwm.softwaredeployment.TdSoftwareSystem;
import org.talend.cwm.xml.TdXmlElementType;
import org.talend.cwm.xml.TdXmlSchema;
import org.talend.utils.security.CryptoHelper;
import orgomg.cwm.foundation.softwaredeployment.Component;
import orgomg.cwm.objectmodel.core.ModelElement;
import orgomg.cwm.objectmodel.core.Namespace;
import orgomg.cwm.objectmodel.core.Package;
import orgomg.cwm.objectmodel.core.TaggedValue;
import orgomg.cwm.resource.relational.Catalog;
import orgomg.cwm.resource.relational.ColumnSet;
import orgomg.cwm.resource.relational.Schema;

/**
 * @author scorreia
 * 
 * Utility class for data provider handling.
 */
public class ConnectionHelper {

    public static final String PASSPHRASE = "99ZwBDt1L9yMX2ApJx fnv94o99OeHbCGuIHTy22 V9O6cZ2i374fVjdV76VX9g49DG1r3n90hT5c1"; //$NON-NLS-1$

    private static Logger log = Logger.getLogger(ConnectionHelper.class);

    /**
     * Method "createTdDataProvider" creates a data provider with the given name.
     * 
     * @param name the name of the data provider (could be null)
     * @return the created data provider.
     */
    public static DatabaseConnection createDatabaseConnection(String name) {
        DatabaseConnection provider = ConnectionFactory.eINSTANCE.createDatabaseConnection();
        provider.setName(name);
        return provider;
    }

    /**
     * Method create MDM connection
     * 
     * @param name
     * @return
     */
    public static MDMConnection createMDMConnection(String name) {
        MDMConnection provider = ConnectionFactory.eINSTANCE.createMDMConnection();
        provider.setName(name);
        return provider;
    }

    public static Connection getTdDataProvider(Package catalog) {
        Collection<Connection> tdDataProviders = DataProviderHelper.getTdDataProviders(catalog.getDataManager());
        if ((tdDataProviders.isEmpty()) || (tdDataProviders.size() > 1)) {
            // check whether given object is a schema contained in a catalog
            Namespace cat = catalog.getNamespace();
            if (cat != null) {
                Catalog realCatalog = SwitchHelpers.CATALOG_SWITCH.doSwitch(cat);
                if (realCatalog != null) {
                    return getTdDataProvider(realCatalog);
                }
            }
            return null;
        }
        // else
        return tdDataProviders.iterator().next();
    }

    /**
     * Method "getTdDataProvider".
     * 
     * @param column
     * @return the data provider or null
     */
    public static DatabaseConnection getTdDataProvider(TdColumn column) {
        ColumnSet columnSetOwner = ColumnHelper.getColumnOwnerAsColumnSet(column);
        if (columnSetOwner == null) {
            return null;
        }
        return getDataProvider(columnSetOwner);
    }

    /**
     * Method "getDataProvider".
     * 
     * @param columnSetOwner
     * @return the data provider or null
     */
    public static DatabaseConnection getDataProvider(ColumnSet columnSetOwner) {
        Namespace schemaOrCatalog = columnSetOwner.getNamespace();
        if (schemaOrCatalog == null) {
            return null;
        }
        Schema schema = SwitchHelpers.SCHEMA_SWITCH.doSwitch(schemaOrCatalog);
        if (schema != null) {
            return (DatabaseConnection) getTdDataProvider(schema);
        }
        // else
        Catalog catalog = SwitchHelpers.CATALOG_SWITCH.doSwitch(schemaOrCatalog);
        if (catalog != null) {
            return (DatabaseConnection) getTdDataProvider(catalog);
        }
        // else
        return null;
    }

    /**
     * Method "getTdDataProviders".
     * 
     * @param objects a collection of objects
     * @return the subset of objects containing only the TdDataProviders.
     */
    public static Collection<Connection> getTdDataProviders(Collection<? extends EObject> objects) {
        Collection<Connection> list = new ArrayList<Connection>();
        getTdDataProvider(objects, list);
        return list;
    }

    /**
     * Method "getTdDataProvider" adds the TdDataProviders found in the objects collection into the resultingCollection.
     * 
     * @param objects collection in which to search for TdDataProviders (must not be null)
     * @param resultingCollection the collection in which the TdDataProviders are added (must not be null).
     * @return true if resulting collection is not empty.
     */
    public static boolean getTdDataProvider(Collection<? extends EObject> objects, Collection<Connection> resultingCollection) {

        assert objects != null;
        assert resultingCollection != null;
        for (EObject object : objects) {
            Connection dataProv = SwitchHelpers.CONNECTION_SWITCH.doSwitch(object);
            if (dataProv != null) {
                resultingCollection.add(dataProv);
            }
        }
        return !resultingCollection.isEmpty();
    }

    /**
     * Method "setTechnicalName".
     * 
     * @param dataProvider the data provider
     * @param technicalName the technical name of the given data provider
     * @return true if the technical name was not set before.
     */
    public static boolean setTechnicalName(DatabaseConnection dataProvider, String technicalName) {
        return TaggedValueHelper.setTaggedValue(dataProvider, TaggedValueHelper.TECH_NAME_TAGGED_VAL, technicalName);
    }

    /**
     * Method "getTechnicalName".
     * 
     * @param element a cwm element
     * @return the technical name of the element (or null if none)
     */
    public static String getTechnicalName(ModelElement element) {
        TaggedValue taggedValue = TaggedValueHelper.getTaggedValue(TaggedValueHelper.TECH_NAME_TAGGED_VAL, element
                .getTaggedValue());
        if (taggedValue == null) {
            return "";
        }
        return taggedValue.getValue();
    }

    /**
     * Method "setIdentifierQuoteString" sets a comment on the given element.
     * 
     * @param identifierQuoteString the quote to set
     * @param dataProvider the data provider
     * @return true if the value was not set before.
     */
    public static boolean setIdentifierQuoteString(String identifierQuoteString, Connection dataProvider) {
        return TaggedValueHelper
                .setTaggedValue(dataProvider, TaggedValueHelper.DB_IDENTIFIER_QUOTE_STRING, identifierQuoteString);
    }

    /**
     * Method "getIdentifierQuoteString".
     * 
     * @param dataProvider
     * @return the identifier quote string
     */
    public static String getIdentifierQuoteString(Connection dataProvider) {
        TaggedValue taggedValue = TaggedValueHelper.getTaggedValue(TaggedValueHelper.DB_IDENTIFIER_QUOTE_STRING, dataProvider
                .getTaggedValue());
        if (taggedValue == null) {
            return "";
        }
        return taggedValue.getValue();
    }

    /**
     * Method "getDatabaseConnection" returns the data provider when the catalog (or schema) is associated to only one
     * data provider. It returns null if there is no data provider or more than one data provider.
     * 
     * @param catalog the catalog or schema
     * @return the associated data provider or null
     */
    public static Connection getConnection(Package thePackage) {
        Collection<Connection> tdDatabaseConnections = ConnectionHelper.getConnections(thePackage.getDataManager());
        if ((tdDatabaseConnections.isEmpty()) || (tdDatabaseConnections.size() > 1)) {
            // check whether given object is a schema contained in a catalog
            Namespace cat = thePackage.getNamespace();
            if (cat != null) {
                Package realCatalog = SwitchHelpers.PACKAGE_SWITCH.doSwitch(cat);
                if (realCatalog != null) {
                    return getConnection(realCatalog);
                }
            } // else no package is owner so return null
            return null;
        } // else we have go the connection so return it
        return tdDatabaseConnections.iterator().next();
    }

    /**
     * DOC xqliu Comment method "getDatabaseConnection".
     * 
     * @param xmlElement
     * @return
     */
    public static Connection getConnection(TdXmlElementType xmlElement) {
        return getConnection(xmlElement.getOwnedDocument());
    }

    /**
     * Method "getDatabaseConnection".
     * 
     * @param column
     * @return the data provider or null
     */
    public static Connection getConnection(TdColumn column) {
        ColumnSet columnSetOwner = ColumnHelper.getColumnOwnerAsColumnSet(column);
        if (columnSetOwner == null) {
            return null;
        }
        return getConnection(columnSetOwner);
    }

    /**
     * Method "getDatabaseConnection".
     * 
     * @param columnSetOwner
     * @return the data provider or null
     */
    public static Connection getConnection(ColumnSet columnSetOwner) {
        Namespace schemaOrCatalog = columnSetOwner.getNamespace();
        if (schemaOrCatalog == null) {
            return null;
        }
        Package thePackage = SwitchHelpers.PACKAGE_SWITCH.doSwitch(schemaOrCatalog);
        if (thePackage != null) {
            return getConnection(thePackage);
        } // else
        return null;
    }

    /**
     * Method "getDatabaseConnections".
     * 
     * @param objects a collection of objects
     * @return the subset of objects containing only the DatabaseConnections.
     */
    public static Collection<Connection> getConnections(Collection<? extends EObject> objects) {
        Collection<Connection> list = new ArrayList<Connection>();
        getConnection(objects, list);
        return list;
    }

    /**
     * Method "getDatabaseConnection" adds the DatabaseConnections found in the objects collection into the
     * resultingCollection.
     * 
     * @param objects collection in which to search for DatabaseConnections (must not be null)
     * @param resultingCollection the collection in which the DatabaseConnections are added (must not be null).
     * @return true if resulting collection is not empty.
     */
    public static boolean getConnection(Collection<? extends EObject> objects, Collection<Connection> resultingCollection) {
        assert objects != null;
        assert resultingCollection != null;
        for (EObject object : objects) {
            Connection dataProv = SwitchHelpers.CONNECTION_SWITCH.doSwitch(object);
            if (dataProv != null) {
                resultingCollection.add(dataProv);
            }
        }
        return !resultingCollection.isEmpty();
    }

    public static boolean addCatalogs(Collection<Catalog> catalogs, Connection dataProvider) {
        return addPackages(catalogs, dataProvider);
    }

    public static boolean addCatalog(Catalog catalog, Connection dataProvider) {
        return addPackage(catalog, dataProvider);
    }

    public static boolean addSchemas(Collection<Schema> schemas, Connection dataProvider) {
        return addPackages(schemas, dataProvider);
    }

    public static boolean addSchema(Schema schema, Connection dataProvider) {
        return addPackage(schema, dataProvider);
    }

    // MOD mzhao feature 10238
    public static boolean addXMLDocuments(Collection<TdXmlSchema> xmlDocuments, Connection dataProvider) {
        return addPackages(xmlDocuments, dataProvider);
    }

    public static TdSoftwareSystem getSoftwareSystem(Connection dataProvider) {
        final Component component = dataProvider.getComponent();
        if (component != null) {
            final Namespace namespace = component.getNamespace();
            if (namespace != null) {
                final TdSoftwareSystem softwareSystem = SwitchHelpers.TDSOFTWARE_SYSTEM_SWITCH.doSwitch(namespace);
                return softwareSystem;
            }
        }
        return null;
    }

    /**
     * Method "setSoftwareSystem" sets the relation between the dataprovider and the software system.
     * 
     * @param dataProvider (must not be null)
     * @param softwareSystem (must not be null)
     * @return true if the link between the data provider and the software system is set
     */
    public static boolean setSoftwareSystem(Connection dataProvider, TdSoftwareSystem softwareSystem) {
        assert softwareSystem != null;
        final EList<ModelElement> ownedElements = softwareSystem.getOwnedElement();
        for (ModelElement modelElement : ownedElements) {
            if (modelElement != null) {
                Component component = SwitchHelpers.COMPONENT_SWITCH.doSwitch(modelElement);
                if (component != null) {
                    dataProvider.setComponent(component);
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean addPackages(Collection<? extends Package> packages, Connection dataProvider) {
        boolean added = false;
        if ((dataProvider != null) && (packages != null)) {
            List<Package> packageList = dataProvider.getDataPackage();
            if (packageList != null && packageList.size() > 0) {
                packageList.clear();
            }

            Resource eResource = dataProvider.eResource();
            if (eResource != null) {
                eResource.getContents().addAll(packages);
            }

            added = packageList.addAll(packages);
        }
        return added;
    }

    /**
     * add the give package to the Conneciton. You may directly use Connection.getDataPackage.add()
     * 
     * @param pack the package to be added (never null)
     * @param connection the connection to own the Package (never null)
     * @return true if add was suscesful
     */
    public static boolean addPackage(Package pack, Connection connection) {
        boolean added = false;
        if ((connection != null) && (pack != null)) {
            added = connection.getDataPackage().add(pack);
        }
        return added;
    }

    /**
     * Method "getCatalogs".
     * 
     * @param dataProvider the data provider
     * @return the catalogs contained in the data provider
     */
    public static List<Catalog> getCatalogs(Connection dataProvider) {
        return CatalogHelper.getCatalogs(dataProvider.getDataPackage());
    }

    /**
     * Method "getSchema".
     * 
     * @param dataProvider the data provider
     * @return the schemas contained in the data provider
     */
    public static List<Schema> getSchema(Connection dataProvider) {
        return SchemaHelper.getSchemas(dataProvider.getDataPackage());
    }

    /**
     * 
     * DOC mzhao Comment method "getTdXmlDocument".
     * 
     * @param dataProvider
     * @return
     */
    public static List<TdXmlSchema> getTdXmlDocument(Connection dataProvider) {
        return XmlSchemaHelper.getDocuments(dataProvider.getDataPackage());
    }

    /**
     * DOC xqliu Comment method "getUniverse".
     * 
     * @param element
     * @return
     */
    public static String getUniverse(MDMConnection element) {
        TaggedValue taggedValue = TaggedValueHelper.getTaggedValue(TaggedValueHelper.UNIVERSE, element.getTaggedValue());
        if (taggedValue == null) {
            return "";
        }
        return taggedValue.getValue();
    }

    /**
     * DOC zshen Comment method "getDataFilter".
     * 
     * @param element
     * @return
     */
    public static String getDataFilter(Connection element) {
        TaggedValue taggedValue = TaggedValueHelper.getTaggedValue(TaggedValueHelper.DATA_FILTER, element.getTaggedValue());
        if (taggedValue == null) {
            return "";
        }
        return taggedValue.getValue();
    }

    /**
     * DOC xqliu Comment method "setUniverse".
     * 
     * @param universe
     * @param element
     */
    public static void setUniverse(String universe, MDMConnection element) {
        TaggedValueHelper.setTaggedValue(element, TaggedValueHelper.UNIVERSE, universe);
    }

    /**
     * DOC xqliu Comment method "getUniverse".
     * 
     * @param element
     * @return
     */
    public static String getUniverse(Connection element) {
        MDMConnection mdmConnection = SwitchHelpers.MDMCONNECTION_SWITCH.doSwitch(element);
        if (mdmConnection != null) {
            return getUniverse(mdmConnection);
        }
        return "";
    }

    /**
     * DOC xqliu Comment method "setUniverse".
     * 
     * @param universe
     * @param element
     */
    public static void setUniverse(String universe, Connection element) {
        MDMConnection mdmConnection = SwitchHelpers.MDMCONNECTION_SWITCH.doSwitch(element);
        if (mdmConnection != null) {
            setUniverse(universe, mdmConnection);
        }
    }

    // MOD klliu 2010-10-09 feature 15821
    /**
     * 
     * DOC klliu Comment method "getOtherParameter".
     * 
     * @return
     */
    public static String getOtherParameter(ModelElement element) {
        TaggedValue tv = TaggedValueHelper.getTaggedValue(TaggedValueHelper.OTHER_PARAMETER, element.getTaggedValue());
        if (tv == null) {
            return "";
        }
        return tv.getValue();
    }

    /**
     * 
     * DOC klliu Comment method "setOtherParameter".
     * 
     * @param otherParameter
     */
    public static void setOtherParameter(String otherParameter, ModelElement element) {
        TaggedValueHelper.setTaggedValue(element, TaggedValueHelper.OTHER_PARAMETER, otherParameter);
    }
    /**
     * DOC xqliu Comment method "setRetrieveAllMetadata". ADD xqliu 2010-03-03 feature 11412
     * 
     * @param retrieveAllMetadata
     * @param element
     */
    public static void setRetrieveAllMetadata(boolean retrieveAllMetadata, ModelElement element) {
        TaggedValueHelper.setTaggedValue(element, TaggedValueHelper.RETRIEVE_ALL, String.valueOf(retrieveAllMetadata));
    }

    /**
     * DOC xqliu Comment method "getRetrieveAllMetadata". ADD xqliu 2010-03-03 feature 11412
     * 
     * @param element
     * @return
     */
    public static boolean getRetrieveAllMetadata(ModelElement element) {
        TaggedValue taggedValue = TaggedValueHelper.getTaggedValue(TaggedValueHelper.RETRIEVE_ALL, element.getTaggedValue());
        if (taggedValue == null) {
            return true;
        }
        return Boolean.valueOf(taggedValue.getValue());
    }

    /**
     * return a set of all MetadataTable linked to this connection by inspecting through all the connection Package and
     * sub-packages
     * 
     * @param connection the connection to find the related table
     * @return a set of tables.
     */
    public static Set<MetadataTable> getTables(Connection connection) {
        HashSet<MetadataTable> result = new HashSet<MetadataTable>();
        EList<Package> packages = connection.getDataPackage();
        for (Package pack : packages) {
            PackageHelper.getAllTables(pack, result);
        }
        return result;
    }

    public static List<MetadataTable> getTablesWithOrders(Connection connection) {
        ArrayList<MetadataTable> result = new ArrayList<MetadataTable>();
        EList<Package> packages = connection.getDataPackage();
        for (Package pack : packages) {
            PackageHelper.getAllTablesWithOrders(pack, result);
        }
        return result;
    }

    /**
     * return the list of schemas related to a Connectio, it is look for direct Schema and all the potential Schema
     * owned by a Schema.
     * 
     * @param connection, the connection to look for schemas
     * @return Set of unique Schemas related to the connection
     */
    public static Set<Schema> getAllSchemas(Connection connection) {
        return getAllDataPackages(connection, Schema.class);
    }

    /**
     * return the list of Catalogs related to a Connectio, it is look for direct Catalog and all the potential Catalog
     * owned by a Catalog.
     * 
     * @param connection, the connection to look for Catalogs
     * @return Set of unique Catalogs related to the connection
     */
    public static Set<Catalog> getAllCatalogs(Connection connection) {
        return getAllDataPackages(connection, Catalog.class);
    }

    // hywang
    public static Package getPackage(String name, Connection connection, Class theClass) {
        Set<Package> allCatalogs = getAllDataPackages(connection, theClass);
        Iterator<Package> it = allCatalogs.iterator();
        while (it.hasNext()) {
            Package current = it.next();
            if (current.getName() != null && current.getName().equals(name)) {
                return current;
            }
        }
        return null;
    }

    /**
     * get all the packages and their sub(owned) packages of the connection.
     * 
     * @param connection the connection that refers to the packages
     * @return the list of TdTables found in the given list (never null, but can be empty).
     */
    @SuppressWarnings("unchecked")
    public static <T extends Package> Set<T> getAllDataPackages(Connection connection, Class<T> theClass) {
        assert connection != null;
        assert theClass != null;
        Set<T> result = new HashSet<T>(connection.getDataPackage().size());
        for (Package pack : connection.getDataPackage()) {
            if (pack != null && theClass.isAssignableFrom(pack.getClass())) {
                result.add((T) pack);
                // get all packages owned by this package
                result.addAll(PackageHelper.getOwnedElements(pack, theClass));
            }
        }
        return result;
    }

    /**
     * DOC xqliu Comment method "getDecryptPassword".
     * 
     * @param password
     * @return
     */
    public static String getDecryptPassword(String password) {
        CryptoHelper cryptoHelper = new CryptoHelper(ConnectionHelper.PASSPHRASE);
        return cryptoHelper.decrypt(password);
    }

    /**
     * DOC xqliu Comment method "getEncryptPassword".
     * 
     * @param password
     * @return
     */
    public static String getEncryptPassword(String password) {
        CryptoHelper cryptoHelper = new CryptoHelper(ConnectionHelper.PASSPHRASE);
        return cryptoHelper.encrypt(password);
    }

}
