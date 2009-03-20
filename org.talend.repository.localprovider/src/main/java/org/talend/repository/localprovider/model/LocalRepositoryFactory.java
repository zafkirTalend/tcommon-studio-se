// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
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
import org.talend.commons.utils.workbench.resources.ResourceUtils;
import org.talend.core.PluginChecker;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.model.general.Project;
import org.talend.core.model.general.TalendNature;
import org.talend.core.model.metadata.builder.connection.ConnectionPackage;
import org.talend.core.model.properties.BusinessProcessItem;
import org.talend.core.model.properties.ByteArray;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.core.model.properties.ContextItem;
import org.talend.core.model.properties.EbcdicConnectionItem;
import org.talend.core.model.properties.FileItem;
import org.talend.core.model.properties.FolderItem;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.ItemState;
import org.talend.core.model.properties.JobDocumentationItem;
import org.talend.core.model.properties.JobletDocumentationItem;
import org.talend.core.model.properties.JobletProcessItem;
import org.talend.core.model.properties.LinkDocumentationItem;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.properties.ProjectReference;
import org.talend.core.model.properties.PropertiesFactory;
import org.talend.core.model.properties.PropertiesPackage;
import org.talend.core.model.properties.Property;
import org.talend.core.model.properties.RoutineItem;
import org.talend.core.model.properties.SQLPatternItem;
import org.talend.core.model.properties.SnippetItem;
import org.talend.core.model.properties.SpagoBiServer;
import org.talend.core.model.properties.Status;
import org.talend.core.model.properties.User;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.Folder;
import org.talend.core.model.repository.IRepositoryObject;
import org.talend.core.model.repository.RepositoryObject;
import org.talend.core.ui.images.ECoreImage;
import org.talend.repository.ProjectManager;
import org.talend.repository.localprovider.exceptions.IncorrectFileException;
import org.talend.repository.localprovider.i18n.Messages;
import org.talend.repository.localprovider.imports.ImportItemUtil;
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
 * $Id$ $Id: RepositoryFactory.java,v 1.55 2006/08/23
 * 14:30:39 tguiu Exp $
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
    protected <K, T> RootContainer<K, T> getObjectFromFolder(Project project, ERepositoryObjectType type, boolean onlyLastVersion)
            throws PersistenceException {
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
        addFolderMembers(project, type, toReturn, objectFolder, onlyLastVersion);

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
            Object objectFolder, boolean onlyLastVersion) throws PersistenceException {
        FolderHelper folderHelper = getFolderHelper(project.getEmfProject());
        FolderItem currentFolderItem = folderHelper.getFolder(((IFolder) objectFolder).getProjectRelativePath());
        // FolderItem folder =
        // folderHelper.getFolder(current.getProjectRelativePath());

        for (IResource current : ResourceUtils.getMembers((IFolder) objectFolder)) {
            if (current instanceof IFile) {
                try {
                    IRepositoryObject currentObject = null;

                    if (xmiResourceManager.isPropertyFile((IFile) current)) {
                        Property property = null;
                        try {
                            property = xmiResourceManager.loadProperty(current);
                        } catch (RuntimeException e) {
                            // property will be null
                            ExceptionHandler.process(e);
                        }
                        if (property != null) {
                            currentObject = new RepositoryObject(property);
                            if (!currentFolderItem.getChildren().contains(property.getItem())
                                    && !getRepositoryContext().getProject().equals(project)) {
                                currentFolderItem.getChildren().add(property.getItem());
                            }
                        } else {
                            log.error(Messages.getString("LocalRepositoryFactory.CannotLoadProperty") + current); //$NON-NLS-1$
                        }
                    }

                    if (currentObject != null) {
                        K key;
                        String currentVersion = null;
                        try {
                            currentVersion = currentObject.getVersion();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (onlyLastVersion) {
                            key = (K) currentObject.getId();
                        } else {
                            key = (K) new MultiKey(currentObject.getId(), currentVersion);
                        }

                        try {
                            if (onlyLastVersion) {
                                // Version :
                                T old = toReturn.getMember(key);

                                if (old == null) {
                                    toReturn.addMember(key, (T) currentObject);
                                } else {
                                    String oldVersion = ((IRepositoryObject) old).getVersion();
                                    if (VersionUtils.compareTo(oldVersion, currentVersion) < 0) {
                                        toReturn.addMember(key, (T) currentObject);
                                    }
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
                    FolderItem folder = folderHelper.getFolder(current.getProjectRelativePath());

                    Property property = null;
                    if (folder == null) {
                        FolderItem newFolder = folderHelper.createFolder(current.getProjectRelativePath().toString());
                        property = newFolder.getProperty();
                    } else {
                        property = folder.getProperty();
                    }

                    cont.setProperty(property);
                    cont.setId(property.getId());
                    addFolderMembers(project, type, cont, (IFolder) current, onlyLastVersion);
                } else {
                    addFolderMembers(project, type, toReturn, (IFolder) current, onlyLastVersion);
                }
            }
        }
    }

    public List<IRepositoryObject> getAll(Project project, ERepositoryObjectType type, boolean withDeleted, boolean allVersions)
            throws PersistenceException {
        IFolder folder = LocalResourceModelUtils.getFolder(project, type);
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
    protected List<IRepositoryObject> getSerializableFromFolder(Project project, Object folder, String id,
            ERepositoryObjectType type, boolean allVersion, boolean searchInChildren, boolean withDeleted)
            throws PersistenceException {
        List<IRepositoryObject> toReturn = new VersionList(allVersion);
        FolderHelper folderHelper = getFolderHelper(project.getEmfProject());
        FolderItem currentFolderItem = folderHelper.getFolder(((IFolder) folder).getProjectRelativePath());

        for (IResource current : ResourceUtils.getMembers((IFolder) folder)) {
            if (current instanceof IFile) {
                if (xmiResourceManager.isPropertyFile((IFile) current)) {
                    Property property = null;
                    try {
                        property = xmiResourceManager.loadProperty(current);
                    } catch (RuntimeException e) {
                        // property will be null
                        ExceptionHandler.process(e);
                    }
                    if (property != null) {
                        if (id == null || property.getId().equals(id)) {
                            if (withDeleted || !property.getItem().getState().isDeleted()) {
                                toReturn.add(new RepositoryObject(property));
                            }
                        }
                        if (!currentFolderItem.getChildren().contains(property.getItem())
                                && !getRepositoryContext().getProject().equals(project)) {
                            currentFolderItem.getChildren().add(property.getItem());
                        }
                    } else {
                        log.error(Messages.getString("LocalRepositoryFactory.CannotLoadProperty") + current); //$NON-NLS-1$
                    }
                }
            } else if (current instanceof IFolder) { // &&
                // (!current.getName().equals
                // ("bin"))) {
                if (searchInChildren) {
                    toReturn
                            .addAll(getSerializableFromFolder(project, (IFolder) current, id, type, allVersion, true, withDeleted));
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
        // /PTODO tgu quick fix for registering the emf package. needed to make
        // the extention point work
        ConnectionPackage.eINSTANCE.getClass();

        needsBinFolder.add(ERepositoryObjectType.BUSINESS_PROCESS);
        needsBinFolder.add(ERepositoryObjectType.DOCUMENTATION);
        needsBinFolder.add(ERepositoryObjectType.METADATA_CONNECTIONS);
        needsBinFolder.add(ERepositoryObjectType.METADATA_SAPCONNECTIONS);
        needsBinFolder.add(ERepositoryObjectType.SQLPATTERNS);
        needsBinFolder.add(ERepositoryObjectType.METADATA_FILE_DELIMITED);
        needsBinFolder.add(ERepositoryObjectType.METADATA_FILE_POSITIONAL);
        needsBinFolder.add(ERepositoryObjectType.METADATA_FILE_REGEXP);
        needsBinFolder.add(ERepositoryObjectType.METADATA_FILE_XML);
        needsBinFolder.add(ERepositoryObjectType.METADATA_FILE_EXCEL);
        needsBinFolder.add(ERepositoryObjectType.METADATA_FILE_LDIF);
        needsBinFolder.add(ERepositoryObjectType.METADATA_LDAP_SCHEMA);
        needsBinFolder.add(ERepositoryObjectType.METADATA_GENERIC_SCHEMA);
        needsBinFolder.add(ERepositoryObjectType.METADATA_WSDL_SCHEMA);
        needsBinFolder.add(ERepositoryObjectType.METADATA_SALESFORCE_SCHEMA);
        needsBinFolder.add(ERepositoryObjectType.PROCESS);
        needsBinFolder.add(ERepositoryObjectType.ROUTINES);
        needsBinFolder.add(ERepositoryObjectType.SNIPPETS);
        needsBinFolder.add(ERepositoryObjectType.JOBLET);
        needsBinFolder.add(ERepositoryObjectType.CONTEXT);

        if (PluginChecker.isDocumentationPluginLoaded()) {
            needsBinFolder.add(ERepositoryObjectType.JOBS);
            if (PluginChecker.isJobLetPluginLoaded()) {
                needsBinFolder.add(ERepositoryObjectType.JOBLETS);
            }
        }
        if (PluginChecker.isEBCDICPluginLoaded()) {
            needsBinFolder.add(ERepositoryObjectType.METADATA_FILE_EBCDIC);
        }

    }

    public Project createProject(String label, String description, ECodeLanguage language, User author)
            throws PersistenceException {
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();

        String technicalLabel = Project.createTechnicalName(label);
        IProject prj = root.getProject(technicalLabel);

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

        Project project = new Project();
        // Fill project object
        project.setLabel(label);
        project.setDescription(description);
        project.setLanguage(language);
        project.setAuthor(author);
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
        IFolder f1 = ResourceUtils.getFolder(prj, ERepositoryObjectType.getFolderName(ERepositoryObjectType.ROUTINES)
                + IPath.SEPARATOR + RepositoryConstants.SYSTEM_DIRECTORY, false);
        ResourceUtils.deleteResource(f1);

        createSystemRoutines();
    }

    public void synchronizeSqlpatterns(IProject prj) throws PersistenceException {
        if (prj == null) {
            Project project = getRepositoryContext().getProject();
            prj = ResourceModelUtils.getProject(project);
        }

        // Purge old sqlpatterns :
        // 1. old built-in:
        IFolder sqlpatternRoot = ResourceUtils.getFolder(prj, ERepositoryObjectType
                .getFolderName(ERepositoryObjectType.SQLPATTERNS), false);
        clearSystemSqlPatterns(sqlpatternRoot);
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
        createFolder(prj, folderHelper, RepositoryConstants.IMG_DIRECTORY_OF_JOBLET_OUTLINE);

        // 3. Bin folders :
        for (ERepositoryObjectType type : needsBinFolder) {
            String folderName = ERepositoryObjectType.getFolderName(type) + IPath.SEPARATOR + BIN;
            createFolder(prj, folderHelper, folderName);
        }

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

        String parentPath = ERepositoryObjectType.getFolderName(type) + IPath.SEPARATOR + path.toString();

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

        if (label.equalsIgnoreCase(BIN)) {
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
        String completeNewPath = ERepositoryObjectType.getFolderName(type) + IPath.SEPARATOR + targetPath.toString()
                + IPath.SEPARATOR + sourcePath.lastSegment();

        // Getting the folder :
        IFolder folder = ResourceUtils.getFolder(fsProject, completeOldPath, false);

        FolderHelper folderHelper = getFolderHelper(getRepositoryContext().getProject().getEmfProject());
        FolderItem emfFolder = folderHelper.getFolder(completeOldPath);

        createFolder(getRepositoryContext().getProject(), type, targetPath, emfFolder.getProperty().getLabel());

        Item[] childrens = (Item[]) emfFolder.getChildren().toArray();
        for (int i = 0; i < childrens.length; i++) {
            FolderItem children = (FolderItem) childrens[i];
            moveFolder(type, sourcePath.append(children.getProperty().getLabel()), targetPath.append(emfFolder.getProperty()
                    .getLabel()));
        }

        List<IRepositoryObject> serializableFromFolder = getSerializableFromFolder(project, folder, null, type, true, true, true);
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
    public List<IRepositoryObject> getProcess2() throws PersistenceException {
        List<IRepositoryObject> toReturn = new VersionList(false);

        FolderHelper folderHelper = getFolderHelper(getRepositoryContext().getProject().getEmfProject());
        IFolder folder = LocalResourceModelUtils.getFolder(getRepositoryContext().getProject(), ERepositoryObjectType.PROCESS);

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
     * @see org.talend.repository.model.IRepositoryFactory#deleteObject(org.talend .core.model.general.Project,
     * org.talend.core.model.repository.IRepositoryObject)
     */

    public void deleteObjectLogical(Project project, IRepositoryObject objToDelete) throws PersistenceException {

        // can only delete in the main project
        IProject fsProject = ResourceModelUtils.getProject(project);

        IFolder bin = ResourceUtils.getFolder(fsProject, ERepositoryObjectType.getFolderName(objToDelete.getType())
                + IPath.SEPARATOR + BIN, true);

        List<IRepositoryObject> allVersionToDelete = getAllVersion(project, objToDelete.getId());
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

    public void deleteObjectPhysical(Project project, IRepositoryObject objToDelete) throws PersistenceException {

        // can only delete in the main project
        List<IRepositoryObject> allVersionToDelete = getAllVersion(project, objToDelete.getId());
        for (IRepositoryObject currentVersion : allVersionToDelete) {
            List<Resource> affectedResources = xmiResourceManager.getAffectedResources(currentVersion.getProperty());
            for (Resource resource : affectedResources) {
                xmiResourceManager.deleteResource(resource);
            }
        }
    }

    public void restoreObject(IRepositoryObject objToRestore, IPath path) throws PersistenceException {
        IProject fsProject = ResourceModelUtils.getProject(getRepositoryContext().getProject());
        IFolder typeRootFolder = ResourceUtils.getFolder(fsProject, ERepositoryObjectType.getFolderName(objToRestore.getType()),
                true);
        // IPath thePath = (path == null ? typeRootFolder.getFullPath() :
        // typeRootFolder.getFullPath().append(path));
        org.talend.core.model.properties.Project project = xmiResourceManager.loadProject(getProject());

        List<IRepositoryObject> allVersionToDelete = getAllVersion(getRepositoryContext().getProject(), objToRestore.getId());
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
     * @see org.talend.repository.model.IRepositoryFactory#moveObject(org.talend. core.model.general.Project,
     * org.talend.core.model.repository.IRepositoryObject, org.eclipse.core.runtime.IPath)
     */
    public void moveObject(IRepositoryObject objToMove, IPath newPath) throws PersistenceException {
        IProject fsProject = ResourceModelUtils.getProject(getRepositoryContext().getProject());
        String folderName = ERepositoryObjectType.getFolderName(objToMove.getType()) + IPath.SEPARATOR + newPath;
        IFolder folder = ResourceUtils.getFolder(fsProject, folderName, true);

        List<IRepositoryObject> allVersionToMove = getAllVersion(getRepositoryContext().getProject(), objToMove.getId());
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

    protected XmiResourceManager xmiResourceManager = new XmiResourceManager();

    public void lock(Item item) throws PersistenceException {
        if (getStatus(item) == ERepositoryStatus.DEFAULT) {
            // lockedObject.put(item.getProperty().getId(), new LockedObject(new
            // Date(), repositoryContext.getUser()));
            item.getState().setLockDate(new Date());
            item.getState().setLocker(getRepositoryContext().getUser());
            item.getState().setLocked(true);
            xmiResourceManager.saveResource(item.eResource());
        }
    }

    public void unlock(Item item) throws PersistenceException {
        if (getStatus(item) == ERepositoryStatus.LOCK_BY_USER || item instanceof JobletDocumentationItem
                || item instanceof JobDocumentationItem) {
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

    public List<SpagoBiServer> getSpagoBiServer() throws PersistenceException {
        org.talend.core.model.properties.Project loadProject = xmiResourceManager.loadProject(getProject());
        return copyListOfSpagoBiServer(loadProject.getSpagoBiServer());
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

    public void setSpagoBiServer(List<SpagoBiServer> list) throws PersistenceException {
        org.talend.core.model.properties.Project loadProject = xmiResourceManager.loadProject(getProject());
        loadProject.getSpagoBiServer().clear();
        loadProject.getSpagoBiServer().addAll(list);
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

        itemResource.getContents().add(item.getConnection());

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
        itemResource.getContents().add(item.getConnection());

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
        itemResource.getContents().add(item.getIcon());
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
            case PropertiesPackage.BUSINESS_PROCESS_ITEM:
                itemResource = save((BusinessProcessItem) item);
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

        property.setModificationDate(new Date());
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
            e.printStackTrace();
        }

        return null;
    }

    private void propagateFileName(Project project, Property property) throws PersistenceException {
        List<IRepositoryObject> allVersionToMove = getAllVersion(project, property.getId());
        for (IRepositoryObject object : allVersionToMove) {
            xmiResourceManager.propagateFileName(property, object.getProperty());
        }
    }

    public void create(Project project, Item item, IPath path) throws PersistenceException {
        computePropertyMaxInformationLevel(item.getProperty());

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
        IProject project2 = ResourceModelUtils.getProject(project);
        Resource itemResource;
        EClass eClass = item.eClass();
        if (eClass.eContainer() == PropertiesPackage.eINSTANCE) {
            switch (eClass.getClassifierID()) {
            case PropertiesPackage.BUSINESS_PROCESS_ITEM:
                itemResource = create(project2, (BusinessProcessItem) item, path);
                break;
            case PropertiesPackage.DATABASE_CONNECTION_ITEM:
                itemResource = create(project2, (ConnectionItem) item, ERepositoryObjectType.METADATA_CONNECTIONS, path);
                break;
            case PropertiesPackage.SAP_CONNECTION_ITEM:
                itemResource = create(project2, (ConnectionItem) item, ERepositoryObjectType.METADATA_SAPCONNECTIONS, path);
                break;
            case PropertiesPackage.EBCDIC_CONNECTION_ITEM:
                itemResource = create(project2, (EbcdicConnectionItem) item, ERepositoryObjectType.METADATA_FILE_EBCDIC, path);
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

        // String parentPath =
        // ERepositoryObjectType.getFolderName(ERepositoryObjectType
        // .getItemType(item)) +
        // IPath.SEPARATOR
        // + path.toString();
        // FolderHelper folderHelper = getFolderHelper(project.getEmfProject());
        // FolderItem parentFolderItem = folderHelper.getFolder(parentPath);
        // parentFolderItem.getChildren().add(item);

        xmiResourceManager.saveResource(itemResource);
        xmiResourceManager.saveResource(propertyResource);

        // saveProject();
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
        // xmiResourceManager.unloadResources();
    }

    public void logOnProject(Project project) throws PersistenceException, LoginException {

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
            ImportItemUtil util = new ImportItemUtil();
            List<IRepositoryObject> allRoutines = getAll(project, ERepositoryObjectType.ROUTINES, true, true);
            for (IRepositoryObject object : allRoutines) {
                Item item = object.getProperty().getItem();
                util.changeRoutinesPackage(item);
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
            return ResourceUtils.getFolder(fsProject, ERepositoryObjectType.getFolderName(repositoryObjectType), true);
        } catch (ResourceNotFoundException rex) {
            //
        }
        return null;
    }

    public List<org.talend.core.model.properties.Project> getReferencedProjects() {
        List<org.talend.core.model.properties.Project> refProjectList = new ArrayList<org.talend.core.model.properties.Project>();
        for (ProjectReference refProject : (List<ProjectReference>) getRepositoryContext().getProject().getEmfProject()
                .getReferencedProjects()) {
            refProjectList.add(refProject.getReferencedProject());
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

        List<IRepositoryObject> serializableFromFolder = getSerializableFromFolder(baseProject, folder, null, type, true, false,
                false);
        for (IRepositoryObject repositoryObject : serializableFromFolder) {
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
        IProject iProject = ResourceModelUtils.getProject(getRepositoryContext().getProject());
        org.talend.core.model.properties.Project emfProject = xmiResourceManager.loadProject(iProject);
        Resource projectResource = emfProject.eResource();

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

    protected void saveProject() throws PersistenceException {
        org.talend.core.model.properties.Project loadProject = xmiResourceManager.loadProject(getProject());
        xmiResourceManager.saveResource(loadProject.eResource());
    }
}
