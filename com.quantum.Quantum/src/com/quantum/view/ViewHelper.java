/*
 * Created on 22-jul-2003
 *
 */
package com.quantum.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.SQLException;

import com.quantum.Messages;
import com.quantum.QuantumPlugin;
import com.quantum.log.QuantumLog;
import com.quantum.model.Bookmark;
import com.quantum.sql.MultiSQLServer;
import com.quantum.sql.SQLResults;
import com.quantum.ui.dialog.SQLExceptionDialog;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewPart;

/**
 * @author panic
 *
 */
public class ViewHelper {

	/** @deprecated */
	public static SQLResults tryGetResults(IViewPart view, Bookmark bookmark, Connection con, String query) {
		return tryGetResults(view.getSite().getShell(), bookmark, con, query);
	}

	/** @deprecated */
	public static SQLResults tryGetResults(Shell shell, Bookmark bookmark, Connection con, String query) {
		try {
			MultiSQLServer server = MultiSQLServer.getInstance();
			return server.execute(bookmark, con, query);
		} catch (SQLException e) {
			QuantumLog.getInstance().error(e.getLocalizedMessage(), e); //$NON-NLS-1$ //$NON-NLS-2$
            SQLExceptionDialog.openException(shell, bookmark, e);
			return null;
		}
	}
	
	public static FileOutputStream askSaveFile(String key, Shell shell) {
		return askSaveFile(key, shell, null, null);
	}
	/**
	 * Asks the user for a file to be saved. Uses a key to get the preferences and save the path.
	 * The preferences should be defined in the Messages file.
	 * @param key		The key to save the used directory. Different saved directories should have different keys
	 * @param shell		A Shell to have display context to show the dialog 
	 * @param filterExt		Extensions allowed ("*.txt")
	 * @param filterNames	Names of the filters ("Text files")
	 * @return - An already opened FileOutputStream, or NULL if nothing selected, dialog canceled or some error.
	 */
	public static FileOutputStream askSaveFile(String key, Shell shell, String[] filterExt, String[] filterNames) {
	
		FileDialog dialog = new FileDialog(shell, SWT.SAVE);
		
		if (filterExt == null) {
			dialog.setFilterExtensions(new String[]{Messages.getString("filedialog."+key+".filter"),
													Messages.getString("filedialog.allfiles.filter")}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			dialog.setFilterNames(new String[]{	Messages.getString("filedialog."+key+".name"),
												Messages.getString("filedialog.allfiles.name")}); //$NON-NLS-1$ //$NON-NLS-2$
		} else {
			dialog.setFilterExtensions(filterExt);
			dialog.setFilterNames(filterNames);
		}	
		
		dialog.setFilterPath(QuantumPlugin.getDefault().getPreferenceStore().getString("quantum.dialogs."+ key + ".path"));
		String filename = dialog.open();
		if (filename == null) return null;
		// We save the used path
		QuantumPlugin.getDefault().getPreferenceStore().setValue("quantum.dialogs."+ key + ".path", filename);
		
		FileOutputStream out = null;
		File target = new File(filename);
		if (target.exists() && Messages.getString("filedialog.options.ConfirmOverwrite").equals("y")) {
			boolean confirmOverwrite = 
				MessageDialog.openConfirm(shell, 
						Messages.getString("filedialog.OverwriteTitle"), 
						Messages.getString("filedialog.OverwriteMsgWithArgument", 
								new Object[] { target.getAbsoluteFile() } ));
			if (!confirmOverwrite) return null; 
		} 

		try {
			out = new FileOutputStream(target);
		} catch (FileNotFoundException e) {
			MessageDialog.openConfirm(shell, Messages.getString("filedialog.message.CannotOpenFileTitle"), //$NON-NLS-1$
						Messages.getString("filedialog.message.CannotOpenFileMessage") + filename+ //$NON-NLS-1$ 
						Messages.getString("filedialog.message.CannotOpenFileExplain"));  //$NON-NLS-1$ 

		}
		return out;		
	}	
}
