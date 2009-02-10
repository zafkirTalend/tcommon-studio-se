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
package org.talend.core.model.metadata.builder.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.eclipse.jface.preference.IPreferenceStore;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.utils.database.DB2ForZosDataBaseMetadata;
import org.talend.commons.utils.database.TeradataDataBaseMetadata;
import org.talend.core.CorePlugin;
import org.talend.core.database.EDatabaseTypeName;
import org.talend.core.prefs.ITalendCorePrefConstants;

/**
 * DOC cantoine. Extract Meta Data Table. Contains all the Table and Metadata about a DB Connection. <br/>
 * 
 * $Id$
 * 
 */
public class ExtractMetaDataUtils {

    private static Logger log = Logger.getLogger(ExtractMetaDataUtils.class);

    public static Connection conn;

    public static String schema;

    public static boolean isReconnect = true;

    /**
     * DOC cantoine. Method to return DatabaseMetaData of a DB connection.
     * 
     * @param AbstractConnection conn
     * @return DatabaseMetaData
     */
    public static DatabaseMetaData getDatabaseMetaData(Connection conn, String dbType) {

        DatabaseMetaData dbMetaData = null;
        try {

            if (needFakeDatabaseMetaData(dbType)) {
                dbMetaData = createFakeDatabaseMetaData(conn);
            } else if (teradataNeedFakeDatabaseMetaData(dbType)) {
                dbMetaData = createTeradataFakeDatabaseMetaData(conn);
            }
            dbMetaData = conn.getMetaData();
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
     * check if a FakeDatabaseMetaData is needed. only for db2 on z/os right now.
     * 
     * @param dbMetaData
     * @return
     */
    private static boolean needFakeDatabaseMetaData(String dbType) {
        // TODO check if it's db2 for z/os
        if (dbType.equals(EDatabaseTypeName.IBMDB2ZOS.getXmlName())) {
            return true;
        }
        return false;
    }

    /**
     * check if a FakeDatabaseMetaData is needed. only for Teradata right now.
     * 
     * @param dbMetaData
     * @return
     */

    private static boolean teradataNeedFakeDatabaseMetaData(String dbType) {
        // TODO check if it's teradata
        if (dbType.equals(EDatabaseTypeName.TERADATA.getXmlName())) {
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

    /**
     * DOC cantoine. Method to return MetaDataInfo on Column DataBaseConnection.
     * 
     * @param ResultSet columns
     * @param String infoType
     * @return String : the result of column's information MetaData
     */
    public static String getStringMetaDataInfo(ResultSet columns, String infoType) {
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
        String driverClass = null;

        try {

            Hashtable<String, String> hashTable = new Hashtable<String, String>();
            hashTable.put("MySQL", "org.gjt.mm.mysql.Driver"); //$NON-NLS-1$ //$NON-NLS-2$
            hashTable.put("PostgreSQL", "org.postgresql.Driver"); //$NON-NLS-1$ //$NON-NLS-2$ 
            hashTable.put("PostgresPlus", "org.postgresql.Driver"); //$NON-NLS-1$ //$NON-NLS-2$ 
            hashTable.put("Greenplum", "org.postgresql.Driver"); //$NON-NLS-1$ //$NON-NLS-2$ 
            hashTable.put("ParAccel", "com.paraccel.Driver"); //$NON-NLS-1$ //$NON-NLS-2$ 
            hashTable.put("Oracle with SID", "oracle.jdbc.driver.OracleDriver"); //$NON-NLS-1$ //$NON-NLS-2$
            hashTable.put("Oracle with service name", "oracle.jdbc.driver.OracleDriver"); //$NON-NLS-1$ //$NON-NLS-2$
            hashTable.put("Generic ODBC", "sun.jdbc.odbc.JdbcOdbcDriver"); //$NON-NLS-1$ //$NON-NLS-2$
            hashTable.put("Microsoft SQL Server (Odbc driver)", "sun.jdbc.odbc.JdbcOdbcDriver"); //$NON-NLS-1$ //$NON-NLS-2$

            hashTable.put("IBM DB2", "com.ibm.db2.jcc.DB2Driver"); //$NON-NLS-1$ //$NON-NLS-2$
            hashTable.put("IBM DB2 ZOS", "COM.ibm.db2os390.sqlj.jdbc.DB2SQLJDriver"); //$NON-NLS-1$ //$NON-NLS-2$
            hashTable.put("SAS", "com.sas.rio.MVADriver"); //$NON-NLS-1$ //$NON-NLS-2$
            hashTable.put("Sybase ASE", "com.sybase.jdbc3.jdbc.SybDriver"); //$NON-NLS-1$ //$NON-NLS-2$
            hashTable.put("Sybase IQ", "com.sybase.jdbc3.jdbc.SybDriver"); //$NON-NLS-1$ //$NON-NLS-2$
            // hashTable.put("Sybase", "net.sourceforge.jtds.jdbc.Driver");
            // //$NON-NLS-1$ //$NON-NLS-2$
            hashTable.put("Microsoft SQL Server", "net.sourceforge.jtds.jdbc.Driver"); //$NON-NLS-1$ //$NON-NLS-2$
            hashTable.put("Ingres", "com.ingres.jdbc.IngresDriver"); //$NON-NLS-1$ //$NON-NLS-2$
            hashTable.put("Interbase", "interbase.interclient.Driver"); //$NON-NLS-1$ //$NON-NLS-2$            
            hashTable.put("SQLite", "org.sqlite.JDBC"); //$NON-NLS-1$ //$NON-NLS-2$
            hashTable.put("FireBird", "org.firebirdsql.jdbc.FBDriver"); //$NON-NLS-1$ //$NON-NLS-2$
            hashTable.put("Informix", "com.informix.jdbc.IfxDriver"); //$NON-NLS-1$ //$NON-NLS-2$
            hashTable.put("Access", "sun.jdbc.odbc.JdbcOdbcDriver"); //$NON-NLS-1$ //$NON-NLS-2$

            hashTable.put("Teradata", "com.ncr.teradata.TeraDriver"); //$NON-NLS-1$ //$NON-NLS-2$

            hashTable.put("JavaDB Embeded", "org.apache.derby.jdbc.EmbeddedDriver"); //$NON-NLS-1$
            //$NON-NLS-2$
            hashTable.put("JavaDB JCCJDBC", "com.ibm.db2.jcc.DB2Driver");
            //$NON-NLS-1$ //$NON-NLS-2$
            hashTable.put("JavaDB DerbyClient", "org.apache.derby.jdbc.ClientDriver"); //$NON-NLS-1$
            //$NON-NLS-2$
            hashTable.put("AS400", "com.ibm.as400.access.AS400JDBCDriver"); //$NON-NLS-1$

            hashTable.put("HSQLDB", "org.hsqldb.jdbcDriver"); //$NON-NLS-1$ //$NON-NLS-2$

            hashTable.put("HSQLDB Server", "org.hsqldb.jdbcDriver"); //$NON-NLS-1$ //$NON-NLS-2$

            hashTable.put("HSQLDB WebServer", "org.hsqldb.jdbcDriver"); //$NON-NLS-1$ //$NON-NLS-2$

            hashTable.put("HSQLDB In-Process", "org.hsqldb.jdbcDriver"); //$NON-NLS-1$ //$NON-NLS-2$

            hashTable.put("MaxDB", "com.sap.dbtech.jdbc.DriverSapDB"); //$NON-NLS-1$ //$NON-NLS-2$

            hashTable.put("Netezza", "org.netezza.Driver"); //$NON-NLS-1$ //$NON-NLS-2$

            driverClass = hashTable.get(dbType);

        } catch (Exception e) {
            log.error(e.toString());
            throw new RuntimeException(e);
        }
        return driverClass;
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
            if (driverJarPath[i] == null || driverJarPath[i].equals("")) {
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
    public static void getConnection(String dbType, String url, String username, String pwd, String dataBase, String schemaBase,
            final String driverClassName, final String driverJarPath, String dbVersion) {
        boolean isColsed = false;
        try {
            if (conn != null) {
                isColsed = conn.isClosed();
            }
        } catch (Exception e) {
            log.error(e.toString());
        }
        if (isReconnect || conn == null || isColsed) {
            try {
                checkDBConnectionTimeout();
                conn = connect(dbType, url, username, pwd, driverClassName, driverJarPath, dbVersion);

                if (schemaBase != null && !schemaBase.equals("")) { //$NON-NLS-1$
                    final boolean equals = EDatabaseTypeName.getTypeFromDbType(dbType).getProduct().equals(
                            EDatabaseTypeName.ORACLEFORSID.getProduct());
                    if (!ExtractMetaDataFromDataBase.checkSchemaConnection(schemaBase, conn, equals, dbType)) {
                        schema = null;
                    }
                } else {
                    schema = null;
                    // PTODO Verify for each Database type the Schema necessity
                    // if (dataBase.equals("")) {
                    // schema = null;
                    // } else {
                    // schema = dataBase;
                    // }
                }
            } catch (SQLException e) {
                log.error(e.toString());
                throw new RuntimeException(e);
            } catch (Exception e) {
                log.error(e.toString());
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * DOC cantoine. Method to close connect to DataBase.
     */
    public static void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                if (isReconnect) {
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
        for (String s : connString.split(";")) {
            s = s.toLowerCase();
            int pos = s.indexOf("dbq");
            if (pos > -1) {
                s = s.substring(pos + 3).replaceAll("=", "").trim();
                // check the value of dbp
                if (!s.endsWith(".mdb") && !s.endsWith(".accdb")) {
                    throw new Exception("No data found.");
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
        IPreferenceStore preferenceStore = CorePlugin.getDefault().getPreferenceStore();
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
                ExceptionHandler.process(e);
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
    public static Connection connect(String dbType, String url, String username, String pwd, final String driverClassNameArg,
            final String driverJarPathArg, String dbVersion) throws Exception {
        Connection connection;

        String driverClassName = driverClassNameArg;
        String[] driverJarPath = null;
        // see feature 4720&4722
        if ((driverJarPath == null || driverJarPath.equals(""))) {
            List<String> driverNames = EDatabaseDriver4Version.getDrivers(dbType, dbVersion);
            if (driverNames != null) {
                List<String> jarPathList = new ArrayList<String>();
                for (String jarName : driverNames) {
                    jarPathList.add(getJavaLibPath() + jarName);
                }
                driverJarPath = jarPathList.toArray(new String[0]);
                driverClassName = ExtractMetaDataUtils.getDriverClassByDbType(dbType);
            }
        }
        /*
         * For general jdbc, driver class is specific by user.
         */
        if (driverClassName == null || driverClassName.equals("")) {
            driverClassName = ExtractMetaDataUtils.getDriverClassByDbType(dbType);
            // see bug 4404: Exit TOS when Edit Access Schema in repository
            if (dbType.equals(EDatabaseTypeName.ACCESS.getXmlName())) {
                // throw exception to prevent getting connection, which may crash
                ExtractMetaDataUtils.checkAccessDbq(url);
            }
        }
        ExtractMetaDataUtils.checkDBConnectionTimeout();
        if (dbType != null && dbType.equalsIgnoreCase(EDatabaseTypeName.GENERAL_JDBC.getXmlName())) {
            JDBCDriverLoader loader = new JDBCDriverLoader();
            connection = loader.getConnection(driverJarPath, driverClassName, url, username, pwd);
        } else if (dbType != null && isValidJarFile(driverJarPath)) {
            // Load jdbc driver class dynamicly
            JDBCDriverLoader loader = new JDBCDriverLoader();
            connection = loader.getConnection(driverJarPath, driverClassName, url, username, pwd);
        } else {
            // Don't use DriverManager
            Class<?> klazz = Class.forName(driverClassName);
            Properties info = new Properties();
            info.put("user", username);
            info.put("password", pwd);

            connection = ((Driver) klazz.newInstance()).connect(url, info);
        }

        return connection;
    }

    /**
     * 
     * DOC YeXiaowei Comment method "getJavaLibPath".
     * 
     * @return
     */
    private static String getJavaLibPath() {
        String separator = "/";
        String javaLibPath = CorePlugin.getDefault().getLibrariesService().getJavaLibrariesPath();
        return javaLibPath + separator;
    }
}
