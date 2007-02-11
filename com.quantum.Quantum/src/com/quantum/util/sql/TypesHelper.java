/* Created on Jan 9, 2004 */
package com.quantum.util.sql;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Types;

/**
 * This class provides some utilities for working with Types, as well as providing some
 * support around SQL types that exist in certain versions of the JDK.
 * 
 * @author BC Holmes
 * @see java.lang.Types
 */
public class TypesHelper {
	
	public static final int BIT = Types.BIT;
	public static final int TINYINT = Types.TINYINT;
	public static final int SMALLINT = Types.SMALLINT;
	public static final int INTEGER = Types.INTEGER;
	public static final int BIGINT = Types.BIGINT;
	public static final int FLOAT = Types.FLOAT;
	public static final int REAL = Types.REAL;
	public static final int DOUBLE = Types.DOUBLE;
	public static final int NUMERIC = Types.NUMERIC;
	public static final int DECIMAL = Types.DECIMAL;
	public static final int CHAR = Types.CHAR;
	public static final int VARCHAR = Types.VARCHAR;
	public static final int LONGVARCHAR = Types.LONGVARCHAR;
	public static final int DATE = Types.DATE;
	public static final int TIME = Types.TIME;
	public static final int TIMESTAMP = Types.TIMESTAMP;
	public static final int BINARY = Types.BINARY;
	public static final int VARBINARY = Types.VARBINARY;
	public static final int LONGVARBINARY = Types.LONGVARBINARY;
	public static final int NULL = Types.NULL;
	public static final int OTHER = Types.OTHER;
	public static final int JAVA_OBJECT = Types.JAVA_OBJECT;
	public static final int DISTINCT = Types.DISTINCT;
	public static final int STRUCT = Types.STRUCT;
	public static final int ARRAY = Types.ARRAY;
	public static final int BLOB = Types.BLOB;
	public static final int CLOB = Types.CLOB;
	public static final int REF = Types.REF;
	/**
	 * <p>The constant in the Java programming language, somtimes referred to 
	 * as a type code, that identifies the generic SQL type <code>DATALINK</code>.
	 * 
	 * <p>Note: For some reason, some versions of DB2 and/or the DB2 driver use an invalid 
	 * type code for DATALINK.  The correct value should be 70, but DB2/NT 7.01.00 uses
	 * -400.
	 */
	public static final int DATALINK;
	public static final int BOOLEAN;
	
	static {
		// These fields only exist in the JDK 1.4 version of the Types class.
		BOOLEAN = getType("BOOLEAN", 16);
		DATALINK = getType("DATALINK", 70);
	}
	
	public static boolean isFixedSize(int type) {
	    if (type == DECIMAL || type == NUMERIC || type == CHAR || type == VARCHAR
	            || type == LONGVARCHAR) {
	        return false;
	    } else {
	        return true;
	    }
	}
	
	private static int getType(String typeName, int defaultValue) {
		try {
			Field field = Types.class.getField(typeName);
			defaultValue = field.getInt(null);
		} catch (NoSuchFieldException e) {
		} catch (IllegalAccessException e) {
		}
		return defaultValue;
	}

    public static int getType(String typeName)
    {
        int defaultValue = 0;
        try {
            Field field = Types.class.getField(typeName);
            defaultValue = field.getInt(null);
        } catch (NoSuchFieldException e) {
        } catch (IllegalAccessException e) {
        }
        return defaultValue;
    }
	public static String getTypeName(int type) {
		String name = null;
		try {
			Field[] fields = TypesHelper.class.getFields();
			for (int i = 0, length = fields == null ? 0 : fields.length; 
					name == null && i < length; i++) {
				if (Modifier.isStatic(fields[i].getModifiers()) 
						&& Modifier.isPublic(fields[i].getModifiers()) 
						&& fields[i].getType() == Integer.TYPE 
						&& type == fields[i].getInt(null)) {
					name = fields[i].getName();
				}
			}
		} catch (IllegalAccessException e) {
		}
		return name;
	}

	/**
	 * True if the type is Real (numeric and with decimal part) according to java.sql.Types
	 * @param type
	 * @return
	 */
	public static boolean isReal(int type) {
		return (type == DECIMAL || type == DOUBLE || type == FLOAT || 
			type == NUMERIC || type == REAL );
	}

	/**
	 * True if the type is Numeric according to java.sql.Types
	 * @param type
	 * @return
	 */
	public static boolean isNumeric(int type) {
		return (type == DECIMAL || type == DOUBLE || type ==FLOAT || 
			type == NUMERIC || type == REAL || type == BIGINT ||
			type == TINYINT || type == SMALLINT || type == INTEGER );
	}

	/**
	 * True if the type is textual according to java.sql.Types
	 * @param type
	 * @return
	 */
	public static boolean isText(int type) {
		return ( type == CHAR || type == VARCHAR || type == LONGVARCHAR
				|| type == BINARY || type == VARBINARY || type == LONGVARBINARY 
				 || type == CLOB || type == BLOB);
	}
	
    public static boolean isDate(int type) {
        return (type == DATE || type == TIME || type == TIMESTAMP );
    }
    
    public static String formatType(int type, long size, int precision){
        String answer = "";// we want the database type
        if(isFixedSize(type)){
            return answer;
        }else{
            if(isText(type)){
                answer += "(" + size + ")";
                return answer;
            }else if(isNumeric(type)){
                if(isReal(type)){
                    answer += "(" + size + ", " + precision + ")";
                    return answer;
                }else{
                    answer += "(" + size + ")";
                    return answer;
                }
            }else{
                return answer; // do not know what to do.
            }
        }
    }

    public static String formatType(String sType, long size, int precision){
        int type = getType(sType);
        System.out.println("In:" + sType + " Out: " + type);
        return formatType(type, size, precision);
    }
}
