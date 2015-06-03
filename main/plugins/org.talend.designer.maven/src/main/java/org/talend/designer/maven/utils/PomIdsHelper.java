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
package org.talend.designer.maven.utils;

import org.talend.commons.utils.generation.JavaUtils;
import org.talend.core.model.general.Project;
import org.talend.core.model.properties.Property;
import org.talend.core.model.utils.JavaResourcesHelper;
import org.talend.designer.maven.model.TalendMavenConstants;
import org.talend.repository.ProjectManager;

/**
 * DOC ggu class global comment. Detailled comment
 */
public class PomIdsHelper {

    /*
     * FIXME, keep the fixing group id for poms, if false, need check more for the assembly for dependency set/module
     * set.
     */
    private static final boolean FLAG_FIXING_GROUP_ID = true;

    /*
     * FIXME, keep the fixing artifact id for poms, if false, need check more for the assembly for module set for
     * routines and job jars.
     */
    private static final boolean FLAG_FIXING_ATIFACT_ID = true;

    /**
     * return something like "org.talend.master.<ProjectName>".
     * 
     * for example, project name is Test, return "org.talend.master.test".
     * 
     * If projectName is null, will return default one "org.talend.master" only.
     */
    public static String getProjectGroupId() {
        if (!FLAG_FIXING_GROUP_ID) {
            final Project currentProject = ProjectManager.getInstance().getCurrentProject();
            if (currentProject != null) {
                String technicalLabel = currentProject.getTechnicalLabel();
                return JavaResourcesHelper.getGroupName(TalendMavenConstants.DEFAULT_MASTER + '.' + technicalLabel);
            }
        }
        return JavaResourcesHelper.getGroupName(TalendMavenConstants.DEFAULT_MASTER);
        // return TalendMavenConstants.DEFAULT_CODE_PROJECT_GROUP_ID;
    }

    /**
     * return something like "code.<ProjectName>".
     * 
     * for example, project name is Test, return "code.Test".
     * 
     * If projectName is null, will return default one "code.Master" only.
     * 
     * always depend on current project.
     */
    public static String getProjectArtifactId() {
        if (!FLAG_FIXING_ATIFACT_ID) {// add project name for artifact
            final Project currentProject = ProjectManager.getInstance().getCurrentProject();
            if (currentProject != null) {
                String technicalLabel = currentProject.getTechnicalLabel();
                return technicalLabel + '.' + TalendMavenConstants.DEFAULT_CODE;
            }
        }
        return TalendMavenConstants.DEFAULT_CODE_PROJECT_ARTIFACT_ID;
    }

    /**
     * always depend on current project.
     * 
     * return "org.talend.code.<projectName>"
     */
    public static String getRoutineGroupId() {
        if (!FLAG_FIXING_GROUP_ID) {
            final Project currentProject = ProjectManager.getInstance().getCurrentProject();
            if (currentProject != null) {
                String technicalLabel = currentProject.getTechnicalLabel();
                return JavaResourcesHelper.getGroupName(TalendMavenConstants.DEFAULT_CODE + '.' + technicalLabel);
            }
        }
        return JavaResourcesHelper.getGroupName(TalendMavenConstants.DEFAULT_CODE);
        // return TalendMavenConstants.DEFAULT_ROUTINES_GROUP_ID;
    }

    /**
     * always depend on current project.
     * 
     * return "<project>.routines"
     */
    public static String getRoutinesArtifactId() {
        if (!FLAG_FIXING_ATIFACT_ID) { // add project name for artifact
            final Project currentProject = ProjectManager.getInstance().getCurrentProject();
            if (currentProject != null) {
                String technicalLabel = currentProject.getTechnicalLabel();
                return technicalLabel + '.' + JavaUtils.ROUTINE_JAR_NAME;
            }
        }
        return TalendMavenConstants.DEFAULT_ROUTINES_ARTIFACT_ID;
    }

    /**
     * return something like "org.talend.job.<projectName>".
     */
    public static String getJobGroupId(String name) {
        if (!FLAG_FIXING_GROUP_ID) {
            return JavaResourcesHelper.getGroupName(TalendMavenConstants.DEFAULT_JOB + '.' + name);
        }
        return JavaResourcesHelper.getGroupName(TalendMavenConstants.DEFAULT_JOB);
        // return TalendMavenConstants.DEFAULT_JOB_GROUP_ID;
    }

    /**
     * something like org.talend.job.<projectName>[.<refProjectName>].
     */
    public static String getJobGroupId(Property property) {
        if (!FLAG_FIXING_GROUP_ID) {
            if (property != null) {
                ProjectManager pManager = ProjectManager.getInstance();
                Project currentProject = pManager.getCurrentProject();
                if (!pManager.isInCurrentMainProject(property)) {
                    org.talend.core.model.properties.Project project = pManager.getProject(property);
                    if (project != null) {
                        return getJobGroupId(currentProject.getTechnicalLabel() + '.' + project.getTechnicalLabel());
                    }
                }
                if (currentProject != null) {
                    return getJobGroupId(currentProject.getTechnicalLabel());
                }
            }
        }
        return getJobGroupId((String) null);
        // return TalendMavenConstants.DEFAULT_JOB_GROUP_ID;
    }

    /**
     * 
     * return "<projectName>[.<refProjectName>].<jobName>".
     */
    public static String getJobArtifactId(Property property) {
        if (property != null) {
            ProjectManager pManager = ProjectManager.getInstance();
            Project currentProject = pManager.getCurrentProject();
            String artifactId = JavaResourcesHelper.escapeFileName(property.getLabel());

            if (!FLAG_FIXING_ATIFACT_ID) { // add project name for artifact
                if (pManager.isInCurrentMainProject(property)) {
                    if (currentProject != null) {
                        artifactId = currentProject.getTechnicalLabel() + '.' + artifactId;
                    }
                } else {
                    org.talend.core.model.properties.Project project = pManager.getProject(property);
                    if (project != null) {
                        artifactId = currentProject.getTechnicalLabel() + '.' + project.getTechnicalLabel() + '.' + artifactId;
                    }
                }
            } // else { //just use the name of label.
            return artifactId;
        }
        return null;
    }

}
