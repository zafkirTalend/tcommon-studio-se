// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.commons.utils.image;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.graphics.Transform;
import org.talend.commons.exception.ExceptionHandler;

/**
 * Image utilities. <br/>
 * 
 * $Id: ImageUtils.java 7038 2007-11-15 14:05:48Z plegall $
 * 
 */
public class ImageUtils {

    public enum ICON_SIZE {
        ICON_32(32),
        ICON_24(24),
        ICON_16(16),

        ;

        private int size;

        ICON_SIZE(int size) {
            this.size = size;
        }

        public int getSize() {
            return this.size;
        }

        public String getComment() {
            return this.size + "x" + this.size; //$NON-NLS-1$
        }

    }

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

    public static Image scale(Image image, ICON_SIZE size) {
        if (image != null && size != null) {
            ImageData imageData = image.getImageData().scaledTo(size.getSize(), size.getSize());
            return ImageDescriptor.createFromImageData(imageData).createImage();
        }
        return image;
    }

    public static ImageDescriptor scale(ImageDescriptor imageDes, ICON_SIZE size) {
        if (imageDes != null) {
            if (!checkSize(imageDes, size)) {
                ImageData imageData = imageDes.getImageData().scaledTo(size.getSize(), size.getSize());
                return ImageDescriptor.createFromImageData(imageData);
            }
        }
        return imageDes;
    }

    public static ImageDescriptor createImageFromData(byte[] data) {
        if (data != null) {
            ByteArrayInputStream bais = new ByteArrayInputStream(data);
            return ImageDescriptor.createFromImage(new Image(null, bais));
        }
        return null;
    }

    public static byte[] saveImageToData(ImageDescriptor imageDes) {
        if (imageDes != null) {
            if (imageDes != null) {
                Image img = imageDes.createImage();
                if (img != null) {
                    ByteArrayOutputStream baos = null;
                    try {
                        baos = new ByteArrayOutputStream();
                        ImageLoader imageLoader = new ImageLoader();
                        imageLoader.data = new ImageData[] { imageDes.getImageData() };
                        imageLoader.save(baos, img.type);
                        byte[] imageByteArray = baos.toByteArray();
                        return imageByteArray;
                    } catch (Exception e) {
                        ExceptionHandler.process(e);
                    } finally {
                        if (baos != null) {
                            try {
                                baos.close();
                            } catch (IOException e) {
                                ExceptionHandler.process(e);
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public static boolean checkSize(Image image, ICON_SIZE iconSize) {
        if (iconSize == null) {
            return true; // don't check the size
        }
        if (image != null) {
            if (image.getImageData().width == iconSize.getSize() && image.getImageData().height == iconSize.getSize()) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkSize(ImageDescriptor imageDes, ICON_SIZE iconSize) {
        if (iconSize == null) {
            return true; // don't check the size
        }
        if (imageDes != null) {
            if (imageDes.getImageData().width == iconSize.getSize() && imageDes.getImageData().height == iconSize.getSize()) {
                return true;
            }
        }
        return false;
    }
}
