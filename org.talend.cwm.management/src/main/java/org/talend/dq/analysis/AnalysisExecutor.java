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
package org.talend.dq.analysis;

import java.sql.Connection;
import java.util.Date;

import org.apache.log4j.Logger;
import org.talend.cwm.helper.ColumnHelper;
import org.talend.cwm.helper.ColumnSetHelper;
import org.talend.cwm.helper.DataProviderHelper;
import org.talend.cwm.helper.SwitchHelpers;
import org.talend.cwm.management.connection.JavaSqlFactory;
import org.talend.cwm.relational.TdColumn;
import org.talend.cwm.softwaredeployment.TdDataProvider;
import org.talend.cwm.softwaredeployment.TdProviderConnection;
import org.talend.dataquality.analysis.Analysis;
import org.talend.dataquality.analysis.AnalysisResult;
import org.talend.dataquality.analysis.ExecutionInformations;
import org.talend.utils.sugars.ReturnCode;
import org.talend.utils.sugars.TypedReturnCode;
import orgomg.cwm.foundation.softwaredeployment.DataManager;
import orgomg.cwm.objectmodel.core.Package;
import orgomg.cwm.resource.relational.ColumnSet;

/**
 * DOC scorreia class global comment. Detailled comment
 */
public abstract class AnalysisExecutor implements IAnalysisExecutor {

    private static Logger log = Logger.getLogger(AnalysisExecutor.class);

    protected String errorMessage;

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.dq.analysis.IAnalysisExecutor#execute(org.talend.dataquality.analysis.Analysis)
     */
    public ReturnCode execute(final Analysis analysis) {
        // --- preconditions
        if (!check(analysis)) {
            return getReturnCode(false);
        }
        assert analysis != null;

        // --- creation time
        final ExecutionInformations resultMetadata = analysis.getResults().getResultMetadata();
        final long startime = System.currentTimeMillis();
        resultMetadata.setExecutionDate(new Date(startime));

        // --- create SQL statement
        String sql = createSqlStatement(analysis);
        if (sql == null) {
            return getReturnCode(false);
        }
        if (log.isDebugEnabled()) {
            log.debug("Generated SQL: " + sql);
        }

        // --- run analysis
        boolean ok = runAnalysis(analysis, sql);

        // --- compute execution duration
        long endtime = System.currentTimeMillis();
        resultMetadata.setExecutionDuration(endtime - startime);
        return getReturnCode(ok);
    }

    /**
     * Method "createSqlStatement".
     * 
     * @param analysis the analysis from which the SQL will be generated
     * @return the generated SQL statement or null if problem
     */
    protected abstract String createSqlStatement(Analysis analysis);

    /**
     * Method "getReturnCode".
     * 
     * @param ok
     * @return a return code with the last error message
     */
    protected ReturnCode getReturnCode(boolean ok) {
        return ok ? new ReturnCode() : new ReturnCode(this.errorMessage, false);
    }

    /**
     * Method "check" checks that the analysis can be run.
     * 
     * @param analysis the analysis to prepare
     * @return true if ok.
     */
    protected boolean check(Analysis analysis) {
        AnalysisResult results = analysis.getResults();
        if (results == null) {
            this.errorMessage = "Analysis not prepared correctly. No Result container.";
            return false;
        }
        return true;
    }

    /**
     * Method "runAnalysis".
     * 
     * @param analysis the analysis to be run
     * @param sqlStatement the sql statement to execute on Database
     * @return true if ok
     */
    protected abstract boolean runAnalysis(Analysis analysis, String sqlStatement);

    // {
    // IndicatorEvaluator eval = new IndicatorEvaluator();
    // // --- add indicators
    // EList<Indicator> indicators = analysis.getResults().getIndicators();
    // for (Indicator indicator : indicators) {
    // TdColumn tdColumn = SwitchHelpers.COLUMN_SWITCH.doSwitch(indicator.getAnalyzedElement());
    // if (tdColumn == null) {
    // continue;
    // }
    // String columnName = getColumnName(tdColumn);
    // eval.storeIndicator(columnName, indicator);
    // }
    //
    // // open a connection
    // TypedReturnCode<Connection> connection = getConnection(analysis);
    // if (!connection.isOk()) {
    // log.error(connection.getMessage());
    // this.errorMessage = connection.getMessage();
    // return false;
    // }
    //
    // // set it into the evaluator
    // eval.setConnection(connection.getObject());
    // // when to close connection
    // boolean closeAtTheEnd = true;
    // ReturnCode rc = eval.evaluateIndicators(sqlStatement, closeAtTheEnd);
    // if (!rc.isOk()) {
    // log.warn(rc.getMessage());
    // this.errorMessage = rc.getMessage();
    // }
    // return rc.isOk();
    // }

    /**
     * DOC scorreia Comment method "getConnection".
     * 
     * @param analysis
     * @param schema
     * @return
     */
    protected TypedReturnCode<Connection> getConnection(Analysis analysis, Package schema) {
        TypedReturnCode<Connection> rc = new TypedReturnCode<Connection>();

        DataManager datamanager = analysis.getContext().getConnection();
        TdDataProvider dataprovider = SwitchHelpers.TDDATAPROVIDER_SWITCH.doSwitch(datamanager);
        if (dataprovider == null) {
            return null;
        }
        TypedReturnCode<TdProviderConnection> providerConnection = DataProviderHelper
                .getTdProviderConnection(dataprovider);
        if (!providerConnection.isOk()) {
            rc.setReturnCode(providerConnection.getMessage(), false);
            return rc;
        }
        // else ok

        TypedReturnCode<Connection> connection = JavaSqlFactory
                .createConnection(providerConnection.getObject(), schema);
        if (!connection.isOk()) {
            rc.setReturnCode(connection.getMessage(), false);
            return rc;
        }
        // else ok
        rc.setObject(connection.getObject());
        return rc;

    }

    /**
     * DOC scorreia Comment method "createSchemaProviderConnection".
     * 
     * @param object
     * @return
     */
    private TdProviderConnection createSchemaProviderConnection(TdProviderConnection object) {
        String connectionString = object.getConnectionString();
        if (connectionString.matches("jdbc:mysql://\\p{Alnum}*\\:\\p{Digit}*.*")) {
            if (!connectionString.matches("jdbc:mysql://\\p{Alnum}*\\:\\p{Digit}*/(\\p{Alnum}){1,}")) {
                if (log.isDebugEnabled()) {
                    log.debug("INVALID Mysql connection string: " + connectionString);
                }
            } else {
                if (log.isDebugEnabled()) {
                    log.debug("Valid Mysql connection string: " + connectionString);
                }

            }
        }
        if (log.isDebugEnabled()) {
            log.debug("Valid Mysql connection string: " + connectionString);
        }
        return object;
    }

    /**
     * DOC scorreia Comment method "getColumnNames".
     * 
     * @param columnSet
     * @return
     */
    private String[] getColumnNames(ColumnSet columnSet) {
        return ColumnHelper.getColumnFullNames(columnSet);
    }

    /**
     * DOC scorreia Comment method "getColumnName".
     * 
     * @param tdColumn
     * @return
     */
    private String getColumnName(TdColumn tdColumn) {
        return ColumnHelper.getFullName(tdColumn);
    }

    private String getTableName(TdColumn tdColumn) {
        return ColumnHelper.getColumnSetFullName(tdColumn);
    }

    /**
     * DOC scorreia Comment method "getTableNames".
     * 
     * @param columnSet
     * @return
     */
    private String[] getTableNames(ColumnSet columnSet) {
        return ColumnSetHelper.getColumnSetNames(columnSet);
    }
}
