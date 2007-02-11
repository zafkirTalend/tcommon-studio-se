/*
 * Created on Jul 18, 2004
 */
package com.quantum.editors.graphical.model.commands;

import org.eclipse.gef.commands.Command;

import com.quantum.editors.graphical.model.Column;

/**
 * Command to change the name and type text field
 * 
 * @author Phil Zoio
 */
public class ResetNameForColumnCommand extends Command
{

	private Column source;
	private String name, oldName;
	/**
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute()
	{
        source.setName(name); // this one fires the property change
	}

	/**
	 * @return whether we can apply changes
	 */
	public boolean canExecute()
	{
		if (name != null)// && type != null)
		{
			return true;
		}
		else
		{
			name = oldName;
			return false;
		}
	}

	/**
	 * Sets the new Column name
	 * 
	 * @param string
	 *            the new name
	 */
	public void setNameType(String string)
	{
		String oldName = this.name;

		if (string != null)
		{
		    name = string;      
        }
	}

	/**
	 * Sets the old Column name
	 * 
	 * @param string
	 *            the old name
	 */
	public void setOldName(String string)
	{
		oldName = string;
	}

	/**
	 * Sets the source Column
	 * 
	 * @param column
	 *            the source Column
	 */
	public void setSource(Column column)
	{
		source = column;
	}

	/**
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo()
	{
		source.setName(oldName);
	}
}