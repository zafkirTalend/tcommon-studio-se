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
package org.talend.rcp.intro;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IExtension;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.registry.ActionSetRegistry;
import org.eclipse.ui.internal.registry.IActionSetDescriptor;
import org.talend.commons.utils.workbench.extensions.ExtensionPointLimiterImpl;
import org.talend.commons.utils.workbench.extensions.IExtensionPointLimiter;
import org.talend.core.ui.perspective.PerspectiveMenuManager;
import org.talend.rcp.i18n.Messages;
import org.talend.repository.model.ProxyRepositoryFactory;
import org.talend.repository.ui.actions.toolbar.ProjectSettingsAction;

/**
 * DOC ccarbone class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

    private IWorkbenchAction introAction;

    private IWorkbenchWindow window;

    private final IActionBarConfigurer actionBarConfigurer;

    private static final String GROUP_UNDO = "group undo"; //$NON-NLS-1$

    private static final String GROUP_COPY = "group copy"; //$NON-NLS-1$

    private static final String GROUP_DELETE = "group delete"; //$NON-NLS-1$

    private static SwitchProjectAction switchProjectAction;

    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
        actionBarConfigurer = configurer;
    }

    /**
     * qwei Comment method "activeSwitchAction".
     */
    public static void activeSwitchAction() {
        if (switchProjectAction != null) {
            switchProjectAction.setEnabled(true);
        }

    }

    // private List<IAction> actions = new ArrayList<IAction>();

    public static final IExtensionPointLimiter GLOBAL_ACTIONS = new ExtensionPointLimiterImpl("org.talend.core.global_actions", //$NON-NLS-1$
            "GlobalAction"); //$NON-NLS-1$

    @Override
    protected void makeActions(final IWorkbenchWindow myWindow) {
        this.window = myWindow;
        introAction = ActionFactory.INTRO.create(myWindow);
        register(introAction);

        // List<IAction> list = ExtensionImplementationProviders.getInstance(GLOBAL_ACTIONS);
        // actions.addAll(list);
        // makeTalendGlobalActions();

        registerGlobalActions();
    }

    // private void makeTalendGlobalActions() {
    // List<IConfigurationElement> extension = ExtensionImplementationProviders.getInstanceV2(GLOBAL_ACTIONS);
    //
    // for (IConfigurationElement current : extension) {
    // try {
    // IAction currentAction = (IAction) current.createExecutableExtension("class");
    // actions.add(currentAction);
    // } catch (CoreException e) {
    // e.printStackTrace();
    // }
    // }
    // }

    private void registerGlobalActions() {
        actionBarConfigurer.registerGlobalAction(ActionFactory.SAVE.create(window));
        actionBarConfigurer.registerGlobalAction(ActionFactory.UNDO.create(window));
        actionBarConfigurer.registerGlobalAction(ActionFactory.REDO.create(window));
        actionBarConfigurer.registerGlobalAction(ActionFactory.CUT.create(window));
        actionBarConfigurer.registerGlobalAction(ActionFactory.COPY.create(window));
        actionBarConfigurer.registerGlobalAction(ActionFactory.PASTE.create(window));
        actionBarConfigurer.registerGlobalAction(ActionFactory.DELETE.create(window));
        actionBarConfigurer.registerGlobalAction(ActionFactory.SELECT_ALL.create(window));
        // IContextService contextService = (IContextService) Activator.getDefault().getWorkbench()
        // .getAdapter(IContextService.class);
        // contextService.activateContext("talend.global");
        //
        // IWorkbench workbench = PlatformUI.getWorkbench();
        // IHandlerService handlerService = (IHandlerService) workbench.getService(IHandlerService.class);
        //
        // IHandler handler;
        // for (IAction action : actions) {
        // if (action.getActionDefinitionId() != null) {
        // handler = new ActionHandler(action);
        // handlerService.activateHandler(action.getActionDefinitionId(), handler);
        // }
        // }
    }

    private void removeAction(final ActionSetRegistry reg, final IActionSetDescriptor actionSet) {
        IExtension ext = actionSet.getConfigurationElement().getDeclaringExtension();
        reg.removeExtension(ext, new Object[] { actionSet });
    }

    private static final String[] ACTIONSETID = new String[] {
            "org.eclipse.ui.edit.text.actionSet.convertLineDelimitersTo", //$NON-NLS-1$
            "org.eclipse.ui.edit.text.actionSet.annotationNavigation", "org.eclipse.ui.NavigateActionSet", //$NON-NLS-1$ //$NON-NLS-2$
            "org.eclipse.ui.WorkingSetActionSet", "org.eclipse.ui.edit.text.actionSet.navigation", //$NON-NLS-1$ //$NON-NLS-2$
            "org.eclipse.search.searchActionSet", "org.eclipse.ui.externaltools.ExternalToolsSet", "org.talend.repository.bootTalendActionSet" }; //$NON-NLS-1$

    @Override
    protected void fillMenuBar(final IMenuManager menuBar) {

        ActionSetRegistry reg = WorkbenchPlugin.getDefault().getActionSetRegistry();
        IActionSetDescriptor[] actionSets = reg.getActionSets();

        List<String> list = new ArrayList<String>();
        for (String item : ACTIONSETID) {
            list.add(item);
        }
        if (ProxyRepositoryFactory.getInstance().isUserReadOnlyOnCurrentProject()) {
            list.add("org.talend.repository.CreateactionSet");
        }
        for (int i = 0; i < actionSets.length; i++) {
            if (list.contains(actionSets[i].getId())) {
                removeAction(reg, actionSets[i]);
            }
            /* else { System.out.println(actionSets[i].getId()); } */

        }

        MenuManager fileMenu = new MenuManager(
                Messages.getString("ApplicationActionBarAdvisor.menuFileLabel"), IWorkbenchActionConstants.M_FILE); //$NON-NLS-1$
        menuBar.add(fileMenu);
        // MenuManager subFile = new MenuManager("&New", IWorkbenchActionConstants.NEW_EXT);
        // subFile.add(ActionFactory.NEW.create(window));
        // fileMenu.add(subFile);

        IWorkbenchAction closeAction = ActionFactory.CLOSE.create(window);
        fileMenu.add(closeAction);
        actionBarConfigurer.registerGlobalAction(closeAction);

        IWorkbenchAction closeAllAction = ActionFactory.CLOSE_ALL.create(window);
        fileMenu.add(closeAllAction);
        actionBarConfigurer.registerGlobalAction(closeAllAction);
        fileMenu.add(new Separator());

        fileMenu.add(ActionFactory.SAVE.create(window));

        IWorkbenchAction saveAllAction = ActionFactory.SAVE_ALL.create(window);
        fileMenu.add(saveAllAction);
        actionBarConfigurer.registerGlobalAction(saveAllAction);
        // fileMenu.add(ActionFactory.SAVE_AS.create(window));
        fileMenu.add(new Separator());
        fileMenu.add(ActionFactory.PRINT.create(window));
        fileMenu.add(new Separator());
        switchProjectAction = new SwitchProjectAction();
        fileMenu.add(switchProjectAction);
        ProjectSettingsAction projSetting = new ProjectSettingsAction();
        fileMenu.add(projSetting);
        fileMenu.add(new Separator());

        fileMenu.add(ActionFactory.IMPORT.create(window));
        fileMenu.add(ActionFactory.EXPORT.create(window));

        fileMenu.add(new Separator());
        fileMenu.add(ActionFactory.QUIT.create(window));
        MenuManager editMenu = new MenuManager(
                Messages.getString("ApplicationActionBarAdvisor.menuEditLabel"), IWorkbenchActionConstants.M_EDIT); //$NON-NLS-1$
        menuBar.add(editMenu);
        editMenu.add(new Separator(GROUP_UNDO));
        editMenu.add(new Separator(GROUP_COPY));
        editMenu.add(new Separator(GROUP_DELETE));
        editMenu.appendToGroup(GROUP_UNDO, ActionFactory.UNDO.create(window));
        editMenu.appendToGroup(GROUP_UNDO, ActionFactory.REDO.create(window));
        editMenu.appendToGroup(GROUP_COPY, ActionFactory.CUT.create(window));
        editMenu.appendToGroup(GROUP_COPY, ActionFactory.COPY.create(window));
        editMenu.appendToGroup(GROUP_COPY, ActionFactory.PASTE.create(window));
        editMenu.appendToGroup(GROUP_DELETE, ActionFactory.DELETE.create(window));
        editMenu.appendToGroup(GROUP_DELETE, ActionFactory.SELECT_ALL.create(window));
        editMenu.add(new GroupMarker(IWorkbenchActionConstants.FIND_EXT));

        MenuManager perspMenu = new PerspectiveMenuManager();

        MenuManager windowMenu = new MenuManager(
                Messages.getString("ApplicationActionBarAdvisor.menuWindowLabel"), IWorkbenchActionConstants.M_WINDOW); //$NON-NLS-1$
        menuBar.add(windowMenu);
        windowMenu.add(perspMenu);
        windowMenu.add(new ShowViewAction());
        windowMenu.add(new Separator());
        // windowMenu.add(ActionFactory.SHOW_VIEW_MENU.create(window));
        windowMenu.add(ActionFactory.MAXIMIZE.create(window));
        // windowMenu.add(ActionFactory.SHOW_VIEW_MENU.create(window));
        // menuBar.add(new MenuManager("&Test",IWorkbenchActionConstants.VIEW_EXT));

        // windowMenu.add(ActionFactory.PROPERTIES.create(window));
        // windowMenu.add(ActionFactory.MAXIMIZE.create(window));
        windowMenu.add(ActionFactory.PREFERENCES.create(window));

        MenuManager helpMenu = new MenuManager(
                Messages.getString("ApplicationActionBarAdvisor.menuHelpLabel"), IWorkbenchActionConstants.M_HELP); //$NON-NLS-1$
        menuBar.add(helpMenu);

        // Help
        helpMenu.add(introAction);
        helpMenu.add(ActionFactory.HELP_CONTENTS.create(window));
        IWorkbenchAction create = ActionFactory.ABOUT.create(window);
        helpMenu.add(create);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.application.ActionBarAdvisor#fillCoolBar(org.eclipse.jface.action.ICoolBarManager)
     */
    @Override
    protected void fillCoolBar(ICoolBarManager coolBar) {
        // IToolBarManager toolbar = new ToolBarManager(SWT.FLAT | SWT.RIGHT);
        // coolBar.add(new ToolBarContributionItem(toolbar, "main"));

        // for (IAction action : coolbaractions) {
        // toolbar.add(action);
        // }
        IToolBarManager toolBar = new ToolBarManager(SWT.FLAT | SWT.RIGHT);
        coolBar.add(new ToolBarContributionItem(toolBar, "save"));
        toolBar.add(ActionFactory.SAVE.create(window));

    }

}
