package com.quantum.adapters;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;

import com.quantum.sql.MultiSQLServer;
import com.quantum.sql.SQLResultSetResults;
import com.quantum.sql.parser.SQL;
import com.quantum.util.connection.Connectable;

/**
 * @author BC Holmes
 */
public class DB2StatementExplainer implements StatementExplainer {
    
    private Random random = new Random();

    /**
     * An explainable statement is a DELETE, INSERT, SELECT, SELECT INTO, UPDATE, 
     * VALUES, or VALUES INTO SQL statement.
     */
    public boolean isExplainable(SQL sqlStatement) {
        return true;
    }

    public SQLResultSetResults explain(
            Connectable connectable, Connection connection, SQL sqlStatement) 
    		throws SQLException {
        int queryNumber = getRandomQueryNumber();
        MultiSQLServer.getInstance().execute(connectable, connection, 
                createExplainStatement(sqlStatement, queryNumber), Integer.MAX_VALUE);
        
        return (SQLResultSetResults) MultiSQLServer.getInstance().execute(
                connectable, connection, getPlanStatement(queryNumber), Integer.MAX_VALUE);
    }

    /**
     * @param sqlStatement
     * @param queryNumber
     * @return
     */
    protected String createExplainStatement(SQL sqlStatement, int queryNumber) {
        String explainStatement = "EXPLAIN PLAN SET queryno = " +
        	queryNumber + " FOR (" + sqlStatement.toString() + ")";
        return explainStatement;
    }

    /**
     * @param queryNumber
     * @return
     */
    protected String getPlanStatement(int queryNumber) {
	    return "SELECT * " +
	    		"FROM  EXPLAIN_STATEMENT s, explain_object o " +
	    		"where s.queryno = " + queryNumber +
	    		" and s.EXPLAIN_REQUESTER = o.EXPLAIN_REQUESTER " +
	    		" and s.EXPLAIN_TIME = o.EXPLAIN_TIME " +
	    		" and s.SOURCE_NAME = o.SOURCE_NAME " +
	    		" and s.SOURCE_SCHEMA = o.SOURCE_SCHEMA ";
    }

    /**
     * @return
     */
    private synchronized int getRandomQueryNumber() {
        return this.random.nextInt(Integer.MAX_VALUE-1) + 1;
    }
}
