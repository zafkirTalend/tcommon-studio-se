/*
 * Created on Jul 13, 2004
 */
package com.quantum.editors.graphical.parts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.ManhattanConnectionRouter;
import org.eclipse.draw2d.MidpointLocator;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

import com.quantum.ImageStore;
import com.quantum.editors.graphical.model.Column;
import com.quantum.editors.graphical.model.Relationship;
import com.quantum.editors.graphical.policy.RelationshipEditPolicy;
import com.quantum.ui.dialog.RelationshipDialog;


/**
 * Represents the editable primary key/foreign key relationship
 * @author Phil Zoio
 */
public class RelationshipPart extends PropertyAwareConnectionPart
{

    Label label;
    Font f2 = null;
    
	/**
	 * @see org.eclipse.gef.EditPart#activate()
	 */
	public void activate() {
		super.activate();
	}
	
	
	/**
	 * @see org.eclipse.gef.EditPart#activate()
	 */
	public void deactivate() {
		super.deactivate();
	}
	
    public void performRequest(Request request) {
        if (request.getType() == RequestConstants.REQ_OPEN) {
            // show our dialog
            RelationshipDialog sd = new RelationshipDialog(PlatformUI
                    .getWorkbench().getActiveWorkbenchWindow()
                    .getShell(), ImageStore.RELATIONSHIPSON);
            sd.create();
            Relationship r = (Relationship) getModel();
            sd.setTable1(r.getPrimaryKeyTable().getName());
            sd.setTable2(r.getForeignKeyTable().getName());
            for(int i = 0; i < r.getPrimaryKeyTable().getModelColumns().size(); i++)
            {
                Column c = (Column) r.getPrimaryKeyTable().getModelColumns().get(i);
                sd.addAColumnToTable1(c.getName(), c.isPrimaryKey(), c.isForeignKey());
            }
            for(int i = 0; i < r.getForeignKeyTable().getModelColumns().size(); i++)
            {
                Column c = (Column) r.getForeignKeyTable().getModelColumns().get(i);
                sd.addAColumnToTable2(c.getName(), c.isPrimaryKey(), c.isForeignKey());
            }
            String[] p = new String[1];
            p[0] = r.getPrimaryKeyName();
            String[] f = new String[1];
            f[0] = r.getForeignKeyName();
            sd.addRelation(r.getName(), r.getPrimaryKeyTable().getName(), p, r.getForeignKeyTable().getName(), f);
            int result = sd.open();
            if(result == RelationshipDialog.OK)
            {
                // get the results.
                // TODO: Investigate whether we really need more than one.
                String names = sd.getRelationshipName();
                r.getForeignKeyTable().removeForeignKeyRelationship(r);
                r.getPrimaryKeyTable().removePrimaryKeyRelationship(r);
                r = new Relationship(names, r.getForeignKeyTable(), sd.getForeignKey(), r.getPrimaryKeyTable(), sd.getPrimaryKey());
            }

        }
    }
	
	/**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
	protected void createEditPolicies()
	{
		installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE, new ConnectionEndpointEditPolicy());
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new RelationshipEditPolicy());
//        installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE, new ConnectionBendpointEditPolicy());
	}

	/**
	 * @see org.eclipse.gef.editparts.AbstractConnectionEditPart#createFigure()
	 */
	protected IFigure createFigure()
	{
		PolylineConnection conn = (PolylineConnection) super.createFigure();
//        conn.setConnectionRouter(new FanRouter());
        conn.setConnectionRouter(new ManhattanConnectionRouter());
//        conn.setConnectionRouter(new BendpointConnectionRouter());
		conn.setTargetDecoration(new PolygonDecoration());
        label = new Label();
        if(((Relationship)getModel()).getDiagram().areRelationshipNamesShown() == true)
        {
            label.setText(((Relationship)getModel()).getName());
        }else{
            label.setText("");
        }
        label.setOpaque(true);
        MidpointLocator locator = new MidpointLocator(conn, 1);
        conn.add(label, locator);
		return conn;
	}
    /**
     * Reset the layout constraint, and revalidate the content pane
     */

    public void updateText(boolean show)
    {
        if (show) {
            label.setText(((Relationship) getModel()).getName());
        } else {
            label.setText("");
        }
    }
    
    protected void refreshBendpoints() {
        List bendpoints = ((Relationship)getModel()).getBendpoints();
        List constraint = new ArrayList();
        
         for (int i = 0; i < bendpoints.size(); i++) {
            constraint.add(new AbsoluteBendpoint((Point) bendpoints.get(i)));
        }
        
        getConnectionFigure().setRoutingConstraint(constraint);
    }
    

	/**
	 * Sets the width of the line when selected
	 */
	public void setSelected(int value)
	{
		super.setSelected(value);
		if (value != EditPart.SELECTED_NONE){
			((PolylineConnection) getFigure()).setLineWidth(2);
            if(f2 != null)f2.dispose();
            Font f = label.getFont();
            FontData[] fd = f.getFontData();
            for(int i=0; i<fd.length; i++)
            {
                fd[i].setStyle(SWT.ITALIC);
            }
            Font f2 = new Font(Display.getDefault(), fd);
            label.setFont(f2);
        }else{
			((PolylineConnection) getFigure()).setLineWidth(1);
            if(f2 != null)f2.dispose();
            Font f = label.getFont();
            FontData[] fd = f.getFontData();
            for(int i=0; i<fd.length; i++)
            {
                fd[i].setStyle(SWT.NONE);
            }
            Font f2 = new Font(Display.getDefault(), fd);
            label.setFont(f2);
        }
	}
}