// ============================================================================
//
// Copyright (C) 2006-2011 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.model.metadata.builder.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import metadata.managment.i18n.Messages;
import net.sourceforge.jtds.jdbc.ConnectionJDBC2;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.preference.IPreferenceStore;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.commons.utils.database.DB2ForZosDataBaseMetadata;
import org.talend.commons.utils.database.SASDataBaseMetadata;
import org.talend.commons.utils.database.TeradataDataBaseMetadata;
import org.talend.commons.utils.platform.PluginChecker;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.ILibraryManagerService;
import org.talend.core.IManagementService;
import org.talend.core.database.EDatabase4DriverClassName;
import org.talend.core.database.EDatabaseTypeName;
import org.talend.core.database.conn.template.EDatabaseConnTemplate;
import org.talend.core.database.conn.version.EDatabaseVersion4Drivers;
import org.talend.core.database.utils.ManagementTextUtils;
import org.talend.core.model.general.Project;
import org.talend.core.model.metadata.IMetadataConnection;
import org.talend.core.model.metadata.MetadataConnection;
import org.talend.core.model.metadata.builder.ConvertionHelper;
import org.talend.core.model.metadata.builder.connection.DatabaseConnection;
import org.talend.core.model.metadata.builder.connection.MetadataColumn;
import org.talend.core.model.metadata.builder.util.MetadataConnectionUtils;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.prefs.ITalendCorePrefConstants;
import org.talend.core.repository.model.ResourceModelUtils;
import org.talend.core.runtime.CoreRuntimePlugin;
import org.talend.core.utils.TalendQuoteUtils;
import org.talend.repository.ProjectManager;
import org.talend.utils.sugars.TypedReturnCode;
import orgomg.cwm.objectmodel.core.Expression;

/**
 * DOC cantoine. Extract Meta Data Table. Contains all the Table and Metadata about a DB Connection. <br/>
 * 
 * $Id: ExtractMetaDataUtils.java 38999 2010-03-24 03:33:58Z cli $
 * 
 */
public class ExtractMetaDataUtils {

    private static Logger log = Logger.getLogger(ExtractMetaDataUtils.class);

    private static final char SPLIT_CHAR = ',';

    public static Connection conn;

    public static String schema;

    public static boolean isReconnect = true;

    // public static IMetadataConnection metadataCon; // for teradata to use

    // hywang add for bug 7038
    private static List<String> functionlist = new ArrayList<String>();

    /**
     * DOC cantoine. Method to return DatabaseMetaData of a DB connection.
     * 
     * @param Connection conn
     * @param Connection dbType
     * @return DatabaseMetaData
     * 
     * MOD by zshen this method don't care about sqlMode
     */
    public static DatabaseMetaData getDatabaseMetaData(Connection conn, String dbType) {

        DatabaseMetaData dbMetaData = null;
        try {

            if ("net.sourceforge.jtds.jdbc.ConnectionJDBC3".equals(conn.getClass().getName())) {
                dbMetaData = createJtdsDatabaseMetaData(conn);
            } else if (dbType.equals(EDatabaseTypeName.IBMDB2ZOS.getXmlName())) {
                dbMetaData = createFakeDatabaseMetaData(conn);
            } else if (dbType.equals(EDatabaseTypeName.SAS.getXmlName())) {
                dbMetaData = createSASFakeDatabaseMetaData(conn);
            } else {
                dbMetaData = conn.getMetaData();
            }
        } catch (SQLException e) {
            log.error(e.toString());
            throw new RuntimeException(e);
        } catch (Exception e) {
            log.error(e.toString());
            throw new RuntimeException(e);
        }
        return dbMetaData;
    }

    private static DatabaseMetaData createJtdsDatabaseMetaData(Connection jtdsConn) {
        if (jtdsConn instanceof ConnectionJDBC2) {
            return new JtdsMetadataAdapter((ConnectionJDBC2) jtdsConn);
        } else {
            try {
                return jtdsConn.getMetaData();
            } catch (SQLException e) {
                log.error(e);
                return null;
            }
        }
    }

    public static DatabaseMetaData getDatabaseMetaData(Connection conn, String dbType, boolean isSqlMode, String database) {

        DatabaseMetaData dbMetaData = null;
        try {

            if ("net.sourceforge.jtds.jdbc.ConnectionJDBC3".equals(conn.getClass().getName())) {
                dbMetaData = createJtdsDatabaseMetaData(conn);
            } else if (dbType.equals(EDatabaseTypeName.IBMDB2ZOS.getXmlName())) {
                dbMetaData = createFakeDatabaseMetaData(conn);
            } else if (dbType.equals(EDatabaseTypeName.TERADATA.getXmlName()) && isSqlMode) {
                dbMetaData = createTeradataFakeDatabaseMetaData(conn);
                // add by wzhang for bug 8106. set database name for teradata.
                TeradataDataBaseMetadata teraDbmeta = (TeradataDataBaseMetadata) dbMetaData;
                teraDbmeta.setDatabaseName(database);
            } else if (dbType.equals(EDatabaseTypeName.SAS.getXmlName())) {
                dbMetaData = createSASFakeDatabaseMetaData(conn);
            } else {
                dbMetaData = conn.getMetaData();
            }
        } catch (SQLException e) {
            log.error(e.toString());
            throw new RuntimeException(e);
        } catch (Exception e) {
            log.error(e.toString());
            throw new RuntimeException(e);
        }
        return dbMetaData;
    }

