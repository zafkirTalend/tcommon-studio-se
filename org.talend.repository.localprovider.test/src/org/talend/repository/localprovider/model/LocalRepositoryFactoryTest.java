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
package org.talend.repository.localprovider.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.talend.commons.exception.LoginException;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.utils.VersionUtils;
import org.talend.commons.utils.data.container.RootContainer;
import org.talend.commons.utils.workbench.resources.ResourceUtils;
import org.talend.core.context.Context;
import org.talend.core.context.RepositoryContext;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.model.general.Project;
import org.talend.core.model.general.TalendNature;
import org.talend.core.model.properties.FolderItem;
import org.talend.core.model.properties.ItemState;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.properties.PropertiesFactory;
import org.talend.core.model.properties.Property;
import org.talend.core.model.properties.RoutineItem;
import org.talend.core.model.properties.User;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.Folder;
import org.talend.core.model.repository.IRepositoryViewObject;
import org.talend.core.model.repository.RepositoryObject;
import org.talend.core.repository.model.FolderHelper;
import org.talend.core.repository.model.ProxyRepositoryFactory;
import org.talend.core.repository.model.ResourceModelUtils;
import org.talend.core.repository.utils.XmiResourceManager;
import org.talend.core.runtime.CoreRuntimePlugin;
import org.talend.designer.core.model.utils.emf.talendfile.NodeType;
import org.talend.designer.core.model.utils.emf.talendfile.ProcessType;
import org.talend.designer.core.model.utils.emf.talendfile.TalendFileFactory;
import org.talend.repository.ProjectManager;

/**
 * DOC nrousseau class global comment. Detailled comment
 */
public class LocalRepositoryFactoryTest {

    private static LocalRepositoryFactory repositoryFactory;

    @BeforeClass
    public static void beforeAllTests() {
        repositoryFactory = new LocalRepositoryFactory();
    }

    @AfterClass
    public static void afterAllTests() {
        repositoryFactory = null;
    }

    Project originalProject;

    @Before
    public void beforeTest() throws PersistenceException, CoreException, LoginException {
        createTempProject();
        Context ctx = CoreRuntimePlugin.getInstance().getContext();
        RepositoryContext repositoryContext = (RepositoryContext) ctx.getProperty(Context.REPOSITORY_CONTEXT_KEY);
        originalProject = repositoryContext.getProject();
        repositoryContext.setProject(sampleProject);
    }

    @After
    public void afterTest() throws Exception {
        // make sure there is nothing in both jobs / routines after do anything
        final List<IRepositoryViewObject> allJobs = repositoryFactory.getAll(ProjectManager.getInstance().getCurrentProject(),
                ERepositoryObjectType.PROCESS, true, true);
        assertNotNull(allJobs);
        Set<String> jobs = new HashSet<String>();
        for (IRepositoryViewObject object : allJobs) {
            jobs.add(object.getLabel());
        }
        if (!jobs.isEmpty()) {
            throw new Exception("job:" + jobs + " should have been deleted before");
        }
        assertTrue(allJobs.isEmpty());

        final List<IRepositoryViewObject> allRoutines = repositoryFactory.getAll(
                ProjectManager.getInstance().getCurrentProject(), ERepositoryObjectType.ROUTINES, true, true);
        assertNotNull(allRoutines);
        for (IRepositoryViewObject o : allRoutines) {
            RoutineItem ri = (RoutineItem) o.getProperty().getItem();
            if (!ri.isBuiltIn()) {
                System.out.println("routine:" + o.getLabel() + " should have been deleted before");
            }
            assertTrue(ri.isBuiltIn()); // should only have system routines, not others
        }

        removeTempProject();
        ProjectManager.getInstance().getFolders(sampleProject.getEmfProject()).clear();
        Context ctx = CoreRuntimePlugin.getInstance().getContext();
        RepositoryContext repositoryContext = (RepositoryContext) ctx.getProperty(Context.REPOSITORY_CONTEXT_KEY);
        repositoryContext.setProject(originalProject);
        originalProject = null;
        sampleProject = null;
    }

    Project sampleProject;

    private void createTempProject() throws CoreException, PersistenceException, LoginException {
        Project projectInfor = new Project();
        projectInfor.setLabel("testauto");
        projectInfor.setDescription("no desc");
        projectInfor.setLanguage(ECodeLanguage.JAVA);
        User user = PropertiesFactory.eINSTANCE.createUser();
        user.setLogin("testauto@talend.com");
        projectInfor.setAuthor(user);
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();

        String technicalLabel = Project.createTechnicalName(projectInfor.getLabel());
        IProject prj = root.getProject(technicalLabel);

        final IWorkspace workspace = ResourcesPlugin.getWorkspace();

        try {
            IProjectDescription desc = null;
            if (prj.exists()) {
                prj.delete(true, null); // always delete to avoid conflicts between 2 tests
            }
            desc = workspace.newProjectDescription(projectInfor.getLabel());
            desc.setNatureIds(new String[] { TalendNature.ID });
            desc.setComment(projectInfor.getDescription());

            prj.create(desc, null);
            prj.open(IResource.DEPTH_INFINITE, null);
            prj.setDefaultCharset("UTF-8", null);
        } catch (CoreException e) {
            throw new PersistenceException(e);
        }

        sampleProject = new Project();
        // Fill project object
        sampleProject.setLabel(projectInfor.getLabel());
        sampleProject.setDescription(projectInfor.getDescription());
        sampleProject.setLanguage(projectInfor.getLanguage());
        sampleProject.setAuthor(projectInfor.getAuthor());
        sampleProject.setLocal(true);
        sampleProject.setTechnicalLabel(technicalLabel);
        XmiResourceManager xmiResourceManager = new XmiResourceManager();
        Resource projectResource = xmiResourceManager.createProjectResource(prj);
        projectResource.getContents().add(sampleProject.getEmfProject());
        projectResource.getContents().add(sampleProject.getAuthor());
        xmiResourceManager.saveResource(projectResource);
    }

    private void removeTempProject() throws PersistenceException, CoreException {
        repositoryFactory.logOffProject();

        // clear the folder, same as it should be in a real logoffProject.
        ProjectManager.getInstance().getFolders(sampleProject.getEmfProject()).clear();
        final IProject project = ResourceModelUtils.getProject(sampleProject);
        project.delete(true, null);
    }

    @SuppressWarnings("unchecked")
    private ProcessItem createTempProcessItem(LocalRepositoryFactory factory, String path) throws PersistenceException {
        ProcessItem processItem = PropertiesFactory.eINSTANCE.createProcessItem();
        Property myProperty = PropertiesFactory.eINSTANCE.createProperty();
        ItemState itemState = PropertiesFactory.eINSTANCE.createItemState();
        itemState.setPath(path);
        processItem.setProperty(myProperty);
        processItem.setState(itemState);
        myProperty.setId(factory.getNextId());
        myProperty.setLabel("myJob");
        myProperty.setVersion("0.1");

        processItem.setProcess(TalendFileFactory.eINSTANCE.createProcessType());
        processItem.getProcess().getNode().add(TalendFileFactory.eINSTANCE.createNodeType());
        ((NodeType) processItem.getProcess().getNode().get(0)).setComponentName("test");
        ((NodeType) processItem.getProcess().getNode().get(0)).setComponentVersion("0.1");

        if (!"".equals(path)) {
            factory.createFolder(ProjectManager.getInstance().getCurrentProject(), ERepositoryObjectType.PROCESS, new Path(""),
                    path);
        }

        factory.create(ProjectManager.getInstance().getCurrentProject(), processItem, new Path(path), false);

        return processItem;
    }

    private RoutineItem createTempRoutineItem(LocalRepositoryFactory factory, String path) throws PersistenceException {
        RoutineItem routineItem = PropertiesFactory.eINSTANCE.createRoutineItem();
        Property myProperty = PropertiesFactory.eINSTANCE.createProperty();
        ItemState itemState = PropertiesFactory.eINSTANCE.createItemState();
        itemState.setPath(path);
        routineItem.setState(itemState);
        routineItem.setProperty(myProperty);
        myProperty.setId(factory.getNextId());
        myProperty.setLabel("myRoutine");
        myProperty.setVersion("0.1");
        routineItem.setContent(PropertiesFactory.eINSTANCE.createByteArray());
        routineItem.getContent().setInnerContent("myRoutineContent".getBytes());

        if (!"".equals(path)) {
            factory.createFolder(ProjectManager.getInstance().getCurrentProject(), ERepositoryObjectType.ROUTINES, new Path(""),
                    path);
        }
        factory.create(ProjectManager.getInstance().getCurrentProject(), routineItem, new Path(path), false);
        return routineItem;
    }

    /**
     * Test method for
     * {@link org.talend.repository.localprovider.model.LocalRepositoryFactory#createProject(org.talend.core.model.general.Project)}
     * .
     * 
     * @throws PersistenceException
     * @throws CoreException
     */
    @Test
    public void testCreateProject() throws PersistenceException, CoreException {
        Project project = new Project();
        project.setLabel("testauto2");
        project.setDescription("no desc");
        project.setLanguage(ECodeLanguage.JAVA);
        User user = PropertiesFactory.eINSTANCE.createUser();
        user.setLogin("testauto2@talend.com");
        project.setAuthor(user);
        Project createProject = repositoryFactory.createProject(project);
        assertTrue(createProject != null);
        final IProject project2 = ResourceModelUtils.getProject(createProject);
        project2.delete(true, null);
    }

    /**
     * Test method for {@link org.talend.repository.localprovider.model.LocalRepositoryFactory#readProject()}.
     * 
     * @throws CoreException
     * @throws PersistenceException
     */
    @Test
    public void testReadProject() throws PersistenceException, CoreException {
        final Project[] readProject = repositoryFactory.readProject();
        assertTrue(readProject != null && readProject.length > 0);
        boolean contains = false;
        for (Project project : readProject) {
            if (project.getLabel().equals("testauto")) {
                contains = true;
                break;
            }
        }
        assertTrue(contains);
    }

    /**
     * Test method for
     * {@link org.talend.repository.localprovider.model.LocalRepositoryFactory#logOnProject(org.talend.core.model.general.Project)}
     * .
     * 
     * @throws LoginException
     * @throws PersistenceException
     * @throws CoreException
     */
    @Test
    public void testLogOnProject() throws PersistenceException, LoginException, CoreException {
        // new project, so all folders should be empty
        assertTrue(ProjectManager.getInstance().getFolders(sampleProject.getEmfProject()).isEmpty());
        repositoryFactory.logOnProject(sampleProject);
        assertTrue(!ProjectManager.getInstance().getFolders(sampleProject.getEmfProject()).isEmpty());
    }

    /**
     * Test method for
     * {@link org.talend.repository.localprovider.model.LocalRepositoryFactory#getFolder(org.talend.core.model.general.Project, org.talend.core.model.repository.ERepositoryObjectType)}
     * .
     * 
     * @throws PersistenceException
     * @throws CoreException
     * @throws LoginException
     */
    @Test
    public void testGetFolder() throws PersistenceException, CoreException, LoginException {
        repositoryFactory.logOnProject(ProjectManager.getInstance().getCurrentProject());
        final Object folder = repositoryFactory.getFolder(ProjectManager.getInstance().getCurrentProject(),
                ERepositoryObjectType.PROCESS);
        assertNotNull(folder);
    }

    /**
     * Test method for
     * {@link org.talend.repository.localprovider.model.LocalRepositoryFactory#createUser(org.talend.core.model.general.Project)}
     * .
     * 
     * @throws CoreException
     * @throws PersistenceException
     */
    @Test
    public void testCreateUser() throws PersistenceException, CoreException {
        repositoryFactory.createUser(sampleProject);
        Resource projectResource = sampleProject.getEmfProject().eResource();
        assertTrue(projectResource.getContents().contains(repositoryFactory.getRepositoryContext().getUser()));
    }

