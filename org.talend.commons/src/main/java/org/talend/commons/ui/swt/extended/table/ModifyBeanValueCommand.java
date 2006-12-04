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
package org.talend.commons.ui.swt.extended.table;

import org.eclipse.gef.commands.Command;
import org.talend.commons.ui.swt.tableviewer.ModifiedBeanEvent;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator;


/**
 * DOC amaumont  class global comment. Detailled comment
 * <br/>
 *
 * $Id$
 *
 * @param <B> modie
 */
public class ModifyBeanValueCommand<B> extends Command {

    private ModifiedBeanEvent<B> event;

    /**
     * DOC amaumont ModifiedBeanValueCommand constructor comment.
     * @param event
     */
    public ModifyBeanValueCommand(ModifiedBeanEvent<B> event) {
        this.event = event;
    }

    
    
    /* (non-Javadoc)
     * @see org.talend.commons.ui.command.CommonCommand#getLabel()
     */
    @Override
    public String getLabel() {
        return "ModifyBeanValueCommand";
    }



    /* (non-Javadoc)
     * @see org.talend.commons.ui.command.CommonCommand#redo()
     */
    @Override
    public void redo() {
        TableViewerCreator tableViewerCreator = event.column.getTableViewerCreator();
        tableViewerCreator.setBeanValue(event.column, event.bean, event.newValue, false);
    }

    /* (non-Javadoc)
     * @see org.talend.commons.ui.command.CommonCommand#undo()
     */
    @Override
    public void undo() {
        TableViewerCreator tableViewerCreator = event.column.getTableViewerCreator();
        tableViewerCreator.setBeanValue(event.column, event.bean, event.previousValue, false);
    }



    /* (non-Javadoc)
     * @see org.talend.commons.ui.command.CommonCommand#canUndo()
     */
    @Override
    public boolean canUndo() {
        return true;
    }

    
    
    
}
