/*
 * Created on Jul 13, 2004
 */
package com.quantum.editors.graphical.parts.connectors;

import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;

import com.quantum.editors.graphical.model.Column;
import com.quantum.editors.graphical.model.Relationship;
import com.quantum.editors.graphical.parts.ColumnPart;
import com.quantum.editors.graphical.parts.TablePart;

/**
 * Right anchor for joining connections between figures
 * 
 * @author jhvdv
 */
public class RightAnchor extends AbstractConnectionAnchor
{
    ConnectionEditPart connection;

	public RightAnchor(IFigure source, ConnectionEditPart connection)
	{
		super(source);
        this.connection = connection;
	}

	public Point getLocation(Point reference)
	{
		Rectangle r = getOwner().getBounds().getCopy();
		getOwner().translateToAbsolute(r);
        int off = 0;
        // what are we linking
        Relationship relation = (Relationship) connection.getModel();
        // what is the column we are linking to
        String foreign = relation.getForeignKeyName();
        if(foreign == null)
        {
            return r.getTopLeft().translate(0, off);
        }
        // can we access the coordinates
        TablePart t = (TablePart) connection.getSource();
        for(int i=0; i<t.getChildren().size(); i++)
        {
            ColumnPart cp = (ColumnPart) t.getChildren().get(i);
            Column c = (Column) cp.getModel();
            if(c.getName().equalsIgnoreCase(foreign))
            {
                Rectangle cpr = cp.getFigure().getBounds().getCopy();
                cp.getFigure().translateToAbsolute(cpr); // essential for zoom support
                off = cpr.height/2 + cpr.y - r.y;
                if (r.contains(reference) || r.getLeft().x > reference.x)
                    return r.getTopLeft().translate(0, off);
                else
                    return r.getTopRight().translate(0, off);
            }
        }
        return r.getTopRight().translate(0, off);
	}
}