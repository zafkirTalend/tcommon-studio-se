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
package org.talend.commons.ui.swt.advanced.dataeditor.commands;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.Command;
import org.talend.commons.i18n.internal.Messages;
import org.talend.commons.ui.swt.extended.table.ExtendedTableModel;
import org.talend.commons.ui.utils.SimpleClipboard;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public abstract class ExtendedTablePasteCommand extends Command implements IExtendedTableCommand {

    private ExtendedTableModel extendedTable;

    private Integer indexStart;

    public static final String LABEL = Messages.getString("ExtendedTablePasteCommand.Paste.Label"); //$NON-NLS-1$

    /**
     * DOC amaumont ExtendedTableAddCommand constructor comment.
     */
    public ExtendedTablePasteCommand(ExtendedTableModel extendedTable, Integer indexStartAdd) {
        super(LABEL);
        this.extendedTable = extendedTable;
        this.indexStart = indexStartAdd;
    }

    /**
     * DOC amaumont ExtendedTableAddCommand constructor comment.
     */
    public ExtendedTablePasteCommand(ExtendedTableModel extendedTable) {
        this(extendedTable, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @SuppressWarnings("unchecked") //$NON-NLS-1$
    @Override
    public void execute() {

        Object data = SimpleClipboard.getInstance().getData();
        if (data instanceof List) {
            List list = new ArrayList((List) data);
            list = createPastableBeansList(extendedTable, list);
            extendedTable.addAll(indexStart, list);
        }

    }

    public abstract List createPastableBeansList(ExtendedTableModel extendedTable, List copiedObjectsList);

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.ui.command.CommonCommand#canUndo()
     */
    @Override
    public boolean canUndo() {
        return true;
    }

}
