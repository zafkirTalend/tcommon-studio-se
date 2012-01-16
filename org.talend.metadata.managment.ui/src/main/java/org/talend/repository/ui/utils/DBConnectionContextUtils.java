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
package org.talend.repository.ui.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.Path;
import org.talend.commons.bridge.ReponsitoryContextBridge;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.commons.ui.utils.PathUtils;
import org.talend.commons.utils.PasswordEncryptUtil;
import org.talend.core.database.EDatabaseTypeName;
import org.talend.core.database.conn.DatabaseConnStrUtil;
import org.talend.core.database.conn.template.EDatabaseConnTemplate;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.language.LanguageManager;
import org.talend.core.model.metadata.Dbms;
import org.talend.core.model.metadata.IMetadataConnection;
import org.talend.core.model.metadata.MetadataTalendType;
import org.talend.core.model.metadata.builder.connection.ConnectionFactory;
import org.talend.core.model.metadata.builder.connection.DatabaseConnection;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.IContextParameter;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.core.model.utils.ContextParameterUtils;
import org.talend.core.utils.TalendQuoteUtils;
import org.talend.cwm.helper.ConnectionHelper;
import org.talend.designer.core.model.utils.emf.talendfile.ContextType;
import org.talend.repository.model.IConnParamName;
import orgomg.cwm.objectmodel.core.ModelElement;

/**
 * ggu class global comment. Detailled comment
 */
public final class DBConnectionContextUtils {

    private static final ECodeLanguage LANGUAGE = LanguageManager.getCurrentLanguage();

    /**
     * 
     */
    public enum EDBParamName implements IConnParamName {
        Login,
        Password,
        // hshen
        JdbcUrl,
        DriverJar,
        ClassName,
        MappingFile,
        Port,
        Server,
        Schema,
        AdditionalParams,
        Datasource,
        DBRootPath,
        File,
        //
        Sid,
        Database,
        ServiceName,
    }

    static List<IContextParameter> getDBVariables(String prefixName, DatabaseConnection conn, Set<IConnParamName> paramSet) {
        if (conn == null || prefixName == null || paramSet == null || paramSet.isEmpty()) {
            return Collections.emptyList();
        }

        List<IContextParameter> varList = new ArrayList<IContextParameter>();
        prefixName = prefixName + ConnectionContextHelper.LINE;
        String paramName = null;
        for (IConnParamName param : paramSet) {
            if (param instanceof EDBParamName) {
                EDBParamName dbParam = (EDBParamName) param;
                paramName = prefixName + dbParam;
                switch (dbParam) {
                case AdditionalParams:
                    ConnectionContextHelper.createParameters(varList, paramName, conn.getAdditionalParams());
                    break;
                case Datasource:
                    ConnectionContextHelper.createParameters(varList, paramName, conn.getDatasourceName());
                    break;
                case DBRootPath:
                    ConnectionContextHelper
                            .createParameters(varList, paramName, conn.getDBRootPath(), JavaTypesManager.DIRECTORY);
                    break;
                case File:
                    ConnectionContextHelper.createParameters(varList, paramName, conn.getFileFieldName(), JavaTypesManager.FILE);
                    break;
                case Password:
                    ConnectionContextHelper.createParameters(varList, paramName, conn.getPassword(), JavaTypesManager.PASSWORD);
                    break;
                // hshen
                case JdbcUrl:

                    String url = conn.getURL();
                    String h2Prefix = "jdbc:h2:";
                    if (url.startsWith(h2Prefix)) {
                        String path = url.substring(h2Prefix.length(), url.length());
                        path = PathUtils.getPortablePath(path);
                        url = h2Prefix + path;
                    }

                    ConnectionContextHelper.createParameters(varList, paramName, url);
                    break;
                case DriverJar:
                    // fix for 23031
                    String path = conn.getDriverJarPath();
                    PathUtils.getOSPath(path);
                    if (path.contains("\\")) {
                        Path p = new Path(path);
                        String newPath = p.getDevice();
                        for (int i = 0; i < p.segmentCount(); i++) {
                            newPath = newPath + "\\\\" + p.segment(i);
                        }
                        path = newPath;
                    }
                    ConnectionContextHelper.createParameters(varList, paramName, path);
                    break;
                case MappingFile:
                    ConnectionContextHelper.createParameters(varList, paramName, conn.getDbmsId());
                    break;
                case ClassName:
                    ConnectionContextHelper.createParameters(varList, paramName, conn.getDriverClass());
                    break;
                case Port:
                    ConnectionContextHelper.createParameters(varList, paramName, conn.getPort(), JavaTypesManager.STRING);
                    break;
                case Schema:
                    if (conn.getProductId().equals(EDatabaseTypeName.ORACLEFORSID.getProduct())) {
                        String schema = conn.getUiSchema();
                        if (schema != null) {
                            conn.setUiSchema(schema.toUpperCase());
                        }
                    }
                    ConnectionContextHelper.createParameters(varList, paramName, conn.getUiSchema());
                    break;
                case Server:
                    ConnectionContextHelper.createParameters(varList, paramName, conn.getServerName());
                    break;
                case Sid:
                case Database:
                case ServiceName:
                    ConnectionContextHelper.createParameters(varList, paramName, conn.getSID());
                    break;
                case Login:
                    ConnectionContextHelper.createParameters(varList, paramName, conn.getUsername());
                    break;
                default:
                }
            }
        }

        return varList;
    }

