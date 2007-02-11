/*
 * Created on Jul 15, 2004
 */
package com.quantum.editors.graphical.model.commands;

import org.eclipse.gef.commands.Command;

import com.quantum.editors.graphical.model.Column;
import com.quantum.editors.graphical.model.Table;


/**
 * Command to create a new table
 * 
 * @author Phil Zoio
 */
public class ColumnCreateCommand extends Command
{

	private Column column;
	private Table table;
	private int index = -1;
    
	public void setColumn(Column column) {
        this.column = column;
    }

	public void setTable(Table table) {
        this.table = table;
    }

    public void setIndex(int index){
        this.index = index;
    }
    
	public void execute() {
        if(index == -1){
            table.addColumn(column);
        }else{
            table.addColumn(column, index);
        }
    }

	public void undo() {
        table.removeColumn(column);
    }

}