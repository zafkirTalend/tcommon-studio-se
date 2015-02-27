// ============================================================================
//
// Copyright (C) 2006-2015 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.model.process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.talend.core.model.components.IComponent;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.MetadataTable;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 * $Id: AbstractNode.java 51166 2010-11-11 06:09:01Z wchen $
 * 
 */
public class BigDataNode extends AbstractNode implements IBigDataNode {

    private String outputType = null;

    private String inputType = null;

    private Map<String, List<IMetadataColumn>> keyList = new java.util.HashMap<String, List<IMetadataColumn>>();

    // private boolean isIdentity = false;

    public BigDataNode() {
    }

    public BigDataNode(IComponent component, String uniqueName) {
        setComponentName(component.getName());
        List<IMetadataTable> metaList = new ArrayList<IMetadataTable>();
        IMetadataTable metaTable = new MetadataTable();
        metaTable.setTableName(uniqueName);
        metaList.add(metaTable);
        setMetadataList(metaList);
        setComponent(component);
        setElementParameters(component.createElementParameters(this));
        setListConnector(component.createConnectors(this));
        setUniqueName(uniqueName);
        setHasConditionalOutputs(component.hasConditionalOutputs());
        setIsMultiplyingOutputs(component.isMultiplyingOutputs());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.process.IBigDataNode#getInputType()
     */
    @Override
    public String getIncomingType() {
        List<? extends IConnection> incomingConnections = getIncomingConnections(EConnectionType.FLOW_MAIN);
        if (incomingConnections.size() > 0) {
            IBigDataNode node = (IBigDataNode) incomingConnections.get(0).getSource();
            String requiredOutputType = node.getRequiredOutputType();
            return requiredOutputType != null ? requiredOutputType : node.getIncomingType();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.process.IBigDataNode#getOutputType()
     */
    @Override
    public String getOutgoingType() {
        List<? extends IConnection> outgoingConnections = getOutgoingConnections(EConnectionType.FLOW_MAIN);
        if (outgoingConnections.size() > 0) {
            IBigDataNode node = (IBigDataNode) outgoingConnections.get(0).getTarget();
            String requiredInputType = node.getRequiredInputType();
            return (requiredInputType != null && !node.isIdentity()) ? requiredInputType : node.getOutgoingType();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.process.IBigDataNode#getRequiredInputType()
     */
    @Override
    public String getRequiredInputType() {
        return this.inputType == null ? getComponent().getInputType() : this.inputType;
    }

    @Override
    public void setRequiredInputType(String requiredInputType) {
        this.inputType = requiredInputType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.process.IBigDataNode#getRequiredOutputType()
     */
    @Override
    public String getRequiredOutputType() {
        return this.outputType == null ? getComponent().getOutputType() : this.outputType;
    }

    @Override
    public void setRequiredOutputType(String requiredOutputType) {
        this.outputType = requiredOutputType;
    }

    @Override
    public boolean isIdentity() {
        return "IDENTITY".equals(getRequiredInputType()) && "IDENTITY".equals(getRequiredOutputType()); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public void setKeyList(IBigDataNode bigDataNode, String direction) {
        String[] partitionKey = bigDataNode.getComponent().getPartitioning().split("\\."); //$NON-NLS-1$
        boolean partitionKeyIsValid = partitionKey.length > 1 ? true : false;
        this.keyList = new HashMap<String, List<IMetadataColumn>>();
        List<IMetadataColumn> columnList = new ArrayList<IMetadataColumn>();
        if (partitionKeyIsValid) {
            IElementParameter parTableNode = bigDataNode.getElementParameter(partitionKey[0]);
            String clumnNodeListName = partitionKey[1];
            IElementParameter nodeElemForList = null;
            for (Object nodeItemList : parTableNode.getListItemsValue()) {
                if (parTableNode.isBasedOnSchema()) {
                    nodeElemForList = (IElementParameter) nodeItemList;
                    break;
                }
                if (((IElementParameter) nodeItemList).getFieldType().equals(EParameterFieldType.PREV_COLUMN_LIST)
                        || ((IElementParameter) nodeItemList).getFieldType().equals(EParameterFieldType.COLUMN_LIST)) {
                    nodeElemForList = (IElementParameter) nodeItemList;
                    break;
                }
            }

            if (nodeElemForList != null) {
                for (Map nodeColumnListMap : (List<Map>) parTableNode.getValue()) {
                    Object value = nodeColumnListMap.get(clumnNodeListName);
                    String colName = ""; //$NON-NLS-1$
                    if (nodeColumnListMap.get(clumnNodeListName) instanceof String) {
                        if (parTableNode.isBasedOnSchema()) {
                            if ("true".equals(value)) {
                                colName = (String) nodeColumnListMap.get("SCHEMA_COLUMN");
                            } else {
                                break;
                            }
                        } else {
                            colName = (String) value;
                        }
                    } else if (value instanceof Integer) {
                        Integer index = (Integer) value;
                        if (nodeElemForList.getListItemsDisplayName().length > index) {
                            colName = nodeElemForList.getListItemsDisplayName()[index];
                        }
                    }
                    columnList.add(bigDataNode.getMetadataList().get(0).getColumn(colName));
                }
                this.keyList.put(direction, columnList);
            }
        }
    }

    @Override
    public Map<String, List<IMetadataColumn>> getKeyList() {
        return keyList;
    }
}
