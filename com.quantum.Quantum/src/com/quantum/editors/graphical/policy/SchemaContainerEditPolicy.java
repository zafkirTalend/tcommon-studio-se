/*
 * Created on Jul 15, 2004
 */
package com.quantum.editors.graphical.policy;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;
import org.eclipse.gef.editpolicies.ContainerEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.GroupRequest;

import com.quantum.editors.graphical.model.EntityRelationDiagram;
import com.quantum.editors.graphical.model.Table;
import com.quantum.editors.graphical.model.commands.TableAddCommand;
import com.quantum.editors.graphical.parts.EntityRelationDiagramEditPart;

/**
 * Handles creation of new tables using drag and drop or point and click from the palette
 * @author Phil Zoio
 */
public class SchemaContainerEditPolicy extends ContainerEditPolicy
{

	/**
	 * @see org.eclipse.gef.editpolicies.ContainerEditPolicy#getAddCommand(org.eclipse.gef.requests.GroupRequest)
	 */
	protected Command getAddCommand(GroupRequest request)
	{
		return null;
	}

	/**
	 * @see ContainerEditPolicy#getCreateCommand(org.eclipse.gef.requests.CreateRequest)
	 */
	protected Command getCreateCommand(CreateRequest request)
	{

		Object newObject = request.getNewObject();
		if (!(newObject instanceof Table))
		{
			return null;
		}
		Point location = request.getLocation();
		EntityRelationDiagramEditPart schemaPart = (EntityRelationDiagramEditPart)getHost();
        schemaPart.getFigure().translateToRelative(location); // for adding in zoomed state
        EntityRelationDiagram diagram = schemaPart.getDiagram();
		Table table = (Table) newObject;
		TableAddCommand tableAddCommand = new TableAddCommand();
		tableAddCommand.setDiagram(diagram);
		tableAddCommand.setTable(table);
        tableAddCommand.setLocation(location);
		return tableAddCommand;
	}

	/**
	 * @see AbstractEditPolicy#getTargetEditPart(org.eclipse.gef.Request)
	 */
	public EditPart getTargetEditPart(Request request)
	{
		if (REQ_CREATE.equals(request.getType()))
			return getHost();
		if (REQ_ADD.equals(request.getType()))
			return getHost();
		if (REQ_MOVE.equals(request.getType()))
			return getHost();
		return super.getTargetEditPart(request);
	}

}