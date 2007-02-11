package com.quantum.view.bookmark;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.quantum.Messages;
import com.quantum.model.Bookmark;
import com.quantum.model.DatabaseObject;

/**
 * <p>A "quick list" is a set of favourite entities that a user may want 
 * to work with without having to sift through the (somtimes lengthy) longer
 * list of tables.</p>
 * 
 * @author bcholmes
 */
public class QuickListNode extends TreeNode implements PropertyChangeListener {
    
    private Map children = new Hashtable();

    /**
     * @param parent
     */
    public QuickListNode(BookmarkNode parent) {
        super(parent);
        
        Bookmark bookmark = getBookmark();
        bookmark.addPropertyChangeListener(this);
    }
    
    /**
     * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
     */
    public void propertyChange(PropertyChangeEvent event) {
        if ("quickList".equals(event.getPropertyName())) {
            initializeChildren();
            firePropertyChange("quickList", null, null);
        }
    }

    /**
     * @see com.quantum.view.bookmark.TreeNode#hasChildren()
     */
    public boolean hasChildren() {
        return getBookmark().hasQuickList();
    }
    /**
     * @see com.quantum.view.bookmark.TreeNode#getImageName()
     */
    protected String getImageName() {
        return "group.gif";
    }
    
    /**
     * @see com.quantum.view.bookmark.TreeNode#getChildren()
     */
    public Object[] getChildren() {
        Bookmark bookmark = getBookmark();
        if (children.isEmpty() && bookmark.hasQuickList()) {
            initializeChildren();
        }
        List list = new ArrayList(this.children.values());
        Collections.sort(list);
        
        return (TreeNode[]) list.toArray(new TreeNode[list.size()]);
    }

    /**
     * @see com.quantum.view.bookmark.TreeNode#getName()
     */
    public String getName() {
        return Messages.getString(QuickListNode.class.getName() + ".labelName");
    }
    
    protected synchronized void initializeChildren() {
        this.children.clear();
        DatabaseObject[] entities = getBookmark().getQuickListEntries();
        for (int i = 0, length = (entities == null) ? 0 : entities.length;
            i < length;
            i++) {
                
            this.children.put(entities[i].getQualifiedName(), 
                            DbObjectNodeFactory.create( this, entities[i] ) );
        }
      }
    /* (non-Javadoc)
     * @see com.quantum.view.bookmark.TreeNode#isInitialized()
     */
    protected boolean isInitialized() {
        return true;
    }

}
