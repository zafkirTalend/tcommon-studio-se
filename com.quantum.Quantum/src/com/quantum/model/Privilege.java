/* Created on 10/04/2005
 * Data for the Privileges of the tables and columns.
 */
package com.quantum.model;

public interface Privilege {
	/**
	 * @return The Grantor of the Privilege (the user that granted the privilege)
	 */
	public String getGrantor();
	/**
	 * @return The grantee of the privilege (the user to which the privilege is granted)
	 */
	public String getGrantee();
	/**
	 * @return The name of the granted access (SELECT, 
     *      INSERT, UPDATE, REFRENCES, ...) 
	 */
	public String getAccess();
	/**
	 * @return If the privilege can be granted to others by the grantee, or not
	 */
	public boolean isGrantable();
}
