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
package org.talend.rcp.intro;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.internal.IWorkbenchHelpContextIds;

/**
 * ggu class global comment. Detailled comment
 */
public class ExportCommandAction extends Action {

    private IWorkbenchAction exportAction;

    /**
     */
    public ExportCommandAction(IWorkbenchWindow window) {
        exportAction = ActionFactory.EXPORT.create(window);
        setId("talend_" + exportAction.getId()); //$NON-NLS-1$
        setText(exportAction.getText());
        setToolTipText(exportAction.getToolTipText());
        setImageDescriptor(exportAction.getImageDescriptor());
        window.getWorkbench().getHelpSystem().setHelp(this, IWorkbenchHelpContextIds.EXPORT_ACTION);
    }

    @Override
    public void run() {
        super.run();
        if (exportAction != null) {
            // refresh resource, see bug 5425.
            try {
                IRunnableWithProgress runnable = new IRunnableWithProgress() {

                    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                        monitor.beginTask(ExportCommandAction.this.getToolTipText(), IProgressMonitor.UNKNOWN);
                        try {
                            ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, null);
                        } catch (CoreException e) {
                            e.printStackTrace();
                        }
                        monitor.done();

                    }
                };
                new ProgressMonitorDialog(Display.getCurrent().getActiveShell()).run(true, false, runnable);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // call system export
            exportAction.run();
        }
    }

}
