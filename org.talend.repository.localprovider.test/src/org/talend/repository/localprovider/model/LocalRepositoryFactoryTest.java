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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Test;
import org.talend.commons.exception.LoginException;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.utils.VersionUtils;
import org.talend.commons.utils.data.container.RootContainer;
import org.talend.core.context.Context;
import org.talend.core.context.RepositoryContext;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.model.general.ConnectionBean;
import org.talend.core.model.general.Project;
import org.talend.core.model.general.TalendNature;
import org.talend.core.model.properties.FolderItem;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.properties.PropertiesFactory;
import org.talend.core.model.properties.Property;
import org.talend.core.model.properties.User;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.Folder;
import org.talend.core.model.repository.IRepositoryViewObject;
import org.talend.core.runtime.CoreRuntimePlugin;
import org.talend.designer.core.model.utils.emf.talendfile.ProcessType;
import org.talend.designer.core.model.utils.emf.talendfile.TalendFileFactory;
import org.talend.repository.model.FolderHelper;
import org.talend.repository.model.ProxyRepositoryFactory;
import org.talend.repository.model.ResourceModelUtils;
import org.talend.repository.utils.XmiResourceManager;

/**
 * DOC talend class global comment. Detailled comment
 */
public class LocalRepositoryFactoryTest {

    private Project sampleProject;

    private void createTempProject() throws CoreException, PersistenceException {
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
                desc = prj.getDescription();
            } else {
                desc = workspace.newProjectDescription(projectInfor.getLabel());
            }
            desc.setNatureIds(new String[] { TalendNature.ID });
            desc.setComment(projectInfor.getDescription());

            if (!prj.exists()) {
                prj.create(desc, null);
            }
            prj.open(IResource.BACKGROUND_REFRESH, null);
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

