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
package org.talend.core.repository.seeker;

import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.talend.core.model.utils.RepositoryManagerHelper;
import org.talend.repository.model.IRepositoryNode;
import org.talend.repository.ui.views.IRepositoryView;

/**
 * DOC ggu class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 55206 2011-02-15 17:32:14Z mhirt $
 * 
 */
public final class RepositorySeekerManager {

    private final RepositorySeekerRegistryReader seekerReader;

    private static RepositorySeekerManager instance;

    public static RepositorySeekerManager getInstance() {
        if (instance == null) {
            instance = new RepositorySeekerManager();
        }
        return instance;
    }

    private RepositorySeekerManager() {
        this.seekerReader = new RepositorySeekerRegistryReader();
    }

    public RepositorySeekerRegistryReader getSeekerReader() {
        return this.seekerReader;
    }

    /**
     * This work for the repository view of Integration and Mediation
     */
    public IRepositoryNode searchRepoViewNode(String itemId) {
        // find the repository view
        final IRepositoryView repositoryView = RepositoryManagerHelper.findRepositoryView();
        TreeViewer treeViewer = null;
        if (repositoryView != null) {
            StructuredViewer viewer = repositoryView.getViewer();
            if (viewer instanceof TreeViewer) {
                treeViewer = (TreeViewer) viewer;
            }
        }

        // seek
        IRepositorySeeker<IRepositoryNode>[] allSeeker = getSeekerReader().getAllSeeker();
        for (IRepositorySeeker<IRepositoryNode> seeker : allSeeker) {
            IRepositoryNode searchedNode = seeker.searchNode(treeViewer, itemId);
            if (searchedNode != null) {
                return searchedNode;
            }
        }
        return null;
    }
}