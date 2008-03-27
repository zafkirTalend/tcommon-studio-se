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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.talend.cwm.helper.CatalogHelper;
import org.talend.cwm.helper.SwitchHelpers;
import org.talend.cwm.relational.TdColumn;
import org.talend.dataprofiler.core.helper.FileResourceMapHelper;
import org.talend.dataprofiler.core.helper.FolderNodeHelper;
import org.talend.dataprofiler.core.model.nodes.foldernode.IFolderNode;

/**
 * @author rli
 * 
 */
public class DQRepositoryViewContentProvider extends
		AdapterFactoryContentProvider {	

	/**
	 * @param adapterFactory
	 */
	public DQRepositoryViewContentProvider() {
		super(MNComposedAdapterFactory.getAdapterFactory());
	}

	public Object[] getChildren(Object parentElement) {
        if (parentElement instanceof IFile) {
            parentElement = FileResourceMapHelper.get((IFile) parentElement);
        } else if (parentElement instanceof IFolderNode) {
            IFolderNode folerNode = (IFolderNode) parentElement;
            if (!(folerNode.isLoaded())) {
                folerNode.loadChildren();
            }
            return folerNode.getChildren();
        } else if (SwitchHelpers.CATALOG_SWITCH.doSwitch((EObject) parentElement) != null) {
            if (CatalogHelper.getSchemas(SwitchHelpers.CATALOG_SWITCH.doSwitch((EObject) parentElement)).size() > 0) {
                return super.getChildren(parentElement);
            } else {
               return FolderNodeHelper.getFolderNode((EObject) parentElement);
            }

        } else {
            return FolderNodeHelper.getFolderNode((EObject) parentElement);
        }
        return super.getChildren(parentElement);
    }

	@Override
	public Object[] getElements(Object object) {
		return this.getChildren(object);
	}

	public Object getParent(Object element) {
		if (element instanceof IFile) {
			return ((IResource) element).getParent();
		}
		return super.getParent(element);
	}

	public boolean hasChildren(Object element) {
	    return !(element instanceof TdColumn);
    }

}
