package com.quantum.util.sql;


/**
 * @author BC Holmes
 */
public abstract class SQLStates {
	
	/**
	 * The driver does not support the requested operation.  This SQL code is returned 
	 * by MS Access (and possibly others) when calling JDBC functions that are not 
	 * implemented by the driver.
	 */
	public static final String ODBC_DRIVER_NOT_CAPABLE = "S1C00";

	private SQLStates() {
	}
}
