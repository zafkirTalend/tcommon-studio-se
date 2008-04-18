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
package org.talend.core.model.update;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PlatformUI;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.ui.swt.dialogs.ProgressDialog;
import org.talend.core.CorePlugin;
import org.talend.core.i18n.Messages;
import org.talend.core.model.context.JobContextManager;
import org.talend.core.model.metadata.builder.connection.Connection;
import org.talend.core.model.metadata.builder.connection.MetadataTable;
import org.talend.core.model.metadata.builder.connection.Query;
import org.talend.core.model.process.IContextManager;
import org.talend.core.model.process.IProcess;
import org.talend.core.model.process.IProcess2;
import org.talend.core.model.properties.ContextItem;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.JobletProcessItem;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.properties.Property;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryObject;
import org.talend.designer.core.IDesignerCoreService;
import org.talend.designer.core.model.utils.emf.talendfile.ProcessType;
import org.talend.designer.joblet.model.JobletProcess;
import org.talend.repository.model.IProxyRepositoryFactory;

/**
 * ggu class global comment. Detailled comment
 */
public abstract class RepositoryUpdateManager {

    /**
     * for repository context rename.
     */
    private Map<ContextItem, Map<String, String>> repositoryRenamedMap = new HashMap<ContextItem, Map<String, String>>();

    /**
     * used for filter result.
     */
    private Object parameter;

    public RepositoryUpdateManager(Object parameter) {
        super();
        this.parameter = parameter;
    }

    public Map<ContextItem, Map<String, String>> getRenamedMap() {
        return this.repositoryRenamedMap;
    }

    public void setRenamedMap(Map<ContextItem, Map<String, String>> repositoryRenamedMap) {
        this.repositoryRenamedMap = repositoryRenamedMap;
    }

    public abstract Set<EUpdateItemType> getTypes();

    private boolean openPropagationDialog() {
        return MessageDialog.openQuestion(Display.getCurrent().getActiveShell(), Messages
                .getString("RepositoryUpdateManager.Title"), //$NON-NLS-1$
                Messages.getString("RepositoryUpdateManager.Messages")); //$NON-NLS-1$
    }

