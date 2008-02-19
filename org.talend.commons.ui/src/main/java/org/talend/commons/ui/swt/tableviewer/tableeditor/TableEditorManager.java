// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.commons.ui.swt.tableviewer.tableeditor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    /**
     * 
     * Event type. <br/>
     * 
     * $Id: TableEditorManager.java 7183 2007-11-23 11:03:36Z amaumont $
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
    }

    @SuppressWarnings("unchecked")
    public void refresh() {

        Table table = tableViewerCreator.getTable();
        if (table.isDisposed()) {
            return;
        }

        TableItem[] items = table.getItems();

        List<TableViewerCreatorColumn> columns = tableViewerCreator.getColumns();
        List<TableViewerCreatorColumn> columnsWithEditorContent = new ArrayList<TableViewerCreatorColumn>(columns.size());

        for (int iCol = 0; iCol < columns.size(); iCol++) {
            TableViewerCreatorColumn column = columns.get(iCol);
            TableEditorContent tableEditorContent = column.getTableEditorContent();
            if (tableEditorContent != null) {
                columnsWithEditorContent.add(column);
            }
        }

        if (columnsWithEditorContent.size() == 2 && items.length != 0 && !tableEditorList.isEmpty()) {
            for (int iEditorCol = 0; iEditorCol < columnsWithEditorContent.size(); iEditorCol++) {
                TableViewerCreatorColumn column = columnsWithEditorContent.get(iEditorCol);

                TableEditorContent tableEditorContent = column.getTableEditorContent();
                tableEditorContent.setLayoutEnabled(false);

                String idProperty = column.getId();

                TableEditor tableEditor = tableEditorContent.createTableEditor(table);
                TableItem tableItem = items[items.length - 1];
                tableEditorList.add(tableEditor);
                Object currentRowObject = tableItem.getData();
                Object value = tableViewerCreator.getCellModifier().getValue(currentRowObject, idProperty);
                Control control = tableEditorContent.initialize(table, tableEditor, column, currentRowObject, value);
                if (tableItem != null && !tableItem.isDisposed()) {
                    tableEditor.setEditor(control, tableItem, column.getIndex());
                    fireEvent(new TableEditorManagerEvent(EVENT_TYPE.CONTROL_CREATED, tableEditor));
                }
            }
        } else {
            for (int i = 0; i < tableEditorList.size(); i++) {
                TableEditor tableEditor = tableEditorList.get(i);
                tableEditor.getEditor().dispose();
                fireEvent(new TableEditorManagerEvent(EVENT_TYPE.CONTROL_DISPOSED, tableEditor));
            }

            tableEditorList.clear();

            for (int iEditorCol = 0; iEditorCol < columnsWithEditorContent.size(); iEditorCol++) {

                TableViewerCreatorColumn column = columnsWithEditorContent.get(iEditorCol);

                TableEditorContent tableEditorContent = column.getTableEditorContent();
                tableEditorContent.setLayoutEnabled(false);

                String idProperty = column.getId();

                for (int i = 0; i < items.length; i++) {
                    TableEditor tableEditor = tableEditorContent.createTableEditor(table);
                    TableItem tableItem = items[i];
                    tableEditorList.add(tableEditor);
                    Object currentRowObject = tableItem.getData();
                    Object value = tableViewerCreator.getCellModifier().getValue(currentRowObject, idProperty);
                    Control control = tableEditorContent.initialize(table, tableEditor, column, currentRowObject, value);
                    if (tableItem != null && !tableItem.isDisposed()) {
                        tableEditor.setEditor(control, tableItem, column.getIndex());
                        fireEvent(new TableEditorManagerEvent(EVENT_TYPE.CONTROL_CREATED, tableEditor));
                    }
                }
            }
        }

        for (int iEditorCol = 0; iEditorCol < columnsWithEditorContent.size(); iEditorCol++) {
            TableViewerCreatorColumn column = columnsWithEditorContent.get(iEditorCol);
            column.getTableEditorContent().setLayoutEnabled(true);
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
