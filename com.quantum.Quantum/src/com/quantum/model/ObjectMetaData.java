/*
 * Created on 8/04/2003
 *
 */
package com.quantum.model;

import java.util.Vector;

import com.quantum.util.StringMatrix;


/**
 * Class to hold the Metadata of a database element
 * @author panic
 */
public class ObjectMetaData {
	private StringMatrix columns = new StringMatrix();
	private StringMatrix primaryKeys = new StringMatrix();
	private StringMatrix foreignKeys = new StringMatrix();
	private StringMatrix indexInfo = new StringMatrix();
	private StringMatrix bestRowId = new StringMatrix();
	
	
	/**
	 * Gives the order of the column in the primary key
	 * @param column
	 * @return the order of the column in the primary key, 0 if it's not part of it.
	 */
	public int getPrimaryKeyOrder(String column){
		if (primaryKeys.size() == 0) return 0;
		StringMatrix keyColumns = primaryKeys.select("COLUMN_NAME", column); //$NON-NLS-1$
		if (keyColumns != null && keyColumns.size() > 0) {
			 String index = keyColumns.get("KEY_SEQ", 0); // We suppose there is only a primary key //$NON-NLS-1$
			 if (index != null ) return Integer.parseInt(index);
		}
		return 0;
	}

	/**
	 * Gives the type of the column
	 * @param column
	 * @return the type of the column using the values defined in java.sql.Types
	 */
	public int getColumnType(String column){
		StringMatrix selectCol = columns.select("COLUMN_NAME", column); //$NON-NLS-1$
		if (selectCol != null && selectCol.size() > 0) {
			 String type = selectCol.get("DATA_TYPE", 0); // It should be only one column //$NON-NLS-1$
			 if (type != null ) return Integer.parseInt(type);
		}
		return 0;
		
	}

	/**
	 * Returns a String with the names of the columns, separated by commas 
	 */
	public String getColumnsString() {
		String result = ""; //$NON-NLS-1$
		Vector columnNames = columns.getColumn("COLUMN_NAME"); //$NON-NLS-1$
		for (int i = 0; i < columnNames.size(); i++) {
			if (i > 0) result += ", "; //$NON-NLS-1$
			result += (String) columnNames.get(i);
		}
		return result;
	}
	
	/**
	 * Returns a vector of Strings with the names of the columns
	 */
	public Vector getColumnNamesVector() {
		Vector result = new Vector();
		Vector columnNames = columns.getColumn("COLUMN_NAME"); //$NON-NLS-1$
		for (int i = 0; i < columnNames.size(); i++) {
			result.add(columnNames.get(i)); 
		}
		return result;
	}

	// Inmediate getters and setters

	/**
	 * @param matrix
	 */
	public void setColumns(StringMatrix matrix) {
		columns = matrix;
	}
	/**
	 * @param matrix
	 */
	public void setForeignKeys(StringMatrix matrix) {
		foreignKeys = matrix;
	}

	/**
	 * @param matrix
	 */
	public void setPrimaryKeys(StringMatrix matrix) {
		primaryKeys = matrix;
	}
	/**
	 * @param matrix
	 */
	public void setBestRowId(StringMatrix matrix) {
		bestRowId = matrix;
	}
	
	/**
	 * @param matrix
	 */
	public void setIndexInfo(StringMatrix matrix) {
		indexInfo = matrix;
	}

	/**
	 * @return
	 */
	public StringMatrix getColumns() {
		return columns;
	}

	/**
	 * @return
	 */
	public StringMatrix getForeignKeys() {
		return foreignKeys;
	}

	/**
	 * @return
	 */
	public StringMatrix getPrimaryKeys() {
		return primaryKeys;
	}

	
	/**
	 * @return
	 */
	public StringMatrix getIndexInfo() {
		return indexInfo;
	}
	/**
	 * @return
	 */
	public StringMatrix getBestRowId() {
		return bestRowId;
	}


	/**
	 * @param column
	 */
	public void dropColumn(String columnName) {
		columns.dropMatching("COLUMN_NAME", columnName); //$NON-NLS-1$
	}

	// Common Object interface
	
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public Object clone() {
		ObjectMetaData result = new ObjectMetaData();
		result.columns = (StringMatrix) columns.clone();
		result.primaryKeys = (StringMatrix) primaryKeys.clone();
		result.indexInfo = (StringMatrix) indexInfo.clone();
		result.foreignKeys = (StringMatrix) foreignKeys.clone();
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof ObjectMetaData)) return false;
		ObjectMetaData obMd = (ObjectMetaData) obj;
		return (columns.equals(obMd.columns) && 
				primaryKeys.equals(obMd.primaryKeys) &&
				indexInfo.equals(obMd.indexInfo) &&
				foreignKeys.equals(obMd.foreignKeys) );
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return (	columns.toString() + 
					foreignKeys.toString() + 
					indexInfo.toString() + 
					primaryKeys.toString());
	}

	public String getQualifiedTableName(){
			if (columns.size() < 1) return "";
			String result = columns.get("TABLE_NAME", 0);
			String schema = columns.get("TABLE_SCHEM", 0);
			if (schema != null && schema.length() > 0)
				result = schema + "." + result;
			return result;

	}


}
