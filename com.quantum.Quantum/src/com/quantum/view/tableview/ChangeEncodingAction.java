package com.quantum.view.tableview;

import java.sql.SQLException;

import com.quantum.Messages;
import com.quantum.sql.SQLResultSetResults;

import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.ui.IViewPart;


/**
 * @author BC
 */
public class ChangeEncodingAction extends ResultSetAction {

	private final String encoding;

	/**
	 * @param view
	 * @param selectionProvider
	 */
	public ChangeEncodingAction(IViewPart view, ISelectionProvider selectionProvider, String encoding, String key) {
		super(view, selectionProvider);
		this.encoding = encoding;
		setText(key + " " + Messages.getString(getClass(), "encoding"));
		setToolTipText(key + " " + Messages.getString(getClass(), "encoding"));
	}

	/* (non-Javadoc)
	 * @see com.quantum.view.tableview.ResultSetAction#executeResultSetAction(com.quantum.sql.SQLResultSetResults)
	 */
	protected void executeResultSetAction(SQLResultSetResults results)
			throws SQLException {
		results.setEncoding(this.encoding);
		results.refresh(getConnection(results));
	}

}
