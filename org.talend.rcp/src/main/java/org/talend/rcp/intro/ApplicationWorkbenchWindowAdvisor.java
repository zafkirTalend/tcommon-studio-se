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
package org.talend.rcp.intro;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.commands.ActionHandler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.contexts.IContextService;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.internal.ide.EditorAreaDropAdapter;
import org.talend.commons.utils.workbench.extensions.ExtensionImplementationProvider;
import org.talend.commons.utils.workbench.extensions.ExtensionPointLimiterImpl;
import org.talend.commons.utils.workbench.extensions.IExtensionPointLimiter;
import org.talend.core.CorePlugin;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.context.Context;
import org.talend.core.context.RepositoryContext;
import org.talend.core.model.general.Project;
import org.talend.core.prefs.PreferenceManipulator;
import org.talend.core.ui.branding.IBrandingService;
import org.talend.rcp.Activator;
import org.talend.rcp.i18n.Messages;
import org.talend.sqlbuilder.erdiagram.ui.ErDiagramDialog;
import org.talend.sqlbuilder.ui.SQLBuilderDialog;

/**
 * DOC ccarbone class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

    private List<IAction> actions = new ArrayList<IAction>();

    ApplicationActionBarAdvisor adviser = null;

    public static final IExtensionPointLimiter GLOBAL_ACTIONS = new ExtensionPointLimiterImpl("org.talend.core.global_actions", //$NON-NLS-1$
            "GlobalAction"); //$NON-NLS-1$

    public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        super(configurer);
    }

    @Override
    public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
        return adviser = new ApplicationActionBarAdvisor(configurer);
    }

    @Override
    public void preWindowOpen() {
        IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
        configurer.setInitialSize(new Point(1000, 750));
        configurer.setShowCoolBar(true);
        configurer.setShowStatusLine(true);
        configurer.setShowProgressIndicator(true);
        configurer.setShowPerspectiveBar(true);
        configurer.configureEditorAreaDropListener(new EditorAreaDropAdapter(configurer.getWindow()));
        RepositoryContext repositoryContext = (RepositoryContext) CorePlugin.getContext().getProperty(
                Context.REPOSITORY_CONTEXT_KEY);
        Project project = repositoryContext.getProject();

        Object buildId = Activator.getDefault().getBundle().getHeaders().get(org.osgi.framework.Constants.BUNDLE_VERSION);

        String appName = configurer.getTitle(); // BrandingService.getInstance().getFullProductName();
        PreferenceManipulator prefManipulator = new PreferenceManipulator(CorePlugin.getDefault().getPreferenceStore());
        configurer
                .setTitle(appName
                        + " (" + buildId + ") | " + repositoryContext.getUser() + " | " + project.getLabel() + " (" + prefManipulator.getLastConnection() + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$

        IBrandingService service = (IBrandingService) GlobalServiceRegister.getDefault().getService(IBrandingService.class);
        ActionBarBuildHelper helper = (ActionBarBuildHelper) service.getBrandingConfiguration().getHelper();
        if (helper == null) {
            helper = new ActionBarBuildHelper();
            service.getBrandingConfiguration().setHelper(helper);
        }
        helper.preWindowOpen(configurer);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.application.WorkbenchWindowAdvisor#postWindowOpen()
     */
    @Override
    public void postWindowOpen() {
        createActions();
        registerActions();
        adviser.getHelper().postWindowOpen();
    }

    /**
     * DOC smallet Comment method "createActions".
     */
    private void createActions() {

        List<IAction> list = ExtensionImplementationProvider.getInstance(GLOBAL_ACTIONS);
        actions.addAll(list);
    }

    /**
     * DOC smallet Comment method "registerActions".
     */
    private void registerActions() {
        IContextService contextService = (IContextService) Activator.getDefault().getWorkbench()
                .getAdapter(IContextService.class);
        contextService.activateContext("talend.global"); //$NON-NLS-1$

        IWorkbench workbench = PlatformUI.getWorkbench();
        IHandlerService handlerService = (IHandlerService) workbench.getService(IHandlerService.class);

        IHandler handler;
        for (IAction action : actions) {
            handler = new ActionHandler(action);
            handlerService.activateHandler(action.getActionDefinitionId(), handler);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.application.WorkbenchWindowAdvisor#postWindowClose()
     */
    @Override
    public void postWindowClose() {
        Shell[] shelles = Display.getDefault().getShells();
        for (Shell shell : shelles) {
            if (!shell.isDisposed() && shell.getData() != null) {
                if (shell.getData() instanceof SQLBuilderDialog) {
                    ((SQLBuilderDialog) shell.getData()).close();
                } else if (shell.getData() instanceof ErDiagramDialog) {
                    ((ErDiagramDialog) shell.getData()).close();
                }
            }
        }
        super.postWindowClose();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.application.WorkbenchWindowAdvisor#preWindowShellClose()
     */
    @Override
    public boolean preWindowShellClose() {

        Object shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
        MessageBox box = new MessageBox((Shell) shell, SWT.ICON_WARNING | SWT.YES | SWT.NO);
        box.setText(Messages.getString("ApplicationWorkbenchWindowAdvisor.title")); //$NON-NLS-1$
        box.setMessage(Messages.getString("ApplicationWorkbenchWindowAdvisor.messages")); //$NON-NLS-1$
        int choice = box.open();
        if (choice == SWT.YES) {
            return super.preWindowShellClose();
        } else {
            return false;
        }
    }
}
