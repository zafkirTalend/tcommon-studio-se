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
package org.talend.core.ui.branding;

import org.eclipse.jface.resource.ImageDescriptor;
import org.talend.core.CorePlugin;
import org.talend.core.i18n.BrandingMessages;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 1 2006-09-29 17:06:40 +0000 (ven., 29 sept. 2006) nrousseau $
 * 
 */
public class BrandingService {

    private static BrandingService singleton;

    public static BrandingService getInstance() {
        if (singleton == null) {
            singleton = new BrandingService();
        }
        return singleton;
    }

    public String getFullProductName() {
        return BrandingMessages.getString("productfullname"); //$NON-NLS-1$
    }

    public String getShortProductName() {
        return BrandingMessages.getString("productshortname"); //$NON-NLS-1$
    }

    public String getCorporationName() {
        return BrandingMessages.getString("corporationname"); //$NON-NLS-1$
    }

    public ImageDescriptor getLoginVImage() {
        return CorePlugin.imageDescriptorFromPlugin(CorePlugin.PLUGIN_ID, BrandingMessages.getString("loginimageleft")); //$NON-NLS-1$
    }

    public ImageDescriptor getLoginHImage() {
        return CorePlugin.imageDescriptorFromPlugin(CorePlugin.PLUGIN_ID, BrandingMessages.getString("loginimagehigh")); //$NON-NLS-1$
    }
}
