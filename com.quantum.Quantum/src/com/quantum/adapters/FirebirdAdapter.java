package com.quantum.adapters;

import java.util.HashMap;
import java.util.Map;


/**
 * @author bcholmes
 */
public class FirebirdAdapter extends DatabaseAdapter {

	/**
	 * @param type
	 */
	protected FirebirdAdapter() {
		super(AdapterFactory.FIREBIRD);
	}

	   public Map getDefaultConnectionParameters() {
    	Map map = new HashMap();
    	map.put("port", "3050");
    	map.put("hostname", "localhost");
    	return map;
    }
  
	   public String getCreateStatement(String schema, String viewName) {
	   	return "SELECT RDB$VIEW_SOURCE FROM RDB$RELATIONS "
		+ "WHERE RDB$RELATION_NAME = '" + viewName + "'";
	   }

}
