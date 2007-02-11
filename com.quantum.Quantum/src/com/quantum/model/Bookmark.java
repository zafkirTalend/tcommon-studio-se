package com.quantum.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.quantum.IQuantumConstants;
import com.quantum.QuantumPlugin;
import com.quantum.actions.BookmarkSelectionUtil;
import com.quantum.adapters.AdapterFactory;
import com.quantum.adapters.DatabaseAdapter;
import com.quantum.log.QuantumLog;
import com.quantum.util.QuantumUtil;
import com.quantum.util.connection.Connectable;
import com.quantum.util.connection.ConnectionException;
import com.quantum.util.connection.NotConnectedException;
import com.quantum.util.connection.PasswordFinder;
import com.quantum.util.sql.SQLGrammar;

import org.eclipse.jface.preference.IPreferenceStore;

/**
 * Class Bookmark holds the "static" information of a bookmark, that is the data that
 * is saved and loaded from the external file and describes a bookmark. This info will
 * be filled up by the end user.
 * 
 * @author root
 */
public class Bookmark implements Connectable {
	
	public static final int SCHEMA_RULE_USE_ALL = 1;
	public static final int SCHEMA_RULE_USE_DEFAULT = 2;
	public static final int SCHEMA_RULE_USE_SELECTED = 3;
    
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	private String name = ""; //$NON-NLS-1$
	private String username = ""; //$NON-NLS-1$
    private String password = ""; //$NON-NLS-1$
    private String connectionUrl = ""; //$NON-NLS-1$
    private JDBCDriver driver;
    private boolean quoteAll;
	private boolean ignoreQueryViewSetting;
    /**  List of local database keywords, loaded on connect. */
    private Set dbKeywords = null;		
    /**  List of database string functions allowed as escaped functions for JDBC */
    private Set stringFunctions = null;		
    /**
     * String will all the characters that can be used in unquoted
     * identifier names (those beyond a-z, A-Z, 0-9 and _).
     */
    private String extraNameChars = "";	
    /**  List of all data types supported by the database. */
    private DataType[] dataTypes = null;	
    /**  Quote string for identifiers in this database. */
    private String identifierQuoteString = "\"";	
    
    private int schemaRule = SCHEMA_RULE_USE_ALL;
    
    /**
     * A quick list is a list of favourite tables that a person might want to view
     * without having to look at the entire list of tables.
     */
    private Map quickList = new Hashtable();
	private Set schemas = new HashSet();   
	private Connection connection = null;
    private ConnectionEstablisher connectionEstablisher;
    private boolean changed = true;
    private List queries = Collections.synchronizedList(new ArrayList());
    private boolean promptForPassword = false;
    private boolean autoCommit = true;
	private boolean stripNewline = false;
	private boolean sendQueryAsIs = false;
	private String autoCommitPreference = IQuantumConstants.autoCommitTrue;
	private Database database;
	
	private Schema defaultSchema;
	private String defaultEncoding = "";
	
	public Bookmark() {
        this(ConnectionEstablisherImpl.getInstance());
	}
    
    public Bookmark(ConnectionEstablisher connectionEstablisher) {
        this.connectionEstablisher = connectionEstablisher;
    }

	public Bookmark(Bookmark data) {
		this();
		setName(data.getName());
		setUsername(data.getUsername());
		setPassword(data.getPassword());
		setConnect(data.getConnect());
		setJDBCDriver(data.getJDBCDriver());
        setPromptForPassword(data.getPromptForPassword());
        setAutoCommit(data.isAutoCommit());
        setAutoCommitPreference(data.getAutoCommitPreference());
        setQuoteAll(data.isQuoteAll());
        setSendQueryAsIs(data.isSendQueryAsIs());
        setStripNewline(data.isStripNewline());
		setIgnoreQueryViewSetting(data.isIgnoreQueryViewSetting());
        setSchemaRule(data.getSchemaRule());
        
        this.schemas.addAll(data.schemas);
        this.quickList = new Hashtable(data.quickList);
	}

