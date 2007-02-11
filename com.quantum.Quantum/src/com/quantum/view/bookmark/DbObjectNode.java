/*
 * Created on Oct 19, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.quantum.view.bookmark;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.quantum.model.DatabaseObject;
import com.quantum.model.SchemaHolder;
import com.quantum.util.connection.NotConnectedException;

/**
 * @author lleavitt
 */
public abstract class DbObjectNode extends TreeNode {

    DatabaseObject object;
    private boolean longFormName;

    public DbObjectNode(TreeNode parent, DatabaseObject object ) {
        this(parent, object, false);
    }
    public DbObjectNode(TreeNode parent, DatabaseObject object, boolean longFormName) {
        super(parent);
        this.object = object;
        this.longFormName = longFormName;
        
        if (parent instanceof SchemaHolder) {
            SchemaHolder schemaHolder = (SchemaHolder) parent;
            if (!schemaHolder.getSchema().getDisplayName().equals(object.getSchema())) {
                this.longFormName = true;
            }
        }
    }

    public DatabaseObject getDatabaseObject() {
        return this.object;
    }
    
    protected void setDatabaseObject( DatabaseObject object ) {
		this.object = object;
    }
    
    public String getName() {
        return this.object.getName();
    }
    
    public String getLabelName() {
        return (this.longFormName) ? this.object.getQualifiedName() : this.object.getName();
    }

    protected String getImageName() {
        return null;
    }

    // -----------------------------------
    public Object[] getChildren() throws NotConnectedException, SQLException {
        return BookmarkListNode.EMPTY_ARRAY;
    }

    public boolean hasChildren() {
        return false;
    }

    protected void initializeChildren() throws SQLException, NotConnectedException {
    }

    protected Map getChildrenAsMap() {
    	Map map = new HashMap();
    	for (Iterator i = this.children.iterator(); i.hasNext();) {
			TreeNode node = (TreeNode) i.next();
			map.put(node.getName(), node);
		}
    	return map;
    }
}
