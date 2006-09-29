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

import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.talend.repository.ui.login.LoginDialog;

/**
 * Displays the Login Dialog for choose a project to Open. <br/>
 * 
 * $Id$
 * 
 */
public class SwitchProjectAction extends Action {

    private static final String PERSPECTIVE_ID = "org.talend.rcp.perspective";

    /**
     * Constructs a new SwitchProjectAction.
     */
    public SwitchProjectAction() {
        super("Switch &Project...");
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {

        //The prefered method Actually is a restart : 
        PlatformUI.getWorkbench().restart();
        
        //But the logically Method will be :
        //The Problem is that the RunProcess view is always "No process to run"
        
        // After loginDialog are open, the Ok Button must to open the SelectedProject.
//        logUserOnProject(new Shell());
//        try {
//            IWorkbench workbench = PlatformUI.getWorkbench();
//            workbench.openWorkbenchWindow(PERSPECTIVE_ID, null);
//            
//            RepositoryContext repositoryContext = (RepositoryContext) Context.getContext().getProperty(Context.REPOSITORY_CONTEXT_KEY);
//
//            IRepositoryView repositoryView = (IRepositoryView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
//                    .findView(RepositoryView.VIEW_ID);
//
//            repositoryView.setRepositoryContext(repositoryContext);
//
//            RefreshAction refreshAction = new RefreshAction(repositoryView);
//            refreshAction.run();
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            for (int i = 1; i <= PlatformUI.getWorkbench().getWorkbenchWindows().length; i++) {
//                PlatformUI.getWorkbench().getWorkbenchWindows()[0].close();
//            }
//        }
    }
    
    private boolean logUserOnProject(Shell shell) {
        boolean logged = false;
        LoginDialog loginDialog = new LoginDialog(shell);
        logged = loginDialog.open() == LoginDialog.OK;
        return logged;
    }
}
