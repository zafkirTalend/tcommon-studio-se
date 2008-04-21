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
package org.talend.commons.emf;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;

/**
 * @author scorreia
 * 
 * Helper on resources.
 */
public class ResourceHelper {

    /**
     * Method "getUUID".
     * 
     * @param object a EMF object
     * @return the universal id as stored in the resource or null if not found.
     */
    public static String getUUID(EObject object) {
        if (object == null) {
            return null;
        }
        Resource resource = object.eResource();
        if (resource == null || !(resource instanceof XMLResource)) {
            return null;
        }
        XMLResource xmlResource = (XMLResource) resource;
        return xmlResource.getID(object);
    }
}
