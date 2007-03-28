// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006-2007 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
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
package org.talend.repository.localprovider.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.keyvalue.MultiKey;
import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.talend.commons.exception.BusinessException;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.utils.VersionUtils;
import org.talend.commons.utils.data.container.Container;
import org.talend.commons.utils.data.container.RootContainer;
import org.talend.commons.utils.workbench.resources.ResourceUtils;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.model.general.Project;
import org.talend.core.model.general.TalendNature;
import org.talend.core.model.metadata.builder.connection.ConnectionPackage;
import org.talend.core.model.properties.BusinessProcessItem;
import org.talend.core.model.properties.ByteArray;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.core.model.properties.ContextItem;
import org.talend.core.model.properties.FileItem;
import org.talend.core.model.properties.FolderItem;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.ItemState;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.properties.PropertiesFactory;
import org.talend.core.model.properties.PropertiesPackage;
import org.talend.core.model.properties.Property;
import org.talend.core.model.properties.RoutineItem;
import org.talend.core.model.properties.Status;
import org.talend.core.model.properties.User;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.Folder;
import org.talend.core.model.repository.IRepositoryObject;
import org.talend.core.model.repository.RepositoryObject;
import org.talend.repository.localprovider.exceptions.IncorrectFileException;
import org.talend.repository.localprovider.i18n.Messages;
import org.talend.repository.model.AbstractEMFRepositoryFactory;
import org.talend.repository.model.ERepositoryStatus;
import org.talend.repository.model.FolderHelper;
import org.talend.repository.model.IRepositoryFactory;
import org.talend.repository.model.RepositoryConstants;
import org.talend.repository.model.ResourceModelUtils;
import org.talend.repository.model.URIHelper;
import org.talend.repository.model.VersionList;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * $Id$ $Id:
 * RepositoryFactory.java,v 1.55 2006/08/23 14:30:39 tguiu Exp $
 * 
 */
public class LocalRepositoryFactory extends AbstractEMFRepositoryFactory implements IRepositoryFactory {

    private static final String BIN = "bin"; //$NON-NLS-1$

    private static Logger log = Logger.getLogger(LocalRepositoryFactory.class);

    private static LocalRepositoryFactory singleton = null;

    public LocalRepositoryFactory() {
        super();
        singleton = this;
    }

    public static LocalRepositoryFactory getInstance() {
        if (singleton == null) {
            singleton = new LocalRepositoryFactory();
        }
        return singleton;
    }

    /**
     * DOC smallet Comment method "getObjectFromFolder".
     * 
     * @param type - the type of object to search
     * @param onlyLastVersion specify <i>true</i> if only the last version of an object must be returned, false for all
     * version
     * @param project - the project to searched in
     * 
     * @param <T> - DOC smallet
     * @return a structure of all object of type in the project
     * @throws PersistenceException if project, folder or resource cannot be found
     */
    protected <K, T> RootContainer<K, T> getObjectFromFolder(ERepositoryObjectType type, boolean onlyLastVersion)
            throws PersistenceException {
        long currentTime = System.currentTimeMillis();

        RootContainer<K, T> toReturn = new RootContainer<K, T>();

        IProject fsProject = ResourceModelUtils.getProject(getRepositoryContext().getProject());

        IFolder objectFolder = ResourceUtils.getFolder(fsProject, ERepositoryObjectType.getFolderName(type), true);

        addFolderMembers(type, toReturn, objectFolder, onlyLastVersion);

        String arg1 = toReturn.absoluteSize() + ""; //$NON-NLS-1$
        String arg2 = (System.currentTimeMillis() - currentTime) / 1000 + ""; //$NON-NLS-1$

        log.trace(Messages.getString("LocalRepositoryFactory.logRetrievingFiles", new String[] { arg1, arg2 })); //$NON-NLS-1$

        return toReturn;
    }

    /**
     * 
     * DOC smallet Comment method "addFolderMembers".
     * 
     * @param <T> - DOC smallet
     * @param type - DOC smallet
     * @param toReturn - DOC smallet
     * @param objectFolder - DOC smallet
     * @param onlyLastVersion specify <i>true</i> if only the last version of an object must be returned, false for all
     * version
     * @throws PersistenceException - DOC smallet
     */
    protected <K, T> void addFolderMembers(ERepositoryObjectType type, Container<K, T> toReturn, Object objectFolder,
            boolean onlyLastVersion) throws PersistenceException {
        for (IResource current : ResourceUtils.getMembers((IFolder) objectFolder)) {
            if (current instanceof IFile) {
                try {
                    IRepositoryObject currentObject = null;

                    if (xmiResourceManager.isPropertyFile((IFile) current)) {
                        Property property = xmiResourceManager.loadProperty(current);
                        currentObject = new RepositoryObject(property);
                    }

                    if (currentObject != null) {
                        K key;
                        if (onlyLastVersion) {
                            key = (K) currentObject.getId();
                        } else {
                            key = (K) new MultiKey(currentObject.getId(), currentObject.getVersion());
                        }

                        try {
                            if (onlyLastVersion) {
                                // Version :
                                T old = toReturn.getMember(key);
                                if (old == null) {
                                    toReturn.addMember(key, (T) currentObject);
                                } else if (((IRepositoryObject) old).getVersion().compareTo(currentObject.getVersion()) < 0) {
                                    toReturn.addMember(key, (T) currentObject);
                                }
                            } else {
                                toReturn.addMember(key, (T) currentObject);
                            }
                        } catch (BusinessException e) {
                            throw new PersistenceException(e);
                        }
                    }
                } catch (IncorrectFileException e) {
                    ExceptionHandler.process(e);
                } catch (PersistenceException e) {
                    ExceptionHandler.process(e);
                }
            } else if (current instanceof IFolder) {
                if (!current.getName().equals(BIN)) {
                    Container<K, T> cont = toReturn.addSubContainer(current.getName());
                    FolderHelper folderHelper = getFolderHelper(getRepositoryContext().getProject().getEmfProject());
                    FolderItem folder = folderHelper.getFolder(current.getProjectRelativePath());
                    Property property = folder.getProperty();
                    cont.setProperty(property);
                    cont.setId(property.getId());
                    addFolderMembers(type, cont, (IFolder) current, onlyLastVersion);
                } else {
                    addFolderMembers(type, toReturn, (IFolder) current, onlyLastVersion);
                }
            }
        }
    }

