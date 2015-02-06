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
package org.talend.designer.maven.project;

import java.util.Properties;

import org.apache.maven.model.Model;
import org.apache.maven.project.MavenProject;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.embedder.MavenModelManager;
import org.eclipse.m2e.core.project.ProjectImportConfiguration;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.core.model.utils.JavaResourcesHelper;
import org.talend.designer.maven.model.MavenConstants;
import org.talend.designer.maven.model.MavenSystemFolders;
import org.talend.designer.maven.model.ProjectSystemFolder;
import org.talend.designer.maven.model.TalendMavenContants;
import org.talend.designer.maven.utils.PomManager;

/**
 * created by ggu on 22 Jan 2015 Detailled comment
 *
 */
public class CreateMavenCodeProject extends CreateMaven {

    private IProject project;

    public CreateMavenCodeProject(IProject project) {
        super();
        Assert.isNotNull(project);
        this.project = project;
    }

    public IProject getProject() {
        return this.project;
    }

    @SuppressWarnings("nls")
    @Override
    protected Model createModel() {
        // The groupId and artifactId are temp, will change it after create project.
        setGroupId(project.getName());
        setArtifactId(project.getName());
        // Must be jar, if pom, won't create the classpath or such for jdt.
        setPackaging(MavenConstants.PACKAGING_JAR);

        Model model = super.createModel();
        Properties p = new Properties();

        /**
         * TODO, need change the default compiler version(1.5)? or try maven-compiler-plugin?
         * 
         * same version as jet compile, @see TalendJetEmitter.getBatchCompilerCmd
         */
        p.put("maven.compiler.source", TalendMavenContants.DEFAULT_JDK_VERSION);
        p.put("maven.compiler.target", TalendMavenContants.DEFAULT_JDK_VERSION);
        model.setProperties(p);

        return model;
    }

    /**
     * 
     * By default, it's current workspace.
     * 
     */
    protected IPath getBaseLocation() {
        return ResourcesPlugin.getWorkspace().getRoot().getLocation();
    }

    /**
     * 
     * By default, create the all maven folders.
     * 
     */
    protected String[] getFolders() {
        ProjectSystemFolder[] mavenDirectories = MavenSystemFolders.ALL_DIRS;

        String[] directories = new String[mavenDirectories.length];
        for (int i = 0; i < directories.length; i++) {
            directories[i] = mavenDirectories[i].getPath();
        }

        return directories;
    }

    /**
     * 
     * can do something before create operation.
     */
    protected void beforeCreate(IProgressMonitor monitor, IResource res) throws Exception {
        // nothing to do
    }

    /**
     * 
     * after create operation, can do something, like add some natures.
     */
    protected void afterCreate(IProgressMonitor monitor, IResource res) throws Exception {
        IProject p = res.getProject();
        covertJavaProjectToPom(monitor, p);
        changeClasspath(monitor, p);
    }

    @Override
    public void create(IProgressMonitor monitor) throws Exception {
        IProgressMonitor pMoniter = monitor;
        if (monitor == null) {
            pMoniter = new NullProgressMonitor();
        }
        IProgressMonitor subMonitor = new SubProgressMonitor(pMoniter, 100);

        final Model model = createModel();
        final ProjectImportConfiguration importConfiguration = new ProjectImportConfiguration();
        final IProject p = importConfiguration.getProject(ResourcesPlugin.getWorkspace().getRoot(), model);
        final IPath location = getBaseLocation();
        final String[] folders = getFolders();

        beforeCreate(subMonitor, p);
        subMonitor.worked(10);

        MavenPlugin.getProjectConfigurationManager().createSimpleProject(p, location.append(p.getName()), model, folders,
                importConfiguration, subMonitor);
        subMonitor.worked(80);

        afterCreate(subMonitor, p);
        subMonitor.done();

        // update the project
        this.project = p;
        return;
    }

    private void covertJavaProjectToPom(IProgressMonitor monitor, IProject p) {
        IFile pomFile = p.getFile(MavenConstants.POM_FILE_NAME);
        if (pomFile.exists()) {
            try {
                MavenModelManager mavenModelManager = MavenPlugin.getMavenModelManager();
                MavenProject mavenProject = mavenModelManager.readMavenProject(pomFile, monitor);
                if (mavenProject != null) {
                    Model model = mavenProject.getOriginalModel();
                    // if not pom, change to pom
                    if (!MavenConstants.PACKAGING_POM.equals(model.getPackaging())) {
                        model.setPackaging(MavenConstants.PACKAGING_POM);
                        // TalendMavenContants.DEFAULT_GROUP_ID
                        model.setGroupId(JavaResourcesHelper.getGroupName(null));
                        // TalendMavenContants.DEFAULT_CODE_PROJECT_ARTIFACT_ID,
                        model.setArtifactId(JavaResourcesHelper.getGroupName("sources")); //$NON-NLS-1$

                        PomManager.savePom(monitor, model, pomFile);
                    }
                }
            } catch (Exception e) {
                ExceptionHandler.process(e);
            }

        }
    }

    private void changeClasspath(IProgressMonitor monitor, IProject p) {
        try {
            IJavaProject javaProject = JavaCore.create(p);
            IClasspathEntry[] rawClasspathEntries = javaProject.getRawClasspath();
            boolean changed = false;

            for (int index = 0; index < rawClasspathEntries.length; index++) {
                IClasspathEntry entry = rawClasspathEntries[index];

                IClasspathEntry newEntry = null;
                if (entry.getEntryKind() == IClasspathEntry.CPE_SOURCE) {
                    IPath path = entry.getPath();
                    if (p.getFullPath().isPrefixOf(path)) {
                        path = path.removeFirstSegments(1);
                    }

                    // src/main/resources, in order to removing the 'excluding="**"'.
                    if (MavenSystemFolders.RESOURCES.getPath().equals(path.toString())) {
                        newEntry = JavaCore.newSourceEntry(entry.getPath(), new IPath[0], new IPath[0], //
                                entry.getOutputLocation(), entry.getExtraAttributes());
                    }

                } else if (entry.getEntryKind() == IClasspathEntry.CPE_CONTAINER) {
                    // remove the special version of jre in container.
                    IPath defaultJREContainerPath = JavaRuntime.newDefaultJREContainerPath();
                    if (defaultJREContainerPath.isPrefixOf(entry.getPath())) {
                        // JavaRuntime.getDefaultJREContainerEntry(); //missing properties
                        // newEntry = JavaCore.newContainerEntry(defaultJREContainerPath, entry.getAccessRules(),
                        // entry.getExtraAttributes(), entry.isExported());
                    }
                }
                if (newEntry != null) {
                    rawClasspathEntries[index] = newEntry;
                    changed = true;
                }

            }
            if (changed) {
                javaProject.setRawClasspath(rawClasspathEntries, monitor);
            }
        } catch (CoreException e) {
            ExceptionHandler.process(e);
        }
    }

}