    /**
     * DOC cantoine. Method to return DatabaseMetaData of a DB connection.
     * 
     * @param Connection conn
     * @param DatabaseConnection dbConn
     * @return DatabaseMetaData
     * 
     * MOD by zshen this method don't care about sqlMode
     */
    public static DatabaseMetaData getDatabaseMetaData(Connection conn, DatabaseConnection dbConn) {
        boolean isSqlMode = dbConn.isSQLMode();
        return getDatabaseMetaData(conn, dbConn, isSqlMode);
    }

    /**
     * DOC cantoine. Method to return DatabaseMetaData of a DB connection.
     * 
     * @param Connection conn
     * @param isSqlMode
     * @param DatabaseConnection dbConn
     * @return DatabaseMetaData
     * 
     */
    public static DatabaseMetaData getDatabaseMetaData(Connection conn, DatabaseConnection dbConn, boolean isSqlMode) {

        DatabaseMetaData dbMetaData = null;
        try {
            String dbType = dbConn.getDatabaseType();

            if ("net.sourceforge.jtds.jdbc.ConnectionJDBC3".equals(conn.getClass().getName())) {
                dbMetaData = createJtdsDatabaseMetaData(conn);
            } else if (dbType.equals(EDatabaseTypeName.IBMDB2ZOS.getXmlName())) {
                dbMetaData = createFakeDatabaseMetaData(conn);
            } else if (dbType.equals(EDatabaseTypeName.TERADATA.getXmlName()) && isSqlMode) {
                String database = dbConn.getSID();
                dbMetaData = createTeradataFakeDatabaseMetaData(conn);
                // add by wzhang for bug 8106. set database name for teradata.
                TeradataDataBaseMetadata teraDbmeta = (TeradataDataBaseMetadata) dbMetaData;
                teraDbmeta.setDatabaseName(database);
            } else if (dbType.equals(EDatabaseTypeName.SAS.getXmlName())) {
                dbMetaData = createSASFakeDatabaseMetaData(conn);
            } else {
                dbMetaData = conn.getMetaData();
            }
        } catch (SQLException e) {
            log.error(e.toString());
            throw new RuntimeException(e);
        } catch (Exception e) {
            log.error(e.toString());
            throw new RuntimeException(e);
        }
        return dbMetaData;
    }

    public static DatabaseMetaData getConnectionMetadata(java.sql.Connection conn) throws SQLException {
        DatabaseMetaData dbMetaData = conn.getMetaData();
        // MOD xqliu 2009-11-17 bug 7888
        if (dbMetaData != null && dbMetaData.getDatabaseProductName() != null) {
            if ("net.sourceforge.jtds.jdbc.ConnectionJDBC3".equals(conn.getClass().getName())) {
                dbMetaData = createJtdsDatabaseMetaData(conn);
            } else if (dbMetaData.getDatabaseProductName().equals(EDatabaseTypeName.IBMDB2ZOS.getXmlName())) {
                getDatabaseMetaData(conn, EDatabaseTypeName.IBMDB2ZOS.getXmlName());
            } else if (dbMetaData.getDatabaseProductName().equals(EDatabaseTypeName.TERADATA.getXmlName())) {
                getDatabaseMetaData(conn, EDatabaseTypeName.TERADATA.getXmlName());
            } else if (dbMetaData.getDatabaseProductName().equals(EDatabaseTypeName.SAS.getXmlName())) {
                getDatabaseMetaData(conn, EDatabaseTypeName.SAS.getXmlName());
            }
        }
        // if (dbMetaData != null && dbMetaData.getDatabaseProductName() != null) {
        // if (dbMetaData.getDatabaseProductName().equals(EDatabaseTypeName.IBMDB2ZOS.getProduct())) {
        // dbMetaData = createFakeDatabaseMetaData(conn);
        // log.info("IBM DB2 for z/OS");
        // } else if (dbMetaData.getDatabaseProductName().equals(EDatabaseTypeName.TERADATA.getProduct()) && metadataCon
        // != null
        // && metadataCon.isSqlMode()) {
        // dbMetaData = createTeradataFakeDatabaseMetaData(conn);
        // TeradataDataBaseMetadata teraDbmeta = (TeradataDataBaseMetadata) dbMetaData;
        // teraDbmeta.setDatabaseName(ExtractMetaDataUtils.metadataCon.getDatabase());
        // } else if (dbMetaData.getDatabaseProductName().equals(EDatabaseTypeName.SAS.getProduct())) {
        // dbMetaData = createSASFakeDatabaseMetaData(conn);
        // }
        // }
        // // ~
        return dbMetaData;
    }

