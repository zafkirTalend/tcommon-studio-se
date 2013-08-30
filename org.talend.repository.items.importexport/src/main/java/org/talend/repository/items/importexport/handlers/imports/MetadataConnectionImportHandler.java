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

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.talend.commons.utils.io.FileCopyUtils;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.core.model.properties.Item;
import org.talend.core.repository.constants.FileConstants;
import org.talend.core.repository.model.ProxyRepositoryFactory;
import org.talend.repository.items.importexport.handlers.model.ItemRecord;
import org.talend.repository.items.importexport.manager.ResourcesManager;

/**
 * DOC ggu class global comment. Detailled comment
 */
public class MetadataConnectionImportHandler extends ImportRepTypeHandler {

    public MetadataConnectionImportHandler() {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.items.importexport.handlers.imports.ImportRepTypeHandler#afterCreatedItem(org.
     * talend.repository.items.importexport.ui.wizard.imports.managers.ResourcesManager,
     * org.talend.repository.items.importexport.ui.wizard.imports.models.ItemRecord)
     */
    @Override
    protected void afterCreatedItem(ResourcesManager resManager, ItemRecord selectedItemRecord) throws Exception {
        final Item tmpItem = selectedItemRecord.getItem();
        // import has not been developed to cope with migration in mind
        // so some model may not be able to load like the ConnectionItems
        // in that case items needs to be copied before migration
        // here we check that the loading of the item failed before calling the create method
        boolean isConnectionEmptyBeforeMigration = tmpItem instanceof ConnectionItem
                && ((ConnectionItem) tmpItem).getConnection().eResource() == null
                && !selectedItemRecord.getMigrationTasksToApply().isEmpty();
        if (isConnectionEmptyBeforeMigration) {
            // copy the file before migration, this is bad because it
            // should not refer to Filesytem
            // but this is a quick hack and anyway the migration task only works on files
            // IPath itemPath = itemRecord.getPath().removeFileExtension().addFileExtension(
            // FileConstants.ITEM_EXTENSION);

            InputStream is = resManager.getStream(selectedItemRecord.getPath().removeFileExtension()
                    .addFileExtension(FileConstants.ITEM_EXTENSION));

            try {
                URI propertyResourceURI = EcoreUtil.getURI(((ConnectionItem) tmpItem).getProperty());
                URI relativePlateformDestUri = propertyResourceURI.trimFileExtension().appendFileExtension(
                        FileConstants.ITEM_EXTENSION);
                URL fileURL = FileLocator.toFileURL(new java.net.URL(
                        "platform:/resource" + relativePlateformDestUri.toPlatformString(true))); //$NON-NLS-1$
                OutputStream os = new FileOutputStream(fileURL.getFile());
                try {
                    FileCopyUtils.copyStreams(is, os);
                } finally {
                    os.close();
                }
            } finally {
                is.close();
            }
            ProxyRepositoryFactory repFactory = ProxyRepositoryFactory.getInstance();
            repFactory.unloadResources(tmpItem.getProperty());
        } else {
            super.afterCreatedItem(resManager, selectedItemRecord);
        }
    }

}
