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
package org.talend.core.model.metadata;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Shell;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.core.CorePlugin;
import org.talend.core.PluginChecker;
import org.talend.core.model.metadata.builder.ConvertionHelper;
import org.talend.core.model.metadata.builder.connection.AbstractMetadataObject;
import org.talend.core.model.metadata.builder.connection.Connection;
import org.talend.core.model.metadata.builder.connection.MetadataTable;
import org.talend.core.model.metadata.builder.connection.QueriesConnection;
import org.talend.core.model.metadata.builder.connection.Query;
import org.talend.core.model.metadata.builder.connection.SAPConnection;
import org.talend.core.model.metadata.builder.connection.SAPFunctionUnit;
import org.talend.core.model.process.EParameterFieldType;
import org.talend.core.model.process.IElementParameter;
import org.talend.core.model.process.INode;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.impl.ConnectionItemImpl;
import org.talend.core.model.repository.IRepositoryViewObject;
import org.talend.designer.core.model.utils.emf.talendfile.ColumnType;
import org.talend.designer.core.model.utils.emf.talendfile.MetadataType;
import org.talend.designer.core.model.utils.emf.talendfile.TalendFileFactory;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 * $Id: talend-code-templates.xml 1 2006-09-29 17:06:40 +0000 (ven., 29 sept. 2006) nrousseau $
 * 
 */
public class MetadataTool {

    //    private static final String VALIDATE_PATTERN_NAME = "^[a-zA-Z_][a-zA-Z_0-9]*$"; //$NON-NLS-1$

    //    private static final String VALIDATE_PATTERN_SCHEMA_NAME = "^[a-zA-Z_0-9][a-zA-Z_0-9]*$"; //$NON-NLS-1$

    public static List<ColumnNameChanged> getColumnNameChanged(IMetadataTable oldTable, IMetadataTable newTable) {
        List<ColumnNameChanged> columnNameChanged = new ArrayList<ColumnNameChanged>();
        for (int i = 0; i < oldTable.getListColumns().size(); i++) {
            IMetadataColumn originalColumn = oldTable.getListColumns().get(i);
            int oldIndex = oldTable.getListColumns().indexOf(originalColumn);
            // TDI-9847:since the SCD type3field's currentColumn and previousColumn has same ID,when edit schema,we
            // up/down outputSchema's column order,need add index to make a difference to get the right column
            IMetadataColumn clonedColumn = getColumn(newTable, originalColumn, oldIndex);
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
            int oldIndex = oldTable.getListColumns().indexOf(originalColumn);
            IMetadataColumn clonedColumn = getColumn(newTable, originalColumn, oldIndex);
            if (clonedColumn != null) {
                if (!originalColumn.getLabel().equals(clonedColumn.getLabel())) {
                    columnNameChanged.add(new ColumnNameChangedExt(changedNode, originalColumn.getLabel(), clonedColumn
                            .getLabel()));
                }
            }
        }
        return columnNameChanged;
    }

    private static IMetadataColumn getColumn(IMetadataTable table, IMetadataColumn originalColumn, int oldIndex) {
        if (originalColumn.getId() != null) {
            for (IMetadataColumn col : table.getListColumns()) {
                if (originalColumn.getId().equals(col.getId())) {
                    if (oldIndex != table.getListColumns().indexOf(col)) {
                        if (originalColumn.getLabel().equals(col.getLabel())) {
                            return col;
                        } else {
                            continue;
                        }
                    } else {
                        return col;
                    }
                }
            }
        }
        return null;
    }

    public static void copyTable(IMetadataTable source, IMetadataTable target) {
        copyTable(source, target, null);
    }

    /**
     * @author wzhang Comment method "copyTable".
     * @param dbmsid
     * @param source
     * @param target
     */
    public static void copyTable(String dbmsId, IMetadataTable source, IMetadataTable target) {
        MetadataToolHelper.copyTable(dbmsId, source, target);
    }

    /**
     * DOC wzhang Comment method "setDBType".
     */
    public static void setDBType(IMetadataTable metaTable, String dbmsid) {
        MetadataToolHelper.setDBType(metaTable, dbmsid);
    }

    /**
     * 
     * DOC qli Comment method "copyTable".
     * 
     * @param sourceColumns,target
     * @return
     */
    public static void copyTable(List<IMetadataColumn> sourceColumns, IMetadataTable target) {
        copyTable(sourceColumns, target, null);
    }

    public static void copyTable(IMetadataTable source, IMetadataTable target, String targetDbms) {
        MetadataToolHelper.copyTable(source, target, targetDbms);
    }