    public boolean doWork() {
        IDesignerCoreService designerCoreService = CorePlugin.getDefault().getDesignerCoreService();
        final List<UpdateResult> results = new ArrayList<UpdateResult>();
        ProgressDialog progress = new ProgressDialog(Display.getCurrent().getActiveShell()) {

            @Override
            public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                List<UpdateResult> returnResult = checkJobItemsForUpdate(getTypes());
                if (returnResult != null) {
                    results.addAll(returnResult);
                }
            }

        };
        try {
            progress.executeProcess();
        } catch (InvocationTargetException e) {
            ExceptionHandler.process(e);
        } catch (InterruptedException e) {
            ExceptionHandler.process(e);
        }
        // filter
        List<UpdateResult> checkedResults = filterCheckedResult(results);
        if (checkedResults != null && !checkedResults.isEmpty()) {
            if (unShowDialog(checkedResults)) {
                return designerCoreService.executeUpdatesManager(checkedResults);
            }
            if (openPropagationDialog()) {
                return designerCoreService.executeUpdatesManager(checkedResults);
            }

        }
        return false;
    }

    private List<UpdateResult> filterCheckedResult(List<UpdateResult> results) {
        if (results == null) {
            return null;
        }
        List<UpdateResult> checkedResults = new ArrayList<UpdateResult>();
        for (UpdateResult result : results) {
            if (filterForType(result.getParameter())) {
                checkedResults.add(result);
            } else {
                // for context
                if (result.getUpdateType() == EUpdateItemType.CONTEXT && result.getResultType() == EUpdateResult.BUIL_IN) {
                    checkedResults.add(result);
                }
            }

        }
        return checkedResults;
    }

    private boolean unShowDialog(List<UpdateResult> checkedResults) {
        if (checkedResults == null) {
            return false;
        }
        for (UpdateResult result : checkedResults) {
            if (result.getResultType() != EUpdateResult.UPDATE) {
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")//$NON-NLS-1$
    private boolean filterForType(Object object) {
        if (object == null || parameter == null) {
            return false;
        }
        if (object == parameter) {
            return true;
        }
        if (object instanceof List) { // for context rename
            List list = ((List) object);
            if (!list.isEmpty()) {
                if (parameter == list.get(0)) {
                    return true;
                }
            }

        }
        // schema ignore
        if (parameter instanceof org.talend.core.model.metadata.builder.connection.MetadataTable) {
            return true;
        }
        if (object instanceof org.talend.core.model.metadata.MetadataTable) {
            return true;
        }
        return false;
    }

    /**
     * 
     * ggu Comment method "checkJobItemsForUpdate".
     * 
     * @param types - need update types of jobs.
     * @param sourceIdMap - map old source id to new one.
     * @param sourceItem - modified repository item.
     * @return
     */
    private List<UpdateResult> checkJobItemsForUpdate(final Set<EUpdateItemType> types) {
        if (types == null || types.isEmpty()) {
            return null;
        }
        IEditorReference[] reference = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences();
        List<IProcess> openedProcessList = CorePlugin.getDefault().getDesignerCoreService().getOpenedProcess(reference);

        try {
            List<UpdateResult> resultList = new ArrayList<UpdateResult>();
            //
            IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
            List<IRepositoryObject> processList = factory.getAll(ERepositoryObjectType.PROCESS, true);
            if (processList == null) {
                processList = new ArrayList<IRepositoryObject>();
            }
            List<IRepositoryObject> jobletList = factory.getAll(ERepositoryObjectType.JOBLET, true);
            if (jobletList != null) {
                processList.addAll(jobletList);
            }
            //
            for (IRepositoryObject repositoryObj : processList) {
                List<IRepositoryObject> allVersion = factory.getAllVersion(repositoryObj.getId());
                for (IRepositoryObject object : allVersion) {
                    Item item = object.getProperty().getItem();
                    // avoid the opened job
                    if (isOpenedItem(item, openedProcessList)) {
                        continue;
                    }
                    List<UpdateResult> updatesNeededFromItems = getUpdatesNeededFromItems(item, types, getRenamedMap());
                    if (updatesNeededFromItems != null) {
                        resultList.addAll(updatesNeededFromItems);
                    }
                }
            }

            // opened job
            for (IProcess process : openedProcessList) {
                List<UpdateResult> resultFromProcess = getResultFromProcess(process, types, getRenamedMap());
                if (resultFromProcess != null) {
                    resultList.addAll(resultFromProcess);
                }
            }
            return resultList;
        } catch (PersistenceException e) {
            //
        }
        return null;
    }

    private boolean isOpenedItem(Item openedItem, List<IProcess> openedProcessList) {
        if (openedItem == null || openedProcessList == null) {
            return false;
        }
        for (IProcess process : openedProcessList) {
            Property property = openedItem.getProperty();
            if (process.getId().equals(property.getId()) && process.getLabel().equals(property.getLabel())
                    && process.getVersion().equals(property.getVersion())) {
                return true;
            }
        }
        return false;
    }

    private List<UpdateResult> getResultFromProcess(IProcess process, final Set<EUpdateItemType> types,
            final Map<ContextItem, Map<String, String>> repositoryRenamedMap) {
        if (process == null || types == null) {
            return null;
        }
        List<UpdateResult> resultList = new ArrayList<UpdateResult>();
        if (process instanceof IProcess2) {
            IProcess2 process2 = (IProcess2) process;
            IContextManager contextManager = process2.getContextManager();
            if (contextManager instanceof JobContextManager) {
                JobContextManager jobContextManager = (JobContextManager) contextManager;
                jobContextManager.setRepositoryRenamedMap(repositoryRenamedMap);
            }
            for (EUpdateItemType type : types) {
                List<UpdateResult> updatesNeeded = process2.getUpdateManager().getUpdatesNeeded(type);
                if (updatesNeeded != null) {
                    resultList.addAll(updatesNeeded);
                }
            }
        }
        return resultList;
    }

    private List<UpdateResult> getUpdatesNeededFromItems(Item item, final Set<EUpdateItemType> types,
            final Map<ContextItem, Map<String, String>> repositoryRenamedMap) {
        if (item == null || types == null) {
            return null;
        }
        IDesignerCoreService designerCoreService = CorePlugin.getDefault().getDesignerCoreService();
        if (designerCoreService == null) {
            return null;
        }
        //
        IProcess process = null;
        if (item instanceof ProcessItem) {
            process = designerCoreService.getProcessFromProcessItem((ProcessItem) item);
        } else if (item instanceof JobletProcessItem) {
            process = designerCoreService.getProcessFromJobletProcessItem((JobletProcessItem) item);
        }
        //
        if (process != null && process instanceof IProcess2) {
            IProcess2 process2 = (IProcess2) process;
            // for save item
            List<UpdateResult> resultFromProcess = getResultFromProcess(process2, types, repositoryRenamedMap);
            // set
            addItemForResult(process2, resultFromProcess);
            return resultFromProcess;

        }
        return null;
    }

    private void addItemForResult(IProcess2 process2, List<UpdateResult> updatesNeededFromItems) {
        if (process2 == null || updatesNeededFromItems == null) {
            return;
        }
        for (UpdateResult result : updatesNeededFromItems) {
            result.setItemProcess(process2);
        }
    }

    public static void saveModifiedItem(List<UpdateResult> updatesNeededResult) {
        if (updatesNeededResult == null) {
            return;
        }
        Set<IProcess2> process2List = new HashSet<IProcess2>();

        for (UpdateResult result : updatesNeededResult) {
            IProcess2 process2 = result.getItemProcess();
            if (process2 != null) { // for item update
                process2List.add(process2);
            }
        }
        if (process2List.isEmpty()) {
            return;
        }
        // save
        IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
        for (IProcess2 process2 : process2List) {
            try {
                ProcessType processType = process2.saveXmlFile();
                Item item = process2.getProperty().getItem();
                if (item instanceof JobletProcessItem) {
                    ((JobletProcessItem) item).setJobletProcess((JobletProcess) processType);
                } else {
                    ((ProcessItem) item).setProcess(processType);
                }
                factory.save(item);
            } catch (PersistenceException e) {
                // 
            } catch (IOException e) {
                // 
            }
        }
    }

    public static boolean updateDBConnection(Connection connection) {
        RepositoryUpdateManager repositoryUpdateManager = new RepositoryUpdateManager(connection) {

            @Override
            public Set<EUpdateItemType> getTypes() {
                Set<EUpdateItemType> types = new HashSet<EUpdateItemType>();
                types.add(EUpdateItemType.NODE_PROPERTY);
                types.add(EUpdateItemType.JOB_PROPERTY_EXTRA);
                types.add(EUpdateItemType.JOB_PROPERTY_STATS_LOGS);
                return types;
            }

        };
        return repositoryUpdateManager.doWork();
    }

    public static boolean updateFileConnection(Connection connection) {
        RepositoryUpdateManager repositoryUpdateManager = new RepositoryUpdateManager(connection) {

            @Override
            public Set<EUpdateItemType> getTypes() {
                Set<EUpdateItemType> types = new HashSet<EUpdateItemType>();
                types.add(EUpdateItemType.NODE_PROPERTY);
                types.add(EUpdateItemType.NODE_SCHEMA);
                return types;
            }

        };
        return repositoryUpdateManager.doWork();
    }

    public static boolean updateSchema(final MetadataTable metadataTable) {
        RepositoryUpdateManager repositoryUpdateManager = new RepositoryUpdateManager(metadataTable) {

            @Override
            public Set<EUpdateItemType> getTypes() {
                Set<EUpdateItemType> types = new HashSet<EUpdateItemType>();
                types.add(EUpdateItemType.NODE_SCHEMA);
                return types;
            }

        };
        return repositoryUpdateManager.doWork();
    }

    public static boolean updateQuery(Query query) {
        RepositoryUpdateManager repositoryUpdateManager = new RepositoryUpdateManager(query) {

            @Override
            public Set<EUpdateItemType> getTypes() {
                Set<EUpdateItemType> types = new HashSet<EUpdateItemType>();
                types.add(EUpdateItemType.NODE_QUERY);
                return types;
            }

        };
        return repositoryUpdateManager.doWork();
    }

    public static boolean updateContext(JobContextManager repositoryContextManager, ContextItem item) {
        RepositoryUpdateManager repositoryUpdateManager = new RepositoryUpdateManager(item) {

            @Override
            public Set<EUpdateItemType> getTypes() {
                Set<EUpdateItemType> types = new HashSet<EUpdateItemType>();
                types.add(EUpdateItemType.CONTEXT);
                return types;
            }

        };
        Map<ContextItem, Map<String, String>> repositoryRenamedMap = new HashMap<ContextItem, Map<String, String>>();
        repositoryRenamedMap.put(item, repositoryContextManager.getNameMap());
        repositoryUpdateManager.setRenamedMap(repositoryRenamedMap);
        return repositoryUpdateManager.doWork();
    }
}
