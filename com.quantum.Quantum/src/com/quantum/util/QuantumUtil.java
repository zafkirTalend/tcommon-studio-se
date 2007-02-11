/*
 * Created on 24/08/2003
 *
 */
package com.quantum.util;

import java.util.StringTokenizer;

/**
 * @author panic
 *
 */
public class QuantumUtil {
	/**
	 * Gets the first string in a string with the structure XXXX.XXXX
	 * @param qualifiedName
	 * @return The schema name if present, else an empty string ("")
	 */
	public static String getSchemaName(String qualifiedName) {
		StringTokenizer st = new StringTokenizer(qualifiedName, "."); //$NON-NLS-1$
		if (st.countTokens() > 1) {
			return st.nextToken();
		} else 
			return "";
	}

	/**
	 * Gets the second string in a string with the structure XXXX.XXXX
	 * @param qualifiedName
	 * @return The table name if present, else the received parameter
	 */
	public static String getTableName(String qualifiedName) {
		StringTokenizer st = new StringTokenizer(qualifiedName, "."); //$NON-NLS-1$
		if (st.countTokens() > 1) {
			st.nextToken();
			return st.nextToken();
		} else 
			return qualifiedName;
	}
	
	/**
	 * Appends the schema name and the table name, not quoting
	 * @param schemaName The schema name, may be null or empty string
	 * @param tableName The table name
	 * @return A string in the form <code>schema.table</code>, or <code>table</code> if schema void.
	 */
	public static String getQualifiedName(String schemaName, String tableName) {
		if (schemaName == null || schemaName.equals("")) 
			return tableName;
		else
			return schemaName + "." + tableName;
	}

	public static String trasposeEscape(String untrans){
		String trasposed = StringUtil.substituteString(untrans, "\\n", "\n");
		trasposed = StringUtil.substituteString(trasposed, "\\t", "\t");
		trasposed = StringUtil.substituteString(trasposed, "\\r", "\r");
		return trasposed;
	}
	public static String unTrasposeEscape(String trans){
		String unTrasposed = StringUtil.substituteString(trans, "\n", "\\n");
		unTrasposed = StringUtil.substituteString(unTrasposed, "\t", "\\t");
		unTrasposed = StringUtil.substituteString(unTrasposed, "\r", "\\r");
		return unTrasposed;
	}
	public static String quote(String tableName, String quote) {
		if (getSchemaName(tableName).equals(""))
			return quote + tableName + quote; //$NON-NLS-1$
		else
			return quote + getSchemaName(tableName) + quote + "." + 
					quote +	getTableName(tableName) + quote;

	}

	/**
	 * Converts text formatted for SQL to text formatted for java, inside a string literal.
	 * For example
	 * 	SELECT A
	 *  FROM B
	 * will become
	 * "SELECT A " +
	 * "FROM B";
	 * @param Text in SQL form
	 * @return Text in java form, inside quotes
	 */
	public static String convertSqlToJava(String text) {
		if (text.length() == 0) return "";
		// String literals need all " inside to be substituted by \\"
		String javaString = text.replaceAll("\"", "\\\\\"");
		// Each line has to be enclosed in quotes, and end in a +
		javaString = javaString.replaceAll("(?m)^(.*)$", "\\\"$1 \\\" \\+");
		// The last line will have a semicolon instead of a +;
		javaString = javaString.replaceAll("\\+$",";");
		return javaString;
	}

	/**
	 * Converts a LIKE regular expression to a java regular expression, so that
	 *  "%TEST_"
	 * will become
	 *  ".*TEST."
	 *  or rather something equivalent, but more complex to allow for other Java metacharacters to be present
	 * @param likeSearch
	 */
	public static String convertLikeToJavaRegexp(String likeSearch) {
		// Java has more metacharacters, those need to be escaped. Use que \Q \E characters to quote text pieces
		String javaRegexp = likeSearch.replaceAll("^(.*)$","\\\\Q$1\\\\E");
		// now the characters % will be substituted by .* , they shoudn't be inside the \Q\E so make it \E.*\Q
		javaRegexp = javaRegexp.replaceAll("%","\\\\E\\.\\*\\\\Q");
		// same for the _ character substituted by \E.\Q
		javaRegexp = javaRegexp.replaceAll("_","\\\\E\\.\\*\\\\Q");
		//eliminate empty quotation pairs
		javaRegexp = javaRegexp.replaceAll("\\\\Q\\\\E","");
		return javaRegexp;
		
	}

}
