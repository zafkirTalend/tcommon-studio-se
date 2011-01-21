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
package org.talend.metadata.managment.ui;

import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class MetadataManagmentUiPlugin extends AbstractUIPlugin {

    // The plug-in ID
    public static final String PLUGIN_ID = "org.talend.metadata.management.ui"; //$NON-NLS-1$

    // The data profiling perspective id.
    protected static final String DATA_PROFILING_PERSPECTIVE_ID = "org.talend.dataprofiler.DataProfilingPerspective";

    // The shared instance
    private static MetadataManagmentUiPlugin plugin;

    /**
     * The constructor
     */
    public MetadataManagmentUiPlugin() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     */
    public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
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

    /**
     * Returns the shared instance
     * 
     * @return the shared instance
     */
    public static MetadataManagmentUiPlugin getDefault() {
        return plugin;
    }

    /**
     * DOC bZhou Comment method "isDataProfilePerspectiveSelected".
     * 
     * @return
     */
    public boolean isDataProfilePerspectiveSelected() {
        IPerspectiveDescriptor curPerspective = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getPerspective();
        return curPerspective.getId().equals(DATA_PROFILING_PERSPECTIVE_ID);
    }
}
