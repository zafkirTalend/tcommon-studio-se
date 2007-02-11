/*
 * Created on Jul 15, 2004
 */
package com.quantum.editors.graphical.model.commands;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.PlatformUI;

import com.quantum.ImageStore;
import com.quantum.editors.graphical.model.Column;
import com.quantum.editors.graphical.model.Relationship;
import com.quantum.editors.graphical.model.Table;
import com.quantum.ui.dialog.RelationshipDialog;


/**
 * Command to create a new relationship between tables
 * 
 * @author Phil Zoio
 */
public class RelationshipCreateCommand extends Command
{

	/** The relationship between primary and foreign key tables * */
	protected Relationship relationship;
	/** The source (foreign key) table * */
	protected Table foreignTable;
	/** The target (primary key) table * */
	protected Table primaryTable;

	/**
	 * @see org.eclipse.gef.commands.Command#canExecute()
	 */
	public boolean canExecute()
	{

		boolean returnValue = true;
		if (foreignTable.equals(primaryTable))
		{
			returnValue = false;
		}
		else
		{

			if (primaryTable == null)
			{
				return false;
			}
			else
			{
				// Check for existence of relationship already
				List relationships = primaryTable.getPrimaryKeyRelationships();
				for (int i = 0; i < relationships.size(); i++)
				{
					Relationship currentRelationship = (Relationship) relationships.get(i);
					if (currentRelationship.getForeignKeyTable().equals(foreignTable))
					{
						returnValue = false;
						break;
					}
				}
			}

		}
		return returnValue;

	}

	/**
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute()
	{
        // show our dialog
        RelationshipDialog sd = new RelationshipDialog(PlatformUI
                .getWorkbench().getActiveWorkbenchWindow()
                .getShell(), ImageStore.RELATIONSHIPSON);
        sd.create();
        sd.setTable1(primaryTable.getTableName());
        sd.setTable2(foreignTable.getTableName());
        for(int i = 0; i < primaryTable.getModelColumns().size(); i++)
        {
            Column c = (Column) primaryTable.getModelColumns().get(i);
            sd.addAColumnToTable1(c.getName(), c.isPrimaryKey(), c.isForeignKey());
        }
        for(int i = 0; i < foreignTable.getModelColumns().size(); i++)
        {
            Column c = (Column) foreignTable.getModelColumns().get(i);
            sd.addAColumnToTable2(c.getName(), c.isPrimaryKey(), c.isForeignKey());
        }
        int result = sd.open();
        if(result == RelationshipDialog.OK)
        {
            // get the results.
            // TODO: Investigate whether we really need more than one.
            String names = sd.getRelationshipName();
            relationship = new Relationship(names,foreignTable, sd.getForeignKey(), primaryTable, sd.getPrimaryKey());
        }
	}

	/**
	 * @return Returns the foreignTable.
	 */
	public Table getForeignTable()
	{
		return foreignTable;
	}

	/**
	 * @return Returns the primaryTable.
	 */
	public Table getPrimaryTable()
	{
		return primaryTable;
	}

	/**
	 * Returns the Relationship between the primary and foreign tables
	 * 
	 * @return the transistion
	 */
	public Relationship getRelationship()
	{
		return relationship;
	}

	/**
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo()
	{
		foreignTable.addForeignKeyRelationship(relationship);
		primaryTable.addPrimaryKeyRelationship(relationship);
	}

	/**
	 * @param foreignTable
	 *            The foreignTable to set.
	 */
	public void setForeignTable(Table foreignTable)
	{
		this.foreignTable = foreignTable;
	}

	/**
	 * @param primaryTable
	 *            The primaryTable to set.
	 */
	public void setPrimaryTable(Table primaryTable)
	{
		this.primaryTable = primaryTable;
	}

	/**
	 * @param relationship
	 *            The relationship to set.
	 */
	public void setRelationship(Relationship relationship)
	{
		this.relationship = relationship;
	}

	/**
	 * Undo version of command
	 */
	public void undo()
	{
		foreignTable.removeForeignKeyRelationship(relationship);
		primaryTable.removePrimaryKeyRelationship(relationship);
	}

}