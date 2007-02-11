/*
 * Created on 5/03/2005
 *	Data for the check constraints of the database. Very database dependent.
 */
package com.quantum.model;

/**
 * @author Julen
 *
 */
public interface Check {

	/**
	 * @return	The name of the check.
	 */
	public String getName();
	/**
	 * @return	The body of the constraint. 
	 * 			Usually code for the database to execute.
	 */
	public String getBody();
}
