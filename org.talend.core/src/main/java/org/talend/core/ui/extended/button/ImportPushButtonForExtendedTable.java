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

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.talend.commons.ui.command.ICommonCommand;
import org.talend.commons.ui.swt.extended.table.AbstractExtendedTableViewer;
import org.talend.commons.ui.swt.extended.table.ExtendedTableModel;


/**
 * DOC amaumont  class global comment. Detailled comment
 * <br/>
 *
 * $Id$
 *
 */
public abstract class ImportPushButtonForExtendedTable extends ImportPushButton {

    private File file;

    /**
     * DOC amaumont SchemaTargetAddPushButton constructor comment.
     * @param parent
     * @param extendedControlViewer
     */
    public ImportPushButtonForExtendedTable(Composite parent, AbstractExtendedTableViewer extendedTableViewer) {
        super(parent, extendedTableViewer);
    }

    protected ICommonCommand getCommandToExecute() {
        AbstractExtendedTableViewer extendedTableViewer = (AbstractExtendedTableViewer) extendedControlViewer;
        Table table = extendedTableViewer.getTableViewerCreator().getTable();
        return getCommandToExecute(extendedTableViewer.getExtendedTableModel(), file);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.ui.swt.advanced.dataeditor.control.ExtendedPushButton#beforeCommandExecution()
     */
    @Override
    protected void beforeCommandExecution() {
        FileDialog dial = new FileDialog(getButton().getShell(), SWT.OPEN);
        dial.setFilterExtensions(new String[] { "*.xml" });
        String fileName = dial.open();
        if ((fileName != null) && (!fileName.equals(""))) {
            file = new File(fileName);
        }
    }

    protected abstract ICommonCommand getCommandToExecute(ExtendedTableModel model, File file);
    
    
    private void openMessageError(String errorText) {
        MessageBox msgBox = new MessageBox(getButton().getShell());
        msgBox.setText("Error occurred");
        msgBox.setMessage(errorText);
        msgBox.open();
    }

}
