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
package org.talend.dataprofiler.core.ui.action.provider;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.DeleteResourceAction;
import org.eclipse.ui.ide.undo.DeleteResourcesOperation;
import org.eclipse.ui.ide.undo.WorkspaceUndoUtil;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;
import org.eclipse.ui.progress.WorkbenchJob;
import org.talend.commons.emf.EMFUtil;
import org.talend.cwm.dependencies.DependenciesHandler;
import org.talend.cwm.softwaredeployment.TdDataProvider;
import org.talend.dataprofiler.core.CorePlugin;
import org.talend.dataprofiler.core.ImageLib;
import org.talend.dataprofiler.core.PluginConstant;
import org.talend.dataprofiler.core.helper.AnaResourceFileHelper;
import org.talend.dataprofiler.core.helper.PrvResourceFileHelper;
import org.talend.dataprofiler.core.helper.RepResourceFileHelper;
import org.talend.dataquality.analysis.Analysis;
import org.talend.dataquality.reports.TdReport;
import org.talend.utils.sugars.TypedReturnCode;
import orgomg.cwm.objectmodel.core.Dependency;
import orgomg.cwm.objectmodel.core.ModelElement;

/**
 * DOC rli class global comment. Detailled comment
 */
public class DeleteResourceProvider extends CommonActionProvider {

    private static Logger log = Logger.getLogger(DeleteResourceProvider.class);

    private IFile selectedFile;

    private Object[] selectedObjects;

    public DeleteResourceProvider() {
    }

    private DeleteDataResourceAction deleteResourceAction;

