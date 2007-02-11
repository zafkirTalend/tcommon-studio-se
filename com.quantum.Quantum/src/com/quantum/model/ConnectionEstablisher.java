package com.quantum.model;

import java.sql.Connection;
import java.sql.SQLException;

import com.quantum.util.connection.ConnectionException;
import com.quantum.util.connection.PasswordFinder;


/**
 * @author BC
 */
public interface ConnectionEstablisher {
    
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
        throws ConnectionException, SQLException;
}
