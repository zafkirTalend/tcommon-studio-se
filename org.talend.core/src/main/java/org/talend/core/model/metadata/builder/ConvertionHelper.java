// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006-2007 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
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

package org.talend.core.model.metadata.builder;

import java.util.ArrayList;
import java.util.List;

import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataConnection;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.builder.connection.DatabaseConnection;
import org.talend.core.model.metadata.builder.connection.MetadataColumn;
import org.talend.core.model.metadata.builder.connection.MetadataTable;

/**
 * 
 */
public final class ConvertionHelper {

    /**
     * This method doesn't perform a deep copy. DOC tguiu Comment method "convert".
     * 
     * @param connection
     * @return
     */
    public static IMetadataConnection convert(DatabaseConnection connection) {
        if (connection == null) {
            return null;
        }
        IMetadataConnection result = new org.talend.core.model.metadata.MetadataConnection();
        result.setDescription(connection.getComment());
        result.setDatabase(connection.getSID());
        result.setDataSourceName(connection.getDatasourceName());
        result.setDbType(connection.getDatabaseType());
        result.setDriverClass(connection.getDriverClass());
        result.setFileFieldName(connection.getFileFieldName());
        result.setId(connection.getId());
        result.setLabel(connection.getLabel());
        result.setNullChar(connection.getNullChar());
        result.setPassword(connection.getPassword());
        result.setPort(connection.getPort());
        result.setSchema(connection.getSchema());
        result.setServerName(connection.getServerName());
        result.setSqlSyntax(connection.getSqlSynthax());
        result.setStringQuote(connection.getStringQuote());
        result.setUrl(connection.getURL());
        result.setUsername(connection.getUsername());
        result.setMapping(connection.getDbmsId());
        result.setProduct(connection.getProductId());
        return result;
    }

    public static IMetadataTable convert(MetadataTable old) {
        IMetadataTable result = new org.talend.core.model.metadata.MetadataTable();
        result.setDescription(old.getComment());
        result.setId(old.getId());
        result.setLabel(old.getLabel());
        result.setTableName(old.getSourceName());
        List<IMetadataColumn> columns = new ArrayList<IMetadataColumn>(old.getColumns().size());
        for (Object o : old.getColumns()) {
            MetadataColumn column = (MetadataColumn) o;
            IMetadataColumn newColumn = new org.talend.core.model.metadata.MetadataColumn();
            columns.add(newColumn);
            newColumn.setComment(column.getComment());
            newColumn.setDefault(column.getDefaultValue());
            newColumn.setKey(column.isKey());
            newColumn.setLabel(column.getLabel());
            newColumn.setPattern(column.getPattern());
            newColumn.setLength(column.getLength());
            newColumn.setNullable(column.isNullable());
            newColumn.setPrecision(column.getPrecision());
            newColumn.setTalendType(column.getTalendType());
            newColumn.setType(column.getSourceType());
        }
        result.setListColumns(columns);
        // result.setVersion();
        return result;
    }

    private ConvertionHelper() {
    }

}
