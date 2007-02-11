package com.quantum.model;

/**
 * 
 * @author bcholmes
 */
public interface Column {
	/**
	 * @return	The size of the column
	 */
	public long getSize();
	/**
	 * @return	The ordinal of this column in the primary key, counting from 1.
	 * 			0 if the column is not part of the primary key. 
	 */
	public int getPrimaryKeyOrder();
    /**
     * @return	true if the column is part of the primary key. False if not
     */
    public boolean isPrimaryKey();
	/**
	 * @return The name of the column
	 */
	public String getName();
	/**
	 * @return the number of fractional digits of the column
	 */
	public int getNumberOfFractionalDigits();
	/**
	 * @return	The local (to database) type name of the column
	 */
	public String getTypeName();
	/**
	 * @return true if the column is a real name, with fractional digits
	 */
	public boolean isReal();
    /**
     * @return true if the column can be null, false if not
     */
    public boolean isNullable();
	/**
	 * @return true if the column is a number, false if not
	 */
	public boolean isNumeric();
	/**
	 * @return the java.sql.Types type number for the column
	 */
	public int getType();
	/**
	 * @return	The parent entity of the column. Null if none.
	 */
	public Entity getParentEntity();
    /**
     * @return The remarks of the column
     */
    public String getRemarks();
    /**
     * @return The ordinal of this column in the table or view column list
     */
    public int getPosition();
	/**
	 * @return	The default value to assign to this column when a new row is added
	 */
	public String getDefaultValue();
}