    static void setPropertiesForContextMode(String prefixName, DatabaseConnection conn, Set<IConnParamName> paramSet) {
        if (conn == null || prefixName == null || paramSet == null || paramSet.isEmpty()) {
            return;
        }
        prefixName = prefixName + ConnectionContextHelper.LINE;
        String paramName = null;
        for (IConnParamName param : paramSet) {
            if (param instanceof EDBParamName) {
                EDBParamName dbParam = (EDBParamName) param;
                paramName = prefixName + dbParam;
                switch (dbParam) {
                case AdditionalParams:
                    conn.setAdditionalParams(ContextParameterUtils.getNewScriptCode(paramName, LANGUAGE));
                    break;
                case Datasource:
                    conn.setDatasourceName(ContextParameterUtils.getNewScriptCode(paramName, LANGUAGE));
                    break;
                case DBRootPath:
                    conn.setDBRootPath(ContextParameterUtils.getNewScriptCode(paramName, LANGUAGE));
                    break;
                case File:
                    conn.setFileFieldName(ContextParameterUtils.getNewScriptCode(paramName, LANGUAGE));
                    break;
                case Password:
                    conn.setPassword(ContextParameterUtils.getNewScriptCode(paramName, LANGUAGE));
                    break;
                // hshen
                case JdbcUrl:
                    conn.setURL(ContextParameterUtils.getNewScriptCode(paramName, LANGUAGE));
                    break;
                case DriverJar:
                    conn.setDriverJarPath(ContextParameterUtils.getNewScriptCode(paramName, LANGUAGE));
                    break;
                case MappingFile:
                    conn.setDbmsId(ContextParameterUtils.getNewScriptCode(paramName, LANGUAGE));
                    break;
                case ClassName:
                    conn.setDriverClass(ContextParameterUtils.getNewScriptCode(paramName, LANGUAGE));
                    break;
                case Port:
                    conn.setPort(ContextParameterUtils.getNewScriptCode(paramName, LANGUAGE));
                    break;
                case Schema:
                    conn.setUiSchema(ContextParameterUtils.getNewScriptCode(paramName, LANGUAGE));
                    break;
                case Server:
                    conn.setServerName(ContextParameterUtils.getNewScriptCode(paramName, LANGUAGE));
                    break;
                case Sid:
                case Database:
                case ServiceName:
                    conn.setSID(ContextParameterUtils.getNewScriptCode(paramName, LANGUAGE));
                    break;
                case Login:
                    conn.setUsername(ContextParameterUtils.getNewScriptCode(paramName, LANGUAGE));
                    break;
                default:
                }
            }
        }

    }

