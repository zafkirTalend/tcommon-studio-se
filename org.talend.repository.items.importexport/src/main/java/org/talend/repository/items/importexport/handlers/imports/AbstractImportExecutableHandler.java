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

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.talend.repository.items.importexport.handlers.model.ItemRecord;
import org.talend.repository.items.importexport.manager.ResourcesManager;

/**
 * DOC ggu class global comment. Detailled comment
 */
public abstract class AbstractImportExecutableHandler implements IImportItemsHandler {

    protected final static Logger log = Logger.getLogger(AbstractImportExecutableHandler.class);

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.talend.repository.items.importexport.handlers.imports.IImportItemsHandler#findRelatedItemRecord(org.eclipse.core
     * .runtime.IProgressMonitor, org.talend.repository.items.importexport.manager.ResourcesManager,
     * org.talend.repository.items.importexport.handlers.model.ItemRecord,
     * org.talend.repository.items.importexport.handlers.model.ItemRecord[])
     */
    @Override
    public List<ItemRecord> findRelatedItemRecord(IProgressMonitor monitor, ResourcesManager resManager,
            ItemRecord selectedItemRecord, ItemRecord[] allImportItemRecords) {
        return Collections.emptyList(); // default, no related items
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.items.importexport.handlers.imports.IImportItemsHandler#isImportRelatedItemRecordPrior()
     */
    @Override
    public boolean isImportRelatedItemRecordPrior() {
        return true; // default, import related item prior.
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.talend.repository.items.importexport.handlers.imports.IImportItemsHandler#afterImportingItemRecords(org.eclipse
     * .core.runtime.IProgressMonitor, org.talend.repository.items.importexport.manager.ResourcesManager,
     * org.talend.repository.items.importexport.handlers.model.ItemRecord)
     */
    @Override
    public void afterImportingItemRecords(IProgressMonitor monitor, ResourcesManager resManager, ItemRecord selectedItemRecord) {
        // default, nothing to do
    }

}
