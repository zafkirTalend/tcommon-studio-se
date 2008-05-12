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

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.talend.cwm.helper.ColumnHelper;
import org.talend.cwm.helper.SwitchHelpers;
import org.talend.cwm.management.api.SoftwareSystemManager;
import org.talend.cwm.relational.TdColumn;
import org.talend.cwm.softwaredeployment.TdDataProvider;
import org.talend.cwm.softwaredeployment.TdSoftwareSystem;
import org.talend.dataquality.analysis.Analysis;
import org.talend.dataquality.analysis.AnalysisResult;
import org.talend.dataquality.domain.Domain;
import org.talend.dataquality.helpers.IndicatorDocumentationHandler;
import org.talend.dataquality.indicators.DateGrain;
import org.talend.dataquality.indicators.Indicator;
import org.talend.dataquality.indicators.IndicatorParameters;
import org.talend.dataquality.indicators.IndicatorsPackage;
import org.talend.dataquality.indicators.TextParameters;
import org.talend.dataquality.indicators.definition.IndicatorDefinition;
import org.talend.sqltools.ZQueryHelper;
import org.talend.utils.sugars.TypedReturnCode;

import orgomg.cwm.foundation.softwaredeployment.DataManager;
import orgomg.cwm.objectmodel.core.CoreFactory;
import orgomg.cwm.objectmodel.core.Expression;
import orgomg.cwm.objectmodel.core.ModelElement;
import orgomg.cwm.objectmodel.core.Package;

import Zql.ParseException;
import Zql.ZExp;
import Zql.ZQuery;
import Zql.ZqlParser;

/**
 * DOC scorreia class global comment. Detailled comment
 */
public class ColumnAnalysisSqlExecutor extends ColumnAnalysisExecutor {

    /**
     * 
     */
    private static final String DOT = ".";

    /**
     * End of SQL Statement.
     */
    private static final String EOS = ";";

    private static Logger log = Logger.getLogger(ColumnAnalysisSqlExecutor.class);

    private static final String DEFAULT_QUOTE_STRING = "";

    private String dbQuote;

    private Analysis cachedAnalysis;

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.dq.analysis.AnalysisExecutor#createSqlStatement(org.talend.dataquality.analysis.Analysis)
     */
    @Override
    protected String createSqlStatement(Analysis analysis) {
        this.cachedAnalysis = analysis;
        AnalysisResult results = analysis.getResults();
        assert results != null;

        try {
            // get data filter
            ZExp dataFilterExpression = null;
            ColumnAnalysisHandler handler = new ColumnAnalysisHandler();
            handler.setAnalysis(analysis);
            String stringDataFilter = handler.getStringDataFilter();
            if (StringUtils.isNotBlank(stringDataFilter)) {
                ZqlParser filterParser = new ZqlParser();
                filterParser.initParser(new ByteArrayInputStream(stringDataFilter.getBytes()));
                dataFilterExpression = filterParser.readExpression();
            }
            // create one sql statement for each indicator
            EList<Indicator> indicators = results.getIndicators();
            for (Indicator indicator : indicators) {
                if (!createSqlQuery(dataFilterExpression, indicator)) {
                    // execute java indicator
                    continue;
                }
            }
        } catch (ParseException e) {
            log.error(e, e);
            return null;
        }

        return "";
    }

    public static String toQualifiedName(String catalog, String schema, String name) {
        StringBuffer qualName = new StringBuffer();
        if (catalog != null && catalog.length() > 0) {
            qualName.append(catalog);
            qualName.append(DOT);
        }
        if (schema != null && schema.length() > 0) {
            qualName.append(schema);
            qualName.append(DOT);
        }

        qualName.append(name);
        if (log.isDebugEnabled()) {
            log.debug(String.format("%s.%s.%s -> %s", catalog, schema, name, qualName));
        }
        return qualName.toString();
    }

