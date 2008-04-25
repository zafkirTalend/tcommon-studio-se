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
package org.talend.cwm.dependencies;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.talend.commons.emf.EMFUtil;
import org.talend.cwm.helper.ModelElementHelper;
import org.talend.dataquality.analysis.Analysis;
import org.talend.utils.sugars.TypedReturnCode;
import orgomg.cwm.foundation.softwaredeployment.DataManager;
import orgomg.cwm.objectmodel.core.Dependency;
import orgomg.cwm.objectmodel.core.ModelElement;

/**
 * @author scorreia
 * 
 * A singleton class to handle dependencies between objects.
 */
public final class DependenciesHandler {

    private static Logger log = Logger.getLogger(DependenciesHandler.class);

    /**
     * As specified in CWM document at p. 67, the dependency kind can be of two types "Usage" or "Abstraction", but can
     * also be of other types.
     */
    public static final String USAGE = "Usage";

    public static final String ANALYSIS_DATAPROVIDER = "ANALYSIS_DATAPROVIDER";

    public static final String REPORT_ANALYSIS = "REPORT_ANALYSIS";

    private static DependenciesHandler instance;

    /**
     * workspace relative path to the default file.
     */
    private static final String FILENAME = ".TDP_dependencies.core";

    private static final String PATH_NAME = "/Libraries/" + FILENAME;

    private final Resource dependencyResource;

    private DependenciesHandler() {
        this.dependencyResource = loadFromFile(PATH_NAME);
    }

    private Resource loadFromFile(String path) {
        EMFUtil util = new EMFUtil();
        Resource resource = null;
        URI uri = URI.createPlatformResourceURI(path, false);
        try { // load from plugin path
            resource = util.getResourceSet().getResource(uri, true);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
        }

        if (resource == null) {
            // try to create it
            try {
                resource = util.getResourceSet().createResource(uri);
            } catch (RuntimeException e) {
                log.error(e.getMessage(), e);
            }
        }
        if (resource == null) {
            // try to load from a local file
            resource = util.getResourceSet().getResource(URI.createFileURI(".." + File.separator + path), true);
        }
        if (resource == null) {
            log.error("No resource found at " + path + " URI= " + uri);
            return null;
        }
        return resource;
    }

    public static DependenciesHandler getInstance() {
        if (instance == null) {
            instance = new DependenciesHandler();
        }
        return instance;
    }

    // scorreia: cannot save simply this resource, must save the dependent resources together
    // public boolean saveDependencies() {
    // return EMFUtil.saveResource(dependencyResource);
    // }

    /**
     * Method "getDependencies". This method can be used to add a new dependency.
     * 
     * @return the existing dependencies
     */
    private EList<EObject> getDependencies() {
        return dependencyResource.getContents();
    }

    /**
     * DOC scorreia Comment method "saveDependencies".
     * 
     * @param object
     */
    public boolean addDependency(Dependency object) {
        getDependencies().add(object);
        this.dependencyResource.setModified(true);
        return true;
    }

    /**
     * Method "removeDependency".
     * 
     * @param dependency the dependency to remove
     * @return the list of modified resources. These resources should be saved together with a ResourceSet then.
     */
    public List<Resource> removeDependency(Dependency dependency) {
        List<Resource> modifiedResources = new ArrayList<Resource>();
        Resource res = dependency.eResource();
        if (res != null) {
            modifiedResources.add(res);
        }
        EList<ModelElement> clients = dependency.getClient();
        for (ModelElement client : clients) {
            Resource resource = client.eResource();
            if (resource != null) {
                modifiedResources.add(resource);
            }
        }
        EList<ModelElement> suppliers = dependency.getSupplier();
        for (ModelElement supplier : suppliers) {
            Resource resource = supplier.eResource();
            if (resource != null) {
                modifiedResources.add(resource);
            }
        }

        getDependencies().remove(dependency);
        return modifiedResources;
    }

    public Resource getDependencyResource() {
        return this.dependencyResource;
    }

    /**
     * Method "createUsageDependencyOn".
     * 
     * Example Analysis depends on the exitence of a DataProvider. This method must be called
     * createUsageDependencyOn(Analysis, DataProvider). The created dependency is added to the DataProvider in its
     * "client dependencies" and to the Analysis in its "supplier dependencies". See OMG CWM spec paragraph 4.3.2.7.
     * 
     * @param kind the kind of dependency
     * @param clientElement the element that requires the requiredElement
     * @param supplierElement the required element
     * @return the Dependency of clientElement on requiredElement
     */
    public Dependency createDependencyOn(String kind, ModelElement clientElement, ModelElement supplierElement) {
        Dependency dependency = ModelElementHelper.createDependencyOn(kind, clientElement, supplierElement);
        getDependencies().add(dependency);
        return dependency;
    }

    /**
     * Method "removeSupplierDependencies". The given element (supplier) is an element required by other elements (the
     * clients). This method gets all the dependencies that link the supplier to the clients. Then for each client, the
     * dependency toward the supplier is removed. And finally the list of dependencies that link the supplier to its
     * clients is suppressed.
     * 
     * @param supplierElement an element that is required by other elements
     * @return
     */
    public boolean removeSupplierDependencies(ModelElement supplierElement) {
        return ModelElementHelper.removeSupplierDependencies(supplierElement);
    }

    /**
     * Method "getDependencyBetween" the dependency that relates the supplier to the client. This method looks into the
     * list of dependencies of both the supplier and the client.
     * 
     * @param kind the kind of dependency looked for (could be null)
     * @param clientElement
     * @param supplierElement
     * @return the dependency that relates the supplier to the client or null if none is found.
     */
    public Dependency getDependencyBetween(String kind, ModelElement clientElement, ModelElement supplierElement) {
        return ModelElementHelper.getDependencyBetween(kind, clientElement, supplierElement);
    }

    /**
     * Method "createUsageDependencyOn".
     * 
     * @param clientElement the analysis that depends on the data provider.
     * @param dataManager the data provider
     * @return a true return code if the dependency has been correctly added to the resource of the supplier element.
     * Return false otherwise. In any case, the dependency is created and the getObject() method returns it.
     */
    public TypedReturnCode<Dependency> createUsageDependencyOn(Analysis clientElement, DataManager dataManager) {
        assert dataManager != null;
        Dependency dependency = createDependencyOn(ANALYSIS_DATAPROVIDER, clientElement, dataManager);
        TypedReturnCode<Dependency> rc = new TypedReturnCode<Dependency>();
        rc.setObject(dependency);
        return rc;
    }

    /**
     * Method "setDependencyOn" sets the dependency between the analysis and the data manager.
     * 
     * @param analysis
     * @param dataManager
     * @return a true return code if the dependency has been correctly added to the resource of the supplier element.
     * Return false otherwise. The dependency is created only if needed and the getObject() method returns it.
     */
    public TypedReturnCode<Dependency> setDependencyOn(Analysis analysis, DataManager dataManager) {
        Dependency dependency = getDependencyBetween(analysis, dataManager);
        if (dependency == null) {
            return createUsageDependencyOn(analysis, dataManager);
        }
        // else
        TypedReturnCode<Dependency> rc = new TypedReturnCode<Dependency>();
        rc.setObject(dependency);
        return rc;
    }

    /**
     * Method "getDependencyBetween".
     * 
     * @param clientElement
     * @param dataManager
     * @return the dependency between the given elements or null.
     */
    public Dependency getDependencyBetween(Analysis clientElement, DataManager dataManager) {
        return getDependencyBetween(ANALYSIS_DATAPROVIDER, clientElement, dataManager);
    }

}
