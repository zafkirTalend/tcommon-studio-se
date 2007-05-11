// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006-2007 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
//
// ============================================================================
package org.talend.commons.ui.swt.dialogs;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.talend.commons.utils.threading.AsynchronousThreading;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public abstract class ProgressDialog {

    private Shell parentShell;

    private long timeBeforeShowDialog;

    /**
     * 
     * Show progress dialog when executeProcess() is called.
     * 
     * @param parentShell
     */
    public ProgressDialog(Shell parentShell) {
        super();
        this.parentShell = parentShell;
    }

    /**
     * 
     * Show progress dialog when executeProcess() is called and <code>timeBeforeShowDialog</code> is elapsed.
     * 
     * @param parentShell
     * @param timeBeforeShowDialog time before show dialog
     */
    public ProgressDialog(Shell parentShell, int timeBeforeShowDialog) {
        super();
        this.parentShell = parentShell;
        this.timeBeforeShowDialog = timeBeforeShowDialog;
    }

    public void executeProcess() throws InvocationTargetException, InterruptedException {
        final Display display = parentShell.getDisplay();
        final InvocationTargetException[] iteHolder = new InvocationTargetException[1];
        try {
            final IRunnableWithProgress op = new IRunnableWithProgress() {

                public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                    final InvocationTargetException[] iteHolder1 = new InvocationTargetException[1];
                    display.syncExec(new Runnable() {

                        public void run() {
                            try {
                                ProgressDialog.this.run(monitor);
                            } catch (InvocationTargetException e) {
                                // Pass it outside the workspace runnable
                                iteHolder1[0] = e;
                            } catch (InterruptedException e) {
                                // Re-throw as OperationCanceledException, which will be
                                // caught and re-thrown as InterruptedException below.
                                throw new OperationCanceledException(e.getMessage());
                            }
                            // CoreException and OperationCanceledException are propagated
                        }

                    });
                    // Re-throw the InvocationTargetException, if any occurred
                    if (iteHolder1[0] != null) {
                        throw iteHolder1[0];
                    }
                }
            };

            display.syncExec(new Runnable() {

                public void run() {
                    final ProgressMonitorDialog progressMonitorDialog = new ProgressMonitorDialog(parentShell);
                    if (timeBeforeShowDialog > 0) {
                        progressMonitorDialog.setOpenOnRun(false);
                        AsynchronousThreading asynchronousThreading = new AsynchronousThreading(timeBeforeShowDialog, true, display,
                                new Runnable() {
                            
                            public void run() {
                                progressMonitorDialog.open();
                            }
                        });
                        asynchronousThreading.start();
                    }
                        
                    try {
                        progressMonitorDialog.run(false, true, op);
                    } catch (InvocationTargetException e) {
                        // Pass it outside the workspace runnable
                        iteHolder[0] = e;
                    } catch (InterruptedException e) {
                        // Re-throw as OperationCanceledException, which will be
                        // caught and re-thrown as InterruptedException below.
                        throw new OperationCanceledException(e.getMessage());
                    }
                }
            });

        } catch (OperationCanceledException e) {
            throw new InterruptedException(e.getMessage());
        }
        // Re-throw the InvocationTargetException, if any occurred
        if (iteHolder[0] != null) {
            throw iteHolder[0];
        }
    }

    public abstract void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException;

}
