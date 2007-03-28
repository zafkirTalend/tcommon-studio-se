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
package org.talend.commons.ui.swt.tableviewer.data;

import org.talend.commons.i18n.internal.Messages;
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

                String message = Messages.getString("AccessorUtils.Assert0", column.getTitle(), column.getId(), bean.getClass()); //$NON-NLS-1$
                cce.printStackTrace();
                throw new RuntimeException(message, cce);
            } catch (NoClassDefFoundError e) {
                // e.printStackTrace();
                System.err.println(Messages.getString("AccessorUtils.NoClassDef", AccessorUtils.class) + e.getMessage()); //$NON-NLS-1$ //$NON-NLS-2$
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

                String message = Messages.getString("AccessorUtils.Assert1", column.getTitle(), column.getId()); //$NON-NLS-1$
                if (bean != null) {
                    message += "\n " + bean.getClass() + Messages.getString("AccessorUtils.isReq"); //$NON-NLS-2$
                }
                if (value != null) {
                    message += "\n " + value.getClass() + Messages.getString("AccessorUtils.isReqValue"); //$NON-NLS-1$
                }
                throw new RuntimeException(message, cce);
            }
        }
    }

}
