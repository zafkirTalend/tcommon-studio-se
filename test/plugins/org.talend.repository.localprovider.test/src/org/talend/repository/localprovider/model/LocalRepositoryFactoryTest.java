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
package org.talend.repository.localprovider.model;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.talend.commons.exception.BusinessException;
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
import org.talend.core.model.metadata.builder.connection.ConnectionFactory;
import org.talend.core.model.metadata.builder.connection.DatabaseConnection;
import org.talend.core.model.properties.DatabaseConnectionItem;
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

    private static Project originalProject;

    @BeforeClass
    public static void beforeAllTests() {
        Context ctx = CoreRuntimePlugin.getInstance().getContext();
        RepositoryContext repositoryContext = (RepositoryContext) ctx.getProperty(Context.REPOSITORY_CONTEXT_KEY);
        originalProject = repositoryContext.getProject();

        repositoryFactory = new LocalRepositoryFactory();
    }

    @AfterClass
    public static void afterAllTests() {
        repositoryFactory = null;

        Context ctx = CoreRuntimePlugin.getInstance().getContext();
        RepositoryContext repositoryContext = (RepositoryContext) ctx.getProperty(Context.REPOSITORY_CONTEXT_KEY);
        repositoryContext.setProject(originalProject);
    }

    @Before
    public void beforeTest() throws PersistenceException, CoreException, LoginException {
        createTempProject();
        Context ctx = CoreRuntimePlugin.getInstance().getContext();
        RepositoryContext repositoryContext = (RepositoryContext) ctx.getProperty(Context.REPOSITORY_CONTEXT_KEY);
        repositoryContext.setProject(sampleProject);
    }

    @After
    public void afterTest() throws Exception {
        // make sure there is nothing in both jobs / routines after do anything
        try {
            final List<IRepositoryViewObject> allJobs = repositoryFactory.getAll(sampleProject, ERepositoryObjectType.PROCESS,
                    true, true);
            assertNotNull(allJobs);
            Set<String> jobs = new HashSet<String>();
            for (IRepositoryViewObject object : allJobs) {
                jobs.add(object.getLabel());
            }
            if (!jobs.isEmpty()) {
                throw new Exception("job:" + jobs + " should have been deleted before");
            }
            assertTrue(allJobs.isEmpty());

            final List<IRepositoryViewObject> allRoutines = repositoryFactory.getAll(ProjectManager.getInstance()
                    .getCurrentProject(), ERepositoryObjectType.ROUTINES, true, true);
            assertNotNull(allRoutines);
            for (IRepositoryViewObject o : allRoutines) {
                RoutineItem ri = (RoutineItem) o.getProperty().getItem();
                if (!ri.isBuiltIn()) {
                    System.out.println("routine:" + o.getLabel() + " should have been deleted before");
                }
                assertTrue(ri.isBuiltIn()); // should only have system routines, not others
            }
        } catch (Exception e) {
            throw e;
        } finally {
            removeTempProject();
            ProjectManager.getInstance().getFolders(sampleProject.getEmfProject()).clear();
            sampleProject = null;
        }
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
            desc = workspace.newProjectDescription(technicalLabel);
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
            factory.createFolder(sampleProject, ERepositoryObjectType.PROCESS, new Path(""), path);
        }

        factory.create(sampleProject, processItem, new Path(path), false);

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
            factory.createFolder(sampleProject, ERepositoryObjectType.ROUTINES, new Path(""), path);
        }
        factory.create(sampleProject, routineItem, new Path(path), false);
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
        repositoryFactory.logOnProject(sampleProject);
        final Object folder = repositoryFactory.getFolder(sampleProject, ERepositoryObjectType.PROCESS);
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
        repositoryFactory.logOnProject(sampleProject);

        Property property = PropertiesFactory.eINSTANCE.createProperty();
        property.setAuthor(sampleProject.getAuthor());
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
        repositoryFactory.create(sampleProject, processItem, new Path(""), false);
        assertNotNull(processItem.eResource());

        IProject project = ResourceUtils.getProject(sampleProject.getTechnicalLabel());
        checkFileExists(project, ERepositoryObjectType.PROCESS, "", "myJob", VersionUtils.DEFAULT_VERSION);
        // delete the item to cleanup the workspace
        repositoryFactory.deleteObjectPhysical(sampleProject, new RepositoryObject(property));
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
        repositoryFactory.logOnProject(sampleProject);

        final Folder createFolder = repositoryFactory.createFolder(sampleProject, ERepositoryObjectType.PROCESS, new Path(""),
                "MyFolder");
        // check if the folder created is not null
        assertNotNull(createFolder);
        String folderName = new Path(ERepositoryObjectType.getFolderName(ERepositoryObjectType.PROCESS)).append("MyFolder")
                .toPortableString();
        // check that the folder exists in the project emf
        assertNotNull(repositoryFactory.getFolderHelper(sampleProject.getEmfProject()).getFolder(folderName));

        // check that the folder exists physically
        IFolder pFolder = ResourceUtils.getFolder(ResourceUtils.getProject(sampleProject.getTechnicalLabel()), folderName, false);
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
        repositoryFactory.logOnProject(sampleProject);

        testGetSerializableProcess(repositoryFactory, "");
        testGetSerializableProcess(repositoryFactory, "myProcessFolder");

        String folderName = new Path(ERepositoryObjectType.getFolderName(ERepositoryObjectType.PROCESS))
                .append("myProcessFolder").toPortableString();
        // check that the folder exists physically
        IFolder pFolder = ResourceUtils.getFolder(ResourceUtils.getProject(sampleProject.getTechnicalLabel()), folderName, false);
        assertTrue(pFolder.exists());
        pFolder.delete(true, new NullProgressMonitor());

        testGetSerializableRoutine(repositoryFactory, "");
        testGetSerializableRoutine(repositoryFactory, "myRoutineFolder");

        folderName = new Path(ERepositoryObjectType.getFolderName(ERepositoryObjectType.ROUTINES)).append("myRoutineFolder")
                .toPortableString();
        // check that the folder exists physically
        pFolder = ResourceUtils.getFolder(ResourceUtils.getProject(sampleProject.getTechnicalLabel()), folderName, false);
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
        fullFolder = getFullFolder(repositoryFactory, sampleProject, ERepositoryObjectType.ROUTINES, "");
        serializableFromFolder = repositoryFactory.getSerializableFromFolder(sampleProject, fullFolder, routineId,
                ERepositoryObjectType.ROUTINES, true, true, true, false);
        assertNotNull(serializableFromFolder);
        assertTrue(serializableFromFolder.size() == 1);
        rvo = serializableFromFolder.get(0);
        String content = new String(((RoutineItem) (rvo.getProperty().getItem())).getContent().getInnerContent());
        assertTrue(content.equals("myRoutineContent"));

        // test if logical delete (can retrieve or not with flag)
        repositoryFactory.deleteObjectLogical(sampleProject, rvo);
        serializableFromFolder = repositoryFactory.getSerializableFromFolder(sampleProject, fullFolder, routineId,
                ERepositoryObjectType.ROUTINES, true, true, true, false);
        assertNotNull(serializableFromFolder);
        assertTrue(serializableFromFolder.size() == 1);
        content = new String(((RoutineItem) (serializableFromFolder.get(0).getProperty().getItem())).getContent()
                .getInnerContent());
        assertTrue(content.equals("myRoutineContent"));

        serializableFromFolder = repositoryFactory.getSerializableFromFolder(sampleProject, fullFolder, routineId,
                ERepositoryObjectType.ROUTINES, true, true, false, false);
        assertNotNull(serializableFromFolder);
        assertTrue(serializableFromFolder.size() == 0);

        // test if physical delete
        repositoryFactory.deleteObjectPhysical(sampleProject, rvo);
        serializableFromFolder = repositoryFactory.getSerializableFromFolder(sampleProject, fullFolder, routineId,
                ERepositoryObjectType.ROUTINES, true, true, true, false);
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

        IProject project = ResourceModelUtils.getProject(sampleProject);
        XmiResourceManager xrm = new XmiResourceManager();
        Resource processItemResource = xrm.createItemResource(project, routineItem, new Path(path),
                ERepositoryObjectType.ROUTINES, false);
        Resource propertyResource = xrm.createPropertyResource(routineItem, processItemResource);

        propertyResource.getContents().add(routineItem.getProperty());
        propertyResource.getContents().add(routineItem.getState());
        propertyResource.getContents().add(routineItem);

        processItemResource.getContents().add(routineItem.getContent());
        xrm.saveResource(processItemResource);
        xrm.saveResource(propertyResource);
        assertNull(routineItem.getParent());
        // unload the resource to be really the same as created from navigator or even SVN project.
        xrm.unloadResources(routineItem.getProperty());
        serializableFromFolder = repositoryFactory.getSerializableFromFolder(sampleProject, fullFolder, "777",
                ERepositoryObjectType.ROUTINES, true, true, true, false);
        assertNotNull(serializableFromFolder);
        assertTrue(serializableFromFolder.size() == 1);
        FolderItem folderItem = repositoryFactory.getFolderItem(sampleProject, ERepositoryObjectType.ROUTINES, new Path(path));
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
        serializableFromFolder = repositoryFactory.getSerializableFromFolder(sampleProject, fullFolder, "777",
                ERepositoryObjectType.ROUTINES, true, true, true, false);
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
        Object fullFolder = getFullFolder(repositoryFactory, sampleProject, ERepositoryObjectType.PROCESS, path);
        List<IRepositoryViewObject> serializableFromFolder = repositoryFactory.getSerializableFromFolder(ProjectManager
                .getInstance().getCurrentProject(), fullFolder, jobId, ERepositoryObjectType.PROCESS, true, true, true, false);
        assertNotNull(serializableFromFolder);
        assertTrue(serializableFromFolder.size() == 1);
        IRepositoryViewObject rvo = serializableFromFolder.get(0);
        assertTrue(((NodeType) ((ProcessItem) (serializableFromFolder.get(0).getProperty().getItem())).getProcess().getNode()
                .get(0)).getComponentVersion().equals("0.1"));

        // test if logical delete (can retrieve or not with flag)
        repositoryFactory.deleteObjectLogical(sampleProject, rvo);
        serializableFromFolder = repositoryFactory.getSerializableFromFolder(sampleProject, fullFolder, jobId,
                ERepositoryObjectType.PROCESS, true, true, true, false);
        assertNotNull(serializableFromFolder);
        assertTrue(serializableFromFolder.size() == 1);
        assertTrue(((NodeType) ((ProcessItem) (serializableFromFolder.get(0).getProperty().getItem())).getProcess().getNode()
                .get(0)).getComponentVersion().equals("0.1"));

        serializableFromFolder = repositoryFactory.getSerializableFromFolder(sampleProject, fullFolder, jobId,
                ERepositoryObjectType.PROCESS, true, true, false, false);
        assertNotNull(serializableFromFolder);
        assertTrue(serializableFromFolder.size() == 0);

        // test if physical delete
        repositoryFactory.deleteObjectPhysical(sampleProject, rvo);
        serializableFromFolder = repositoryFactory.getSerializableFromFolder(sampleProject, fullFolder, jobId,
                ERepositoryObjectType.PROCESS, true, true, true, false);
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

        IProject project = ResourceModelUtils.getProject(sampleProject);
        XmiResourceManager xrm = new XmiResourceManager();
        Resource processItemResource = xrm.createItemResource(project, processItem, new Path(path),
                ERepositoryObjectType.PROCESS, false);
        Resource propertyResource = xrm.createPropertyResource(processItem, processItemResource);

        propertyResource.getContents().add(processItem.getProperty());
        propertyResource.getContents().add(processItem.getState());
        propertyResource.getContents().add(processItem);

        processItemResource.getContents().add(processItem.getProcess());
        xrm.saveResource(processItemResource);
        xrm.saveResource(propertyResource);
        assertNull(processItem.getParent());
        // unload the resource to be really the same as created from navigator or even SVN project.
        xrm.unloadResources(processItem.getProperty());
        serializableFromFolder = repositoryFactory.getSerializableFromFolder(sampleProject, fullFolder, "666",
                ERepositoryObjectType.PROCESS, true, true, true, false);
        assertNotNull(serializableFromFolder);
        assertTrue(serializableFromFolder.size() == 1);
        FolderItem folderItem = repositoryFactory.getFolderItem(sampleProject, ERepositoryObjectType.PROCESS, new Path(path));
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
        serializableFromFolder = repositoryFactory.getSerializableFromFolder(sampleProject, fullFolder, "666",
                ERepositoryObjectType.PROCESS, true, true, true, false);
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
            fullFolder = repositoryFactory.getFolder(project, itemType);
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
        repositoryFactory.logOnProject(sampleProject);

        Property property = PropertiesFactory.eINSTANCE.createProperty();
        property.setAuthor(sampleProject.getAuthor());
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
        repositoryFactory.create(sampleProject, processItem, new Path(""), false);
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
        repositoryFactory.logOnProject(sampleProject);

        final FolderHelper folderHelper = repositoryFactory.getFolderHelper(sampleProject.getEmfProject());
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
        repositoryFactory.logOnProject(sampleProject);

        Property property = PropertiesFactory.eINSTANCE.createProperty();
        property.setAuthor(sampleProject.getAuthor());
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
        repositoryFactory.create(sampleProject, processItem, new Path(""), false);
        assertEquals(property.getDisplayName(), "myJob");
        property.setDisplayName("myJob2");
        final Property uptodateProperty = repositoryFactory.getUptodateProperty(sampleProject, property);
        assertNotNull(uptodateProperty);
        assertNotNull(uptodateProperty.eResource());
        // item was not unloaded before, so value will be same as property
        // to review, not sure it's a good use for this function since it won't really force the update.. or
        // should rename it maybe?
        assertEquals(uptodateProperty.getDisplayName(), "myJob2");

        repositoryFactory.unloadUnlockedResources();
        final Property uptodateProperty2 = repositoryFactory.getUptodateProperty(sampleProject, property);
        assertNotNull(uptodateProperty2);
        assertNotNull(uptodateProperty2.eResource());
        // item has been unloaded, so will reload from file
        assertEquals(uptodateProperty2.getDisplayName(), "myJob");

        repositoryFactory.deleteObjectPhysical(sampleProject, new RepositoryObject(uptodateProperty));
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
        final org.talend.core.model.properties.Project oldEmfProject = sampleProject.getEmfProject();
        repositoryFactory.reloadProject(sampleProject);
        assertNotNull(sampleProject.getEmfProject());
        assertNotSame(oldEmfProject, sampleProject.getEmfProject());
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
        repositoryFactory.logOnProject(sampleProject);

        Property property = PropertiesFactory.eINSTANCE.createProperty();
        property.setAuthor(sampleProject.getAuthor());
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
        repositoryFactory.create(sampleProject, processItem, new Path(""), false);
        final List<IRepositoryViewObject> all = repositoryFactory
                .getAll(sampleProject, ERepositoryObjectType.PROCESS, true, true);
        assertNotNull(all);
        assertTrue(!all.isEmpty());

        repositoryFactory.deleteObjectPhysical(sampleProject, new RepositoryObject(property));
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
        repositoryFactory.logOnProject(sampleProject);

        Property property = PropertiesFactory.eINSTANCE.createProperty();
        property.setAuthor(sampleProject.getAuthor());
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
        property2.setAuthor(sampleProject.getAuthor());
        property2.setVersion(VersionUtils.DEFAULT_VERSION);
        property2.setStatusCode("");
        property2.setLabel("jobB");
        final String nextId2 = repositoryFactory.getNextId();
        property2.setId(nextId2);
        ProcessItem processItem2 = PropertiesFactory.eINSTANCE.createProcessItem();
        processItem2.setProperty(property2);
        ProcessType process2 = TalendFileFactory.eINSTANCE.createProcessType();
        processItem2.setProcess(process2);

        repositoryFactory.create(sampleProject, processItem, new Path(""), false);
        repositoryFactory.create(sampleProject, processItem2, new Path(""), false);

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

        IRepositoryViewObject object1 = repositoryFactory.getLastVersion(sampleProject, pItem.getProperty().getId());
        Property prop1 = object1.getProperty();
        pItem = (ProcessItem) prop1.getItem();
        IRepositoryViewObject object2 = repositoryFactory.getLastVersion(sampleProject, rItem.getProperty().getId());
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

        pItem = (ProcessItem) repositoryFactory.getLastVersion(sampleProject, pItem.getProperty().getId()).getProperty()
                .getItem();

        // test locked item (keep in memory)
        pItem.getState().setDeleted(false);
        pItem.getState().setLocked(true);
        repositoryFactory.unloadUnlockedResources();
        assertNotNull(pItem.getParent());
        assertTrue(((FolderItem) pItem.getParent()).getChildren().contains(pItem));
        assertNotNull(pItem.eResource());

        property = repositoryFactory.getUptodateProperty(sampleProject, property);
        repositoryFactory.deleteObjectPhysical(sampleProject, new RepositoryObject(property));
        property2 = repositoryFactory.getUptodateProperty(sampleProject, property2);
        repositoryFactory.deleteObjectPhysical(sampleProject, new RepositoryObject(property2));
        prop1 = repositoryFactory.getUptodateProperty(sampleProject, prop1);
        repositoryFactory.deleteObjectPhysical(sampleProject, new RepositoryObject(prop1));
        prop2 = repositoryFactory.getUptodateProperty(sampleProject, prop2);
        repositoryFactory.deleteObjectPhysical(sampleProject, new RepositoryObject(prop2));
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
        repositoryFactory.logOnProject(sampleProject);

        Property property = PropertiesFactory.eINSTANCE.createProperty();
        property.setAuthor(sampleProject.getAuthor());
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
        property2.setAuthor(sampleProject.getAuthor());
        property2.setVersion(VersionUtils.DEFAULT_VERSION);
        property2.setStatusCode("");
        property2.setLabel("jobB");
        final String nextId2 = repositoryFactory.getNextId();
        property2.setId(nextId2);
        ProcessItem processItem2 = PropertiesFactory.eINSTANCE.createProcessItem();
        processItem2.setProperty(property2);
        ProcessType process2 = TalendFileFactory.eINSTANCE.createProcessType();
        processItem2.setProcess(process2);

        repositoryFactory.create(sampleProject, processItem, new Path(""), false);
        repositoryFactory.create(sampleProject, processItem2, new Path(""), false);

        assertNotNull(processItem.eResource());
        assertNotNull(processItem2.eResource());
        repositoryFactory.unloadResources(property2);
        assertNotNull(processItem.eResource());
        assertNull(processItem2.eResource());

        property = repositoryFactory.getUptodateProperty(sampleProject, property);
        repositoryFactory.deleteObjectPhysical(sampleProject, new RepositoryObject(property));
        property2 = repositoryFactory.getUptodateProperty(sampleProject, property2);
        repositoryFactory.deleteObjectPhysical(sampleProject, new RepositoryObject(property2));
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
        repositoryFactory.logOnProject(sampleProject);

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
        repositoryFactory.logOnProject(sampleProject);

        testProcesspropagateFileName2(repositoryFactory, "");
        testProcesspropagateFileName2(repositoryFactory, "myFolder");

        testRoutinepropagateFileName2(repositoryFactory, "");
        testRoutinepropagateFileName2(repositoryFactory, "myFolder");
    }

    private void testProcesspropagateFileName(LocalRepositoryFactory factory, String path) throws PersistenceException {
        IProject project = ResourceModelUtils.getProject(sampleProject);
        ProcessItem processItem = createTempProcessItem(factory, path);

        String processId = processItem.getProperty().getId();
        FolderItem folderItem = factory.getFolderItem(sampleProject, ERepositoryObjectType.PROCESS, new Path(path));
        assertNotNull(processItem.getParent());
        assertTrue(processItem.getParent().equals(folderItem));

        // same name, same version, only change content:
        ((NodeType) processItem.getProcess().getNode().get(0)).setComponentName("toto");
        factory.save(sampleProject, processItem);
        factory.unloadUnlockedResources();
        processItem = (ProcessItem) factory.getLastVersion(sampleProject, processId).getProperty().getItem();
        assertTrue(((NodeType) (processItem).getProcess().getNode().get(0)).getComponentName().equals("toto"));
        folderItem = factory.getFolderItem(sampleProject, ERepositoryObjectType.PROCESS, new Path(path));
        assertNotNull(processItem.getParent());
        assertTrue(processItem.getParent().equals(folderItem));

        // test 1 item, 1 version, change label only
        processItem.getProperty().setLabel("myJob2");
        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob", "0.1");
        factory.save(sampleProject, processItem);
        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob2", "0.1");
        checkFileNotExists(project, ERepositoryObjectType.PROCESS, path, "myJob", "0.1");
        folderItem = factory.getFolderItem(sampleProject, ERepositoryObjectType.PROCESS, new Path(path));
        assertNotNull(processItem.getParent());
        assertTrue(processItem.getParent().equals(folderItem));

        // test 1 item, 1 version, change version only
        processItem.getProperty().setVersion("0.2");
        ((NodeType) processItem.getProcess().getNode().get(0)).setComponentVersion("0.2");
        factory.save(sampleProject, processItem);
        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob2", "0.1");
        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob2", "0.2");

        factory.unloadUnlockedResources();

        List<IRepositoryViewObject> objects = factory.getAllVersion(sampleProject, processId, false);
        for (IRepositoryViewObject object : objects) {
            assertTrue(object instanceof RepositoryObject);
            assertTrue(((NodeType) ((ProcessItem) object.getProperty().getItem()).getProcess().getNode().get(0))
                    .getComponentVersion().equals(object.getProperty().getVersion()));
            folderItem = factory.getFolderItem(sampleProject, ERepositoryObjectType.PROCESS, new Path(path));
            assertNotNull(object.getProperty().getItem().getParent());
            assertTrue(object.getProperty().getItem().getParent().equals(folderItem));
        }

        processItem = (ProcessItem) factory.getLastVersion(sampleProject, processId).getProperty().getItem();
        folderItem = factory.getFolderItem(sampleProject, ERepositoryObjectType.PROCESS, new Path(path));
        assertNotNull(processItem.getParent());
        assertTrue(processItem.getParent().equals(folderItem));

        // test 1 item, 2 version, change name only
        processItem.getProperty().setLabel("myJob3");
        factory.save(sampleProject, processItem);

        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob3", "0.1");
        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob3", "0.2");
        checkFileNotExists(project, ERepositoryObjectType.PROCESS, path, "myJob2", "0.1");
        checkFileNotExists(project, ERepositoryObjectType.PROCESS, path, "myJob2", "0.2");

        // test 1 item, 2 version, change version
        processItem.getProperty().setVersion("0.3");
        ((NodeType) processItem.getProcess().getNode().get(0)).setComponentVersion("0.3");
        factory.save(sampleProject, processItem);
        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob3", "0.1");
        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob3", "0.2");
        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob3", "0.3");

        factory.unloadUnlockedResources();

        objects = factory.getAllVersion(sampleProject, processId, false);
        for (IRepositoryViewObject object : objects) {
            assertTrue(object instanceof RepositoryObject);
            assertTrue(((NodeType) ((ProcessItem) object.getProperty().getItem()).getProcess().getNode().get(0))
                    .getComponentVersion().equals(object.getProperty().getVersion()));
            folderItem = factory.getFolderItem(sampleProject, ERepositoryObjectType.PROCESS, new Path(path));
            assertNotNull(object.getProperty().getItem().getParent());
            assertTrue(object.getProperty().getItem().getParent().equals(folderItem));
        }

        processItem = (ProcessItem) factory.getLastVersion(sampleProject, processId).getProperty().getItem();
        folderItem = factory.getFolderItem(sampleProject, ERepositoryObjectType.PROCESS, new Path(path));
        assertNotNull(processItem.getParent());
        assertTrue(processItem.getParent().equals(folderItem));

        // test 1 item, 3 version, change version and name
        processItem.getProperty().setVersion("1.0");
        processItem.getProperty().setLabel("myJob");
        ((NodeType) processItem.getProcess().getNode().get(0)).setComponentVersion("1.0");
        factory.save(sampleProject, processItem);
        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob", "0.1");
        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob", "0.2");
        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob", "0.3");
        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob", "1.0");
        checkFileNotExists(project, ERepositoryObjectType.PROCESS, path, "myJob3", "0.1");
        checkFileNotExists(project, ERepositoryObjectType.PROCESS, path, "myJob3", "0.2");
        checkFileNotExists(project, ERepositoryObjectType.PROCESS, path, "myJob3", "0.3");

        objects = factory.getAllVersion(sampleProject, processId, false);
        for (IRepositoryViewObject object : objects) {
            assertTrue(object instanceof RepositoryObject);
            assertTrue(((NodeType) ((ProcessItem) object.getProperty().getItem()).getProcess().getNode().get(0))
                    .getComponentVersion().equals(object.getProperty().getVersion()));
            folderItem = factory.getFolderItem(sampleProject, ERepositoryObjectType.PROCESS, new Path(path));
            assertNotNull(object.getProperty().getItem().getParent());
            assertTrue(object.getProperty().getItem().getParent().equals(folderItem));
        }

        factory.deleteObjectPhysical(sampleProject, factory.getLastVersion(sampleProject, processId));
    }

    private void testRoutinepropagateFileName(LocalRepositoryFactory factory, String path) throws PersistenceException {
        IProject project = ResourceModelUtils.getProject(sampleProject);
        RoutineItem routineItem = createTempRoutineItem(factory, path);
        FolderItem folderItem = factory.getFolderItem(sampleProject, ERepositoryObjectType.ROUTINES, new Path(path));
        assertNotNull(routineItem.getParent());
        assertTrue(routineItem.getParent().equals(folderItem));

        String routineId = routineItem.getProperty().getId();

        // same name, same version, only change content:
        routineItem.getContent().setInnerContent("myRoutineContent_0.1".getBytes());
        factory.save(sampleProject, routineItem);
        factory.unloadUnlockedResources();
        routineItem = (RoutineItem) factory.getLastVersion(sampleProject, routineId).getProperty().getItem();
        String content = new String(routineItem.getContent().getInnerContent());
        assertTrue(content.equals("myRoutineContent_0.1"));

        // test 1 item, 1 version, change label only
        routineItem.getProperty().setLabel("myRoutine2");
        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine", "0.1");
        factory.save(sampleProject, routineItem);
        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine2", "0.1");
        checkFileNotExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine", "0.1");

        // test 1 item, 1 version, change version only
        routineItem.getProperty().setVersion("0.2");
        routineItem.getContent().setInnerContent("myRoutineContent_0.2".getBytes());
        factory.save(sampleProject, routineItem);
        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine2", "0.1");
        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine2", "0.2");

        factory.unloadUnlockedResources();

        List<IRepositoryViewObject> objects = factory.getAllVersion(sampleProject, routineId, false);
        for (IRepositoryViewObject object : objects) {
            assertTrue(object instanceof RepositoryObject);
            content = new String(((RoutineItem) object.getProperty().getItem()).getContent().getInnerContent());
            assertTrue(content.equals("myRoutineContent_" + object.getProperty().getVersion()));
            folderItem = factory.getFolderItem(sampleProject, ERepositoryObjectType.ROUTINES, new Path(path));
            assertNotNull(object.getProperty().getItem().getParent());
            assertTrue(object.getProperty().getItem().getParent().equals(folderItem));
        }

        routineItem = (RoutineItem) factory.getLastVersion(sampleProject, routineId).getProperty().getItem();
        folderItem = factory.getFolderItem(sampleProject, ERepositoryObjectType.ROUTINES, new Path(path));
        assertNotNull(routineItem.getParent());
        assertTrue(routineItem.getParent().equals(folderItem));

        // test 1 item, 2 version, change name only
        routineItem.getProperty().setLabel("myRoutine3");
        factory.save(sampleProject, routineItem);

        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine3", "0.1");
        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine3", "0.2");
        checkFileNotExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine2", "0.1");
        checkFileNotExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine2", "0.2");

        // test 1 item, 2 version, change version
        routineItem.getProperty().setVersion("0.3");
        routineItem.getContent().setInnerContent("myRoutineContent_0.3".getBytes());
        factory.save(sampleProject, routineItem);

        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine3", "0.1");
        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine3", "0.2");
        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine3", "0.3");

        objects = factory.getAllVersion(sampleProject, routineId, false);
        for (IRepositoryViewObject object : objects) {
            assertTrue(object instanceof RepositoryObject);
            content = new String(((RoutineItem) object.getProperty().getItem()).getContent().getInnerContent());
            assertTrue(content.equals("myRoutineContent_" + object.getProperty().getVersion()));
            folderItem = factory.getFolderItem(sampleProject, ERepositoryObjectType.ROUTINES, new Path(path));
            assertNotNull(object.getProperty().getItem().getParent());
            assertTrue(object.getProperty().getItem().getParent().equals(folderItem));
        }

        routineItem = (RoutineItem) factory.getLastVersion(sampleProject, routineId).getProperty().getItem();
        folderItem = factory.getFolderItem(sampleProject, ERepositoryObjectType.ROUTINES, new Path(path));
        assertNotNull(routineItem.getParent());
        assertTrue(routineItem.getParent().equals(folderItem));

        // test 1 item, 3 version, change version and name
        routineItem.getProperty().setVersion("1.0");
        routineItem.getProperty().setLabel("myRoutine");
        routineItem.getContent().setInnerContent("myRoutineContent_1.0".getBytes());
        factory.save(sampleProject, routineItem);
        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine", "0.1");
        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine", "0.2");
        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine", "0.3");
        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine", "1.0");
        checkFileNotExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine3", "0.1");
        checkFileNotExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine3", "0.2");
        checkFileNotExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine3", "0.3");

        objects = factory.getAllVersion(sampleProject, routineId, false);
        for (IRepositoryViewObject object : objects) {
            assertTrue(object instanceof RepositoryObject);
            content = new String(((RoutineItem) object.getProperty().getItem()).getContent().getInnerContent());
            assertTrue(content.equals("myRoutineContent_" + object.getProperty().getVersion()));
            folderItem = factory.getFolderItem(sampleProject, ERepositoryObjectType.ROUTINES, new Path(path));
            assertNotNull(object.getProperty().getItem().getParent());
            assertTrue(object.getProperty().getItem().getParent().equals(folderItem));
        }

        factory.deleteObjectPhysical(sampleProject, factory.getLastVersion(sampleProject, routineId));
    }

    private void testProcesspropagateFileName2(LocalRepositoryFactory factory, String path) throws PersistenceException {
        IProject project = ResourceModelUtils.getProject(sampleProject);
        ProcessItem processItem = createTempProcessItem(factory, path);
        FolderItem folderItem = factory.getFolderItem(sampleProject, ERepositoryObjectType.PROCESS, new Path(path));
        assertNotNull(processItem.getParent());
        assertTrue(processItem.getParent().equals(folderItem));

        String processId = processItem.getProperty().getId();

        // test 1 item, 1 version, change label only
        processItem.getProperty().setLabel("myJob2");
        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob", "0.1");
        factory.save(sampleProject, processItem.getProperty());
        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob2", "0.1");
        checkFileNotExists(project, ERepositoryObjectType.PROCESS, path, "myJob", "0.1");

        // test 1 item, 1 version, change version only
        processItem.getProperty().setVersion("0.2");
        factory.save(sampleProject, processItem.getProperty());
        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob2", "0.1");
        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob2", "0.2");

        factory.unloadUnlockedResources();

        List<IRepositoryViewObject> objects = factory.getAllVersion(sampleProject, processId, false);
        assertTrue(objects.size() == 2);

        processItem = (ProcessItem) factory.getLastVersion(sampleProject, processId).getProperty().getItem();
        folderItem = factory.getFolderItem(sampleProject, ERepositoryObjectType.PROCESS, new Path(path));
        assertNotNull(processItem.getParent());
        assertTrue(processItem.getParent().equals(folderItem));

        // test 1 item, 2 version, change name only
        processItem.getProperty().setLabel("myJob3");
        factory.save(sampleProject, processItem.getProperty());

        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob3", "0.1");
        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob3", "0.2");
        checkFileNotExists(project, ERepositoryObjectType.PROCESS, path, "myJob2", "0.1");
        checkFileNotExists(project, ERepositoryObjectType.PROCESS, path, "myJob2", "0.2");

        // test 1 item, 2 version, change version
        processItem.getProperty().setVersion("0.3");
        factory.save(sampleProject, processItem.getProperty());
        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob3", "0.1");
        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob3", "0.2");
        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob3", "0.3");

        factory.unloadUnlockedResources();

        objects = factory.getAllVersion(sampleProject, processId, false);
        assertTrue(objects.size() == 3);

        processItem = (ProcessItem) factory.getLastVersion(sampleProject, processId).getProperty().getItem();
        folderItem = factory.getFolderItem(sampleProject, ERepositoryObjectType.PROCESS, new Path(path));
        assertNotNull(processItem.getParent());
        assertTrue(processItem.getParent().equals(folderItem));

        // test 1 item, 3 version, change version and name
        processItem.getProperty().setVersion("1.0");
        processItem.getProperty().setLabel("myJob");
        factory.save(sampleProject, processItem.getProperty());
        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob", "0.1");
        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob", "0.2");
        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob", "0.3");
        checkFileExists(project, ERepositoryObjectType.PROCESS, path, "myJob", "1.0");
        checkFileNotExists(project, ERepositoryObjectType.PROCESS, path, "myJob3", "0.1");
        checkFileNotExists(project, ERepositoryObjectType.PROCESS, path, "myJob3", "0.2");
        checkFileNotExists(project, ERepositoryObjectType.PROCESS, path, "myJob3", "0.3");

        objects = factory.getAllVersion(sampleProject, processId, false);
        assertTrue(objects.size() == 4);

        factory.deleteObjectPhysical(sampleProject, factory.getLastVersion(sampleProject, processId));
    }

    private void testRoutinepropagateFileName2(LocalRepositoryFactory factory, String path) throws PersistenceException {
        IProject project = ResourceModelUtils.getProject(sampleProject);
        RoutineItem routineItem = createTempRoutineItem(factory, path);
        FolderItem folderItem = factory.getFolderItem(sampleProject, ERepositoryObjectType.ROUTINES, new Path(path));
        assertNotNull(routineItem.getParent());
        assertTrue(routineItem.getParent().equals(folderItem));

        String routineId = routineItem.getProperty().getId();

        // test 1 item, 1 version, change label only
        routineItem.getProperty().setLabel("myRoutine2");
        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine", "0.1");
        factory.save(sampleProject, routineItem.getProperty());
        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine2", "0.1");
        checkFileNotExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine", "0.1");

        // test 1 item, 1 version, change version only
        routineItem.getProperty().setVersion("0.2");
        factory.save(sampleProject, routineItem.getProperty());
        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine2", "0.1");
        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine2", "0.2");

        factory.unloadUnlockedResources();

        List<IRepositoryViewObject> objects = factory.getAllVersion(sampleProject, routineId, false);
        assertTrue(objects.size() == 2);

        routineItem = (RoutineItem) factory.getLastVersion(sampleProject, routineId).getProperty().getItem();
        folderItem = factory.getFolderItem(sampleProject, ERepositoryObjectType.ROUTINES, new Path(path));
        assertNotNull(routineItem.getParent());
        assertTrue(routineItem.getParent().equals(folderItem));

        // test 1 item, 2 version, change name only
        routineItem.getProperty().setLabel("myRoutine3");
        factory.save(sampleProject, routineItem.getProperty());

        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine3", "0.1");
        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine3", "0.2");
        checkFileNotExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine2", "0.1");
        checkFileNotExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine2", "0.2");

        // test 1 item, 2 version, change version
        routineItem.getProperty().setVersion("0.3");
        factory.save(sampleProject, routineItem.getProperty());

        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine3", "0.1");
        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine3", "0.2");
        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine3", "0.3");

        objects = factory.getAllVersion(sampleProject, routineId, false);
        assertTrue(objects.size() == 3);

        routineItem = (RoutineItem) factory.getLastVersion(sampleProject, routineId).getProperty().getItem();
        folderItem = factory.getFolderItem(sampleProject, ERepositoryObjectType.ROUTINES, new Path(path));
        assertNotNull(routineItem.getParent());
        assertTrue(routineItem.getParent().equals(folderItem));

        // test 1 item, 3 version, change version and name
        routineItem.getProperty().setVersion("1.0");
        routineItem.getProperty().setLabel("myRoutine");
        factory.save(sampleProject, routineItem.getProperty());
        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine", "0.1");
        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine", "0.2");
        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine", "0.3");
        checkFileExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine", "1.0");
        checkFileNotExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine3", "0.1");
        checkFileNotExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine3", "0.2");
        checkFileNotExists(project, ERepositoryObjectType.ROUTINES, path, "myRoutine3", "0.3");

        objects = factory.getAllVersion(sampleProject, routineId, false);
        assertTrue(objects.size() == 4);

        factory.deleteObjectPhysical(sampleProject, factory.getLastVersion(sampleProject, routineId));
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
        repositoryFactory.logOnProject(sampleProject);

        ProcessItem processItem = createTempProcessItem(repositoryFactory, "");
        String processId = processItem.getProperty().getId();
        List<IRepositoryViewObject> objects = repositoryFactory.getAllVersion(sampleProject, processId, true);

        final Folder createFolder = repositoryFactory.createFolder(sampleProject, ERepositoryObjectType.PROCESS, new Path(""),
                "moveToThisFolder");
        assertNotNull(createFolder);

        IPath ip = new Path(createFolder.getLabel());

        assertNotNull(objects.get(0));
        repositoryFactory.moveObject(objects.get(0), ip);
        IProject project = ResourceModelUtils.getProject(sampleProject);
        checkMoveObjectFileExists(project, ERepositoryObjectType.PROCESS, createFolder.getLabel(), processItem.getProperty()
                .getLabel(), processItem.getProperty().getVersion());

        Property property = repositoryFactory.getUptodateProperty(sampleProject, processItem.getProperty());
        repositoryFactory.deleteObjectPhysical(sampleProject, new RepositoryObject(property));
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
        repositoryFactory.logOnProject(sampleProject);

        ProcessItem processItem = createTempProcessItem(repositoryFactory, "sourceFolder");
        String processId = processItem.getProperty().getId();
        List<IRepositoryViewObject> objects = repositoryFactory.getAllVersion(sampleProject, processId, "sourceFolder",
                ERepositoryObjectType.PROCESS);

        if (objects.isEmpty()) {
            return;
        }

        final Folder createTargetFolder = repositoryFactory.createFolder(sampleProject, ERepositoryObjectType.PROCESS, new Path(
                ""), "targetFolder");
        assertNotNull(createTargetFolder);

        IPath sourcePath = new Path("sourceFolder");
        IPath targetPath = new Path(createTargetFolder.getLabel());

        assertNotNull(objects.get(0));
        repositoryFactory.moveFolder(ERepositoryObjectType.PROCESS, sourcePath, targetPath);
        IProject project = ResourceModelUtils.getProject(sampleProject);
        checkMoveObjectFileExists(project, ERepositoryObjectType.PROCESS, createTargetFolder.getLabel() + "/" + "sourceFolder",
                processItem.getProperty().getLabel(), processItem.getProperty().getVersion());

        Property property = repositoryFactory.getUptodateProperty(sampleProject, processItem.getProperty());
        repositoryFactory.deleteObjectPhysical(sampleProject, new RepositoryObject(property));
    }

    /**
     * Test method for
     * {@link org.talend.repository.localprovider.model.LocalRepositoryFactory#deleteObjectPhysical(org.talend.core.model.general.Project, org.talend.core.model.repository.IRepositoryViewObject)}
     * .
     * 
     * @throws PersistenceException
     * @throws IOException
     * @throws BusinessException
     */
    @Test
    public void testDeleteObjectPhysical() throws PersistenceException, IOException, BusinessException {
        repositoryFactory.logOnProject(sampleProject);

        Property property = PropertiesFactory.eINSTANCE.createProperty();
        property.setAuthor(sampleProject.getAuthor());
        property.setVersion(VersionUtils.DEFAULT_VERSION);
        property.setLabel("myJob");
        property.setDisplayName("myJob");
        property.setStatusCode("");
        property.setId(repositoryFactory.getNextId());

        DatabaseConnectionItem conItem = PropertiesFactory.eINSTANCE.createDatabaseConnectionItem();
        conItem.setProperty(property);
        DatabaseConnection dbcon = ConnectionFactory.eINSTANCE.createDatabaseConnection();
        conItem.setConnection(dbcon);

        assertNull(conItem.eResource());
        repositoryFactory.create(sampleProject, conItem, new Path(""), false);
        assertNotNull(conItem.eResource());

        IProject project = ResourceUtils.getProject(sampleProject.getTechnicalLabel());
        checkFileExists(project, ERepositoryObjectType.METADATA_CONNECTIONS, "", "myJob", VersionUtils.DEFAULT_VERSION);

        IFile fileItem = project.getFile(new Path(ERepositoryObjectType.getFolderName(ERepositoryObjectType.METADATA_CONNECTIONS)
                + "/" + "myJob" + "_" + VersionUtils.DEFAULT_VERSION + ".item"));

        // unload resource
        URI uri = URI.createPlatformResourceURI(fileItem.getFullPath().toString(), false);
        Resource resource = repositoryFactory.xmiResourceManager.resourceSet.getResource(uri, false);
        resource.unload();

        // delete all the content in file(destroy the file)
        File file = new File(fileItem.getLocationURI());
        FileWriter fw = new FileWriter(file);
        fw.write("");
        fw.close();

        // load resource
        Resource resource2;
        try {
            resource2 = repositoryFactory.xmiResourceManager.resourceSet.getResource(uri, true);
            repositoryFactory.xmiResourceManager.resourceSet.getResources().add(resource2);
        } catch (Exception e) {
            resource2 = null;
        }

        // delete the item to cleanup the workspace
        repositoryFactory.deleteObjectPhysical(sampleProject, new RepositoryObject(property));

        // check File Not Exists
        checkFileNotExists(project, ERepositoryObjectType.METADATA_CONNECTIONS, "", "myJob", VersionUtils.DEFAULT_VERSION);

    }
}
