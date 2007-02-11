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

public class FolderNode extends TreeNode {
    private String name;
    private boolean open;
    
    public FolderNode(TreeNode parent, String name) {
        super(parent);
        this.name = name;
        this.open = false;
    }

	public Object[] getChildren() throws NotConnectedException, SQLException {
		return (TreeNode[]) this.children.toArray(new TreeNode[this.children.size()]);
	}

    protected void initializeChildren() throws NotConnectedException, SQLException {
    }

	public boolean hasChildren() {
		return (children != null && children.size() > 0);
	}

	protected void dispose() {
	}

    /**
     * @see com.quantum.model.TreeNode#getName()
     */
    public String getName() {
        return name;
    }

    protected String getImageName() {
        return open ? "entitygroup.gif" : "entitygroup.gif";
    } 

}
