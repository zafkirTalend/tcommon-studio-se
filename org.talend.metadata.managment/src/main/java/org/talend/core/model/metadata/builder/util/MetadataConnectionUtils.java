// ============================================================================
//
// Copyright (C) 2006-2012 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.model.metadata.builder.util;

import java.io.UnsupportedEncodingException;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.rpc.ServiceException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.commons.utils.database.DB2ForZosDataBaseMetadata;
import org.talend.commons.utils.database.SASDataBaseMetadata;
import org.talend.commons.utils.database.TeradataDataBaseMetadata;
import org.talend.commons.utils.platform.PluginChecker;
import org.talend.core.database.EDatabase4DriverClassName;
import org.talend.core.database.EDatabaseTypeName;
import org.talend.core.model.metadata.DBConnectionFillerImpl;
import org.talend.core.model.metadata.IMetadataConnection;
import org.talend.core.model.metadata.MetadataConnection;
import org.talend.core.model.metadata.MetadataFillFactory;
import org.talend.core.model.metadata.builder.ConvertionHelper;
import org.talend.core.model.metadata.builder.connection.Connection;
import org.talend.core.model.metadata.builder.connection.DatabaseConnection;
import org.talend.core.model.metadata.builder.connection.MDMConnection;
import org.talend.core.model.metadata.builder.database.DriverShim;
import org.talend.core.model.metadata.builder.database.ExtractMetaDataUtils;
import org.talend.core.model.metadata.builder.database.IDriverService;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.core.repository.model.ProxyRepositoryFactory;
import org.talend.core.runtime.CoreRuntimePlugin;
import org.talend.cwm.constants.SoftwareSystemConstants;
import org.talend.cwm.helper.ConnectionHelper;
import org.talend.cwm.helper.SwitchHelpers;
import org.talend.cwm.helper.TaggedValueHelper;
import org.talend.cwm.relational.RelationalFactory;
import org.talend.cwm.relational.TdSqlDataType;
import org.talend.cwm.softwaredeployment.SoftwaredeploymentFactory;
import org.talend.cwm.softwaredeployment.TdSoftwareSystem;
import org.talend.mdm.webservice.XtentisBindingStub;
import org.talend.mdm.webservice.XtentisPort;
import org.talend.mdm.webservice.XtentisServiceLocator;
import org.talend.repository.model.IRepositoryService;
import org.talend.utils.sql.ConnectionUtils;
import org.talend.utils.string.AsciiUtils;
import org.talend.utils.sugars.ReturnCode;
import org.talend.utils.sugars.TypedReturnCode;
import orgomg.cwm.foundation.softwaredeployment.Component;
import orgomg.cwm.foundation.softwaredeployment.DataProvider;
import orgomg.cwm.objectmodel.core.Package;
import orgomg.cwm.resource.relational.Catalog;
import orgomg.cwm.resource.relational.Schema;

/**
 * DOC zshen class global comment. Detailled comment
 */
public class MetadataConnectionUtils {

    private static Logger log = Logger.getLogger(MetadataConnectionUtils.class);

    // MOD mzhao 2009-06-05 Bug 7571
    private static final Map<String, Driver> DRIVER_CACHE = new HashMap<String, Driver>();

    private static final SimpleDateFormat SMPL_DATE_FMT = new SimpleDateFormat("yyyyMMddhhmmss"); //$NON-NLS-1$

    private static final String CHARS_TO_REMOVE = "/"; //$NON-NLS-1$

    private static final String REPLACEMENT_CHARS = "_"; //$NON-NLS-1$

    private static final String DRIVER_EXTENSION_POINT_ID = "org.talend.metadata.managment.DBDriver_extension";

    private static final String TOP_DRIVER_EXTENSION_ID = "org.talend.dataprofiler.core.TOPDriverService";

    private static List<String> sybaseDBProductsNames;

    // MOD by zshen don't needed can replaced by ExtractMetaDataUtils.metadataCon
    // private static IMetadataConnection metadataCon;

    private static Driver derbyDriver;

    public static final String FAKE_SCHEMA_SYNONYMS = "AllSynonyms";//$NON-NLS-N$

    /**
     * DOC xqliu Comment method "getConnectionMetadata". 2009-07-13 bug 7888.
     * 
     * @param conn
     * @return
     * @throws SQLException
     */

    // public static DatabaseMetaData getConnectionMetadata(java.sql.Connection conn) throws SQLException {
    // DatabaseMetaData dbMetaData = conn.getMetaData();
    // // MOD xqliu 2009-11-17 bug 7888
    // if (dbMetaData != null && dbMetaData.getDatabaseProductName() != null) {
    // if (dbMetaData.getDatabaseProductName().equals(EDatabaseTypeName.IBMDB2ZOS.getProduct())) {
    // dbMetaData = createFakeDatabaseMetaData(conn);
    // log.info("IBM DB2 for z/OS");
    // } else if (dbMetaData.getDatabaseProductName().equals(EDatabaseTypeName.TERADATA.getProduct()) && metadataCon !=
    // null
    // && metadataCon.isSqlMode()) {
    // dbMetaData = createTeradataFakeDatabaseMetaData(conn);
    // TeradataDataBaseMetadata teraDbmeta = (TeradataDataBaseMetadata) dbMetaData;
    // teraDbmeta.setDatabaseName(ExtractMetaDataUtils.metadataCon.getDatabase());
    // } else if (dbMetaData.getDatabaseProductName().equals(EDatabaseTypeName.SAS.getProduct())) {
    // dbMetaData = createSASFakeDatabaseMetaData(conn);
    // }
    // }
    // // ~
    // return dbMetaData;
    // }

