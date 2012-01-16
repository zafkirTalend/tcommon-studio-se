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
package org.talend.core.model.metadata.builder;

import java.util.ArrayList;
import java.util.List;

import org.talend.core.GlobalServiceRegister;
import org.talend.core.ICoreService;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataConnection;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.builder.connection.AbstractMetadataObject;
import org.talend.core.model.metadata.builder.connection.Connection;
import org.talend.core.model.metadata.builder.connection.ConnectionFactory;
import org.talend.core.model.metadata.builder.connection.DatabaseConnection;
import org.talend.core.model.metadata.builder.connection.DelimitedFileConnection;
import org.talend.core.model.metadata.builder.connection.MDMConnection;
import org.talend.core.model.metadata.builder.connection.MetadataColumn;
import org.talend.core.model.metadata.builder.connection.MetadataTable;
import org.talend.core.model.metadata.builder.database.ExtractMetaDataUtils;
import org.talend.core.runtime.CoreRuntimePlugin;
import org.talend.repository.model.IRepositoryService;

/**
 * 
 */
public final class ConvertionHelper {

    private static ICoreService coreService = (ICoreService) GlobalServiceRegister.getDefault().getService(ICoreService.class);

    /**
     * This method doesn't perform a deep copy. DOC tguiu Comment method "convert".
     * 
     * @param connection
     * @return
     */
    public static IMetadataConnection convert(Connection sourceConnection) {
        return convert(sourceConnection, false);
    }

    public static IMetadataConnection convert(Connection sourceConnection, boolean defaultContext) {
        if (sourceConnection instanceof DatabaseConnection) {
            return convert((DatabaseConnection) sourceConnection, defaultContext, null);
        } else if (sourceConnection instanceof MDMConnection) {
            return convert((MDMConnection) sourceConnection, defaultContext, null);
        } else if (sourceConnection instanceof DelimitedFileConnection) {
            return convert((DelimitedFileConnection) sourceConnection, defaultContext, null);
        }
        return null;
    }
    public static IMetadataConnection convert(DatabaseConnection sourceConnection, boolean defaultContext, String selectedContext) {

        if (sourceConnection == null) {
            return null;
        }
        // if sourceConnection is not context mode, will be same as before.
        DatabaseConnection connection = null;
        DatabaseConnection originalValueConnection = null;
        IRepositoryService repositoryService = CoreRuntimePlugin.getInstance().getRepositoryService();
        if (repositoryService != null) {
            originalValueConnection = repositoryService.cloneOriginalValueConnection(sourceConnection, defaultContext,
                    selectedContext);
        }
        if (originalValueConnection == null) {
            connection = sourceConnection;
        } else {
            connection = originalValueConnection;
        }
        IMetadataConnection result = new org.talend.core.model.metadata.MetadataConnection();
        result.setComment(connection.getComment());
        result.setDatabase(connection.getSID());
        result.setDataSourceName(connection.getDatasourceName());

        if (connection.getDatabaseType() == null || "".equals(connection.getDatabaseType())) { // 0009594 //$NON-NLS-1$
            String trueDbType = ExtractMetaDataUtils.getDbTypeByClassName(connection.getDriverClass());
            result.setDbType(trueDbType);
        } else {
            result.setDbType(connection.getDatabaseType());
        }
        result.setDriverJarPath(connection.getDriverJarPath());
        result.setDbVersionString(connection.getDbVersionString());
        result.setDriverClass(connection.getDriverClass());
        result.setFileFieldName(connection.getFileFieldName());
        result.setId(connection.getId());
        result.setLabel(connection.getLabel());
        result.setNullChar(connection.getNullChar());
        result.setPassword(connection.getPassword());
        result.setPort(connection.getPort());
        result.setServerName(connection.getServerName());
        result.setSqlSyntax(connection.getSqlSynthax());
        result.setSchema(connection.getUiSchema());
        result.setStringQuote(connection.getStringQuote());
        result.setUrl(connection.getURL());
        result.setAdditionalParams(connection.getAdditionalParams());
        result.setUsername(connection.getUsername());
        result.setMapping(connection.getDbmsId());
        result.setProduct(connection.getProductId());
        result.setDbRootPath(connection.getDBRootPath());
        result.setSqlMode(connection.isSQLMode());
        result.setCurrentConnection(connection); // keep the connection for the metadataconnection
        result.setContentModel(connection.isContextMode());
        result.setContextId(connection.getContextId());
        result.setContextName(connection.getContextName());
        // handle oracle database connnection of general_jdbc.
        result.setSchema(ExtractMetaDataUtils.getMeataConnectionSchema(result));

        return result;

    }
    
