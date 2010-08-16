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
package org.talend.core.repository;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.talend.core.context.Context;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * $Id: RepositoryPlugin.java 38013 2010-03-05 14:21:59Z mhirt $
 * 
 */
public class CoreRepositoryPlugin extends AbstractUIPlugin {

    // The plug-in ID
    public static final String PLUGIN_ID = "org.talend.core.repository"; //$NON-NLS-1$

    // The shared instance
    private static CoreRepositoryPlugin plugin;

    /** Context. */
    private Context userContext;

    public CoreRepositoryPlugin() {
        plugin = this;
        userContext = new Context();
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

    public static CoreRepositoryPlugin getDefault() {
        return plugin;
    }

    /**
     * Getter for context.
     * 
     * @return the context
     */
    public Context getContext() {
        return this.userContext;
    }

    // public IDesignerCoreService getDesignerCoreService() {
    // return (IDesignerCoreService) GlobalServiceRegister.getDefault().getService(IDesignerCoreService.class);
    // }
    //
    // public IRepositoryService getRepositoryService() {
    // return (IRepositoryService) GlobalServiceRegister.getDefault().getService(IRepositoryService.class);
    // }
    //
    // public IRunProcessService getRunProcessService() {
    // return (IRunProcessService) GlobalServiceRegister.getDefault().getService(IRunProcessService.class);
    // }

}
