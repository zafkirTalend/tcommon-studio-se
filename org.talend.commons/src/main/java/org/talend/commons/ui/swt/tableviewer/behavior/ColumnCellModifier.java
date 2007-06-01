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
package org.talend.commons.ui.swt.tableviewer.behavior;

import org.talend.commons.ui.swt.tableviewer.TableViewerCreatorColumn;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 */
public class ColumnCellModifier implements IColumnCellModifier {

    private TableViewerCreatorColumn column;

    /**
     * DOC amaumont ColumnCellModifier constructor comment.
     * 
     * @param column
     */
    public ColumnCellModifier(TableViewerCreatorColumn column) {
        this.column = column;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.ui.swt.tableviewer.behavior.IColumnCellModifier#canModify(java.lang.Object)
     */
    public boolean canModify(Object bean) {
        return column.isModifiable();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.ui.swt.tableviewer.behavior.IColumnCellModifier#getValue(java.lang.Object)
     */
    public Object getValue(Object bean) {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.ui.swt.tableviewer.behavior.IColumnCellModifier#modify(java.lang.Object,
     * java.lang.Object)
     */
    public boolean modify(Object bean, Object value) {
        return false;
    }

}
