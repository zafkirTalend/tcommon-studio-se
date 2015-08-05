// ============================================================================
//
// Copyright (C) 2006-2015 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.viewer.filter;

import java.util.Set;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.ui.navigator.INavigatorContentService;
import org.talend.core.model.properties.Property;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.repository.model.IRepositoryNode;
import org.talend.repository.model.RepositoryNode;
import org.talend.repository.model.nodes.IProjectRepositoryNode;
import org.talend.repository.viewer.ui.provider.INavigatorContentServiceProvider;

/**
 * DOC ggu class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 55206 2011-02-15 17:32:14Z mhirt $
 * 
 */
public class RecycleBinViewerFilter extends ViewerFilter {

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object,
     * java.lang.Object)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public boolean select(Viewer viewer, Object parentElement, Object element) {
        INavigatorContentService navigatorContentService = null;
        if (viewer instanceof INavigatorContentServiceProvider) {
            navigatorContentService = ((INavigatorContentServiceProvider) viewer).getNavigatorContentService();
        }

        if (navigatorContentService != null && element instanceof RepositoryNode) {
            final RepositoryNode node = (RepositoryNode) element;

            if (isUnderRecycleBinNode(node)) { // olny process the nodes are under Recycle Bin.
                ERepositoryObjectType contextType = findRealContextType(node);
                if (contextType != null) { // don't check the SubItems, like schema, query, etc.
                    IRepositoryNode contextNode = node.getRoot().getRootRepositoryNode(contextType);
                    Set contentExtensions = navigatorContentService.findContentExtensionsByTriggerPoint(contextNode);
                    if (contentExtensions.isEmpty()) { // deactive or invisible
                        return false;
                    }
                }
            }

        }
        return true; // don't filter others
    }

    private boolean isUnderRecycleBinNode(final RepositoryNode node) {
        if (node != null) {
            if (node instanceof IProjectRepositoryNode) {
                return false;
            }
            // recycle bin
            if (node.isBin()/* ||node instanceof BinRepositoryNode. */) {
                return true;
            }
            // check parent node
            return isUnderRecycleBinNode(node.getParent());
        }
        return false;
    }

    /**
     * 
     * DOC ggu Comment method "findRealContextType".
     * 
     * @param node
     * @return
     */
    private ERepositoryObjectType findRealContextType(final RepositoryNode node) {
        ERepositoryObjectType contentType = null;
        // elements
        if (node.getType() == IRepositoryNode.ENodeType.REPOSITORY_ELEMENT) {
            contentType = node.getObjectType();
        }
        //
        if (contentType == null) {
            contentType = node.getContentType();
        }
        // bin is null
        if (contentType != null) {
            // SubItems
            if (contentType.isSubItem()) {
                ERepositoryObjectType itemType = null;
                if (node.getObject() != null) {
                    // get property of SubItem.
                    Property property = node.getObject().getProperty();
                    if (property != null) {
                        try {
                            itemType = ERepositoryObjectType.getItemType(property.getItem());
                        } catch (IllegalStateException e) { // can't find the item type.
                            // nothing to do
                        }
                    }
                }
                contentType = itemType;
            }
        }
        return contentType;
    }
}
