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
package org.talend.commons.ui.swt.extended.table;

import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreatorColumn;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator.LAYOUT_MODE;
import org.talend.commons.ui.swt.tableviewer.selection.ILineSelectionListener;
import org.talend.commons.ui.swt.tableviewer.selection.LineSelectionEvent;
import org.talend.commons.ui.swt.tableviewer.selection.SelectionHelper;
import org.talend.commons.utils.data.list.IListenableListListener;
import org.talend.commons.utils.data.list.ListenableListEvent;
import org.talend.commons.utils.data.list.ListenableListEvent.TYPE;
import org.talend.commons.utils.threading.AsynchronousThreading;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 * @param <B> Type of beans
 */
public abstract class AbstractExtendedTableViewer<B> extends AbstractExtendedControlViewer {

    protected TableViewerCreator<B> tableViewerCreator;

    private boolean executeSelectionEvent = true;

    protected boolean forceExecuteSelectionEvent;

    /**
     * DOC amaumont AbstractMacroTableView constructor comment.
     */
    public AbstractExtendedTableViewer(ExtendedTableModel<B> extendedTableModel, Composite parent) {
        this(extendedTableModel, parent, false);
    }

    /**
     * DOC amaumont AbstractMacroTableView constructor comment.
     */
    public AbstractExtendedTableViewer(ExtendedTableModel<B> extendedTableModel, Composite parent, boolean readOnly) {
        super(extendedTableModel, parent, readOnly);
        initTable();
        if (getExtendedControlModel() != null) {
            initModelListeners();
        }
    }
    
    public AbstractExtendedTableViewer(Composite parent) {
        super(null, parent, false);
        initTable();
    }

    public AbstractExtendedTableViewer(Composite parent, boolean readOnly) {
        super(null, parent, readOnly);
        initTable();
    }
    
    /**
     * DOC amaumont Comment method "init".
     * 
     */
    protected void initTable() {
        if (this.tableViewerCreator != null && this.tableViewerCreator.getTable() != null) {
            this.tableViewerCreator.getTable().dispose();
        }
        this.tableViewerCreator = createTable(getParentComposite());
        createColumns(this.tableViewerCreator, this.tableViewerCreator.getTable());
        loadTableData();
        initTableListeners();
        this.tableViewerCreator.getTable().layout();
    }

    /**
     * DOC amaumont Comment method "initViewTableListeners".
     */
    protected void initTableListeners() {
        initLineSelectionListeners();
    }

    /**
     * DOC amaumont Comment method "loadTable".
     */
    private void loadTableData() {
        ExtendedTableModel<B> extendedTableModel = getExtendedTableModel();
        if (extendedTableModel != null) {
            if (extendedTableModel.isDataRegistered()) {
                this.tableViewerCreator.init(getBeansList());
            } else {
                this.tableViewerCreator.init();
            }
            extendedTableModel.setModifiedBeanListenable(this.tableViewerCreator);
        } else {
            this.tableViewerCreator.init(null);
        }
    }

    protected TableViewerCreator<B> createTable(Composite parentComposite) {
        TableViewerCreator<B> newTableViewerCreator = new TableViewerCreator<B>(parentComposite);
        newTableViewerCreator.setLayout(new RowLayout(SWT.HORIZONTAL));

        setTableViewerCreatorOptions(newTableViewerCreator);

        final Table table = newTableViewerCreator.createTable();

        table.setLayoutData(new GridData(GridData.FILL_BOTH));

        newTableViewerCreator.setCommandStack(getCommandStack());

        return newTableViewerCreator;
    }

    /**
     * DOC amaumont Comment method "initLineSelectionListeners".
     * 
     * @return
     */
    protected void initLineSelectionListeners() {
        final SelectionHelper selectionHelper = getTableViewerCreator().getSelectionHelper();
        final Table table = getTableViewerCreator().getTable();
        final ILineSelectionListener beforeLineSelectionListener = new ILineSelectionListener() {

            public void handle(LineSelectionEvent e) {
                if (e.selectionByMethod && !selectionHelper.isMouseSelectionning() && !forceExecuteSelectionEvent) {
                    executeSelectionEvent = false;
                } else {
                    executeSelectionEvent = true;
                }
            }
        };
        final ILineSelectionListener afterLineSelectionListener = new ILineSelectionListener() {

            public void handle(LineSelectionEvent e) {
                executeSelectionEvent = true;
            }
        };
        selectionHelper.addBeforeSelectionListener(beforeLineSelectionListener);
        selectionHelper.addAfterSelectionListener(afterLineSelectionListener);

        DisposeListener disposeListener = new DisposeListener() {

            public void widgetDisposed(DisposeEvent e) {
                selectionHelper.removeBeforeSelectionListener(beforeLineSelectionListener);
                selectionHelper.removeAfterSelectionListener(afterLineSelectionListener);
                table.removeDisposeListener(this);
            }
        };
        table.addDisposeListener(disposeListener);

        table.addListener(SWT.KeyDown, new Listener() {

            public void handleEvent(Event event) {
                if (event.character == '\u0001') { // CTRL + A
                    forceExecuteSelectionEvent = true;
                    selectionHelper.selectAll();
                    forceExecuteSelectionEvent = false;
                }
            }

        });
    }

