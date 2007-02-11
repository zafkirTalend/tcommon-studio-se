package com.quantum.actions;

import com.quantum.wizards.ExportBookmarkWizard;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

/**
 * @author root
 * Asks the user for a file name and saves there the bookmarks in xml format 
 */
public class ExportBookmarksAction implements IViewActionDelegate {
		
	private IViewPart view;

	/**
	 * @see org.eclipse.ui.IViewActionDelegate#init(IViewPart)
	 */
	public void init(IViewPart view) {
		this.view = view;		
	}

	/**
	 * @see org.eclipse.ui.IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
        
        ExportBookmarkWizard wizard = new ExportBookmarkWizard();
        WizardDialog dialog =
            new WizardDialog(this.view.getSite().getShell(), wizard);
        dialog.open();
	}
	/**
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

}
