// ============================================================================
//
// Copyright (C) 2006-2016 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.librariesmanager.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.talend.core.model.general.ModuleToInstall;
import org.talend.librariesmanager.ui.dialogs.ExternalModulesInstallDialogWithProgress;
import org.talend.librariesmanager.ui.i18n.Messages;

/**
 * created by Administrator on 2012-9-17 Detailled comment
 * 
 */
public class ModulesInstaller {

    private static Logger log = Logger.getLogger(ModulesInstaller.class);

    public static void installModules(final Shell shell, final String[] jarNames) {
        final List<ModuleToInstall> toInstall = new ArrayList<ModuleToInstall>();
        final IRunnableWithProgress rwp = RemoteModulesHelper.getInstance().getNotInstalledModulesRunnable(jarNames, toInstall);
        if (rwp != null) {
            Job job = new Job(Messages.getString("RemoteModulesHelper.job.title")) {//$NON-NLS-1$

                @Override
                protected IStatus run(IProgressMonitor progressMonitor) {
                    try {
                        rwp.run(progressMonitor);
                    } catch (InvocationTargetException e) {
                        log.warn("fetching remote Modules data failed", e); //$NON-NLS-1$
                        return Status.CANCEL_STATUS;
                    } catch (InterruptedException e) {
                        log.warn("fetching remote Modules data failed", e); //$NON-NLS-1$
                        return Status.CANCEL_STATUS;
                    }
                    return Status.OK_STATUS;
                }
            };

            job.setUser(true);
            job.setPriority(Job.INTERACTIVE);
            job.schedule();
            try {
                job.join();
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
        }
        if (!toInstall.isEmpty()) {
            Display.getDefault().syncExec(new Runnable() {

                @Override
                public void run() {
                    String text = Messages.getString("ModulesInstaller_text"); //$NON-NLS-1$
                    String title = Messages.getString("ModulesInstaller_title"); //$NON-NLS-1$
                    ExternalModulesInstallDialogWithProgress dialog = new ExternalModulesInstallDialogWithProgress(shell, text,
                            title, SWT.APPLICATION_MODAL);
                    dialog.showDialog(true, jarNames);
                }
            });
        }
    }

}
