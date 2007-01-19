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
package org.talend.repository.model;

import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.talend.commons.exception.BusinessException;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.utils.data.container.RootContainer;
import org.talend.core.context.RepositoryContext;
import org.talend.core.model.general.Project;
import org.talend.core.model.metadata.builder.connection.MetadataTable;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.Property;
import org.talend.core.model.properties.Status;
import org.talend.core.model.properties.User;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.Folder;
import org.talend.core.model.repository.IRepositoryObject;
import org.talend.core.model.temp.ECodeLanguage;

/**
 * DOC qian class global comment. Repository factory use by client. Based on implementation provide by extension point
 * system. This class contains all commons treatments done by repository whatever implementation. <br/>
 * 
 * $Id: talend-code-templates.xml 1 2006-09-29 17:06:40 +0000 (星期五, 29 九月 2006) nrousseau $
 * 
 */
public interface IProxyRepositoryFactory {

    public abstract RepositoryContext getRepositoryContext();

    /**
     * @param project
     * @return
     * @throws PersistenceException
     * @see org.talend.repository.model.IMetadataFactory#getMetadataConnections(org.talend.core.model.general.Project)
     */
    public abstract List<ConnectionItem> getMetadataConnectionsItem() throws PersistenceException;

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.model.IRepositoryFactory#isValid(org.talend.core.model.general.Project,
     * org.talend.core.model.repository.ERepositoryObjectType, org.eclipse.core.runtime.IPath, java.lang.String)
     */
    public abstract boolean isNameAvailable(Item item, String name) throws PersistenceException;

    public abstract boolean isPathValid(ERepositoryObjectType type, IPath path, String label) throws PersistenceException;

    /**
     * @param label
     * @param description
     * @param language
     * @param author
     * @return
     * @throws PersistenceException
     * @see org.talend.core.model.repository.factories.IRepositoryFactory#createProject(java.lang.String,
     * java.lang.String, java.lang.String, org.talend.core.model.general.User)
     */
    public abstract Project createProject(String label, String description, ECodeLanguage language, User author)
            throws PersistenceException;

    /**
     * @param project
     * @param type
     * @param path
     * @param label
     * @throws PersistenceException
     * @see org.talend.core.model.repository.factories.IRepositoryFactory#createFolder(org.talend.core.model.general.Project,
     * org.talend.core.model.repository.ERepositoryObjectType, org.eclipse.core.runtime.IPath, java.lang.String)
     */
    public abstract Folder createFolder(ERepositoryObjectType type, IPath path, String label) throws PersistenceException;

    /**
     * @param project
     * @param type
     * @param path
     * @throws PersistenceException
     * @see org.talend.core.model.repository.factories.IRepositoryFactory#deleteFolder(org.talend.core.model.general.Project,
     * org.talend.core.model.repository.ERepositoryObjectType, org.eclipse.core.runtime.IPath)
     */
    public abstract void deleteFolder(ERepositoryObjectType type, IPath path) throws PersistenceException;

    /**
     * @param project
     * @param type
     * @param sourcePath
     * @param targetPath
     * @throws PersistenceException
     * @see org.talend.core.model.repository.factories.IRepositoryFactory#moveFolder(org.talend.core.model.general.Project,
     * org.talend.core.model.repository.ERepositoryObjectType, org.eclipse.core.runtime.IPath,
     * org.eclipse.core.runtime.IPath)
     */
    public abstract void moveFolder(ERepositoryObjectType type, IPath sourcePath, IPath targetPath) throws PersistenceException;

    /**
     * @param project
     * @return
     * @throws PersistenceException
     * @see org.talend.core.model.repository.factories.IBusinessProcessFactory#getBusinessProcess(org.talend.core.model.general.Project)
     */
    public abstract RootContainer<String, IRepositoryObject> getBusinessProcess() throws PersistenceException;

    /**
     * @param project
     * @return
     * @throws PersistenceException
     * @see org.talend.core.model.repository.factories.IDocumentationFactory#getDocumentation(org.talend.core.model.general.Project)
     */
    public abstract RootContainer<String, IRepositoryObject> getDocumentation() throws PersistenceException;

    /**
     * @param project
     * @return
     * @throws PersistenceException
     * @see org.talend.core.model.repository.factories.IMetadataFactory#getMetadataConnection(org.talend.core.model.general.Project)
     */
    public abstract RootContainer<String, IRepositoryObject> getMetadataConnection() throws PersistenceException;

    /**
     * @param project
     * @return
     * @throws PersistenceException
     * @see org.talend.core.model.repository.factories.IMetadataFactory#getMetadataFileDelimited(org.talend.core.model.general.Project)
     */
    public abstract RootContainer<String, IRepositoryObject> getMetadataFileDelimited() throws PersistenceException;

    /**
     * @param project
     * @return
     * @throws PersistenceException
     * @see org.talend.core.model.repository.factories.IRepositoryFactory#getNextId(org.talend.core.model.general.Project)
     */
    public abstract String getNextId();

    /**
     * @param project
     * @return
     * @throws PersistenceException
     * @see org.talend.core.model.repository.factories.IProcessFactory#getProcess(org.talend.core.model.general.Project)
     */
    public abstract RootContainer<String, IRepositoryObject> getProcess() throws PersistenceException;

    /**
     * @param project
     * @return
     * @throws PersistenceException
     * @see org.talend.core.model.repository.factories.IRoutineFactory#getRoutine(org.talend.core.model.general.Project)
     */
    public abstract RootContainer<String, IRepositoryObject> getRoutine() throws PersistenceException;

    /**
     * @param server
     * @param username
     * @param password
     * @return
     * @throws PersistenceException
     * @throws BusinessException
     * @see org.talend.core.model.repository.factories.IRepositoryFactory#readProject(java.lang.String,
     * java.lang.String, java.lang.String)
     */
    // TODO SML Rename in getProjects
    public abstract Project[] readProject() throws PersistenceException, BusinessException;

    /**
     * @param project
     * @param type
     * @param path
     * @param label
     * @throws PersistenceException
     * @see org.talend.core.model.repository.factories.IRepositoryFactory#renameFolder(org.talend.core.model.general.Project,
     * org.talend.core.model.repository.ERepositoryObjectType, org.eclipse.core.runtime.IPath, java.lang.String)
     */
    public abstract void renameFolder(ERepositoryObjectType type, IPath path, String label) throws PersistenceException;

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.model.IRepositoryFactory#deleteObject(org.talend.core.model.general.Project,
     * org.talend.core.model.repository.IRepositoryObject)
     */
    public abstract void deleteObjectLogical(IRepositoryObject objToDelete) throws PersistenceException;

    public abstract void deleteObjectPhysical(IRepositoryObject objToDelete) throws PersistenceException;

    public abstract void restoreObject(IRepositoryObject objToRestore, IPath path) throws PersistenceException;

    public abstract String getOldPath(IRepositoryObject obj) throws PersistenceException;

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.model.IRepositoryFactory#moveObject(org.talend.core.model.general.Project,
     * org.talend.core.model.repository.IRepositoryObject)
     */
    public abstract void moveObject(IRepositoryObject objToMove, IPath path) throws PersistenceException, BusinessException;

    /**
     * @param project
     * @return
     * @throws PersistenceException
     * @see org.talend.core.model.repository.factories.IMetadataFactory#getMetadataFilePositional(org.talend.core.model.general.Project)
     */
    public abstract RootContainer<String, IRepositoryObject> getMetadataFilePositional() throws PersistenceException;

    /**
     * @param project
     * @return
     * @throws PersistenceException
     * @see org.talend.core.model.repository.factories.IMetadataFactory#getMetadataFileRegexp(org.talend.core.model.general.Project)
     */
    public abstract RootContainer<String, IRepositoryObject> getMetadataFileRegexp() throws PersistenceException;

    /**
     * @param project
     * @return
     * @throws PersistenceException
     * @see org.talend.core.model.repository.factories.IMetadataFactory#getMetadataFileXml(org.talend.core.model.general.Project)
     */
    public abstract RootContainer<String, IRepositoryObject> getMetadataFileXml() throws PersistenceException;

    /**
     * @param project
     * @return
     * @throws PersistenceException
     * @see org.talend.core.model.repository.factories.IMetadataFactory#getMetadataFileLdif(org.talend.core.model.general.Project)
     */
    public abstract RootContainer<String, IRepositoryObject> getMetadataFileLdif() throws PersistenceException;

    public abstract void lock(IRepositoryObject obj) throws PersistenceException, BusinessException;

    /**
     * @param item
     * @throws PersistenceException
     * @throws BusinessException
     * @see org.talend.repository.model.IRepositoryFactory#lock(org.talend.core.model.properties.Item)
     */
    public abstract void lock(Item item) throws PersistenceException, BusinessException;

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.model.IRepositoryFactory#getAllVersion(org.talend.core.model.general.Project, int)
     */
    public abstract List<IRepositoryObject> getAllVersion(String id) throws PersistenceException;

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.model.IRepositoryFactory#getLastVersion(org.talend.core.model.general.Project, int)
     */
    public abstract IRepositoryObject getLastVersion(String id) throws PersistenceException;

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.model.IRepositoryFactory#getAll(org.talend.core.model.general.Project,
     * org.talend.core.model.repository.ERepositoryObjectType)
     */
    public abstract List<IRepositoryObject> getAll(ERepositoryObjectType type) throws PersistenceException;

    public abstract List<String> getFolders(ERepositoryObjectType type) throws PersistenceException;

    /**
     * @return
     * @see org.talend.repository.model.IRepositoryFactory#getDocumentationStatus()
     */
    public abstract List<Status> getDocumentationStatus() throws PersistenceException;

    /**
     * @return
     * @see org.talend.repository.model.IRepositoryFactory#getTechnicalStatus()
     */
    public abstract List<Status> getTechnicalStatus() throws PersistenceException;

    /**
     * @param list
     * @see org.talend.repository.model.IRepositoryFactory#setDocumentationStatus(java.util.List)
     */
    public abstract void setDocumentationStatus(List<Status> list) throws PersistenceException;

    /**
     * @param list
     * @see org.talend.repository.model.IRepositoryFactory#setTechnicalStatus(java.util.List)
     */
    public abstract void setTechnicalStatus(List<Status> list) throws PersistenceException;

    public abstract void setMigrationTasksDone(Project project, List<String> list) throws PersistenceException;

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.model.IRepositoryFactory#isServerValid()
     */
    // public abstract String isServerValid();
    public abstract void create(Item item, IPath path) throws PersistenceException;

    public abstract void save(Item item) throws PersistenceException;

    public abstract void save(Property property) throws PersistenceException;

    public abstract Item copy(Item item, IPath path) throws PersistenceException;

    public abstract Property reload(Property property);

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.model.IRepositoryFactory#unlock(org.talend.core.model.general.Project,
     * org.talend.core.model.repository.IRepositoryObject)
     */
    public abstract void unlock(IRepositoryObject obj) throws PersistenceException;

    /**
     * @param obj
     * @throws PersistenceException
     * @see org.talend.repository.model.IRepositoryFactory#unlock(org.talend.core.model.properties.Item)
     */
    public abstract void unlock(Item obj) throws PersistenceException;

    // public abstract boolean doesLoggedUserExist() throws PersistenceException;

    // public abstract void createUser() throws PersistenceException;

    public abstract void initialize();

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.model.IRepositoryFactory#getStatus(org.talend.core.model.properties.Item)
     */
    public abstract ERepositoryStatus getStatus(IRepositoryObject obj);

    public abstract ERepositoryStatus getStatus(Item item);

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.model.IRepositoryFactory#getStatusAndLockIfPossible(org.talend.core.model.properties.Item)
     */
    public abstract boolean isEditableAndLockIfPossible(Item item);

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.model.IRepositoryFactory#isEditable(org.talend.core.model.repository.IRepositoryObject)
     */
    public abstract boolean isEditableAndLockIfPossible(IRepositoryObject obj);

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.model.IRepositoryFactory#isPotentiallyEditable(org.talend.core.model.properties.Item)
     */
    public abstract boolean isPotentiallyEditable(Item item);

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.model.IRepositoryFactory#isPotentiallyEditable(org.talend.core.model.repository.IRepositoryObject)
     */
    public abstract boolean isPotentiallyEditable(IRepositoryObject obj);

    @Deprecated
    public boolean isDeleted(MetadataTable table);

}
