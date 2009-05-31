// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.database.conn;

import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.MatchResult;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;
import org.talend.core.database.conn.template.EDatabaseConnTemplate;
import org.talend.core.database.conn.version.EDatabaseVersion4Drivers;
import org.talend.core.model.metadata.builder.connection.DatabaseConnection;

/**
 * cli class global comment. Detailled comment
 */
public class DatabaseConnStrUtil {

    private static String getStringReplace(final String init, final String before, final String after) {
        String s = init;
        if (after != null && init != null && before != null) {
            s = init.replace(before, after);
        }
        return s;
    }

    public static String getURLString(final String dbType, final String dbVersion, final String host, final String login,
            final String password, final String port, final String sid, final String filename, final String datasource) {
        EDatabaseConnTemplate connStr = EDatabaseConnTemplate.indexOfTemplate(dbType);
        EDatabaseVersion4Drivers version = EDatabaseVersion4Drivers.indexOfByVersion(dbVersion);
        if (connStr != null) {
            String s = connStr.getUrlTemplate(version);
            if (s != null) {
                s = getStringReplace(s, EDatabaseConnVar.LOGIN.getVariable(), login);
                s = getStringReplace(s, EDatabaseConnVar.PASSWORD.getVariable(), password);
                s = getStringReplace(s, EDatabaseConnVar.HOST.getVariable(), host);
                s = getStringReplace(s, EDatabaseConnVar.PORT.getVariable(), port);
                s = getStringReplace(s, EDatabaseConnVar.SID.getVariable(), sid);
                s = getStringReplace(s, EDatabaseConnVar.SERVICE_NAME.getVariable(), sid);
                s = getStringReplace(s, EDatabaseConnVar.DATASOURCE.getVariable(), datasource);
                // PTODO OCA : if needed, adapt the file separator to all OS (not only backslashes)
                s = getStringReplace(s, EDatabaseConnVar.FILENAME.getVariable(), filename);
                return s;
            }
        }
        return DatabaseConnConstants.EMPTY;
    }

    public static String getURLString(final String dbType, final String dbVersion, final String host, final String login,
            final String password, final String port, final String sid, final String filename, final String datasource,
            final String dbrootPath) {
        String string = getURLString(dbType, dbVersion, host, login, password, port, sid, filename, datasource);
        if (string.equals(DatabaseConnConstants.EMPTY)) {
            return DatabaseConnConstants.EMPTY;
        }
        EDatabaseConnTemplate connStr = EDatabaseConnTemplate.indexOfTemplate(dbType);
        if (connStr == EDatabaseConnTemplate.HSQLDB_IN_PROGRESS) {
            string = getStringReplace(string, EDatabaseConnVar.DBROOTPATH.getVariable(), dbrootPath);
        } else {
            string = getStringReplace(string, EDatabaseConnVar.DBROOTPATH.getVariable(), sid);
        }
        return string;
    }

    public static String getURLString(final String dbType, final String dbVersion, final String host, final String login,
            final String password, final String port, final String sid, final String filename, final String datasource,
            final String dbrootPath, final String addParams, final String... otherParams) {
        String string = getURLString(dbType, dbVersion, host, login, password, port, sid, filename, datasource, dbrootPath);
        if (string.equals(DatabaseConnConstants.EMPTY)) {
            return DatabaseConnConstants.EMPTY;
        }
        EDatabaseConnTemplate connStr = EDatabaseConnTemplate.indexOfTemplate(dbType);
        if (connStr != null) {
            switch (connStr) {
            case MYSQL:
            case MSSQL:
            case INFORMIX:
            case AS400:
                string = getStringReplace(string, EDatabaseConnVar.PROPERTY.getVariable(), addParams);
            default:
            }
        }
        return string;
    }

    public static String getURLString(DatabaseConnection conn) {
        if (conn != null) {
            return getURLString(conn.getDatabaseType(), conn.getDbVersionString(), conn.getServerName(), conn.getUsername(), conn
                    .getPassword(), conn.getPort(), conn.getSID(), conn.getFileFieldName(), conn.getDatasourceName(), conn
                    .getDBRootPath(), conn.getAdditionalParams());
        }
        return DatabaseConnConstants.EMPTY;
    }

    public static String[] analyseURL(String currentDbType, String dbVersion, String url) {
        String[] s = { currentDbType, DatabaseConnConstants.EMPTY, DatabaseConnConstants.EMPTY, DatabaseConnConstants.EMPTY,
                DatabaseConnConstants.EMPTY, DatabaseConnConstants.EMPTY };
        EDatabaseConnTemplate template = EDatabaseConnTemplate.indexOfTemplate(currentDbType);
        if (template == null || url == null || url == DatabaseConnConstants.EMPTY) {
            return s;
        }
        EDatabaseVersion4Drivers version = EDatabaseVersion4Drivers.indexOfByVersion(dbVersion);
        String regex = template.getUrlPattern(version);

        Perl5Compiler compiler = new Perl5Compiler();
        Perl5Matcher matcher = new Perl5Matcher();
        Pattern pattern = null;
        try {
            pattern = compiler.compile(regex);
            if (matcher.contains(url, pattern)) {
                matcher.matches(url, pattern);
                MatchResult matchResult = matcher.getMatch();
                s[0] = currentDbType;
                if (matchResult != null) {
                    for (int i = 1; i < matchResult.groups(); i++) {
                        s[i] = matchResult.group(i);
                    }
                }
            } else {
                // search if another regex corresponding at the string of connection
                String newDbType = searchGoodRegex(currentDbType, dbVersion, url);
                if (!newDbType.equals(currentDbType)) {
                    currentDbType = newDbType;
                    s = analyseURL(currentDbType, dbVersion, url);
                }
            }

        } catch (MalformedPatternException e) {
            e.printStackTrace();
        }
        return s;
    }

    private static String searchGoodRegex(String currentDbType, String dbVersion, String stringConnection) {
        String startStringConnection;
        String startTemplateString;
        EDatabaseVersion4Drivers version = EDatabaseVersion4Drivers.indexOfByVersion(dbVersion);

        for (EDatabaseConnTemplate template : EDatabaseConnTemplate.values()) {
            String urlTemplate = template.getUrlTemplate(version);

            startTemplateString = urlTemplate.substring(0, urlTemplate.indexOf("<")); //$NON-NLS-1$
            if (startTemplateString.length() <= stringConnection.length()) {
                startStringConnection = stringConnection.substring(0, startTemplateString.length());
                if (stringConnection.contains("(description=(address=(protocol=tcp)")) { //$NON-NLS-1$
                    return EDatabaseConnTemplate.ORACLESN.getDBDisplayName();
                } else if (startTemplateString.equals(startStringConnection)) {
                    return template.getDBDisplayName();
                }
            }
        }
        return currentDbType;
    }

    // test
    public static void main(String[] args) {
        for (EDatabaseConnTemplate temp : EDatabaseConnTemplate.values()) {
            System.out.println();
            System.out.println(temp.getDBTypeName());
            if (temp == EDatabaseConnTemplate.ACCESS) {
                System.out.println(temp.getUrlTemplate(EDatabaseVersion4Drivers.ACCESS_2003));
                System.out.println(temp.getUrlPattern(EDatabaseVersion4Drivers.ACCESS_2003));
            }
            System.out.println(temp.getUrlTemplate(null));
            System.out.println(temp.getUrlPattern(null));
        }
    }
}
