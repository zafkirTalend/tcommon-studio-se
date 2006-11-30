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

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator.LAYOUT_MODE;
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

    /**
     * DOC amaumont AbstractMacroTableView constructor comment.
     */
    public AbstractExtendedTableViewer(ExtendedTableModel<B> extendedTableModel, Composite parent) {
        super(extendedTableModel, parent);
        initTable();
        if (getExtendedControlModel() != null) {
            initModelListeners();
        }
    }

    public AbstractExtendedTableViewer(Composite parent) {
        super(null, parent);
        initTable();
    }

    /**
     * DOC amaumont Comment method "init".
     * 
     */
    protected void initTable() {
        if (this.tableViewerCreator != null) {
            this.tableViewerCreator.getTable().dispose();
        }
        this.tableViewerCreator = createTable(getParentComposite());
        createColumns(this.tableViewerCreator, this.tableViewerCreator.getTable());
        loadTable();
    }

    /**
     * DOC amaumont Comment method "loadTable".
     */
    private void loadTable() {
        ExtendedTableModel<B> extendedTableModel = getExtendedTableModel();
        if (extendedTableModel != null) {
            if (extendedTableModel.isDataRegistered()) {
                this.tableViewerCreator.init(getBeansList());
            } else {
                this.tableViewerCreator.init();
            }
            extendedTableModel.setModifiedBeanListenable(this.tableViewerCreator);
        }
    }

    protected TableViewerCreator<B> createTable(Composite parentComposite) {
        TableViewerCreator<B> newTableViewerCreator = new TableViewerCreator<B>(parentComposite);
        newTableViewerCreator.setLayout(new RowLayout(SWT.HORIZONTAL));

        setTableViewerCreatorOptions(newTableViewerCreator);

        final Table table = newTableViewerCreator.createTable();

        table.setLayoutData(new GridData(GridData.FILL_BOTH));

        return newTableViewerCreator;
    }

    /**
     * Ov.
     * 
     * @param newTableViewerCreator
     */
    protected void setTableViewerCreatorOptions(TableViewerCreator<B> newTableViewerCreator) {
        newTableViewerCreator.setLayoutMode(LAYOUT_MODE.CONTINUOUS);
        newTableViewerCreator.setAllColumnsResizable(true);
        newTableViewerCreator.setBorderVisible(true);
        newTableViewerCreator.setFirstColumnMasked(true);
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
        getExtendedTableModel().addBeforeOperationListListener(1, new IListenableListListener() {

            public void handleEvent(ListenableListEvent event) {
                handleBeforeListenableListOperationEvent(event);
            }

        });

        getExtendedTableModel().addAfterListener(1, new IListenableListListener() {

            public void handleEvent(ListenableListEvent event) {
                handleAfterListenableListOperationEvent(event);
            }

        });

        getExtendedTableModel().addAfterListener(100, new IListenableListListener() {

            public void handleEvent(ListenableListEvent event) {
                if (event.type == TYPE.ADDED) {
                    tableViewerCreator.getTable().forceFocus();
                    tableViewerCreator.getSelectionHelper().setSelection(event.index, event.index + event.addedObjects.size() - 1);
                }
            }

        });

    }

    /**
     * DOC amaumont Comment method "handleListenableListEvent".
     * 
     * @param event
     */
    protected void handleBeforeListenableListOperationEvent(ListenableListEvent event) {
        if (tableViewerCreator.getInputList() == null && getExtendedTableModel().isDataRegistered()) {
            tableViewerCreator.setInputList(getBeansList());
            tableViewerCreator.layout();
        } else {
            if (event.type == TYPE.REMOVED) {
                tableViewerCreator.getTable().deselectAll();
                tableViewerCreator.getTableViewer().remove(event.removedObjects.toArray());
                tableViewerCreator.layout();
            }
        }
    }

    /**
     * DOC amaumont Comment method "handleListenableListEvent".
     * 
     * @param event
     */
    protected void handleAfterListenableListOperationEvent(ListenableListEvent event) {
        if (event.type == TYPE.LIST_REGISTERED && tableViewerCreator.getInputList() == null && getExtendedTableModel().isDataRegistered()) {
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
        loadTable();
        initModelListeners();
    }

}
