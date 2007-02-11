package com.quantum.ui.dialog;

import java.sql.Connection;
import java.sql.SQLException;

import com.quantum.Messages;
import com.quantum.util.connection.Connectable;
import com.quantum.util.connection.ConnectionException;
import com.quantum.util.connection.NotConnectedException;

import org.eclipse.swt.widgets.Shell;


/**
 * <p>This utility gets a connection from a bookmark, and handles any UI-specific
 * interactions such as providing messages to the user and/or prompting for a 
 * password.
 * 
 * @author BC Holmes
 */
public class ConnectionUtil {

    public Connection getConnection(Connectable connectable, Shell shell) {
        Connection connection = null;
        try {
            connection = connectable.getConnection();
        } catch (NotConnectedException e) {
            connection = connect(connectable, shell);
        }
        return connection;
    }

    public Connection connect(Connectable connectable, Shell shell) {
        Connection connection = null;
        try {
            connection = connectable.connect(PasswordDialog.createPasswordFinder(shell));
        } catch (ConnectionException e) {
        	if (e.getCause() != null && e.getCause() instanceof SQLException) {
        		SQLExceptionDialog.openException(shell, connectable, (SQLException) e.getCause());
        	} else {
	            ExceptionDisplayDialog.openError(shell, 
	                Messages.getString(getClass().getName() + ".title"), 
	                Messages.getString(getClass().getName() + ".message") +
					" (Bookmark:"+connectable.getDisplayName()+")", e);
        	}
        } catch (SQLException e) {
       		SQLExceptionDialog.openException(shell, connectable, e);
        }
        return connection;
    }

}
