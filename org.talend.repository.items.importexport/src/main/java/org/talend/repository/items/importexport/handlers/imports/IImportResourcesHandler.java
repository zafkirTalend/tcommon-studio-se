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

import org.talend.repository.items.importexport.manager.ResourcesManager;

/**
 * DOC ggu class global comment. Detailled comment
 * 
 * The resManager mostly is FilesManager(FileResourcesUnityManager).
 * 
 * If the original import resouce is zip or tar(.gz), The archieve will be decompressed into a temp folder for
 * FilesManager.
 * 
 * If the original import resouces is folder already. All resources will be copied to a temp folder too, in order to
 * avoid breaking the original resources.
 */
public interface IImportResourcesHandler {

    /**
     * 
     * Before do calc item records,or need prepare something before import.
     * 
     */
    void preImport(ResourcesManager resManager);

    /**
     * After all items have been imported, or some items should do some special operations after import.
     * 
     */
    void postImport(ResourcesManager resManager);
}
