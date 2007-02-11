package com.quantum.adapters;

import java.sql.Connection;
import java.sql.SQLException;

import com.quantum.sql.MultiSQLServer;
import com.quantum.sql.SQLResultSetResults;
import com.quantum.sql.parser.SQL;
import com.quantum.util.connection.Connectable;


/**
 * @author BC Holmes
 */
public class SimpleStatementExplainer implements StatementExplainer {

	private final String prefix;

	SimpleStatementExplainer(String prefix) {
		this.prefix = prefix;
	}
	
	public boolean isExplainable(SQL sqlStatement) {
		return sqlStatement.toString().toUpperCase().trim().startsWith("SELECT ");
	}

	public SQLResultSetResults explain(
			Connectable connectable, Connection connection, SQL sqlStatement) throws SQLException {
		
		String explainStatement = this.prefix + sqlStatement;
		return (SQLResultSetResults) MultiSQLServer.getInstance().execute(
				connectable, connection, explainStatement, Integer.MAX_VALUE);
	}

}
