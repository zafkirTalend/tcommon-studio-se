package com.quantum.model;

import java.sql.SQLException;

import com.quantum.sql.MultiSQLServer;
import com.quantum.sql.SQLResultSetResults;
import com.quantum.util.connection.ConnectionException;


class TableImpl extends EntityImpl implements Table {
    public TableImpl(Bookmark bookmark, String schema, String name, boolean isSynonym) {
        super(bookmark, schema, name, TABLE_TYPE, isSynonym);
    }
    
    public Integer getSize() {
        Integer size = null;
        try {
        	Bookmark bookmark = getBookmark();
        	String sql = getBookmark().getAdapter().getCountQuery(
        			bookmark.filterQuoteName(getQualifiedName()));
        	
    	    SQLResultSetResults results = (SQLResultSetResults) 
					MultiSQLServer.getInstance().execute( 
							getBookmark(), getBookmark().getConnection(), sql);
    	    if (results.getRowCount() > 0 && results.getColumnCount() > 0) {
    	        size = Integer.valueOf(results.getElement(1, 1).toString());
    	    }
        } catch (SQLException e) {
        } catch (ConnectionException e) {
        }
        return size;
    }
    
    public void deleteAllRows() throws SQLException, ConnectionException {
        String sql = "DELETE FROM " + getQualifiedName();
		MultiSQLServer.getInstance().execute(getBookmark(), getBookmark().getConnection(), sql);
    }
}