package com.quantum.adapters;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.quantum.Messages;
import com.quantum.model.Bookmark;
import com.quantum.model.Column;
import com.quantum.model.DataType;
import com.quantum.model.Entity;
import com.quantum.model.Schema;
import com.quantum.model.SequenceMetadata;
import com.quantum.model.TableMetadata;
import com.quantum.sql.parser.SQLLexx;
import com.quantum.util.QuantumUtil;
import com.quantum.util.StringMatrix;
import com.quantum.util.StringUtil;
import com.quantum.util.connection.NotConnectedException;
import com.quantum.util.sql.TypesHelper;
/**
 * Abstract base class for all the adapter classes. Most functions can be redefined in
 * the adapters for the different databases. If the functions is not redefined, the base
 * implementation (usually a direct call to the JDBC driver) is used.
 * 
 * @author root
 */
public abstract class DatabaseAdapter {
    
	private final String type;
	
	protected DatabaseAdapter(String type) {
		this.type = type;
	}
	
	public String getDisplayName() {
		return Messages.getString(DatabaseAdapter.class, getType());
	}
	
	/**
	 * Returns the SQL Query to get a list of the tables for the current user (schema) 
	 * @param info
	 * @return - A String with the SQL query 
	 */
	public String getShowTableQuery(String schema) {
		return null;
	}
	/**
	 * Returns the SQL Query to get a list of the queries for the current user (schema) 
	 * @param info
	 * @return - A String with the SQL query
	 */
	public String getShowViewQuery(String schema) {
		return null;
	}
	/**
	 * Returns the SQL Query to get a list of the sequences for the current user (schema) 
	 * @param info
	 * @return - A String with the SQL query
	 */
	public String getShowSequenceQuery(String schema) {
        return null;
	}

	/** Returns the SQL Query to access all the columns of a table (SELECT)
	 * @param table	The table name, already qualified and quoted if needed
	 * @return - A String with the SQL query needed to select all rows and columns from the table
	 */
	public String getTableQuery( String table) {
			return "SELECT * FROM " + table; //$NON-NLS-1$
	}
    
	/**
	 * Gets the SQL query to get the next value of a sequence, (incrementing it).
	 * @param sequence - The name of the sequence
	 * @param owner - The owner (schema) of it
	 * @return - A string with the SQL query
	 */
	public String getNextValue(String sequence, String owner) {
        return null;
	}
	/**
	 * Gets the SQL query to get the actual value (previously used) of a sequence, (the sequence is not altered).
	 * @param sequence - The name of the sequence
	 * @param owner - The owner (schema) of it
	 * @return - A string with the SQL query
	 */
	public String getPrevValue(String sequence, String owner) {
		return null;
	}
	/**
	 * Returns a query to get the comments on the columns of a table. 
	 * Should return a query to generate a recordset with one row where
	 * the first and only column is the comment
	 * @param tableName - The full name of the table qualified with schema if applicable
	 * @param columnName - The name of the column
	 */
	public String getRemarksQuery(String tableName, String columnName) {
		return null;
	}

    /**
     * Returns a query to get a list of all aliases under a particular schema.
     * 
     * @param name
     * @return
     */
    public String getShowAliasQuery(String name) {
        return null;
    }
   
	/**
	 * Quotes a string according to the type of the column 
	 * @param bookmark bookmark with the type info. If null, no type info will be used
	 * @param string to be quoted
	 * @param type according to java.sql.Types
	 * @param typeString string with database-dependent string of the type. Will be
	 * 		   	used in the descendent functions
	 * @return
	 */
	public String quote(Bookmark bookmark, String string, int type, String typeString) {
// For the moment getting the quote strings from the database info has not been neccessary, most databases use ' for text literals.
//	If not, it'll probably be needed to use the different adapters rather than this, due
//	to the fact that the openQuoteString can be inside the literal, and has to be escaped
//		String openQuoteString = "'";
//		String closeQuoteString = "'";
//		try {
//			DataType dataType = bookmark.getDataType(typeString);
//			openQuoteString = dataType.getLiteralPrefix();
//			closeQuoteString = dataType.getLiteralSuffix();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
		if (TypesHelper.isText(type)) {			
			return "'" + StringUtil.substituteString(string, "'", "''") + "'"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		} else if (type == java.sql.Types.DATE || type == java.sql.Types.TIMESTAMP){		
			string = string.trim();
			//Check if we have to strip the millisecods
			if (string.length() > 2) {
				String sub = string.substring(string.length() - 2, string.length() - 1);
				if (string.length() > 1 && sub.equals(".")) //$NON-NLS-1$
					string = string.substring(0, string.length() - 2); // strip the milliseconds
			}
				
			return "'" + string + "'"; //$NON-NLS-1$ //$NON-NLS-2$

		}
		return string;
	}
	
        
	/**
	 * Returns a query to get the number of registers from a table
	 * @param tableName
	 * @return
	 */
	public String getCountQuery(String tableName) {
		return "SELECT COUNT(*) FROM " + tableName;	
	}

