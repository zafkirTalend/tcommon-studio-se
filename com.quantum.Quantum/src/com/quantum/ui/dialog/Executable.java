package com.quantum.ui.dialog;

import java.sql.SQLException;

import com.quantum.util.connection.ConnectionException;


/**
 * @author BC Holmes
 */
public interface Executable {

	public Object execute() throws SQLException, ConnectionException;

}
