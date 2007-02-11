package com.quantum.ui.dialog;

import java.sql.SQLException;

import com.quantum.Messages;
import com.quantum.util.Displayable;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;


/**
 * @author BC Holmes
 */
public class SQLExceptionDialog extends MessageDialog {

	private final SQLException sqlException;

	/**
	 * @param parentShell
	 * @param dialogTitle
	 * @param sqlException
	 */
	private SQLExceptionDialog(Shell parentShell, String dialogTitle, 
			SQLException sqlException) {
		super(parentShell, dialogTitle, null, sqlException.getLocalizedMessage(), ERROR,
				new String[]{IDialogConstants.OK_LABEL}, 0);
		this.sqlException = sqlException;
	}

	protected Control createCustomArea(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		Label label = new Label(composite, SWT.NONE);
		label.setText(Messages.getString(SQLExceptionDialog.class, "sqlState"));
		label = new Label(composite, SWT.NONE);
		label.setText(this.sqlException.getSQLState() == null 
				? "" : this.sqlException.getSQLState());

		label = new Label(composite, SWT.NONE);
		label.setText(Messages.getString(SQLExceptionDialog.class, "errorCode"));
		label = new Label(composite, SWT.NONE);
		label.setText(String.valueOf(this.sqlException.getErrorCode()));
		return composite;
	}
	
	public static void openException(Shell shell, Displayable displayable, SQLException sqlException) {
		SQLExceptionDialog dialog = new SQLExceptionDialog(shell, 
				displayable == null 
					? Messages.getString(SQLExceptionDialog.class, "title") 
					: Messages.getString(
							SQLExceptionDialog.class, "titleWithDisplayName", 
							new Object[] { displayable.getDisplayName() }), 
				sqlException);
		dialog.open();
	}
}
