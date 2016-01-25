// ============================================================================
//
// Copyright (C) 2006-2016 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.items.importexport.ui.wizard.imports.providers;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.talend.commons.ui.runtime.image.ECoreImage;
import org.talend.commons.ui.runtime.image.ImageProvider;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.RepositoryNodeProviderRegistryReader;
import org.talend.core.ui.images.CoreImageProvider;
import org.talend.repository.items.importexport.wizard.models.FolderImportNode;
import org.talend.repository.items.importexport.wizard.models.ImportNode;
import org.talend.repository.items.importexport.wizard.models.ItemImportNode;
import org.talend.repository.items.importexport.wizard.models.ProjectImportNode;
import org.talend.repository.items.importexport.wizard.models.TypeImportNode;

/**
 * DOC ggu class global comment. Detailled comment
 */
public class ImportItemsViewerLabelProvider extends LabelProvider {

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
     */
    @Override
    public Image getImage(Object element) {
        if (element instanceof ImportNode) {
            if (element instanceof ProjectImportNode) {
                return ImageProvider.getImage(ECoreImage.PROJECT_ICON);
            } else if (element instanceof ItemImportNode) {
                final ERepositoryObjectType itemType = ERepositoryObjectType.getItemType(((ItemImportNode) element)
                        .getItemRecord().getItem());
                return getImage(itemType);
            } else if (element instanceof TypeImportNode) {
                return getImage(((TypeImportNode) element).getType());
            } else if (element instanceof FolderImportNode) {
                return CoreImageProvider.getImage(ERepositoryObjectType.FOLDER);
            }
        } else if (element instanceof ERepositoryObjectType) {
            ERepositoryObjectType itemType = (ERepositoryObjectType) element;
            if (itemType != null) {
                Image image = RepositoryNodeProviderRegistryReader.getInstance().getImage(itemType);
                if (image == null) {
                    image = CoreImageProvider.getImage(itemType);
                }
                return image;
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText(Object element) {
        if (element instanceof ImportNode) {
            return ((ImportNode) element).getDisplayLabel();
        }
        return null;
    }

}
