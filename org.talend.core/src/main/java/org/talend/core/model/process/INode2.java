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

import org.eclipse.draw2d.geometry.Point;
import org.talend.core.model.metadata.IMetadataTable;

/**
 * DOC qzhang class global comment. Detailled comment
 */
public interface INode2 extends INode {

    /**
     * DOC qzhang Comment method "isDummy".
     * 
     * @return
     */
    boolean isDummy();

    /**
     * DOC qzhang Comment method "isELTComponent".
     * 
     * @return
     */
    boolean isELTComponent();

    /**
     * DOC qzhang Comment method "getConnectorFromType".
     * 
     * @param table
     * @return
     */
    INodeConnector getConnectorFromType(EConnectionType table);

    /**
     * DOC qzhang Comment method "checkAndRefreshNode".
     */
    void checkAndRefreshNode();

    /**
     * DOC qzhang Comment method "addOutput".
     * 
     * @param connection
     */
    void addOutput(IConnection connection);

    /**
     * DOC qzhang Comment method "addInput".
     * 
     * @param connection
     */
    void addInput(IConnection connection);

    /**
     * DOC qzhang Comment method "removeInput".
     * 
     * @param connection
     */
    void removeInput(IConnection connection);

    /**
     * DOC qzhang Comment method "removeOutput".
     * 
     * @param connection
     */
    void removeOutput(IConnection connection);

    /**
     * DOC qzhang Comment method "getMainBranch".
     * 
     * @return
     */
    INode2 getMainBranch();

    /**
     * DOC qzhang Comment method "getMetadataTable".
     * 
     * @param metaName
     * @return
     */
    IMetadataTable getMetadataTable(String metaName);

    /**
     * DOC qzhang Comment method "getProcessStartNode".
     * 
     * @param withConditions
     * @return
     */
    INode2 getProcessStartNode(boolean withConditions);

    /**
     * DOC qzhang Comment method "setLocation".
     * 
     * @param location
     */
    void setLocation(Point location);

}
