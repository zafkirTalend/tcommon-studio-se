// ============================================================================
//
// Copyright (C) 2006-2013 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.items.importexport.handlers;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.osgi.framework.FrameworkUtil;
import org.talend.commons.CommonsPlugin;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.utils.time.TimeMeasure;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.PluginChecker;
import org.talend.core.model.relationship.RelationshipItemBuilder;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.repository.model.ProxyRepositoryFactory;
import org.talend.core.runtime.CoreRuntimePlugin;
import org.talend.core.ui.IJobletProviderService;
import org.talend.repository.ProjectManager;
import org.talend.repository.RepositoryWorkUnit;
import org.talend.repository.items.importexport.handlers.imports.IImportItemsHandler;
import org.talend.repository.items.importexport.handlers.imports.IImportResourcesHandler;
import org.talend.repository.items.importexport.handlers.imports.ImportCacheHelper;
import org.talend.repository.items.importexport.handlers.imports.ImportExportHandlersRegistryReader;
import org.talend.repository.items.importexport.handlers.model.ItemRecord;
import org.talend.repository.items.importexport.handlers.model.ItemRecord.State;
import org.talend.repository.items.importexport.i18n.Messages;
import org.talend.repository.items.importexport.manager.ResourcesManager;
import org.talend.repository.model.IProxyRepositoryFactory;

/**
 * DOC ggu class global comment. Detailled comment
 */
public final class ImportExportHandlersManager {

    private static final ImportExportHandlersManager instance = new ImportExportHandlersManager();

    private final ImportExportHandlersRegistryReader registryReader;

    private IImportItemsHandler[] importHandlers;

    private IImportResourcesHandler[] resImportHandlers;

    private ImportExportHandlersManager() {
        registryReader = new ImportExportHandlersRegistryReader();
        registryReader.init();
    }

    public static ImportExportHandlersManager getInstance() {
        return instance;
    }

    public IImportItemsHandler[] getImportHandlers() {
        if (importHandlers == null) {
            importHandlers = registryReader.getImportHandlers();
        }
        return importHandlers;
    }

    public IImportResourcesHandler[] getResourceImportHandlers() {
        if (resImportHandlers == null) {
            resImportHandlers = registryReader.getImportResourcesHandlers();
        }
        return resImportHandlers;
    }

    private IImportItemsHandler findValidImportHandler(ResourcesManager resManager, IPath path) {
        for (IImportItemsHandler handler : getImportHandlers()) {
            if (handler.valid(resManager, path)) {
                return handler;
            }
        }
        // the path is not valid in current product, so ignore to import
        return null;
    }

    public List<ItemRecord> populateImportingItems(ResourcesManager resManager, boolean overwrite,
            IProgressMonitor progressMonitor) {
        IProgressMonitor monitor = progressMonitor;
        if (monitor == null) {
            monitor = new NullProgressMonitor();
        }
        TimeMeasure.display = CommonsPlugin.isDebugMode();
        TimeMeasure.displaySteps = CommonsPlugin.isDebugMode();
        TimeMeasure.measureActive = CommonsPlugin.isDebugMode();
        TimeMeasure.begin("populateItems"); //$NON-NLS-1$

        try {

            ImportCacheHelper.getInstance().beforePopulateItems();

            if (resManager == null) {
                return Collections.emptyList();
            }

            Set<IPath> resPaths = resManager.getPaths();

            monitor.beginTask(Messages.getString("ImportExportHandlersManager_populatingItemsMessage"), resPaths.size()); //$NON-NLS-1$

            List<ItemRecord> items = new ArrayList<ItemRecord>();

            for (IPath path : resPaths) {
                if (monitor.isCanceled()) {
                    return Collections.emptyList(); //
                }
                IImportItemsHandler importHandler = findValidImportHandler(resManager, path);
                if (importHandler != null) {
                    ItemRecord itemRecord = importHandler.calcItemRecord(progressMonitor, resManager, path, overwrite, items);
                    if (itemRecord != null) {
                        items.add(itemRecord);
                    }
                }
                monitor.worked(1);
            }
            return items;
        } finally {

            ImportCacheHelper.getInstance().afterPopulateItems();
            //
            TimeMeasure.end("populateItems"); //$NON-NLS-1$
            TimeMeasure.display = false;
            TimeMeasure.displaySteps = false;
            TimeMeasure.measureActive = false;
        }

    }

