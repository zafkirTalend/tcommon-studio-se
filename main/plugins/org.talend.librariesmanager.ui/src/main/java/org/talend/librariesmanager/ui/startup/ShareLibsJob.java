// ============================================================================
//
// Copyright (C) 2006-2014 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.librariesmanager.ui.startup;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.ISVNProviderServiceInCoreRuntime;
import org.talend.core.PluginChecker;
import org.talend.core.nexus.NexusServerBean;
import org.talend.core.runtime.CoreRuntimePlugin;
import org.talend.librariesmanager.maven.TalendLibsServerManager;
import org.talend.librariesmanager.ui.i18n.Messages;
import org.talend.repository.model.IProxyRepositoryFactory;

/**
 * created by Talend on 2015年6月15日 Detailled comment
 *
 */
public class ShareLibsJob extends Job {

    private final String TYPE_NEXUS = "nexus";

    private final String TYPE_SVN = "svn";

    /**
     * DOC Talend ShareLibsJob constructor comment.
     * 
     * @param name
     */
    public ShareLibsJob() {
        super("");
        // TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    protected IStatus run(IProgressMonitor monitor) {
        setName(Messages.getString("ShareLibsJob.message"));

        if (PluginChecker.isSVNProviderPluginLoaded()) {
            try {
                final IProxyRepositoryFactory factory = CoreRuntimePlugin.getInstance().getProxyRepositoryFactory();
                if (!factory.isLocalConnectionProvider() && factory.getRepositoryContext() != null
                        && !factory.getRepositoryContext().isOffline()) {
                    ISVNProviderServiceInCoreRuntime service = (ISVNProviderServiceInCoreRuntime) GlobalServiceRegister
                            .getDefault().getService(ISVNProviderServiceInCoreRuntime.class);
                    if (service != null && service.isSvnLibSetupOnTAC()) {

                    } else {
                        final TalendLibsServerManager instance = TalendLibsServerManager.getInstance();
                        final NexusServerBean customServer = instance.getCustomNexusServer();
                        if (customServer != null) {

                        }

                    }
                }
            } catch (Exception e) {
                ExceptionHandler.process(e);
            }
        }
        return Status.OK_STATUS;
    }

}
