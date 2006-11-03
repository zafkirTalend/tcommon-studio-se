// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
//
// ============================================================================
package org.talend.commons.ui.swt.drawing.link;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.talend.commons.ui.geometry.Curve2DBezier;
import org.talend.commons.ui.geometry.Point2D;
import org.talend.commons.ui.geometry.Point2DList;

/**
 * Draw an horizontal Bezier link. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class HorizontalBezierLink extends AbstractDrawableLink {

    private Curve2DBezier curve;

    private static final int CONNECTOR_WIDTH_DEFAULT = 70;

    private static final int DISTANCE_REFERENCE = 20000;

    /**
     * DOC amaumont BezierLink constructor comment.
     */
    public HorizontalBezierLink(StyleLink style) {
        super(style);
        connectorWidth = CONNECTOR_WIDTH_DEFAULT;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.ui.swt.drawing.link.ICAbstractGraphicalLink#draw(org.eclipse.swt.graphics.GC)
     */
    @Override
    public void drawBody(GC gc) {

        Point2DList pl = null;

        double distance;
        // int spaceBetweenPoints = 1;

        distance = new java.awt.Point(point1.x, point1.y).distance(point2.x, point2.y);

        if (curve == null) {
            curve = new Curve2DBezier();
            pl = new Point2DList();
            curve.setPointList(pl);
            for (int i = 0; i < 5; i++) {
                pl.add(new Point2D());
            }
        } else {
            pl = (Point2DList) curve.getPointList();
        }

        curve.setSubdiv((int) (DISTANCE_REFERENCE / distance));
        double coefDist_connectorWidth = distance / 1500 * connectorWidth;

        int point1x_halfTableWidth1_connWidth = point1.x + connectorWidth;
        int point2x_halfTableWidth2_connWidth = point2.x - connectorWidth;

        ((Point2D) pl.get(0)).setLocation(point1.x, point1.y);
        ((Point2D) pl.get(1)).setLocation(point1x_halfTableWidth1_connWidth + coefDist_connectorWidth, point1.y);

        ((Point2D) pl.get(2))
                .setLocation(
                        (point1x_halfTableWidth1_connWidth + coefDist_connectorWidth + (point2x_halfTableWidth2_connWidth - coefDist_connectorWidth)) / 2,
                        (point1.y + (point2.y)) / 2);

        ((Point2D) pl.get(3)).setLocation(point2x_halfTableWidth2_connWidth - coefDist_connectorWidth, point2.y);
        ((Point2D) pl.get(4)).setLocation(point2.x, point2.y);

        int heightCalculate = 0;
        if (boundsOfCalculate == null) {
            heightCalculate = gc.getClipping().height;
        } else {
            heightCalculate = boundsOfCalculate.height;
        }

        curve.draw(gc, pl, pl, 0, heightCalculate);
    }

}
