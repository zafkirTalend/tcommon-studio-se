package com.quantum.view.bookmark;

import com.quantum.model.Column;

/**
 * @author BC
 */
public class ColumnNode extends TreeNode {
    
    private Column column;
    
    public ColumnNode(TreeNode parent, Column column) {
        super(parent);
        this.column = column;
    }

    public Object[] getChildren() {
        return BookmarkListNode.EMPTY_ARRAY;
    }

    public boolean hasChildren() {
        return false;
    }

    public String getName() {
        return this.column.getName();
    }

    protected String getImageName() {
        return this.column.isPrimaryKey() ? "keycolumn.gif" : "column.gif";
    }

    public String getLabelName() {
        String label = getName() + " : " + this.column.getTypeName(); //$NON-NLS-1$
        if (this.column.isNumeric()) {
            if (this.column.getSize() > 0 || this.column.getNumberOfFractionalDigits() > 0) {
                label += "(" + Long.toString(this.column.getSize()); //$NON-NLS-1$
                if (this.column.getNumberOfFractionalDigits() > 0) {
                    label += "," + Integer.toString(this.column.getNumberOfFractionalDigits()); //$NON-NLS-1$
                }
                label += ")"; //$NON-NLS-1$
            }
        } else if (this.column.getSize() > 0) {
            label += "(" + Long.toString(this.column.getSize()) + ")"; //$NON-NLS-1$ //$NON-NLS-2$
        }
        return label;
    }
    /* (non-Javadoc)
     * @see com.quantum.view.bookmark.TreeNode#initializeChildren()
     */
    protected void initializeChildren() {
    }
    protected boolean isInitialized() {
        return true;
    }

    /**
     * @return
     */
    public Column getColumn() {
        return column;
    }

    void setColumn(Column column) {
    	if (this.column == null || !this.column.equals(column)) {
    		Column original = column;
	    	this.column = column;
	    	firePropertyChange("column", original, column);
    	}
    }
}
