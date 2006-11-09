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

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public abstract class AbstractExtremityDrawableLink implements IExtremityDrawableLink {

    protected IStyleLink styleLink;

    protected int xOffset;
    
    protected int yOffset;
    
    /**
     * DOC amaumont AbstractTipLink constructor comment.
     * 
     * @param styleLink
     */
    public AbstractExtremityDrawableLink(IStyleLink styleLink) {
        this.styleLink = styleLink;
    }

    /**
     * DOC amaumont AbstractTipLink constructor comment.
     * @param styleLink
     * @param xOffset
     * @param yOffset
     */
    public AbstractExtremityDrawableLink(IStyleLink styleLink, int xOffset, int yOffset) {
        super();
        this.styleLink = styleLink;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }


    /**
     * Return the current foregroundColor if defined
     * , else return styleLink foreground if defined
     * , else return gc foreground.
     * 
     * @return the foregroundColor
     */
    public Color getForegroundColor(GC gc) {
        if (styleLink.getForegroundColor() != null) {
            return styleLink.getForegroundColor();
        } else {
            return gc.getForeground();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.ui.swt.drawing.link.ITipLink#draw(org.eclipse.swt.graphics.GC)
     */
    public abstract void draw(GC gc, Point point);

    /**
     * Getter for styleLink.
     * 
     * @return the styleLink
     */
    public IStyleLink getStyleLink() {
        return this.styleLink;
    }

    /**
     * Sets the styleLink.
     * 
     * @param styleLink the styleLink to set
     */
    public void setStyleLink(IStyleLink styleLink) {
        this.styleLink = styleLink;
    }

    
    /**
     * Getter for xOffset.
     * @return the xOffset
     */
    public int getXOffset() {
        return this.xOffset;
    }

    
    /**
     * Sets the xOffset.
     * @param offset the xOffset to set
     */
    public void setXOffset(int offset) {
        this.xOffset = offset;
    }

    
    /**
     * Getter for yOffset.
     * @return the yOffset
     */
    public int getYOffset() {
        return this.yOffset;
    }

    
    /**
     * Sets the yOffset.
     * @param offset the yOffset to set
     */
    public void setYOffset(int offset) {
        this.yOffset = offset;
    }

    
    
}
