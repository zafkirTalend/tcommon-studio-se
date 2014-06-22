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
package org.talend.repository.ui.utils;

import java.util.Map;

import org.apache.log4j.Logger;
import org.talend.core.database.EDatabaseTypeName;
import org.talend.core.database.conn.ConnParameterKeys;
import org.talend.core.model.metadata.IMetadataConnection;
import org.talend.core.model.metadata.builder.database.ExtractMetaDataFromDataBase;
import org.talend.core.model.metadata.connection.hive.HiveConnVersionInfo;
import org.talend.core.repository.ConnectionStatus;
import org.talend.core.repository.IDBMetadataProvider;
import org.talend.metadata.managment.ui.i18n.Messages;

/**
 * @author ocarbone
 * 
 */
public class ManagerConnection {

    private static Logger log = Logger.getLogger(ManagerConnection.class);

    private boolean isValide = false;

    String messageException = null;

    // Strings to save

    String dbVersionString;

    String dbTypeString;

    String urlConnectionString;

    String server;

    String username;

    String password;

    String port;

    String sidOrDatabase;

    String datasource;

    String sqlSyntax;

    String strQuote;

    String nullChar;

    String filePath;

    Integer id = null;

    String additionalParams;

    private String schemaOracle;

    private String dbRootPath;

    private String driverClassName;

    private String driverJarPath;

    IMetadataConnection oldConnection;

    /**
     * setValue.
     * 
     * @param id
     * @param dbType
     * @param sidStr
     * @param connectionString
     * @param server
     * @param username
     * @param password
     */
    public void setValue(Integer id, final String dbType, final String url, final String server, final String username,
            final String password, final String sidOrDatabase, final String port, final String file, final String datasource,
            final String schemaOracle, final String additionalParams, final String driverClassName, final String driverJarPath,
            final String dbVersionString) {
        this.id = id;
        this.dbTypeString = dbType;
        this.urlConnectionString = url;
        this.server = server;
        this.username = username;
        this.password = password;
        this.sidOrDatabase = sidOrDatabase;
        this.port = port;
        this.filePath = file;
        this.datasource = datasource;
        this.schemaOracle = schemaOracle;
        this.additionalParams = additionalParams;
        this.driverClassName = driverClassName;
        this.driverJarPath = driverJarPath;
        this.dbVersionString = dbVersionString;

    }

    public void setValueProperties(final String sqlSyntax, final String strQuote, final String nullChar) {
        this.sqlSyntax = sqlSyntax;
        this.strQuote = strQuote;
        this.nullChar = nullChar;
    }

    /**
     * Just for Hive embedded mode to check if a remote metastore db can be connected.
     * Added by Marvin Wang on Oct 19, 2012.
     * @param properties
     * @return
     */
    public boolean checkForHive(Map<String,String> properties){
    	driverJarPath = properties.get(ConnParameterKeys.CONN_PARA_KEY_METASTORE_CONN_DRIVER_JAR);
    	dbTypeString = properties.get("dbTypeString");
    	urlConnectionString = properties.get("urlConnectionString");
    	username = properties.get("username");
    	password = properties.get("password");
    	driverClassName = properties.get("driverClassName");
    	dbVersionString = properties.get("dbVersionString");
    	additionalParams = properties.get("additionalParams");
    	
    	
    	ConnectionStatus testConnection = ExtractMetaDataFromDataBase.testConnection(dbTypeString, urlConnectionString, username,
                password, "", driverClassName, driverJarPath, dbVersionString, additionalParams);
    	isValide = testConnection.getResult();
        messageException = testConnection.getMessageException();
    	return isValide;
    }
    
    /**
     * Check connexion from the fields form.
     * 
     * @return isValide
     */
    public boolean check() {
        messageException = null;
        ConnectionStatus testConnection = null;
        try {
            EDatabaseTypeName type = EDatabaseTypeName.getTypeFromDbType(dbTypeString);
            /* use provider for the databse didn't use JDBC,for example: HBase */
            if (type.isUseProvider()) {
                IDBMetadataProvider extractorToUse = ExtractMetaDataFromDataBase.getProviderByDbType(dbTypeString);
                if (extractorToUse != null) {
                    testConnection = extractorToUse.testConnection(dbTypeString, urlConnectionString, username, password,
                            schemaOracle, server, port, driverClassName, driverJarPath, dbVersionString, additionalParams);
                }
            } else {
                // MOD xqliu 2012-01-05 TDQ-4162
                // get the real schema name
                String schemaName = schemaOracle;
                if (EDatabaseTypeName.TERADATA.equals(type)) {
                    schemaName = sidOrDatabase;
                }
                // test the connection
                testConnection = ExtractMetaDataFromDataBase.testConnection(dbTypeString, urlConnectionString, username,
                        password, schemaName, driverClassName, driverJarPath, dbVersionString, additionalParams);
            }
            isValide = testConnection.getResult();
            messageException = testConnection.getMessageException();
        } catch (Exception e) {
            log.error(Messages.getString("CommonWizard.exception") + "\n" + e.toString()); //$NON-NLS-1$ //$NON-NLS-2$
        }
        return isValide;
    }

