package com.quantum.view.subset;

import com.quantum.view.bookmark.TreeNode;


/**
 * @author root
 */
public final class SubsetRoot extends TreeNode {
    public final static SubsetRoot ROOT = new SubsetRoot();
 	public final static TreeNode[] EMPTY_ARRAY = new TreeNode[0];
   
    private SubsetRoot() {
        super(null);
    }
	/**
	 * @see com.quantum.view.bookmark.TreeNode#getChildren()
	 */
	public Object[] getChildren() {
		return EMPTY_ARRAY;
	}

	/**
	 * @see com.quantum.view.bookmark.TreeNode#hasChildren()
	 */
	public boolean hasChildren() {
		return true;
	}
	
	public String getName() {
		return "ROOT"; //$NON-NLS-1$
	}
    protected String getImageName() {
        return null;
    }
    /* (non-Javadoc)
     * @see com.quantum.view.bookmark.TreeNode#initializeChildren()
     */
    protected void initializeChildren() {
    }
}
