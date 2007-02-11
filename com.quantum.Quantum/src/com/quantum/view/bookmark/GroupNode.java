package com.quantum.view.bookmark;

import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.quantum.Messages;
import com.quantum.model.Bookmark;
import com.quantum.model.DatabaseObject;
import com.quantum.model.Schema;
import com.quantum.model.SchemaHolder;

/**
 * GroupNode represents a level of grouping in the BookmarkView hierarchy
 * It will have categories like "TABLE", "VIEW" and so on, usually gotten from
 * the JDBC driver.
 * 
 * @author panic
 */
public class GroupNode extends TreeNode implements Comparable, SchemaHolder {
	private String type = null;
    private Schema schema = null;
    private boolean initialized = false;

	public GroupNode(TreeNode parent, Schema schema, String type) {
        super(parent);
        this.schema = schema;
		this.type = type;
	}
	
	protected boolean isInitialized() {
		return this.initialized;
	}
	public boolean hasChildren() {
		if (!isInitialized()) {
			return true;
		} else {
            return !this.children.isEmpty();
		}
	}
	public Object[] getChildren() {
        if (!isInitialized() && getBookmark().isConnected()) {
            initializeChildren();
        }
		return (TreeNode[]) this.children.toArray(new TreeNode[this.children.size()]);
	}
    protected void initializeChildren() {
        try {
            boolean firstTimeInitialization = !isInitialized();
            Map temp = new HashMap();
            for (Iterator i = this.children.iterator(); i.hasNext();) {
                TreeNode treeNode = (TreeNode) i.next();
                temp.put(treeNode.getName(), treeNode);
            }
            this.children.clear();
            
            Bookmark bookmark = getBookmark();
            DatabaseObject[] entities = bookmark.getObjectsForSchema(schema, type);
            for (int i = 0,
                length = (entities == null) ? 0 : entities.length;
                i < length;
                i++) {
                
                String name = entities[i].getName();
                DbObjectNode objectNode = (DbObjectNode) temp.remove(name);
                if (objectNode == null) {
                    this.children.add( DbObjectNodeFactory.create( this, entities[i] ));
                } else {
                    objectNode.setDatabaseObject(entities[i]);
                    this.children.add(objectNode);
                }
            }
             for (Iterator i = temp.values().iterator(); i.hasNext();) {
				((TreeNode) i.next()).dispose();
			}
            
            Collections.sort(this.children);
            if (!firstTimeInitialization) {
                firePropertyChange("children", null, null);
            }
            
            this.initialized = true;
        } catch (SQLException e) {
        }
    }
	public String getName() {
		return Messages.getString(getClass().getName() + "." + this.type);
	}
	protected String getImageName() {
		return "entitygroup.gif"; //$NON-NLS-1$
	}
    /**
     * @return
     */
    public Schema getSchema() {
        return schema;
    }
	public String getType() {
		return this.type;
	}
}
