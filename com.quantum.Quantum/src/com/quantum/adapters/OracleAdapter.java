package com.quantum.adapters;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.quantum.Messages;
import com.quantum.QuantumPlugin;
import com.quantum.model.Bookmark;
import com.quantum.model.Check;
import com.quantum.model.Column;
import com.quantum.model.DataType;
import com.quantum.model.Entity;
import com.quantum.model.ForeignKey;
import com.quantum.model.Schema;
import com.quantum.model.SequenceMetadata;
import com.quantum.model.TableMetadata;
import com.quantum.model.Trigger;
import com.quantum.util.QuantumUtil;
import com.quantum.util.StringMatrix;
import com.quantum.util.connection.NotConnectedException;
import com.quantum.util.sql.TypesHelper;

import org.eclipse.jface.preference.IPreferenceStore;



public class OracleAdapter extends DatabaseAdapter {
	
	private OracleStatementExplainer explainer = new OracleStatementExplainer();
	
    /* (non-Javadoc)
     * @see com.quantum.adapters.DatabaseAdapter#getConnectionProperties()
     */
    public Properties getConnectionProperties() {
        Properties properties = new Properties();
        IPreferenceStore store = QuantumPlugin.getDefault().getPreferenceStore();
    	// remarksReporting will make the JDBC driver return the remarks for the tables and
    	// the columns. It'll make getting the tables and columns much slower, so it's an option
    	if (store.getBoolean("getComments")) //$NON-NLS-1$
        	properties.put("remarksReporting", "true");
    	// includeSynonyms will make the JDBC driver return the proper columns when querying
    	// about a synonym. If not given, synonyms will appear with no columns, exports of data
    	// containing synonyms will break, etc.n So it's needed from the moment you add the synonyms
    	// with the getSynonymsList() in the Database.getEntities() function. 
    	if (store.getBoolean("getSynonyms")) //$NON-NLS-1$
            properties.put("includeSynonyms", "true");
        return properties;
    }
	protected OracleAdapter() {
		super(AdapterFactory.ORACLE);
	}
	public String getShowSequenceQuery(String qualifier) {
        return "SELECT SEQUENCE_OWNER, SEQUENCE_NAME FROM ALL_SEQUENCES WHERE SEQUENCE_OWNER = '" + qualifier + "'"; //$NON-NLS-1$
	}
	public String getPrevValue(String sequence, String owner) {
    	return "SELECT " + getQualifiedName(owner, sequence) + ".CURRVAL FROM DUAL";
	}
	public String getNextValue(String sequence, String owner) {
		return "SELECT " + getQualifiedName(owner, sequence) + ".NEXTVAL FROM DUAL";
	}
	public String getRemarksQuery(String tableName, String column) {
		String query = "SELECT COMMENTS FROM ALL_COL_COMMENTS WHERE TABLE_NAME = '"; 
		query += QuantumUtil.getTableName(tableName) + "' AND COLUMN_NAME = '" + column + "'" ;
		if (!(QuantumUtil.getSchemaName(tableName).equals("")))
			query += " AND OWNER = '" + QuantumUtil.getSchemaName(tableName) + "'";
		return query;
	}
	/**
	 * Quotes a string according to the type of the column 
	 * @param string to be quoted
	 * @param type according to java.sql.Types
	 * @return
	 */
	public String quote(Bookmark bookmark, String string, int type, String typeString) {
		if (type == java.sql.Types.DATE || type == java.sql.Types.TIMESTAMP) {
			string = string.trim();
			// Eliminate the fractions of seconds, if present
			if (string.length() > 1) {
				// If the third character from the end is a dot, it means it has fractions
				String sub = string.substring(string.length()-2, string.length()-1);
				if ( sub.equals(Messages.getString("."))) //$NON-NLS-1$
					string = string.substring(0,string.length()-2);
			}
			return "TO_DATE('" + string + "','yyyy-mm-dd hh24:mi:ss')"; //$NON-NLS-1$ //$NON-NLS-2$
		}
		// use the default (upper type)
		return super.quote(bookmark, string, type, typeString);
	}


    /**
     * The default schema for Oracle is the upper-case userid.
     * @see com.quantum.adapters.DatabaseAdapter#getDefaultSchema(java.lang.String)
     */
    public String getDefaultSchema(String userid) {
        return super.getDefaultSchema(userid).toUpperCase();
    }
    
