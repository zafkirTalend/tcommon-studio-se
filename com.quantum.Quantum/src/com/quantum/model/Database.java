package com.quantum.model;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.preference.IPreferenceStore;

import com.quantum.QuantumPlugin;
import com.quantum.adapters.DatabaseAdapter;
import com.quantum.sql.MultiSQLServer;
import com.quantum.sql.SQLResultSetResults;
import com.quantum.util.connection.JDBCUtil;
import com.quantum.util.connection.NotConnectedException;

/**
 * @author BC
 */
public class Database {
    
	
	private DatabaseAdapter databaseAdapter;
    private Bookmark bookmark;
    
    private List entityTypes;
    
    /*
     * this map can become some sort of MetaData:
     * It will eventually contain all tablenames
     * and all column names... And consume all memory
     */ 
    private HashMap columnsInTable = new HashMap();
    
    /*
     * This list contains tables used so (spanning different queries, at least that is the intention)
     */
    private ArrayList tables = new ArrayList();
    
    public Database(Bookmark bookmark) {
        this.bookmark = bookmark;
        this.databaseAdapter = bookmark.getAdapter();
    }

    private static final String[] ALL_TABLE_TYPES = { 
            Entity.ALIAS_TYPE, 
	        Entity.TABLE_TYPE, 
	        Entity.VIEW_TYPE, 
	        Entity.SEQUENCE_TYPE };
        
    private static final List STANDARD_TABLE_TYPES = 
        Collections.synchronizedList(new ArrayList());
        
    static {
        for (int i = 0, length = (ALL_TABLE_TYPES == null) ? 0 : ALL_TABLE_TYPES.length;
            i < length;
            i++) {
            STANDARD_TABLE_TYPES.add(ALL_TABLE_TYPES[i]);
        }
    }
    
    public synchronized String[] getEntityTypes() 
        throws NotConnectedException, SQLException {
    	if (this.entityTypes == null) {
    		Collection collection = initializeObjectTypes(this.bookmark.getConnection());
            this.entityTypes = Collections.synchronizedList(new ArrayList(collection));
    	}
        return (String[]) this.entityTypes.toArray(new String[this.entityTypes.size()]);
    }
    
    public String getUsername() throws NotConnectedException, SQLException {
    	return getMetaData().getUserName();
    }


    /**
     * <p>This method returns a set of entity types supported by the database
     * adapter.  This list will always be limited to Tables, Views and 
     * Sequences.</p>
     * 
     * <p>Not all databases support all types.  MySQL only supports 
     * Tables.  Informix supports Tables and Views.  Oracle and DB2 support
     * Tables, Views and Sequences.</p>
     * 
     * @param connection
     * @return a set of Strings, typically "TABLE", "VIEW", and "SEQUENCE"
     * @throws SQLException
     */
    private Set initializeObjectTypes(Connection connection) throws SQLException {
        
        Set set = new HashSet();
        if (this.databaseAdapter.getShowTableQuery(this.bookmark.getUsername()) != null) {
            set.add(Entity.TABLE_TYPE);
        }
        if (this.databaseAdapter.getShowViewQuery(this.bookmark.getUsername()) != null) {
            set.add(Entity.VIEW_TYPE);
        }
        if (this.databaseAdapter.getShowSequenceQuery(this.bookmark.getUsername()) != null) {
            set.add(Entity.SEQUENCE_TYPE);
        }
        if (this.databaseAdapter.getShowPackagesQuery(this.bookmark.getUsername()) != null) {
            set.add(Entity.PACKAGE_TYPE);
        }

        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet resultSet = metaData.getTableTypes();
        while (resultSet.next()) {
            String type = resultSet.getString(JDBCUtil.TABLE_TYPE_METADATA_TABLE_TYPE);
            if (type != null) {
            	// Informix, in particular, pads this with extra spaces
                type = type.trim();
            }
            if (STANDARD_TABLE_TYPES.contains(type)) {
                set.add(type);
            }
        }
        return set;
    }

    public String getInformation() throws SQLException {
        try {
            DatabaseMetaData metaData = getMetaData();
        
            return metaData == null ? null : metaData.getDatabaseProductName() + " " 
                    + metaData.getDatabaseProductVersion();
        } catch (NotConnectedException e) {
            // TODO: think about this...
            return "";
        }
    }
    
