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
package org.talend.commons.ui.swt.tableviewer.sort;

import java.text.Collator;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreatorColumn;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator.SORT;

/**
 * 
 * The default <code>ViewerSorter</code> used by <code>TableRecflect</code>. <br/>
 * 
 * $Id$
 * 
 */
public class TableViewerCreatorSorter extends ViewerSorter {

    private MultipleColumnsBeanComparator columnsBeanComparator;

    private TableViewerCreatorColumn lastIdColumnSorted;

    private SORT orderDirection;

    public TableViewerCreatorSorter() {
        init();
    }

    public TableViewerCreatorSorter(Collator collator) {
        super(collator);
        init();
    }

    private void init() {
        this.columnsBeanComparator = new MultipleColumnsBeanComparator();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.ViewerComparator#compare(org.eclipse.jface.viewers.Viewer, java.lang.Object,
     * java.lang.Object)
     */
    @Override
    public int compare(Viewer viewer, Object object1, Object object2) {
        if (lastIdColumnSorted != null && lastIdColumnSorted.getComparator() != null) {
            return lastIdColumnSorted.getComparator().compare(object1, object2);
        } else {
            return this.columnsBeanComparator.compare(object1, object2);
        }
    }

    public void prepareSort(TableViewerCreator tableViewerCreator, TableViewerCreatorColumn tableViewerCreatorColumn) {
        SORT newOrderDirection = SORT.ASC;
        if (this.lastIdColumnSorted != null && this.lastIdColumnSorted == tableViewerCreatorColumn) {
            newOrderDirection = (this.orderDirection == SORT.ASC) ? SORT.DESC : SORT.ASC;
        }
        prepareSort(tableViewerCreator, tableViewerCreatorColumn, newOrderDirection);
    }

    /**
     * DOC amaumont Comment method "prepareSort".
     * 
     * @param tableViewerCreator
     * @param tableViewerCreatorColumn
     * @param newOrderDirection
     */
    public void prepareSort(TableViewerCreator tableViewerCreator, TableViewerCreatorColumn tableViewerCreatorColumn,
            SORT newOrderDirection) {
        tableViewerCreator.getTable().setSortColumn(tableViewerCreatorColumn.getTableColumn());
        tableViewerCreator.getTable().setSortDirection(newOrderDirection == SORT.DESC ? SWT.DOWN : SWT.UP);
        this.columnsBeanComparator.setIgnoreCase(tableViewerCreatorColumn.isOrderWithIgnoreCase());
        this.lastIdColumnSorted = tableViewerCreatorColumn;
        this.columnsBeanComparator.setColumnToOrder(tableViewerCreatorColumn);
        this.columnsBeanComparator.setAscendingOrder(newOrderDirection == SORT.ASC);
        this.orderDirection = newOrderDirection;
    }

}
