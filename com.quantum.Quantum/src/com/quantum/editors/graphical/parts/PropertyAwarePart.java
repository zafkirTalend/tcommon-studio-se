/*
 * Created on Jul 15, 2004
 */
package com.quantum.editors.graphical.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import com.quantum.editors.graphical.model.EntityRelationDiagram;
import com.quantum.editors.graphical.model.PropertyAwareObject;
import com.quantum.editors.graphical.model.Relationship;
import com.quantum.editors.graphical.model.Table;


/**
 * An abstract EditPart implementation which is property aware and responds to
 * PropertyChangeEvents fired from the model
 * @author Phil Zoio
 */
public abstract class PropertyAwarePart extends AbstractGraphicalEditPart implements PropertyChangeListener
{

	/**
	 * @see org.eclipse.gef.EditPart#activate()
	 */
	public void activate()
	{
		super.activate();
		PropertyAwareObject propertyAwareObject = (PropertyAwareObject) getModel();
		propertyAwareObject.addPropertyChangeListener(this);
	}

	/**
	 * @see org.eclipse.gef.EditPart#deactivate()
	 */
	public void deactivate()
	{
		super.deactivate();
		PropertyAwareObject propertyAwareObject = (PropertyAwareObject) getModel();
		propertyAwareObject.removePropertyChangeListener(this);
	}

	/**
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent evt)
	{

		String property = evt.getPropertyName();

		if (PropertyAwareObject.CHILD.equals(property))
		{
			handleChildChange(evt);
		}
		if (PropertyAwareObject.REORDER.equals(property))
		{
			handleReorderChange(evt);
		}
		else if (PropertyAwareObject.OUTPUT.equals(property))
		{
			handleOutputChange(evt);
		}
		else if (PropertyAwareObject.INPUT.equals(property))
		{
			handleInputChange(evt);
		}
		else if (PropertyAwareObject.NAME.equals(property))
		{
			commitNameChange(evt);
		}
        else if (PropertyAwareObject.ALIAS.equals(property))
        {
            commitAliasChange(evt);
        }
		else if (PropertyAwareObject.BOUNDS.equals(property))
		{
			handleBoundsChange(evt);
		}
		else if (PropertyAwareObject.LAYOUT.equals(property))
		{
			handleLayoutChange(evt);
		}
        else if (PropertyAwareObject.RELATIONSHIPNAMES.equals(property))
        {
            handleRelationshipNameChange(evt);
        }
        else if (PropertyAwareObject.MARKEDFOROUTPUT.equals(property))
        {
            handleMarkedForOutputChange(evt);
        }
//        else if (PropertyAwareObject.SHOWCOLUMNS.equals(property)){
//            handleShowColumnsChange(evt);
//        }
        else if (PropertyAwareObject.COLUMNIMAGE.equals(property)) {
            handleColumnImageChange(evt);
        }
        else if (PropertyAwareObject.SIZE_TYPE_PRECISION.equals(property)) {
            handleColumnSizeChange(evt);
        }
        else if("connected".equals(property)){
            System.out.println("Connected!");
        }
            
		//we want direct edit name changes to update immediately
		//not use the Graph animation, if automatic layout is being used
		if (PropertyAwareObject.NAME.equals(property))
		{
			GraphicalEditPart graphicalEditPart = (GraphicalEditPart) (getViewer().getContents());
			IFigure partFigure = graphicalEditPart.getFigure();
			partFigure.getUpdateManager().performUpdate();
		}

	}

    protected void handleColumnSizeChange(PropertyChangeEvent evt) {
        commitSizeChange(evt);
    }

    protected void commitSizeChange(PropertyChangeEvent evt) {
    }

    protected void handleColumnImageChange(PropertyChangeEvent evt) {
    }

    /**
	 * Called when change to one of the inputs occurs
	 */
	private void handleInputChange(PropertyChangeEvent evt)
	{

		//this works but is not efficient
//		refreshTargetConnections();

		//a more efficient implementation should either remove or add the
		//relevant target connection
		//using the removeTargetConnection(ConnectionEditPart connection) or
		//addTargetConnection(ConnectionEditPart connection, int index)

		Object newValue = evt.getNewValue();
		Object oldValue = evt.getOldValue();

		if (!((oldValue != null) ^ (newValue != null)))
		{
			throw new IllegalStateException("Exactly one of old or new values must be non-null for INPUT event");
		}

		if (newValue != null)
		{
			//add new connection
			ConnectionEditPart editPart = createOrFindConnection(newValue);
			int modelIndex = getModelTargetConnections().indexOf(newValue);
			addTargetConnection(editPart, modelIndex);

		}
		else
		{

			//remove connection
			List children = getTargetConnections();

			ConnectionEditPart partToRemove = null;
			for (Iterator iter = children.iterator(); iter.hasNext();)
			{
				ConnectionEditPart part = (ConnectionEditPart) iter.next();
				if (part.getModel() == oldValue)
				{
					partToRemove = part;
					break;
				}
			}

			if (partToRemove != null)
				removeTargetConnection(partToRemove);
		}
		
		getContentPane().revalidate();

	}

