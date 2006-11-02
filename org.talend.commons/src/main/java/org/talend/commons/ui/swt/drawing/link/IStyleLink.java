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
import org.talend.commons.ui.swt.drawing.link.StyleLink.EDirection;


/**
 * DOC amaumont  class global comment. Detailled comment
 * <br/>
 *
 * $Id$
 *
 */
public interface IStyleLink {

    /**
     * Getter for direction.
     * @return the direction
     */
    public EDirection getDirection();

    /**
     * Sets the direction.
     * @param direction the direction to set
     */
    public void setDirection(EDirection direction);

    /**
     * Getter for backgroundColor.
     * @return the backgroundColor
     */
    public Color getBackgroundColor();

    /**
     * Sets the backgroundColor.
     * @param backgroundColor the backgroundColor to set
     */
    public void setBackgroundColor(Color backgroundColor);

    /**
     * Getter for foregroundColor.
     * @return the foregroundColor
     */
    public Color getForegroundColor();

    /**
     * Sets the foregroundColor.
     * @param foregroundColor the foregroundColor to set
     */
    public void setForegroundColor(Color foregroundColor);

    /**
     * Getter for tipPoint1.
     * @return the tipPoint1
     */
    public ITipLink getTipPoint1();

    /**
     * Sets the tipPoint1.
     * @param tipPoint1 the tipPoint1 to set
     */
    public void setTipPoint1(ITipLink tipPoint1);

    /**
     * Getter for tipPoint2.
     * @return the tipPoint2
     */
    public ITipLink getTipPoint2();

    /**
     * Sets the tipPoint2.
     * @param tipPoint2 the tipPoint2 to set
     */
    public void setTipPoint2(ITipLink tipPoint2);

    /**
     * Getter for drawableLink.
     * @return the drawableLink
     */
    public IDrawableLink getDrawableLink();

    /**
     * Sets the drawableLink.
     * @param drawableLink the drawableLink to set
     */
    public void setDrawableLink(IDrawableLink drawableLink);

    
    
}
