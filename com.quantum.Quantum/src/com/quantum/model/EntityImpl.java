package com.quantum.model;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.quantum.adapters.AdapterFactory;
import com.quantum.adapters.DatabaseAdapter;
import com.quantum.sql.MultiSQLServer;
import com.quantum.sql.SQLMetaDataResults;
import com.quantum.util.ModelUtil;
import com.quantum.util.connection.JDBCUtil;
import com.quantum.util.connection.NotConnectedException;
import com.quantum.util.sql.SQLStates;

/**
 * This class models a table or view.
 * 
 * @author bcholmes
 */
abstract class EntityImpl extends DatabaseObjectImpl implements Entity {
	

    private Boolean exists = Boolean.TRUE;
    private boolean isSynonym = false;	// Tells if its a synonym or not
    // Columns will be cached in this array, as sometimes asking for them to the JDBC driver is very costy
    // (for example in Oracle when synonyms and remarks are asked for )
    private Column[] columns = null; 
    
    public EntityImpl(Bookmark bookmark, String schema, String name, String type, boolean isSynonym) {
        super( bookmark, schema, name, type );
        this.isSynonym = isSynonym;
    }
    
   public Column getColumn(String columnName) throws NotConnectedException, SQLException  {
        Column column = null;
        if (this.columns == null) this.columns = getColumns();
        for (int i = 0, length = (this.columns == null) ? 0 : this.columns.length;
            column == null && i < length;
            i++) {
            if (columnName != null && columnName.equals(this.columns[i].getName())) {
                column = this.columns[i];
            }
        }
        return column;
    }
   
    public Column[] getColumns() throws NotConnectedException, SQLException {
        if (this.columns != null) return this.columns;
        Connection connection = getBookmark().getConnection();
        try {
        	this.columns = getColumnsFromMetaData(connection);
        	// An entity by definition should have columns. If it doesn't, it may be a permissions problem
        	// We try to get them from the old-fashioned method
        	if (this.columns.length == 0) {
        		this.columns = getColumnsFromQuery(connection);
        	}
        	return this.columns;
        } catch (SQLException e) {
        	// Also if the driver is not capable to give metadata, try getting the
        	// columns via query
        	if (SQLStates.ODBC_DRIVER_NOT_CAPABLE.equals(e.getSQLState()) 
        			&& AdapterFactory.JDBC_ODBC_BRIDGE.equals(
        					getBookmark().getJDBCDriver().getType())) {
        		this.columns = getColumnsFromQuery(connection);
        		return this.columns;
        	} else {
        		throw e;
        	}
        }
        
    }
    
    /**
	 * @param connection
	 * @return
	 * @throws SQLException
	 */
	private Column[] getColumnsFromMetaData(Connection connection) throws SQLException {
		Map temp = new HashMap();
		DatabaseMetaData metaData = connection.getMetaData();
		ResultSet resultSet = metaData.getColumns(null, getSchema(), getName(), null);
		try {
		    while (resultSet.next()) {
		    	// The default Value must be extracted before the other values, because it's possibly a very big value
		    	// (could be the default value of a very big column), and ORACLE at least (other databases perhaps too)
		    	// gives this big values as an stream of data, that must be read before other columns that go after it.
		    	// If you don't do that, Oracle gives an "Stream is closed" error, because when you read a column after
		    	// the default Value one (default value is number 13, so if you read for example number 14),
		    	// the stream to access that value is implicitly closed.
		    	String defaultValue = resultSet.getString(JDBCUtil.COLUMN_METADATA_DEFAULT_VALUE);
		    	
		        ColumnImpl column = new ColumnImpl(
		            this, 
		            resultSet.getString(JDBCUtil.COLUMN_METADATA_COLUMN_NAME),
		            resultSet.getString(JDBCUtil.COLUMN_METADATA_TYPE_NAME),
		            resultSet.getInt(JDBCUtil.COLUMN_METADATA_DATA_TYPE),
		            resultSet.getInt(JDBCUtil.COLUMN_METADATA_COLUMN_SIZE),
		            resultSet.getInt(JDBCUtil.COLUMN_METADATA_DECIMAL_DIGITS),
		            "YES".equalsIgnoreCase(resultSet.getString(JDBCUtil.COLUMN_METADATA_IS_NULLABLE)),
		            resultSet.getInt(JDBCUtil.COLUMN_METADATA_ORDINAL_POSITION),
		            defaultValue,
					resultSet.getString(JDBCUtil.COLUMN_METADATA_REMARKS)
		            );
		        temp.put(column.getName(), column);
		    }
		} finally {
			resultSet.close();
		}

		resultSet = metaData.getPrimaryKeys(null, getSchema(), getName());
		try {
		    while (resultSet.next()) {
		        String name = resultSet.getString(JDBCUtil.PRIMARY_KEYS_METADATA_COLUMN_NAME);
		        short keySequence = resultSet.getShort(JDBCUtil.PRIMARY_KEYS_METADATA_KEY_SEQ);
		        ColumnImpl column = (ColumnImpl) temp.get(name);
		        if (column != null) {
		            column.setPrimaryKeyOrder(keySequence);
		        }
		        
		    }
		    
		    List columnList = Collections.synchronizedList(
		        new ArrayList(temp.values()));
		    Collections.sort(columnList);
		    return (Column[]) columnList.toArray(new Column[columnList.size()]);
		    
		} finally {
			resultSet.close();
		}
	}
	