    public String getDatabaseName() throws SQLException {
        try {
            DatabaseMetaData metaData = getMetaData();
        
            return metaData == null ? null : metaData.getDatabaseProductName();
        } catch (NotConnectedException e) {
            return "";
        }
    }

    public int getDatabaseMajorVersion() throws SQLException {
        try {
            DatabaseMetaData metaData = getMetaData();
        
            return metaData == null ? 0 : metaData.getDatabaseMajorVersion();
        } catch (NotConnectedException e) {
            return 0;
        }
    }
    
    /**
     * Get a list of entities (tables, views, sequences) for a particular
     * bookmark. This function is usually not redefined because it gives
     * an external interface. You will usually redefine the getShowTableQuery(), 
     * getShowViewQuery(), etc.
     * 
     * @param bookmark -
     *     the bookmark that describes the database that is being accessed.
     * @param passwordFinder -
     *     a utility class that knows how to obtain a password, if required
     * @param schema -
     *     the schema from which to extract
     * @param type -
     *     the type ("VIEW", "TABLE", etc.) of entities to extract or null
     *     if all entity types should be extracted
     * @return 
     *     an array of entity objects representing the tables, views and sequences.
     * @throws SQLException
     */
    public DatabaseObject[] getObjects(Bookmark bookmark, Schema schema, String type) 
        throws SQLException, NotConnectedException {
    	// We call the base function, with a prefix null, that means all the objects
    	return getObjects( bookmark,  schema,  type, null);
    }

    public DatabaseObject[] getObjects(Bookmark bookmark, Schema schema, String type, String prefix) 
    throws SQLException, NotConnectedException {
    	Connection connection = bookmark.getConnection();
    	DatabaseObject[] result = getObjects(bookmark, connection, schema, type, prefix);
    	return (result == null) ? new Entity[0] : result;
    }

    protected DatabaseObject[] getObjects(Bookmark bookmark, Connection connection, Schema schema, String type, String prefix) 
        throws SQLException {
        
        List list = new ArrayList();
        String[] types = (type == null) ? ALL_TABLE_TYPES : new String[] { type };
        IPreferenceStore store = QuantumPlugin.getDefault().getPreferenceStore();
            
        for (int i = 0; i < types.length; i++) {
            list.addAll(getObjectsList(bookmark, connection, types[i], schema, prefix));
           if (store.getBoolean("getSynonyms")) //$NON-NLS-1$
            	list.addAll(getSynonymsList(bookmark, connection, types[i], schema));
        }

        return (DatabaseObject[]) list.toArray(new DatabaseObject[list.size()]);
    }

    protected List getObjectsList(Bookmark bookmark, Connection connection, String type, Schema schema, String prefix)
        throws SQLException {

        List list = new ArrayList();
        //      We try first the JDBC driver
        DatabaseMetaData metaData = connection.getMetaData();
        // getTables needs a null schema to get all the schemas. So we don't pass a "" schema, but a null one
		ResultSet set = null;
		String regexp = (prefix != null) ? prefix + "%" : "%";
		if (metaData.supportsSchemasInTableDefinitions()) {
	        set = metaData.getTables(null, (schema != null) ? schema.getName() : null, regexp, new String[] { type });
		} else {
			set = metaData.getTables(null, null, "%", new String[] { type });
		}
	    
        while (set.next()) {
            String tempSchema = set.getString(JDBCUtil.TABLE_METADATA_TABLE_SCHEM);
            tempSchema = (tempSchema == null) ? "" : tempSchema.trim();
            String tableName = set.getString(JDBCUtil.TABLE_METADATA_TABLE_NAME);
            tableName = (tableName == null) ? "" : tableName.trim();

            if (tableName != null && tableName.length() > 0) {
                DatabaseObject object = DatabaseObjectFactory.getInstance().create(bookmark, tempSchema, tableName, type, false);
                if (object != null) {
                    list.add(object);
                }
            }
        }
        set.close();
        // If we have some results, then the JDBC driver is working, 
        // so we return the results and quit
        if (list.size() > 0) 
        	return list;
        
        
        // If no results, we check also the sql query to get the list of entities
        SQLResultSetResults results = null;
        // Get the proper sql query to the appropiate type of entity
        String sql = getSQL(bookmark, type, schema);
        // If nothing returned, too bad, it seems there is no sql query for that database and entity type
        if (sql != null) {
            results = (SQLResultSetResults) MultiSQLServer.getInstance().execute(
            		bookmark, connection, sql, Integer.MAX_VALUE);
            for (int i = 1, size = (results == null) ? 0 : results.getRowCount(); i <= size; i++) {
                String schemaName = results.getColumnCount() == 1 
                    ? schema.getName() : results.getElement(1, i).toString();
                if (schemaName != null) {
                	schemaName = schemaName.trim();
                }
                String tableName = results.getColumnCount() == 1 
                    ? results.getElement(1, i).toString() 
                    : results.getElement(2, i).toString();
                if (tableName != null && tableName.length() > 0) {
                    DatabaseObject object = DatabaseObjectFactory.getInstance().create(
                        bookmark, schemaName, tableName, type, false);
                    if (object != null) {
                        list.add(object);
                    }
                }
            }
        }
        return list;
    }

