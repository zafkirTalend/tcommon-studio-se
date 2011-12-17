// ============================================================================
//
// Copyright (C) 2006-2011 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.model.utils;

import java.util.List;

import org.talend.core.model.components.IComponent;
import org.talend.core.model.metadata.builder.connection.Connection;
import org.talend.core.model.properties.Item;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.repository.model.RepositoryNode;

/**
 * hwang class global comment. Detailled comment
 */
public interface IDragAndDropServiceHandler {

    /**
     * DOC hwang Comment method "canHandle".
     * 
     * to judge can handle or not(base on connection)
     * 
     * @param connection - connection
     */
    public boolean canHandle(Connection connection);

    /**
     * DOC hwang Comment method "getComponentValue".
     * 
     * get parameter value of the connection
     * 
     * @param connection - connection
     * @param value - parameter name
     */
    public String getComponentValue(Connection connection, String value);

    /**
     * DOC hwang Comment method "filterNeededComponents".
     * 
     * get components list when you drag&drop a repositoryNode to processEditor
     * 
     * @param item - the Item of the selectedNode
     * @param seletetedNode - the repositoryNode you selected
     * @param type - the type of the selectedNode
     */
    public List<IComponent> filterNeededComponents(Item item, RepositoryNode seletetedNode, ERepositoryObjectType type);
}
