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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.resource.Resource;


/**
 * This class help the '.prv' file to store the corresponding DataProvider value.
 *
 */
public final class FileResourceMapHelper {
    
    private FileResourceMapHelper() {

    }

    private static Map<IFile, Resource> needStoreProviderMap = new HashMap<IFile, Resource>();

    public static void register(IFile file, Resource resource) {
        needStoreProviderMap.put(file, resource);
    }

    public static Resource get(IFile file) {
        return needStoreProviderMap.get(file);
    }
    
}