    /**
     * Returns a list with the package objects of the given type, using a query  
     * @param bookmark
     * @param connection
     * @param type
     * @param schema
     * @return
     * @throws SQLException
     */
    protected List getPackages(Bookmark bookmark, Connection connection, Schema schema)
    	throws SQLException 
    {
        List list = new ArrayList();
        
        // If no results, we check also the sql query to get the list of entities
        SQLResultSetResults results = null;
        // Get the proper sql query to the appropiate type of entity
        String sql = getSQL(bookmark, DatabaseObject.PACKAGE_TYPE, schema);

        // If nothing returned, too bad, it seems there is no sql query for that database and entity type
        if (sql != null) 
        {
            results = (SQLResultSetResults) MultiSQLServer.getInstance().execute(
            		bookmark, connection, sql, Integer.MAX_VALUE);
            
            for (int i = 1, size = (results == null) ? 0 : results.getRowCount(); i <= size; i++) 
            {
                String schemaName = results.getColumnCount() == 1 
                    ? schema.getName() : results.getElement(1, i).toString();

                if (schemaName != null) {
                	schemaName = schemaName.trim();
                }

                String packageName = results.getColumnCount() == 1 
                    ? results.getElement(1, i).toString() 
                    : results.getElement(2, i).toString();
                if (packageName != null && packageName.length() > 0) {
                    DatabaseObject object = DatabaseObjectFactory.getInstance().create(
                        bookmark, schemaName, packageName, DatabaseObject.PACKAGE_TYPE, false);
                    if (object != null) {
                        list.add(object);
                    }
                }
            }
        }
        return list;
	}

    /**
     * Returns a list with the synonym objects of the given type, using a query  
     * @param bookmark
     * @param connection
     * @param type
     * @param schema
     * @return
     * @throws SQLException
     */
    protected List getSynonymsList(Bookmark bookmark, Connection connection, String type, Schema schema)
    	throws SQLException 
    {

        List list = new ArrayList();
        SQLResultSetResults results = null;
        // Get the proper sql query to the appropiate type of entity
        String sql = this.databaseAdapter.getShowSynonymsQuery(schema.getName(), type);
        // If nothing returned, too bad, it seems there is no sql query for that database and entity type
    	if (sql != null) {
        	results = (SQLResultSetResults) MultiSQLServer.getInstance().execute(
        		bookmark, connection, sql, Integer.MAX_VALUE);
        	for (int i = 1, size = (results == null) ? 0 : results.getRowCount(); i <= size; i++) {
            	String schemaName = results.getColumnCount() == 1 
                	? schema.getName() : results.getElement(1, i).toString();
                if (schemaName != null) {
            		schemaName = schemaName.trim();
            	}
            	String tableName = results.getColumnCount() == 1 
                	? results.getElement(1, i).toString() 
                	  : results.getElement(2, i).toString();
                if (tableName != null && tableName.length() > 0) {
                    Entity entity = DatabaseObjectFactory.getInstance().createEntity(
                            bookmark, schemaName, tableName, type, true);
                	if (entity != null) {
                	    list.add(entity);
                	}
            	}
        	}
    	}
	    return list;
	}

