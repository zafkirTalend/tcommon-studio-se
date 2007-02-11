package com.quantum.view.bookmark;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.quantum.IQuantumConstants;
import com.quantum.Messages;

/**
 * @author BC
 */
public class QueryListNode extends TreeNode implements PropertyChangeListener {

    /**
     * @param parent
     */
    public QueryListNode(TreeNode parent) {
        super(parent);
        getBookmark().addPropertyChangeListener(this);
    }

    /**
     * @see com.quantum.view.bookmark.TreeNode#getChildren()
     */
    public Object[] getChildren() {
        initializeChildren();
        return (TreeNode[]) this.children.toArray(new TreeNode[this.children.size()]);
    }

    protected void initializeChildren() {
        this.children.clear();
        String[] queries = getBookmark().getQueries();
        for (int i = 0, length = (queries == null) ? 0 : queries.length;
            i < length;
            i++) {
            this.children.add(new QueryNode(this, queries[i]));
        }
    }

    /**
     * @see com.quantum.view.bookmark.TreeNode#hasChildren()
     */
    public boolean hasChildren() {
        return getBookmark().getQueries().length > 0;
    }

    /**
     * @see com.quantum.view.bookmark.TreeNode#getName()
     */
    public String getName() {
        return Messages.getString(getClass().getName() + ".labelName");
    }

    /**
     * @see com.quantum.view.bookmark.TreeNode#getImageName()
     */
    protected String getImageName() {
        return "group.gif";
    }

    /**
     * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
     */
    public void propertyChange(PropertyChangeEvent event) {
        if (IQuantumConstants.QUERIES_PROPERTY.equals(event.getPropertyName())) {
            firePropertyChange(IQuantumConstants.QUERIES_PROPERTY, null, null);
        }
    }
    /**
     * @see com.quantum.view.bookmark.TreeNode#dispose()
     */
    protected void dispose() {
        getBookmark().removePropertyChangeListener(this);
        super.dispose();
    }

}
