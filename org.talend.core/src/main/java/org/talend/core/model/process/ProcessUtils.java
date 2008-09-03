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
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.JobletProcessItem;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryObject;
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
                    if (repositoryContextId != null && !"".equals(repositoryContextId)) {
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
                    List<IElementParameter> eleParams = (List<IElementParameter>) node.getElementParameters();
                    for (IElementParameter elementParameter : eleParams) {
                        String repositoryMetadataId = "";
                        if (elementParameter.getName().equals("PROPERTY")) {
                            repositoryMetadataId = (String) elementParameter.getChildParameters().get("REPOSITORY_PROPERTY_TYPE")
                                    .getValue();
                        }
                        if (elementParameter.getName().equals("SCHEMA")) {
                            repositoryMetadataId = (String) elementParameter.getChildParameters().get("REPOSITORY_SCHEMA_TYPE")
                                    .getValue();
                        }
                        if (elementParameter.getName().equals("QUERYSTORE")) {
                            repositoryMetadataId = (String) elementParameter.getChildParameters().get(
                                    "REPOSITORY_QUERYSTORE_TYPE").getValue();
                        }
                        String[] id = repositoryMetadataId.split(" - ");
                        if (id.length > 0) {

                            if (repositoryMetadataId != null && !repositoryMetadataId.equals("")) {
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

                }

            }
        }
        return repositoryObjects;
    }

    private static Collection<IRepositoryObject> getChildPorcessDependenciesOfProcess(Collection<Item> items) {
        List<IRepositoryObject> returnListObject = new ArrayList<IRepositoryObject>();
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
                    List<IElementParameter> eleParams = (List<IElementParameter>) node.getElementParameters();
                    for (IElementParameter elementParameter : eleParams) {

                        if (elementParameter.getName().equals("PROCESS")) {
                            String repositoryMetadataId = (String) elementParameter.getChildParameters().get(
                                    "PROCESS_TYPE_PROCESS").getValue();
                            if (repositoryMetadataId != null && !repositoryMetadataId.equals("")) {
                                try {
                                    IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
                                    IRepositoryObject lastVersion = factory.getLastVersion(repositoryMetadataId);
                                    if (lastVersion != null) {
                                        if (!repositoryObjects.contains(lastVersion)) {
                                            repositoryObjects.add(lastVersion);
                                            returnListObject.add(lastVersion);
                                        }
                                        Item item2 = lastVersion.getProperty().getItem();
                                        if (item2 != null) {
                                            Item foundItem = returnItems.get(item2.getProperty().getId());
                                            if (foundItem == null) {
                                                returnItems.put(item2.getProperty().getId(), item2);
                                                returnListObject.addAll(getContextDependenciesOfProcess(returnItems.values()));
                                                returnListObject.addAll(getMetadataDependenciesOfProcess(returnItems.values()));
                                                returnListObject
                                                        .addAll(getChildPorcessDependenciesOfProcess(returnItems.values()));
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
        return returnListObject;

    }

}