    /**
     * @return	An array of DataType objects, representing the data types supported by this database
     * @throws NotConnectedException
     * @throws SQLException
     */
    public DataType[] getTypes() throws NotConnectedException, SQLException {
    	DatabaseMetaData metaData = getMetaData();
    	List list = new ArrayList();
    	ResultSet results = null;
    	try {
			results = metaData.getTypeInfo();
	    	while (results.next()) {
	    		list.add(new DataType(
	    				results.getInt(JDBCUtil.TYPE_INFO_METADATA_DATA_TYPE),
	    				results.getString(JDBCUtil.TYPE_INFO_METADATA_TYPE_NAME),
						results.getLong(JDBCUtil.TYPE_INFO_METADATA_PRECISION),
						results.getString(JDBCUtil.TYPE_INFO_METADATA_LITERAL_PREFIX),
						results.getString(JDBCUtil.TYPE_INFO_METADATA_LITERAL_SUFFIX),
						results.getString(JDBCUtil.TYPE_INFO_METADATA_CREATE_PARMS),
						results.getShort(JDBCUtil.TYPE_INFO_METADATA_SEARCHABLE)
						));
	    	}
    	} finally {
			if (results!=null){
	    		results.close();				
			}
    	}
    	return (DataType[]) list.toArray(new DataType[list.size()]);
    }


    /**
	 * @return
	 * @throws NotConnectedException
	 * @throws SQLException
	 */
	public DatabaseMetaData getMetaData() throws NotConnectedException, SQLException {
		Connection connection = this.bookmark.getConnection();
    	DatabaseMetaData metaData = connection.getMetaData();
		return metaData;
	}

	private String getSQL(Bookmark bookmark, String type, Schema schema) {
		String name = "";
		if(schema != null) name = schema.getName();
	    if (Entity.TABLE_TYPE.equals(type)) {
            return this.databaseAdapter.getShowTableQuery(name);
        } else if (Entity.ALIAS_TYPE.equals(type)) {
            return this.databaseAdapter.getShowAliasQuery(name);
        } else if (Entity.VIEW_TYPE.equals(type)) {
            return this.databaseAdapter.getShowViewQuery(name);
        } else if (Entity.SEQUENCE_TYPE.equals(type)) {
            return this.databaseAdapter.getShowSequenceQuery(name);
        } else if (Entity.PACKAGE_TYPE.equals(type)) {
            return this.databaseAdapter.getShowPackagesQuery(name);
        } else {
            return null;
        }
    }
	
	public ForeignKey[] getExportedKeys(String schema, String entityName) 
			throws NotConnectedException, SQLException {
		DatabaseMetaData metaData = getMetaData();
		List list = new ArrayList();
		return getForeignKeys(list, metaData.getExportedKeys(null, schema, entityName));
	}

	public ForeignKey[] getImportedKeys(String schema, String entityName) 
			throws NotConnectedException, SQLException {
		DatabaseMetaData metaData = getMetaData();
		List list = new ArrayList();
		return getForeignKeys(list, metaData.getImportedKeys(null, schema, entityName));
	}

	/**
	 * @param list
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	private ForeignKey[] getForeignKeys(List list, ResultSet resultSet) throws SQLException {
		ForeignKeyImpl foreignKey = null;
		
		int lowestKeySequence = Integer.MAX_VALUE;
		try {
			while (resultSet.next()) {
				int keySequence = resultSet.getInt(JDBCUtil.FOREIGN_KEY_METADATA_KEY_SEQ);
				lowestKeySequence = Math.min(lowestKeySequence, keySequence);
				
				if (keySequence == lowestKeySequence || foreignKey == null) {
					foreignKey = new ForeignKeyImpl();
					list.add(foreignKey);
					foreignKey.setName(resultSet.getString(JDBCUtil.FOREIGN_KEY_METADATA_FK_NAME));
					foreignKey.setUpdateRule(resultSet.getShort(JDBCUtil.FOREIGN_KEY_METADATA_UPDATE_RULE));
					foreignKey.setDeleteRule(resultSet.getShort(JDBCUtil.FOREIGN_KEY_METADATA_DELETE_RULE));
					foreignKey.setForeignEntitySchema(resultSet.getString(JDBCUtil.FOREIGN_KEY_METADATA_FKTABLE_SCHEM));
					foreignKey.setForeignEntityName(resultSet.getString(JDBCUtil.FOREIGN_KEY_METADATA_FKTABLE_NAME));
					foreignKey.setLocalEntitySchema(resultSet.getString(JDBCUtil.FOREIGN_KEY_METADATA_PKTABLE_SCHEM));
					foreignKey.setLocalEntityName(resultSet.getString(JDBCUtil.FOREIGN_KEY_METADATA_PKTABLE_NAME));
				}
				
				foreignKey.addColumns(
						resultSet.getString(JDBCUtil.FOREIGN_KEY_METADATA_PKCOLUMN_NAME), 
						resultSet.getString(JDBCUtil.FOREIGN_KEY_METADATA_FKCOLUMN_NAME));
			}
			return (ForeignKey[]) list.toArray(new ForeignKey[list.size()]);
		} finally {
			resultSet.close();
		}
	}

	/**
	 * @return
	 * @throws SQLException
	 * @throws NotConnectedException
	 */
	public Schema[] getSchemas() throws NotConnectedException, SQLException {
		List list = new ArrayList();
		
		if (supportsSchemasInTableDefinitions()) {
			ResultSet resultSet = getMetaData().getSchemas();
			try {
				while (resultSet.next()) {
					String schemaName = resultSet.getString(JDBCUtil.SCHEMA_METADATA_TABLE_SCHEM);
					list.add(new Schema(schemaName));
				}
			} finally {
				resultSet.close();
			}
		}
		return (Schema[]) list.toArray(new Schema[list.size()]);
	}

