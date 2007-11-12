// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the  agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//   
// ============================================================================
package org.talend.commons.utils.image;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.graphics.Transform;

/**
 * Image utilities. <br/>
 * 
 * $Id$
 * 
 */
public class ImageUtils {

    /**
     * Save an image in a file.
     * 
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
