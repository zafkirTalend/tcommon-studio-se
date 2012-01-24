// ============================================================================
//
// Copyright (C) 2006-2012 Talend Inc. - www.talend.com
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
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.set.MapBackedSet;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreatorColumnNotModifiable;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreatorNotModifiable;
import org.talend.commons.ui.swt.tableviewer.selection.ITableColumnSelectionListener;
import org.talend.commons.ui.swt.tableviewer.sort.IColumnSortedListener;
import org.talend.commons.ui.utils.threading.AsynchronousThreading;
import org.talend.commons.utils.data.list.IListenableListListener;
import org.talend.commons.utils.data.list.ListenableList;
import org.talend.commons.utils.data.list.ListenableListEvent;
import org.talend.commons.utils.data.list.ListenableListEvent.TYPE;
import org.talend.commons.utils.data.map.MultiLazyValuesMap;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * $Id: TableEditorManager.java,v 1.3 2006/06/02 15:24:10 amaumont Exp $
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

    private TableViewerCreatorNotModifiable tableViewerCreator;

    private List<TableEditor> tableEditorList = new ArrayList<TableEditor>();

    List<ITableEditorManagerListener> listeners = new ArrayList<ITableEditorManagerListener>();

    private ListenableList listenableList;

    private List<TableViewerCreatorColumnNotModifiable> columns;

    private List<TableViewerCreatorColumnNotModifiable> columnsWithEditorContent;

    private HashSet<TableItem> previousItemsHash;

    // //////////////////////////////////
    // Warning: using identity comparison
    // //////////////////////////////////
    MultiLazyValuesMap dataToMultipleDataEditor = new MultiLazyValuesMap(new IdentityHashMap()) {

        @Override
        public Collection instanciateNewCollection() {
            return (Collection) new ArrayList();
        }

    };

    private IListenableListListener listenableListListener;

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
    public TableEditorManager(TableViewerCreatorNotModifiable tableViewerCreator) {
        this.tableViewerCreator = tableViewerCreator;
    }

    @SuppressWarnings("unchecked")
    public void init(ListenableList listenableList) {
        if (listenableList != this.listenableList) {
            releaseListenableListListener();

            List<TableViewerCreatorColumnNotModifiable> columns = tableViewerCreator.getColumns();
            for (TableViewerCreatorColumnNotModifiable column : columns) {
                if (column.getTableColumnSelectionListener() instanceof ITableColumnSelectionListener) {
                    column.getTableColumnSelectionListener().addColumnSortedListener(new IColumnSortedListener() {

                        public void handle() {
                            refresh();
                        }

                    });
                }
            }

            if (this.listenableListListener == null) {

                this.listenableListListener = new IListenableListListener() {

                    public void handleEvent(ListenableListEvent event) {

                        if (event.type == TYPE.ADDED) {

                            handleAddedEventAsynchronous(event);

                        } else if (event.type == TYPE.REMOVED) {
                            handleRemovedEventAsynchronous(event);
                        } else if (event.type == TYPE.SWAPED) {
                            handleSwapedEvent(event);
                        } else if (event.type == TYPE.LIST_REGISTERED || event.type == TYPE.CLEARED
                                || event.type == TYPE.REPLACED) {
                            refresh();
                        }
                    }

                };
            }

            // System.out.println("TableEditor " + this.hashCode() + " Listener " + listenableListListener.hashCode()
            // + " added into listenableList " + listenableList.hashCode());

            listenableList.addPostOperationListener(listenableListListener);

        }

        this.listenableList = listenableList;

    }

    private void handleAddedEventAsynchronous(final ListenableListEvent event) {

        final Composite compositeParent = tableViewerCreator.getCompositeParent();

        if (!compositeParent.isDisposed()) {

            new AsynchronousThreading(10, true, compositeParent.getDisplay(), new Runnable() {

                public void run() {
                    if (!compositeParent.isDisposed()) {
                        handleAddedEvent(event);
                    }
                }

            }).start();
        }

    }

    private void handleRemovedEventAsynchronous(final ListenableListEvent event) {

        final Composite compositeParent = tableViewerCreator.getCompositeParent();

        if (!compositeParent.isDisposed()) {

            new AsynchronousThreading(10, true, tableViewerCreator.getCompositeParent().getDisplay(), new Runnable() {

                public void run() {
                    if (!compositeParent.isDisposed()) {
                        handleRemovedEvent(event);
                    }
                }

            }).start();

        }

    }

    private void handleAddedEvent(final ListenableListEvent event) {

        int indexStart = event.index;

        if (tableViewerCreator.getTable().isDisposed()) {
            return;
        }

        TableItem[] items = tableViewerCreator.getTable().getItems();

        List<TableEditor> addedTableEditor = new ArrayList<TableEditor>();

        for (int i = 0; i < items.length; i++) {
            TableItem tableItem = items[i];
            if (!previousItemsHash.contains(tableItem)) {

                for (int iEditorCol = 0; iEditorCol < columnsWithEditorContent.size(); iEditorCol++) {
                    TableViewerCreatorColumnNotModifiable column = columnsWithEditorContent.get(iEditorCol);

                    TableEditorContentNotModifiable tableEditorContent = column.getTableEditorContent();

                    String idProperty = column.getId();

                    TableEditor tableEditor = addTableEditor(column, tableEditorContent, idProperty, tableItem);
                    addedTableEditor.add(tableEditor);

                }
            }
        }

        for (int i = indexStart; i < items.length; i++) {
            TableItem tableItem = items[i];
            Object data = tableItem.getData();
            Collection<TableEditor> tableEditorCollection = (Collection<TableEditor>) dataToMultipleDataEditor
                    .getCollection(data);
            for (TableEditor tableEditor : tableEditorCollection) {
                tableEditor.setItem(tableItem);
            }
        }

        previousItemsHash = new HashSet<TableItem>(Arrays.asList((TableItem[]) items));

    }

    private void handleRemovedEvent(final ListenableListEvent event) {

        Collection removedObjects = event.removedObjects;

        for (Object data : removedObjects) {
            Collection<TableEditor> collection = dataToMultipleDataEditor.getCollection(data);
            for (TableEditor tableEditor : collection) {
                tableEditorList.remove(tableEditor);
                disposeTableEditor(tableEditor);
            }
            dataToMultipleDataEditor.remove(data);
        }

        int tableEditorListSize = tableEditorList.size();
        for (int i = 0; i < tableEditorListSize; i++) {
            TableEditor tableEditor = tableEditorList.get(i);
            tableEditor.layout();
        }

    }

    private void disposeTableEditor(TableEditor tableEditor) {
        Control editor = tableEditor.getEditor();
        if (editor != null && !editor.isDisposed()) {
            editor.dispose();
        }
        tableEditor.dispose();
    }

    private void handleSwapedEvent(ListenableListEvent event) {

        if (tableViewerCreator.getTable().isDisposed()) {
            return;
        }

        Table table = tableViewerCreator.getTable();
        TableItem[] items = table.getItems();

        // //////////////////////////////////
        // Warning: using identity comparison
        // //////////////////////////////////
        Set dataHash = MapBackedSet.decorate(new IdentityHashMap());
        dataHash.addAll(Arrays.asList((Object[]) event.swapedObjects));
        ;
        for (int i = 0; i < items.length; i++) {
            TableItem tableItem = items[i];
            Object data = tableItem.getData();
            if (dataHash.contains(data)) {
                Collection<TableEditor> tableEditorCollection = (Collection<TableEditor>) dataToMultipleDataEditor
                        .getCollection(data);
                for (TableEditor tableEditor : tableEditorCollection) {
                    tableEditor.setItem(tableItem);
                }
            }
        }

    }

    @SuppressWarnings("unchecked")
    public void refresh() {
        if (tableEditorList == null) {// widget have been disposed and release method called so ignore the refresh
            return;
        }
        for (int i = 0; i < tableEditorList.size(); i++) {
            TableEditor tableEditor = tableEditorList.get(i);
            disposeTableEditor(tableEditor);
            fireEvent(new TableEditorManagerEvent(EVENT_TYPE.CONTROL_DISPOSED, tableEditor));
        }

        tableEditorList.clear();
        dataToMultipleDataEditor.clear();

        Table table = tableViewerCreator.getTable();
        if (table.isDisposed()) {
            return;
        }

        TableItem[] items = table.getItems();

        previousItemsHash = new HashSet<TableItem>(Arrays.asList((TableItem[]) items));

        columns = tableViewerCreator.getColumns();
        columnsWithEditorContent = new ArrayList<TableViewerCreatorColumnNotModifiable>(columns.size());

        for (int iCol = 0; iCol < columns.size(); iCol++) {
            TableViewerCreatorColumnNotModifiable column = columns.get(iCol);
            TableEditorContentNotModifiable tableEditorContent = column.getTableEditorContent();
            if (tableEditorContent != null) {
                columnsWithEditorContent.add(column);
            }
        }

        for (int iEditorCol = 0; iEditorCol < columnsWithEditorContent.size(); iEditorCol++) {
            TableViewerCreatorColumnNotModifiable column = columnsWithEditorContent.get(iEditorCol);

            TableEditorContentNotModifiable tableEditorContent = column.getTableEditorContent();

            String idProperty = column.getId();

            for (int i = 0; i < items.length; i++) {
                addTableEditor(column, tableEditorContent, idProperty, items[i]);
            }
        }

    }

    private TableEditor addTableEditor(TableViewerCreatorColumnNotModifiable column,
            TableEditorContentNotModifiable tableEditorContent, String idProperty, TableItem tableItem) {

        tableEditorContent.setLayoutEnabled(true);
        TableEditor tableEditor = tableEditorContent.createTableEditor(tableItem.getParent());
        tableEditorList.add(tableEditor);
        dataToMultipleDataEditor.put(tableItem.getData(), tableEditor);

        Object currentRowObject = tableItem.getData();
        Object value = tableViewerCreator.getCellModifier().getValue(currentRowObject, idProperty);
        Control control = tableEditorContent.initialize(tableItem.getParent(), tableEditor, column, currentRowObject, value);

        // control.addDisposeListener(new DisposeListener() {
        //
        // public void widgetDisposed(DisposeEvent e) {
        //
        // // System.out.println(e);
        //
        // }
        //
        // });

        if (tableItem != null && !tableItem.isDisposed()) {
            tableEditor.setEditor(control, tableItem, column.getIndex());
            fireEvent(new TableEditorManagerEvent(EVENT_TYPE.CONTROL_CREATED, tableEditor));
        }

        return tableEditor;
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

    public void release() {

        releaseListenableListListener();
        listenableListListener = null;
        tableViewerCreator = null;
        int tableEditorListListSize = tableEditorList.size();
        for (int i = 0; i < tableEditorListListSize; i++) {
            TableEditor tableEditor = tableEditorList.get(i);
            disposeTableEditor(tableEditor);
        }
        tableEditorList = null;
        listeners = null;
        listenableList = null;
        columns = null;
        columnsWithEditorContent = null;
        previousItemsHash = null;
        dataToMultipleDataEditor = null;
    }

    private void releaseListenableListListener() {
        if (listenableList != null && listenableListListener != null) {
            listenableList.removeListener(listenableListListener);

            // System.out.println("TableEditor " + this.hashCode() + " Listener " + listenableListListener.hashCode()
            // + " REMOVED into listenableList " + listenableList.hashCode());

        }
    }

}
