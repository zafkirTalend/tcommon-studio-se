package com.quantum.model;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

import com.quantum.Messages;
import com.quantum.log.QuantumLog;
import com.quantum.util.connection.ConnectionException;
import com.quantum.util.connection.PasswordFinder;
import com.quantum.util.proxy.ProxyFactory;

/**
 * 
 */
class ConnectionEstablisherImpl implements ConnectionEstablisher {

	public static final String USERNAME = "user"; //$NON-NLS-1$
    public static final String PASSWORD = "password"; //$NON-NLS-1$
    
    private static final ConnectionEstablisher instance = new ConnectionEstablisherImpl();
    
    private ConnectionEstablisherImpl() {
    }
    
    public static ConnectionEstablisher getInstance() {
        return ConnectionEstablisherImpl.instance;
    }
    
    /**
     * Makes a connection to a JDBC driver based on the data from a bookmark
     * @param bookmark - 
     *     The Bookmark with the data needed to make the connection
     * @param passwordFinder - 
     *     A utility class that can be invoked if the bookmark does not 
     *     include a password
     * @return The Connection object if everything went OK
     */
    public Connection connect(Bookmark bookmark, PasswordFinder passwordFinder)
        throws ConnectionException, SQLException {

        String password = bookmark.getPassword();
        if (bookmark.getPromptForPassword()) {
            password = passwordFinder.getPassword();
            if (passwordFinder.isPasswordMeantToBeSaved()) {
                bookmark.setPassword(password);
            }
        }
        
        if (password != null) {
            Connection connection = connect(bookmark, password);
            if (connection != null) {
		        // Set the autoCommit state of the bookmark to the default on new connections
		 		bookmark.setAutoCommit(bookmark.getDefaultAutoCommit());
		 		// Set the autoCommit state of the JDBC connection to the bookmark autoCommit statec
		       	connection.setAutoCommit(bookmark.isAutoCommit());
            }
	       	return connection;
        } else {
            return null;
        }
        
    }
    private Connection connect(Bookmark bookmark, String password)
        throws ConnectionException, SQLException {
    	JDBCDriver jdbcDriver = bookmark.getJDBCDriver();
        QuantumLog.getInstance().info("Instantiating driver \"" + jdbcDriver.getName() + "\""); //$NON-NLS-1$
    	Driver driver = ProxyFactory.createDriverProxy(bookmark, jdbcDriver.getDriver());
        if (driver != null) {
            Properties properties = getConnectionProperties(bookmark, password);
	        QuantumLog.getInstance().info("Connecting to: " + bookmark.getName()); //$NON-NLS-1$
            Connection connection = driver.connect(bookmark.getConnect(), properties);
            if (connection == null) {
                throw new ConnectionException(
                        "Error: Driver returned a null connection: " + bookmark.toString()); //$NON-NLS-1$
            }
            updateJDBCDriverDetails(jdbcDriver, connection);
            QuantumLog.getInstance().info("Sucessfully connected to: " + bookmark.getName()); //$NON-NLS-1$
            return connection;
        } else {
        	throw new ConnectionException(Messages.getString(
        			ConnectionException.class, "couldNotInstantiateDriver", 
					new Object[] { jdbcDriver.getClassName(), bookmark.getName() }));
        }
    }

    /**
     * @param bookmark
     * @param password
     * @return
     */
    private Properties getConnectionProperties(Bookmark bookmark, String password) {
        Properties connectionProperties = new Properties();
        connectionProperties.put(USERNAME, bookmark.getUsername());
        connectionProperties.put(PASSWORD, password);
        Properties adapterSpecificProperties = bookmark.getAdapter().getConnectionProperties();
        if (adapterSpecificProperties != null && !adapterSpecificProperties.isEmpty()) {
            connectionProperties.putAll(adapterSpecificProperties);
        }
        return connectionProperties;
    }

    /**
     * @param jdbcDriver
     * @param connection
     * @throws SQLException
     */
    private void updateJDBCDriverDetails(JDBCDriver jdbcDriver, Connection connection) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        jdbcDriver.setName(metaData.getDriverName());
        jdbcDriver.setVersion(metaData.getDriverVersion());
    }
}
