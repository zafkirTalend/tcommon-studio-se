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

import org.apache.commons.collections.map.MultiKeyMap;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class ImageProvider {

    public static Image getImage(ImageDescriptor desc) {
        return desc.createImage();
    }

    private static MultiKeyMap cacheDescriptors = new MultiKeyMap();

    private static MultiKeyMap cacheImages = new MultiKeyMap();

    public static Image getImage(IImage image) {
        Image toReturn = (Image) cacheImages.get(image.getLocation(), image.getPath());
        if (toReturn == null) {
            ImageDescriptor desc = getImageDesc(image);
            toReturn = desc.createImage();
            cacheImages.put(image.getLocation(), image.getPath(), toReturn);
        }
        return toReturn;
    }

    public static ImageDescriptor getImageDesc(IImage image) {
        ImageDescriptor toReturn = (ImageDescriptor) cacheDescriptors.get(image.getLocation(), image.getPath());
        if (toReturn == null) {
            toReturn = ImageDescriptor.createFromFile(image.getLocation(), image.getPath());
            cacheDescriptors.put(image.getLocation(), image.getPath(), toReturn);
        }
        return toReturn;
    }

    public static String getImageCache() {
        return cacheImages.keySet().toString();
    }

    public static String getImageDescCache() {
        return cacheDescriptors.keySet().toString();
    }

}