	/**
	 * Some databases, (in particular, MS Access under ODBC) aren't terribly friendly
	 * about supporting metadata.  This method scrapes out the data the old-fashioned way.
	 * 
	 * @param temp
	 * @param connection
	 * @throws SQLException
	 */
	private Column[] getColumnsFromQuery(Connection connection) throws SQLException {
		List temp = new ArrayList();
		SQLMetaDataResults results = 
				(SQLMetaDataResults) MultiSQLServer.getInstance().getMetaData(
					this, connection);
		SQLMetaDataResults.Row[] rows = results.getRows();
		for (int i = 0, length = results.getRowCount(); i < length; i++) {
		    ColumnImpl column = new ColumnImpl(
		            this, 
		            (String) rows[i].get(1),
		            (String) rows[i].get(2),
		            ((Integer) rows[i].get(7)).intValue(),
		            ((Long) rows[i].get(3)).longValue(),
		            ((Integer) rows[i].get(4)).intValue(),
		            "Nullable".equalsIgnoreCase((String) rows[i].get(5)),
		            i+1, 
					null,
					"");
		        temp.add(column);
		}
		return (Column[]) temp.toArray(new Column[temp.size()]);
	}
//	/**
//     * Some JDBC drivers (Oracle for example) won't return the comments
//     * We recheck with a custom query, if it's defined
//	 * @param iniComment The already got comment
//	 * @param tableName The fully qualified table name
//	 * @param columnName The column name
//	 * 
//	 * 
//	 */
//	private String getRemarks( String iniComment, String tableName, String columnName) {
//		if (iniComment != null && iniComment.length() > 0) 
//			return iniComment;
//		String comment = "";
//		try {
//			Connection con = getBookmark().getConnection();
//			DatabaseAdapter adapter = getBookmark().getAdapter();
//			Statement stmt = con.createStatement();
//			try {
//				if (adapter != null && stmt != null 
//						&& adapter.getRemarksQuery(tableName, columnName) != null) {
//				
//					stmt.execute(adapter.getRemarksQuery(tableName, columnName));
//					ResultSet set = stmt.getResultSet();
//					try {
//						if (set.next()) {
//							comment = set.getString(1);
//						}
//					} finally {
//						set.close();
//					}
//				}
//			} finally {
//				stmt.close();
//			}
//		} catch (NotConnectedException e) {
//		} catch (SQLException e) {
//		}
//            
//		return comment;
//	}
	
	public Index[] getIndexes() {
        
        List indexList = new ArrayList();
        Map temp = new HashMap();
        try {
            Connection connection = getBookmark().getConnection();
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getIndexInfo(null, getSchema(), getName(), false, false);
            
			try {
	            while (resultSet.next()) {
	                short type = resultSet.getShort(JDBCUtil.INDEX_METADATA_TYPE);
	                if (type != DatabaseMetaData.tableIndexStatistic) {
		                String indexName = resultSet.getString(JDBCUtil.INDEX_METADATA_INDEX_NAME);
		                boolean notUnique = resultSet.getBoolean(JDBCUtil.INDEX_METADATA_NOT_UNIQUE);
		                IndexImpl index = (IndexImpl) temp.get(indexName);
		                if (index == null) {
		                    index = new IndexImpl(this, indexName, !notUnique);
		                    temp.put(indexName, index);
		                }
		                String columnName = resultSet.getString(JDBCUtil.INDEX_METADATA_COLUMN_NAME);
		                String ascending = resultSet.getString(JDBCUtil.INDEX_METADATA_ASC_OR_DESC);
			                index.addColumn(columnName, ascending == null 
			                    ? null : (ascending.toUpperCase().startsWith("A") 
			                        ? Boolean.TRUE : Boolean.FALSE));
	                }
	            }
			} finally {
			    resultSet.close();
			}
            indexList.addAll(temp.values());
            
        } catch (NotConnectedException e) {
        } catch (SQLException e) {
        }
        return (Index[]) indexList.toArray(new Index[indexList.size()]);
    }
    
