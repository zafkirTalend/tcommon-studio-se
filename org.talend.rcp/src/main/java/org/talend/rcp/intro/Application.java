// ============================================================================
//
// Copyright (C) 2006-2011 Talend Inc. - www.talend.com
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
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.core.runtime.Platform;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.osgi.service.datalocation.Location;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.tweaklets.Tweaklets;
import org.eclipse.ui.internal.tweaklets.WorkbenchImplementation;
import org.talend.commons.exception.BusinessException;
import org.talend.commons.ui.swt.dialogs.ErrorDialogWidthDetailArea;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.model.migration.IMigrationToolService;
import org.talend.core.ui.branding.IBrandingService;
import org.talend.rcp.i18n.Messages;
import org.talend.rcp.intro.linksbar.Workbench3xImplementation4CoolBar;
import org.talend.repository.RegistrationPlugin;
import org.talend.repository.license.LicenseManagement;
import org.talend.repository.model.IRepositoryService;
import org.talend.repository.registeruser.RegisterManagement;
import org.talend.repository.ui.login.LoginComposite;
import org.talend.repository.ui.login.connections.ConnectionUserPerReader;
import org.talend.repository.ui.wizards.license.LicenseWizard;
import org.talend.repository.ui.wizards.license.LicenseWizardDialog;
import org.talend.repository.ui.wizards.register.RegisterWizard;
import org.talend.repository.ui.wizards.register.RegisterWizardPage1;

/**
 * This class controls all aspects of the application's execution.
 */
public class Application implements IApplication {

    static final String TALEND_PROJECT_TYPE_COMMAND = "-talendProjectType"; //$NON-NLS-1$

    static final String TALEND_RESTART_COMMAND = "-talendRestart"; //$NON-NLS-1$

    static final String PROP_VM = "eclipse.vm"; //$NON-NLS-1$

    static final String PROP_VMARGS = "eclipse.vmargs"; //$NON-NLS-1$

    static final String PROP_COMMANDS = "eclipse.commands"; //$NON-NLS-1$

    static final String CMD_VMARGS = "-vmargs"; //$NON-NLS-1$

    static final String NEW_LINE = "\n"; //$NON-NLS-1$

    public Object start(IApplicationContext context) throws Exception {
        Display display = PlatformUI.createDisplay();
        try {
            Shell shell = new Shell(display, SWT.ON_TOP);
            // If we cannot get the workspace lock, pop up an error dialog and then exit the application.
            boolean inuse = !acquireWorkspaceLock(shell);
            // if (!acquireWorkspaceLock(shell)) {
            // return IApplication.EXIT_OK;
            // }
            /*
             * setSqlpatternUsibility(context); setRefProjectUsibility(context);
             */
            if (GlobalServiceRegister.getDefault().isServiceRegistered(IRepositoryService.class)) {
                IRepositoryService repositoryService = (IRepositoryService) GlobalServiceRegister.getDefault().getService(
                        IRepositoryService.class);
                repositoryService.setRCPMode();
            }

            if (!ArrayUtils.contains(Platform.getApplicationArgs(), "--disableLoginDialog") && !Boolean.parseBoolean(System.getProperty("talend.project.Startable"))) {//$NON-NLS-1$ //$NON-NLS-2$
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
                if (shell != null) {
                    shell.dispose();
                }
            }

            // if some commands are set to relaunch (not restart) the eclipse then relaunch it
            // this happens when project type does not match the running product type
            if (System.getProperty(org.eclipse.equinox.app.IApplicationContext.EXIT_DATA_PROPERTY) != null) {
                return IApplication.EXIT_RELAUNCH;
            }

            if (LoginComposite.isRestart) {
                return IApplication.EXIT_RESTART;
            }

            Tweaklets.setDefault(WorkbenchImplementation.KEY, new Workbench3xImplementation4CoolBar());

            int returnCode = PlatformUI.createAndRunWorkbench(display, new ApplicationWorkbenchAdvisor());
            if (returnCode == PlatformUI.RETURN_RESTART) {
                // use relaunch instead of restart to remove change the restart property that may have been added in the
                // previous
                // relaunch
                System.setProperty(org.eclipse.equinox.app.IApplicationContext.EXIT_DATA_PROPERTY, buildRestartFalseCommandLine());
                return IApplication.EXIT_RELAUNCH;
            } else {
                return IApplication.EXIT_OK;
            }
        } finally {
            display.dispose();
            // release workspace lock
            releaseWorkspaceLock();
        }

    }

