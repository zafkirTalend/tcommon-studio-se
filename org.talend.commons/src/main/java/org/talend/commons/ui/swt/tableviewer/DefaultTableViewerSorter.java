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
package org.talend.commons.ui.swt.tableviewer;

import java.text.Collator;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator.SORT;
import org.talend.commons.utils.data.sort.MultiplePropertiesBeanComparator;

/**
 * 
 * The default <code>ViewerSorter</code> used by <code>TableRecflect</code>. <br/>
 * 
 * $Id$
 * 
 */
public class DefaultTableViewerSorter extends ViewerSorter {

    private MultiplePropertiesBeanComparator propertiesBeanComparator;

    private String lastIdPropertySorted;

    private SORT orderDirection;

    public DefaultTableViewerSorter() {
        init();
    }

    public DefaultTableViewerSorter(Collator collator) {
        super(collator);
        init();
    }

    private void init() {
        this.propertiesBeanComparator = new MultiplePropertiesBeanComparator();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.ViewerComparator#compare(org.eclipse.jface.viewers.Viewer, java.lang.Object,
     * java.lang.Object)
     */
    @Override
    public int compare(Viewer viewer, Object object1, Object object2) {
        return this.propertiesBeanComparator.compare(object1, object2);
    }

    public void prepareSort(TableViewerCreator tableViewerCreator, TableViewerCreatorColumn tableViewerCreatorColumn) {
        SORT newOrderDirection = SORT.ASC;
        if (this.lastIdPropertySorted != null && this.lastIdPropertySorted.equals(tableViewerCreatorColumn.getId())) {
            newOrderDirection = (this.orderDirection == SORT.ASC) ? SORT.DESC : SORT.ASC;
        }
        tableViewerCreator.getTable().setSortColumn(tableViewerCreatorColumn.getTableColumn());
        tableViewerCreator.getTable().setSortDirection(newOrderDirection == SORT.DESC ? SWT.DOWN : SWT.UP);
        this.propertiesBeanComparator.setIgnoreCase(tableViewerCreatorColumn.isOrderWithIgnoreCase());
        this.lastIdPropertySorted = tableViewerCreatorColumn.getId();
        // TODO change this without reflection
        this.propertiesBeanComparator.setProperty(tableViewerCreatorColumn.getBeanPropertyName());
        this.propertiesBeanComparator.setAscendingOrder(newOrderDirection == SORT.ASC);
        this.orderDirection = newOrderDirection;
    }

}
