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

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.talend.commons.ui.swt.tableviewer.TableEditorContent;
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

    /*
     * 
     * (non-Javadoc)
     * @see org.talend.commons.ui.swt.tableviewer.TableEditorContent#initialize(org.eclipse.swt.widgets.Table, org.eclipse.swt.custom.TableEditor, org.talend.commons.ui.swt.tableviewer.TableViewerCreatorColumn, java.lang.Object, java.lang.Object)
     */
    public Control initialize(Table table, TableEditor tableEditor, final TableViewerCreatorColumn currentColumn,
            final Object currentRowObject, final Object currentCellValue) {
        
        /*
         * Do not set check control as field because one instance of CheckboxTableEditorContent is shared by all checks
         * of a same column
         * 
         */
        
        final Button check = new Button(table, SWT.CHECK);
        check.setText("");
        check.setBackground(table.getBackground());
        check.setSelection(currentCellValue.equals(true));
        check.addSelectionListener(new SelectionListener() {

            public void widgetDefaultSelected(SelectionEvent e) {
            }

            @SuppressWarnings("unchecked")
            public void widgetSelected(SelectionEvent e) {
                currentColumn.getBeanPropertyAccessors().set(currentRowObject,
                        check.getSelection() ? CHECKED : UNCHECKED);
            }

        });
        Point size = check.computeSize(SWT.DEFAULT, table.getItemHeight());

        // Set attributes of the editor
        tableEditor.horizontalAlignment = SWT.CENTER;
        tableEditor.minimumHeight = size.y;
        tableEditor.minimumWidth = size.x;
        return check;
    }

    
}
