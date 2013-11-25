// ============================================================================
//
// Copyright (C) 2006-2013 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.model.repository;

import java.util.List;

import org.talend.core.model.properties.Property;

/**
 * created by wchen on 2013-8-22 Detailled comment
 * 
 */
public interface IExtendedRepositoryNodeHandler {

    public Property getProperty(Object nodeObject);

    public Object getParent(Object repositoryNode);

    public ERepositoryObjectType getObjectType(Object repositoryNode);

    public Object getRepositoryNode(IRepositoryViewObject repositoryObject);

    public List getRepositoryNodeAndDependencies(IRepositoryViewObject repositoryObject);

}