	/**
	 * Called when change to one of the outputs occurs
	 */
	private void handleOutputChange(PropertyChangeEvent evt)
	{

		//this works but is not efficient
		//refreshSourceConnections();

		// a more efficient implementation should either remove or add the
		// relevant target connect
		//using the removeSourceConnection(ConnectionEditPart connection) or
		//addSourceConnection(ConnectionEditPart connection, int index)

		Object newValue = evt.getNewValue();
		Object oldValue = evt.getOldValue();

		if (!((oldValue != null) ^ (newValue != null)))
		{
			throw new IllegalStateException("Exactly one of old or new values must be non-null for INPUT event");
		}

		if (newValue != null)
		{
			//add new connection
			ConnectionEditPart editPart = createOrFindConnection(newValue);
			int modelIndex = getModelSourceConnections().indexOf(newValue);
			addSourceConnection(editPart, modelIndex);
		}
		else
		{

			//remove connection
			List children = getSourceConnections();

			ConnectionEditPart partToRemove = null;
			for (Iterator iter = children.iterator(); iter.hasNext();)
			{
				ConnectionEditPart part = (ConnectionEditPart) iter.next();
				if (part.getModel() == oldValue)
				{
					partToRemove = part;
					break;
				}
			}

			if (partToRemove != null)
				removeSourceConnection(partToRemove);
		}
		
		getContentPane().revalidate();

	}

	/**
	 * called when child added or removed
	 */
	protected void handleChildChange(PropertyChangeEvent evt)
	{

		//we could do this but it is not very efficient
		//refreshChildren();

		Object newValue = evt.getNewValue();
		Object oldValue = evt.getOldValue();

		if (!((oldValue != null) ^ (newValue != null)))
		{
			throw new IllegalStateException("Exactly one of old or new values must be non-null for CHILD event");
		}

		if (newValue != null)
		{
			//add new child
			EditPart editPart = createChild(newValue);
			int modelIndex = getModelChildren().indexOf(newValue);
			addChild(editPart, modelIndex);

		}
		else
		{

			List children = getChildren();

			EditPart partToRemove = null;
			for (Iterator iter = children.iterator(); iter.hasNext();)
			{
				EditPart part = (EditPart) iter.next();
				if (part.getModel() == oldValue)
				{
					partToRemove = part;
					break;
				}
			}

			if (partToRemove != null)
				removeChild(partToRemove);
		}
		
		//getContentPane().revalidate();

	}

	/**
	 * Called when columns are re-ordered within
	 */
	protected void handleReorderChange(PropertyChangeEvent evt)
	{
		refreshChildren();
		refreshVisuals();
	}

    protected void handleRelationshipNameChange(PropertyChangeEvent evt)
    {
        // this is only relevant for connections
        EntityRelationDiagram diagram = (EntityRelationDiagram)evt.getSource();
        List tables = diagram.getTables();
        for(int i=0; i<tables.size(); i++)
        {
            Table t = (Table)tables.get(i);
            List relationships = t.getForeignKeyRelationships(); 
            for(int j = 0; j<relationships.size(); j++)
            {
                Relationship r = (Relationship) relationships.get(j);
                RelationshipPart editPart = (RelationshipPart) createOrFindConnection(r);
                editPart.updateText(((Boolean)evt.getNewValue()).booleanValue());
            }
        }
    }

//    protected void handleShowColumnsChange(PropertyChangeEvent evt)
//    {
//         List kids = getChildren();
//        for(int i=0; i<kids.size(); i++)
//        {
//            TablePart tp = (TablePart)kids.get(i);
//            tp.showColumns(((Boolean)evt.getNewValue()).booleanValue());
//        }
//    }

    /**
	 * @param subclass
	 *            decides what to do if layout property event is fired
	 */
	protected void handleLayoutChange(PropertyChangeEvent evt)
	{
	}

	/**
	 * handles change in bounds, to be overridden by subclass
	 */
	protected void handleBoundsChange(PropertyChangeEvent evt)
	{
	}

	/**
	 * Handles change in name - to be overridden by subclasses
	 */
	protected void commitNameChange(PropertyChangeEvent evt)
	{
	}

    protected void commitAliasChange(PropertyChangeEvent evt)
    {
    }

    protected void handleMarkedForOutputChange(PropertyChangeEvent evt)
    {
    }
}