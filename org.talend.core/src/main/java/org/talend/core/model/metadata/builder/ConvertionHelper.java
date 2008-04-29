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
package org.talend.core.model.metadata.builder;

import java.util.ArrayList;
import java.util.List;

import org.talend.core.CorePlugin;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataConnection;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.builder.connection.ConnectionFactory;
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
    public static IMetadataConnection convert(DatabaseConnection sourceConnection) {
        if (sourceConnection == null) {
            return null;
        }
        // if sourceConnection is not context mode, will be same as before.
        DatabaseConnection connection = null;
        DatabaseConnection originalValueConnection = CorePlugin.getDefault().getRepositoryService().cloneOriginalValueConnection(
                sourceConnection);
        if (originalValueConnection == null) {
            connection = sourceConnection;
        } else {
            connection = originalValueConnection;
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
        result.setAdditionalParams(connection.getAdditionalParams());
        result.setUsername(connection.getUsername());
        result.setMapping(connection.getDbmsId());
        result.setProduct(connection.getProductId());
        result.setDbRootPath(connection.getDBRootPath());
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
            if (column.getLength() < 0) {
                newColumn.setLength(null);
            } else {
                newColumn.setLength(column.getLength());
            }
            newColumn.setNullable(column.isNullable());
            if (column.getPrecision() < 0) {
                newColumn.setPrecision(null);
            } else {
                newColumn.setPrecision(column.getPrecision());
            }
            newColumn.setTalendType(column.getTalendType());
            newColumn.setType(column.getSourceType());
            if (column.getOriginalField() == null || column.getOriginalField().equals("")) {
                newColumn.setOriginalDbColumnName(column.getLabel());
            } else {
                newColumn.setOriginalDbColumnName(column.getOriginalField());
            }
        }
        result.setListColumns(columns);
        return result;
    }

    private ConvertionHelper() {
    }

    /**
     * DOC qiang.zhang Comment method "convert".
     * 
     * @param metadataTable
     * @return
     */
    public static MetadataTable convert(IMetadataTable old) {
        MetadataTable result = ConnectionFactory.eINSTANCE.createMetadataTable();
        result.setComment(old.getDescription());
        result.setId(old.getId());
        result.setLabel(old.getLabel());
        result.setSourceName(old.getTableName());
        List<MetadataColumn> columns = new ArrayList<MetadataColumn>(old.getListColumns().size());
        for (IMetadataColumn column : old.getListColumns()) {
            MetadataColumn newColumn = ConnectionFactory.eINSTANCE.createMetadataColumn();
            columns.add(newColumn);
            newColumn.setComment(column.getComment());
            newColumn.setDefaultValue(column.getDefault());
            newColumn.setKey(column.isKey());
            newColumn.setLabel(column.getLabel());
            newColumn.setPattern(column.getPattern());
            if (column.getLength() < 0) {
                newColumn.setLength(0);
            } else {
                newColumn.setLength(column.getLength());
            }
            newColumn.setNullable(column.isNullable());
            if (column.getPrecision() < 0) {
                newColumn.setPrecision(0);
            } else {
                newColumn.setPrecision(column.getPrecision());
            }
            newColumn.setTalendType(column.getTalendType());
            newColumn.setSourceType(column.getType());
            if (column.getOriginalDbColumnName() == null || column.getOriginalDbColumnName().equals("")) {
                newColumn.setOriginalField(column.getLabel());
            } else {
                newColumn.setOriginalField(column.getOriginalDbColumnName());
            }
        }
        result.getColumns().addAll(columns);
        return result;
    }

}
