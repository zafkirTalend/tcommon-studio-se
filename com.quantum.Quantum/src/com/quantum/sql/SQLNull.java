package com.quantum.sql;


/**
 * This class represents a null returned as part of an SQL result set.
 * 
 * @author BC Holmes
 */
public class SQLNull {
	
	public static final SQLNull SQL_NULL = new SQLNull();
	
	private SQLNull() {
	}
	
	public String toString() {
		return "<NULL>";
	}
}
