// ============================================================================
//
// Copyright (C) 2006-2013 Talend Inc. - www.talend.com
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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.core.download.DownloadHelperWithProgress;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.model.general.ModuleToInstall;
import org.talend.librariesmanager.prefs.LibrariesManagerUtils;
import org.talend.librariesmanager.ui.LibManagerUiPlugin;
import org.talend.librariesmanager.ui.i18n.Messages;
import org.talend.librariesmanager.ui.wizards.AcceptModuleLicensesWizard;
import org.talend.librariesmanager.ui.wizards.AcceptModuleLicensesWizardDialog;

abstract public class DownloadModuleRunnable implements IRunnableWithProgress {

    protected List<ModuleToInstall> toDownload;

    protected Set<String> downloadFailed;

    protected Set<String> installedModules;

    /**
     * DOC sgandon DownloadModuleRunnable constructor comment.
     * 
     * @param shell, never null, used to ask the user to accept the licenses
     * @param toDownload
     */
    public DownloadModuleRunnable(List<ModuleToInstall> toDownload) {
        this.toDownload = toDownload;
        downloadFailed = new HashSet<String>();
        installedModules = new HashSet<String>();
    }

    @Override
    public void run(final IProgressMonitor monitor) {
        SubMonitor subMonitor = SubMonitor.convert(monitor,
                Messages.getString("ExternalModulesInstallDialog.downloading2"), toDownload.size() * 10 + 5); //$NON-NLS-1$
        if (checkAndAcceptLicenses()) {
            downLoad(subMonitor);
        }
        if (monitor != null) {
            monitor.done();
        }
    }

    private void downLoad(final SubMonitor monitor) {
        final List<URL> downloadOk = new ArrayList<URL>();
        for (final ModuleToInstall module : toDownload) {
            if (!monitor.isCanceled()) {
                monitor.subTask(module.getName());
                monitor.worked(5);

                String librariesPath = LibrariesManagerUtils.getLibrariesPath(ECodeLanguage.JAVA);
                File target = new File(librariesPath);
                boolean accepted;
                if (module.getUrl_download() != null && !"".equals(module.getUrl_download())) { //$NON-NLS-1$
                    try {
                        // check license
                        boolean isLicenseAccepted = LibManagerUiPlugin.getDefault().getPreferenceStore()
                                .getBoolean(module.getLicenseType());
                        accepted = isLicenseAccepted;
                        if (!accepted) {
                            monitor.worked(5);
                            continue;
                        }
                        if (monitor.isCanceled()) {
                            return;
                        }
                        File destination = new File(target.toString() + File.separator + module.getName());
                        DownloadHelperWithProgress downloader = new DownloadHelperWithProgress();
                        downloader.download(new URL(module.getUrl_download()), destination, monitor.newChild(4));
                        downloadOk.add(destination.toURL());
                        installedModules.add(module.getName());
                        monitor.worked(1);
                    } catch (Exception e) {
                        downloadFailed.add(module.getName());
                        ExceptionHandler.process(e);
                        continue;
                    }
                }
                accepted = false;
            } else {
                downloadFailed.add(module.getName());
            }
        }
        if (!downloadOk.isEmpty()) {
            try {
                LibManagerUiPlugin.getDefault().getLibrariesService()
                        .deployLibrarys(downloadOk.toArray(new URL[downloadOk.size()]));
            } catch (IOException e) {
                ExceptionHandler.process(e);
            }
            monitor.worked(5);
        }

    }

    protected boolean hasLicensesToAccept() {
        if (toDownload != null && toDownload.size() > 0) {
            for (ModuleToInstall module : toDownload) {
                String licenseType = module.getLicenseType();
                if (licenseType != null) {
                    boolean isLicenseAccepted = LibManagerUiPlugin.getDefault().getPreferenceStore()
                            .getBoolean(module.getLicenseType());
                    if (!isLicenseAccepted) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    protected boolean checkAndAcceptLicenses() {
        final AtomicBoolean accepted = new AtomicBoolean(true);
        if (hasLicensesToAccept()) {
            Display.getDefault().syncExec(new Runnable() {

                @Override
                public void run() {
                    AcceptModuleLicensesWizard licensesWizard = new AcceptModuleLicensesWizard(toDownload);
                    AcceptModuleLicensesWizardDialog wizardDialog = new AcceptModuleLicensesWizardDialog(PlatformUI
                            .getWorkbench().getActiveWorkbenchWindow().getShell(), licensesWizard, toDownload);
                    wizardDialog.setPageSize(700, 380);
                    wizardDialog.create();
                    if (wizardDialog.open() != Window.OK) {
                        accepted.set(false);
                    }
                }
            });

        }

        return accepted.get();
    }

    /**
     * DOC sgandon Comment method "acceptLicence".
     * 
     * @param module
     */
    abstract protected boolean acceptLicence(ModuleToInstall module);

    /**
     * Getter for downloadFailed.
     * 
     * @return the downloadFailed
     */
    public Set<String> getDownloadFailed() {
        return this.downloadFailed;
    }

    /**
     * Getter for installedModules.
     * 
     * @return the installedModules
     */
    public Set<String> getInstalledModules() {
        return this.installedModules;
    }
}