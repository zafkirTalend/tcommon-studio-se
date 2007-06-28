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

import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.osgi.framework.Bundle;
import org.talend.core.CorePlugin;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.i18n.BrandingMessages;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 1 2006-09-29 17:06:40 +0000 (ven., 29 sept. 2006) nrousseau $
 * 
 */
public class BrandingService implements IBrandingService {

    private static IBrandingService singleton;

    public static IBrandingService getInstance() {
        if (singleton == null) {
            try {
                singleton = (IBrandingService) GlobalServiceRegister.getDefault().getService(IBrandingService.class);
            } catch (ClassCastException e) {
                e.printStackTrace();
                singleton = new BrandingService();
            } catch (RuntimeException ex) {
                singleton = new BrandingService();
            }
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

    public URL getLicenseFile() throws IOException {
        final Bundle b = Platform.getBundle(CorePlugin.PLUGIN_ID);
        final URL url = FileLocator.toFileURL(FileLocator.find(b, new Path("resources/license.txt"), null)); //$NON-NLS-1$
        return url;
    }
}
