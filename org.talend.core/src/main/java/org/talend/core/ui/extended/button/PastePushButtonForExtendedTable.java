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
package org.talend.core.ui.extended.button;

import org.eclipse.gef.commands.Command;
import org.eclipse.swt.widgets.Composite;
import org.talend.commons.ui.swt.extended.table.AbstractExtendedTableViewer;
import org.talend.commons.ui.swt.extended.table.ExtendedTableModel;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public abstract class PastePushButtonForExtendedTable extends PastePushButton implements IExtendedTablePushButton {

    /**
     * DOC amaumont SchemaTargetAddPushButton constructor comment.
     * 
     * @param parent
     * @param extendedControlViewer
     */
    public PastePushButtonForExtendedTable(Composite parent, AbstractExtendedTableViewer extendedTableViewer) {
        super(parent, extendedTableViewer);
    }

    @Override
    protected Command getCommandToExecute() {
        AbstractExtendedTableViewer abstractExtendedTableViewer = ((AbstractExtendedTableViewer) getExtendedControlViewer());
        ExtendedTableModel extendedTableModel = abstractExtendedTableViewer.getExtendedTableModel();
        int[] selection = abstractExtendedTableViewer.getTableViewerCreator().getTable().getSelectionIndices();
        Integer indexWhereInsert = null;
        if (selection.length > 0) {
            indexWhereInsert = selection[selection.length - 1] + 1;
        }
        return getCommandToExecute(extendedTableModel, indexWhereInsert);
    }

    protected abstract Command getCommandToExecute(ExtendedTableModel extendedTableModel, Integer indexWhereInsert);

    /* (non-Javadoc)
     * @see org.talend.core.ui.extended.button.IExtendedTablePushButton#getExtendedTableViewer()
     */
    public AbstractExtendedTableViewer getExtendedTableViewer() {
        return (AbstractExtendedTableViewer) getExtendedControlViewer();
    }


}
