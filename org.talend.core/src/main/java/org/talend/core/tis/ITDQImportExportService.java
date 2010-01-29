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

import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.talend.core.IService;
import org.talend.core.model.properties.Item;
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

    /**
     * DOC bZhou Comment method "getTDQItemPath".
     * 
     * This method is to get the relateive path to the project.
     * 
     * @param basePath
     * @param item
     * @return the relative path to the project
     */
    public IPath getTDQItemPath(IPath basePath, TDQItem item);

    /**
     * DOC bZhou Comment method "getItemTypePath".
     * 
     * This method to get the firm path for each different item.
     * 
     * @param item
     * @return null if can't find the typed path.
     */
    public IPath getItemTypePath(TDQItem item);

    /**
     * DOC bZhou Comment method "getFileNameWithVersion".
     * 
     * Get file name which is with version label, from item
     * 
     * @param item
     * @return
     */
    public String getFileNameWithVersion(TDQItem item);

    /**
     * DOC bZhou Comment method "needCopyObjects".
     * 
     * Retrieve all need copied EMF Objects.
     * 
     * @param item
     * @return a coolection of emf object.
     */
    public Collection<EObject> needCopyObjects(TDQItem item);

    /**
     * DOC bZhou Comment method "moveObjectsToPropertyResource".
     * 
     * Add objects which need copied, to property resource.
     * 
     * @param resource
     * @param objects
     */
    public void moveObjectsToPropertyResource(Resource resource, Collection<EObject> objects);

    /**
     * DOC bZhou Comment method "moveObjectsToItemResource".
     * 
     * Add objects which need copied, to item resource.
     * 
     * @param resource
     * @param item
     */
    public void moveObjectsToItemResource(Resource resource, TDQItem item);

    /**
     * DOC bZhou Comment method "retrieveDependencies".
     * 
     * Retrieve all dependent object of the item.
     * 
     * @param item
     * @return
     */
    public List<Item> retrieveDependencies(TDQItem item);
}