    public static boolean needFakeDatabaseMetaData(String dbType, boolean isSqlMode) {
        if (dbType.equals(EDatabaseTypeName.IBMDB2ZOS.getXmlName())) {
            return true;
        } else if (dbType.equals(EDatabaseTypeName.TERADATA.getXmlName()) && isSqlMode) {

            return true;
        } else if (dbType.equals(EDatabaseTypeName.SAS.getXmlName())) {
            return true;
        }
        return false;
    }

    /**
     * only for db2 on z/os right now.
     * 
     * @param conn2
     * @return
     */
    private static DatabaseMetaData createFakeDatabaseMetaData(Connection conn) {
        DB2ForZosDataBaseMetadata dmd = new DB2ForZosDataBaseMetadata(conn);
        return dmd;
    }

    private static DatabaseMetaData createTeradataFakeDatabaseMetaData(Connection conn) {
        TeradataDataBaseMetadata tmd = new TeradataDataBaseMetadata(conn);
        return tmd;
    }

    private static DatabaseMetaData createSASFakeDatabaseMetaData(Connection conn) {
        SASDataBaseMetadata tmd = new SASDataBaseMetadata(conn);
        return tmd;
    }

    /**
     * DOC cantoine. Method to return MetaDataInfo on Column DataBaseConnection.
     * 
     * @param ResultSet columns
     * @param String infoType
     * @return String : the result of column's information MetaData
     */
    public static String getStringMetaDataInfo(ResultSet columns, String infoType, DatabaseMetaData dbMetaData) {
        String metaDataInfo = null;
        try {
            metaDataInfo = columns.getString(infoType);
            // hywang modified it for bug 7038
            List<String> funcions = getAllDBFuctions(dbMetaData);
            if (metaDataInfo != null) {
                if ((dbMetaData != null && funcions != null && !funcions.contains(metaDataInfo))) {
                    metaDataInfo = ManagementTextUtils.QUOTATION_MARK + metaDataInfo + ManagementTextUtils.QUOTATION_MARK;
                }
            }
            // Replace ALL ' in the retrieveSchema, cause PB for Default Value.
            // metaDataInfo = metaDataInfo.replaceAll("'", ""); //$NON-NLS-1$
            // //$NON-NLS-2$
        } catch (SQLException e) {
            // log.error(e.toString());
            return metaDataInfo;
        } catch (Exception e) {
            // log.error(e.toString());
            return metaDataInfo;
        }
        return metaDataInfo;
    }

    /**
     * 
     * DOC xye Comment method "getStringMetaDataInfo".
     * 
     * @param columns
     * @param infoType
     * @return
     */
    public static String getStringMetaDataInfo(ResultSet columns, int infoType) {
        String metaDataInfo = null;
        try {
            metaDataInfo = columns.getString(infoType);
            // Replace ALL ' in the retrieveSchema, cause PB for Default Value.
            // metaDataInfo = metaDataInfo.replaceAll("'", ""); //$NON-NLS-1$
            // //$NON-NLS-2$
        } catch (SQLException e) {
            // log.error(e.toString());
            return metaDataInfo;
        } catch (Exception e) {
            // log.error(e.toString());
            return metaDataInfo;
        }
        return metaDataInfo;
    }

    /**
     * DOC cantoine. Method to return MetaDataInfo on Column DataBaseConnection.
     * 
     * @param ResultSet columns
     * @param String infoType
     * @return int : the result of column's information MetaData
     */
    public static Integer getIntMetaDataInfo(ResultSet columns, String infoType) {
        Integer metaDataInfo = new Integer(0);
        try {
            metaDataInfo = new Integer(columns.getInt(infoType));
        } catch (SQLException e) {
            // log.error(e.toString());
            return metaDataInfo;
        } catch (Exception e) {
            // log.error(e.toString());
            return metaDataInfo;
        }
        return metaDataInfo;
    }

    public static Integer getOracleIntMatadataInfo(ResultSet columns, String infoType) {
        Integer metaDataInfo = new Integer(0);
        try {
            metaDataInfo = new Integer(columns.getInt(infoType));
            if (columns.getString("CHAR_USED").equals("C")) {
                metaDataInfo = metaDataInfo / 2;
            }
        } catch (SQLException e) {
            // log.error(e.toString());
            return metaDataInfo;
        } catch (Exception e) {
            // log.error(e.toString());
            return metaDataInfo;
        }
        return metaDataInfo;
    }

