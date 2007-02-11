/*
 * Created on 28-jul-2003
 *
 */
package com.quantum.view.tableview;

import com.quantum.Messages;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Table;


public final class SelectAllAction extends Action {
	
	private final TableView view;
	
	public SelectAllAction(TableView view) {
		super();
		this.view = view;
		setText(Messages.getString(getClass(), "text"));
	}
	public void run() {
		Table table = getTable();
		if (table != null) {
			table.selectAll();
		}
	}
	
	private Table getTable() {
		ResultSetViewer viewer = this.view.getSelectedResultSetViewer();
		return viewer == null ? null : viewer.getTable();
	}
}