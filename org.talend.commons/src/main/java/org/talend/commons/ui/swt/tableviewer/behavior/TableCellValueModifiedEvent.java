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

import org.eclipse.swt.widgets.TableItem;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreatorColumn;

/**
 * This event is used by TableViewerCreator to notify that a cell value has changed.
 * 
 * $Id$
 * 
 */
public class TableCellValueModifiedEvent {

    /** current tableItem where value has been applied. */
    public TableItem tableItem;

    /** current bean where value has been stored. */
    public Object bean;

    /** current column where value has been applied. */
    public TableViewerCreatorColumn column;

    /** value returned by CellEditor. */
    public Object cellEditorAppliedValue;

    /** value stored in the bean. */
    public Object storedValue;

    /**
     * DOC amaumont CellEditorAppliedEvent constructor comment.
     * 
     * @param tableItem
     * @param bean
     * @param idColumn
     * @param cellEditorAppliedValue
     * @param storedValue
     */
    public TableCellValueModifiedEvent(TableItem tableItem, Object bean, TableViewerCreatorColumn column, Object cellEditorAppliedValue, Object storedValue) {
        this.tableItem = tableItem;
        this.bean = bean;
        this.column = column;
        this.cellEditorAppliedValue = cellEditorAppliedValue;
        this.storedValue = storedValue;
    }

}
