package com.quantum.view.tableview;


import java.util.Iterator;

import com.quantum.ImageStore;
import com.quantum.Messages;
import com.quantum.sql.SQLResultSetCollection;
import com.quantum.sql.SQLResultSetResults;
import com.quantum.util.versioning.VersioningHelper;

import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.actions.SelectionListenerAction;

/**
 * @author root
 * @author BC Holmes
 */
public class CloseResultSetAction extends SelectionListenerAction {
	
	private final ISelectionProvider selectionProvider;
	
	/**
	 * @param text
	 */
	public CloseResultSetAction(IViewPart view, ISelectionProvider selectionProvider) {
		super("");
		this.selectionProvider = selectionProvider;
		this.selectionProvider.addSelectionChangedListener(this);
		setEnabled(!this.selectionProvider.getSelection().isEmpty());
		
		setImageDescriptor(ImageStore.getImageDescriptor(ImageStore.CLOSE));
		if (VersioningHelper.isEclipse30()) {
			setDisabledImageDescriptor(ImageStore.getImageDescriptor(ImageStore.CLOSE_DISABLED));
		}
		setToolTipText(Messages.getString(getClass(), "text"));
	}

	public void run() {
		IStructuredSelection selection = 
			(IStructuredSelection) this.selectionProvider.getSelection();
		if (!selection.isEmpty()) {
			for (Iterator i = selection.iterator(); i.hasNext(); ) {
				SQLResultSetCollection.getInstance().removeSQLResultSet((SQLResultSetResults) i.next());
			}
		}
	}

	public boolean updateSelection(IStructuredSelection selection) {
        return !selection.isEmpty();
	}
}
