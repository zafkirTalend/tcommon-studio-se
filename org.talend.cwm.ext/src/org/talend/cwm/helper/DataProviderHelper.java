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
package org.talend.cwm.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.talend.cwm.relational.TdCatalog;
import org.talend.cwm.relational.TdSchema;
import org.talend.cwm.softwaredeployment.SoftwaredeploymentFactory;
import org.talend.cwm.softwaredeployment.TdDataProvider;
import org.talend.cwm.softwaredeployment.TdProviderConnection;
import org.talend.utils.sugars.TypedReturnCode;
import orgomg.cwm.foundation.softwaredeployment.ProviderConnection;
import orgomg.cwm.objectmodel.core.Package;

/**
 * @author scorreia
 * 
 * Utility class for data provider handling.
 */
public final class DataProviderHelper {

    private DataProviderHelper() {
    }

    /**
     * Method "createTdDataProvider" creates a data provider with the given name.
     * 
     * @param name the name of the data provider
     * @return the created data provider.
     */
    public static TdDataProvider createTdDataProvider(String name) {
        TdDataProvider provider = SoftwaredeploymentFactory.eINSTANCE.createTdDataProvider();
        provider.setName(name);
        return provider;
    }

    /**
     * Method "getTdDataProviders".
     * 
     * @param objects a collection of objects
     * @return the subset of objects containing only the TdDataProviders.
     */
    public static Collection<TdDataProvider> getTdDataProviders(Collection<EObject> objects) {
        Collection<TdDataProvider> list = new ArrayList<TdDataProvider>();
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
    public static boolean getTdDataProvider(Collection<EObject> objects, Collection<TdDataProvider> resultingCollection) {
        assert objects != null;
        assert resultingCollection != null;
        for (EObject object : objects) {
            TdDataProvider dataProv = SwitchHelpers.TDDATAPROVIDER_SWITCH.doSwitch(object);
            if (dataProv != null) {
                resultingCollection.add(dataProv);
            }
        }
        return !resultingCollection.isEmpty();
    }

    public static boolean addCatalogs(Collection<TdCatalog> catalogs, TdDataProvider dataProvider) {
        return addPackages(catalogs, dataProvider);
    }

    public static boolean addCatalog(TdCatalog catalog, TdDataProvider dataProvider) {
        return addPackage(catalog, dataProvider);
    }

    public static boolean addSchemas(Collection<TdSchema> schemas, TdDataProvider dataProvider) {
        return addPackages(schemas, dataProvider);
    }

    public static boolean addSchema(TdSchema schema, TdDataProvider dataProvider) {
        return addPackage(schema, dataProvider);
    }

    public static boolean addPackages(Collection<? extends Package> packages, TdDataProvider dataProvider) {
        boolean added = false;
        if ((dataProvider != null) && (packages != null)) {
            added = dataProvider.getDataPackage().addAll(packages);
        }
        return added;
    }

    public static boolean addPackage(Package pack, TdDataProvider dataProvider) {
        boolean added = false;
        if ((dataProvider != null) && (pack != null)) {
            added = dataProvider.getDataPackage().add(pack);
        }
        return added;
    }

    /**
     * Method "addProviderConnection" adds the connection provider to the data provider.
     * 
     * @param providerConnection the provider connection
     * @param dataProvider the data provider
     * @return true if added
     */
    public static boolean addProviderConnection(TdProviderConnection providerConnection, TdDataProvider dataProvider) {
        boolean added = false;
        if ((dataProvider != null) && (providerConnection != null)) {
            // this relation is a reference to a contained object.
            added = dataProvider.getResourceConnection().add(providerConnection);
            // this relation is a reference to a non contained object.
            // dataProvider.getClientConnection().add(providerConnection);
        }
        return added;
    }

    /**
     * Method "getTdProviderConnections".
     * 
     * @param dataProvider must not be null
     * @return the list of provider connections.
     */
    public static List<TdProviderConnection> getTdProviderConnections(TdDataProvider dataProvider) {
        assert dataProvider != null;
        List<TdProviderConnection> tdProvConnections = new ArrayList<TdProviderConnection>();
        EList<ProviderConnection> resourceConnections = dataProvider.getResourceConnection();
        for (ProviderConnection providerConnection : resourceConnections) {
            TdProviderConnection tdProvConn = SwitchHelpers.TDPROVIDER_CONNECTION_SWITCH.doSwitch(providerConnection);
            if (tdProvConn != null) {
                tdProvConnections.add(tdProvConn);
            }
        }
        return tdProvConnections;
    }

    /**
     * Method "getTdProviderConnection".
     * 
     * @param dataProvider the data provider that contains connections.
     * @return the returned code is true only when there is only one provider connection in the list. When there is no
     * provider connection, the returned code is false, the object is null and an error message is set. When there are
     * several provider connections, the returned code is false, an error message is set, but the first connection is
     * returned by the method {@link TypedReturnCode#getObject()}.
     */
    public static TypedReturnCode<TdProviderConnection> getTdProviderConnection(TdDataProvider dataProvider) {
        assert dataProvider != null;
        TypedReturnCode<TdProviderConnection> rc = new TypedReturnCode<TdProviderConnection>(true);
        List<TdProviderConnection> resourceConnections = getTdProviderConnections(dataProvider);
        int nbConnections = resourceConnections.size();
        if (nbConnections == 0) {
            rc.setReturnCode("No connection found in Data provider " + dataProvider.getName(), false);
        } else {
            TdProviderConnection tdProvConn = resourceConnections.get(0);
            if (nbConnections > 1) {
                rc.setReturnCode("Warning: several connection found for this data provider " + dataProvider.getName()
                        + ". Returning the first one only.", false, tdProvConn);
            }
            // else got only one provider connection
            rc.setObject(tdProvConn);
        }
        return rc;
    }

    public static List<TdCatalog> getTdCatalogs(TdDataProvider dataProvider) {
        return CatalogHelper.getTdCatalogs(dataProvider.getDataPackage());
    }

}