    /**
     * 
     * DOC zshen Comment method "convert".
     * 
     * @param sourceConnection
     * @param defaultContext
     * @param selectedContext
     * @return convert from sourceConnection to MetadataConnection
     */
    public static IMetadataConnection convert(MDMConnection sourceConnection, boolean defaultContext, String selectedContext) {

        if (sourceConnection == null) {
            return null;
        }
        // if sourceConnection is not context mode, will be same as before.
        MDMConnection connection = null;

        connection = sourceConnection;

        IMetadataConnection result = new org.talend.core.model.metadata.MetadataConnection();
        result.setComment(connection.getComment());

        result.setId(connection.getId());
        result.setLabel(connection.getLabel());

        result.setPassword(connection.getPassword());
        result.setPort(connection.getPort());
        result.setServerName(connection.getServer());

        result.setUsername(connection.getUsername());
        result.setUniverse(connection.getUniverse());
        result.setDatamodel(connection.getDatamodel());
        result.setDatacluster(connection.getDatacluster());

        result.setCurrentConnection(connection); // keep the connection for the metadataconnection
        // handle oracle database connnection of general_jdbc.
        result.setSchema(ExtractMetaDataUtils.getMeataConnectionSchema(result));

        return result;

    }

    /**
     * 
     * DOC zshen Comment method "convert".
     * 
     * @param sourceConnection
     * @param defaultContext
     * @param selectedContext
     * @return convert form DelimitedFileConnection to MetadataConnection
     */
    public static IMetadataConnection convert(DelimitedFileConnection sourceConnection, boolean defaultContext,
            String selectedContext) {

        if (sourceConnection == null) {
            return null;
        }
        // if sourceConnection is not context mode, will be same as before.
        DelimitedFileConnection connection = null;

        connection = sourceConnection;

        IMetadataConnection result = new org.talend.core.model.metadata.MetadataConnection();
        result.setComment(connection.getComment());

        result.setId(connection.getId());
        result.setLabel(connection.getLabel());

        result.setServerName(connection.getServer());

        result.setCurrentConnection(connection); // keep the connection for the metadataconnection
        // handle oracle database connnection of general_jdbc.
        result.setSchema(ExtractMetaDataUtils.getMeataConnectionSchema(result));

        return result;

    }

    public static IMetadataTable convert(MetadataTable old) {
        IMetadataTable result = new org.talend.core.model.metadata.MetadataTable();
        result.setComment(old.getComment());
        result.setId(old.getId());
        result.setLabel(old.getLabel());
        String sourceName = old.getName();
        if (sourceName == null) {
            sourceName = old.getLabel();
        }
        result.setTableName(sourceName);
        List<IMetadataColumn> columns = new ArrayList<IMetadataColumn>(old.getColumns().size());
        for (Object o : old.getColumns()) {
            MetadataColumn column = (MetadataColumn) o;
            IMetadataColumn newColumn = new org.talend.core.model.metadata.MetadataColumn();
            columns.add(newColumn);
            newColumn.setComment(column.getComment());
            newColumn.setDefault(column.getDefaultValue());
            newColumn.setKey(column.isKey());
            String label2 = column.getLabel();
            if (coreService != null) {
                if (coreService.isKeyword(label2)) {
                    label2 = "_" + label2; //$NON-NLS-1$
                }
            }
            newColumn.setLabel(label2);
            newColumn.setPattern(column.getPattern());
            if (column.getLength() < 0) {
                newColumn.setLength(null);
            } else {
                newColumn.setLength(Long.valueOf(column.getLength()).intValue());
            }
            newColumn.setNullable(column.isNullable());
            if (column.getPrecision() < 0) {
                newColumn.setPrecision(null);
            } else {
                newColumn.setPrecision(Long.valueOf(column.getPrecision()).intValue());
            }
            newColumn.setTalendType(column.getTalendType());
            newColumn.setType(column.getSourceType());
            if (column.getName() == null || column.getName().equals("")) { //$NON-NLS-1$
                String label = label2;
                if (label != null && label.length() > 0) {
                    String substring = label.substring(1);
                    if (coreService != null) {
                        if (label.startsWith("_") && coreService.isKeyword(substring)) { //$NON-NLS-1$
                            label = substring;
                        }
                    }
                }
                newColumn.setOriginalDbColumnName(label);
            } else {
                newColumn.setOriginalDbColumnName(column.getName());
            }
            // columns.add(convertToIMetaDataColumn(column));
        }
        result.setListColumns(columns);
        return result;
    }

