package com.quantum.editors.graphical.parts;

import java.beans.PropertyChangeEvent;
import java.util.EventObject;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.CommandStackListener;

import com.quantum.editors.graphical.figures.EntityRelationFigure;
import com.quantum.editors.graphical.figures.TableFigure;
import com.quantum.editors.graphical.layout.DelegatingLayoutManager;
import com.quantum.editors.graphical.layout.GraphAnimation;
import com.quantum.editors.graphical.layout.GraphLayoutManager;
import com.quantum.editors.graphical.model.EntityRelationDiagram;
import com.quantum.editors.graphical.model.Table;
import com.quantum.editors.graphical.policy.SchemaContainerEditPolicy;
import com.quantum.editors.graphical.policy.SchemaXYLayoutPolicy;

/**
 * Edit part for Schema object, and uses a SchemaDiagram figure as
 * the container for all graphical objects
 * 
 * @author Phil Zoio
 */
public class EntityRelationDiagramEditPart extends PropertyAwarePart
{

	CommandStackListener stackListener = new CommandStackListener()
	{

		public void commandStackChanged(EventObject event)
		{
			if (delegatingLayoutManager.getActiveLayoutManager() instanceof GraphLayoutManager)
			{
				if (!GraphAnimation.captureLayout(getFigure()))
					return;
				while (GraphAnimation.step())
					getFigure().getUpdateManager().performUpdate();
				GraphAnimation.end();
			}
			else
			{
				getFigure().getUpdateManager().performUpdate();
			}
		}
	};
	private DelegatingLayoutManager delegatingLayoutManager;

	/**
	 * Adds this EditPart as a command stack listener, which can be used to call
	 * performUpdate() when it changes
	 */
	public void activate()
	{
		super.activate();
		getViewer().getEditDomain().getCommandStack().addCommandStackListener(stackListener);
	}

	/**
	 * Removes this EditPart as a command stack listener
	 */
	public void deactivate()
	{
		getViewer().getEditDomain().getCommandStack().removeCommandStackListener(stackListener);
		super.deactivate();
	}

	protected IFigure createFigure()
	{
		Figure f = new EntityRelationFigure();

//        // Create the static router for the connection layer
//        ConnectionLayer connLayer = (ConnectionLayer)getLayer(LayerConstants.CONNECTION_LAYER);
//        connLayer.setConnectionRouter(new BendpointConnectionRouter());

		delegatingLayoutManager = new DelegatingLayoutManager(this);
		f.setLayoutManager(delegatingLayoutManager);
		return f;
	}

	public EntityRelationDiagram getDiagram()
	{
		return (EntityRelationDiagram) getModel();
	}

	/**
	 * @return the children Model objects as a new ArrayList
	 */
	protected List getModelChildren()
	{
		return getDiagram().getTables();
	}

	/**
	 * @see org.eclipse.gef.editparts.AbstractEditPart#isSelectable()
	 */
	public boolean isSelectable()
	{
		return false;
	}

	/**
	 * Creates EditPolicy objects for the EditPart. The LAYOUT_ROLE policy is
	 * left to the delegating layout manager
	 */
	protected void createEditPolicies()
	{
		installEditPolicy(EditPolicy.CONTAINER_ROLE, new SchemaContainerEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new SchemaXYLayoutPolicy());
	}

	/**
	 * Updates the table bounds in the model so that the same bounds can be
	 * restored after saving
	 * 
	 * @return whether the procedure execute successfully without any omissions.
	 *         The latter occurs if any TableFigure has no bounds set for any of
	 *         the Table model objects
	 */
	public boolean setTableModelBounds()
	{

		List tableParts = getChildren();

		for (Iterator iter = tableParts.iterator(); iter.hasNext();)
		{
			TablePart tablePart = (TablePart) iter.next();
			TableFigure tableFigure = (TableFigure) tablePart.getFigure();

			//if we don't find a node for one of the children then we should
			// continue
			if (tableFigure == null)
				continue;

			Rectangle bounds = tableFigure.getBounds().getCopy();
			Table table = tablePart.getTable();
			table.setBounds(bounds);
		}

		return true;

	}

	/**
	 * Updates the bounds of the table figure (without invoking any event
	 * handling), and sets layout constraint data
	 * 
	 * @return whether the procedure execute successfully without any omissions.
	 *         The latter occurs if any Table objects have no bounds set or if
	 *         no figure is available for the TablePart
	 */
	public boolean setTableFigureBounds(boolean updateConstraint)
	{

		List tableParts = getChildren();

		for (Iterator iter = tableParts.iterator(); iter.hasNext();)
		{
			TablePart tablePart = (TablePart) iter.next();
			Table table = tablePart.getTable();

			//now check whether we can find an entry in the tableToNodesMap
			Rectangle bounds = table.getBounds();
			if (bounds == null)
			{
				return false;
			}
			else
			{
				TableFigure tableFigure = (TableFigure) tablePart.getFigure();
				if (tableFigure == null)
				{
					return false;
				}
				else
				{
					if (updateConstraint)
					{
						//pass the constraint information to the xy layout
						//setting the width and height so that the preferred size will be applied
						delegatingLayoutManager.setXYLayoutConstraint(tableFigure, new Rectangle(bounds.x, bounds.y, -1, -1));
					}
				}
			}

		}
		return true;

	}

	/**
	 * Passes on to the delegating layout manager that the layout type has
	 * changed. The delegating layout manager will then decide whether to
	 * delegate layout to the XY or Graph layout
	 */
	protected void handleLayoutChange(PropertyChangeEvent evt)
	{
		getFigure().setLayoutManager(delegatingLayoutManager);
	}

	/**
	 * Sets layout constraint only if XYLayout is active
	 */
	public void setLayoutConstraint(EditPart child, IFigure childFigure, Object constraint)
	{
			super.setLayoutConstraint(child, childFigure, constraint);
	}

	/**
	 * Passes on to the delegating layout manager that the layout type has
	 * changed. The delegating layout manager will then decide whether to
	 * delegate layout to the XY or Graph layout
	 */
	protected void handleChildChange(PropertyChangeEvent evt)
	{
		super.handleChildChange(evt);
	}

}