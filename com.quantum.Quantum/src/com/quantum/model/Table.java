package com.quantum.model;

import java.sql.SQLException;

import com.quantum.util.connection.ConnectionException;

/**
 * @author BC
 */
public interface Table extends Entity {
    public Integer getSize();
    public void deleteAllRows() throws SQLException, ConnectionException;
}
