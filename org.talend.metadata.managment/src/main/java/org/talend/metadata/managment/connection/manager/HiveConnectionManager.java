// ============================================================================
//
// Copyright (C) 2006-2013 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.metadata.managment.connection.manager;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

import org.talend.commons.exception.CommonExceptionHandler;
import org.talend.core.database.EDatabase4DriverClassName;
import org.talend.core.database.conn.ConnParameterKeys;
import org.talend.core.model.metadata.IMetadataConnection;
import org.talend.core.model.metadata.builder.connection.DatabaseConnection;
import org.talend.core.model.metadata.builder.database.JavaSqlFactory;
import org.talend.core.model.metadata.builder.database.hive.EmbeddedHiveDataBaseMetadata;
import org.talend.core.model.metadata.connection.hive.HiveConnVersionInfo;
import org.talend.metadata.managment.hive.HiveClassLoaderFactory;

/**
 * Created by Marvin Wang on Mar 13, 2013.
 */
public class HiveConnectionManager extends DataBaseConnectionManager {

    private static HiveConnectionManager manager;

    /**
     * DOC marvin HiveDataBaseConnectionManager constructor comment.
     */
    private HiveConnectionManager() {
    }

    public static synchronized HiveConnectionManager getInstance() {
        if (manager == null) {
            manager = new HiveConnectionManager();
        }
        return manager;
    }

    /**
     * Creates a connection by the given argument, maybe it is an embedded hive connection or a standalone connection,
     * that depends on the parameter in {@link IMetadataConnection#getParameter(String)}. The key is
     * {@link ConnParameterKeys#CONN_PARA_KEY_HIVE_MODE}. Added by Marvin Wang on Mar 14, 2013.
     * 
     * @param metadataConn
     * @return
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws SQLException
     */
    public Connection createConnection(IMetadataConnection metadataConn) throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, SQLException {
        Connection conn = null;
        String hiveModel = (String) metadataConn.getParameter(ConnParameterKeys.CONN_PARA_KEY_HIVE_MODE);
        if (HiveConnVersionInfo.MODE_STANDALONE.getKey().equalsIgnoreCase(hiveModel)) {
            conn = createHiveStandloneConnection(metadataConn);
        } else {
            conn = createHiveEmbeddedConnection(metadataConn);
        }
        return conn;
    }

