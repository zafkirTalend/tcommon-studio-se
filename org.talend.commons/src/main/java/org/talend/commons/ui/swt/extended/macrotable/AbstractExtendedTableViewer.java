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
package org.talend.commons.ui.swt.extended.macrotable;

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
    public AbstractExtendedTableViewer(ExtendedTableModel<B> extendedTable, Composite parent, int styleChild) {
        super(extendedTable, parent);
        init(styleChild);
    }

    /**
     * DOC amaumont Comment method "init".
     * 
     * @param styleChild
     */
    protected void init(int styleChild) {
        this.tableViewerCreator = createTable(parentComposite);
        getExtendedTableModel().setModifiedBeanListenable(this.tableViewerCreator);
        createColumns(this.tableViewerCreator, this.tableViewerCreator.getTable());
        if (getExtendedTableModel().isDataRegistered()) {
            this.tableViewerCreator.init(getBeansList());
        } else {
            this.tableViewerCreator.init();
        }

        getExtendedTableModel().setModifiedBeanListenable(this.tableViewerCreator);

        initListeners();
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
    protected void initListeners() {
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
     * @param newTableViewerCreator
     */
    protected void setTableViewerCreatorOptions(TableViewerCreator<B> newTableViewerCreator) {
        newTableViewerCreator.setLayoutMode(LAYOUT_MODE.CONTINUOUS);
        newTableViewerCreator.setAllColumnsResizable(true);
        newTableViewerCreator.setBorderVisible(true);
        newTableViewerCreator.setFirstColumnMasked(true);
        newTableViewerCreator.setFirstVisibleColumnIsSelection(false);
        newTableViewerCreator.setCheckboxInFirstColumn(false);
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
        return (ExtendedTableModel<B>) this.extendedControl;
    }

    /**
     * Sets the extendedTable.
     * 
     * @param extendedTable the extendedTable to set
     */
    public void setExtendedTable(ExtendedTableModel<B> extendedTable) {
        this.extendedControl = extendedTable;
        tableViewerCreator.init(getBeansList());
    }

    /**
     * Getter for parentComposite.
     * 
     * @return the parentComposite
     */
    public Composite getParentComposite() {
        return this.parentComposite;
    }

    /**
     * Getter for tableViewerCreator.
     * 
     * @return the tableViewerCreator
     */
    public TableViewerCreator<B> getTableViewerCreator() {
        return this.tableViewerCreator;
    }

}
