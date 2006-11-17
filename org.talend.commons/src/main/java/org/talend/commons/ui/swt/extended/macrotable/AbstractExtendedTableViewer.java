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

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator.LAYOUT_MODE;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator.LINE_SELECTION;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator.SHOW_SELECTION;
import org.talend.commons.utils.data.list.IListenableListListener;
import org.talend.commons.utils.data.list.ListenableListEvent;
import org.talend.commons.utils.data.list.ListenableListEvent.TYPE;

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
        this.tableViewerCreator = createTable(parentComposite, styleChild);
        getExtendedTableModel().setModifiedBeanListenable(this.tableViewerCreator);
        createColumns(this.tableViewerCreator, this.tableViewerCreator.getTable());
        if (getExtendedTableModel().isDataRegistered()) {
//            this.tableViewerCreator.init(new ArrayList<B>(((ListenableList)getExtendedTableModel().getBeansList()).getOriginaList()));
            this.tableViewerCreator.init(getExtendedTableModel().getBeansList());
        } else {
            this.tableViewerCreator.init();
        }

        getExtendedTableModel().setModifiedBeanListenable(this.tableViewerCreator);

        initListeners();
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
            tableViewerCreator.setInputList(getExtendedTableModel().getBeansList());
        } else {
            if (event.type == TYPE.REMOVED) {
                tableViewerCreator.getTableViewer().remove(event.removedObjects.toArray());
            }
        }
    }

    /**
     * DOC amaumont Comment method "handleListenableListEvent".
     * 
     * @param event
     */
    protected void handleAfterListenableListOperationEvent(ListenableListEvent event) {
        if (tableViewerCreator.getInputList() == null && getExtendedTableModel().isDataRegistered()) {
//            tableViewerCreator.setInputList(new ArrayList<B>(((ListenableList)getExtendedTableModel().getBeansList()).getOriginaList()));
            tableViewerCreator.setInputList(getExtendedTableModel().getBeansList());
        } else {
            
            if (event.type == TYPE.REMOVED) {
            } else if (event.type == TYPE.ADDED) {
                tableViewerCreator.getTableViewer().add(event.addedObjects.toArray());
//            } else if (event.type == TYPE.SWAPED) {
//                tableViewerCreator.getTableViewer().(event.addedObjects.toArray());
            } else {
                tableViewerCreator.getTableViewer().refresh();
            }
        }
    }
    
    protected TableViewerCreator<B> createTable(Composite parentComposite, int styleChild) {
        TableViewerCreator<B> newTableViewerCreator = new TableViewerCreator<B>(parentComposite);
        newTableViewerCreator.setLayout(new RowLayout(SWT.HORIZONTAL));

        newTableViewerCreator.setHeaderVisible(true);
        newTableViewerCreator.setAllColumnsResizable(true);
        newTableViewerCreator.setBorderVisible(true);
        newTableViewerCreator.setLinesVisible(true);
        newTableViewerCreator.setShowSelection(SHOW_SELECTION.FULL);
        newTableViewerCreator.setLineSelection(LINE_SELECTION.MULTI);
        newTableViewerCreator.setLayoutMode(LAYOUT_MODE.CONTINUOUS);
        newTableViewerCreator.setFirstColumnMasked(true);
        newTableViewerCreator.setFirstVisibleColumnIsSelection(true);
        newTableViewerCreator.setBgColorForEmptyArea(parentComposite.getDisplay().getSystemColor(SWT.COLOR_WHITE));
//        newTableViewerCreator.setUseCustomColoring(true);

        final Table table = newTableViewerCreator.createTable();
        table.setLayoutData(new GridData(GridData.FILL_BOTH));

        return newTableViewerCreator;
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
        tableViewerCreator.init(getExtendedTableModel().getBeansList());
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
