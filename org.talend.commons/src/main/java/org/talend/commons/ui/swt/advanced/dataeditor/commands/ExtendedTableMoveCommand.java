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
import java.util.HashSet;
import java.util.Set;

import org.eclipse.gef.commands.Command;
import org.talend.commons.ui.command.CommonCommand;
import org.talend.commons.ui.swt.extended.macrotable.ExtendedTableModel;
import org.talend.commons.utils.data.list.ListenableList;



/**
 * DOC amaumont  class global comment. Detailled comment
 * <br/>
 *
 * $Id$
 *
 */
public class ExtendedTableMoveCommand extends CommonCommand {

    private ExtendedTableModel extendedTable;
    private boolean moveUp;
    private int[] entriesIndices;

    /**
     * DOC amaumont ExtendedTableAddCommand constructor comment.
     */
    public ExtendedTableMoveCommand(ExtendedTableModel extendedTable, boolean moveUp, int[] entriesIndices) {
        super();
        this.extendedTable = extendedTable;
        this.moveUp = moveUp;
        this.entriesIndices = entriesIndices;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
        
        ListenableList list = (ListenableList) extendedTable.getBeansList();
        Set<Integer> setIndicesSelectedMoved = new HashSet<Integer>();
        int increment;
        int startIndex;
        int endIndex;
        ArrayList<Integer> indexOrigin = new ArrayList<Integer>();
        ArrayList<Integer> indexDestination = new ArrayList<Integer>();
        if (this.moveUp) {
            increment = -1;
            startIndex = 0;
            endIndex = entriesIndices.length;

        } else {
            increment = 1;
            startIndex = entriesIndices.length - 1;
            endIndex = -1;
        }

        for (int i = startIndex; i != endIndex; i -= increment) {
            int indice = entriesIndices[i];
            int newIndice = indice + increment;
            if (newIndice < 0) {
                newIndice = 0;
            }
            if (newIndice >= list.size()) {
                newIndice = list.size() - 1;
            }
            if (!setIndicesSelectedMoved.contains(newIndice)) {
                indexOrigin.add(indice);
                indexDestination.add(newIndice);
                setIndicesSelectedMoved.add(newIndice);
            } else {
                setIndicesSelectedMoved.add(indice);
            }
        }

        list.swapElements(indexOrigin, indexDestination);
        
    }

}
