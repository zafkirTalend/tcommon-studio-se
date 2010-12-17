// ============================================================================
//
// Copyright (C) 2006-2010 Talend Inc. - www.talend.com
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
        String productPath = getProductInstallPath();
        String pluginLocation = Platform.getBundle(pluginId).getLocation();
        String pluginRelativePath = "";
        if (pluginLocation.lastIndexOf(":") != -1) {
            pluginRelativePath = pluginLocation.substring(pluginLocation.lastIndexOf(":") + 1);
        } else {
            pluginRelativePath = pluginLocation;
        }
        pluginPath = productPath.concat(pluginRelativePath);
        return pluginPath;
    }

    /**
     * DOC ycbai Get the install path of product.
     * 
     * @return
     */
    public static String getProductInstallPath() {
        return convertUrlToString(Platform.getInstallLocation().getURL());
    }

    /**
     * DOC ycbai Convert url to local string.
     * 
     * @param url
     * @return
     */
    public static String convertUrlToString(URL url) {
        String str = null;
        try {
            str = FileLocator.toFileURL(url).getFile();
        } catch (IOException e) {
            ExceptionHandler.process(e);
        }
        return str;
    }

}