    /**
     * DOC scorreia Comment method "createSqlQuery".
     * 
     * @param dataFilterExpression
     * 
     * @param analysis
     * 
     * @param indicator
     * @throws ParseException
     */
    private boolean createSqlQuery(ZExp dataFilterExpression, Indicator indicator) throws ParseException {
        ModelElement analyzedElement = indicator.getAnalyzedElement();
        if (analyzedElement == null) {
            log.error("Analyzed element for indicator "
                    + IndicatorDocumentationHandler.getName(indicator.eClass().getClassifierID()));
            return false;
        }
        TdColumn tdColumn = SwitchHelpers.COLUMN_SWITCH.doSwitch(indicator.getAnalyzedElement());
        if (tdColumn == null) {
            log.error("Analyzed element is not a column for indicator "
                    + IndicatorDocumentationHandler.getName(indicator.eClass().getClassifierID()));
            return false;
        }
        // --- get the schema owner
        String colName = tdColumn.getName();
        if (!belongToSameSchemata(tdColumn)) {
            StringBuffer buf = new StringBuffer();
            for (orgomg.cwm.objectmodel.core.Package schema : schemata.values()) {
                buf.append(schema.getName() + " ");
            }
            log.error("Column " + colName + " does not belong to an existing schema [" + buf.toString().trim() + "]");
            return false;
        }

        // TODO get correct language for current database
        String language = getDatabaseSubtype(); // FIXME scorreia language should depend on the DBMS
        Expression sqlExpression = null; // SqlIndicatorHandler.getSqlCwmExpression(indicator, language);

        // TODO create select statement
        // TODO get indicator's sql columnS (generate the real SQL statement from its definition)
        // FIXME scorreia set indicator definition elsewhere
        // boolean definitionIsSet = DefinitionHandler.getInstance().setDefaultIndicatorDefinition(indicator);
        // if (log.isDebugEnabled()) {
        // log.debug("Definition is set " + definitionIsSet);
        // }
        // if (!definitionIsSet) {
        // log.warn("Uncorrectly defined indicator "
        // + IndicatorDocumentationHandler.getName(indicator.eClass().getClassifierID()));
        // }
        IndicatorDefinition indicatorDefinition = indicator.getIndicatorDefinition();
        if (indicatorDefinition == null) {
            log.error("No indicator definition found for indicator "
                    + IndicatorDocumentationHandler.getName(indicator.eClass().getClassifierID()));
            return false;
        }
        sqlExpression = getSqlExpression(indicatorDefinition, language);
        if (sqlExpression == null) {
            // try with default language
            log.warn("The indicator definition has not been found for the database type " + language + " for the indicator"
                    + indicatorDefinition.getName());
            if (log.isInfoEnabled()) {
                log.info("Trying to compute the indicator with the default language " + DEFAULT_SQL);
            }
            sqlExpression = getSqlExpression(indicatorDefinition, DEFAULT_SQL);
        }

        if (sqlExpression == null || sqlExpression.getBody() == null) {
            log.error("No SQL expression found for indicator "
                    + IndicatorDocumentationHandler.getName(indicator.eClass().getClassifierID()));
            return false;
        }

        // --- get indicator parameters and convert them into sql expression
        List<String> whereExpression = new ArrayList<String>();
        IndicatorParameters parameters = indicator.getParameters();
        if (parameters != null) {
            Domain bins = parameters.getBins();
            // TODO handle bins
            DateGrain dateAggregationType = parameters.getDateAggregationType();
            // TODO handle data grain
            TextParameters textParameter = parameters.getTextParameter();
            colName = quote(colName);
            if (textParameter != null) {
                if (textParameter.isIgnoreCase()) {
                    colName = getUpperCase(language, colName);
                }
                if (!textParameter.isUseBlank()) {
                    whereExpression.add(getWhereIsNotBlank(language, colName));
                }
                if (textParameter.isUseNulls()) {
                    colName = replaceNullsWithEmptyString(language, colName);
                }
            }
        }

        String table = ColumnHelper.getColumnSetFullName(tdColumn);

        // --- normalize table name
        String catalogName = getCatalogName(tdColumn);
        table = toQualifiedName(catalogName, null, table);

        String completedSqlString = null;

        // TODO handle case when indicator is median
        if (indicator.eClass().equals(IndicatorsPackage.eINSTANCE.getMedianIndicator())
                || indicator.eClass().equals(IndicatorsPackage.eINSTANCE.getLowerQuartileIndicator())
                || indicator.eClass().equals(IndicatorsPackage.eINSTANCE.getUpperQuartileIndicator())) {
            completedSqlString = getCompletedString(indicator, sqlExpression, colName, table, dataFilterExpression);
        } else { // default
            completedSqlString = getCompletedSqlString(sqlExpression.getBody(), colName, quote(table)) + EOS;
        }

        if (log.isDebugEnabled()) {
            log.debug("Completed SQL expression for language " + language + ": " + completedSqlString);
        }

        // LIMIT clause is specific to MySQL, it is not understood by ZQL. remove it and add only at the end
        String[] withoutLimit = removeLimitClause(completedSqlString);
        String safeZqlString = (withoutLimit == null) ? completedSqlString : withoutLimit[0];

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(safeZqlString.getBytes());
        ZqlParser parser = new ZqlParser();
        // FIXME we should use a parser factory that creates a parser that depends on the DB
        parser.addCustomFunction("TRIM", 1); // scorreia trim function for MySQL database
        parser.addCustomFunction("CHAR_LENGTH", 1);
        parser.addCustomFunction("SUM", 1);
        parser.addCustomFunction("MIN", 1);
        parser.addCustomFunction("MAX", 1);

        parser.initParser(byteArrayInputStream);

        ZQuery query = (ZQuery) parser.readStatement();
        if (dataFilterExpression != null) {
            query.addWhere(dataFilterExpression);
        }

        Vector<ZExp> whereVector = ZQueryHelper.createWhereVector(whereExpression.toArray(new String[whereExpression.size()]));
        for (ZExp exp : whereVector) {
            query.addWhere(exp);
        }

        // set the instantiated sql expression into the indicator.
        String finalQuery = query.toString();
        if (withoutLimit != null) { // insert MySQL Limit clause at the end
            finalQuery = finalQuery + " " + withoutLimit[1];
        }

        // --- handle case of unique and duplicate count
        if (indicator.eClass().equals(IndicatorsPackage.eINSTANCE.getUniqueCountIndicator())
                || indicator.eClass().equals(IndicatorsPackage.eINSTANCE.getDuplicateCountIndicator())) {

            // for mysql TODO scorreia see how it should be handled in other DB
            finalQuery = "SELECT COUNT(*) FROM (" + finalQuery + ") myquery";
        }

        Expression instantiateSqlExpression = instantiateSqlExpression(language, finalQuery);
        indicator.setInstantiatedExpression(instantiateSqlExpression);
        return true;
    }

