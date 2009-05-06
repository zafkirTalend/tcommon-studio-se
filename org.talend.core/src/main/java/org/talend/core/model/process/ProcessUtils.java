// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.model.process;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.exception.PersistenceException;
import org.talend.core.CorePlugin;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.PluginChecker;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.JobletProcessItem;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.properties.Property;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryObject;
import org.talend.core.ui.IJobletProviderService;
import org.talend.designer.core.IDesignerCoreService;
import org.talend.designer.core.model.utils.emf.talendfile.ContextParameterType;
import org.talend.designer.core.model.utils.emf.talendfile.ContextType;
import org.talend.designer.core.model.utils.emf.talendfile.ProcessType;
import org.talend.repository.model.IProxyRepositoryFactory;

/**
 * DOC bqian class global comment. Detailled comment
 */
public class ProcessUtils {

    public static Collection<IRepositoryObject> getProcessDependencies(ERepositoryObjectType dependencyType,
            Collection<Item> items) {

        switch (dependencyType) {
        case CONTEXT:
            return getContextDependenciesOfProcess(items);
        case METADATA:
            return getMetadataDependenciesOfProcess(items);
        case PROCESS:
            return getChildPorcessDependenciesOfProcess(items);
        case JOBLET:
            return getJobletDependenciesOfProcess(items);
        default:
            return Collections.EMPTY_LIST;
        }

    }

    private static Collection<IRepositoryObject> getContextDependenciesOfProcess(Collection<Item> items) {
        Collection<IRepositoryObject> repositoryObjects = new ArrayList<IRepositoryObject>();
        for (Item item : items) {
            if (item == null) {
                continue;
            }
            ProcessType process = null;
            if (item instanceof ProcessItem) {
                process = ((ProcessItem) item).getProcess();
            } else if (item instanceof JobletProcessItem) {
                process = ((JobletProcessItem) item).getJobletProcess();
            }
            if (process != null) {
                ContextType contextType = (ContextType) process.getContext().get(0);
                for (ContextParameterType param : (List<ContextParameterType>) contextType.getContextParameter()) {
                    String repositoryContextId = param.getRepositoryContextId();
                    if (repositoryContextId != null && !"".equals(repositoryContextId)) { //$NON-NLS-1$
                        try {
                            IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
                            IRepositoryObject lastVersion = factory.getLastVersion(repositoryContextId);
                            if (lastVersion != null) {
                                if (!repositoryObjects.contains(lastVersion)) {
                                    repositoryObjects.add(lastVersion);
                                }
                            }

                        } catch (PersistenceException e) {
                            ExceptionHandler.process(e);
                        }

                    }
                }

            }
        }
        return repositoryObjects;
    }

    private static Collection<IRepositoryObject> getMetadataDependenciesOfProcess(Collection<Item> items) {
        Collection<IRepositoryObject> repositoryObjects = new ArrayList<IRepositoryObject>();
        for (Item item : items) {
            if (item == null) {
                continue;
            }
            IDesignerCoreService designerCoreService = CorePlugin.getDefault().getDesignerCoreService();

            IProcess process = null;
            if (item instanceof ProcessItem) {
                process = designerCoreService.getProcessFromProcessItem((ProcessItem) item);
            } else if (item instanceof JobletProcessItem) {
                process = designerCoreService.getProcessFromJobletProcessItem((JobletProcessItem) item);
            }
            if (process != null) {
                List<INode> nodes = (List<INode>) process.getGraphicalNodes();
                for (INode node : nodes) {
                    // List<IElementParameter> eleParams = (List<IElementParameter>) node.getElementParameters();
                    // for (IElementParameter elementParameter : eleParams) {

                    IElementParameter propertyParam = node.getElementParameter("PROPERTY");//$NON-NLS-1$
                    String repositoryMetadataId = ""; //$NON-NLS-1$
                    if (propertyParam != null) {
                        repositoryMetadataId = (String) propertyParam.getChildParameters().get("REPOSITORY_PROPERTY_TYPE") //$NON-NLS-1$
                                .getValue();

                        addRepositoryObject(repositoryMetadataId, repositoryObjects);
                    }
                    IElementParameter schemaParam = node.getElementParameter("SCHEMA");//$NON-NLS-1$
                    if (schemaParam != null) {
                        repositoryMetadataId = (String) schemaParam.getChildParameters().get("REPOSITORY_SCHEMA_TYPE") //$NON-NLS-1$
                                .getValue();

                        addRepositoryObject(repositoryMetadataId, repositoryObjects);
                    }
                    IElementParameter querystoreParam = node.getElementParameter("QUERYSTORE");//$NON-NLS-1$
                    if (querystoreParam != null) {
                        repositoryMetadataId = (String) querystoreParam.getChildParameters()
                                .get("REPOSITORY_QUERYSTORE_TYPE").getValue(); //$NON-NLS-1$

                        addRepositoryObject(repositoryMetadataId, repositoryObjects);
                    }

                }

            }

        }
        // }
        return repositoryObjects;
    }

    private static void addRepositoryObject(String repositoryMetadataId, Collection<IRepositoryObject> repositoryObjects) {
        String[] id = repositoryMetadataId.split(" - "); //$NON-NLS-1$
        if (id.length > 0) {

            if (repositoryMetadataId != null && !repositoryMetadataId.equals("")) { //$NON-NLS-1$
                try {
                    IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
                    IRepositoryObject lastVersion = factory.getLastVersion(id[0].trim());
                    if (lastVersion != null) {
                        if (!repositoryObjects.contains(lastVersion)) {
                            repositoryObjects.add(lastVersion);
                        }
                    }
                } catch (PersistenceException e) {
                    ExceptionHandler.process(e);
                }

            }
        }
    }

