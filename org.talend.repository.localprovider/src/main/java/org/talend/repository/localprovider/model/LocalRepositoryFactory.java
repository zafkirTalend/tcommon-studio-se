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
import java.util.Map;
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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.resource.ImageDescriptor;
import org.talend.commons.exception.BusinessException;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.exception.LoginException;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.exception.ResourceNotFoundException;
import org.talend.commons.ui.image.ImageProvider;
import org.talend.commons.utils.VersionUtils;
import org.talend.commons.utils.data.container.Container;
import org.talend.commons.utils.data.container.RootContainer;
import org.talend.commons.utils.image.ImageUtils;
import org.talend.commons.utils.io.FilesUtils;
import org.talend.commons.utils.workbench.resources.ResourceUtils;
import org.talend.core.model.general.Project;
import org.talend.core.model.general.TalendNature;
import org.talend.core.model.metadata.MetadataManager;
import org.talend.core.model.metadata.builder.connection.ConnectionPackage;
import org.talend.core.model.properties.BusinessProcessItem;
import org.talend.core.model.properties.ByteArray;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.core.model.properties.ContextItem;
import org.talend.core.model.properties.EbcdicConnectionItem;
import org.talend.core.model.properties.FTPConnectionItem;
import org.talend.core.model.properties.FileItem;
import org.talend.core.model.properties.FolderItem;
import org.talend.core.model.properties.HL7ConnectionItem;
import org.talend.core.model.properties.HeaderFooterConnectionItem;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.ItemState;
import org.talend.core.model.properties.JobDocumentationItem;
import org.talend.core.model.properties.JobletDocumentationItem;
import org.talend.core.model.properties.JobletProcessItem;
import org.talend.core.model.properties.LinkDocumentationItem;
import org.talend.core.model.properties.LinkRulesItem;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.properties.ProjectReference;
import org.talend.core.model.properties.PropertiesFactory;
import org.talend.core.model.properties.PropertiesPackage;
import org.talend.core.model.properties.Property;
import org.talend.core.model.properties.RoutineItem;
import org.talend.core.model.properties.RulesItem;
import org.talend.core.model.properties.SQLPatternItem;
import org.talend.core.model.properties.SVGBusinessProcessItem;
import org.talend.core.model.properties.SnippetItem;
import org.talend.core.model.properties.SpagoBiServer;
import org.talend.core.model.properties.Status;
import org.talend.core.model.properties.User;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.Folder;
import org.talend.core.model.repository.IRepositoryViewObject;
import org.talend.core.model.repository.RepositoryObject;
import org.talend.core.model.repository.RepositoryViewObject;
import org.talend.core.repository.constants.FileConstants;
import org.talend.core.ui.images.ECoreImage;
import org.talend.repository.ProjectManager;
import org.talend.repository.RepositoryWorkUnit;
import org.talend.repository.localprovider.exceptions.IncorrectFileException;
import org.talend.repository.localprovider.i18n.Messages;
import org.talend.repository.model.AbstractEMFRepositoryFactory;
import org.talend.repository.model.ERepositoryStatus;
import org.talend.repository.model.FolderHelper;
import org.talend.repository.model.ILocalRepositoryFactory;
import org.talend.repository.model.IProxyRepositoryFactory;
import org.talend.repository.model.RepositoryConstants;
import org.talend.repository.model.ResourceModelUtils;
import org.talend.repository.model.VersionList;
import org.talend.repository.utils.RoutineUtils;
import org.talend.repository.utils.URIHelper;
import org.talend.repository.utils.XmiResourceManager;
import orgomg.cwm.foundation.businessinformation.BusinessinformationPackage;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * $Id$ $Id: RepositoryFactory.java,v 1.55 2006/08/23
 * 14:30:39 tguiu Exp $
 * 
 */
public class LocalRepositoryFactory extends AbstractEMFRepositoryFactory implements ILocalRepositoryFactory {

    private static final String BIN = "bin"; //$NON-NLS-1$

    private static Logger log = Logger.getLogger(LocalRepositoryFactory.class);

    private static LocalRepositoryFactory singleton = null;

    private List<String> loadedFolders = new ArrayList<String>();

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

