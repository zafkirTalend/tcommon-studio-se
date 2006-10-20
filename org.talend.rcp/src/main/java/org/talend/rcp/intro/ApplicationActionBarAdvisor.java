// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
//
// ============================================================================
package org.talend.rcp.intro;

import org.eclipse.core.runtime.IExtension;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.registry.ActionSetRegistry;
import org.eclipse.ui.internal.registry.IActionSetDescriptor;
import org.talend.rcp.perspective.PerspectiveMenuManager;

/**
 * DOC ccarbone class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

    private IWorkbenchAction introAction;

    private IWorkbenchWindow window;

    private IActionBarConfigurer actionBarConfigurer;

    private static final String GROUP_UNDO = "group undo";

    private static final String GROUP_COPY = "group copy";

    private static final String GROUP_DELETE = "group delete";

    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
        actionBarConfigurer = configurer;
    }

    protected void makeActions(final IWorkbenchWindow myWindow) {
        this.window = myWindow;
        introAction = ActionFactory.INTRO.create(myWindow);
        register(introAction);
        registerGlobalActions();
    }

    private void registerGlobalActions() {
        actionBarConfigurer.registerGlobalAction(ActionFactory.SAVE.create(window));
        actionBarConfigurer.registerGlobalAction(ActionFactory.UNDO.create(window));
        actionBarConfigurer.registerGlobalAction(ActionFactory.REDO.create(window));
        actionBarConfigurer.registerGlobalAction(ActionFactory.CUT.create(window));
        actionBarConfigurer.registerGlobalAction(ActionFactory.COPY.create(window));
        actionBarConfigurer.registerGlobalAction(ActionFactory.PASTE.create(window));
        actionBarConfigurer.registerGlobalAction(ActionFactory.DELETE.create(window));
        actionBarConfigurer.registerGlobalAction(ActionFactory.SELECT_ALL.create(window));
    }

    private void removeAction(final ActionSetRegistry reg, final IActionSetDescriptor actionSet) {
        IExtension ext = actionSet.getConfigurationElement().getDeclaringExtension();
        reg.removeExtension(ext, new Object[] { actionSet });
    }

    protected void fillMenuBar(final IMenuManager menuBar) {

        ActionSetRegistry reg = WorkbenchPlugin.getDefault().getActionSetRegistry();
        IActionSetDescriptor[] actionSets = reg.getActionSets();
        String actionSetId1 = "org.eclipse.ui.edit.text.actionSet.convertLineDelimitersTo";
        String actionSetId2 = "org.eclipse.ui.edit.text.actionSet.annotationNavigation";
        String actionSetId3 = "org.eclipse.ui.NavigateActionSet";
        String actionSetId4 = "org.eclipse.ui.WorkingSetActionSet";
        String actionSetId5 = "org.eclipse.ui.edit.text.actionSet.navigation";

        for (int i = 0; i < actionSets.length; i++) {
            if (actionSets[i].getId().equals(actionSetId1) || actionSets[i].getId().equals(actionSetId2)
                    || actionSets[i].getId().equals(actionSetId3) || actionSets[i].getId().equals(actionSetId4)
                    || actionSets[i].getId().equals(actionSetId5)) {
                removeAction(reg, actionSets[i]);
            }/*
                 * else { System.out.println(actionSets[i].getId()); }
                 */
        }

        MenuManager fileMenu = new MenuManager("&File", IWorkbenchActionConstants.M_FILE);
        menuBar.add(fileMenu);
        // MenuManager subFile = new MenuManager("&New", IWorkbenchActionConstants.NEW_EXT);
        // subFile.add(ActionFactory.NEW.create(window));
        // fileMenu.add(subFile);
        fileMenu.add(ActionFactory.SAVE.create(window));
        // fileMenu.add(ActionFactory.SAVE_AS.create(window));

//      CAN. SwitchProject Action must be call the LoginDialog to Change of Project and Open this.  
        fileMenu.add(new SwitchProjectAction());
        fileMenu.add(ActionFactory.PRINT.create(window));
        fileMenu.add(ActionFactory.QUIT.create(window));
        MenuManager editMenu = new MenuManager("&Edit", IWorkbenchActionConstants.M_EDIT);
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

        MenuManager perspMenu = new PerspectiveMenuManager();

        MenuManager windowMenu = new MenuManager("&Window", IWorkbenchActionConstants.M_WINDOW);
        menuBar.add(windowMenu);
        windowMenu.add(perspMenu);
        windowMenu.add(new ShowViewAction());
        windowMenu.add(new Separator());
        windowMenu.add(ActionFactory.SHOW_VIEW_MENU.create(window));
        windowMenu.add(ActionFactory.MAXIMIZE.create(window));
        // windowMenu.add(ActionFactory.SHOW_VIEW_MENU.create(window));
        // menuBar.add(new MenuManager("&Test",IWorkbenchActionConstants.VIEW_EXT));

        // windowMenu.add(ActionFactory.PROPERTIES.create(window));
        // windowMenu.add(ActionFactory.MAXIMIZE.create(window));
        windowMenu.add(ActionFactory.PREFERENCES.create(window));

        MenuManager helpMenu = new MenuManager("&Help", IWorkbenchActionConstants.M_HELP);
        menuBar.add(helpMenu);

        // Help
        helpMenu.add(introAction);
        helpMenu.add(ActionFactory.HELP_CONTENTS.create(window));
        helpMenu.add(ActionFactory.ABOUT.create(window));
    }

}
