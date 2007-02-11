/*
 * Created on 12.08.2004
 *
 */
package com.quantum.util.sql;

import java.sql.SQLException;

import com.quantum.adapters.DatabaseAdapter;
import com.quantum.model.Bookmark;
import com.quantum.model.Column;
import com.quantum.model.Entity;
import com.quantum.model.Schema;
import com.quantum.model.TableMetadata;
import com.quantum.util.QuantumUtil;
import com.quantum.util.StringMatrix;
import com.quantum.util.connection.NotConnectedException;

/**
 * Functions to build SQL instructions adapted to the particular database
 * 
 * @author Julen
 */
public class SQLInstructionBuilder {
	
	/**
	 * Generates an Insert SQL instruction for each row of data in an StrigMatrix
	 * @param entity	The entity to generate the instruction for
	 * @param columns	A StringMatrix holding the names and values of the columns to insert 
	 * @return	A String with the insert sentences generated
	 */
	public static String buildInsert(Entity entity, StringMatrix columns)
	{
		return buildGenericInsert(entity, columns, false);
	}
	
	/**
	 * Generates a Prepared Insert SQL instruction, using only the names in the header of the StringMatrix
	 * @param entity	The entity to generate the instruction for
	 * @param columns	A StringMatrix holding just the names of the columns to insert 
	 * @return	A String with the insert sentence generated
	 */
	public static String buildPreparedInsert(Entity entity, StringMatrix columns)
	{
		return buildGenericInsert(entity, columns, true);
	}
	
