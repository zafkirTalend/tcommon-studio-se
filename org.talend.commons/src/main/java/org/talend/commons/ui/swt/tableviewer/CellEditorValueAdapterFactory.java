// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006-2007 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
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

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.talend.commons.ui.swt.celleditor.ComboxCellEditorImproved;
import org.talend.commons.ui.swt.tableviewer.behavior.CellEditorValueAdapter;
import org.talend.commons.ui.swt.tableviewer.behavior.ComboEditorValueAdapter;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class CellEditorValueAdapterFactory {

    public static CellEditorValueAdapter getPositiveIntAdapter() {

        CellEditorValueAdapter positiveIntValueAdapter = new CellEditorValueAdapter() {

            public Object getOriginalTypedValue(final CellEditor cellEditor, Object value) {
                try {
                    Integer integer = new Integer(value.toString().trim());
                    if (integer < 0) {
                        return integer * -1;
                    }
                    return integer;
                } catch (Exception ex) {
                    return 0;
                }
            }

            public Object getCellEditorTypedValue(final CellEditor cellEditor, Object value) {
                if (value != null && value instanceof Integer && ((Integer) value).intValue() != -1
                        && ((Integer) value).intValue() != 0) {
                    return String.valueOf(value);
                }
                return "";
            }

            /*
             * (non-Javadoc)
             * 
             * @see org.talend.commons.ui.swt.tableviewer.behavior.CellEditorValueAdapter#getColumnText(org.eclipse.jface.viewers.CellEditor,
             * java.lang.Object)
             */
            @Override
            public String getColumnText(CellEditor cellEditor, Object bean, Object cellEditorTypedValue) {
                return (String) getCellEditorTypedValue(cellEditor, cellEditorTypedValue);
            }

        };

        return positiveIntValueAdapter;
    }

    /**
     * DOC amaumont Comment method "getComboAdapter".
     * 
     * @return
     */
    public static CellEditorValueAdapter getComboAdapterForComboCellEditor(final String defaultItem) {
        return new ComboEditorValueAdapter(defaultItem) {

            @Override
            public String[] getItems(CellEditor cellEditor) {
                return ((ComboBoxCellEditor) cellEditor).getItems();
            }

        };

    }

    /**
     * DOC amaumont Comment method "getComboAdapter".
     * 
     * @return
     */
    public static CellEditorValueAdapter getComboAdapterForComboCellEditor() {
        return getComboAdapterForComboCellEditor(null);
    }

    /**
     * DOC amaumont Comment method "getComboAdapter".
     * 
     * @return
     */
    public static CellEditorValueAdapter getComboAdapterForComboCellEditorImproved(final String defaultItem) {
        return new ComboEditorValueAdapter(defaultItem) {

            @Override
            public String[] getItems(CellEditor cellEditor) {
                return ((ComboxCellEditorImproved) cellEditor).getItems();
            }

        };

    }

    /**
     * DOC amaumont Comment method "getComboAdapter".
     * 
     * @return
     */
    public static CellEditorValueAdapter getComboAdapterForComboCellEditorImproved() {
        return getComboAdapterForComboCellEditorImproved(null);
    }

    /**
     * DOC amaumont Comment method "getNullToEmptyStringTextAdapater".
     */
    public static CellEditorValueAdapter getNullToEmptyStringTextAdapater() {
        CellEditorValueAdapter nullToEmptyStringAdapter = new CellEditorValueAdapter() {

            public Object getCellEditorTypedValue(final CellEditor cellEditor, Object value) {
                if (value != null) {
                    if (!(value instanceof String)) {
                        throw new IllegalArgumentException("Bean value should be a class of String type !");
                    }
                    return value;
                }

                return "";
            }

        };

        return nullToEmptyStringAdapter;
    }

}
