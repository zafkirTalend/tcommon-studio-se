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
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.m2e.core.internal.IMavenConstants;
import org.talend.commons.utils.generation.JavaUtils;
import org.talend.core.model.general.Project;
import org.talend.core.model.utils.JavaResourcesHelper;
import org.talend.designer.maven.model.MavenConstants;
import org.talend.designer.maven.model.TalendMavenContants;
import org.talend.designer.maven.project.CreateMavenCodeProject;
import org.talend.repository.ProjectManager;

/**
 * created by ggu on 23 Jan 2015 Detailled comment
 *
 */
public final class TalendCodeProjectUtil {

    /**
     * must be same maven-dependency-plugin in job pom.
     * 
     * later, if false, need remove this flag maybe.
     */
    public static boolean stripVersion = true;

    @SuppressWarnings("restriction")
    public static IProject initCodeProject(IProgressMonitor monitor) throws Exception {
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();

        IProject codeProject = root.getProject(TalendMavenContants.PROJECT_NAME);
        boolean recreate = false;
        if (codeProject.exists()) {
            IFile pomFile = codeProject.getFile(MavenConstants.POM_FILE_NAME);
            // if no pom or maven nature, will re-create pure Maven project.
            if (!pomFile.exists() || !codeProject.hasNature(IMavenConstants.NATURE_ID)) {
                codeProject.close(monitor);
                codeProject.delete(true, true, monitor); // delete
                recreate = true;
            }
        } else {
            recreate = true;
        }
        if (recreate) {
            CreateMavenCodeProject createProject = new CreateMavenCodeProject(codeProject);
            createProject.create(monitor);
            codeProject = createProject.getProject();
        }

        codeProject.open(IProject.BACKGROUND_REFRESH, monitor);

        return codeProject;
    }

    /**
     * 
     * just unify for routines with template.
     */
    public static Model getRoutinesTempalteModel() {
        Model routinesModel = new Model();

        routinesModel.setGroupId(getCurProjectGroup());
        routinesModel.setVersion(JavaUtils.ROUTINE_JAR_DEFAULT_VERSION);
        routinesModel.setArtifactId(getRoutinesArtifactId());
        return routinesModel;
    }

    private static String getRoutinesArtifactId() {
        String artifact = TalendMavenContants.DEFAULT_ROUTINES_ARTIFACT_ID;
        if (TalendCodeProjectUtil.stripVersion) { // in order to keep with version for jar always.
            artifact = JavaResourcesHelper.getJobJarName(TalendMavenContants.DEFAULT_ROUTINES_ARTIFACT_ID,
                    JavaUtils.ROUTINE_JAR_DEFAULT_VERSION);
        }
        return artifact;
    }

    public static boolean isRoutinesArtifact(String routineArtifact) {
        if (TalendMavenContants.DEFAULT_ROUTINES_ARTIFACT_ID.equals(routineArtifact)
                || getRoutinesArtifactId().equals(routineArtifact)) {
            return true;
        }
        return false;
    }

    /**
     * 
     * something like org.talend.demo.
     */
    public static String getCurProjectGroup() {
        final Project currentProject = ProjectManager.getInstance().getCurrentProject();
        return JavaResourcesHelper.getGroupName(currentProject.getTechnicalLabel());
    }

    public static String getCompileLevel() {
        return TalendMavenContants.DEFAULT_JDK_VERSION;
    }
    // public static IMarker[] getMavenMarks(IFile file) throws CoreException {
    // IMarker[] findMarkers = file.findMarkers(IMavenConstants.MARKER_CONFIGURATION_ID, true, IResource.DEPTH_ONE);
    // return findMarkers;
    // }

}
