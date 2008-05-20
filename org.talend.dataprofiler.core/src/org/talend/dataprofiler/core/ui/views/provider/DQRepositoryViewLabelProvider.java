// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.dataprofiler.core.ui.views.provider;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.talend.dataprofiler.core.ImageLib;
import org.talend.dataprofiler.core.model.nodes.foldernode.IFolderNode;

/**
 * @author rli
 * 
 */
public class DQRepositoryViewLabelProvider extends AdapterFactoryLabelProvider {
	public DQRepositoryViewLabelProvider() {
		super(MNComposedAdapterFactory.getAdapterFactory());
	}

	public Image getImage(Object element) {
	    if (element instanceof IFolderNode) {
            return ImageLib.getImage(ImageLib.FOLDERNODE_IMAGE);
        }
		return super.getImage(element);
	}

	public String getText(Object element) {
        if (element instanceof IFolderNode) {
            return ((IFolderNode) element).getName();
        }
        return super.getText(element);
    }
}
