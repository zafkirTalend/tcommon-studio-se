package com.quantum.adapters;

import java.sql.Connection;
import java.sql.SQLException;

import com.quantum.sql.SQLResultSetResults;
import com.quantum.sql.parser.SQL;
import com.quantum.util.connection.Connectable;

/**
 * The purpose of this interface is to provide a mechanism to explain statements.  
 * Unfortunately, because EXPLAIN is fairly database-specific, we need to deal with
 * explains in some DB-specific ways.
 * 
 * @author BC Holmes
 */
public interface StatementExplainer {

	/**
	 * Some database, like MySQL, only support explain on SELECT statements.  Others
	 * support any statements.
	 * 
	 * @param sqlStatement
	 * @return
	 */
	public boolean isExplainable(SQL sqlStatement);
	
	public SQLResultSetResults explain(
			Connectable connectable, Connection connection, SQL sqlStatement) 
			throws SQLException;
}
