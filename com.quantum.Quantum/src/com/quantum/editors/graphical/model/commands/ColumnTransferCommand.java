/*
 * Created on Jul 19, 2004
 */
package com.quantum.editors.graphical.model.commands;

import org.eclipse.gef.commands.Command;

import com.quantum.editors.graphical.model.Column;
import com.quantum.editors.graphical.model.Table;

/**
 * Moves column to a different table
 * 
 * @author Phil Zoio
 */
public class ColumnTransferCommand extends Command
{

	private Column columnToMove;
	private Table originalTable;
	private Table newTable;
	private int oldIndex, newIndex;

	public ColumnTransferCommand(Column columnToMove, Column columnAfter, Table originalTable, Table newTable,
			int oldIndex, int newIndex)
	{
		super();
		this.columnToMove = columnToMove;
		this.originalTable = originalTable;
		this.newTable = newTable;
		this.oldIndex = oldIndex;
		this.newIndex = newIndex;
	}

	/**
	 * allows for transfer only if there are one or more columns
	 */
	public boolean canExecute()
	{
		if (originalTable.getModelColumns().size() > 1)
			return true;
		else
			return false;
	}

	public void execute()
	{
		originalTable.removeColumn(columnToMove);
		newTable.addColumn(columnToMove, newIndex);
	}

	public void undo()
	{
		newTable.removeColumn(columnToMove);
		originalTable.addColumn(columnToMove, oldIndex);
	}

}