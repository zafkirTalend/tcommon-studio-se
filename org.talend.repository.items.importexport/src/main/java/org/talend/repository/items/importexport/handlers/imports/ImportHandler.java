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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.repository.items.importexport.handlers.HandlerUtil;
import org.talend.repository.items.importexport.handlers.model.ItemRecord;
import org.talend.repository.items.importexport.manager.ResourcesManager;

/**
 * DOC ggu class global comment. Detailled comment
 * 
 * This class mainly use for extension point with types.
 */
abstract class ImportHandler implements IImportHandler {

    protected static Logger log = Logger.getLogger(ImportHandler.class);

    private final List<ERepositoryObjectType> processTypes = new ArrayList<ERepositoryObjectType>();

    protected final ERepositoryObjectType[] getProcessTypes() {
        return this.processTypes.toArray(new ERepositoryObjectType[0]);
    }

    protected abstract boolean isValidPath(IPath path);

    @Override
    public boolean valid(ResourcesManager resManager, IPath path) {
        boolean valid = isValidPath(path);
        if (valid) {
            IPath projectFilePath = HandlerUtil.getValidProjectFilePath(resManager, path);
            if (projectFilePath != null) {
                IPath projectRootPath = projectFilePath.removeLastSegments(1);
                IPath relativePath = path.makeRelativeTo(projectRootPath);
                return validRelativePath(relativePath);
            } else {
                valid = false;
            }

        }
        return valid;
    }

    protected boolean validRelativePath(IPath relativePath) {
        ERepositoryObjectType[] processTypesArray = getProcessTypes();
        if (processTypesArray != null) {
            for (ERepositoryObjectType type : processTypesArray) {
                if (type.isResouce() && StringUtils.isNotEmpty(type.getFolder())) {
                    IPath typeFolder = new Path(type.getFolder());
                    // the relativePath is under the type of folder.
                    if (typeFolder.isPrefixOf(relativePath)) {
                        return true;
                    }
                }
            }
        }
        //
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.talend.repository.items.importexport.handlers.imports.IImportHandler#populateItemRecord(org.eclipse.core.
     * runtime.IProgressMonitor, org.talend.repository.items.importexport.ui.wizard.imports.managers.ResourcesManager,
     * org.eclipse.core.runtime.IPath, boolean, java.util.List)
     */
    @Override
    public ItemRecord calcItemRecord(IProgressMonitor progressMonitor, ResourcesManager resManager, IPath resourcePath,
            boolean overwrite, List<ItemRecord> existeditems) {

        ItemRecord itemRecord = computeItemRecord(resManager, resourcePath);
        if (progressMonitor.isCanceled()) {
            return null;
        }
        if (itemRecord != null && itemRecord.getProperty() != null) {
            boolean alreadyInList = false;
            for (ItemRecord currentItemRecord : existeditems) {
                if (isSame(itemRecord, currentItemRecord)) {
                    // if have any duplicate item from same project & same folder, just don't do
                    // anything,
                    // no need to display.
                    alreadyInList = true;
                    break;
                }
            }
            if (alreadyInList) {
                return null; // if existed, it not valid.
            }

            if (checkItem(resManager, itemRecord, overwrite)) {
                if (progressMonitor.isCanceled()) {
                    return null;
                }
                checkAndSetProject(resManager, itemRecord);
            }
            // set the import handler
            itemRecord.setImportHandler(this);
            return itemRecord;
        }
        return null;

    }

    abstract protected ItemRecord computeItemRecord(ResourcesManager resManager, IPath path);

    abstract protected boolean isSame(ItemRecord itemRecord1, ItemRecord itemRecord2);

    abstract protected boolean checkItem(ResourcesManager resManager, ItemRecord itemRecord, boolean overwrite);

    abstract protected void checkAndSetProject(ResourcesManager resManager, ItemRecord itemRecord);

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.items.importexport.handlers.imports.IImportHandler#needImportRelatedItemRecordFirst()
     */
    @Override
    public boolean needImportRelatedItemRecordFirst() {
        return true; // default, import related item first.
    }

}
