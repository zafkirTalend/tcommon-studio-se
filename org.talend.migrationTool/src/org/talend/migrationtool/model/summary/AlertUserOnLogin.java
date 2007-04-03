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
package org.talend.migrationtool.model.summary;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.model.migration.IMigrationToolService;
import org.talend.migrationtool.MigrationToolService;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 */
public class AlertUserOnLogin implements IStartup {

    public void earlyStartup() {
        final IWorkbench workbench = PlatformUI.getWorkbench();
        workbench.getDisplay().asyncExec(new Runnable() {

            public void run() {
                IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
                if (window != null) {
                    IMigrationToolService service = (IMigrationToolService) GlobalServiceRegister.getDefault().getService(
                            IMigrationToolService.class);

                    MigrationToolService m = (MigrationToolService) service;

                    if (!m.doneThisSession.isEmpty()) {
                        SummaryDialog loginDialog = new SummaryDialog(new Shell(), m.doneThisSession);
                        loginDialog.open();
                    }
                }
            }
        });
    }
}
