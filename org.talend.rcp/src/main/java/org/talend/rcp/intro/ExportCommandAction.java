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
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.IWorkbenchHelpContextIds;
import org.eclipse.ui.internal.WorkbenchImages;
import org.eclipse.ui.internal.WorkbenchMessages;

/**
 * ggu class global comment. Detailled comment
 */
public class ExportCommandAction extends Action {

    private IWorkbenchWindow window;

    /**
     */
    public ExportCommandAction(IWorkbenchWindow window) {
        if (window == null) {
            throw new IllegalArgumentException();
        }
        this.window = window;
        setId("talend_export"); ////$NON-NLS-1$
        setText(WorkbenchMessages.ExportResourcesAction_text);
        setToolTipText(WorkbenchMessages.ExportResourcesAction_toolTip);
        window.getWorkbench().getHelpSystem().setHelp(this, IWorkbenchHelpContextIds.EXPORT_ACTION);
        setImageDescriptor(WorkbenchImages.getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_EXPORT_WIZ));
    }

    @Override
    public void run() {
        super.run();
        // refresh resource, see bug 5425.
        try {
            IRunnableWithProgress runnable = new IRunnableWithProgress() {

                public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                    monitor.beginTask(ExportCommandAction.this.getText(), IProgressMonitor.UNKNOWN);
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

        IWorkbenchAction systemExportAction = ActionFactory.EXPORT.create(this.window);
        systemExportAction.run();
    }

}