    public Map getDefaultConnectionParameters() {
    	Map map = new HashMap();
    	map.put("port", "1521");
    	map.put("hostname", "localhost");
    	return map;
    }
    

	/* (non-Javadoc)
	 * @see com.quantum.adapters.DatabaseAdapter#getShowSynonymsQuery(java.lang.String, java.lang.String)
	 */
	public String getShowSynonymsQuery(String schema, String type) {
		// The type string is the same as the one needed by Oracle. If it changes a switch would be needed.
		return "select SYNONYM_NAME from ALL_SYNONYMS, ALL_OBJECTS where " +
				" ALL_SYNONYMS.OWNER = '" + schema + "'" +
				" and ALL_SYNONYMS.TABLE_OWNER = ALL_OBJECTS.OWNER" +
				" and ALL_SYNONYMS.TABLE_NAME = ALL_OBJECTS.OBJECT_NAME" + 
				" and ALL_OBJECTS.OBJECT_TYPE = '" + type + "'" ;
		}

	/* (non-Javadoc)
	 * @see com.quantum.adapters.DatabaseAdapter#getShowPackagesQuery(java.lang.String)
	 */
	public String getShowPackagesQuery(String userid) {
		return  "select OBJECT_NAME " +
				"from ALL_OBJECTS " +
				"where OBJECT_TYPE = 'PACKAGE' " +
				"and OWNER='"+getDefaultSchema(userid)+"'";
	}

	/* (non-Javadoc)
	 * @see com.quantum.adapters.DatabaseAdapter#getProceduresQuery(java.lang.String, java.lang.String)
	 */
	public String getProceduresQuery( String schema, String pack ) {
		return "select distinct OBJECT_NAME, nvl( OVERLOAD, 0 ) "+
		       "from ALL_ARGUMENTS " +
			   "where OWNER='"+schema.toUpperCase()+"' " +
				( pack == null
				  ? ""
				  : "and PACKAGE_NAME = '"+pack.toUpperCase()+"'");
	}

	/* (non-Javadoc)
	 * @see com.quantum.adapters.DatabaseAdapter#getProcedureArgumentsQuery(java.lang.String, java.lang.String, java.lang.String, int)
	 */
	public String getProcedureArgumentsQuery( String schema, String pack, String proc, int overload ) {
		return "select ARGUMENT_NAME, POSITION, DATA_TYPE "+
	       "from ALL_ARGUMENTS " +
		   "where OWNER='"+schema.toUpperCase()+"' " +
		   "and OBJECT_NAME='"+proc.toUpperCase()+"' "+
		   "and nvl( OVERLOAD,0)="+overload+" "+
			( pack == null
			  ? ""
			  : "and PACKAGE_NAME = '"+pack.toUpperCase()+"'");
	}

	/* (non-Javadoc)
	 * @see com.quantum.adapters.DatabaseAdapter#getCreateStatement(java.lang.String)
	 */
	public String getCreateStatement(String schema, String viewName) {
		return "select TEXT from ALL_VIEWS where OWNER = '" + schema + "'" +
				"and VIEW_NAME = '" + viewName + "'" ;
	}
	/* (non-Javadoc)
	 * @see com.quantum.adapters.DatabaseAdapter#getSequenceMetadataQuery(java.lang.String, java.lang.String)
	 */
	public String getSequenceMetadataQuery(String schema, String sequenceName) {
		return "select MIN_VALUE, MAX_VALUE, LAST_NUMBER, INCREMENT_BY, " +
					"decode(CYCLE_FLAG, 'N', 'FALSE', 'Y', 'TRUE' , 'FALSE'), " +
					"decode(ORDER_FLAG, 'N', 'FALSE', 'Y', 'TRUE' , 'FALSE')" +
		       "from ALL_SEQUENCES " +
		       "where SEQUENCE_OWNER = '" + schema + "'" +
		       "and SEQUENCE_NAME = '" + sequenceName + "'" ;
	}

