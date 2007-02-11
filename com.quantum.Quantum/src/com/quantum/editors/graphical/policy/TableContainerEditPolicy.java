/*
 * Created on Jul 15, 2004
 */
package com.quantum.editors.graphical.policy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ContainerEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import com.quantum.editors.graphical.model.Column;
import com.quantum.editors.graphical.model.Table;
import com.quantum.editors.graphical.model.commands.ColumnCreateCommand;
import com.quantum.editors.graphical.parts.TablePart;

/**
 * Edit policy for Table as a container, handling functionality for dropping new columns into tables
 * 
 * @author Phil Zoio
 */
public class TableContainerEditPolicy extends ContainerEditPolicy
{

	/**
 * @return command to handle adding a new column
 */
	protected Command getCreateCommand(CreateRequest request)
	{
		Object newObject = request.getNewObject();
		if (!(newObject instanceof Column))
		{
			return null;
		}
		
		TablePart tablePart = (TablePart) getHost();
		Table table = (Table)tablePart.getModel();
		Column column = (Column) newObject;
		ColumnCreateCommand command = new ColumnCreateCommand();
		command.setTable(table);
		command.setColumn(column);
		return command;
	}

}