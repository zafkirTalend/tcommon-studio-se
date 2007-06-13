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
package org.talend.core.model.metadata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.talend.commons.exception.PersistenceException;
import org.talend.core.CorePlugin;
import org.talend.core.model.metadata.builder.ConvertionHelper;
import org.talend.core.model.metadata.builder.connection.MetadataTable;
import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.process.EParameterFieldType;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IElementParameter;
import org.talend.core.model.process.INode;
import org.talend.core.model.properties.ConnectionItem;
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

        // List<IMetadataColumn> listColumns = target.getListColumns();
        // for (IMetadataColumn column : listColumns) {
        // column.setPattern(null);
        // }

    }

    public static void updateColumnList(List<ColumnNameChanged> columnsChanged, INode elem) {
        List<String> columnList = getColumnList(elem);
        List<String> prevColumnList = getPrevColumnList(elem);
        Map<IConnection, List<String>> refColumnLists = getRefColumnLists(elem);

        String[] columnNameList = (String[]) columnList.toArray(new String[0]);
        String[] prevColumnNameList = (String[]) prevColumnList.toArray(new String[0]);
        String[] curColumnNameList = null;
        String[] curColumnValueList = null;

        List<String> refColumnListNamesTmp = new ArrayList<String>();
        List<String> refColumnListValuesTmp = new ArrayList<String>();
        for (IConnection connection : refColumnLists.keySet()) {
            String name = connection.getName() + ".";
            String value = connection.getSource().getUniqueName() + ".";
            for (String column : refColumnLists.get(connection)) {
                refColumnListNamesTmp.add(name + column);
                refColumnListValuesTmp.add(value + column);
            }
        }
        String[] refColumnListNames = (String[]) refColumnListNamesTmp.toArray(new String[0]);
        String[] refColumnListValues = (String[]) refColumnListValuesTmp.toArray(new String[0]);
        for (int i = 0; i < elem.getElementParameters().size(); i++) {
            IElementParameter param = elem.getElementParameters().get(i);
            if (param.getField() == EParameterFieldType.COLUMN_LIST) {
                curColumnNameList = columnNameList;
                curColumnValueList = columnNameList;
            }
            if (param.getField() == EParameterFieldType.PREV_COLUMN_LIST) {
                curColumnNameList = prevColumnNameList;
                curColumnValueList = prevColumnNameList;
            }
            if (param.getField() == EParameterFieldType.LOOKUP_COLUMN_LIST) {
                curColumnNameList = refColumnListNames;
                curColumnValueList = refColumnListValues;
            }
            if (param.getField() == EParameterFieldType.COLUMN_LIST || param.getField() == EParameterFieldType.PREV_COLUMN_LIST
                    || param.getField() == EParameterFieldType.LOOKUP_COLUMN_LIST) {
                param.setListItemsDisplayName(curColumnNameList);
                param.setListItemsValue(curColumnValueList);
            }
            if (param.getField() == EParameterFieldType.TABLE) {
                Object[] itemsValue = (Object[]) param.getListItemsValue();
                for (int j = 0; j < itemsValue.length; j++) {
                    if (itemsValue[j] instanceof IElementParameter) {
                        IElementParameter tmpParam = (IElementParameter) itemsValue[j];
                        if (tmpParam.getField() == EParameterFieldType.COLUMN_LIST) {
                            curColumnNameList = columnNameList;
                            curColumnValueList = columnNameList;
                        }
                        if (tmpParam.getField() == EParameterFieldType.PREV_COLUMN_LIST) {
                            curColumnNameList = prevColumnNameList;
                            curColumnValueList = prevColumnNameList;
                        }
                        if (tmpParam.getField() == EParameterFieldType.LOOKUP_COLUMN_LIST) {
                            curColumnNameList = refColumnListNames;
                            curColumnValueList = refColumnListValues;
                        }
                        if (tmpParam.getField() == EParameterFieldType.COLUMN_LIST
                                || tmpParam.getField() == EParameterFieldType.PREV_COLUMN_LIST
                                || tmpParam.getField() == EParameterFieldType.LOOKUP_COLUMN_LIST) {
                            tmpParam.setListItemsDisplayCodeName(curColumnNameList);
                            tmpParam.setListItemsDisplayName(curColumnNameList);
                            tmpParam.setListItemsValue(curColumnValueList);
                            if (curColumnValueList.length > 0) {
                                tmpParam.setDefaultClosedListValue(curColumnValueList[0]);
                            } else {
                                tmpParam.setDefaultClosedListValue(""); //$NON-NLS-1$
                            }
                        }
                    }
                }
            }
            if (param.isBasedOnSchema()) {
                List<Map<String, Object>> paramValues = (List<Map<String, Object>>) param.getValue();
                List<Map<String, Object>> newParamValues = new ArrayList<Map<String, Object>>();
                for (int j = 0; j < columnNameList.length; j++) {
                    String columnName = columnNameList[j];
                    String[] codes = param.getListItemsDisplayCodeName();

                    Map<String, Object> newLine = null;
                    boolean found = false;
                    ColumnNameChanged colChanged = null;
                    if (columnsChanged != null) {
                        for (int k = 0; k < columnsChanged.size() && !found; k++) {
                            colChanged = columnsChanged.get(k);
                            if (colChanged.getNewName().equals(columnName)) {
                                found = true;
                            }
                        }
                    }
                    if (found) {
                        found = false;
                        for (int k = 0; k < paramValues.size() && !found; k++) {
                            Map<String, Object> currentLine = (Map<String, Object>) paramValues.get(k);
                            if (currentLine.get(codes[0]).equals(colChanged.getOldName())) {
                                currentLine.put(codes[0], colChanged.getNewName());
                                found = true;
                            }
                        }
                    }
                    found = false;
                    for (int k = 0; k < paramValues.size() && !found; k++) {
                        Map<String, Object> currentLine = (Map<String, Object>) paramValues.get(k);
                        if (currentLine.get(codes[0]) == null) {
                            currentLine.put(codes[0], columnName);
                        }
                        if (currentLine.get(codes[0]).equals(columnName)) {
                            found = true;
                            newLine = currentLine;
                        }
                    }
                    if (!found) {
                        newLine = new HashMap<String, Object>();
                        newLine.put(codes[0], columnName);
                    }
                    newParamValues.add(j, newLine);
                }
                paramValues.clear();
                paramValues.addAll(newParamValues);

            }
        }
    }

    private static List<String> getColumnList(INode node) {
        List<String> columnList = new ArrayList<String>();

        if (node.getMetadataList().size() > 0) {
            IMetadataTable table = node.getMetadataList().get(0);
            for (IMetadataColumn column : table.getListColumns()) {
                columnList.add(column.getLabel());
            }
        }

        return columnList;
    }

    private static List<String> getPrevColumnList(INode node) {
        List<String> columnList = new ArrayList<String>();

        IConnection connection = null;
        boolean found = false;
        for (int i = 0; i < node.getIncomingConnections().size() && !found; i++) {
            IConnection curConnec = node.getIncomingConnections().get(i);
            if (curConnec.getLineStyle() == EConnectionType.FLOW_MAIN) {
                connection = curConnec;
                found = true;
            }
        }
        if (connection != null) {
            IMetadataTable table = connection.getMetadataTable();
            for (IMetadataColumn column : table.getListColumns()) {
                columnList.add(column.getLabel());
            }
        }

        return columnList;
    }

    private static Map<IConnection, List<String>> getRefColumnLists(INode node) {
        Map<IConnection, List<String>> refColumnLists = new HashMap<IConnection, List<String>>();

        List<IConnection> refConnections = new ArrayList<IConnection>();
        for (int i = 0; i < node.getIncomingConnections().size(); i++) {
            IConnection curConnec = node.getIncomingConnections().get(i);
            if (curConnec.getLineStyle() == EConnectionType.FLOW_REF) {
                refConnections.add(curConnec);
            }
        }
        for (IConnection connection : refConnections) {
            List<String> columnList = new ArrayList<String>();
            IMetadataTable table = connection.getMetadataTable();
            for (IMetadataColumn column : table.getListColumns()) {
                columnList.add(column.getLabel());
            }
            refColumnLists.put(connection, columnList);
        }
        return refColumnLists;
    }

    public static IMetadataTable getMetadataFromRepository(String metaRepositoryName) {
        IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
        List<ConnectionItem> metadataConnectionsItem = null;
        List<String> repositoryList = new ArrayList<String>();
        IMetadataTable metaToReturn = null;
        try {
            metadataConnectionsItem = factory.getMetadataConnectionsItem();
        } catch (PersistenceException e) {
            throw new RuntimeException(e);
        }
        if (metadataConnectionsItem != null) {
            for (ConnectionItem connectionItem : metadataConnectionsItem) {
                org.talend.core.model.metadata.builder.connection.Connection connection;
                connection = (org.talend.core.model.metadata.builder.connection.Connection) connectionItem
                        .getConnection();
                for (Object tableObj : connection.getTables()) {
                    MetadataTable table = (MetadataTable) tableObj;
                    if (!factory.isDeleted(table)) {
                        String name = connectionItem.getProperty().getId() + " - " + table.getLabel(); //$NON-NLS-1$
                        if (name.equals(metaRepositoryName)) {
                            metaToReturn = ConvertionHelper.convert(table);
                        }
                        repositoryList.add(name);
                    }
                }
            }
        }
        return metaToReturn;
    }


    public static org.talend.core.model.metadata.builder.connection.Connection getConnectionFromRepository(String metaRepositoryName) {
        IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
        List<ConnectionItem> metadataConnectionsItem = null;
        List<String> repositoryList = new ArrayList<String>();
        IMetadataTable metaToReturn = null;
        try {
            metadataConnectionsItem = factory.getMetadataConnectionsItem();
        } catch (PersistenceException e) {
            throw new RuntimeException(e);
        }
        if (metadataConnectionsItem != null) {
            for (ConnectionItem connectionItem : metadataConnectionsItem) {
                org.talend.core.model.metadata.builder.connection.Connection connection;
                connection = (org.talend.core.model.metadata.builder.connection.Connection) connectionItem
                        .getConnection();
                if (metaRepositoryName.startsWith(connectionItem.getProperty().getId())) {
                    return connection;
                }
            }
        }
        return null;
    }
}
