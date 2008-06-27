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
package org.talend.core.model.metadata;

import java.io.InputStream;
import java.util.List;

import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.RepositoryObject;

/**
 * DOC cantoine Meta Data Connection. Contains info of conncection. <br/>
 * 
 * $Id$
 * 
 */
public class MetadataConnection extends RepositoryObject implements IMetadataConnection {

    private String dbType;

    private String driverClass;

    private String driverJarPath;

    private String url;

    private String port;

    private String username;

    private String password;

    private String database;

    private String schema;

    private String serverName;

    private String dataSourceName;

    private String fileFieldName;

    private String sqlSyntax;

    private String stringQuote;

    private String nullChar;

    private List<IMetadataTable> listTables;

    private String mapping;

    private String product;

    private InputStream xmlStream;

    private String dbRootPath;

    private String additionalParams;

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.metadata.IMetadataTable#getName()
     */
    public String getDbType() {
        return this.dbType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.metadata.IMetadataTable#setName(java.lang.String)
     */
    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.metadata.IMetadataTable#getName()
     */
    public String getDriverClass() {
        return this.driverClass;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.metadata.IMetadataTable#setName(java.lang.String)
     */
    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.metadata.IMetadataTable#getName()
     */
    public String getUrl() {
        return this.url;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.metadata.IMetadataTable#setName(java.lang.String)
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.metadata.IMetadataTable#getName()
     */
    public String getPort() {
        return this.port;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.metadata.IMetadataTable#setName(java.lang.String)
     */
    public void setPort(String port) {
        this.port = port;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.metadata.IMetadataTable#getName()
     */
    public String getUsername() {
        return this.username;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.metadata.IMetadataTable#setName(java.lang.String)
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.metadata.IMetadataTable#getName()
     */
    public String getPassword() {
        return this.password;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.metadata.IMetadataTable#setName(java.lang.String)
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.metadata.IMetadataTable#getName()
     */
    public String getServerName() {
        return this.serverName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.metadata.IMetadataTable#setName(java.lang.String)
     */
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.metadata.IMetadataTable#getName()
     */
    public String getDataSourceName() {
        return this.dataSourceName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.metadata.IMetadataTable#setName(java.lang.String)
     */
    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.metadata.IMetadataTable#getName()
     */
    public String getFileFieldName() {
        return this.fileFieldName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.metadata.IMetadataTable#setName(java.lang.String)
     */
    public void setFileFieldName(String fileFieldName) {
        this.fileFieldName = fileFieldName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.metadata.IMetadataConnection#getDatabase()
     */
    public String getDatabase() {
        return database;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.metadata.IMetadataConnection#setDatabase(java.lang.String)
     */
    public void setDatabase(String database) {
        this.database = database;

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.metadata.IMetadataTable#getName()
     */
    public String getSchema() {
        return this.schema;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.metadata.IMetadataTable#setName(java.lang.String)
     */
    public void setSchema(String schema) {
        this.schema = schema;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.metadata.IMetadataTable#getName()
     */
    public String getSqlSyntax() {
        return this.sqlSyntax;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.metadata.IMetadataTable#setName(java.lang.String)
     */
    public void setSqlSyntax(String sqlSyntax) {
        this.sqlSyntax = sqlSyntax;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.metadata.IMetadataTable#getName()
     */
    public String getStringQuote() {
        return this.stringQuote;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.metadata.IMetadataTable#setName(java.lang.String)
     */
    public void setStringQuote(String stringQuote) {
        this.stringQuote = stringQuote;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.metadata.IMetadataTable#getName()
     */
    public String getNullChar() {
        return this.nullChar;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.metadata.IMetadataTable#setName(java.lang.String)
     */
    public void setNullChar(String nullChar) {
        this.nullChar = nullChar;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.temp.IXmlSerializable#getXmlStream()
     */
    public InputStream getXmlStream() {
        return xmlStream;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.temp.IXmlSerializable#setXmlStream(java.io.InputStream)
     */
    public void setXmlStream(InputStream xmlStream) {
        this.xmlStream = xmlStream;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.model.metadata.IMetadataTable#getListColumns()
     */
    public List<IMetadataTable> getListTables() {
        return this.listTables;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.model.metadata.IMetadataTable#setListColumns(java.util.Hashtable)
     */
    public void setListTables(List<IMetadataTable> listTables) {
        this.listTables = listTables;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.repository.IRepositoryObject#getType()
     */
    @Override
    public ERepositoryObjectType getType() {
        return ERepositoryObjectType.METADATA_CONNECTIONS;
    }

    public String getMapping() {
        return this.mapping;
    }

    public void setMapping(String mapping) {
        this.mapping = mapping;
    }

    public String getProduct() {
        return this.product;
    }

    public void setProduct(String product) {
        this.product = product;
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
        this.dbRootPath = dbRootPath;
    }

    /**
     * Getter for additionalParams.
     * 
     * @return the additionalParams
     */
    public String getAdditionalParams() {
        return this.additionalParams;
    }

    /**
     * Sets the additionalParams.
     * 
     * @param additionalParams the additionalParams to set
     */
    public void setAdditionalParams(String additionalParams) {
        this.additionalParams = additionalParams;
    }

    /**
     * Getter for driverJarPath.
     * 
     * @return the driverJarPath
     */
    public String getDriverJarPath() {
        return this.driverJarPath;
    }

    /**
     * Sets the driverJarPath.
     * 
     * @param driverJarPath the driverJarPath to set
     */
    public void setDriverJarPath(String driverJarPath) {
        this.driverJarPath = driverJarPath;
    }

}
