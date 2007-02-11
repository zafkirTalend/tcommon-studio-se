package com.quantum.view.tableview;

import java.sql.Connection;
import java.sql.SQLException;

import com.quantum.sql.SQLResultSetResults;
import com.quantum.ui.dialog.ConnectionUtil;
import com.quantum.ui.dialog.ExceptionDisplayDialog;
import com.quantum.ui.dialog.SQLExceptionDialog;

import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.actions.SelectionListenerAction;

/**
 * @author BC Holmes
 */
public abstract class ResultSetAction extends SelectionListenerAction {
	private IViewPart view;
	private ConnectionUtil connectionUtil = new ConnectionUtil();
	private final ISelectionProvider selectionProvider;
	
	/**
	 * @see org.eclipse.ui.IViewActionDelegate#init(IViewPart)
	 */
	public ResultSetAction(IViewPart view, ISelectionProvider selectionProvider) {
		super("");
		this.selectionProvider = selectionProvider;
		this.view = view;
		this.selectionProvider.addSelectionChangedListener(this);
	}

	public void run() {
		try {
			IStructuredSelection selection = 
				(IStructuredSelection) this.selectionProvider.getSelection();
			if (!selection.isEmpty()) {
				SQLResultSetResults results = (SQLResultSetResults) selection.getFirstElement();
				executeResultSetAction(results);
			}
		} catch (SQLException e) {
			SQLExceptionDialog.openException(getShell(), null, e);
		} catch (RuntimeException e) {
			ExceptionDisplayDialog.openError(getShell(), null, null, e);
		}
	}

	protected boolean updateSelection(IStructuredSelection selection) {
		return selection != null && !selection.isEmpty();
	}
	/**
	 * @param results
	 * @throws SQLException
	 */
	protected abstract void executeResultSetAction(SQLResultSetResults results) throws SQLException;

	/**
	 * @param results
	 * @return
	 */
	protected Connection getConnection(SQLResultSetResults results) {
		return this.connectionUtil.connect(results.getConnectable(), getShell());
	}

	/**
	 * @return
	 */
	private Shell getShell() {
		return this.view.getSite().getShell();
	}
}