    public static Integer getMysqlIntMetaDataInfo(ResultSetMetaData rMetadata, int columnIndex) {
        Integer metaDataInfo = new Integer(0);
        try {
            metaDataInfo = rMetadata.getPrecision(columnIndex);
        } catch (SQLException e) {
            // log.error(e.toString());
            return metaDataInfo;
        } catch (Exception e) {
            // log.error(e.toString());
            return metaDataInfo;
        }
        return metaDataInfo;
    }

    /**
     * Return boolean value, used only to check if one field is nullable or not. If used to something else than nullable
     * check, take care of the default values returned. Actually returns null if there is any error during the check.
     * 
     * @param ResultSet columns
     * @param String infoType
     * @return boolean : the result of column's information MetaData
     */
    public static boolean getBooleanMetaDataInfo(ResultSet columns, String infoType) {
        boolean metaDataInfo = false;
        try {
            String result = columns.getString(infoType);
            if (result != null && result.equals("YES")) { //$NON-NLS-1$
                metaDataInfo = true;
            }
        } catch (SQLException e) {
            // log.error(e.toString());
            return true;
        } catch (Exception e) {
            // log.error(e.toString());
            return true;
        }
        return metaDataInfo;
    }

    // PTODO cantoine : Be careful : Integrate in properties or preferences of
    // Talend Product
    // OCA : save connectionString and associated regex in the same place.
    /**
     * DOC cantoine. Method to return MetaDataInfo on Column DataBaseConnection.
     * 
     * @param ResultSet columns
     * @param String infoType
     * @return String : the result of column's information MetaData
     */
    public static String getDriverClassByDbType(String dbType) {

        EDatabase4DriverClassName t4d = EDatabase4DriverClassName.indexOfByDbType(dbType);
        if (t4d != null) {
            return t4d.getDriverClass();
        }
        return null;
    }

    // hywang add for bug 7575
    public static String getDbTypeByClassName(String driverClassName) {
        return getDbTypeByClassNameAndDriverJar(driverClassName, null);
    }

    public static String getDbTypeByClassNameAndDriverJar(String driverClassName, String driverJar) {
        List<EDatabase4DriverClassName> t4d = EDatabase4DriverClassName.indexOfByDriverClass(driverClassName);
        if (t4d.size() == 1) {
            return t4d.get(0).getDbTypeName();
        } else if (t4d.size() > 1) {
            // for some dbs use the same driverClassName.
            if (driverJar == null || "".equals(driverJar) || !driverJar.contains(".jar")) {
                return t4d.get(0).getDbTypeName();
            } else if (driverJar.contains("postgresql-8.3-603.jdbc3.jar") || driverJar.contains("postgresql-8.3-603.jdbc4.jar")
                    || driverJar.contains("postgresql-8.3-603.jdbc2.jar")) {//
                return EDatabase4DriverClassName.PSQL.getDbTypeName();
            } else {
                return t4d.get(0).getDbTypeName(); // first default
            }
        }
        return null;
    }

    /**
     * 
     * DOC YeXiaowei Comment method "isValidJarFile".
     * 
     * @param driverJarPath
     * @return
     */
    private static boolean isValidJarFile(final String[] driverJarPath) {
        boolean a = false;
        for (int i = 0; i < driverJarPath.length; i++) {
            if (driverJarPath[i] == null || driverJarPath[i].equals("")) { //$NON-NLS-1$
                return a;
            }
            File jarFile = new File(driverJarPath[i]);
            a = jarFile.exists() && jarFile.isFile();
        }
        return a;

    }

