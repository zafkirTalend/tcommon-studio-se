package com.quantum.sql;

import org.eclipse.core.runtime.IAdaptable;



/**
 * @author BC
 */
public abstract class SQLResults implements IAdaptable {
	public abstract boolean isResultSet();

	private long time;

	private String query;
	
	public long getTime() {
		return this.time;
	}

	public void setTime(long time) {
		this.time = time;
	}
	
	public Object getAdapter(Class adapter) {
		return null;
	}
	
	
	/**
	 * 
	 * @return Returns the query that mede this SQLResults.
	 */
	public String getQuery() {
		return query;
	}
	/**
	 * @param query The query that generated this SQLResults.
	 */
	public void setQuery(String query) {
		this.query = query;
	}
}
