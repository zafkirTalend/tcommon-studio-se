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
package org.talend.commons.ui.swt.advanced.dataeditor.commands;

import java.util.ArrayList;

import org.eclipse.gef.commands.Command;
import org.talend.commons.ui.command.CommonCommand;
import org.talend.commons.ui.swt.extended.macrotable.ExtendedTableModel;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class ExtendedTableRemoveCommand extends CommonCommand {

    private ExtendedTableModel extendedTable;

    private Integer indexItemToRemove;

    private ArrayList beansToRemove;

    private int[] indexItemsToRemove;

    private Object beanToRemove;

    /**
     * DOC amaumont ExtendedTableAddCommand constructor comment.
     */
    public ExtendedTableRemoveCommand(ExtendedTableModel extendedTable, Integer indexItemToRemove) {
        super();
        this.extendedTable = extendedTable;
        this.indexItemToRemove = indexItemToRemove;
    }

    /**
     * DOC amaumont ExtendedTableAddCommand constructor comment.
     */
    public ExtendedTableRemoveCommand(ExtendedTableModel extendedTable, Object beanToRemove) {
        super();
        this.extendedTable = extendedTable;
        this.beanToRemove = beanToRemove;
    }

    /**
     * DOC amaumont ExtendedTableAddCommand constructor comment.
     */
    public ExtendedTableRemoveCommand(ExtendedTableModel extendedTable, ArrayList beansToRemove) {
        this.extendedTable = extendedTable;
        this.indexItemToRemove = indexItemToRemove;
    }

    /**
     * DOC amaumont ExtendedTableAddCommand constructor comment.
     */
    public ExtendedTableRemoveCommand(ExtendedTableModel extendedTable, int[] indexItemsToRemove) {
        this.extendedTable = extendedTable;
        this.indexItemsToRemove = indexItemsToRemove;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {

        if (indexItemToRemove != null) {
            extendedTable.remove(indexItemToRemove);
        }
        if (indexItemsToRemove != null) {
            extendedTable.remove(indexItemsToRemove);
        }
        if (beansToRemove != null) {
            extendedTable.remove(beansToRemove);
        }
        if (beanToRemove != null) {
            extendedTable.remove(beanToRemove);
        }

    }

}
