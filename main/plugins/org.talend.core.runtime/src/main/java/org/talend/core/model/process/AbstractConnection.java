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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.talend.core.model.metadata.IMetadataTable;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public abstract class AbstractConnection implements IConnection {

    private EConnectionType lineStyle;

    private IMetadataTable metadataTable;

    private String name;

    private INode source;

    private INode target;

    private boolean activate;

    private String condition;

    private String routeConnectionType;

    // TESB-8043
    private String endChoice;

    private String exceptionList;

    private boolean readOnly;

    private String uniqueName;

    private List<? extends IElementParameter> elementParameters = new ArrayList<IElementParameter>();

    private String connectorName;

    private int inputId;

    private int outputId;

    private boolean isMonitorConnection;

    public boolean isActivate() {
        return this.activate;
    }

    public void setActivate(boolean activate) {
        this.activate = activate;
    }

    public EConnectionType getLineStyle() {
        return this.lineStyle;
    }

    public void setLineStyle(EConnectionType lineStyle) {
        this.lineStyle = lineStyle;
    }

    public IMetadataTable getMetadataTable() {
        return this.metadataTable;
    }

    public void setMetadataTable(IMetadataTable metadataTable) {
        this.metadataTable = metadataTable;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
        if (uniqueName == null) {
            uniqueName = name;
        }
    }

    public INode getSource() {
        return this.source;
    }

    public void setSource(INode source) {
        this.source = source;
    }

    public INode getTarget() {
        return this.target;
    }

    public void setTarget(INode target) {
        this.target = target;
    }

    public String getCondition() {
        return this.condition;
    }

    public String getRouteConnectionType() {
        return this.routeConnectionType;
    }

    // TESB-8043
    public String getEndChoice() {
        return endChoice;
    }

    public String getExceptionList() {
        return this.exceptionList;
    }

    // TESB-8043
    public void setEndChoice(String endChoice) {
        this.endChoice = endChoice;
    }

    public void setRouteConnectionType(String routeConnectionType) {
        this.routeConnectionType = routeConnectionType;
    }

    public void setExceptionList(String exceptionList) {
        this.exceptionList = exceptionList;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public List<? extends IElementParameter> getElementParameters() {
        return elementParameters;
    }

    public IElementParameter getElementParameter(String name) {
        for (IElementParameter elementParam : elementParameters) {
            if (elementParam.getName().equals(name)) {
                return elementParam;
            }
        }
        return null;
    }

    public void setElementParameters(List<? extends IElementParameter> elementParameters) {
        this.elementParameters = elementParameters;
    }

    public void setTraceData(Map<String, TraceData> traceData) {
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    /**
     * Getter for uniqueName.
     * 
     * @return the uniqueName
     */
    public String getUniqueName() {
        return uniqueName;
    }

    /**
     * Sets the uniqueName.
     * 
     * @param uniqueName the uniqueName to set
     */
    public void setUniqueName(String uniqueName) {
        this.uniqueName = uniqueName;
    }

    /**
     * Getter for connectorName.
     * 
     * @return the connectorName
     */
    public String getConnectorName() {
        return connectorName;
    }

    /**
     * Sets the connectorName.
     * 
     * @param connectorName the connectorName to set
     */
    public void setConnectorName(String connectorName) {
        this.connectorName = connectorName;
    }

    public int getInputId() {
        return inputId;
    }

    public void setInputId(int inputId) {
        this.inputId = inputId;
    }

    public int getOutputId() {
        return outputId;
    }

    public void setOutputId(int outputId) {
        this.outputId = outputId;
    }

    @SuppressWarnings("unchecked")
    public boolean isUseByMetter() {
        INode sourceNode = this.getSource();
        List<INode> metterNodes = (List<INode>) sourceNode.getProcess().getNodesOfType("tFlowMeter"); //$NON-NLS-1$
        if (metterNodes.size() > 0) {

            Iterator<INode> it = (Iterator<INode>) metterNodes.iterator();
            while (it.hasNext()) {
                INode node = it.next();
                String absolute = (String) node.getElementParameter("ABSOLUTE").getValue(); //$NON-NLS-1$
                String reference = (String) node.getElementParameter("CONNECTIONS").getValue(); //$NON-NLS-1$

                if (absolute.equals("Relative") && reference.equals(this.getUniqueName())) { //$NON-NLS-1$
                    return true;
                }
            }
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "{Name=" + getName() + ", Table=" + getMetadataTable() + "}"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }

    public List<? extends IElementParameter> getElementParametersWithChildrens() {
        List<IElementParameter> fullListParam = new ArrayList<IElementParameter>(this.elementParameters);

        for (IElementParameter curParam : elementParameters) {
            for (String key : curParam.getChildParameters().keySet()) {
                IElementParameter childParam = curParam.getChildParameters().get(key);
                fullListParam.add(childParam);
            }
        }
        return fullListParam;
    }

    public boolean isTraceConnection() {
        return false;
    }

    public List<String> getEnabledTraceColumns() {
        return null;
    }

    public String getTracesCondition() {
        return null;
    }

    public INode getTmpNode() {
        // TODO Auto-generated method stub
        return null;
    }

    public Map<String, TraceData> getTraceData() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.process.IElement#getElementName()
     */
    public String getElementName() {
        return this.getUniqueName();
    }

    public String getMetaName() {
        // TODO Auto-generated method stub
        return null;
    }

    public INodeConnector getSourceNodeConnector() {
        // TODO Auto-generated method stub
        return null;
    }

    public INodeConnector getTargetNodeConnector() {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean isMonitorConnection() {
        return this.isMonitorConnection;
    }

    public void setMonitorConnection(boolean isMonitorConnection) {
        this.isMonitorConnection = isMonitorConnection;
    }

    public boolean isSubjobConnection() {
        // TODO Auto-generated method stub
        return false;
    }

    public void reconnect() {
        // TODO Auto-generated method stub

    }

    public void reconnect(INode newSource, INode oldTarget, EConnectionType newLineStyle) {
        // TODO Auto-generated method stub

    }

    public void setMetaName(String uniqueName) {
        // TODO Auto-generated method stub

    }

    public void setTraceConnection(boolean trace) {
        // TODO Auto-generated method stub

    }

    public void updateAllId() {
        // TODO Auto-generated method stub

    }

    public void updateName() {
        // TODO Auto-generated method stub

    }

    public Object getPropertyValue(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    public void setPropertyValue(String name, Object value) {
        // TODO Auto-generated method stub

    }

    public IElementParameter getElementParameterFromField(EParameterFieldType propertyType, EComponentCategory category) {
        // TODO Auto-generated method stub
        return null;
    }

    public IElementParameter getElementParameterFromField(EParameterFieldType dbtable) {
        // TODO Auto-generated method stub
        return null;
    }

    public <T extends IElementParameter> void addElementParameter(T elementParameter) {
        ((List<T>) elementParameters).add(elementParameter);
    }

    public void disconnect() {
        // TODO Auto-generated method stub

    }

    public Object getPropertyValue(String name, String paramName) {
        // TODO Auto-generated method stub
        return null;
    }
}