	/**
	 * Generates an Insert SQL instruction for each row of data in an StrigMatrix
	 * @param entity	The entity to generate the instruction for
	 * @param columns	A StringMatrix holding the names and values of the columns to insert 
	 * @param prepared	true if the statement is to be a PreparedStatement, with "?" as placeholders 
	 * @return	A String with the insert sentences generated
	 */
	public static String buildGenericInsert(Entity entity, StringMatrix columns, boolean prepared) {
		if (entity == null || columns == null ) return "";
		Bookmark bookmark = entity.getBookmark();
		StringBuffer valuesClause = new StringBuffer();
		StringBuffer namesClause = new StringBuffer();
		String insertSentences = "";
		
		// We generate an update sentence for each row in the StringMatrix
		for (int iRow = 0; iRow < columns.size(); iRow++) {
			for (int iCol = 0; iCol < columns.getNumColumns(); iCol++) {
				if (iCol > 0) {
					namesClause.append(", "); //$NON-NLS-1$
					valuesClause.append(", "); //$NON-NLS-1$
				}
				String column = columns.getHeaderColumn(iCol);
				namesClause.append((bookmark != null) ? bookmark.filterQuoteName(column) : column);
				if (!prepared) {
					valuesClause.append(quoteValue( entity, columns.getHeaderColumn(iCol), columns.get(iCol, iRow)));
				} else {
					valuesClause.append("?");
				}
					
			}
			if (iRow > 0) insertSentences += ";\n";
			insertSentences += "INSERT INTO " + entity.getQuotedTableName(); //$NON-NLS-1$
			insertSentences += " (" + namesClause + " )"; //$NON-NLS-1$
			insertSentences +=  " VALUES " + " ( " + valuesClause + " )" ; //$NON-NLS-1$
			// If it's a prepared statement, there is no sense in generating a lot of inserts
			if (prepared) break;
		}
		return insertSentences;
	}
	/**
	 * Generates an UPDATE SQL instruction for each row of data in an StrigMatrix
	 * @param entity	The entity to generate the instruction for
	 * @param columns	A StringMatrix holding the names and values of the columns to insert 
	 * @param key		A StringMatrix holding the names and values of the columns of the key 
	 * @return	A String with the insert sentences generated
	 */
	public static String buildUpdate(Entity entity, StringMatrix columns, StringMatrix key)
	{
		if (entity == null || columns == null ) return "";
		Bookmark bookmark = entity.getBookmark();
		StringBuffer setClause = new StringBuffer();
		String whereClause = "";
		String updateSentences = "";
		
		// We generate an update sentence for each row in the StringMatrix
		for (int iRow = 0; iRow < columns.size(); iRow++) {
			for (int iCol = 0; iCol < columns.getNumColumns(); iCol++) {
				if (iCol > 0) setClause.append(", "); //$NON-NLS-1$
				String column = columns.getHeaderColumn(iCol);
				setClause.append((bookmark != null) ? bookmark.filterQuoteName(column) : column);
				setClause.append(" = "); //$NON-NLS-1$
				setClause.append(quoteValue( entity, columns.getHeaderColumn(iCol), columns.get(iCol, iRow)));
			}
			if (key != null && iRow < key.size()) {
				whereClause = getWhereClause(entity, key, iRow);
			}
			if (iRow > 0) updateSentences += ";\n";
			updateSentences += "UPDATE " + entity.getQuotedTableName(); //$NON-NLS-1$
			updateSentences += " SET " + setClause.toString(); //$NON-NLS-1$
			if (whereClause.length() > 0) 
				updateSentences += " WHERE " + whereClause; //$NON-NLS-1$
			}
		return updateSentences;
	}
	/**
	 * @param entity
	 * @param key
	 * @return
	 */
	public static String buildDelete(Entity entity, StringMatrix key)
	{
		if (entity == null ) return "";
		String deleteSentences = "";
		String whereClause = "";
		
		// We generate an update sentence for each row in the StringMatrix
		if (key == null) return "DELETE FROM " + entity.getQuotedTableName(); //$NON-NLS-1$
		
		for (int iRow = 0; iRow < key.size(); iRow++) {
			if (key != null && iRow < key.size()) {
				whereClause = getWhereClause(entity, key, iRow);
			}
			if (iRow > 0) deleteSentences += ";\n";
			deleteSentences += "DELETE FROM " + entity.getQuotedTableName(); //$NON-NLS-1$
			if (whereClause.length() > 0) 
				deleteSentences += " WHERE " + whereClause; //$NON-NLS-1$
			}
		return deleteSentences;
	}
	/**
	 * Builds a Select query with all columns and no rows (useful for structure querying)
	 * @param entity
	 * @return
	 */
	public static String buildSelectAllColumnsNoRows(Entity entity) {
		return "SELECT * FROM " + entity.getQuotedTableName() + " WHERE (1 = 0)"; //$NON-NLS-1$ //$NON-NLS-2$
	}
	/**
	 * Builds a Select query with all columns and no rows (useful for structure querying)
	 * @param entity
	 * @return
	 */
	public static String buildSelectAllColumnsNoRows(Bookmark bookmark, String schemaName, String tableName) {
		return "SELECT * FROM " + bookmark.filterQuoteName(QuantumUtil.getQualifiedName(schemaName, tableName)) + " WHERE (1 = 0)"; //$NON-NLS-1$ //$NON-NLS-2$
	}
	/**
	 * Builds a Select query with all columns and no rows (useful for structure querying)
	 * @param entity
	 * @return
	 */
	public static String buildSelectAllColumnsAllRows(Entity entity) {
		return "SELECT * FROM " + entity.getQuotedTableName() ; //$NON-NLS-1$ //$NON-NLS-2$
	}
	/**
	 * Builds a Select query with the selected columns and the selected rows (useful for structure querying)
	 * @param entity
	 * @param columns Selected columns. 
	 * @param key	Selected key and values, in row 0. Only 1 select query will be generated.
	 * @return
	 */
	public static String buildSelect(Entity entity, Column[] columns, StringMatrix key) {
		if (entity == null || columns == null ) return "";
		StringBuffer columnsList = new StringBuffer();
		String whereClause = "";
		String selectQuery = "";
		
		for (int iCol = 0; iCol < columns.length; iCol++) {
			if (iCol > 0) columnsList.append(", "); //$NON-NLS-1$
			columnsList.append(columns[iCol].getName());
		}
		if (key != null) {
			whereClause = getWhereClause(entity, key, 0);
		}
		selectQuery += "SELECT " + columnsList; //$NON-NLS-1$
		selectQuery += " FROM " + entity.getQuotedTableName(); //$NON-NLS-1$
		if (whereClause.length() > 0) 
			selectQuery += " WHERE " + whereClause; //$NON-NLS-1$

		return selectQuery;		
	}
	
	
	/**
	 * @param entity	The entity to get the where clause for
	 * @param key		A StringMatrix with the colums that form the key and rows with the values
	 * @param iRow		The key to the row that contains the values we are interested in
	 * @return 	A String with where clause (without the 'WHERE' reserved word), adapted to the database of the entity
	 */
	public static String getWhereClause(Entity entity, StringMatrix key, int iRow) {
		StringBuffer whereClause = new StringBuffer();
		Bookmark bookmark = entity.getBookmark();
		
		for (int iKey = 0; iKey < key.getNumColumns(); iKey++) {
			if (iKey > 0) whereClause.append(" AND "); //$NON-NLS-1$
			whereClause.append("("); //$NON-NLS-1$
			String column = key.getHeaderColumn(iKey);
			whereClause.append((bookmark != null) ? bookmark.filterQuoteName(column) : column);
			whereClause.append(" = "); //$NON-NLS-1$
			whereClause.append(quoteValue( entity, key.getHeaderColumn(iKey), key.get(iKey, iRow)));
			whereClause.append(")"); //$NON-NLS-1$
		}
		return whereClause.toString();
	}
	/**
	 * Default-value function.
	 * Generates a whereClause adapted to the entity´s database
	 * with the data of the first row of the StringMatrix (row 0).
	 * @see com.quantum.util.sql.SQLInstructionBuilder#getWhereClause
	 */
	public static String getWhereClause(Entity entity, StringMatrix key) {
		return getWhereClause(entity, key, 0);
	}

