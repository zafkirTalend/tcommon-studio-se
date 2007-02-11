/*
 * Created on 9/02/2005
 *
*/
package com.quantum.adapters;

/**
 * @author panic
 *
 */
public class DerbyAdapter extends DatabaseAdapter {
	protected DerbyAdapter() {
		super(AdapterFactory.DERBY);
	}

	public String getCreateStatement(String schema, String viewName) {
		return "SELECT VIEWDEFINITION "
				+ "FROM SYS.SysTables t, SYS.SysSchemas s, SYS.SysViews v "
				+ "WHERE  s.SCHEMANAME = '" + schema + "'" 
				+ " AND t.TABLENAME = '" + viewName + "'"
				+ " AND v.TABLEID = t.TABLEID and t.SCHEMAID = s.SCHEMAID";
		}

}
