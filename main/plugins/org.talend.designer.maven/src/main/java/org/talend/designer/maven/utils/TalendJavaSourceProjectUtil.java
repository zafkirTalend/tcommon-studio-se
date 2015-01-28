// ============================================================================
//
// Copyright (C) 2006-2015 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.designer.maven.utils;

import java.util.Properties;

import org.apache.maven.model.Model;
import org.apache.maven.project.MavenProject;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.embedder.MavenModelManager;
import org.eclipse.m2e.core.internal.IMavenConstants;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.designer.maven.model.JavaSourceProjectConstants;
import org.talend.designer.maven.model.MavenConstants;
import org.talend.designer.maven.model.MavenSystemFolders;
import org.talend.designer.maven.project.CreateMavenProject;

/**
 * created by ggu on 23 Jan 2015 Detailled comment
 *
 */
public final class TalendJavaSourceProjectUtil {

    @SuppressWarnings("restriction")
    public static IProject initJavaProject(IProgressMonitor monitor) throws Exception {
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();

        IProject javaProject = root.getProject(JavaSourceProjectConstants.PROJECT_NAME);
        boolean recreate = false;
        if (javaProject.exists()) {
            IFile pomFile = javaProject.getFile(IMavenConstants.POM_FILE_NAME);
            // if no pom or maven nature, will re-create pure Maven project.
            if (!pomFile.exists() || !javaProject.hasNature(IMavenConstants.NATURE_ID)) {
                javaProject.close(monitor);
                javaProject.delete(true, true, monitor); // delete
                recreate = true;
            }
        } else {
            recreate = true;
        }
        if (recreate) {
            javaProject = create(monitor, javaProject);
            covertJavaProjectToPom(monitor, javaProject);
        }

        javaProject.open(IProject.BACKGROUND_REFRESH, monitor);

        return javaProject;
    }

    /**
     * 
     * create maven project with jdt nature.
     */
    private static IProject create(IProgressMonitor monitor, IProject project) throws Exception {
        CreateMavenProject cmProject = new CreateMavenProject() {

            @SuppressWarnings("nls")
            @Override
            protected Model getModel() {
                Model model = super.getModel();
                Properties p = new Properties();
                // TODO, need change the default compiler version(1.5)? or try maven-compiler-plugin?
                p.put("maven.compiler.source", "1.6");
                p.put("maven.compiler.target", "1.6");
                model.setProperties(p);

                return model;
            }

            @Override
            protected void afterCreate(IProgressMonitor m, IProject p) throws Exception {
                super.afterCreate(m, p);

                covertJavaProjectToPom(m, p);
                changeClasspath(m, p);
            }

        };

        // The groupId and artifactId are temp, will change it after create project.
        cmProject.setGroupId(project.getName());
        cmProject.setArtifactId(project.getName());

        // Must be jar, if pom, won't create the classpath or such for jdt.
        cmProject.setPackaging(MavenConstants.PACKAGING_JAR);

        IProject sourceProject = cmProject.createSourceProject(monitor);

        return sourceProject;
    }

    @SuppressWarnings({ "restriction", "nls" })
    private static void covertJavaProjectToPom(IProgressMonitor monitor, IProject project) {
        IFile pomFile = project.getFile(IMavenConstants.POM_FILE_NAME);
        if (pomFile.exists()) {
            try {
                MavenModelManager mavenModelManager = MavenPlugin.getMavenModelManager();
                MavenProject mavenProject = mavenModelManager.readMavenProject(pomFile, monitor);
                if (mavenProject != null) {
                    Model model = mavenProject.getOriginalModel();
                    // if not pom, change to pom
                    if (!MavenConstants.PACKAGING_POM.equals(model.getPackaging())) {
                        model.setPackaging(MavenConstants.PACKAGING_POM);
                        // TODO, change the default ".Java" to sepcial one. if OEM, maybe need change too.
                        model.setGroupId("org.talend");
                        model.setArtifactId("org.talend.source");

                        /*
                         * need find one way to do overwrite.
                         */
                        // IModelManager modelManager = StructuredModelManager.getModelManager();
                        // IStructuredModel sModel = modelManager.getModelForRead(pomFile);
                        // IDOMModel domModel = (IDOMModel)
                        // modelManager.getModelForEdit(sModel.getStructuredDocument());
                        // ElementValueProvider privider = new ElementValueProvider(PomEdits.ARTIFACT_ID);
                        // Element el = privider.get(domModel.getDocument());
                        // PomEdits.setText(el, model.getArtifactId());
                        // sModel.save();

                        pomFile.delete(true, monitor);
                        mavenModelManager.createMavenModel(pomFile, model);
                    }
                }
            } catch (CoreException e) {
                ExceptionHandler.process(e);
            }

        }
    }

    private static void changeClasspath(IProgressMonitor monitor, IProject project) {
        try {
            IJavaProject javaProject = JavaCore.create(project);
            IClasspathEntry[] rawClasspathEntries = javaProject.getRawClasspath();
            boolean changed = false;

            for (int index = 0; index < rawClasspathEntries.length; index++) {
                IClasspathEntry entry = rawClasspathEntries[index];

                IClasspathEntry newEntry = null;
                if (entry.getEntryKind() == IClasspathEntry.CPE_SOURCE) {
                    IPath path = entry.getPath();
                    if (project.getFullPath().isPrefixOf(path)) {
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
