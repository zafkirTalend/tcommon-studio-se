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
package org.talend.commons.utils.image;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.graphics.Transform;

import com.sun.imageio.plugins.common.ImageUtil;


/**
 * Image utilities.
 * <br/>
 *
 * $Id$
 *
 */
public class ImageUtils {

    /**
     * Save an image in a file.
     * @param image
     * @param file 
     * @param format SWT.IMAGE_BMP, SWT.IMAGE_GIF, etc.
     */
    public static void save(Image image, String filePath, int format) {
        ImageLoader loader = new ImageLoader();
        loader.data = new ImageData[] { image.getImageData() };
        loader.save(filePath, format);
    }


    
    /**
     * Scale image with provided percent value.
     */
    public static Image scale(Image image, int percent) {
        Transform transform = new Transform(image.getDevice());
        float scale = (float) percent / 100f;
        int newWidth = (int) (scale * image.getImageData().width);
        int newHeight = (int) (scale * image.getImageData().height);
        transform.scale(scale, scale);
        GC gc = new GC(image);
        gc.setTransform(transform);
        gc.drawImage(image, 0, 0);
        
        Image newImage = new Image(image.getDevice(), newWidth, newHeight);
        gc.copyArea(newImage, 0, 0);
        return newImage;
    }
    
    
}
