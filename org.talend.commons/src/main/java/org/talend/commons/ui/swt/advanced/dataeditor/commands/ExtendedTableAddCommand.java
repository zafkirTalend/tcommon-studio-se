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
 * DOC amaumont  class global comment. Detailled comment
 * <br/>
 *
 * $Id$
 *
 */
public class ExtendedTableAddCommand extends CommonCommand {

    private ExtendedTableModel extendedTable;
    private Integer indexStartAdd;
    private ArrayList beansToAdd;

    /**
     * DOC amaumont ExtendedTableAddCommand constructor comment.
     */
    public ExtendedTableAddCommand(ExtendedTableModel extendedTable, Integer indexStartAdd, ArrayList beansToAdd) {
        super();
        this.extendedTable = extendedTable;
        this.indexStartAdd = indexStartAdd;
        this.beansToAdd = beansToAdd;
    }

    /**
     * DOC amaumont ExtendedTableAddCommand constructor comment.
     */
    public ExtendedTableAddCommand(ExtendedTableModel extendedTable, ArrayList beansToAdd) {
        this(extendedTable, null, beansToAdd);
    }
    
    /**
     * DOC amaumont ExtendedTableAddCommand constructor comment.
     */
    public ExtendedTableAddCommand(ExtendedTableModel extendedTable, Integer indexStartAdd, Object beanToAdd) {
        super();
        this.extendedTable = extendedTable;
        this.indexStartAdd = indexStartAdd;
        ArrayList list = new ArrayList(1);
        list.add(beanToAdd);
        this.beansToAdd = list;
    }
    
    /**
     * DOC amaumont ExtendedTableAddCommand constructor comment.
     */
    public ExtendedTableAddCommand(ExtendedTableModel extendedTable, Object beanToAdd) {
        this(extendedTable, null, beanToAdd);
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
        
        extendedTable.addAll(indexStartAdd, beansToAdd);
        
    }

}
