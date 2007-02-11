/*
 * Created on Jul 15, 2004
 */
package com.quantum.editors.graphical.model.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.quantum.editors.graphical.model.EntityRelationDiagram;
import com.quantum.editors.graphical.model.Table;

/**
 * Command to create a new table table
 * 
 * @author Phil Zoio
 */
public class TableAddCommand extends Command
{

	private EntityRelationDiagram diagram;
	private Table table;
//	private int index = -1;
    private Point location;

	/**
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute()
	{
        if(table.getName() == null)
        {
            this.table.setName("TABLE" + (diagram.getTables().size() + 1));
        }
		this.table.setDiagram(diagram);
        diagram.addTable(table);
        table.modifyBounds(new Rectangle(location.x, location.y, -1, -1));
	}

//	/**
//	 * Sets the index to the passed value
//	 * 
//	 * @param i
//	 *            the index
//	 */
//	public void setIndex(int i)
//	{
//		index = i;
//	}

	/**
	 * Sets the parent ActivityDiagram
	 * 
	 * @param sa
	 *            the parent
	 */
	public void setDiagram(EntityRelationDiagram diagram) {
        this.diagram = diagram;
    }

	/**
	 * Sets the Activity to create
	 * 
	 * @param table
	 *            the Activity to create
	 */
	public void setTable(Table table)
	{
		this.table = table;
	}
    
    public void setLocation(Point p)
    {
        this.location = p;
    }

	public void undo()
	{
		diagram.removeTable(table);
	}

}