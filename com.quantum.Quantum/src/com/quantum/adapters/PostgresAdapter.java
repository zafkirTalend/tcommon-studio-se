package com.quantum.adapters;

import java.util.HashMap;
import java.util.Map;

import com.quantum.model.Bookmark;
import com.quantum.util.sql.TypesHelper;


public class PostgresAdapter extends DatabaseAdapter {
	protected PostgresAdapter() {
		super(AdapterFactory.POSTGRES);
	}
	public String getShowTableQuery(String qualifier) {
        return "SELECT SCHEMANAME, TABLENAME FROM PG_TABLES WHERE SCHEMANAME = '" 
            + qualifier + "'";
    }
    public String getShowViewQuery(String qualifier) {
        return "SELECT SCHEMANAME, VIEWNAME FROM PG_VIEWS WHERE SCHEMANAME = '" 
            + qualifier + "'";
    }
    public String getShowSequenceQuery(String qualifier) {
    	return "select pg_namespace.nspname, relname " +
    			"from pg_class, pg_namespace where relkind = 'S' " +
    			"and relnamespace = pg_namespace.oid " +
    			"and pg_namespace.nspname = '" + qualifier + "'";
    }
	public String getNextValue(String sequence, String owner) {
		return "SELECT NEXTVAL('" + getQualifiedName(owner, sequence) + "')";
	}
	public String getPrevValue(String sequence, String owner) {
		return "SELECT * FROM " + getQualifiedName(owner, sequence);
	}

	/**
	 * Quotes a string according to the type of the column 
	 * @param string to be quoted
	 * @param type according to java.sql.Types
	 * @return
	 */
	public String quote(Bookmark bookmark, String string, int type, String typeString) {
		// Booleans in PostgreSQL are queried "t" or "f", but require "true" or "false" when inputed.
		if (type == TypesHelper.BIT || type == TypesHelper.BOOLEAN ) // Postgresql seems to identify booleans as BITs
		{
			if (string.indexOf('t') >= 0 || string.indexOf('T') >= 0 )
				return "true";
			else if (string.indexOf('f') >= 0 || string.indexOf('F') >= 0 )
				return "false";
			else 
				return string;
		}
		// use the default (upper type)
		return super.quote(bookmark, string, type, typeString);
	}

    /**
     * @see com.quantum.adapters.DatabaseAdapter#getDefaultSchema(java.lang.String)
     */
    public String getDefaultSchema(String userid) {
        return "public";
    }

	public Map getDefaultConnectionParameters() {
		Map map = new HashMap();
		map.put("port", "5432");
		map.put("hostname", "localhost");
		return map;
	}

	public String getCreateStatement(String schema, String viewName) {
		return "select view_definition from information_schema.views "
			+ "where table_schema = '" + schema + "' "  
			+ "and table_name = '" + viewName + "'" ;
	}

}