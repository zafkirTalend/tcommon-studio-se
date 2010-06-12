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
import org.talend.core.model.properties.SQLPatternItem;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryViewObject;
import org.talend.core.model.utils.SQLPatternUtils;
import org.talend.core.ui.IJobletProviderService;
import org.talend.designer.core.IDesignerCoreService;
import org.talend.designer.core.model.utils.emf.talendfile.ContextParameterType;
import org.talend.designer.core.model.utils.emf.talendfile.ContextType;
import org.talend.designer.core.model.utils.emf.talendfile.ProcessType;
import org.talend.repository.model.IProxyRepositoryFactory;

/**
 * DOC bqian class global comment. Detailled comment
 */
@SuppressWarnings("unchecked")
public final class ProcessUtils {

    private ProcessUtils() {
    }

    private static List<IProcess> fakeProcesses = new ArrayList<IProcess>();

    public static void clearFakeProcesses() {
        for (IProcess p : fakeProcesses) {
            if (p instanceof IProcess2) {
                ((IProcess2) p).dispose();
            }
        }
        fakeProcesses.clear();
    }

    private static void createFakeProcesses(Collection<Item> items) {
        IDesignerCoreService designerCoreService = CorePlugin.getDefault().getDesignerCoreService();
        if (items != null) {
            for (Item item : items) {
                if (item == null || existedFakeProcess(item.getProperty().getId())) {
                    continue;
                }
                IProcess process = null;
                if (item instanceof ProcessItem) {
                    process = designerCoreService.getProcessFromProcessItem((ProcessItem) item);
                } else if (item instanceof JobletProcessItem) {
                    process = designerCoreService.getProcessFromJobletProcessItem((JobletProcessItem) item);
                }
                if (process != null) {
                    fakeProcesses.add(process);
                }
            }
        }
    }

