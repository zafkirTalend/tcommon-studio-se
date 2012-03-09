// ============================================================================
//
// Copyright (C) 2006-2012 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.navigator;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.viewers.ITreeViewerListener;
import org.eclipse.jface.viewers.TreeExpansionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.navigator.CommonViewer;
import org.talend.repository.model.IRepositoryNode;
import org.talend.repository.model.RepositoryNode;

/**
 * DOC sgandon class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 55206 2011-02-15 17:32:14Z mhirt $
 * 
 */
public class RepoViewCommonViewer extends CommonViewer implements ITreeViewerListener {

    private Map<String, Boolean> expanded = new HashMap<String, Boolean>();

    private final RepoViewCommonNavigator repViewCommonNavigator;

    /**
     * Getter for repViewCommonNavigator.
     * 
     * @return the repViewCommonNavigator
     */
    public RepoViewCommonNavigator getRepViewCommonNavigator() {
        return this.repViewCommonNavigator;
    }

    public RepoViewCommonViewer(RepoViewCommonNavigator repViewCommonNavigator, String aViewerId, Composite parent, int style) {
        super(aViewerId, parent, style);
        this.repViewCommonNavigator = repViewCommonNavigator;
    }

    private RepositoryNode getRepositoryNode(Item node) {
        Object data = node.getData();
        RepositoryNode repositoryNode = null;
        if (data instanceof RepositoryNode) {
            repositoryNode = (RepositoryNode) data;
        }
        return repositoryNode;
    }

    @Override
    protected boolean getExpanded(Item item) {
        RepositoryNode repositoryNode = getRepositoryNode(item);
        if (repositoryNode != null && repositoryNode.getId() != null) {
            Boolean result = expanded.get(repositoryNode.getId());
            if (result != null) {
                if (item instanceof TreeItem) {
                    TreeItem treeItem = (TreeItem) item;
                    treeItem.setExpanded(result);
                }
            }
        }
        return super.getExpanded(item);
    }

    @Override
    public void setExpandedState(Object elementOrTreePath, boolean expanded) {
        if (expanded) {
            internalExpand(elementOrTreePath);
        } else {
            internalCollapse(elementOrTreePath);
        }
        super.setExpandedState(elementOrTreePath, expanded);
    }

    @Override
    public void treeCollapsed(TreeExpansionEvent event) {
        Object element = event.getElement();
        internalCollapse(element);
    }

    @Override
    public void treeExpanded(TreeExpansionEvent event) {
        Object element = event.getElement();
        internalExpand(element);
    }

    private void internalCollapse(Object element) {
        if (element instanceof RepositoryNode) {
            RepositoryNode repositoryNode = (RepositoryNode) element;
            if (idIsValid(repositoryNode)) {
                expanded.put(repositoryNode.getId(), false);
            }
            emptyExpandedChildren(repositoryNode);
        }
    }

    private void internalExpand(Object element) {
        if (element instanceof RepositoryNode) {
            RepositoryNode repositoryNode = (RepositoryNode) element;
            if (idIsValid(repositoryNode)) {
                expanded.put(repositoryNode.getId(), true);
            }
        }
    }

    private void emptyExpandedChildren(RepositoryNode repositoryNode) {
        for (IRepositoryNode children : repositoryNode.getChildren()) {
            if (idIsValid(children)) {
                expanded.remove(children.getId());
            }
            emptyExpandedChildren((RepositoryNode) children);
        }
    }

    private boolean idIsValid(IRepositoryNode repositoryNode) {
        String id = repositoryNode.getId();
        return id != null && !RepositoryNode.NO_ID.equals(id);
    }
}