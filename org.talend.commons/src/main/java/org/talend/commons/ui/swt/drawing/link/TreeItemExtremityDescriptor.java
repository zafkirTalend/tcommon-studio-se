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

import org.eclipse.swt.widgets.TreeItem;


/**
 * DOC amaumont  class global comment. Detailled comment
 * <br/>
 *
 * $Id$
 *
 */
public class TreeItemExtremityDescriptor implements IExtremityLink<TreeItem> {

    private TreeItem treeItem;

    /**
     * DOC amaumont TreeItemExtremityDescriptor constructor comment.
     * @param treeItem
     */
    public TreeItemExtremityDescriptor(TreeItem treeItem) {
        super();
        this.treeItem = treeItem;
    }
    
    /* (non-Javadoc)
     * @see org.talend.commons.ui.swt.drawing.link.IExtremityLink#getAssociatedItem()
     */
    public TreeItem getAssociatedItem() {
        return treeItem;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((this.treeItem == null) ? 0 : this.treeItem.hashCode());
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
        final TreeItemExtremityDescriptor other = (TreeItemExtremityDescriptor) obj;
        if (this.treeItem == null) {
            if (other.treeItem != null)
                return false;
        } else if (!this.treeItem.equals(other.treeItem))
            return false;
        return true;
    }

    
}
