// ============================================================================
//
// Copyright (C) 2006-2015 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.rcp.intro;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.URIUtil;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.osgi.service.datalocation.Location;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.PlatformUI;
import org.talend.commons.exception.BusinessException;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.ui.swt.dialogs.ErrorDialogWidthDetailArea;
import org.talend.commons.utils.system.EclipseCommandLine;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.model.migration.IMigrationToolService;
import org.talend.core.repository.CoreRepositoryPlugin;
import org.talend.core.tis.ICoreTisService;
import org.talend.core.ui.branding.IBrandingService;
import org.talend.rcp.i18n.Messages;
import org.talend.repository.RegistrationPlugin;
import org.talend.repository.license.LicenseManagement;
import org.talend.repository.model.IRepositoryService;
import org.talend.repository.ui.login.LoginComposite;
import org.talend.repository.ui.login.connections.ConnectionUserPerReader;
import org.talend.repository.ui.wizards.license.LicenseWizard;
import org.talend.repository.ui.wizards.license.LicenseWizardDialog;

/**
 * This class controls all aspects of the application's execution.
 */
public class Application implements IApplication {

    @Override
    public Object start(IApplicationContext context) throws Exception {
        Display display = PlatformUI.createDisplay();
        boolean inuse = false;

        try {
            Shell shell = new Shell(display, SWT.ON_TOP);

            // check workspace inuse, if true, means have do lock in configurator. if false, will try to lock
            if (!Boolean.getBoolean("org.talend.workspace.locked")) { //$NON-NLS-1$
                // TDI-28205, the lock may be acquired by the configurator but leave a possibility to do it here for TOS
                inuse = !acquireWorkspaceLock(shell);
            }// else already locked by the configurator so not in use.
            if (inuse) {// if inuse, will forbid launching.
                MessageDialog.openError(shell, Messages.getString("Application.WorkspaceInuseTitle"), //$NON-NLS-1$
                        Messages.getString("Application.WorkspaceInuseMessage")); //$NON-NLS-1$
                shell.dispose();
                return EXIT_OK;
            }

            /*
             * setSqlpatternUsibility(context); setRefProjectUsibility(context);
             */
            CoreRepositoryPlugin.getDefault().setRCPMode();

            checkBrowserSupport();

            if (!ArrayUtils.contains(Platform.getApplicationArgs(), EclipseCommandLine.TALEND_DISABLE_LOGINDIALOG_COMMAND)
                    && !Boolean.parseBoolean(System.getProperty("talend.project.reload"))) {//$NON-NLS-1$ 
                openLicenseAndRegister(shell);
            }

            IMigrationToolService service = (IMigrationToolService) GlobalServiceRegister.getDefault().getService(
                    IMigrationToolService.class);
            service.executeWorspaceTasks();
            // saveConnectionBean(email);

            boolean logUserOnProject = logUserOnProject(display.getActiveShell(), inuse);
            try {
                if (!logUserOnProject) {
                    // MOD qiongli 2010-11-1,bug 16723: Code Cleansing
                    // Platform.endSplash();
                    context.applicationRunning();
                    // ~
                    return EXIT_OK;
                }
            } finally {
                shell.dispose();
            }

            // if some commands are set to relaunch (not restart) the eclipse then relaunch it
            // this happens when project type does not match the running product type
            if (System.getProperty(org.eclipse.equinox.app.IApplicationContext.EXIT_DATA_PROPERTY) != null) {
                return IApplication.EXIT_RELAUNCH;
            }

            boolean afterUpdate = false;
            if (GlobalServiceRegister.getDefault().isServiceRegistered(ICoreTisService.class)) {
                ICoreTisService tisService = (ICoreTisService) GlobalServiceRegister.getDefault().getService(
                        ICoreTisService.class);
                afterUpdate = tisService.needRestartAfterUpdate();
            }

            // common restart
            if (LoginComposite.isRestart) {
                // if after update,need to lauch the product by loading all new version plugins
                if (afterUpdate) {
                    EclipseCommandLine.updateOrCreateExitDataPropertyWithCommand(EclipseCommandLine.TALEND_RELOAD_COMMAND,
                            Boolean.TRUE.toString(), false);
                    // if relaunch, should delete the "disableLoginDialog" argument in eclipse data for bug TDI-19214
                    EclipseCommandLine.updateOrCreateExitDataPropertyWithCommand(
                            EclipseCommandLine.TALEND_DISABLE_LOGINDIALOG_COMMAND, null, true);
                    return IApplication.EXIT_RELAUNCH;
                }
                return IApplication.EXIT_RESTART;
            }

            IBrandingService brandingService = (IBrandingService) GlobalServiceRegister.getDefault().getService(
                    IBrandingService.class);

            // for talend product only to add the links on the left of the coolbar
            // other products will simply reuse the default presentation factory.
            if (brandingService.isPoweredbyTalend()) {
                // setup the presentation factory, which is defined in the plugin.xml of the org.talend.rcp
                IPreferenceStore store = PlatformUI.getPreferenceStore();
                store.putValue(IWorkbenchPreferenceConstants.PRESENTATION_FACTORY_ID, "org.talend.rcp.presentationfactory"); //$NON-NLS-1$
            }
            int returnCode = PlatformUI.createAndRunWorkbench(display, new ApplicationWorkbenchAdvisor());
            if (returnCode == PlatformUI.RETURN_RESTART) {
                // use relaunch instead of restart to remove change the restart property that may have been added in the
                // previous
                // relaunch
                EclipseCommandLine.updateOrCreateExitDataPropertyWithCommand(EclipseCommandLine.TALEND_RELOAD_COMMAND,
                        Boolean.FALSE.toString(), false);
                EclipseCommandLine.updateOrCreateExitDataPropertyWithCommand(EclipseCommandLine.TALEND_PROJECT_TYPE_COMMAND,
                        null, true);
                // if relaunch, should delete the "disableLoginDialog" argument in eclipse data for bug TDI-19214
                EclipseCommandLine.updateOrCreateExitDataPropertyWithCommand(
                        EclipseCommandLine.TALEND_DISABLE_LOGINDIALOG_COMMAND, null, true, true);
                // TDI-8426, fix the swith project failure, when in dev also.
                // if dev, can't be restart, so specially for dev.
                if (Platform.inDevelopmentMode()) {
                    return IApplication.EXIT_RESTART;
                }
                return IApplication.EXIT_RELAUNCH;
            } else {
                return IApplication.EXIT_OK;
            }
        } finally {
            display.dispose();
            if (!inuse) { // release workspace lock for current app only, not for anothers.
                releaseWorkspaceLock();
            }
        }

    }

