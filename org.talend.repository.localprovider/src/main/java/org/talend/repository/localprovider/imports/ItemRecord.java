// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the  agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
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
