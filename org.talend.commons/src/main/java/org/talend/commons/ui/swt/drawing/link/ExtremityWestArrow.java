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

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;


/**
 * DOC amaumont  class global comment. Detailled comment
 * <br/>
 *
 * $Id$
 *
 */
public class ExtremityWestArrow extends AbstractExtremityDrawableLink {

    /**
     * DOC amaumont TipWestArrow constructor comment.
     * @param styleLink
     */
    public ExtremityWestArrow(IStyleLink styleLink) {
        super(styleLink);
    }

    
    
    /**
     * DOC amaumont TipWestArrow constructor comment.
     * @param styleLink
     * @param xOffset
     * @param yOffset
     */
    public ExtremityWestArrow(IStyleLink styleLink, int xOffset, int yOffset) {
        super(styleLink, xOffset, yOffset);
        // TODO Auto-generated constructor stub
    }



    /* (non-Javadoc)
     * @see org.talend.commons.ui.swt.drawing.link.ITipLink#draw(org.eclipse.swt.graphics.GC, org.eclipse.swt.graphics.Point)
     */
    public void draw(GC gc, Point point) {

        gc.setBackground(styleLink.getForegroundColor());
        gc.fillPolygon(
                new int[] {
                        point.x + xOffset, point.y + yOffset - 1 - ExtremityEastArrow.HEIGHT_ARROW / 2, 
                        point.x + xOffset - ExtremityEastArrow.WIDTH_ARROW, point.y + yOffset - 1,
                        point.x + xOffset - ExtremityEastArrow.WIDTH_ARROW, point.y + yOffset ,
                        point.x + xOffset, point.y + yOffset + ExtremityEastArrow.HEIGHT_ARROW / 2,
                }
        );

//        Image image = ExtremityEastArrow.getImage(gc, getForegroundColor(gc));
//        ImageData src = image.getImageData();
//        RGB[] rgbs = src.palette.getRGBs();
//        ImageData dest = new ImageData(src.width, src.height, src.depth, new PaletteData(rgbs));
//        int transparentPixel = dest.palette.getPixel(rgbs[0]);
//        dest.transparentPixel = transparentPixel;
//
//        /* rotate by rearranging the pixels */
//        for (int i = 0; i < src.width; i++) {
//            for (int j = 0; j < src.height; j++) {
//                int pixel = src.getPixel(i, j);
//                dest.setPixel(src.width - 1 - i, j, pixel);
//            }
//        }
//        Image westArrow = new Image(gc.getDevice(), dest);
//        gc.drawImage(westArrow, point.x - ExtremityEastArrow.WIDTH_ARROW + xOffset, point.y - ExtremityEastArrow.HEIGHT_ARROW / 2 + yOffset);
//        westArrow.dispose();
        
    }

    public Point getSize() {
        return new Point(ExtremityEastArrow.WIDTH_ARROW, ExtremityEastArrow.HEIGHT_ARROW);
    }

}
