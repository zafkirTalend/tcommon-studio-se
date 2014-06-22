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

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.talend.core.repository.model.ProxyRepositoryFactory;
import org.talend.repository.model.IProxyRepositoryFactory;
import org.talend.repository.model.IRepositoryNode;
import org.talend.repository.model.ProjectRepositoryNode;
import org.talend.repository.model.RepositoryNode;

public abstract class SingleTopLevelContentProvider implements ITreeContentProvider {

    private final IProxyRepositoryFactory factory = ProxyRepositoryFactory.getInstance();

    protected static final Object[] NO_CHILDREN = new Object[0];

    private RepositoryNode topLevelNode;

    // private ProjectRepositoryNode projectRepositoryNode;

    /**
     * Getter for topLevelNode.
     * 
     * @return the topLevelNode
     */
    public RepositoryNode getTopLevelNode() {
        return this.topLevelNode;
    }

    private RepositoryNode rootNode;

    /**
     * Getter for projectRepositoryNode.
     * 
     * @return the projectRepositoryNode
     */
    // public ProjectRepositoryNode getProjectRepositoryNode() {
    // return this.projectRepositoryNode;
    // }

    /**
     * DOC sgandon LegacyRepositoryContentProvider constructor comment.
     */
    public SingleTopLevelContentProvider() {

    }

    protected IProxyRepositoryFactory getFactory() {
        return factory;
    }

    @Override
    public Object[] getElements(Object inputElement) {// check for ProjectRepositoryNode parent
        return getChildren(inputElement);
    }

    /**
     * Called when getting the single root element for this Content Provider. This is called only once if the return
     * value is not null
     * 
     * @param the root node, never null
     * @return the first and single item object for this content provider
     */
    abstract protected RepositoryNode getInitialTopLevelNode(RepositoryNode theRootNode);

    // use a cached version so that top level node does not get created again and again and we have a single instance
    private RepositoryNode getCachedTopLevelNode(RepositoryNode repositoryNode) {
        if (topLevelNode == null) {
            this.topLevelNode = getInitialTopLevelNode(repositoryNode);
        }// else already cached
        return topLevelNode;
    }

    /**
     * this returnt the children for any type handle by this class. It first check if the element is a potential root
     * node, if so it store it. if element is the root node then getTopLevelNode is called to get the single children if
     * it is some other nodes it can handle is returns their children by delegating to the getRepositoryNodeChildren or
     * returns none.
     * */
    @Override
    public Object[] getChildren(Object element) {
        // store the root node
        if (getRootNode() == null && isRootNodeType(element)) {
            RepositoryNode potentialRootNode = extractPotentialRootNode(element);
            setRootNode(potentialRootNode);
        }

        RepositoryNode theRootNode = getRootNode();
        // if root then return the top level node
        if (isRootNodeType(element) && extractPotentialRootNode(element) == theRootNode) {
            IRepositoryNode cachedTopLevelNode = getCachedTopLevelNode(theRootNode);
            return cachedTopLevelNode != null ? new Object[] { cachedTopLevelNode } : NO_CHILDREN;
        } else if (element instanceof RepositoryNode) {// else return children
            // // check that element is top level node or a children of it
            RepositoryNode repositoryNode = (RepositoryNode) element;
            return getRepositoryNodeChildren(repositoryNode);
        }// else not a root node and not a child not so ignors it
        return NO_CHILDREN;

    }

    /**
     * DOC sgandon Comment method "isRootNode".
     * 
     * @param element
     * @return
     */
    abstract protected boolean isRootNodeType(Object element);

    /**
     * extract a potention root node. This is called right after checking type by calling isRootNodeType so the check
     * does not need to be done again.
     * 
     * @param element used to find a potential root node
     * @return the potential rootNode or null.
     */
    abstract protected RepositoryNode extractPotentialRootNode(Object element);

    /**
     * DOC sgandon Comment method "setRootNode".
     * 
     * @param theRootNode
     */
    protected void setRootNode(RepositoryNode theRootNode) {
        rootNode = theRootNode;

    }

    /**
     * the root object of this content provider.
     * 
     * @return
     */
    protected RepositoryNode getRootNode() {
        return rootNode;
    }

    /**
     * DOC sgandon Comment method "getRepositoryNodeChildren".
     * 
     * @param element
     * @param repositoryNode
     * @return
     */
    protected Object[] getRepositoryNodeChildren(RepositoryNode repositoryNode) {
        if (!repositoryNode.isInitialized()) {
            if (repositoryNode.getParent() instanceof ProjectRepositoryNode) {
                // initialize repository from main project
                initializeChildren(repositoryNode);
            }// else sub sub node so no need to initialize
            repositoryNode.setInitialized(true);
        }// else already initialised
        return repositoryNode.getChildren().toArray();
    }

    protected void initializeChildren(RepositoryNode parent) {
        ((ProjectRepositoryNode) parent.getParent()).initializeChildren(parent);
    }

    @Override
    public Object getParent(Object element) {
        return null;
    }

    @Override
    public boolean hasChildren(Object element) {
        Boolean boolean1 = factory.hasChildren(element);
        if (boolean1 != null) {
            return boolean1;
        } else {
            if (element instanceof RepositoryNode) {
                RepositoryNode repositoryNode = (RepositoryNode) element;
                if (repositoryNode.isInitialized()) {
                    return repositoryNode.getChildren().size() > 0;
                } else {
                    return getChildren(element).length > 0;
                }
            }
            return true;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.IContentProvider#dispose()
     */
    @Override
    public void dispose() {
        // do nothing

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.talend.repository.viewer.content.ProjectRepoDirectChildrenNodeContentProvider#inputChanged(org.eclipse.jface
     * .viewers.Viewer, java.lang.Object, java.lang.Object)
     */
    @Override
    public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
        // do nothing caus there is nothing to update.
    }

}
