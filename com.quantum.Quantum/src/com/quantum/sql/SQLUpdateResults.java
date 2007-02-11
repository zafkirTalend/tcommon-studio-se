package com.quantum.sql;


/**
 * @author BC
 */
public class SQLUpdateResults extends SQLResults {
	private int updateCount = 0;
	
	public SQLUpdateResults(int updateCount) {
		this.updateCount = updateCount;
	}
	
	public int getUpdateCount() {
		return this.updateCount;
	}

	public boolean isResultSet() {
		return false;
	}
}
