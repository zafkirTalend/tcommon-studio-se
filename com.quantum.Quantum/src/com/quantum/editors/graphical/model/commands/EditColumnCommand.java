/*
 * Created on Jul 17, 2004
 */
package com.quantum.editors.graphical.model.commands;

import org.eclipse.gef.commands.Command;

import com.quantum.editors.graphical.model.Column;

/**
 * Command to edit a column object
 * 
 * @author jhvdv
 */
public class EditColumnCommand extends Command{

	private Column column = null;
    private String type;
    private int precision;
    private long size;
    private String oldType;
    private int oldPrecision;
    private long oldSize;
    
	public boolean canExecute() {
        if (column != null) {
            return true;
        }
        return true;
    }

	public void execute() {
        column.setSize(size);
        column.setOriginalSize(size);
        column.setPrecision(precision);
        column.setOriginalPrecision(precision);
        column.setType(type);
        column.setOriginalType(type);
    }

	public void redo() {
        execute();
    }
    
    public void undo() {
        column.setSize(oldSize);
        column.setPrecision(oldPrecision);
        column.setType(oldType);
    }

	public void setColumn(Column co) {
        column = co;
    }

    public void setAttributes(String type, long size, int precision){
        this.type = type;
        this.size = size;
        this.precision = precision;
    }
    public void setOldAttributes(String type, long size, int precision){
        this.oldType = type;
        this.oldSize = size;
        this.oldPrecision = precision;
    }
}

