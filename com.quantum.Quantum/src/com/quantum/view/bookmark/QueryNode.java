package com.quantum.view.bookmark;

import com.quantum.model.ObjectMetaData;

public class QueryNode extends TreeNode {
    
    private String query;
    private static final int MAX_SIZE = 30;
	
	public QueryNode(TreeNode parent, String query) {
        super(parent);
		this.query = query;
	}

	public ObjectMetaData getMetaData() {
		 return null;	//no metadata implementation for now
	 }

	public String getQuery() {
		return this.query;
	}

    public String getName() {
        String name = this.query.trim();
        boolean trimmed = false;
        name = name.replace('\n', ' ');
        name = name.replace('\f', ' ');
        name = name.replace('\r', ' ');
        name = name.replace('\t', ' ');
        
        if (name.length() > MAX_SIZE) {
            name = name.substring(0, MAX_SIZE);
            trimmed = true;
        }
        if (trimmed) {
            name += "...->"; //$NON-NLS-1$
        }
		return name;
	}

    /**
     * @see com.quantum.view.bookmark.TreeNode#getChildren()
     */
    public Object[] getChildren() {
        return BookmarkListNode.EMPTY_ARRAY;
    }

    /**
     * @see com.quantum.view.bookmark.TreeNode#hasChildren()
     */
    public boolean hasChildren() {
        return false;
    }

    /**
     * @see com.quantum.view.bookmark.TreeNode#getImageName()
     */
    protected String getImageName() {
        return "script.gif";
    }

    /* (non-Javadoc)
     * @see com.quantum.view.bookmark.TreeNode#initializeChildren()
     */
    protected void initializeChildren() {
    }
}
