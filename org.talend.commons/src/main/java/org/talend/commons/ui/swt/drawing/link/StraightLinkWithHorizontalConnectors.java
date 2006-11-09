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
public class StraightLinkWithHorizontalConnectors extends AbstractDrawableLink {

    private static final int CONNECTOR_WIDTH_DEFAULT = 20;

    /**
     * DOC amaumont BezierLink constructor comment.
     */
    public StraightLinkWithHorizontalConnectors(StyleLink style) {
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

        gc.drawPolyline(new int[] { point1.x, point1.y,
                point1.x + connectorWidth, point1.y,
                point2.x - connectorWidth, point2.y, point2.x,
                point2.y });
    }

}
