// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.tis;

import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.talend.core.IService;
import org.talend.core.model.properties.Project;
import org.talend.core.model.properties.Property;
import org.talend.core.model.properties.TDQItem;

/**
 * bZhou class global comment. Detailled comment
 */
public interface ITDQImportExportService extends IService {

    /**
     * 
     * cli Comment method "initRepository".
     * 
     * init the tdq repository dirs.
     * 
     */
    public boolean initRepository(Project project);

    /**
     * 
     * cli Comment method "importElement".
     * 
     * the version is tdq version for migration task.
     */
    public boolean importElement(Project curProject, Property property, String version);

    /**
     * 
     * cli Comment method "retrieveVersionInfor".
     * 
     * got the version from tdq system.
     */
    public String retrieveVersionInfor(IPath path);

    /**
     * 
     * cli Comment method "migrateElement".
     * 
     * do migration task for tdq element
     */
    public boolean migrateElement(Project curProject, Property property, String version);

    /**
     * 
     * cli Comment method "getAllVersion".
     * 
     * get all tdq item from current project
     */
    public List<TDQItem> getAllVersion(Project curProject);

}
