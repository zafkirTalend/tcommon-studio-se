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

import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.swt.widgets.TableItem;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreatorColumn;
import org.talend.commons.ui.swt.tableviewer.data.AccessorUtils;
import org.talend.commons.ui.swt.tableviewer.data.ModifiedObjectInfo;

/**
 * 
 * Default implementation of <code>ICellModifier</code> used by <code>TableViewerCreator</code>. This
 * implementation retrieve or modify values with reflection on data object. <br/>
 * 
 * $Id$
 * 
 * @see ICellModifier
 * 
 * @param <O>
 */
public class DefaultCellModifier<O> implements ICellModifier {

    TableViewerCreator tableViewerCreator;

    public DefaultCellModifier(TableViewerCreator tableViewerCreator) {
        super();
        this.tableViewerCreator = tableViewerCreator;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.ICellModifier#canModify(java.lang.Object, java.lang.String)
     */
    public boolean canModify(Object element, String property) {
        return tableViewerCreator.getColumn(property).isModifiable();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.ICellModifier#getValue(java.lang.Object, java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public Object getValue(Object bean, String idProperty) {
        TableViewerCreatorColumn column = tableViewerCreator.getColumn(idProperty);
        ModifiedObjectInfo modifiedObjectInfo = this.tableViewerCreator.getModifiedObjectInfo();
        modifiedObjectInfo.setCurrentModifiedBean(bean);
        modifiedObjectInfo.setCurrentModifiedColumn(column);
        modifiedObjectInfo.setCurrentModifiedIndex(this.tableViewerCreator.getInputList().indexOf(bean));
        
        Object returnValue = null;
        Object value = AccessorUtils.get(bean, column);

        if (column.getRetrieverValue() != null) {
            returnValue = column.getRetrieverValue().getCellEditorTypedValue(column.getCellEditor(), value);
        } else {
            returnValue = value;
        }
        if (returnValue == null && column.getDefaultInternalValue() != null) {
            returnValue = column.getDefaultInternalValue();
        }
        modifiedObjectInfo.setOriginalPropertyBeanValue(returnValue);
        modifiedObjectInfo.setPreviousPropertyBeanValue(returnValue);
        // System.out.println("getValue : value=" + returnValue);
        return returnValue;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.ICellModifier#modify(java.lang.Object, java.lang.String, java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    public void modify(Object element, String idColumn, Object value) {
        Object data = ((TableItem) element).getData();
        TableViewerCreatorColumn column = tableViewerCreator.getColumn(idColumn);
        if (column.getBeanPropertyAccessors() != null) {
            Object typedValue = null;
            if (column.getRetrieverValue() != null) {
                typedValue = column.getRetrieverValue().getOriginalTypedValue(column.getCellEditor(), value);
            } else {
                typedValue = value;
            }
            if (typedValue == null && column.getDefaultInternalValue() != null) {
                typedValue = column.getDefaultInternalValue();
            }
            AccessorUtils.set(data, typedValue, column);
        }
        tableViewerCreator.getTableViewer().refresh(data);
        ModifiedObjectInfo modifiedObjectInfo = this.tableViewerCreator.getModifiedObjectInfo();
        modifiedObjectInfo.setPreviousModifiedBean(((TableItem) element).getData());
        modifiedObjectInfo.setPreviousModifiedIndex(modifiedObjectInfo.getCurrentModifiedIndex());
        modifiedObjectInfo.setCurrentModifiedBean(null);
        modifiedObjectInfo.setPreviousModifiedColumn(modifiedObjectInfo.getCurrentModifiedColumn());
        modifiedObjectInfo.setCurrentModifiedColumn(null);
        modifiedObjectInfo.setOriginalPropertyBeanValue(null);
    }
}
