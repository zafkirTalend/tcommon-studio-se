/*
 * Created on Oct 19, 2004
 */
package com.quantum.model;

import com.quantum.util.Displayable;

/**
 * @author lleavitt
 */
public interface DatabaseObject extends BookmarkHolder, Comparable, 
		Displayable, DatabaseObjectMetadata {
    
    public static final String ALIAS_TYPE     = "ALIAS";
    public static final String TABLE_TYPE     = "TABLE";
    public static final String VIEW_TYPE      = "VIEW";
    public static final String SEQUENCE_TYPE  = "SEQUENCE";
    public static final String PACKAGE_TYPE   = "PACKAGE";
    public static final String PROCEDURE_TYPE = "PROCEDURE";
    public static final String COLUMN_TYPE	  = "COLUMN";
    public static final String CHECK_TYPE	  = "CHECK";
    public static final String VARIABLE_TYPE  = "VARIABLE";
    
    
    
    public static final String[] ALL_TYPES = { 
        ALIAS_TYPE, TABLE_TYPE, VIEW_TYPE, SEQUENCE_TYPE, 
        PACKAGE_TYPE, PROCEDURE_TYPE, COLUMN_TYPE };
    
    public String getType();

    /**
     * Returns a String with the qualified name of the Entity in the
     * format "schema.name". 
	 */
	public String getQualifiedName();

}
