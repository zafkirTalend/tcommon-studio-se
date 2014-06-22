// ============================================================================
//
// Copyright (C) 2006-2011 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.utils;

import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;

/**
 * DOC ycbai class global comment. Util class about plugin.
 */
public class PluginUtil {

    /**
     * DOC ycbai Get the absolute install path of plugin.
     * 
     * @param pluginId
     */
    public static String getPluginInstallPath(String pluginId) {
        String pluginPath = null;
        try {
            URL url = FileLocator.resolve(Platform.getBundle(pluginId).getEntry("/")); //$NON-NLS-1$
            if (url == null)
                return null;
            pluginPath = url.getFile();
            String protoPath = url.getProtocol();
            if (pluginPath.startsWith("file:")) { //$NON-NLS-1$
                pluginPath = pluginPath.substring(pluginPath.indexOf("file:") + 6); //$NON-NLS-1$
            }
            if ("jar".equals(protoPath) && pluginPath.endsWith("!/")) { //$NON-NLS-1$ //$NON-NLS-2$
                pluginPath = pluginPath.substring(0, pluginPath.lastIndexOf("!/")); //$NON-NLS-1$
            }
        } catch (IOException e) {
            ExceptionHandler.process(e);
        }
        return pluginPath;
    }

    /**
     * DOC ycbai Get the install path of product.
     * 
     * @return
     */
    public static String getProductInstallPath() {
        return (Platform.getInstallLocation().getURL()).getFile();
    }

}