	/**
     * @param userid - the userid used to connect to the database
     * @return the default schema for the database
     */
    public String getDefaultSchema(String userid) {
        return userid;
    }
	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return this.type;
	}

	protected String getQualifiedName(String schema, String name) {
		return (schema != null && schema.length() > 0) ? schema + "." + name : name;
	}
	
	public Map getDefaultConnectionParameters() {
		return new HashMap();
	}
	
	/**
	 * Returns the SQL Query to get a list of the Sysnonyms for the current user (schema), of the given type 
	 * @param schema	The schema to get the query for
	 * @param type		The type of the synonym to get. Types can be one from the Entity types
	 * @return - A String with the SQL query 
	 */
	public String getShowSynonymsQuery(String schema, String type) {
		return null;
	}
	
	public String getShowPackagesQuery(String userid) {
		return null;
	}
	
	public String getProceduresQuery( String schema, String pack ) {
	    return null;
	}

	public String getProcedureArgumentsQuery( String schema, String pack, String proc, int overload ) {
	    return null;
	}
	
	/**
	 * @param schema	The schema of the view, null or empty if not applicable
	 * @param viewName	The name of the view
	 * @return	The SQL Statement used to create the view. Null if not supported.
	 * 			
	 */
	public String getCreateStatement(String schema, String viewName) {
		return null;
	}
	
	public boolean isExplainSupported() {
		return getStatementExplainer() != null;
	}
		/**
	 * This method returns any connection properties (other than userid and password)
	 * that should be provided by default during connection.  Adapters should provide
	 * this only in rare circumstances.
	 * 
	 * @return properties
	 */
	public Properties getConnectionProperties()  {
	    return null;
	}
	public StatementExplainer getStatementExplainer() {
		return null;
	}
	
	/**
	 * @param schema		The schema of the sequence, null or empty if not applicable
	 * @param sequenceName	The name of the sequence
	 * @return	The SQL Statement needed to select the metadata for the sequence, 
	 * 			with columns in the order:
	 * 		<LI>	MinValue (Minimum value of the sequence - String representing a long integer),
	 * 		<LI>	MaxValue (Maximum value of the sequence - String representing a long integer),
	 * 		<LI>	InitialValue (Value to start counting from - String representing a long integer),
	 * 		<LI>	IncrementBy (By how much to increment the value of the sequence in each invocation - String representing a long integer),
	 * 		<LI>	isCycled (If the sequence cycles itself when reaches the MaxValue - String representing boolean value, must be 'TRUE' or 'FALSE'),
	 * 		<LI>	isOrdered (if the sequence is forcibly ordered - String representing boolean value, must be 'TRUE' or 'FALSE')
	 * 			
	 * 		<br>	All can be set to null if not applicable in that database
	 * 			
	 */
	public String getSequenceMetadataQuery(String schema, String sequenceName) {
		return null;
	}

	
	/**
	 * @param schema		The schema of the database object, null or empty if not applicable
	 * @param objectName	The name of the database object
	 * @return	The SQL Statement needed to get information from the triggers defined for the database object 
	 * 			with columns in the order:
	 * 
	 * 	<LI>	Name (Name of the trigger - String),
	 * 	<LI>	Type (Type of the trigger, After or Before, each row or statement - String),
	 * 	<LI>	Event (The event that will execute the trigger (Insert, Update, Delete...) - String),
	 * 	<LI>	Column (The column name if it's a column trigger. Null if not. - String),
	 * 	<LI>	ReferencingNames (How the trigger will refer to old and new values - String),
	 * 	<LI>	Language (The languaje of the trigger or null if not applicable - String),
	 * 	<LI>	When (The When clause of the trigger, null if none. - String),
	 * 	<LI>	Status (Status (Enabled or Disabled) of the trigger - Status),
	 * 	<LI>	ActionType (The Action to execute, if it's a call or a program, etc - String),
	 * 	<LI>	Body (The body of the trigger, the code that executes when the trigger is called - String)
	 * 
	 * 		<br>	All can be set to null if not applicable
	 */
	public String getTriggersStatement(String schema, String objectName) {
		return null;
	}
	
	/**
	 * @param schema		The schema of the database object, null or empty if not applicable
	 * @param objectName	The name of the database object
	 * @return	The SQL Statement needed to get information from the check constraings defined for the database object 
	 * 			with columns in the order:
	 *  <LI>	Name	(The name of the check constraint - String)
	 *  <LI>	Body	(The body from the check constraint code - String)
	 */
	public String getChecksStatement(String schema, String objectName) {
		return null;
	}
	
	/*********************************************************************************************
	 *  "Build" statements. 
	 **********************************************************************************************/
	
	/**
	 * Generates an Insert SQL instruction for each row of data in an StrigMatrix
	 * @param entity	The entity to generate the instruction for
	 * @param columns	A StringMatrix holding the names and values of the columns to insert 
	 * @param noQuote 
	 * @return	A String with the insert sentences generated
	 */
	public String buildInsert(Entity entity, StringMatrix columns, List noQuote)
	{
		return buildGenericInsert(entity, columns, false, noQuote);
	}
	/**
	 * Generates a Prepared Insert SQL instruction, using only the names in the header of the StringMatrix
	 * @param entity	The entity to generate the instruction for
	 * @param columns	A StringMatrix holding just the names of the columns to insert 
	 * @return	A String with the insert sentence generated
	 */
	public String buildPreparedInsert(Entity entity, StringMatrix columns)
	{
		return buildGenericInsert(entity, columns, true, null);
	}
	
	/**
	 * Generates an Insert SQL instruction for each row of data in an StrigMatrix
	 * @param entity	The entity to generate the instruction for
	 * @param columns	A StringMatrix holding the names and values of the columns to insert 
	 * @param prepared	true if the statement is to be a PreparedStatement, with "?" as placeholders
	 * @param noQuote	A List of Boolean objects that will be true if the corresponding (same index)
	 * 					column is NOT to be quoted. If null, all will be quoted.
	 * @return	A String with the insert sentences generated
	 */
	public String buildGenericInsert(Entity entity, StringMatrix columns, boolean prepared, List noQuote) {
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
					// If we have a noQuote List, and the corresponding index is true, the column is not to be quoted
					if (noQuote != null && iCol < noQuote.size() && ((Boolean)noQuote.get(iCol)).booleanValue()) {
						valuesClause.append( columns.get(iCol, iRow));
					} else {
						valuesClause.append(quoteValue( entity, columns.getHeaderColumn(iCol), columns.get(iCol, iRow)));
					}
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
	 * @param noQuote	A List of Boolean objects that will be true if the corresponding (same index)
	 * 					column is NOT to be quoted. If null, all will be quoted.
	 * @param noQuoteKey	A List of Boolean objects that will be true if the corresponding (same index)
	 * 						KEY column is NOT to be quoted. If null, all will be quoted.
	 * @return	A String with the insert sentences generated
	 */
	public String buildUpdate(Entity entity, StringMatrix columns, StringMatrix key, List noQuote, List noQuoteKeys)
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
//				 If we have a noQuote List, and the corresponding index is true, the column is not to be quoted
				if (noQuote != null && iCol < noQuote.size() && ((Boolean)noQuote.get(iCol)).booleanValue()) {
					setClause.append( columns.get(iCol, iRow));
				} else {
					setClause.append(quoteValue( entity, columns.getHeaderColumn(iCol), columns.get(iCol, iRow)));
				}
			}
			if (key != null && iRow < key.size()) {
				whereClause = getWhereClause(entity, key, iRow, noQuoteKeys);
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
	 * @param noQuoteKey 
	 * @return
	 */
	public String buildDelete(Entity entity, StringMatrix key, List noQuoteKey)
	{
		if (entity == null ) return "";
		String deleteSentences = "";
		String whereClause = "";
		
		// We generate an update sentence for each row in the StringMatrix
		if (key == null) return "DELETE FROM " + entity.getQuotedTableName(); //$NON-NLS-1$
		
		for (int iRow = 0; iRow < key.size(); iRow++) {
			if (key != null && iRow < key.size()) {
				whereClause = getWhereClause(entity, key, iRow, noQuoteKey);
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
	public String buildSelectAllColumnsNoRows(Entity entity) {
		return "SELECT * FROM " + entity.getQuotedTableName() + " WHERE (1 = 0)"; //$NON-NLS-1$ //$NON-NLS-2$
	}
	/**
	 * Builds a Select query with all columns and no rows (useful for structure querying)
	 * @param entity
	 * @return
	 */
	public String buildSelectAllColumnsAllRows(Entity entity) {
		return "SELECT * FROM " + entity.getQuotedTableName() ; //$NON-NLS-1$ //$NON-NLS-2$
	}
	/**
	 * Builds a Select query with the selected columns and the selected rows (useful for structure querying)
	 * @param entity
	 * @param columns Selected columns. 
	 * @param key	Selected key and values, in row 0. Only 1 select query will be generated.
	 * @return
	 */
	public String buildSelect(Entity entity, Column[] columns, StringMatrix key) {
		if (entity == null || columns == null ) return "";
		StringBuffer columnsList = new StringBuffer();
		String whereClause = "";
		String selectQuery = "";
		
		for (int iCol = 0; iCol < columns.length; iCol++) {
			if (iCol > 0) columnsList.append(", "); //$NON-NLS-1$
			columnsList.append(columns[iCol].getName());
		}
		if (key != null) {
			whereClause = getWhereClause(entity, key, 0, null);
		}
		selectQuery += "SELECT " + columnsList; //$NON-NLS-1$
		selectQuery += " FROM " + entity.getQuotedTableName(); //$NON-NLS-1$
		if (whereClause.length() > 0) 
			selectQuery += " WHERE " + whereClause; //$NON-NLS-1$

		return selectQuery;
	}

	
	/**
	 * @param bookmark	The bookmark where we are creating the table
	 * @param metadata	The metadata for the table to create
	 * @param schema	The schema where we are creating the table, null or empty if not applicable
	 * @param useJavaTypes	True if you will be using the Java Types of the metadata when creating the table, 
	 * 						(usually if you change databases). False if not (usually if the table metadata 
	 * 							comes from the same database as the destination)
	 * @return	A String with the CREATE TABLE statement needed to duplicate the structure of the table, 
	 * 			empty string if not defined for that database
	 * @throws NotConnectedException
	 * @throws SQLException
	 */
	public String buildCreateTable(Bookmark bookmark, TableMetadata metadata, Schema schema, boolean useJavaTypes) 
			throws NotConnectedException, SQLException {
		StringBuffer buffer = new StringBuffer("CREATE TABLE ");
		String tableName = bookmark.getDatabase().supportsSchemasInTableDefinitions()
				? QuantumUtil.getQualifiedName(metadata.getSchema(), metadata.getName())
				: metadata.getName();
		buffer.append(bookmark.filterQuoteName(tableName));
		buffer.append(" (");
		buffer.append(StringUtil.LINE_SEPARATOR);
		Column columns[] = metadata.getColumns();
		for (int i = 0; i < columns.length; i++) {
			Column column = columns[i];
			if (i > 0) {
				buffer.append(",").append(StringUtil.LINE_SEPARATOR);
			}
			
			buffer.append("        ");
			buffer.append(column.getName());
			buffer.append(" ");
			buffer.append(getTypeName(bookmark, column, useJavaTypes));
			if (column.getDefaultValue() != null) {
				buffer.append(" DEFAULT ").append(column.getDefaultValue());
			}
			if (!column.isNullable()) {
			    buffer.append(" NOT NULL ");
			}
		}
		
		buffer.append(StringUtil.LINE_SEPARATOR);
		buffer.append(");");
		buffer.append(StringUtil.LINE_SEPARATOR);
		buffer.append(buildPrimaryKey(bookmark, tableName, metadata));
		
		// shouldn't these be built separately?
		buffer.append(buildForeignKeys(bookmark, metadata, schema, false));
		buffer.append(buildRemarks(bookmark, tableName, metadata));
		buffer.append(buildTriggers(bookmark, tableName, metadata, schema));
		buffer.append(buildChecks(bookmark, tableName, metadata, schema));
		return buffer.toString();
	}
	
	/**
	 * This function provides a default implementation for getting the type name with the length
	 * as used in the SQL CREATE statemets, for example.
	 * Unfortunately, this doesn't always work.  Consider Informix, with its int8 type.
	 * The standard driver says that the size of the int8 is 10.  But you don't 
	 * define a column as int8(10), you just define it as int8.
	 * So this function will have to be redefined in the different adapters to filter those cases.
	 *  
	 * @param bookmark	The bookmark (database) to which the generated string will be applied
	 * @param column	The column from which to extract the type name
	 * @param useJavaTypes	If java types are to be used. If not, the column saved type name will be
	 * 						used, which is usually fine if the database is the same, but will likely fail if not.
	 * @return
	 */
	public String getTypeName(Bookmark bookmark, Column column, boolean useJavaTypes) {
		String type = "";
		if (useJavaTypes) {
			try {
				type = javaToSQL(bookmark.getDataTypes(), column.getType());
			} catch (NotConnectedException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// If we are not to use java types, or there is no matching type in the array, we'll return the extracted type
		if (type.equals(""))
			type = column.getTypeName();
		
		// A column type name that ends with ")" indicates that the size is already in the type name
		if ( column.getSize() > 0 && !type.endsWith(")")) {
			type += "(" + String.valueOf(column.getSize());
			if (column.getNumberOfFractionalDigits() > 0)
				type += "," + String.valueOf(column.getNumberOfFractionalDigits());
			type += ")";
		}
		return type;
	}
	
	/**
	 * @param bookmark
	 * @param tableName
	 * @param metadata
	 * @return
	 * @throws SQLException 
	 * @throws NotConnectedException 
	 */
	public String buildRemarks(Bookmark bookmark, String tableName, TableMetadata metadata) throws NotConnectedException, SQLException {
		return "";	
	}

	/**
	 * @param bookmark
	 * @param tableName
	 * @param metadata
	 * @param schema
	 * @return
	 */
	public  String buildTriggers(Bookmark bookmark, String tableName, TableMetadata metadata, Schema schema) {
		return "";	
	}

	/**
	 * @param bookmark
	 * @param tableName
	 * @param metadata
	 * @param schema
	 * @return
	 */
	public  String buildChecks(Bookmark bookmark, String tableName, TableMetadata metadata, Schema schema) {
		return "";	
	}

	/**
	 * @param bookmark
	 * @param tableName
	 * @param metadata
	 * @return
	 * @throws NotConnectedException
	 * @throws SQLException
	 */
	public  String buildPrimaryKey(Bookmark bookmark, String tableName, TableMetadata metadata) throws NotConnectedException, SQLException {
		return "";	
	}
	/**
	 * @param tableName
	 * @param metadata
	 * @return
	 * @throws SQLException 
	 * @throws NotConnectedException 
	 */
	public  String buildForeignKeys(Bookmark bookmark, TableMetadata metadata, Schema schema, boolean absoluteReference) 
	 throws NotConnectedException, SQLException {
		return "";
	}

	/**
	 * @param bookmark	The bookmark where we are creating the table
	 * @param metadata	The metadata for the sequence to create
	 * @param schema	The schema where we are creating the sequence, null or empty if not applicable
	 * @return	A String with the CREATE SEQUENCE (or equivalent) statement needed to duplicate the structure of the given sequence
	 * @throws NotConnectedException
	 * @throws SQLException
	 */
	public  String buildCreateSequence(Bookmark bookmark, SequenceMetadata metadata, Schema schema ) throws NotConnectedException, SQLException
	{
		return "";	
	}
	/**
	 * @param bookmark	The bookmark where we are creating the table
	 * @param metadata	The metadata for the view to create
	 * @param schema	The schema where we are creating the view, null or empty if not applicable
	 * @return	A String with the CREATE view statement needed to duplicate the structure of the table
	 * @throws NotConnectedException
	 * @throws SQLException
	 */
	// TODO: This should really have a "ViewMetadata" as parameter, some refactoring is needed
	public String buildCreateView(Bookmark bookmark, TableMetadata metadata, Schema schema) 
	throws NotConnectedException, SQLException {
		return "";	
	}

	/**
	 * @param entity	The entity to get the where clause for
	 * @param key		A StringMatrix with the colums that form the key and rows with the values
	 * @param iRow		The key to the row that contains the values we are interested in
	 * @param noQuote	A List of Boolean objects that will be true if the corresponding (same index)
	 * 					column is NOT to be quoted. If null, all will be quoted.
	 * @return 	A String with where clause (without the 'WHERE' reserved word), adapted to the database of the entity
	 */
	public String getWhereClause(Entity entity, StringMatrix key, int iRow, List noQuote) {
		StringBuffer whereClause = new StringBuffer();
		Bookmark bookmark = entity.getBookmark();
		
		for (int iKey = 0; iKey < key.getNumColumns(); iKey++) {
			if (iKey > 0) whereClause.append(" AND "); //$NON-NLS-1$
			whereClause.append("("); //$NON-NLS-1$
			String column = key.getHeaderColumn(iKey);
			whereClause.append((bookmark != null) ? bookmark.filterQuoteName(column) : column);
			whereClause.append(" = "); //$NON-NLS-1$
//			 If we have a noQuote List, and the corresponding index is true, the column is not to be quoted
			if (noQuote != null && iKey < noQuote.size() && ((Boolean)noQuote.get(iKey)).booleanValue()) {
				whereClause.append( key.get(iKey, iRow));
			} else {
				whereClause.append(quoteValue( entity, key.getHeaderColumn(iKey), key.get(iKey, iRow)));
			}
			whereClause.append(")"); //$NON-NLS-1$
		}
		return whereClause.toString();
	}

	/**
	 * Quotes the 'value' according with the type of the column and the database
	 * @param entity	Entity 
	 * @param columnName	Name of the column in the Entity
	 * @param value		Value of the column, to be quoted
	 */
	public String quoteValue(Entity entity, String columnName, String value) {
		if ( entity != null && getColumn(entity, columnName) != null) {
			Column column = getColumn(entity, columnName);
			 return quote(entity.getBookmark(), value, column.getType(), column.getTypeName());
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
	public Column getColumn(Entity entity, String columnName)  {
		try {
			return entity == null ? null : entity.getColumn(columnName);
		} catch (NotConnectedException e) {
			return null;
		} catch (SQLException e) {
			return null;
		}
	}

	/**
	 * Converts the given java sql type (from java.sql.types) to a string describing the type for SQL sentences
	 * @param types		An array with the DataTypes supported by the database (obtained usually from java.sql.DatabaseMetaData.getTypeInfo())
	 * @param javaType	The given java type from java.sql.types
	 * @return
	 */
	public String javaToSQL(DataType[] types, int javaType) {
		String SQL = "";
		for (int i = 0; i < types.length; i++) {
			DataType type = types[i];
			if (type.getJavaType() == javaType) {
				SQL = type.getDatabaseTypeName();
				// In principle, the first found match is the best
				break;
			}
		}
		return SQL;
	}
	
	public String getProcedureBodyStatement(String Schema, String Package, String Name, int Overload)
	{
		return null;
	}
	
	public String getObjectType(Bookmark bm, String object)
	{
		return "";
	}
	
	public SQLLexx getLexer()
	{
		return new SQLLexx();
	}

	public String getTableExistsQuery(String name)
	{
		return "";
	}
	public String getColumnExistsQuery(String columnName, String tableName)
	{
		return "";
	}
	public String getTablesForColumnQuery(String columnName)
	{
		return "";
	}
    
    public String buildAlterTable(Bookmark bm, String tableName, String operation, String columnName, com.quantum.editors.graphical.model.Column c){
        return "-- Your specific database adapter does not support ALTER TABLE yet.\r\n";
    }

    public String getBody(Bookmark bm, String Schema, String Package, String Name, int Overload)
    {
        return "";
    }
    
    /**
     **	<LI><B>SEARCHABLE</B> short => can you use "WHERE" based on this type:
     *      <UL>
     *      <LI> DatabaseMetadata.typePredNone - No support
     *      <LI> DatabaseMetadata.typePredChar - Only supported with WHERE .. LIKE
     *      <LI> DatabaseMetadata.typePredBasic - Supported except for WHERE .. LIKE
     *      <LI> DatabaseMetadata.typeSearchable - Supported for all WHERE ..
     *      </UL>
 
     * @param bm	The bookmark with the database info
     * @param dataTypeName	The type name (local name of the database)
     * @return	true if the type is searchable, false if not. Searchable types can perhaps not support LIKE, check it with isTypeLikeSearchable
     * @throws NotConnectedException If the bookmark is not connected
     * @throws SQLException
     */
    public short typeSearchable(Bookmark bookmark, String dataTypeName)  {
    	DataType dataType;
		try {
			dataType = bookmark.getDataType(dataTypeName);
		} catch (Exception e) {
			e.printStackTrace();
			return DatabaseMetaData.typePredNone;
		}
    	if (dataType == null) {
    		// if type not found, we return it's not searchable
    		return DatabaseMetaData.typePredNone;
    	}
    	return dataType.getSearchable();
    }
    
    
}