    /**
     * Test method for
     * {@link org.talend.repository.localprovider.model.LocalRepositoryFactory#create(org.talend.core.model.general.Project, org.talend.core.model.properties.Item, org.eclipse.core.runtime.IPath, boolean[])}
     * .
     * 
     * @throws CoreException
     * @throws PersistenceException
     * @throws LoginException
     */
    @Test
    public void testCreate() throws PersistenceException, CoreException, LoginException {
        repositoryFactory.logOnProject(ProjectManager.getInstance().getCurrentProject());

        Property property = PropertiesFactory.eINSTANCE.createProperty();
        property.setAuthor(ProjectManager.getInstance().getCurrentProject().getAuthor());
        property.setVersion(VersionUtils.DEFAULT_VERSION);
        property.setLabel("myJob");
        property.setDisplayName("myJob");
        property.setStatusCode("");
        property.setId(repositoryFactory.getNextId());
        ProcessItem processItem = PropertiesFactory.eINSTANCE.createProcessItem();
        processItem.setProperty(property);
        ProcessType process = TalendFileFactory.eINSTANCE.createProcessType();
        processItem.setProcess(process);
        assertNull(processItem.eResource());
        repositoryFactory.create(ProjectManager.getInstance().getCurrentProject(), processItem, new Path(""), false);
        assertNotNull(processItem.eResource());

        IProject project = ResourceUtils.getProject(ProjectManager.getInstance().getCurrentProject().getTechnicalLabel());
        checkFileExists(project, ERepositoryObjectType.PROCESS, "", "myJob", VersionUtils.DEFAULT_VERSION);
        // delete the item to cleanup the workspace
        repositoryFactory.deleteObjectPhysical(ProjectManager.getInstance().getCurrentProject(), new RepositoryObject(property));
        checkFileNotExists(project, ERepositoryObjectType.PROCESS, "", "myJob", VersionUtils.DEFAULT_VERSION);
    }

    /**
     * Test method for
     * {@link org.talend.repository.localprovider.model.LocalRepositoryFactory#createFolder(org.talend.core.model.general.Project, org.talend.core.model.repository.ERepositoryObjectType, org.eclipse.core.runtime.IPath, java.lang.String)}
     * .
     * 
     * @throws CoreException
     * @throws PersistenceException
     * @throws LoginException
     */
    @Test
    public void testCreateFolder() throws PersistenceException, CoreException, LoginException {
        repositoryFactory.logOnProject(ProjectManager.getInstance().getCurrentProject());

        final Folder createFolder = repositoryFactory.createFolder(ProjectManager.getInstance().getCurrentProject(),
                ERepositoryObjectType.PROCESS, new Path(""), "MyFolder");
        // check if the folder created is not null
        assertNotNull(createFolder);
        String folderName = new Path(ERepositoryObjectType.getFolderName(ERepositoryObjectType.PROCESS)).append("MyFolder")
                .toPortableString();
        // check that the folder exists in the project emf
        assertNotNull(repositoryFactory.getFolderHelper(ProjectManager.getInstance().getCurrentProject().getEmfProject())
                .getFolder(folderName));

        // check that the folder exists physically
        IFolder pFolder = ResourceUtils
                .getFolder(ResourceUtils.getProject(ProjectManager.getInstance().getCurrentProject().getTechnicalLabel()),
                        folderName, false);
        assertTrue(pFolder.exists());
        pFolder.delete(true, new NullProgressMonitor());
    }

    /**
     * Test method for {@link org.talend.repository.localprovider.model.LocalRepositoryFactory#getResourceManager()}.
     */
    @Test
    public void testGetResourceManager() {
        final XmiResourceManager resourceManager = repositoryFactory.getResourceManager();
        assertNotNull(resourceManager);
    }

    /**
     * Test method for
     * {@link org.talend.repository.localprovider.model.LocalRepositoryFactory#getSerializableFromFolder(org.talend.core.model.general.Project, java.lang.Object, java.lang.String, org.talend.core.model.repository.ERepositoryObjectType, boolean, boolean, boolean, boolean, boolean[])}
     * .
     * 
     * @throws CoreException
     * @throws PersistenceException
     * @throws LoginException
     */
    @Test
    public void testGetSerializableFromFolder() throws PersistenceException, CoreException, LoginException {
        repositoryFactory.logOnProject(ProjectManager.getInstance().getCurrentProject());

        testGetSerializableProcess(repositoryFactory, "");
        testGetSerializableProcess(repositoryFactory, "myProcessFolder");

        String folderName = new Path(ERepositoryObjectType.getFolderName(ERepositoryObjectType.PROCESS))
                .append("myProcessFolder").toPortableString();
        // check that the folder exists physically
        IFolder pFolder = ResourceUtils
                .getFolder(ResourceUtils.getProject(ProjectManager.getInstance().getCurrentProject().getTechnicalLabel()),
                        folderName, false);
        assertTrue(pFolder.exists());
        pFolder.delete(true, new NullProgressMonitor());

        testGetSerializableRoutine(repositoryFactory, "");
        testGetSerializableRoutine(repositoryFactory, "myRoutineFolder");

        folderName = new Path(ERepositoryObjectType.getFolderName(ERepositoryObjectType.ROUTINES)).append("myRoutineFolder")
                .toPortableString();
        // check that the folder exists physically
        pFolder = ResourceUtils
                .getFolder(ResourceUtils.getProject(ProjectManager.getInstance().getCurrentProject().getTechnicalLabel()),
                        folderName, false);
        assertTrue(pFolder.exists());
        pFolder.delete(true, new NullProgressMonitor());
    }

    private void testGetSerializableRoutine(LocalRepositoryFactory repositoryFactory, String path) throws PersistenceException,
            CoreException {
        Object fullFolder;
        List<IRepositoryViewObject> serializableFromFolder;
        IRepositoryViewObject rvo;
        // ### START ### Test for routine
        // retrieve after create
        RoutineItem rItem = createTempRoutineItem(repositoryFactory, path);
        String routineId = rItem.getProperty().getId();
        fullFolder = getFullFolder(repositoryFactory, ProjectManager.getInstance().getCurrentProject(),
                ERepositoryObjectType.ROUTINES, "");
        serializableFromFolder = repositoryFactory.getSerializableFromFolder(ProjectManager.getInstance().getCurrentProject(),
                fullFolder, routineId, ERepositoryObjectType.ROUTINES, true, true, true, false);
        assertNotNull(serializableFromFolder);
        assertTrue(serializableFromFolder.size() == 1);
        rvo = serializableFromFolder.get(0);
        String content = new String(((RoutineItem) (rvo.getProperty().getItem())).getContent().getInnerContent());
        assertTrue(content.equals("myRoutineContent"));

        // test if logical delete (can retrieve or not with flag)
        repositoryFactory.deleteObjectLogical(ProjectManager.getInstance().getCurrentProject(), rvo);
        serializableFromFolder = repositoryFactory.getSerializableFromFolder(ProjectManager.getInstance().getCurrentProject(),
                fullFolder, routineId, ERepositoryObjectType.ROUTINES, true, true, true, false);
        assertNotNull(serializableFromFolder);
        assertTrue(serializableFromFolder.size() == 1);
        content = new String(((RoutineItem) (serializableFromFolder.get(0).getProperty().getItem())).getContent()
                .getInnerContent());
        assertTrue(content.equals("myRoutineContent"));

        serializableFromFolder = repositoryFactory.getSerializableFromFolder(ProjectManager.getInstance().getCurrentProject(),
                fullFolder, routineId, ERepositoryObjectType.ROUTINES, true, true, false, false);
        assertNotNull(serializableFromFolder);
        assertTrue(serializableFromFolder.size() == 0);

        // test if physical delete
        repositoryFactory.deleteObjectPhysical(ProjectManager.getInstance().getCurrentProject(), rvo);
        serializableFromFolder = repositoryFactory.getSerializableFromFolder(ProjectManager.getInstance().getCurrentProject(),
                fullFolder, routineId, ERepositoryObjectType.ROUTINES, true, true, true, false);
        assertNotNull(serializableFromFolder);
        assertTrue(serializableFromFolder.size() == 0);

        // test manual creation, check if can be retrieved.
        RoutineItem routineItem = PropertiesFactory.eINSTANCE.createRoutineItem();
        Property myProperty = PropertiesFactory.eINSTANCE.createProperty();
        routineItem.setProperty(myProperty);
        myProperty.setLabel("myTestRoutine");
        myProperty.setVersion("0.1");
        routineItem.setContent(PropertiesFactory.eINSTANCE.createByteArray());
        routineItem.getContent().setInnerContent("myRoutineContent".getBytes());
        routineItem.getProperty().setId("777");
        routineItem.setState(PropertiesFactory.eINSTANCE.createItemState());
        routineItem.getState().setPath(path);

        IProject project = ResourceModelUtils.getProject(ProjectManager.getInstance().getCurrentProject());
        XmiResourceManager xrm = new XmiResourceManager();
        Resource processItemResource = xrm.createItemResource(project, routineItem, new Path(path),
                ERepositoryObjectType.ROUTINES, false);
        Resource propertyResource = xrm.createPropertyResource(processItemResource);

        propertyResource.getContents().add(routineItem.getProperty());
        propertyResource.getContents().add(routineItem.getState());
        propertyResource.getContents().add(routineItem);

        processItemResource.getContents().add(routineItem.getContent());
        xrm.saveResource(processItemResource);
        xrm.saveResource(propertyResource);
        assertNull(routineItem.getParent());
        // unload the resource to be really the same as created from navigator or even SVN project.
        xrm.unloadResources(routineItem.getProperty());
        serializableFromFolder = repositoryFactory.getSerializableFromFolder(ProjectManager.getInstance().getCurrentProject(),
                fullFolder, "777", ERepositoryObjectType.ROUTINES, true, true, true, false);
        assertNotNull(serializableFromFolder);
        assertTrue(serializableFromFolder.size() == 1);
        FolderItem folderItem = repositoryFactory.getFolderItem(ProjectManager.getInstance().getCurrentProject(),
                ERepositoryObjectType.ROUTINES, new Path(path));
        routineItem = (RoutineItem) serializableFromFolder.get(0).getProperty().getItem();
        assertNotNull(routineItem.getParent());
        assertTrue(routineItem.getParent().equals(folderItem));

        // test manual deletion
        IFile fileProperty;
        IFile fileItem;
        if ("".equals(path)) {
            fileProperty = project.getFile(new Path(ERepositoryObjectType.getFolderName(ERepositoryObjectType.ROUTINES)
                    + "/myTestRoutine_0.1.properties"));
            fileItem = project.getFile(new Path(ERepositoryObjectType.getFolderName(ERepositoryObjectType.ROUTINES)
                    + "/myTestRoutine_0.1.item"));
        } else {
            fileProperty = project.getFile(new Path(ERepositoryObjectType.getFolderName(ERepositoryObjectType.ROUTINES) + "/"
                    + path + "/myTestRoutine_0.1.properties"));
            fileItem = project.getFile(new Path(ERepositoryObjectType.getFolderName(ERepositoryObjectType.ROUTINES) + "/" + path
                    + "/myTestRoutine_0.1.item"));
        }
        fileItem.delete(true, null);
        fileProperty.delete(true, null);
        serializableFromFolder = repositoryFactory.getSerializableFromFolder(ProjectManager.getInstance().getCurrentProject(),
                fullFolder, "777", ERepositoryObjectType.ROUTINES, true, true, true, false);
        assertNotNull(serializableFromFolder);
        assertTrue(serializableFromFolder.size() == 0);

        // item has been deleted, so link to original folder should be null
        assertNull(routineItem.getParent());

        // ### END ### Test for routine
    }