	/* (non-Javadoc)
	 * @see com.quantum.adapters.DatabaseAdapter#getTriggersStatement(java.lang.String, java.lang.String)
	 */
	public String getTriggersStatement(String schema, String name) {
		return "select TRIGGER_NAME, " +
				"decode( substr( trigger_type, 1, 1 ), 'A', 'AFTER', 'B', 'BEFORE', 'I', 'INSTEAD OF', null )," +
				"decode( instr( trigger_type, 'EACH ROW' ), 0, null, 'FOR EACH ROW' )," +
				"TRIGGERING_EVENT, COLUMN_NAME, NULL, " +
				"REFERENCING_NAMES, WHEN_CLAUSE, STATUS, ACTION_TYPE, TRIGGER_BODY " +
				"from ALL_TRIGGERS " +
				"where TABLE_OWNER = '" + schema + "'" +
				"and TABLE_NAME = '" + name + "'";
	}
	

	/* (non-Javadoc)
	 * @see com.quantum.adapters.DatabaseAdapter#getChecksStatement(java.lang.String, java.lang.String)
	 */
	public String getChecksStatement(String schema, String objectName) {
		return "select CONSTRAINT_NAME, SEARCH_CONDITION " +
		"from ALL_CONSTRAINTS " +
		"where OWNER = '" + schema + "'" +
		"and TABLE_NAME = '" + objectName + "'" +
		"and CONSTRAINT_TYPE = 'C'";
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
	public String buildCreateTable(Bookmark bookmark, TableMetadata metadata, Schema schema, boolean useJavaTypes) 
	throws NotConnectedException, SQLException {

		return super.buildCreateTable(bookmark, metadata, schema, useJavaTypes);
		
/*		String eol = System.getProperty("line.separator");
		String tableName = QuantumUtil.getQualifiedName(schema.getName(), metadata.getName());
		String createTable = "CREATE TABLE " + bookmark.filterQuoteName(tableName) + " (" + eol;
		Column columns[] = metadata.getColumns();
		//String createPrimaryKey = "PRIMARY KEY "
		for (int i = 0; i < columns.length; i++) {
			Column column = columns[i];
			if (i > 0) 
				createTable += "," + eol; 
			createTable += "        " + column.getName();
			createTable += " " + column.getTypeName();
			// A column type name that ends with ")" indicates that the size is already in the type name
			// Also, DATE has no size
			if (!column.getTypeName().equals("DATE") && 
					( column.getSize() > 0 && !column.getTypeName().endsWith(")"))) {
				createTable += "(" + String.valueOf(column.getSize());
				if (column.getNumberOfFractionalDigits() > 0)
					createTable += "," + String.valueOf(column.getNumberOfFractionalDigits());
				createTable += ")";
			}
			if (column.getDefaultValue() != null)
				createTable += " DEFAULT " + column.getDefaultValue();
		}
		
		createTable += eol + ");" + eol;
		createTable += buildPrimaryKey(bookmark, tableName, metadata);
		createTable += buildForeignKeys(bookmark, metadata, schema, false);
		createTable += buildRemarks(bookmark, tableName, metadata);
		createTable += buildTriggers(bookmark, tableName, metadata, schema);
		createTable += buildChecks(bookmark, tableName, metadata, schema);
		return createTable;
*/	}
	/* (non-Javadoc)
	 * @see com.quantum.adapters.DatabaseAdapter#buildRemarks(com.quantum.model.Bookmark, java.lang.String, com.quantum.model.TableMetadata)
	 */
	public String buildRemarks(Bookmark bookmark, String tableName, TableMetadata metadata) throws NotConnectedException, SQLException {
		Column[] columns = metadata.getColumns();
		if (columns == null || columns.length == 0) {
			return "";
		}
		String eol = System.getProperty("line.separator");
		String comments = "";
		for (int i = 0; i < columns.length; i++) {
			Column column = columns[i];
			if (column.getRemarks() != null && column.getRemarks() != "") {
				comments += "COMMENT ON COLUMN " + bookmark.filterQuoteName(tableName) + "." +  bookmark.filterQuoteName(column.getName()) + 
							" IS '" + column.getRemarks() + "';" + eol;
			}
		}	
		return comments;
	}

	/* (non-Javadoc)
	 * @see com.quantum.adapters.DatabaseAdapter#buildTriggers(com.quantum.model.Bookmark, java.lang.String, com.quantum.model.TableMetadata, com.quantum.model.Schema)
	 */
	public  String buildTriggers(Bookmark bookmark, String tableName, TableMetadata metadata, Schema schema) {
		Trigger[] triggers = null;
		String createTriggers = "";
		String eol = System.getProperty("line.separator");
		try {
			triggers = metadata.getTriggers();
		} catch (NotConnectedException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (triggers != null) {
	        for (int i = 0; i < triggers.length; i++) {
				Trigger trigger = triggers[i];
				createTriggers += "CREATE TRIGGER " + QuantumUtil.getQualifiedName(schema.getName(), trigger.getName()) + eol;
				createTriggers += trigger.getMoment() + " " + trigger.getEvent() + eol;
				createTriggers += " ON " + bookmark.filterQuoteName(tableName) + eol; 
				createTriggers += trigger.getReferencing() + eol;
				if (trigger.getForEach() != null){
					createTriggers += trigger.getForEach() ;
					createTriggers += (trigger.getWhenClause() != null) ? trigger.getWhenClause() : "";
					createTriggers += eol;
				}
				createTriggers += trigger.getBody() + ";" + eol;
	        }
		}
		return createTriggers;
	}

	/* (non-Javadoc)
	 * @see com.quantum.adapters.DatabaseAdapter#buildChecks(com.quantum.model.Bookmark, java.lang.String, com.quantum.model.TableMetadata, com.quantum.model.Schema)
	 */
	public  String buildChecks(Bookmark bookmark, String tableName, TableMetadata metadata, Schema schema) {
		Check[] checks = null;
		String createTriggers = "";
		String eol = System.getProperty("line.separator");
		try {
			checks = metadata.getChecks();
		} catch (NotConnectedException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (checks != null) {
	        for (int i = 0; i < checks.length; i++) {
				Check check = checks[i];
				createTriggers += "ALTER TABLE " + bookmark.filterQuoteName(tableName) +  eol;
				createTriggers += " ADD CONSTRAINT " + check.getName() + eol;
				createTriggers += " CHECK (" + check.getBody() + ");" + eol;;
	        }
		}
		return createTriggers;
	}

	/* (non-Javadoc)
	 * @see com.quantum.adapters.DatabaseAdapter#buildPrimaryKey(com.quantum.model.Bookmark, java.lang.String, com.quantum.model.TableMetadata)
	 */
	public  String buildPrimaryKey(Bookmark bookmark, String tableName, TableMetadata metadata) throws NotConnectedException, SQLException {
		Column[] columns = metadata.getPrimaryKeyColumns();
		if (columns == null || columns.length == 0) {
			return "";
		}
		String eol = System.getProperty("line.separator");
		String primaryKey = "ALTER TABLE " + bookmark.filterQuoteName(tableName) + eol +
							"   ADD CONSTRAINT " + QuantumUtil.getTableName(tableName) + "_PK PRIMARY KEY ( ";
		for (int i = 0; i < columns.length; i++) {
			Column column = columns[i];
			if (i > 0) {
				primaryKey += ", ";
			}
			primaryKey += column.getName();
		}
		primaryKey += " );" + eol;
		
		return primaryKey;
	}

	/* (non-Javadoc)
	 * @see com.quantum.adapters.DatabaseAdapter#buildForeignKeys(com.quantum.model.Bookmark, com.quantum.model.TableMetadata, com.quantum.model.Schema, boolean)
	 */
	public  String buildForeignKeys(Bookmark bookmark, TableMetadata metadata, Schema schema, boolean absoluteReference) 
	 throws NotConnectedException, SQLException {
		ForeignKey[] keys = metadata.getImportedKeys();
		if (keys == null || keys.length == 0) {
			return "";
		}
		String foreignKey = "";
		String eol = System.getProperty("line.separator");
		for (int i = 0; i < keys.length; i++) {
			ForeignKey key = keys[i];
			String localTableName = QuantumUtil.getQualifiedName(schema.getName(), key.getLocalEntityName());
			// If the referenced table is from the same schema, and it's not an absolute reference
			// , we change it to the selected new schema
			String foreignSchemaName = key.getForeignEntitySchema();
			
			if (!absoluteReference && key.getLocalEntitySchema().equals(key.getForeignEntitySchema())) {
				foreignSchemaName = schema.getName();
			}
			String foreignTableName =  QuantumUtil.getQualifiedName(foreignSchemaName , key.getForeignEntityName());
			QuantumUtil.getQualifiedName(schema.getName(), key.getLocalEntityName());
			foreignKey += "ALTER TABLE " + bookmark.filterQuoteName(foreignTableName) + eol +
			"   ADD CONSTRAINT " + key.getName() + " FOREIGN KEY ( ";
			for (int j = 0; j < key.getNumberOfColumns(); j++) {
				if (j > 0) {
					foreignKey += ", ";
				}
				foreignKey += key.getForeignColumnName(j);
			}
			foreignKey += " )" + eol;
			foreignKey += "   REFERENCES " + localTableName + "( ";
			for (int j = 0; j < key.getNumberOfColumns(); j++) {
				if (j > 0) {
					foreignKey += ", ";
				}
				foreignKey += key.getLocalColumnName(j);
			}
			foreignKey += " );" + eol;
			
		}
		return foreignKey;
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
		String eol = System.getProperty("line.separator");
		String sequenceName = QuantumUtil.getQualifiedName(schema.getName(), metadata.getName());
		String createSequence = "CREATE SEQUENCE " + bookmark.filterQuoteName(sequenceName) + eol;
		createSequence += "MINVALUE " + metadata.getMinValue() + eol;
		createSequence += "MAXVALUE " + metadata.getMaxValue() + eol;
		createSequence += "START WITH " + metadata.getInitialValue() + eol;
		createSequence += "INCREMENT BY " + metadata.getIncrementBy() ;
		if (metadata.isCycled()) { 
			createSequence += eol + "CYCLE";
		}
		if (metadata.isOrdered()) {
			createSequence += eol + "ORDERED";
		}
		
		return createSequence;
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
		String eol = System.getProperty("line.separator");
		String metadataCreate = metadata.getCreateStatement();
		if (metadataCreate == null) {
			return "";
		}
		// Some databases (IBM), save the create statement with the CREATE clause
		if (metadataCreate.length() > 6) {
			if ( metadataCreate.trim().substring(0,6).toUpperCase().equals("CREATE")) {
				return metadataCreate;
			}
		}
		String viewName = QuantumUtil.getQualifiedName(schema.getName(), metadata.getName());
		return "CREATE VIEW " + bookmark.filterQuoteName(viewName) + eol 
				+ "  AS " + eol + 
				metadataCreate;

	}

	
	
	
	/**
	 * @param entity	The entity to get the where clause for
	 * @param key		A StringMatrix with the colums that form the key and rows with the values
	 * @param iRow		The key to the row that contains the values we are interested in
	 * @return 	A String with where clause (without the 'WHERE' reserved word), adapted to the database of the entity
	 */
	public String getWhereClause(Entity entity, StringMatrix key, int iRow) {
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
	
    public StatementExplainer getStatementExplainer() {
        return this.explainer;
    }
	
	/* (non-Javadoc)
	 * @see com.quantum.adapters.DatabaseAdapter#javaToSQL(com.quantum.model.DataType[], int)
	 */
	public String javaToSQL(DataType[] types, int javaType) {
		// Oracle does not give a row for the DECIMAL type
		if (javaType == TypesHelper.DECIMAL || javaType == TypesHelper.BOOLEAN)
			javaType = TypesHelper.NUMERIC;
		return super.javaToSQL(types, javaType);
	}

	
	/* (non-Javadoc)
	 * @see com.quantum.adapters.DatabaseAdapter#getTypeName(com.quantum.model.Bookmark, com.quantum.model.TableMetadata, com.quantum.model.Column, boolean)
	 */

	public String getTypeName(Bookmark bookmark, Column column, boolean useJavaTypes) {
		if (useJavaTypes) {
			if (column.getType() == TypesHelper.TIMESTAMP){
				return "TIMESTAMP";
			}
		}
		String result = super.getTypeName(bookmark, column, useJavaTypes).toUpperCase();
		// There is probably a more elegant way of doing this
		result = result.replaceAll("TIME ZONE\\([^)]*\\)" , "TIME ZONE");
		result = result.replaceAll("DATE\\([^)]*\\)" , "DATE");
		result = result.replaceAll("TO MONTH\\([^)]*\\)" , "TO MONTH");
		result = result.replaceAll("ROWID\\([^)]*\\)" , "ROWID");	//Covers also UROWID
		result = result.replaceAll("LOB\\([^)]*\\)" , "LOB");		//Covers CLOB, NCLOB, BLOB
		result = result.replaceAll("LONG\\([^)]*\\)" , "LONG");
		result = result.replaceAll("LONG RAW\\([^)]*\\)" , "LONG RAW");
		result = result.replaceAll("MLSLABEL\\([^)]*\\)" , "MLSLABEL");
		result = result.replaceAll("BFILE\\([^)]*\\)" , "BFILE");
		return result;
	}

}