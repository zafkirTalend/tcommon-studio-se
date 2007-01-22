// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
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
package org.talend.core.model.process;

import java.util.ArrayList;
import java.util.List;

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

    private List<? extends IConnection> outgoingConnections, incomingConnections;

    private List<IMetadataTable> metadataList;

    private String pluginFullName;

    private String uniqueName;

    private boolean activate;

    private boolean start;

    private boolean subProcessStart;

    private Boolean multipleMethods;

    private IProcess process;

    private IComponent component;
    
    private boolean readOnly;

    private Boolean hasConditionalOutputs = Boolean.FALSE;

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
    		if (param.getName().equals("UNIQUE_NAME")) {
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

    public Boolean isMultipleMethods() {
        return multipleMethods;
    }

    public void setMultipleMethods(Boolean multipleMethods) {
        this.multipleMethods = multipleMethods;
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
            if ((getCurrentActiveLinksNbInput(EConnectionType.FLOW_MAIN) == 0)
                    && (getCurrentActiveLinksNbInput(EConnectionType.FLOW_REF) == 0)
                    && (getCurrentActiveLinksNbInput(EConnectionType.ITERATE) == 0)) {
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
            connec = (IConnection) getIncomingConnections().get(j);
            if (!connec.getLineStyle().equals(EConnectionType.FLOW_REF)) {
                return ((AbstractNode) connec.getSource()).getSubProcessStartNode(withConditions);
            }
        }
        return null;
    }

    private int getCurrentActiveLinksNbInput(EConnectionType type) {
        int nb = 0;
        for (IConnection connection : getIncomingConnections()) {
            if (connection.isActivate() && connection.getLineStyle().equals(type)) {
                nb++;
            }
        }
        return nb;
    }

    /* (non-Javadoc)
     * @see org.talend.core.model.process.INode#hasConditionnalOutputs()
     */
    public Boolean hasConditionalOutputs() {
        return this.hasConditionalOutputs;
    }

    
    /**
     * Sets the hasConditionnalOutputs.
     * @param hasConditionalOutputs the hasConditionnalOutputs to set
     */
    public void setHasConditionalOutputs(boolean hasConditionalOutputs) {
        this.hasConditionalOutputs = hasConditionalOutputs;
    }

    
    
}
