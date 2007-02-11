/*
 * Created on 28-jul-2003
 *
 */
package com.quantum.view.tableview;

import com.quantum.ImageStore;
import com.quantum.Messages;
import com.quantum.QuantumPlugin;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;


public final class CopyAction extends Action {
	
	private static final String LINE_SEPARATOR = System.getProperty("line.separator");
	
	private final TableView view;
	
	public CopyAction(TableView view) {
		super();
		this.view = view;
		setText(Messages.getString(getClass(), "text"));
		setImageDescriptor(ImageStore.getImageDescriptor(ImageStore.COPY));
	}
	
	public void run() {
		Table table = getTable();
		if (table != null) {
			TableItem items[] = table.getSelection();
			StringBuffer text = new StringBuffer();
			TableColumn[] cols = table.getColumns();
			for (int j = 0; j < cols.length; j++) {
				text.append(cols[j].getText());
				text.append('\t');
			}
			text.append(LINE_SEPARATOR);
			for (int i = 0; i < items.length; i++) {
				int columns = table.getColumnCount();
				for (int col = 0; col < columns; col++) {
					text.append(items[i].getText(col));
					text.append('\t');
				}
				text.append(LINE_SEPARATOR);
			}
			if (text.length()>0) {
				QuantumPlugin.getDefault().getSysClip().setContents(
						new Object[] { text.toString()},
						new Transfer[] { TextTransfer.getInstance()});
			}
		}
	}
	
	private Table getTable() {
		ResultSetViewer viewer = this.view.getSelectedResultSetViewer();
		return viewer == null ? null : viewer.getTable();
	}
}