package com.quantum.adapters;

import java.sql.Connection;
import java.sql.SQLException;

import com.quantum.sql.MultiSQLServer;
import com.quantum.sql.SQLResultSetResults;
import com.quantum.sql.parser.SQL;
import com.quantum.util.connection.Connectable;

/**
 * @author Julen
 */
public class OracleStatementExplainer implements StatementExplainer {
    /**
     * Oracle explains mostly everything
     */
    public boolean isExplainable(SQL sqlStatement) {
        return true;
    }
	
	   public SQLResultSetResults explain(
			   Connectable connectable, Connection connection, SQL sqlStatement) 
	    		throws SQLException {
	        MultiSQLServer.getInstance().execute(connectable, connection, 
	                createExplainStatement(sqlStatement), Integer.MAX_VALUE);
	        
	        return (SQLResultSetResults) MultiSQLServer.getInstance().execute(
	                connectable, connection, getPlanStatement(), Integer.MAX_VALUE);
	    }
	   /**
	     * @param sqlStatement	
	     * @return
	     */
	    protected String createExplainStatement(SQL sqlStatement) {
	        String explainStatement = "EXPLAIN PLAN FOR (" + sqlStatement.toString() + ")";
	        return explainStatement;
	    }
		 /**
	     * @return
	     */
	    protected String getPlanStatement() {
		    return "SELECT * FROM PLAN_TABLE";
	    }


}
