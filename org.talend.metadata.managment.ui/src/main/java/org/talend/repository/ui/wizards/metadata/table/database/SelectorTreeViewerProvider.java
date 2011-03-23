// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.ui.wizards.metadata.table.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.core.model.metadata.IMetadataConnection;
import org.talend.core.model.metadata.MetadataFillFactory;
import org.talend.core.model.metadata.builder.connection.MetadataTable;
import org.talend.core.model.metadata.builder.database.ExtractMetaDataFromDataBase.ETableTypes;
import org.talend.core.model.metadata.builder.database.ExtractMetaDataUtils;
import org.talend.core.model.metadata.builder.database.TableInfoParameters;
import org.talend.core.model.metadata.builder.database.TableNode;
import org.talend.cwm.relational.TdTable;
import org.talend.cwm.relational.TdView;

/**
 * wzhang class global comment. Detailled comment
 */
public class SelectorTreeViewerProvider extends LabelProvider implements ITreeContentProvider, ITableLabelProvider {

    private SelectorTableForm form;

    public SelectorTreeViewerProvider(SelectorTableForm form) {
        super();
        this.form = form;
    }

    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

    }

    public Object[] getElements(Object inputElement) {
        List list = (List) inputElement;
        return list.toArray();
    }

    public Object[] getChildren(Object parentElement) {
        TableNode tableNode = (TableNode) parentElement;
        IMetadataConnection metadataConn = tableNode.getMetadataConn();

        Connection conn = ExtractMetaDataUtils.getSqlConnection(metadataConn);
        DatabaseMetaData dbMetaData = ExtractMetaDataUtils.getDatabaseMetaData(conn, metadataConn.getDbType());
        int type = tableNode.getType();
        orgomg.cwm.objectmodel.core.Package pack = null;

        List<MetadataTable> tableList = new ArrayList<MetadataTable>();

        if (type == tableNode.CATALOG) {
            if (tableNode.getChildren().isEmpty()) {
                pack = tableNode.getCatalog();
            }
        } else if (type == tableNode.SCHEMA) {
            pack = tableNode.getSchema();
        }
        try {
            if (pack != null) {
                TableInfoParameters paras = tableNode.getParas();
                List<ETableTypes> paraType = paras.getTypes();
                Set<String> availableTableTypes = new HashSet<String>();
                for (ETableTypes tableType : paraType) {
                    availableTableTypes.add(tableType.getName());
                }
                // get all tables/views
                tableList = MetadataFillFactory.getDBInstance().fillAll(pack, dbMetaData, null, null,
                        availableTableTypes.toArray(new String[] {}));
            }
        } catch (Exception e) {
            ExceptionHandler.process(e);
        }

        transferToTableNode(tableList, tableNode);
        List<TableNode> children = tableNode.getChildren();
        return children.toArray();
    }

    public Object getParent(Object element) {
        TableNode tableNode = (TableNode) element;
        return tableNode.getParent();
    }

    public boolean hasChildren(Object element) {
        TableNode tableNode = (TableNode) element;
        int type = tableNode.getType();
        if (type == TableNode.CATALOG || type == TableNode.SCHEMA) {
            return true;
        }
        return !tableNode.getChildren().isEmpty();
    }

    public Image getColumnImage(Object element, int columnIndex) {
        return null;
    }

    public String getColumnText(Object element, int columnIndex) {
        TableNode tableNode = (TableNode) element;
        int type = tableNode.getType();
        switch (columnIndex) {
        case 0:
            String value = tableNode.getValue();
            if (value == null) {
                return "";
            }
            return value;
        case 1:
            if (type == TableNode.CATALOG) {
                return "CATALOG";
            } else if (type == TableNode.SCHEMA) {
                return "SCHEMA";
            } else {
                return tableNode.getItemType();
            }
        default:
            return "";
        }
    }

    private void transferToTableNode(List<MetadataTable> list, TableNode parentNode) {
        if (list != null && !list.isEmpty()) {
            for (MetadataTable table : list) {
                if (table instanceof TdTable) {
                    TdTable td = (TdTable) table;
                    TableNode tableNode = new TableNode();
                    tableNode.setType(TableNode.TABLE);
                    tableNode.setValue(td.getLabel());
                    tableNode.setItemType(td.getTableType());
                    tableNode.setTable(td);
                    tableNode.setParent(parentNode);
                    parentNode.addChild(tableNode);
                } else if (table instanceof TdView) {
                    TdView tv = (TdView) table;
                    TableNode tableNode = new TableNode();
                    tableNode.setType(TableNode.TABLE);
                    tableNode.setValue(tv.getLabel());
                    tableNode.setItemType(tv.getTableType());
                    tableNode.setView(tv);
                    tableNode.setParent(parentNode);
                    parentNode.addChild(tableNode);
                }
            }
        }
    }

}
