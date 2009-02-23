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

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.talend.core.context.Context;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.language.LanguageManager;
import org.talend.core.model.general.ILibrariesService;
import org.talend.core.model.migration.IMigrationToolService;
import org.talend.designer.business.diagram.custom.IDiagramModelService;
import org.talend.designer.codegen.ICodeGeneratorService;
import org.talend.designer.components.IComponentsLocalProviderService;
import org.talend.designer.core.IDesignerCoreService;
import org.talend.designer.mapper.IDesignerMapperService;
import org.talend.designer.runprocess.IRunProcessService;
import org.talend.rcp.IRcpService;
import org.talend.repository.model.IProxyRepositoryFactory;
import org.talend.repository.model.IRepositoryLocalProviderService;
import org.talend.repository.model.IRepositoryService;
import org.talend.resource.IResourceService;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class CorePlugin extends AbstractUIPlugin {

    // The plug-in ID
    public static final String PLUGIN_ID = "org.talend.core"; //$NON-NLS-1$

    public static final String PROJECT_LANGUAGE_TYPE = "PROJECT_LANGUAGE_TYPE"; //$NON-NLS-1$

    private static Logger logger = Logger.getLogger(CorePlugin.class);

    // The shared instance
    private static CorePlugin plugin;

    /** Context. */
    private final Context context;

    public CorePlugin() {
        plugin = this;
        context = new Context();

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     */
    @Override
    public void start(BundleContext contextP) throws Exception {
        super.start(contextP);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public void stop(BundleContext contextP) throws Exception {
        try {
            ECodeLanguage lan = LanguageManager.getCurrentLanguage();
            getPluginPreferences().setValue(PROJECT_LANGUAGE_TYPE, lan.getName());
            plugin = null;
        } catch (RuntimeException e) {
            logger.log(Priority.DEBUG, e.getMessage(), e);
        }
        super.stop(contextP);
    }

    public static CorePlugin getDefault() {
        return plugin;
    }

    /**
     * Getter for context.
     * 
     * @return the context
     */
    public static Context getContext() {
        return getDefault().context;
    }

    /**
     * Returns an image descriptor for the image file at the given plug-in relative path.
     * 
     * @param path the path
     * @return the image descriptor
     */
    public static ImageDescriptor getImageDescriptor(String path) {
        return imageDescriptorFromPlugin(PLUGIN_ID, path);
    }

    public IProxyRepositoryFactory getProxyRepositoryFactory() {
        IRepositoryService service = getRepositoryService();
        return service.getProxyRepositoryFactory();
    }

    /**
     * DOC get a implement of RunProcessService.
     * 
     * @return a implement of RunProcessService
     */
    public IRunProcessService getRunProcessService() {
        IService service = GlobalServiceRegister.getDefault().getService(IRunProcessService.class);
        return (IRunProcessService) service;
    }

    public IDesignerCoreService getDesignerCoreService() {
        IService service = GlobalServiceRegister.getDefault().getService(IDesignerCoreService.class);
        return (IDesignerCoreService) service;
    }

    public ILibrariesService getLibrariesService() {
        return (ILibrariesService) GlobalServiceRegister.getDefault().getService(ILibrariesService.class);
    }

    public IRepositoryService getRepositoryService() {
        IService service = GlobalServiceRegister.getDefault().getService(IRepositoryService.class);
        return (IRepositoryService) service;
    }

    public IRepositoryLocalProviderService getRepositoryLocalProviderService() {
        return (IRepositoryLocalProviderService) GlobalServiceRegister.getDefault().getService(
                IRepositoryLocalProviderService.class);
    }

    public IComponentsLocalProviderService getComponentsLocalProviderService() {
        return (IComponentsLocalProviderService) GlobalServiceRegister.getDefault().getService(
                IComponentsLocalProviderService.class);
    }

    public ICodeGeneratorService getCodeGeneratorService() {
        return (ICodeGeneratorService) GlobalServiceRegister.getDefault().getService(ICodeGeneratorService.class);
    }

    public IMigrationToolService getMigrationToolService() {
        return (IMigrationToolService) GlobalServiceRegister.getDefault().getService(IMigrationToolService.class);
    }

    public IDiagramModelService getDiagramModelService() {
        return (IDiagramModelService) GlobalServiceRegister.getDefault().getService(IDiagramModelService.class);
    }

    public IResourceService getResourceService() {
        return (IResourceService) GlobalServiceRegister.getDefault().getService(IResourceService.class);
    }

    public IRcpService getRcpService() {
        return (IRcpService) GlobalServiceRegister.getDefault().getService(IRcpService.class);
    }

    /*
     * public boolean useSQLTemplate() { return (Boolean) CorePlugin.getContext().getProperty("useSQLTemplate"); }
     * 
     * public boolean useRefproject() { return (Boolean) CorePlugin.getContext().getProperty("useRefProject"); }
     */

    public IDesignerMapperService getMapperService() {
        return (IDesignerMapperService) GlobalServiceRegister.getDefault().getService(IDesignerMapperService.class);
    }

}
