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

import java.util.List;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public interface IMetadataConnection extends IMetadata {

    public String getDbType();

    public void setDbType(String dbType);

    public String getDriverClass();

    public void setDriverClass(String driverClass);

    public String getUrl();

    public void setUrl(String url);

    public String getPort();

    public void setPort(String port);

    public String getUsername();

    public void setUsername(String username);

    public String getPassword();

    public void setPassword(String password);

    public String getServerName();

    public void setServerName(String serverName);

    public String getDataSourceName();

    public void setDataSourceName(String dataSourceName);

    public String getFileFieldName();

    public void setFileFieldName(String fileFieldName);

    public String getDatabase();

    public void setDatabase(String database);

    public String getSchema();

    public void setSchema(String schema);

    public String getSqlSyntax();

    public void setSqlSyntax(String sqlSyntax);

    public String getStringQuote();

    public void setStringQuote(String stringQuote);

    public String getNullChar();

    public void setNullChar(String nullChar);

    public List<IMetadataTable> getListTables();

    public void setListTables(List<IMetadataTable> listTables);

}
