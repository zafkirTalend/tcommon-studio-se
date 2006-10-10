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

import java.util.List;

import org.talend.core.model.components.IComponent;
import org.talend.core.model.components.IODataComponent;
import org.talend.core.model.metadata.IMetadataTable;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 * $Id$
 */
public interface INode extends IElement {

    /**
     * Gives the unique name of the node.
     * 
     * @return unique name
     */
    public String getUniqueName();

    /**
     * Return the start status of this node.
     * 
     * @return
     */
    public boolean isStart();

    /**
     * Return the activate status of this node.
     * 
     * @return
     */
    public boolean isActivate();

    /**
     * Return true if the node is the start of a sub process.
     * 
     * @return
     */
    public boolean isSubProcessStart();

    /**
     * Gives the name of the node. Typically it's the name of the component type.
     * 
     * @return
     */
    public String getComponentName();

    /**
     * Gives all incoming connections (only).
     * 
     * @return List of Connection
     */
    public List<? extends IConnection> getIncomingConnections();

    /**
     * Gives all outgoing connections (only).
     * 
     * @return List of Connection
     */
    public List<? extends IConnection> getOutgoingConnections();

    public String getPluginFullName();

    public boolean isMultipleMethods();

    /**
     * Set performance data on this node.
     * 
     * @param perfData Performance data (string to be parsed).
     */
    void setPerformanceData(String perfData);

    /**
     * Return list of Metadatas.
     * 
     * @return
     */
    public List<IMetadataTable> getMetadataList();

    List<? extends INodeReturn> getReturns();

    public IProcess getProcess();

    public void setProcess(IProcess process);

    public IComponent getComponent();

    // public IExternalNode getExternalNode();

    public void metadataChanged(IODataComponent dataComponent);
}