    public static IMetadataTable convertServicesOperational(AbstractMetadataObject old) {
        IMetadataTable result = new org.talend.core.model.metadata.MetadataTable();
        result.setComment(old.getComment());
        result.setId(old.getId());
        result.setLabel(old.getLabel());
        String sourceName = old.getName();
        if (sourceName == null) {
            sourceName = old.getLabel();
        }
        result.setTableName(sourceName);
        return result;
    }

    public static MetadataColumn convertToMetaDataColumn(IMetadataColumn column) {
        MetadataColumn newColumn = ConnectionFactory.eINSTANCE.createMetadataColumn();
        newColumn.setComment(column.getComment());
        newColumn.setDefaultValue(column.getDefault());
        newColumn.setKey(column.isKey());
        newColumn.setLabel(column.getLabel());
        newColumn.setPattern(column.getPattern());
        if (column.getLength() == null || column.getLength() < 0) {
            newColumn.setLength(0);
        } else {
            newColumn.setLength(column.getLength());
        }
        newColumn.setNullable(column.isNullable());
        if (column.getPrecision() == null || column.getPrecision() < 0) {
            newColumn.setPrecision(0);
        } else {
            newColumn.setPrecision(column.getPrecision());
        }
        newColumn.setTalendType(column.getTalendType());
        newColumn.setSourceType(column.getType());
        if (column.getOriginalDbColumnName() == null || column.getOriginalDbColumnName().equals("")) { //$NON-NLS-1$
            newColumn.setName(column.getLabel());
        } else {
            newColumn.setName(column.getOriginalDbColumnName());
        }
        return newColumn;
    }

    public static IMetadataColumn convertToIMetaDataColumn(MetadataColumn column) {
        IMetadataColumn newColumn = new org.talend.core.model.metadata.MetadataColumn();
        newColumn.setComment(column.getComment());
        newColumn.setDefault(column.getDefaultValue());
        newColumn.setKey(column.isKey());
        newColumn.setLabel(column.getLabel());
        newColumn.setPattern(column.getPattern());
        if (column.getLength() < 0) {
            newColumn.setLength(null);
        } else {
            newColumn.setLength(Long.valueOf(column.getLength()).intValue());
        }
        newColumn.setNullable(column.isNullable());
        if (column.getPrecision() < 0) {
            newColumn.setPrecision(null);
        } else {
            newColumn.setPrecision(Long.valueOf(column.getPrecision()).intValue());
        }
        newColumn.setTalendType(column.getTalendType());
        newColumn.setType(column.getSourceType());
        if (column.getName() == null || column.getName().equals("")) { //$NON-NLS-1$
            newColumn.setOriginalDbColumnName(column.getLabel());
        } else {
            newColumn.setOriginalDbColumnName(column.getName());
        }
        return newColumn;
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
        result.setComment(old.getComment());
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
            if (column.getLength() == null || column.getLength() < 0) {
                newColumn.setLength(0);
            } else {
                newColumn.setLength(column.getLength());
            }
            newColumn.setNullable(column.isNullable());
            if (column.getPrecision() == null || column.getPrecision() < 0) {
                newColumn.setPrecision(0);
            } else {
                newColumn.setPrecision(column.getPrecision());
            }
            newColumn.setTalendType(column.getTalendType());
            newColumn.setSourceType(column.getType());
            if (column.getOriginalDbColumnName() == null || column.getOriginalDbColumnName().equals("")) { //$NON-NLS-1$
                newColumn.setName(column.getLabel());
            } else {
                newColumn.setName(column.getOriginalDbColumnName());
            }
            // columns.add(convertToMetaDataColumn(column));
        }
        result.getColumns().addAll(columns);
        return result;
    }

}
