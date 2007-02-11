package com.quantum.sql.parser;


/**
 * @author BC
 */
public interface SQL {

	/**
	 * The command describes the type of SQL statement that is represented.  The command
	 * might be "SELECT" or "ALTER TABLE" or "GRANT"
	 * 
	 * @return
	 */
	public String getCommand();
	
	public boolean isComment();
}