    public Boolean exists() {
        return this.exists;
    }
    
    
    /**
     * @see com.quantum.model.Entity#getQuotedTableName()
     */
    public String getQuotedTableName() {
        return getBookmark().filterQuoteName(getQualifiedName());
    }

	/* (non-Javadoc)
	 * @see com.quantum.model.TableMetadata#getPrimaryKeyColumns()
	 */
	public Column[] getPrimaryKeyColumns() throws NotConnectedException, SQLException {		
        if (columns == null) {
        	columns = getColumns();
        }
        return ModelUtil.extractPrimaryKeyColumns(columns);
	
	}

	
    public ForeignKey[] getExportedKeys() throws SQLException, NotConnectedException {
    	return getBookmark().getDatabase().getExportedKeys(getSchema(), getName());
    }

    public ForeignKey[] getImportedKeys() throws SQLException, NotConnectedException {
    	return getBookmark().getDatabase().getImportedKeys(getSchema(), getName());
    }
    public ForeignKey[] getReferences() throws SQLException, NotConnectedException {
    	ForeignKey[] importedKeys = getImportedKeys();
    	ForeignKey[] exportedKeys = getExportedKeys();
    	
    	List list = new ArrayList(); // if we could guarantee JDK 1.4, we'd use LinkedHashSet 
    	for (int i = 0, length = importedKeys == null ? 0 : importedKeys.length; i < length; i++) {
			list.add(importedKeys[i]);
		}
    	for (int i = 0, length = exportedKeys == null ? 0 : exportedKeys.length; i < length; i++) {
    		if (!list.contains(exportedKeys[i])) {
    			list.add(exportedKeys[i]);
    		}
		}
    	return (ForeignKey[]) list.toArray(new ForeignKey[list.size()]);
    }
    
   
    /**
	 * @return Returns the isSynonym.
	 */
	public boolean isSynonym() {
		return isSynonym;
	}
	
	/* (non-Javadoc)
	 * @see com.quantum.model.TableMetadata#getCreateStatement()
	 * Because MSSQLServer returns this stuff in chunks, we concatenate the results here.
	 * The statement returned from adapter.getCreateStatement should take care of the ordering.
	 */
	public String getCreateStatement() throws NotConnectedException, SQLException {
		if (this.getType() == Entity.VIEW_TYPE){
			DatabaseAdapter adapter = getBookmark().getAdapter();
			if (adapter == null) return "";
			String query = adapter.getCreateStatement(this.getSchema(), this.getName());
//			String f = getBookmark().getDatabase().getDatabaseName();
//		This work is stopped, as I cannot seem to read the system tables in Access using the JDBC Bridge
//			if (adapter.getType() == AdapterFactory.JDBC_ODBC_BRIDGE &&
//					getBookmark().getDatabase().getDatabaseName().equals("ACCESS") )
//				return MsAccessAdapter.getCreateStatement( this );
//			
			Connection con = getBookmark().getConnection();
			Statement stmt = con.createStatement();
			String result = "";
			if (adapter != null && stmt != null && query != null) {
				try {
					stmt.execute(query);
					ResultSet set = stmt.getResultSet();
					try {
						while (set.next()) {
							result += set.getString(1);
						}
					} finally {
						set.close();
					}
				} finally {
					stmt.close();
				}
				return result;
			}
		}else if(this.getType() == Entity.TABLE_TYPE){
		    DatabaseAdapter adapter = getBookmark().getAdapter();
            if(adapter == null)return "";
            String query = adapter.buildCreateTable(getBookmark(), this,null, false);
            return query;
        }
		return "";
	}
	
