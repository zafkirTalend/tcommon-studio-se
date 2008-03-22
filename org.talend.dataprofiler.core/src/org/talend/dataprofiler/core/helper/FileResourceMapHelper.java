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
package org.talend.dataprofiler.core.helper;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.talend.commons.emf.EMFUtil;
import org.talend.cwm.helper.DataProviderHelper;
import org.talend.cwm.softwaredeployment.TdDataProvider;
import org.talend.utils.sugars.TypedReturnCode;


/**
 * This class help the '.prv' file to store the corresponding DataProvider value.
 *
 */
public final class FileResourceMapHelper {
    
    private FileResourceMapHelper() {

    }

    private static Map<IFile, Resource> registedResourceMap = new HashMap<IFile, Resource>();
    
    private static Map<IFile, TypedReturnCode<TdDataProvider>> providerMap = new HashMap<IFile, TypedReturnCode<TdDataProvider>>();

    public static void register(IFile file, Resource resource) {
        registedResourceMap.put(file, resource);
    }

    public static Resource get(IFile file) {
        return registedResourceMap.get(file);
    }
    

    
    /**
     * Method "readFromFile".
     * 
     * @param file the file to read
     * @return the Data provider if found.
     */
    public static TypedReturnCode<TdDataProvider> readFromFile(IFile file) {
        TypedReturnCode<TdDataProvider> rc = providerMap.get(file);
        if (rc != null) {
            return rc;
        }
        rc = new TypedReturnCode<TdDataProvider>();
        EMFUtil util = new EMFUtil();
        String path = file.getFullPath().toString();
        URI uri = URI.createPlatformResourceURI(path, true);
        ResourceSet rs = util.getResourceSet();
        Resource resource = rs.getResource(uri, true);
        FileResourceMapHelper.register(file, resource);
        Collection<TdDataProvider> tdDataProviders = DataProviderHelper.getTdDataProviders(resource.getContents());
        if (tdDataProviders.isEmpty()) {
            rc.setReturnCode("No Data Provider found in " + file.getFullPath(), false);
        }
        if (tdDataProviders.size() > 1) {
            rc.setReturnCode("Found too many DataProvider (" + tdDataProviders.size() + ") in file " + file.getFullPath(), false);
        }
        TdDataProvider prov = tdDataProviders.iterator().next();
        rc.setObject(prov);
        providerMap.put(file, rc);
        return rc;
    }
    
}
