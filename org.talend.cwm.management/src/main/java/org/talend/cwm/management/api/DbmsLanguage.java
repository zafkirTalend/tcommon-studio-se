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
package org.talend.cwm.management.api;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.talend.dataquality.domain.sql.SqlPredicate;
import org.talend.utils.sugars.TypedReturnCode;

import Zql.ParseException;
import Zql.ZQuery;
import Zql.ZqlParser;

/**
 * @author scorreia
 * 
 * This class handle DBMS specific SQL terms and functions.
 */
public class DbmsLanguage {

    private static Logger log = Logger.getLogger(DbmsLanguage.class);

    /** Functions of the system. */
    private final Map<String, Integer> dbmsFunctions;

    // --- add here other supported systems (always in uppercase)

    private static final String ORACLE = "ORACLE";

    private static final String MYSQL = "MYSQL";

    /**
     * Ansi SQL.
     */
    private static final String SQL = "SQL";

    private static final String DOT = ".";

    /**
     * End of Statement: ";".
     */
    private static final String EOS = ";";

    /**
     * in upper case.
     */
    private final String dbmsName;

    /**
     * DbmsLanguage constructor for generic ANSI SQL (independent of any DBMS).
     */
    public DbmsLanguage() {
        this.dbmsName = SQL;
        this.dbmsFunctions = initDbmsFunctions(dbmsName);
    }

    /**
     * Method "initDbmsFunctions" initialize functions specific to DBMS. This is needed for ZQLParser which does not
     * know all available functions.
     * 
     * @param dbms
     * @return the initialized map of functions with their number of parameters.
     */
    private Map<String, Integer> initDbmsFunctions(String dbms) {
        Map<String, Integer> functions = new HashMap<String, Integer>();

        if (is(SQL)) {
            functions.put("TRIM", 1);
            functions.put("CHAR_LENGTH", 1);
            functions.put("SUM", 1);
            functions.put("MIN", 1);
            functions.put("MAX", 1);
        }

        if (is(MYSQL)) {
            functions.put("TRIM", 1);
            functions.put("CHAR_LENGTH", 1);
            functions.put("SUM", 1);
            functions.put("MIN", 1);
            functions.put("MAX", 1);
        }

        if (is(ORACLE)) {
            functions.put("LENGTH", 1);
            functions.put("SUM", 1);
            functions.put("MIN", 1);
            functions.put("MAX", 1);
        }

        return functions;
    }

    /**
     * DbmsLanguage constructor.
     * 
     * @param dbmsType the name of the DBMS (MySQL, Oracle,...)
     */
    public DbmsLanguage(String dbmsType) {
        this.dbmsName = dbmsType;
        this.dbmsFunctions = initDbmsFunctions(dbmsName);
    }

    /**
     * DbmsLanguage constructor. Use this constructor when functions are specific to a given release of the DBMS.
     * 
     * @param dbmsType the name of the DBMS (MySQL, Oracle,...)
     * @param majorVersion the major version number
     * @param minorVersion the minor version number
     */
    public DbmsLanguage(String dbmsType, int majorVersion, int minorVersion) {
        this.dbmsName = dbmsType;
        // PTODO scorreia handle dbms versions if needed
        this.dbmsFunctions = initDbmsFunctions(dbmsName);
    }

    public String and() {
        return surroundWithSpaces(SqlPredicate.AND.getLiteral());
    }

    public String or() {
        return surroundWithSpaces(SqlPredicate.OR.getLiteral());
    }

    public String between() {
        return surroundWithSpaces(SqlPredicate.BETWEEN.getLiteral());
    }

    /**
     * Method "isNull".
     * 
     * @return IS NULL surrounded with spaces.
     */
    public String isNull() {
        return surroundWithSpaces(SqlPredicate.IS_NULL.getLiteral());
    }

    /**
     * Method "notEqual".
     * 
     * @return "<>" by default or "!=" on some specific DBMS
     */
    public String notEqual() {
        // --- add the DBMS that do not allow <> operator

        // "!=" seem to be more performant on Oracle than "<>". See
        // http://www.freelists.org/archives/oracle-l/09-2006/msg01005.html
        if (is(ORACLE)) {
            return surroundWithSpaces(SqlPredicate.NOT_EQUAL2.getLiteral());
        }

        // ANSI SQL, MySQL,
        return surroundWithSpaces(SqlPredicate.NOT_EQUAL.getLiteral());

    }

    public String unionAll() {
        return union() + all();
    }

    public String all() {
        return surroundWithSpaces(SqlPredicate.ALL.getLiteral());
    }

    public String union() {
        return surroundWithSpaces(SqlPredicate.UNION.getLiteral());
    }

    public String from() {
        return surroundWithSpaces("FROM");
    }

    /**
     * Method "eos".
     * 
     * @return the end of SQL statement token (";")
     */
    public String eos() {
        return EOS;
    }

    // TODO scorreia implement other predicate methods

    public String getDbmsName() {
        return this.dbmsName;
    }

    /**
     * Method "getDefaultLanguage".
     * 
     * @return the default String to use when no dbms is defined.
     */
    public String getDefaultLanguage() {
        return SQL;
    }

