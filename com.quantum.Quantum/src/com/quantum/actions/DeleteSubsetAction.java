package com.quantum.actions;

import com.quantum.Messages;
import com.quantum.view.subset.SubsetNode;
import com.quantum.view.subset.SubsetView;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

/**
 * @author root
 *	Deletes a complete subset from the subset view
 */
public class DeleteSubsetAction extends Action implements IViewActionDelegate  {
   	SubsetView view;
	/**
	 * @see org.eclipse.ui.IViewActionDelegate#init(IViewPart)
	 */
	public void init(IViewPart view) {
		this.view = (SubsetView) view;
	}

	/**
	 * @see org.eclipse.ui.IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		run();
	}
	
	public void run() {
		Object selection = view.getCurrent();
		if (selection instanceof SubsetNode) {
			SubsetNode node = (SubsetNode) selection;
			if (node != null) {
				boolean flag = MessageDialog.openConfirm(view.getSite().getShell(), 
								Messages.getString("DeleteSubsetAction.DeleteSubset"),  //$NON-NLS-1$
								Messages.getString("DeleteSubsetAction.ConfirmDeleteSubset") + node.getName()); //$NON-NLS-1$
				if (flag) {
					view.deleteCurrent();
				}
			}
		}
	}

	/**
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

}
