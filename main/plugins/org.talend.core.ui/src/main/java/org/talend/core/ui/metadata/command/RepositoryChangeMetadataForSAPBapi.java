// ============================================================================
//
// Copyright (C) 2006-2014 Talend Inc. - www.talend.com
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

import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.commands.Command;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.ISAPConstant;
import org.talend.core.model.metadata.MetadataSchemaType;
import org.talend.core.model.metadata.MetadataToolHelper;
import org.talend.core.model.metadata.MultiSchemasUtil;
import org.talend.core.model.metadata.builder.ConvertionHelper;
import org.talend.core.model.metadata.builder.connection.MetadataTable;
import org.talend.core.model.metadata.builder.connection.SAPFunctionParamData;
import org.talend.core.model.metadata.builder.connection.SAPFunctionParameter;
import org.talend.core.model.metadata.builder.connection.SAPFunctionUnit;
import org.talend.core.model.process.IElementParameter;
import org.talend.core.model.process.INode;
import org.talend.core.ui.CoreUIPlugin;
import org.talend.core.utils.TalendQuoteUtils;

/**
 * 
 * created by wchen on Sep 19, 2014 Detailled comment
 *
 */
public class RepositoryChangeMetadataForSAPBapi extends Command {

    private INode node;

    private IMetadataTable newMetadatTable, oldMetadataTable;

    private SAPFunctionUnit functionUnit;

    public RepositoryChangeMetadataForSAPBapi(INode node, SAPFunctionUnit functionUnit, IMetadataTable newMetadatTable,
            IMetadataTable oldMetadataTable) {
        super();
        this.node = node;
        this.functionUnit = functionUnit;
        if (newMetadatTable != null) {
            this.newMetadatTable = newMetadatTable.clone();
            newMetadatTable.setTableType(newMetadatTable.getTableType());
        }
        this.oldMetadataTable = oldMetadataTable;
    }

    @Override
    public void execute() {
        if (node == null) {
            return;
        }

        IElementParameter elementParameter = node.getElementParameter(ISAPConstant.SAP_FUNCTION);
        if (elementParameter != null && functionUnit != null) {
            elementParameter.setValue(TalendQuoteUtils.addQuotes(functionUnit.getName()));
        }

        // setup output schemas
        IElementParameter schemasTableParam = node.getElementParameter(ISAPConstant.TABLE_SCHEMAS);
        if (schemasTableParam != null) {
            List<Map<String, Object>> paramValues = (List<Map<String, Object>>) schemasTableParam.getValue();
            if (paramValues == null) {
                paramValues = new ArrayList<Map<String, Object>>();
                schemasTableParam.setValue(paramValues);
            }
            if (newMetadatTable != null) {
                // create new line
                createNewSchema(paramValues, newMetadatTable, MetadataSchemaType.OUTPUT.name());
            } else {
                if (functionUnit != null) {
                    EList<MetadataTable> tables = functionUnit.getTables();
                    for (MetadataTable table : tables) {
                        createNewSchema(paramValues, ConvertionHelper.convert(table), MetadataSchemaType.OUTPUT.name());
                    }
                }
            }
        }
        // setup output schemas
        IElementParameter inputTableParam = node.getElementParameter(ISAPConstant.MAPPING_INPUT);
        if (inputTableParam != null) {
            List<Map<String, Object>> paramValues = (List<Map<String, Object>>) inputTableParam.getValue();
            if (paramValues == null) {
                paramValues = new ArrayList<Map<String, Object>>();
                inputTableParam.setValue(paramValues);
            }
            if (newMetadatTable != null) {
                // create new line
                createNewSchema(paramValues, newMetadatTable, MetadataSchemaType.INPUT.name());
            } else {
                if (functionUnit != null) {
                    EList<MetadataTable> tables = functionUnit.getInputTables();
                    for (MetadataTable table : tables) {
                        createNewSchema(paramValues, ConvertionHelper.convert(table), MetadataSchemaType.INPUT.name());
                    }
                }
            }
        }
    }

    private void createNewSchema(List<Map<String, Object>> paramValues, IMetadataTable newMetadatTable, String tableType) {
        if (newMetadatTable != null && tableType.equals(newMetadatTable.getTableType())) {
            boolean found = false;
            if (paramValues.size() > 0) {
                List<IMetadataTable> metadataList = node.getMetadataList();
                for (IMetadataTable metadata : metadataList) {
                    if (tableType.equals(metadata.getTableType())) {
                        if (metadata.getLabel() != null && metadata.getLabel().equals(newMetadatTable.getLabel())) {
                            found = true;
                            MetadataToolHelper.copyTable(newMetadatTable, metadata);
                        }
                    }
                }
            }
            if (found) {
                return;
            }
            HashMap valueMap = new HashMap<String, Object>();
            paramValues.add(valueMap);

            String uinqueTableName = node.getProcess().generateUniqueConnectionName(
                    MultiSchemasUtil.getConnectionBaseName(newMetadatTable.getLabel()));
            String paramType = getParamType(newMetadatTable);
            if (paramType == null) {
                return;
            }

            valueMap.put(ISAPConstant.NAME, uinqueTableName);
            valueMap.put(ISAPConstant.TYPE, paramType);
            valueMap.put(ISAPConstant.FIELD_SCHEMA, uinqueTableName);
            if (MetadataSchemaType.INPUT.name().equals(tableType)) {
                valueMap.put(ISAPConstant.PARENT_ROW, "");
            }

            if (oldMetadataTable != null) {
                CoreUIPlugin.getDefault().getDesignerCoreService().removeConnection(node, oldMetadataTable.getTableName());
                node.getMetadataList().remove(oldMetadataTable);
            }

            newMetadatTable.setTableName(uinqueTableName);
            node.getProcess().addUniqueConnectionName(uinqueTableName);
            node.getMetadataList().add(newMetadatTable);
        }
    }

    private String getParamType(IMetadataTable table) {
        if (functionUnit == null) {
            return null;
        }
        SAPFunctionParamData paramData = functionUnit.getParamData();
        if (paramData == null) {
            return null;
        }

        // SCHEMAS is output param
        for (SAPFunctionParameter parameter : paramData.getOutputRoot().getChildren()) {
            if (parameter.getName().equals(table.getTableName())) {
                if (parameter.getType().equals(ISAPConstant.PARAM_STRUCTURE)) {
                    return ISAPConstant.PARAM_STRUCTURE.toUpperCase();
                } else if (parameter.getType().equals(ISAPConstant.PARAM_TABLE)) {
                    return ISAPConstant.PARAM_TABLE.toUpperCase();
                } else {
                    return ISAPConstant.PARAM_SINGLE.toUpperCase();
                }
            }

        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    @Override
    public void undo() {
        // TODO Auto-generated method stub
        super.undo();
    }

}
