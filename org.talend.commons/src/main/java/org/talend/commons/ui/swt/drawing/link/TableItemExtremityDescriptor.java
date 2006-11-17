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
package org.talend.commons.ui.swt.drawing.link;

import org.eclipse.swt.widgets.TableItem;


/**
 * DOC amaumont  class global comment. Detailled comment
 * <br/>
 *
 * $Id$
 *
 */
public class TableItemExtremityDescriptor implements IExtremityLink<TableItem> {

    private TableItem tableItem;
    
    /**
     * DOC amaumont TableItemExtremityDescriptor constructor comment.
     * @param tableItem
     */
    public TableItemExtremityDescriptor(TableItem tableItem) {
        super();
        this.tableItem = tableItem;
    }


    /* (non-Javadoc)
     * @see org.talend.commons.ui.swt.drawing.link.IExtremityLink#getAssociatedItem()
     */
    public TableItem getAssociatedItem() {
        return this.tableItem;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.tableItem == null) ? 0 : this.tableItem.hashCode());
        return result;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final TableItemExtremityDescriptor other = (TableItemExtremityDescriptor) obj;
        if (this.tableItem == null) {
            if (other.tableItem != null)
                return false;
        } else if (!this.tableItem.equals(other.tableItem))
            return false;
        return true;
    }
    
    
}
