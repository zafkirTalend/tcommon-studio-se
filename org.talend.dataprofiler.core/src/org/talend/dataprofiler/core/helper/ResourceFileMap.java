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

import java.io.File;
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

    protected boolean resourceChanged = true;

    // private ResourceFileMapHelper instance = new ResourceFileMapHelper();

    public void register(IFile file, Resource resource) {
        registedResourceMap.put(file, resource);
    }

    public void remove(IFile file) {
        this.registedResourceMap.remove(file);
    }

    /**
     * DOC zqin Comment method "getFileResource".
     * 
     * @param file
     * @return
     */
    public Resource getFileResource(IFile file) {
        Resource res = registedResourceMap.get(file);
        if (res != null) {
            return res;
        }
        EMFUtil util = new EMFUtil();
        String path = file.getFullPath().toString();
        URI uri = URI.createPlatformResourceURI(path, true);
        ResourceSet rs = util.getResourceSet();
        Resource resource = rs.getResource(uri, true);
        this.registedResourceMap.put(file, resource);
        return resource;
    }

    protected Resource getFileResource(File file) {
        EMFUtil util = new EMFUtil();
        URI uri = URI.createFileURI(file.getPath());
        ResourceSet rs = util.getResourceSet();
        Resource resource = rs.getResource(uri, true);
        return resource;
    }

    /**
     * Getter for resourceChanged.
     * 
     * @return the resourceChanged
     */
    public boolean isResourceChanged() {
        return resourceChanged;
    }

    /**
     * Sets the resourceChanged.
     * 
     * @param resourceChanged the resourceChanged to set
     */
    public void setResourceChanged(boolean resourceChanged) {
        this.resourceChanged = resourceChanged;
    }

}