	/**
	 * @return
	 * @throws SQLException
	 * @throws NotConnectedException
	 */
	public boolean supportsSchemasInTableDefinitions() 
			throws NotConnectedException, SQLException {
		return getMetaData().supportsSchemasInTableDefinitions();
	}
	
	public boolean tableExists(String name)
	{
        // first check the cache
        if(tables.contains(name))return true;
		String sql = this.databaseAdapter.getTableExistsQuery(name);
        // If nothing returned, too bad, it seems there is no sql query for that database and entity type
    	if (!sql.equals("")) 
    	{
    		try
    		{
        	SQLResultSetResults results = (SQLResultSetResults) MultiSQLServer.getInstance().execute(
        		bookmark, bookmark.getConnection(), sql, Integer.MAX_VALUE);
        	for (int i = 1, size = (results == null) ? 0 : results.getRowCount(); i <= size; i++) {
                if(results.getElement(1,i).toString().equals("1"))
                {
                    tables.add(name);
                    return true;
                }
        	}
    		}catch(SQLException e){    			
    		}catch(NotConnectedException e){
    		}
        }
    	return false;
	}
    
	public boolean columnExists(String tableName, String columnName)
	{
        // first check the Map for stored results.
        Set hmKeys = columnsInTable.keySet();
        Iterator setiterator = hmKeys.iterator();
        List tableList = null;
        while (setiterator.hasNext()) {
           String key = setiterator.next().toString();
           if(key.equalsIgnoreCase(tableName))
           {
               // we are in business
               List value = (List)columnsInTable.get(key);
               for(int i = 0; i < value.size(); i++)
               {
                   if(((String)value.get(i)).equalsIgnoreCase(columnName)){
                       return true;
                   }
               }
               tableList = value;
           }
        }
		String sql = this.databaseAdapter.getColumnExistsQuery(tableName, columnName);
        // If nothing returned, too bad, it seems there is no sql query for that database and entity type
    	if (sql != null) 
    	{
    		try
    		{
        	SQLResultSetResults results = (SQLResultSetResults) MultiSQLServer.getInstance().execute(
        		bookmark, bookmark.getConnection(), sql, Integer.MAX_VALUE);
        	for (int i = 1, size = (results == null) ? 0 : results.getRowCount(); i <= size; i++) {
                if(results.getElement(1,i).toString().equals("1"))
                {
                    if(tableList != null)
                    {
                        tableList.add(columnName);
                        columnsInTable.put(tableName, tableList);
                    }else{
                        List l = new ArrayList();
                        l.add(columnName);
                        columnsInTable.put(tableName, l);
                    }
                    return true;
                }
        	}
    		}catch(SQLException e){    			
    		}catch(NotConnectedException e){
    		}
        }
    	return false;
	}
	
	public String[] getTablesForColumn(String columnName)
	{
		String sql = this.databaseAdapter.getTablesForColumnQuery(columnName);
		if(sql != null)
		{
			List tables = new ArrayList();
			try
			{
	        	SQLResultSetResults results = (SQLResultSetResults) MultiSQLServer.getInstance().execute(
	            		bookmark, bookmark.getConnection(), sql, Integer.MAX_VALUE);
	        	for (int i = 1, size = (results == null) ? 0 : results.getRowCount(); i <= size; i++) {
	        		tables.add(results.getElement(1,i).toString());
	        	}
    		}catch(SQLException e){    			
    		}catch(NotConnectedException e){
    		}
			return (String[]) tables.toArray(new String[tables.size()]);
		}else{
			// Do it via dmd: loop over all tables and then over all columns..
			return (String[]) null;
		}
	}
}