    /**
     * 
     * ggu Comment method "setManagerConnectionValues".
     * 
     * set the ManagerConnection parameter and return the url string connection.
     */
    public static String setManagerConnectionValues(ManagerConnection managerConnection, ConnectionItem connectionItem,
            final String dbType, final int dbTypeIndex) {
        if (managerConnection == null || connectionItem == null || dbType == null || dbTypeIndex < 0) {
            return null;
        }
        DatabaseConnection dbConn = (DatabaseConnection) connectionItem.getConnection();

        ContextType contextType = ConnectionContextHelper.getContextTypeForContextMode(dbConn);

        String server = ConnectionContextHelper.getOriginalValue(contextType, dbConn.getServerName());
        String username = ConnectionContextHelper.getOriginalValue(contextType, dbConn.getUsername());
        String password = ConnectionContextHelper.getOriginalValue(contextType, dbConn.getPassword());
        String port = ConnectionContextHelper.getOriginalValue(contextType, dbConn.getPort());
        String sidOrDatabase = ConnectionContextHelper.getOriginalValue(contextType, dbConn.getSID());
        String datasource = ConnectionContextHelper.getOriginalValue(contextType, dbConn.getDatasourceName());
        String filePath = ConnectionContextHelper.getOriginalValue(contextType, dbConn.getFileFieldName());
        String schemaOracle = ConnectionContextHelper.getOriginalValue(contextType, dbConn.getUiSchema());
        String dbRootPath = ConnectionContextHelper.getOriginalValue(contextType, dbConn.getDBRootPath());
        String additionParam = ConnectionContextHelper.getOriginalValue(contextType, dbConn.getAdditionalParams());
        String driverClassName = ConnectionContextHelper.getOriginalValue(contextType, dbConn.getDriverClass());
        String driverJarPath = ConnectionContextHelper.getOriginalValue(contextType, dbConn.getDriverJarPath());
        String dbVersionString = ConnectionContextHelper.getOriginalValue(contextType, dbConn.getDbVersionString());

        filePath = TalendQuoteUtils.removeQuotes(filePath);
        dbRootPath = TalendQuoteUtils.removeQuotes(dbRootPath);
        // url

        String urlConnection;
        if (dbConn.getDatabaseType().equals(EDatabaseConnTemplate.GENERAL_JDBC.getDBDisplayName())) {
            urlConnection = ConnectionContextHelper.getOriginalValue(contextType, dbConn.getURL());
        } else {
            urlConnection = DatabaseConnStrUtil.getURLString(dbConn.getDatabaseType(), dbConn.getDbVersionString(), server,
                    username, password, port, sidOrDatabase, filePath.toLowerCase(), datasource, dbRootPath, additionParam);
        }

        if (dbConn.getProductId().equals(EDatabaseTypeName.ORACLEFORSID.getProduct())) {
            schemaOracle = schemaOracle.toUpperCase();
        }
        // set the value
        managerConnection.setValue(0, dbType, urlConnection, server, username, password, sidOrDatabase, port, filePath,
                datasource, schemaOracle, additionParam, driverClassName, driverJarPath, dbVersionString);
        managerConnection.setDbRootPath(dbRootPath);

        return urlConnection;
    }

