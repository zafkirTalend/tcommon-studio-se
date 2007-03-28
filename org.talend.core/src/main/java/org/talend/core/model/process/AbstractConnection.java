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
package org.talend.core.model.process;

import java.util.List;

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
    
    private boolean readOnly;
    
    private String uniqueName;

    private List<? extends IElementParameter> elementParameters;

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

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public List<? extends IElementParameter> getElementParameters() {
        return elementParameters;
    }

    public void setElementParameters(List<? extends IElementParameter> elementParameters) {
        this.elementParameters = elementParameters;
    }

    public void setTraceData(String traceData) {
    }
    
    public boolean isReadOnly() {
        return readOnly;
    }
    
    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    
    /**
     * Getter for uniqueName.
     * @return the uniqueName
     */
    public String getUniqueName() {
        return uniqueName;
    }

    
    /**
     * Sets the uniqueName.
     * @param uniqueName the uniqueName to set
     */
    public void setUniqueName(String uniqueName) {
        this.uniqueName = uniqueName;
    }
}
