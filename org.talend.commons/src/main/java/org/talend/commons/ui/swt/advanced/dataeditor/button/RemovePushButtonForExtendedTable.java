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
package org.talend.commons.ui.swt.advanced.dataeditor.button;

import org.eclipse.gef.commands.Command;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.talend.commons.ui.swt.advanced.dataeditor.commands.ExtendedTableRemoveCommand;
import org.talend.commons.ui.swt.extended.table.AbstractExtendedTableViewer;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class RemovePushButtonForExtendedTable extends RemovePushButton implements IExtendedTablePushButton {

    private EnableStateListenerForTableButton enableStateHandler;

    /**
     * DOC amaumont SchemaTargetAddPushButton constructor comment.
     * 
     * @param parent
     * @param extendedControlViewer
     */
    public RemovePushButtonForExtendedTable(Composite parent, AbstractExtendedTableViewer extendedTableViewer) {
        super(parent, extendedTableViewer);
        this.enableStateHandler = new EnableStateListenerForTableButton(this);
    }

    public boolean getEnabledState() {
        return super.getEnabledState() && this.enableStateHandler.getEnabledState();
    }
    
    protected Command getCommandToExecute() {
        AbstractExtendedTableViewer extendedTableViewer = (AbstractExtendedTableViewer) extendedControlViewer;
        Table table = extendedTableViewer.getTableViewerCreator().getTable();
        return new ExtendedTableRemoveCommand(extendedTableViewer.getExtendedTableModel(), table.getSelectionIndices());
    }

    /* (non-Javadoc)
     * @see org.talend.core.ui.extended.button.IExtendedTablePushButton#getExtendedTableViewer()
     */
    public AbstractExtendedTableViewer getExtendedTableViewer() {
        return (AbstractExtendedTableViewer) getExtendedControlViewer();
    }

}
