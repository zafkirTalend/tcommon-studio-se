// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================

// Java & B�zier
// (C) 2002 Emmanuel Turquin
//
// Point.java
//
// This program is free software; you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation; either version 2 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

package org.talend.commons.ui.geometry;

import java.awt.Color;

/**
 * 
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id: Point.java 7048 2007-11-16 02:36:17Z nrousseau $
 * 
 */
public abstract class Point {

    boolean visible = true;

    Color color = Color.black;

    // isVisible
    public boolean isVisible() {
        return visible;
    }

    // setVisible
    public void setVisible(boolean b) {
        visible = b;
    }

    // getColor
    public Color getColor() {
        return color;
    }

    // setColor
    public void setColor(Color c) {
        color = c;
    }

}
