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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.talend.commons.emf.EMFUtil;
import org.talend.commons.emf.FactoriesUtil;
import org.talend.cwm.helper.CatalogHelper;
import org.talend.cwm.helper.DataProviderHelper;
import org.talend.cwm.relational.TdTable;
import org.talend.cwm.softwaredeployment.TdDataProvider;
import org.talend.utils.sugars.TypedReturnCode;
import orgomg.cwm.resource.relational.Catalog;

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

    public static List<TdTable> getTables(Catalog catalog, boolean loadFromDB) {
        if (loadFromDB) {
            return loadTables(catalog);
        } else {
            return CatalogHelper.getTables(catalog);
        }
    }

    /**
     * DOC scorreia Comment method "loadTables".
     * 
     * @param catalog
     * @return
     */
    private static List<TdTable> loadTables(Catalog catalog) {
        String schemaName = null;
        String catalogName;
        Connection connection;
        // TODO scorreia DatabaseContentRetriever.getTablesWithColumns(catalogName, schemaName, connection);
        return null;
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
