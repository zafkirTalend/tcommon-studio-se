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
package org.talend.core.model.process;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;
import org.eclipse.draw2d.geometry.Point;
import org.talend.core.model.components.IComponent;
import org.talend.core.model.metadata.IMetadataTable;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public abstract class AbstractNode implements INode {

    private String componentName;

    List<? extends IElementParameter> elementParameters;

    private List<? extends IConnection> outgoingConnections = new ArrayList<IConnection>();

    private List<? extends IConnection> incomingConnections = new ArrayList<IConnection>();

    private List<IMetadataTable> metadataList;

    private String pluginFullName;

    private String uniqueName;

    private boolean activate;

    private boolean start;

    private boolean subProcessStart;

    private IProcess process;

    private IComponent component;

    private boolean readOnly;

    private IExternalNode externalNode;

    private Boolean hasConditionalOutputs = Boolean.FALSE;

    private Boolean isMultiplyingOutputs = Boolean.FALSE;

    private List<BlockCode> blocksCodeToClose;

    private boolean isThereLinkWithHash;

    private boolean isThereLinkWithMerge;

    private Map<INode, Integer> mergeInfo;

    private String label;

    protected List<? extends INodeConnector> listConnector;

    private INode designSubjobStartNode;

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public List<? extends IElementParameter> getElementParameters() {
        return elementParameters;
    }

    public void setElementParameters(List<? extends IElementParameter> elementParameters) {
        this.elementParameters = elementParameters;
    }

    public List<? extends IConnection> getIncomingConnections() {
        return incomingConnections;
    }

    public void setIncomingConnections(List<? extends IConnection> incomingConnections) {
        this.incomingConnections = incomingConnections;
    }

    public List<? extends IConnection> getOutgoingConnections() {
        return outgoingConnections;
    }

    public void setOutgoingConnections(List<? extends IConnection> outgoingConnections) {
        this.outgoingConnections = outgoingConnections;
    }

    public List<IMetadataTable> getMetadataList() {
        return metadataList;
    }

    public void setMetadataList(List<IMetadataTable> metadataList) {
        this.metadataList = metadataList;
    }

    public String getPluginFullName() {
        return pluginFullName;
    }

    public void setPluginFullName(String pluginFullName) {
        this.pluginFullName = pluginFullName;
    }

    public String getUniqueName() {
        return uniqueName;
    }

    public void setUniqueName(String uniqueName) {
        for (IElementParameter param : elementParameters) {
            if (param.getName().equals("UNIQUE_NAME")) { //$NON-NLS-1$
                param.setValue(uniqueName);
            }
        }
        this.uniqueName = uniqueName;
    }

    public boolean isActivate() {
        return activate;
    }

    public void setActivate(boolean activate) {
        this.activate = activate;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public boolean isSubProcessStart() {
        return subProcessStart;
    }

    public void setSubProcessStart(boolean subProcessStart) {
        this.subProcessStart = subProcessStart;
    }

    public void setPerformanceData(String perfData) {
        // null
    }

    public void setTraceData(String traceData) {
        // null
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.process.INode#getReturns()
     */
    public List<? extends INodeReturn> getReturns() {
        return new ArrayList<INodeReturn>();
    }

    public IProcess getProcess() {
        return process;
    }

    public void setProcess(IProcess process) {
        this.process = process;
    }

    public void setComponent(IComponent component) {
        this.component = component;
    }

    public IComponent getComponent() {
        return component;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    /**
     * Will return the first item of the subprocess. If "withCondition" is true, if there is links from type RunIf /
     * RunAfter / RunBefore, it will return the first element found. If "withCondition" is false, it will return the
     * first element with no active link from type Main/Ref/Iterate.<br>
     * <i><b>Note:</b></i> This function doesn't work if the node has several start points (will return a random
     * start node).
     * 
     * @param withCondition
     * @return Start Node found.
     */
    public AbstractNode getSubProcessStartNode(boolean withConditions) {
        if (!withConditions) {
            if ((getCurrentActiveLinksNbInput(EConnectionType.MAIN) == 0)
            // && (getCurrentActiveLinksNbInput(EConnectionType.FLOW_REF) == 0)
            // && (getCurrentActiveLinksNbInput(EConnectionType.ITERATE) == 0)
            ) {
                return this;
            }
        } else {
            int nb = 0;
            for (IConnection connection : getIncomingConnections()) {
                if (connection.isActivate()) {
                    nb++;
                }
            }
            if (nb == 0) {
                return this;
            }
        }
        IConnection connec;

        for (int j = 0; j < getIncomingConnections().size(); j++) {
            connec = getIncomingConnections().get(j);
            if (!connec.getLineStyle().equals(EConnectionType.FLOW_REF)) {
                return ((AbstractNode) connec.getSource()).getSubProcessStartNode(withConditions);
            }
        }
        return null;
    }

    private int getCurrentActiveLinksNbInput(int connectionCategory) {
        int nb = 0;
        for (IConnection connection : getIncomingConnections()) {
            if (connection.isActivate() && connection.getLineStyle().hasConnectionCategory(connectionCategory)) {
                nb++;
            }
        }
        return nb;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.process.INode#hasConditionnalOutputs()
     */
    public Boolean hasConditionalOutputs() {
        return this.hasConditionalOutputs;
    }

    /**
     * Sets the hasConditionnalOutputs.
     * 
     * @param hasConditionalOutputs the hasConditionnalOutputs to set
     */
    public void setHasConditionalOutputs(boolean hasConditionalOutputs) {
        this.hasConditionalOutputs = hasConditionalOutputs;
    }

    /**
     * Getter for isMultiplyingOutputs.
     * 
     * @return the isMultiplyingOutputs
     */
    public Boolean isMultiplyingOutputs() {
        return isMultiplyingOutputs;
    }

    /**
     * Sets the isMultiplyingOutputs.
     * 
     * @param isMultiplyingOutputs the isMultiplyingOutputs to set
     */
    public void setIsMultiplyingOutputs(Boolean isMultiplyingOutputs) {
        this.isMultiplyingOutputs = isMultiplyingOutputs;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer();
        buff.append(uniqueName + " - ");
        buff.append("inputs:(");
        for (int i = 0; i < incomingConnections.size(); i++) {
            buff.append(incomingConnections.get(i).getName());
            if (i < (incomingConnections.size() - 1)) {
                buff.append(",");
            }
        }
        buff.append(") ");
        buff.append("outputs:(");
        for (int i = 0; i < outgoingConnections.size(); i++) {
            buff.append(outgoingConnections.get(i).getName());
            if (i < (outgoingConnections.size() - 1)) {
                buff.append(",");
            }
        }
        buff.append(")");
        return buff.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.process.INode#getLocation()
     */
    public Point getLocation() {
        return null;
    }

    public IExternalNode getExternalNode() {
        return externalNode;
    }

    public void setExternalNode(IExternalNode externalNode) {
        this.externalNode = externalNode;
    }

    /**
     * Getter for isThereLinkWithHash.
     * 
     * @return the isThereLinkWithHash
     */
    public boolean isThereLinkWithHash() {
        return isThereLinkWithHash;
    }

    /**
     * Sets the isThereLinkWithHash.
     * 
     * @param isThereLinkWithHash the isThereLinkWithHash to set
     */
    public void setThereLinkWithHash(boolean isThereLinkWithHash) {
        this.isThereLinkWithHash = isThereLinkWithHash;
    }

    public IElementParameter getElementParameter(String name) {
        if (name.contains(":")) { // look for the parent first, then will retrieve the children
            StringTokenizer token = new StringTokenizer(name, ":");
            String parentId = token.nextToken();
            String childId = token.nextToken();
            for (int i = 0; i < elementParameters.size(); i++) {
                if (elementParameters.get(i).getName().equals(parentId)) {
                    IElementParameter parent = elementParameters.get(i);
                    return parent.getChildParameters().get(childId);
                }
            }
        } else {
            for (IElementParameter elementParam : elementParameters) {
                if (elementParam.getName().equals(name)) {
                    return elementParam;
                }
            }
        }

        // if not found, look for the name if it's the name of a children
        // this code is added only for compatibility and will be executed only one time
        // to initialize the child.
        // The parameters name are unique, so we just take the first one.
        for (IElementParameter elementParam : elementParameters) {
            for (String key : elementParam.getChildParameters().keySet()) {
                IElementParameter param = elementParam.getChildParameters().get(key);
                if (param.getName().equals(name)) {
                    return param;
                }
            }
        }
        return null;
    }

    public List<? extends IConnection> getOutgoingSortedConnections() {
        return org.talend.core.model.utils.NodeUtil.getOutgoingSortedConnections(this);
    }

    public List<? extends IConnection> getMainOutgoingConnections() {
        return org.talend.core.model.utils.NodeUtil.getMainOutgoingConnections(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.process.INode#getOutgoingConnections(org.talend.core.model.process.EConnectionType)
     */
    public List<? extends IConnection> getOutgoingConnections(EConnectionType connectionType) {
        return org.talend.core.model.utils.NodeUtil.getOutgoingConnections(this, connectionType);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.process.INode#getOutgoingConnections(java.lang.String)
     */
    public List<? extends IConnection> getOutgoingConnections(String connectorName) {
        return org.talend.core.model.utils.NodeUtil.getOutgoingConnections(this, connectorName);
    }

    public List<BlockCode> getBlocksCodeToClose() {
        return this.blocksCodeToClose;
    }

    public void setBlocksCodeToClose(List<BlockCode> blockCodesToClose) {
        this.blocksCodeToClose = blockCodesToClose;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.process.INode#renameData(java.lang.String, java.lang.String)
     */
    public void renameData(String oldName, String newName) {
        if (oldName.equals(newName)) {
            return;
        }

        for (IElementParameter param : this.getElementParameters()) {
            if (param.getValue() instanceof String) { // for TEXT / MEMO etc..
                String value = (String) param.getValue();
                if (value.contains(oldName)) {
                    param.setValue(value.replaceAll(oldName, newName));
                }
            } else if (param.getValue() instanceof List) { // for TABLE
                List<Map<String, Object>> tableValues = (List<Map<String, Object>>) param.getValue();
                for (Map<String, Object> line : tableValues) {
                    for (String key : line.keySet()) {
                        Object cellValue = line.get(key);
                        if (cellValue instanceof String) { // cell is text so
                            // rename data if
                            // needed
                            String value = (String) cellValue;
                            if (value.contains(oldName)) {
                                line.put(key, value.replaceAll(oldName, newName));
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean valueContains(String value, String toTest) {
        if (value.contains(toTest)) {
            Perl5Matcher matcher = new Perl5Matcher();
            Perl5Compiler compiler = new Perl5Compiler();
            Pattern pattern;

            try {
                pattern = compiler.compile("(.*" + toTest + "[^0-9]+.*)"); //$NON-NLS-1$
                if (matcher.contains(value, pattern)) {
                    return true;
                }
            } catch (MalformedPatternException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.process.INode#useData(java.lang.String)
     */
    public boolean useData(String name) {
        for (IElementParameter param : this.getElementParameters()) {
            if (param.getField() == EParameterFieldType.IMAGE) {
                continue;
            }
            if (param.getValue() instanceof String) { // for TEXT / MEMO etc..
                String value = (String) param.getValue();
                if (valueContains(value, name)) {
                    return true;
                }
            } else if (param.getValue() instanceof List) { // for TABLE
                List<Map<String, Object>> tableValues = (List<Map<String, Object>>) param.getValue();
                for (Map<String, Object> line : tableValues) {
                    for (String key : line.keySet()) {
                        Object cellValue = line.get(key);
                        if (cellValue instanceof String) { // cell is text so
                            // test data
                            if (valueContains((String) cellValue, name)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public Map<INode, Integer> getLinkedMergeInfo() {
        return mergeInfo;
    }

    public void setLinkedMergeInfo(Map<INode, Integer> mergeInfo) {
        this.mergeInfo = mergeInfo;
    }

    public boolean isThereLinkWithMerge() {
        return isThereLinkWithMerge;
    }

    public void setThereLinkWithMerge(boolean isThereLinkWithHash) {
        this.isThereLinkWithMerge = isThereLinkWithHash;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.process.INode#getIncomingConnections(org.talend.core.model.process.EConnectionType)
     */
    public List<? extends IConnection> getIncomingConnections(EConnectionType connectionType) {
        return org.talend.core.model.utils.NodeUtil.getIncomingConnections(this, connectionType);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.process.INode#getLabel()
     */
    public String getLabel() {
        return label;
    }

    public IMetadataTable getMetadataFromConnector(String connector) {
        if (metadataList == null) {
            return null;
        }
        for (IMetadataTable table : metadataList) {
            if (table.getAttachedConnector().equals(connector)) {
                return table;
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.process.INode#getConnectorFromName(java.lang.String)
     */
    public INodeConnector getConnectorFromName(String connName) {
        INodeConnector nodeConnector = null;
        int nbConn = 0;

        while ((nodeConnector == null) && (nbConn < listConnector.size())) {
            if (listConnector.get(nbConn).getName().equals(connName)) {
                nodeConnector = listConnector.get(nbConn);
            }
            nbConn++;
        }
        return nodeConnector;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.process.INode#reloadComponent(org.talend.core.model.components.IComponent,
     * java.util.Map)
     */
    public void reloadComponent(IComponent component, Map<String, Object> parameters) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.process.INode#getDesignSubjobStart()
     */
    public INode getDesignSubjobStartNode() {
        return designSubjobStartNode;
    }

    /**
     * Sets the designSubjobStart.
     * 
     * @param designSubjobStart the designSubjobStart to set
     */
    public void setDesignSubjobStartNode(INode designSubjobStartNode) {
        this.designSubjobStartNode = designSubjobStartNode;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.process.INode#isDesignSubjobStartNode()
     */
    public boolean isDesignSubjobStartNode() {
        return this.equals(designSubjobStartNode);
    }
}