    // TODO scorreia move this method in a utility class
    public String toQualifiedName(String catalog, String schema, String name) {
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
     * Method "parseQuery".
     * 
     * @param queryString
     * @return the parsed query. When boolean isOk() is true, this means that the finalizeQuery must called (for example
     * for handling the MySQL LIMIT clause). When false, calling finalizeQuery() is not needed (but it won't hurt to
     * call it).
     * @throws ParseException
     */
    public TypedReturnCode<ZQuery> parseQuery(final String queryString) throws ParseException {
        // LIMIT clause is specific to MySQL, it is not understood by ZQL. remove it and add only at the end
        withoutLimit = removeLimitClause(queryString);
        containsLimitClause = (withoutLimit != null);
        String safeZqlString = containsLimitClause ? withoutLimit[0] : queryString;
        // extractClause = removeExtractFromClause(safeZqlString);
        safeZqlString = closeStatement(safeZqlString);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(safeZqlString.getBytes());
        ZqlParser parser = getZqlParser();
        parser.initParser(byteArrayInputStream);
        if (log.isDebugEnabled()) {
            log.debug("Parsing query: " + safeZqlString);
        }
        ZQuery zQuery = (ZQuery) parser.readStatement();
        TypedReturnCode<ZQuery> trc = new TypedReturnCode<ZQuery>();
        trc.setObject(zQuery);
        trc.setOk(containsLimitClause);
        return trc;
    }

    public String finalizeQuery(ZQuery zQuery) {
        StringBuffer buf = new StringBuffer();
        buf.append(zQuery.toString());
        if (containsLimitClause) {
            buf.append(" " + withoutLimit[1]);
        }
        return buf.toString();
    }

    public ZqlParser getZqlParser() {
        ZqlParser parser = new ZqlParser();
        for (String fnct : this.dbmsFunctions.keySet()) {
            parser.addCustomFunction(fnct, this.dbmsFunctions.get(fnct));
        }
        return parser;
    }

    public String replaceNullsWithString(String colName, String replacement) {
        if (is(MYSQL)) {
            return " IFNULL(" + colName + "," + replacement + ")";
        }

        if (is(ORACLE)) {
            return " NVL(" + colName + "," + replacement + ")";
        }
        // default
        return " CASE WHEN " + colName + isNull() + " THEN " + replacement + " ELSE " + colName + " END ";
    }

    public String isNotBlank(String colName) {
        // default is OK for MySQL, Oracle
        return " TRIM(" + colName + ") " + notEqual() + " '' ";
    }

    public String toUpperCase(String colName) {
        // default is OK for MySQL, Oracle
        return " UPPER(" + colName + ")";
    }

    public String toLowerCase(String colName) {
        // default is OK for MySQL, Oracle
        return " LOWER(" + colName + ")";
    }

    public String extractYear(String colName) {
        return extract("YEAR", colName);
    }

    public String extractQuarter(String colName) {
        return extract("QUARTER", colName);
    }

    public String extractMonth(String colName) {
        return extract("MONTH", colName);
    }

    public String extractWeek(String colName) {
        return extract("WEEK", colName);
    }

    public String extractDay(String colName) {
        return extract("DAY", colName);
    }

    /**
     * Method "countRowInSubquery".
     * 
     * @param subquery
     * @param alias the mandatory alias for the subquery
     * @return the select count(*) from aliased subquery
     */
    public String countRowInSubquery(String subquery, String alias) {
        // ANSI SQL, MySQL
        return " SELECT COUNT(*) FROM (" + subquery + ") AS " + alias;
    }

    public String sumRowInSubquery(String colToSum, String subquery, String alias) {
        // ANSI SQL, MySQL
        return " SELECT SUM(" + colToSum + ") FROM (" + subquery + ") AS " + alias;
    }

    private String extract(String field, String colName) {

        // ANSI SQL, MySQL, Oracle
        return " EXTRACT(" + field + from() + colName + ") ";
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
            res[0] = completedSqlString.substring(0, lastIndexOf) + eos();
            res[1] = completedSqlString.substring(lastIndexOf);
            return res;
        }
        return null;
    }

    private static final String LIMIT_REGEXP = ".*(LIMIT){1}\\p{Blank}+\\p{Digit}+,?\\p{Digit}?.*";

    private static final String EXTRACT_REGEXP = ".*\\(\\p{Blank}(EXTRACT){1}\\p{Blank}+(FROM){1}\\p{BLANK}+\\)?\\p{Blank}?.*";

    private String[] withoutLimit;

    private boolean containsLimitClause;

    /**
     * Method "closeStatement" coses the statement with ';' if needed.
     * 
     * @param safeZqlString
     * @return
     */
    private String closeStatement(String safeZqlString) {
        if (!safeZqlString.trim().endsWith(eos())) {
            safeZqlString += eos();
        }
        return safeZqlString;
    }

    /**
     * Method "is".
     * 
     * @param dbName a DBMS name
     * @return true if this DBMS is given string
     */
    private boolean is(String dbName) {
        return StringUtils.equalsIgnoreCase(this.dbmsName, dbName);
    }

    private String surroundWithSpaces(String toSurround) {
        return " " + toSurround + " ";
    }

}
