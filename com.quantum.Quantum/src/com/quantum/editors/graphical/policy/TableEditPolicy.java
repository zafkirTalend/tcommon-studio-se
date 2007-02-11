/*
 * Created on Jul 15, 2004
 */
package com.quantum.editors.graphical.policy;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.quantum.editors.graphical.model.EntityRelationDiagram;
import com.quantum.editors.graphical.model.Table;
import com.quantum.editors.graphical.model.commands.DeleteTableCommand;
import com.quantum.editors.graphical.parts.TablePart;

/**
 * Handles deletion of tables
 * @author Phil Zoio
 */
public class TableEditPolicy extends ComponentEditPolicy
{

	protected Command createDeleteCommand(GroupRequest request)
	{
		TablePart tablePart = (TablePart) getHost();
		Rectangle bounds = tablePart.getFigure().getBounds().getCopy();
		EntityRelationDiagram parent = (EntityRelationDiagram) (tablePart.getParent().getModel());
		DeleteTableCommand deleteCmd = new DeleteTableCommand();
		deleteCmd.setSchema(parent);
		deleteCmd.setTable((Table) (tablePart.getModel()));
		deleteCmd.setOriginalBounds(bounds);
		return deleteCmd;
	}
	
}