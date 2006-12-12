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
package org.talend.commons.ui.swt.tableviewer.data;

import org.talend.commons.ui.swt.tableviewer.TableViewerCreatorColumn;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public final class AccessorUtils {

    /**
     * Default Constructor. Must not be used.
     */
    private AccessorUtils() {
    }

    @SuppressWarnings("unchecked")
    public static Object get(Object bean, TableViewerCreatorColumn column) {
        Object value = null;
        if (column.getBeanPropertyAccessors() != null) {
            try {
                value = column.getBeanPropertyAccessors().get(bean);
            } catch (ClassCastException cce) {

                String message = "The 'BeanPropertyAccessors' of the column (with title '" + column.getTitle() + "' and id '"
                        + column.getId() + "') is not configured correctly. " + " " + bean.getClass() + " is required for bean <B>";
                cce.printStackTrace();
                throw new RuntimeException(message, cce);
            } catch (NoClassDefFoundError e) {
                // e.printStackTrace();
                System.err.println("NoClassDefFoundError (" + AccessorUtils.class + "):" + e.getMessage());
            }
        }
        return value;
    }

    @SuppressWarnings("unchecked")
    public static void set(TableViewerCreatorColumn column, Object bean, Object value) {
        if (column.getBeanPropertyAccessors() != null) {
            try {
                column.getBeanPropertyAccessors().set(bean, value);
            } catch (ClassCastException cce) {

                String message = "The 'BeanPropertyAccessors' of the column (with title '" + column.getTitle() + "' and id '"
                        + column.getId() + "') is not configured correctly or the value set has not the correct type. ";
                if (bean != null) {
                    message += "\n " + bean.getClass() + " is required for the bean (<B>).";
                }
                if (value != null) {
                    message += "\n " + value.getClass()
                            + " is required for the value (<V>), but the value could be adapted with CellEditorValueAdapter.";
                }
                throw new RuntimeException(message, cce);
            }
        }
    }

}
