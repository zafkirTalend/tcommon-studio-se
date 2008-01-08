// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core;

import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

/**
 * This class can check whether some specific plugins are loaded or not. <br/>
 * 
 */
public class PluginChecker {

    private static final String DOCUMENTATION_PLUGIN_ID = "org.talend.repository.documentation";

    private static final String SNIPPET_PLUGIN_ID = "org.talend.snippets";
    
    private static final String JOBLET_PLUGIN_ID = "org.talend.designer.joblet";

    /**
     * Check if specific plug-in is loaded.
     * 
     * @return isLoaded
     */
    public static boolean isPluginLoaded(String pluginID) {
        boolean isLoaded = true;
        Bundle bundle = Platform.getBundle(pluginID);
        if (bundle == null || (bundle != null && bundle.getState() == Bundle.UNINSTALLED)) {
            isLoaded = false;
        }
        return isLoaded;
    }

    /**
     * Checks if documentation plug-in is loaded.
     * 
     * @return isLoaded
     */
    public static boolean isDocumentationPluginLoaded() {
        return isPluginLoaded(DOCUMENTATION_PLUGIN_ID);
    }

    /**
     * Check if snippets plug-in is loaded.
     * 
     * @return isLoaded
     */
    public static boolean isSnippetsPluginLoaded() {
        return isPluginLoaded(SNIPPET_PLUGIN_ID);
    }

    /**
     * DOC tang Comment method "isJobLetPluginLoaded".
     * @return
     */
    public static boolean isJobLetPluginLoaded() {
        return isPluginLoaded(JOBLET_PLUGIN_ID);
    }

}
