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
package org.talend.core.runtime.services;

import java.util.List;

import org.eclipse.swt.graphics.Image;
import org.talend.core.IService;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.repository.model.RepositoryNode;

/**
 * created by ycbai on 2015年9月10日 Detailled comment
 *
 */
public interface IGenericWizardService extends IService {

    /**
     * Create repository nodes by component service.
     * 
     * @param curParentNode
     */
    public List<RepositoryNode> createNodesFromComponentService(RepositoryNode curParentNode);

    /**
     * Get all the names of generic type.
     * 
     * @return
     */
    public List<String> getGenericTypeNames();

    /**
     * 
     * Estimate whether <code>repObjType</code> is a generic type or not.
     * 
     * @param repObjType
     * @return
     */
    public boolean isGenericType(ERepositoryObjectType repObjType);

    /**
     * Get node image by node type name.
     * 
     * @param typeName
     * @return
     */
    public Image getNodeImage(String typeName);

    /**
     * Get wizard image by node type name.
     * 
     * @param typeName
     * @return
     */
    public Image getWiardImage(String typeName);

}
