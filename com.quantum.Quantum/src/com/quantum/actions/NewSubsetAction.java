package com.quantum.actions;

import com.quantum.Messages;
import com.quantum.view.subset.SubsetNode;
import com.quantum.view.subset.SubsetView;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

/**
 * @author root
 *
 */
public class NewSubsetAction extends Action implements IViewActionDelegate {
   IViewPart view;
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
		run();
	}
	public void run() {
		System.out.println(Messages.getString("NewSubsetAction.CreatingNewSubset")); //$NON-NLS-1$
		InputDialog dialog = new InputDialog(view.getSite().getShell(), 
			Messages.getString("NewSubsetAction.NameOfNewSubset"), Messages.getString("NewSubsetAction.PleaseEnterName"), null, null); //$NON-NLS-1$ //$NON-NLS-2$
		dialog.open();
		String value = dialog.getValue();
		if (value != null) {
			SubsetNode subset = new SubsetNode(value);
			((SubsetView) view).addNewSubset(subset);
		}
			
	}

	/**
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

}
