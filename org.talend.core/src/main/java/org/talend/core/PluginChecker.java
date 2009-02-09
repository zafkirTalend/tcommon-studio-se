// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
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
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.Bundle;

/**
 * This class can check whether some specific plugins are loaded or not. <br/>
 * 
 */
public class PluginChecker {

    private static final String DOCUMENTATION_PLUGIN_ID = "org.talend.repository.documentation"; //$NON-NLS-1$

    private static final String SNIPPET_PLUGIN_ID = "org.talend.snippets"; //$NON-NLS-1$

    private static final String JOBLET_PLUGIN_ID = "org.talend.designer.joblet"; //$NON-NLS-1$

    private static final String WSDL_PLUGIN_ID = "org.talend.wsdl.advancement"; //$NON-NLS-1$

    private static final String CDC_PLUGIN_ID = "org.talend.designer.cdc"; //$NON-NLS-1$

    private static final String SAP_WZIARD_PLUGIN_ID = "org.talend.repository.sapwizard"; //$NON-NLS-1$

    private static final String EBCDIC_PLUGIN_ID = "org.talend.repository.ebcdic"; //$NON-NLS-1$

    private static final String REF_PROJECT_PLUGIN_ID = "org.talend.repository.refproject"; //$NON-NLS-1$

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

    public static boolean isTIS() {
        return isJobLetPluginLoaded();
    }

    public static Plugin getWSDLPlugin() {
        return Platform.getPlugin(WSDL_PLUGIN_ID);
    }

    public static boolean isRefProjectLoaded() {
        return isPluginLoaded(REF_PROJECT_PLUGIN_ID);
    }

    public static boolean isWSDLPluginLoaded() {
        return isPluginLoaded(WSDL_PLUGIN_ID);
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
     * 
     * @return
     */
    public static boolean isJobLetPluginLoaded() {
        return isPluginLoaded(JOBLET_PLUGIN_ID);
    }

    /**
     * DOC qzhang Comment method "containJoblet".
     * 
     * @return
     */
    public static boolean isCDCPluginLoaded() {
        return Platform.getBundle(CDC_PLUGIN_ID) != null;
    }

    public static boolean isSAPWizardPluginLoaded() {
        return isPluginLoaded(SAP_WZIARD_PLUGIN_ID);
    }

    public static boolean isEBCDICPluginLoaded() {
        return isPluginLoaded(EBCDIC_PLUGIN_ID);
    }
}
