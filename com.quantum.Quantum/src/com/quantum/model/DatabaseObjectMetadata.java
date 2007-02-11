/*
 * Created on 01.02.2005
 * Interface to represent the metadata for a database object
 */
package com.quantum.model;

/**
 * @author Julen
 * 
 */
public interface DatabaseObjectMetadata {

	/**
	 * @return The name of the object, without schemas. Could be null.
	 */
	public String getName();

	/**
	 * @return The schema of the object. Could be null.
	 */
	public String getSchema();

	/**
	 * @return The name of the object, qualifyed by the schema if needed. 
	 * Empty string if no data. Cannot be null.
	 */
	public String getQualifiedName();

	/**
	 * @return True if the object is a synonym instead of a real object, false if not
	 */
	public boolean isSynonym();
}