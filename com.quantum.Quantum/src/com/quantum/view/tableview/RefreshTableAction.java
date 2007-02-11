package com.quantum.view.tableview;

import java.sql.SQLException;

import com.quantum.ImageStore;
import com.quantum.Messages;
import com.quantum.sql.SQLResultSetResults;

import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.ui.IViewPart;

/**
 * Implements the "Refresh Table" action for the TableView view
 * 
 * @author root
 */
public class RefreshTableAction extends ResultSetAction {
	
	/**
	 * @param view
	 * @param selectionProvider
	 */
	public RefreshTableAction(IViewPart view, ISelectionProvider selectionProvider) {
		super(view, selectionProvider);
		setText(Messages.getString(getClass(), "text"));
		setImageDescriptor(ImageStore.getImageDescriptor(ImageStore.REFRESH));
		setToolTipText(Messages.getString(getClass(), "text"));
	}

	protected void executeResultSetAction(SQLResultSetResults results) throws SQLException {
		results.refresh(getConnection(results));
	}
}
