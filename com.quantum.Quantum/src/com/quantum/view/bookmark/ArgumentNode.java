/*
 * Created on Oct 19, 2004
 */
package com.quantum.view.bookmark;

import com.quantum.model.ProcedureArgument;

/**
 * @author lleavitt
 */
public class ArgumentNode extends TreeNode {
    
    private ProcedureArgument argument;
    
    public ArgumentNode(TreeNode parent, ProcedureArgument argument) {
        super(parent);
        this.argument = argument;
    }

    public Object[] getChildren() {
        return BookmarkListNode.EMPTY_ARRAY;
    }

    public boolean hasChildren() {
        return false;
    }

    public String getName() {
        return this.argument.getName();
    }

    protected String getImageName() {
        return this.argument.getIndex() == 0 ? "keycolumn.gif" : "column.gif";
    }

    public String getLabelName() {
        return getName() + " : " + this.argument.getTypeName(); //$NON-NLS-1$
    }


    protected void initializeChildren() {
    }
    
    protected boolean isInitialized() {
        return true;
    }

    /**
     * @return
     */
    public ProcedureArgument getProcedureArgument() {
        return argument;
    }

    void setProcedureArgument(ProcedureArgument argument) {
    	if (this.argument == null || !this.argument.equals(argument)) {
    	    ProcedureArgument original = argument;
	    	this.argument = argument;
	    	firePropertyChange("argument", original, argument);
    	}
    }
}
