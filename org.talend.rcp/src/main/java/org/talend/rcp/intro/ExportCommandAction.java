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

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.internal.IWorkbenchHelpContextIds;
import org.talend.rcp.i18n.Messages;

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
        // qli comment
        // see the bug "0005942",the IRunnableWithProgress can't run on the linux.so the Job is used on here.
        if (exportAction != null) {
            Job job = new Job(Messages.getString("ExportCommandAction.refreshWorkspace")) { //$NON-NLS-1$

                @Override
                protected IStatus run(IProgressMonitor monitor) {
                    monitor.beginTask(ExportCommandAction.this.getToolTipText(), IProgressMonitor.UNKNOWN);
                    try {
                        ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, null);
                    } catch (CoreException e) {
                        e.printStackTrace();
                    }
                    monitor.done();
                    return Status.OK_STATUS;
                }
            };
            job.setUser(true);
            job.setPriority(Job.BUILD);
            job.schedule();

            // call system export
            exportAction.run();
        }
    }

}
