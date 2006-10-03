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

import org.eclipse.jface.viewers.CellEditor;

/**
 * 
 * This class is used to adapt value from external data to cell editor and inverse.
 * 
 * <BR/>If your property's object has a different type of the cellEditor value, you must adapt them.
 * 
 * <BR/>For example you have a price property with Integer type and you want use a TextCellEditor. The TextCellEditor
 * only accept a String value. Then you must convert the value :
 * <p>
 * <code>
 *  column.setCellEditor(new TextCellEditor(table), new CellEditorValueAdapter() {
 *
 *     public Object getOriginalTypedValue(CellEditor cellEditor, Object value) {
 *                return new Integer((String)value);
 *     }
 *
 *     public Object getCellEditorTypedValue(CellEditor cellEditor, Object value) {
 *          return String.valueOf(value);
 *     }
 *   });
 * </code>
 * </p>
 * 
 * Other example with ComboBoxCellEditor :
 * <p>
 * <code>
 * 
 *        final String[] VALUE_SET = new String[] { "xxx", "yyy", "zzz" };
 *        column.setCellEditor(new ComboBoxCellEditor(table, VALUE_SET), new CellEditorValueAdapter() {
 *
 *            public String getColumnText(CellEditor cellEditor, Object value) {
 *                String[] items = ((ComboBoxCellEditor) cellEditor).getItems();
 *                int index = (Integer) value;
 *                if (index >= 0 && index < items.length) {
 *                    return items[index];
 *                } else {
 *                    return "";
 *                }
 *            }
 *        });
 * </code>
 * </p>
 * 
 * <br/>
 * 
 * $Id$
 * 
 */
public class CellEditorValueAdapter {

    /**
     * Convert cellEditorValue to String value which will be set in the current cell of the <code>Table</code>.
     * 
     * @param cellEditor current cellEditor, use if necessary.
     * @param cellEditorTypedValue value returned by cellEditor.
     * @return
     */
    public String getColumnText(CellEditor cellEditor, Object cellEditorTypedValue) {
        return String.valueOf(cellEditorTypedValue);
    };

    /**
     * Convert cellEditor value to original type of data (type of object's property which is introspected).
     * 
     * @param cellEditor
     * @param cellEditorTypedValue
     * @return
     */
    public Object getOriginalTypedValue(CellEditor cellEditor, Object cellEditorTypedValue) {
        return cellEditorTypedValue;
    };

    /**
     * Convert original typed value to cellEditor typed value.
     * 
     * DOC amaumont Comment method "getInternalTypedValue".
     * 
     * @param cellEditor
     * @param originalTypedValue
     * @return
     */
    public Object getCellEditorTypedValue(CellEditor cellEditor, Object originalTypedValue) {
        return originalTypedValue;
    };
}
