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
package org.talend.core;

import org.talend.core.model.process.INode;
import org.talend.core.model.properties.Item;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.repository.model.RepositoryNode;

/**
 * DOC nrousseau class global comment. Detailled comment
 */
public interface IESBService extends IService {

    public ERepositoryObjectType getServicesType();

    public String getServiceLabel(Item item, String linkedRepository);

    public void updateOperation(INode node, String linkedRepository, RepositoryNode selectNode);

    // public void setSelectedItem(Item, )

    // public AbstractMetadataObject getServicesOperation(Connection connection, String operationName);

    // public void changeOperationLabel(RepositoryNode newNode, INode node, Connection connection);

}