    /**
     * DOC cantoine. Method to connect to DataBase.
     * 
     * @param String driverClass
     * @param String urlString pwdT
     * @param String username
     * @param String pwd
     * @param String schemaBase
     */
    public static List getConnection(String dbType, String url, String username, String pwd, String dataBase, String schemaBase,
            final String driverClassName, final String driverJarPath, String dbVersion, String additionalParams) {
        boolean isColsed = false;
        List conList = new ArrayList();
        try {
            if (conn != null) {
                isColsed = conn.isClosed();
            }
        } catch (Exception e) {
            log.error(e.toString());
        }
        // bug 9162
        List list = new ArrayList();
        DriverShim wapperDriver = null;
        if (isReconnect || conn == null || isColsed) {
            try {
                closeConnection(true); // colse before connection.
                checkDBConnectionTimeout();

                list = connect(dbType, url, username, pwd, driverClassName, driverJarPath, dbVersion, additionalParams);
                if (list != null && list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i) instanceof Connection) {
                            conn = (Connection) list.get(i);
                        }
                        if (list.get(i) instanceof DriverShim) {
                            wapperDriver = (DriverShim) list.get(i);
                        }
                    }
                }
                if (schemaBase != null && !schemaBase.equals("")) { //$NON-NLS-1$
                    final boolean equals = EDatabaseTypeName.getTypeFromDbType(dbType).getProduct()
                            .equals(EDatabaseTypeName.ORACLEFORSID.getProduct());
                    if (!ExtractMetaDataFromDataBase.checkSchemaConnection(schemaBase, conn, equals, dbType)) {
                        schema = null;
                    }
                } else {
                    boolean teradata = EDatabaseTypeName.getTypeFromDbType(dbType).getProduct()
                            .equals(EDatabaseTypeName.TERADATA.getProduct());
                    // add for bug 10644
                    boolean as400 = EDatabaseTypeName.getTypeFromDbType(dbType).getProduct()
                            .equals(EDatabaseTypeName.AS400.getProduct());
                    if (teradata) {
                        schema = dataBase;
                    } else if (as400) {
                        schema = retrieveSchemaPatternForAS400(url);
                    } else if (EDatabaseTypeName.SAS.getProduct()
                            .equals(EDatabaseTypeName.getTypeFromDbType(dbType).getProduct())) {
                        schema = dataBase;
                    } else {
                        schema = null;
                    }
                }
                conList.add(conn);
                if (wapperDriver != null) {
                    conList.add(wapperDriver);
                }
            } catch (SQLException e) {
                log.error(e.toString());
                throw new RuntimeException(e);
            } catch (Exception e) {
                log.error(e.toString());
                throw new RuntimeException(e);
            }
        }
        return conList;
    }

    /**
     * 
     * cli Comment method "retrieveSchemaPatternForAS400".
     * 
     * bug 12179
     */
    public static String retrieveSchemaPatternForAS400(String url) {
        String libsPattern = "libraries\\s*=\\s*"; //$NON-NLS-1$
        Pattern regex = Pattern.compile(libsPattern + "(([\\w.]+?)\\s*,?\\s*)*\\s*;?", //$NON-NLS-1$
                Pattern.CANON_EQ | Pattern.CASE_INSENSITIVE);
        Matcher regexMatcher = regex.matcher(url);
        Set<String> libs = new HashSet<String>();
        while (regexMatcher.find()) {
            String str = regexMatcher.group();
            if (str != null && !"".equals(str.trim())) { //$NON-NLS-1$
                Pattern libP = Pattern.compile(libsPattern + "(.*)"); //$NON-NLS-1$
                Matcher libMatcher = libP.matcher(str.trim());
                if (libMatcher.find()) {
                    String libStr = libMatcher.group(1);
                    if (libStr != null) {
                        libStr = libStr.trim();
                        if (libStr.endsWith(";")) { //$NON-NLS-1$
                            libStr = libStr.substring(0, libStr.length() - 1);
                        }
                        libStr = libStr.trim();
                        if (!libStr.equals("")) { //$NON-NLS-1$
                            String[] multiSchems = getMultiSchems(libStr);
                            if (multiSchems != null) {
                                for (String s : multiSchems) {
                                    if (s != null) {
                                        libs.add(s.trim());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (!libs.isEmpty()) {
            StringBuffer sb = new StringBuffer();
            int index = 0;
            for (String lib : libs) {
                sb.append(lib);
                if (index < libs.size() - 1) {
                    sb.append(SPLIT_CHAR);
                }
                index++;
            }
            return sb.toString();
        } else {
            return null;
        }
    }

    /**
     * 
     * cli Comment method "getMultiSchems".
     * 
     * bug 12179
     */
    public static String[] getMultiSchems(String schemas) {
        if (schemas != null) {
            String[] split = schemas.split(String.valueOf(SPLIT_CHAR));
            return split;
        }
        return null;
    }

    /**
     * DOC cantoine. Method to close connect to DataBase.
     */
    public static void closeConnection() {
        closeConnection(false);
    }

    public static void closeConnection(boolean force) {
        try {
            if (conn != null && !conn.isClosed()) {
                if (isReconnect || force) {
                    conn.close();
                }
            }
        } catch (SQLException e) {
            log.error(e.toString());
            throw new RuntimeException(e);
        } catch (Exception e) {
            log.error(e.toString());
            throw new RuntimeException(e);
        }
    }

    public static void checkAccessDbq(String connString) throws Exception {
        for (String s : connString.split(";")) { //$NON-NLS-1$
            s = s.toLowerCase();
            int pos = s.indexOf("dbq"); //$NON-NLS-1$
            if (pos > -1) {
                s = s.substring(pos + 3).replaceAll("=", "").trim(); //$NON-NLS-1$ //$NON-NLS-2$
                // check the value of dbp
                if (!s.endsWith(".mdb") && !s.endsWith(".accdb")) { //$NON-NLS-1$ //$NON-NLS-2$
                    throw new Exception(Messages.getString("ExtractMetaDataUtils.noData")); //$NON-NLS-1$
                }
                return;
            }
        }
    }

    /**
     * 
     * ggu Comment method "checkDBConnectionTimeout".
     * 
     * there is no effect for oracle.
     */
    public static void checkDBConnectionTimeout() {
        DriverManager.setLoginTimeout(getDBConnectionTimeout());
    }

    private static int getDBConnectionTimeout() {
        IManagementService managementSerivce = CoreRuntimePlugin.getInstance().getManagementService();
        IPreferenceStore preferenceStore = managementSerivce.getDesignerCorePreferenceStore();
        if (preferenceStore != null && preferenceStore.getBoolean(ITalendCorePrefConstants.DB_CONNECTION_TIMEOUT_ACTIVED)) {
            return preferenceStore.getInt(ITalendCorePrefConstants.DB_CONNECTION_TIMEOUT);
        }
        // disable timeout
        return 0;
    }

    /**
     * 
     * ggu Comment method "setQueryStatementTimeout".
     * 
     * @param statement
     */
    public static void setQueryStatementTimeout(Statement statement) {
        if (statement != null) {
            try {
                statement.setQueryTimeout(getDBConnectionTimeout());
            } catch (SQLException e) {
                // nothing, some db doesn't support the timeout
                // no need to throw exception or this one will be throw all the time
            }
        }
    }

    /**
     * DOC xye Comment method "connect".
     * 
     * @param dbType
     * @param url
     * @param username
     * @param pwd
     * @param driverClassName
     * @param driverJarPath
     * @param dbVersionString
     * @return
     * @throws Exception
     */
    public static List connect(String dbType, String url, String username, String pwd, final String driverClassNameArg,
            final String driverJarPathArg, String dbVersion, String additionalParams) throws Exception {
        Connection connection = null;
        DriverShim wapperDriver = null;
        List conList = new ArrayList();

        String driverClassName = driverClassNameArg;
        List<String> jarPathList = new ArrayList<String>();

        if (PluginChecker.isOnlyTopLoaded()) {
            if (StringUtils.isBlank(driverClassName)) {
                driverClassName = ExtractMetaDataUtils.getDriverClassByDbType(dbType);
            }

            IMetadataConnection mconn = new MetadataConnection();
            mconn.setUrl(url);
            mconn.setUsername(username);
            mconn.setPassword(pwd);
            mconn.setDbType(dbType);
            mconn.setDriverClass(driverClassName);
            mconn.setDriverJarPath(driverJarPathArg);
            mconn.setDbVersionString(dbVersion);
            mconn.setAdditionalParams(additionalParams);

            TypedReturnCode<Connection> checkConnection = MetadataConnectionUtils.checkConnection(mconn);
            if (checkConnection.isOk()) {
                conList.add(checkConnection.getObject());
            } else {
                throw new Exception(checkConnection.getMessage());
            }

        } else {
            ILibraryManagerService librairesManagerService = (ILibraryManagerService) GlobalServiceRegister.getDefault()
                    .getService(ILibraryManagerService.class);
            // see feature 4720&4722
            if ((driverJarPathArg == null || driverJarPathArg.equals(""))) { //$NON-NLS-1$
                List<String> driverNames = EDatabaseVersion4Drivers.getDrivers(dbType, dbVersion);
                if (driverNames != null) {
                    for (String jar : driverNames) {
                        if (!new File(getJavaLibPath() + jar).exists()) {
                            librairesManagerService.retrieve(jar, getJavaLibPath(), new NullProgressMonitor());
                        }
                        jarPathList.add(getJavaLibPath() + jar);
                    }
                    driverClassName = ExtractMetaDataUtils.getDriverClassByDbType(dbType);
                }
            } else {
                Set<String> jarsAvailable = librairesManagerService.list(new NullProgressMonitor());
                // add another test with start with / in case of linux OS.
                if (driverJarPathArg.contains("\\") || driverJarPathArg.startsWith("/")) { //$NON-NLS-1$
                    if (driverJarPathArg.contains(";")) {
                        String jars[] = driverJarPathArg.split(";");
                        for (int i = 0; i < jars.length; i++) {
                            Path path = new Path(jars[i]);
                            // fix for 19020
                            if (jarsAvailable.contains(path.lastSegment())) {
                                if (!new File(getJavaLibPath() + path.lastSegment()).exists()) {
                                    librairesManagerService.retrieve(path.lastSegment(), getJavaLibPath(),
                                            new NullProgressMonitor());
                                }
                                jarPathList.add(getJavaLibPath() + path.lastSegment());
                            } else {
                                jarPathList.add(jars[i]);
                            }
                        }
                    } else {
                        Path path = new Path(driverJarPathArg);
                        if (jarsAvailable.contains(path.lastSegment())) {
                            String jarUnderLib = getJavaLibPath() + path.lastSegment();
                            File file = new File(jarUnderLib);
                            if (!file.exists()) {
                                librairesManagerService.retrieve(path.lastSegment(), getJavaLibPath(), new NullProgressMonitor());
                            }
                            jarPathList.add(jarUnderLib);
                        } else {
                            jarPathList.add(driverJarPathArg);
                        }
                    }
                } else {
                    if (driverJarPathArg.contains(";")) {
                        String jars[] = driverJarPathArg.split(";");
                        for (int i = 0; i < jars.length; i++) {
                            if (!new File(getJavaLibPath() + jars[i]).exists()) {
                                librairesManagerService.retrieve(jars[i], getJavaLibPath(), new NullProgressMonitor());
                            }
                            jarPathList.add(getJavaLibPath() + jars[i]);
                        }
                    } else {
                        if (!new File(getJavaLibPath() + driverJarPathArg).exists()) {
                            librairesManagerService.retrieve(driverJarPathArg, getJavaLibPath(), new NullProgressMonitor());
                        }
                        jarPathList.add(getJavaLibPath() + driverJarPathArg);
                    }
                }
            }

            final String[] driverJarPath = jarPathList.toArray(new String[0]);

            /*
             * For general jdbc, driver class is specific by user.
             */
            if (driverClassName == null || driverClassName.equals("")) { //$NON-NLS-1$
                driverClassName = ExtractMetaDataUtils.getDriverClassByDbType(dbType);
                // see bug 4404: Exit TOS when Edit Access Schema in repository
                if (dbType.equals(EDatabaseTypeName.ACCESS.getXmlName())) {
                    // throw exception to prevent getting connection, which may crash
                    ExtractMetaDataUtils.checkAccessDbq(url);
                }
            }
            // bug 9162
            List list = new ArrayList();
            ExtractMetaDataUtils.checkDBConnectionTimeout();
            if (dbType != null && dbType.equalsIgnoreCase(EDatabaseTypeName.GENERAL_JDBC.getXmlName())) {
                JDBCDriverLoader loader = new JDBCDriverLoader();
                list = loader.getConnection(driverJarPath, driverClassName, url, username, pwd, dbType, dbVersion,
                        additionalParams);
                if (list != null && list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i) instanceof Connection) {
                            connection = (Connection) list.get(i);
                        }
                        if (list.get(i) instanceof DriverShim) {
                            wapperDriver = (DriverShim) list.get(i);
                        }
                    }
                }

            } else if (dbType != null
                    && (isValidJarFile(driverJarPath) || dbType.equalsIgnoreCase(EDatabaseTypeName.GODBC.getXmlName()))) {
                // Load jdbc driver class dynamicly
                JDBCDriverLoader loader = new JDBCDriverLoader();
                list = loader.getConnection(driverJarPath, driverClassName, url, username, pwd, dbType, dbVersion,
                        additionalParams);
                if (list != null && list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i) instanceof Connection) {
                            connection = (Connection) list.get(i);
                        }
                        if (list.get(i) instanceof DriverShim) {
                            wapperDriver = (DriverShim) list.get(i);
                        }
                    }
                }
            } else {
                // Don't use DriverManager
                Class<?> klazz = Class.forName(driverClassName);
                Properties info = new Properties();
                info.put("user", username); //$NON-NLS-1$
                info.put("password", pwd); //$NON-NLS-1$

                connection = ((Driver) klazz.newInstance()).connect(url, info);

            }

            // throw a new exception.
            if (connection == null) {
                throw new Exception(Messages.getString("ExtractMetaDataUtils.1")); //$NON-NLS-1$
            }
            conList.add(connection);
            if (wapperDriver != null) {
                conList.add(wapperDriver);
            }
        }

        return conList;
    }

    /**
     * 
     * DOC YeXiaowei Comment method "getJavaLibPath".
     * 
     * @return
     */
    public static String getJavaLibPath() {
        Project project = ProjectManager.getInstance().getCurrentProject();
        IProject physProject;
        String tmpFolder = System.getProperty("user.dir"); //$NON-NLS-1$
        try {
            physProject = ResourceModelUtils.getProject(project);
            tmpFolder = physProject.getFolder("temp").getLocation().toPortableString(); //$NON-NLS-1$
        } catch (Exception e) {
            ExceptionHandler.process(e);
        }
        tmpFolder = tmpFolder + "/dbWizard"; //$NON-NLS-1$
        File file = new File(tmpFolder);
        if (!file.exists()) {
            file.mkdirs();
        }
        return tmpFolder + "/"; //$NON-NLS-1$
    }

    // hywang added for bug 7038
    private static List<String> getAllDBFuctions(DatabaseMetaData dbMetadata) {
        if (dbMetadata == null) {
            return functionlist;
        }
        try {
            String[] systemFunctions = null;
            if (dbMetadata.getSystemFunctions() != null) {
                systemFunctions = dbMetadata.getSystemFunctions().split(",\\s*"); //$NON-NLS-N$ //$NON-NLS-1$
            }
            String[] numericFunctions = null;
            if (dbMetadata.getNumericFunctions() != null) {
                numericFunctions = dbMetadata.getNumericFunctions().split(",\\s*"); //$NON-NLS-N$ //$NON-NLS-1$
            }

            String[] stringFunctions = null;
            if (dbMetadata.getStringFunctions() != null) {
                stringFunctions = dbMetadata.getStringFunctions().split(",\\s*"); //$NON-NLS-N$ //$NON-NLS-1$
            }

            String[] timeFunctions = null;
            if (dbMetadata.getTimeDateFunctions() != null) {
                timeFunctions = dbMetadata.getTimeDateFunctions().split(",\\s*"); //$NON-NLS-N$ //$NON-NLS-1$
            }

            convertFunctions2Array(functionlist, systemFunctions);

            convertFunctions2Array(functionlist, numericFunctions);

            convertFunctions2Array(functionlist, stringFunctions);

            convertFunctions2Array(functionlist, timeFunctions);

        } catch (SQLException e) {
            ExceptionHandler.process(e);
        }
        return functionlist;
    }

    // hywang added for bug 7038
    private static List<String> convertFunctions2Array(List<String> functionlist, String[] functions) {
        if (functions != null) {
            for (int i = 0; i < functions.length; i++) {
                functionlist.add(functions[i]);
            }

        }
        return functionlist;
    }

    public static boolean useAllSynonyms;

    public static boolean isUseAllSynonyms() {
        return useAllSynonyms;
    }

    public static void setUseAllSynonyms(boolean val) {
        useAllSynonyms = val;
    }

    /**
     * DOC ycbai Comment method "getMeataConnectionSchema".
     * 
     * @param metadataConnection
     * @return
     */
    public static String getMeataConnectionSchema(IMetadataConnection metadataConnection) {
        String schema = metadataConnection.getSchema();
        String dbType = metadataConnection.getDbType();
        String url = metadataConnection.getUrl();
        String generalJDBCDisplayName = EDatabaseConnTemplate.GENERAL_JDBC.getDBDisplayName();
        if (generalJDBCDisplayName.equals(dbType) && url.contains("oracle")) {//$NON-NLS-1$
            schema = metadataConnection.getUsername().toUpperCase();
        }
        return schema;
    }

    /**
     * DOC ycbai Comment method "getDBConnectionSchema".
     * 
     * @param dbConnection
     * @return
     */
    public static String getDBConnectionSchema(DatabaseConnection dbConnection) {
        IMetadataConnection imetadataConnection = ConvertionHelper.convert(dbConnection, true);
        return imetadataConnection.getSchema();
    }

    public static List getConnectionList(IMetadataConnection metadataConnection) {
        List list = getConnection(metadataConnection.getDbType(), metadataConnection.getUrl(), metadataConnection.getUsername(),
                metadataConnection.getPassword(), metadataConnection.getDatabase(), metadataConnection.getSchema(),
                metadataConnection.getDriverClass(), metadataConnection.getDriverJarPath(),
                metadataConnection.getDbVersionString(), metadataConnection.getAdditionalParams());
        return list;
    }

    public static boolean isOLAPConnection(DatabaseConnection connection) {
        if (connection != null && connection.getProductId().equals(EDatabaseTypeName.GENERAL_JDBC.getProduct())) {
            String url = connection.getURL();
            String value = url.substring(url.indexOf(":") + 1);
            String substring = value.substring(0, value.indexOf(":"));
            if (substring.contains("olap")) {
                return true;
            }
        }
        return false;
    }

    /**
     * DOC ycbai Comment method "handleDefaultValue".
     * 
     * @param column
     */
    public static void handleDefaultValue(MetadataColumn column) {
        if (column == null) {
            return;
        }
        String talendType = column.getTalendType();
        if (talendType == null) {
            return;
        }
        Expression initialValue = column.getInitialValue();
        if (initialValue == null) {
            return;
        }
        String defautVal = initialValue.getBody();
        if (StringUtils.isEmpty(defautVal)) {
            return;
        }
        if (talendType.equals(JavaTypesManager.INTEGER.getId()) || talendType.equals(JavaTypesManager.FLOAT.getId())
                || talendType.equals(JavaTypesManager.DOUBLE.getId()) || talendType.equals(JavaTypesManager.LONG.getId())
                || talendType.equals(JavaTypesManager.SHORT.getId()) || talendType.equals(JavaTypesManager.BIGDECIMAL.getId())
                || talendType.equals(JavaTypesManager.CHARACTER.getId())) {
            defautVal = TalendQuoteUtils.removeQuotes(defautVal);
            if (column.getTalendType().equals(JavaTypesManager.CHARACTER.getId())) {
                defautVal = TalendQuoteUtils.addQuotes(defautVal, TalendQuoteUtils.SINGLE_QUOTE);
            }
            initialValue.setBody(defautVal);
        } else {
            initialValue.setBody(TalendQuoteUtils.addQuotesIfNotExist(defautVal));
        }
    }
}