    /**
     * only for db2 on z/os right now. 2009-07-13 bug 7888.
     * 
     * @param conn2
     * @return
     */
    private static DatabaseMetaData createFakeDatabaseMetaData(java.sql.Connection conn) {
        DB2ForZosDataBaseMetadata dmd = new DB2ForZosDataBaseMetadata(conn);
        return dmd;
    }

    private static DatabaseMetaData createTeradataFakeDatabaseMetaData(java.sql.Connection conn) {
        TeradataDataBaseMetadata tmd = new TeradataDataBaseMetadata(conn);
        return tmd;
    }

    private static DatabaseMetaData createSASFakeDatabaseMetaData(java.sql.Connection conn) {
        SASDataBaseMetadata tmd = new SASDataBaseMetadata(conn);
        return tmd;
    }

    /**
     * 
     * method "checkConnection".
     * 
     * @param metadataBean pass the parameter for java.sql.Connection.
     * @return one object which adjust whether the Connection can be connected and take one object for
     * java.sql.connection. Note if use this method, you need to care for the value of return and close the Connect
     * after use it.
     */
    public static TypedReturnCode<java.sql.Connection> checkConnection(IMetadataConnection metadataBean) {
        TypedReturnCode<java.sql.Connection> rc = new TypedReturnCode<java.sql.Connection>();
        rc.setOk(false);
        if (metadataBean == null) {
            rc.setMessage("connection information can not be null");
            return rc;
        }
        String driverClass = metadataBean.getDriverClass();
        String dbUrl = metadataBean.getUrl();
        String password = metadataBean.getPassword();
        String userName = metadataBean.getUsername();
        String dbType = metadataBean.getDbType();

        Properties props = new Properties();
        props.setProperty(TaggedValueHelper.PASSWORD, password == null ? "" : password);
        props.setProperty(TaggedValueHelper.USER, userName == null ? "" : userName);

        if (StringUtils.isNotBlank(dbUrl) && StringUtils.isNotBlank(driverClass)) {
            java.sql.Connection sqlConn = null;
            Driver driver = null;
            try {
                if (isHsqlInprocess(metadataBean)) {
                    List list = getConnection(metadataBean);
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i) instanceof Driver) {
                            driver = (Driver) list.get(i);
                        }
                        if (list.get(i) instanceof java.sql.Connection) {
                            sqlConn = (java.sql.Connection) list.get(i);
                        }
                    }
                } else {
                    driver = getClassDriver(metadataBean);
                    sqlConn = ConnectionUtils.createConnection(dbUrl, driver, props);
                }

