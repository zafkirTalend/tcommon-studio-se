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
package org.talend.repository.utils;

import org.eclipse.emf.ecore.resource.Resource;
import org.talend.core.IService;
import orgomg.cwm.objectmodel.core.ModelElement;

/**
 * 
 * DOC mzhao Abstract unload resource service which can be extended by client.
 */
public class AbstractResourceChangesService implements IService {

    public void handleUnload(Resource toBeUnloadedResource) {
    }

    // Add new elements to resource, remove elements from resource, delete resource
    public boolean handleResourceChange(ModelElement modelElement) {
        return true;
    }

}