    /**
     * this recreates the Eclipse command line by add (or changing) the option -talendRestart false and removing the
     * -talendProjectType value
     * 
     * @return
     */
    private String buildRestartFalseCommandLine() {
        String falseValue = "false";
        // define the java process tio launch
        String property = System.getProperty(PROP_VM);
        StringBuffer result = new StringBuffer(512);
        result.append(property);
        result.append(NEW_LINE);

        // add the java argument for the jvm
        // append the vmargs and commands. Assume that these already end in \n
        String vmargs = System.getProperty(PROP_VMARGS);
        if (vmargs != null) {
            result.append(vmargs);
        }

        // append the rest of the args, replacing or adding -data as required
        property = System.getProperty(PROP_COMMANDS);
        if (property == null) {
            result.append(TALEND_RESTART_COMMAND);
            result.append(NEW_LINE);
            result.append(falseValue); //$NON-NLS-1$
            result.append(NEW_LINE);
        } else {
            // try to find existing commands to update them
            // find the index of the arg to replace its value
            Pattern restartPattern = Pattern.compile(TALEND_RESTART_COMMAND + "\\s+.+\\s");//$NON-NLS-1$ -talendRestart\s+.+\s
            Matcher restartMatcher = restartPattern.matcher(property);
            if (restartMatcher.find()) {
                property = restartMatcher.replaceAll(TALEND_RESTART_COMMAND + NEW_LINE + falseValue + NEW_LINE); //$NON-NLS-1$
            } else {
                result.append(TALEND_RESTART_COMMAND);
                result.append(NEW_LINE);
                result.append(falseValue);
                result.append(NEW_LINE);
            }
            Pattern projTypePattern = Pattern.compile(TALEND_PROJECT_TYPE_COMMAND + "\\s+.+\\s");//$NON-NLS-1$ -talendProjectType\s+.+\s
            Matcher projTypeMatcher = projTypePattern.matcher(property);
            if (projTypeMatcher.find()) {
                property = projTypeMatcher.replaceAll(NEW_LINE);
            } // not here already to do nothing

            result.append(property);
        }

        // put the vmargs back at the very end so that the Main.java can know the vm args and set them in the system
        // property eclipse.vmargs
        // (the previously set eclipse.commands property already contains the -vm arg)
        if (vmargs != null) {
            result.append(CMD_VMARGS);
            result.append(NEW_LINE);
            result.append(vmargs);
        }

        return result.toString();
    }

    private void openLicenseAndRegister(Shell shell) {
        IBrandingService brandingService = (IBrandingService) GlobalServiceRegister.getDefault().getService(
                IBrandingService.class);
        if (!LicenseManagement.isLicenseValidated()) {
            LicenseWizard licenseWizard = new LicenseWizard();
            LicenseWizardDialog dialog = new LicenseWizardDialog(shell, licenseWizard);
            dialog.setTitle(Messages.getString("LicenseWizard.windowTitle")); //$NON-NLS-1$
            if (dialog.open() == WizardDialog.OK) {
                try {
                    LicenseManagement.acceptLicense();
                } catch (BusinessException e) {
                    ErrorDialogWidthDetailArea errorDialog = new ErrorDialogWidthDetailArea(shell, RegistrationPlugin.PLUGIN_ID,
                            Messages.getString("RegisterWizardPage.serverCommunicationProblem"), e.getMessage()); //$NON-NLS-1$
                    System.exit(0);
                }

            } else {
                System.exit(0);
            }
        }

        if (brandingService.getBrandingConfiguration().isUseProductRegistration()) {
            if (!RegisterManagement.getInstance().isProductRegistered()) {
                RegisterWizard registerWizard = new RegisterWizard();
                RegisterWizardPage1 dialog = new RegisterWizardPage1(shell, registerWizard);
                dialog.open();
            }
        }

    }

    /**
     * TODO This method should be removed after finishing the sqlpattern
     * 
     * @param context
     */
    /*
     * private void setSqlpatternUsibility(IApplicationContext context) { Map map = context.getArguments(); String[]
     * args = (String[]) map.get(IApplicationContext.APPLICATION_ARGS); if (args == null) { return; }
     * 
     * boolean use = true; // for (int i = 0; i < args.length; i++) { // if (args[i].equals("-useSQLPattern")) { // use
     * = Boolean.parseBoolean(args[i + 1]); // break; // } // } CorePlugin.getContext().putProperty("useSQLPattern",
     * use); }
     *//**
     * TODO This method should be removed after finishing the refProject
     * 
     * @param context
     */
    /*
     * private void setRefProjectUsibility(IApplicationContext context) { Map map = context.getArguments(); String[]
     * args = (String[]) map.get(IApplicationContext.APPLICATION_ARGS); if (args == null) { return; }
     * 
     * boolean use = false; for (int i = 0; i < args.length; i++) { if (args[i].equals("-useRefProject")) { use =
     * Boolean.parseBoolean(args[i + 1]); break; } } CorePlugin.getContext().putProperty("useRefProject", use); }
     */

    /**
     * Return <code>true</code> if the lock could be acquired.
     * 
     * @param shell
     * @throws IOException
     */
    private boolean acquireWorkspaceLock(Shell shell) {
        Location instanceLoc = Platform.getInstanceLocation();
        ConnectionUserPerReader perReader = ConnectionUserPerReader.getInstance();
        if (perReader.isHaveUserPer()) {
            String lastWorkSpacePath = perReader.readLastWorkSpace();
            if (!"".equals(lastWorkSpacePath) && lastWorkSpacePath != null) {//$NON-NLS-1$

                File file = new File(lastWorkSpacePath);
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
                URL url = null;
                try {
                    url = file.toURL();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                if (url != null) {
                    instanceLoc.setURL(url, false);
                }
            }

        }

        // It may be the first time for user to use tos, and we haven't create workspace yet.
        if (instanceLoc == null || instanceLoc.getURL() == null) {
            return true;
        }

        try {
            // try to lock the workspace
            if (instanceLoc.lock()) {
                return true;
            }
        } catch (Throwable t) {
            // do nothing
        }
        //        MessageDialog.openError(shell, Messages.getString("Application_workspaceInUseTitle"), //$NON-NLS-1$
        //                Messages.getString("Application.workspaceInUse")); //$NON-NLS-1$
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

    public void stop() {
        final IWorkbench workbench = PlatformUI.getWorkbench();
        if (workbench == null) {
            return;
        }
        final Display display = workbench.getDisplay();
        display.syncExec(new Runnable() {

            public void run() {
                if (!display.isDisposed()) {
                    workbench.close();
                }
            }
        });
    }
}