    /**
     * DOC scorreia Comment method "getSqlExpression".
     * 
     * @param indicatorDefinition
     * @param language
     * @param sqlExpression
     * @return
     */
    private Expression getSqlExpression(IndicatorDefinition indicatorDefinition, String language) {
        EList<Expression> sqlGenericExpression = indicatorDefinition.getSqlGenericExpression();
        for (Expression sqlGenExpr : sqlGenericExpression) {
            if (language.compareTo(sqlGenExpr.getLanguage()) == 0) {
                return sqlGenExpr; // language found
            }
        }
        return null;
    }

    /**
     * DOC scorreia Comment method "getCompletedString".
     * 
     * @param indicator
     * @param sqlExpression
     * @param colName
     * @param table
     * @param dataFilterExpression
     */
    private String getCompletedString(Indicator indicator, Expression sqlExpression, String colName, String table,
            ZExp dataFilterExpression) {
        // first, count nb lines
        String catalog = getCatalogName(indicator.getAnalyzedElement());
        long count = getCount(cachedAnalysis, colName, quote(table), catalog, dataFilterExpression);
        if (count == -1) {
            log.error("Cannot count number of lines in table " + table);
            return null;
        }

        Long midleCount = getLimitFirstArg(indicator, count);
        Integer nbRow = getNbReturnedRows(indicator, count);
        return MessageFormat.format(sqlExpression.getBody(), quote(colName), quote(table), String.valueOf(midleCount), String
                .valueOf(nbRow))
                + EOS;
    }

