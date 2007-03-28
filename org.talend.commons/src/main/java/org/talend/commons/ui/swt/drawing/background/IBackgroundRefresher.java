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
package org.talend.commons.ui.swt.drawing.background;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public interface IBackgroundRefresher {

    public abstract void refreshBackground();

    /**
     * DOC amaumont Comment method "updateBackgroundWithLimiter".
     */
    public abstract void refreshBackgroundWithLimiter();

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

    public Point convertPointToCommonParentOrigin(Point point, Composite child);

    /**
     * Getter for antialiasActivated.
     * 
     * @return the antialiasActivated
     */
    public boolean isAntialiasAllowed();

}