                ReturnCode varc = ConnectionUtils.isValid(sqlConn);
                if (varc.isOk()) {
                    derbyDriver = null;
                    if (driver != null && isDerbyRelatedDb(driverClass, dbType)) {
                        DBConnectionFillerImpl.setDriver(driver);
                        derbyDriver = driver;
                    }
                    rc.setObject(sqlConn);
                    rc.setMessage(varc.getMessage());
                    rc.setOk(true);
                }
            } catch (SQLException e) {
                log.error(e, e);
                rc.setMessage(e.getCause() == null ? e.getMessage() : e.getCause().toString());
            } catch (InstantiationException e) {
                log.error(e, e);
                rc.setMessage(e.getCause() == null ? e.getMessage() : e.getCause().toString());
            } catch (IllegalAccessException e) {
                log.error(e, e);
                rc.setMessage(e.getCause() == null ? e.getMessage() : e.getCause().toString());
            } catch (ClassNotFoundException e) {
                log.error(e, e);
                rc.setMessage(e.getCause() == null ? e.getMessage() : e.getCause().toString());
            }
        } else {
            if (StringUtils.isNotBlank(dbUrl)) {
                rc.setMessage("the driver of connection parameter can not be null");
            } else {
                rc.setMessage("the url of connection parameter can not be null");
            }
        }
        return rc;
    }

    /**
     * 
     * method "checkConnection".
     * 
     * @param metadataBean pass the parameter for java.sql.Connection.
     * @return one object which adjust whether the Connection can be connected and take one object for
     * java.sql.connection. Note if use this method, you need to care for the value of return and close the Connect
     * after use it.
     */
    public static TypedReturnCode<java.sql.Connection> checkConnection(DatabaseConnection databaseConnection) {
        IMetadataConnection metadataConnection = new MetadataConnection();

        String dbUrl = databaseConnection.getURL();
        String password = databaseConnection.getPassword();
        String userName = databaseConnection.getUsername();
        String dbType = databaseConnection.getDatabaseType();
        String driverClass = databaseConnection.getDriverClass() == null ? EDatabase4DriverClassName
                .getDriverClassByDbType(dbType) : databaseConnection.getDriverClass();
        String driverJarPath = databaseConnection.getDriverJarPath();
        String dataBase = databaseConnection.getSID();
        String dbVersionString = databaseConnection.getDbVersionString();
        String additionalParams = databaseConnection.getAdditionalParams();

        // MOD qiongli 2011-9-6,TDQ 3317.handle context mode
        if (databaseConnection.isContextMode()) {
            IRepositoryService repositoryService = CoreRuntimePlugin.getInstance().getRepositoryService();
            if (repositoryService != null) {
                String contextName = databaseConnection.getContextName();
                DatabaseConnection origValueConn = null;
                if (contextName == null) {
                    origValueConn = repositoryService.cloneOriginalValueConnection(databaseConnection, true);
                } else {
                    origValueConn = repositoryService.cloneOriginalValueConnection(databaseConnection, false, contextName);
                }

                if (origValueConn != null) {
                    dbUrl = origValueConn.getURL();
                    password = origValueConn.getPassword();
                    userName = origValueConn.getUsername();
                    driverClass = origValueConn.getDriverClass();
                    driverJarPath = origValueConn.getDriverJarPath();
                    dbType = origValueConn.getDatabaseType();
                    dataBase = origValueConn.getSID();
                    dbVersionString = origValueConn.getDbVersionString();
                    additionalParams = origValueConn.getAdditionalParams();
                }

            }
        }// ~

        metadataConnection.setAdditionalParams(additionalParams);
        metadataConnection.setDbVersionString(dbVersionString);
        metadataConnection.setDatabase(dataBase);
        metadataConnection.setDbType(dbType);
        metadataConnection.setDriverJarPath(driverJarPath);
        metadataConnection.setDriverClass(driverClass);
        metadataConnection.setUsername(userName);
        metadataConnection.setPassword(password);
        metadataConnection.setUrl(dbUrl);
        return checkConnection(metadataConnection);
    }

    public static TdSoftwareSystem getSoftwareSystem(java.sql.Connection connection) throws SQLException {
        // MOD xqliu 2009-07-13 bug 7888

        DatabaseMetaData databaseMetadata = ExtractMetaDataUtils.getConnectionMetadata(connection);
        // ~
        // --- get informations
        String databaseProductName = null;
        try {
            databaseProductName = databaseMetadata.getDatabaseProductName();
            if (log.isInfoEnabled()) {
                log.info("Database Product Name: " + databaseProductName);
            }
        } catch (Exception e1) {
            log.warn("could not get database product name. " + e1, e1);
        }
        String databaseProductVersion = null;
        try {
            databaseProductVersion = databaseMetadata.getDatabaseProductVersion();
            if (log.isInfoEnabled()) {
                log.info("Database Product Version: " + databaseProductVersion);
            }
        } catch (Exception e1) {
            log.warn("Could not get database product version. " + e1, e1);
        }
        try {
            int databaseMinorVersion = databaseMetadata.getDatabaseMinorVersion();
            int databaseMajorVersion = databaseMetadata.getDatabaseMajorVersion();
            // simplify the database product version when these informations are accessible
            databaseProductVersion = Integer.toString(databaseMajorVersion) + "." + databaseMinorVersion;

            if (log.isDebugEnabled()) {
                log.debug("Database=" + databaseProductName + " | " + databaseProductVersion + ". DB version: "
                        + databaseMajorVersion + "." + databaseMinorVersion);
            }
        } catch (RuntimeException e) {
            // happens for Sybase ASE for example
            if (log.isDebugEnabled()) {
                log.debug("Database=" + databaseProductName + " | " + databaseProductVersion + " " + e, e);
            }
        }

        // --- create and fill the software system
        TdSoftwareSystem system = SoftwaredeploymentFactory.eINSTANCE.createTdSoftwareSystem();
        if (databaseProductName != null) {
            system.setName(databaseProductName);
            system.setSubtype(databaseProductName);
        }
        system.setType(SoftwareSystemConstants.DBMS.toString());
        if (databaseProductVersion != null) {
            system.setVersion(databaseProductVersion);
        }
        Component component = orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentFactory.eINSTANCE.createComponent();
        system.getOwnedElement().add(component);

        return system;
    }

    /**
     * DOC xqliu Comment method "isOdbcPostgresql".
     * 
     * @param connection
     * @return
     * @throws SQLException
     */
    public static boolean isOdbcPostgresql(java.sql.Connection connection) throws SQLException {
        return isOdbcPostgresql(ExtractMetaDataUtils.getConnectionMetadata(connection));
    }

    public static boolean isOdbcPostgresql(DatabaseMetaData connectionMetadata) throws SQLException {
        if (connectionMetadata.getDriverName() != null
                && connectionMetadata.getDriverName().toLowerCase().startsWith(DatabaseConstant.ODBC_DRIVER_NAME)
                && connectionMetadata.getDatabaseProductName() != null
                && connectionMetadata.getDatabaseProductName().toLowerCase().indexOf(DatabaseConstant.POSTGRESQL_PRODUCT_NAME) > -1) {
            return true;
        }
        return false;
    }

    /**
     * DOC zshen Comment method "isOdbcMssql". feature 10630
     * 
     * @param connection
     * @return
     * @throws SQLException
     */
    public static boolean isOdbcExcel(java.sql.Connection connection) throws SQLException {
        return isOdbcExcel(ExtractMetaDataUtils.getConnectionMetadata(connection));
    }

    public static boolean isOdbcExcel(DatabaseMetaData connectionMetadata) throws SQLException {
        if (connectionMetadata.getDriverName() != null
                && connectionMetadata.getDriverName().toLowerCase().startsWith(DatabaseConstant.ODBC_DRIVER_NAME)
                && connectionMetadata.getDatabaseProductName() != null
                && connectionMetadata.getDatabaseProductName().equals(DatabaseConstant.ODBC_EXCEL_PRODUCT_NAME)) {
            return true;
        }
        return false;
    }

    /**
     * 
     * DOC qiongli Comment method "isAccess".
     * 
     * @param connection
     * @return
     * @throws SQLException
     */
    public static boolean isAccess(java.sql.Connection connection) throws SQLException {
        return isAccess(ExtractMetaDataUtils.getConnectionMetadata(connection));
    }

    public static boolean isAccess(DatabaseMetaData connectionMetadata) throws SQLException {
        if (connectionMetadata.getDriverName() != null && connectionMetadata.getDatabaseProductName() != null
                && connectionMetadata.getDatabaseProductName().equals(DatabaseConstant.MS_ACCESS_PRODUCT_NAME)) {
            return true;
        }
        return false;
    }

    /**
     * DOC zshen Comment method "isSybase".
     * 
     * @param connection
     * @return decide to whether is sybase connection
     * @throws SQLException
     */
    public static boolean isSybase(java.sql.Connection connection) throws SQLException {
        return isSybase(ExtractMetaDataUtils.getConnectionMetadata(connection));
    }

    public static boolean isSybase(DatabaseMetaData connectionMetadata) throws SQLException {
        if (connectionMetadata.getDriverName() != null && connectionMetadata.getDatabaseProductName() != null) {
            for (String keyString : getSybaseDBProductsName()) {
                if (keyString.trim().equals(connectionMetadata.getDatabaseProductName().trim())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * DOC xqliu Comment method "isTeradata".
     * 
     * @param connection
     * @return
     */
    public static boolean isTeradata(DatabaseConnection connection) {
        EDatabaseTypeName dbType = EDatabaseTypeName.getTypeFromDbType(connection.getDatabaseType());
        if (dbType == EDatabaseTypeName.TERADATA) {
            return true;
        }
        return false;
    }

    /**
     * zshen Comment method "isOdbcConnection". feature 10630
     * 
     * @param connection
     * @return
     * @throws SQLException
     */
    public static boolean isOdbcConnection(DatabaseMetaData connectionMetadata) throws SQLException {
        if (connectionMetadata.getDriverName() != null
                && connectionMetadata.getDriverName().toLowerCase().startsWith(DatabaseConstant.ODBC_DRIVER_NAME)) {
            return true;
        }
        return false;
    }

    public static boolean isOdbcConnection(java.sql.Connection connection) throws SQLException {
        return isOdbcConnection(ExtractMetaDataUtils.getConnectionMetadata(connection));
    }

    /**
     * 
     * zshen Comment method "isODBCCatalog".
     * 
     * @param catalogName the name for need to be decided.
     * @param connection
     * @return if connection is a ODBC connection and catalogName isn't which be found then return ture, else return
     * true.
     * @throws SQLException
     */
    public static boolean isODBCCatalog(String catalogName, DatabaseMetaData connectionMetadata) throws SQLException {
        if (isOdbcConnection(connectionMetadata)) {
            String userCatalogName = connectionMetadata.getConnection().getCatalog();
            if (catalogName != null
                    && (catalogName.equals(userCatalogName) || userCatalogName == null || "null".equals(userCatalogName))) {

                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    public static boolean isOracle8i(Connection connection) {
        if (connection != null && connection instanceof DatabaseConnection) {
            DatabaseConnection dbConn = (DatabaseConnection) connection;
            String version = dbConn.getDbVersionString();
            if (version != null && version.equals("ORACLE_8")) { //$NON-NLS-1$
                return true;
            }
        }
        return false;
    }

    public static boolean isOracle(Connection connection) {
        if (connection != null && connection instanceof DatabaseConnection) {
            DatabaseConnection dbConn = (DatabaseConnection) connection;
            if (EDatabaseTypeName.ORACLEFORSID.getDisplayName().equals(dbConn.getDatabaseType())
                    || EDatabaseTypeName.ORACLESN.getDisplayName().equals(dbConn.getDatabaseType())
                    || EDatabaseTypeName.ORACLE_RAC.getDisplayName().equals(dbConn.getDatabaseType())
                    || EDatabaseTypeName.ORACLE_OCI.getDisplayName().equals(dbConn.getDatabaseType())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isOracleJDBC(Connection connection) {
        if (connection != null && connection instanceof DatabaseConnection) {
            DatabaseConnection dbConn = (DatabaseConnection) connection;
            if (EDatabaseTypeName.GENERAL_JDBC.getDisplayName().equals(dbConn.getDatabaseType())) {
                if (dbConn.getDriverClass() != null
                        && dbConn.getDriverClass().startsWith(DatabaseConstant.ODBC_ORACLE_PRODUCT_NAME)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isMssql(DatabaseMetaData connectionMetadata) throws SQLException {
        if (connectionMetadata.getDriverName() != null && connectionMetadata.getDatabaseProductName() != null) {
            if (EDataBaseType.Microsoft_SQL_Server.getProductName().equals(connectionMetadata.getDatabaseProductName().trim())) {
                return true;

            }
        }
        return false;
    }

    public static boolean isMssql(java.sql.Connection connection) throws SQLException {
        return isMssql(ExtractMetaDataUtils.getConnectionMetadata(connection));
    }

    public static boolean isMysql(java.sql.Connection connection) throws SQLException {
        return isMysql(ExtractMetaDataUtils.getConnectionMetadata(connection));
    }

    public static boolean isMysql(DatabaseMetaData connectionMetadata) throws SQLException {
        if (connectionMetadata.getDriverName() != null && connectionMetadata.getDatabaseProductName() != null) {
            if (EDataBaseType.MySQL.getProductName().equals(connectionMetadata.getDatabaseProductName().trim())) {
                return true;

            }
        }
        return false;
    }

    /**
     * yyi 2010-08-25 for 14851, Sybase DB has several names with different productions and versions. For example the
     * Sybase IQ with version 12.6 is called 'Sybase' getting by JDBC but the version 15+ it is changed to 'Sybase IQ'.
     * it is user by org.talend.cwm.db.connection.ConnectionUtils.isSybase
     * 
     * @return All Sybase DB products name
     * ,"Adaptive Server Enterprise","Sybase Adaptive Server IQ","Sybase IQ","Sybase"
     */
    public static String[] getSybaseDBProductsName() {
        if (null == sybaseDBProductsNames) {
            sybaseDBProductsNames = new ArrayList<String>();
            sybaseDBProductsNames.add("Adaptive Server Enterprise");
            sybaseDBProductsNames.add(" Sybase Adaptive Server IQ");
            sybaseDBProductsNames.add("Sybase");
            sybaseDBProductsNames.add("Sybase IQ");
            sybaseDBProductsNames.add("Adaptive Server Enterprise | Sybase Adaptive Server IQ");
        }
        return sybaseDBProductsNames.toArray(new String[sybaseDBProductsNames.size()]);
    }

    /**
     * DOC qzhang Comment method "getClassDriver".
     * 
     * @param driverClassName
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     */
    public static Driver getClassDriver(IMetadataConnection metadataBean) throws InstantiationException, IllegalAccessException,
            ClassNotFoundException {
        String driverClassName = metadataBean.getDriverClass();
        // MOD mzhao 2009-06-05,Bug 7571 Get driver from catch first, if not
        // exist then get a new instance.
        if (driverClassName.equals("org.apache.derby.jdbc.EmbeddedDriver")) {
            DRIVER_CACHE.remove(driverClassName);
        }
        Driver driver = DRIVER_CACHE.get(driverClassName);
        // The case for generalJDBC
        String driverPath = metadataBean.getDriverJarPath();

        if (StringUtils.isEmpty(driverPath)) {
            if (driver != null) {
                return driver;
            }
        }

        IExtension extension = Platform.getExtensionRegistry().getExtension(DRIVER_EXTENSION_POINT_ID, TOP_DRIVER_EXTENSION_ID);
        if (extension != null) {
            // top
            if (PluginChecker.isOnlyTopLoaded()) {
                IConfigurationElement[] configurationElement = extension.getConfigurationElements();
                for (IConfigurationElement ele : configurationElement) {
                    try {
                        IDriverService driverService = (IDriverService) ele.createExecutableExtension("class");
                        driver = driverService.getDriver(metadataBean);
                    } catch (Exception e) {
                        log.error(e, e);
                    }
                }
            } else {
                // tdq
            	driver = getDriver(metadataBean);
            }
        } else {
            // tos
        	driver = getDriver(metadataBean);
        }
        // MOD mzhao 2009-06-05,Bug 7571 Get driver from catch first, if not
        // exist then get a new instance.
        if (driver != null) {
            DRIVER_CACHE.put(driverClassName, driver);
        }
        return driver;
    }

	/**
	 * get driver.
	 * @param metadataBean
	 * @param driver
	 * @return
	 */
	private static Driver getDriver(IMetadataConnection metadataBean) {
		Driver driver = null;
		List<?> connList = getConnection(metadataBean);
		try {
		    if (connList != null && !connList.isEmpty()) { // FIXME unnecessary check !connList.isEmpty()
		        // FIXME scorreia 2011-03-31 why do we loop here? Is it possible to have several drivers. If
		        // yes,
		        // what do we do?
			    for (int i = 0; i < connList.size(); i++) {
					if (connList.get(i) instanceof Driver) {
				        driver = (DriverShim) connList.get(i); // FIXME scorreia 2011-03-31 strange cast here.
			        }
			    }
		    }
		} catch (Exception e) {
		    log.error(e, e);
		}finally{
			// ADD msjian TDQ-5952: we should close the unused connection at once.
			try {
			    for (int i = 0; i < connList.size(); i++) {
			        if (connList.get(i) instanceof java.sql.Connection) {
			        	java.sql.Connection con = (java.sql.Connection) connList.get(i);
					    if (con != null && !con.isClosed()) {
					    	con.close();
					    }
			        }
			    }
			} catch (SQLException e) {
				  log.error(e, e);
			}
			// TDQ-5952~
		}
		return driver;
	}

    /**
     * This method to get all database template supported by TDQ.
     * 
     * @return
     */
    public static List<String> getTDQSupportDBTemplate() {
        IExtension extension = Platform.getExtensionRegistry().getExtension(DRIVER_EXTENSION_POINT_ID, TOP_DRIVER_EXTENSION_ID);
        if (extension != null) {
            IConfigurationElement[] configurationElement = extension.getConfigurationElements();
            for (IConfigurationElement ele : configurationElement) {
                try {
                    IDriverService driverService = (IDriverService) ele.createExecutableExtension("class");
                    return driverService.getTDQSupportDBTemplate();
                } catch (Exception e) {
                    log.error(e, e);
                }
            }
        }

        return new ArrayList<String>();
    }

    /**
     * 
     * DOC zshen Comment method "getCommonQueryStr".
     * 
     * @param sqlConn
     * @return null only if conn is null.or can not find the
     */
    public static String getCommonQueryStr(String productName, String tableName) {
        if (productName == null) {
            return null;
        }
        productName = productName.replaceAll(" ", "_");
        EDataBaseType eDataBaseType = null;
        try {
            eDataBaseType = EDataBaseType.valueOf(productName);
        } catch (Exception e) {
            eDataBaseType = EDataBaseType.Microsoft_SQL_Server;
        }
        String sqlStr = "";
        switch (eDataBaseType) {
        case Oracle:
            sqlStr = "SELECT COMMENTS FROM USER_TAB_COMMENTS WHERE TABLE_NAME='" + tableName + "'"; //$NON-NLS-1$ //$NON-NLS-2$
            break;
        case MySQL:
            sqlStr = "SELECT TABLE_COMMENT FROM information_schema.TABLES WHERE TABLE_NAME='" + tableName + "'"; //$NON-NLS-1$ //$NON-NLS-2$
            break;
        default:
            sqlStr = null;

        }
        return sqlStr;

    }

    /**
     * DOC xqliu Comment method "createDataType".
     * 
     * @param dataType
     * @param typeName
     * @param decimalDigits
     * @param numPrecRadix
     * @return
     */
    public static TdSqlDataType createDataType(int dataType, String typeName, int decimalDigits, int numPrecRadix) {
        TdSqlDataType sqlDataType = RelationalFactory.eINSTANCE.createTdSqlDataType();
        sqlDataType.setName(typeName);
        sqlDataType.setJavaDataType(dataType);
        sqlDataType.setNumericPrecision(decimalDigits);
        sqlDataType.setNumericPrecisionRadix(numPrecRadix);
        return sqlDataType;
    }

    /**
     * 
     * zshen Comment method "getXtentisBindingStub".
     * 
     * @param metadataBean
     * @return
     * @throws ServiceException
     * @noreference This method is not intended to be referenced by clients.
     */
    public static XtentisBindingStub getXtentisBindingStub(IMetadataConnection metadataBean) throws ServiceException {
        return getXtentisBindingStub(metadataBean.getUrl(), metadataBean.getUniverse(), metadataBean.getUsername(),
                metadataBean.getPassword());
    }

    /**
     * 
     * zshen Comment method "getXtentisBindingStub".
     * 
     * @param dataProvider
     * @return
     * @throws ServiceException
     */
    public static XtentisBindingStub getXtentisBindingStub(MDMConnection dataProvider) throws ServiceException {
        return getXtentisBindingStub(dataProvider.getPathname(), ConnectionHelper.getUniverse(dataProvider),
                dataProvider.getUsername(), dataProvider.getPassword());
    }

    /**
     * 
     * zshen Comment method "getXtentisBindingStub".
     * 
     * @param url
     * @param universe
     * @param userName
     * @param password
     * @return
     * @throws ServiceException
     */
    private static XtentisBindingStub getXtentisBindingStub(String url, String universe, String userName, String password)
            throws ServiceException {

        // initialization Web Service calling
        XtentisServiceLocator xtentisService = new XtentisServiceLocator();
        xtentisService.setXtentisPortEndpointAddress(url);
        XtentisPort xtentisWS = xtentisService.getXtentisPort();
        XtentisBindingStub stub = (XtentisBindingStub) xtentisWS;

        // authorization
        if (universe == null || universe.trim().length() == 0) {
            stub.setUsername(userName);
        } else {
            stub.setUsername(universe + "/" + userName);
        }
        stub.setPassword(password);
        return stub;
    }

    public static void main(String[] args) {
        MetadataConnectionUtils.getCommonQueryStr(EDataBaseType.Microsoft_SQL_Server.getProductName(), "tableName");
    }

    /**
     * Method "createTechnicalName" creates a technical name used for file system storage. MOD mzhao make this method as
     * public access.
     * 
     * @param functionalName the user friendly name
     * @return the technical name created from the user given name.
     */
    public static String createTechnicalName(final String functionalName) {
        String techname = "no_name"; //$NON-NLS-1$
        if (functionalName == null) {
            log.warn("A functional name should not be null");
            return techname;
        }
        // encode in base 64 so that all characters such white spaces, accents, everything that is dangerous when used
        // for file names are removed
        try {
            // encode
            String b64 = new String(Base64.encodeBase64(functionalName.getBytes()), "UTF-8"); //$NON-NLS-1$
            // replace special characters
            String date = SMPL_DATE_FMT.format(new Date(System.currentTimeMillis()));
            techname = AsciiUtils.replaceCharacters(b64, CHARS_TO_REMOVE, REPLACEMENT_CHARS) + date;
        } catch (UnsupportedEncodingException e) {
            log.error(e, e);
        } // .replaceAll(B64ID, PREFIX);
        if (log.isDebugEnabled()) {
            log.debug("Functional name: " + functionalName + " -> techname: " + techname);
        }
        return techname;
    }

    public static List<String> getPackageFilter(Connection connection, DatabaseMetaData dbMetaData, boolean isCatalog) {
        List<String> packageFilter = new ArrayList<String>();
        try {
            if (isMdmConnection(connection)) {
                // MDMConnection mdmConnection = (MDMConnection) connection;
            } else {
                DatabaseConnection dbConnection = (DatabaseConnection) connection;
                // MOD qiongli 2011-9-23 handle context mod.
                IRepositoryService repositoryService = null;
                DatabaseConnection origValueConn = null;
                if (dbConnection.isContextMode()) {
                    repositoryService = CoreRuntimePlugin.getInstance().getRepositoryService();
                    if (repositoryService != null) {
                        // get the original value and select the defalut context
                        String contextName = dbConnection.getContextName();
                        origValueConn = repositoryService.cloneOriginalValueConnection(dbConnection, contextName == null ? true
                                : false, contextName);
                    }
                }
                if (isCatalog) {
                    if (dbMetaData.supportsCatalogsInIndexDefinitions()) {
                        String sid = dbConnection.getSID();
                        if (origValueConn != null) {
                            sid = origValueConn.getSID();
                        }
                        if (!StringUtils.isEmpty(sid) && !packageFilter.contains(sid)) {
                            packageFilter.add(sid);
                        }
                    }
                } else {
                    if (dbMetaData.supportsSchemasInIndexDefinitions()) {
                        String uiSchema = dbConnection.getUiSchema();
                        if (origValueConn != null) {
                            uiSchema = origValueConn.getUiSchema();
                        }
                        if (!StringUtils.isEmpty(uiSchema) && !packageFilter.contains(uiSchema)) {
                            packageFilter.add(uiSchema);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            log.error(e, e);
        }
        return packageFilter;
    }

    public static boolean isMdmConnection(DataProvider dataprovider) {
        return dataprovider instanceof MDMConnection;
    }

    /**
     * DOC connection created by TOS need to fill the basic information for useing in TOP.<br>
     * 
     * 
     * @param conn
     * @return
     */
    public static Connection fillConnectionInformation(ConnectionItem connItem) {
        boolean saveFlag = false;
        Connection conn = connItem.getConnection();
        // // fill metadata of connection
        // if (conn.getName() == null || conn.getLabel() == null) {
        // saveFlag = true;
        // conn = fillConnectionMetadataInformation(conn);
        // }
        // fill structure of connection
        List<Catalog> catalogs = ConnectionHelper.getCatalogs(conn);
        List<Schema> schemas = ConnectionHelper.getSchema(conn);
        // MOD xqliu 2010-10-19 bug 16441: case insensitive
        boolean isNeedToFill = false;
        if (conn instanceof DatabaseConnection) {
            String dbProductID = ((DatabaseConnection) conn).getProductId();
            if (ConnectionHelper.getAllSchemas(conn).isEmpty()
                    && (EDatabaseTypeName.MSSQL05_08.getProduct().equals(dbProductID) || EDatabaseTypeName.MSSQL.getProduct()
                            .equals(dbProductID))) {
                isNeedToFill = true;
            } else if (EDatabaseTypeName.AS400.getProduct().equals(dbProductID)) {
                isNeedToFill = true;
            } else if (EDatabaseTypeName.PSQL.getProduct().equals(dbProductID)) {
                isNeedToFill = true;
            }
        }
        if ((catalogs.isEmpty() && schemas.isEmpty()) || isNeedToFill) {

            // ~ 16441
            DatabaseConnection dbConn = SwitchHelpers.DATABASECONNECTION_SWITCH.doSwitch(conn);
            if (dbConn != null) {
                saveFlag = true;

                conn = fillDbConnectionInformation(dbConn);
            }
            // else {
            // MDMConnection mdmConn = SwitchHelpers.MDMCONNECTION_SWITCH.doSwitch(conn);
            // if (mdmConn != null) {
            // if (mdmConn.getDataPackage().isEmpty()) {
            // saveFlag = true;
            // conn = fillMdmConnectionInformation(mdmConn);
            // }
            // }
            // }
        }
        if (saveFlag && conn != null) {
            try {
                ProxyRepositoryFactory.getInstance().save(connItem);
            } catch (PersistenceException e) {
                log.error(e, e);
            }
        }
        // updateRetrieveAllFlag(conn);
        return conn;
    }

    /**
     * 
     * DOC zshen Comment method "setMDMConnectionParameter".
     * 
     * @param conn
     * @param metadataConnection
     * 
     * set the parameter of MDMConnection from metadataConnection
     */
    public static void setMDMConnectionParameter(MDMConnection conn, IMetadataConnection metadataConnection) {
        if (conn == null || metadataConnection == null) {
            return;
        }
        // host
        conn.setServer(metadataConnection.getServerName());

        // port
        conn.setPort(metadataConnection.getPort());
        // password
        ConnectionHelper.setPassword(conn, metadataConnection.getPassword());
        // user
        conn.setUsername(metadataConnection.getUsername());
        conn.setComment(metadataConnection.getComment());
        conn.setId(metadataConnection.getId());
        conn.setLabel(metadataConnection.getLabel());
        conn.setUniverse(metadataConnection.getUniverse());
        conn.setDatamodel(metadataConnection.getDatamodel());
        conn.setDatacluster(metadataConnection.getDatacluster());

    }

    // /**
    // * DOC xqliu Comment method "fillConnectionMetadataInformation".
    // *
    // * @param conn
    // * @return
    // */
    // public static Connection fillConnectionMetadataInformation(Connection conn) {
    // // ADD xqliu 2010-10-13 bug 15756
    // int tSize = conn.getTaggedValue().size();
    // EList<Package> dataPackage = conn.getDataPackage();
    // // ~ 15756
    // Property property = PropertyHelper.getProperty(conn);
    // // fill name and label
    // conn.setName(property.getLabel());
    // conn.setLabel(property.getLabel());
    // // fill metadata
    // MetadataHelper.setAuthor(conn, property.getAuthor().getLogin());
    // MetadataHelper.setDescription(property.getDescription(), conn);
    // String statusCode = property.getStatusCode() == null ? "" : property.getStatusCode();
    // MetadataHelper.setDevStatus(conn, "".equals(statusCode) ? DevelopmentStatus.DRAFT.getLiteral() : statusCode);
    // MetadataHelper.setPurpose(property.getPurpose(), conn);
    // MetadataHelper.setVersion(property.getVersion(), conn);
    // String retrieveAllMetadataStr = MetadataHelper.getRetrieveAllMetadata(conn);
    // // ADD xqliu 2010-10-13 bug 15756
    // if (tSize == 0 && dataPackage.size() == 1 && !"".equals(dataPackage.get(0).getName())) {
    // retrieveAllMetadataStr = "false";
    // }
    // // ~ 15756
    // // MOD klliu bug 15821 retrieveAllMetadataStr for Diff database
    // MetadataHelper.setRetrieveAllMetadata(retrieveAllMetadataStr == null ? "true" : retrieveAllMetadataStr, conn);
    // String schema = MetadataHelper.getOtherParameter(conn);
    // MetadataHelper.setOtherParameter(schema, conn);
    // return conn;
    // }

    /**
     * DOC xqliu Comment method "fillDbConnectionInformation".
     * 
     * @param dbConn
     * @return
     */
    public static DatabaseConnection fillDbConnectionInformation(DatabaseConnection dbConn) {
        // fill database structure
        // if (DatabaseConstant.XML_EXIST_DRIVER_NAME.equals(dbConn.getDriverClass())) { // xmldb(e.g eXist)
        // IXMLDBConnection xmlDBConnection = new EXistXMLDBConnection(dbConn.getDriverClass(), dbConn.getURL());
        // ConnectionHelper.addXMLDocuments(xmlDBConnection.createConnection(dbConn));
        // } else {
        boolean noStructureExists = ConnectionHelper.getAllCatalogs(dbConn).isEmpty()
                && ConnectionHelper.getAllSchemas(dbConn).isEmpty();
        // MOD xqliu 2010-10-19 bug 16441: case insensitive
        // if (ConnectionHelper.getAllSchemas(dbConn).isEmpty()
        // && (ConnectionUtils.isMssql(dbConn) ||
        // ConnectionUtils.isPostgresql(dbConn) || ConnectionUtils
        // .isAs400(dbConn))) {
        // noStructureExists = true;
        // }
        // ~ 16441
        java.sql.Connection sqlConn = null;
        // only for derby related driver
        try {
            if (noStructureExists) { // do no override existing catalogs or
                                     // schemas
                // Map<String, String> paramMap =
                // ParameterUtil.toMap(ConnectionUtils.createConnectionParam(dbConn));
                IMetadataConnection metaConnection = ConvertionHelper.convert(dbConn);
                dbConn = (DatabaseConnection) MetadataFillFactory.getDBInstance().fillUIConnParams(metaConnection, dbConn);
                sqlConn = (java.sql.Connection) MetadataConnectionUtils.checkConnection(metaConnection).getObject();
                DatabaseMetaData databaseMetaData = ExtractMetaDataUtils.getDatabaseMetaData(sqlConn, dbConn, false);
                if (sqlConn != null) {
                    MetadataFillFactory.getDBInstance().fillCatalogs(dbConn, databaseMetaData,
                            MetadataConnectionUtils.getPackageFilter(dbConn, databaseMetaData, true));
                    MetadataFillFactory.getDBInstance().fillSchemas(dbConn, databaseMetaData,
                            MetadataConnectionUtils.getPackageFilter(dbConn, databaseMetaData, false));
                }
            }
        } catch (Exception e) {
            log.error(e, e);
        } finally {
            if (derbyDriver != null) {
                try {
                    derbyDriver.connect("jdbc:derby:;shutdown=true", null); //$NON-NLS-1$
                } catch (SQLException e) {
                    // exception of shutdown success. no need to catch.
                }
            }
        }
        return dbConn;
    }

    // /**
    // * update the RETRIEVE_ALL tagged value of this connection.
    // *
    // * @param conn
    // */
    // private static void updateRetrieveAllFlag(Connection conn) {
    // if (conn != null && conn instanceof DatabaseConnection) {
    // String sid = ((DatabaseConnection) conn).getSID();
    // if (sid != null && sid.trim().length() > 0) {
    // TaggedValueHelper.setTaggedValue(conn, TaggedValueHelper.RETRIEVE_ALL, "false");
    // } else {
    // TaggedValueHelper.setTaggedValue(conn, TaggedValueHelper.RETRIEVE_ALL, "true");
    // }
    // }
    // }

    // MOD by zshen don't needed can replaced by ExtractMetaDataUtils
    // public static IMetadataConnection getMetadataCon() {
    // return metadataCon;
    // }
    //
    // public static void setMetadataCon(IMetadataConnection metadataConnection) {
    // metadataCon = metadataConnection;
    // }

    public static boolean isPostgresql(java.sql.Connection connection) throws SQLException {
        return isPostgresql(ExtractMetaDataUtils.getConnectionMetadata(connection));
    }

    public static boolean isPostgresql(DatabaseMetaData connectionMetadata) throws SQLException {
        if (connectionMetadata != null) {
            String databaseProductName = connectionMetadata.getDatabaseProductName();
            if (databaseProductName != null) {
                return databaseProductName.toLowerCase().indexOf(DatabaseConstant.POSTGRESQL_PRODUCT_NAME) > -1;
            }
        }
        return false;
    }

    public static boolean isDerbyRelatedDb(String driverClassname, String dbType) {
        return (driverClassname != null && driverClassname.equals(EDatabase4DriverClassName.JAVADB_EMBEDED.getDriverClass()))
                || (dbType != null && dbType.equals(EDatabaseTypeName.JAVADB_EMBEDED.getDisplayName())
                        || dbType.equals(EDatabaseTypeName.JAVADB_DERBYCLIENT.getDisplayName())
                        || dbType.equals(EDatabaseTypeName.JAVADB_DERBYCLIENT.getDisplayName())
                        || dbType.equals(EDatabaseTypeName.JAVADB_JCCJDBC.getDisplayName()) || dbType
                        .equals(EDatabaseTypeName.HSQLDB_IN_PROGRESS.getDisplayName()));
    }

    public static boolean isHsqlInprocess(IMetadataConnection metadataConnection) {
        if (metadataConnection != null) {
            String dbType = metadataConnection.getDbType();
            if (dbType != null && dbType.equals(EDatabaseTypeName.HSQLDB_IN_PROGRESS.getDisplayName())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isHive(DatabaseMetaData metadata) {
        if (metadata != null) {
            try {
                String name = metadata.getDatabaseProductName();
                if (name != null && name.equals(EDatabaseTypeName.HIVE.getDisplayName())) {
                    return true;
                }
            } catch (SQLException e) {
                ExceptionHandler.process(e);
            }
        }
        return false;
    }

    public static Driver getDerbyDriver() {
        return derbyDriver;
    }

    /**
     * Check a package is the one of AS400. return false if it's not.
     * 
     * @param packge
     * @return
     */
    public static boolean isAS400(Package packge) {
        Connection connection = ConnectionHelper.getConnection(packge);
        if (connection instanceof DatabaseConnection) {
            DatabaseConnection dbConnection = (DatabaseConnection) connection;
            return StringUtils.equalsIgnoreCase(dbConnection.getDatabaseType(), EDatabaseTypeName.AS400.getDisplayName());
        }

        return false;
    }

    public static List getConnection(IMetadataConnection metadataBean) {
        return ExtractMetaDataUtils.getConnection(metadataBean.getDbType(), metadataBean.getUrl(), metadataBean.getUsername(),
                metadataBean.getPassword(), metadataBean.getDatabase(), metadataBean.getSchema(), metadataBean.getDriverClass(),
                metadataBean.getDriverJarPath(), metadataBean.getDbVersionString(), metadataBean.getAdditionalParams());
    }
}
