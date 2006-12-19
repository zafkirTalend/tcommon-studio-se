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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreatorColumn;
import org.talend.commons.ui.swt.tableviewer.selection.ITableColumnSelectionListener;
import org.talend.commons.ui.swt.tableviewer.sort.IColumnSortedListener;

/**
 * DOC amaumont class global comment. Detailled comment <br/> $Id: TableEditorManager.java,v 1.3 2006/06/02 15:24:10
 * amaumont Exp $
 */
public class TableEditorManager {

    private TableViewerCreator tableViewerCreator;

    private List<TableEditor> tableEditorList = new ArrayList<TableEditor>();

    /**
     * DOC amaumont TableEditorManager constructor comment.
     * 
     * @param tableViewerCreator
     */
    public TableEditorManager(TableViewerCreator tableViewerCreator) {
        this.tableViewerCreator = tableViewerCreator;
    }

    @SuppressWarnings("unchecked")
    public void init() {
        List<TableViewerCreatorColumn> columns = tableViewerCreator.getColumns();
        for (TableViewerCreatorColumn column : columns) {
            if (column.getTableColumnSelectionListener() instanceof ITableColumnSelectionListener) {
                column.getTableColumnSelectionListener().addColumnSortedListener(new IColumnSortedListener() {

                    public void handle() {
                        refresh();
                    }

                });
            }
        }

        refresh();
    }

    @SuppressWarnings("unchecked")
    public void refresh() {
        // System.out.println("table editor refreshed");
        for (TableEditor tableEditor : tableEditorList) {
            tableEditor.getEditor().dispose();
        }
        tableEditorList.clear();

        Table table = tableViewerCreator.getTable();
        if (table.isDisposed()) {
            return;
        }

        TableItem[] items = table.getItems();
        Map<Object, TableItem> objectRowToTableItem = new HashMap<Object, TableItem>();
        for (int i = 0; i < items.length; i++) {
            TableItem item = items[i];
            if (!item.isDisposed()) {
                objectRowToTableItem.put(item.getData(), item);
            }
        }

        List<TableViewerCreatorColumn> columns = tableViewerCreator.getColumns();

        for (int iCol = 0; iCol < columns.size(); iCol++) {
            TableViewerCreatorColumn column = columns.get(iCol);

            TableEditorContent tableEditorContent = column.getTableEditorContent();
            if (tableEditorContent != null) {

                String idProperty = column.getId();
//                Collection collection = (Collection) tableViewerCreator.getTableViewer().getInput();
//                if (collection != null) {
//                    for (Object currentRowObject : collection) {
//                        TableEditor tableEditor = tableEditorContent.createTableEditor(tableViewerCreator.getTable());
//                        tableEditorList.add(tableEditor);
//                        Object value = tableViewerCreator.getCellModifier().getValue(currentRowObject, idProperty);
//                        Control control = tableEditorContent.initialize(tableViewerCreator.getTable(), tableEditor, column,
//                                currentRowObject, value);
//                        TableItem tableItem = objectRowToTableItem.get(currentRowObject);
//                        if (tableItem != null && !tableItem.isDisposed()) {
//                            tableEditor.setEditor(control, objectRowToTableItem.get(currentRowObject), iCol);
//                        }
//                    }
//                }
                
                for (int i = 0; i < items.length; i++) {
                    TableEditor tableEditor = tableEditorContent.createTableEditor(table);
                    TableItem tableItem = items[i];
                    tableEditorList.add(tableEditor);
                    Object currentRowObject = tableItem.getData();
                    Object value = tableViewerCreator.getCellModifier().getValue(currentRowObject, idProperty);
                    Control control = tableEditorContent.initialize(table, tableEditor, column,
                            currentRowObject, value);
                    if (tableItem != null && !tableItem.isDisposed()) {
                        tableEditor.setEditor(control, tableItem, iCol);
                    }

                }
            }
        }

    }

    /**
     * DOC amaumont Comment method "redrawAll".
     */
    public void redrawControls() {
        for (TableEditor tableEditor : tableEditorList) {
            Control editor = tableEditor.getEditor();
            if (editor != null) {
                editor.redraw();
            }
        }

    }

}