    /**
     * 
     * ggu Comment method "getUrlConnectionString".
     * 
     * if display is false, the string connection will be returned by default context.
     */
    public static String getUrlConnectionString(ConnectionItem connectionItem, boolean defaultContext) {
        if (connectionItem == null) {
            return null;
        }
        DatabaseConnection dbConn = (DatabaseConnection) connectionItem.getConnection();
        ContextType contextType = ConnectionContextHelper.getContextTypeForContextMode(dbConn, defaultContext);

        String server = ConnectionContextHelper.getOriginalValue(contextType, dbConn.getServerName());
        String username = ConnectionContextHelper.getOriginalValue(contextType, dbConn.getUsername());
        String password = ConnectionContextHelper.getOriginalValue(contextType, dbConn.getPassword());
        String port = ConnectionContextHelper.getOriginalValue(contextType, dbConn.getPort());
        String sidOrDatabase = ConnectionContextHelper.getOriginalValue(contextType, dbConn.getSID());
        String datasource = ConnectionContextHelper.getOriginalValue(contextType, dbConn.getDatasourceName());
        String filePath = ConnectionContextHelper.getOriginalValue(contextType, dbConn.getFileFieldName());
        String schemaOracle = ConnectionContextHelper.getOriginalValue(contextType, dbConn.getUiSchema());
        String dbRootPath = ConnectionContextHelper.getOriginalValue(contextType, dbConn.getDBRootPath());
        String additionParam = ConnectionContextHelper.getOriginalValue(contextType, dbConn.getAdditionalParams());
        // hshen
        String jdbcUrl = ConnectionContextHelper.getOriginalValue(contextType, dbConn.getURL());
        String driverJar = ConnectionContextHelper.getOriginalValue(contextType, dbConn.getDriverJarPath());
        String className = ConnectionContextHelper.getOriginalValue(contextType, dbConn.getDbmsId());
        String mappingFile = ConnectionContextHelper.getOriginalValue(contextType, dbConn.getFileFieldName());

        filePath = TalendQuoteUtils.removeQuotes(filePath);
        dbRootPath = TalendQuoteUtils.removeQuotes(dbRootPath);

        String newUrl = DatabaseConnStrUtil.getURLString(dbConn.getDatabaseType(), dbConn.getDbVersionString(), server, username,
                password, port, sidOrDatabase, filePath.toLowerCase(), datasource, dbRootPath, additionParam, jdbcUrl, driverJar,
                className, mappingFile);
        return newUrl;

    }

    /**
     * 
     * ggu Comment method "cloneOriginalValueConnection".
     * 
     * perhaps, if connection is in context mode, will open dialog to choose context sets.
     */
    public static DatabaseConnection cloneOriginalValueConnection(DatabaseConnection dbConn) {
        return cloneOriginalValueConnection(dbConn, false, null);
    }