    public void init(ICommonActionExtensionSite anExtensionSite) {

        if (anExtensionSite.getViewSite() instanceof ICommonViewerWorkbenchSite) {
            deleteResourceAction = new DeleteDataResourceAction(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
        }
    }

    /**
     * Adds a submenu to the given menu with the name "New Component".
     */
    public void fillContextMenu(IMenuManager menu) {
        IStructuredSelection selection = (IStructuredSelection) getContext().getSelection();
        Object firstElement = selection.getFirstElement();
        if (firstElement instanceof IFile) {
            selectedFile = (IFile) firstElement;
            selectedObjects = selection.toArray();
        }
        deleteResourceAction.selectionChanged(selection);
        menu.add(deleteResourceAction);
    }

    /**
     * DOC rli DeleteResourceProvider class global comment. Detailled comment
     */
    class DeleteDataResourceAction extends DeleteResourceAction {

        private static final String REPORTS = "reports";

        private static final String REPORT = "report";

        private static final String ANALYSES = "analyses";

        private static final String ANALYSIS = "analysis";

        protected boolean isDeleteContent = false;

        private Shell shell;

        public DeleteDataResourceAction(Shell shell) {
            super(shell);
            this.shell = shell;
            setImageDescriptor(ImageLib.getImageDescriptor(ImageLib.ACTION_DELETE));
        }

        /*
         * (non-Javadoc) Method declared on IAction.
         */
        public void run() {
            deleteResource();
        }

        public void deleteResource() {

            final IResource[] resources = getSelectedResourcesArray();
            // WARNING: do not query the selected resources more than once
            // since the selection may change during the run,
            // e.g. due to window activation when the prompt dialog is dismissed.
            // For more details, see Bug 60606 [Navigator] (data loss) Navigator
            // deletes/moves the wrong file
            if (!checkDeleteContent()) {
                return;
            }

            Job deletionCheckJob = new Job("Checking resources") {

                /*
                 * (non-Javadoc)
                 * 
                 * @see org.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.IProgressMonitor)
                 */
                protected IStatus run(IProgressMonitor monitor) {
                    if (resources.length == 0) {
                        return Status.CANCEL_STATUS;
                    }
                    scheduleDeleteJob(resources);
                    return Status.OK_STATUS;
                }

                /*
                 * (non-Javadoc)
                 * 
                 * @see org.eclipse.core.runtime.jobs.Job#belongsTo(java.lang.Object)
                 */
                public boolean belongsTo(Object family) {
                    if ("Deleting resources".equals(family)) {
                        return true;
                    }
                    return super.belongsTo(family);
                }
            };

            deletionCheckJob.schedule();
            removeDependencys();
        }

        /**
         * Return an array of the currently selected resources.
         * 
         * @return the selected resources
         */
        @SuppressWarnings("unchecked")
        private IResource[] getSelectedResourcesArray() {
            List selection = getSelectedResources();
            IResource[] resources = new IResource[selection.size()];
            selection.toArray(resources);
            return resources;
        }

        /**
         * DOC rli Comment method "getCheckImpactString".
         */
        private boolean checkDeleteContent() {
            List<String> impactNames = new ArrayList<String>();
            if (selectedFile.getName().endsWith(PluginConstant.PRV_SUFFIX)) {
                TypedReturnCode<TdDataProvider> returnValue = PrvResourceFileHelper.getInstance().readFromFile(selectedFile);
                TdDataProvider provider = returnValue.getObject();
                EList<Dependency> supplierDependencies = provider.getSupplierDependency();
                for (Dependency dependency : supplierDependencies) {
                    EList<ModelElement> clients = dependency.getClient();
                    for (ModelElement modelElement : clients) {
                        if (impactNames.contains(modelElement.getName())) {
                            continue;
                        }
                        impactNames.add(modelElement.getName());
                    }
                }
                popConfirmDialog(impactNames, provider.getName(), impactNames.size() > 1 ? ANALYSES : ANALYSIS);
                if (isDeleteContent) {
                    PrvResourceFileHelper.getInstance().clear();
                }
            } else if (selectedFile.getName().endsWith(PluginConstant.ANA_SUFFIX)) {
                Analysis findAnalysis = AnaResourceFileHelper.getInstance().findAnalysis(selectedFile);
                EList<Dependency> supplierDependencies = findAnalysis.getSupplierDependency();
                for (Dependency dependency : supplierDependencies) {
                    EList<ModelElement> clients = dependency.getClient();
                    for (ModelElement modelElement : clients) {
                        if (impactNames.contains(modelElement.getName()) || modelElement.getName() == null) {
                            continue;
                        }
                        impactNames.add(modelElement.getName());
                    }
                }
                popConfirmDialog(impactNames, findAnalysis.getName(), impactNames.size() > 1 ? REPORTS : REPORT);
                if (isDeleteContent) {
                    AnaResourceFileHelper.getInstance().clear();
                }

            } else if (selectedFile.getName().endsWith(PluginConstant.REP_SUFFIX)) {
                TdReport findReport = RepResourceFileHelper.getInstance().findReport(selectedFile);
                popConfirmDialog(impactNames, findReport.getName(), null);
                if (isDeleteContent) {
                    AnaResourceFileHelper.getInstance().clear();
                }
            } else {
                popConfirmDialog(impactNames, selectedFile.getName(), null);
            }
            return isDeleteContent;
        }

        private void removeDependencys() {
            for (Object selectedObj : selectedObjects) {
                String fileName = ((IFile) selectedObj).getName();
                EList<Dependency> supplierDependencies = null;
                if (fileName.endsWith(PluginConstant.PRV_SUFFIX)) {
                    TypedReturnCode<TdDataProvider> returnValue = PrvResourceFileHelper.getInstance().readFromFile(selectedFile);
                    TdDataProvider provider = returnValue.getObject();
                    supplierDependencies = provider.getSupplierDependency();
                } else if (fileName.endsWith(PluginConstant.ANA_SUFFIX)) {
                    Analysis findAnalysis = AnaResourceFileHelper.getInstance().findAnalysis(selectedFile);
                    supplierDependencies = findAnalysis.getSupplierDependency();
                }
                if (supplierDependencies != null) {
                    EMFUtil util = new EMFUtil();
                    for (Dependency dependency : supplierDependencies) {
                        List<Resource> modifiedResources = DependenciesHandler.getInstance().removeClientDependencies(dependency);                
                        // save now modified resources
                        util.getResourceSet().getResources().addAll(modifiedResources);
                    }
                    if (!util.save()) {
                        log.warn("Problem when saving resources " + util.getLastErrorMessage());
                    }
                    // refresh workspace in order to avoid unsynchronized resources
                    CorePlugin.getDefault().refreshWorkSpace();
                }
            }

        }

        /**
         * DOC rli Comment method "popConfirmDialog".
         * 
         * @param impactNames
         * @param provider
         */
        private void popConfirmDialog(List<String> impactNames, String resourceName, String relatedResourceType) {
            if (impactNames.size() != 0) {
                isDeleteContent = MessageDialog.openConfirm(null, "Confirm Resource Delete", "The following "
                        + relatedResourceType + " will be unusable!\n" + impactNames + "\n\n"
                        + "Are you sure you want to delele " + "\"" + resourceName + "\"?");
            } else {
                if (selectedObjects.length > 1) {
                    isDeleteContent = MessageDialog.openConfirm(null, "Confirm Resource Delete",
                            "Are you sure you want to delele these " + selectedObjects.length + " resources from file system?");
                } else {
                    isDeleteContent = MessageDialog.openConfirm(null, "Confirm Resource Delete",
                            "Are you sure you want to delele " + "\"" + resourceName + "\" from file system??");
                }
            }
        }

        /**
         * Schedule a job to delete the resources to delete.
         * 
         * @param resourcesToDelete
         */
        private void scheduleDeleteJob(final IResource[] resourcesToDelete) {
            // use a non-workspace job with a runnable inside so we can avoid
            // periodic updates
            Job deleteJob = new Job("Deleting resources") {

                public IStatus run(final IProgressMonitor monitor) {
                    try {
                        final DeleteResourcesOperation op = new DeleteResourcesOperation(resourcesToDelete, "Delete Resources",
                                isDeleteContent);
                        op.setModelProviderIds(getModelProviderIds());
                        // If we are deleting projects and their content, do not
                        // execute the operation in the undo history, since it cannot be
                        // properly restored. Just execute it directly so it won't be
                        // added to the undo history.
                        if (isDeleteContent) {
                            // We must compute the execution status first so that any user prompting
                            // or validation checking occurs. Do it in a syncExec because
                            // we are calling this from a Job.
                            WorkbenchJob statusJob = new WorkbenchJob("Status checking") { //$NON-NLS-1$

                                /*
                                 * (non-Javadoc)
                                 * 
                                 * @see org.eclipse.ui.progress.UIJob#runInUIThread(org.eclipse.core.runtime.IProgressMonitor)
                                 */
                                public IStatus runInUIThread(IProgressMonitor monitor) {
                                    return op.computeExecutionStatus(monitor);
                                }

                            };

                            statusJob.setSystem(true);
                            statusJob.schedule();
                            try { // block until the status is ready
                                statusJob.join();
                            } catch (InterruptedException e) {
                                // Do nothing as status will be a cancel
                            }

                            if (statusJob.getResult().isOK()) {
                                return op.execute(monitor, WorkspaceUndoUtil.getUIInfoAdapter(shell));
                            }
                            return statusJob.getResult();
                        }
                        return PlatformUI.getWorkbench().getOperationSupport().getOperationHistory().execute(op, monitor,
                                WorkspaceUndoUtil.getUIInfoAdapter(shell));
                    } catch (ExecutionException e) {
                        if (e.getCause() instanceof CoreException) {
                            return ((CoreException) e.getCause()).getStatus();
                        }
                        return new Status(IStatus.ERROR, "The IDE workbench plugin ID.", e.getMessage(), e);
                    }
                }

                /*
                 * (non-Javadoc)
                 * 
                 * @see org.eclipse.core.runtime.jobs.Job#belongsTo(java.lang.Object)
                 */
                public boolean belongsTo(Object family) {
                    if ("Deleting resources".equals(family)) {
                        return true;
                    }
                    return super.belongsTo(family);
                }

            };
            deleteJob.setUser(true);
            deleteJob.schedule();
        }

    }
}
