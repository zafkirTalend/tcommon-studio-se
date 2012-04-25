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
package org.talend.repository.viewer.content;

import java.util.Collection;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.navigator.CommonViewer;
import org.talend.core.repository.constants.FileConstants;
import org.talend.repository.model.ProjectRepositoryNode;
import org.talend.repository.model.RepositoryNode;
import org.talend.repository.navigator.RepoViewCommonViewer;

/**
 * this handle content that root node is of type ProjectRepositoryNode
 * */
public abstract class FolderListenerSingleTopContentProvider extends SingleTopLevelContentProvider {

    private boolean reInit = true; // default is true

    public FolderListenerSingleTopContentProvider() {
        super();
    }

    /**
     * DOC sgandon class global comment. Detailled comment <br/>
     * 
     * $Id: talend.epf 55206 2011-02-15 17:32:14Z mhirt $
     * 
     */
    private final class DirectChildrenNodeVisitor extends ResouceChangeVisitorListener.RunnableResourceVisitor {

        @Override
        protected boolean visit(IResourceDelta delta, Collection<Runnable> runnables) {
            IResource resource = delta.getResource();
            IPath path = resource.getProjectRelativePath();
            String topLevelNodeProjectRelativePath = getTopLevelNodeProjectRelativePath();
            if (topLevelNodeProjectRelativePath != null) {

                // be sure we are the last path of the resources and then check for the right folder and then check for
                // file of type .properties or folder.

                if (delta.getAffectedChildren().length == 0
                        && path.toPortableString().startsWith(topLevelNodeProjectRelativePath)
                        && (FileConstants.PROPERTIES_EXTENSION.equals(path.getFileExtension()) || resource instanceof IContainer)) {
                    if (viewer instanceof RepoViewCommonViewer) {
                        runnables.add(new Runnable() {

                            @Override
                            public void run() {
                                refreshTopLevelNode();
                            }
                        });
                    }

                    return false;
                } else {// not the propoer resouce change so ignors and continue exploring
                    return true;
                }
            }// else no root node so ignor children
            return false;
        }
    }

    protected boolean isReInit() {
        return reInit;
    }

    protected void setReInit(boolean reInit) {
        this.reInit = reInit;
    }

    /**
     * This calls the refresh of the toplevel node, this must be invoke from the UI thread.
     * 
     * @param topLevelNode
     */
    protected void refreshTopLevelNode() {
        RepositoryNode topLevelNode = getTopLevelNode();
        if (topLevelNode != null) {
            if (isReInit()) {
                topLevelNode.setInitialized(false);
                topLevelNode.getChildren().clear();
            }

            beforeRefreshTopLevelNode();

            // for bug 11786
            if (topLevelNode.getParent() instanceof ProjectRepositoryNode) {
                ((ProjectRepositoryNode) topLevelNode.getParent()).clearNodeAndProjectCash();
            }
            if (viewer != null) {
                viewer.refresh(topLevelNode);
            }
        }
    }

    protected void beforeRefreshTopLevelNode() {
        // do nothing here
    }

    /**
     * DOC sgandon Comment method "getTopLevelNodeProjectRelativePath".
     * 
     * @return
     */
    public String getTopLevelNodeProjectRelativePath() {
        RepositoryNode topLevelNode = getTopLevelNode();
        if (topLevelNode != null && topLevelNode.getContentType() != null) {
            return topLevelNode.getContentType().getFolder();
        }// else return null
        return null;
    }

    protected CommonViewer viewer;

    private ResouceChangeVisitorListener resouceChangeVisitor;

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object,
     * java.lang.Object)
     */
    @Override
    public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
        if (arg0 instanceof CommonViewer) {
            this.viewer = (CommonViewer) arg0;
        }
        addResourceChangeListener();
    }

    /**
     * DOC sgandon Comment method "addResourceChangeListener".
     */
    protected void addResourceChangeListener() {
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        if (workspace != null) {
            if (resouceChangeVisitor != null) {// remove the previous listener
                workspace.removeResourceChangeListener(resouceChangeVisitor);
            }
            resouceChangeVisitor = createResourceChangedVisitor();
            workspace.addResourceChangeListener(resouceChangeVisitor, IResourceChangeEvent.POST_CHANGE);
        }// else workspace not accessible any more so do nothing
    }

    /**
     * DOC sgandon Comment method "createResourceChangedVisitor".
     * 
     * @return
     */
    protected ResouceChangeVisitorListener createResourceChangedVisitor() {
        return new ResouceChangeVisitorListener(viewer, new DirectChildrenNodeVisitor());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.IContentProvider#dispose()
     */
    @Override
    public void dispose() {
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        if (workspace != null && resouceChangeVisitor != null) {
            workspace.removeResourceChangeListener(resouceChangeVisitor);
        }// else workspace not accessible any more so do nothing
        super.dispose();
    }

}
