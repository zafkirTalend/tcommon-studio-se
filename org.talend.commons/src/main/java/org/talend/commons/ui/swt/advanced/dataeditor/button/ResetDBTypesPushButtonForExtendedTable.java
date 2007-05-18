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
import org.talend.commons.ui.swt.extended.table.AbstractExtendedTableViewer;
import org.talend.commons.ui.swt.extended.table.ExtendedTableModel;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public abstract class ResetDBTypesPushButtonForExtendedTable extends ResetDBTypesPushButton implements
        IExtendedTablePushButton {

    protected String dbmsId;

    /**
     * DOC amaumont SchemaTargetAddPushButton constructor comment.
     * 
     * @param parent
     * @param dbmsId
     * @param extendedControlViewer
     */
    public ResetDBTypesPushButtonForExtendedTable(Composite parent, AbstractExtendedTableViewer extendedTableViewer,
            String dbmsId) {
        super(parent, extendedTableViewer);
        this.dbmsId = dbmsId;
    }

    protected Command getCommandToExecute() {
        AbstractExtendedTableViewer extendedTableViewer = (AbstractExtendedTableViewer) extendedControlViewer;
        return getCommandToExecute(extendedTableViewer.getExtendedTableModel());
    }

    protected abstract Command getCommandToExecute(ExtendedTableModel extendedTableModel);

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.extended.button.IExtendedTablePushButton#getExtendedTableViewer()
     */
    public AbstractExtendedTableViewer getExtendedTableViewer() {
        return (AbstractExtendedTableViewer) getExtendedControlViewer();
    }
}
