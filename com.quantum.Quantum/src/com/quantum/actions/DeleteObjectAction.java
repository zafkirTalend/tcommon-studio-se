package com.quantum.actions;

import java.util.Iterator;

import com.quantum.Messages;
import com.quantum.view.subset.ObjectNode;
import com.quantum.view.subset.SubsetNode;
import com.quantum.view.subset.SubsetView;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

/**
 * Deletes an entire object (Table) from the SubsetView
 * @author root
 *
 */
public class DeleteObjectAction extends Action implements IViewActionDelegate  {
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
								Messages.getString("DeleteObjectAction.DeleteSubset"),  //$NON-NLS-1$
								Messages.getString("DeleteObjectAction.ConfirmDeleteSubset") + node.getName()); //$NON-NLS-1$
				if (flag) {
					view.deleteCurrent();
				}
			}
		} else if (selection instanceof ObjectNode) {
			StructuredSelection allSelected = view.getSelection();
			Iterator iter = allSelected.iterator();
			if (! MessageDialog.openConfirm(view.getSite().getShell(), 
							Messages.getString("DeleteObjectAction.DeleteItems"),  //$NON-NLS-1$
							Messages.getString("DeleteObjectAction.ConfirmDeleteItems") )) //$NON-NLS-1$
				return;
		
			while (iter.hasNext()) {
				Object current = iter.next();
				if (current instanceof ObjectNode) {
					ObjectNode node = (ObjectNode) current;
					if (node != null) {
							view.deleteObject(node);
					}
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
