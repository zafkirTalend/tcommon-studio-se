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

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class ExtremityWestArrow extends AbstractExtremityDrawableLink {

    /**
     * DOC amaumont TipWestArrow constructor comment.
     * 
     * @param styleLink
     */
    public ExtremityWestArrow(IStyleLink styleLink) {
        super(styleLink);
    }

    /**
     * DOC amaumont TipWestArrow constructor comment.
     * 
     * @param styleLink
     * @param xOffset
     * @param yOffset
     */
    public ExtremityWestArrow(IStyleLink styleLink, int xOffset, int yOffset) {
        super(styleLink, xOffset, yOffset);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.ui.swt.drawing.link.ITipLink#draw(org.eclipse.swt.graphics.GC,
     * org.eclipse.swt.graphics.Point)
     */
    public void draw(GC gc, Point point) {
        Color previousBackground = gc.getBackground();
        gc.setBackground(styleLink.getForegroundColor());
        gc.fillPolygon(new int[] { point.x + xOffset, point.y + yOffset - 1 - ExtremityEastArrow.HEIGHT_ARROW / 2,
                point.x + xOffset - ExtremityEastArrow.WIDTH_ARROW, point.y + yOffset - 1,
                point.x + xOffset - ExtremityEastArrow.WIDTH_ARROW, point.y + yOffset, point.x + xOffset,
                point.y + yOffset + ExtremityEastArrow.HEIGHT_ARROW / 2, });
        gc.setBackground(previousBackground);
    }

    public Point getSize() {
        return new Point(ExtremityEastArrow.WIDTH_ARROW, ExtremityEastArrow.HEIGHT_ARROW);
    }

}
