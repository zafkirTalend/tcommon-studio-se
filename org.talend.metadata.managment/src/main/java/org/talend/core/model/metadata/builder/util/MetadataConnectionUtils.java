// ============================================================================
//
// Copyright (C) 2006-2010 Talend Inc. - www.talend.com
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
import org.talend.commons.bridge.ReponsitoryContextBridge;
import org.talend.commons.utils.database.DB2ForZosDataBaseMetadata;
import org.talend.core.database.EDatabaseTypeName;
import org.talend.core.model.metadata.IMetadataConnection;
import org.talend.core.model.metadata.builder.connection.MDMConnection;
import org.talend.core.model.metadata.builder.database.ExtractMetaDataUtils;
import org.talend.core.model.metadata.builder.database.IDriverService;
import org.talend.cwm.constants.SoftwareSystemConstants;
import org.talend.cwm.helper.ConnectionHelper;
import org.talend.cwm.helper.TaggedValueHelper;
import org.talend.cwm.relational.RelationalFactory;
import org.talend.cwm.relational.TdSqlDataType;
import org.talend.cwm.softwaredeployment.SoftwaredeploymentFactory;
import org.talend.cwm.softwaredeployment.TdSoftwareSystem;
import org.talend.mdm.webservice.XtentisBindingStub;
import org.talend.mdm.webservice.XtentisPort;
import org.talend.mdm.webservice.XtentisServiceLocator;
import org.talend.utils.sql.ConnectionUtils;
import org.talend.utils.string.AsciiUtils;
import org.talend.utils.sugars.ReturnCode;
import org.talend.utils.sugars.TypedReturnCode;
import orgomg.cwm.foundation.softwaredeployment.Component;

