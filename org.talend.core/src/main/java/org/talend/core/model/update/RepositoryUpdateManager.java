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
package org.talend.core.model.update;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.map.MultiKeyMap;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.progress.ProgressMonitorJobsDialog;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.exception.PersistenceException;
import org.talend.core.CorePlugin;
import org.talend.core.i18n.Messages;
import org.talend.core.model.context.JobContextManager;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.builder.ConvertionHelper;
import org.talend.core.model.metadata.builder.connection.Connection;
import org.talend.core.model.metadata.builder.connection.MetadataTable;
import org.talend.core.model.metadata.builder.connection.QueriesConnection;
import org.talend.core.model.metadata.builder.connection.Query;
import org.talend.core.model.metadata.builder.connection.SAPFunctionUnit;
import org.talend.core.model.process.IContext;
import org.talend.core.model.process.IContextManager;
import org.talend.core.model.process.IContextParameter;
import org.talend.core.model.process.IProcess;
import org.talend.core.model.process.IProcess2;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.core.model.properties.ContextItem;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.JobletProcessItem;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.properties.Property;
import org.talend.core.model.relationship.RelationshipItemBuilder;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryObject;
import org.talend.core.prefs.ITalendCorePrefConstants;
import org.talend.designer.core.IDesignerCoreService;
import org.talend.designer.runprocess.ItemCacheManager;
import org.talend.repository.UpdateRepositoryUtils;
import org.talend.repository.model.ERepositoryStatus;
import org.talend.repository.model.IProxyRepositoryFactory;
import org.talend.repository.model.RepositoryNode;

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
     * for context group
     */
    private Map<ContextItem, List<IContext>> repositoryContextGroupMap = new HashMap<ContextItem, List<IContext>>();

    /**
     * used for filter result.
     */
    protected Object parameter;

    private Map<ContextItem, Set<String>> newParametersMap = new HashMap<ContextItem, Set<String>>();

    private boolean onlyOpeningJob = false;

    private List<IRepositoryObject> updateObjList;

    public RepositoryUpdateManager(Object parameter) {
        super();
        this.parameter = parameter;
    }

    public RepositoryUpdateManager(Object parameter, List<IRepositoryObject> updateObjList) {
        super();
        this.parameter = parameter;
        this.updateObjList = updateObjList;
    }

    public void setOnlyOpeningJob(boolean onlyOpeningJob) {
        this.onlyOpeningJob = onlyOpeningJob;
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

    public Map<ContextItem, List<IContext>> getRepositoryAddGroupContext() {
        return this.repositoryContextGroupMap;
    }

    public void setRepositoryAddGroupContext(Map<ContextItem, List<IContext>> repositoryContextGroupMap) {
        this.repositoryContextGroupMap = repositoryContextGroupMap;
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

    /**
     * 
     * ggu Comment method "openNoModificationDialog".
     * 
     * @param onlyImpactAnalysis for 9543
     * @return
     */
    private void openNoModificationDialog(boolean onlyImpactAnalysis) {
        String title = Messages.getString("RepositoryUpdateManager.NoModificationTitle"); //$NON-NLS-1$
        String messages = Messages.getString("RepositoryUpdateManager.NoModificationMessages"); ////$NON-NLS-1$
        if (onlyImpactAnalysis) {
            title = Messages.getString("RepositoryUpdateManager.NotFoundTitle"); //$NON-NLS-1$
            messages = Messages.getString("RepositoryUpdateManager.NotFoundMessages"); //$NON-NLS-1$
        }
        MessageDialog.openInformation(Display.getCurrent().getActiveShell(), title, messages);
    }

    private boolean openRenameCheckedDialog() {
        return MessageDialog.openQuestion(Display.getCurrent().getActiveShell(), Messages
                .getString("RepositoryUpdateManager.RenameContextTitle"), //$NON-NLS-1$
                Messages.getString("RepositoryUpdateManager.RenameContextMessages")); //$NON-NLS-1$

    }

    public boolean doWork() {
        return doWork(true, false);
    }

    public boolean needForcePropagation() {
        return needForcePropagationForContext() || (getSchemaRenamedMap() != null && !getSchemaRenamedMap().isEmpty());
    }

    private boolean needForcePropagationForContext() {
        return getContextRenamedMap() != null && !getContextRenamedMap().isEmpty();
    }

    @SuppressWarnings("restriction")
    public boolean doWork(boolean show, final boolean onlyImpactAnalysis) {
        // check the dialog.
        boolean checked = true;
        boolean showed = false;
        if (show) {
            if (needForcePropagationForContext()) {
                checked = openRenameCheckedDialog(); // bug 4988
                showed = true;
            } else if (parameter != null && !needForcePropagation()) {
                // see feature 4786
                boolean deactive = Boolean.parseBoolean(CorePlugin.getDefault().getDesignerCoreService().getPreferenceStore(
                        ITalendCorePrefConstants.DEACTIVE_REPOSITORY_UPDATE));
                if (deactive) {
                    return false;
                }

                checked = openPropagationDialog();
                showed = true;
            }
        } else {
            showed = true;
        }
        if (checked) {
            final List<UpdateResult> results = new ArrayList<UpdateResult>();
            boolean cancelable = !needForcePropagation();
            IRunnableWithProgress runnable = new IRunnableWithProgress() {

                public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                    List<UpdateResult> returnResult = checkJobItemsForUpdate(monitor, getTypes(), onlyImpactAnalysis);
                    if (returnResult != null) {
                        results.addAll(returnResult);
                    }
                }
            };

            try {
                final ProgressMonitorJobsDialog dialog = new ProgressMonitorJobsDialog(null);
                dialog.run(true, cancelable, runnable);

                // PlatformUI.getWorkbench().getProgressService().run(true, true, runnable);
            } catch (InvocationTargetException e) {
                ExceptionHandler.process(e);
            } catch (InterruptedException e) {
                if (e.getMessage().equals(UpdatesConstants.MONITOR_IS_CANCELED)) {
                    return false;
                }
                ExceptionHandler.process(e);
            }
            List<UpdateResult> checkedResults = null;

            if (parameter == null) { // update all job
                checkedResults = filterSpecialCheckedResult(results);
            } else { // filter
                checkedResults = filterCheckedResult(results);
            }
            if (checkedResults != null && !checkedResults.isEmpty()) {
                if (showed || parameter == null || unShowDialog(checkedResults) || openPropagationDialog()) {
                    IDesignerCoreService designerCoreService = CorePlugin.getDefault().getDesignerCoreService();
                    return designerCoreService.executeUpdatesManager(checkedResults, onlyImpactAnalysis);
                }
                return false;
            }
            openNoModificationDialog(onlyImpactAnalysis);
        }
        return false;
    }

    private List<UpdateResult> filterSpecialCheckedResult(List<UpdateResult> results) {
        if (results == null) {
            return null;
        }
        List<IProcess> openedProcessList = CorePlugin.getDefault().getDesignerCoreService().getOpenedProcess(getEditors());

        List<UpdateResult> checkedResults = new ArrayList<UpdateResult>();
        for (UpdateResult result : results) {
            if (result.getParameter() instanceof JobletProcessItem) {
                if (result.getJob() instanceof IProcess2) { // only opening job
                    if (openedProcessList.contains(result.getJob())) {
                        checkedResults.add(result);
                    }
                }
            } else {
                checkedResults.add(result); // ignore others
            }
        }
        return checkedResults;
    }

    private List<UpdateResult> filterCheckedResult(List<UpdateResult> results) {
        if (results == null) {
            return null;
        }
        List<UpdateResult> checkedResults = new ArrayList<UpdateResult>();
        for (UpdateResult result : results) {
            if (filterForType(result)) {
                checkedResults.add(result);
            } else {
                // for context
                if (result.getUpdateType() == EUpdateItemType.CONTEXT && result.getResultType() == EUpdateResult.BUIL_IN) {
                    checkedResults.add(result);
                }
                // for context group
                if (result.getUpdateType() == EUpdateItemType.CONTEXT_GROUP && result.getResultType() == EUpdateResult.ADD) {
                    Object job = result.getJob();
                    if (parameter instanceof ContextItem && job instanceof IProcess2) {
                        ContextItem contextItem = (ContextItem) parameter;
                        String sourceLabel = contextItem.getProperty().getLabel();
                        IProcess2 relatedJob = (IProcess2) job;
                        if (relatedJob != null) {
                            List<IContext> listContext = relatedJob.getContextManager().getListContext();
                            List<String> existSource = new ArrayList<String>();
                            for (IContext context : listContext) {
                                for (IContextParameter param : context.getContextParameterList()) {
                                    String source = param.getSource();
                                    if (source != null && !existSource.contains(source)) {
                                        existSource.add(source);
                                    }
                                }
                            }
                            if (existSource.contains(sourceLabel)) {
                                checkedResults.add(result);
                            }
                        }

                    }
                } else if (result.getUpdateType() == EUpdateItemType.CONTEXT && result.getResultType() == EUpdateResult.ADD) {
                    ConnectionItem contextModeConnectionItem = result.getContextModeConnectionItem();
                    // for context mode
                    if (contextModeConnectionItem != null && contextModeConnectionItem.getConnection() == this.parameter) {
                        checkedResults.add(result);
                    }
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

    @SuppressWarnings("unchecked")
    private boolean filterForType(UpdateResult result) {
        if (result == null || parameter == null) {
            return false;
        }
        Object object = result.getParameter();
        if (object == null) {
            return false;
        }
        if (object == parameter) {
            return true;
        }
        if (object instanceof List) {
            List list = ((List) object);
            if (!list.isEmpty()) {
                Object firstObj = list.get(0);
                if (parameter == firstObj) { // for context rename
                    return true;
                }

                // schema
                if (checkResultSchema(result, firstObj, parameter)) {
                    return true;
                }

            }

        }
        // schema
        if (checkResultSchema(result, object, parameter)) {
            return true;
        }
        // query for wizard
        if (parameter instanceof QueriesConnection && object instanceof Query) {
            for (Query query : (List<Query>) ((QueriesConnection) parameter).getQuery()) {
                if (query.getId().equals(((Query) object).getId())) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean checkResultSchema(UpdateResult result, Object object, Object parameter) {
        if (object == null || parameter == null) {
            return false;
        }
        // schema
        if (object instanceof IMetadataTable) { // 
            if (parameter instanceof ConnectionItem) { //
                String source = UpdateRepositoryUtils.getRepositorySourceName((ConnectionItem) parameter);
                if (result.getRemark() != null && result.getRemark().startsWith(source)) {
                    return true;
                }
            } else if (parameter instanceof org.talend.core.model.metadata.builder.connection.MetadataTable) {
                IMetadataTable table1 = ((IMetadataTable) object);
                MetadataTable table2 = (org.talend.core.model.metadata.builder.connection.MetadataTable) parameter;
                if (table1.getId() == null || table2.getId() == null) {
                    return table1.getLabel().equals(table2.getLabel());
                } else {
                    return table1.getId().equals(table2.getId());
                }
            } else if (parameter instanceof SAPFunctionUnit) {
                // check sap function and schema
                IMetadataTable table1 = ((IMetadataTable) object);
                return table1.getId().equals(((SAPFunctionUnit) parameter).getMetadataTable().getId());
            }

        }
        return false;
    }

    public static IEditorReference[] getEditors() {
        final List<IEditorReference> list = new ArrayList<IEditorReference>();
        Display.getDefault().syncExec(new Runnable() {

            public void run() {
                IEditorReference[] reference = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                        .getEditorReferences();
                list.addAll(Arrays.asList(reference));
            }
        });
        return list.toArray(new IEditorReference[0]);
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
    private List<UpdateResult> checkJobItemsForUpdate(IProgressMonitor parentMonitor, final Set<EUpdateItemType> types,
            final boolean onlySimpleShow) throws InterruptedException {
        if (types == null || types.isEmpty()) {
            return null;
        }

        final List<IEditorReference> list = new ArrayList<IEditorReference>();
        Display.getDefault().syncExec(new Runnable() {

            public void run() {
                IEditorReference[] reference = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                        .getEditorReferences();
                list.addAll(Arrays.asList(reference));
            }
        });

        List<IProcess> openedProcessList = CorePlugin.getDefault().getDesignerCoreService().getOpenedProcess(getEditors());

        try {
            List<UpdateResult> resultList = new ArrayList<UpdateResult>();
            int size = openedProcessList.size();
            List<IRepositoryObject> allVersionList = new ArrayList<IRepositoryObject>();
            //
            if (!onlyOpeningJob) {
                IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
                List<IRepositoryObject> updateList = new ArrayList<IRepositoryObject>();
                if (isItemIndexChecked() && parameter != null) {
                    updateList.addAll(updateObjList);
                } else {
                    List<IRepositoryObject> processList = factory.getAll(ERepositoryObjectType.PROCESS, true);
                    if (processList == null) {
                        processList = new ArrayList<IRepositoryObject>();
                    }
                    updateList.addAll(processList);
                    List<IRepositoryObject> jobletList = factory.getAll(ERepositoryObjectType.JOBLET, true);
                    if (jobletList != null) {
                        if (!processList.isEmpty()) {
                            processList.clear();
                        }
                        processList.addAll(jobletList);
                    }
                    updateList.addAll(processList);
                }

                // must match TalendDesignerPrefConstants.CHECK_ONLY_LAST_VERSION
                boolean checkOnlyLastVersion = Boolean.parseBoolean(CorePlugin.getDefault().getDesignerCoreService()
                        .getPreferenceStore("checkOnlyLastVersion")); //$NON-NLS-1$
                // get all version
                allVersionList = new ArrayList<IRepositoryObject>((int) (updateList.size() * 1.1));
                for (IRepositoryObject repositoryObj : updateList) {
                    if (!checkOnlyLastVersion) {
                        List<IRepositoryObject> allVersion = factory.getAllVersion(repositoryObj.getId());
                        for (IRepositoryObject object : allVersion) {
                            if (factory.getStatus(object) != ERepositoryStatus.LOCK_BY_OTHER
                                    && factory.getStatus(object) != ERepositoryStatus.LOCK_BY_USER) {
                                allVersionList.add(object);
                            }
                        }
                    } else {
                        // assume that repositoryObj is the last version, otherwise we should call
                        // factory.getLastVersion(repositoryObj.getId());
                        IRepositoryObject lastVersion = repositoryObj; // factory.getLastVersion(repositoryObj.getId());
                        ERepositoryStatus status = factory.getStatus(lastVersion);
                        if (status != ERepositoryStatus.LOCK_BY_OTHER && status != ERepositoryStatus.LOCK_BY_USER) {
                            allVersionList.add(lastVersion);
                        }
                    }
                    size = allVersionList.size() + openedProcessList.size();
                }

            }
            //
            parentMonitor.beginTask(Messages.getString("RepositoryUpdateManager.Check"), size); //$NON-NLS-1$
            parentMonitor.setTaskName(Messages.getString("RepositoryUpdateManager.ItemsToUpdate")); //$NON-NLS-1$
            checkMonitorCanceled(parentMonitor);

            if (!onlyOpeningJob) {// && !isItemIndexChecked()
                MultiKeyMap openProcessMap = createOpenProcessMap(openedProcessList);
                int index = 0;
                for (IRepositoryObject repositoryObj : allVersionList) {
                    System.out.println(index + ":" + repositoryObj.getLabel());
                    checkMonitorCanceled(parentMonitor);
                    Item item = repositoryObj.getProperty().getItem();
                    // avoid the opened job
                    if (isOpenedItem(item, openProcessMap)) {
                        continue;
                    }
                    parentMonitor.subTask(getUpdateJobInfor(repositoryObj.getProperty()));
                    List<UpdateResult> updatesNeededFromItems = getUpdatesNeededFromItems(parentMonitor, item, types,
                            onlySimpleShow);
                    if (updatesNeededFromItems != null && !updatesNeededFromItems.isEmpty()) {
                        resultList.addAll(updatesNeededFromItems);
                        System.out.println(index);
                    }
                    index++;
                    parentMonitor.worked(1);
                }
            }
            // opened job
            for (IProcess process : openedProcessList) {
                checkMonitorCanceled(parentMonitor);
                parentMonitor.subTask(getUpdateJobInfor(process.getProperty()));

                List<UpdateResult> resultFromProcess = getResultFromProcess(process, types, onlySimpleShow);
                if (resultFromProcess != null) {
                    resultList.addAll(resultFromProcess);
                }
                parentMonitor.worked(1);
            }

            if (!onlyOpeningJob) {
                // Ok, you also need to update the job setting in "create job with template"
                List<UpdateResult> templateSetUpdate = checkSettingInJobTemplateWizard();
                if (templateSetUpdate != null) {
                    resultList.addAll(templateSetUpdate);
                }
            }
            parentMonitor.done();
            return resultList;
        } catch (PersistenceException e) {
            //
        }

        return null;
    }

    private void checkMonitorCanceled(IProgressMonitor monitor) {
        if (monitor.isCanceled()) {
            throw new OperationCanceledException(UpdatesConstants.MONITOR_IS_CANCELED);
        }
    }

    /**
     * YeXiaowei Comment method "checkSettingInJobTemplateWizard".
     */
    private List<UpdateResult> checkSettingInJobTemplateWizard() {
        List<IProcess> processes = CorePlugin.getDefault().getDesignerCoreService().getProcessForJobTemplate();

        if (processes == null || processes.isEmpty()) {
            return null;
        }

        List<UpdateResult> result = new ArrayList<UpdateResult>();

        for (IProcess process : processes) {
            if (process instanceof IProcess2) {
                IProcess2 nowProcess = (IProcess2) process;
                nowProcess.getUpdateManager().checkAllModification();
                List<UpdateResult> results = nowProcess.getUpdateManager().getUpdatesNeeded();
                if (results != null) {
                    result.addAll(results);
                }
            }
        }

        return result;
    }

    /**
     * Create a hashmap for fash lookup of the specified IProcess.
     * 
     * @param openedProcessList
     * @return
     */
    private MultiKeyMap createOpenProcessMap(List<IProcess> openedProcessList) {
        MultiKeyMap map = new MultiKeyMap();
        if (openedProcessList != null) {
            for (IProcess process : openedProcessList) {
                map.put(process.getId(), process.getLabel(), process.getVersion(), process);
            }
        }
        return map;
    }

    private boolean isOpenedItem(Item openedItem, MultiKeyMap openProcessMap) {
        if (openedItem == null) {
            return false;
        }
        Property property = openedItem.getProperty();
        return (openProcessMap.get(property.getId(), property.getLabel(), property.getVersion()) != null);
    }

    protected List<UpdateResult> getResultFromProcess(IProcess process, final Set<EUpdateItemType> types,
            final boolean onlySimpleShow) {
        if (process == null || types == null) {
            return null;
        }

        List<UpdateResult> resultList = new ArrayList<UpdateResult>();
        if (process instanceof IProcess2) {
            IProcess2 process2 = (IProcess2) process;
            // context rename and context group
            IContextManager contextManager = process2.getContextManager();
            if (contextManager instanceof JobContextManager) {
                JobContextManager jobContextManager = (JobContextManager) contextManager;
                jobContextManager.setRepositoryRenamedMap(getContextRenamedMap());
                jobContextManager.setNewParametersMap(getNewParametersMap());
                Map<ContextItem, List<IContext>> repositoryAddGroupContext = getRepositoryAddGroupContext();
                Collection<List<IContext>> values = repositoryAddGroupContext.values();
                Iterator<List<IContext>> iterator = values.iterator();
                List<IContext> listIContext = new ArrayList<IContext>();
                if (iterator != null && iterator.hasNext()) {
                    List<IContext> next = iterator.next();
                    listIContext.addAll(next);
                }
                jobContextManager.setAddGroupContext(listIContext);
                jobContextManager.setAddContextGroupMap(repositoryAddGroupContext);
            }
            // schema rename
            IUpdateManager updateManager = process2.getUpdateManager();
            if (updateManager instanceof AbstractUpdateManager) {
                AbstractUpdateManager manager = (AbstractUpdateManager) updateManager;
                manager.setSchemaRenamedMap(getSchemaRenamedMap());
            }
            //
            for (EUpdateItemType type : types) {
                List<UpdateResult> updatesNeeded = updateManager.getUpdatesNeeded(type, onlySimpleShow);
                if (updatesNeeded != null) {
                    resultList.addAll(updatesNeeded);
                }
            }
        }
        return resultList;
    }

    protected List<UpdateResult> getUpdatesNeededFromItems(IProgressMonitor parentMonitor, Item item,
            final Set<EUpdateItemType> types, final boolean onlySimpleShow) {
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
            List<UpdateResult> resultFromProcess = getResultFromProcess(process2, types, onlySimpleShow);
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

    public static String getUpdateJobInfor(Property property) {
        StringBuffer infor = new StringBuffer();
        String prefix = UpdatesConstants.JOB;
        String label = null;
        String version = null;
        if (property.getItem() instanceof JobletProcessItem) { // for joblet
            prefix = UpdatesConstants.JOBLET;
        }
        label = property.getLabel();
        version = property.getVersion();
        infor.append(prefix);
        if (label != null) {
            infor.append(UpdatesConstants.SPACE);
            infor.append(label);
            infor.append(UpdatesConstants.SPACE);
            infor.append(version);
        }
        return infor.toString();

    }

    /**
     * 
     * ggu Comment method "updateSchema".
     * 
     * for repository wizard.
     */
    public static boolean updateDBConnection(ConnectionItem connection) {
        return updateDBConnection(connection, true, false);
    }

    public static boolean updateDBConnection(ConnectionItem connection, boolean show) {
        return updateDBConnection(connection, show, false);
    }

    /**
     * 
     * ggu Comment method "updateQuery".
     * 
     * if show is false, will work for context menu action.
     */
    public static boolean updateDBConnection(ConnectionItem connectionItem, boolean show, final boolean onlySimpleShow) {
        List<IRepositoryObject> updateList = new ArrayList<IRepositoryObject>();
        IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
        List<RelationshipItemBuilder.Relation> relations = RelationshipItemBuilder.getInstance().getItemsRelatedTo(
                connectionItem.getProperty().getId(), ItemCacheManager.LATEST_VERSION, RelationshipItemBuilder.PROPERTY_RELATION);
        for (RelationshipItemBuilder.Relation relation : relations) {
            try {
                IRepositoryObject obj = factory.getLastVersion(relation.getId());
                if (obj != null) {
                    updateList.add(obj);
                }
            } catch (PersistenceException e) {
                ExceptionHandler.process(e);
            }
        }
        RepositoryUpdateManager repositoryUpdateManager = new RepositoryUpdateManager(connectionItem.getConnection(), updateList) {

            @Override
            public Set<EUpdateItemType> getTypes() {
                Set<EUpdateItemType> types = new HashSet<EUpdateItemType>();
                types.add(EUpdateItemType.NODE_PROPERTY);
                types.add(EUpdateItemType.JOB_PROPERTY_EXTRA);
                types.add(EUpdateItemType.JOB_PROPERTY_STATS_LOGS);
                return types;
            }

        };
        return repositoryUpdateManager.doWork(true, false);
    }

    /**
     * 
     * ggu Comment method "updateSchema".
     * 
     * for repository wizard.
     */
    public static boolean updateFileConnection(ConnectionItem connection) {
        return updateFileConnection(connection, true, false);
    }

    /**
     * 
     * ggu Comment method "updateQuery".
     * 
     * if show is false, will work for context menu action.
     */
    public static boolean updateFileConnection(ConnectionItem connectionItem, boolean show, boolean onlySimpleShow) {
        IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
        List<IRepositoryObject> updateList = new ArrayList<IRepositoryObject>();
        List<RelationshipItemBuilder.Relation> relations = RelationshipItemBuilder.getInstance().getItemsRelatedTo(
                connectionItem.getProperty().getId(), ItemCacheManager.LATEST_VERSION, RelationshipItemBuilder.PROPERTY_RELATION);
        for (RelationshipItemBuilder.Relation relation : relations) {
            try {
                IRepositoryObject obj = factory.getLastVersion(relation.getId());
                if (obj != null) {
                    updateList.add(obj);
                }
            } catch (PersistenceException e) {
                ExceptionHandler.process(e);
            }
        }
        RepositoryUpdateManager repositoryUpdateManager = new RepositoryUpdateManager(connectionItem.getConnection(), updateList) {

            @Override
            public Set<EUpdateItemType> getTypes() {
                Set<EUpdateItemType> types = new HashSet<EUpdateItemType>();
                types.add(EUpdateItemType.NODE_PROPERTY);
                types.add(EUpdateItemType.NODE_SCHEMA);
                return types;
            }

        };
        return repositoryUpdateManager.doWork(show, onlySimpleShow);
    }

    @SuppressWarnings("unchecked")
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

    @SuppressWarnings("unchecked")
    public static Map<String, String> getTableIdAndNameMap(SAPFunctionUnit functionUnit) {
        if (functionUnit == null) {
            return Collections.emptyMap();
        }
        Map<String, String> idAndNameMap = new HashMap<String, String>();
        EList tables = functionUnit.getTables();
        if (tables != null) {
            for (MetadataTable table : (List<MetadataTable>) tables) {
                idAndNameMap.put(table.getId(), table.getLabel());
            }
        }
        return idAndNameMap;
    }

    public static Map<String, String> getOldTableIdAndNameMap(ConnectionItem connItem, MetadataTable metadataTable,
            boolean creation) {
        Map<String, String> oldTableMap = getTableIdAndNameMap(connItem);
        if (creation && metadataTable != null) {
            oldTableMap.remove(metadataTable.getId());
        }
        return oldTableMap;
    }

    public static Map<String, String> getOldTableIdAndNameMap(SAPFunctionUnit functionUnit, MetadataTable metadataTable,
            boolean creation) {
        Map<String, String> oldTableMap = getTableIdAndNameMap(functionUnit);
        if (creation && metadataTable != null) {
            oldTableMap.remove(metadataTable.getId());
        }
        return oldTableMap;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, String> getSchemaRenamedMap(ConnectionItem connItem, Map<String, String> oldTableMap) {
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

    @SuppressWarnings("unchecked")
    public static Map<String, String> getSchemaRenamedMap(SAPFunctionUnit functionUnit, ConnectionItem connItem,
            Map<String, String> oldTableMap) {
        if (functionUnit == null || oldTableMap == null) {
            return Collections.emptyMap();
        }

        Map<String, String> schemaRenamedMap = new HashMap<String, String>();

        final String prefix = connItem.getProperty().getId() + UpdatesConstants.SEGMENT_LINE;
        EList tables = functionUnit.getTables();
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

    /**
     * 
     * ggu Comment method "updateSchema".
     * 
     * for repository wizard.
     */
    public static boolean updateSingleSchema(ConnectionItem connItem, final MetadataTable newTable,
            final IMetadataTable oldMetadataTable, Map<String, String> oldTableMap) {
        if (connItem == null) {
            return false;
        }
        Map<String, String> schemaRenamedMap = RepositoryUpdateManager.getSchemaRenamedMap(connItem, oldTableMap);
        boolean update = !schemaRenamedMap.isEmpty();

        if (!update) {
            if (newTable != null && oldMetadataTable != null && oldTableMap.containsKey(newTable.getId())) {
                IMetadataTable newMetadataTable = ConvertionHelper.convert(newTable);
                update = !oldMetadataTable.sameMetadataAs(newMetadataTable, IMetadataColumn.OPTIONS_NONE);
            }
        }
        if (update) {
            // update
            return updateSchema(newTable, connItem, schemaRenamedMap, true, false);
        }
        return false;
    }

    public static boolean updateMultiSchema(ConnectionItem connItem, List<IMetadataTable> oldMetadataTable,
            Map<String, String> oldTableMap) {
        if (connItem == null) {
            return false;
        }
        Map<String, String> schemaRenamedMap = RepositoryUpdateManager.getSchemaRenamedMap(connItem, oldTableMap);
        boolean update = !schemaRenamedMap.isEmpty();

        if (!update) {
            if (oldMetadataTable != null) {
                List<IMetadataTable> newMetadataTable = RepositoryUpdateManager.getConversionMetadataTables(connItem
                        .getConnection());
                update = !RepositoryUpdateManager.sameAsMetadatTable(newMetadataTable, oldMetadataTable, oldTableMap);
            }
        }
        // update
        if (update) {
            return updateSchema(connItem, connItem, schemaRenamedMap, true, false);
        }
        return false;

    }

    public static boolean updateMultiSchema(SAPFunctionUnit functionUnit, ConnectionItem connItem,
            List<IMetadataTable> oldMetadataTable, Map<String, String> oldTableMap) {
        if (functionUnit == null) {
            return false;
        }
        Map<String, String> schemaRenamedMap = RepositoryUpdateManager.getSchemaRenamedMap(functionUnit, connItem, oldTableMap);
        boolean update = !schemaRenamedMap.isEmpty();

        if (!update) {
            if (oldMetadataTable != null) {
                List<IMetadataTable> newMetadataTable = RepositoryUpdateManager.getConversionMetadataTables(functionUnit);
                update = !RepositoryUpdateManager.sameAsMetadatTable(newMetadataTable, oldMetadataTable, oldTableMap);
            }
        }
        // update
        if (update) {
            return updateSchema(functionUnit, connItem, schemaRenamedMap, true, false);
        }
        return false;

    }

    private static boolean sameAsMetadatTable(List<IMetadataTable> newTables, List<IMetadataTable> oldTables,
            Map<String, String> oldTableMap) {
        if (newTables == null || oldTables == null) {
            return false;
        }

        Map<String, IMetadataTable> id2TableMap = new HashMap<String, IMetadataTable>();
        for (IMetadataTable oldTable : oldTables) {
            id2TableMap.put(oldTable.getId(), oldTable);
        }

        for (IMetadataTable newTable : newTables) {
            IMetadataTable oldTable = id2TableMap.get(newTable.getId());
            if (oldTableMap.containsKey(newTable.getId())) { // not a new created table.
                if (oldTable == null) {
                    return false;
                } else {
                    if (!newTable.sameMetadataAs(oldTable, IMetadataColumn.OPTIONS_NONE)) {
                        return false;
                    }
                }
            }
        }
        return true;

    }

    /**
     * 
     * xye Comment method "updateSAPFunction".
     * 
     * @param sapFunction
     * @param show
     * @return
     */
    public static boolean updateSAPFunction(final SAPFunctionUnit sapFunction, boolean show, boolean onlySimpleShow) {
        IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
        List<IRepositoryObject> updateList = new ArrayList<IRepositoryObject>();
        List<RelationshipItemBuilder.Relation> relations = RelationshipItemBuilder.getInstance().getItemsRelatedTo(
                sapFunction.getId(), ItemCacheManager.LATEST_VERSION, RelationshipItemBuilder.PROPERTY_RELATION);
        for (RelationshipItemBuilder.Relation relation : relations) {
            try {
                IRepositoryObject obj = factory.getLastVersion(relation.getId());
                if (obj != null) {
                    updateList.add(obj);
                }
            } catch (PersistenceException e) {
                ExceptionHandler.process(e);
            }
        }
        RepositoryUpdateManager repositoryUpdateManager = new RepositoryUpdateManager(sapFunction, updateList) {

            @Override
            public Set<EUpdateItemType> getTypes() {
                Set<EUpdateItemType> types = new HashSet<EUpdateItemType>();
                types.add(EUpdateItemType.NODE_SAP_FUNCTION);
                types.add(EUpdateItemType.NODE_SCHEMA);
                return types;
            }

        };

        return repositoryUpdateManager.doWork(show, onlySimpleShow);
    }

    /**
     * 
     * xye Comment method "updateSAPFunction".
     * 
     * @param sapFunction
     * @return
     */
    public static boolean updateSAPFunction(final SAPFunctionUnit sapFunction) {
        return updateSAPFunction(sapFunction, true, false);
    }

    /**
     * 
     * ggu Comment method "updateSchema".
     * 
     * if show is false, will work for context menu action.
     */
    public static boolean updateSchema(final MetadataTable metadataTable, boolean show) {

        return updateSchema(metadataTable, null, null, show, false);
    }

    public static boolean updateSchema(final MetadataTable metadataTable, RepositoryNode node, boolean show,
            boolean onlySimpleShow) {
        ConnectionItem connItem = (ConnectionItem) node.getObject().getProperty().getItem();
        return updateSchema(metadataTable, connItem, null, show, onlySimpleShow);
    }

    private static boolean updateSchema(final Object table, ConnectionItem connItem, Map<String, String> schemaRenamedMap,
            boolean show, boolean onlySimpleShow) {
        IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
        List<IRepositoryObject> updateList = new ArrayList<IRepositoryObject>();
        List<RelationshipItemBuilder.Relation> relations = RelationshipItemBuilder.getInstance().getItemsRelatedTo(
                ((ConnectionItem) connItem).getProperty().getId(), ItemCacheManager.LATEST_VERSION,
                RelationshipItemBuilder.PROPERTY_RELATION);

        for (RelationshipItemBuilder.Relation relation : relations) {
            try {
                IRepositoryObject obj = factory.getLastVersion(relation.getId());
                if (obj != null) {
                    updateList.add(obj);
                }
            } catch (PersistenceException e) {
                ExceptionHandler.process(e);
            }
        }
        RepositoryUpdateManager repositoryUpdateManager = new RepositoryUpdateManager(table, updateList) {

            @Override
            public Set<EUpdateItemType> getTypes() {
                Set<EUpdateItemType> types = new HashSet<EUpdateItemType>();
                types.add(EUpdateItemType.NODE_SCHEMA);
                return types;
            }

        };

        // set renamed schema
        repositoryUpdateManager.setSchemaRenamedMap(schemaRenamedMap);

        return repositoryUpdateManager.doWork(show, onlySimpleShow);
    }

    /**
     * 
     * ggu Comment method "updateQuery".
     * 
     * for repository wizard.
     */
    public static boolean updateQuery(Query query) {

        return updateQueryObject(query, true, false);
    }

    public static boolean updateQuery(Query query, RepositoryNode node) {
        return updateQueryObject(query, true, false, node);
    }

    /**
     * 
     * ggu Comment method "updateQuery".
     * 
     * if show is false, will work for context menu action.
     */
    public static boolean updateQuery(Query query, boolean show) {
        return updateQueryObject(query, show, false);
    }

    public static boolean updateQuery(Query query, RepositoryNode node, boolean show, boolean onlySimpleShow) {
        return updateQueryObject(query, show, onlySimpleShow, node);
    }

    private static boolean updateQueryObject(Object parameter, boolean show, boolean onlySimpleShow) {
        IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
        List<IRepositoryObject> updateList = new ArrayList<IRepositoryObject>();
        List<RelationshipItemBuilder.Relation> relations = null;
        if (parameter instanceof Query) {
            relations = RelationshipItemBuilder.getInstance().getItemsRelatedTo(((Query) parameter).getId(),
                    ItemCacheManager.LATEST_VERSION, RelationshipItemBuilder.QUERY_RELATION);
        } else if (parameter instanceof QueriesConnection) {
            relations = RelationshipItemBuilder.getInstance().getItemsRelatedTo(
                    ((QueriesConnection) parameter).getConnection().getId(), ItemCacheManager.LATEST_VERSION,
                    RelationshipItemBuilder.QUERY_RELATION);
        }
        for (RelationshipItemBuilder.Relation relation : relations) {
            try {
                IRepositoryObject obj = factory.getLastVersion(relation.getId());
                if (obj != null) {
                    updateList.add(obj);
                }
            } catch (PersistenceException e) {
                ExceptionHandler.process(e);
            }
        }
        RepositoryUpdateManager repositoryUpdateManager = new RepositoryUpdateManager(parameter, updateList) {

            @Override
            public Set<EUpdateItemType> getTypes() {
                Set<EUpdateItemType> types = new HashSet<EUpdateItemType>();
                types.add(EUpdateItemType.NODE_QUERY);
                return types;
            }

        };
        return repositoryUpdateManager.doWork(show, onlySimpleShow);
    }

    private static boolean updateQueryObject(Object parameter, boolean show, boolean onlySimpleShow, RepositoryNode node) {
        Item item = node.getObject().getProperty().getItem();
        IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
        List<IRepositoryObject> updateList = new ArrayList<IRepositoryObject>();
        List<RelationshipItemBuilder.Relation> relations = null;
        if (parameter instanceof Query) {
            String id = item.getProperty().getId() + " - " + ((Query) parameter).getLabel();
            relations = RelationshipItemBuilder.getInstance().getItemsRelatedTo(id, ItemCacheManager.LATEST_VERSION,
                    RelationshipItemBuilder.QUERY_RELATION);
        }
        for (RelationshipItemBuilder.Relation relation : relations) {
            try {
                IRepositoryObject obj = factory.getLastVersion(relation.getId());
                if (obj != null) {
                    updateList.add(obj);
                }
            } catch (PersistenceException e) {
                ExceptionHandler.process(e);
            }
        }
        RepositoryUpdateManager repositoryUpdateManager = new RepositoryUpdateManager(parameter, updateList) {

            @Override
            public Set<EUpdateItemType> getTypes() {
                Set<EUpdateItemType> types = new HashSet<EUpdateItemType>();
                types.add(EUpdateItemType.NODE_QUERY);
                return types;
            }

        };
        return repositoryUpdateManager.doWork(show, onlySimpleShow);
    }

    /**
     * 
     * ggu Comment method "updateContext".
     * 
     * if show is false, will work for context menu action.
     */
    public static boolean updateContext(ContextItem item, boolean show) {
        return updateContext(null, item, show, false);
    }

    public static boolean updateContext(ContextItem item, boolean show, boolean onlySimpleShow) {
        return updateContext(null, item, show, onlySimpleShow);
    }

    /**
     * 
     * ggu Comment method "updateContext".
     * 
     * for repository wizard.
     */
    public static boolean updateContext(JobContextManager repositoryContextManager, ContextItem item) {

        return updateContext(repositoryContextManager, item, true, false);
    }

    private static boolean updateContext(JobContextManager repositoryContextManager, ContextItem item, boolean show,
            boolean onlySimpleShow) {
        IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
        List<IRepositoryObject> updateList = new ArrayList<IRepositoryObject>();
        List<RelationshipItemBuilder.Relation> relations = RelationshipItemBuilder.getInstance().getItemsRelatedTo(
                item.getProperty().getId(), ItemCacheManager.LATEST_VERSION, RelationshipItemBuilder.CONTEXT_RELATION);
        for (RelationshipItemBuilder.Relation relation : relations) {
            try {
                IRepositoryObject obj = factory.getLastVersion(relation.getId());
                if (obj != null) {
                    updateList.add(obj);
                }
            } catch (PersistenceException e) {
                ExceptionHandler.process(e);
            }
        }

        RepositoryUpdateManager repositoryUpdateManager = new RepositoryUpdateManager(item, updateList) {

            @Override
            public Set<EUpdateItemType> getTypes() {
                Set<EUpdateItemType> types = new HashSet<EUpdateItemType>();
                types.add(EUpdateItemType.CONTEXT);
                types.add(EUpdateItemType.CONTEXT_GROUP);
                return types;
            }

        };
        if (repositoryContextManager != null) {
            // add for bug 9119 context group
            Map<ContextItem, List<IContext>> repositoryContextGroupMap = new HashMap<ContextItem, List<IContext>>();
            List<IContext> addGroupContext = repositoryContextManager.getAddGroupContext();
            if (!addGroupContext.isEmpty()) {
                repositoryContextGroupMap.put(item, addGroupContext);
            }
            repositoryUpdateManager.setRepositoryAddGroupContext(repositoryContextGroupMap);

            Map<ContextItem, Map<String, String>> repositoryRenamedMap = new HashMap<ContextItem, Map<String, String>>();
            if (!repositoryContextManager.getNameMap().isEmpty()) {
                repositoryRenamedMap.put(item, repositoryContextManager.getNameMap());
            }
            repositoryUpdateManager.setContextRenamedMap(repositoryRenamedMap);

            // newly added parameters
            Map<ContextItem, Set<String>> newParametersMap = new HashMap<ContextItem, Set<String>>();
            if (!repositoryContextManager.getNewParameters().isEmpty()) {
                newParametersMap.put(item, repositoryContextManager.getNewParameters());
            }
            repositoryUpdateManager.setNewParametersMap(newParametersMap);

        }
        return repositoryUpdateManager.doWork(show, onlySimpleShow);
    }

    public Map<ContextItem, Set<String>> getNewParametersMap() {
        return newParametersMap;
    }

    public void setNewParametersMap(Map<ContextItem, Set<String>> newParametersMap) {
        this.newParametersMap = newParametersMap;
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

    @SuppressWarnings("unchecked")
    public static List<IMetadataTable> getConversionMetadataTables(Connection conn) {
        if (conn == null) {
            return Collections.emptyList();
        }
        List<IMetadataTable> tables = new ArrayList<IMetadataTable>();

        EList tables2 = conn.getTables();
        if (tables2 != null) {
            for (org.talend.core.model.metadata.builder.connection.MetadataTable originalTable : (List<org.talend.core.model.metadata.builder.connection.MetadataTable>) tables2) {
                IMetadataTable conversionTable = ConvertionHelper.convert(originalTable);
                tables.add(conversionTable);
            }
        }

        return tables;
    }

    @SuppressWarnings("unchecked")
    public static List<IMetadataTable> getConversionMetadataTables(SAPFunctionUnit functionUnit) {
        if (functionUnit == null) {
            return Collections.emptyList();
        }
        List<IMetadataTable> tables = new ArrayList<IMetadataTable>();

        EList tables2 = functionUnit.getTables();
        if (tables2 != null) {
            for (org.talend.core.model.metadata.builder.connection.MetadataTable originalTable : (List<org.talend.core.model.metadata.builder.connection.MetadataTable>) tables2) {
                IMetadataTable conversionTable = ConvertionHelper.convert(originalTable);
                tables.add(conversionTable);
            }
        }

        return tables;
    }

    public static boolean updateJoblet(JobletProcessItem item, boolean show, boolean onlySimpleShow) {
        IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
        List<IRepositoryObject> updateList = new ArrayList<IRepositoryObject>();
        List<RelationshipItemBuilder.Relation> relations = RelationshipItemBuilder.getInstance().getItemsRelatedTo(
                item.getProperty().getId(), ItemCacheManager.LATEST_VERSION, RelationshipItemBuilder.JOBLET_RELATION);
        for (RelationshipItemBuilder.Relation relation : relations) {
            try {
                IRepositoryObject obj = factory.getLastVersion(relation.getId());
                if (obj != null) {
                    updateList.add(obj);
                }
            } catch (PersistenceException e) {
                ExceptionHandler.process(e);
            }
        }
        RepositoryUpdateManager repositoryUpdateManager = new RepositoryUpdateManager(item, updateList) {

            @Override
            public Set<EUpdateItemType> getTypes() {
                Set<EUpdateItemType> types = new HashSet<EUpdateItemType>();
                types.add(EUpdateItemType.RELOAD);
                return types;
            }

        };
        repositoryUpdateManager.setOnlyOpeningJob(true);

        return repositoryUpdateManager.doWork(show, onlySimpleShow);
    }

    public boolean isItemIndexChecked() {
        IDesignerCoreService designerCoreService = CorePlugin.getDefault().getDesignerCoreService();
        IPreferenceStore preferenceStore = designerCoreService.getDesignerCorePreferenceStore();
        return preferenceStore.getBoolean(ITalendCorePrefConstants.ITEM_INDEX);
    }
}
