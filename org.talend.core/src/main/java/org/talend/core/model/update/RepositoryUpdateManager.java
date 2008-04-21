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

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.EList;
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
import org.talend.core.model.properties.ConnectionItem;
import org.talend.core.model.properties.ContextItem;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.JobletProcessItem;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.properties.Property;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryObject;
import org.talend.designer.core.IDesignerCoreService;
import org.talend.repository.model.ERepositoryStatus;
import org.talend.repository.model.IProxyRepositoryFactory;

/**
 * ggu class global comment. Detailled comment
 */
public abstract class RepositoryUpdateManager {

    /**
     * for repository context rename.
     */
    private Map<ContextItem, Map<String, String>> repositoryRenamedMap = new HashMap<ContextItem, Map<String, String>>();

    private Map<String, String> schemaRenamedMap = new HashMap<String, String>();

    /**
     * used for filter result.
     */
    private Object parameter;

    public RepositoryUpdateManager(Object parameter) {
        super();
        this.parameter = parameter;
    }

    /*
     * context
     */
    public Map<ContextItem, Map<String, String>> getContextRenamedMap() {
        return this.repositoryRenamedMap;
    }

    public void setContextRenamedMap(Map<ContextItem, Map<String, String>> repositoryRenamedMap) {
        this.repositoryRenamedMap = repositoryRenamedMap;
    }

    /*
     * Schema old name to new one
     */

    public Map<String, String> getSchemaRenamedMap() {
        return this.schemaRenamedMap;
    }

    public void setSchemaRenamedMap(Map<String, String> schemaRenamedMap) {
        this.schemaRenamedMap = schemaRenamedMap;
    }

    public abstract Set<EUpdateItemType> getTypes();

    private boolean openPropagationDialog() {
        return MessageDialog.openQuestion(Display.getCurrent().getActiveShell(), Messages
                .getString("RepositoryUpdateManager.Title"), //$NON-NLS-1$
                Messages.getString("RepositoryUpdateManager.Messages")); //$NON-NLS-1$
    }

    private void openNoModificationDialog() {
        MessageDialog.openInformation(Display.getCurrent().getActiveShell(), Messages
                .getString("RepositoryUpdateManager.NoModificationTitle"), //$NON-NLS-1$
                Messages.getString("RepositoryUpdateManager.NoModificationMessages")); //$NON-NLS-1$
    }

