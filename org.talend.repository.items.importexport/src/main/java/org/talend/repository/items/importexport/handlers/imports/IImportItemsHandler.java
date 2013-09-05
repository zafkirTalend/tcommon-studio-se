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

import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.talend.repository.items.importexport.handlers.model.ItemRecord;
import org.talend.repository.items.importexport.manager.ResourcesManager;

/**
 * DOC ggu class global comment. Detailled comment
 */
public interface IImportItemsHandler extends IExecutableExtension {

    /**
     * Check the path is valid to import or ignore.
     */
    boolean valid(ResourcesManager resManager, IPath path);

    /**
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
    ItemRecord calcItemRecord(IProgressMonitor monitor, ResourcesManager resManager, IPath resourcePath, boolean overwrite,
            List<ItemRecord> existeditems);

    /**
     * Find out current item's related items. in order to implictly import the related items.
     * 
     * @param monitor
     * @param resManager
     * @param selectedItemRecord the item will be imported.
     * @param allPopulatedItemRecords the list of items are all populated items.
     * @return the related item for selected items.
     */
    List<ItemRecord> findRelatedItemRecord(IProgressMonitor monitor, ResourcesManager resManager, ItemRecord selectedItemRecord,
            ItemRecord[] allImportItemRecords);

    /**
     * Will import the valid selected items to current project.
     * 
     * 
     * @param selectedItemRecord the importing item
     * @param overwrite If true will overwrite the original item. else will ignore to import the selected item.
     * @param destinationPath, only used for contextual import otherwise it is null. If not null it is to the handler
     * responsibility to use it if the items it handles should be placed in this contextual folder. Handlers that do not
     * recognize this destination path may import their items by ignoring this value.
     */
    void importItemRecord(IProgressMonitor monitor, ResourcesManager resManager, ItemRecord selectedItemRecord,
            boolean overwrite, IPath destinationPath, Set<String> overwriteDeletedItems, Set<String> idDeletedBeforeImport);

    /**
     * When there are some related items to be import, will use this flag.
     * 
     * If true, will import the related items before current item.
     * 
     * if false, will after import current item.
     * 
     */
    boolean isImportRelatedItemRecordPrior();

    /**
     * Maybe, need some disposed actions(like EMF Resources), sync functions, etc.
     * 
     */
    void afterImportingItemRecords(IProgressMonitor monitor, ResourcesManager resManager, ItemRecord selectedItemRecord);
}
