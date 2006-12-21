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
package org.talend.commons.ui.swt.tableviewer.behavior;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.events.SelectionEvent;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreatorColumn;
import org.talend.commons.ui.swt.tableviewer.selection.ITableColumnSelectionListener;
import org.talend.commons.ui.swt.tableviewer.sort.IColumnSortedListener;
import org.talend.commons.ui.swt.tableviewer.sort.TableViewerCreatorSorter;

/**
 * DOC amaumont class global comment. Detailled comment <br/> $Id: DefaultHeaderColumnSelectionListener.java,v 1.3
 * 2006/06/02 15:24:10 amaumont Exp $
 */
public class DefaultHeaderColumnSelectionListener implements ITableColumnSelectionListener {

    private TableViewerCreatorColumn tableViewerCreatorColumn;

    private TableViewerCreator tableViewerCreator;

    private List<IColumnSortedListener> sortListenerList = new ArrayList<IColumnSortedListener>();

    public DefaultHeaderColumnSelectionListener(TableViewerCreatorColumn tableViewerCreatorColumn, TableViewerCreator tableViewerCreator) {
        super();
        this.tableViewerCreatorColumn = tableViewerCreatorColumn;
        this.tableViewerCreator = tableViewerCreator;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
     */
    public void widgetDefaultSelected(SelectionEvent e) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
     */
    public void widgetSelected(SelectionEvent e) {
        TableViewerCreatorSorter viewerSorter = (TableViewerCreatorSorter) tableViewerCreator.getTableViewer().getSorter();
        viewerSorter.prepareSort(tableViewerCreator, tableViewerCreatorColumn);
        tableViewerCreator.getTableViewer().refresh();
        fireColumnSorted();
    }

    /**
     * DOC amaumont Comment method "fireColumnSorted".
     */
    private void fireColumnSorted() {
        for (IColumnSortedListener columnSortedListener : sortListenerList) {
            columnSortedListener.handle();
        }
    }

    public void addColumnSortedListener(IColumnSortedListener columnSortedListener) {
        sortListenerList.add(columnSortedListener);
    }

    public void removeColumnSortedListener(IColumnSortedListener columnSortedListener) {
        sortListenerList.remove(columnSortedListener);
    }

    public TableViewerCreatorColumn getTableViewerCreatorColumn() {
        return this.tableViewerCreatorColumn;
    }

    public void setTableViewerCreatorColumn(TableViewerCreatorColumn tableViewerCreatorColumn) {
        this.tableViewerCreatorColumn = tableViewerCreatorColumn;
    }

    public TableViewerCreator getTableViewerCreator() {
        return this.tableViewerCreator;
    }

    public void setTableViewerCreator(TableViewerCreator tableViewerCreator) {
        this.tableViewerCreator = tableViewerCreator;
    }

}
