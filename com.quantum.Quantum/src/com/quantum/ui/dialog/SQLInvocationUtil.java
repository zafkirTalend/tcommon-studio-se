package com.quantum.ui.dialog;

import java.sql.SQLException;

import com.quantum.util.connection.Connectable;
import com.quantum.util.connection.ConnectionException;

import org.eclipse.swt.widgets.Shell;


/**
 * @author BC Holmes
 */
public class SQLInvocationUtil {

	public static Object execute(
			Shell shell, Connectable connectable, Executable executable) {
		Object result = null;
		try {
			result = executable.execute();
        } catch (ConnectionException e) {
        	if (e.getCause() != null && e.getCause() instanceof SQLException) {
        		SQLExceptionDialog.openException(shell, connectable, (SQLException) e.getCause());
        	} else {
	            ExceptionDisplayDialog.openError(shell, null, null, e);
        	}
        } catch (SQLException e) {
       		SQLExceptionDialog.openException(shell, connectable, e);
        }
        return result;
	}
}