    /**
     * 
     * DOC qli Comment method "copyTable".
     * 
     * @param sourceColumns,target,targetDbms
     * @return
     */
    public static void copyTable(List<IMetadataColumn> sourceColumns, IMetadataTable target, String targetDbms) {
        MetadataToolHelper.copyTable(sourceColumns, target, targetDbms);
    }

    public static Query getQueryFromRepository(String metaRepositoryId) {
        org.talend.core.model.metadata.builder.connection.Connection connection;

        String[] names = metaRepositoryId.split(" - "); //$NON-NLS-1$
        String name2 = null;
        if (names.length < 2) {
            return null;
        }
        String linkedRepository = names[0];
        if (names.length == 2) {
            name2 = names[1];
        } else if (names.length > 2) {
            name2 = metaRepositoryId.substring(linkedRepository.length() + 3);
        }

        connection = getConnectionFromRepository(linkedRepository);

        if (connection != null) {
            QueriesConnection queriesConnection = connection.getQueries();
            if (queriesConnection == null) {
                return null;
            }
            EList<Query> queries = queriesConnection.getQuery();

            for (Query currentQuery : queries) {
                if (currentQuery.getLabel().equals(name2)) {
                    return currentQuery;
                }
            }
        }
        return null;

    }

    public static MetadataTable getMetadataTableFromRepository(String metaRepositoryId) {
        return MetadataToolHelper.getMetadataTableFromRepository(metaRepositoryId);
    }

