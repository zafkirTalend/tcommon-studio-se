// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.dq.javasql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.talend.dataquality.indicators.Indicator;
import org.talend.utils.collections.MultiMapHelper;
import org.talend.utils.sql.ConnectionUtils;
import org.talend.utils.sugars.ReturnCode;

/**
 * DOC scorreia class global comment. Detailled comment
 */
public class IndicatorEvaluator {

    private static Logger log = Logger.getLogger(IndicatorEvaluator.class);

    private Connection connection;

    private Map<String, List<Indicator>> columnToIndicators = new HashMap<String, List<Indicator>>();

    private int fetchSize = 0;

    public boolean storeIndicator(String columnName, Indicator indicator) {
        return MultiMapHelper.addUniqueObjectToListMap(columnName, indicator, columnToIndicators);
    }

    public List<Indicator> getIndicators(String columnName) {
        List<Indicator> indics = columnToIndicators.get(columnName);
        return (indics != null) ? indics : new ArrayList<Indicator>();
    }

    /**
     * Method "evaluateIndicators". A connection must be open It does not close the connection. It is to the caller
     * responsibility to close the connection.
     * 
     * @param sqlStatement
     * @return a return code with an error message if any problem has been encountered.
     */
    public ReturnCode evaluateIndicators(String sqlStatement) {
        ReturnCode rc = checkConnection();
        if (!rc.isOk()) {
            return rc;
        }
        try {
            return executeSqlQueryLow(sqlStatement);
        } catch (SQLException e) {
            if (log.isDebugEnabled()) {
                log.debug("Exception while executing SQL query " + sqlStatement, e);
            }
            rc.setReturnCode(e.getMessage(), false);
        }
        return rc;
    }

    /**
     * Method "evaluateIndicators". A connection must be open. This method does not close the connection. It is to the
     * caller responsibility to close the connection.
     * 
     * @param sqlStatement
     * @return a return code with an error message if any problem has been encountered.
     */
    public ReturnCode evaluateIndicators(String sqlStatement, boolean closeConnection) {
        ReturnCode rc = evaluateIndicators(sqlStatement);
        if (!closeConnection) {
            return rc;
        } else {
            if (rc.isOk()) {
                return closeConnection();
            } else { // problem with evaluation
                ReturnCode connRc = closeConnection();
                if (!connRc.isOk()) {
                    // add the message to returned code
                    String message = rc.getMessage();
                    message += " Connection problem:" + connRc.getMessage();
                    rc.setMessage(message);

                }
            }
            return rc;
        }
    }

    /**
     * Method "getAnalyzedColumns".
     * 
     * @return the unmodifiable set of analyzed columns.
     */
    public Set<String> getAnalyzedColumns() {
        return Collections.unmodifiableSet(this.columnToIndicators.keySet());
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public int getFetchSize() {
        return this.fetchSize;
    }

    public void setFetchSize(int fetchSize) {
        this.fetchSize = fetchSize;
    }

    private ReturnCode closeConnection() {

        if (connection != null) {
            return ConnectionUtils.closeConnection(connection);
        }
        return new ReturnCode("Attempting to close a null connection. ", false);
    }

    private ReturnCode checkConnection() {
        if (connection == null) {
            return new ReturnCode("Attempting to open a null connection. Set the connection parameters first.", false);
        }
        return ConnectionUtils.isValid(connection);
    }

    private ReturnCode executeSqlQueryLow(String sqlStatement) throws SQLException {
        ReturnCode ok = new ReturnCode(true);
        // check analyzed columns
        Set<String> columns = getAnalyzedColumns();
        if (columns.isEmpty()) {
            ok.setReturnCode("No column to analyze found? Define the analyzed columns properly, please.", false);
            return ok;
        }

        // create query statement
        Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT);
        statement.setFetchSize(fetchSize);
        statement.execute(sqlStatement);

        // get the results
        ResultSet resultSet = statement.getResultSet();
        if (resultSet == null) {
            String mess = "No result set for this statement: " + sqlStatement;
            log.warn(mess);
            ok.setReturnCode(mess, false);
            return ok;
        }
        while (resultSet.next()) {

            // --- for each column
            for (String col : columns) {
                List<Indicator> indicators = getIndicators(col);

                // --- get content of column
                Object object = resultSet.getObject(col);

                // --- give row to handle to indicators
                for (Indicator indicator : indicators) {
                    indicator.handle(object);
                }
            }

            // TODO scorreia give a full row to indicator (indicator will have to handle its data??

        }
        // --- release resultset
        resultSet.close();
        // --- close
        connection.close();
        return ok;
    }

}
