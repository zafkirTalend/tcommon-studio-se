/*
 * Created on 20.08.2004
 *
 *	A Virtual Result Set. It's virtual because it doesn't has cache,
 *	but rather is tightly coupled with its RecordSet object,
 *	and mainly gives some added functionality, for use in
 *	virtual tables. It has SQLResults as a superclass mainly
 *	for conceptual reasons, it could be standalone.
 */
package com.quantum.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import com.quantum.adapters.DatabaseAdapter;
import com.quantum.model.Bookmark;
import com.quantum.model.Entity;
import com.quantum.util.connection.NotConnectedException;
import com.quantum.util.sql.SQLInstructionBuilder;

/**
 * @author Julen
 *
 */
public class SQLVirtualResultSet extends SQLResults {
	// The resultSet will be based on a query, that can be changed
	private String query = "";
	private Statement statement = null;
	private ResultSet resultSet = null;
	private int knownNumberOfRows = 0;
	
	
	/* (non-Javadoc)
	 * @see com.quantum.sql.SQLResults#isResultSet()
	 */
	public SQLVirtualResultSet(Entity entity) {
		Bookmark bookmark = entity.getBookmark();
		DatabaseAdapter adapter = (bookmark == null)? null : bookmark.getAdapter();
		if (adapter == null) return;
		try {
			// Cannot use "SELECT *" because that is not updatable, at least in some JDBC drivers
			Initialize(adapter.buildSelect(entity, entity.getColumns(), null), 
						entity.getBookmark().getConnection(), 
						ResultSet.TYPE_SCROLL_SENSITIVE, 
						ResultSet.CONCUR_UPDATABLE);

		} catch (NotConnectedException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public SQLVirtualResultSet(Entity entity, int type, int concurrency) {
		Bookmark bookmark = entity.getBookmark();
		DatabaseAdapter adapter = (bookmark == null)? null : bookmark.getAdapter();
		if (adapter == null) return;
		try {
			// Cannot use "SELECT *" because that is not updatable, at least in some JDBC drivers
			Initialize(adapter.buildSelect(entity, entity.getColumns(), null), 
						entity.getBookmark().getConnection(), 
						type, 
						concurrency);

		} catch (NotConnectedException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @param string
	 * @param connection
	 */
	private void Initialize(String query, Connection connection, int type, int concurrency) {
		if (connection == null) return;
		this.query = query;
		try {
			statement = connection.createStatement(type, concurrency);
			resultSet = statement.executeQuery(query);
			// If the recordset is not empty, we know there is at least one row. This call may fall also
			// because the recordset is not of the correct type to run isLast, so the resultSet may be valid.
			if (type == ResultSet.TYPE_FORWARD_ONLY) return; 
			if (!resultSet.isLast())
				knownNumberOfRows = 1; 
		} catch (SQLException e) {
			return;
		}
		
		
	}
	
	public boolean isResultSet() {
		return true;
	}
	
	/**
	 * @return If the resultSet is open, that is, connected to a data source
	 */
	public boolean isOpen() {
		return (resultSet != null);
	}
	

	/**
	 * @return Returns the knowNumberOfRows.
	 */
	public int getKnownNumberOfRows() {
		return knownNumberOfRows;
	}
	/**
	 * @param index
	 * @param i
	 * @return
	 */
	public String getStringValue(int row, int column) {
		if (resultSet == null) return null;
		try {
			// 	Rows and columns in resultSets are 1-based
			resultSet.absolute(row+1);
			actKnownNumberOfRows();
			return resultSet.getString(column+1);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 
	 */
	private void actKnownNumberOfRows() {
		if (resultSet == null) return;
		int nRowsNow;
		try {
			nRowsNow = resultSet.getRow() + (resultSet.isLast() ? 0 : 1);
			knownNumberOfRows = Math.max( nRowsNow, knownNumberOfRows);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @param index
	 * @return
	 */
	public String[] getRowAsStringArray(int row) {
		if (resultSet == null) return null;
		try {
			resultSet.absolute(row+1);
			actKnownNumberOfRows();
			return getRowAsStringArray();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @return
	 * 
	 */
	public String[] getRowAsStringArray() {
		String[] resultArray = null;
		int numColumns;
		try {
			numColumns = resultSet.getMetaData().getColumnCount();
			resultArray = new String[numColumns];
			for (int i = 0; i <  numColumns; i++) {
				// 	Rows and columns in resultSets are 1-based
				resultArray[i] =  resultSet.getString(i+1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultArray;
	}
	
	/**
	 * 
	 */
	public boolean next(){
		try {
			return resultSet.next();
		} catch (SQLException e) {
			return false;
		}
	}
	public boolean isLast() {
		try {
			return resultSet.isLast();
		} catch (SQLException e) {
			return true;
		}
	}
	/**
	 * @return	Names of the Columns of the ResultSet, in the order given
	 */
	public String[] getColumnNames() {
		if (resultSet == null) return null;
		String resultArray[] = null;
		try {
			int numColumns = resultSet.getMetaData().getColumnCount();
			resultArray = new String[numColumns];
			for (int i = 0; i <  numColumns; i++) {
				// 	Rows and columns in resultSets are 1-based
				resultArray[i] =  resultSet.getMetaData().getColumnName(i+1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultArray;
	}
	/**
	 * @param columnIndex
	 * @param valueStr
	 */
	public boolean update(int row, int columnIndex, String valueStr) {
		if (row < 0 || columnIndex < 0 ) return false;
		try {
			resultSet.absolute(row+1);
			resultSet.updateString(columnIndex+1, valueStr);
			resultSet.updateRow();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	public void close(){
		try {
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Inserts a row using the InsertRow facility in JDBC 2.
	 * To test if the JDBC driver has the functionality, use the isInsertCapable() function
	 * @param columns The values as strings of the columns to insert
	 * @param columnNames The (optional) names of the columns to insert. Null if columns should be position-inserted.
	 * 						If not null, should have the same number of items as the first vector.
	 */
	public boolean insertRow(Vector columns, Vector columnNames, Vector emptyColums) {
		try {
			resultSet.moveToInsertRow();
			for (int i = 0; i < columns.size(); i++){
				String stringValue = (String) columns.get(i);
				int len = stringValue.length();
				// There is no way of differentiating in the CSV format between null values and empty strings
				if (len > 0) {
					if ( columnNames == null ) {
						resultSet.updateString(i+1, stringValue);
					} else {
						resultSet.updateString((String) columnNames.get(i), stringValue);
					}
				} else {
					if ( columnNames == null ) {
						resultSet.updateNull(i+1);
					} else {
						resultSet.updateNull((String) columnNames.get(i));
					}
				}
			}
			resultSet.insertRow();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * @return true if the resultSet can insert by means of an Insert Row, false if not
	 */
	public boolean isInsertCapable() {
		try {
			resultSet.moveToInsertRow();
		}
		catch (SQLException e) {
			return false;
		}
		return true;
	}
	
}
	