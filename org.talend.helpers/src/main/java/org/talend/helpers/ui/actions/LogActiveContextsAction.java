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
package org.talend.helpers.ui.actions;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.contexts.IContextService;
import org.talend.helpers.HelpersPlugin;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class LogActiveContextsAction extends Action {

    private static Logger log = Logger.getLogger(LogActiveContextsAction.class);

    public LogActiveContextsAction() {
        super();
        this.setActionDefinitionId("logActiveContexts");
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
        StringBuffer toDisplay = new StringBuffer();
        for (Object o : col) {
            toDisplay.append(o + "/");
        }
        log.info("Cache image: " + toDisplay);
    }

}
