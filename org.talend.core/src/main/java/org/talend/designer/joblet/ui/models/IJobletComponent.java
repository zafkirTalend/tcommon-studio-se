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

import org.talend.core.model.components.IComponent;
import org.talend.core.model.process.IProcess2;
import org.talend.core.model.properties.Property;

/**
 * DOC qzhang class global comment. Detailled comment
 */
public interface IJobletComponent extends IComponent {

    public void setProperty(Property property);

    public Property getProperty();

    public EJobletNodeType getJobletNodeType();

    public IProcess2 getJobletGEFProcess();

    /**
     * DOC qzhang Comment method "openJobletEditor".
     */
    public void openJobletEditor();
}
