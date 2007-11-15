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

import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Shell;
import org.talend.rcp.i18n.Messages;
import org.talend.repository.ui.login.LoginDialog;

/**
 * Displays the Login Dialog for choose a project to Open. <br/>
 * 
 * $Id$
 * 
 */
public class OpenStudioAction extends Action {

    private static final String PERSPECTIVE_ID = "org.talend.rcp.perspective"; //$NON-NLS-1$
    
    /**
     * Constructs a new SwitchProjectAction.
     */
    public OpenStudioAction() {
        super(Messages.getString("OpenStudioAction.label")); //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        // After loginDialog are open, the Ok Button must to open the SelectedProject.
        logUserOnProject(new Shell());
        try {
            
//            PlatformUI.createAndRunWorkbench(PlatformUI.createDisplay(), new ApplicationWorkbenchAdvisor());
            
//            PlatformUI.getWorkbench().openWorkbenchWindow(PERSPECTIVE_ID, null);

//            RefreshAction refreshAction = new RefreshAction((IRepositoryView)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(RepositoryView.VIEW_ID));
//            refreshAction.run();
            
//            for (int i = 1; i <= PlatformUI.getWorkbench().getWorkbenchWindows().length; i++) {
//                PlatformUI.getWorkbench().getWorkbenchWindows()[0].close();
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private boolean logUserOnProject(Shell shell) {
        boolean logged = false;
        LoginDialog loginDialog = new LoginDialog(shell);
        logged = loginDialog.open() == LoginDialog.OK;
        return logged;
    }
}
