package com.quantum.view.tableview;


import com.quantum.ImageStore;
import com.quantum.Messages;
import com.quantum.sql.SQLResultSetCollection;
import com.quantum.util.versioning.VersioningHelper;

import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.actions.SelectionListenerAction;

/**
 * @author root
 *
 */
public class CloseAllResultSetsAction extends SelectionListenerAction {
	private final ISelectionProvider selectionProvider;
	/**
	 * @param text
	 */
	public CloseAllResultSetsAction(IViewPart view, ISelectionProvider selectionProvider) {
		super("Close All");
		this.selectionProvider = selectionProvider;
		this.selectionProvider.addSelectionChangedListener(this);
		setEnabled(!this.selectionProvider.getSelection().isEmpty());
		
		setImageDescriptor(ImageStore.getImageDescriptor(ImageStore.CLOSE_ALL));
		if (VersioningHelper.isEclipse30()) {
			setDisabledImageDescriptor(ImageStore.getImageDescriptor(ImageStore.CLOSE_ALL_DISABLED));
		}
		setText(Messages.getString(getClass(), "text"));
		setToolTipText(Messages.getString(getClass(), "text"));
	}
	
	public void run() {
		SQLResultSetCollection.getInstance().removeAllSQLResultSet();
	}

	public boolean updateSelection(IStructuredSelection selection) {
        return !selection.isEmpty();
	}
}
