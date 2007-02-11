package com.quantum.sql;

import java.util.Date;


/**
 * This class holds an SQL date type (java.sql.Date, java.sql.Time, or 
 * java.sql.Timestamp) along with the server-specific string representation.
 * 
 * @author BC Holmes
 */
public class SQLDate {

	private Date date;
	private String serverSpecificStringRepresentation;
	
	/**
	 * @param date
	 * @param serverSpecificStringRepresentation
	 */
	public SQLDate(Date date, String serverSpecificStringRepresentation) {
		super();
		this.date = date;
		this.serverSpecificStringRepresentation = serverSpecificStringRepresentation;
	}
	public Date getDate() {
		return this.date;
	}
	public String getServerSpecificStringRepresentation() {
		return this.serverSpecificStringRepresentation;
	}
	
	public String toString() {
	    if (this.serverSpecificStringRepresentation != null && 
	            this.serverSpecificStringRepresentation.trim().length() > 0) {
	        return this.serverSpecificStringRepresentation;
	    } else if (this.date != null) {
	        return this.date.toString();
	    } else {
	        return "";
	    }
	}
}