    private void testGetSerializableProcess(LocalRepositoryFactory repositoryFactory, String path) throws PersistenceException,
            CoreException {
        // ### START ### Test for Job
        // retrieve after create
        ProcessItem pItem = createTempProcessItem(repositoryFactory, path);
        String jobId = pItem.getProperty().getId();
        Object fullFolder = getFullFolder(repositoryFactory, ProjectManager.getInstance().getCurrentProject(),
                ERepositoryObjectType.PROCESS, path);
        List<IRepositoryViewObject> serializableFromFolder = repositoryFactory.getSerializableFromFolder(ProjectManager
                .getInstance().getCurrentProject(), fullFolder, jobId, ERepositoryObjectType.PROCESS, true, true, true, false);
        assertNotNull(serializableFromFolder);
        assertTrue(serializableFromFolder.size() == 1);
        IRepositoryViewObject rvo = serializableFromFolder.get(0);
        assertTrue(((NodeType) ((ProcessItem) (serializableFromFolder.get(0).getProperty().getItem())).getProcess().getNode()
                .get(0)).getComponentVersion().equals("0.1"));

        // test if logical delete (can retrieve or not with flag)
        repositoryFactory.deleteObjectLogical(ProjectManager.getInstance().getCurrentProject(), rvo);
        serializableFromFolder = repositoryFactory.getSerializableFromFolder(ProjectManager.getInstance().getCurrentProject(),
                fullFolder, jobId, ERepositoryObjectType.PROCESS, true, true, true, false);
        assertNotNull(serializableFromFolder);
        assertTrue(serializableFromFolder.size() == 1);
        assertTrue(((NodeType) ((ProcessItem) (serializableFromFolder.get(0).getProperty().getItem())).getProcess().getNode()
                .get(0)).getComponentVersion().equals("0.1"));

        serializableFromFolder = repositoryFactory.getSerializableFromFolder(ProjectManager.getInstance().getCurrentProject(),
                fullFolder, jobId, ERepositoryObjectType.PROCESS, true, true, false, false);
        assertNotNull(serializableFromFolder);
        assertTrue(serializableFromFolder.size() == 0);

        // test if physical delete
        repositoryFactory.deleteObjectPhysical(ProjectManager.getInstance().getCurrentProject(), rvo);
        serializableFromFolder = repositoryFactory.getSerializableFromFolder(ProjectManager.getInstance().getCurrentProject(),
                fullFolder, jobId, ERepositoryObjectType.PROCESS, true, true, true, false);
        assertNotNull(serializableFromFolder);
        assertTrue(serializableFromFolder.size() == 0);

        // test manual creation, check if can be retrieved.
        ProcessItem processItem = PropertiesFactory.eINSTANCE.createProcessItem();
        Property myProperty = PropertiesFactory.eINSTANCE.createProperty();
        processItem.setProperty(myProperty);
        myProperty.setLabel("myTestJob");
        myProperty.setVersion("0.1");
        processItem.getProperty().setId("666");
        processItem.setState(PropertiesFactory.eINSTANCE.createItemState());
        processItem.getState().setPath(path);

        processItem.setProcess(TalendFileFactory.eINSTANCE.createProcessType());
        processItem.getProcess().getNode().add(TalendFileFactory.eINSTANCE.createNodeType());
        ((NodeType) processItem.getProcess().getNode().get(0)).setComponentName("test");
        ((NodeType) processItem.getProcess().getNode().get(0)).setComponentVersion("0.1");

        IProject project = ResourceModelUtils.getProject(ProjectManager.getInstance().getCurrentProject());
        XmiResourceManager xrm = new XmiResourceManager();
        Resource processItemResource = xrm.createItemResource(project, processItem, new Path(path),
                ERepositoryObjectType.PROCESS, false);
        Resource propertyResource = xrm.createPropertyResource(processItemResource);

        propertyResource.getContents().add(processItem.getProperty());
        propertyResource.getContents().add(processItem.getState());
        propertyResource.getContents().add(processItem);

        processItemResource.getContents().add(processItem.getProcess());
        xrm.saveResource(processItemResource);
        xrm.saveResource(propertyResource);
        assertNull(processItem.getParent());
        // unload the resource to be really the same as created from navigator or even SVN project.
        xrm.unloadResources(processItem.getProperty());
        serializableFromFolder = repositoryFactory.getSerializableFromFolder(ProjectManager.getInstance().getCurrentProject(),
                fullFolder, "666", ERepositoryObjectType.PROCESS, true, true, true, false);
        assertNotNull(serializableFromFolder);
        assertTrue(serializableFromFolder.size() == 1);
        FolderItem folderItem = repositoryFactory.getFolderItem(ProjectManager.getInstance().getCurrentProject(),
                ERepositoryObjectType.PROCESS, new Path(path));
        processItem = (ProcessItem) serializableFromFolder.get(0).getProperty().getItem();
        assertNotNull(processItem.getParent());
        assertTrue(processItem.getParent().equals(folderItem));

        // test manual deletion
        IFile fileProperty;
        IFile fileItem;
        if ("".equals(path)) {
            fileProperty = project.getFile(new Path(ERepositoryObjectType.getFolderName(ERepositoryObjectType.PROCESS)
                    + "/myTestJob_0.1.properties"));
            fileItem = project.getFile(new Path(ERepositoryObjectType.getFolderName(ERepositoryObjectType.PROCESS)
                    + "/myTestJob_0.1.item"));
        } else {
            fileProperty = project.getFile(new Path(ERepositoryObjectType.getFolderName(ERepositoryObjectType.PROCESS) + "/"
                    + path + "/myTestJob_0.1.properties"));
            fileItem = project.getFile(new Path(ERepositoryObjectType.getFolderName(ERepositoryObjectType.PROCESS) + "/" + path
                    + "/myTestJob_0.1.item"));
        }
        fileItem.delete(true, null);
        fileProperty.delete(true, null);
        serializableFromFolder = repositoryFactory.getSerializableFromFolder(ProjectManager.getInstance().getCurrentProject(),
                fullFolder, "666", ERepositoryObjectType.PROCESS, true, true, true, false);
        assertNotNull(serializableFromFolder);
        assertTrue(serializableFromFolder.size() == 0);

        // item has been deleted, so link to original folder should be null
        assertNull(processItem.getParent());

        // ### END ### Test for Job
    }

    private Object getFullFolder(LocalRepositoryFactory repositoryFactory, Project project, ERepositoryObjectType itemType,
            String path) throws PersistenceException {
        Object folder = repositoryFactory.getFolder(project, itemType);
        if (folder == null) {
            return null;
        }
        Object fullFolder;
        if (folder instanceof IFolder) {
            fullFolder = (IFolder) repositoryFactory.getFolder(project, itemType);
            if (path != null && !"".equals(path)) {
                fullFolder = ((IFolder) fullFolder).getFolder(new Path(path));
            }
        } else {
            if (path != null && !"".equals(path)) {
                fullFolder = repositoryFactory.getFolderHelper(project.getEmfProject()).getFolder(
                        ((FolderItem) folder).getProperty().getLabel() + "/" + path); //$NON-NLS-1$
            } else {
                fullFolder = folder;
            }
        }
        return fullFolder;
    }

    /**
     * Test method for
     * {@link org.talend.repository.localprovider.model.LocalRepositoryFactory#getObjectFromFolder(org.talend.core.model.general.Project, org.talend.core.model.repository.ERepositoryObjectType, boolean, boolean[])}
     * .
     * 
     * @throws CoreException
     * @throws PersistenceException
     * @throws LoginException
     */
    @Test
    public void testGetObjectFromFolder() throws PersistenceException, CoreException, LoginException {
        repositoryFactory.logOnProject(ProjectManager.getInstance().getCurrentProject());

        Property property = PropertiesFactory.eINSTANCE.createProperty();
        property.setAuthor(ProjectManager.getInstance().getCurrentProject().getAuthor());
        property.setVersion(VersionUtils.DEFAULT_VERSION);
        property.setStatusCode("");
        property.setLabel("myJob");
        property.setDisplayName("myJob");
        final String nextId = repositoryFactory.getNextId();
        property.setId(nextId);
        ProcessItem processItem = PropertiesFactory.eINSTANCE.createProcessItem();
        processItem.setProperty(property);
        ProcessType process = TalendFileFactory.eINSTANCE.createProcessType();
        processItem.setProcess(process);
        repositoryFactory.create(ProjectManager.getInstance().getCurrentProject(), processItem, new Path(""), false);
        final RootContainer<Object, Object> objectFromFolder = repositoryFactory.getObjectFromFolder(ProjectManager.getInstance()
                .getCurrentProject(), ERepositoryObjectType.PROCESS, true);
        assertNotNull(objectFromFolder);
        for (Resource resource : repositoryFactory.xmiResourceManager.getAffectedResources(property)) {
            repositoryFactory.xmiResourceManager.deleteResource(resource);
        }
    }

    /**
     * Test method for
     * {@link org.talend.repository.localprovider.model.LocalRepositoryFactory#getFolderHelper(org.talend.core.model.properties.Project)}
     * .
     * 
     * @throws CoreException
     * @throws PersistenceException
     * @throws LoginException
     */
    @Test
    public void testGetFolderHelper() throws PersistenceException, LoginException {
        repositoryFactory.logOnProject(ProjectManager.getInstance().getCurrentProject());

        final FolderHelper folderHelper = repositoryFactory.getFolderHelper(ProjectManager.getInstance().getCurrentProject()
                .getEmfProject());
        assertNotNull(folderHelper);
    }

    /**
     * Test method for
     * {@link org.talend.repository.localprovider.model.LocalRepositoryFactory#getUptodateProperty(org.talend.core.model.general.Project, org.talend.core.model.properties.Property)}
     * .
     * 
     * @throws CoreException
     * @throws PersistenceException
     * @throws LoginException
     */
    @Test
    public void testGetUptodateProperty() throws PersistenceException, LoginException {
        repositoryFactory.logOnProject(ProjectManager.getInstance().getCurrentProject());

        Property property = PropertiesFactory.eINSTANCE.createProperty();
        property.setAuthor(ProjectManager.getInstance().getCurrentProject().getAuthor());
        property.setVersion(VersionUtils.DEFAULT_VERSION);
        property.setLabel("myJob");
        property.setDisplayName("myJob");
        property.setStatusCode("");
        final String nextId = repositoryFactory.getNextId();
        property.setId(nextId);
        ProcessItem processItem = PropertiesFactory.eINSTANCE.createProcessItem();
        processItem.setProperty(property);
        ProcessType process = TalendFileFactory.eINSTANCE.createProcessType();
        processItem.setProcess(process);
        repositoryFactory.create(ProjectManager.getInstance().getCurrentProject(), processItem, new Path(""), false);
        assertEquals(property.getDisplayName(), "myJob");
        property.setDisplayName("myJob2");
        final Property uptodateProperty = repositoryFactory.getUptodateProperty(ProjectManager.getInstance().getCurrentProject(),
                property);
        assertNotNull(uptodateProperty);
        assertNotNull(uptodateProperty.eResource());
        // item was not unloaded before, so value will be same as property
        // to review, not sure it's a good use for this function since it won't really force the update.. or
        // should rename it maybe?
        assertEquals(uptodateProperty.getDisplayName(), "myJob2");

        repositoryFactory.unloadUnlockedResources();
        final Property uptodateProperty2 = repositoryFactory.getUptodateProperty(
                ProjectManager.getInstance().getCurrentProject(), property);
        assertNotNull(uptodateProperty2);
        assertNotNull(uptodateProperty2.eResource());
        // item has been unloaded, so will reload from file
        assertEquals(uptodateProperty2.getDisplayName(), "myJob");

        repositoryFactory.deleteObjectPhysical(ProjectManager.getInstance().getCurrentProject(), new RepositoryObject(
                uptodateProperty));
    }

    /**
     * Test method for
     * {@link org.talend.repository.localprovider.model.LocalRepositoryFactory#reloadProject(org.talend.core.model.general.Project)}
     * .
     * 
     * @throws CoreException
     * @throws PersistenceException
     */
    @Test
    public void testReloadProject() throws PersistenceException, CoreException {
        final org.talend.core.model.properties.Project oldEmfProject = ProjectManager.getInstance().getCurrentProject()
                .getEmfProject();
        repositoryFactory.reloadProject(ProjectManager.getInstance().getCurrentProject());
        assertNotNull(ProjectManager.getInstance().getCurrentProject().getEmfProject());
        assertNotSame(oldEmfProject, ProjectManager.getInstance().getCurrentProject().getEmfProject());
    }

