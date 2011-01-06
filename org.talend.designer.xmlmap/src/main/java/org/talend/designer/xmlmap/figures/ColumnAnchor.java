// ============================================================================
//
// Copyright (C) 2006-2010 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.designer.xmlmap.figures;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * wchen class global comment. Detailled comment
 */

public class ColumnAnchor extends ChopboxAnchor {

    private boolean isSource;

    public ColumnAnchor(IFigure owner, boolean isSource) {
        super(owner);
        this.isSource = isSource;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.draw2d.ChopboxAnchor#getLocation(org.eclipse.draw2d.geometry.Point)
     */
    @Override
    public Point getLocation(Point reference) {
        Rectangle r = Rectangle.SINGLETON;
        r.setBounds(getBox());
        r.translate(-1, -1);
        r.resize(1, 1);

        getOwner().translateToAbsolute(r);
        if (isSource) {
            int rightX = r.x + r.width;
            float centerY = r.y + 0.5f * r.height;

            if (r.isEmpty() || (reference.x == rightX && reference.y == (int) centerY))
                return new Point((int) rightX, (int) centerY); // This avoids
                                                               // divide-by-zero

            float dx = reference.x - rightX;
            float dy = reference.y - centerY;

            // r.width, r.height, dx, and dy are guaranteed to be non-zero.
            float scale = 0.5f / Math.max(Math.abs(dx) / r.width, Math.abs(dy) / r.height);

            dx *= scale;
            dy *= scale;
            rightX += dx;
            centerY += dy;

            return new Point(Math.round(rightX), Math.round(centerY));
        } else {

            int leftX = r.x;
            float centerY = r.y + 0.5f * r.height;

            if (r.isEmpty() || (reference.x == leftX && reference.y == centerY))
                return new Point(leftX, (int) centerY); // This avoids
                                                        // divide-by-zero

            float dx = reference.x - leftX;
            float dy = reference.y - centerY;

            // r.width, r.height, dx, and dy are guaranteed to be non-zero.
            float scale = 0.5f / Math.max(Math.abs(dx) / r.width, Math.abs(dy) / r.height);

            dx *= scale;
            dy *= scale;
            leftX += dx;
            centerY += dy;

            return new Point(Math.round(leftX), Math.round(centerY));
        }
    }
}
