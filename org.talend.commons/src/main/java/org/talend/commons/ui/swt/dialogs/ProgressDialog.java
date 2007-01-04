// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
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
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.talend.commons.exception.ExceptionHandler;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public abstract class ProgressDialog {

    private Shell parentShell;

    /**
     * DOC amaumont ProgressDialog constructor comment.
     */
    public ProgressDialog(Shell parentShell) {
        super();
        this.parentShell = parentShell;
    }

    public void executeProcess() {
        final Display display = parentShell.getDisplay();
        final IRunnableWithProgress op = new IRunnableWithProgress() {

            public void run(final IProgressMonitor monitor) {
                display.asyncExec(new Runnable() {

                    public void run() {
                        try {
                            ProgressDialog.this.run(monitor);
                        } catch (RuntimeException e) {
                            ExceptionHandler.process(e);
                            monitor.done();
                        }
                    }

                });
            }
        };

        display.asyncExec(new Runnable() {

            public void run() {
                try {
                    new ProgressMonitorDialog(parentShell).run(true, true, op);
                } catch (InvocationTargetException e) {
                    ExceptionHandler.process(e);
                } catch (InterruptedException e) {
                    ExceptionHandler.process(e);
                }
            }
        });

    }

    public abstract void run(final IProgressMonitor monitor);

}