    boolean projectModified;

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
    protected <K, T> RootContainer<K, T> getObjectFromFolder(Project project, ERepositoryObjectType type,
            boolean onlyLastVersion, boolean... options) throws PersistenceException {
        long currentTime = System.currentTimeMillis();

        RootContainer<K, T> toReturn = new RootContainer<K, T>();

        IProject fsProject = ResourceModelUtils.getProject(project);
        IFolder objectFolder = null;
        try {
            objectFolder = ResourceUtils.getFolder(fsProject, ERepositoryObjectType.getFolderName(type), true);
        } catch (ResourceNotFoundException rex) {
            // log.info(rex.getMessage());
            return new RootContainer<K, T>(); // return empty
        }
        projectModified = false;
        addFolderMembers(project, type, toReturn, objectFolder, onlyLastVersion, options);

        if (projectModified) {
            saveProject(project);
        }

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
    protected <K, T> void addFolderMembers(Project project, ERepositoryObjectType type, Container<K, T> toReturn,
            Object objectFolder, boolean onlyLastVersion, boolean... options) throws PersistenceException {
        FolderHelper folderHelper = getFolderHelper(project.getEmfProject());
        FolderItem currentFolderItem;
        if (objectFolder instanceof IFolder) {
            currentFolderItem = folderHelper.getFolder(((IFolder) objectFolder).getProjectRelativePath());
            if (currentFolderItem == null) {
                // create folder
                currentFolderItem = folderHelper.createFolder(((IFolder) objectFolder).getProjectRelativePath()
                        .toPortableString());
            }
        } else {
            currentFolderItem = (FolderItem) objectFolder;
        }
        List<String> nameFounds = new ArrayList<String>();
        List<Item> invalidItems = new ArrayList<Item>();
        for (Item curItem : (List<Item>) currentFolderItem.getChildren()) {
            if (curItem instanceof FolderItem) {
                FolderItem subFolder = (FolderItem) curItem;
                Container<K, T> cont = toReturn.addSubContainer(subFolder.getProperty().getLabel());
                Property property = subFolder.getProperty();
                subFolder.setParent(currentFolderItem);

                cont.setProperty(property);
                cont.setId(property.getId());
                addFolderMembers(project, type, cont, curItem, onlyLastVersion, options);
            } else {
                Property property = curItem.getProperty();
                if (property != null) {
                    property.getItem().setParent(currentFolderItem);
                    IRepositoryViewObject currentObject;
                    if (options.length > 0 && options[0] == true) {
                        // called from repository view
                        currentObject = new RepositoryViewObject(property);
                    } else {
                        currentObject = new RepositoryObject(property);
                    }
                    nameFounds.add(property.getLabel() + "_" + property.getVersion() + "." + FileConstants.PROPERTIES_EXTENSION);
                    addItemToContainer(toReturn, currentObject, onlyLastVersion);

                    addToHistory(property.getId(), type, property.getItem().getState().getPath());
                } else {
                    invalidItems.add(curItem);
                    projectModified = true;
                }
            }
        }
        currentFolderItem.getChildren().removeAll(invalidItems);

        // if not logged on project, allow to browse directly the folders.
        // if already logged, project should be up to date.
        if (objectFolder instanceof IFolder) {
            String projectPath = project.getTechnicalLabel() + ":"
                    + ((IFolder) objectFolder).getProjectRelativePath().toPortableString();
            if (!loadedFolders.contains(projectPath)) {
                loadedFolders.add(projectPath);
                for (IResource current : ResourceUtils.getMembers((IFolder) objectFolder)) {
                    if (current instanceof IFile) {
                        try {
                            String fileName = ((IFile) current).getName();
                            IRepositoryViewObject currentObject = null;

                            if (xmiResourceManager.isPropertyFile((IFile) current) && !nameFounds.contains(fileName)) {
                                Property property = null;
                                try {
                                    property = xmiResourceManager.loadProperty(current);
                                } catch (RuntimeException e) {
                                    // property will be null
                                    ExceptionHandler.process(e);
                                }
                                if (property != null) {
                                    // System.out.println("new propertyLoaded:" + property.getLabel());
                                    if (options.length > 0 && options[0] == true) {
                                        // called from repository view
                                        currentObject = new RepositoryViewObject(property);
                                    } else {
                                        currentObject = new RepositoryObject(property);
                                    }
                                    currentFolderItem.getChildren().add(property.getItem());
                                    property.getItem().setParent(currentFolderItem);
                                    projectModified = true;
                                } else {
                                    log.error(Messages.getString("LocalRepositoryFactory.CannotLoadProperty") + current); //$NON-NLS-1$
                                }
                            }
                            addItemToContainer(toReturn, currentObject, onlyLastVersion);
                        } catch (IncorrectFileException e) {
                            ExceptionHandler.process(e);
                        } catch (PersistenceException e) {
                            ExceptionHandler.process(e);
                        }
                    } else if (current instanceof IFolder) {
                        if (!current.getName().equals(BIN)) {
                            Container<K, T> cont = toReturn.addSubContainer(current.getName());
                            FolderItem folder = folderHelper.getFolder(current.getProjectRelativePath());

                            Property property = null;
                            if (folder == null) {
                                folder = folderHelper.createFolder(current.getProjectRelativePath().toString());
                            }
                            property = folder.getProperty();
                            folder.setParent(currentFolderItem);

                            cont.setProperty(property);
                            cont.setId(property.getId());
                            addFolderMembers(project, type, cont, (IFolder) current, onlyLastVersion, options);
                        } else {
                            addFolderMembers(project, type, toReturn, (IFolder) current, onlyLastVersion, options);
                        }
                    }
                }
            }
        }
    }

    private <K, T> void addItemToContainer(Container<K, T> container, IRepositoryViewObject objectToAdd, boolean onlyLastVersion)
            throws PersistenceException {
        if (objectToAdd != null) {
            K key;
            String currentVersion = null;
            try {
                currentVersion = objectToAdd.getVersion();
            } catch (Exception e) {
                // e.printStackTrace();
                ExceptionHandler.process(e);
            }
            if (onlyLastVersion) {
                key = (K) objectToAdd.getId();
            } else {
                key = (K) new MultiKey(objectToAdd.getId(), currentVersion);
            }

            try {
                if (onlyLastVersion) {
                    // Version :
                    T old = container.getMember(key);

                    if (old == null) {
                        container.addMember(key, (T) objectToAdd);
                    } else {
                        String oldVersion = ((IRepositoryViewObject) old).getVersion();
                        if (VersionUtils.compareTo(oldVersion, currentVersion) < 0) {
                            container.addMember(key, (T) objectToAdd);
                        }
                    }
                } else {
                    container.addMember(key, (T) objectToAdd);
                }
            } catch (BusinessException e) {
                throw new PersistenceException(e);
            }
        }

    }

    public List<IRepositoryViewObject> getAll(Project project, ERepositoryObjectType type, boolean withDeleted,
            boolean allVersions) throws PersistenceException {
        IFolder folder = null;
        try {
            if (type.hasFolder()) {
                folder = LocalResourceModelUtils.getFolder(project, type);
            } else {
                return Collections.emptyList();
            }
        } catch (ResourceNotFoundException e) {
            return Collections.emptyList();
        }
        return convert(getSerializableFromFolder(project, folder, null, type, allVersions, true, withDeleted));
    }

    /**
     * 
     * Get all object in a folder recursively.
     * 
     * @param folder - the folder to search in
     * @param id - the id of the object searched. Specify <code>null</code> to get all objects.
     * @param type - the type searched
     * @param allVersion - <code>true</code> if all version of each object must be return or <code>false</code> if only
     * the most recent version
     * @return a list (may be empty) of objects found
     * @throws PersistenceException
     */
    protected List<IRepositoryViewObject> getSerializableFromFolder(Project project, Object folder, String id,
            ERepositoryObjectType type, boolean allVersion, boolean searchInChildren, boolean withDeleted,
            boolean... recursiveCall) throws PersistenceException {
        List<IRepositoryViewObject> toReturn = new VersionList(allVersion);
        FolderHelper folderHelper = getFolderHelper(project.getEmfProject());

        if (recursiveCall.length == 0 || !recursiveCall[0]) {
            projectModified = false;
        }

        if (folder != null) {
            FolderItem currentFolderItem;
            if (folder instanceof IFolder) {
                currentFolderItem = folderHelper.getFolder(((IFolder) folder).getProjectRelativePath());
                if (((IFolder) folder).getLocation().toPortableString().contains("bin")) {
                    // don't do anything for bin directory
                } else if (currentFolderItem == null) {
                    // create folder
                    currentFolderItem = folderHelper.createFolder(((IFolder) folder).getProjectRelativePath().toPortableString());
                }
            } else {
                currentFolderItem = (FolderItem) folder;
            }
            List<String> nameFounds = new ArrayList<String>();
            List<Item> toRemoveFromFolder = new ArrayList<Item>();
            if (currentFolderItem != null) {
                for (Item curItem : (List<Item>) currentFolderItem.getChildren()) {
                    if (curItem instanceof FolderItem && searchInChildren) {
                        toReturn
                                .addAll(getSerializableFromFolder(project, curItem, id, type, allVersion, true, withDeleted, true));
                    } else if (!(curItem instanceof FolderItem)) {
                        Property property = curItem.getProperty();
                        if (property != null) {
                            if (id == null || property.getId().equals(id)) {
                                if (withDeleted || !property.getItem().getState().isDeleted()) {
                                    toReturn.add(new RepositoryObject(property));
                                }
                            }
                            nameFounds.add(property.getLabel() + "_" + property.getVersion() + "."
                                    + FileConstants.PROPERTIES_EXTENSION);
                            property.getItem().setParent(currentFolderItem);
                            addToHistory(id, type, property.getItem().getState().getPath());
                        } else {
                            toRemoveFromFolder.add(curItem);
                        }
                    }
                }
                if (toRemoveFromFolder.size() != 0) {
                    currentFolderItem.getChildren().removeAll(toRemoveFromFolder);
                    projectModified = true;
                    // System.out.println("bug !!! should never go here");
                }
            }
            // if not logged on project, allow to browse directly the folders.
            // if already logged, project should be up to date.
            if (folder instanceof IFolder) {
                String projectPath = project.getTechnicalLabel() + ":"
                        + ((IFolder) folder).getProjectRelativePath().toPortableString();
                if (!loadedFolders.contains(projectPath)) {
                    loadedFolders.add(projectPath);
                    IFolder folder2 = (IFolder) folder;
                    if (folder2.exists()) {
                        for (IResource current : ResourceUtils.getMembers(folder2)) {
                            if (current instanceof IFile) {
                                String fileName = ((IFile) current).getName();
                                if (xmiResourceManager.isPropertyFile((IFile) current) && !nameFounds.contains(fileName)) {
                                    Property property = null;
                                    try {
                                        property = xmiResourceManager.loadProperty(current);
                                        addToHistory(id, type, property.getItem().getState().getPath());
                                    } catch (RuntimeException e) {
                                        // property will be null
                                        ExceptionHandler.process(e);
                                    }
                                    if (property != null) {
                                        // System.out.println("new propertyLoaded:" + property.getLabel());
                                        if (id == null || property.getId().equals(id)) {
                                            if (withDeleted || !property.getItem().getState().isDeleted()) {
                                                toReturn.add(new RepositoryObject(property));
                                            }
                                        }
                                        currentFolderItem.getChildren().add(property.getItem());
                                        property.getItem().setParent(currentFolderItem);
                                        projectModified = true;
                                    } else {
                                        log.error(Messages.getString("LocalRepositoryFactory.CannotLoadProperty") + current); //$NON-NLS-1$
                                    }
                                }
                            } else if (current instanceof IFolder) { // &&
                                if (!FilesUtils.isSVNFolder(current) && searchInChildren) {
                                    if (((IFolder) current).getLocation().toPortableString().contains("bin")) {
                                        // don't do anything for bin directory
                                    } else {
                                        toReturn.addAll(getSerializableFromFolder(project, (IFolder) current, id, type,
                                                allVersion, true, withDeleted, true));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if ((recursiveCall.length != 0 && !recursiveCall[0] && projectModified) || (recursiveCall.length == 0 && projectModified)) {
            saveProject(project);
        }
        return toReturn;
    }

    static {
        // /PTODO tgu quick fix for registering the emf package. needed to make
        // the extention point work
        ConnectionPackage.eINSTANCE.getClass();
    }

    public Project createProject(Project projectInfor) throws PersistenceException {
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();

        String technicalLabel = Project.createTechnicalName(projectInfor.getLabel());
        IProject prj = root.getProject(technicalLabel);

        final IWorkspace workspace = ResourcesPlugin.getWorkspace();
        final IProjectDescription desc = workspace.newProjectDescription(projectInfor.getLabel());
        desc.setNatureIds(new String[] { TalendNature.ID });
        desc.setComment(projectInfor.getDescription());

        try {
            prj.create(desc, null);
            prj.open(IResource.BACKGROUND_REFRESH, null);
            prj.setDefaultCharset("UTF-8", null);
        } catch (CoreException e) {
            throw new PersistenceException(e);
        }

        Project project = new Project();
        // Fill project object
        project.setLabel(projectInfor.getLabel());
        project.setDescription(projectInfor.getDescription());
        project.setLanguage(projectInfor.getLanguage());
        project.setAuthor(projectInfor.getAuthor());
        project.setLocal(true);
        project.setTechnicalLabel(technicalLabel);

        saveProject(prj, project);

        return project;
    }

    public void saveProject(Project project) throws PersistenceException {
        Resource projectResource = project.getEmfProject().eResource();
        xmiResourceManager.saveResource(projectResource);
    }

    /**
     * DOC qwei Comment method "saveProjectf".
     * 
     * @param author
     * @param prj
     * @param project
     * @throws PersistenceException
     */
    private void saveProject(IProject prj, Project project) throws PersistenceException {
        Resource projectResource = xmiResourceManager.createProjectResource(prj);
        projectResource.getContents().add(project.getEmfProject());
        projectResource.getContents().add(project.getAuthor());
        xmiResourceManager.saveResource(projectResource);
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
        // IFolder f1 = ResourceUtils.getFolder(prj, ERepositoryObjectType.getFolderName(ERepositoryObjectType.ROUTINES)
        // + IPath.SEPARATOR + RepositoryConstants.SYSTEM_DIRECTORY, false);
        // ResourceUtils.deleteResource(f1);

        // MOD mzhao 15422: Unable to open the MDM workspace
        try {
            createSystemRoutines();
        } catch (Exception e) {
            log.error(e, e);
        }
    }

    public void synchronizeSqlpatterns(IProject prj) throws PersistenceException {
        if (prj == null) {
            Project project = getRepositoryContext().getProject();
            prj = ResourceModelUtils.getProject(project);
        }

        // Purge old sqlpatterns :
        // 1. old built-in:
        // IFolder sqlpatternRoot = ResourceUtils.getFolder(prj, ERepositoryObjectType
        // .getFolderName(ERepositoryObjectType.SQLPATTERNS), false);
        // clearSystemSqlPatterns(sqlpatternRoot);
        createSystemSQLPatterns();
    }

    /**
     * bqian Comment method "clearSystemSqlPatterns".
     * 
     * @param f1
     */
    private void clearSystemSqlPatterns(IFolder sqlPatternFolder) {
        try {
            for (IResource resource : sqlPatternFolder.members()) {
                if (resource.getType() == IResource.FOLDER) {
                    IFolder category = (IFolder) resource;
                    IFolder sysFolder = (IFolder) category.getFolder(RepositoryConstants.SYSTEM_DIRECTORY);
                    if (sysFolder != null && sysFolder.exists()) {
                        ResourceUtils.emptyFolder(sysFolder);
                    }
                }
            }
        } catch (Exception e) {
            ExceptionHandler.process(e);
        }
    }

    /**
     * DOC smallet Comment method "createFolders".
     * 
     * @param prj
     * @param project
     * @throws PersistenceException
     */
    private void createFolders(IProject prj, org.talend.core.model.properties.Project emfProject) throws PersistenceException {
        FolderHelper folderHelper = getFolderHelper(emfProject);

        // Folder creation :
        for (ERepositoryObjectType type : ERepositoryObjectType.values()) {
            try {
                if (type.hasFolder()) {
                    String folderName = ERepositoryObjectType.getFolderName(type);
                    createFolder(prj, folderHelper, folderName);
                }
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
        createFolder(prj, folderHelper, RepositoryConstants.IMG_DIRECTORY_OF_JOBLET_OUTLINE);

        // 4. Code/routines/Snippets :
        createFolder(prj, folderHelper, "code/routines"); //$NON-NLS-1$  
        // 5. Job Disigns/System
        // createFolder(prj, folderHelper, "process/system"); //$NON-NLS-1$

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
        }
        if (folderHelper.getFolder(new Path(path)) == null) {
            folderHelper.createSystemFolder(new Path(path));
        }
    }

    public Project[] readProject(boolean local) throws PersistenceException {
        // TODO SML Delete this method when remote is implemented

        IProject[] prjs = ResourceUtils.getProjetWithNature(TalendNature.ID);

        List<Project> toReturn = new ArrayList<Project>();

        Exception ex = null;
        int readSuccess = 0;
        for (int i = 0; i < prjs.length; i++) {
            IProject p = prjs[i];
            try {
                readProject(local, toReturn, p, false);
                readProject(local, toReturn, p, true);
                readSuccess++;
            } catch (Exception e) {
                ExceptionHandler.process(e);
            }

        }
        if (readSuccess == 0 && prjs.length > 0) {
            throw new PersistenceException("Read projects error.", ex); //$NON-NLS-1$
        } else {
            return toReturn.toArray(new Project[toReturn.size()]);
        }
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

                        // ignore folders with . (e.g. : .settings) see bug 364
                        if (path.lastSegment().equals(".settings")) { //$NON-NLS-1$
                            return false;
                        }
                        if (path.lastSegment().equals("bin")) { //$NON-NLS-1$
                            return false;
                        }
                        if (path.toPortableString().equals("lib")) { //$NON-NLS-1$
                            return false;
                        }

                        if (!listFolders.remove(path)) {
                            // create emf folder
                            helper.createFolder(path);
                        } else {
                            // add state to existing emf folder
                            FolderItem folder = helper.getFolder(path);
                            if (folder != null && folder.getState() == null) {
                                helper.createItemState(folder);
                            }
                        }
                    }
                    return true;
                }

            });
        } catch (CoreException e) {
            // e.printStackTrace();
            ExceptionHandler.process(e);
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
     * @see org.talend.designer.core.extension.IRepositoryFactory#createFolder(org .talend.core.model.temp.Project,
     * org.talend.core.model.repository.EObjectType, org.eclipse.core.runtime.IPath, java.lang.String)
     */

    public Folder createFolder(Project project, ERepositoryObjectType type, IPath path, String label) throws PersistenceException {
        if (type == null) {
            throw new IllegalArgumentException(Messages.getString("LocalRepositoryFactory.illegalArgumentException01")); //$NON-NLS-1$
        }
        if (path == null) {
            throw new IllegalArgumentException(Messages.getString("LocalRepositoryFactory.illegalArgumentException02")); //$NON-NLS-1$
        }
        if (label == null || label.length() == 0) {
            throw new IllegalArgumentException(Messages.getString("LocalRepositoryFactory.illegalArgumentException03")); //$NON-NLS-1$
        }

        IProject fsProject = ResourceModelUtils.getProject(project);

        String parentPath = ERepositoryObjectType.getFolderName(type);
        if (!path.isEmpty()) {
            parentPath += IPath.SEPARATOR + path.toString();
        }

        String completePath = parentPath + IPath.SEPARATOR + label;
        FolderItem folderItem = getFolderHelper(project.getEmfProject()).createFolder(completePath);
        xmiResourceManager.saveResource(project.getEmfProject().eResource());
        // Getting the folder :
        IFolder folder = ResourceUtils.getFolder(fsProject, completePath, false);
        if (!folder.exists()) {
            ResourceUtils.createFolder(folder);
        }

        return new Folder(folderItem.getProperty(), type);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.model.IRepositoryFactory#isValid(org.talend.core .model.general.Project,
     * org.talend.core.model.repository.ERepositoryObjectType, org.eclipse.core.runtime.IPath, java.lang.String)
     */

    public boolean isPathValid(Project project, ERepositoryObjectType type, IPath path, String label) throws PersistenceException {
        if (path == null) {
            return false;
        }

        if (label.equalsIgnoreCase("bin")) {
            return false;
        } else if (RepositoryConstants.isSystemFolder(label) || RepositoryConstants.isGeneratedFolder(label)
                || RepositoryConstants.isJobsFolder(label) || RepositoryConstants.isJobletsFolder(label)) {
            // can't create the "system" ,"Generated", "Jobs" folder in the
            // root.
            return false;
        } else {
            // TODO SML Delete this ?
            IProject fsProject = ResourceModelUtils.getProject(project);
            String completePath = ERepositoryObjectType.getFolderName(type) + IPath.SEPARATOR + path.toString();

            // if label is "", don't need to added label into completePath (used
            // for job's documentation and for folder
            // only)
            // if (!label.equals("")) {
            completePath = completePath + IPath.SEPARATOR + label;
            // }

            // Getting the folder :
            IFolder existingFolder = ResourceUtils.getFolder(fsProject, completePath, false);
            return !existingFolder.exists();
        }
    }

    public void deleteFolder(Project project, ERepositoryObjectType type, IPath path) throws PersistenceException {

        // If the "System", "Generated", "Jobs" folder is created in the root,
        // it can't be deleted .
        if (RepositoryConstants.isSystemFolder(path.toString()) || RepositoryConstants.isGeneratedFolder(path.toString())
                || RepositoryConstants.isJobsFolder(path.toString()) || RepositoryConstants.isJobletsFolder(path.toString())) {
            return;
        }
        IProject fsProject = ResourceModelUtils.getProject(project);

        String completePath = ERepositoryObjectType.getFolderName(type) + IPath.SEPARATOR + path.toString();

        // Getting the folder :
        IFolder folder = ResourceUtils.getFolder(fsProject, completePath, true);
        getFolderHelper(project.getEmfProject()).deleteFolder(completePath);
        xmiResourceManager.saveResource(project.getEmfProject().eResource());

        ResourceUtils.deleteResource(folder);
    }

    public void moveFolder(ERepositoryObjectType type, IPath sourcePath, IPath targetPath) throws PersistenceException {
        if (RepositoryConstants.isSystemFolder(sourcePath.toString())
                || RepositoryConstants.isSystemFolder(targetPath.toString())) {
            // The "system" folder wasn't allowed to move
            return;
        }
        Project project = getRepositoryContext().getProject();
        IProject fsProject = ResourceModelUtils.getProject(project);

        String completeOldPath = ERepositoryObjectType.getFolderName(type) + IPath.SEPARATOR + sourcePath.toString();
        String completeNewPath;
        if (targetPath.equals("")) {
            completeNewPath = ERepositoryObjectType.getFolderName(type) + IPath.SEPARATOR + sourcePath.lastSegment();
        } else {
            completeNewPath = ERepositoryObjectType.getFolderName(type) + IPath.SEPARATOR + targetPath.toString()
                    + IPath.SEPARATOR + sourcePath.lastSegment();
        }
        if (completeNewPath.equals(completeOldPath)) {
            // nothing to do
            return;
        }
        // Getting the folder :
        IFolder folder = ResourceUtils.getFolder(fsProject, completeOldPath, false);

        FolderHelper folderHelper = getFolderHelper(getRepositoryContext().getProject().getEmfProject());
        FolderItem emfFolder = folderHelper.getFolder(completeOldPath);

        createFolder(getRepositoryContext().getProject(), type, targetPath, emfFolder.getProperty().getLabel());
        FolderItem newFolder = folderHelper.getFolder(completeNewPath);

        Item[] childrens = (Item[]) emfFolder.getChildren().toArray();
        for (int i = 0; i < childrens.length; i++) {
            if (childrens[i] instanceof FolderItem) {
                FolderItem children = (FolderItem) childrens[i];
                moveFolder(type, sourcePath.append(children.getProperty().getLabel()), targetPath.append(emfFolder.getProperty()
                        .getLabel()));
            } else {
                emfFolder.getChildren().remove(childrens[i]);
                newFolder.getChildren().add(childrens[i]);
                childrens[i].setParent(newFolder);
            }
        }

        List<IRepositoryViewObject> serializableFromFolder = getSerializableFromFolder(project, folder, null, type, true, true,
                true);
        for (IRepositoryViewObject object : serializableFromFolder) {
            List<Resource> affectedResources = xmiResourceManager.getAffectedResources(object.getProperty());
            for (Resource resource : affectedResources) {
                IPath path = getPhysicalProject(project).getFullPath().append(completeNewPath).append(
                        resource.getURI().lastSegment());
                xmiResourceManager.moveResource(resource, path);
            }
        }

        deleteFolder(getRepositoryContext().getProject(), type, sourcePath);

        xmiResourceManager.saveResource(getRepositoryContext().getProject().getEmfProject().eResource());
    }

    public void renameFolder(ERepositoryObjectType type, IPath path, String label) throws PersistenceException {
        IProject fsProject = ResourceModelUtils.getProject(getRepositoryContext().getProject());

        String completePath = ERepositoryObjectType.getFolderName(type) + IPath.SEPARATOR + path.toString();
        // Getting the folder :
        IFolder folder = ResourceUtils.getFolder(fsProject, completePath, false);

        // IPath targetPath = new
        // Path(SystemFolderNameFactory.getFolderName(type)).append(path).
        // removeLastSegments(1).append(label);
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
    public List<IRepositoryViewObject> getProcess2() throws PersistenceException {
        List<IRepositoryViewObject> toReturn = new VersionList(false);

        IFolder folder = LocalResourceModelUtils.getFolder(getRepositoryContext().getProject(), ERepositoryObjectType.PROCESS);

        for (IResource current : ResourceUtils.getMembers(folder)) {
            if (current instanceof IFile) {
                if (xmiResourceManager.isPropertyFile((IFile) current)) {
                    Property property = xmiResourceManager.loadProperty(current);
                    toReturn.add(new RepositoryObject(property));
                }
            }
        }
        return toReturn;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.model.IRepositoryFactory#deleteObject(org.talend .core.model.general.Project,
     * org.talend.core.model.repository.IRepositoryViewObject)
     */

    public void deleteObjectLogical(Project project, IRepositoryViewObject objToDelete) throws PersistenceException {

        // can only delete in the main project
        // IProject fsProject = ResourceModelUtils.getProject(project);

        // IFolder bin = ResourceUtils.getFolder(fsProject, ERepositoryObjectType.getFolderName(objToDelete.getType())
        // + IPath.SEPARATOR + BIN, true);

        List<IRepositoryViewObject> allVersionToDelete = getAllVersion(project, objToDelete.getId());
        for (IRepositoryViewObject currentVersion : allVersionToDelete) {
            ItemState state = currentVersion.getProperty().getItem().getState();
            state.setDeleted(true);
            xmiResourceManager.saveResource(state.eResource());
        }
    }

    public void deleteObjectPhysical(Project project, IRepositoryViewObject objToDelete) throws PersistenceException {
        deleteObjectPhysical(project, objToDelete, null);
    }

    public void deleteObjectPhysical(Project project, IRepositoryViewObject objToDelete, String version)
            throws PersistenceException {
        if ("".equals(version)) { //$NON-NLS-1$
            version = null; // for all version
        }
        // can only delete in the main project
        List<IRepositoryViewObject> allVersionToDelete = getAllVersion(project, objToDelete.getId());
        for (IRepositoryViewObject currentVersion : allVersionToDelete) {
            if (version == null || currentVersion.getVersion().equals(version)) {
                Item currentItem = currentVersion.getProperty().getItem();
                if (currentItem.getParent() instanceof FolderItem) {
                    ((FolderItem) currentItem.getParent()).getChildren().remove(currentItem);
                }
                currentItem.setParent(null);
                List<Resource> affectedResources = xmiResourceManager.getAffectedResources(currentVersion.getProperty());
                for (Resource resource : affectedResources) {
                    xmiResourceManager.deleteResource(resource);
                }
            }
        }
        saveProject(project);
    }

    public void restoreObject(IRepositoryViewObject objToRestore, IPath path) throws PersistenceException {
        // IProject fsProject = ResourceModelUtils.getProject(getRepositoryContext().getProject());
        // IFolder typeRootFolder = ResourceUtils.getFolder(fsProject,
        // ERepositoryObjectType.getFolderName(objToRestore.getType()),
        // true);
        // IPath thePath = (path == null ? typeRootFolder.getFullPath() :
        // typeRootFolder.getFullPath().append(path));
        // org.talend.core.model.properties.Project project = xmiResourceManager.loadProject(getProject());

        List<IRepositoryViewObject> allVersionToDelete = getAllVersion(getRepositoryContext().getProject(), objToRestore.getId());
        for (IRepositoryViewObject currentVersion : allVersionToDelete) {
            ItemState itemState = currentVersion.getProperty().getItem().getState();
            itemState.setDeleted(false);
            xmiResourceManager.saveResource(itemState.eResource());
        }

        // xmiResourceManager.saveResource(project.eResource());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.model.IRepositoryFactory#moveObject(org.talend. core.model.general.Project,
     * org.talend.core.model.repository.IRepositoryViewObject, org.eclipse.core.runtime.IPath)
     */
    public void moveObject(IRepositoryViewObject objToMove, IPath newPath) throws PersistenceException {
        Project project = getRepositoryContext().getProject();
        IProject fsProject = ResourceModelUtils.getProject(project);
        String folderName = ERepositoryObjectType.getFolderName(objToMove.getType()) + IPath.SEPARATOR + newPath;
        IFolder folder = ResourceUtils.getFolder(fsProject, folderName, true);

        List<IRepositoryViewObject> allVersionToMove = getAllVersion(getRepositoryContext().getProject(), objToMove.getId());
        for (IRepositoryViewObject obj : allVersionToMove) {
            Item currentItem = obj.getProperty().getItem();
            if (currentItem.getParent() instanceof FolderItem) {
                ((FolderItem) currentItem.getParent()).getChildren().remove(currentItem);
            }
            FolderItem newFolderItem = getFolderItem(project, objToMove.getType(), newPath);
            newFolderItem.getChildren().add(currentItem);
            currentItem.setParent(newFolderItem);

            ItemState state = obj.getProperty().getItem().getState();
            state.setPath(newPath.toString());
            xmiResourceManager.saveResource(state.eResource());

            // all resources attached must be saved before move the resources
            List<Resource> affectedResources = xmiResourceManager.getAffectedResources(obj.getProperty());
            for (Resource resource : affectedResources) {
                xmiResourceManager.saveResource(resource);
            }
            for (Resource resource : affectedResources) {
                IPath path = folder.getFullPath().append(resource.getURI().lastSegment());
                xmiResourceManager.moveResource(resource, path);
            }
            // all resources attached must be saved again after move the resources, or author will link to wrong path
            // for project file
            affectedResources = xmiResourceManager.getAffectedResources(obj.getProperty());
            for (Resource resource : affectedResources) {
                xmiResourceManager.saveResource(resource);
            }
        }
        saveProject(project);
    }

    public XmiResourceManager xmiResourceManager = new XmiResourceManager();

    public void lock(Item item) throws PersistenceException {
        if (getStatus(item) == ERepositoryStatus.DEFAULT) {
            // lockedObject.put(item.getProperty().getId(), new LockedObject(new
            // Date(), repositoryContext.getUser()));
            item.getState().setLockDate(new Date());
            item.getState().setLocker(getRepositoryContext().getUser());
            item.getState().setLocked(true);
            xmiResourceManager.saveResource(item.getProperty().eResource());
        }
    }

    public void unlock(Item item) throws PersistenceException {
        if (getStatus(item) == ERepositoryStatus.LOCK_BY_USER || item instanceof JobletDocumentationItem
                || item instanceof JobDocumentationItem) {
            // lockedObject.remove(obj.getProperty().getId());
            item.getState().setLocker(null);
            item.getState().setLockDate(null);
            item.getState().setLocked(false);
            xmiResourceManager.saveResource(item.getProperty().eResource());
        }
    }

    public List<Status> getDocumentationStatus() throws PersistenceException {
        // reloadProject(ProjectManager.getInstance().getCurrentProject());
        return copyList(ProjectManager.getInstance().getCurrentProject().getEmfProject().getDocumentationStatus());
    }

    public List<Status> getTechnicalStatus() throws PersistenceException {
        // reloadProject(ProjectManager.getInstance().getCurrentProject());
        return copyList(ProjectManager.getInstance().getCurrentProject().getEmfProject().getTechnicalStatus());
    }

    public List<SpagoBiServer> getSpagoBiServer() throws PersistenceException {
        // reloadProject(ProjectManager.getInstance().getCurrentProject());
        return copyListOfSpagoBiServer(ProjectManager.getInstance().getCurrentProject().getEmfProject().getSpagoBiServer());
    }

    private List<Status> copyList(List listOfStatus) {
        List<Status> result = new ArrayList<Status>();
        for (Object obj : listOfStatus) {
            result.add((Status) obj);
        }
        return result;
    }

    private List<SpagoBiServer> copyListOfSpagoBiServer(List listOfSpagoBiServer) {
        List<SpagoBiServer> result = new ArrayList<SpagoBiServer>();
        for (Object obj : listOfSpagoBiServer) {
            result.add((SpagoBiServer) obj);
        }
        return result;
    }

    public void setDocumentationStatus(List<Status> list) throws PersistenceException {
        Project curProject = ProjectManager.getInstance().getCurrentProject();
        // reloadProject(curProject);
        curProject.getEmfProject().getDocumentationStatus().clear();
        curProject.getEmfProject().getDocumentationStatus().addAll(list);
        xmiResourceManager.saveResource(curProject.getEmfProject().eResource());
    }

    public void setTechnicalStatus(List<Status> list) throws PersistenceException {
        Project curProject = ProjectManager.getInstance().getCurrentProject();
        // reloadProject(curProject);
        curProject.getEmfProject().getTechnicalStatus().clear();
        curProject.getEmfProject().getTechnicalStatus().addAll(list);
        xmiResourceManager.saveResource(curProject.getEmfProject().eResource());
    }

    public void setSpagoBiServer(List<SpagoBiServer> list) throws PersistenceException {
        Project curProject = ProjectManager.getInstance().getCurrentProject();
        // reloadProject(curProject);
        curProject.getEmfProject().getSpagoBiServer().clear();
        curProject.getEmfProject().getSpagoBiServer().addAll(list);
        xmiResourceManager.saveResource(curProject.getEmfProject().eResource());
    }

    public void setMigrationTasksDone(Project project, List<String> list) throws PersistenceException {
        // reloadProject(project);
        // IProject iproject = ResourceModelUtils.getProject(project);
        // org.talend.core.model.properties.Project loadProject = xmiResourceManager.loadProject(iproject);
        project.getEmfProject().getMigrationTasks().clear();
        project.getEmfProject().getMigrationTasks().addAll(list);
        xmiResourceManager.saveResource(project.getEmfProject().eResource());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.model.IRepositoryFactory#isServerValid(java.lang .String, java.lang.String, int)
     */
    public String isServerValid() {
        return ""; //$NON-NLS-1$
    }

    private Resource create(IProject project, BusinessProcessItem item, IPath path) throws PersistenceException {
        Resource itemResource = xmiResourceManager.createItemResource(project, item, path,
                ERepositoryObjectType.BUSINESS_PROCESS, false);
        // notation depends on semantic ...
        // in case of new(=empty) diagram, we don't care about order
        // in other cases, the ordered addition references between notaion and
        // semantic will be updated
        itemResource.getContents().add(item.getSemantic());
        itemResource.getContents().add(item.getNotationHolder());
        item.computeNotationHolder();

        return itemResource;
    }

    private Resource create(IProject project, ConnectionItem item, ERepositoryObjectType type, IPath path)
            throws PersistenceException {
        Resource itemResource = xmiResourceManager.createItemResource(project, item, path, type, false);

        // itemResource.getContents().add(item.getConnection());
        MetadataManager.addContents(item, itemResource); // hywang 13221

        return itemResource;
    }

    private Resource create(IProject project, JobDocumentationItem item, IPath path, ERepositoryObjectType type)
            throws PersistenceException {
        Resource itemResource = xmiResourceManager.createItemResource(project, item, path, type, true);
        itemResource.getContents().clear();
        itemResource.getContents().add(item.getContent());

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
        MetadataManager.addContents(item, itemResource); // 13221

        // add to the current resource all Document and Description instances because they are not reference in
        // containment references.
        Map<EObject, Collection<Setting>> externalCrossref = EcoreUtil.ExternalCrossReferencer.find(item.getConnection());
        Collection<Object> documents = EcoreUtil.getObjectsByType(externalCrossref.keySet(),
                BusinessinformationPackage.Literals.DOCUMENT);
        for (Object doc : documents) {
            itemResource.getContents().add((EObject) doc);
        }
        Collection<Object> descriptions = EcoreUtil.getObjectsByType(externalCrossref.keySet(),
                BusinessinformationPackage.Literals.DESCRIPTION);
        for (Object doc : descriptions) {
            itemResource.getContents().add((EObject) doc);
        }
        // itemResource.getContents().add(item.getConnection());

        return itemResource;
    }

    private Resource create(IProject project, FileItem item, IPath path, ERepositoryObjectType type) throws PersistenceException {
        Resource itemResource = xmiResourceManager.createItemResource(project, item, path, type, true);

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

    private Resource create(IProject project, ProcessItem item, IPath path) throws PersistenceException {
        Resource itemResource = xmiResourceManager.createItemResource(project, item, path, ERepositoryObjectType.PROCESS, false);
        itemResource.getContents().add(item.getProcess());

        return itemResource;
    }

    private Resource create(IProject project, JobletProcessItem item, IPath path, ERepositoryObjectType type)
            throws PersistenceException {
        Resource itemResource = xmiResourceManager.createItemResource(project, item, path, type, false);
        itemResource.getContents().add(item.getJobletProcess());
        ByteArray icon = item.getIcon();
        if (icon != null) {
            itemResource.getContents().add(icon);
        }
        return itemResource;
    }

    private Resource create(IProject project, ContextItem item, IPath path) throws PersistenceException {
        Resource itemResource = xmiResourceManager.createItemResource(project, item, path, ERepositoryObjectType.CONTEXT, false);
        itemResource.getContents().addAll(item.getContext());

        return itemResource;
    }

    private Resource save(ContextItem item) {
        Resource itemResource = xmiResourceManager.getItemResource(item);

        itemResource.getContents().clear();
        itemResource.getContents().addAll(item.getContext());

        return itemResource;
    }

    private Resource create(IProject project, SnippetItem item, IPath path) throws PersistenceException {
        Resource itemResource = xmiResourceManager.createItemResource(project, item, path, ERepositoryObjectType.SNIPPETS, false);
        itemResource.getContents().addAll(item.getVariables());

        return itemResource;
    }

    private Resource save(SnippetItem item) {
        Resource itemResource = xmiResourceManager.getItemResource(item);

        itemResource.getContents().clear();
        itemResource.getContents().addAll(item.getVariables());

        return itemResource;
    }

    private Resource save(ProcessItem item) {
        Resource itemResource = xmiResourceManager.getItemResource(item);

        itemResource.getContents().clear();
        itemResource.getContents().add(item.getProcess());

        return itemResource;
    }

    private Resource save(JobletProcessItem item) {
        Resource itemResource = xmiResourceManager.getItemResource(item);

        itemResource.getContents().clear();
        itemResource.getContents().add(item.getJobletProcess());
        if (item.getIcon() == null) {
            item.setIcon(PropertiesFactory.eINSTANCE.createByteArray());
            ImageDescriptor imageDesc = ImageProvider.getImageDesc(ECoreImage.JOBLET_COMPONENT_ICON);
            item.getIcon().setInnerContent(ImageUtils.saveImageToData(imageDesc));
        }
        if (item.getIcon() != null) {
            itemResource.getContents().add(item.getIcon());
        }
        return itemResource;
    }

    private Resource create(IProject project, LinkDocumentationItem item, IPath path) throws PersistenceException {
        Resource itemResource = xmiResourceManager.createItemResource(project, item, path, ERepositoryObjectType.DOCUMENTATION,
                false);
        itemResource.getContents().add(item.getLink());

        return itemResource;
    }

    private Resource create(IProject project, LinkRulesItem item, IPath path) throws PersistenceException { // hywang
        // 6484
        Resource itemResource = xmiResourceManager.createItemResource(project, item, path,
                ERepositoryObjectType.METADATA_FILE_LINKRULES, false);
        itemResource.getContents().add(item.getLink());

        return itemResource;
    }

    private Resource save(LinkDocumentationItem item) {
        Resource itemResource = xmiResourceManager.getItemResource(item);

        itemResource.getContents().clear();
        itemResource.getContents().add(item.getLink());

        return itemResource;
    }

    public void save(Project project, Item item) throws PersistenceException {
        computePropertyMaxInformationLevel(item.getProperty());

        item.getProperty().setModificationDate(new Date());
        Resource itemResource;
        EClass eClass = item.eClass();
        if (eClass.eContainer() == PropertiesPackage.eINSTANCE) {
            switch (eClass.getClassifierID()) {

            case PropertiesPackage.HEADER_FOOTER_CONNECTION_ITEM:
                itemResource = save((HeaderFooterConnectionItem) item);
                break;
            case PropertiesPackage.BUSINESS_PROCESS_ITEM:
                itemResource = save((BusinessProcessItem) item);
                break;
            case PropertiesPackage.SVG_BUSINESS_PROCESS_ITEM:
                itemResource = save((SVGBusinessProcessItem) item);
                break;
            case PropertiesPackage.POSITIONAL_FILE_CONNECTION_ITEM:
            case PropertiesPackage.DELIMITED_FILE_CONNECTION_ITEM:
            case PropertiesPackage.DATABASE_CONNECTION_ITEM:
            case PropertiesPackage.REG_EX_FILE_CONNECTION_ITEM:
            case PropertiesPackage.XML_FILE_CONNECTION_ITEM:
            case PropertiesPackage.EXCEL_FILE_CONNECTION_ITEM:
            case PropertiesPackage.LDAP_SCHEMA_CONNECTION_ITEM:
            case PropertiesPackage.SALESFORCE_SCHEMA_CONNECTION_ITEM:
            case PropertiesPackage.WSDL_SCHEMA_CONNECTION_ITEM:
            case PropertiesPackage.SAP_CONNECTION_ITEM:
            case PropertiesPackage.MDM_CONNECTION_ITEM:
            case PropertiesPackage.HL7_CONNECTION_ITEM:
            case PropertiesPackage.FTP_CONNECTION_ITEM:
            case PropertiesPackage.EBCDIC_CONNECTION_ITEM:
                // not really usefull for ConnectionItem : it's not copied to
                // another resource for edition
                itemResource = save((ConnectionItem) item);
                break;
            case PropertiesPackage.LDIF_FILE_CONNECTION_ITEM:
                // not really usefull for ConnectionItem : it's not copied to
                // another resource for edition
                itemResource = save((ConnectionItem) item);
                break;
            case PropertiesPackage.GENERIC_SCHEMA_CONNECTION_ITEM:
                // not really usefull for ConnectionItem : it's not copied to
                // another resource for edition
                itemResource = save((ConnectionItem) item);
                break;
            case PropertiesPackage.DOCUMENTATION_ITEM:
            case PropertiesPackage.ROUTINE_ITEM:
            case PropertiesPackage.SQL_PATTERN_ITEM:
                itemResource = save((FileItem) item);
                break;
            case PropertiesPackage.PROCESS_ITEM:
                itemResource = save((ProcessItem) item);
                break;
            case PropertiesPackage.JOBLET_PROCESS_ITEM:
                itemResource = save((JobletProcessItem) item);
                break;
            case PropertiesPackage.CONTEXT_ITEM:
                itemResource = save((ContextItem) item);
                break;
            case PropertiesPackage.SNIPPET_ITEM:
                itemResource = save((SnippetItem) item);
                break;
            case PropertiesPackage.JOB_DOCUMENTATION_ITEM:
                itemResource = save((JobDocumentationItem) item);
                break;
            case PropertiesPackage.JOBLET_DOCUMENTATION_ITEM:
                itemResource = save((JobletDocumentationItem) item);
                break;
            case PropertiesPackage.LINK_DOCUMENTATION_ITEM:
                itemResource = save((LinkDocumentationItem) item);
                break;
            case PropertiesPackage.RULES_ITEM:// feature 6484 added
                itemResource = save((RulesItem) item);
                break;
            default:
                throw new UnsupportedOperationException();
            }
        } else {
            throw new UnsupportedOperationException();
        }

        propagateFileName(project, item.getProperty());

        xmiResourceManager.saveResource(item.eResource());
        xmiResourceManager.saveResource(itemResource);
    }

    public void save(Project project, Property property) throws PersistenceException {
        computePropertyMaxInformationLevel(property);
        propagateFileName(project, property);
        // update the property of the node repository object
        Resource propertyResource = property.eResource();
        if (propertyResource != null) {
            xmiResourceManager.saveResource(propertyResource);
        }
    }

    public Item copy(Item originalItem, IPath path) throws PersistenceException, BusinessException {
        return copy(originalItem, path, true);
    }

    public Item copy(Item originalItem, IPath path, boolean changeLabelWithCopyPrefix) throws PersistenceException,
            BusinessException {
        Resource resource;
        ProjectManager projectManage = ProjectManager.getInstance();
        if (!projectManage.getProject(originalItem).equals(projectManage.getCurrentProject().getEmfProject())) {
            originalItem.getProperty().eResource().getContents().add(originalItem);
        }
        resource = originalItem.getProperty().eResource();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            resource.save(out, null);
            Resource createResource = new ResourceSetImpl().createResource(resource.getURI());
            ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
            createResource.load(in, null);
            Item newItem = copyFromResource(createResource, changeLabelWithCopyPrefix);
            create(getRepositoryContext().getProject(), newItem, path);
            return newItem;
        } catch (IOException e) {
            // e.printStackTrace();
            ExceptionHandler.process(e);
        }

        return null;
    }

    private void propagateFileName(Project project, Property property) throws PersistenceException {
        List<IRepositoryViewObject> allVersionToMove = getAllVersion(project, property.getId());
        for (IRepositoryViewObject object : allVersionToMove) {
            xmiResourceManager.propagateFileName(property, object.getProperty());
        }
    }

    public void create(Project project, Item item, IPath path, boolean... isImportItem) throws PersistenceException {
        computePropertyMaxInformationLevel(item.getProperty());

        if (item.getProperty().getVersion() == null) {
            item.getProperty().setVersion(VersionUtils.DEFAULT_VERSION);
        }
        if (item.getProperty().getAuthor() == null) {
            item.getProperty().setAuthor(getRepositoryContext().getUser());
        }

        if (item.getProperty().getCreationDate() == null) {
            item.getProperty().setCreationDate(new Date());
        }

        if (item.getProperty().getModificationDate() == null) {
            item.getProperty().setModificationDate(item.getProperty().getCreationDate());
        }

        ItemState itemState = PropertiesFactory.eINSTANCE.createItemState();
        if (item.getState() != null) {
            itemState.setDeleted(item.getState().isDeleted());
        } else {
            itemState.setDeleted(false);
        }
        itemState.setPath(path.toString());

        item.setState(itemState);
        IProject project2 = ResourceModelUtils.getProject(project);
        Resource itemResource;
        EClass eClass = item.eClass();
        if (eClass.eContainer() == PropertiesPackage.eINSTANCE) {
            switch (eClass.getClassifierID()) {

            case PropertiesPackage.BUSINESS_PROCESS_ITEM:
                itemResource = create(project2, (BusinessProcessItem) item, path);
                break;
            case PropertiesPackage.SVG_BUSINESS_PROCESS_ITEM:
                itemResource = create(project2, (FileItem) item, path, ERepositoryObjectType.SVG_BUSINESS_PROCESS);
                break;
            case PropertiesPackage.DATABASE_CONNECTION_ITEM:
                itemResource = create(project2, (ConnectionItem) item, ERepositoryObjectType.METADATA_CONNECTIONS, path);
                break;
            case PropertiesPackage.SAP_CONNECTION_ITEM:
                itemResource = create(project2, (ConnectionItem) item, ERepositoryObjectType.METADATA_SAPCONNECTIONS, path);
                break;
            case PropertiesPackage.MDM_CONNECTION_ITEM:
                itemResource = create(project2, (ConnectionItem) item, ERepositoryObjectType.METADATA_MDMCONNECTION, path);
                break;
            case PropertiesPackage.EBCDIC_CONNECTION_ITEM:
                itemResource = create(project2, (EbcdicConnectionItem) item, ERepositoryObjectType.METADATA_FILE_EBCDIC, path);
                break;
            case PropertiesPackage.HL7_CONNECTION_ITEM:
                itemResource = create(project2, (HL7ConnectionItem) item, ERepositoryObjectType.METADATA_FILE_HL7, path);
                break;
            case PropertiesPackage.FTP_CONNECTION_ITEM:
                itemResource = create(project2, (FTPConnectionItem) item, ERepositoryObjectType.METADATA_FILE_FTP, path);
                break;
            case PropertiesPackage.DELIMITED_FILE_CONNECTION_ITEM:
                itemResource = create(project2, (ConnectionItem) item, ERepositoryObjectType.METADATA_FILE_DELIMITED, path);
                break;
            case PropertiesPackage.POSITIONAL_FILE_CONNECTION_ITEM:
                itemResource = create(project2, (ConnectionItem) item, ERepositoryObjectType.METADATA_FILE_POSITIONAL, path);
                break;
            case PropertiesPackage.REG_EX_FILE_CONNECTION_ITEM:
                itemResource = create(project2, (ConnectionItem) item, ERepositoryObjectType.METADATA_FILE_REGEXP, path);
                break;
            case PropertiesPackage.XML_FILE_CONNECTION_ITEM:
                itemResource = create(project2, (ConnectionItem) item, ERepositoryObjectType.METADATA_FILE_XML, path);
                break;
            case PropertiesPackage.EXCEL_FILE_CONNECTION_ITEM:
                itemResource = create(project2, (ConnectionItem) item, ERepositoryObjectType.METADATA_FILE_EXCEL, path);
                break;
            case PropertiesPackage.LDIF_FILE_CONNECTION_ITEM:
                itemResource = create(project2, (ConnectionItem) item, ERepositoryObjectType.METADATA_FILE_LDIF, path);
                break;
            case PropertiesPackage.GENERIC_SCHEMA_CONNECTION_ITEM:
                itemResource = create(project2, (ConnectionItem) item, ERepositoryObjectType.METADATA_GENERIC_SCHEMA, path);
                break;
            case PropertiesPackage.LDAP_SCHEMA_CONNECTION_ITEM:
                itemResource = create(project2, (ConnectionItem) item, ERepositoryObjectType.METADATA_LDAP_SCHEMA, path);
                break;
            case PropertiesPackage.WSDL_SCHEMA_CONNECTION_ITEM:
                itemResource = create(project2, (ConnectionItem) item, ERepositoryObjectType.METADATA_WSDL_SCHEMA, path);
                break;
            case PropertiesPackage.SALESFORCE_SCHEMA_CONNECTION_ITEM:
                itemResource = create(project2, (ConnectionItem) item, ERepositoryObjectType.METADATA_SALESFORCE_SCHEMA, path);
                break;
            case PropertiesPackage.HEADER_FOOTER_CONNECTION_ITEM:
                itemResource = create(project2, (HeaderFooterConnectionItem) item, ERepositoryObjectType.METADATA_HEADER_FOOTER,
                        path);
                break;
            case PropertiesPackage.DOCUMENTATION_ITEM:
                itemResource = create(project2, (FileItem) item, path, ERepositoryObjectType.DOCUMENTATION);
                break;
            case PropertiesPackage.JOB_DOCUMENTATION_ITEM:
                itemResource = create(project2, (JobDocumentationItem) item, path, ERepositoryObjectType.JOB_DOC);
                break;
            case PropertiesPackage.JOBLET_DOCUMENTATION_ITEM:
                itemResource = create(project2, (JobletDocumentationItem) item, path, ERepositoryObjectType.JOBLET_DOC);
                break;
            case PropertiesPackage.ROUTINE_ITEM:
                itemResource = create(project2, (FileItem) item, path, ERepositoryObjectType.ROUTINES);
                break;
            case PropertiesPackage.SQL_PATTERN_ITEM:
                itemResource = create(project2, (FileItem) item, path, ERepositoryObjectType.SQLPATTERNS);
                break;
            case PropertiesPackage.PROCESS_ITEM:
                itemResource = create(project2, (ProcessItem) item, path);
                break;
            case PropertiesPackage.JOBLET_PROCESS_ITEM:
                itemResource = create(project2, (JobletProcessItem) item, path, ERepositoryObjectType.JOBLET);
                break;
            case PropertiesPackage.CONTEXT_ITEM:
                itemResource = create(project2, (ContextItem) item, path);
                break;
            case PropertiesPackage.SNIPPET_ITEM:
                itemResource = create(project2, (SnippetItem) item, path);
                break;
            case PropertiesPackage.LINK_DOCUMENTATION_ITEM:
                itemResource = create(project2, (LinkDocumentationItem) item, path);
                break;
            case PropertiesPackage.RULES_ITEM: // hywang add for 6484
                itemResource = create(project2, (FileItem) item, path, ERepositoryObjectType.METADATA_FILE_RULES);
                break;
            case PropertiesPackage.LINK_RULES_ITEM: // hywang add for 6484
                itemResource = create(project2, (LinkRulesItem) item, path);
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

        String parentPath = ERepositoryObjectType.getFolderName(ERepositoryObjectType.getItemType(item)) + IPath.SEPARATOR
                + path.toString();
        FolderHelper folderHelper = getFolderHelper(project.getEmfProject());
        FolderItem parentFolderItem = folderHelper.getFolder(parentPath);
        parentFolderItem.getChildren().add(item);
        item.setParent(parentFolderItem);

        xmiResourceManager.saveResource(itemResource);
        xmiResourceManager.saveResource(propertyResource);

        if (isImportItem.length == 0 || !isImportItem[0]) {
            saveProject(project);
        }
    }

    private IProject getPhysicalProject(Project project) throws PersistenceException {
        return ResourceModelUtils.getProject(project);
    }

    public Property reload(Property property) {
        IFile file = URIHelper.getFile(property.eResource().getURI());
        List<Resource> affectedResources = xmiResourceManager.getAffectedResources(property);
        for (Resource resource : affectedResources) {
            resource.unload();
        }

        return xmiResourceManager.loadProperty(file);
    }

    public Property reload(Property property, IFile file) {
        // IFile file = URIHelper.getFile(property.eResource().getURI());
        List<Resource> affectedResources = xmiResourceManager.getAffectedResources(property);
        for (Resource resource : affectedResources) {
            resource.unload();
        }

        return xmiResourceManager.loadProperty(file);
    }

    public boolean doesLoggedUserExist() throws PersistenceException {
        boolean exist = false;
        org.talend.core.model.properties.Project emfProject = getRepositoryContext().getProject().getEmfProject();
        // IProject iProject = ResourceModelUtils.getProject(getRepositoryContext().getProject());
        // org.talend.core.model.properties.Project emfProject = xmiResourceManager.loadProject(iProject);
        Resource projectResource = emfProject.eResource();

        Collection users = EcoreUtil.getObjectsByType(projectResource.getContents(), PropertiesPackage.eINSTANCE.getUser());
        for (Iterator iter = users.iterator(); iter.hasNext();) {
            User emfUser = (User) iter.next();

            if (emfUser.getLogin().equals(getRepositoryContext().getUser().getLogin())) {
                getRepositoryContext().setUser(emfUser);
                exist = true;
            }
        }

        return exist;
    }

    public void createUser(Project project) throws PersistenceException {
        Resource projectResource = project.getEmfProject().eResource();
        projectResource.getContents().add(getRepositoryContext().getUser());
        xmiResourceManager.saveResource(projectResource);
    }

    public void initialize() throws PersistenceException {
        unloadUnlockedResources();
    }

    public void unloadUnlockedResources() {
        List<Resource> resourceToUnload = new ArrayList<Resource>();
        List<URI> possibleItemsURItoUnload = new ArrayList<URI>();
        EList<Resource> kaka = xmiResourceManager.resourceSet.getResources();
        for (int i = 0; i < kaka.size(); i++) {
            Resource resource = xmiResourceManager.resourceSet.getResources().get(i);
            for (EObject object : resource.getContents()) {
                if (object instanceof Property) {
                    if (((Property) object).getItem() instanceof FolderItem) {
                        continue;
                    }
                    if (((Property) object).getItem() instanceof RoutineItem) {
                        RoutineItem item = (RoutineItem) ((Property) object).getItem();
                        if (item.isBuiltIn()) {
                            continue;
                        }
                    }
                    if (((Property) object).getItem() instanceof SQLPatternItem) {
                        SQLPatternItem item = (SQLPatternItem) ((Property) object).getItem();
                        if (item.isSystem()) {
                            continue;
                        }
                    }
                    ERepositoryStatus status = getStatus(((Property) object).getItem());
                    if ((status == ERepositoryStatus.LOCK_BY_USER) || (status == ERepositoryStatus.NOT_UP_TO_DATE)) {
                        // System.out.println("locked (don't unload):" + ((Property) object).getLabel());
                        continue;
                    }
                    resourceToUnload.add(resource);
                    possibleItemsURItoUnload.add(xmiResourceManager.getItemResourceURI(resource.getURI()));
                }
                // else if (possibleItemsURItoUnload.contains(resource.getURI())) {
                // resourceToUnload.add(resource);
                // }
            }
        }
        for (Resource resource : xmiResourceManager.resourceSet.getResources()) {
            for (EObject object : resource.getContents()) {
                if (!(object instanceof Property)) {
                    if (possibleItemsURItoUnload.contains(resource.getURI()) && !resourceToUnload.contains(resource)) {
                        resourceToUnload.add(resource);
                    }
                }
            }
        }

        for (Resource resource : resourceToUnload) {
            if (resource.isLoaded()) {
                resource.unload();
                // xmiResourceManager.resourceSet.getResources().remove(resource);
            }
        }
    }

    public void logOnProject(Project project) throws PersistenceException, LoginException {
        this.loadedFolders.clear();
        if (getRepositoryContext().getUser().getLogin() == null) {
            throw new LoginException(Messages.getString("LocalRepositoryFactory.UserLoginCannotBeNull")); //$NON-NLS-1$
        }

        super.logOnProject(project);

        if (!doesLoggedUserExist()) {
            createUser(project);
        }

        IProject project2 = ResourceModelUtils.getProject(project);
        createFolders(project2, project.getEmfProject());
        synchronizeRoutines(project2);
        synchronizeSqlpatterns(project2);
        synchronizeFolders(project2, project.getEmfProject());
        changeRoutinesPackage(project);
    }

    private void changeRoutinesPackage(Project project) {
        if (project == null) {
            return;
        }
        try {
            List<IRepositoryViewObject> allRoutines = getAll(project, ERepositoryObjectType.ROUTINES, true, true);
            for (IRepositoryViewObject object : allRoutines) {
                Item item = object.getProperty().getItem();
                RoutineUtils.changeRoutinesPackage(item);
            }
        } catch (PersistenceException e) {
            ExceptionHandler.process(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.model.IRepositoryFactory#getStatus(org.talend.core .model.properties.Item)
     */
    public ERepositoryStatus getStatus(Item item) {
        if (item != null) {
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
            if (item instanceof SQLPatternItem) {
                if (((SQLPatternItem) item).isSystem()) {
                    return ERepositoryStatus.READ_ONLY;
                }
            }
        }
        return ERepositoryStatus.DEFAULT;
    }

    @Override
    protected Object getFolder(Project project, ERepositoryObjectType repositoryObjectType) throws PersistenceException {
        IProject fsProject = ResourceModelUtils.getProject(project);
        try {
            if (repositoryObjectType.hasFolder()) {
                String folderName = ERepositoryObjectType.getFolderName(repositoryObjectType);
                return ResourceUtils.getFolder(fsProject, folderName, true);
            }
        } catch (ResourceNotFoundException rex) {
            //
        }
        return null;
    }

    public List<org.talend.core.model.properties.Project> getReferencedProjects(Project project) {
        String parentBranch = getRepositoryContext().getFields().get(
                IProxyRepositoryFactory.BRANCH_SELECTION + "_" + project.getTechnicalLabel());

        List<org.talend.core.model.properties.Project> refProjectList = new ArrayList<org.talend.core.model.properties.Project>();
        for (ProjectReference refProject : (List<ProjectReference>) getRepositoryContext().getProject().getEmfProject()
                .getReferencedProjects()) {
            if (refProject.getBranch() == null || parentBranch.equals(refProject.getBranch())) {
                refProjectList.add(refProject.getReferencedProject());
            }
        }
        return refProjectList;
    }

    public Boolean hasChildren(Object parent) {
        return null;
    }

    public void updateItemsPath(ERepositoryObjectType type, IPath targetPath) throws PersistenceException {
        Project baseProject = getRepositoryContext().getProject();
        IProject project = ResourceModelUtils.getProject(baseProject);
        String folderPathString = ERepositoryObjectType.getFolderName(type) + IPath.SEPARATOR + targetPath.toString();
        IFolder folder = ResourceUtils.getFolder(project, folderPathString, false);

        List<IRepositoryViewObject> serializableFromFolder = getSerializableFromFolder(baseProject, folder, null, type, true,
                false, false);
        for (IRepositoryViewObject repositoryObject : serializableFromFolder) {
            ItemState state = repositoryObject.getProperty().getItem().getState();
            state.setPath(targetPath.toString());
            xmiResourceManager.saveResource(state.eResource());
        }

        for (IResource current : ResourceUtils.getMembers((IFolder) folder)) {
            if (current instanceof IFolder) {
                updateItemsPath(type, targetPath.append(current.getName()));
            }
        }
    }

    public boolean setAuthorByLogin(Item item, String login) throws PersistenceException {
        // IProject iProject = ResourceModelUtils.getProject(getRepositoryContext().getProject());
        // org.talend.core.model.properties.Project emfProject = xmiResourceManager.loadProject(iProject);
        Resource projectResource = getRepositoryContext().getProject().getEmfProject().eResource();

        Collection users = EcoreUtil.getObjectsByType(projectResource.getContents(), PropertiesPackage.eINSTANCE.getUser());
        for (Iterator iter = users.iterator(); iter.hasNext();) {
            User emfUser = (User) iter.next();
            if (emfUser.getLogin().equals(login)) {
                item.getProperty().setAuthor(emfUser);
                return true;
            }
        }
        return false;
    }

    // protected void saveProject() throws PersistenceException {
    // org.talend.core.model.properties.Project loadProject = xmiResourceManager.loadProject(getProject());
    // xmiResourceManager.saveResource(loadProject.eResource());
    // }

    public void unloadResources(Property property) {
        xmiResourceManager.unloadResources(property);
    }

    /**
     * MOD mzhao feature 9207
     */
    public void unloadResources(String uriString) {
        xmiResourceManager.unloadResource(uriString);
    }

    public void unloadResources() {
        xmiResourceManager.unloadResources();
    }

    @Override
    public Property getUptodateProperty(Project project, Property property) throws PersistenceException {
        Property uptodateProperty = super.getUptodateProperty(project, property);

        // FolderItem folderItem = null;
        // if (property.getItem().getParent() instanceof FolderItem) {
        // folderItem = (FolderItem) property.getItem().getParent();
        // folderItem.getChildren().remove(property.getItem());
        // }
        //
        // Property afterForceReload = xmiResourceManager.forceReloadProperty(uptodateProperty);
        // if (folderItem != null) {
        // folderItem.getChildren().add(afterForceReload.getItem());
        // afterForceReload.getItem().setParent(folderItem);
        // saveProject(project);
        // }

        return uptodateProperty;
    }

    public void reloadProject(Project project) throws PersistenceException {
        IProject pproject = getPhysicalProject(project);
        project.setEmfProject(xmiResourceManager.loadProject(pproject));
    }

    @Override
    public void executeRepositoryWorkUnit(RepositoryWorkUnit workUnit) {
        Project currentProject = ProjectManager.getInstance().getCurrentProject();
        if (currentProject != null && currentProject.isLocal() && !workUnit.isAvoidUnloadResources()) { // 14969 avoid
            // reload before
            // create
            unloadUnlockedResources();
        }
        super.executeRepositoryWorkUnit(workUnit);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.model.IRepositoryFactory#enableSandboxProject()
     */
    public boolean enableSandboxProject() {
        return false; // don't support in local model
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.model.IRepositoryFactory#isLocalConnectionProvider()
     */
    public boolean isLocalConnectionProvider() throws PersistenceException {
        return true;
    }

}