    /**
     * Test method for
     * {@link org.talend.repository.localprovider.model.LocalRepositoryFactory#getAll(org.talend.core.model.general.Project, org.talend.core.model.repository.ERepositoryObjectType, boolean, boolean)}
     * .
     * 
     * @throws CoreException
     * @throws PersistenceException
     * @throws LoginException
     */
    @Test
    public void testGetAll() throws PersistenceException, CoreException, LoginException {
        repositoryFactory.logOnProject(ProjectManager.getInstance().getCurrentProject());

        Property property = PropertiesFactory.eINSTANCE.createProperty();
        property.setAuthor(ProjectManager.getInstance().getCurrentProject().getAuthor());
        property.setVersion(VersionUtils.DEFAULT_VERSION);
        property.setStatusCode("");
        property.setLabel("myJob");
        property.setDisplayName("myJob");
        final String nextId = repositoryFactory.getNextId();
        property.setId(nextId);
        ProcessItem processItem = PropertiesFactory.eINSTANCE.createProcessItem();
        processItem.setProperty(property);
        ProcessType process = TalendFileFactory.eINSTANCE.createProcessType();
        processItem.setProcess(process);
        repositoryFactory.create(ProjectManager.getInstance().getCurrentProject(), processItem, new Path(""), false);
        final List<IRepositoryViewObject> all = repositoryFactory.getAll(ProjectManager.getInstance().getCurrentProject(),
                ERepositoryObjectType.PROCESS, true, true);
        assertNotNull(all);
        assertTrue(!all.isEmpty());

        repositoryFactory.deleteObjectPhysical(ProjectManager.getInstance().getCurrentProject(), new RepositoryObject(property));
    }

    /**
     * Test method for
     * {@link org.talend.repository.localprovider.model.LocalRepositoryFactory#unloadUnlockedResources()}.
     * 
     * @throws CoreException
     * @throws PersistenceException
     * @throws LoginException
     */
    @Test
    public void testUnloadUnlockedResources() throws PersistenceException, CoreException, LoginException {
        repositoryFactory.logOnProject(ProjectManager.getInstance().getCurrentProject());

        Property property = PropertiesFactory.eINSTANCE.createProperty();
        property.setAuthor(ProjectManager.getInstance().getCurrentProject().getAuthor());
        property.setVersion(VersionUtils.DEFAULT_VERSION);
        property.setStatusCode("");
        property.setLabel("jobA");

        final String nextId = repositoryFactory.getNextId();
        property.setId(nextId);
        ProcessItem processItem = PropertiesFactory.eINSTANCE.createProcessItem();
        processItem.setProperty(property);
        ProcessType process = TalendFileFactory.eINSTANCE.createProcessType();
        processItem.setProcess(process);

        Property property2 = PropertiesFactory.eINSTANCE.createProperty();
        property2.setAuthor(ProjectManager.getInstance().getCurrentProject().getAuthor());
        property2.setVersion(VersionUtils.DEFAULT_VERSION);
        property2.setStatusCode("");
        property2.setLabel("jobB");
        final String nextId2 = repositoryFactory.getNextId();
        property2.setId(nextId2);
        ProcessItem processItem2 = PropertiesFactory.eINSTANCE.createProcessItem();
        processItem2.setProperty(property2);
        ProcessType process2 = TalendFileFactory.eINSTANCE.createProcessType();
        processItem2.setProcess(process2);

        repositoryFactory.create(ProjectManager.getInstance().getCurrentProject(), processItem, new Path(""), false);
        repositoryFactory.create(ProjectManager.getInstance().getCurrentProject(), processItem2, new Path(""), false);

        ProcessItem pItem = createTempProcessItem(repositoryFactory, "");
        RoutineItem rItem = createTempRoutineItem(repositoryFactory, "");

        assertNotNull(processItem.eResource());
        assertNotNull(processItem2.eResource());
        assertNotNull(pItem.eResource());
        assertNotNull(rItem.eResource());
        FolderItem parent = (FolderItem) pItem.getParent();
        ProxyRepositoryFactory.getInstance().setFullLogonFinished(true);
        repositoryFactory.unloadUnlockedResources();
        assertNull(processItem.eResource());
        assertNull(processItem2.eResource());
        assertNull(pItem.eResource());
        assertNull(rItem.eResource());
        assertNull(pItem.getParent());
        assertTrue(!parent.getChildren().contains(pItem));

        IRepositoryViewObject object1 = repositoryFactory.getLastVersion(ProjectManager.getInstance().getCurrentProject(), pItem
                .getProperty().getId());
        Property prop1 = object1.getProperty();
        pItem = (ProcessItem) prop1.getItem();
        IRepositoryViewObject object2 = repositoryFactory.getLastVersion(ProjectManager.getInstance().getCurrentProject(), rItem
                .getProperty().getId());
        Property prop2 = object2.getProperty();
        rItem = (RoutineItem) prop2.getItem();
        assertNotNull(pItem.eResource());
        assertNotNull(rItem.eResource());
        assertTrue(((NodeType) (pItem).getProcess().getNode().get(0)).getComponentVersion().equals("0.1"));
        String content = new String((rItem).getContent().getInnerContent());
        assertTrue(content.equals("myRoutineContent"));

        // ## test to modify after bug 16633 is updated
        pItem.getState().setDeleted(true);
        repositoryFactory.unloadUnlockedResources();
        // unlocked resource kept in memory, and keep in folder
        assertNotNull(pItem.getParent());
        assertTrue(((FolderItem) pItem.getParent()).getChildren().contains(pItem));
        // ## test to modify after bug 16633 is updated

        pItem = (ProcessItem) repositoryFactory
                .getLastVersion(ProjectManager.getInstance().getCurrentProject(), pItem.getProperty().getId()).getProperty()
                .getItem();

        // test locked item (keep in memory)
        pItem.getState().setDeleted(false);
        pItem.getState().setLocked(true);
        repositoryFactory.unloadUnlockedResources();
        assertNotNull(pItem.getParent());
        assertTrue(((FolderItem) pItem.getParent()).getChildren().contains(pItem));
        assertNotNull(pItem.eResource());

        property = repositoryFactory.getUptodateProperty(ProjectManager.getInstance().getCurrentProject(), property);
        repositoryFactory.deleteObjectPhysical(ProjectManager.getInstance().getCurrentProject(), new RepositoryObject(property));
        property2 = repositoryFactory.getUptodateProperty(ProjectManager.getInstance().getCurrentProject(), property2);
        repositoryFactory.deleteObjectPhysical(ProjectManager.getInstance().getCurrentProject(), new RepositoryObject(property2));
        prop1 = repositoryFactory.getUptodateProperty(ProjectManager.getInstance().getCurrentProject(), prop1);
        repositoryFactory.deleteObjectPhysical(ProjectManager.getInstance().getCurrentProject(), new RepositoryObject(prop1));
        prop2 = repositoryFactory.getUptodateProperty(ProjectManager.getInstance().getCurrentProject(), prop2);
        repositoryFactory.deleteObjectPhysical(ProjectManager.getInstance().getCurrentProject(), new RepositoryObject(prop2));
    }

    /**
     * Test method for
     * {@link org.talend.repository.localprovider.model.LocalRepositoryFactory#unloadResources(org.talend.core.model.properties.Property)}
     * .
     * 
     * @throws CoreException
     * @throws PersistenceException
     * @throws LoginException
     */
    @Test
    public void testUnloadResourcesProperty() throws PersistenceException, CoreException, LoginException {
        repositoryFactory.logOnProject(ProjectManager.getInstance().getCurrentProject());

        Property property = PropertiesFactory.eINSTANCE.createProperty();
        property.setAuthor(ProjectManager.getInstance().getCurrentProject().getAuthor());
        property.setVersion(VersionUtils.DEFAULT_VERSION);
        property.setStatusCode("");
        property.setLabel("jobA");

        final String nextId = repositoryFactory.getNextId();
        property.setId(nextId);
        ProcessItem processItem = PropertiesFactory.eINSTANCE.createProcessItem();
        processItem.setProperty(property);
        ProcessType process = TalendFileFactory.eINSTANCE.createProcessType();
        processItem.setProcess(process);

        Property property2 = PropertiesFactory.eINSTANCE.createProperty();
        property2.setAuthor(ProjectManager.getInstance().getCurrentProject().getAuthor());
        property2.setVersion(VersionUtils.DEFAULT_VERSION);
        property2.setStatusCode("");
        property2.setLabel("jobB");
        final String nextId2 = repositoryFactory.getNextId();
        property2.setId(nextId2);
        ProcessItem processItem2 = PropertiesFactory.eINSTANCE.createProcessItem();
        processItem2.setProperty(property2);
        ProcessType process2 = TalendFileFactory.eINSTANCE.createProcessType();
        processItem2.setProcess(process2);

        repositoryFactory.create(ProjectManager.getInstance().getCurrentProject(), processItem, new Path(""), false);
        repositoryFactory.create(ProjectManager.getInstance().getCurrentProject(), processItem2, new Path(""), false);

        assertNotNull(processItem.eResource());
        assertNotNull(processItem2.eResource());
        repositoryFactory.unloadResources(property2);
        assertNotNull(processItem.eResource());
        assertNull(processItem2.eResource());

        property = repositoryFactory.getUptodateProperty(ProjectManager.getInstance().getCurrentProject(), property);
        repositoryFactory.deleteObjectPhysical(ProjectManager.getInstance().getCurrentProject(), new RepositoryObject(property));
        property2 = repositoryFactory.getUptodateProperty(ProjectManager.getInstance().getCurrentProject(), property2);
        repositoryFactory.deleteObjectPhysical(ProjectManager.getInstance().getCurrentProject(), new RepositoryObject(property2));
    }

    /**
     * Test method for {@link
     * org.talend.repository.localprovider.model.LocalRepositoryFactory#save((org.talend.core.model.general.Project,
     * org.talend.core.model.properties.Item)} .
     * 
     * @throws PersistenceException
     * @throws CoreException
     * @throws LoginException
     */
    @Test
    public void testSaveProjectItem() throws PersistenceException, CoreException, LoginException {
        repositoryFactory.logOnProject(ProjectManager.getInstance().getCurrentProject());

        testProcesspropagateFileName(repositoryFactory, "");
        testProcesspropagateFileName(repositoryFactory, "myFolder");

        testRoutinepropagateFileName(repositoryFactory, "");
        testRoutinepropagateFileName(repositoryFactory, "myFolder");
    }

    /**
     * Test method for {@link
     * org.talend.repository.localprovider.model.LocalRepositoryFactory#save((org.talend.core.model.general.Project,
     * org.talend.core.model.properties.Property)} .
     * 
     * @throws PersistenceException
     * @throws CoreException
     * @throws LoginException
     */
    @Test
    public void testSaveProjectProperty() throws PersistenceException, CoreException, LoginException {
        repositoryFactory.logOnProject(ProjectManager.getInstance().getCurrentProject());

        testProcesspropagateFileName2(repositoryFactory, "");
        testProcesspropagateFileName2(repositoryFactory, "myFolder");

        testRoutinepropagateFileName2(repositoryFactory, "");
        testRoutinepropagateFileName2(repositoryFactory, "myFolder");
    }

