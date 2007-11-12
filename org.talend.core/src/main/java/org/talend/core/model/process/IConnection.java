// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the  agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
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