    public List<IRepositoryObject> getAll(ERepositoryObjectType type, boolean withDeleted) throws PersistenceException {
        IFolder folder = LocalResourceModelUtils.getFolder(getRepositoryContext().getProject(), type);
        return convert(getSerializableFromFolder(folder, null, type, false, true, withDeleted));
    } 

    public IRepositoryObject getLastVersion(String id) throws PersistenceException {
        List<IRepositoryObject> serializableAllVersion = getSerializable(getRepositoryContext().getProject(), id, false);

        if (serializableAllVersion.size() > 1) {
            throw new PersistenceException(Messages
                    .getString("LocalRepositoryFactory.presistenceException.onlyOneOccurenceAllowed")); //$NON-NLS-1$
        } else if (serializableAllVersion.size() == 1) {
            return serializableAllVersion.get(0);
        } else {
            return null;
        }
    }

    /**
     * 
     * Get all object in a folder recursively.
     * 
     * @param folder - the folder to search in
     * @param id - the id of the object searched. Specify <code>null</code> to get all objects.
     * @param type - the type searched
     * @param allVersion - <code>true</code> if all version of each object must be return or <code>false</code> if
     * on ly the most recent version
     * @return a list (may be empty) of objects found
     * @throws PersistenceException
     */
    protected List<IRepositoryObject> getSerializableFromFolder(Object folder, String id, ERepositoryObjectType type,
            boolean allVersion, boolean searchInChildren, boolean withDeleted) throws PersistenceException {
        List<IRepositoryObject> toReturn = new VersionList(allVersion);

        for (IResource current : ResourceUtils.getMembers((IFolder) folder)) {
            if (current instanceof IFile) {
                if (xmiResourceManager.isPropertyFile((IFile) current)) {
                    Property property = xmiResourceManager.loadProperty(current);
                    if (id == null || property.getId().equals(id)) {
                        if (withDeleted || !property.getItem().getState().isDeleted()) {
                            toReturn.add(new RepositoryObject(property));
                        }
                    }
                }
            } else if (current instanceof IFolder) {
                if (searchInChildren) {
                    toReturn.addAll(getSerializableFromFolder((IFolder) current, id, type, allVersion, true,
                            withDeleted));
                }
            }
        }
        return toReturn;
    }

    /**
     * @see org.talend.core.model.repository.factories.IRepositoryFactory#createProject(java.lang.String,
     * java.lang.String, java.lang.String, org.talend.core.model.general.User)
     */
    private static List<ERepositoryObjectType> needsBinFolder = new ArrayList<ERepositoryObjectType>();

    static {
        // /PTODO tgu quick fix for registering the emf package. needed to make the extention point work
        ConnectionPackage.eINSTANCE.getClass();

        needsBinFolder.add(ERepositoryObjectType.BUSINESS_PROCESS);
        needsBinFolder.add(ERepositoryObjectType.DOCUMENTATION);
        needsBinFolder.add(ERepositoryObjectType.METADATA_CONNECTIONS);
        needsBinFolder.add(ERepositoryObjectType.METADATA_FILE_DELIMITED);
        needsBinFolder.add(ERepositoryObjectType.METADATA_FILE_POSITIONAL);
        needsBinFolder.add(ERepositoryObjectType.METADATA_FILE_REGEXP);
        needsBinFolder.add(ERepositoryObjectType.METADATA_FILE_XML);
        needsBinFolder.add(ERepositoryObjectType.METADATA_FILE_LDIF);
        needsBinFolder.add(ERepositoryObjectType.PROCESS);
        needsBinFolder.add(ERepositoryObjectType.ROUTINES);
        needsBinFolder.add(ERepositoryObjectType.CONTEXT);
    }

    public Project createProject(String label, String description, ECodeLanguage language, User author)
            throws PersistenceException {
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();

        String technicalLabel = Project.createTechnicalName(label);
        IProject prj = root.getProject(technicalLabel);
        Project project = new Project();
        Resource projectResource = xmiResourceManager.createProjectResource(prj);
        projectResource.getContents().add(project.getEmfProject());

        final IWorkspace workspace = ResourcesPlugin.getWorkspace();
        final IProjectDescription desc = workspace.newProjectDescription(label);
        desc.setNatureIds(new String[] { TalendNature.ID });
        desc.setComment(description);

        try {
            prj.create(desc, null);
            prj.open(IResource.BACKGROUND_REFRESH, null);
        } catch (CoreException e) {
            throw new PersistenceException(e);
        }

        // Fill project object
        project.setLabel(label);
        project.setDescription(description);
        project.setLanguage(language);
        project.setAuthor(author);
        project.setLocal(true);
        project.setTechnicalLabel(technicalLabel);

        projectResource.getContents().add(author);
        xmiResourceManager.saveResource(projectResource);

        return project;
    }