    /**
     * DOC scorreia Comment method "getNbReturnedRows".
     * 
     * @param indicator
     * @param count
     * @return
     */
    private Integer getNbReturnedRows(Indicator indicator, long count) {
        if (indicator.eClass().equals(IndicatorsPackage.eINSTANCE.getMedianIndicator())) {
            boolean isEven = count % 2 == 0;
            return (isEven) ? 2 : 1;
        }
        return 1;
    }

    /**
     * DOC scorreia Comment method "getLimitFirstArg".
     * 
     * @param indicator
     * @param count
     * @return
     */
    private Long getLimitFirstArg(Indicator indicator, long count) {
        if (indicator.eClass().equals(IndicatorsPackage.eINSTANCE.getMedianIndicator())) {
            boolean isEven = count % 2 == 0;
            return isEven ? count / 2 - 1 : (count - 1) / 2;
        } else if (indicator.eClass().equals(IndicatorsPackage.eINSTANCE.getLowerQuartileIndicator())) {
            return count / 4 - 1;
        } else if (indicator.eClass().equals(IndicatorsPackage.eINSTANCE.getUpperQuartileIndicator())) {
            return (3 * count) / 4 - 1;
        }
        return null;
    }

    private Long getCount(Analysis analysis, String colName, String table, String catalog, ZExp dataFilterExpression) {
        try {
            return getCountLow(analysis, colName, table, catalog, dataFilterExpression);
        } catch (SQLException e) {
            log.error(e, e);
            return -1L;
        }
    }

    /**
     * DOC scorreia Comment method "getCount".
     * 
     * @param cachedAnalysis2
     * @param colName
     * @param quote
     * @param dataFilterExpression
     * @param catalogName
     * @return
     * @throws SQLException
     */
    private Long getCountLow(Analysis analysis, String colName, String table, String catalogName, ZExp dataFilterExpression)
            throws SQLException {
        TypedReturnCode<Connection> trc = this.getConnection(analysis);
        if (!trc.isOk()) {
            log.error("Cannot execute Analysis " + analysis.getName() + ". Error: " + trc.getMessage());
            return -1L;
        }
        Connection connection = trc.getObject();
        String whereExp = (dataFilterExpression == null || dataFilterExpression.toString().trim().length() == 0) ? "" : " where "
                + dataFilterExpression.toString();
        String queryStmt = "SELECT COUNT(" + colName + ") from " + table + whereExp + EOS;

        List<Object[]> myResultSet = executeQuery(catalogName, connection, queryStmt);

        if (myResultSet.isEmpty() || myResultSet.size() > 1) {
            log.error("Too many result obtained for a simple count: " + myResultSet);
            return -1L;
        }
        return (Long) myResultSet.get(0)[0];
    }

    /**
     * DOC scorreia Comment method "getLanguage".
     * 
     * @return
     */
    private String getDatabaseSubtype() {
        String language = DEFAULT_SQL; // FIXME use constant for a default language

        DataManager connection = this.cachedAnalysis.getContext().getConnection();
        if (connection == null) {
            return language;
        }
        TdDataProvider dataprovider = SwitchHelpers.TDDATAPROVIDER_SWITCH.doSwitch(connection);
        if (dataprovider == null) {
            return language;
        }

        TdSoftwareSystem softwareSystem = SoftwareSystemManager.getInstance().getSoftwareSystem(dataprovider);
        if (softwareSystem == null) {
            return language;
        }
        return softwareSystem.getSubtype();
    }