    /**
     * 
     * ggu Comment method "cloneOriginalValueConnection".
     * 
     * only clone the properties of connection.
     * 
     * @param selectedContext
     */
    public static DatabaseConnection cloneOriginalValueConnection(DatabaseConnection dbConn, boolean defaultContext,
            String selectedContext) {
        if (dbConn == null) {
            return null;
        }
        ContextType contextType = ConnectionContextHelper.getContextTypeForContextMode(null, dbConn, selectedContext,
                defaultContext);
        DatabaseConnection cloneConn = ConnectionFactory.eINSTANCE.createDatabaseConnection();
        // get values
        String server = ConnectionContextHelper.getOriginalValue(contextType, dbConn.getServerName());
        String username = ConnectionContextHelper.getOriginalValue(contextType, dbConn.getUsername());
        String password = ConnectionContextHelper.getOriginalValue(contextType, dbConn.getRawPassword());
        String port = ConnectionContextHelper.getOriginalValue(contextType, dbConn.getPort());
        String sidOrDatabase = ConnectionContextHelper.getOriginalValue(contextType, dbConn.getSID());
        String datasource = ConnectionContextHelper.getOriginalValue(contextType, dbConn.getDatasourceName());
        String filePath = ConnectionContextHelper.getOriginalValue(contextType, dbConn.getFileFieldName());
        String schemaOracle = ConnectionContextHelper.getOriginalValue(contextType, dbConn.getUiSchema());
        String dbRootPath = ConnectionContextHelper.getOriginalValue(contextType, dbConn.getDBRootPath());
        String additionParam = ConnectionContextHelper.getOriginalValue(contextType, dbConn.getAdditionalParams());
        String url = ConnectionContextHelper.getOriginalValue(contextType, dbConn.getURL());
        String className = ConnectionContextHelper.getOriginalValue(contextType, dbConn.getDriverClass());
        String jarPath = ConnectionContextHelper.getOriginalValue(contextType, dbConn.getDriverJarPath());

        // hyWang add for bug 0007252
        String dbmsID = ConnectionContextHelper.getOriginalValue(contextType, dbConn.getDbmsId());

        filePath = TalendQuoteUtils.removeQuotes(filePath);
        dbRootPath = TalendQuoteUtils.removeQuotes(dbRootPath);
        cloneConn.setAdditionalParams(additionParam);
        cloneConn.setDatasourceName(datasource);
        cloneConn.setDBRootPath(dbRootPath);
        cloneConn.setFileFieldName(filePath);

        // if use context
        if (contextType != null) {
            String encryptedPassword = null;
            try {
                encryptedPassword = PasswordEncryptUtil.encryptPassword(password);
            } catch (Exception e) {
                ExceptionHandler.process(e);
            }
            cloneConn.setPassword(encryptedPassword);
        } else {
            cloneConn.setPassword(password);
        }

        cloneConn.setPort(port);
        cloneConn.setUiSchema(schemaOracle);
        cloneConn.setServerName(server);
        cloneConn.setSID(sidOrDatabase);
        cloneConn.setUsername(username);
        cloneConn.setDriverJarPath(jarPath);

        cloneConn.setComment(dbConn.getComment());
        cloneConn.setDatabaseType(dbConn.getDatabaseType());
        cloneConn.setDbmsId(dbmsID);
        cloneConn.setDivergency(dbConn.isDivergency());
        cloneConn.setDbVersionString(dbConn.getDbVersionString());
        cloneConn.setId(dbConn.getId());
        cloneConn.setLabel(dbConn.getLabel());
        cloneConn.setNullChar(dbConn.getNullChar());
        cloneConn.setProductId(dbConn.getProductId());
        cloneConn.setSqlSynthax(dbConn.getSqlSynthax());
        cloneConn.setStandardSQL(dbConn.isStandardSQL());
        cloneConn.setStringQuote(dbConn.getStringQuote());
        cloneConn.setSynchronised(dbConn.isSynchronised());
        cloneConn.setSystemSQL(dbConn.isSystemSQL());
        cloneConn.setVersion(dbConn.getVersion());
        cloneConn.setReadOnly(dbConn.isReadOnly());
        cloneConn.setDriverClass(className);
        cloneConn.setName(dbConn.getName());
        if (dbConn.isSetSQLMode()) {
            cloneConn.setSQLMode(dbConn.isSQLMode());
        } else {
            // set true by default as it's only used actually for teradata.
            // should be modified if default value is changed later.
            cloneConn.setSQLMode(true);
        }

        // cloneConn.setProperties(dbConn.getProperties());
        // cloneConn.setCdcConns(dbConn.getCdcConns());
        // cloneConn.setQueries(dbConn.getQueries());
        // cloneConn.getTables().addAll(dbConn.getTables());
        /*
         * mustn't be set, is flag for method convert in class ConvertionHelper.
         * 
         * working for sql builder especially.
         */
        // cloneConn.setContextId(dbConn.getContextId());
        // cloneConn.setContextMode(dbConn.isContextMode());
        if (dbConn.getURL() != null && !dbConn.getURL().equals("")) { //$NON-NLS-1$
            // for general db, url is given directly.
            cloneConn.setURL(url);
        } else {
            String newURL = DatabaseConnStrUtil.getURLString(cloneConn.getDatabaseType(), dbConn.getDbVersionString(), server,
                    username, password, port, sidOrDatabase, filePath.toLowerCase(), datasource, dbRootPath, additionParam);
            cloneConn.setURL(newURL);
        }

        return cloneConn;
    }

