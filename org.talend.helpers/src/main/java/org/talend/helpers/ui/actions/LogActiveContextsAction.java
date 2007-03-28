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
package org.talend.helpers.ui.actions;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.contexts.IContextService;
import org.talend.helpers.HelpersPlugin;
import org.talend.helpers.i18n.Messages;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class LogActiveContextsAction extends Action implements IWorkbenchWindowActionDelegate {

    private static Logger log = Logger.getLogger(LogActiveContextsAction.class);

    public LogActiveContextsAction() {
        super();
        this.setActionDefinitionId("logActiveContexts"); //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        IContextService contextService = (IContextService) HelpersPlugin.getDefault().getWorkbench().getAdapter(
                IContextService.class);
        Collection col = contextService.getActiveContextIds();
        for (Object o : col) {
            log.info(Messages.getString("LogActiveContextsAction.activeContextLog",o) ); //$NON-NLS-1$
        }
    }

    public void dispose() {
    }

    public void init(IWorkbenchWindow window) {
    }

    public void run(IAction action) {
        run();
    }

    public void selectionChanged(IAction action, ISelection selection) {
    }
}
