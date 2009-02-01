// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend �C www.talend.com
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
package org.talend.resources;

import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;
import org.talend.commons.exception.RuntimeExceptionHandler;
import org.talend.resource.IResourceService;

/**
 * yzhang class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 1 2006-09-29 17:06:40Z nrousseau $
 * 
 */
public class ResourceService implements IResourceService {

    private final static String RESOURCE_LOCATION = "org.talend.resources"; //$NON-NLS-1$

    private final static String JAVA_LIBRARIE = "resources/demoprojects/java/TALENDDEMOSJAVA/lib/java"; //$NON-NLS-1$

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.resource.IResourceService#getJavaLibraryPath()
     */
    public String getJavaLibraryPath() {
        // TODO Auto-generated method stub

        Bundle b = Platform.getBundle(RESOURCE_LOCATION);
        URL url = null;
        try {
            url = FileLocator.toFileURL(FileLocator.find(b, new Path(JAVA_LIBRARIE), null));
        } catch (IOException e) {
            RuntimeExceptionHandler.process(e);
        }

        if (url != null) {
            return url.getFile();
        } else {
            return null;
        }

    }
}
