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
package org.talend.core.ui.context;

import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreatorColumn;
import org.talend.commons.ui.swt.tableviewer.celleditor.DateDialog;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.IContextParameter;

/**
 * qzhang class global comment. Detailled comment <br/>
 * 
 */
public class DialogCellEditorForContext extends CustomDialogCellEditor {

    private TableViewerCreator tableViewerCreator;

    private TableViewerCreatorColumn tableViewerCreatorColumn;

    private IContextParameter contextParameter;

    Object dialog;

    /**
     * qzhang TextCellEditorForContext constructor comment.
     */
    public DialogCellEditorForContext(TableViewerCreatorColumn tableViewerCreatorColumn) {
        super();
        init(tableViewerCreatorColumn);
    }

    public DialogCellEditorForContext(Composite parent, int style, TableViewerCreatorColumn tableViewerCreatorColumn) {
        super(parent, style);
        init(tableViewerCreatorColumn);
    }

    public DialogCellEditorForContext(Composite parent, TableViewerCreatorColumn tableViewerCreatorColumn) {
        super(parent);
        init(tableViewerCreatorColumn);
    }

    /**
     * qzhang Comment method "init".
     * 
     * @param tableViewerCreatorColumn
     */
    private void init(TableViewerCreatorColumn tableViewerCreatorColumn) {
        this.tableViewerCreatorColumn = tableViewerCreatorColumn;
        this.tableViewerCreator = tableViewerCreatorColumn.getTableViewerCreator();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.CellEditor#activate()
     */
    @Override
    public void activate() {
        super.activate();
        final Table table = tableViewerCreator.getTable();
        final TableEditor editor = new TableEditor(table);
        editor.horizontalAlignment = SWT.CENTER;
        editor.grabHorizontal = true;
        TableItem[] selection = table.getSelection();

        if (selection.length > 0) {
            final TableItem item = selection[0];
            Object data = item.getData();
            if (data instanceof IContextParameter) {
                contextParameter = (IContextParameter) data;
                String type = contextParameter.getType();
                if (type == null) {
                    dialog = null;
                    button.setVisible(false);
                    defaultLabel.setFocus();
                    disposeCheck();
                } else if (type.equals(JavaTypesManager.FILE.getId())) {
                    dialog = new FileDialog(getControl().getShell());
                    final FileDialog fileDialog = ((FileDialog) dialog);
                    if (defaultLabel.getText() != null) {
                        fileDialog.setFileName(defaultLabel.getText());
                    }
                    button.setVisible(true);
                    disposeCheck();
                } else if (type.equals(JavaTypesManager.DATE.getId())) {
                    dialog = new DateDialog(getControl().getShell());
                    button.setVisible(true);
                    disposeCheck();
                } else if (type.equals(JavaTypesManager.DIRECTORY.getId())) {
                    dialog = new DirectoryDialog(getControl().getShell());
                    final DirectoryDialog fileDialog = ((DirectoryDialog) dialog);
                    if (defaultLabel.getText() != null) {
                        fileDialog.setFilterPath(defaultLabel.getText());
                    }
                    button.setVisible(true);
                    disposeCheck();
                    // } else if (type.equals(JavaTypesManager.BOOLEAN.getId())) {
                    // dialog = null;
                    // button.setVisible(false);
                    // combo = new CCombo(table, SWT.FLAT);
                    // combo.setItems(new String[] { "false", "true" });
                    // combo.addFocusListener(new FocusAdapter() {
                    //
                    // /*
                    // * (non-Javadoc)
                    // *
                    // * @see org.eclipse.swt.events.FocusAdapter#focusLost(org.eclipse.swt.events.FocusEvent)
                    // */
                    // @Override
                    // public void focusLost(FocusEvent e) {
                    // combo.dispose();
                    // }
                    // });
                    // editor.setEditor(combo, item, 4);
                } else {
                    dialog = null;
                    button.setVisible(false);
                    defaultLabel.setFocus();
                    disposeCheck();
                }
            }
        }
    }

    /**
     * qzhang Comment method "disposeCheck".
     */
    private void disposeCheck() {
        if (combo != null && !combo.isDisposed()) {
            combo.dispose();
            combo = null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.DialogCellEditor#openDialogBox(org.eclipse.swt.widgets.Control)
     */
    @Override
    protected Object openDialogBox(Control cellEditorWindow) {
        String value = "";
        if (dialog instanceof FileDialog) {
            value = ((FileDialog) dialog).open();
        } else if (dialog instanceof DirectoryDialog) {
            value = ((DirectoryDialog) dialog).open();
        } else if (dialog instanceof DateDialog) {
            final DateDialog dateDialog = ((DateDialog) dialog);
            if (dateDialog.open() == DateDialog.OK) {
                value = dateDialog.getTalendDateString();
            }
        }

        return value;
    }
}