    private void testProcesspropagateFileName(LocalRepositoryFactory factory, String path) throws PersistenceException {
        IProject project = ResourceModelUtils.getProject(ProjectManager.getInstance().getCurrentProject());
        ProcessItem processItem = createTempProcessItem(factory, path);

        String processId = processItem.getProperty().getId();
        FolderItem folderItem = factory.getFolderItem(ProjectManager.getInstance().getCurrentProject(),
                ERepositoryObjectType.PROCESS, new Path(path));
        assertNotNull(processItem.getParent());
        assertTrue(processItem.getParent().equals(folderItem));

        // same name, same version, only change content:
        ((NodeType) processItem.getProcess().getNode().get(0)).setComponentName("toto");
        factory.save(ProjectManager.getInstance().getCurrentProject(), processItem);
        factory.unloadUnlockedResources();
        processItem = (ProcessItem) factory.getLastVersion(ProjectManager.getInstance().getCurrentProject(), processId)
                .getProperty().getItem();
        assertTrue(((NodeType) (processItem).getProcess().getNode().get(0)).getComponentName().equals("toto"));
        folderItem = factory.getFolderItem(ProjectManager.getInstance().getCurrentProject(), ERepositoryObjectType.PROCESS,
                new Path(path));
        assertNotNull(processItem.getParent());
        assertTrue(processItem.getParent().equals(folderItem));

        // test 1 item, 1 version, change label only
        processItem.getProperty().setLabel("myJob2");
        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob", "0.1");
        factory.save(ProjectManager.getInstance().getCurrentProject(), processItem);
        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob2", "0.1");
        checkFileNotExists(project, ERepositoryObjectType.PROCESS, path, "myJob", "0.1");
        folderItem = factory.getFolderItem(ProjectManager.getInstance().getCurrentProject(), ERepositoryObjectType.PROCESS,
                new Path(path));
        assertNotNull(processItem.getParent());
        assertTrue(processItem.getParent().equals(folderItem));

        // test 1 item, 1 version, change version only
        processItem.getProperty().setVersion("0.2");
        ((NodeType) processItem.getProcess().getNode().get(0)).setComponentVersion("0.2");
        factory.save(ProjectManager.getInstance().getCurrentProject(), processItem);
        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob2", "0.1");
        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob2", "0.2");

        factory.unloadUnlockedResources();

        List<IRepositoryViewObject> objects = factory.getAllVersion(ProjectManager.getInstance().getCurrentProject(), processId,
                false);
        for (IRepositoryViewObject object : objects) {
            assertTrue(object instanceof RepositoryObject);
            assertTrue(((NodeType) ((ProcessItem) object.getProperty().getItem()).getProcess().getNode().get(0))
                    .getComponentVersion().equals(object.getProperty().getVersion()));
            folderItem = factory.getFolderItem(ProjectManager.getInstance().getCurrentProject(), ERepositoryObjectType.PROCESS,
                    new Path(path));
            assertNotNull(object.getProperty().getItem().getParent());
            assertTrue(object.getProperty().getItem().getParent().equals(folderItem));
        }

        processItem = (ProcessItem) factory.getLastVersion(ProjectManager.getInstance().getCurrentProject(), processId)
                .getProperty().getItem();
        folderItem = factory.getFolderItem(ProjectManager.getInstance().getCurrentProject(), ERepositoryObjectType.PROCESS,
                new Path(path));
        assertNotNull(processItem.getParent());
        assertTrue(processItem.getParent().equals(folderItem));

        // test 1 item, 2 version, change name only
        processItem.getProperty().setLabel("myJob3");
        factory.save(ProjectManager.getInstance().getCurrentProject(), processItem);

        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob3", "0.1");
        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob3", "0.2");
        checkFileNotExists(project, ERepositoryObjectType.PROCESS, path, "myJob2", "0.1");
        checkFileNotExists(project, ERepositoryObjectType.PROCESS, path, "myJob2", "0.2");

        // test 1 item, 2 version, change version
        processItem.getProperty().setVersion("0.3");
        ((NodeType) processItem.getProcess().getNode().get(0)).setComponentVersion("0.3");
        factory.save(ProjectManager.getInstance().getCurrentProject(), processItem);
        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob3", "0.1");
        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob3", "0.2");
        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob3", "0.3");

        factory.unloadUnlockedResources();

        objects = factory.getAllVersion(ProjectManager.getInstance().getCurrentProject(), processId, false);
        for (IRepositoryViewObject object : objects) {
            assertTrue(object instanceof RepositoryObject);
            assertTrue(((NodeType) ((ProcessItem) object.getProperty().getItem()).getProcess().getNode().get(0))
                    .getComponentVersion().equals(object.getProperty().getVersion()));
            folderItem = factory.getFolderItem(ProjectManager.getInstance().getCurrentProject(), ERepositoryObjectType.PROCESS,
                    new Path(path));
            assertNotNull(object.getProperty().getItem().getParent());
            assertTrue(object.getProperty().getItem().getParent().equals(folderItem));
        }

        processItem = (ProcessItem) factory.getLastVersion(ProjectManager.getInstance().getCurrentProject(), processId)
                .getProperty().getItem();
        folderItem = factory.getFolderItem(ProjectManager.getInstance().getCurrentProject(), ERepositoryObjectType.PROCESS,
                new Path(path));
        assertNotNull(processItem.getParent());
        assertTrue(processItem.getParent().equals(folderItem));

        // test 1 item, 3 version, change version and name
        processItem.getProperty().setVersion("1.0");
        processItem.getProperty().setLabel("myJob");
        ((NodeType) processItem.getProcess().getNode().get(0)).setComponentVersion("1.0");
        factory.save(ProjectManager.getInstance().getCurrentProject(), processItem);
        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob", "0.1");
        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob", "0.2");
        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob", "0.3");
        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob", "1.0");
        checkFileNotExists(project, ERepositoryObjectType.PROCESS, path, "myJob3", "0.1");
        checkFileNotExists(project, ERepositoryObjectType.PROCESS, path, "myJob3", "0.2");
        checkFileNotExists(project, ERepositoryObjectType.PROCESS, path, "myJob3", "0.3");

        objects = factory.getAllVersion(ProjectManager.getInstance().getCurrentProject(), processId, false);
        for (IRepositoryViewObject object : objects) {
            assertTrue(object instanceof RepositoryObject);
            assertTrue(((NodeType) ((ProcessItem) object.getProperty().getItem()).getProcess().getNode().get(0))
                    .getComponentVersion().equals(object.getProperty().getVersion()));
            folderItem = factory.getFolderItem(ProjectManager.getInstance().getCurrentProject(), ERepositoryObjectType.PROCESS,
                    new Path(path));
            assertNotNull(object.getProperty().getItem().getParent());
            assertTrue(object.getProperty().getItem().getParent().equals(folderItem));
        }

        factory.deleteObjectPhysical(ProjectManager.getInstance().getCurrentProject(),
                factory.getLastVersion(ProjectManager.getInstance().getCurrentProject(), processId));
    }

    private void testRoutinepropagateFileName(LocalRepositoryFactory factory, String path) throws PersistenceException {
        IProject project = ResourceModelUtils.getProject(ProjectManager.getInstance().getCurrentProject());
        RoutineItem routineItem = createTempRoutineItem(factory, path);
        FolderItem folderItem = factory.getFolderItem(ProjectManager.getInstance().getCurrentProject(),
                ERepositoryObjectType.ROUTINES, new Path(path));
        assertNotNull(routineItem.getParent());
        assertTrue(routineItem.getParent().equals(folderItem));

        String routineId = routineItem.getProperty().getId();

        // same name, same version, only change content:
        routineItem.getContent().setInnerContent("myRoutineContent_0.1".getBytes());
        factory.save(ProjectManager.getInstance().getCurrentProject(), routineItem);
        factory.unloadUnlockedResources();
        routineItem = (RoutineItem) factory.getLastVersion(ProjectManager.getInstance().getCurrentProject(), routineId)
                .getProperty().getItem();
        String content = new String(routineItem.getContent().getInnerContent());
        assertTrue(content.equals("myRoutineContent_0.1"));

        // test 1 item, 1 version, change label only
        routineItem.getProperty().setLabel("myRoutine2");
        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine", "0.1");
        factory.save(ProjectManager.getInstance().getCurrentProject(), routineItem);
        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine2", "0.1");
        checkFileNotExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine", "0.1");

        // test 1 item, 1 version, change version only
        routineItem.getProperty().setVersion("0.2");
        routineItem.getContent().setInnerContent("myRoutineContent_0.2".getBytes());
        factory.save(ProjectManager.getInstance().getCurrentProject(), routineItem);
        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine2", "0.1");
        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine2", "0.2");

        factory.unloadUnlockedResources();

        List<IRepositoryViewObject> objects = factory.getAllVersion(ProjectManager.getInstance().getCurrentProject(), routineId,
                false);
        for (IRepositoryViewObject object : objects) {
            assertTrue(object instanceof RepositoryObject);
            content = new String(((RoutineItem) object.getProperty().getItem()).getContent().getInnerContent());
            assertTrue(content.equals("myRoutineContent_" + object.getProperty().getVersion()));
            folderItem = factory.getFolderItem(ProjectManager.getInstance().getCurrentProject(), ERepositoryObjectType.ROUTINES,
                    new Path(path));
            assertNotNull(object.getProperty().getItem().getParent());
            assertTrue(object.getProperty().getItem().getParent().equals(folderItem));
        }

        routineItem = (RoutineItem) factory.getLastVersion(ProjectManager.getInstance().getCurrentProject(), routineId)
                .getProperty().getItem();
        folderItem = factory.getFolderItem(ProjectManager.getInstance().getCurrentProject(), ERepositoryObjectType.ROUTINES,
                new Path(path));
        assertNotNull(routineItem.getParent());
        assertTrue(routineItem.getParent().equals(folderItem));

        // test 1 item, 2 version, change name only
        routineItem.getProperty().setLabel("myRoutine3");
        factory.save(ProjectManager.getInstance().getCurrentProject(), routineItem);

        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine3", "0.1");
        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine3", "0.2");
        checkFileNotExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine2", "0.1");
        checkFileNotExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine2", "0.2");

        // test 1 item, 2 version, change version
        routineItem.getProperty().setVersion("0.3");
        routineItem.getContent().setInnerContent("myRoutineContent_0.3".getBytes());
        factory.save(ProjectManager.getInstance().getCurrentProject(), routineItem);

        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine3", "0.1");
        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine3", "0.2");
        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine3", "0.3");

        objects = factory.getAllVersion(ProjectManager.getInstance().getCurrentProject(), routineId, false);
        for (IRepositoryViewObject object : objects) {
            assertTrue(object instanceof RepositoryObject);
            content = new String(((RoutineItem) object.getProperty().getItem()).getContent().getInnerContent());
            assertTrue(content.equals("myRoutineContent_" + object.getProperty().getVersion()));
            folderItem = factory.getFolderItem(ProjectManager.getInstance().getCurrentProject(), ERepositoryObjectType.ROUTINES,
                    new Path(path));
            assertNotNull(object.getProperty().getItem().getParent());
            assertTrue(object.getProperty().getItem().getParent().equals(folderItem));
        }

        routineItem = (RoutineItem) factory.getLastVersion(ProjectManager.getInstance().getCurrentProject(), routineId)
                .getProperty().getItem();
        folderItem = factory.getFolderItem(ProjectManager.getInstance().getCurrentProject(), ERepositoryObjectType.ROUTINES,
                new Path(path));
        assertNotNull(routineItem.getParent());
        assertTrue(routineItem.getParent().equals(folderItem));

        // test 1 item, 3 version, change version and name
        routineItem.getProperty().setVersion("1.0");
        routineItem.getProperty().setLabel("myRoutine");
        routineItem.getContent().setInnerContent("myRoutineContent_1.0".getBytes());
        factory.save(ProjectManager.getInstance().getCurrentProject(), routineItem);
        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine", "0.1");
        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine", "0.2");
        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine", "0.3");
        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine", "1.0");
        checkFileNotExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine3", "0.1");
        checkFileNotExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine3", "0.2");
        checkFileNotExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine3", "0.3");

        objects = factory.getAllVersion(ProjectManager.getInstance().getCurrentProject(), routineId, false);
        for (IRepositoryViewObject object : objects) {
            assertTrue(object instanceof RepositoryObject);
            content = new String(((RoutineItem) object.getProperty().getItem()).getContent().getInnerContent());
            assertTrue(content.equals("myRoutineContent_" + object.getProperty().getVersion()));
            folderItem = factory.getFolderItem(ProjectManager.getInstance().getCurrentProject(), ERepositoryObjectType.ROUTINES,
                    new Path(path));
            assertNotNull(object.getProperty().getItem().getParent());
            assertTrue(object.getProperty().getItem().getParent().equals(folderItem));
        }

        factory.deleteObjectPhysical(ProjectManager.getInstance().getCurrentProject(),
                factory.getLastVersion(ProjectManager.getInstance().getCurrentProject(), routineId));
    }

