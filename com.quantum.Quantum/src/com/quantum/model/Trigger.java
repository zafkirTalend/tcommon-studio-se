/*
 * Created on 1/03/2005
 * Data for the triggers of the database. Very database dependent.
 */
package com.quantum.model;

/**
 * @author Julen
 *
 */
public interface Trigger {

	/**
	 * @return The name of the trigger
	 */
	public String getName(); 
	/**
	 * @return If the trigger shoots After or Before or Instead of
	 */
	public String getMoment();
	
	/**
	 * @return For Each clause of the trigger
	 */
	public String getForEach();
	
	/**
	 * @return The event that will execute the trigger (Insert, Update, Delete...)
	 */
	public String getEvent();
	/**
	 * @return The column name if it's a column trigger. Null if not.
	 */
	public String getColumnName();
	/**
	 * @return How the trigger will refer to old and new values
	 */
	public String getReferencing();
	/**
	 * @return The languaje of the trigger or null if not applicable
	 */
	public String getLanguage();
	/**
	 * @return The When clause of the trigger, null if none.
	 */
	public String getWhenClause();
	/**
	 * @return Status (Enabled or Disabled) of the trigger
	 */
	public String getStatus();
	/**
	 * @return The Action to execute, if it's a call or a program, etc
	 */
	public String getActionType();
	/**
	 * @return The body of the trigger, the code that executes when the trigger is called
	 */
	public String getBody();
}