    /**
     * .
     * 
     * @param newTableViewerCreator
     */
    protected void setTableViewerCreatorOptions(TableViewerCreator<B> newTableViewerCreator) {
        newTableViewerCreator.setLayoutMode(LAYOUT_MODE.CONTINUOUS);
        newTableViewerCreator.setColumnsResizableByDefault(true);
        newTableViewerCreator.setBorderVisible(true);
        newTableViewerCreator.setFirstColumnMasked(true);
        // newTableViewerCreator.setUseCustomItemColoring(true);
        newTableViewerCreator.setFirstVisibleColumnIsSelection(false);
        newTableViewerCreator.setCheckboxInFirstColumn(false);
        newTableViewerCreator.setBgColorForEmptyArea(getParentComposite().getDisplay().getSystemColor(SWT.COLOR_WHITE));
    }

    /**
     * DOC amaumont Comment method "getBeansList".
     * 
     * @return
     */
    private List<B> getBeansList() {
        return getExtendedTableModel().getBeansList();
    }

    /**
     * DOC amaumont Comment method "initListeners".
     */
    protected void initModelListeners() {
        if (getExtendedTableModel() != null) {

            getExtendedTableModel().addBeforeOperationListListener(1, new IListenableListListener() {

                public void handleEvent(ListenableListEvent event) {
                    handleBeforeListenableListOperationEvent(event);
                }

            });

            getExtendedTableModel().addAfterOperationListListener(1, new IListenableListListener() {

                public void handleEvent(ListenableListEvent event) {
                    handleAfterListenableListOperationEvent(event);
                }

            });

            getExtendedTableModel().addAfterOperationListListener(100, new IListenableListListener<B>() {

                public void handleEvent(ListenableListEvent<B> event) {
                    if (tableViewerCreator.getTable() != null && !tableViewerCreator.getTable().isDisposed()) {
                        tableViewerCreator.getTable().forceFocus();
                        if (event.type == TYPE.ADDED) {
                            if (event.index != null) {
                                tableViewerCreator.getSelectionHelper().setSelection(event.index,
                                        event.index + event.addedObjects.size() - 1);
                            } else if (event.indicesTarget != null) {
                                int[] selection = ArrayUtils.toPrimitive((Integer[]) event.indicesTarget.toArray(new Integer[0]));
                                tableViewerCreator.getSelectionHelper().setSelection(selection);

                            }
                        } else if (event.type == TYPE.REMOVED) {
                            if (event.index != null || event.indicesOrigin != null && event.indicesOrigin.size() > 0) {
                                int index = 0;
                                if (event.index != null) {
                                    index = event.index;
                                } else {
                                    index = event.indicesOrigin.get(event.indicesOrigin.size() - 1);
                                }
                                int itemCount = tableViewerCreator.getTable().getItemCount();
                                if (index >= itemCount) {
                                    index = itemCount - 1;
                                }
                                tableViewerCreator.getSelectionHelper().setSelection(index, index);
                            } else {
                                tableViewerCreator.getTable().deselectAll();
                            }

                        } else if (event.type == TYPE.SWAPED) {
                            if (event.indicesTarget != null) {
                                int[] selection = ArrayUtils.toPrimitive((Integer[]) event.indicesTarget.toArray(new Integer[0]));
                                tableViewerCreator.getSelectionHelper().setSelection(selection);
                            }
                        }
                    }

                }

            });
        }

    }

