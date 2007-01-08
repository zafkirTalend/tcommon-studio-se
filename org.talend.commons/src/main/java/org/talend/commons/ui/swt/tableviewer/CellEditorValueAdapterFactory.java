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

import org.eclipse.jface.viewers.CellEditor;
import org.talend.commons.ui.swt.tableviewer.behavior.CellEditorValueAdapter;


/**
 * DOC amaumont  class global comment. Detailled comment
 * <br/>
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
                    return null;
                }
            }

            public Object getCellEditorTypedValue(final CellEditor cellEditor, Object value) {
                if (value != null && value instanceof Integer && ((Integer) value).intValue() != -1 && ((Integer) value).intValue() != 0) {
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
            public String getColumnText(CellEditor cellEditor, Object cellEditorTypedValue) {
                return (String) getCellEditorTypedValue(cellEditor, cellEditorTypedValue);
            }

        };

        return positiveIntValueAdapter;
    }
    
    
}
