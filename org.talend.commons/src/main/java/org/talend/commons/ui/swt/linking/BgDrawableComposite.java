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
package org.talend.commons.ui.swt.linking;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.talend.commons.ui.swt.drawing.background.IBgDrawableComposite;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public abstract class BgDrawableComposite implements IBgDrawableComposite {

    private Composite commonParent;

    private Point offsetPoint = new Point(0, 0);

    /**
     * DOC amaumont DrawableBackground constructor comment.
     * 
     * @param commonParent
     */
    public BgDrawableComposite(Composite commonParent) {
        this.commonParent = commonParent;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.ui.swt.drawing.background.IDrawableComposite#drawBackground(org.eclipse.swt.graphics.GC)
     */
    public abstract void drawBackground(GC gc);

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.ui.swt.drawing.background.IDrawableComposite#getComposite()
     */
    public Composite getBgDrawableComposite() {
        return this.commonParent;
    }

    public void setOffset(Point offsetPoint) {
        this.offsetPoint = offsetPoint;
    }

    public Point getOffset() {
        return this.offsetPoint;
    }

}