    /**
     * Returns the driver by the given argument. Added by Marvin Wang on Mar 14, 2013.
     * 
     * @param metadataConn
     * @return
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public Driver getDriver(IMetadataConnection metadataConn) throws ClassNotFoundException, InstantiationException,
            IllegalAccessException {
        ClassLoader hiveClassLoader = HiveClassLoaderFactory.getInstance().getClassLoader(metadataConn);
        String connURL = metadataConn.getUrl();
        Driver driver = null;
        if (connURL != null) {
            if (connURL.startsWith(DatabaseConnConstants.HIVE_2_URL_FORMAT)) {
                Class<?> driverClass = Class.forName(EDatabase4DriverClassName.HIVE2.getDriverClass(), true, hiveClassLoader);
                driver = (Driver) driverClass.newInstance();
            } else if (connURL.startsWith(DatabaseConnConstants.HIVE_1_URL_FORMAT)) {
                Class<?> driverClass = Class.forName(EDatabase4DriverClassName.HIVE.getDriverClass(), true, hiveClassLoader);
                driver = (Driver) driverClass.newInstance();
            } else {
                // Create a default hive connection.
            }
        }
        return driver;
    }

    private Connection createHiveStandloneConnection(IMetadataConnection metadataConn) throws ClassNotFoundException,
            InstantiationException, IllegalAccessException, SQLException {
        Connection hiveStandaloneConn = null;
        String connURL = metadataConn.getUrl();
        if (connURL != null) {
            if (connURL.startsWith(DatabaseConnConstants.HIVE_2_URL_FORMAT)) {
                hiveStandaloneConn = createHive2StandaloneConnection(metadataConn);
            } else if (connURL.startsWith(DatabaseConnConstants.HIVE_1_URL_FORMAT)) {
                hiveStandaloneConn = createHive1StandaloneConnection(metadataConn);
            } else {
                // Create a default hive connection.
            }
        }
        return hiveStandaloneConn;
    }

    private Connection createHive2StandaloneConnection(IMetadataConnection metadataConn) throws ClassNotFoundException,
            InstantiationException, IllegalAccessException, SQLException {
        String connURL = metadataConn.getUrl();
        String username = metadataConn.getUsername();
        String password = metadataConn.getPassword();

        // 1. Get class loader.
        ClassLoader hiveClassLoader = HiveClassLoaderFactory.getInstance().getClassLoader(metadataConn);

        // 2. Fetch the HiveDriver from the new classloader
        Class<?> driver = Class.forName(EDatabase4DriverClassName.HIVE2.getDriverClass(), true, hiveClassLoader);
        Driver hiveDriver = (Driver) driver.newInstance();

        // 3. Try to connect by driver
        Properties info = new Properties();

        username = username != null ? username : ""; //$NON-NLS-1$
        password = password != null ? password : "";//$NON-NLS-1$
        return hiveDriver.connect(connURL, info);
    }

    private Connection createHive1StandaloneConnection(IMetadataConnection metadataConn) throws ClassNotFoundException,
            InstantiationException, IllegalAccessException, SQLException {
        String connURL = metadataConn.getUrl();
        String username = metadataConn.getUsername();
        String password = metadataConn.getPassword();

        // 1. Get class loader.
        ClassLoader hiveClassLoader = HiveClassLoaderFactory.getInstance().getClassLoader(metadataConn);

        // 2. Fetch the HiveDriver from the new classloader
        Class<?> driver = Class.forName(EDatabase4DriverClassName.HIVE.getDriverClass(), true, hiveClassLoader);
        Driver hiveDriver = (Driver) driver.newInstance();

        // 3. Try to connect by driver
        Properties info = new Properties();

        username = username != null ? username : ""; //$NON-NLS-1$
        password = password != null ? password : "";//$NON-NLS-1$
        return hiveDriver.connect(connURL, info);
    }

    private Connection createHiveEmbeddedConnection(IMetadataConnection metadataConn) throws ClassNotFoundException,
            InstantiationException, IllegalAccessException {
        String connURL = metadataConn.getUrl();
        String username = metadataConn.getUsername();
        String password = metadataConn.getPassword();

        // 1. Try to connect by driver
        Properties info = new Properties();

        username = username != null ? username : ""; //$NON-NLS-1$
        password = password != null ? password : "";//$NON-NLS-1$
        info.setProperty("user", username);//$NON-NLS-1$
        info.setProperty("password", password);//$NON-NLS-1$
        JavaSqlFactory.doHivePreSetup((DatabaseConnection) metadataConn.getCurrentConnection());

        // 2. Get class loader.
        ClassLoader currClassLoader = Thread.currentThread().getContextClassLoader();
        ClassLoader hiveClassLoader = HiveClassLoaderFactory.getInstance().getClassLoader(metadataConn);
        Thread.currentThread().setContextClassLoader(hiveClassLoader);

        // 3. Fetch the HiveDriver from the new classloader
        Driver hiveDriver = null;
        Connection conn = null;
        try {
            if (connURL != null) {
                if (connURL.startsWith(DatabaseConnConstants.HIVE_2_URL_FORMAT)) {
                    Class<?> driver = Class.forName(EDatabase4DriverClassName.HIVE2.getDriverClass(), true, hiveClassLoader);
                    hiveDriver = (Driver) driver.newInstance();
                    conn = hiveDriver.connect(connURL, info);
                } else if (connURL.startsWith(DatabaseConnConstants.HIVE_1_URL_FORMAT)) {
                    Class<?> driver = Class.forName(EDatabase4DriverClassName.HIVE.getDriverClass(), true, hiveClassLoader);
                    hiveDriver = (Driver) driver.newInstance();
                    conn = hiveDriver.connect(connURL, info);
                } else {
                    // Create a default hive connection.
                }
            }
        } catch (SQLException e) {
            CommonExceptionHandler.process(e);
        } finally {
            Thread.currentThread().setContextClassLoader(currClassLoader);
        }
        return conn;
    }

    /**
     * Checks if Hive can be connected.
     * 
     * <pre>
     * <li>For hive standalone model, it trys to {
     * {@link #createHiveStandloneConnection(IMetadataConnection)}, if there is no any exceptions thrown, then it
     * indicates Standalone Hive can be connected. 
     * <li>For hive embedded model, it just checks if tables can be fetched from a "fake" database metadata of hive, 
     * The fake database metadata is {@link EmbeddedHiveDataBaseMetadata#checkConnection()}.
     * </pre>
     * 
     * Added by Marvin Wang on Mar 18, 2013.
     * 
     * @param metadataConn
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws SQLException
     */
    public void checkConnection(IMetadataConnection metadataConn) throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, SQLException {
        String hiveModel = (String) metadataConn.getParameter(ConnParameterKeys.CONN_PARA_KEY_HIVE_MODE);
        if (HiveConnVersionInfo.MODE_STANDALONE.getKey().equalsIgnoreCase(hiveModel)) {
            createHiveStandloneConnection(metadataConn);
        } else {
            EmbeddedHiveDataBaseMetadata embeddedHiveDatabaseMetadata = new EmbeddedHiveDataBaseMetadata(metadataConn);
            embeddedHiveDatabaseMetadata.checkConnection();
        }
    }

    /**
     * Extracts the database metadata from hive connection by the given argument {@link IMetadataConnection}, it can be
     * hive embedded or standalone .Added by Marvin Wang on Mar 14, 2013.
     * 
     * @param metadataConn
     * @return
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws SQLException
     */
    public DatabaseMetaData extractDatabaseMetaData(IMetadataConnection metadataConn) throws ClassNotFoundException,
            InstantiationException, IllegalAccessException, SQLException {
        String hiveModel = (String) metadataConn.getParameter(ConnParameterKeys.CONN_PARA_KEY_HIVE_MODE);
        if (HiveConnVersionInfo.MODE_STANDALONE.getKey().equalsIgnoreCase(hiveModel)) {
            return extractHiveStandaloneDatabaseMetaData(metadataConn);
        } else {
            return extractHiveEmbeddedDatabaseMetaData(metadataConn);
        }
    }

    private DatabaseMetaData extractHiveEmbeddedDatabaseMetaData(IMetadataConnection metadataConn) {
        return new EmbeddedHiveDataBaseMetadata(metadataConn);
    }

    private DatabaseMetaData extractHiveStandaloneDatabaseMetaData(IMetadataConnection metadataConn)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Connection conn = createHiveStandloneConnection(metadataConn);
        return conn.getMetaData();
    }

    public boolean isCDHHive2(IMetadataConnection metadataConn) {
        if (metadataConn != null) {
            String connURL = metadataConn.getUrl();
            if (connURL != null) {
                if (connURL.startsWith(DatabaseConnConstants.HIVE_2_URL_FORMAT)) {
                    String distroVersion = (String) metadataConn.getParameter(ConnParameterKeys.CONN_PARA_KEY_HIVE_VERSION);
                    if ("Cloudera_CDH4".equals(distroVersion)) { //$NON-NLS-1$
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