    /**
     * DOC smallet Comment method "synchronizeRoutines".
     * 
     * @throws PersistenceException
     */
    public void synchronizeRoutines(IProject prj) throws PersistenceException {
        if (prj == null) {
            Project project = getRepositoryContext().getProject();
            prj = ResourceModelUtils.getProject(project);
        }

        // Purge old routines :
        // 1. old built-in:
        IFolder f1 = ResourceUtils.getFolder(prj, ERepositoryObjectType.getFolderName(ERepositoryObjectType.ROUTINES)
                + IPath.SEPARATOR + RepositoryConstants.SYSTEM_DIRECTORY, false);
        ResourceUtils.deleteFolder(f1);

        createSystemRoutines();
    }

    /**
     * DOC smallet Comment method "createFolders".
     * 
     * @param prj
     * @param project
     * @throws PersistenceException
     */
    private void createFolders(IProject prj, org.talend.core.model.properties.Project emfProject)
            throws PersistenceException {
        FolderHelper folderHelper = getFolderHelper(emfProject);

        // Folder creation :
        for (ERepositoryObjectType type : ERepositoryObjectType.values()) {
            try {
                String folderName = ERepositoryObjectType.getFolderName(type);
                createFolder(prj, folderHelper, folderName);
            } catch (IllegalArgumentException iae) {
                // Some repository object type doesn't need a folder
            }
        }

        // Special folders creation :
        // 1. Temp folder :
        createFolder(prj, folderHelper, RepositoryConstants.TEMP_DIRECTORY);

        // 2. Img folder :
        createFolder(prj, folderHelper, RepositoryConstants.IMG_DIRECTORY);
        createFolder(prj, folderHelper, RepositoryConstants.IMG_DIRECTORY_OF_JOB_OUTLINE);

        // 3. Bin folders :
        for (ERepositoryObjectType type : needsBinFolder) {
            String folderName = ERepositoryObjectType.getFolderName(type) + IPath.SEPARATOR + BIN;
            createFolder(prj, folderHelper, folderName);
        }

        // 3. Code/routines/Snippets :
        createFolder(prj, folderHelper, "code/routines"); //$NON-NLS-1$
    }

    protected FolderHelper getFolderHelper(org.talend.core.model.properties.Project emfProject) {
        return LocalFolderHelper.createInstance(emfProject, getRepositoryContext().getUser());
    }

    /**
     * DOC smallet Comment method "createFolder".
     * 
     * @param prj
     * @param folderHelper
     * @throws PersistenceException
     */
    private void createFolder(IProject prj, FolderHelper folderHelper, String path) throws PersistenceException {
        IFolder folderTemp = ResourceUtils.getFolder(prj, path, false);
        if (!folderTemp.exists()) {
            ResourceUtils.createFolder(folderTemp);
            folderHelper.createSystemFolder(new Path(path));
        }
    }

    public Project[] readProject(boolean local) throws PersistenceException {
        // TODO SML Delete this method when remote is implemented

        IProject[] prjs = ResourceUtils.getProjetWithNature(TalendNature.ID);

        List<Project> toReturn = new ArrayList<Project>();
        for (int i = 0; i < prjs.length; i++) {
            IProject p = prjs[i];

            readProject(local, toReturn, p, false);
            readProject(local, toReturn, p, true);
        }

        return toReturn.toArray(new Project[toReturn.size()]);
    }

    private void readProject(boolean local, List<Project> toReturn, IProject p, boolean useOldProjectFile) {
        xmiResourceManager.setUseOldProjectFile(useOldProjectFile);

        try {
            if (xmiResourceManager.hasTalendProjectFile(p)) {
                org.talend.core.model.properties.Project emfProject = xmiResourceManager.loadProject(p);
                if (emfProject.isLocal() == local) {
                    Project project = new Project(emfProject);
                    toReturn.add(project);
                }
            }
        } catch (PersistenceException e) {
            ExceptionHandler.process(e);
        }

        xmiResourceManager.setUseOldProjectFile(false);
    }

    /**
     * @see org.talend.core.model.repository.factories.IRepositoryFactory#readProject(java.lang.String,
     * java.lang.String, java.lang.String)
     */
    public Project[] readProject() throws PersistenceException {
        return readProject(true);
    }

