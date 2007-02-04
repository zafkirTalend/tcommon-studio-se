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
package org.talend.repository.localprovider.ui.actions;

import org.apache.log4j.Logger;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.talend.commons.exception.MessageBoxExceptionHandler;
import org.talend.commons.exception.PersistenceException;
import org.talend.repository.localprovider.i18n.Messages;
import org.talend.repository.localprovider.model.LocalRepositoryFactory;
import org.talend.repository.ui.views.IRepositoryView;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 1 2006-09-29 17:06:40 +0000 (ven., 29 sept. 2006) nrousseau $
 * 
 */
public class ReloadSystemRoutinesAction extends Action {

    private static Logger log = Logger.getLogger(ReloadSystemRoutinesAction.class);

    public ReloadSystemRoutinesAction() {
        super();
        this.setActionDefinitionId(Messages.getString("ReloadSystemRoutinesAction.0")); //$NON-NLS-1$
    }

    @Override
    public void run() {
        try {
            LocalRepositoryFactory.getInstance().synchronizeRoutines(null);
            IWorkbenchPage iwp = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
            IRepositoryView viewPart = (IRepositoryView) iwp.findView(IRepositoryView.VIEW_ID);
            viewPart.refresh();
            log.info(Messages.getString("ReloadSystemRoutinesAction.logInfo.sysRoutinesSuccessfullyReloaded")); //$NON-NLS-1$
        } catch (PersistenceException e) {
            MessageBoxExceptionHandler.process(e);
        }
    }

}
