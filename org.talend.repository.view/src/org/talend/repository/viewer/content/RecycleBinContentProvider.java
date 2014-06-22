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
package org.talend.repository.viewer.content;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.Viewer;
import org.talend.repository.model.ProjectRepositoryNode;
import org.talend.repository.model.RepositoryNode;
import org.talend.repository.model.nodes.IProjectRepositoryNode;
import org.talend.repository.navigator.RepoViewCommonViewer;
import org.talend.repository.viewer.content.listener.IRefreshNodePerspectiveListener;

public class RecycleBinContentProvider extends ProjectRepoDirectChildrenNodeContentProvider {

    private IRefreshNodePerspectiveListener refreshNodeListener = new IRefreshNodePerspectiveListener() {

        @Override
        public void refreshNode() {
            refreshTopLevelNodes();

        }
    };

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.talend.repository.viewer.content.ProjectRepoChildrenNodeContentProvider#getTopLevelNodeFromProjectRepositoryNode
     * (org.talend.repository.model.ProjectRepositoryNode)
     */
    @Override
    protected RepositoryNode getTopLevelNodeFromProjectRepositoryNode(ProjectRepositoryNode projectRepositoryNode) {

        return projectRepositoryNode.getRecBinNode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.talend.repository.viewer.content.FolderListenerSingleTopContentProvider#getWorkspaceTopNodePath(org.talend
     * .repository.model.RepositoryNode)
     */
    @Override
    protected IPath getWorkspaceTopNodePath(RepositoryNode topLevelNode) {
        IPath workspaceRelativePath = null;
        IProjectRepositoryNode root = topLevelNode.getRoot();
        if (root != null) {
            String projectName = root.getProject().getTechnicalLabel();
            if (projectName != null) {
                workspaceRelativePath = Path.fromPortableString('/' + projectName);
            }// should never happend
        }// should never happend
        return workspaceRelativePath;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.talend.repository.viewer.content.ProjectRepoAbstractContentProvider#inputChanged(org.eclipse.jface.viewers
     * .Viewer, java.lang.Object, java.lang.Object)
     */
    @Override
    public void inputChanged(Viewer v, Object arg1, Object arg2) {
        super.inputChanged(v, arg1, arg2);
        if (v instanceof RepoViewCommonViewer && refreshNodeListener != null) {
            final RepoViewCommonViewer repoViewCommonViewer = (RepoViewCommonViewer) v;
            // remove old one, make sure only one be existed.
            repoViewCommonViewer.removeRefreshNodePerspectiveLisenter(refreshNodeListener);
            repoViewCommonViewer.addRefreshNodePerspectiveLisenter(refreshNodeListener);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.viewer.content.ProjectRepoAbstractContentProvider#dispose()
     */
    @Override
    public void dispose() {
        if (viewer != null && viewer instanceof RepoViewCommonViewer && refreshNodeListener != null) {
            ((RepoViewCommonViewer) viewer).removeRefreshNodePerspectiveLisenter(refreshNodeListener);
        }
        super.dispose();
    }

}
