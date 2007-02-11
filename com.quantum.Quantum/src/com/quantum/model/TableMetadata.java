/*
 * Created on 01.02.2005
 *
 */
package com.quantum.model;

import java.sql.SQLException;

import com.quantum.util.connection.NotConnectedException;

/**
 * @author Julen
 *
  */
public interface TableMetadata extends DatabaseObjectMetadata {
	/**
	 * @return	A Column array with all the columns of the table, in the defining order
	 * @throws NotConnectedException
	 * @throws SQLException
	 */
	public Column[] getColumns() throws NotConnectedException, SQLException;
	
	/**
	 * @return A Column array with the primary key columns of the table, in the defining order
	 * @throws NotConnectedException
	 * @throws SQLException
	 */
	public Column[] getPrimaryKeyColumns() throws NotConnectedException, SQLException;
	
	/**
	 * @return	A Index array with the indexed defined for the table
	 * @throws NotConnectedException
	 * @throws SQLException
	 */
	public Index[] getIndexes() throws NotConnectedException, SQLException;

	/**
	 * @param columnName	The name of the column to search for in the table
	 * @return	The Column object corresponding to the supplied column name, null if not found.
	 * @throws NotConnectedException
	 * @throws SQLException
	 */
	public Column getColumn(String columnName) throws NotConnectedException,
			SQLException;

	
	/**
	 * @return	An array of ForeignKey objects with the exported keys of the table. 
	 * 			That is, all foreign keys pointing TO the table 
	 *     
	 * @throws NotConnectedException
	 * @throws SQLException
	 */
	public ForeignKey[] getExportedKeys() throws NotConnectedException,
			SQLException;

	/** 
	 * @return An array of ForeignKey objects with the imported keys of the table. 
	 * 			That is, all foreign keys pointing FROM the table to other tables
	 * @throws NotConnectedException
	 * @throws SQLException
	 */
	public ForeignKey[] getImportedKeys() throws NotConnectedException,
			SQLException;

	/**
	 * @return An array of ForeignKey objects with all the foreign keys of the table.
	 * 			That is, both exported and imported keys
	 * @throws NotConnectedException
	 * @throws SQLException
	 */
	public ForeignKey[] getReferences() throws NotConnectedException,
			SQLException;

	/**
	 * @return The SQL CREATE statement for the view. Empty string if the entity is not a view or other error.
	 * @throws NotConnectedException
	 * @throws SQLException
	 */
	public String getCreateStatement()  throws NotConnectedException,
			SQLException;
	
	
	/**
	 * @return An array with the triggers associated to this table
	 * @throws NotConnectedException
	 * @throws SQLException
	 */
	public Trigger[] getTriggers()   throws NotConnectedException, SQLException;

	/**
	 * @return An array with the check constraints associated to this table
	 * @throws NotConnectedException
	 * @throws SQLException
	 */
	public Check[] getChecks()  throws NotConnectedException, SQLException;
	
	/**
	 * @return An array with the Privileges granted for that table (table-level only)
	 * @throws NotConnectedException
	 * @throws SQLException
	 */
	public Privilege[] getPrivileges() throws NotConnectedException, SQLException;
	
}