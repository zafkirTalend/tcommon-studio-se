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
package org.talend.designer.core.ui.celleditor;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.talend.commons.ui.swt.tableviewer.behavior.CellEditorValueAdapter;
import org.talend.commons.utils.data.bean.IBeanPropertyAccessors;
import org.talend.core.model.metadata.MetadataTalendType;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.types.JavaTypesManager;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * @param <B> type of bean
 */
public class JavaTypeComboValueAdapter<B> extends CellEditorValueAdapter {

    private JavaType defaultJavaType;

    private IBeanPropertyAccessors<B, Boolean> nullableAccessors;

    /**
     * DOC amaumont JavaTypeComboValueAdapter constructor comment.
     * 
     * @param nullableAccessors
     */
    public JavaTypeComboValueAdapter(JavaType defaultJavaType, IBeanPropertyAccessors<B, Boolean> nullableAccessors) {
        super();
        this.defaultJavaType = defaultJavaType;
        this.nullableAccessors = nullableAccessors;
    }

    public Object getOriginalTypedValue(final CellEditor cellEditor, Object cellEditorTypedValue) {
        JavaType[] javaTypes = JavaTypesManager.getJavaTypes();
        int i = (Integer) cellEditorTypedValue;
        if (i >= 0) {
            return javaTypes[i].getId();
        }
        // else {
        // return null;
        // }
        throw new IllegalStateException("No selection is invalid"); //$NON-NLS-1$
    }

    public Object getCellEditorTypedValue(final CellEditor cellEditor, Object originalTypedValue) {
        String[] items = ((ComboBoxCellEditor) cellEditor).getItems();

        JavaType javaType = JavaTypesManager.getJavaTypeFromId((String) originalTypedValue);

        String label = javaType.getLabel();

        if (originalTypedValue == null && defaultJavaType != null) {
            label = defaultJavaType.getLabel();
        }

        for (int i = 0; i < items.length; i++) {
            if (items[i].equals(label)) {
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
        JavaType javaType = JavaTypesManager.getJavaTypeFromId((String) originalTypedValue);

        Class primitiveClass = javaType.getPrimitiveClass();
        Boolean nullable = nullableAccessors.get((B) bean);
        String displayedValue = null;
        if (primitiveClass != null && !nullable.equals(Boolean.TRUE)) {
            displayedValue = primitiveClass.getSimpleName();
        } else {
            displayedValue = javaType.getNullableClass().getSimpleName();
        }

        if (displayedValue == null && defaultJavaType != null) {
            displayedValue = defaultJavaType.getLabel();
        }
        return displayedValue;
    }

};