    private void testProcesspropagateFileName2(LocalRepositoryFactory factory, String path) throws PersistenceException {
        IProject project = ResourceModelUtils.getProject(ProjectManager.getInstance().getCurrentProject());
        ProcessItem processItem = createTempProcessItem(factory, path);
        FolderItem folderItem = factory.getFolderItem(ProjectManager.getInstance().getCurrentProject(),
                ERepositoryObjectType.PROCESS, new Path(path));
        assertNotNull(processItem.getParent());
        assertTrue(processItem.getParent().equals(folderItem));

        String processId = processItem.getProperty().getId();

        // test 1 item, 1 version, change label only
        processItem.getProperty().setLabel("myJob2");
        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob", "0.1");
        factory.save(ProjectManager.getInstance().getCurrentProject(), processItem.getProperty());
        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob2", "0.1");
        checkFileNotExists(project, ERepositoryObjectType.PROCESS, path, "myJob", "0.1");

        // test 1 item, 1 version, change version only
        processItem.getProperty().setVersion("0.2");
        factory.save(ProjectManager.getInstance().getCurrentProject(), processItem.getProperty());
        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob2", "0.1");
        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob2", "0.2");

        factory.unloadUnlockedResources();

        List<IRepositoryViewObject> objects = factory.getAllVersion(ProjectManager.getInstance().getCurrentProject(), processId,
                false);
        assertTrue(objects.size() == 2);

        processItem = (ProcessItem) factory.getLastVersion(ProjectManager.getInstance().getCurrentProject(), processId)
                .getProperty().getItem();
        folderItem = factory.getFolderItem(ProjectManager.getInstance().getCurrentProject(), ERepositoryObjectType.PROCESS,
                new Path(path));
        assertNotNull(processItem.getParent());
        assertTrue(processItem.getParent().equals(folderItem));

        // test 1 item, 2 version, change name only
        processItem.getProperty().setLabel("myJob3");
        factory.save(ProjectManager.getInstance().getCurrentProject(), processItem.getProperty());

        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob3", "0.1");
        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob3", "0.2");
        checkFileNotExists(project, ERepositoryObjectType.PROCESS, path, "myJob2", "0.1");
        checkFileNotExists(project, ERepositoryObjectType.PROCESS, path, "myJob2", "0.2");

        // test 1 item, 2 version, change version
        processItem.getProperty().setVersion("0.3");
        factory.save(ProjectManager.getInstance().getCurrentProject(), processItem.getProperty());
        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob3", "0.1");
        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob3", "0.2");
        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob3", "0.3");

        factory.unloadUnlockedResources();

        objects = factory.getAllVersion(ProjectManager.getInstance().getCurrentProject(), processId, false);
        assertTrue(objects.size() == 3);

        processItem = (ProcessItem) factory.getLastVersion(ProjectManager.getInstance().getCurrentProject(), processId)
                .getProperty().getItem();
        folderItem = factory.getFolderItem(ProjectManager.getInstance().getCurrentProject(), ERepositoryObjectType.PROCESS,
                new Path(path));
        assertNotNull(processItem.getParent());
        assertTrue(processItem.getParent().equals(folderItem));

        // test 1 item, 3 version, change version and name
        processItem.getProperty().setVersion("1.0");
        processItem.getProperty().setLabel("myJob");
        factory.save(ProjectManager.getInstance().getCurrentProject(), processItem.getProperty());
        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob", "0.1");
        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob", "0.2");
        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob", "0.3");
        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob", "1.0");
        checkFileNotExists(project, ERepositoryObjectType.PROCESS, path, "myJob3", "0.1");
        checkFileNotExists(project, ERepositoryObjectType.PROCESS, path, "myJob3", "0.2");
        checkFileNotExists(project, ERepositoryObjectType.PROCESS, path, "myJob3", "0.3");

        objects = factory.getAllVersion(ProjectManager.getInstance().getCurrentProject(), processId, false);
        assertTrue(objects.size() == 4);

        factory.deleteObjectPhysical(ProjectManager.getInstance().getCurrentProject(),
                factory.getLastVersion(ProjectManager.getInstance().getCurrentProject(), processId));
    }

    private void testRoutinepropagateFileName2(LocalRepositoryFactory factory, String path) throws PersistenceException {
        IProject project = ResourceModelUtils.getProject(ProjectManager.getInstance().getCurrentProject());
        RoutineItem routineItem = createTempRoutineItem(factory, path);
        FolderItem folderItem = factory.getFolderItem(ProjectManager.getInstance().getCurrentProject(),
                ERepositoryObjectType.ROUTINES, new Path(path));
        assertNotNull(routineItem.getParent());
        assertTrue(routineItem.getParent().equals(folderItem));

        String routineId = routineItem.getProperty().getId();

        // test 1 item, 1 version, change label only
        routineItem.getProperty().setLabel("myRoutine2");
        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine", "0.1");
        factory.save(ProjectManager.getInstance().getCurrentProject(), routineItem.getProperty());
        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine2", "0.1");
        checkFileNotExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine", "0.1");

        // test 1 item, 1 version, change version only
        routineItem.getProperty().setVersion("0.2");
        factory.save(ProjectManager.getInstance().getCurrentProject(), routineItem.getProperty());
        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine2", "0.1");
        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine2", "0.2");

        factory.unloadUnlockedResources();

        List<IRepositoryViewObject> objects = factory.getAllVersion(ProjectManager.getInstance().getCurrentProject(), routineId,
                false);
        assertTrue(objects.size() == 2);

        routineItem = (RoutineItem) factory.getLastVersion(ProjectManager.getInstance().getCurrentProject(), routineId)
                .getProperty().getItem();
        folderItem = factory.getFolderItem(ProjectManager.getInstance().getCurrentProject(), ERepositoryObjectType.ROUTINES,
                new Path(path));
        assertNotNull(routineItem.getParent());
        assertTrue(routineItem.getParent().equals(folderItem));

        // test 1 item, 2 version, change name only
        routineItem.getProperty().setLabel("myRoutine3");
        factory.save(ProjectManager.getInstance().getCurrentProject(), routineItem.getProperty());

        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine3", "0.1");
        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine3", "0.2");
        checkFileNotExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine2", "0.1");
        checkFileNotExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine2", "0.2");

        // test 1 item, 2 version, change version
        routineItem.getProperty().setVersion("0.3");
        factory.save(ProjectManager.getInstance().getCurrentProject(), routineItem.getProperty());

        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine3", "0.1");
        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine3", "0.2");
        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine3", "0.3");

        objects = factory.getAllVersion(ProjectManager.getInstance().getCurrentProject(), routineId, false);
        assertTrue(objects.size() == 3);

        routineItem = (RoutineItem) factory.getLastVersion(ProjectManager.getInstance().getCurrentProject(), routineId)
                .getProperty().getItem();
        folderItem = factory.getFolderItem(ProjectManager.getInstance().getCurrentProject(), ERepositoryObjectType.ROUTINES,
                new Path(path));
        assertNotNull(routineItem.getParent());
        assertTrue(routineItem.getParent().equals(folderItem));

        // test 1 item, 3 version, change version and name
        routineItem.getProperty().setVersion("1.0");
        routineItem.getProperty().setLabel("myRoutine");
        factory.save(ProjectManager.getInstance().getCurrentProject(), routineItem.getProperty());
        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine", "0.1");
        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine", "0.2");
        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine", "0.3");
        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine", "1.0");
        checkFileNotExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine3", "0.1");
        checkFileNotExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine3", "0.2");
        checkFileNotExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine3", "0.3");

        objects = factory.getAllVersion(ProjectManager.getInstance().getCurrentProject(), routineId, false);
        assertTrue(objects.size() == 4);

        factory.deleteObjectPhysical(ProjectManager.getInstance().getCurrentProject(),
                factory.getLastVersion(ProjectManager.getInstance().getCurrentProject(), routineId));
    }

    private void checkFileExists(IProject project, ERepositoryObjectType type, String path, String name, String version) {
        IFile fileProperty;
        IFile fileItem;
        if ("".equals(path)) {
            fileProperty = project.getFile(new Path(ERepositoryObjectType.getFolderName(type) + "/" + name + "_" + version
                    + ".properties"));
            fileItem = project
                    .getFile(new Path(ERepositoryObjectType.getFolderName(type) + "/" + name + "_" + version + ".item"));
        } else {
            fileProperty = project.getFile(new Path(ERepositoryObjectType.getFolderName(type) + "/" + path + "/" + name + "_"
                    + version + ".properties"));
            fileItem = project.getFile(new Path(ERepositoryObjectType.getFolderName(type) + "/" + path + "/" + name + "_"
                    + version + ".item"));
        }

        assertTrue(fileProperty.exists());
        assertTrue(fileItem.exists());
    }

    private void checkFileNotExists(IProject project, ERepositoryObjectType type, String path, String name, String version) {
        IFile fileProperty;
        IFile fileItem;
        if ("".equals(path)) {
            fileProperty = project.getFile(new Path(ERepositoryObjectType.getFolderName(type) + "/" + name + "_" + version
                    + ".properties"));
            fileItem = project
                    .getFile(new Path(ERepositoryObjectType.getFolderName(type) + "/" + name + "_" + version + ".item"));
        } else {
            fileProperty = project.getFile(new Path(ERepositoryObjectType.getFolderName(type) + "/" + path + "/" + name + "_"
                    + version + ".properties"));
            fileItem = project.getFile(new Path(ERepositoryObjectType.getFolderName(type) + "/" + path + "/" + name + "_"
                    + version + ".item"));
        }

        assertTrue(!fileProperty.exists());
        assertTrue(!fileItem.exists());
    }

    /**
     * Test method for
     * {@link org.talend.repository.localprovider.model.LocalRepositoryFactory#moveObject(org.talend.core.model.general.Project)}
     * .
     * 
     * @throws CoreException
     * @throws PersistenceException
     * @throws LoginException
     */
    @Test
    public void testMoveObject() throws PersistenceException, CoreException, LoginException {
        repositoryFactory.logOnProject(ProjectManager.getInstance().getCurrentProject());

        ProcessItem processItem = createTempProcessItem(repositoryFactory, "");
        String processId = processItem.getProperty().getId();
        List<IRepositoryViewObject> objects = repositoryFactory.getAllVersion(ProjectManager.getInstance().getCurrentProject(),
                processId, true);

        final Folder createFolder = repositoryFactory.createFolder(ProjectManager.getInstance().getCurrentProject(),
                ERepositoryObjectType.PROCESS, new Path(""), "moveToThisFolder");
        assertNotNull(createFolder);

        IPath ip = new Path(createFolder.getLabel());

        assertNotNull(objects.get(0));
        repositoryFactory.moveObject(objects.get(0), ip);
        IProject project = ResourceModelUtils.getProject(ProjectManager.getInstance().getCurrentProject());
        checkMoveObjectFileExists(project, ERepositoryObjectType.PROCESS, createFolder.getLabel(), processItem.getProperty()
                .getLabel(), processItem.getProperty().getVersion());

        Property property = repositoryFactory.getUptodateProperty(ProjectManager.getInstance().getCurrentProject(),
                processItem.getProperty());
        repositoryFactory.deleteObjectPhysical(ProjectManager.getInstance().getCurrentProject(), new RepositoryObject(property));
    }

    private void checkMoveObjectFileExists(IProject project, ERepositoryObjectType type, String path, String name, String version) {
        IFile fileProperty;
        if ("".equals(path)) {
            fileProperty = project.getFile(new Path(ERepositoryObjectType.getFolderName(type) + "/" + name + "_" + version
                    + ".properties"));
        } else {
            fileProperty = project.getFile(new Path(ERepositoryObjectType.getFolderName(type) + "/" + path + "/" + name + "_"
                    + version + ".properties"));
        }

        assertTrue(fileProperty.exists());
    }

