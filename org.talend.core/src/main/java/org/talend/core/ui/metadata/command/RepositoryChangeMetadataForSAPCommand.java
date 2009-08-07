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
package org.talend.core.ui.metadata.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.gef.commands.Command;
import org.talend.core.CorePlugin;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.ISAPConstant;
import org.talend.core.model.metadata.MetadataTool;
import org.talend.core.model.metadata.MultiSchemasUtil;
import org.talend.core.model.process.IElementParameter;
import org.talend.core.model.process.INode;

/**
 * DOC hwang class global comment. Detailled comment
 */
public class RepositoryChangeMetadataForSAPCommand extends Command {

    private INode node;

    private final String propName;

    private final Object oldPropValue, newPropValue;

    private IMetadataTable newOutputMetadata, oldOutputMetadata;

    private Integer index;

    /**
     * 
     * hwang RepositoryChangeMetadataForSAPCommand constructor comment.
     * 
     * only for DND
     */
    public RepositoryChangeMetadataForSAPCommand(INode node, String propName, Object propValue, IMetadataTable newOutputMetadata) {
        this(node, propName, propValue, null, newOutputMetadata, null, null);
    }

    public RepositoryChangeMetadataForSAPCommand(INode node, String propName, Object newPropValue,
            IMetadataTable newOutputMetadata, int index) {
        this(node, propName, newPropValue, null, newOutputMetadata, null, index);
    }

    public RepositoryChangeMetadataForSAPCommand(INode node, String propName, Object newPropValue, Object oldPropValue,
            IMetadataTable newOutputMetadata, IMetadataTable oldOutputMetadata, int index) {
        this(node, propName, newPropValue, oldPropValue, newOutputMetadata, oldOutputMetadata, new Integer(index));
    }

    private RepositoryChangeMetadataForSAPCommand(INode node, String propName, Object newPropValue, Object oldPropValue,
            IMetadataTable newOutputMetadata, IMetadataTable oldOutputMetadata, Integer index) {
        super();
        this.node = node;
        this.propName = propName;
        this.newPropValue = newPropValue;// /////
        this.newOutputMetadata = newOutputMetadata.clone();
        this.index = index;
        this.oldPropValue = oldPropValue;
        this.oldOutputMetadata = oldOutputMetadata;
    }

    @Override
    public void execute() {
        if (node == null) {
            return;
        }
        IElementParameter schemasTableParam = node.getElementParameter(propName);
        if (schemasTableParam != null) {

            List<Map<String, Object>> paramValues = (List<Map<String, Object>>) schemasTableParam.getValue();
            if (paramValues == null) {
                paramValues = new ArrayList<Map<String, Object>>();
                schemasTableParam.setValue(paramValues);
            }
            //
            Map<String, Object> valueMap = null;
            if (index != null) {
                if (paramValues.size() > 0) {
                    valueMap = paramValues.get(index.intValue());
                }

                setLineValue(paramValues, valueMap);

            } else { // for dnd
                boolean found = false;
                if (paramValues.size() > 0) {
                    for (Map<String, Object> line : paramValues) {
                        String schemaName = (String) line.get(ISAPConstant.FIELD_SCHEMA);
                        if (schemaName != null && schemaName.equals(newPropValue)) {
                            found = true;
                            IMetadataTable table = MetadataTool.getMetadataTableFromNode(node, (String) newPropValue);
                            if (table != null) {
                                MetadataTool.copyTable(newOutputMetadata, table);
                            }

                        }
                    }
                }
                if (!found) {
                    // create new line
                    setLineValue(paramValues, valueMap);
                }

            }

        }
    }

    private void setLineValue(List<Map<String, Object>> paramValues, Map<String, Object> valueMap) {
        //
        if (valueMap == null) {
            valueMap = new HashMap<String, Object>();
            paramValues.add(valueMap);
        }

        valueMap.put(ISAPConstant.FIELD_SCHEMA, newPropValue);// ////
        // if no value, set default field value
        Object fieldValue = valueMap.get(ISAPConstant.FIELD_SAP_ITERATE_OUT_TYPE);
        if (fieldValue == null || "".equals(fieldValue)) { //$NON-NLS-1$
            valueMap.put(ISAPConstant.FIELD_SAP_ITERATE_OUT_TYPE, "table_output");// ///
        }
        valueMap.put(ISAPConstant.FIELD_SCHEMA + ISAPConstant.REF_TYPE, ISAPConstant.REF_ATTR_REPOSITORY);

        Object tableNameValue = valueMap.get(ISAPConstant.FIELD_SAP_TABLE_NAME);
        if (tableNameValue == null || "".equals(tableNameValue)) {
            valueMap.put(ISAPConstant.FIELD_SAP_TABLE_NAME, newOutputMetadata.getLabel());
        }
        Object mapValue = valueMap.get(ISAPConstant.FIELD_MAPPING);
        List<IMetadataColumn> columnList = newOutputMetadata.getListColumns();
        StringBuffer buff = new StringBuffer();
        for (int i = 0; i < columnList.size(); i++) {
            IMetadataColumn metadataColumn = columnList.get(i);
            buff.append("\"");
            buff.append(metadataColumn.getLabel());
            if (i == columnList.size() - 1) {
                buff.append("\"");
            } else {
                buff.append("\",");
            }
        }
        if (mapValue == null || "".equals(mapValue)) {
            valueMap.put(ISAPConstant.FIELD_MAPPING, buff.toString());
        }

        if (oldOutputMetadata != null) {
            CorePlugin.getDefault().getDesignerCoreService().removeConnection(node, oldOutputMetadata.getTableName());
            node.getMetadataList().remove(oldOutputMetadata);
        }

        String uinqueTableName = node.getProcess().generateUniqueConnectionName(
                MultiSchemasUtil.getConnectionBaseName((String) newPropValue));
        newOutputMetadata.setLabel((String) newPropValue);
        newOutputMetadata.setTableName(uinqueTableName);
        node.getProcess().addUniqueConnectionName(uinqueTableName);
        node.getMetadataList().add(newOutputMetadata);
    }

}
