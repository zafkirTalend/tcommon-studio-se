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
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.navigator.CommonViewer;
import org.talend.core.repository.constants.FileConstants;
import org.talend.repository.model.ProjectRepositoryNode;
import org.talend.repository.model.RepositoryNode;
import org.talend.repository.model.nodes.IProjectRepositoryNode;
import org.talend.repository.navigator.RepoViewCommonViewer;

/**
 * this handle content that root node is of type ProjectRepositoryNode
 * */
public abstract class FolderListenerSingleTopContentProvider extends SingleTopLevelContentProvider {

    /*
     * map used to store the folder path related to a top level node in order to cache it for performance purposes the
     * map value may be null
     */
    Map<RepositoryNode, IPath> topLevelNodeToPathMap = new HashMap<RepositoryNode, IPath>();

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
            IPath path = resource.getFullPath();
            Set<RepositoryNode> topLevelNodes = getTopLevelNodes();
            for (final RepositoryNode repoNode : topLevelNodes) {
                IPath topLevelNodeWorkspaceRelativePath = topLevelNodeToPathMap.get(repoNode);
                if (topLevelNodeWorkspaceRelativePath != null) {
                    int matchingFirstSegments = path.matchingFirstSegments(topLevelNodeWorkspaceRelativePath);
                    if (path.segmentCount() == matchingFirstSegments
                            || matchingFirstSegments == topLevelNodeWorkspaceRelativePath.segmentCount()) {
                        // be sure we are the last path of the resources and then check for the right folder and then
                        // check
                        // for
                        // file of type .properties or folder.
                        if ((delta.getAffectedChildren().length == 0)
                                && (FileConstants.PROPERTIES_EXTENSION.equals(path.getFileExtension()) || (resource instanceof IContainer))) {
                            if (viewer instanceof RepoViewCommonViewer) {
                                runnables.add(new Runnable() {

                                    @Override
                                    public void run() {
                                        refreshTopLevelNode(repoNode);
                                    }
                                });
                            }// else nothing to update so stop visiting.
                            return false;
                        } else {// not the propoer resouce change so ignors and continue exploring
                            return true;
                        }
                    }// else not the projer project or folder so stop visiting.
                }// else no root node so ignor children
            }
            return false;
        }
    }

    /*
     * stores the folder workspace relative path related to the topLevel node into a map.
     * 
     * @see
     * org.talend.repository.viewer.content.SingleTopLevelContentProvider#initilizeContentProvider(org.talend.repository
     * .model.RepositoryNode)
     */
    @Override
    protected void initilizeContentProviderWithTopLevelNode(RepositoryNode aTopLevelNode) {
        super.initilizeContentProviderWithTopLevelNode(aTopLevelNode);
        if (!topLevelNodeToPathMap.containsKey(aTopLevelNode)) {
            topLevelNodeToPathMap.put(aTopLevelNode, getWorkspaceTopNodePath(aTopLevelNode));
        }

    }

    /**
     * This calls the refresh of the toplevel node, this must be invoke from the UI thread.
     * 
     * @param repoNode
     * 
     * @param topLevelNode
     */
    protected void refreshTopLevelNode(RepositoryNode repoNode) {
        getTopLevelNodes();
        resetTopLevelNode(repoNode);

        beforeRefreshTopLevelNode(repoNode);

        // for bug 11786
        if (repoNode.getParent() instanceof ProjectRepositoryNode) {
            ((ProjectRepositoryNode) repoNode.getParent()).clearNodeAndProjectCash();
        }
        if (viewer != null && !viewer.getTree().isDisposed()) {
            viewer.refresh(repoNode);
        }
    }

    protected void beforeRefreshTopLevelNode(RepositoryNode repoNode) {
        // do nothing here
    }

    protected IPath getWorkspaceTopNodePath(RepositoryNode topLevelNode) {
        IPath workspaceRelativePath = null;
        IProjectRepositoryNode root = topLevelNode.getRoot();
        if (root != null) {
            String projectName = root.getProject().getTechnicalLabel();
            String topLevelNodeProjectRelativePath = getTopLevelNodeProjectRelativePath(topLevelNode);
            if (projectName != null && (topLevelNodeProjectRelativePath != null && !"".equals(topLevelNodeProjectRelativePath))) { //$NON-NLS-1$
                workspaceRelativePath = Path.fromPortableString('/' + projectName).append(topLevelNodeProjectRelativePath);
            }
        }
        return workspaceRelativePath;
    }

    /**
     * DOC sgandon Comment method "getTopLevelNodeProjectRelativePath".
     * 
     * @param topLevelNode
     * 
     * @return the relative path to the project or null if none
     */
    protected String getTopLevelNodeProjectRelativePath(RepositoryNode topLevelNode) {
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
        // FIXME by TDI-22701, If the input model has been changed, so need clean
        super.inputChanged(arg0, arg1, arg2);
        topLevelNodeToPathMap.clear();

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
         // to help garbage collection
        topLevelNodeToPathMap.clear();
        topLevelNodeToPathMap = null;
        super.dispose();
    }

}
