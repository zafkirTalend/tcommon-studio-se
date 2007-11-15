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
    public TableCellValueModifiedEvent(TableItem tableItem, Object bean, TableViewerCreatorColumn column, Object cellEditorAppliedValue,
            Object storedValue) {
        this.tableItem = tableItem;
        this.bean = bean;
        this.column = column;
        this.cellEditorAppliedValue = cellEditorAppliedValue;
        this.storedValue = storedValue;
    }

}