    /**
     * 
     * DOC ggu Comment method "checkForBrowser".
     */
    private void checkBrowserSupport() {
        Shell shell = new Shell();
        try {
            Browser browser = new Browser(shell, SWT.BORDER);
            System.setProperty("USE_BROWSER", Boolean.TRUE.toString()); //$NON-NLS-1$ 
            browser.dispose();
        } catch (Throwable t) {
            System.setProperty("USE_BROWSER", Boolean.FALSE.toString()); //$NON-NLS-1$ 
        } finally {
            shell.dispose();

        }
    }

    private void openLicenseAndRegister(Shell shell) {
        if (!LicenseManagement.isLicenseValidated()) {
            LicenseWizard licenseWizard = new LicenseWizard();
            LicenseWizardDialog dialog = new LicenseWizardDialog(shell, licenseWizard);
            dialog.setTitle(""); //$NON-NLS-1$
            if (dialog.open() == WizardDialog.OK) {
                try {
                    LicenseManagement.acceptLicense();
                } catch (BusinessException e) {
                    ErrorDialogWidthDetailArea errorDialog = new ErrorDialogWidthDetailArea(shell, RegistrationPlugin.PLUGIN_ID,
                            "", e.getMessage()); //$NON-NLS-1$
                    System.exit(0);
                }

            } else {
                System.exit(0);
            }
        }

    }

    /**
     * Return <code>true</code> if the lock could be acquired.
     * 
     * @param shell
     * @throws IOException
     */
    private boolean acquireWorkspaceLock(Shell shell) {
        Location instanceLoc = Platform.getInstanceLocation();
        ConnectionUserPerReader perReader = ConnectionUserPerReader.getInstance();
        if (perReader.isHaveUserPer() && instanceLoc != null && !instanceLoc.isSet()) {
            try {
                String lastWorkSpacePath = perReader.readLastWorkSpace();
                if (!"".equals(lastWorkSpacePath) && lastWorkSpacePath != null) {//$NON-NLS-1$
                    File file = new File(lastWorkSpacePath);
                    boolean needSet = true;
                    if (instanceLoc.isSet()) {
                        File curWorkspace = URIUtil.toFile(URIUtil.toURI(instanceLoc.getURL()));
                        if (file.equals(curWorkspace)) {
                            needSet = false;
                        }
                    }
                    // make sure set really.
                    if (needSet) {
                        if (!file.exists()) {
                            // for bug 10307
                            boolean mkdirs = file.mkdirs();
                            if (!mkdirs) {
                                MessageDialog.openError(shell, Messages.getString("Application_workspaceInUseTitle"), //$NON-NLS-1$
                                        Messages.getString("Application.workspaceNotExiste")); //$NON-NLS-1$
                                perReader.saveConnections(null);
                                return true;
                            }
                        }
                        // For TUP-1749, we have to use File.toURL() although it is a deprecated method in order
                        // to support the workspace path which contains space.
                        instanceLoc.set(file.toURL(), false);
                    }
                }
            } catch (MalformedURLException e) {
                ExceptionHandler.process(e);
            } catch (URISyntaxException e) {
                ExceptionHandler.process(e);
            } catch (IllegalStateException e) {
                ExceptionHandler.process(e);
            } catch (IOException e) {
                ExceptionHandler.process(e);
            }

        }

        // It may be the first time for user to use tos, and we haven't create workspace yet.
        if (instanceLoc == null || instanceLoc.getURL() == null) {
            return true;
        }
        if (instanceLoc.isSet()) {
            try {
                // try to lock the workspace
                if (instanceLoc.lock()) {
                    return true;
                }
            } catch (Throwable t) {
                // do nothing
            }
        }
        return false;
    }

    /**
     * Release the workspace lock before we exit the application.
     */
    private void releaseWorkspaceLock() {
        Location instanceLoc = Platform.getInstanceLocation();
        if (instanceLoc != null) {
            instanceLoc.release();
        }
    }

    private boolean logUserOnProject(Shell shell, boolean inuse) {
        IRepositoryService service = (IRepositoryService) GlobalServiceRegister.getDefault().getService(IRepositoryService.class);
        return service.openLoginDialog(shell, inuse);
    }

    @Override
    public void stop() {
        final IWorkbench workbench = PlatformUI.getWorkbench();
        if (workbench == null) {
            return;
        }
        final Display display = workbench.getDisplay();
        display.syncExec(new Runnable() {

            @Override
            public void run() {
                if (!display.isDisposed()) {
                    workbench.close();
                }
            }
        });
    }
}
