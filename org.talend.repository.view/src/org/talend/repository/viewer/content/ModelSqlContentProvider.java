// ============================================================================
//
// Copyright (C) 2006-2012 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.viewer.content;

import org.talend.repository.model.IRepositoryNode;
import org.talend.repository.model.nodes.IProjectRepositoryNode;

public class ModelSqlContentProvider extends LegacyRepositoryContentProvider {

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.talend.repository.example.viewer.content.LegacyRepositoryContentProvider#getRootNode(org.talend.repository
     * .model.ProjectRepositoryNode)
     */
    @Override
    protected IRepositoryNode getTopLevelNode(IProjectRepositoryNode projectRepositoryNode) {
        return projectRepositoryNode.getSQLPatternNode();
    }

}
