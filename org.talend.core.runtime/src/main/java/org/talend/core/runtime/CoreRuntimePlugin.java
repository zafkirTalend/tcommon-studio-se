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
package org.talend.core.runtime;

import org.talend.core.GlobalServiceRegister;
import org.talend.core.IManagementService;
import org.talend.core.IService;
import org.talend.core.IStatusPreferenceInitService;
import org.talend.core.context.Context;
import org.talend.core.model.general.ILibrariesService;
import org.talend.repository.model.IProxyRepositoryFactory;
import org.talend.repository.model.IRepositoryService;

/**
 * DOC nrousseau class global comment. Detailled comment
 */
public class CoreRuntimePlugin {

    /** Context. */
    private final Context context;

    private static CoreRuntimePlugin instance = null;

    private CoreRuntimePlugin() {
        context = new Context();
    }

    public static CoreRuntimePlugin getInstance() {
        if (instance == null) {
            instance = new CoreRuntimePlugin();
        }
        return instance;
    }

    /**
     * Getter for context.
     * 
     * @return the context
     */
    public Context getContext() {
        return this.context;
    }

    public IProxyRepositoryFactory getProxyRepositoryFactory() {
        IRepositoryService service = getRepositoryService();
        return service.getProxyRepositoryFactory();
    }

    public IRepositoryService getRepositoryService() {
        IService service = GlobalServiceRegister.getDefault().getService(IRepositoryService.class);
        return (IRepositoryService) service;
    }

    public ILibrariesService getLibrariesService() {
        return (ILibrariesService) GlobalServiceRegister.getDefault().getService(ILibrariesService.class);
    }

    public IManagementService getManagementService() {
        return (IManagementService) GlobalServiceRegister.getDefault().getService(IManagementService.class);

    }

    public IStatusPreferenceInitService getStatusPreferenceInitService() {
        return (IStatusPreferenceInitService) GlobalServiceRegister.getDefault().getService(IStatusPreferenceInitService.class);
    }
}