    public static IMetadataTable getMetadataFromRepository(String metaRepositoryId) {
        MetadataTable table = getMetadataTableFromRepository(metaRepositoryId);
        if (table != null) {
            return ConvertionHelper.convert(table);
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
        return MetadataToolHelper.getMetadataTableFromConnection(conn);
    }

    public static org.talend.core.model.metadata.builder.connection.Connection getConnectionFromRepository(String metaRepositoryid) {
        return MetadataToolHelper.getConnectionFromRepository(metaRepositoryid);
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
                list.add(new ColumnNameChanged("", column.getLabel())); //$NON-NLS-1$
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
                list.add(new ColumnNameChanged(column.getLabel(), "")); //$NON-NLS-1$
            }
        }
        return list;
    }

    public static void initilializeSchemaFromElementParameters(IMetadataTable metadataTable,
            List<IElementParameter> elementParameters) {
        IElementParameter mappingParameter = getMappingParameter(elementParameters);
        for (int i = 0; i < elementParameters.size(); i++) {
            IElementParameter param = elementParameters.get(i);
            if (param.getFieldType().equals(EParameterFieldType.SCHEMA_TYPE)
                    && param.getContext().equals(metadataTable.getAttachedConnector())) {
                if (param.getValue() instanceof IMetadataTable) {
                    param.setValueToDefault(elementParameters);
                    IMetadataTable table = (IMetadataTable) param.getValue();
                    if (mappingParameter != null) {
                        if (mappingParameter.getValue() != null && (!mappingParameter.getValue().equals(""))) { //$NON-NLS-1$
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
                                oldColumn.setComment(newColumn.getComment());
                            }
                        } else { // if column doesn't exist, then add it.
                            // if (newColumn.isReadOnly() || newColumn.isCustom() || table.isReadOnly()) {
                            // metadataTable.getListColumns().add(newColumn);
                            // }
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
            if (param.getFieldType().equals(EParameterFieldType.MAPPING_TYPE)) {
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

            if (PluginChecker.isDatacertPluginLoaded()) {
                createColumnType.setRelatedEntity(column.getRelatedEntity());
                createColumnType.setRelationshipType(column.getRelationshipType());
            }
            colTypes.add(createColumnType);
        }

    }

    /**
     * @param node
     * @param name
     * @return
     * @deprecated use getMetadataTableFromNodeLabel instead
     */
    public static IMetadataTable getMetadataTableFromNode(INode node, String name) {
        return getMetadataTableFromNodeLabel(node, name);
    }

    public static IMetadataTable getMetadataTableFromNodeLabel(INode node, String name) {
        return MetadataToolHelper.getMetadataTableFromNodeLabel(node, name);
    }

    public static IMetadataTable getMetadataTableFromNodeTableName(INode node, String name) {
        return MetadataToolHelper.getMetadataTableFromNodeTableName(node, name);
    }

    /**
     * 
     * qli Comment method "validateColumnName".
     * 
     * 
     */
    public static String validateColumnName(String columnName, int index) {
        return MetadataToolHelper.validateColumnName(columnName, index);
    }

    public static boolean isValidSchemaName(String name) {
        return MetadataToolHelper.isValidSchemaName(name);
    }

    public static boolean isValidColumnName(String name) {
        return MetadataToolHelper.isValidColumnName(name);
    }

    /**
     * wzhang Comment method "validataValue".
     */
    public static String validateValue(String columnName) {
        return MetadataToolHelper.validateValue(columnName);
    }

    /**
     * zli Comment method "validataValue".
     */
    public static String validateValueForDBType(String columnName) {
        return MetadataToolHelper.validateValueForDBType(columnName);
    }

    /**
     * wzhang Comment method "checkSchema".
     */
    public static void checkSchema(Shell shell, KeyEvent event) {
        MetadataToolHelper.checkSchema(shell, event);
    }

    /**
     * wzhang Comment method "validateSchema".
     */
    public static void validateSchema(String value) {
        MetadataToolHelper.validateSchema(value);
    }

    /**
     * 
     * wzhangComment method "validateSchemaValue".
     * 
     * @param value
     * @param beanPosition
     * @param list
     * @return
     */
    public static String validateSchemaValue(String value, int beanPosition, List<String> list) {
        return MetadataToolHelper.validateSchemaValue(value, beanPosition, list);
    }

    public static boolean hasCustomColumns(IMetadataTable table) {
        if (table == null) {
            return false;
        }
        for (IMetadataColumn column : table.getListColumns()) {
            if (column.isCustom()) {
                return true;
            }
        }
        return false;
    }

    public static Collection<IRepositoryViewObject> getContextDependenciesOfMetadataConnection(Collection<Item> items) {
        Collection<IRepositoryViewObject> repositoryObjects = new ArrayList<IRepositoryViewObject>();
        try {
            for (Item item : items) {
                if (item == null) {
                    continue;
                }
                if (item instanceof ConnectionItemImpl) {
                    Connection connection = ((ConnectionItemImpl) item).getConnection();
                    if (connection != null) {
                        IRepositoryViewObject lastVersion = null;
                        if (connection.getContextId() != null) {
                            lastVersion = CorePlugin.getDefault().getProxyRepositoryFactory()
                                    .getLastVersion(connection.getContextId());
                        }
                        if (lastVersion != null) {
                            repositoryObjects.add(lastVersion);
                        }
                    }

                }

            }
        } catch (PersistenceException e) {
            ExceptionHandler.process(e);
        }
        return repositoryObjects;
    }

    /**
     * 
     * cli Comment method "processFieldLength".
     * 
     */
    public static void processFieldsLength(EObject obj) {
        if (obj != null) {

            // if the comment length of metadata object is more than 255. will be cut
            if (obj instanceof AbstractMetadataObject) {
                AbstractMetadataObject metadataObj = (AbstractMetadataObject) obj;
                String comment = processFieldsLength(metadataObj.getComment());
                if (comment != null) {
                    metadataObj.setComment(comment);
                }
            } else if (obj instanceof MetadataType) {
                MetadataType type = (MetadataType) obj;
                String comment = processFieldsLength(type.getComment());
                if (comment != null) {
                    type.setComment(comment);
                }
            } else if (obj instanceof ColumnType) {
                ColumnType type = (ColumnType) obj;
                String comment = processFieldsLength(type.getComment());
                if (comment != null) {
                    type.setComment(comment);
                }
            }
        }
    }

    private static String processFieldsLength(String comment) {
        final int max = 255;
        if (comment != null && comment.length() > max) {
            final String dots = "..."; //$NON-NLS-1$
            comment = comment.substring(0, max - 4);
            if (!comment.endsWith(dots)) {
                comment += dots;
            }
            return comment;
        }
        return null; // don't process
    }

    public static SAPFunctionUnit getSAPFunctionFromRepository(String functionRepositoryId) {
        org.talend.core.model.metadata.builder.connection.Connection connection;

        String[] names = functionRepositoryId.split(" - "); //$NON-NLS-1$
        String name2 = null;
        if (names.length < 2) {
            return null;
        }
        String linkedRepository = names[0];
        if (names.length == 2) {
            name2 = names[1];
        } else if (names.length > 2) {
            name2 = functionRepositoryId.substring(linkedRepository.length() + 3);
        }

        connection = getConnectionFromRepository(linkedRepository);
        if (connection != null) {
            if (connection instanceof SAPConnection) {
                SAPConnection sapConn = (SAPConnection) connection;
                EList funtions = sapConn.getFuntions();
                for (Object obj : funtions) {
                    SAPFunctionUnit funciton = (SAPFunctionUnit) obj;
                    if (name2.equals(funciton.getLabel())) {
                        return funciton;
                    }

                }
            }
        }
        return null;

    }
}
