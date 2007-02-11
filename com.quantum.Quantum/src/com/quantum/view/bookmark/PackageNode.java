/*
 * Created on Oct 19, 2004
 */
package com.quantum.view.bookmark;

import java.sql.SQLException;
import java.util.Map;

import com.quantum.ImageStore;
import com.quantum.model.Package;
import com.quantum.model.Procedure;
import com.quantum.util.connection.NotConnectedException;
/**
 * @author lleavitt
 */
public class PackageNode extends DbObjectNode {

    boolean initialized = false;
    
    public PackageNode(TreeNode parent, Package object) {
        super(parent, object, false);
    }

    protected String getImageName() {
        return ImageStore.ALL_PROCEDURES;
}
    
    public Package getPackage() {
        return (Package)this.getDatabaseObject();
    }


    public Object[] getChildren() throws NotConnectedException, SQLException {
   	 if (!isInitialized()) {
 	 	initializeChildren();
 	 }
     if (this.children.size() > 0) {
         return (ProcedureNode[]) this.children.toArray(new ProcedureNode[this.children.size()]);
     } else {
         return BookmarkListNode.EMPTY_ARRAY;
     }
    }

    public boolean hasChildren() {
        return true;
    }

    protected void initializeChildren() throws SQLException, NotConnectedException {
    	boolean wasInitialized = isInitialized();
        Map map = getChildrenAsMap();
        Procedure[] procedures = getPackage().getProcedures();
        this.children.clear();
        for (int i = 0, length = (procedures == null) ? 0 : procedures.length;
            i < length;
            i++) {
        	
            ProcedureNode node = (ProcedureNode) map.get(procedures[i].getName());
        	if (node == null) {
        		this.children.add(new ProcedureNode(this, procedures[i]));
        	} else {
        		node.setProcedure(procedures[i]);
        		this.children.add(node);
        	}
        	
        	if (wasInitialized) {
        		firePropertyChange("procedures", null, null);
        	}
        }
    }

}