        RepositoryContext repositoryContext = new RepositoryContext();
        repositoryContext.setUser(sampleProject.getAuthor());
        repositoryContext.setProject(sampleProject);
        ConnectionBean connection = ConnectionBean.getDefaultConnectionBean();
        connection.setUser(sampleProject.getAuthor().getLogin());
        repositoryContext.setFields(connection.getDynamicFields());
        CoreRuntimePlugin.getInstance().getContext().putProperty(Context.REPOSITORY_CONTEXT_KEY, repositoryContext);
        LocalRepositoryFactory repositoryFactory = new LocalRepositoryFactory();
        ProxyRepositoryFactory.getInstance().setRepositoryFactoryFromProvider(repositoryFactory);
    }

    private void removeTempProject() throws PersistenceException, CoreException {
        final IProject project = ResourceModelUtils.getProject(sampleProject);
        project.delete(true, null);

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
        LocalRepositoryFactory repositoryFactory = new LocalRepositoryFactory();
        Project project = new Project();
        project.setLabel("testauto");
        project.setDescription("no desc");
        project.setLanguage(ECodeLanguage.JAVA);
        User user = PropertiesFactory.eINSTANCE.createUser();
        user.setLogin("testauto@talend.com");
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
        createTempProject();
        LocalRepositoryFactory repositoryFactory = new LocalRepositoryFactory();
        final Project[] readProject = repositoryFactory.readProject();
        assertTrue(readProject != null && readProject.length > 0);
        removeTempProject();

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
        LocalRepositoryFactory repositoryFactory = new LocalRepositoryFactory();
        createTempProject();
        assertTrue(sampleProject.getEmfProject().getFolders().isEmpty());
        repositoryFactory.logOnProject(sampleProject);
        assertTrue(!sampleProject.getEmfProject().getFolders().isEmpty());
        removeTempProject();
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
        LocalRepositoryFactory repositoryFactory = new LocalRepositoryFactory();
        createTempProject();
        repositoryFactory.logOnProject(sampleProject);
        final Object folder = repositoryFactory.getFolder(sampleProject, ERepositoryObjectType.PROCESS);
        assertNotNull(folder);
        removeTempProject();
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
        LocalRepositoryFactory repositoryFactory = new LocalRepositoryFactory();
        createTempProject();
        repositoryFactory.createUser(sampleProject);
        Resource projectResource = sampleProject.getEmfProject().eResource();
        assertTrue(projectResource.getContents().contains(sampleProject.getAuthor()));
        removeTempProject();
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
        LocalRepositoryFactory repositoryFactory = new LocalRepositoryFactory();
        createTempProject();
        Property property = PropertiesFactory.eINSTANCE.createProperty();
        property.setAuthor(sampleProject.getAuthor());
        property.setVersion(VersionUtils.DEFAULT_VERSION);
        property.setStatusCode("");
        property.setId(repositoryFactory.getNextId());
        ProcessItem processItem = PropertiesFactory.eINSTANCE.createProcessItem();
        processItem.setProperty(property);
        ProcessType process = TalendFileFactory.eINSTANCE.createProcessType();
        processItem.setProcess(process);
        repositoryFactory.createUser(sampleProject);
        repositoryFactory.logOnProject(sampleProject);
        assertNull(processItem.eResource());
        repositoryFactory.create(sampleProject, processItem, new Path(""), false);
        assertNotNull(processItem.eResource());
        removeTempProject();
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
        LocalRepositoryFactory repositoryFactory = new LocalRepositoryFactory();
        createTempProject();
        final Folder createFolder = repositoryFactory.createFolder(sampleProject, ERepositoryObjectType.PROCESS, new Path(""),
                "MyFolder");
        assertNotNull(createFolder);
        removeTempProject();
    }

    /**
     * Test method for {@link org.talend.repository.localprovider.model.LocalRepositoryFactory#getResourceManager()}.
     */
    @Test
    public void testGetResourceManager() {
        LocalRepositoryFactory repositoryFactory = new LocalRepositoryFactory();
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
        LocalRepositoryFactory repositoryFactory = new LocalRepositoryFactory();
        createTempProject();
        Property property = PropertiesFactory.eINSTANCE.createProperty();
        property.setAuthor(sampleProject.getAuthor());
        property.setVersion(VersionUtils.DEFAULT_VERSION);
        property.setStatusCode("");
        final String nextId = repositoryFactory.getNextId();
        property.setId(nextId);
        ProcessItem processItem = PropertiesFactory.eINSTANCE.createProcessItem();
        processItem.setProperty(property);
        ProcessType process = TalendFileFactory.eINSTANCE.createProcessType();
        processItem.setProcess(process);
        repositoryFactory.createUser(sampleProject);
        repositoryFactory.logOnProject(sampleProject);
        repositoryFactory.create(sampleProject, processItem, new Path(""), false);
        final Object fullFolder = getFullFolder(repositoryFactory, sampleProject, ERepositoryObjectType.PROCESS, "");

        final List<IRepositoryViewObject> serializableFromFolder = repositoryFactory.getSerializableFromFolder(sampleProject,
                fullFolder, nextId, ERepositoryObjectType.PROCESS, true, true, true, true);
        assertNotNull(serializableFromFolder);
        assertTrue(!serializableFromFolder.isEmpty());
        removeTempProject();
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
                if (folder == null) {
                    fullFolder = ResourceModelUtils.getProject(project).getFolder(new Path(path));
                } else {
                    fullFolder = repositoryFactory.getFolderHelper(project.getEmfProject()).getFolder(
                            ((FolderItem) folder).getProperty().getLabel() + "/" + path); //$NON-NLS-1$
                }
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
        LocalRepositoryFactory repositoryFactory = new LocalRepositoryFactory();
        createTempProject();
        Property property = PropertiesFactory.eINSTANCE.createProperty();
        property.setAuthor(sampleProject.getAuthor());
        property.setVersion(VersionUtils.DEFAULT_VERSION);
        property.setStatusCode("");
        final String nextId = repositoryFactory.getNextId();
        property.setId(nextId);
        ProcessItem processItem = PropertiesFactory.eINSTANCE.createProcessItem();
        processItem.setProperty(property);
        ProcessType process = TalendFileFactory.eINSTANCE.createProcessType();
        processItem.setProcess(process);
        repositoryFactory.createUser(sampleProject);
        repositoryFactory.logOnProject(sampleProject);
        repositoryFactory.create(sampleProject, processItem, new Path(""), false);
        final RootContainer<Object, Object> objectFromFolder = repositoryFactory.getObjectFromFolder(sampleProject,
                ERepositoryObjectType.PROCESS, true);
        assertNotNull(objectFromFolder);
        removeTempProject();
    }

    /**
     * Test method for
     * {@link org.talend.repository.localprovider.model.LocalRepositoryFactory#getFolderHelper(org.talend.core.model.properties.Project)}
     * .
     * 
     * @throws CoreException
     * @throws PersistenceException
     */
    @Test
    public void testGetFolderHelper() throws PersistenceException, CoreException {
        createTempProject();
        LocalRepositoryFactory repositoryFactory = new LocalRepositoryFactory();
        final FolderHelper folderHelper = repositoryFactory.getFolderHelper(sampleProject.getEmfProject());
        assertNotNull(folderHelper);
        removeTempProject();
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
    public void testGetUptodateProperty() throws PersistenceException, CoreException, LoginException {
        LocalRepositoryFactory repositoryFactory = new LocalRepositoryFactory();
        createTempProject();
        Property property = PropertiesFactory.eINSTANCE.createProperty();
        property.setAuthor(sampleProject.getAuthor());
        property.setVersion(VersionUtils.DEFAULT_VERSION);
        property.setStatusCode("");
        final String nextId = repositoryFactory.getNextId();
        property.setId(nextId);
        ProcessItem processItem = PropertiesFactory.eINSTANCE.createProcessItem();
        processItem.setProperty(property);
        ProcessType process = TalendFileFactory.eINSTANCE.createProcessType();
        processItem.setProcess(process);
        repositoryFactory.createUser(sampleProject);
        repositoryFactory.logOnProject(sampleProject);
        repositoryFactory.create(sampleProject, processItem, new Path(""), false);
        final Property uptodateProperty = repositoryFactory.getUptodateProperty(sampleProject, property);
        assertNotNull(uptodateProperty);
        removeTempProject();
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
        LocalRepositoryFactory repositoryFactory = new LocalRepositoryFactory();
        createTempProject();
        final org.talend.core.model.properties.Project oldEmfProject = sampleProject.getEmfProject();
        repositoryFactory.reloadProject(sampleProject);
        assertNotNull(sampleProject.getEmfProject());
        assertNotSame(oldEmfProject, sampleProject.getEmfProject());

        removeTempProject();
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
        LocalRepositoryFactory repositoryFactory = new LocalRepositoryFactory();
        createTempProject();
        Property property = PropertiesFactory.eINSTANCE.createProperty();
        property.setAuthor(sampleProject.getAuthor());
        property.setVersion(VersionUtils.DEFAULT_VERSION);
        property.setStatusCode("");
        final String nextId = repositoryFactory.getNextId();
        property.setId(nextId);
        ProcessItem processItem = PropertiesFactory.eINSTANCE.createProcessItem();
        processItem.setProperty(property);
        ProcessType process = TalendFileFactory.eINSTANCE.createProcessType();
        processItem.setProcess(process);
        repositoryFactory.createUser(sampleProject);
        repositoryFactory.logOnProject(sampleProject);
        repositoryFactory.create(sampleProject, processItem, new Path(""), false);
        final List<IRepositoryViewObject> all = repositoryFactory
                .getAll(sampleProject, ERepositoryObjectType.PROCESS, true, true);
        assertNotNull(all);
        assertTrue(!all.isEmpty());

        removeTempProject();
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
        LocalRepositoryFactory repositoryFactory = new LocalRepositoryFactory();
        createTempProject();
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

        repositoryFactory.createUser(sampleProject);
        repositoryFactory.logOnProject(sampleProject);
        repositoryFactory.create(sampleProject, processItem, new Path(""), false);
        repositoryFactory.create(sampleProject, processItem2, new Path(""), false);

        assertNotNull(processItem.eResource());
        assertNotNull(processItem2.eResource());
        ProxyRepositoryFactory.getInstance().setFullLogonFinished(true);
        repositoryFactory.unloadUnlockedResources();
        assertNull(processItem.eResource());
        assertNull(processItem2.eResource());
        removeTempProject();
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
        LocalRepositoryFactory repositoryFactory = new LocalRepositoryFactory();
        createTempProject();
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

        repositoryFactory.createUser(sampleProject);
        repositoryFactory.logOnProject(sampleProject);
        repositoryFactory.create(sampleProject, processItem, new Path(""), false);
        repositoryFactory.create(sampleProject, processItem2, new Path(""), false);

        assertNotNull(processItem.eResource());
        assertNotNull(processItem2.eResource());
        repositoryFactory.unloadResources(property2);
        assertNotNull(processItem.eResource());
        assertNull(processItem2.eResource());
        removeTempProject();
    }

    /**
     * Test method for {@link org.talend.repository.localprovider.model.LocalRepositoryFactory#getInstance()}.
     */
    @Test
    public void testGetInstance() {
        final LocalRepositoryFactory instance = LocalRepositoryFactory.getInstance();
        assertTrue(instance != null);
    }

}
