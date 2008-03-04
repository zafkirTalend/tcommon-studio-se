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
package org.talend.cwm.management.connection;

import java.util.Properties;

import org.talend.cwm.db.connection.FolderProvider;

/**
 * @author scorreia
 * 
 * Simple container for connection parameters used in the wizard.
 */
public class ConnectionParameters {

    // TODO scorreia add user, version, status?
    private String connectionId;

    private String jdbcUrl;

    private String driverClassName;

    private Properties parameters;

    private String connectionName;

    private String connectionPurpose;

    private String connectionDescription;

    /**
     * The path where to store the connection.
     */
    private FolderProvider folderProvider;

    /**
     * Getter for connectionId.
     * 
     * @return the connectionId
     */
    public String getConnectionId() {
        return this.connectionId;
    }

    /**
     * Sets the connectionId.
     * 
     * @param connectionId the connectionId to set
     */
    public void setConnectionId(String connectionId) {
        this.connectionId = connectionId;
    }

    /**
     * Getter for jdbcUrl.
     * 
     * @return the jdbcUrl
     */
    public String getJdbcUrl() {
        return this.jdbcUrl;
    }

    /**
     * Sets the jdbcUrl.
     * 
     * @param jdbcUrl the jdbcUrl to set
     */
    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    /**
     * Getter for parameters.
     * 
     * @return the parameters
     */
    public Properties getParameters() {
        return this.parameters;
    }

    /**
     * Sets the parameters.
     * 
     * @param parameters the parameters to set
     */
    public void setParameters(Properties parameters) {
        this.parameters = parameters;
    }

    /**
     * Getter for driverClassName.
     * 
     * @return the driverClassName
     */
    public String getDriverClassName() {
        return this.driverClassName;
    }

    /**
     * Sets the driverClassName.
     * 
     * @param driverClassName the driverClassName to set
     */
    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getConnectionName() {
        return this.connectionName;
    }

    public void setConnectionName(String connectionName) {
        this.connectionName = connectionName;
    }

    public String getConnectionPurpose() {
        return this.connectionPurpose;
    }

    public void setConnectionPurpose(String connectionPurpose) {
        this.connectionPurpose = connectionPurpose;
    }

    public String getConnectionDescription() {
        return this.connectionDescription;
    }

    public void setConnectionDescription(String connectionDescription) {
        this.connectionDescription = connectionDescription;
    }

    public FolderProvider getFolderProvider() {
        return this.folderProvider;
    }

    public void setFolderProvider(FolderProvider folderProvider) {
        this.folderProvider = folderProvider;
    }
}
