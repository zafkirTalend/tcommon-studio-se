// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the  agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//   
// ============================================================================
package org.talend.commons.ui.swt.tableviewer.tableeditor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.talend.commons.ui.swt.extended.table.IExtendedControlEventType;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreatorColumn;
import org.talend.commons.ui.swt.tableviewer.selection.ITableColumnSelectionListener;
import org.talend.commons.ui.swt.tableviewer.sort.IColumnSortedListener;

/**
 * DOC amaumont class global comment. Detailled comment <br/> $Id: TableEditorManager.java,v 1.3 2006/06/02 15:24:10
 * amaumont Exp $
 */
public class TableEditorManager {

    /**
     * 
     * Event type. <br/>
     * 
     * $Id$
     * 
     */
    public enum EVENT_TYPE implements ITableEditorManagerEventType {
        CONTROL_CREATED,
        CONTROL_DISPOSED,
    };


    private TableViewerCreator tableViewerCreator;

    private List<TableEditor> tableEditorList = new ArrayList<TableEditor>();

    List<ITableEditorManagerListener> listeners = new ArrayList<ITableEditorManagerListener>();

    public void addListener(ITableEditorManagerListener listener) {
        listeners.add(listener);
    }

    public void removeListener(ITableEditorManagerListener listener) {
        listeners.remove(listener);
    }

    protected void fireEvent(TableEditorManagerEvent event) {
        int listenersListSize = listeners.size();
        for (int i = 0; i < listenersListSize; i++) {
            ITableEditorManagerListener listener = listeners.get(i);
            listener.notifyEvent(event);
        }
    }


    
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
            fireEvent(new TableEditorManagerEvent(EVENT_TYPE.CONTROL_DISPOSED, tableEditor));
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
                // Collection collection = (Collection) tableViewerCreator.getTableViewer().getInput();
                // if (collection != null) {
                // for (Object currentRowObject : collection) {
                // TableEditor tableEditor = tableEditorContent.createTableEditor(tableViewerCreator.getTable());
                // tableEditorList.add(tableEditor);
                // Object value = tableViewerCreator.getCellModifier().getValue(currentRowObject, idProperty);
                // Control control = tableEditorContent.initialize(tableViewerCreator.getTable(), tableEditor, column,
                // currentRowObject, value);
                // TableItem tableItem = objectRowToTableItem.get(currentRowObject);
                // if (tableItem != null && !tableItem.isDisposed()) {
                // tableEditor.setEditor(control, objectRowToTableItem.get(currentRowObject), iCol);
                // }
                // }
                // }

                for (int i = 0; i < items.length; i++) {
                    TableEditor tableEditor = tableEditorContent.createTableEditor(table);
                    TableItem tableItem = items[i];
                    tableEditorList.add(tableEditor);
                    Object currentRowObject = tableItem.getData();
                    Object value = tableViewerCreator.getCellModifier().getValue(currentRowObject, idProperty);
                    Control control = tableEditorContent
                            .initialize(table, tableEditor, column, currentRowObject, value);
                    if (tableItem != null && !tableItem.isDisposed()) {
                        tableEditor.setEditor(control, tableItem, iCol);
                        fireEvent(new TableEditorManagerEvent(EVENT_TYPE.CONTROL_CREATED, tableEditor));
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

    
    public List<TableEditor> getTableEditorList() {
        return Collections.unmodifiableList(this.tableEditorList);
    }

    
    
}
