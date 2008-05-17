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
import org.talend.cwm.helper.ResourceHelper;
import org.talend.dataquality.analysis.Analysis;
import org.talend.dataquality.reports.TdReport;
import org.talend.utils.sugars.TypedReturnCode;
import orgomg.cwm.foundation.softwaredeployment.DataManager;
import orgomg.cwm.objectmodel.core.Dependency;
import orgomg.cwm.objectmodel.core.ModelElement;

/**
 * @author scorreia
 * 
 * A singleton class to handle dependencies between objects. PTODO scorreia clean code of this class.
 */
public final class DependenciesHandler {

    private static Logger log = Logger.getLogger(DependenciesHandler.class);

    /**
     * As specified in CWM document at p. 67, the dependency kind can be of two types "Usage" or "Abstraction", but can
     * also be of other types.
     */
    public static final String USAGE = "Usage";

    // public static final String ANALYSIS_DATAPROVIDER = "ANALYSIS_DATAPROVIDER";

    // public static final String REPORT_ANALYSIS = "REPORT_ANALYSIS";

    private static DependenciesHandler instance;

    /**
     * workspace relative path to the default file.
     */
    private static final String FILENAME = ".TDP_dependencies.core";

    private static final String PATH_NAME = "/Libraries/" + FILENAME;

    // private final Resource dependencyResource;

    private DependenciesHandler() {
        // this.dependencyResource = loadFromFile(PATH_NAME);
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
    // private EList<EObject> getDependencies() {
    // return dependencyResource.getContents();
    // }
    //
    // /**
    // * DOC scorreia Comment method "saveDependencies".
    // *
    // * @param object
    // */
    // boolean addDependency(Dependency object) {
    // getDependencies().add(object);
    // this.dependencyResource.setModified(true);
    // return true;
    // }
    /**
     * Method "removeDependency".
     * 
     * @param dependency the dependency to remove
     * @return the list of modified resources of the client dependencies. These resources should be saved together with
     * a ResourceSet then.
     */
    public List<Resource> removeClientDependencies(Dependency dependency) {
        List<Resource> modifiedResources = new ArrayList<Resource>();
        EList<ModelElement> clients = dependency.getClient();
        for (ModelElement client : clients) {
            Resource resource = client.eResource();
            if (resource != null) {
                modifiedResources.add(resource);
            }
        }
        clients.clear();
        // EList<ModelElement> suppliers = dependency.getSupplier();
        // for (ModelElement supplier : suppliers) {
        // Resource resource = supplier.eResource();
        // if (resource != null) {
        // modifiedResources.add(resource);
        // }
        // }

        // if (getDependencies().remove(dependency)) {
        // if (depResource != null) { // resource is modified
        // modifiedResources.add(depResource);
        // }
        // } else {
        // // try to remove it by id
        // CwmResource depRes = getDependencyResource() instanceof CwmResource ? (CwmResource) getDependencyResource() :
        // null;
        // if (depRes != null) {
        // String id = ResourceHelper.getUUID(dependency);
        // if (id == null) {
        // log.error("id is null for dependency " + dependency);
        // }
        // EObject depToRemove = depRes.getIDToEObjectMap().get(id);
        // if (depToRemove != null) {
        // if (getDependencies().remove(depToRemove)) {
        // modifiedResources.add(depRes);
        // } else {
        // log.error("Not found dependency with id: " + id);
        // }
        // }
        // }
        // }

        return modifiedResources;
    }

    // public Resource getDependencyResource() {
    // return this.dependencyResource;
    // }

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
    Dependency createDependencyOn(String kind, ModelElement clientElement, ModelElement supplierElement) {
        Dependency dependency = ModelElementHelper.createDependencyOn(kind, clientElement, supplierElement);
        Resource supplierResource = supplierElement.eResource();
        if (supplierResource != null) {
            supplierResource.getContents().add(dependency);
        }
        // getDependencies().add(dependency);
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
    boolean removeSupplierDependencies(ModelElement supplierElement) {
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
    Dependency getDependencyBetween(String kind, ModelElement clientElement, ModelElement supplierElement) {
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
    TypedReturnCode<Dependency> createUsageDependencyOn(ModelElement clientElement, ModelElement dataManager) {
        assert dataManager != null;
        Dependency dependency = createDependencyOn(USAGE, clientElement, dataManager);
        TypedReturnCode<Dependency> rc = new TypedReturnCode<Dependency>();
        rc.setObject(dependency);
        return rc;
    }

    // /**
    // * Method "createUsageDependencyOn".
    // *
    // * @param report the analysis that depends on the data provider.
    // * @param analysis the data provider
    // * @return a true return code if the dependency has been correctly added to the resource of the supplier element.
    // * Return false otherwise. In any case, the dependency is created and the getObject() method returns it.
    // */
    // TypedReturnCode<Dependency> createUsageDependencyOn(TdReport report, Analysis analysis) {
    // assert analysis != null;
    // Dependency dependency = createDependencyOn(USAGE, report, analysis);
    // TypedReturnCode<Dependency> rc = new TypedReturnCode<Dependency>();
    // rc.setObject(dependency);
    // return rc;
    // }

    /**
     * Method "setDependencyOn" sets the dependency between the analysis and the data manager.
     * 
     * @param analysis the analysis which requires the data manager
     * @param dataManager the data manager required by the analysis
     * @return a true return code if the dependency has been correctly added to the resource of the supplier element.
     * Return false otherwise. The dependency is created only if needed and the getObject() method returns it.
     */
    public TypedReturnCode<Dependency> setDependencyOn(Analysis analysis, DataManager dataManager) {
        return setUsageDependencyOn(analysis, dataManager);
    }

    public TypedReturnCode<Dependency> setUsageDependencyOn(ModelElement client, ModelElement supplier) {
        // get data manager usage dependencies
        EList<Dependency> supplierDependencies = supplier.getSupplierDependency();
        // search for analysis into them
        for (Dependency dependency : supplierDependencies) {
            if (USAGE.compareTo(dependency.getKind()) == 0) {
                // if exist return true with the dependency
                EObject resolvedObject = ResourceHelper.resolveObject(dependency.getClient(), client);
                if (resolvedObject == null) {
                    // if not add analysis to the dependency
                    dependency.getClient().add(client);
                }

                TypedReturnCode<Dependency> rc = new TypedReturnCode<Dependency>();
                rc.setObject(dependency);
                return rc;
            }
        }
        // if not exist create a usage dependency
        return createUsageDependencyOn(client, supplier);
    }

    /**
     * Method "setDependencyOn" sets the dependency between the report and the analysis.
     * 
     * @param report
     * @param analysis
     * @return a true return code if the dependency has been correctly added to the resource of the supplier element.
     * Return false otherwise. The dependency is created only if needed and the getObject() method returns it.
     */
    public TypedReturnCode<Dependency> setDependencyOn(TdReport report, Analysis analysis) {
        return setUsageDependencyOn(report, analysis);
    }

    /**
     * Method "getDependencyBetween".
     * 
     * @param clientElement
     * @param dataManager
     * @return the dependency between the given elements or null.
     */
    Dependency getDependencyBetween(Analysis clientElement, DataManager dataManager) {
        return getDependencyBetween(USAGE, clientElement, dataManager);
    }

    /**
     * Method "getDependencyBetween".
     * 
     * @param report
     * @param analysis
     * @return the dependency between the given elements or null.
     */
    Dependency getDependencyBetween(TdReport report, Analysis analysis) {
        return getDependencyBetween(USAGE, report, analysis);
    }

}
