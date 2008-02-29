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
package org.talend.dataprofiler.rcp.intro;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.IExtension;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.registry.ActionSetRegistry;
import org.eclipse.ui.internal.registry.IActionSetDescriptor;
import org.talend.dataprofiler.core.ui.perspective.PerspectiveMenuManager;

/**
 * DOC rli  class global comment. Detailled comment
 * <br/>
 *
 */
public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	private IWorkbenchAction exitAction;

	private IWorkbenchAction preferenceAction;

	private IWorkbenchAction aboutAction;
    
	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}

	protected void makeActions(IWorkbenchWindow window) {
		exitAction = ActionFactory.QUIT.create(window);
		register(exitAction);

		preferenceAction = ActionFactory.PREFERENCES.create(window);
		register(preferenceAction);

		aboutAction = ActionFactory.ABOUT.create(window);
		register(aboutAction);
	}

	protected void fillMenuBar(IMenuManager menuBar) {
		this.beforefillMenuBar();
		MenuManager fileMenu = new MenuManager("&File", IWorkbenchActionConstants.M_FILE);
		MenuManager windowMenu = new MenuManager("&Window", IWorkbenchActionConstants.M_WINDOW); //$NON-NLS-1$
		MenuManager helpMenu = new MenuManager("&Help", IWorkbenchActionConstants.M_HELP); //$NON-NLS-1$

		menuBar.add(fileMenu);
		menuBar.add(windowMenu);
		// Add a group marker indicating where action set menus will appear.
		menuBar.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
		menuBar.add(helpMenu);

		// File
		fileMenu.add(exitAction);

		// Window
		
		 MenuManager perspMenu = new PerspectiveMenuManager();
		menuBar.add(windowMenu);
		windowMenu.add(perspMenu);
		windowMenu.add(preferenceAction);
		windowMenu.add(new ShowViewAction());
		// Help
		helpMenu.add(aboutAction);
	}

	private static final String[] ACTIONSETID = new String[] {
//        "org.eclipse.ui.NavigateActionSet", //$NON-NLS-1$ //$NON-NLS-2$
        "org.eclipse.ui.WorkingSetActionSet", //$NON-NLS-1$ //$NON-NLS-2$
        "org.eclipse.update.ui.softwareUpdates",
//        "org.eclipse.ui.actionSet.keyBindings", //$NON-NLS-1$ //$NON-NLS-2$
        "org.eclipse.ui.edit.text.actionSet.openExternalFile", //$NON-NLS-1$
        "org.eclipse.ui.edit.text.actionSet.convertLineDelimitersTo" }; //$NON-NLS-1$ 
	
	private void beforefillMenuBar() {
		this.removeAction();
	}
	
	/**
	 * remove the unnecessary actions.
	 */
	@SuppressWarnings("restriction") //$NON-NLS-1$
	private void removeAction() {
		ActionSetRegistry reg = WorkbenchPlugin.getDefault()
				.getActionSetRegistry();
		IActionSetDescriptor[] actionSets = reg.getActionSets();
		List list = Arrays.asList(ACTIONSETID);
		for (int i = 0; i < actionSets.length; i++) {
			if (list.contains(actionSets[i].getId())) {
				IExtension ext = actionSets[i].getConfigurationElement()
						.getDeclaringExtension();
				reg.removeExtension(ext, new Object[] { actionSets[i] });
			}
		}

	}

}
