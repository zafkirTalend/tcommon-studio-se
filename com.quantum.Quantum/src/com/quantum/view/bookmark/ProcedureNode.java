/*
 * Created on Oct 19, 2004
 */
package com.quantum.view.bookmark;

import java.sql.SQLException;
import java.util.Map;

import com.quantum.ImageStore;
import com.quantum.model.Procedure;
import com.quantum.model.ProcedureArgument;
import com.quantum.util.connection.NotConnectedException;

/**
 * @author lleavitt
 */
public class ProcedureNode extends DbObjectNode {

    boolean initialized = false;
    
    public ProcedureNode(TreeNode parent, Procedure object) {
        super(parent, object, false);
    }

    protected String getImageName() {
            return ImageStore.PROCEDURE;
    }
    
    public Procedure getProcedure() {
        return (Procedure)this.getDatabaseObject();
    }

    public void setProcedure( Procedure procedure ) {
        this.setDatabaseObject( procedure );
    }

    
    public Object[] getChildren() throws NotConnectedException, SQLException {
   	 if (!isInitialized()) {
 	 	initializeChildren();
 	 }
     if (this.children.size() > 0) {
         return (ArgumentNode[]) this.children.toArray(new ArgumentNode[this.children.size()]);
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
        ProcedureArgument[] arguments = getProcedure().getArguments();
        this.children.clear();
        for (int i = 0, length = (arguments == null) ? 0 : arguments.length;
            i < length;
            i++) {
        	
        	if(arguments[i] == null) 
        		continue;
            ArgumentNode node = (ArgumentNode) map.get(arguments[i].getName());
        	if (node == null) {
        		this.children.add(new ArgumentNode(this, arguments[i]));
        	} else {
        		node.setProcedureArgument(arguments[i]);
        		this.children.add(node);
        	}
        	
        	if (wasInitialized) {
        		firePropertyChange("arguments", null, null);
        	}
        }
    }
}
