// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006-2007 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
//
// ============================================================================
package org.talend.repository.localprovider.ui.wizard;

import java.io.File;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.talend.commons.exception.PersistenceException;
import org.talend.core.model.properties.PropertiesPackage;
import org.talend.core.model.properties.RoutineItem;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.repository.localprovider.RepositoryLocalProviderPlugin;
import org.talend.repository.localprovider.model.XmiResourceManager;
import org.talend.repository.model.ProxyRepositoryFactory;

/**
 */
public class RepositoryUtil {

    private XmiResourceManager xmiResourceManager = new XmiResourceManager();

    private boolean errors = false;

    public void setErrors(boolean errors) {
        this.errors = errors;
    }

    public boolean hasErrors() {
        return errors;
    }

    public boolean isPropertyFile(File file) {
        return xmiResourceManager.isPropertyFile(file);
    }

    public boolean isPropertyFile(String elementLabel) {
        return xmiResourceManager.isPropertyFile(elementLabel);
    }

    public boolean isItemAndPropertyFile(String elementLabel, String elementLabel2) {
        return elementLabel.replace(XmiResourceManager.PROPERTIES_EXTENSION, XmiResourceManager.ITEM_EXTENSION).equals(
                elementLabel2);
    }

    public void populateValidItems(List validItems, ItemRecord itemRecord) {
        try {
            boolean nameAvailable = ProxyRepositoryFactory.getInstance().isNameAvailable(itemRecord.getItem(),
                    itemRecord.getItem().getProperty().getLabel());
            boolean idAvailable = ProxyRepositoryFactory.getInstance().getLastVersion(
                    itemRecord.getItem().getProperty().getId()) == null;

            boolean isSystemRoutine = false;
            // we do not import built in routines
            if (itemRecord.getItem().eClass().equals(PropertiesPackage.eINSTANCE.getRoutineItem())) {
                RoutineItem routineItem = (RoutineItem) itemRecord.getItem();
                if (routineItem.isBuiltIn()) {
                    isSystemRoutine = true;
                }
            }

            if (nameAvailable && idAvailable && !isSystemRoutine) {
                validItems.add(itemRecord);
            }
        } catch (PersistenceException e) {
            // ignore
        }
    }

    public void importItemRecord(ItemRecord itemRecord) {
        itemRecord.resolveItem();
        if (itemRecord.getItem() != null) {
            ERepositoryObjectType itemType = ERepositoryObjectType.getItemType(itemRecord.getItem());
            IPath path = new Path(itemRecord.getItem().getState().getPath());

            List<String> folders = null;
            try {
                folders = ProxyRepositoryFactory.getInstance().getFolders(itemType);
            } catch (Exception e) {
                logError(e);
            }

            boolean foldersCreated = true;

            try {
                for (int i = 0; i < path.segmentCount(); i++) {
                    IPath parentPath = path.removeLastSegments(path.segmentCount() - i);
                    String folderLabel = path.segment(i);

                    String folderName = parentPath.append(folderLabel).toString();
                    if (!folders.contains(folderName)) {
                        ProxyRepositoryFactory.getInstance().createFolder(itemType, parentPath, folderLabel);
                    }
                }
            } catch (Exception e) {
                foldersCreated = false;
                logError(e);
            }

            if (!foldersCreated) {
                path = new Path(""); //$NON-NLS-1$
            }

            try {
                ProxyRepositoryFactory.getInstance().create(itemRecord.getItem(), path);
            } catch (Exception e) {
                logError(e);
            }

        }
    }

    private void logError(Exception e) {
        errors = true;
        IStatus status;
        String messageStatus = e.getMessage() != null ? e.getMessage() : ""; //$NON-NLS-1$
        status = new Status(IStatus.ERROR, RepositoryLocalProviderPlugin.PLUGIN_ID, IStatus.OK, messageStatus, e);
        RepositoryLocalProviderPlugin.getDefault().getLog().log(status);
    }

}
