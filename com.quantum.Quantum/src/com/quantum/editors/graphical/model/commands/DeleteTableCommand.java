/*
 * Created on Jul 17, 2004
 */
package com.quantum.editors.graphical.model.commands;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.quantum.editors.graphical.model.EntityRelationDiagram;
import com.quantum.editors.graphical.model.Relationship;
import com.quantum.editors.graphical.model.Table;

/**
 * Command to delete tables from the schema
 * 
 * @author Phil Zoio
 */
public class DeleteTableCommand extends Command
{

	private Table table;
	private EntityRelationDiagram diagram;
	private int index = -1;
	private List foreignKeyRelationships = new ArrayList();
	private List primaryKeyRelationships = new ArrayList();
	private Rectangle bounds;

	private void deleteRelationships(Table t)
	{

		this.foreignKeyRelationships.addAll(t.getForeignKeyRelationships());

		//for all relationships where current table is foreign key
		for (int i = 0; i < foreignKeyRelationships.size(); i++)
		{
			Relationship r = (Relationship) foreignKeyRelationships.get(i);
			r.getPrimaryKeyTable().removePrimaryKeyRelationship(r);
			t.removeForeignKeyRelationship(r);
		}

		//for all relationships where current table is primary key
		this.primaryKeyRelationships.addAll(t.getPrimaryKeyRelationships());
		for (int i = 0; i < primaryKeyRelationships.size(); i++)
		{
			Relationship r = (Relationship) primaryKeyRelationships.get(i);
			r.getForeignKeyTable().removeForeignKeyRelationship(r);
			t.removePrimaryKeyRelationship(r);
		}
	}

	/**
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute()
	{
		primExecute();
	}

	/**
	 * Invokes the execution of this command.
	 */
	protected void primExecute()
	{
		deleteRelationships(table);
		index = diagram.getTables().indexOf(table);
        diagram.removeTable(table);
	}

	/**
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo()
	{
		primExecute();
	}

	private void restoreRelationships()
	{
		for (int i = 0; i < foreignKeyRelationships.size(); i++)
		{
			Relationship r = (Relationship) foreignKeyRelationships.get(i);
			r.getForeignKeyTable().addForeignKeyRelationship(r);
			r.getPrimaryKeyTable().addPrimaryKeyRelationship(r);
		}
		foreignKeyRelationships.clear();
		for (int i = 0; i < primaryKeyRelationships.size(); i++)
		{
			Relationship r = (Relationship) primaryKeyRelationships.get(i);
			r.getForeignKeyTable().addForeignKeyRelationship(r);
			r.getPrimaryKeyTable().addPrimaryKeyRelationship(r);
		}
		primaryKeyRelationships.clear();
	}

	/**
	 * Sets the child to the passed Table
	 * 
	 * @param a
	 *            the child
	 */
	public void setTable(Table a)
	{
		table = a;
	}

	/**
	 * Sets the parent to the passed Schema
	 * 
	 * @param sa
	 *            the parent
	 */
	public void setSchema(EntityRelationDiagram diagram)
	{
        this.diagram = diagram;
	}

	/**
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo()
	{
        diagram.addTable(table, index);
		restoreRelationships();
		table.modifyBounds(bounds);
	}

	/**
	 * Sets the original bounds for the table so that these can be restored
	 */
	public void setOriginalBounds(Rectangle bounds)
	{
		this.bounds = bounds;
	}

}

