/*
 * Created on 18/02/2005
 * Constants for JDBC
 */
package com.quantum.util.connection;

/**
 * @author Julen
 *
 */
public interface JDBCUtil {
	// The JDBC-ODBC Driver is more happy if you look up metadata values
	// using the column number than if you use the column name
	
	
    
    public static final int INDEX_METADATA_NOT_UNIQUE = 4;
	public static final int INDEX_METADATA_INDEX_NAME = 6;
    public static final int INDEX_METADATA_TYPE = 7;
	public static final int INDEX_METADATA_COLUMN_NAME = 9;
	public static final int INDEX_METADATA_ASC_OR_DESC = 10;
	
	public static final int PRIMARY_KEYS_METADATA_COLUMN_NAME = 4;
	public static final int PRIMARY_KEYS_METADATA_KEY_SEQ = 5;
	
	public static final int COLUMN_METADATA_SCHEMA_NAME = 2;
	public static final int COLUMN_METADATA_TABLE_NAME = 3;
	public static final int COLUMN_METADATA_COLUMN_NAME = 4;
	public static final int COLUMN_METADATA_DATA_TYPE = 5;
	public static final int COLUMN_METADATA_TYPE_NAME = 6;
	public static final int COLUMN_METADATA_COLUMN_SIZE = 7;
	public static final int COLUMN_METADATA_DECIMAL_DIGITS = 9;
	public static final int COLUMN_METADATA_REMARKS = 12;
	public static final int COLUMN_METADATA_DEFAULT_VALUE = 13;
	public static final int COLUMN_METADATA_ORDINAL_POSITION = 17;
	public static final int COLUMN_METADATA_IS_NULLABLE = 18;
	
	public static final int SCHEMA_METADATA_TABLE_SCHEM = 1;
	
	public static final int TABLE_METADATA_TABLE_SCHEM = 2;
	public static final int TABLE_METADATA_TABLE_NAME = 3;
	
	public static final int TABLE_TYPE_METADATA_TABLE_TYPE = 1;

	public static final int FOREIGN_KEY_METADATA_PKTABLE_SCHEM = 2;
	public static final int FOREIGN_KEY_METADATA_PKTABLE_NAME = 3;
	public static final int FOREIGN_KEY_METADATA_PKCOLUMN_NAME = 4;
	public static final int FOREIGN_KEY_METADATA_FKTABLE_SCHEM = 6;
	public static final int FOREIGN_KEY_METADATA_FKTABLE_NAME = 7;
	public static final int FOREIGN_KEY_METADATA_FKCOLUMN_NAME = 8;
	public static final int FOREIGN_KEY_METADATA_KEY_SEQ = 9;
	public static final int FOREIGN_KEY_METADATA_UPDATE_RULE = 10;
	public static final int FOREIGN_KEY_METADATA_DELETE_RULE = 11;
	public static final int FOREIGN_KEY_METADATA_FK_NAME = 12;
	
	public static final int TYPE_INFO_METADATA_TYPE_NAME = 1;
	public static final int TYPE_INFO_METADATA_DATA_TYPE = 2;
	public static final int TYPE_INFO_METADATA_PRECISION = 3;
	public static final int TYPE_INFO_METADATA_LITERAL_PREFIX = 4;
	public static final int TYPE_INFO_METADATA_LITERAL_SUFFIX = 5;
	public static final int TYPE_INFO_METADATA_CREATE_PARMS = 6;
	public static final int TYPE_INFO_METADATA_SEARCHABLE = 9;
	
	public static final int PRIVILEGES_METADATA_GRANTOR = 5;
	public static final int PRIVILEGES_METADATA_GRANTEE = 6;
	public static final int PRIVILEGES_METADATA_ACCESS = 7;
	public static final int PRIVILEGES_METADATA_IS_GRANTABLE = 8;
	
}