    public boolean doWork() {
        // check the dialog.
        boolean checked = true;
        boolean showed = false;
        if (parameter != null && getContextRenamedMap().isEmpty() && getSchemaRenamedMap().isEmpty()) {
            checked = openPropagationDialog();
            showed = true;
        }
        if (checked) {
            final List<UpdateResult> results = new ArrayList<UpdateResult>();
            ProgressDialog progress = new ProgressDialog(Display.getCurrent().getActiveShell()) {

                @Override
                public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                    monitor.setCanceled(false);
                    List<UpdateResult> returnResult = checkJobItemsForUpdate(monitor, getTypes());
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
            List<UpdateResult> checkedResults = null;

            if (parameter == null) { // update all job
                checkedResults = results;
            } else { // filter
                checkedResults = filterCheckedResult(results);
            }
            if (checkedResults != null && !checkedResults.isEmpty()) {
                if (showed || parameter == null || unShowDialog(checkedResults) || openPropagationDialog()) {
                    IDesignerCoreService designerCoreService = CorePlugin.getDefault().getDesignerCoreService();
                    return designerCoreService.executeUpdatesManager(checkedResults);
                }
                return false;
            }
            openNoModificationDialog();
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
    private List<UpdateResult> checkJobItemsForUpdate(IProgressMonitor parentMonitor, final Set<EUpdateItemType> types) {
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
            // get all version
            List<IRepositoryObject> allVersionList = new ArrayList<IRepositoryObject>();
            for (IRepositoryObject repositoryObj : processList) {
                List<IRepositoryObject> allVersion = factory.getAllVersion(repositoryObj.getId());
                for (IRepositoryObject object : allVersion) {
                    if (factory.getStatus(object) != ERepositoryStatus.LOCK_BY_OTHER
                            && factory.getStatus(object) != ERepositoryStatus.LOCK_BY_USER) {
                        allVersionList.add(object);
                    }
                }
            }
            //
            int size = (allVersionList.size() + openedProcessList.size() + 1) * UpdatesConstants.SCALE;
            parentMonitor.beginTask(Messages.getString("RepositoryUpdateManager.Check"), size); //$NON-NLS-1$

            for (IRepositoryObject repositoryObj : allVersionList) {
                Item item = repositoryObj.getProperty().getItem();
                // avoid the opened job
                if (isOpenedItem(item, openedProcessList)) {
                    continue;
                }
                List<UpdateResult> updatesNeededFromItems = getUpdatesNeededFromItems(parentMonitor, item, types);
                if (updatesNeededFromItems != null) {
                    resultList.addAll(updatesNeededFromItems);
                }
            }

            // opened job
            for (IProcess process : openedProcessList) {
                List<UpdateResult> resultFromProcess = getResultFromProcess(parentMonitor, process, types);
                if (resultFromProcess != null) {
                    resultList.addAll(resultFromProcess);
                }
            }
            parentMonitor.done();
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

    private List<UpdateResult> getResultFromProcess(IProgressMonitor parentMonitor, IProcess process,
            final Set<EUpdateItemType> types) {
        if (process == null || types == null) {
            return null;
        }
        if (parentMonitor == null) {
            parentMonitor = new NullProgressMonitor();
        }
        SubProgressMonitor subMonitor = new SubProgressMonitor(parentMonitor, 1 * UpdatesConstants.SCALE,
                SubProgressMonitor.PREPEND_MAIN_LABEL_TO_SUBTASK);
        subMonitor.beginTask(UpdatesConstants.EMPTY, types.size());
        subMonitor.subTask(getUpdateJobInfor(process));

        List<UpdateResult> resultList = new ArrayList<UpdateResult>();
        if (process instanceof IProcess2) {
            IProcess2 process2 = (IProcess2) process;
            // context rename
            IContextManager contextManager = process2.getContextManager();
            if (contextManager instanceof JobContextManager) {
                JobContextManager jobContextManager = (JobContextManager) contextManager;
                jobContextManager.setRepositoryRenamedMap(getContextRenamedMap());
            }
            // schema rename
            IUpdateManager updateManager = process2.getUpdateManager();
            if (updateManager instanceof AbstractUpdateManager) {
                AbstractUpdateManager manager = (AbstractUpdateManager) updateManager;
                manager.setSchemaRenamedMap(getSchemaRenamedMap());
            }
            //
            for (EUpdateItemType type : types) {
                List<UpdateResult> updatesNeeded = updateManager.getUpdatesNeeded(type);
                if (updatesNeeded != null) {
                    resultList.addAll(updatesNeeded);
                }
                subMonitor.worked(1);
            }
        }
        subMonitor.done();
        return resultList;
    }

    private List<UpdateResult> getUpdatesNeededFromItems(IProgressMonitor parentMonitor, Item item,
            final Set<EUpdateItemType> types) {
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
            List<UpdateResult> resultFromProcess = getResultFromProcess(parentMonitor, process2, types);
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

    public static ERepositoryObjectType getTypeFromSource(String source) {
        if (source == null) {
            return null;
        }
        for (ERepositoryObjectType type : ERepositoryObjectType.values()) {
            String alias = type.getAlias();
            if (alias != null && source.startsWith(alias)) {
                return type;
            }
        }
        return null;
    }

    public static String getUpdateJobInfor(IProcess process) {
        if (process == null) {
            return UpdatesConstants.JOB;
        }
        StringBuffer infor = new StringBuffer();
        String prefix = UpdatesConstants.JOB;
        String label = null;
        String version = null;
        if (process instanceof IProcess2) {
            IProcess2 process2 = (IProcess2) process;
            if (process2.disableRunJobView()) { // for joblet
                prefix = UpdatesConstants.JOBLET;
            }
            label = process2.getProperty().getLabel();
            version = process2.getProperty().getVersion();
        }
        infor.append(prefix);
        if (label != null) {
            infor.append(UpdatesConstants.SPACE);
            infor.append(label);
            infor.append(UpdatesConstants.SPACE);
            infor.append(version);
        }
        return infor.toString();

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

    @SuppressWarnings("unchecked")//$NON-NLS-1$
    public static Map<String, String> getTableIdAndNameMap(ConnectionItem connItem) {
        if (connItem == null) {
            return Collections.emptyMap();
        }
        Map<String, String> idAndNameMap = new HashMap<String, String>();
        EList tables = connItem.getConnection().getTables();
        if (tables != null) {
            for (MetadataTable table : (List<MetadataTable>) tables) {
                idAndNameMap.put(table.getId(), table.getLabel());
            }
        }
        return idAndNameMap;
    }

    @SuppressWarnings("unchecked")//$NON-NLS-1$
    private static Map<String, String> getSchemaRenamedMap(ConnectionItem connItem, Map<String, String> oldTableMap) {
        if (connItem == null || oldTableMap == null) {
            return Collections.emptyMap();
        }

        Map<String, String> schemaRenamedMap = new HashMap<String, String>();

        final String prefix = connItem.getProperty().getId() + UpdatesConstants.SEGMENT_LINE;
        EList tables = connItem.getConnection().getTables();
        if (tables != null) {
            for (MetadataTable table : (List<MetadataTable>) tables) {
                String oldName = oldTableMap.get(table.getId());
                String newName = table.getLabel();
                if (oldName != null && !oldName.equals(newName)) {
                    schemaRenamedMap.put(prefix + oldName, prefix + newName);
                }
            }
        }
        return schemaRenamedMap;
    }

    public static boolean updateSchema(final MetadataTable metadataTable, ConnectionItem connItem, Map<String, String> oldTableMap) {
        Map<String, String> schemaRenamedMap = getSchemaRenamedMap(connItem, oldTableMap);
        RepositoryUpdateManager repositoryUpdateManager = new RepositoryUpdateManager(metadataTable) {

            @Override
            public Set<EUpdateItemType> getTypes() {
                Set<EUpdateItemType> types = new HashSet<EUpdateItemType>();
                types.add(EUpdateItemType.NODE_SCHEMA);
                return types;
            }

        };

        // set renamed schema
        repositoryUpdateManager.setSchemaRenamedMap(schemaRenamedMap);

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
        if (!repositoryContextManager.getNameMap().isEmpty()) {
            repositoryRenamedMap.put(item, repositoryContextManager.getNameMap());
        }
        repositoryUpdateManager.setContextRenamedMap(repositoryRenamedMap);
        return repositoryUpdateManager.doWork();
    }

    public static boolean updateAllJob() {
        RepositoryUpdateManager repositoryUpdateManager = new RepositoryUpdateManager(null) {

            @Override
            public Set<EUpdateItemType> getTypes() {
                Set<EUpdateItemType> types = new HashSet<EUpdateItemType>();
                for (EUpdateItemType type : EUpdateItemType.values()) {
                    types.add(type);
                }
                return types;
            }

        };
        return repositoryUpdateManager.doWork();
    }
}