    private void synchronizeFolders(final IProject project, final org.talend.core.model.properties.Project emfProject)
            throws PersistenceException {
        final FolderHelper helper = getFolderHelper(emfProject);
        final Set<IPath> listFolders = helper.listFolders();
        try {
            project.accept(new IResourceVisitor() {

                public boolean visit(IResource resource) throws CoreException {
                    if (resource.getType() == IResource.FOLDER) {
                        IPath path = resource.getProjectRelativePath();
                        if (!listFolders.remove(path)) {
                            // create emf folder
                            helper.createFolder(path);
                        } else {
                            // add state to existing emf folder
                            FolderItem folder = helper.getFolder(path);
                            if (folder.getState() == null) {
                                helper.createItemState(folder);
                            }
                        }
                    }
                    return true;
                }

            });
        } catch (CoreException e) {
            e.printStackTrace();
        }
        // delete remaining folders
        for (IPath path : listFolders) {
            helper.deleteFolder(path);
        }
        xmiResourceManager.saveResource(emfProject.eResource());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.extension.IRepositoryFactory#createFolder(org.talend.core.model.temp.Project,
     * org.talend.core.model.repository.EObjectType, org.eclipse.core.runtime.IPath, java.lang.String)
     */
    public Folder createFolder(ERepositoryObjectType type, IPath path, String label) throws PersistenceException {
        if (type == null) {
            throw new IllegalArgumentException(Messages.getString("LocalRepositoryFactory.illegalArgumentException01")); //$NON-NLS-1$
        }
        if (path == null) {
            throw new IllegalArgumentException(Messages.getString("LocalRepositoryFactory.illegalArgumentException02")); //$NON-NLS-1$
        }
        if (label == null || label.length() == 0) {
            throw new IllegalArgumentException(Messages.getString("LocalRepositoryFactory.illegalArgumentException03")); //$NON-NLS-1$
        }

        IProject fsProject = ResourceModelUtils.getProject(getRepositoryContext().getProject());

        String completePath = ERepositoryObjectType.getFolderName(type) + IPath.SEPARATOR + path.toString()
                + IPath.SEPARATOR + label;
        FolderItem folderItem = getFolderHelper(getRepositoryContext().getProject().getEmfProject()).createFolder(
                completePath);
        xmiResourceManager.saveResource(getRepositoryContext().getProject().getEmfProject().eResource());
        // Getting the folder :
        IFolder folder = ResourceUtils.getFolder(fsProject, completePath, false);
        ResourceUtils.createFolder(folder);

        return new Folder(folderItem.getProperty(), type);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.model.IRepositoryFactory#isValid(org.talend.core.model.general.Project,
     * org.talend.core.model.repository.ERepositoryObjectType, org.eclipse.core.runtime.IPath, java.lang.String)
     */
    public boolean isPathValid(ERepositoryObjectType type, IPath path, String label) throws PersistenceException {
        if (path == null) {
            return false;
        }

        if (label.equals(BIN)) {
            return false;
        } else {
            // TODO SML Delete this ?
            IProject fsProject = ResourceModelUtils.getProject(getRepositoryContext().getProject());
            String completePath = ERepositoryObjectType.getFolderName(type) + IPath.SEPARATOR + path.toString()
                    + IPath.SEPARATOR + label;

            // Getting the folder :
            IFolder existingFolder = ResourceUtils.getFolder(fsProject, completePath, false);
            return !existingFolder.exists();
        }
    }

    public void deleteFolder(ERepositoryObjectType type, IPath path) throws PersistenceException {
        IProject fsProject = ResourceModelUtils.getProject(getRepositoryContext().getProject());

        String completePath = ERepositoryObjectType.getFolderName(type) + IPath.SEPARATOR + path.toString();

        // Getting the folder :
        IFolder folder = ResourceUtils.getFolder(fsProject, completePath, true);
        getFolderHelper(getRepositoryContext().getProject().getEmfProject()).deleteFolder(completePath);
        xmiResourceManager.saveResource(getRepositoryContext().getProject().getEmfProject().eResource());

        ResourceUtils.deleteFolder(folder);
    }

    public void moveFolder(ERepositoryObjectType type, IPath sourcePath, IPath targetPath) throws PersistenceException {
        IProject fsProject = ResourceModelUtils.getProject(getRepositoryContext().getProject());

        String completeOldPath = ERepositoryObjectType.getFolderName(type) + IPath.SEPARATOR + sourcePath.toString();
        String completeNewPath = ERepositoryObjectType.getFolderName(type) + IPath.SEPARATOR + targetPath.toString()
                + IPath.SEPARATOR + sourcePath.lastSegment();

        // Getting the folder :
        IFolder folder = ResourceUtils.getFolder(fsProject, completeOldPath, false);

        FolderHelper folderHelper = getFolderHelper(getRepositoryContext().getProject().getEmfProject());
        FolderItem emfFolder = folderHelper.getFolder(completeOldPath);

        createFolder(type, targetPath, emfFolder.getProperty().getLabel());

        Item[] childrens = (Item[]) emfFolder.getChildren().toArray();
        for (int i = 0; i < childrens.length; i++) {
            FolderItem children = (FolderItem) childrens[i];
            moveFolder(type, sourcePath.append(children.getProperty().getLabel()), targetPath.append(emfFolder.getProperty().getLabel()));
        }

        List<IRepositoryObject> serializableFromFolder = getSerializableFromFolder(folder, null, type, true, true, true);
        for (IRepositoryObject object : serializableFromFolder) {
            List<Resource> affectedResources = xmiResourceManager.getAffectedResources(object.getProperty());
            for (Resource resource : affectedResources) {
                IPath path = getProject().getFullPath().append(completeNewPath).append(resource.getURI().lastSegment());
                xmiResourceManager.moveResource(resource, path);
            }
            for (Resource resource : affectedResources) {
                xmiResourceManager.saveResource(resource);
            }
        }

        deleteFolder(type, sourcePath);
        
        xmiResourceManager.saveResource(getRepositoryContext().getProject().getEmfProject().eResource());
    }

