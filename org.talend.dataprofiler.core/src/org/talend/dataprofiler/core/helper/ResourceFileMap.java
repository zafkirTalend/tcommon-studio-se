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
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.talend.commons.emf.EMFUtil;

/**
 * DOC rli class global comment. Detailled comment
 */
public class ResourceFileMap {

    private Map<IFile, Resource> registedResourceMap = new HashMap<IFile, Resource>();

//    private ResourceFileMapHelper instance = new ResourceFileMapHelper();

    public void register(IFile file, Resource resource) {
        registedResourceMap.put(file, resource);
    }

    public Resource get(IFile file) {
        return registedResourceMap.get(file);
    }

    /**
     * DOC zqin Comment method "getFileResource".
     * 
     * @param file
     * @return
     */
    protected Resource getFileResource(IFile file) {
        EMFUtil util = new EMFUtil();
        String path = file.getFullPath().toString();
        URI uri = URI.createPlatformResourceURI(path, true);
        ResourceSet rs = util.getResourceSet();
        Resource resource = rs.getResource(uri, true);
        return resource;
    }

}
