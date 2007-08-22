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
package org.talend.rcp.intro;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.WorkbenchMessages;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.dialogs.ShowViewDialog;
import org.eclipse.ui.internal.misc.StatusUtil;
import org.eclipse.ui.statushandlers.StatusManager;
import org.eclipse.ui.views.IViewDescriptor;
import org.talend.rcp.i18n.Messages;

/**
 * Displays a window for view selection. <br/>
 * 
 * $Id$
 * 
 */
public class ShowViewAction extends Action {

    /**
     * Constructs a new ShowViewAction.
     */
    public ShowViewAction() {
        super(Messages.getString("ShowViewAction.actionLabel")); //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        final IWorkbenchPage page = window.getActivePage();
        if (page == null) {
            return;
        }

        final ShowViewDialog dialog = new ShowViewDialog(window, WorkbenchPlugin.getDefault().getViewRegistry());
        dialog.open();

        if (dialog.getReturnCode() == Window.CANCEL) {
            return;
        }

        final IViewDescriptor[] descriptors = dialog.getSelection();
        for (int i = 0; i < descriptors.length; ++i) {
            try {
                page.showView(descriptors[i].getId());
            } catch (PartInitException e) {
                StatusUtil.handleStatus(e.getStatus(), WorkbenchMessages.ShowView_errorTitle + ": " + e.getMessage(), //$NON-NLS-1$
                        StatusManager.SHOW);
            }
        }
    }
}
