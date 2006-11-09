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

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class ExtremityEastArrow extends AbstractExtremityDrawableLink {

    public static final int WIDTH_IMAGE_ARROW = 5;
    public static final int HEIGHT_IMAGE_ARROW = 10;
    public static final int WIDTH_ARROW = 5;
    public static final int HEIGHT_ARROW = 10;
    public static final int FOREGROUND_INDEX_PALETTE_COLOR = 1;

    /**
     * DOC amaumont TipArrow constructor comment.
     */
    public ExtremityEastArrow(IStyleLink styleLink) {
        super(styleLink);
    }

    
    
    /**
     * DOC amaumont TipEastArrow constructor comment.
     * @param styleLink
     * @param xOffset
     * @param yOffset
     */
    public ExtremityEastArrow(IStyleLink styleLink, int xOffset, int yOffset) {
        super(styleLink, xOffset, yOffset);
    }



    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.ui.swt.drawing.link.AbstractTipLink#draw(org.eclipse.swt.graphics.GC)
     */
    @Override
    public void draw(GC gc, Point point) {
//        Image image = getImage(gc, getForegroundColor(gc));
//        gc.drawImage(image, point.x + xOffset, point.y + yOffset - HEIGHT_ARROW / 2);
//        image.dispose();
        gc.setBackground(styleLink.getForegroundColor());
        gc.fillPolygon(
                new int[] {
                        point.x + xOffset, point.y + yOffset - 1 - HEIGHT_ARROW / 2, 
                        point.x + xOffset + WIDTH_ARROW, point.y + yOffset - 1,
                        point.x + xOffset + WIDTH_ARROW, point.y + yOffset ,
                        point.x + xOffset, point.y + yOffset + HEIGHT_ARROW / 2,
                }
        );

    }

//    public static Image getImage(GC gc, Color foregroundColor) {
//
//        RGB transparentColor = new RGB(1, 1, 1);
//        PaletteData paletteData = new PaletteData(new RGB[] { transparentColor, foregroundColor.getRGB(), });
//        ImageData imageData = new ImageData(WIDTH_IMAGE_ARROW, HEIGHT_IMAGE_ARROW, 1, paletteData);
//
//        int transparentPixel = imageData.palette.getPixel(transparentColor);
//        imageData.transparentPixel = transparentPixel;
//
//        for (int i = 0; i < HEIGHT_ARROW / 2; i++) {
//            for (int j = i; j < HEIGHT_ARROW / 2; j++) {
//                imageData.setPixel(i, j, FOREGROUND_INDEX_PALETTE_COLOR);
//                imageData.setPixel(i, HEIGHT_ARROW - j - 1, FOREGROUND_INDEX_PALETTE_COLOR);
//            }
//        }
//        return new Image(gc.getDevice(), imageData);
//    }
//
    public Point getSize() {
        return new Point(WIDTH_ARROW, HEIGHT_ARROW);
    }
}