    /**
     * DOC scorreia Comment method "removeLimitClause".
     * 
     * @param completedSqlString
     * @return
     */
    private String[] removeLimitClause(String completedSqlString) {
        if (completedSqlString == null) {
            return null;
        }
        String upperCased = completedSqlString.toUpperCase();
        if (upperCased.matches(LIMIT_REGEXP)) {
            String[] res = new String[2];
            int lastIndexOf = upperCased.lastIndexOf("LIMIT");
            res[0] = completedSqlString.substring(0, lastIndexOf) + EOS;
            res[1] = completedSqlString.substring(lastIndexOf);
            return res;
        }
        return null;
    }

    private static final String LIMIT_REGEXP = ".*(LIMIT){1}\\p{Blank}+\\p{Digit}+,?\\p{Digit}?.*";

    private static final String DEFAULT_SQL = "SQL";

    private Expression instantiateSqlExpression(String language, String body) {
        Expression expression = CoreFactory.eINSTANCE.createExpression();
        expression.setLanguage(language);
        expression.setBody(body);
        return expression;
    }

    /**
     * Method "getCompletedSqlString".
     * 
     * @param sqlGenericString a string with 2 parameters {0} and {1}
     * @param column the string that replaces the {0} parameter
     * @param table the string that replaces the {1} parameter
     * @return the string with the given parameters
     */
    private String getCompletedSqlString(String sqlGenericString, String column, String table) {
        Object[] arguments = { column, table };
        String toFormat = surroundSingleQuotes(sqlGenericString);

        return MessageFormat.format(toFormat, arguments);
    }

    /**
     * Method "surroundSingleQuotes".
     * 
     * see http://java.sun.com/j2se/1.4.2/docs/api/java/text/MessageFormat.html
     * 
     * @param sqlGenericString
     * @return
     */
    private String surroundSingleQuotes(String sqlGenericString) {
        return sqlGenericString.replaceAll("'", "''");
    }

    /**
     * DOC scorreia Comment method "replaceNullsWithEmptyString".
     * 
     * @param language
     * @param colName
     * @return
     */
    private String replaceNullsWithEmptyString(String language, String colName) {
        // TODO implement cases for different languages
        return "IFNULL(" + colName + ",'')";
    }

    /**
     * DOC scorreia Comment method "getWhereIsNotBlank".
     * 
     * @param language
     * @param colName
     * @return
     */
    private String getWhereIsNotBlank(String language, String colName) {
        // TODO implement cases for different languages
        return "TRIM(" + colName + ") <> '' ";
    }

    /**
     * DOC scorreia Comment method "getUpperCase".
     * 
     * @param language
     * @param colName
     * @return
     */
    private String getUpperCase(String language, String colName) {
        // TODO implement cases for different languages
        return "UPPER(" + colName + ")";
    }

    /**
     * Method "quote".
     * 
     * @param input
     * @return the given string between quotes
     */
    private String quote(String input) {
        if (true) { // FIXME scorreia ZQL does not handle well quote strings
            return input;
        }
        return getDbQuoteString(this.cachedAnalysis) + input + getDbQuoteString(this.cachedAnalysis);
    }

