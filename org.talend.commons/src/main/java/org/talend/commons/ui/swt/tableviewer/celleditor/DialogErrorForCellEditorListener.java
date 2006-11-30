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
package org.talend.commons.ui.swt.tableviewer.celleditor;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ICellEditorListener;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Text;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreatorColumn;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator.CELL_EDITOR_STATE;
import org.talend.commons.ui.swt.tableviewer.data.ModifiedObjectInfo;
import org.talend.commons.ui.utils.ControlUtils;
import org.talend.commons.utils.threading.AsynchronousThreading;

/**
 * Validate column value and show a dialog message if value is not valid.
 * 
 * $Id$
 * 
 */
public abstract class DialogErrorForCellEditorListener implements ICellEditorListener {

    protected TextCellEditor cellEditor;

    protected TableViewerCreatorColumn column;

    protected TableViewerCreator tableViewerCreator;

    /**
     * DOC amaumont CellEditorListener constructor comment.
     * 
     * @param column
     * @param cellEditor
     */
    public DialogErrorForCellEditorListener(TextCellEditor cellEditor, TableViewerCreatorColumn column) {
        super();
        this.cellEditor = cellEditor;
        this.column = column;
        this.tableViewerCreator = column.getTableViewerCreator();
    }

    Object lastValidValue = null;

    public void applyEditorValue() {
        ModifiedObjectInfo modifiedObjectInfo = tableViewerCreator.getModifiedObjectInfo();
        Object bean = modifiedObjectInfo.getCurrentModifiedBean() != null ? modifiedObjectInfo.getCurrentModifiedBean()
                : modifiedObjectInfo.getPreviousModifiedBean();
        String text = ControlUtils.getText(cellEditor.getControl());
        onValueChanged(text, true, bean, CELL_EDITOR_STATE.APPLYING);
        lastValidValue = null;
    }

    public void cancelEditor() {
        ModifiedObjectInfo modifiedObjectInfo = tableViewerCreator.getModifiedObjectInfo();
        String originalName = (String) modifiedObjectInfo.getOriginalPropertyBeanValue();
        ControlUtils.setText(cellEditor.getControl(), originalName);
        onValueChanged(originalName, false, modifiedObjectInfo.getCurrentModifiedBean(), CELL_EDITOR_STATE.CANCELING);
        lastValidValue = null;
    }

    public void editorValueChanged(boolean oldValidState, boolean newValidState) {
        ModifiedObjectInfo modifiedObjectInfo = tableViewerCreator.getModifiedObjectInfo();
        onValueChanged(((Text) cellEditor.getControl()).getText(), false, modifiedObjectInfo.getCurrentModifiedBean(),
                CELL_EDITOR_STATE.EDITING);
    }

    protected void onValueChanged(final String newValue, boolean showAlertIfError, final Object currentModifiedBean,
            CELL_EDITOR_STATE state) {
        final Text text = (Text) cellEditor.getControl();
        final ModifiedObjectInfo modifiedObjectInfo = tableViewerCreator.getModifiedObjectInfo();
        Object originalValue = modifiedObjectInfo.getOriginalPropertyBeanValue();
        lastValidValue = lastValidValue != null && state == CELL_EDITOR_STATE.EDITING ? lastValidValue : originalValue;

        int beanPosition = tableViewerCreator.getInputList().indexOf(currentModifiedBean);
        final String errorMessage = validateValue(newValue, beanPosition);
        if (errorMessage == null) {
            newValidValueTyped(beanPosition, lastValidValue, newValue, state);
            text.setBackground(text.getDisplay().getSystemColor(SWT.COLOR_WHITE));
            lastValidValue = newValue;
        } else {
            text.setBackground(text.getDisplay().getSystemColor(SWT.COLOR_RED));
            if (showAlertIfError) {
                final Point selection = text.getSelection();
                text.setText(lastValidValue.toString());

                new AsynchronousThreading(20, false, text.getDisplay(), new Runnable() {

                    public void run() {

                        MessageDialog.openError(text.getShell(), "Error", errorMessage);
                        final int columnPosition = tableViewerCreator.getColumns().indexOf(column);
                        tableViewerCreator.getTableViewer().editElement(currentModifiedBean, columnPosition);
                        text.setText(newValue);
                        text.setSelection(selection.x, selection.y);

                    }
                }).start();
            }
        }
    }

    /**
     * Implement this to execute treatments when new value is valid.
     * @param itemIndex TODO
     * @param previousValue
     * @param newValue
     * @param state
     */
    public abstract void newValidValueTyped(int itemIndex, final Object previousValue, final Object newValue, CELL_EDITOR_STATE state);

    /**
     * DOC amaumont Comment method "validateValue".
     * @param value to validate
     * @param beanPosition
     * @return error message if value is invalid, null else
     */
    public abstract String validateValue(String value, int beanPosition);

}
