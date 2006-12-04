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
package org.talend.commons.ui.swt.tableviewer.tableeditor;

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.talend.commons.ui.swt.tableviewer.IModifiedBeanListener;
import org.talend.commons.ui.swt.tableviewer.ModifiedBeanEvent;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreatorColumn;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class CheckboxTableEditorContent extends TableEditorContent {

    public static final boolean CHECKED = true;

    public static final boolean UNCHECKED = false;

    private boolean readOnly;

    /**
     * DOC amaumont CheckboxTableEditorContent constructor comment.
     */
    public CheckboxTableEditorContent() {
        super();
    }

    /**
     * DOC amaumont CheckboxTableEditorContent constructor comment.
     * 
     * @param readOnly applied for ALL checbox of column
     */
    public CheckboxTableEditorContent(boolean readOnly) {
        super();
        this.readOnly = readOnly;
    }

    /*
     * 
     * (non-Javadoc)
     * 
     * @see org.talend.commons.ui.swt.tableviewer.TableEditorContent#initialize(org.eclipse.swt.widgets.Table,
     * org.eclipse.swt.custom.TableEditor, org.talend.commons.ui.swt.tableviewer.TableViewerCreatorColumn,
     * java.lang.Object, java.lang.Object)
     */
    public Control initialize(Table table, TableEditor tableEditor, final TableViewerCreatorColumn currentColumn,
            final Object currentRowObject, final Object currentCellValue) {

        /*
         * Do not set check control as field because one instance of CheckboxTableEditorContent is shared by all checks
         * of a same column
         * 
         */

        final TableViewerCreator<Object> tableViewerCreator = currentColumn.getTableViewerCreator();

        final Button check = new Button(table, SWT.CHECK);
        check.setEnabled(!this.readOnly);
        check.setText("");
        check.setBackground(table.getBackground());
        check.setSelection(currentCellValue.equals(true));
        check.addSelectionListener(new SelectionListener() {

            public void widgetDefaultSelected(SelectionEvent e) {
            }

            @SuppressWarnings("unchecked")
            public void widgetSelected(SelectionEvent e) {
                tableViewerCreator.setBeanValue(currentColumn, currentRowObject, ((Button) e.getSource()).getSelection() ? CHECKED
                        : UNCHECKED, true);
                tableViewerCreator.getTableViewer().setSelection(new StructuredSelection(currentRowObject));
            }

        });
        Point size = check.computeSize(SWT.DEFAULT, table.getItemHeight());

        tableViewerCreator.addModifiedBeanListener(new IModifiedBeanListener<Object>() {

            public void handleEvent(ModifiedBeanEvent<Object> event) {
                if (check.isDisposed()) {
                    tableViewerCreator.removeModifiedBeanListener(this);
                    return;
                }
                Object beanValue = tableViewerCreator.getBeanValue(currentColumn, currentRowObject);
                if (beanValue instanceof Boolean) {
                    check.setSelection(((Boolean) beanValue).booleanValue());
                }
            }

        });

        // Set attributes of the editor
        tableEditor.horizontalAlignment = SWT.CENTER;
        tableEditor.minimumHeight = size.y;
        tableEditor.minimumWidth = size.x;
        return check;
    }

}
