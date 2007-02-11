/*
 * Created on 9/02/2005
 *
*/
package com.quantum.adapters;

/**
 * @author panic
 *
 */
public class HsqldbAdapter extends DatabaseAdapter {
	protected HsqldbAdapter() {
		super(AdapterFactory.HSQLDB);
	}

	public String getCreateStatement(String schema, String viewName) {
		return "SELECT VIEW_DEFINITION "
				+ "FROM SYSTEM_VIEWS "
				+ "WHERE TABLE_NAME = '" + viewName + "'";
		}

}
