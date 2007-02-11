package com.quantum.adapters;

import com.quantum.sql.parser.SQL;

/**
 * @author BC Holmes
 */
public class DB2OS390StatementExplainer extends DB2StatementExplainer {

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
	    return "SELECT pt.queryno, pt.planno, pt.creator, pt.accesstype, " +
	            "pt.tname, pt.accesscreator, pt.accessname, " +
	    		"pt.indexonly, ds.stmt_type as statement_type, ds.COST_CATEGORY, ds.reason " +
	    		"FROM  dsn_statemnt_table ds, plan_table pt " +
	    		"where ds.queryno = pt.queryno " +
	    		"and ds.queryno = " + queryNumber;
    }
}
