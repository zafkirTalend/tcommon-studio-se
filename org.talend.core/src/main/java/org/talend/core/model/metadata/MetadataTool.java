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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.talend.commons.exception.PersistenceException;
import org.talend.core.CorePlugin;
import org.talend.core.model.metadata.builder.ConvertionHelper;
import org.talend.core.model.metadata.builder.connection.Connection;
import org.talend.core.model.metadata.builder.connection.MetadataTable;
import org.talend.core.model.metadata.builder.connection.QueriesConnection;
import org.talend.core.model.metadata.builder.connection.Query;
import org.talend.core.model.metadata.builder.connection.SAPConnection;
import org.talend.core.model.metadata.builder.connection.SAPFunctionUnit;
import org.talend.core.model.process.EParameterFieldType;
import org.talend.core.model.process.IElementParameter;
import org.talend.core.model.process.INode;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.core.model.repository.IRepositoryObject;
import org.talend.designer.core.model.utils.emf.talendfile.ColumnType;
import org.talend.designer.core.model.utils.emf.talendfile.MetadataType;
import org.talend.designer.core.model.utils.emf.talendfile.TalendFileFactory;
import org.talend.repository.model.ERepositoryStatus;
import org.talend.repository.model.IProxyRepositoryFactory;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 * $Id: talend-code-templates.xml 1 2006-09-29 17:06:40 +0000 (ven., 29 sept. 2006) nrousseau $
 * 
 */
public class MetadataTool {

    public static List<ColumnNameChanged> getColumnNameChanged(IMetadataTable oldTable, IMetadataTable newTable) {
        List<ColumnNameChanged> columnNameChanged = new ArrayList<ColumnNameChanged>();
        for (int i = 0; i < oldTable.getListColumns().size(); i++) {
            IMetadataColumn originalColumn = oldTable.getListColumns().get(i);
            IMetadataColumn clonedColumn = getColumn(newTable, originalColumn.getId());
            if (clonedColumn != null) {
                if (!originalColumn.getLabel().equals(clonedColumn.getLabel())) {
                    columnNameChanged.add(new ColumnNameChanged(originalColumn.getLabel(), clonedColumn.getLabel()));
                }
            }
        }
        return columnNameChanged;
    }

    public static List<ColumnNameChanged> getColumnNameChangedExt(INode changedNode, IMetadataTable oldTable,
            IMetadataTable newTable) {
        if (changedNode == null || oldTable == null || newTable == null) {
            return Collections.EMPTY_LIST;
        }
        List<ColumnNameChanged> columnNameChanged = new ArrayList<ColumnNameChanged>();
        for (int i = 0; i < oldTable.getListColumns().size(); i++) {
            IMetadataColumn originalColumn = oldTable.getListColumns().get(i);
            IMetadataColumn clonedColumn = getColumn(newTable, originalColumn.getId());
            if (clonedColumn != null) {
                if (!originalColumn.getLabel().equals(clonedColumn.getLabel())) {
                    columnNameChanged.add(new ColumnNameChangedExt(changedNode, originalColumn.getLabel(), clonedColumn
                            .getLabel()));
                }
            }
        }
        return columnNameChanged;
    }

    private static IMetadataColumn getColumn(IMetadataTable table, int id) {
        for (IMetadataColumn col : table.getListColumns()) {
            if (col.getId() == id) {
                return col;
            }
        }
        return null;
    }

    public static void copyTable(IMetadataTable source, IMetadataTable target) {
        copyTable(source, target, null);
    }

    public static void copyTable(IMetadataTable source, IMetadataTable target, String targetDbms) {
        List<IMetadataColumn> columnsToRemove = new ArrayList<IMetadataColumn>();
        List<String> readOnlycolumns = new ArrayList<String>();
        for (IMetadataColumn column : target.getListColumns()) {
            if (!column.isCustom()) {
                columnsToRemove.add(column);
            }
            if (column.isReadOnly()) {
                readOnlycolumns.add(column.getLabel());
            }
        }
        target.getListColumns().removeAll(columnsToRemove);

        List<IMetadataColumn> columnsTAdd = new ArrayList<IMetadataColumn>();
        for (IMetadataColumn column : source.getListColumns()) {
            IMetadataColumn targetColumn = target.getColumn(column.getLabel());
            IMetadataColumn newTargetColumn = column.clone();
            if (targetColumn == null) {
                columnsTAdd.add(newTargetColumn);
                newTargetColumn.setReadOnly(target.isReadOnly() || readOnlycolumns.contains(newTargetColumn.getLabel()));
            } else {
                if (!targetColumn.isReadOnly()) {
                    target.getListColumns().remove(targetColumn);
                    newTargetColumn.setCustom(targetColumn.isCustom());
                    newTargetColumn.setCustomId(targetColumn.getCustomId());
                    columnsTAdd.add(newTargetColumn);
                }
            }
        }
        target.getListColumns().addAll(columnsTAdd);
        target.sortCustomColumns();
    }

