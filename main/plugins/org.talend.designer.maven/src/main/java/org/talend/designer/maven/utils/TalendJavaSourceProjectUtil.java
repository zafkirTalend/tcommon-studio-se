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

import org.apache.maven.model.Model;
import org.apache.maven.project.MavenProject;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.embedder.MavenModelManager;
import org.eclipse.m2e.core.internal.IMavenConstants;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.designer.maven.model.JavaSourceProjectConstants;
import org.talend.designer.maven.model.MavenConstants;
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
        if (javaProject.exists()) { // FIXME, so far, just delete it, and recreate .Java
            IFile pomFile = javaProject.getFile(IMavenConstants.POM_FILE_NAME);
            if (!pomFile.exists() || !javaProject.hasNature(IMavenConstants.NATURE_ID)) { // delete
                javaProject.close(monitor);
                javaProject.delete(true, true, monitor);
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
    @SuppressWarnings("nls")
    private static IProject create(IProgressMonitor monitor, IProject project) throws Exception {
        CreateMavenProject cmProject = new CreateMavenProject();

        // TODO, if for OEM, maybe need change something for groupId and name..
        cmProject.setGroupId("org.talend");
        cmProject.setArtifactId(project.getName());

        // FIXME, must be jar, if pom, won't create the classpath or such for jdt.
        cmProject.setPackaging(MavenConstants.PACKAGING_JAR);

        IProject sourceProject = cmProject.createSourceProject(monitor);

        return sourceProject;
    }

    @SuppressWarnings("restriction")
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
                        // TODO, change the default ".Java" to sepcial one.
                        model.setArtifactId("org.talend.source");

                        // TODO need find one to overwrite.
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
}