    /**
     * Method "getDbQuoteString".
     * 
     * @param analysis
     * @return the database identifier quote string
     */
    private String getDbQuoteString(Analysis analysis) {
        if (dbQuote != null) {
            return dbQuote;
        }
        TypedReturnCode<Connection> trc = this.getConnection(analysis);
        if (!trc.isOk()) {
            log.error("Cannot execute Analysis " + analysis.getName() + ". Error: " + trc.getMessage());
            return DEFAULT_QUOTE_STRING;
        }
        try {
            dbQuote = DEFAULT_QUOTE_STRING;
            dbQuote = trc.getObject().getMetaData().getIdentifierQuoteString();
            trc.getObject().close();
            return dbQuote;
        } catch (SQLException e) {
            log.warn("Could not get identifier quote string from database for analysis " + analysis.getName());
            return DEFAULT_QUOTE_STRING;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.dq.analysis.AnalysisExecutor#runAnalysis(org.talend.dataquality.analysis.Analysis,
     * java.lang.String)
     */
    @Override
    protected boolean runAnalysis(Analysis analysis, String sqlStatement) {
        boolean ok = true;
        TypedReturnCode<Connection> trc = this.getConnection(analysis);
        if (!trc.isOk()) {
            log.error("Cannot execute Analysis " + analysis.getName() + ". Error: " + trc.getMessage());
            return false;
        }

        try {
            Connection connection = trc.getObject();
            // execute the sql statement for each indicator
            EList<Indicator> indicators = analysis.getResults().getIndicators();
            for (Indicator indicator : indicators) {
                // set the connection's catalog
                String catalogName = getCatalogName(indicator.getAnalyzedElement());
                if (catalogName != null) { // check whether null argument can be given
                    connection.setCatalog(quote(catalogName));
                }

                Expression query = indicator.getInstantiatedExpressions(getDatabaseSubtype());
                if (query == null) {
                    // try to get a default sql expression
                    query = indicator.getInstantiatedExpressions(DEFAULT_SQL);
                }
                if (query == null || !executeQuery(indicator, connection, query.getBody())) {
                    ok = false;
                }
            }
            // get the results

            // store the results in each indicator

            connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ok;
    }

    /**
     * DOC scorreia Comment method "getCatalogName".
     * 
     * @param analyzedElement
     * @return
     */
    private String getCatalogName(ModelElement analyzedElement) {
        Package schema = super.schemata.get(analyzedElement);
        if (schema == null) {
            log.error("No schema found for column " + analyzedElement.getName());
            return null;
        }
        // else
        return schema.getName();
        // if (tdColumn == null) {
        // log.error("Analyzed element is not a column: " +analyzedElement.getName());
        // return null;
        // }
        // this.belongToSameSchemata(tdColumn, schemata)
        // return null;
    }

    /**
     * DOC scorreia Comment method "executeQuery".
     * 
     * @param indicator
     * 
     * @param connection
     * 
     * @param queryStmt
     * @return
     * @throws SQLException
     */
    private boolean executeQuery(Indicator indicator, Connection connection, String queryStmt) throws SQLException {
        String cat = getCatalogName(indicator.getAnalyzedElement());
        List<Object[]> myResultSet = executeQuery(cat, connection, queryStmt);

        // give result to indicator so that it handles the results
        return indicator.storeSqlResults(myResultSet);
    }

    /**
     * DOC scorreia Comment method "executeQuery".
     * 
     * @param catalogName (can be null)
     * @param connection
     * @param queryStmt
     * @return
     * @throws SQLException
     */
    private List<Object[]> executeQuery(String catalogName, Connection connection, String queryStmt) throws SQLException {

        if (catalogName != null) { // check whether null argument can be given
            connection.setCatalog(quote(catalogName));
        }
        // create query statement
        // Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY,
        // ResultSet.CLOSE_CURSORS_AT_COMMIT);
        Statement statement = connection.createStatement();
        // statement.setFetchSize(fetchSize);
        if (log.isDebugEnabled()) {
            log.debug("Excuting query: " + queryStmt);
        }
        statement.execute(queryStmt);

        // get the results
        ResultSet resultSet = statement.getResultSet();
        if (resultSet == null) {
            String mess = "No result set for this statement: " + queryStmt;
            log.warn(mess);
            return null;
        }
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        List<Object[]> myResultSet = new ArrayList<Object[]>();
        while (resultSet.next()) {
            Object[] result = new Object[columnCount];
            for (int i = 0; i < columnCount; i++) {
                result[i] = resultSet.getObject(i + 1);
            }
            myResultSet.add(result);
        }
        resultSet.close();

        return myResultSet;
    }

}