    /**
     * Test method for
     * {@link org.talend.repository.localprovider.model.LocalRepositoryFactory#createFolder(org.talend.core.model.general.Project)}
     * .
     * 
     * @throws CoreException
     * @throws PersistenceException
     * @throws LoginException
     */
    @Test
    public void testMoveFolder() throws PersistenceException, CoreException, LoginException {
        repositoryFactory.logOnProject(ProjectManager.getInstance().getCurrentProject());

        ProcessItem processItem = createTempProcessItem(repositoryFactory, "sourceFolder");
        String processId = processItem.getProperty().getId();
        List<IRepositoryViewObject> objects = repositoryFactory.getAllVersion(ProjectManager.getInstance().getCurrentProject(),
                processId, "sourceFolder", ERepositoryObjectType.PROCESS);

        if (objects.isEmpty()) {
            return;
        }

        final Folder createTargetFolder = repositoryFactory.createFolder(ProjectManager.getInstance().getCurrentProject(),
                ERepositoryObjectType.PROCESS, new Path(""), "targetFolder");
        assertNotNull(createTargetFolder);

        IPath sourcePath = new Path("sourceFolder");
        IPath targetPath = new Path(createTargetFolder.getLabel());

        assertNotNull(objects.get(0));
        repositoryFactory.moveFolder(ERepositoryObjectType.PROCESS, sourcePath, targetPath);
        IProject project = ResourceModelUtils.getProject(ProjectManager.getInstance().getCurrentProject());
        checkMoveObjectFileExists(project, ERepositoryObjectType.PROCESS, createTargetFolder.getLabel() + "/" + "sourceFolder",
                processItem.getProperty().getLabel(), processItem.getProperty().getVersion());

        Property property = repositoryFactory.getUptodateProperty(ProjectManager.getInstance().getCurrentProject(),
                processItem.getProperty());
        repositoryFactory.deleteObjectPhysical(ProjectManager.getInstance().getCurrentProject(), new RepositoryObject(property));
    }

