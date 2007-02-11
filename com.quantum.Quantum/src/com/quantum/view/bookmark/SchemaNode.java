package com.quantum.view.bookmark;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.quantum.model.Bookmark;
import com.quantum.model.Schema;
import com.quantum.model.SchemaHolder;
import com.quantum.util.connection.NotConnectedException;

/**
 * @author BC
 */
public class SchemaNode extends TreeNode implements SchemaHolder {

    private Schema schema;
    /**
     * @param parent
     */
    public SchemaNode(TreeNode parent, Schema schema) {
        super(parent);
        this.schema = schema;
    }

    /**
     * @see com.quantum.view.bookmark.TreeNode#getChildren()
     */
    public Object[] getChildren() {
        Bookmark bookmark = getBookmark();
        if (!bookmark.isConnected()) {
            return BookmarkListNode.EMPTY_ARRAY;
        } else {
            if (this.children.isEmpty()) {
                initializeChildren();
            }
        }
        return (TreeNode[]) this.children.toArray(new TreeNode[this.children.size()]);
    }

    protected void initializeChildren() {
    	boolean firstTimeInitialization = this.children.isEmpty();
    	boolean changed = false;
        Map temp = new HashMap();
        for (Iterator i = this.children.iterator(); i.hasNext();) {
			GroupNode element = (GroupNode) i.next();
			temp.put(element.getType(), element);
		}
        this.children.clear();
        
        Bookmark bookmark = getBookmark();
        try {
            String[] types = bookmark.getDatabase().getEntityTypes();
            for (int i = 0, length = (types == null) ? 0 : types.length;
                i < length;
                i++) {
            	GroupNode node = (GroupNode) temp.remove(types[i]);
            	if (node == null) {
	                this.children.add(new GroupNode(this, this.schema, types[i]));
	                changed = true;
            	} else {
            		this.children.add(node);
            	}
            }
            for (Iterator i = temp.values().iterator(); i.hasNext();) {
				((GroupNode) i.next()).dispose();
				changed = true;
			}
            if (!firstTimeInitialization && changed) {
            	firePropertyChange("children", null, null);
            }
        } catch (NotConnectedException e) {
        } catch (SQLException e) {
        }
    }

    /**
     * @see com.quantum.view.bookmark.TreeNode#hasChildren()
     */
    public boolean hasChildren() {
        return getBookmark().isConnected();
    }

    /**
     * @see com.quantum.view.bookmark.TreeNode#getName()
     */
    public String getName() {
        return this.schema.getDisplayName();
    }

    /**
     * @see com.quantum.view.bookmark.TreeNode#getImageName()
     */
    protected String getImageName() {
        return this.schema.exists() 
				? "schema.gif" 
				: "missingschema.gif";
    }

    /**
     * @see com.quantum.model.SchemaHolder#getSchema()
     */
    public Schema getSchema() {
        return this.schema;
    }
}
