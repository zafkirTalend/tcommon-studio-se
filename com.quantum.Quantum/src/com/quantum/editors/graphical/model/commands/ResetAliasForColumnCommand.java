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
public class ResetAliasForColumnCommand extends Command
{

	private Column source;
	private String alias, oldAlias;
	/**
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute()
	{
        source.setAliasName(alias); // this one fires the property change
	}

	/**
	 * @return whether we can apply changes
	 */
	public boolean canExecute()
	{
		if (alias!= null)// && type != null)
		{
			return true;
		}
		else
		{
			alias = oldAlias;
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
		String oldAlias= this.alias;

		if (string != null)
		{
		    alias = string;      
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
		oldAlias = string;
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
		source.setAliasName(oldAlias);
	}
}