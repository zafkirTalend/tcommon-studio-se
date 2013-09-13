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
package org.talend.core.model.metadata.designerproperties;

/**
 * Uses this to store all repository values of "
 * <code>NAME<code>" from component t*_java.xml file. Note that the names also can be
 * used for "<code>REPOSITORY_VALUE</code>" in component t*_java.xml file. Created by Marvin Wang on Nov 26, 2012.
 */
public enum EParameterNameForComponent {

    PARA_NAME_TYPE("TYPE"), //$NON-NLS-1$

    PARA_NAME_USE_YARN("USE_YARN"), //$NON-NLS-1$

    PARA_NAME_SET_MAPRED_JT("SET_MAPRED_JT"), //$NON-NLS-1$

    PARA_NAME_MAPRED_JT("MAPRED_JT"), //$NON-NLS-1$

    PARA_NAME_SET_FS_DEFAULT_NAME("SET_FS_DEFAULT_NAME"), //$NON-NLS-1$

    PARA_NAME_FS_DEFAULT_NAME("FS_DEFAULT_NAME"), //$NON-NLS-1$

    PARA_NAME_SET_RESOURCE_MANAGER("SET_RESOURCE_MANAGER"), //$NON-NLS-1$

    PARA_NAME_RESOURCE_MANAGER("RESOURCE_MANAGER"); //$NON-NLS-1$

    private String name;

    EParameterNameForComponent(String name) {
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

    @Override
    public String toString() {
        return this.name;
    }

}
