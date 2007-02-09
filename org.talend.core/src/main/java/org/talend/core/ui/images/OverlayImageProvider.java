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

import java.util.Arrays;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.talend.commons.ui.image.EImage;
import org.talend.commons.ui.image.IImage;
import org.talend.commons.ui.image.ImageProvider;
import org.talend.commons.ui.image.OverlayImage;
import org.talend.commons.ui.image.OverlayImage.EPosition;
import org.talend.repository.model.ERepositoryStatus;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 1 2006-09-29 17:06:40 +0000 (ven., 29 sept. 2006) nrousseau $
 * 
 */
public class OverlayImageProvider {

    public static ImageDescriptor getImageWithNew(Image source) {
        ImageDescriptor img = ImageProvider.getImageDesc(ECoreImage.NEW_OVERLAY);
        EPosition position = EPosition.TOP_RIGHT;
        OverlayImage overlayImage = new OverlayImage(source, img, position);
        return overlayImage;
    }

    public static Image getImageWithDocExt(String extension) {
        Image source = ImageProvider.getImage(ECoreImage.DOCUMENTATION_SINGLE_ICON);
        if (extension != null) {
            ImageDescriptor img = null;
            String ext = extension.toLowerCase();
            if (Arrays.asList(new String[] { "doc", "dot" }).contains(ext)) { //$NON-NLS-1$ //$NON-NLS-2$
                img = ImageProvider.getImageDesc(ECoreImage.DOC_WORD_OVERLAY);
            } else if (Arrays.asList(new String[] { "xls", "xlt" }).contains(ext)) { //$NON-NLS-1$ //$NON-NLS-2$
                img = ImageProvider.getImageDesc(ECoreImage.DOC_EXCEL_OVERLAY);
            } else if (Arrays.asList(new String[] { "ppt", "pps", "pot" }).contains(ext)) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                img = ImageProvider.getImageDesc(ECoreImage.DOC_PPT_OVERLAY);
            } else if (Arrays.asList(new String[] { "pdf" }).contains(ext)) { //$NON-NLS-1$
                img = ImageProvider.getImageDesc(ECoreImage.DOC_PDF_OVERLAY);
            } else if (Arrays.asList(new String[] { "gif", "jpg", "jpeg", "bmp", "gif", "png" }).contains(ext)) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
            }
            if (img == null) {
                return source;
            } else {
                return new OverlayImage(source, img, EPosition.BOTTOM_RIGHT).createImage();
            }
        }
        return source;
    }

    public static ImageDescriptor getImageWithStatus(Image source, ERepositoryStatus status) {
        IImage statusOverlay;
        switch (status) {
        case NEW:
            statusOverlay = ECoreImage.NEW_OVERLAY;
            break;
        case LOCK_BY_OTHER:
            statusOverlay = ECoreImage.LOCKED_OTHER_OVERLAY;
            break;
        case LOCK_BY_USER:
            statusOverlay = ECoreImage.LOCKED_USER_OVERLAY;
            break;
        default:
            statusOverlay = EImage.EMPTY;
            break;
        }
        ImageDescriptor img = ImageProvider.getImageDesc(statusOverlay);
        EPosition position = EPosition.BOTTOM_RIGHT;
        OverlayImage overlayImage = new OverlayImage(source, img, position);
        return overlayImage;
    }
}
