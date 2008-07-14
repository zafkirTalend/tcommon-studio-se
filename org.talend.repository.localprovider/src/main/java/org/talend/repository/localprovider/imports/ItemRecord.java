// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.localprovider.imports;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.swt.graphics.Image;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.Property;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.ui.images.CoreImageProvider;

/**
 */
public class ItemRecord {

    private String itemName;

    private Property property;

    private IPath path;

    private List<String> errors = new ArrayList<String>();

    private List<String> migrationTasksToApply = new ArrayList<String>();

    private boolean resolved = false;

    private State state = State.NON_EXISTED;

    private boolean locked;

    private boolean imported = false;

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
        // TODO mhelleboid split validation in two steps to be able to filter a valid item and avoid adding an error
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

    public boolean isResolved() {
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }

    public ERepositoryObjectType getType() {
        return ERepositoryObjectType.getItemType(getItem());
    }

    public Image getImage() {
        return CoreImageProvider.getImage(getType());
    }

    /**
     * 
     * DOC hcw ItemRecord class global comment. Detailled comment
     */
    enum State {
        NAME_EXISTED,
        ID_EXISTED,
        NON_EXISTED
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public void setImported(boolean imported) {
        this.imported = imported;
    }

    public boolean isImported() {
        return imported;
    }
}