    static void revertPropertiesForContextMode(DatabaseConnection conn, ContextType contextType) {
        if (conn == null || contextType == null) {
            return;
        }
        String server = ConnectionContextHelper.getOriginalValue(contextType, conn.getServerName());
        String username = ConnectionContextHelper.getOriginalValue(contextType, conn.getUsername());
        String password = ConnectionContextHelper.getOriginalValue(contextType, conn.getPassword());
        String port = ConnectionContextHelper.getOriginalValue(contextType, conn.getPort());
        String sidOrDatabase = ConnectionContextHelper.getOriginalValue(contextType, conn.getSID());
        String datasource = ConnectionContextHelper.getOriginalValue(contextType, conn.getDatasourceName());
        String filePath = ConnectionContextHelper.getOriginalValue(contextType, conn.getFileFieldName());
        String schemaOracle = ConnectionContextHelper.getOriginalValue(contextType, conn.getUiSchema());
        String dbRootPath = ConnectionContextHelper.getOriginalValue(contextType, conn.getDBRootPath());
        String additionParam = ConnectionContextHelper.getOriginalValue(contextType, conn.getAdditionalParams());
        // hshen
        String jdbcUrl = ConnectionContextHelper.getOriginalValue(contextType, conn.getURL());
        String driverJar = ConnectionContextHelper.getOriginalValue(contextType, conn.getDriverJarPath());
        String className = ConnectionContextHelper.getOriginalValue(contextType, conn.getDriverClass());
        String mappingFile = ConnectionContextHelper.getOriginalValue(contextType, conn.getDbmsId());

        filePath = TalendQuoteUtils.removeQuotes(filePath);
        dbRootPath = TalendQuoteUtils.removeQuotes(dbRootPath);
        conn.setAdditionalParams(additionParam);
        conn.setDatasourceName(datasource);
        conn.setDBRootPath(dbRootPath);
        conn.setFileFieldName(filePath);
        try {
            conn.setPassword(PasswordEncryptUtil.encryptPassword(password));
        } catch (Exception e) {
            ExceptionHandler.process(e);
        }
        conn.setPort(port);
        conn.setUiSchema(schemaOracle);
        conn.setServerName(server);

        // fix for 19315 , catalog name is exported to context model in some previous version
        if (conn.getDataPackage() != null) {
            for (orgomg.cwm.objectmodel.core.Package dataPackage : conn.getDataPackage()) {
                if (dataPackage instanceof ModelElement) {
                    ModelElement element = (ModelElement) dataPackage;
                    if (conn.getSID() != null && conn.getSID().equals(element.getName())) {
                        element.setName(sidOrDatabase);
                    }
                }
            }
        }

        conn.setSID(sidOrDatabase);
        conn.setUsername(username);
        // hshen
        conn.setURL(jdbcUrl);
        conn.setDriverJarPath(driverJar);
        conn.setDriverClass(className);
        conn.setDbmsId(mappingFile);

    }

