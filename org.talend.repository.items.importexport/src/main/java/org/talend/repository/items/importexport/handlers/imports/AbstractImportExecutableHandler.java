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
package org.talend.repository.items.importexport.handlers.imports;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.repository.items.importexport.handlers.model.ItemRecord;
import org.talend.repository.items.importexport.manager.ResourcesManager;

/**
 * DOC ggu class global comment. Detailled comment
 */
public abstract class AbstractImportExecutableHandler implements IExecutableExtension {

    protected final static Logger log = Logger.getLogger(AbstractImportExecutableHandler.class);

    /**
     * Check the path is valid to import or ignore.
     */
    abstract public boolean valid(ResourcesManager resManager, IPath path);

    /**
     * 
     * DOC ggu Comment method "calcItemRecord".
     * 
     * According to the valid resource path, resolve the EMF properties file, and check the overwrite status or be valid
     * item or not, etc.
     * 
     * @param monitor
     * @param resManager
     * @param resourcePath according to the this path to calculate the item record.
     * @param overwrite
     * @param existeditems this items list has been calculated. will check the duplicated name or not.
     * @return if not valid, will return null.
     */
    abstract public ItemRecord calcItemRecord(IProgressMonitor monitor, ResourcesManager resManager, IPath resourcePath,
            boolean overwrite, List<ItemRecord> existeditems);

    /**
     * 
     * DOC ggu Comment method "findRelatedItemRecord".
     * 
     * Find out current item's related items. in order to implictly import the related items.
     * 
     * @param monitor
     * @param resManager
     * @param selectedItemRecord the item will be imported.
     * @param allPopulatedItemRecords the list of items are all populated items.
     * @return the related item for selected items.
     */
    public List<ItemRecord> findRelatedItemRecord(IProgressMonitor monitor, ResourcesManager resManager,
            ItemRecord selectedItemRecord, ItemRecord[] allImportItemRecords) {
        return Collections.emptyList(); // default, no related items
    }

    /**
     * 
     * DOC ggu Comment method "isImportRelatedItemRecordPrior".
     * 
     * when there are some related items to be import, will use this flag, if true, will import the related items before
     * current item, else if false, will after import current item.
     * 
     * @return
     */
    public boolean isImportRelatedItemRecordPrior() {
        return true; // default, import related item prior.
    }

    /**
     * 
     * DOC ggu Comment method "importItemRecords".
     * 
     * will import the valid selected items to current project.
     * 
     * 
     * @param selectedItemRecord the importing item
     * @param overwrite
     * @param destinationPath work with contentType, if current repository node is same as the content type, will import
     * the item into destination path.
     * @param contentType
     */
    abstract public void importItemRecord(IProgressMonitor monitor, ResourcesManager resManager, ItemRecord selectedItemRecord,
            boolean overwrite, IPath destinationPath, ERepositoryObjectType contentType, Set<String> overwriteDeletedItems,
            Set<String> idDeletedBeforeImport);

    /**
     * 
     * DOC ggu Comment method "afterImportingItemRecords".
     * 
     * Maybe, need some disposed actions(like EMF Resources), sync functions, etc.
     * 
     * @param itemRecord
     */
    public void afterImportingItemRecords(IProgressMonitor monitor, ResourcesManager resManager, ItemRecord selectedItemRecord) {
        // default, nothing to do
    }
}