    public void importItemRecords(final IProgressMonitor progressMonitor, final ResourcesManager resManager,
            final List<ItemRecord> checkedItemRecords, final boolean overwrite, final ItemRecord[] allImportItemRecords,
            final IPath destinationPath) throws InvocationTargetException {
        TimeMeasure.display = CommonsPlugin.isDebugMode();
        TimeMeasure.displaySteps = CommonsPlugin.isDebugMode();
        TimeMeasure.measureActive = CommonsPlugin.isDebugMode();
        TimeMeasure.begin("ImportingItems"); //$NON-NLS-1$

        try {
            ImportCacheHelper.getInstance().beforeImportItems();

            if (resManager == null || checkedItemRecords.isEmpty()) {
                return;
            }
            progressMonitor.beginTask(
                    Messages.getString("ImportExportHandlersManager_importingItemsMessage"), checkedItemRecords.size() * 2 + 1); //$NON-NLS-1$

            /*
             * FIXME ????? why need sort it?
             * 
             * Maybe, Have done by priority for import handler, so no need.
             */
            // Collections.sort(itemRecords, new Comparator<ItemRecord>() {
            //
            // @Override
            // public int compare(ItemRecord o1, ItemRecord o2) {
            // if (o1.getProperty().getItem() instanceof RoutineItem && o2.getProperty().getItem() instanceof
            // RoutineItem) {
            // return 0;
            // } else if (!(o1.getProperty().getItem() instanceof RoutineItem)
            // && !(o2.getProperty().getItem() instanceof RoutineItem)) {
            // return 0;
            // } else if (o1.getProperty().getItem() instanceof RoutineItem) {
            // return -1;
            // } else {
            // return 1;
            // }
            // }
            // });

            //

            RepositoryWorkUnit repositoryWorkUnit = new RepositoryWorkUnit(
                    Messages.getString("ImportExportHandlersManager_importingItemsMessage")) { //$NON-NLS-1$

                @Override
                public void run() throws PersistenceException {
                    final IWorkspaceRunnable op = new IWorkspaceRunnable() {

                        @Override
                        public void run(final IProgressMonitor monitor) throws CoreException {

                            // bug 10520
                            final Set<String> overwriteDeletedItems = new HashSet<String>();
                            final Set<String> idDeletedBeforeImport = new HashSet<String>();

                            Map<String, String> nameToIdMap = new HashMap<String, String>();

                            for (ItemRecord itemRecord : checkedItemRecords) {
                                if (monitor.isCanceled()) {
                                    return;
                                }
                                if (itemRecord.isValid()) {
                                    if (itemRecord.getState() == State.ID_EXISTED) {
                                        String id = nameToIdMap.get(itemRecord.getProperty().getLabel()
                                                + ERepositoryObjectType.getItemType(itemRecord.getProperty().getItem())
                                                        .toString());
                                        if (id == null) {
                                            /*
                                             * if id exsist then need to genrate new id for this job,in this case the
                                             * job won't override the old one
                                             */
                                            id = EcoreUtil.generateUUID();
                                            nameToIdMap.put(itemRecord.getProperty().getLabel()
                                                    + ERepositoryObjectType.getItemType(itemRecord.getProperty().getItem())
                                                            .toString(), id);
                                        }
                                        itemRecord.getProperty().setId(id);
                                    }
                                }
                            }

                            importItemRecordsWithRelations(monitor, resManager, checkedItemRecords, overwrite,
                                    allImportItemRecords, destinationPath, overwriteDeletedItems, idDeletedBeforeImport);

                            if (PluginChecker.isJobLetPluginLoaded()) {
                                IJobletProviderService service = (IJobletProviderService) GlobalServiceRegister.getDefault()
                                        .getService(IJobletProviderService.class);
                                if (service != null) {
                                    service.loadComponentsFromProviders();
                                }
                            }
                            ImportCacheHelper.getInstance().checkDeletedFolders();
                            monitor.done();

                            TimeMeasure.step("importItemRecords", "before save"); //$NON-NLS-1$ //$NON-NLS-2$

                            if (RelationshipItemBuilder.getInstance().isNeedSaveRelations()) {
                                RelationshipItemBuilder.getInstance().saveRelations();

                                TimeMeasure.step("importItemRecords", "save relations"); //$NON-NLS-1$ //$NON-NLS-2$
                            } else {
                                // only save the project here if no relation need to be saved, since project will
                                // already be
                                // saved
                                // with relations
                                try {
                                    final IProxyRepositoryFactory factory = CoreRuntimePlugin.getInstance()
                                            .getProxyRepositoryFactory();
                                    factory.saveProject(ProjectManager.getInstance().getCurrentProject());
                                } catch (PersistenceException e) {
                                    throw new CoreException(new Status(IStatus.ERROR, FrameworkUtil.getBundle(this.getClass())
                                            .getSymbolicName(),
                                            Messages.getString("ImportExportHandlersManager_importingItemsError"), e)); //$NON-NLS-1$
                                }
                                TimeMeasure.step("importItemRecords", "save project"); //$NON-NLS-1$//$NON-NLS-2$
                            }
                        }

                        private void importItemRecordsWithRelations(final IProgressMonitor monitor,
                                final ResourcesManager manager, final List<ItemRecord> processingItemRecords,
                                final boolean overwriting, ItemRecord[] allPopulatedImportItemRecords, IPath destinationPath,
                                final Set<String> overwriteDeletedItems, final Set<String> idDeletedBeforeImport) {
                            for (ItemRecord itemRecord : processingItemRecords) {
                                if (monitor.isCanceled()) {
                                    return;
                                }
                                if (itemRecord.isImported()) {
                                    return; // have imported
                                }
                                final IImportItemsHandler importHandler = itemRecord.getImportHandler();
                                if (importHandler != null && itemRecord.isValid()) {
                                    List<ItemRecord> relatedItemRecord = importHandler.findRelatedItemRecord(monitor, manager,
                                            itemRecord, allPopulatedImportItemRecords);
                                    // import related items first
                                    if (importHandler.isImportRelatedItemRecordPrior()) {
                                        if (!relatedItemRecord.isEmpty()) {
                                            importItemRecordsWithRelations(monitor, manager, relatedItemRecord, overwriting,
                                                    allPopulatedImportItemRecords, destinationPath, overwriteDeletedItems,
                                                    idDeletedBeforeImport);
                                        }
                                    }
                                    if (monitor.isCanceled()) {
                                        return;
                                    }

                                    // will import
                                    importHandler.importItemRecord(monitor, manager, itemRecord, overwriting, destinationPath,
                                            overwriteDeletedItems, idDeletedBeforeImport);

                                    if (monitor.isCanceled()) {
                                        return;
                                    }
                                    // if import related items behind current item
                                    if (!importHandler.isImportRelatedItemRecordPrior()) {
                                        if (!relatedItemRecord.isEmpty()) {
                                            importItemRecordsWithRelations(monitor, manager, relatedItemRecord, overwriting,
                                                    allPopulatedImportItemRecords, destinationPath, overwriteDeletedItems,
                                                    idDeletedBeforeImport);
                                        }
                                    }

                                    importHandler.afterImportingItemRecords(monitor, manager, itemRecord);

                                    monitor.worked(1);
                                }
                            }
                        }

                    };
                    IWorkspace workspace = ResourcesPlugin.getWorkspace();
                    try {
                        ISchedulingRule schedulingRule = workspace.getRoot();
                        // the update the project files need to be done in the workspace runnable to avoid all
                        // notification
                        // of changes before the end of the modifications.
                        workspace.run(op, schedulingRule, IWorkspace.AVOID_UPDATE, progressMonitor);
                    } catch (CoreException e) {
                        // ?
                    }
                }
            };
            repositoryWorkUnit.setAvoidUnloadResources(true);
            repositoryWorkUnit.setUnloadResourcesAfterRun(true);
            ProxyRepositoryFactory.getInstance().executeRepositoryWorkUnit(repositoryWorkUnit);

            progressMonitor.done();

            if (ImportCacheHelper.getInstance().hasImportingError()) {
                throw new InvocationTargetException(new CoreException(
                        new Status(IStatus.ERROR, FrameworkUtil.getBundle(this.getClass()).getSymbolicName(),
                                Messages.getString("ImportExportHandlersManager_importingItemsError")))); //$NON-NLS-1$
            }
        } finally {

            ImportCacheHelper.getInstance().afterImportItems();
            //
            TimeMeasure.end("ImportingItems"); //$NON-NLS-1$
            TimeMeasure.display = false;
            TimeMeasure.displaySteps = false;
            TimeMeasure.measureActive = false;
        }
    }

    public void preImport(ResourcesManager resManager) {
        IImportResourcesHandler[] importResourcesHandlers = getResourceImportHandlers();
        for (IImportResourcesHandler resHandler : importResourcesHandlers) {
            resHandler.preImport(resManager);
        }

    }

    public void postImport(ResourcesManager resManager) {
        IImportResourcesHandler[] importResourcesHandlers = getResourceImportHandlers();
        for (IImportResourcesHandler resHandler : importResourcesHandlers) {
            resHandler.postImport(resManager);
        }

    }
}
