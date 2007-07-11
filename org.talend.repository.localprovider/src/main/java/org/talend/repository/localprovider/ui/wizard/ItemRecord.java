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
package org.talend.repository.localprovider.ui.wizard;

import org.eclipse.core.runtime.IPath;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.Property;
import org.talend.core.model.repository.ERepositoryObjectType;

/**
 */
class ItemRecord {

    private String itemName;

    private Property property;

    private IPath path;

    public ItemRecord(IPath path, Property property) {
        this.path = path;
        this.property = property;
    }

    public Item getItem() {
        return property.getItem();
    }

    public Property getProperty() {
        return property;
    }

    public String getItemName() {
        if (itemName == null) {
            itemName = ERepositoryObjectType.getItemType(property.getItem()).toString() + " " + property.getLabel() //$NON-NLS-1$
                    + " " + property.getVersion(); //$NON-NLS-1$
        }
        return itemName;
    }

    public IPath getPath() {
        return path;
    }
}
