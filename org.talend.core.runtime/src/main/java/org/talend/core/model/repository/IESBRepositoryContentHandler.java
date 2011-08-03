// ============================================================================
//
// Copyright (C) 2006-2010 Talend Inc. - www.talend.com
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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.resource.Resource;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.ui.runtime.image.IImage;
import org.talend.core.model.properties.Item;
import org.talend.repository.model.RepositoryNode;

/**
 * DOC hwang class global comment. Detailled comment
 */
public interface IESBRepositoryContentHandler {

    public Item createNewItem(ERepositoryObjectType type);

    public Resource create(IProject project, Item item, int classifierID, IPath path) throws PersistenceException;

    public Resource save(Item item) throws PersistenceException;

    public IImage getIcon(ERepositoryObjectType type);

    public boolean isRepObjType(ERepositoryObjectType type);

    public ERepositoryObjectType getRepositoryObjectType(Item item);

    public RepositoryNode createRepositoryNode(RepositoryNode node);

}
