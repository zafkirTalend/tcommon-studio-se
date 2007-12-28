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
package org.talend.designer.joblet.ui.models;

/**
 * DOC qzhang class global comment. Detailled comment
 */
public enum EJobletNodeType {
    INPUT("Joblet Input", "it is used to draw Joblet Input Node"),
    OUTPUT("Joblet Output", "it is used to draw Joblet Output Node"),
    ELEMENT("", "");

    String displayName;

    String longName;

    EJobletNodeType(String displayName, String longName) {
        this.displayName = displayName;
        this.longName = longName;
    }

    /**
     * Getter for displayName.
     * 
     * @return the displayName
     */
    public String getDisplayName() {
        return this.displayName;
    }

    /**
     * Getter for longName.
     * 
     * @return the longName
     */
    public String getLongName() {
        return this.longName;
    }
}
