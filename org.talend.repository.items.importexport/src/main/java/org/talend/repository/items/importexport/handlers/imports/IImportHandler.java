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

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.repository.items.importexport.ui.wizard.imports.managers.ResourcesManager;
import org.talend.repository.items.importexport.ui.wizard.imports.models.ItemRecord;

/**
 * DOC ggu class global comment. Detailled comment
 */
public interface IImportHandler {

    /**
     * Check the path is valid properties or items.
     */
    boolean valid(ResourcesManager resManager, IPath path);

    /**
     * 
     * DOC ggu Comment method "calcItemRecord".
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
     * 
     * DOC ggu Comment method "findRelatedItemRecord".
     * 
     * @param monitor
     * @param resManager
     * @param selectedItemRecord the item will be imported.
     * @param allPopulatedItemRecords the list of items are all populated items.
     * @return the related item for selected items.
     */
    List<ItemRecord> findRelatedItemRecord(IProgressMonitor monitor, ResourcesManager resManager, ItemRecord selectedItemRecord,
            List<ItemRecord> allPopulatedItemRecords);

    boolean needImportRelatedItemRecordFirst();

    /**
     * 
     * DOC ggu Comment method "importItemRecords".
     * 
     * @param selectedItemRecord the importing item
     * @param overwrite
     * @param destinationPath work with contentType, if current repository node is same as the content type, will import
     * the item into destination path.
     * @param contentType
     */
    void importItemRecord(IProgressMonitor monitor, ResourcesManager resManager, ItemRecord selectedItemRecord,
            boolean overwrite, IPath destinationPath, ERepositoryObjectType contentType, Set<String> overwriteDeletedItems,
            Set<String> idDeletedBeforeImport);

    /**
     * 
     * DOC ggu Comment method "afterImportingItemRecords".
     * 
     * @param itemRecord
     */
    void afterImportingItemRecords(IProgressMonitor monitor, ResourcesManager resManager, ItemRecord selectedItemRecord);
}
