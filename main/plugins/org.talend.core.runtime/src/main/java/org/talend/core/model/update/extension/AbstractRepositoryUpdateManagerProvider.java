// ============================================================================
//
// Copyright (C) 2006-2014 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.model.update.extension;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.internal.progress.ProgressMonitorJobsDialog;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.core.model.update.IUpdateItemType;
import org.talend.core.model.update.RepositoryUpdateManager;
import org.talend.core.model.update.UpdateResult;
import org.talend.core.model.update.UpdatesConstants;
import org.talend.core.prefs.ITalendCorePrefConstants;
import org.talend.core.runtime.CoreRuntimePlugin;
import org.talend.designer.core.IDesignerCoreService;
import org.talend.repository.model.IProxyRepositoryFactory;

/**
 * created by ggu on Mar 27, 2014 Detailled comment
 * 
 */
public abstract class AbstractRepositoryUpdateManagerProvider implements IRepositoryUpdateManagerProvider {

    protected IProxyRepositoryFactory factory = CoreRuntimePlugin.getInstance().getProxyRepositoryFactory();

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.talend.core.model.update.extension.IRepositoryUpdateManagerProvider#validateAction(org.eclipse.jface.viewers
     * .TreeViewer, org.eclipse.jface.viewers.IStructuredSelection)
     */
    @Override
    public boolean validateAction(TreeViewer viewer, IStructuredSelection selection) {
        if (viewer != null && selection != null) {
            // only work for one item
            if (!selection.isEmpty() && selection.size() == 1) {
                // not read only
                if (!factory.isUserReadOnlyOnCurrentProject()) {
                    Object obj = selection.getFirstElement();
                    if (obj != null) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.talend.core.model.update.extension.IRepositoryUpdateManagerProvider#updateForRepository(java.lang.Object,
     * boolean)
     */
    @Override
    public boolean updateForRepository(Object node, boolean needConfirm) {
        if (!needPropagate(needConfirm)) {
            return false;
        }
        /*
         * NOTE: Most of functions are similar with RepositoryUpdateManager.doWork, so if update the old dowork, maybe
         * need update here too.
         */

        // retrieve Results
        List<UpdateResult> updateResults = retrieveUpdateResults(node);

        // valid results
        List<UpdateResult> validResults = validResults(updateResults);

        // do update
        if (doUpdate(validResults)) {
            return true;
        }
        // nothing to update
        RepositoryUpdateManager.openNoModificationDialog();
        return false;
    }

    /**
     * 
     * DOC ggu Comment method "needPropagate".
     * 
     * @param continued
     * @return
     */
    protected boolean needPropagate(boolean needConfirm) {
        if (needForcePropagation()) {
            return true;
        }
        if (needConfirm) {
            IDesignerCoreService designerCoreService = CoreRuntimePlugin.getInstance().getDesignerCoreService();
            boolean deactive = designerCoreService != null ? Boolean.parseBoolean(designerCoreService
                    .getPreferenceStore(ITalendCorePrefConstants.DEACTIVE_REPOSITORY_UPDATE)) : true;
            if (deactive) {// disable to do update
                return false;
            }

            boolean propagated = RepositoryUpdateManager.openPropagationDialog();
            if (propagated) { // update
                return true;
            }
        }
        // by default, will propagate always.
        return true;
    }

    protected boolean needForcePropagation() {
        return false; // diable to force propagating by default
    }

    /**
     * 
     * DOC ggu Comment method "retrieveUpdateResults".
     * 
     * @param node
     * @return
     */
    protected List<UpdateResult> retrieveUpdateResults(final Object node) {
        final List<UpdateResult> results = new ArrayList<UpdateResult>();
        boolean cancelable = !needForcePropagation();

        IRunnableWithProgress runnable = new IRunnableWithProgress() {

            @Override
            public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                List<UpdateResult> returnResult = retrieveUpdateResults(monitor, node);
                if (returnResult != null) {
                    results.addAll(returnResult);
                }
            }
        };

        try {
            final ProgressMonitorJobsDialog dialog = new ProgressMonitorJobsDialog(null);
            dialog.run(true, cancelable, runnable);

            // PlatformUI.getWorkbench().getProgressService().run(true, true, runnable);
            return results;
        } catch (InvocationTargetException e) {
            ExceptionHandler.process(e);
        } catch (InterruptedException e) {
            if (!e.getMessage().equals(UpdatesConstants.MONITOR_IS_CANCELED)) {
                ExceptionHandler.process(e); // if not cancele, log it.
            }
        }
        return Collections.emptyList();
    }

    protected abstract Set<IUpdateItemType> getTypes();

    /**
     * 
     * DOC ggu Comment method "retrieveUpdateResults".
     * 
     * retrieve the result for current node.
     * 
     * @param node
     * @return
     */
    protected abstract List<UpdateResult> retrieveUpdateResults(IProgressMonitor monitor, Object node);

    /**
     * 
     * DOC ggu Comment method "validResults".
     * 
     * will do some validation for results, if need.
     * 
     * @param updateResults
     * @return
     */
    protected List<UpdateResult> validResults(List<UpdateResult> updateResults) {
        return updateResults; // by default, nothing to check and valid. so retrurn it directly.
    }

    /**
     * 
     * DOC ggu Comment method "doUpdate".
     * 
     * @param validResults
     * @return
     */

    protected boolean doUpdate(List<UpdateResult> validResults) {
        if (validResults != null && !validResults.isEmpty()) {
            IDesignerCoreService designerCoreService = CoreRuntimePlugin.getInstance().getDesignerCoreService();
            if (designerCoreService != null) {
                return designerCoreService.executeUpdatesManager(validResults, false);
            }
        }
        return false;
    }
}