    public static void setMetadataConnectionParameter(DatabaseConnection conn, IMetadataConnection metadataConnection) {
        if (conn == null || metadataConnection == null) {
            return;
        }
        ContextType contextType = ConnectionContextHelper.getContextTypeForContextMode(conn);

        // driverPath
        metadataConnection.setDriverJarPath(ConnectionContextHelper.getOriginalValue(contextType, conn.getDriverJarPath()));

        // set dbType
        metadataConnection.setDbType(ConnectionContextHelper.getOriginalValue(contextType, conn.getDatabaseType()));
        // set product(ProductId) and Schema(UISchema)
        EDatabaseTypeName edatabasetypeInstance = EDatabaseTypeName.getTypeFromDisplayName(ConnectionContextHelper
                .getOriginalValue(contextType, conn.getDatabaseType()));
        String product = edatabasetypeInstance.getProduct();
        metadataConnection.setProduct(product);
        // set mapping(DbmsId)
        if (!ReponsitoryContextBridge.isDefautProject()) {
            Dbms defaultDbmsFromProduct = MetadataTalendType.getDefaultDbmsFromProduct(product);
            if (defaultDbmsFromProduct != null) {
                String mapping = defaultDbmsFromProduct.getId();
                metadataConnection.setMapping(mapping);
            }
        }
        // set dbVersionString
        metadataConnection.setDbVersionString(ConnectionContextHelper.getOriginalValue(contextType, conn.getDbVersionString()));

        // filePath
        metadataConnection.setFileFieldName(ConnectionContextHelper.getOriginalValue(contextType, conn.getFileFieldName()));
        // jdbcUrl
        metadataConnection.setUrl(ConnectionContextHelper.getOriginalValue(contextType, conn.getURL()));
        // aDDParameter
        metadataConnection.setAdditionalParams(ConnectionContextHelper.getOriginalValue(contextType, conn.getAdditionalParams()));
        // driverClassName
        metadataConnection.setDriverClass(ConnectionContextHelper.getOriginalValue(contextType, conn.getDriverClass()));
        // host
        metadataConnection.setServerName(ConnectionContextHelper.getOriginalValue(contextType, conn.getServerName()));
        // port
        metadataConnection.setPort(ConnectionContextHelper.getOriginalValue(contextType, conn.getPort()));
        // dbName
        metadataConnection.setDatabase(ConnectionContextHelper.getOriginalValue(contextType, conn.getSID()));
        // otherParameter
        metadataConnection.setOtherParameter(ConnectionContextHelper.getOriginalValue(contextType,
                ConnectionHelper.getOtherParameter(conn)));
        // password
        metadataConnection.setPassword(ConnectionContextHelper.getOriginalValue(contextType, ConnectionHelper.getPassword(conn)));
        // user
        metadataConnection.setUsername(ConnectionContextHelper.getOriginalValue(contextType, conn.getUsername()));
        // dbName
        metadataConnection.setDataSourceName(ConnectionContextHelper.getOriginalValue(contextType, conn.getDatasourceName()));
        // schema
        metadataConnection.setSchema(conn.getUiSchema());
        // dbmsId
        if (metadataConnection.getMapping() == null) {
            metadataConnection.setMapping(ConnectionContextHelper.getOriginalValue(contextType, conn.getDbmsId()));
        }

    }
    /**
     * 
     * DOC zshen Comment method "setDatabaseConnectionParameter".
     * 
     * @param conn
     * @param metadataConnection
     * 
     * set parameter from metadataConnection to DatabaseConnection
     */
    public static void setDatabaseConnectionParameter(DatabaseConnection conn, IMetadataConnection metadataConnection) {
        if (conn == null || metadataConnection == null) {
            return;
        }
        ContextType contextType = ConnectionContextHelper.getContextTypeForContextMode(conn);

        // driverPath
        conn.setDriverJarPath(metadataConnection.getDriverJarPath());

        // set dbType
        conn.setDatabaseType(metadataConnection.getDbType());

        conn.setProductId(metadataConnection.getProduct());

        conn.setDbmsId(metadataConnection.getMapping());

        // set dbVersionString
        conn.setDbVersionString(metadataConnection.getDbVersionString());

        // filePath
        conn.setFileFieldName(metadataConnection.getFileFieldName());

        // jdbcUrl
        conn.setURL(metadataConnection.getUrl());

        // aDDParameter
        conn.setAdditionalParams(metadataConnection.getAdditionalParams());

        // driverClassName
        conn.setDriverClass(metadataConnection.getDriverClass());

        // host
        conn.setServerName(metadataConnection.getServerName());

        // port
        conn.setPort(metadataConnection.getPort());

        // dbName
        conn.setSID(metadataConnection.getDatabase());

        // otherParameter
        ConnectionHelper.setOtherParameter(metadataConnection.getOtherParameter(), conn);

        // password
        ConnectionHelper.setPassword(conn, metadataConnection.getPassword());

        // user
        conn.setUsername(metadataConnection.getUsername());

        // dbName
        conn.setDatasourceName(metadataConnection.getDataSourceName());

        // schema
        String uischema = metadataConnection.getUiSchema() == null ? metadataConnection.getSchema() : metadataConnection
                .getUiSchema();
        conn.setUiSchema(uischema);

        conn.setContextMode(metadataConnection.isContentModel());
        conn.setContextId(metadataConnection.getContextId());
        conn.setContextName(metadataConnection.getContextName());
    }
}
