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

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.talend.cwm.util.FactoriesHelper;

/**
 * @author scorreia This class creates the EMF resources and save them.
 * 
 * TODO scorreia this class should be in a commons project (org.talend.commons.emf)
 */
public final class EMFUtil {

    private static Logger log = Logger.getLogger(EMFUtil.class);

    /** the encoding for xml files. */
    private static String encoding = "UTF-8";

    /** the options needed for saving the resources. */
    private Map<String, Object> options;

    private ResourceSet resourceSet;

    private String lastErrorMessage = null;

    /**
     * This CTOR initializes all packages and create a resource set.
     * 
     * @param fileExtensions the list of extensions (without the dot).
     */
    public EMFUtil() {
        // Initialize the enterprise packages
        FactoriesHelper.initializeAllFactories();

        // Register the XMI resource factory for the .enterprise extension
        Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;

        final Collection<String> fileExtensions = FactoriesHelper.getExtensions();
        Map<String, Object> m = reg.getExtensionToFactoryMap();
        for (String extension : fileExtensions) {
            m.put(extension, new XMIResourceFactoryImpl());
        }

        // set the options
        options = new HashMap<String, Object>();
        options.put(XMIResource.OPTION_DECLARE_XML, Boolean.TRUE);
        options.put(XMIResource.OPTION_ENCODING, encoding);

        // Obtain a new resource set
        resourceSet = new ResourceSetImpl();
    }

    /**
     * Creates a new Resource in the ResourceSet. The file will be actually written when the save() method will be
     * called.
     * 
     * @param uri the uri of the file in which the pool will be stored
     * @param eObject the pool that contains objects.
     * @return true (as per the general contract of the <tt>Collection.add</tt> method).
     */
    public boolean addPoolToResourceSet(String uri, EObject eObject) {
        Resource res = resourceSet.createResource(URI.createURI(uri));
        if (res == null) {
            lastErrorMessage = "No factory has been found for URI: " + uri;
            return false;
        }
        return res.getContents().add(eObject);
    }

    /**
     * saves each resource of the resource set.
     */
    public boolean save() {
        boolean ok = true;
        Iterator<Resource> r = resourceSet.getResources().iterator();
        while (r.hasNext()) {
            Resource ress = r.next();
            try {
                ress.save(options);
                if (log.isDebugEnabled()) {
                    log.debug("Resource saved in:" + ress.getURI());
                }
            } catch (IOException e) {
                log.error("Error during the saving of resource. Uri=" + ress.getURI().toString(), e);
                // possible cause is a missing factory initialization and filename extension.
                ok = false;
            }
        }
        return ok;
    }

    /**
     * @return the resource set (never null)
     */
    public ResourceSet getResourceSet() {
        return resourceSet;
    }

    /**
     * @param set the resource set (MUST not be null)
     */
    public void setResourceSet(ResourceSet set) {
        resourceSet = set;
    }

    /**
     * @return
     */
    public Map<String, Object> getOptions() {
        return options;
    }

    /**
     * Method "getLastErrorMessage".
     * 
     * @return the last error message or null when no error happened since the creation of this object.
     */
    public String getLastErrorMessage() {
        return this.lastErrorMessage;
    }

    /**
     * Changes the uri of the given resource. The new uri is formed with the name of the input resource's uri appended
     * to the path outputUri.
     * 
     * @param res the input resource
     * @param destinationUri the destination directory
     * @return the new uri.
     */
    public static URI changeUri(Resource res, URI destinationUri) {
        URI uri = res.getURI();
        URI newUri = destinationUri.appendSegment(uri.lastSegment());
        res.setURI(newUri);
        return newUri;
    }

}
