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

import org.eclipse.swt.widgets.Composite;
import org.talend.commons.ui.command.ICommonCommand;
import org.talend.commons.ui.swt.advanced.dataeditor.commands.ExtendedTableMoveCommand;
import org.talend.commons.ui.swt.advanced.dataeditor.control.ExtendedPushButton;
import org.talend.commons.ui.swt.extended.table.AbstractExtendedControlViewer;
import org.talend.commons.ui.swt.extended.table.AbstractExtendedTableViewer;
import org.talend.core.ui.images.EImage;
import org.talend.core.ui.images.ImageProvider;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public abstract class MoveUpPushButton extends ExtendedPushButton {

    /**
     * DOC amaumont AddPushButton constructor comment.
     * 
     * @param parent
     * @param tooltip
     * @param image
     */
    public MoveUpPushButton(Composite parent, AbstractExtendedControlViewer extendedControlViewer) {
        super(parent, extendedControlViewer, "Move up selected items", ImageProvider.getImage(EImage.UP_ICON));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.ui.swt.advanced.macrotable.control.ExtendedPushButton#getCommandToExecute()
     */
    @Override
    protected ICommonCommand getCommandToExecute() {
        AbstractExtendedTableViewer viewer = (AbstractExtendedTableViewer) getExtendedControlViewer();
        return new ExtendedTableMoveCommand(viewer.getExtendedTableModel(), true, viewer.getTableViewerCreator().getTable()
                .getSelectionIndices());
    }

}