    public static Query getQueryFromRepository(String metaRepositoryId) {
        org.talend.core.model.metadata.builder.connection.Connection connection;

        String[] names = metaRepositoryId.split(" - ");
        if (names.length != 2) {
            return null;
        }
        String linkedRepository = names[0];
        connection = getConnectionFromRepository(linkedRepository);

        if (connection != null) {
            QueriesConnection queriesConnection = connection.getQueries();
            if (queriesConnection == null) {
                return null;
            }
            EList<Query> queries = queriesConnection.getQuery();

            for (Query currentQuery : queries) {
                if (currentQuery.getLabel().equals(names[1])) {
                    return currentQuery;
                }
            }
        }
        return null;

    }

    public static IMetadataTable getMetadataFromRepository(String metaRepositoryId) {
        org.talend.core.model.metadata.builder.connection.Connection connection;

        String[] names = metaRepositoryId.split(" - ");
        if (names.length != 2) {
            return null;
        }
        String linkedRepository = names[0];
        connection = getConnectionFromRepository(linkedRepository);

        if (connection != null) {
            for (Object tableObj : getMetadataTableFromConnection(connection)) {
                MetadataTable table = (MetadataTable) tableObj;
                if (table.getLabel().equals(names[1])) {
                    return ConvertionHelper.convert(table);
                }
            }
        }
        return null;

    }

    /**
     * 
     * DOC YeXiaowei Comment method "getMetadataTableFromConnection".
     * 
     * @param conn
     * @return
     */
    public static EList getMetadataTableFromConnection(final Connection conn) {
        if (conn == null) {
            return null;
        }
        // return conn.getTables();
        if (conn instanceof SAPConnection) {
            final SAPConnection sapConnection = (SAPConnection) conn;
            final EList functions = sapConnection.getFuntions();
            if (functions != null && !functions.isEmpty()) {
                final EList tables = new BasicEList();
                for (int i = 0; i < functions.size(); i++) {
                    tables.add(((SAPFunctionUnit) functions.get(i)).getMetadataTable());
                }
                return tables;
            }
        } else {
            return conn.getTables();
        }
        return null;
    }

    public static org.talend.core.model.metadata.builder.connection.Connection getConnectionFromRepository(String metaRepositoryid) {
        String connectionId = metaRepositoryid;
        // some calls can be done either with only the connection Id or with
        // informations from query or table
        String[] names = metaRepositoryid.split(" - ");
        if (names.length == 2) {
            connectionId = names[0];
        }

        IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
        try {
            IRepositoryObject object = factory.getLastVersion(connectionId);
            if (object == null) {
                return null;
            }
            if (factory.getStatus(object) != ERepositoryStatus.DELETED) {
                return ((ConnectionItem) object.getProperty().getItem()).getConnection();
            }
        } catch (PersistenceException e) {
            throw new RuntimeException(e);
        }
        return null;

    }

    /**
     * qzhang Comment method "getNewMetadataColumns".
     * 
     * @param oldTable
     * @param newTable
     * @return
     */
    public static List<ColumnNameChanged> getNewMetadataColumns(IMetadataTable oldTable, IMetadataTable newTable) {
        List<ColumnNameChanged> list = new ArrayList<ColumnNameChanged>();
        List<IMetadataColumn> newColumns = newTable.getListColumns();
        List<IMetadataColumn> oldColumns = oldTable.getListColumns();
        for (IMetadataColumn column : newColumns) {
            boolean isNew = true;
            for (IMetadataColumn ocolumn : oldColumns) {
                if (column.getLabel().equals(ocolumn.getLabel())) {
                    isNew = false;
                    break;
                }
            }
            if (isNew) {
                list.add(new ColumnNameChanged("", column.getLabel()));
            }
        }
        return list;
    }

    /**
     * qzhang Comment method "getRemoveMetadataColumns".
     * 
     * @param oldTable
     * @param newTable
     * @return
     */
    public static List<ColumnNameChanged> getRemoveMetadataColumns(IMetadataTable oldTable, IMetadataTable newTable) {
        List<ColumnNameChanged> list = new ArrayList<ColumnNameChanged>();
        List<IMetadataColumn> newColumns = newTable.getListColumns();
        List<IMetadataColumn> oldColumns = oldTable.getListColumns();
        for (IMetadataColumn column : oldColumns) {
            boolean isNew = true;
            for (IMetadataColumn ocolumn : newColumns) {
                if (column.getLabel().equals(ocolumn.getLabel())) {
                    isNew = false;
                    break;
                }
            }
            if (isNew) {
                list.add(new ColumnNameChanged(column.getLabel(), ""));
            }
        }
        return list;
    }

