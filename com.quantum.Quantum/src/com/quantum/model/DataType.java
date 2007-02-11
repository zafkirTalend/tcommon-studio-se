package com.quantum.model;

import com.quantum.util.sql.TypesHelper;


/**
 * This class represents a data type.  Columns in databases can be of particular types.
 * 
 * @author BC
 */
public class DataType {
	private final int javaType;
	private final String databaseTypeName;
	private final long precision;
	private final String literalPrefix;
	private final String literalSuffix;
	private final String createParameters;
	private final short searchable;

	
	/**
	 * @param javaType
	 * @param databaseTypeName
	 * @param precision
	 * @param literalPrefix
	 * @param literalSuffix
	 * @param createParameters
	 */
	public DataType(final int javaType, final String databaseTypeName,
			final long precision, final String literalPrefix, final String literalSuffix,
			final String createParameters, final short searchable ) {
		super();
		this.javaType = javaType;
		this.databaseTypeName = databaseTypeName;
		this.precision = precision;
		this.literalPrefix = literalPrefix;
		this.literalSuffix = literalSuffix;
		this.createParameters = createParameters;
		this.searchable = searchable;
	}
	public String getDatabaseTypeName() {
		return this.databaseTypeName;
	}
	public int getJavaType() {
		return this.javaType;
	}
	public String getJavaNameType() {
		return TypesHelper.getTypeName(this.javaType);
	}
	public String getCreateParameters() {
		return this.createParameters;
	}
	public String getLiteralPrefix() {
		return this.literalPrefix;
	}
	public String getLiteralSuffix() {
		return this.literalSuffix;
	}
	public long getPrecision() {
		return this.precision;
	}
	/**
	 * @return Returns if the type is searchable.
	 */
	public short getSearchable() {
		return searchable;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (! (obj instanceof DataType))
			return false;
		if (((DataType)obj).javaType == this.javaType)
			return true;
		else
			return false;
	}
	
}
