package com.quantum.actions;

import java.util.Iterator;

import com.quantum.Messages;
import com.quantum.view.bookmark.ColumnNode;
import com.quantum.view.subset.SubsetView;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

/**
 * Deletes the selected columns from the Subset items (Tables)
 * @author root
 *
 */
public class DeleteColumnAction extends Action implements IViewActionDelegate  {
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
		StructuredSelection selection = view.getSelection();
		Iterator iter = selection.iterator();
		if (! MessageDialog.openConfirm(view.getSite().getShell(), 
						Messages.getString("DeleteColumnAction.DeleteColumns"),  //$NON-NLS-1$
						Messages.getString("DeleteColumnAction.ConfirmDeleteColumns") )) //$NON-NLS-1$
			return;
		
		while (iter.hasNext()) {
			Object current = iter.next();
			if (current instanceof ColumnNode) {
				ColumnNode column = (ColumnNode) current;
				if (column != null) {
					view.deleteColumn(column);
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