    private static boolean existedFakeProcess(String id) {
        for (IProcess p : fakeProcesses) {
            if (p.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    private static IProcess[] checkAndGetFakeProcesses(Collection<Item> items) {
        List<IProcess> tmpProcesses = new ArrayList<IProcess>();

        createFakeProcesses(items);
        if (items != null) {
            List<String> ids = new ArrayList<String>();
            for (Item item : items) {
                String id = item.getProperty().getId();
                if (!ids.contains(id)) {
                    for (IProcess p : fakeProcesses) {
                        if (p != null && p.getId().equals(id)) {
                            ids.add(p.getId());
                            tmpProcesses.add(p);
                        }
                    }
                }
            }
        }
        return tmpProcesses.toArray(new IProcess[0]);
    }

    public static Collection<IRepositoryViewObject> getProcessDependencies(ERepositoryObjectType dependencyType,
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
        case SQLPATTERNS:
            return getSQLTemplatesDependenciesOfProcess(items);
        default:
            return Collections.emptyList();
        }

    }

    public static Collection<IRepositoryViewObject> getAllProcessDependencies(Collection<Item> items) {
        clearFakeProcesses();
        createFakeProcesses(items);

        Collection<IRepositoryViewObject> dependencies = getContextDependenciesOfProcess(items);
        dependencies.addAll(getMetadataDependenciesOfProcess(items));
        dependencies.addAll(getChildPorcessDependenciesOfProcess(items));
        dependencies.addAll(getJobletDependenciesOfProcess(items));
        dependencies.addAll(getSQLTemplatesDependenciesOfProcess(items));

        clearFakeProcesses();
        return dependencies;
    }

    private static Collection<IRepositoryViewObject> getContextDependenciesOfProcess(Collection<Item> items) {
        Collection<IRepositoryViewObject> repositoryObjects = new ArrayList<IRepositoryViewObject>();
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
                            IRepositoryViewObject lastVersion = factory.getLastVersion(repositoryContextId);
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

    private static Collection<IRepositoryViewObject> getMetadataDependenciesOfProcess(Collection<Item> items) {
        Collection<IRepositoryViewObject> repositoryObjects = new ArrayList<IRepositoryViewObject>();
        for (IProcess process : checkAndGetFakeProcesses(items)) {
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

    private static void addRepositoryObject(String repositoryMetadataId, Collection<IRepositoryViewObject> repositoryObjects) {
        String[] id = repositoryMetadataId.split(" - "); //$NON-NLS-1$
        if (id.length > 0) {

            if (repositoryMetadataId != null && !repositoryMetadataId.equals("")) { //$NON-NLS-1$
                try {
                    IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
                    IRepositoryViewObject lastVersion = factory.getLastVersion(id[0].trim());
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

    private static Collection<IRepositoryViewObject> getChildPorcessDependenciesOfProcess(Collection<Item> items) {
        List<IRepositoryViewObject> returnListObject = new ArrayList<IRepositoryViewObject>();
        Map<String, Item> returnItems = new HashMap<String, Item>();
        // Collection<IRepositoryObject> repositoryObjects = new ArrayList<IRepositoryObject>();
        for (IProcess process : checkAndGetFakeProcesses(items)) {
            if (process != null) {
                List<INode> nodes = (List<INode>) process.getGraphicalNodes();
                for (INode node : nodes) {
                    IElementParameter processParam = node.getElementParameter("PROCESS"); //$NON-NLS-1$
                    if (processParam != null) {
                        String repositoryMetadataId = (String) processParam.getChildParameters()
                                .get("PROCESS_TYPE_PROCESS").getValue(); //$NON-NLS-1$
                        if (repositoryMetadataId != null && !repositoryMetadataId.equals("")) { //$NON-NLS-1$
                            try {
                                IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
                                IRepositoryViewObject lastVersion = factory.getLastVersion(repositoryMetadataId);
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

    private static Collection<IRepositoryViewObject> getJobletDependenciesOfProcess(Collection<Item> items) {
        Map<String, Item> returnItems = new HashMap<String, Item>();
        Collection<IRepositoryViewObject> repositoryObjects = new ArrayList<IRepositoryViewObject>();
        for (IProcess process : checkAndGetFakeProcesses(items)) {
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
                                    IRepositoryViewObject lastVersion = factory.getLastVersion(repositoryMetadataId);
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

    private static Collection<IRepositoryViewObject> getSQLTemplatesDependenciesOfProcess(Collection<Item> items) {
        Collection<IRepositoryViewObject> repositoryObjects = new ArrayList<IRepositoryViewObject>();
        for (IProcess process : checkAndGetFakeProcesses(items)) {
            if (process != null) {
                List<INode> nodes = (List<INode>) process.getGraphicalNodes();
                for (INode node : nodes) {
                    IElementParameter sqlTemplateParam = node.getElementParameter("SQLPATTERN_VALUE"); //$NON-NLS-1$
                    if (sqlTemplateParam != null && sqlTemplateParam.getField() == EParameterFieldType.TABLE) {
                        List<Map<String, Object>> values = (List<Map<String, Object>>) sqlTemplateParam.getValue();
                        if (values != null) {
                            for (Map<String, Object> line : values) {
                                Object object = line.get(SQLPatternUtils.SQLPATTERNLIST);
                                if (object instanceof String) {
                                    String[] idAndLable = ((String) object).split(SQLPatternUtils.ID_SEPARATOR);
                                    if (idAndLable.length > 1) {
                                        IRepositoryViewObject repositoryObject = SQLPatternUtils
                                                .getLastVersionRepositoryObjectById(idAndLable[0]);
                                        if (repositoryObject != null && repositoryObject.getLabel().equals(idAndLable[1])) {
                                            Item item2 = repositoryObject.getProperty().getItem();
                                            if (item2 instanceof SQLPatternItem && !((SQLPatternItem) item2).isSystem()) {
                                                repositoryObjects.add(repositoryObject);
                                            }
                                        }
                                    }
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