/**
 * DOC zshen  class global comment. Detailled comment
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
    /**
     * DOC xqliu Comment method "getConnectionMetadata". 2009-07-13 bug 7888.
     * 
     * @param conn
     * @return
     * @throws SQLException
     */

    public static DatabaseMetaData getConnectionMetadata(java.sql.Connection conn) throws SQLException {
        DatabaseMetaData dbMetaData = conn.getMetaData();
        // MOD xqliu 2009-11-17 bug 7888
        if (dbMetaData != null && dbMetaData.getDatabaseProductName() != null
                && dbMetaData.getDatabaseProductName().equals(EDatabaseTypeName.IBMDB2ZOS.getProduct())) {
            dbMetaData = createFakeDatabaseMetaData(conn);
            log.info("IBM DB2 for z/OS");
        }
        // ~
        return dbMetaData;
    }

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
        String driver = metadataBean.getDriverClass();
        String dbUrl = metadataBean.getUrl();
        String password = metadataBean.getPassword();
        String userName = metadataBean.getUsername();
        Properties props = new Properties();
        props.setProperty(TaggedValueHelper.PASSWORD, password);
        props.setProperty(TaggedValueHelper.USER, userName);
        if (StringUtils.isNotBlank(dbUrl) && StringUtils.isNotBlank(driver)) {
            java.sql.Connection sqlConn = null;
            try {
                sqlConn = ConnectionUtils.createConnection(dbUrl, getClassDriver(metadataBean), props);
                ReturnCode varc = ConnectionUtils.isValid(sqlConn);
                if (varc.isOk()) {
                    rc.setObject(sqlConn);
                    rc.setMessage(varc.getMessage());
                    rc.setOk(true);
                }
            } catch (SQLException e) {
                log.error(e, e);
                rc.setMessage(e.getCause() == null ? e.getMessage() : e.getCause().toString());
            } catch (InstantiationException e) {
                log.error(e, e);
            } catch (IllegalAccessException e) {
                log.error(e, e);
            } catch (ClassNotFoundException e) {
                log.error(e, e);
            }

        } else {
            if (!StringUtils.isNotBlank(dbUrl)) {
                rc.setMessage("the driver of connection parameter can not be null");
            } else {
                rc.setMessage("the url of connection parameter can not be null");
            }
        }
        return rc;

    }

    public static TdSoftwareSystem getSoftwareSystem(java.sql.Connection connection) throws SQLException {
        // MOD xqliu 2009-07-13 bug 7888

        DatabaseMetaData databaseMetadata = getConnectionMetadata(connection);
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
        DatabaseMetaData connectionMetadata = getConnectionMetadata(connection);
        if (connectionMetadata.getDriverName() != null
                && connectionMetadata.getDriverName().toLowerCase().startsWith(DatabaseConstant.ODBC_DRIVER_NAME)
                && connectionMetadata.getDatabaseProductName() != null
                && connectionMetadata.getDatabaseProductName().toLowerCase().indexOf(DatabaseConstant.POSTGRESQL_PRODUCT_NAME) > -1) {
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
        DatabaseMetaData connectionMetadata = getConnectionMetadata(connection);
        if (connectionMetadata.getDriverName() != null && connectionMetadata.getDatabaseProductName() != null) {
            for (String keyString : getSybaseDBProductsName()) {
                if (keyString.equals(connectionMetadata.getDatabaseProductName().trim())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isMssql(java.sql.Connection connection) throws SQLException {
        DatabaseMetaData connectionMetadata = getConnectionMetadata(connection);
        if (connectionMetadata.getDriverName() != null && connectionMetadata.getDatabaseProductName() != null) {
            if (EDataBaseType.Microsoft_SQL_Server.getProductName().equals(connectionMetadata.getDatabaseProductName().trim())) {
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
            sybaseDBProductsNames.add("Adaptive Server Enterprise ");
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
        Driver driver = DRIVER_CACHE.get(driverClassName);
        if (driver != null) {
            return driver;
        }


            IExtension extension = Platform.getExtensionRegistry().getExtension(DRIVER_EXTENSION_POINT_ID,
                    TOP_DRIVER_EXTENSION_ID);
            if (extension != null) {
                IConfigurationElement[] configurationElement = extension.getConfigurationElements();
                for (IConfigurationElement ele : configurationElement) {
                    try {
                        IDriverService driverService = (IDriverService) ele.createExecutableExtension("class");
                        driver = driverService.getDriver(metadataBean);
                } catch (Exception e) {
                        log.error(e, e);
                    }
                }
            // top
            if (ReponsitoryContextBridge.isDefautProject()) {
                // waiting for return
            } else {
                // tdqee
                List<?> connList = null;
                try {
                    connList = ExtractMetaDataUtils.getConnection(metadataBean.getDbType(), metadataBean.getUrl(),
                        metadataBean.getUsername(), metadataBean.getPassword(), metadataBean.getDatabase(),
                        metadataBean.getSchema(), driverClassName, metadataBean.getDriverJarPath(),
                        metadataBean.getDbVersionString());
                } catch (Exception e) {
                    // do nothing
                }
                if (connList != null && connList.size() > 1) {
                    driver = (Driver) connList.get(1);
                }

            }
        } else {
            // tos
            try {
            List<?> connList = ExtractMetaDataUtils.getConnection(metadataBean.getDbType(), metadataBean.getUrl(),
                    metadataBean.getUsername(), metadataBean.getPassword(), metadataBean.getDatabase(), metadataBean.getSchema(),
                    driverClassName, metadataBean.getDriverJarPath(), metadataBean.getDbVersionString());
            if (connList.size() > 1) {
                driver = (Driver) connList.get(1);
            }
            } catch (Exception e) {
                log.error(e, e);
            }
        }
        // MOD mzhao 2009-06-05,Bug 7571 Get driver from catch first, if not
        // exist then get a new instance.
        if (driver != null) {
            DRIVER_CACHE.put(driverClassName, driver);
        }
        return driver;
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
        case MySQL:
            sqlStr = "SELECT TABLE_COMMENT FROM information_schema.TABLES WHERE TABLE_NAME='" + tableName + "'"; //$NON-NLS-1$ //$NON-NLS-2$
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
                dataProvider.getUsername(),
                dataProvider.getPassword());
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



}
