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

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.handlers.ShowViewHandler;
import org.talend.rcp.i18n.Messages;


/**
 * Displays a window for view selection.
 * <br/>
 *
 * $Id$
 *
 */
public class ShowViewAction extends Action {
    
    private final ShowViewHandler handler;

    /**
     * Constructs a new ShowViewAction.
     */
    public ShowViewAction() {
        super(Messages.getString("ShowViewAction.actionLabel")); //$NON-NLS-1$
        
        handler = new ShowViewHandler(false);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        try {
            handler.execute(new ExecutionEvent());
        } catch (final ExecutionException e) {
            // Do nothing.
        }
    }

}
