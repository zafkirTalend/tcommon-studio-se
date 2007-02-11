package com.quantum.util.connection;

import java.sql.Connection;
import java.sql.SQLException;

import com.quantum.util.Displayable;

/**
 * @author BC Holmes
 */
public interface Connectable extends Displayable {
    public boolean isConnected();    
    public Connection connect(PasswordFinder passwordFinder) throws ConnectionException, SQLException;
    public void disconnect() throws SQLException;
    public Connection getConnection() throws NotConnectedException;    
}
