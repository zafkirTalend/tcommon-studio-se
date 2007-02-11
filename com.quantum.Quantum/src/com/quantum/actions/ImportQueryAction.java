package com.quantum.actions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.quantum.ImageStore;
import com.quantum.Messages;
import com.quantum.QuantumPlugin;
import com.quantum.log.QuantumLog;
import com.quantum.view.SQLQueryView;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

/**
 * @author root
 *
 */
public class ImportQueryAction extends Action implements IViewActionDelegate {
	SQLQueryView view;
	FileDialog dialog;
	
	public ImportQueryAction() {
		setText(Messages.getString("sqlqueryview.importQuery"));
		setImageDescriptor(ImageStore.getImageDescriptor(ImageStore.IMPORT));
		setToolTipText(Messages.getString("sqlqueryview.importQuery"));
	}
	
	/**
	 * @see org.eclipse.ui.IViewActionDelegate#init(IViewPart)
	 */
	public void init(IViewPart view) {
		this.view = (SQLQueryView) view;
		dialog = new FileDialog(view.getSite().getShell(), SWT.OPEN);
		dialog.setFilterExtensions(new String[]{"*.sql", "*.ddl", "*.*"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		dialog.setFilterNames(new String[]{Messages.getString("filedialog.sqlFiles"), //$NON-NLS-1$
			Messages.getString("filedialog.ddlFiles"), Messages.getString("filedialog.allfiles")}); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * @see org.eclipse.ui.IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		run();
	}

	public void run() {
		dialog.setFilterPath(QuantumPlugin.getDefault().getPreferenceStore().getString("quantum.dialogs.importquery.path"));
		String filename = dialog.open();
		if (filename != null) {
			QuantumPlugin.getDefault().getPreferenceStore().setValue("quantum.dialogs.importquery.path", filename);

			try {
				File importFile = new File(filename);
				FileReader fileReader = new FileReader(importFile);
				BufferedReader reader = new BufferedReader(fileReader);
				String line;
				StringBuffer buffer = new StringBuffer();
				
				while ((line = reader.readLine()) != null) {
					buffer.append(line);
					buffer.append('\n');
				}
				view.setQuery(buffer.toString());
				reader.close();
			} catch (IOException e) {
				QuantumLog.getInstance().error("Problem importing query", e);
			}
		}
	}

	/**
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

}
