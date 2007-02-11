/*
 * Created on 11/08/2003
 *
 */
package com.quantum.util;

import java.util.Vector;

/**
 * "record" class that holds an identifier and the resulting
 * string. This string comes from applying rules to the identifier
 * Also, can have a vector of lower-level classifiers.
 * 
 * @author panic
 *
 */
public class OneLevelConverter {
	private String id = "";
	private String result = "";
	private Vector lower = null;
	
	OneLevelConverter(String id) {
		this.id = id;
	}
	
	// Getters and setters
	/**
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return
	 */
	public Vector getLower() {
		return lower;
	}

	/**
	 * @return
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param string
	 */
	public void setId(String string) {
		id = string;
	}

	/**
	 * @param vector
	 */
	public void setLower(Vector vector) {
		lower = vector;
	}

	/**
	 * @param string
	 */
	public void setResult(String string) {
		result = string;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof OneLevelConverter)) return false;
		return this.id.equals(((OneLevelConverter)obj).id);
	}

}