	/**
	 * Returns the JDBC URL.
	 * @return String
	 */
	public String getConnect() {
		return this.connectionUrl;
	}

	/**
	 * Returns the password.
	 * @return String
	 */
	public String getPassword() {
		if (this.promptForPassword) {
			return null;
		} else {
			return this.password;
		}
	}

	/**
	 * Returns the username.
	 * @return String
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the connect.
	 * @param connectionUrl The connect to set
	 */
	public void setConnect(String connectionUrl) {
		if (connectionUrl == null) {
			connectionUrl = ""; //$NON-NLS-1$
		}
		this.connectionUrl = connectionUrl;
	}

	/**
	 * Sets the password.
	 * @param password The password to set
	 */
	public void setPassword(String password) {
		if (password == null) {
			password = ""; //$NON-NLS-1$
		}
		this.password = password;
	}

	/**
	 * Sets the username.
	 * @param username The username to set
	 */
	public void setUsername(String username) {
		if (username == null) {
			username = ""; //$NON-NLS-1$
		}
		this.username = username;
	}

	/**
	 * Returns the name.
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 * @param name The name to set
	 */
	public void setName(String name) {
		if (name == null) {
			name = ""; //$NON-NLS-1$
		}
        if (!name.equals(this.name)) {
         
            String oldName = this.name;
            this.name = name;
            this.propertyChangeSupport.firePropertyChange(IQuantumConstants.NAME_PROPERTY, oldName, this.name);
            this.changed = true;
        }
	}

