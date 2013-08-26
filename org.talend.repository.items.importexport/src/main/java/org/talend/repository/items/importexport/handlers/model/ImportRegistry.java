// ============================================================================
//
// Copyright (C) 2006-2013 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.items.importexport.handlers.model;

import org.talend.repository.items.importexport.handlers.imports.IImportHandler;

/**
 * DOC ggu class global comment. Detailled comment
 */
public class ImportRegistry {

    private final String bundleId, id;

    private String name, description;

    private EPriority priority = EPriority.NORMAL;

    private IImportHandler handler;

    public ImportRegistry(String bundleId, String id) {
        super();
        this.bundleId = bundleId;
        this.id = id;
    }

    /**
     * Getter for description.
     * 
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the description.
     * 
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for priority.
     * 
     * @return the priority
     */
    public EPriority getPriority() {
        return this.priority;
    }

    /**
     * Sets the priority.
     * 
     * @param priority the priority to set
     */
    public void setPriority(EPriority priority) {
        this.priority = priority;
    }

    /**
     * Getter for handler.
     * 
     * @return the handler
     */
    public IImportHandler getHandler() {
        return this.handler;
    }

    /**
     * Sets the handler.
     * 
     * @param handler the handler to set
     */
    public void setHandler(IImportHandler handler) {
        this.handler = handler;
    }

    /**
     * Getter for bundleId.
     * 
     * @return the bundleId
     */
    public String getBundleId() {
        return this.bundleId;
    }

    /**
     * Getter for id.
     * 
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * Sets the name.
     * 
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for name.
     * 
     * @return the name
     */
    public String getName() {
        return this.name;
    }

}
