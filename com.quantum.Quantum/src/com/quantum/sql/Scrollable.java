package com.quantum.sql;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * @author BC
 */
public interface Scrollable {
	public void nextPage(Connection connection) throws SQLException;
	public void previousPage(Connection connection) throws SQLException;
	public boolean hasNextPage();
	public boolean hasPreviousPage();
	public void setFullMode(boolean fullMode);
	public boolean isFullMode();
	public int getStart();
	public int getEnd();
	public int getLast();
}