    public static void initilializeSchemaFromElementParameters(IMetadataTable metadataTable,
            List<IElementParameter> elementParameters) {
        IElementParameter mappingParameter = getMappingParameter(elementParameters);
        for (int i = 0; i < elementParameters.size(); i++) {
            IElementParameter param = elementParameters.get(i);
            if (param.getField().equals(EParameterFieldType.SCHEMA_TYPE)
                    && param.getContext().equals(metadataTable.getAttachedConnector())) {
                if (param.getValue() instanceof IMetadataTable) {
                    param.setValueToDefault(elementParameters);
                    IMetadataTable table = (IMetadataTable) param.getValue();
                    if (mappingParameter != null) {
                        if (mappingParameter.getValue() != null && (!mappingParameter.getValue().equals(""))) {
                            table.setDbms((String) mappingParameter.getValue());
                        }
                    }
                    metadataTable.setReadOnly(table.isReadOnly());

                    metadataTable.setReadOnlyColumnPosition(table.getReadOnlyColumnPosition());

                    // if all the table is read only then remove all columns to
                    // set the one defined in the emf component
                    // if (metadataTable.isReadOnly()) {
                    // metadataTable.getListColumns().clear();
                    // }
                    for (int k = 0; k < table.getListColumns().size(); k++) {
                        IMetadataColumn newColumn = table.getListColumns().get(k);
                        IMetadataColumn oldColumn = metadataTable.getColumn(newColumn.getLabel());
                        if (oldColumn != null) {
                            // if column exists, then override read only /
                            // custom
                            oldColumn.setReadOnly(newColumn.isReadOnly());
                            oldColumn.setCustom(newColumn.isCustom());
                            oldColumn.setCustomId(newColumn.getCustomId());
                            if (newColumn.isReadOnly()) { // if read only,
                                // override
                                // everything
                                oldColumn.setKey(newColumn.isKey());
                                oldColumn.setNullable(newColumn.isNullable());
                                oldColumn.setLength(newColumn.getLength());
                                oldColumn.setPrecision(newColumn.getPrecision());
                                oldColumn.setPattern(newColumn.getPattern());
                                oldColumn.setType(newColumn.getType());
                                oldColumn.setTalendType(newColumn.getTalendType());
                            }
                        } else { // if column doesn't exist, then add it.
                            if (newColumn.isReadOnly() || newColumn.isCustom() || table.isReadOnly()) {
                                metadataTable.getListColumns().add(newColumn);
                            }
                        }
                    }
                }
            }
        }
        metadataTable.sortCustomColumns();
    }

    public static IElementParameter getMappingParameter(List<IElementParameter> elementParameters) {
        for (int i = 0; i < elementParameters.size(); i++) {
            IElementParameter param = elementParameters.get(i);
            if (param.getField().equals(EParameterFieldType.MAPPING_TYPE)) {
                return param;
            }
        }
        return null;
    }

    /**
     * DOC qzhang Comment method "copyTable".
     * 
     * @param source
     * @param target
     */
    public static void copyTable(IMetadataTable source, MetadataType target) {
        List<ColumnType> colTypes = (List<ColumnType>) target.getColumn();
        colTypes.clear();
        for (IMetadataColumn column : source.getListColumns()) {
            ColumnType createColumnType = TalendFileFactory.eINSTANCE.createColumnType();
            createColumnType.setComment(column.getComment());
            createColumnType.setDefaultValue(column.getDefault());
            createColumnType.setKey(column.isKey());
            if (column.getLength() == null) {
                // colType.setLength(-1);
                createColumnType.unsetLength();
            } else {
                createColumnType.setLength(column.getLength());
            }
            if (column.getPrecision() == null) {
                // colType.setPrecision(-1);
                createColumnType.unsetPrecision();
            } else {
                createColumnType.setPrecision(column.getPrecision());
            }
            if (!column.getLabel().equals(column.getOriginalDbColumnName())) {
                createColumnType.setOriginalDbColumnName(column.getOriginalDbColumnName());
            }
            createColumnType.setName(column.getLabel());
            createColumnType.setNullable(column.isNullable());
            createColumnType.setPattern(column.getPattern());

            createColumnType.setSourceType(column.getType());
            createColumnType.setType(column.getTalendType());
            colTypes.add(createColumnType);
        }

    }

    /**
     * 
     * nrousseau Comment method "getMetadataTableFromNode".
     * 
     * 
     */
    public static IMetadataTable getMetadataTableFromNode(INode node, String name) {
        if (node == null || name == null) {
            return null;
        }
        for (IMetadataTable metadata : node.getMetadataList()) {
            if (name.equals(metadata.getLabel())) {
                return metadata;
            }
        }
        return null;
    }
}