    public void renameFolder(ERepositoryObjectType type, IPath path, String label) throws PersistenceException {
        IProject fsProject = ResourceModelUtils.getProject(getRepositoryContext().getProject());

        String completePath = ERepositoryObjectType.getFolderName(type) + IPath.SEPARATOR + path.toString();
        // Getting the folder :
        IFolder folder = ResourceUtils.getFolder(fsProject, completePath, false);

        // IPath targetPath = new
        // Path(SystemFolderNameFactory.getFolderName(type)).append(path).removeLastSegments(1).append(label);
        IPath targetPath = new Path(label);
        getFolderHelper(getRepositoryContext().getProject().getEmfProject()).renameFolder(completePath, label);
        xmiResourceManager.saveResource(getRepositoryContext().getProject().getEmfProject().eResource());

        ResourceUtils.moveResource(folder, targetPath);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.model.IRepositoryFactory#getProcess2()
     */
    public List<IRepositoryObject> getProcess2() throws PersistenceException {
        List<IRepositoryObject> toReturn = new VersionList(false);

        FolderHelper folderHelper = getFolderHelper(getRepositoryContext().getProject().getEmfProject());
        IFolder folder = LocalResourceModelUtils.getFolder(getRepositoryContext().getProject(),
                ERepositoryObjectType.PROCESS);

        for (IResource current : ResourceUtils.getMembers(folder)) {
            if (current instanceof IFile) {
                if (xmiResourceManager.isPropertyFile((IFile) current)) {
                    Property property = xmiResourceManager.loadProperty(current);
                    toReturn.add(new RepositoryObject(property));
                }
            } else if (current instanceof IFolder) {
                if (!current.getName().equals(BIN)) {
                    Property property = folderHelper.getFolder(current.getProjectRelativePath()).getProperty();
                    Folder oFolder = new Folder(property, ERepositoryObjectType.PROCESS);
                    toReturn.add(oFolder);
                }
            }
        }
        return toReturn;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.model.IRepositoryFactory#deleteObject(org.talend.core.model.general.Project,
     * org.talend.core.model.repository.IRepositoryObject)
     */
    public void deleteObjectLogical(IRepositoryObject objToDelete) throws PersistenceException {
        IProject fsProject = ResourceModelUtils.getProject(getRepositoryContext().getProject());

        IFolder bin = ResourceUtils.getFolder(fsProject, ERepositoryObjectType.getFolderName(objToDelete.getType())
                + IPath.SEPARATOR + BIN, true);

        List<IRepositoryObject> allVersionToDelete = getAllVersion(objToDelete.getId());
        for (IRepositoryObject currentVersion : allVersionToDelete) {
            ItemState state = objToDelete.getProperty().getItem().getState();
            state.setDeleted(true);
            xmiResourceManager.saveResource(state.eResource());

            List<Resource> affectedResources = xmiResourceManager.getAffectedResources(currentVersion.getProperty());
            for (Resource resource : affectedResources) {
                IPath path = URIHelper.convert(resource.getURI());
                IPath newPath = bin.getFullPath().append(path.lastSegment());
                xmiResourceManager.moveResource(resource, newPath);
            }
            for (Resource resource : affectedResources) {
                xmiResourceManager.saveResource(resource);
            }
        }
    }

    public void deleteObjectPhysical(IRepositoryObject objToDelete) throws PersistenceException {
        List<IRepositoryObject> allVersionToDelete = getAllVersion(objToDelete.getId());
        for (IRepositoryObject currentVersion : allVersionToDelete) {
            List<Resource> affectedResources = xmiResourceManager.getAffectedResources(currentVersion.getProperty());
            for (Resource resource : affectedResources) {
                xmiResourceManager.deleteResource(resource);
            }
        }
    }

    public void restoreObject(IRepositoryObject objToRestore, IPath path) throws PersistenceException {
        IProject fsProject = ResourceModelUtils.getProject(getRepositoryContext().getProject());
        IFolder typeRootFolder = ResourceUtils.getFolder(fsProject, ERepositoryObjectType.getFolderName(objToRestore
                .getType()), true);
        // IPath thePath = (path == null ? typeRootFolder.getFullPath() : typeRootFolder.getFullPath().append(path));
        org.talend.core.model.properties.Project project = xmiResourceManager.loadProject(getProject());

        List<IRepositoryObject> allVersionToDelete = getAllVersion(objToRestore.getId());
        for (IRepositoryObject currentVersion : allVersionToDelete) {
            ItemState itemState = currentVersion.getProperty().getItem().getState();
            itemState.setDeleted(false);
            xmiResourceManager.saveResource(itemState.eResource());

            List<Resource> affectedResources = xmiResourceManager.getAffectedResources(currentVersion.getProperty());
            for (Resource resource : affectedResources) {
                IPath originalPath = URIHelper.convert(resource.getURI());
                String path2 = itemState.getPath();
                itemState.setPath(path.toString());
                IPath finalPath = typeRootFolder.getFullPath().append(path).append(originalPath.lastSegment());
                xmiResourceManager.moveResource(resource, finalPath);
            }
            for (Resource resource : affectedResources) {
                xmiResourceManager.saveResource(resource);
            }
        }

        xmiResourceManager.saveResource(project.eResource());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.model.IRepositoryFactory#moveObject(org.talend.core.model.general.Project,
     * org.talend.core.model.repository.IRepositoryObject, org.eclipse.core.runtime.IPath)
     */
    public void moveObject(IRepositoryObject objToMove, IPath newPath) throws PersistenceException {
        IProject fsProject = ResourceModelUtils.getProject(getRepositoryContext().getProject());
        String folderName = ERepositoryObjectType.getFolderName(objToMove.getType()) + IPath.SEPARATOR + newPath;
        IFolder folder = ResourceUtils.getFolder(fsProject, folderName, true);

        List<IRepositoryObject> allVersionToMove = getAllVersion(objToMove.getId());
        for (IRepositoryObject obj : allVersionToMove) {
            ItemState state = obj.getProperty().getItem().getState();
            state.setPath(newPath.toString());
            xmiResourceManager.saveResource(state.eResource());

            List<Resource> affectedResources = xmiResourceManager.getAffectedResources(obj.getProperty());
            for (Resource resource : affectedResources) {
                IPath path = folder.getFullPath().append(resource.getURI().lastSegment());
                xmiResourceManager.moveResource(resource, path);
            }
            for (Resource resource : affectedResources) {
                xmiResourceManager.saveResource(resource);
            }
        }
    }

    private XmiResourceManager xmiResourceManager = new XmiResourceManager();;

    public void lock(Item item) throws PersistenceException {
        if (getStatus(item) == ERepositoryStatus.DEFAULT) {
            // lockedObject.put(item.getProperty().getId(), new LockedObject(new Date(), repositoryContext.getUser()));
            item.getState().setLockDate(new Date());
            item.getState().setLocker(getRepositoryContext().getUser());
            item.getState().setLocked(true);
            xmiResourceManager.saveResource(item.eResource());
        }
    }

    public void unlock(Item item) throws PersistenceException {
        if (getStatus(item) == ERepositoryStatus.LOCK_BY_USER) {
            // lockedObject.remove(obj.getProperty().getId());
            item.getState().setLocker(null);
            item.getState().setLockDate(null);
            item.getState().setLocked(false);
            xmiResourceManager.saveResource(item.eResource());
        }
    }

    public List<Status> getDocumentationStatus() throws PersistenceException {
        org.talend.core.model.properties.Project loadProject = xmiResourceManager.loadProject(getProject());
        return copyList(loadProject.getDocumentationStatus());
    }

    public List<Status> getTechnicalStatus() throws PersistenceException {
        org.talend.core.model.properties.Project loadProject = xmiResourceManager.loadProject(getProject());
        return copyList(loadProject.getTechnicalStatus());
    }

    private List<Status> copyList(List listOfStatus) {
        List<Status> result = new ArrayList<Status>();
        for (Object obj : listOfStatus) {
            result.add((Status) obj);
        }
        return result;
    }

    public void setDocumentationStatus(List<Status> list) throws PersistenceException {
        org.talend.core.model.properties.Project loadProject = xmiResourceManager.loadProject(getProject());
        loadProject.getDocumentationStatus().clear();
        loadProject.getDocumentationStatus().addAll(list);
        xmiResourceManager.saveResource(loadProject.eResource());
    }

    public void setTechnicalStatus(List<Status> list) throws PersistenceException {
        org.talend.core.model.properties.Project loadProject = xmiResourceManager.loadProject(getProject());
        loadProject.getTechnicalStatus().clear();
        loadProject.getTechnicalStatus().addAll(list);
        xmiResourceManager.saveResource(loadProject.eResource());
    }

    public void setMigrationTasksDone(Project project, List<String> list) throws PersistenceException {
        IProject iproject = ResourceModelUtils.getProject(project);
        org.talend.core.model.properties.Project loadProject = xmiResourceManager.loadProject(iproject);
        loadProject.getMigrationTasks().clear();
        loadProject.getMigrationTasks().addAll(list);
        xmiResourceManager.saveResource(loadProject.eResource());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.model.IRepositoryFactory#isServerValid(java.lang.String, java.lang.String, int)
     */
    public String isServerValid() {
        return ""; //$NON-NLS-1$
    }

    private Resource create(BusinessProcessItem item, IPath path) throws PersistenceException {
        Resource itemResource = xmiResourceManager.createItemResource(getProject(), item, path,
                ERepositoryObjectType.BUSINESS_PROCESS, false);
        // notation depends on semantic ...
        // in case of new(=empty) diagram, we don't care about order
        // in other cases, the ordered addition references between notaion and semantic will be updated
        itemResource.getContents().add(item.getSemantic());
        itemResource.getContents().add(item.getNotationHolder());
        item.computeNotationHolder();

        return itemResource;
    }

    private Resource create(ConnectionItem item, ERepositoryObjectType type, IPath path) throws PersistenceException {
        Resource itemResource = xmiResourceManager.createItemResource(getProject(), item, path, type, false);

        itemResource.getContents().add(item.getConnection());

        return itemResource;
    }

    private Resource save(BusinessProcessItem item) {
        Resource itemResource = xmiResourceManager.getItemResource(item);
        itemResource.getContents().clear();
        // itemResource.getContents().add(item.getNotation());
        itemResource.getContents().add(item.getSemantic());
        itemResource.getContents().add(item.getNotationHolder());
        item.computeNotationHolder();

        return itemResource;
    }

    private Resource save(ConnectionItem item) {
        Resource itemResource = xmiResourceManager.getItemResource(item);
        itemResource.getContents().clear();
        itemResource.getContents().add(item.getConnection());

        return itemResource;
    }

    private Resource create(FileItem item, IPath path, ERepositoryObjectType type) throws PersistenceException {
        Resource itemResource = xmiResourceManager.createItemResource(getProject(), item, path, type, true);

        itemResource.getContents().add(item.getContent());

        return itemResource;
    }

    private Resource save(FileItem item) {
        Resource itemResource = xmiResourceManager.getItemResource(item);

        ByteArray content = item.getContent();
        itemResource.getContents().clear();
        itemResource.getContents().add(content);

        return itemResource;
    }

    private Resource create(ProcessItem item, IPath path) throws PersistenceException {
        Resource itemResource = xmiResourceManager.createItemResource(getProject(), item, path,
                ERepositoryObjectType.PROCESS, false);
        itemResource.getContents().add(item.getProcess());

        return itemResource;
    }

    private Resource create(ContextItem item, IPath path) throws PersistenceException {
        Resource itemResource = xmiResourceManager.createItemResource(getProject(), item, path,
                ERepositoryObjectType.CONTEXT, false);
        itemResource.getContents().addAll(item.getContext());

        return itemResource;
    }

    private Resource save(ContextItem item) {
        Resource itemResource = xmiResourceManager.getItemResource(item);

        itemResource.getContents().clear();
        itemResource.getContents().addAll(item.getContext());

        return itemResource;
    }

    private Resource save(ProcessItem item) {
        Resource itemResource = xmiResourceManager.getItemResource(item);

        itemResource.getContents().clear();
        itemResource.getContents().add(item.getProcess());

        return itemResource;
    }

    public void save(Item item) throws PersistenceException {
        item.getProperty().setModificationDate(new Date());

        Resource itemResource;
        EClass eClass = item.eClass();
        if (eClass.eContainer() == PropertiesPackage.eINSTANCE) {
            switch (eClass.getClassifierID()) {
            case PropertiesPackage.BUSINESS_PROCESS_ITEM:
                itemResource = save((BusinessProcessItem) item);
                break;
            case PropertiesPackage.POSITIONAL_FILE_CONNECTION_ITEM:
            case PropertiesPackage.DELIMITED_FILE_CONNECTION_ITEM:
            case PropertiesPackage.DATABASE_CONNECTION_ITEM:
            case PropertiesPackage.REG_EX_FILE_CONNECTION_ITEM:
            case PropertiesPackage.XML_FILE_CONNECTION_ITEM:
                // not really usefull for ConnectionItem : it's not copied to another resource for edition
                itemResource = save((ConnectionItem) item);
                break;
            case PropertiesPackage.LDIF_FILE_CONNECTION_ITEM:
                // not really usefull for ConnectionItem : it's not copied to another resource for edition
                itemResource = save((ConnectionItem) item);
                break;
            case PropertiesPackage.DOCUMENTATION_ITEM:
            case PropertiesPackage.ROUTINE_ITEM:
                itemResource = save((FileItem) item);
                break;
            case PropertiesPackage.PROCESS_ITEM:
                itemResource = save((ProcessItem) item);
                break;
            case PropertiesPackage.CONTEXT_ITEM:
                itemResource = save((ContextItem) item);
                break;
            default:
                throw new UnsupportedOperationException();
            }
        } else {
            throw new UnsupportedOperationException();
        }

        propagateFileName(item.getProperty());

        xmiResourceManager.saveResource(item.eResource());
        xmiResourceManager.saveResource(itemResource);
    }

    public void save(Property property) throws PersistenceException {
        propagateFileName(property);

        property.setModificationDate(new Date());
        Resource propertyResource = property.eResource();
        if (propertyResource != null) {
            xmiResourceManager.saveResource(propertyResource);
        }
    }

    public Item copy(Item originalItem, IPath path) throws PersistenceException {
        Resource resource = originalItem.eResource();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            resource.save(out, null);
            Resource createResource = new ResourceSetImpl().createResource(resource.getURI());
            ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
            createResource.load(in, null);
            Item newItem = copyFromResource(createResource);
            create(newItem, path);
            return newItem;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void propagateFileName(Property property) throws PersistenceException {
        List<IRepositoryObject> allVersionToMove = getAllVersion(property.getId());
        for (IRepositoryObject object : allVersionToMove) {
            xmiResourceManager.propagateFileName(property, object.getProperty());
        }
    }

    public void create(Item item, IPath path) throws PersistenceException {
        if (item.getProperty().getVersion() == null) {
            item.getProperty().setVersion(VersionUtils.DEFAULT_VERSION);
        }
        if (item.getProperty().getAuthor() == null) {
            item.getProperty().setAuthor(getRepositoryContext().getUser());
        }
        item.getProperty().setCreationDate(new Date());

        ItemState itemState = PropertiesFactory.eINSTANCE.createItemState();
        itemState.setDeleted(false);
        itemState.setPath(path.toString());

        item.setState(itemState);

        Resource itemResource;
        EClass eClass = item.eClass();
        if (eClass.eContainer() == PropertiesPackage.eINSTANCE) {
            switch (eClass.getClassifierID()) {
            case PropertiesPackage.BUSINESS_PROCESS_ITEM:
                itemResource = create((BusinessProcessItem) item, path);
                break;
            case PropertiesPackage.DATABASE_CONNECTION_ITEM:
                itemResource = create((ConnectionItem) item, ERepositoryObjectType.METADATA_CONNECTIONS, path);
                break;
            case PropertiesPackage.DELIMITED_FILE_CONNECTION_ITEM:
                itemResource = create((ConnectionItem) item, ERepositoryObjectType.METADATA_FILE_DELIMITED, path);
                break;
            case PropertiesPackage.POSITIONAL_FILE_CONNECTION_ITEM:
                itemResource = create((ConnectionItem) item, ERepositoryObjectType.METADATA_FILE_POSITIONAL, path);
                break;
            case PropertiesPackage.REG_EX_FILE_CONNECTION_ITEM:
                itemResource = create((ConnectionItem) item, ERepositoryObjectType.METADATA_FILE_REGEXP, path);
                break;
            case PropertiesPackage.XML_FILE_CONNECTION_ITEM:
                itemResource = create((ConnectionItem) item, ERepositoryObjectType.METADATA_FILE_XML, path);
                break;
            case PropertiesPackage.LDIF_FILE_CONNECTION_ITEM:
                itemResource = create((ConnectionItem) item, ERepositoryObjectType.METADATA_FILE_LDIF, path);
                break;
            case PropertiesPackage.DOCUMENTATION_ITEM:
                itemResource = create((FileItem) item, path, ERepositoryObjectType.DOCUMENTATION);
                break;
            case PropertiesPackage.ROUTINE_ITEM:
                itemResource = create((FileItem) item, path, ERepositoryObjectType.ROUTINES);
                break;
            case PropertiesPackage.PROCESS_ITEM:
                itemResource = create((ProcessItem) item, path);
                break;
            case PropertiesPackage.CONTEXT_ITEM:
                itemResource = create((ContextItem) item, path);
                break;
            default:
                throw new UnsupportedOperationException();
            }
        } else {
            throw new UnsupportedOperationException();
        }

        Resource propertyResource = xmiResourceManager.createPropertyResource(itemResource);
        propertyResource.getContents().add(item.getProperty());
        propertyResource.getContents().add(item.getState());
        propertyResource.getContents().add(item);

        xmiResourceManager.saveResource(itemResource);
        xmiResourceManager.saveResource(propertyResource);
    }

    private IProject getProject() throws PersistenceException {
        return ResourceModelUtils.getProject(getRepositoryContext().getProject());
    }

    public Property reload(Property property) {
        IFile file = URIHelper.getFile(property.eResource().getURI());

        List<Resource> affectedResources = xmiResourceManager.getAffectedResources(property);
        for (Resource resource : affectedResources) {
            resource.unload();
        }

        return xmiResourceManager.loadProperty(file);
    }

    public boolean doesLoggedUserExist() throws PersistenceException {
        boolean exist = false;
        IProject iProject = ResourceModelUtils.getProject(getRepositoryContext().getProject());
        org.talend.core.model.properties.Project emfProject = xmiResourceManager.loadProject(iProject);
        Resource projectResource = emfProject.eResource();

        Collection users = EcoreUtil.getObjectsByType(projectResource.getContents(), PropertiesPackage.eINSTANCE
                .getUser());
        for (Iterator iter = users.iterator(); iter.hasNext();) {
            User emfUser = (User) iter.next();

            if (emfUser.getLogin().equals(getRepositoryContext().getUser().getLogin())) {
                getRepositoryContext().setUser(emfUser);
                exist = true;
            }
        }

        return exist;
    }

    public void createUser() throws PersistenceException {
        IProject iProject = ResourceModelUtils.getProject(getRepositoryContext().getProject());
        org.talend.core.model.properties.Project emfProject = xmiResourceManager.loadProject(iProject);
        Resource projectResource = emfProject.eResource();

        projectResource.getContents().add(getRepositoryContext().getUser());
        xmiResourceManager.saveResource(projectResource);
    }

    public void initialize() {
        // unused in local mode
    }

    public void logOnProject(Project project) throws PersistenceException {
        if (!doesLoggedUserExist()) {
            createUser();
        }

        IProject project2 = ResourceModelUtils.getProject(project);
        createFolders(project2, project.getEmfProject());
        synchronizeRoutines(project2);
        synchronizeFolders(project2, project.getEmfProject());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.model.IRepositoryFactory#getStatus(org.talend.core.model.properties.Item)
     */
    public ERepositoryStatus getStatus(Item item) {
        if (item.getState().isDeleted()) {
            return ERepositoryStatus.DELETED;
        }

        // FIXME SML [Synchronizer] Move
        if (item.getState().isLocked()) {
            // User locker = item.getState().getLocker();
            // User connected = getRepositoryContext().getUser();
            // if (connected.equals(locker)) {
            return ERepositoryStatus.LOCK_BY_USER;
            // } else {
            // return ERepositoryStatus.LOCK_BY_OTHER;
            // }
        }

        if (item instanceof RoutineItem) {
            if (((RoutineItem) item).isBuiltIn()) {
                return ERepositoryStatus.READ_ONLY;
            }
        }

        return ERepositoryStatus.DEFAULT;
    }

    @Override
    protected Object getFolder(Project project, ERepositoryObjectType repositoryObjectType) throws PersistenceException {
        IProject fsProject = ResourceModelUtils.getProject(project);
        return ResourceUtils.getFolder(fsProject, ERepositoryObjectType.getFolderName(repositoryObjectType), true);
    }

    public List<org.talend.core.model.properties.Project> getReferencedProjects() {
        return Collections.EMPTY_LIST;
    }

}
