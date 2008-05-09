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
package org.talend.dataprofiler.core.tdq.ui.action.provider;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;
import org.talend.dataprofiler.core.ImageLib;
import org.talend.dataprofiler.core.helper.RepResourceFileHelper;
import org.talend.dataprofiler.core.ui.progress.ProgressUI;
import org.talend.dq.persistence.DatabasePersistence;


/**
 * DOC rli  class global comment. Detailled comment
 */
public class HistorizeReportProvider extends CommonActionProvider {

    private IFile selectedFile;

    /**
     * DOC rli HistorizeReportProvider constructor comment.
     */
    public HistorizeReportProvider() {
    }

    private HistorizeReportAction historizeReportAction;

    public void init(ICommonActionExtensionSite anExtensionSite) {

        if (anExtensionSite.getViewSite() instanceof ICommonViewerWorkbenchSite) {
            historizeReportAction = new HistorizeReportAction(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
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
        }
        menu.add(historizeReportAction);
    }

    
    /**
     * DOC rli HistorizeReportProvider class global comment. Detailled comment
     */
    class HistorizeReportAction extends Action {

        public HistorizeReportAction(Shell shell) {            
            super("Historize report");
            setImageDescriptor(ImageLib.getImageDescriptor(ImageLib.REPORT_HISTORIZE));
        }

        /*
         * (non-Javadoc) Method declared on IAction.
         */
        public void run() {
            final Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
            IRunnableWithProgress op = new IRunnableWithProgress() {

                public void run(IProgressMonitor monitor) throws InvocationTargetException {
                    DatabasePersistence databasePersistence = new DatabasePersistence();
                    databasePersistence.persist(RepResourceFileHelper.getInstance().findReport(selectedFile));
                }
            };
            try {
                ProgressUI.popProgressDialog(op, shell);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
