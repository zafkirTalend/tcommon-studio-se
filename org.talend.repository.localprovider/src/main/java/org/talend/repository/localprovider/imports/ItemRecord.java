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
package org.talend.repository.localprovider.imports;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.Property;
import org.talend.core.model.repository.ERepositoryObjectType;

/**
 */
public class ItemRecord {

    private String itemName;

    private Property property;

    private IPath path;

    private List<String> errors = new ArrayList<String>();

    private List<String> migrationTasksToApply = new ArrayList<String>();

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

    public void addError(String message) {
        errors.add(message);
    }

    public List<String> getErrors() {
        return errors;
    }

    public boolean isValid() {
        return errors.isEmpty();
    }

    /**
     * Getter for migrationTasksToApply.
     * 
     * @return the migrationTasksToApply
     */
    public List<String> getMigrationTasksToApply() {
        return this.migrationTasksToApply;
    }

    /**
     * Sets the migrationTasksToApply.
     * 
     * @param migrationTasksToApply the migrationTasksToApply to set
     */
    public void setMigrationTasksToApply(List<String> migrationTasksToApply) {
        this.migrationTasksToApply = migrationTasksToApply;
    }

}
