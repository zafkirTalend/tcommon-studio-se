package com.quantum.model;

import java.sql.SQLException;

import com.quantum.sql.MultiSQLServer;
import com.quantum.sql.SQLResultSetResults;
import com.quantum.util.connection.ConnectionException;


class ViewImpl extends EntityImpl implements View {
    public ViewImpl(Bookmark bookmark, String schema, String name, boolean isSynonym) {
        super(bookmark, schema, name, VIEW_TYPE, isSynonym);
    }

    public Integer getSize() {
        Integer size = null;
        try {
        	Bookmark bookmark = getBookmark();
        	String sql = bookmark.getAdapter().getCountQuery(
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
}