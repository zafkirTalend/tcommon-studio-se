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
package org.talend.core.token;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

/**
 * ggu class global comment. Detailled comment
 * 
 * will delete later
 */
public class TmpCollectTokenAction extends Action implements IWorkbenchWindowActionDelegate {

    public TmpCollectTokenAction() {
        super();
        this.setText("Collect Token");
        this.setToolTipText(this.getText());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
     * org.eclipse.jface.viewers.ISelection)
     */
    public void selectionChanged(IAction arg0, ISelection arg1) {

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#dispose()
     */
    public void dispose() {

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#init(org.eclipse.ui.IWorkbenchWindow)
     */
    public void init(IWorkbenchWindow arg0) {

    }

    public void run(IAction arg0) {
        run();

    }

    @Override
    public void run() {
        final boolean valid = TokenCollectorFactory.getFactory().process();

        if (valid) {
            MessageDialog.openInformation(Display.getCurrent().getActiveShell(), "Ok", "token is ok");
        } else {
            MessageDialog.openError(Display.getCurrent().getActiveShell(), "Error", "token is wrong");
        }
    }

}