	/**
	 * Quotes the 'value' according with the type of the column and the database
	 * @param entity	Entity 
	 * @param columnName	Name of the column in the Entity
	 * @param value		Value of the column, to be quoted
	 */
	public static String quoteValue(Entity entity, String columnName, String value) {
		Bookmark bookmark = entity.getBookmark();
		DatabaseAdapter adapter = bookmark.getAdapter();
		
		if (adapter != null && entity != null && getColumn(entity, columnName) != null) {
			Column column = getColumn(entity, columnName);
			 return adapter.quote(bookmark, value, column.getType(), column.getTypeName());
		} else {
			return value;
		}
}
	/**
	 * Wrapper function to avoid exception handling
	 * @param entity	The entity that has the column
	 * @param columnName	The column name
	 * @return	A Column object from that entity, with the given name. null if not found or not connected. 
	 */
	public static Column getColumn(Entity entity, String columnName)  {
		try {
			return entity == null ? null : entity.getColumn(columnName);
		} catch (NotConnectedException e) {
			return null;
		} catch (SQLException e) {
			return null;
		}
	}
	
	
	/**
	 * @param bookmark	The bookmark where we are creating the table
	 * @param metadata	The metadata for the table to create
	 * @param schema	The schema where we are creating the table, null or empty if not applicable
	 * @param useJavaTypes	True if you will be using the Java Types of the metadata when creating the table, 
	 * 						(usually if you change databases). False if not (usually if the table metadata 
	 * 							comes from the same database as the destination)
	 * @return	A String with the CREATE TABLE statement needed to duplicate the structure of the table
	 * @throws NotConnectedException
	 * @throws SQLException
	 */
	public static String buildCreateTable(Bookmark bookmark, TableMetadata metadata, Schema schema, boolean useJavaTypes) 
	throws NotConnectedException, SQLException {
		String eol = System.getProperty("line.separator");
		String createTable = "CREATE TABLE " + bookmark.filterQuoteName(metadata.getQualifiedName()) + " (" + eol;
		Column columns[] = metadata.getColumns();
		for (int i = 0; i < columns.length; i++) {
			Column column = columns[i];
			if (i > 0) 
				createTable += "," + eol; 
			createTable += "        " + column.getName();
			createTable += " " + column.getTypeName();
			// A column type name that ends with ")" indicates that the size is already in the type name
			if (column.getSize() > 0 && !column.getTypeName().endsWith(")")) {
				createTable += "(" + String.valueOf(column.getSize());
				if (column.getNumberOfFractionalDigits() > 0)
					createTable += "," + String.valueOf(column.getNumberOfFractionalDigits());
				createTable += ")";
			}
			if (column.getDefaultValue() != null)
				createTable += " DEFAULT " + column.getDefaultValue();
		}
		
		createTable += eol + ")";
		
		return createTable;
	}

	
}
