// ============================================================================
//
// Copyright (C) 2006-2012 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.commons;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.talend.commons.debug.TalendDebugHandler;

/**
 * Activator for Code Generator.
 * 
 * $Id$
 * 
 */
public class CommonsPlugin extends Plugin {

    // The plug-in ID
    public static final String PLUGIN_ID = "org.talend.commons.runtime"; //$NON-NLS-1$

    // The shared instance
    private static CommonsPlugin plugin;

    private static boolean headless = false;

    private static boolean storeLibsInWorkspace = false;

    private static boolean isSameProjectLogonCommline = false;

    public static boolean isStoreLibsInWorkspace() {
        return storeLibsInWorkspace;
    }

    public static void setStoreLibsInWorkspace(boolean storeLibsInWorkspace) {
        CommonsPlugin.storeLibsInWorkspace = storeLibsInWorkspace;
    }

    /**
     * Default Constructor.
     */
    public CommonsPlugin() {
        plugin = this;
    }

    public static CommonsPlugin getDefault() {
        return plugin;
    }

    public static boolean isHeadless() {
        return headless;
    }

    public static void setHeadless(boolean headless) {
        CommonsPlugin.headless = headless;
    }

    public static boolean isDebugMode() {
        return ArrayUtils.contains(Platform.getApplicationArgs(), TalendDebugHandler.TALEND_DEBUG);
    }

    /**
     * Answer the file associated with name. This handles the case of running as a plugin and running standalone which
     * happens during testing.
     * 
     * @param filename
     * @return File
     */
    public static InputStream getFileInputStream(String filename) throws IOException {
        URL url = Platform.getBundle(PLUGIN_ID).getEntry(filename);
        return url != null ? url.openStream() : null;

    }

    public static boolean isSameProjectLogonCommline() {
        return isSameProjectLogonCommline;
    }

    public static void setSameProjectLogonCommline(boolean isSameProjectLogonCommline) {
        CommonsPlugin.isSameProjectLogonCommline = isSameProjectLogonCommline;
    }

}
