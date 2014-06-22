// ============================================================================
//
// Copyright (C) 2006-2012 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.model.components;

/**
 * This enum is used to distinguish the category of components, maybe some components belong common process like "DI",
 * another belongs to "M/R". Later if you need to category any components, just add here. Created by Marvin Wang on Jan
 * 11, 2013.
 */
public enum ComponentCategory {

    CATEGORY_4_DI("DI", ""), //$NON-NLS-1$ //$NON-NLS-2$
    CATEGORY_4_MAPREDUCE("MR", ""), //$NON-NLS-1$ //$NON-NLS-2$
    CATEGORY_4_CAMEL("CAMEL", "");//$NON-NLS-1$ //$NON-NLS-2$

    private String name;// Will be stored in component file at header.

    private String desc;

    ComponentCategory(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    /**
     * Getter for name.
     * 
     * @return the name
     */
    public String getName() {
        return this.name;
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
     * Getter for desc.
     * 
     * @return the desc
     */
    public String getDesc() {
        return this.desc;
    }

    /**
     * Sets the desc.
     * 
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

}
