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
package org.talend.core.ui.images;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.talend.commons.ui.image.ImageProvider;
import org.talend.commons.ui.image.OverlayImage;
import org.talend.commons.ui.image.OverlayImage.EPosition;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * TODO SML Rename in OverlayImageProvider
 * 
 * $Id: talend.epf 1 2006-09-29 17:06:40 +0000 (ven., 29 sept. 2006) nrousseau $
 * 
 */
public class BusinessImageProvider {

    public static ImageDescriptor getImageWithNew(Image source) {
        ImageDescriptor img = ImageProvider.getImageDesc(ECoreImage.NEW_OVERLAY);
        EPosition position = EPosition.TOP_RIGHT;
        OverlayImage overlayImage = new OverlayImage(source, img, position);
        return overlayImage;
    }
}
