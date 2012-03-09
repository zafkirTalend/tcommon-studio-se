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

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Control;
import org.talend.core.repository.constants.FileConstants;
import org.talend.repository.model.IRepositoryNode;
import org.talend.repository.model.ProjectRepositoryNode;
import org.talend.repository.model.RepositoryNode;
import org.talend.repository.model.nodes.IProjectRepositoryNode;
import org.talend.repository.navigator.RepoViewCommonViewer;

public abstract class LegacyRepositoryContentProvider implements ITreeContentProvider, IResourceChangeListener {

    /**
     * DOC sgandon class global comment. Detailled comment <br/>
     * 
     * $Id: talend.epf 55206 2011-02-15 17:32:14Z mhirt $
     * 
     */
    private final class ResourceVisitor implements IResourceDeltaVisitor {

        /**
         * 
         */
        private final Collection<Runnable> runnables;

        /**
         * DOC sgandon ResourceVisitor constructor comment.
         * 
         * @param runnables
         */
        private ResourceVisitor(Collection<Runnable> runnables) {
            this.runnables = runnables;
        }

        @Override
        public boolean visit(IResourceDelta delta) throws CoreException {
            IResource resource = delta.getResource();
            IPath path = resource.getProjectRelativePath();
            if (topLevelNode != null) {
                String folderName = topLevelNode.getContentType().getFolder();
                String segment = path.segment(0);
                // filter on the extension path because of a bug that is when a job is created
                // the screen shot is created first but not the .item or .properties
                // and the problem is that when computing children, one children is found even if the .item and
                // .property is not created.
                if (segment != null
                        && segment.equals(folderName)
                        && (FileConstants.PROPERTIES_EXTENSION.equals(path.getFileExtension()) || path.getFileExtension() == null)) {
                    if (viewer instanceof RepoViewCommonViewer) {
                        this.runnables.add(new Runnable() {

                            @Override
                            public void run() {
                                ((RepoViewCommonViewer) viewer).getRepViewCommonNavigator().refreshAllChildNodes(
                                        (RepositoryNode) topLevelNode);
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

    protected static final Object[] NO_CHILDREN = new Object[0];

    private IRepositoryNode topLevelNode;

    private ProjectRepositoryNode projectRepositoryNode;

    private Viewer viewer;

    /**
     * Getter for projectRepositoryNode.
     * 
     * @return the projectRepositoryNode
     */
    public ProjectRepositoryNode getProjectRepositoryNode() {
        return this.projectRepositoryNode;
    }

    /**
     * DOC sgandon LegacyRepositoryContentProvider constructor comment.
     */
    public LegacyRepositoryContentProvider() {

    }

    @Override
    public Object[] getElements(Object inputElement) {// check for ProjectRepositoryNode parent
        return getChildren(inputElement);
    }

    /**
     * Called when getting the single root element for this Content Provider.
     * 
     * @param projectRepositoryNode, never null
     * @return the root object for this content provider
     */
    abstract protected IRepositoryNode getTopLevelNode(IProjectRepositoryNode projRepositoryNode);

    // use a cached version so that top level node does not get created again and again and we have a single instance
    private IRepositoryNode getCachedTopLevelNode(IProjectRepositoryNode projRepositoryNode) {
        if (topLevelNode == null) {
            this.topLevelNode = getTopLevelNode(projRepositoryNode);
        }// else already cached
        return topLevelNode;
    }

    @Override
    public Object[] getChildren(Object element) {
        if (projectRepositoryNode == null) {
            if (element instanceof ProjectRepositoryNode) {
                projectRepositoryNode = (ProjectRepositoryNode) element;
            } else {
                projectRepositoryNode = (ProjectRepositoryNode) Platform.getAdapterManager().getAdapter(element,
                        ProjectRepositoryNode.class);
            }
        }
        if (projectRepositoryNode == element
                || projectRepositoryNode == (ProjectRepositoryNode) Platform.getAdapterManager().getAdapter(element,
                        ProjectRepositoryNode.class)) {
            IRepositoryNode cachedTopLevelNode = getCachedTopLevelNode(projectRepositoryNode);

            return cachedTopLevelNode != null ? new Object[] { cachedTopLevelNode } : NO_CHILDREN;
        } else if (element instanceof RepositoryNode) {
            // // check that element is top level node or a children of it
            RepositoryNode repositoryNode = (RepositoryNode) element;
            if (!repositoryNode.isInitialized()) {
                if (repositoryNode.getParent() instanceof ProjectRepositoryNode) {
                    // initialize repository from main project
                    ((ProjectRepositoryNode) repositoryNode.getParent()).initializeChildren(element);
                }// else sub sub node so no need to initialize
                repositoryNode.setInitialized(true);
            }// else already initialised
            return repositoryNode.getChildren().toArray();
        }// else not a root node and not a child not so ignors it
        return NO_CHILDREN;

    }

    @Override
    public Object getParent(Object element) {
        return null;
    }

    @Override
    public boolean hasChildren(Object element) {
        return getChildren(element).length > 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.IContentProvider#dispose()
     */
    @Override
    public void dispose() {
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        if (workspace != null) {
            workspace.removeResourceChangeListener(this);
        }// else workspace not accessible any more so do nothing
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object,
     * java.lang.Object)
     */
    @Override
    public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
        this.viewer = arg0;
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        if (workspace != null) {
            workspace.addResourceChangeListener(this, IResourceChangeEvent.POST_CHANGE);
        }// else workspace not accessible any more so do nothing
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.core.resources.IResourceChangeListener#resourceChanged(org.eclipse.core.resources.IResourceChangeEvent
     * )
     */
    @Override
    public void resourceChanged(IResourceChangeEvent event) {
        final Control ctrl = viewer.getControl();
        if (ctrl == null || ctrl.isDisposed()) {
            return;
        }
        final Collection<Runnable> runnables = new ArrayList<Runnable>();
        try {
            event.getDelta().accept(new ResourceVisitor(runnables));
        } catch (CoreException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (!runnables.isEmpty()) {
            // Are we in the UIThread? If so spin it until we are done
            if (ctrl.getDisplay().getThread() == Thread.currentThread()) {
                runUpdates(runnables);
            } else {
                ctrl.getDisplay().asyncExec(new Runnable() {

                    /*
                     * (non-Javadoc)
                     * 
                     * @see java.lang.Runnable#run()
                     */
                    @Override
                    public void run() {
                        // Abort if this happens after disposes
                        Control ctrl2 = viewer.getControl();
                        if (ctrl2 == null || ctrl2.isDisposed()) {
                            return;
                        }

                        runUpdates(runnables);
                    }
                });
            }
        }// else do nothing caus there is nothing to do.
    }

    /**
     * Run all of the runnables that are the widget updates
     * 
     * @param runnables
     */
    private void runUpdates(Collection<Runnable> runnables) {
        for (Runnable runnable : runnables) {
            runnable.run();
        }

    }
}