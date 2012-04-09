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
package org.talend.repository;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.talend.core.model.repository.RepositoryManager;

/**
 * DOC ggu class global comment. Detailled comment
 */
public class RepositoryViewPlugin extends AbstractUIPlugin {

    // The plug-in ID
    public static final String PLUGIN_ID = "org.talend.repository.view"; //$NON-NLS-1$

    // The shared instance
    private static RepositoryViewPlugin plugin;

    public RepositoryViewPlugin() {
        plugin = this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     */
    public void start(BundleContext context) throws Exception {
        super.start(context);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
     */
    public void stop(BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
    }

    public static RepositoryViewPlugin getDefault() {
        return plugin;
    }

    public String[] getPreferenceValues(String key) {
        String allValues = getDefault().getPreferenceStore().getString(key);
        if (allValues == null || "".equals(allValues)) {
            return new String[0];
        }
        String[] splits = allValues.split(RepositoryManager.ITEM_SEPARATOR);
        return splits;
    }

}
