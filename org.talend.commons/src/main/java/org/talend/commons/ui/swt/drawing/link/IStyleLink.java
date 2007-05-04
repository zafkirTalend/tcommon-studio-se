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
import org.talend.commons.ui.swt.drawing.link.StyleLink.EDirection;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public interface IStyleLink {

    /**
     * Getter for direction.
     * 
     * @return the direction
     */
    public EDirection getDirection();

    /**
     * Sets the direction.
     * 
     * @param direction the direction to set
     */
    public void setDirection(EDirection direction);

    /**
     * Getter for backgroundColor.
     * 
     * @return the backgroundColor
     */
    public Color getBackgroundColor();

    /**
     * Sets the backgroundColor.
     * 
     * @param backgroundColor the backgroundColor to set
     */
    public void setBackgroundColor(Color backgroundColor);

    /**
     * Getter for foregroundColor.
     * 
     * @return the foregroundColor
     */
    public Color getForegroundColor();

    /**
     * Sets the foregroundColor.
     * 
     * @param foregroundColor the foregroundColor to set
     */
    public void setForegroundColor(Color foregroundColor);

    /**
     * Getter for extremity1.
     * 
     * @return the extremity1
     */
    public IExtremityDrawableLink getExtremity1();

    /**
     * Sets the extremity1.
     * 
     * @param extremity1 the extremity1 to set
     */
    public void setExtremity1(IExtremityDrawableLink extremity1);

    /**
     * Getter for extremity2.
     * 
     * @return the extremity2
     */
    public IExtremityDrawableLink getExtremity2();

    /**
     * Sets the extremity2.
     * 
     * @param extremity2 the extremity2 to set
     */
    public void setExtremity2(IExtremityDrawableLink extremity2);

    /**
     * Getter for drawableLink.
     * 
     * @return the drawableLink
     */
    public IDrawableLink getDrawableLink();

    /**
     * Sets the drawableLink.
     * 
     * @param drawableLink the drawableLink to set
     */
    public void setDrawableLink(IDrawableLink drawableLink);

    /**
     * Getter for lineCap.
     * 
     * @return the lineCap
     */
    public int getLineCap();

    /**
     * Sets the lineCap.
     * 
     * @param lineCap the lineCap to set
     */
    public void setLineCap(int lineCap);

    /**
     * Getter for lineDash.
     * 
     * @return the lineDash
     */
    public int[] getLineDash();

    /**
     * Sets the lineDash.
     * 
     * @param lineDash the lineDash to set
     */
    public void setLineDash(int[] lineDash);

    /**
     * Getter for lineJoin.
     * 
     * @return the lineJoin
     */
    public int getLineJoin();

    /**
     * Sets the lineJoin.
     * 
     * @param lineJoin the lineJoin to set
     */
    public void setLineJoin(int lineJoin);

    /**
     * Getter for lineStyle.
     * 
     * @return the lineStyle
     */
    public int getLineStyle();

    /**
     * Sets the lineStyle.
     * 
     * @param lineStyle the lineStyle to set
     */
    public void setLineStyle(int lineStyle);

    /**
     * Getter for lineWidth.
     * 
     * @return the lineWidth
     */
    public int getLineWidth();

    /**
     * Sets the lineWidth.
     * 
     * @param lineWidth the lineWidth to set
     */
    public void setLineWidth(int lineWidth);

    /**
     * DOC amaumont Comment method "apply".
     * 
     * @param gc
     */
    public void apply(GC gc);

}