    /**
     * DOC amaumont Comment method "handleListenableListEvent".
     * 
     * @param event
     */
    protected void handleBeforeListenableListOperationEvent(ListenableListEvent<B> event) {
        if (tableViewerCreator.getTable() != null && !tableViewerCreator.getTable().isDisposed()) {
            if (tableViewerCreator.getInputList() == null && getExtendedTableModel().isDataRegistered()) {
                tableViewerCreator.setInputList(getBeansList());
                tableViewerCreator.layout();
            } else {
                if (event.type == TYPE.ADDED) {
                    tableViewerCreator.getTable().deselectAll();
                } else if (event.type == TYPE.REMOVED) {
                    // tableViewerCreator.getTable().deselectAll();
                    // tableViewerCreator.getTableViewer().remove(event.removedObjects.toArray());
                    // tableViewerCreator.layout();
                }
            }
        }
    }

    /**
     * DOC amaumont Comment method "handleListenableListEvent".
     * 
     * @param event
     */
    protected void handleAfterListenableListOperationEvent(ListenableListEvent<B> event) {
        if (tableViewerCreator.getTable() != null && !tableViewerCreator.getTable().isDisposed()) {
            if (event.type == TYPE.LIST_REGISTERED && tableViewerCreator.getInputList() == null
                    && getExtendedTableModel().isDataRegistered()) {
                tableViewerCreator.setInputList(getBeansList());
                new AsynchronousThreading(100, true, tableViewerCreator.getTable().getDisplay(), new Runnable() {

                    public void run() {
                        tableViewerCreator.layout();
                    }

                }).start();
            } else {

                tableViewerCreator.getTableViewer().refresh();

            }
        }
    }

    /**
     * DOC amaumont Comment method "createColumns".
     * 
     * @param tableViewerCreator2
     */
    protected abstract void createColumns(TableViewerCreator<B> tableViewerCreator, Table table);

    /**
     * Getter for extendedTable.
     * 
     * @return the extendedTable
     */
    @SuppressWarnings("unchecked")
    public ExtendedTableModel<B> getExtendedTableModel() {
        return (ExtendedTableModel<B>) getExtendedControlModel();
    }

    /**
     * Getter for tableViewerCreator.
     * 
     * @return the tableViewerCreator
     */
    public TableViewerCreator<B> getTableViewerCreator() {
        return this.tableViewerCreator;
    }

    /**
     * Getter for tableViewerCreator.
     * 
     * @return the tableViewerCreator
     */
    public Table getTable() {
        if (this.tableViewerCreator != null) {
            return this.tableViewerCreator.getTable();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.ui.swt.extended.macrotable.AbstractExtendedControlViewer#modelChanged(org.talend.commons.ui.swt.extended.macrotable.AbstractExtendedControlModel,
     * org.talend.commons.ui.swt.extended.macrotable.AbstractExtendedControlModel)
     */
    @Override
    protected void modelChanged(AbstractExtendedControlModel previousModel, AbstractExtendedControlModel newModel) {
        if (previousModel != null) {
            previousModel.release();
        }
        loadTableData();
        initModelListeners();
        this.tableViewerCreator.getTable().layout();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.ui.swt.extended.table.AbstractExtendedControlViewer#setCommandStackAdapter(org.talend.commons.ui.command.ICommandStackAdapter)
     */
    @Override
    public void setCommandStack(CommandStack commandStack) {
        super.setCommandStack(commandStack);
        if (tableViewerCreator != null) {
            tableViewerCreator.setCommandStack(commandStack);
        }
    }

    /**
     * DOC amaumont Comment method "setTableSelection".
     * 
     * @param selectionIndices
     */
    public void setTableSelection(int[] selectionIndices, boolean executeSelectionEvent) {
        SelectionHelper selectionHelper = getTableViewerCreator().getSelectionHelper();
        selectionHelper.setActiveFireSelectionChanged(false);
        selectionHelper.setSelection(selectionIndices);
        selectionHelper.setActiveFireSelectionChanged(true);

    }

    public boolean isExecuteSelectionEvent() {
        return getTableViewerCreator().getSelectionHelper().isActiveFireSelectionChanged();
    }

    public void setExecuteSelectionEvent(boolean executeSelectionEvent) {
        getTableViewerCreator().getSelectionHelper().setActiveFireSelectionChanged(executeSelectionEvent);
    }

    /* (non-Javadoc)
     * @see org.talend.commons.ui.swt.extended.table.AbstractExtendedControlViewer#setReadOnly(boolean)
     */
    @Override
    public void setReadOnly(boolean readOnly) {
        super.setReadOnly(readOnly);
        List<TableViewerCreatorColumn> columns = getTableViewerCreator().getColumns();
        for (TableViewerCreatorColumn column : columns) {
            column.setModifiable(!readOnly);
        }
        getTableViewerCreator().refreshTableEditorControls();
    }

    
    
}
