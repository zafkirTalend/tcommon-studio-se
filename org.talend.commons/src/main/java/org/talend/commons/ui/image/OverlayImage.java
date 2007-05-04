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
package org.talend.commons.ui.image;

import org.eclipse.jface.resource.CompositeImageDescriptor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 1 2006-09-29 17:06:40 +0000 (ven., 29 sept. 2006) nrousseau $
 * 
 */
public class OverlayImage extends CompositeImageDescriptor {

    /**
     * DOC smallet OverlayImage class global comment. Detailled comment
     * 
     * $Id$
     * 
     */
    public enum EPosition {
        TOP_RIGHT(false, true),
        TOP_LEFT(true, true),
        BOTTOM_RIGHT(false, false),
        BOTTOM_LEFT(true, false);

        private boolean left;

        private boolean top;

        private EPosition(boolean left, boolean top) {
            this.left = left;
            this.top = top;
        }

        /**
         * Getter for left.
         * 
         * @return the left
         */
        public boolean isLeft() {
            return this.left;
        }

        /**
         * Getter for top.
         * 
         * @return the top
         */
        public boolean isTop() {
            return this.top;
        }

    }

    private ImageDescriptor mOverlay;

    private Image mimage;

    private boolean mleft;

    private boolean mtop;

    public OverlayImage(Image baseImage, ImageDescriptor overlay, boolean left, boolean top) {
        mimage = baseImage;
        mOverlay = overlay;
        mleft = left;
        mtop = top;
    }

    public OverlayImage(Image baseImage, ImageDescriptor overlay, EPosition position) {
        this(baseImage, overlay, position.isLeft(), position.isTop());
    }

    protected void drawCompositeImage(int width, int height) {
        drawImage(mimage.getImageData(), 0, 0);
        if (mOverlay != null) {
            ImageData id = mOverlay.getImageData();
            int ox, oy;
            if (mleft) {
                ox = 0;
            } else {
                ox = width - id.width;
            }
            if (mtop) {
                oy = 0;
            } else {
                oy = height - id.height;
            }
            if (id != null) {
                drawImage(id, ox, oy);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.resource.CompositeImageDescriptor#getSize()
     */
    protected Point getSize() {
        return new Point(16, 16);
    }
}
