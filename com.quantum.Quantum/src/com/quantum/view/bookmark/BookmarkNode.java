package com.quantum.view.bookmark;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.quantum.IQuantumConstants;
import com.quantum.model.Bookmark;
import com.quantum.model.Schema;
import com.quantum.util.connection.NotConnectedException;

public class BookmarkNode extends TreeNode implements PropertyChangeListener {
    private Bookmark bookmark;
    
    private QuickListNode quickListNode;
    private QueryListNode queryListNode;

    public BookmarkNode(TreeNode parent, Bookmark bookmark) {
        super(parent);
    	this.bookmark = bookmark;
        this.bookmark.addPropertyChangeListener(this);
    }

	public Object[] getChildren() throws NotConnectedException, SQLException {
		if (bookmark.isConnected() && this.children.isEmpty()) {
            initializeChildren();
		}
        if (this.bookmark.isConnected()) {
            return (TreeNode[]) this.children.toArray(new TreeNode[this.children.size()]);
        } else {
            return BookmarkListNode.EMPTY_ARRAY;
        }
	}

    protected void initializeChildren() throws NotConnectedException, SQLException {
        boolean changed = false;
        Map temp = new HashMap();
        for (Iterator i = this.children.iterator(); i.hasNext(); ) {
            TreeNode node = (TreeNode) i.next();
            if (node instanceof SchemaNode) {
                temp.put(node.getName(), node);
            }
        }
        
        this.children.clear();
        if (this.quickListNode == null) {
            this.quickListNode = new QuickListNode(this);
        }
        if (this.queryListNode == null) {
            this.queryListNode = new QueryListNode(this);
        }
        this.children.add(this.quickListNode);
        this.children.add(this.queryListNode);
        Bookmark bookmark = getBookmark();
        
        Schema[] schemas = bookmark.getSchemas();
        for (int i = 0, length = (schemas == null) ? 0 : schemas.length;
            i < length;
            i++) {
            SchemaNode node = (SchemaNode) temp.remove(schemas[i].getDisplayName());
            if (node == null) {
                this.children.add(new SchemaNode(this, schemas[i]));
            } else {
                changed = true;
                this.children.add(node);
            }
        }
        
        for (Iterator i = temp.values().iterator(); i.hasNext(); ) {
            ((TreeNode) i.next()).dispose();
            changed = true;
        }
        
        if (temp.size() > 0 || changed ) {
            firePropertyChange("children", null, null);
        }
    }

	public boolean hasChildren() {
		// If the bookmark is connected but hasn't loaded the tables and views, we suppose it may have some
		if (bookmark.isConnected() && this.children.isEmpty()) {
            return true;
        } else if (!bookmark.isConnected()) {
            return false;
        } else if (children != null && children.size() > 0) {
			return true;
		}
		return false;
	}

	protected void dispose() {
        try {
            this.bookmark.removePropertyChangeListener(this);
            if (this.bookmark.isConnected()) {
                this.bookmark.disconnect();
            }
        } catch (SQLException e) {
        }
	}

    /**
     * @see com.quantum.model.TreeNode#getName()
     */
    public String getName() {
        return this.bookmark == null ? "<<new>>" : this.bookmark.getName();
    }

    /**
     * @return
     */
    public Bookmark getBookmark() {
        return this.bookmark;
    }

    protected String getImageName() {
        return this.bookmark.isConnected() ? "connected.gif" : "bookmarks.gif";
    }

    /* (non-Javadoc)
     * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
     */
    public void propertyChange(PropertyChangeEvent event) {
        if ("connected".equals(event.getPropertyName())) {
            if (Boolean.FALSE.equals(event.getNewValue())) {
                removeAllChildren();
            }
            firePropertyChange("connected", event.getOldValue(), event.getNewValue());
        } else if (IQuantumConstants.SCHEMAS_PROPERTY.equals(event.getPropertyName())) {
        	try {
        		initializeChildren();
        	} catch (NotConnectedException e) {
        		this.children.clear();
        	} catch (SQLException e) {
        		this.children.clear();
        	}
            firePropertyChange("children", event.getOldValue(), event.getNewValue());
        } else if (IQuantumConstants.NAME_PROPERTY.equals(event.getPropertyName())) {
            firePropertyChange(IQuantumConstants.NAME_PROPERTY, event.getOldValue(), event.getNewValue());
        }
    }

    protected void removeAllChildren() {
        if (this.quickListNode != null) {
            this.quickListNode.dispose();
            this.quickListNode = null;
        }
        if (this.queryListNode != null) {
            this.queryListNode.dispose();
            this.queryListNode = null;
        }
        super.removeAllChildren();
        
    }
    public String getLabelDecorations(LabelDecorationInstructions labelDecorationInstructions) {
        if (!labelDecorationInstructions.isDatabaseDataVisible()) {
            return null;
        } else if (!this.bookmark.isConnected()) {
            return null;
        } else {
            try {
                String decoration = this.bookmark.getDatabase().getInformation();
                return decoration == null ? null : "[" + decoration + "]";
            } catch (NotConnectedException e) {
                return null;
            } catch (SQLException e) {
                return null;
            }
        }
    }
}
