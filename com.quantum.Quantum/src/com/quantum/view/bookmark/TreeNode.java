package com.quantum.view.bookmark;

import java.beans.PropertyChangeEvent;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

import com.quantum.ImageStore;
import com.quantum.model.Bookmark;
import com.quantum.model.BookmarkHolder;
import com.quantum.util.connection.NotConnectedException;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.swt.graphics.Image;

/**
 * Base class for all nodes of the internal tree of data. Basically allows navigation.
 * @author root
 */
public abstract class TreeNode 
    implements BookmarkHolder, Comparable, IAdaptable {
    
    private TreeNode parent = null;
    protected Vector children = new Vector();
    private boolean disposed = false;

    public TreeNode(TreeNode parent) {
        this.parent = parent;
    }
    
	public abstract Object[] getChildren() throws NotConnectedException, SQLException;
	public TreeNode getParent() {
        return this.parent;
	}
	public abstract boolean hasChildren();
	public abstract String getName();

    public Bookmark getBookmark() {
        return getParent() == null ? null : getParent().getBookmark();
    }
    
    public String getLabelName() {
        return getName();
    }

    /**
	 * @return an Image object to appear in the view, null if not found
	 */
    public Image getImage() {
    	return ImageStore.getImage(getImageName());
    }

    /**
     * @return
     */
    protected abstract String getImageName();
    
    
    /**
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(Object object) {
        TreeNode that = (TreeNode) object;
        return this.getLabelName().toLowerCase().compareTo(that.getLabelName().toLowerCase());
    }
    
    public String toString() {
        return getLabelName();
    }
    
    public String getLabelDecorations(LabelDecorationInstructions labelDecorationInstructions) {
        return null;
    }
    /**
     * @param propertyName
     * @param oldValue
     * @param newValue
     */
    protected void firePropertyChange(
        String propertyName,
        Object oldValue,
        Object newValue) {
            
        firePropertyChange(new PropertyChangeEvent(this, propertyName, oldValue, newValue));
    }

    /**
     * @param propertyName
     * @param oldValue
     * @param newValue
     */
    protected void firePropertyChange(PropertyChangeEvent event) {
        TreeNode parent = getParent();
        if (parent != null && !this.disposed) {
            parent.firePropertyChange(event);
        }
    }
    
    protected void dispose() {
        this.disposed = true;
        removeAllChildren();
//        this.parent = null;
    }
    protected void removeAllChildren() {
        for (Iterator i = this.children.iterator(); i.hasNext();) {
            TreeNode element = (TreeNode) i.next();
            element.dispose();
        }
    }
    
    protected boolean isInitialized() {
        return !this.children.isEmpty();
    }
    
    public Object getAdapter(Class adapter) {
        return null;
    }
    protected abstract void initializeChildren() throws SQLException, NotConnectedException;
    
    public void reload() throws NotConnectedException, SQLException {
        if (isInitialized()) {
            initializeChildren();
            for (Iterator i = this.children.iterator(); i.hasNext(); ) {
                ((TreeNode) i.next()).reload();
            }
        }
    }
}
