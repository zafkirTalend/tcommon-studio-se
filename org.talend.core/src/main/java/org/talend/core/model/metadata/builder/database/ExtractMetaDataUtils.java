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
package org.talend.core.model.metadata.builder.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

import org.apache.log4j.Logger;
import org.talend.core.database.EDatabaseTypeName;

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
    public static DatabaseMetaData getDatabaseMetaData(Connection conn) {

        DatabaseMetaData dbMetaData = null;
        try {
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
     * DOC cantoine. Method to return MetaDataInfo on Column DataBaseConnection.
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
            return metaDataInfo;
        } catch (Exception e) {
            // log.error(e.toString());
            return metaDataInfo;
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
            hashTable.put("Oracle with SID", "oracle.jdbc.driver.OracleDriver"); //$NON-NLS-1$ //$NON-NLS-2$
            hashTable.put("Oracle with service name", "oracle.jdbc.driver.OracleDriver"); //$NON-NLS-1$ //$NON-NLS-2$
            hashTable.put("Generic ODBC", "sun.jdbc.odbc.JdbcOdbcDriver"); //$NON-NLS-1$ //$NON-NLS-2$
            hashTable.put("Microsoft SQL Server (Odbc driver)", "sun.jdbc.odbc.JdbcOdbcDriver"); //$NON-NLS-1$ //$NON-NLS-2$

            hashTable.put("IBM DB2", "com.ibm.db2.jcc.DB2Driver"); //$NON-NLS-1$ //$NON-NLS-2$
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

            driverClass = hashTable.get(dbType);

        } catch (Exception e) {
            log.error(e.toString());
            throw new RuntimeException(e);
        }
        return driverClass;
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
    public static void getConnection(String dbType, String url, String username, String pwd, String dataBase, String schemaBase) {
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
                Class.forName(getDriverClassByDbType(dbType)).newInstance();
                conn = DriverManager.getConnection(url, username, pwd);
                if (schemaBase != null && !schemaBase.equals("")) { //$NON-NLS-1$
                    final boolean equals = EDatabaseTypeName.getTypeFromDbType(dbType).getProduct().equals(
                            EDatabaseTypeName.ORACLEFORSID.getProduct());
                    if (!ExtractMetaDataFromDataBase.checkSchemaConnection(schemaBase, conn, equals)) {
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
            if (!conn.isClosed()) {
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
}