    /**
     * DOC cantoine : Check connexion from IMetadataConnection comment. Detailled comment.
     * 
     * @return isValide
     */
    public boolean check(IMetadataConnection metadataConnection, boolean... onlyIfNeeded) {
        messageException = null;

        ConnectionStatus testConnection = null;
        // qli
        // check the same connection.
        // if same. just return true.
        if (metadataConnection == null) {
            return false;
        }
        if (onlyIfNeeded != null && onlyIfNeeded.length > 0 && onlyIfNeeded[0] == true) {
            if (metadataConnection.equals(oldConnection)) {
                return true;
            }
        }
        if (metadataConnection.getDbRootPath() != null && !metadataConnection.getDbRootPath().equals("")) { //$NON-NLS-1$
            setDbRootPath(metadataConnection.getDbRootPath());
        }

        try {
            EDatabaseTypeName type = EDatabaseTypeName.getTypeFromDbType(metadataConnection.getDbType());
            /* use extractor for the databse didn't use JDBC,for example: HBase */
            if (type.isUseProvider()) {
                IDBMetadataProvider extractorToUse = ExtractMetaDataFromDataBase.getProviderByDbType(metadataConnection
                        .getDbType());
                if (extractorToUse != null) {
                    testConnection = extractorToUse.testConnection(metadataConnection.getDbType(), metadataConnection.getUrl(),
                            metadataConnection.getUsername(), metadataConnection.getPassword(), metadataConnection.getSchema(),
                            metadataConnection.getServerName(), metadataConnection.getPort(),
                            metadataConnection.getDriverClass(), metadataConnection.getDriverJarPath(),
                            metadataConnection.getDbVersionString(), metadataConnection.getAdditionalParams());
                }
            } else {
                if (EDatabaseTypeName.HIVE.getDisplayName().equals(metadataConnection.getDbType())) {
                    String key = (String) metadataConnection.getParameter(ConnParameterKeys.CONN_PARA_KEY_HIVE_MODE);
                    if (HiveConnVersionInfo.MODE_EMBEDDED.getKey().equals(key)) {
                        System.setProperty("hive.metastore.local", "false");
                        System.setProperty("hive.metastore.uris", "thrift://" + metadataConnection.getServerName() + ":"
                                + metadataConnection.getPort());
                    }
                }
                testConnection = ExtractMetaDataFromDataBase.testConnection(metadataConnection.getDbType(),
                        metadataConnection.getUrl(), metadataConnection.getUsername(), metadataConnection.getPassword(),
                        metadataConnection.getSchema(), metadataConnection.getDriverClass(),
                        metadataConnection.getDriverJarPath(), metadataConnection.getDbVersionString(),
                        metadataConnection.getAdditionalParams());
            }
            // qli
            // record this metadataConnection as old connection.
            oldConnection = metadataConnection;

            isValide = testConnection.getResult();
            messageException = testConnection.getMessageException();
        } catch (Exception e) {
            log.error(Messages.getString("CommonWizard.exception") + "\n" + e.toString()); //$NON-NLS-1$ //$NON-NLS-2$
        }
        return isValide;
    }

    /**
     * Getter for IsValide.
     * 
     * @return isValide
     */
    public boolean getIsValide() {
        return isValide;
    }

    /**
     * Getter for messageException.
     * 
     * @return the messageException
     */
    public String getMessageException() {
        return this.messageException;
    }

    /**
     * Sets the messageException.
     * 
     * @param messageException the messageException to set
     */
    public void setMessageException(final String messageException) {
        this.messageException = messageException;
    }

    /**
     * Getter for dbRootPath.
     * 
     * @return the dbRootPath
     */
    public String getDbRootPath() {
        return this.dbRootPath;
    }

    /**
     * Sets the dbRootPath.
     * 
     * @param dbRootPath the dbRootPath to set
     */
    public void setDbRootPath(String dbRootPath) {
        if (dbRootPath != null) {
            System.setProperty("derby.system.home", dbRootPath); //$NON-NLS-1$
        }
        this.dbRootPath = dbRootPath;
    }

    public void setUrlConnectionString(String urlConnectionString) {
        this.urlConnectionString = urlConnectionString;
    }

}
