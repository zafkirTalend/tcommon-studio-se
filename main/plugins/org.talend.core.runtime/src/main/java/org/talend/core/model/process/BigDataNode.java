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
import java.util.List;

import org.talend.core.model.components.IComponent;
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
            //            node.setIdentity("IDENTITY".equals(requiredInputType) && "IDENTITY".equals(node.getRequiredOutputType())); //$NON-NLS-1$ //$NON-NLS-2$
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

    // @Override
    // public void setIdentity(boolean isIdentity) {
    // this.isIdentity = isIdentity;
    // }
}
