// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006-2007 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
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

import org.eclipse.swt.graphics.GC;

/**
 * Draw a vertical link with horizontal connector and rounded corners between connectors and vertical line. <br/>
 * 
 * $Id$
 * 
 */
public class VerticalRoundedCornerLink extends AbstractDrawableLink {

    private static final int CONNECTOR_WIDTH_DEFAULT = 8;

    private static final int RADIUS_DEFAULT = 6;

    private int radius;

    public VerticalRoundedCornerLink(IStyleLink style) {
        super(style);
        connectorWidth = CONNECTOR_WIDTH_DEFAULT;
        this.radius = RADIUS_DEFAULT;
    }

    public VerticalRoundedCornerLink(IStyleLink style, int radius) {
        super(style);
        connectorWidth = CONNECTOR_WIDTH_DEFAULT;
        this.radius = radius;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.ui.swt.drawing.link.AbstractDrawableLink#drawBody(org.eclipse.swt.graphics.GC)
     */
    @Override
    protected void drawBody(GC gc) {
        boolean point1Above = point1.y > point2.y;

        int mult = point1Above ? 1 : -1;

        int keyLinksCounter = 0;

        int xOffset = 4 * keyLinksCounter + 1;

        gc.drawLine(point1.x, point1.y, point1.x - connectorWidth - xOffset, point1.y);

        gc.drawArc(point1.x - connectorWidth - radius - xOffset, point1.y - (point1Above ? 2 * radius : 0), 2 * radius, 2 * radius,
                point1Above ? 180 : 90, 90);

        // // in1 pr
        gc.drawLine(point1.x - connectorWidth - radius - xOffset, point1.y - mult * radius, point2.x - connectorWidth - radius - xOffset,
                point2.y + mult * radius);

        gc.drawArc(point2.x - connectorWidth - radius - xOffset, point2.y - (point1Above ? 0 : 2 * radius), 2 * radius, 2 * radius,
                point1Above ? 90 : 180, 90);

        // // connector pr (in)
        gc.drawLine(point2.x - connectorWidth - xOffset, point2.y, point2.x, point2.y);

        keyLinksCounter++;

    }

}