	public boolean isEmpty() {
		if (name.equals("") && //$NON-NLS-1$
		username.equals("") && //$NON-NLS-1$
		password.equals("") && //$NON-NLS-1$
		connectionUrl.equals("") && //$NON-NLS-1$
		driver.equals("") && //$NON-NLS-1$
		driver == null) {
			return true;
		}
		return false;
	}
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("["); //$NON-NLS-1$
		buffer.append("name="); //$NON-NLS-1$
		buffer.append(name);
		buffer.append(", "); //$NON-NLS-1$
		buffer.append("username="); //$NON-NLS-1$
		buffer.append(username);
		buffer.append(", "); //$NON-NLS-1$
		buffer.append("password=****"); //$NON-NLS-1$
		buffer.append(", "); //$NON-NLS-1$
		buffer.append("connect="); //$NON-NLS-1$
		buffer.append(connectionUrl);
		buffer.append(", "); //$NON-NLS-1$
		buffer.append("driver="); //$NON-NLS-1$
		buffer.append(driver);
		buffer.append("]"); //$NON-NLS-1$
		return buffer.toString();
	}
	
    public Connection connect(PasswordFinder passwordFinder) throws ConnectionException, SQLException {
        boolean isConnected = isConnected();
        if (this.connection == null) {
            this.connection = this.connectionEstablisher.connect(this, passwordFinder);
    		if (this.connection != null) {
    			try {
    				DatabaseMetaData metadata = this.connection.getMetaData();
    				String keywords = metadata.getSQLKeywords();
    				this.extraNameChars = metadata.getExtraNameCharacters();
    				String[] keywordArray = keywords.split(",");
    				this.dbKeywords = Collections.synchronizedSet(new HashSet());
    				synchronized(this.dbKeywords) {
    					for (int i = 0; i < keywordArray.length; i++) {
    						this.dbKeywords.add(keywordArray[i].trim().toUpperCase());
    					}
    				}
    				String stringFns = metadata.getStringFunctions();
    				String [] stringFunctionsArray = stringFns.split(",");
    				this.stringFunctions = Collections.synchronizedSet(new HashSet());
    				synchronized(this.stringFunctions) {
    					for (int i = 0; i < stringFunctionsArray.length; i++) {
    						this.stringFunctions.add(stringFunctionsArray[i].trim().toUpperCase());
    					}
    				}
    				dataTypes = getDatabase().getTypes();
    				// Get the quote character(s) for identifiers
					identifierQuoteString = metadata.getIdentifierQuoteString();
					
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
    		}
        }
        
        if (isConnected() != isConnected) {
            BookmarkSelectionUtil.getInstance().setLastUsedBookmark(this);
            this.propertyChangeSupport.firePropertyChange(
                "connected", isConnected, isConnected());
        }
        return this.connection;
    }

    /**
	 * Returns the connection object. 
	 * @return the Connection object associated with the current JDBC source.
	 * 
	 */
    public Connection getConnection() throws NotConnectedException {
    	if (this.connection == null) {
            throw new NotConnectedException();
    	}
        return this.connection;
    }

    /**
	 * @return true if the BookmarkNode is connected to a JDBC source
	 */
    public boolean isConnected() {
    	return (connection != null);
    }

    /**
	 * Sets the connection member. From that moment on the BookmarkNode is "connected" (open)
	 * @param connection : a valid connection to a JDBC source
	 */
    public void setConnection(Connection connection) {
    	this.connection = connection;
    }

    public void disconnect() throws SQLException {
    	// TODO: A cleaning of the depending objects could be implemented here. If not, the disconnection
    	// does not free the related objects, like Entities, etc.
        boolean isConnected = isConnected();
        try {
            if (this.connection != null) {
                this.connection.close();
            }
            
            QuantumLog.getInstance().info("Disconnecting from bookmark " + this.name);
        } finally {
            this.connection = null;
            this.database = null;
            this.dbKeywords = null;
            this.dataTypes = null;
            this.defaultSchema = null;
            this.stringFunctions = null;
            if (isConnected() != isConnected) {
                this.propertyChangeSupport.firePropertyChange(
                    "connected", isConnected, isConnected());
            }
        }
    }
    public void addSchema(String schema) {
        if (schema != null && schema.trim().length() > 0) {
            addSchema(new Schema(schema));
        }
    }

    public void addSchema(Schema qualifier) {
        if (qualifier != null) {
            this.schemas.add(qualifier);
            this.changed = true;
            this.propertyChangeSupport.firePropertyChange(IQuantumConstants.SCHEMAS_PROPERTY, null, null);
        }
    }

    public void setSchemaSelections(Schema[] schemas) {
        this.schemas.clear();
        for (int i = 0, length = (schemas == null) ? 0 : schemas.length;
            i < length;
            i++) {
            this.schemas.add(schemas[i]);
            
        }
        this.changed = true;
        this.propertyChangeSupport.firePropertyChange(IQuantumConstants.SCHEMAS_PROPERTY, null, null);
    }

    /**
     * @return a list of all the schemas that have been set up.
     */
    public Schema[] getSchemaSelections() {
        List list = new ArrayList(this.schemas);
        Collections.sort(list);
        return (Schema[]) list.toArray(new Schema[list.size()]);
    }
    
    public Schema[] getSchemas() throws NotConnectedException, SQLException {
    	Schema[] schemas = null;
    	if (useUsernameAsSchema()) {
    		// do nothing
    	} else if (useAllSchemas()) {
	    	schemas = getDatabase().getSchemas();
    	} else {
    		schemas = verifySchemas(getSchemaSelections());
    	}
    	return (schemas == null || schemas.length == 0) 
				? new Schema[] { getDefaultSchema() } 
    			: schemas;
    }
    
    public Schema getSchema(String name) throws NotConnectedException, SQLException {
    	Schema result = null;
    	if (name != null) {
    		Schema[] schemas = getSchemas();
    		for (int i = 0, length = schemas == null ? 0 : schemas.length; i < length; i++) {
				if (name.equals(schemas[i].getName())) {
					result = schemas[i];
					break;
				}
			}
    	}
    	return result;
    }

	/**
	 * @param schemaSelections
	 * @return
	 * @throws SQLException
	 * @throws NotConnectedException
	 */
	private Schema[] verifySchemas(Schema[] schemaSelections) 
			throws NotConnectedException, SQLException {
		Schema[] schemasFromDatabase = getDatabase().getSchemas();
		List list = Arrays.asList(schemasFromDatabase);
		for (int i = 0, length = schemaSelections == null ? 0 : schemaSelections.length; 
				i < length; i++) {
			schemaSelections[i].setExists(list.contains(schemaSelections[i]));
		}
		return schemaSelections;
	}

	/**
	 * @return
	 * @throws NotConnectedException
	 * @throws SQLException
	 */
	public Schema getDefaultSchema() throws NotConnectedException, SQLException {
		if (this.defaultSchema == null) {
			String username = getDatabase().getUsername();
			String actual = getAdapter().getDefaultSchema(username);
			this.defaultSchema = new Schema(actual, username, true);
		}
		return this.defaultSchema;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 * Two bookmarks are equal if they have the same name.
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof Bookmark)) return false;
		String name = ((Bookmark)obj).getName();
		if (name.equals(this.getName())) return true;
		return false;
	}

    /**
     * @param listener
     */
    public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
        this.propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * @param listener
     */
    public synchronized void removePropertyChangeListener(PropertyChangeListener listener) {
        this.propertyChangeSupport.removePropertyChangeListener(listener);
    }

    public void addQuickListEntry(String type, String schemaName, String name, boolean isSynonym) {
        DatabaseObject object = DatabaseObjectFactory.getInstance().create(this, schemaName, name, type, isSynonym);
        this.quickList.put(object.getQualifiedName(), object);
        this.propertyChangeSupport.firePropertyChange("quickList", null, null);
        this.changed = true;
    }
    
    public void addQuickListEntry(DatabaseObject entity) {
    	
        addQuickListEntry(entity.getType(), entity.getSchema(), entity.getName(), entity.isSynonym());
    }
    
    public void removeQuickListEntry(DatabaseObject entity) {
        if (entity != null  && this.quickList.containsKey(entity.getQualifiedName())) {
            this.quickList.remove(entity.getQualifiedName());
            this.propertyChangeSupport.firePropertyChange("quickList", null, null);
            this.changed = true;
        }
    }
    
    public DatabaseObject[] getQuickListEntries() {
        return (DatabaseObject[]) this.quickList.values().toArray(new Entity[this.quickList.size()]);
    }
    
    public boolean hasQuickList() {
        return !this.quickList.isEmpty();
    }
    /**
     * @return
     */
    public boolean isChanged() {
        return changed;
    }

    /**
     * @param b
     */
    public void setChanged(boolean b) {
        changed = b;
    }
    
    public Database getDatabase() throws NotConnectedException {
        if (!isConnected()) {
            throw new NotConnectedException();
        }
        if (this.database == null) {
        	this.database = new Database(this);
        }
        return this.database;
    }
    
    public DatabaseAdapter getAdapter() {
        return this.driver == null 
				? null 
				: AdapterFactory.getInstance().getAdapter(this.driver.getType());
    }

    public DatabaseObject[] getObjectsForSchema(Schema schema, String type) throws SQLException {
        try {
            DatabaseObject[] objects = getDatabase().getObjects(this, schema, type);
            return objects;
        } catch (NotConnectedException e) {
            return new DatabaseObject[0];
        }
    }
    
    public DatabaseObject getObject(Schema schema, String name) throws SQLException {
        DatabaseObject result = null;
        if (schema != null && name != null) {
            DatabaseObject[] objects = getObjectsForSchema(schema, null);
            for (int i = 0, length = (objects == null) ? 0 : objects.length;
                result == null && i < length;
                i++) {
            	// JHvdV thinks this will always fail
            	// because we compare a Schema to a String
                if (schema.getName().equals(objects[i].getSchema()) &&
                    name.equalsIgnoreCase(objects[i].getName())) {
                    result = objects[i];
                }
            }
        }
        return result;
    }
    
    public boolean isInQuickList(Entity entity) {
        return this.quickList.containsKey(entity.getQualifiedName());
    }
    
    /**
     * 
     * @param queryString
     */
    public void addQuery(String queryString) {
        if (this.queries.contains(queryString)) {
            this.queries.remove(queryString);
        }
        this.queries.add(queryString);
        
        int size = getQueryHistorySize();
        
        while (this.queries.size() > size) {
            this.queries.remove(0);
        }
        this.propertyChangeSupport.firePropertyChange(IQuantumConstants.QUERIES_PROPERTY, null, null);
        this.changed = true;
    }
    
    public String[] getQueries() {
        return (String[]) this.queries.toArray(new String[this.queries.size()]);
    }
    
    private int getQueryHistorySize() {
        IPreferenceStore store =
            QuantumPlugin.getDefault().getPreferenceStore();
        return store.getInt(getClass().getName() + ".queryHistorySize"); //$NON-NLS-1$
    }
    /**
     * @return
     */
    public boolean getPromptForPassword() {
        return promptForPassword;
    }

    /**
     * @param b
     */
    public void setPromptForPassword(boolean b) {
        promptForPassword = b;
    }

	/**
	 * @return True if the bookmark should set the auto-commit property of the connection
	 */
	public boolean isAutoCommit() {
		return autoCommit;
	}
	/**
	 * @return The AutoCommit preference as defined
	 */
	public String getAutoCommitPreference() {
		return autoCommitPreference;
	}

	/**
	 * @param b
	 */
	public void setAutoCommit(boolean b) {
		autoCommit = b;
	}

	/**
	 * @param string
	 */
	public void setAutoCommitPreference(String string) {
		autoCommitPreference = string;
	}

	// Returns true or false indicating whether this bookmark must be set to AutoCommit on connection or not 
	public boolean getDefaultAutoCommit(){
		if (autoCommitPreference.equals(IQuantumConstants.autoCommitTrue)) return true;
		else if (autoCommitPreference.equals(IQuantumConstants.autoCommitFalse)) return false;
		else if (autoCommitPreference.equals(IQuantumConstants.autoCommitSaved)) return autoCommit;
		
		return true;	
	}

	public void setJDBCDriver(JDBCDriver jdbcDriver) {
		this.driver = BookmarkCollection.getInstance().findDriver(jdbcDriver);
        this.changed = true;
	}
	
	public JDBCDriver getJDBCDriver() {
		return this.driver;
	}
	public boolean useAllSchemas() {
		return this.schemaRule == SCHEMA_RULE_USE_ALL;
	}
	public boolean useUsernameAsSchema() {
		return this.schemaRule == SCHEMA_RULE_USE_DEFAULT;
	}
	public boolean useSelectedSchemas() {
		return this.schemaRule == SCHEMA_RULE_USE_SELECTED;
	}
	public int getSchemaRule() {
		return this.schemaRule;
	}
	public void setSchemaRule(int schemaRule) {
        if (this.schemaRule != schemaRule) {
            this.schemaRule = schemaRule;
            this.propertyChangeSupport.firePropertyChange(IQuantumConstants.SCHEMAS_PROPERTY, null, null);
        }
	}

	public String getDisplayName() {
		return this.name;
	}

	/**
	 * @param query
	 */
	public void removeQuery(String query) {
		if (this.queries.remove(query)) {
	        this.propertyChangeSupport.firePropertyChange(IQuantumConstants.QUERIES_PROPERTY, null, null);
	        this.changed = true;
		}
	}
	/**
	 * @return True if the bookmark must quote all entities
	 */
	public boolean isQuoteAll() {
		return quoteAll;
	}
	/**
	 * @param quoteAll True if the bookmark must quote all entities
	 */
	public void setQuoteAll(boolean quoteAll) {
		this.quoteAll = quoteAll;
	}
	
	/**
	 * @return	True if the bookmark must ignore the autocommit settings 
	 * 			of the Query View
	 */
	public boolean isIgnoreQueryViewSetting() {
		return ignoreQueryViewSetting;
	}
	

	/**
	 * @param selection	True if the bookmark must ignore the autocommit settings 
	 * 					of the Query View
	 */
	public void setIgnoreQueryViewSetting(boolean selection) {
		this.ignoreQueryViewSetting = selection;
	}	
	
	/**
	 * Quotes the identifier (table or column), according to the defined rules, possibly not changing it at all
	 * @param identifier, it's a table name possibly with the schema, or a column name, possibly with the table name
	 * @return
	 */
	public String filterQuoteName(String identifier) {
		// Define a pattern with the "allowed" character.
		// I a character is not there, the table name is to be quoted
//		String schemaName = QuantumUtil.getSchemaName(tableName);
        
        if(identifier.startsWith(identifierQuoteString))
            return identifier; // already quoted
		
        String justTableName = QuantumUtil.getTableName(identifier);
		
		// If we must quote all names, or is mixed case, quote
		if (this.quoteAll || !identifier.equals(identifier.toUpperCase())) 
			return QuantumUtil.quote(identifier, identifierQuoteString);
		// Check for invalid characters
		for (int i = 0; i < justTableName.length(); i++) {
			char c = justTableName.charAt(i);
			
			if ( !Character.isLetterOrDigit(c) &&  c != '_' && 
					this.extraNameChars.indexOf(c) < 0)
				return QuantumUtil.quote(identifier,identifierQuoteString);
		}
		// If it's a reserved word, quote also
		if (isKeyword(justTableName) ) 
			return QuantumUtil.quote(identifier,identifierQuoteString);
		// If no condition is met, we don't quote
		return identifier;
		
	}
	
	/**
	 * @param word	The reserved word
	 * @return true if it's a generic reserved word, or a particular reserved work for this database, comparison is case unsensitive
	*/
	public boolean isKeyword(String word) {
		// dbKeywords has only the reserved words particular to the database
		return dbKeywords.contains(word.toUpperCase()) || 
				SQLGrammar.getInstance().isKeyword(word);
	}

	/**
	 * @return true if it's an allowed string function for JDBC escapes, false if not, comparison is case unsensitive
	 */
	public boolean isStringFunction(String word) {
		return stringFunctions.contains(word.toUpperCase());
	}
	
	public String getObjectType(String word){
		return this.getAdapter().getObjectType(this, word);
	}
	/**
	 * @return Returns the data types for the database.  
	 * @throws NotConnectedException If the bookmark is not connected.
	 * @throws SQLException If no available datatypes
	 */
	public DataType[] getDataTypes() throws NotConnectedException, SQLException {
	   	if (this.connection == null) {
            throw new NotConnectedException();
    	}
	   	if (dataTypes == null) { 
	   		throw new SQLException();
	   	}
 		return dataTypes;
	}

	/**
	 * @param dataTypeName	The Database Name of the type
	 * @return	The DataType object with that type name, null if not found
	 * @throws NotConnectedException	If bookmark is not connected
	 * @throws SQLException	If no available datatypes
	 */
	public DataType getDataType(String dataTypeName) throws NotConnectedException, SQLException {
		DataType[] dataTypes = getDataTypes();
		for (int i = 0; i < dataTypes.length; i++) {
            if ( dataTypes[i].getDatabaseTypeName().equals(dataTypeName) ) 
               return dataTypes[i];  
         }       
		return null;
	}

	public void setStripNewline(boolean selection) {
		this.stripNewline = selection;		
	}

	public void setSendQueryAsIs(boolean selection) {
		this.sendQueryAsIs = selection;	
	}

	/**
	 * @return Returns true if the bookmark won't add semicolons to the end of the parsed queries
	 */
	public boolean isSendQueryAsIs() {
		return sendQueryAsIs;
	}

	/**
	 * @return Returns true if the bookmark will strip newlines from input (some databases (DB2) balk at them)
	 */
	public boolean isStripNewline() {
		return stripNewline;
	}

	public String getDefaultEncoding() {
		return defaultEncoding;
	}

	public void setDefaultEncoding(String item) {
		this.defaultEncoding = item;
	}

	/**
	 * @return Returns the stringFunctions.
	 */
	public Set getStringFunctions() {
		return stringFunctions;
	}

	public boolean supportsLikeEscapeClause() {
		DatabaseMetaData metadata = null; 
		try {
			metadata = this.connection.getMetaData();
			return metadata.supportsLikeEscapeClause();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
