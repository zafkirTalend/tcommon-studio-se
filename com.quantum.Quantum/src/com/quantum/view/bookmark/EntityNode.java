package com.quantum.view.bookmark;

import java.sql.SQLException;
import java.util.Map;

import com.quantum.model.Column;
import com.quantum.model.Entity;
import com.quantum.model.EntityHolder;
import com.quantum.model.Table;
import com.quantum.model.View;
import com.quantum.util.connection.NotConnectedException;

/**
 * @author BC
 */
public class EntityNode extends DbObjectNode implements EntityHolder {
//    private List columns = Collections.synchronizedList(new ArrayList());
//    private List foreignKeys = Collections.synchronizedList(new ArrayList());
    
    public EntityNode(TreeNode parent, Entity entity) {
        super(parent, entity, false);
    }
    public EntityNode(TreeNode parent, Entity entity, boolean longFormName) {
        super(parent, entity, longFormName);
    }

    public Object[] getChildren() throws NotConnectedException, SQLException {
    	 if (!isInitialized()) {
    	 	initializeChildren();
    	 }
    	
        if (this.children.size() > 0) {
            return (ColumnNode[]) this.children.toArray(new ColumnNode[this.children.size()]);
        } else {
            return BookmarkListNode.EMPTY_ARRAY;
        }
    }

    protected synchronized void initializeChildren() throws NotConnectedException, SQLException {
    	boolean wasInitialized = isInitialized();
        Map map = getChildrenAsMap();
        Column[] columns = getEntity().getColumns();
        this.children.clear();
        for (int i = 0, length = (columns == null) ? 0 : columns.length;
            i < length;
            i++) {
        	
        	ColumnNode node = (ColumnNode) map.get(columns[i].getName());
        	if (node == null) {
        		this.children.add(new ColumnNode(this, columns[i]));
        	} else {
        		node.setColumn(columns[i]);
        		this.children.add(node);
        	}
        	
        	if (wasInitialized) {
        		firePropertyChange("columns", null, null);
        	}
        }
    }
    
    public boolean hasChildren() {
        if (!isSequence()) {
            return true;
        } else {
            return false;
        }
    }
    
    public Entity getEntity() {
        return (Entity)this.getDatabaseObject();
    }
    
    public boolean isTable() {
        return Entity.TABLE_TYPE.equals(getEntity().getType());
    }

    public boolean isView() {
        return Entity.VIEW_TYPE.equals(getEntity().getType());
    }

    public boolean isSequence() {
        return Entity.SEQUENCE_TYPE.equals(getEntity().getType());
    }

    protected String getImageName() {
        if (isSequence()) {
            return "sequence.gif";
        } else if (isView()) {
            return "view.gif";
        } else {
            if (getEntity().exists() == null || getEntity().exists().booleanValue()){
            	if (getEntity().isSynonym())
            		return "big_syn_table.gif";
            	else
            		return "bigtable.gif";
            } else
            	return "missingtable.gif";
   
   
        }
    }

    public String getLabelDecorations(LabelDecorationInstructions instructions) {
        String decoration = null;
        if (instructions.isSizeVisible()) {
            Integer size = getSize();
            if (size != null) {
                decoration = ((decoration == null) ? "" : decoration) 
                    + "[" + size + "]";
            }
        }
        return decoration;
    }
    
    private Integer getSize() {
        if (isTable()) {
            return ((Table) getEntity()).getSize();
        } else if (isView()) {
            return ((View) getEntity()).getSize();
        } else {
            return null;
        }
    }
    
    public int compareTo(Object o) {
        if (o instanceof EntityNode) {
            EntityNode that = (EntityNode) o;
            return getEntity().getQualifiedName().compareTo(
                that.getEntity().getQualifiedName());
        } else {
            return super.compareTo(o);
        }
    }
}