    // /**
    // * Test method for
    // * {@link
    // org.talend.repository.localprovider.model.LocalRepositoryFactory#executeRepositoryWorkUnit(org.talend.repository.RepositoryWorkUnit)}
    // * .
    // */
    // @Test
    // public void testExecuteRepositoryWorkUnit() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for {@link org.talend.repository.localprovider.model.LocalRepositoryFactory#readProject(boolean)}.
    // */
    // @Test
    // public void testReadProjectBoolean() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.repository.localprovider.model.LocalRepositoryFactory#getObjectFromFolder(org.talend.core.model.general.Project,
    // org.talend.core.model.repository.ERepositoryObjectType, boolean, boolean[])}
    // * .
    // */
    // @Test
    // public void testGetObjectFromFolderProjectERepositoryObjectTypeBooleanBooleanArray() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.repository.localprovider.model.LocalRepositoryFactory#getObjectFromFolder(org.talend.core.model.general.Project,
    // org.talend.core.model.repository.ERepositoryObjectType, java.lang.String, boolean, boolean[])}
    // * .
    // */
    // @Test
    // public void testGetObjectFromFolderProjectERepositoryObjectTypeStringBooleanBooleanArray() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.repository.localprovider.model.LocalRepositoryFactory#addFolderMembers(org.talend.core.model.general.Project,
    // org.talend.core.model.repository.ERepositoryObjectType, org.talend.commons.utils.data.container.Container,
    // java.lang.Object, boolean, boolean[])}
    // * .
    // */
    // @Test
    // public void testAddFolderMembers() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.repository.localprovider.model.LocalRepositoryFactory#getMetadatasByFolder(org.talend.core.model.general.Project,
    // org.talend.core.model.repository.ERepositoryObjectType, org.eclipse.core.runtime.IPath)}
    // * .
    // */
    // @Test
    // public void testGetMetadatasByFolder() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for {@link
    // org.talend.repository.localprovider.model.LocalRepositoryFactory#LocalRepositoryFactory()}
    // * .
    // */
    // @Test
    // public void testLocalRepositoryFactory() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.repository.localprovider.model.LocalRepositoryFactory#saveProject(org.talend.core.model.general.Project)}
    // * .
    // */
    // @Test
    // public void testSaveProject() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.repository.localprovider.model.LocalRepositoryFactory#synchronizeRoutines(org.eclipse.core.resources.IProject)}
    // * .
    // */
    // @Test
    // public void testSynchronizeRoutines() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.repository.localprovider.model.LocalRepositoryFactory#synchronizeSqlpatterns(org.eclipse.core.resources.IProject)}
    // * .
    // */
    // @Test
    // public void testSynchronizeSqlpatterns() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for {@link org.talend.repository.localprovider.model.LocalRepositoryFactory#readProjects(boolean)}.
    // */
    // @Test
    // public void testReadProjects() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.repository.localprovider.model.LocalRepositoryFactory#createFolder(org.talend.core.model.general.Project,
    // org.talend.core.model.repository.ERepositoryObjectType, org.eclipse.core.runtime.IPath, java.lang.String)}
    // * .
    // */
    // @Test
    // public void testCreateFolderProjectERepositoryObjectTypeIPathString() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.repository.localprovider.model.LocalRepositoryFactory#createFolder(org.talend.core.model.general.Project,
    // org.talend.core.model.repository.ERepositoryObjectType, org.eclipse.core.runtime.IPath, java.lang.String,
    // boolean)}
    // * .
    // */
    // @Test
    // public void testCreateFolderProjectERepositoryObjectTypeIPathStringBoolean() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.repository.localprovider.model.LocalRepositoryFactory#isPathValid(org.talend.core.model.general.Project,
    // org.talend.core.model.repository.ERepositoryObjectType, org.eclipse.core.runtime.IPath, java.lang.String)}
    // * .
    // */
    // @Test
    // public void testIsPathValid() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.repository.localprovider.model.LocalRepositoryFactory#deleteFolder(org.talend.core.model.general.Project,
    // org.talend.core.model.repository.ERepositoryObjectType, org.eclipse.core.runtime.IPath)}
    // * .
    // */
    // @Test
    // public void testDeleteFolderProjectERepositoryObjectTypeIPath() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.repository.localprovider.model.LocalRepositoryFactory#deleteFolder(org.talend.core.model.general.Project,
    // org.talend.core.model.repository.ERepositoryObjectType, org.eclipse.core.runtime.IPath, boolean)}
    // * .
    // */
    // @Test
    // public void testDeleteFolderProjectERepositoryObjectTypeIPathBoolean() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.repository.localprovider.model.LocalRepositoryFactory#renameFolder(org.talend.core.model.repository.ERepositoryObjectType,
    // org.eclipse.core.runtime.IPath, java.lang.String)}
    // * .
    // */
    // @Test
    // public void testRenameFolder() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for {@link org.talend.repository.localprovider.model.LocalRepositoryFactory#getProcess2()}.
    // */
    // @Test
    // public void testGetProcess2() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.repository.localprovider.model.LocalRepositoryFactory#deleteObjectLogical(org.talend.core.model.general.Project,
    // org.talend.core.model.repository.IRepositoryViewObject)}
    // * .
    // */
    // @Test
    // public void testDeleteObjectLogical() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.repository.localprovider.model.LocalRepositoryFactory#deleteObjectPhysical(org.talend.core.model.general.Project,
    // org.talend.core.model.repository.IRepositoryViewObject)}
    // * .
    // */
    // @Test
    // public void testDeleteObjectPhysicalProjectIRepositoryViewObject() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.repository.localprovider.model.LocalRepositoryFactory#deleteObjectPhysical(org.talend.core.model.general.Project,
    // org.talend.core.model.repository.IRepositoryViewObject, java.lang.String)}
    // * .
    // */
    // @Test
    // public void testDeleteObjectPhysicalProjectIRepositoryViewObjectString() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.repository.localprovider.model.LocalRepositoryFactory#deleteObjectPhysical(org.talend.core.model.general.Project,
    // org.talend.core.model.repository.IRepositoryViewObject, java.lang.String, boolean)}
    // * .
    // */
    // @Test
    // public void testDeleteObjectPhysicalProjectIRepositoryViewObjectStringBoolean() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.repository.localprovider.model.LocalRepositoryFactory#restoreObject(org.talend.core.model.repository.IRepositoryViewObject,
    // org.eclipse.core.runtime.IPath)}
    // * .
    // */
    // @Test
    // public void testRestoreObject() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.repository.localprovider.model.LocalRepositoryFactory#lock(org.talend.core.model.properties.Item)}
    // * .
    // */
    // @Test
    // public void testLock() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.repository.localprovider.model.LocalRepositoryFactory#unlock(org.talend.core.model.properties.Item)}
    // * .
    // */
    // @Test
    // public void testUnlock() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for {@link
    // org.talend.repository.localprovider.model.LocalRepositoryFactory#getDocumentationStatus()}
    // * .
    // */
    // @Test
    // public void testGetDocumentationStatus() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for {@link org.talend.repository.localprovider.model.LocalRepositoryFactory#getTechnicalStatus()}.
    // */
    // @Test
    // public void testGetTechnicalStatus() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for {@link org.talend.repository.localprovider.model.LocalRepositoryFactory#getSpagoBiServer()}.
    // */
    // @Test
    // public void testGetSpagoBiServer() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.repository.localprovider.model.LocalRepositoryFactory#setDocumentationStatus(java.util.List)}.
    // */
    // @Test
    // public void testSetDocumentationStatus() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link org.talend.repository.localprovider.model.LocalRepositoryFactory#setTechnicalStatus(java.util.List)}.
    // */
    // @Test
    // public void testSetTechnicalStatus() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link org.talend.repository.localprovider.model.LocalRepositoryFactory#setSpagoBiServer(java.util.List)}.
    // */
    // @Test
    // public void testSetSpagoBiServer() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.repository.localprovider.model.LocalRepositoryFactory#setMigrationTasksDone(org.talend.core.model.general.Project,
    // java.util.List)}
    // * .
    // */
    // @Test
    // public void testSetMigrationTasksDone() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for {@link org.talend.repository.localprovider.model.LocalRepositoryFactory#isServerValid()}.
    // */
    // @Test
    // public void testIsServerValid() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.repository.localprovider.model.LocalRepositoryFactory#copy(org.talend.core.model.properties.Item,
    // org.eclipse.core.runtime.IPath)}
    // * .
    // */
    // @Test
    // public void testCopyItemIPath() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.repository.localprovider.model.LocalRepositoryFactory#copy(org.talend.core.model.properties.Item,
    // org.eclipse.core.runtime.IPath, boolean)}
    // * .
    // */
    // @Test
    // public void testCopyItemIPathBoolean() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.repository.localprovider.model.LocalRepositoryFactory#reload(org.talend.core.model.properties.Property)}
    // * .
    // */
    // @Test
    // public void testReloadProperty() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.repository.localprovider.model.LocalRepositoryFactory#reload(org.talend.core.model.properties.Property,
    // org.eclipse.core.resources.IFile)}
    // * .
    // */
    // @Test
    // public void testReloadPropertyIFile() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for {@link org.talend.repository.localprovider.model.LocalRepositoryFactory#doesLoggedUserExist()}.
    // */
    // @Test
    // public void testDoesLoggedUserExist() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for {@link org.talend.repository.localprovider.model.LocalRepositoryFactory#initialize()}.
    // */
    // @Test
    // public void testInitialize() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.repository.localprovider.model.LocalRepositoryFactory#getStatus(org.talend.core.model.properties.Item)}
    // * .
    // */
    // @Test
    // public void testGetStatus() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.repository.localprovider.model.LocalRepositoryFactory#getReferencedProjects(org.talend.core.model.general.Project)}
    // * .
    // */
    // @Test
    // public void testGetReferencedProjects() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link org.talend.repository.localprovider.model.LocalRepositoryFactory#hasChildren(java.lang.Object)}.
    // */
    // @Test
    // public void testHasChildren() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.repository.localprovider.model.LocalRepositoryFactory#updateItemsPath(org.talend.core.model.repository.ERepositoryObjectType,
    // org.eclipse.core.runtime.IPath)}
    // * .
    // */
    // @Test
    // public void testUpdateItemsPath() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.repository.localprovider.model.LocalRepositoryFactory#setAuthorByLogin(org.talend.core.model.properties.Item,
    // java.lang.String)}
    // * .
    // */
    // @Test
    // public void testSetAuthorByLogin() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link org.talend.repository.localprovider.model.LocalRepositoryFactory#unloadResources(java.lang.String)}.
    // */
    // @Test
    // public void testUnloadResourcesString() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for {@link org.talend.repository.localprovider.model.LocalRepositoryFactory#unloadResources()}.
    // */
    // @Test
    // public void testUnloadResources() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for {@link
    // org.talend.repository.localprovider.model.LocalRepositoryFactory#enableSandboxProject()}.
    // */
    // @Test
    // public void testEnableSandboxProject() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link org.talend.repository.localprovider.model.LocalRepositoryFactory#isLocalConnectionProvider()}.
    // */
    // @Test
    // public void testIsLocalConnectionProvider() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.repository.localprovider.model.LocalRepositoryFactory#executeMigrations(org.talend.core.model.general.Project,
    // boolean, org.eclipse.core.runtime.SubMonitor)}
    // * .
    // */
    // @Test
    // public void testExecuteMigrations() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link org.talend.repository.localprovider.model.LocalRepositoryFactory#getNavigatorViewDescription()}.
    // */
    // @Test
    // public void testGetNavigatorViewDescription() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for {@link org.talend.core.repository.model.AbstractEMFRepositoryFactory#getNextId()}.
    // */
    // @Test
    // public void testGetNextId() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.core.repository.model.AbstractEMFRepositoryFactory#getMetadata(org.talend.core.model.general.Project,
    // org.talend.core.model.repository.ERepositoryObjectType, boolean[])}
    // * .
    // */
    // @Test
    // public void testGetMetadata() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.core.repository.model.AbstractEMFRepositoryFactory#getRecycleBinItems(org.talend.core.model.general.Project,
    // boolean[])}
    // * .
    // */
    // @Test
    // public void testGetRecycleBinItems() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for {@link org.talend.core.repository.model.AbstractEMFRepositoryFactory#convert(java.util.List)}.
    // */
    // @Test
    // public void testConvert() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link org.talend.core.repository.model.AbstractEMFRepositoryFactory#addToHistory(java.lang.String,
    // org.talend.core.model.repository.ERepositoryObjectType, java.lang.String)}
    // * .
    // */
    // @Test
    // public void testAddToHistory() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.core.repository.model.AbstractEMFRepositoryFactory#getSerializable(org.talend.core.model.general.Project,
    // java.lang.String, boolean, boolean)}
    // * .
    // */
    // @Test
    // public void testGetSerializable() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.core.repository.model.AbstractEMFRepositoryFactory#getAllVersion(org.talend.core.model.general.Project,
    // java.lang.String, boolean)}
    // * .
    // */
    // @Test
    // public void testGetAllVersionProjectStringBoolean() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.core.repository.model.AbstractEMFRepositoryFactory#getAllVersion(org.talend.core.model.general.Project,
    // java.lang.String, java.lang.String, org.talend.core.model.repository.ERepositoryObjectType)}
    // * .
    // */
    // @Test
    // public void testGetAllVersionProjectStringStringERepositoryObjectType() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for {@link
    // * org.talend.core.repository.model.AbstractEMFRepositoryFactory#isNameAvailable(org.talend.core.model.general.
    // * Project, org.talend.core.model.properties.Item, java.lang.String,
    // * java.util.List<org.talend.core.model.repository.IRepositoryViewObject>[])}.
    // */
    // @Test
    // public void testIsNameAvailable() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.core.repository.model.AbstractEMFRepositoryFactory#copyFromResource(org.eclipse.emf.ecore.resource.Resource)}
    // * .
    // */
    // @Test
    // public void testCopyFromResourceResource() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.core.repository.model.AbstractEMFRepositoryFactory#copyFromResource(org.eclipse.emf.ecore.resource.Resource,
    // boolean)}
    // * .
    // */
    // @Test
    // public void testCopyFromResourceResourceBoolean() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for {@link org.talend.core.repository.model.AbstractEMFRepositoryFactory#createSystemRoutines()}.
    // */
    // @Test
    // public void testCreateSystemRoutines() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for {@link
    // org.talend.core.repository.model.AbstractEMFRepositoryFactory#createSystemSQLPatterns()}.
    // */
    // @Test
    // public void testCreateSystemSQLPatterns() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for {@link
    // org.talend.core.repository.model.AbstractEMFRepositoryFactory#getModulesNeededForJobs()}.
    // */
    // @Test
    // public void testGetModulesNeededForJobs() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.core.repository.model.AbstractEMFRepositoryFactory#getRoutineFromProject(org.talend.core.model.general.Project)}
    // * .
    // */
    // @Test
    // public void testGetRoutineFromProject() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.core.repository.model.AbstractEMFRepositoryFactory#getLastVersion(org.talend.core.model.general.Project,
    // java.lang.String)}
    // * .
    // */
    // @Test
    // public void testGetLastVersionProjectString() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.core.repository.model.AbstractEMFRepositoryFactory#getLastVersion(org.talend.core.model.general.Project,
    // java.lang.String, java.lang.String, org.talend.core.model.repository.ERepositoryObjectType)}
    // * .
    // */
    // @Test
    // public void testGetLastVersionProjectStringStringERepositoryObjectType() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.core.repository.model.AbstractEMFRepositoryFactory#computePropertyMaxInformationLevel(org.talend.core.model.properties.Property)}
    // * .
    // */
    // @Test
    // public void testComputePropertyMaxInformationLevel() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.core.repository.model.AbstractEMFRepositoryFactory#getFolderItem(org.talend.core.model.general.Project,
    // org.talend.core.model.repository.ERepositoryObjectType, org.eclipse.core.runtime.IPath)}
    // * .
    // */
    // @Test
    // public void testGetFolderItem() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.core.repository.model.AbstractEMFRepositoryFactory#getMetadataByFolder(org.talend.core.model.general.Project,
    // org.talend.core.model.repository.ERepositoryObjectType, org.eclipse.core.runtime.IPath)}
    // * .
    // */
    // @Test
    // public void testGetMetadataByFolder() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.core.repository.model.AbstractEMFRepositoryFactory#getTdqRepositoryViewObjects(org.talend.core.model.general.Project,
    // org.talend.core.model.repository.ERepositoryObjectType, java.lang.String, boolean[])}
    // * .
    // */
    // @Test
    // public void testGetTdqRepositoryViewObjects() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.core.repository.model.AbstractEMFRepositoryFactory#canLock(org.talend.core.model.properties.Item)}
    // * .
    // */
    // @Test
    // public void testCanLock() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.core.repository.model.AbstractEMFRepositoryFactory#canUnlock(org.talend.core.model.properties.Item)}
    // * .
    // */
    // @Test
    // public void testCanUnlock() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.core.repository.model.AbstractEMFRepositoryFactory#getRootContainerFromType(org.talend.core.model.general.Project,
    // org.talend.core.model.repository.ERepositoryObjectType)}
    // * .
    // */
    // @Test
    // public void testGetRootContainerFromType() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.core.repository.model.AbstractEMFRepositoryFactory#getLockInfo(org.talend.core.model.properties.Item)}
    // * .
    // */
    // @Test
    // public void testGetLockInfo() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for {@link org.talend.core.repository.model.AbstractRepositoryFactory#getButtons()}.
    // */
    // @Test
    // public void testGetButtons() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for {@link org.talend.core.repository.model.AbstractRepositoryFactory#getChoices()}.
    // */
    // @Test
    // public void testGetChoices() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for {@link org.talend.core.repository.model.AbstractRepositoryFactory#isAuthenticationNeeded()}.
    // */
    // @Test
    // public void testIsAuthenticationNeeded() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link org.talend.core.repository.model.AbstractRepositoryFactory#setAuthenticationNeeded(boolean)}.
    // */
    // @Test
    // public void testSetAuthenticationNeeded() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for {@link org.talend.core.repository.model.AbstractRepositoryFactory#getName()}.
    // */
    // @Test
    // public void testGetName() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for {@link org.talend.core.repository.model.AbstractRepositoryFactory#setName(java.lang.String)}.
    // */
    // @Test
    // public void testSetName() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for {@link org.talend.core.repository.model.AbstractRepositoryFactory#getId()}.
    // */
    // @Test
    // public void testGetId() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for {@link org.talend.core.repository.model.AbstractRepositoryFactory#setId(java.lang.String)}.
    // */
    // @Test
    // public void testSetId() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for {@link org.talend.core.repository.model.AbstractRepositoryFactory#getFields()}.
    // */
    // @Test
    // public void testGetFields() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for {@link org.talend.core.repository.model.AbstractRepositoryFactory#setFields(java.util.List)}.
    // */
    // @Test
    // public void testSetFields() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for {@link org.talend.core.repository.model.AbstractRepositoryFactory#getRepositoryContext()}.
    // */
    // @Test
    // public void testGetRepositoryContext() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for {@link org.talend.core.repository.model.AbstractRepositoryFactory#toString()}.
    // */
    // @Test
    // public void testToString() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.core.repository.model.AbstractRepositoryFactory#getMetadataConnectionsItem(org.talend.core.model.general.Project)}
    // * .
    // */
    // @Test
    // public void testGetMetadataConnectionsItem() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.core.repository.model.AbstractRepositoryFactory#getContextItem(org.talend.core.model.general.Project)}
    // * .
    // */
    // @Test
    // public void testGetContextItem() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for {@link org.talend.core.repository.model.AbstractRepositoryFactory#isDisplayToUser()}.
    // */
    // @Test
    // public void testIsDisplayToUser() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for {@link org.talend.core.repository.model.AbstractRepositoryFactory#setDisplayToUser(boolean)}.
    // */
    // @Test
    // public void testSetDisplayToUser() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.core.repository.model.AbstractRepositoryFactory#beforeLogon(org.talend.core.model.general.Project)}
    // * .
    // */
    // @Test
    // public void testBeforeLogonProject() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.core.repository.model.AbstractRepositoryFactory#beforeLogon(org.talend.core.model.general.Project,
    // long)}
    // * .
    // */
    // @Test
    // public void testBeforeLogonProjectLong() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link org.talend.core.repository.model.AbstractRepositoryFactory#isUserReadOnlyOnCurrentProject()}.
    // */
    // @Test
    // public void testIsUserReadOnlyOnCurrentProject() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for {@link org.talend.core.repository.model.AbstractRepositoryFactory#checkAvailability()}.
    // */
    // @Test
    // public void testCheckAvailability() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for {@link org.talend.core.repository.model.AbstractRepositoryFactory#logOffProject()}.
    // */
    // @Test
    // public void testLogOffProject() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for {@link org.talend.core.repository.model.AbstractRepositoryFactory#isLoggedOnProject()}.
    // */
    // @Test
    // public void testIsLoggedOnProject() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for {@link org.talend.core.repository.model.AbstractRepositoryFactory#setLoggedOnProject(boolean)}.
    // */
    // @Test
    // public void testSetLoggedOnProject() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // org.talend.core.repository.model.AbstractRepositoryFactory#addRepositoryWorkUnitListener(org.talend.core.model.repository.IRepositoryWorkUnitListener)}
    // * .
    // */
    // @Test
    // public void testAddRepositoryWorkUnitListener() {
    // fail("Not yet implemented");
    // }
    //
    // /**
    // * Test method for
    // * {@link org.talend.core.repository.model.AbstractRepositoryFactory#runRepositoryWorkUnitListeners()}.
    // */
    // @Test
    // public void testRunRepositoryWorkUnitListeners() {
    // fail("Not yet implemented");
    // }

}