    private static Collection<IRepositoryObject> getChildPorcessDependenciesOfProcess(Collection<Item> items) {
        List<IRepositoryObject> returnListObject = new ArrayList<IRepositoryObject>();
        Map<String, Item> returnItems = new HashMap<String, Item>();
        // Collection<IRepositoryObject> repositoryObjects = new ArrayList<IRepositoryObject>();
        for (Item item : items) {
            if (item == null) {
                continue;
            }
            IDesignerCoreService designerCoreService = CorePlugin.getDefault().getDesignerCoreService();
            IProcess process = null;
            if (item instanceof ProcessItem) {
                process = designerCoreService.getProcessFromProcessItem((ProcessItem) item);
            } else if (item instanceof JobletProcessItem) {
                process = designerCoreService.getProcessFromJobletProcessItem((JobletProcessItem) item);
            }

            if (process != null) {
                List<INode> nodes = (List<INode>) process.getGraphicalNodes();
                for (INode node : nodes) {
                    IElementParameter processParam = node.getElementParameter("PROCESS");
                    if (processParam != null) {
                        String repositoryMetadataId = (String) processParam.getChildParameters()
                                .get("PROCESS_TYPE_PROCESS").getValue(); //$NON-NLS-1$
                        if (repositoryMetadataId != null && !repositoryMetadataId.equals("")) { //$NON-NLS-1$
                            try {
                                IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
                                IRepositoryObject lastVersion = factory.getLastVersion(repositoryMetadataId);
                                if (lastVersion != null) {
                                    if (!returnListObject.contains(lastVersion)) {
                                        // repositoryObjects.add(lastVersion);
                                        returnListObject.add(lastVersion);
                                    }
                                    Item item2 = lastVersion.getProperty().getItem();
                                    if (item2 != null) {
                                        Item foundItem = returnItems.get(item2.getProperty().getId());
                                        if (foundItem == null) {
                                            returnItems.put(item2.getProperty().getId(), item2);
                                            returnListObject.addAll(getContextDependenciesOfProcess(returnItems.values()));
                                            returnListObject.addAll(getMetadataDependenciesOfProcess(returnItems.values()));
                                            returnListObject.addAll(getChildPorcessDependenciesOfProcess(returnItems.values()));
                                            returnListObject.addAll(getJobletDependenciesOfProcess(returnItems.values()));
                                        }
                                    }

                                }
                            } catch (PersistenceException e) {
                                ExceptionHandler.process(e);
                            }

                        }

                    }

                }

            }

        }
        return returnListObject;

    }

    private static Collection<IRepositoryObject> getJobletDependenciesOfProcess(Collection<Item> items) {
        Map<String, Item> returnItems = new HashMap<String, Item>();
        Collection<IRepositoryObject> repositoryObjects = new ArrayList<IRepositoryObject>();
        for (Item item : items) {
            if (item == null) {
                continue;
            }
            IDesignerCoreService designerCoreService = CorePlugin.getDefault().getDesignerCoreService();
            IProcess process = null;
            if (item instanceof ProcessItem) {
                process = designerCoreService.getProcessFromProcessItem((ProcessItem) item);
            } else if (item instanceof JobletProcessItem) {
                process = designerCoreService.getProcessFromJobletProcessItem((JobletProcessItem) item);
            }

            if (process != null) {
                List<INode> nodes = (List<INode>) process.getGraphicalNodes();
                for (INode node : nodes) {

                    if (PluginChecker.isJobLetPluginLoaded()) {
                        IJobletProviderService service = (IJobletProviderService) GlobalServiceRegister.getDefault().getService(
                                IJobletProviderService.class);
                        if (service != null && service.isJobletComponent(node)) {
                            Property jobletItem = service.getJobletComponentItem(node);
                            if (jobletItem == null) {
                                continue;
                            }
                            String repositoryMetadataId = jobletItem.getId();
                            if (repositoryMetadataId != null && !repositoryMetadataId.equals("")) { //$NON-NLS-1$
                                try {
                                    IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
                                    IRepositoryObject lastVersion = factory.getLastVersion(repositoryMetadataId);
                                    if (lastVersion != null) {
                                        if (!repositoryObjects.contains(lastVersion)) {
                                            repositoryObjects.add(lastVersion);
                                        }
                                        Item childItem = lastVersion.getProperty().getItem();
                                        if (childItem != null) {
                                            Item foundItem = returnItems.get(childItem.getProperty().getId());
                                            if (foundItem == null) {
                                                returnItems.put(childItem.getProperty().getId(), childItem);
                                                repositoryObjects.addAll(getContextDependenciesOfProcess(returnItems.values()));
                                                repositoryObjects.addAll(getMetadataDependenciesOfProcess(returnItems.values()));
                                                repositoryObjects.addAll(getChildPorcessDependenciesOfProcess(returnItems
                                                        .values()));
                                                repositoryObjects.addAll(getJobletDependenciesOfProcess(returnItems.values()));
                                            }

                                        }
                                    }
                                } catch (PersistenceException e) {
                                    ExceptionHandler.process(e);
                                }

                            }
                        }
                    }
                }
            }

        }
        return repositoryObjects;
    }

}
