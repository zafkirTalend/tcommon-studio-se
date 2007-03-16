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
package org.talend.commons.ui.swt.advanced.dataeditor.button;

import java.io.File;

import org.eclipse.gef.commands.Command;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.talend.commons.i18n.internal.Messages;
import org.talend.commons.ui.swt.extended.table.AbstractExtendedControlViewer;
import org.talend.commons.ui.swt.extended.table.AbstractExtendedTableViewer;
import org.talend.commons.ui.swt.extended.table.ExtendedControlEvent;
import org.talend.commons.ui.swt.extended.table.ExtendedTableModel;
import org.talend.commons.ui.swt.extended.table.IExtendedControlListener;
import org.talend.commons.utils.data.list.IListenableListListener;
import org.talend.commons.utils.data.list.ListenableListEvent;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public abstract class ExportPushButtonForExtendedTable extends ExportPushButton implements IExtendedTablePushButton {

    private File file;

    /**
     * DOC amaumont SchemaTargetAddPushButton constructor comment.
     * 
     * @param parent
     * @param extendedControlViewer
     */
    public ExportPushButtonForExtendedTable(Composite parent, final AbstractExtendedTableViewer extendedTableViewer) {
        super(parent, extendedTableViewer);
        extendedTableViewer.addListener(new IExtendedControlListener() {

            public void handleEvent(ExtendedControlEvent event) {
                if (event.getType() == AbstractExtendedControlViewer.EVENT_TYPE.MODEL_CHANGED) {
                    ExtendedTableModel extendedTableModel = extendedTableViewer.getExtendedTableModel();
                    registerListListener(extendedTableModel);
                }
            }

        });

        ExtendedTableModel extendedTableModel = extendedTableViewer.getExtendedTableModel();
        registerListListener(extendedTableModel);

    }

    /**
     * DOC amaumont Comment method "registerListListener".
     * 
     * @param extendedTableModel
     */
    private void registerListListener(ExtendedTableModel extendedTableModel) {
        if (extendedTableModel != null) {
            extendedTableModel.addAfterOperationListListener(new IListenableListListener() {

                public void handleEvent(ListenableListEvent event) {
                    if (event.type == ListenableListEvent.TYPE.CLEARED || event.type == ListenableListEvent.TYPE.REMOVED
                            || event.type == ListenableListEvent.TYPE.ADDED) {
                        getButton().setEnabled(getEnabledState());
                    }
                }

            });
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.ui.swt.advanced.dataeditor.control.ExtendedPushButton#beforeCommandExecution()
     */
    @Override
    protected void beforeCommandExecution() {

        FileDialog dial = new FileDialog(getButton().getShell(), SWT.SAVE);
        dial.setFilterExtensions(new String[] { "*.xml" });
        String fileName = dial.open();
        if ((fileName != null) && (!fileName.equals(""))) { //$NON-NLS-1$
            this.file = new File(fileName);
        }

    }

    protected Command getCommandToExecute() {
        AbstractExtendedTableViewer extendedTableViewer = (AbstractExtendedTableViewer) extendedControlViewer;
        Table table = extendedTableViewer.getTableViewerCreator().getTable();
        return getCommandToExecute(extendedTableViewer.getExtendedTableModel(), file);
    }
    
    protected abstract Command getCommandToExecute(ExtendedTableModel model, File file);

    private void openMessageError(String errorText) {
        MessageBox msgBox = new MessageBox(getButton().getShell());
        msgBox.setText(Messages.getString("ExportPushButtonForExtendedTable.ErrorMsg.Text")); //$NON-NLS-1$
        msgBox.setMessage(errorText);
        msgBox.open();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.extended.button.IExtendedTablePushButton#getExtendedTableViewer()
     */
    public AbstractExtendedTableViewer getExtendedTableViewer() {
        return (AbstractExtendedTableViewer) getExtendedControlViewer();
    }

    public boolean getEnabledState() {
        AbstractExtendedTableViewer extendedTableViewer = (AbstractExtendedTableViewer) extendedControlViewer;
        ExtendedTableModel extendedTableModel = extendedTableViewer.getExtendedTableModel();
        boolean enabled = false;
        if (extendedTableModel != null && extendedTableModel.isDataRegistered() && extendedTableModel.getBeansList().size() > 0) {
            enabled = true;
        }
        return super.getEnabledState() && enabled;
    }

}
