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
import org.eclipse.jface.viewers.ComboBoxCellEditor;


/**
 * DOC amaumont  class global comment. Detailled comment
 * <br/>
 *
 * $Id$
 *
 */
public abstract class ComboEditorValueAdapter extends CellEditorValueAdapter {

    private String defaultItem;

    /**
     * DOC amaumont ComboEditorValueAdapter constructor comment.
     * @param defaultItem
     */
    public ComboEditorValueAdapter(String defaultItem) {
        this.defaultItem = defaultItem;
    }

    public Object getOriginalTypedValue(final CellEditor cellEditor, Object value) {
        String[] items = getItems(cellEditor);
        int i = new Integer(value.toString());
        if (i >= 0) {
            return items[i];
        } else {
            return "";
        }
    }

    public Object getCellEditorTypedValue(final CellEditor cellEditor, Object value) {
        String[] items = getItems(cellEditor);

        if (value == null && defaultItem != null) {
            value = defaultItem;
        }

        for (int i = 0; i < items.length; i++) {
            if (items[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.ui.swt.tableviewer.behavior.CellEditorValueAdapter#getColumnText(org.eclipse.jface.viewers.CellEditor,
     * java.lang.Object)
     */
    @Override
    public String getColumnText(CellEditor cellEditor, Object bean, Object originalTypedValue) {
        String displayedValue = super.getColumnText(cellEditor, bean, originalTypedValue);
        if (displayedValue == null && defaultItem != null) {
            displayedValue = defaultItem;
        }
        return displayedValue;
    }

    public abstract String[] getItems(CellEditor cellEditor);
    
}
