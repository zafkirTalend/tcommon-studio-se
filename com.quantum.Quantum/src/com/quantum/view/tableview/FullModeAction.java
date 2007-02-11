package com.quantum.view.tableview;

import java.sql.SQLException;

import com.quantum.ImageStore;
import com.quantum.Messages;
import com.quantum.sql.SQLResultSetResults;
import com.quantum.sql.Scrollable;

import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IViewPart;


/**
 * @author BC
 */
public class FullModeAction extends ResultSetAction {

	/**
	 * @param view
	 * @param selectionProvider
	 */
	public FullModeAction(IViewPart view, ISelectionProvider selectionProvider) {
		super(view, selectionProvider);
		setText(Messages.getString(getClass(), "text"));
		setImageDescriptor(ImageStore.getImageDescriptor(ImageStore.FULLDATA));
		setToolTipText(Messages.getString(getClass(), "text"));
	}

	protected void executeResultSetAction(SQLResultSetResults results) throws SQLException {
		((Scrollable) results).setFullMode(!((Scrollable) results).isFullMode());
		results.refresh(getConnection(results));
	}
	
	
	protected boolean updateSelection(IStructuredSelection selection) {
		return super.updateSelection(selection) 
				&& (selection.getFirstElement() instanceof Scrollable);
	}
}
