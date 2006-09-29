// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
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

    private InputStream xmlStream;

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
    public ERepositoryObjectType getType() {
        return ERepositoryObjectType.METADATA_CONNECTIONS;
    }

}