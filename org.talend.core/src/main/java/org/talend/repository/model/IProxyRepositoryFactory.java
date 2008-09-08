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
package org.talend.repository.model;

import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.talend.commons.exception.BusinessException;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.utils.data.container.RootContainer;
import org.talend.core.context.RepositoryContext;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.model.general.ModuleNeeded;
import org.talend.core.model.general.Project;
import org.talend.core.model.metadata.builder.connection.MetadataTable;
import org.talend.core.model.process.IContext;
import org.talend.core.model.process.IProcess;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.core.model.properties.ContextItem;
import org.talend.core.model.properties.FolderItem;
import org.talend.core.model.properties.InformationLevel;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.Property;
import org.talend.core.model.properties.SpagoBiServer;
import org.talend.core.model.properties.Status;
import org.talend.core.model.properties.User;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.Folder;
import org.talend.core.model.repository.IRepositoryObject;

/**
 * DOC qian class global comment. Repository factory use by client. Based on implementation provide by extension point
 * system. This class contains all commons treatments done by repository whatever implementation. <br/>
 * 
 * $Id: talend-code-templates.xml 1 2006-09-29 17:06:40 +0000 (星期五, 29 九月 2006) nrousseau $
 * 
 */
public interface IProxyRepositoryFactory {

	public abstract RepositoryContext getRepositoryContext();

	public void refreshJobPictureFolder(String picFolder);

	public void refreshDocumentationFolder(String docFolder);

	public void addPropertyChangeListener(PropertyChangeListener l);

	public void removePropertyChangeListener(PropertyChangeListener l);

	/**
	 * @param project
	 * @return
	 * @throws PersistenceException
	 * @see org.talend.repository.model.IMetadataFactory#getMetadataConnections(org.talend.core.model.general.Project)
	 */
	public abstract List<ConnectionItem> getMetadataConnectionsItem()
			throws PersistenceException;

