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

import org.talend.core.model.metadata.IMetadataTable;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public interface IConnection extends IElement {

    String getCondition();

    /**
     * Get the node target of the connection.
     * 
     * @return Node
     */
    public INode getTarget();

    /**
     * Get the node source of the connection.
     * 
     * @return Node
     */
    public INode getSource();

    /**
     * Get the name of the connection.
     * 
     * @return
     */
    public String getName();
    
    public String getUniqueName();

    /**
     * Return the given style of the connection.
     * 
     * @see org.talend.designer.core.ui.editor.connections.EConnectionType
     * @return int value of the style
     */
    public EConnectionType getLineStyle();

    public IMetadataTable getMetadataTable();

    public boolean isActivate();

    public void setTraceData(String traceData);
    
    public String getConnectorName();
    
    public int getInputId();
    
    public boolean isUseByMetter();
}
