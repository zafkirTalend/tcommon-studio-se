package com.quantum.view.bookmark;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import com.quantum.model.Bookmark;
import com.quantum.model.BookmarkCollection;


/**
 * @author root
 */
public final class BookmarkListNode extends TreeNode implements PropertyChangeListener {
    final static BookmarkListNode instance = new BookmarkListNode();
 	final static TreeNode[] EMPTY_ARRAY = new TreeNode[0];
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private boolean initialized = false;
   
    private BookmarkListNode() {
        super(null);
    }
    
    public static BookmarkListNode getInstance() {
        return BookmarkListNode.instance;
    }
    
	/**
	 * @see com.quantum.view.bookmark.TreeNode#getChildren()
	 */
	public Object[] getChildren() {
        if (!initialized) {
            initializeChildren();
            BookmarkCollection.getInstance().addPropertyChangeListener(this);
        }
        BookmarkNode[] nodes = (BookmarkNode[]) this.children.toArray(
            new BookmarkNode[this.children.size()]);
        Arrays.sort(nodes);
        return nodes;
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
    /**
     * @param listener
     */
    public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
        this.propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * @param listener
     */
    public synchronized void removePropertyChangeListener(PropertyChangeListener listener) {
        this.propertyChangeSupport.removePropertyChangeListener(listener);
    }
    protected void firePropertyChange(PropertyChangeEvent event) {
        this.propertyChangeSupport.firePropertyChange(event);
    }

    protected synchronized void initializeChildren() {
        boolean changed = false;
        Map temp = new Hashtable();
        for (Iterator i = this.children.iterator(); i.hasNext();) {
            BookmarkNode bookmark = (BookmarkNode) i.next();
            temp.put(bookmark.getName(), bookmark);
        }
        
        this.children.clear();
        
        Bookmark[] bookmarks = BookmarkCollection.getInstance().getBookmarks();
        for (int i = 0, length = (bookmarks == null) ? 0 : bookmarks.length; i < length; i++) {
            
            BookmarkNode node = (BookmarkNode) temp.remove(bookmarks[i].getName());
            if (node == null) {
                this.children.add(new BookmarkNode(this, bookmarks[i]));
                changed = true;
            } else {
                this.children.add(node);
            }
        }

        for (Iterator i = temp.values().iterator(); i.hasNext(); ) {
            ((TreeNode) i.next()).dispose();
            changed = true;
        }

        Collections.sort(this.children);
        this.initialized = true;
        
        if  (temp.size() > 0 || changed ) {
            firePropertyChange("bookmarks", null, null);
        }
    }

    /**
     * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
     */
    public void propertyChange(PropertyChangeEvent event) {
        if ("bookmarks".equals(event.getPropertyName())) {
            initializeChildren();
        }
    }
}