	public abstract List<ContextItem> getContextItem()
			throws PersistenceException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.talend.repository.model.IRepositoryFactory#isValid(org.talend.core
	 * .model.general.Project,
	 * org.talend.core.model.repository.ERepositoryObjectType,
	 * org.eclipse.core.runtime.IPath, java.lang.String)
	 */
	public abstract boolean isNameAvailable(Item item, String name)
			throws PersistenceException;

	public abstract boolean isNameAvailable(Project project, Item item,
			String name) throws PersistenceException;

	public abstract boolean isPathValid(ERepositoryObjectType type, IPath path,
			String label) throws PersistenceException;

	public abstract boolean isPathValid(Project proejct,
			ERepositoryObjectType type, IPath path, String label)
			throws PersistenceException;

	/**
	 * @param label
	 * @param description
	 * @param language
	 * @param author
	 * @return
	 * @throws PersistenceException
	 * @see org.talend.core.model.repository.factories.IRepositoryFactory#createProject(java.lang.String,
	 *      java.lang.String, java.lang.String,
	 *      org.talend.core.model.general.User)
	 */
	public abstract Project createProject(String label, String description,
			ECodeLanguage language, User author) throws PersistenceException;

	public abstract void saveProject(Project project)
			throws PersistenceException;

	/**
	 * @param project
	 * @param type
	 * @param path
	 * @param label
	 * @throws PersistenceException
	 * @see org.talend.core.model.repository.factories.IRepositoryFactory#createFolder(org.talend.core.model.general.Project,
	 *      org.talend.core.model.repository.ERepositoryObjectType,
	 *      org.eclipse.core.runtime.IPath, java.lang.String)
	 */
	public abstract Folder createFolder(ERepositoryObjectType type, IPath path,
			String label) throws PersistenceException;

	public abstract Folder createFolder(Project project,
			ERepositoryObjectType type, IPath path, String label)
			throws PersistenceException;

	/**
	 * @param project
	 * @param type
	 * @param path
	 * @throws PersistenceException
	 * @see org.talend.core.model.repository.factories.IRepositoryFactory#deleteFolder(org.talend.core.model.general.Project,
	 *      org.talend.core.model.repository.ERepositoryObjectType,
	 *      org.eclipse.core.runtime.IPath)
	 */
	public abstract void deleteFolder(ERepositoryObjectType type, IPath path)
			throws PersistenceException;

	public abstract void deleteFolder(Project project,
			ERepositoryObjectType type, IPath path) throws PersistenceException;

	/**
	 * @param project
	 * @param type
	 * @param sourcePath
	 * @param targetPath
	 * @throws PersistenceException
	 * @see org.talend.core.model.repository.factories.IRepositoryFactory#moveFolder(org.talend.core.model.general.Project,
	 *      org.talend.core.model.repository.ERepositoryObjectType,
	 *      org.eclipse.core.runtime.IPath, org.eclipse.core.runtime.IPath)
	 */
	public abstract void moveFolder(ERepositoryObjectType type,
			IPath sourcePath, IPath targetPath) throws PersistenceException;

	/**
	 * @param project
	 * @return
	 * @throws PersistenceException
	 * @see org.talend.core.model.repository.factories.IBusinessProcessFactory#getBusinessProcess(org.talend.core.model.general.Project)
	 */
	public abstract RootContainer<String, IRepositoryObject> getBusinessProcess(
			Project project) throws PersistenceException;

	/**
	 * @return
	 * @throws PersistenceException
	 * @see org.talend.core.model.repository.factories.IBusinessProcessFactory#getBusinessProcess(org.talend.core.model.general.Project)
	 */
	public abstract RootContainer<String, IRepositoryObject> getBusinessProcess()
			throws PersistenceException;

	/**
	 * @param project
	 * @return
	 * @throws PersistenceException
	 * @see org.talend.core.model.repository.factories.IDocumentationFactory#getDocumentation(org.talend.core.model.general.Project)
	 */
	public abstract RootContainer<String, IRepositoryObject> getDocumentation(
			Project project) throws PersistenceException;

	/**
	 * @return
	 * @throws PersistenceException
	 * @see org.talend.core.model.repository.factories.IDocumentationFactory#getDocumentation(org.talend.core.model.general.Project)
	 */
	public abstract RootContainer<String, IRepositoryObject> getDocumentation()
			throws PersistenceException;

	/**
	 * @param project
	 * @return
	 * @throws PersistenceException
	 * @see org.talend.core.model.repository.factories.IMetadataFactory#getMetadataConnection(org.talend.core.model.general.Project)
	 */
	public abstract RootContainer<String, IRepositoryObject> getMetadataConnection(
			Project project) throws PersistenceException;

	/**
	 * @return
	 * @throws PersistenceException
	 * @see org.talend.core.model.repository.factories.IMetadataFactory#getMetadataConnection(org.talend.core.model.general.Project)
	 */
	public abstract RootContainer<String, IRepositoryObject> getMetadataConnection()
			throws PersistenceException;

	/**
	 * @param project
	 * @return
	 * @throws PersistenceException
	 * @see org.talend.core.model.repository.factories.IMetadataFactory#getMetadataConnection(org.talend.core.model.general.Project)
	 */
	public abstract RootContainer<String, IRepositoryObject> getMetadataSAPConnection(
			Project project) throws PersistenceException;

	/**
	 * @return
	 * @throws PersistenceException
	 * @see org.talend.core.model.repository.factories.IMetadataFactory#getMetadataConnection(org.talend.core.model.general.Project)
	 */
	public abstract RootContainer<String, IRepositoryObject> getMetadataSAPConnection()
			throws PersistenceException;

	/**
	 * @param project
	 * @return
	 * @throws PersistenceException
	 * @see org.talend.core.model.repository.factories.IMetadataFactory#getMetadataFileDelimited(org.talend.core.model.general.Project)
	 */
	public abstract RootContainer<String, IRepositoryObject> getMetadataFileDelimited(
			Project project) throws PersistenceException;

	/**
	 * @param project
	 * @return
	 * @throws PersistenceException
	 * @see org.talend.core.model.repository.factories.IMetadataFactory#getMetadataFileDelimited(org.talend.core.model.general.Project)
	 */
	public abstract RootContainer<String, IRepositoryObject> getMetadataFileDelimited()
			throws PersistenceException;

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
	public abstract RootContainer<String, IRepositoryObject> getProcess(
			Project project) throws PersistenceException;

	/**
	 * @param project
	 * @return
	 * @throws PersistenceException
	 * @see org.talend.core.model.repository.factories.IProcessFactory#getProcess(org.talend.core.model.general.Project)
	 */
	public abstract RootContainer<String, IRepositoryObject> getProcess()
			throws PersistenceException;

	/**
	 * DOC qzhang Comment method "getJoblets".
	 * 
	 * @return
	 * @throws PersistenceException
	 */
	public abstract RootContainer<String, IRepositoryObject> getJoblets(
			Project project) throws PersistenceException;

	/**
	 * DOC qzhang Comment method "getJoblets".
	 * 
	 * @return
	 * @throws PersistenceException
	 */
	public abstract RootContainer<String, IRepositoryObject> getJoblets()
			throws PersistenceException;

	/**
	 * @param project
	 * @return
	 * @throws PersistenceException
	 * @see org.talend.core.model.repository.factories.IProcessFactory#getContext(org.talend.core.model.general.Project)
	 */
	public abstract RootContainer<String, IRepositoryObject> getContext(
			Project project) throws PersistenceException;

	/**
	 * @param project
	 * @return
	 * @throws PersistenceException
	 * @see org.talend.core.model.repository.factories.IProcessFactory#getContext(org.talend.core.model.general.Project)
	 */
	public abstract RootContainer<String, IRepositoryObject> getContext()
			throws PersistenceException;

	/**
	 * @param project
	 * @return
	 * @throws PersistenceException
	 * @see org.talend.core.model.repository.factories.IRoutineFactory#getRoutine(org.talend.core.model.general.Project)
	 */
	public abstract RootContainer<String, IRepositoryObject> getRoutine(
			Project project) throws PersistenceException;

	/**
	 * @return
	 * @throws PersistenceException
	 * @see org.talend.core.model.repository.factories.IRoutineFactory#getRoutine(org.talend.core.model.general.Project)
	 */
	public abstract RootContainer<String, IRepositoryObject> getRoutine()
			throws PersistenceException;

	public abstract RootContainer<String, IRepositoryObject> getMetadataSQLPattern(
			Project project) throws PersistenceException;

	public abstract RootContainer<String, IRepositoryObject> getMetadataSQLPattern()
			throws PersistenceException;

	public abstract RootContainer<String, IRepositoryObject> getSnippets(
			Project project) throws PersistenceException;

	public abstract RootContainer<String, IRepositoryObject> getSnippets()
			throws PersistenceException;

	/**
	 * yzhang Comment method "getRecycleBinItems".
	 * 
	 * @return
	 * @throws PersistenceException
	 */
	public abstract List<IRepositoryObject> getRecycleBinItems()
			throws PersistenceException;

	public abstract List<IRepositoryObject> getRecycleBinItems(Project project)
			throws PersistenceException;

	/**
	 * @param server
	 * @param username
	 * @param password
	 * @return
	 * @throws PersistenceException
	 * @throws BusinessException
	 * @see org.talend.core.model.repository.factories.IRepositoryFactory#readProject(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	// TODO SML Rename in getProjects
	public abstract Project[] readProject() throws PersistenceException,
			BusinessException;

	/**
	 * @param project
	 * @param type
	 * @param path
	 * @param label
	 * @throws PersistenceException
	 * @see org.talend.core.model.repository.factories.IRepositoryFactory#renameFolder(org.talend.core.model.general.Project,
	 *      org.talend.core.model.repository.ERepositoryObjectType,
	 *      org.eclipse.core.runtime.IPath, java.lang.String)
	 */
	public abstract void renameFolder(ERepositoryObjectType type, IPath path,
			String label) throws PersistenceException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.talend.repository.model.IRepositoryFactory#deleteObject(org.talend
	 * .core.model.general.Project,
	 * org.talend.core.model.repository.IRepositoryObject)
	 */
	public abstract void deleteObjectLogical(IRepositoryObject objToDelete)
			throws PersistenceException, BusinessException;

	public abstract void deleteObjectLogical(Project project,
			IRepositoryObject objToDelete) throws PersistenceException,
			BusinessException;

	public abstract void deleteObjectPhysical(IRepositoryObject objToDelete)
			throws PersistenceException;

	public abstract void deleteObjectPhysical(Project project,
			IRepositoryObject objToDelete) throws PersistenceException,
			BusinessException;

	public abstract void restoreObject(IRepositoryObject objToRestore,
			IPath path) throws PersistenceException, BusinessException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.talend.repository.model.IRepositoryFactory#moveObject(org.talend.
	 * core.model.general.Project,
	 * org.talend.core.model.repository.IRepositoryObject)
	 */
	public abstract void moveObject(IRepositoryObject objToMove, IPath path,
			IPath... sourcePath) throws PersistenceException, BusinessException;

	/**
	 * @param project
	 * @return
	 * @throws PersistenceException
	 * @see org.talend.core.model.repository.factories.IMetadataFactory#getMetadataFilePositional(org.talend.core.model.general.Project)
	 */
	public abstract RootContainer<String, IRepositoryObject> getMetadataFilePositional(
			Project project) throws PersistenceException;

	/**
	 * @return
	 * @throws PersistenceException
	 * @see org.talend.core.model.repository.factories.IMetadataFactory#getMetadataFilePositional(org.talend.core.model.general.Project)
	 */
	public abstract RootContainer<String, IRepositoryObject> getMetadataFilePositional()
			throws PersistenceException;

	/**
	 * @param project
	 * @return
	 * @throws PersistenceException
	 * @see org.talend.core.model.repository.factories.IMetadataFactory#getMetadataFileRegexp(org.talend.core.model.general.Project)
	 */
	public abstract RootContainer<String, IRepositoryObject> getMetadataFileRegexp(
			Project project) throws PersistenceException;

	/**
	 * @return
	 * @throws PersistenceException
	 * @see org.talend.core.model.repository.factories.IMetadataFactory#getMetadataFileRegexp(org.talend.core.model.general.Project)
	 */
	public abstract RootContainer<String, IRepositoryObject> getMetadataFileRegexp()
			throws PersistenceException;

	/**
	 * @param project
	 * @return
	 * @throws PersistenceException
	 * @see org.talend.core.model.repository.factories.IMetadataFactory#getMetadataFileXml(org.talend.core.model.general.Project)
	 */
	public abstract RootContainer<String, IRepositoryObject> getMetadataFileXml(
			Project project) throws PersistenceException;

	/**
	 * @return
	 * @throws PersistenceException
	 * @see org.talend.core.model.repository.factories.IMetadataFactory#getMetadataFileXml(org.talend.core.model.general.Project)
	 */
	public abstract RootContainer<String, IRepositoryObject> getMetadataFileXml()
			throws PersistenceException;

	/**
	 * @param project
	 * @return
	 * @throws PersistenceException
	 * @see org.talend.core.model.repository.factories.IMetadataFactory#getMetadataFileLdif(org.talend.core.model.general.Project)
	 */
	public abstract RootContainer<String, IRepositoryObject> getMetadataFileLdif(
			Project project) throws PersistenceException;

	/**
	 * @return
	 * @throws PersistenceException
	 * @see org.talend.core.model.repository.factories.IMetadataFactory#getMetadataFileLdif(org.talend.core.model.general.Project)
	 */
	public abstract RootContainer<String, IRepositoryObject> getMetadataFileLdif()
			throws PersistenceException;

	public abstract RootContainer<String, IRepositoryObject> getMetadataFileExcel(
			Project project) throws PersistenceException;

	public abstract RootContainer<String, IRepositoryObject> getMetadataFileExcel()
			throws PersistenceException;

	public abstract RootContainer<String, IRepositoryObject> getMetadataSalesforceSchema(
			Project project) throws PersistenceException;

	public abstract RootContainer<String, IRepositoryObject> getMetadataSalesforceSchema()
			throws PersistenceException;

	public abstract void lock(IRepositoryObject obj)
			throws PersistenceException, BusinessException;

	/**
	 * @param item
	 * @throws PersistenceException
	 * @throws BusinessException
	 * @see org.talend.repository.model.IRepositoryFactory#lock(org.talend.core.model.properties.Item)
	 */
	public abstract void lock(Item item) throws PersistenceException,
			BusinessException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.talend.repository.model.IRepositoryFactory#getAllVersion(org.talend
	 * .core.model.general.Project, int)
	 */
	public abstract List<IRepositoryObject> getAllVersion(Project project,
			String id) throws PersistenceException;

	public abstract List<IRepositoryObject> getAllVersion(String id)
			throws PersistenceException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.talend.repository.model.IRepositoryFactory#getLastVersion(org.talend
	 * .core.model.general.Project, int)
	 */
	public abstract IRepositoryObject getLastVersion(Project project, String id)
			throws PersistenceException;

	public abstract IRepositoryObject getLastVersion(String id)
			throws PersistenceException;

	public abstract IRepositoryObject getSpecificVersion(Project project,
			String id, String version) throws PersistenceException;

	public abstract IRepositoryObject getSpecificVersion(String id,
			String version) throws PersistenceException;

	public abstract List<IRepositoryObject> getAll(ERepositoryObjectType type)
			throws PersistenceException;

	public abstract List<IRepositoryObject> getAll(ERepositoryObjectType type,
			boolean withDeleted) throws PersistenceException;

	public abstract List<IRepositoryObject> getAll(ERepositoryObjectType type,
			boolean withDeleted, boolean allVersions)
			throws PersistenceException;

	public abstract List<IRepositoryObject> getAll(Project project,
			ERepositoryObjectType type) throws PersistenceException;

	public abstract List<IRepositoryObject> getAll(Project project,
			ERepositoryObjectType type, boolean withDeleted)
			throws PersistenceException;

	public abstract List<IRepositoryObject> getAll(Project project,
			ERepositoryObjectType type, boolean withDeleted, boolean allVersions)
			throws PersistenceException;

	public abstract List<String> getFolders(ERepositoryObjectType type)
			throws PersistenceException;

	/**
	 * @return
	 * @see org.talend.repository.model.IRepositoryFactory#getDocumentationStatus()
	 */
	public abstract List<Status> getDocumentationStatus()
			throws PersistenceException;

	/**
	 * @return
	 * @see org.talend.repository.model.IRepositoryFactory#getTechnicalStatus()
	 */
	public abstract List<Status> getTechnicalStatus()
			throws PersistenceException;

	/**
	 * @return
	 * @see org.talend.repository.model.IRepositoryFactory#getSpagoBiServer()
	 */
	// TODO SML Remove
	// public abstract List<SpagoBiServer> getSpagoBiServer() throws
	// PersistenceException;
	/**
	 * @param list
	 * @see org.talend.repository.model.IRepositoryFactory#setDocumentationStatus(java.util.List)
	 */
	public abstract void setDocumentationStatus(List<Status> list)
			throws PersistenceException;

	/**
	 * @param list
	 * @see org.talend.repository.model.IRepositoryFactory#setTechnicalStatus(java.util.List)
	 */
	public abstract void setTechnicalStatus(List<Status> list)
			throws PersistenceException;

	/**
	 * @param list
	 * @see org.talend.repository.model.IRepositoryFactory#setSpagoBiServer(java.util.List)
	 */
	public abstract void setSpagoBiServer(List<SpagoBiServer> list)
			throws PersistenceException;

	public abstract void setMigrationTasksDone(Project project,
			List<String> list) throws PersistenceException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.talend.repository.model.IRepositoryFactory#isServerValid()
	 */
	// public abstract String isServerValid();
	public abstract void create(Item item, IPath path, boolean... isImportItem)
			throws PersistenceException;

	public abstract void create(Project project, Item item, IPath path,
			boolean... isImportItem) throws PersistenceException;

	public abstract void save(Item item, boolean... isMigrationTask)
			throws PersistenceException;

	public abstract void save(Project project, Item item,
			boolean... isMigrationTask) throws PersistenceException;

	public abstract void save(Property property,
			String... originalNameAndVersion) throws PersistenceException;

	public abstract void save(Project project, Property property,
			String... originalNameAndVersion) throws PersistenceException;

	public abstract Item copy(Item item, IPath path)
			throws PersistenceException, BusinessException;

	public abstract Item copy(Item item, IPath path,
			boolean changeLabelWithCopyPrefix) throws PersistenceException,
			BusinessException;

	public abstract void saveCopy(Item item, Item targetItem)
			throws PersistenceException;

	public abstract Property reload(Property property)
			throws PersistenceException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.talend.repository.model.IRepositoryFactory#unlock(org.talend.core
	 * .model.general.Project,
	 * org.talend.core.model.repository.IRepositoryObject)
	 */
	public abstract void unlock(IRepositoryObject obj)
			throws PersistenceException;

	/**
	 * @param obj
	 * @throws PersistenceException
	 * @see org.talend.repository.model.IRepositoryFactory#unlock(org.talend.core.model.properties.Item)
	 */
	public abstract void unlock(Item obj) throws PersistenceException;

	// public abstract boolean doesLoggedUserExist() throws
	// PersistenceException;

	// public abstract void createUser() throws PersistenceException;

	public abstract void initialize() throws PersistenceException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.talend.repository.model.IRepositoryFactory#getStatus(org.talend.core
	 * .model.properties.Item)
	 */
	public abstract ERepositoryStatus getStatus(IRepositoryObject obj);

	public abstract ERepositoryStatus getStatus(Item item);

	public abstract ERepositoryStatus getStatus(InformationLevel level);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.talend.repository.model.IRepositoryFactory#getStatusAndLockIfPossible
	 * (org.talend.core.model.properties.Item)
	 */
	public abstract boolean isEditableAndLockIfPossible(Item item);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.talend.repository.model.IRepositoryFactory#isEditable(org.talend.
	 * core.model.repository.IRepositoryObject)
	 */
	public abstract boolean isEditableAndLockIfPossible(IRepositoryObject obj);

	public org.talend.core.model.properties.Project getProject(Item item);

	/*
	 * (non-Javadoc)
	 * 
	 * 
	 * @seeorg.talend.repository.model.IRepositoryFactory#isPotentiallyEditable(org
	 * .talend.core.model.repository. IRepositoryObject)
	 */
	public abstract boolean isPotentiallyEditable(IRepositoryObject obj);

	@Deprecated
	public boolean isDeleted(MetadataTable table);

	public boolean isUserReadOnlyOnCurrentProject();

	public abstract List<org.talend.core.model.properties.Project> getReferencedProjects();

	public void removeContextFiles(IProcess process, IContext context)
			throws Exception;

	public Boolean hasChildren(Object parent);

	public abstract RootContainer<String, IRepositoryObject> getMetadataGenericSchema(
			Project project) throws PersistenceException;

	public abstract RootContainer<String, IRepositoryObject> getMetadataGenericSchema()
			throws PersistenceException;

	public abstract RootContainer<String, IRepositoryObject> getMetadataLDAPSchema(
			Project project) throws PersistenceException;

	public abstract RootContainer<String, IRepositoryObject> getMetadataLDAPSchema()
			throws PersistenceException;

	public abstract RootContainer<String, IRepositoryObject> getMetadataWSDLSchema(
			Project project) throws PersistenceException;

	public abstract RootContainer<String, IRepositoryObject> getMetadataWSDLSchema()
			throws PersistenceException;

	public List<ModuleNeeded> getModulesNeededForJobs()
			throws PersistenceException;

	public void forceCreate(Item item, IPath path) throws PersistenceException;

	public void forceCreate(Project project, Item item, IPath path)
			throws PersistenceException;

	public void createParentFoldersRecursively(ERepositoryObjectType itemType,
			IPath path) throws PersistenceException;

	public void createParentFoldersRecursively(Project project,
			ERepositoryObjectType itemType, IPath path)
			throws PersistenceException;

	public void forceDeleteObjectPhysical(IRepositoryObject objToDelete)
			throws PersistenceException;

	public Property getUptodateProperty(Project project, Property property)
			throws PersistenceException;

	public Property getUptodateProperty(Property property)
			throws PersistenceException;

}