	public Trigger[] getTriggers()   throws NotConnectedException, SQLException {
		List list = new ArrayList();
		DatabaseAdapter adapter = getBookmark().getAdapter();
		if (adapter == null) return null;
		String query = adapter.getTriggersStatement(this.getSchema(), this.getName());
		Connection con = getBookmark().getConnection();
		Statement stmt = con.createStatement();
		if (adapter != null && stmt != null && query != null) {
			try {
			stmt.execute(query);
			ResultSet set = stmt.getResultSet();
			String name;
			String moment;
			String forEach;
			String event;
			String actionType;
			String columnName;
			String language;
			String referencing;
			String whenClause;
			String status;
			String body;
			try {
			    if (set.next()) {
			    	// the problem here is that there can be more triggers defined on
			    	// one table, so as long as the first ten fields are the same
			    	// the body should be concatenated.
			    	name = set.getString(1);
			    	moment = set.getString(2);
					forEach = set.getString(3);
					event = set.getString(4);
					actionType = set.getString(5);
					columnName = set.getString(6);
					language = set.getString(7);
					referencing = set.getString(8);
					whenClause = set.getString(9);
					status = set.getString(10);
					body = set.getString(11);
					while(set.next()){
						if(name.equals(set.getString(1)) 
						&& moment.equals(set.getString(2)) 
						&& forEach.equals(set.getString(3))
						&& event.equals(set.getString(4))
						&& actionType.equals(set.getString(5))
						&& columnName.equals(set.getString(6))
						&& language.equals(set.getString(7))
						&& referencing.equals(set.getString(8)) 
						&& whenClause.equals(set.getString(9))
						&& status.equals(set.getString(10))){
							body += set.getString(11);
						}else{
							// Trigger definition finished
							TriggerImpl newTrigger = new TriggerImpl(
									name,
									moment,
									forEach,
									event,
									actionType,
									columnName,
									language,
									referencing,
									whenClause,
									status,
									body);
							list.add(newTrigger);
							// start new definition
					    	name = set.getString(1);
					    	moment = set.getString(2);
							forEach = set.getString(3);
							event = set.getString(4);
							actionType = set.getString(5);
							columnName = set.getString(6);
							language = set.getString(7);
							referencing = set.getString(8);
							whenClause = set.getString(9);
							status = set.getString(10);
							body = set.getString(11);
						}
					}
					TriggerImpl newTrigger = new TriggerImpl(
							name,
							moment,
							forEach,
							event,
							actionType,
							columnName,
							language,
							referencing,
							whenClause,
							status,
							body);
					list.add(newTrigger);
				}
			} finally {
				set.close();
			}
			} finally {
				stmt.close();
		}
			
		}
		return (Trigger[]) list.toArray(new Trigger[list.size()]);
		
	}

	/* (non-Javadoc)
	 * @see com.quantum.model.TableMetadata#getChecks()
	 */
	public Check[] getChecks()   throws NotConnectedException, SQLException {
		List list = new ArrayList();
		DatabaseAdapter adapter = getBookmark().getAdapter();
		if (adapter == null) return null;
		String query = adapter.getChecksStatement(this.getSchema(), this.getName());
		Connection con = getBookmark().getConnection();
		Statement stmt = con.createStatement();
		if (adapter != null && stmt != null && query != null) {
			try {
			stmt.execute(query);
			ResultSet set = stmt.getResultSet();
			try {
			    while (set.next()) {
					CheckImpl newCheck = new CheckImpl(
								set.getString(1),
								set.getString(2)  )	;
					list.add(newCheck);
				}
			} finally {
				set.close();
			}
			} finally {
				stmt.close();
		}
			
		}
		return (Check[]) list.toArray(new Check[list.size()]);
	}
	
	/* (non-Javadoc)
	 * @see com.quantum.model.TableMetadata#getPrivileges()
	 */
	public Privilege[] getPrivileges()   throws NotConnectedException, SQLException {
        List privilegeList = new ArrayList();
        try {
            Connection connection = getBookmark().getConnection();
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet set = metaData.getTablePrivileges(null, getSchema(), getName());
            
			
            while (set.next()) {
				String isGrantable = set.getString(JDBCUtil.PRIVILEGES_METADATA_IS_GRANTABLE);
                PrivilegeImpl privilege = new PrivilegeImpl( 
					set.getString(JDBCUtil.PRIVILEGES_METADATA_GRANTOR),
					set.getString(JDBCUtil.PRIVILEGES_METADATA_GRANTEE),
					set.getString(JDBCUtil.PRIVILEGES_METADATA_ACCESS),
					isGrantable.toUpperCase().equals("YES") ? true : false
					);
				privilegeList.add(privilege);
            }
            set.close();
        } catch (NotConnectedException e) {
        } catch (SQLException e) {
        }
        return (Privilege[]) privilegeList.toArray(new Privilege[privilegeList.size()]);
	}

	}
