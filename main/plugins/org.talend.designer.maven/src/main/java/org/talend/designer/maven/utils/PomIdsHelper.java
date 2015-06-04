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
import org.talend.core.model.process.JobInfo;
import org.talend.core.model.properties.Property;
import org.talend.core.model.utils.JavaResourcesHelper;
import org.talend.designer.maven.model.TalendMavenConstants;
import org.talend.repository.ProjectManager;

/**
 * DOC ggu class global comment. Detailled comment
 */
public class PomIdsHelper {

    /*
     * FIXME, keep the fixing group id for poms.
     * 
     * if false, Will be some problems for the assembly with dependency/module set. so set true first.
     */
    public static final boolean FLAG_FIXING_GROUP_ID = true;

    /*
     * FIXME, keep the fixing artifact id for poms.
     * 
     * if false, Will be some problems for the assembly with module set for routines and job jars. so set true first.
     */
    public static final boolean FLAG_FIXING_ATIFACT_ID = true;

    /*
     * add the project details in version part. if true, need remove the version part to build jar with module set for
     * job jars.
     */
    public static final boolean FLAG_VERSION_WITH_CLASSIFIER = true;

    /*
     * If maven way, the final name should be like "<jobName>-<jobVersion>.zip". if true for special name, will be like
     * "<jobName>_<jobVersion>.zip"
     */
    public static final boolean FLAG_SPECIAL_FINAL_NAME = true;

    /*
     * if not fixing group or artifact, and the special version with classifier.
     */
    public static final boolean FLAG_ROUTINES_OVERWRITE_ALWAYS = !FLAG_FIXING_GROUP_ID || !FLAG_FIXING_ATIFACT_ID
            || FLAG_VERSION_WITH_CLASSIFIER;

    /**
     * @return "org.talend.master.<ProjectName>", like "org.talend.master.test".
     * 
     * If projectName is null, will return default one "org.talend.master" only.
     * 
     * always depend on current project.
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
     * @return "code.<ProjectName>", like "code.Test".
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
                return TalendMavenConstants.DEFAULT_CODE + '.' + technicalLabel;
            }
        }
        return TalendMavenConstants.DEFAULT_CODE_PROJECT_ARTIFACT_ID;
    }

    /**
     * @return "<version>-<ProjectName>", like "6.0.0-TESTABC".
     * 
     * if project is null, will return version only, like "6.0.0".
     * 
     * always depend on current project.
     */
    public static String getProjectVersion() {
        String defaultMavenVersion = PomUtil.getDefaultMavenVersion();
        if (FLAG_VERSION_WITH_CLASSIFIER) {// add project name in version
            final Project currentProject = ProjectManager.getInstance().getCurrentProject();
            if (currentProject != null) {
                String technicalLabel = currentProject.getTechnicalLabel();
                return defaultMavenVersion + '-' + technicalLabel;
            }
        }
        return defaultMavenVersion;
    }

    /**
     * @return "org.talend.code.<projectName>", like "org.talend.code.test".
     * 
     * if project is null, will return "org.talend.code".
     * 
     * always depend on current project.
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
     * @return "<project>.routines", like "TEST.routines"
     * 
     * if project is null, will return "routines".
     * 
     * always depend on current project.
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
     * @return "<version>-<ProjectName>", like "6.0.0-TESTABC".
     * 
     * if project is null, will return version only, like "6.0.0".
     * 
     * always depend on current project.
     */
    public static String getRoutinesVersion() {
        String defaultMavenVersion = PomUtil.getDefaultMavenVersion();
        if (FLAG_VERSION_WITH_CLASSIFIER) {// add project name in version
            final Project currentProject = ProjectManager.getInstance().getCurrentProject();
            if (currentProject != null) {
                String technicalLabel = currentProject.getTechnicalLabel();
                return defaultMavenVersion + '-' + technicalLabel;
            }
        }
        return defaultMavenVersion;
    }

    /**
     * @return "org.talend.job.<name>", like "org.talend.job.test"
     */
    public static String getJobGroupId(String name) {
        if (!FLAG_FIXING_GROUP_ID) {
            if (name != null) {
                return JavaResourcesHelper.getGroupName(TalendMavenConstants.DEFAULT_JOB + '.' + name);
            }
        }
        return JavaResourcesHelper.getGroupName(TalendMavenConstants.DEFAULT_JOB);
        // return TalendMavenConstants.DEFAULT_JOB_GROUP_ID;
    }

    /**
     * @return "org.talend.job.<projectName>".
     */
    public static String getJobGroupId(Property property) {
        if (!FLAG_FIXING_GROUP_ID) {
            if (property != null) {
                ProjectManager pManager = ProjectManager.getInstance();
                Project currentProject = pManager.getCurrentProject();
                if (currentProject != null) {
                    return getJobGroupId(currentProject.getTechnicalLabel());
                }
            }
        }
        return getJobGroupId((String) null);
        // return TalendMavenConstants.DEFAULT_JOB_GROUP_ID;
    }

    /**
     * @return "<projectName>.<jobName>".
     */
    public static String getJobArtifactId(Property property) {
        if (property != null) {
            ProjectManager pManager = ProjectManager.getInstance();
            Project currentProject = pManager.getCurrentProject();
            String artifactId = JavaResourcesHelper.escapeFileName(property.getLabel());

            if (!FLAG_FIXING_ATIFACT_ID) { // add project name for artifact
                if (currentProject != null) {
                    artifactId = currentProject.getTechnicalLabel() + '.' + artifactId;
                }
            } // else { //just use the name of label.
            return artifactId;
        }
        return null;
    }

    public static String getJobArtifactId(JobInfo jobInfo) {
        if (jobInfo != null) {
            ProjectManager pManager = ProjectManager.getInstance();
            Project currentProject = pManager.getCurrentProject();
            String artifactId = JavaResourcesHelper.escapeFileName(jobInfo.getJobName());

            if (!FLAG_FIXING_ATIFACT_ID) { // add project name for artifact
                if (currentProject != null) {
                    artifactId = currentProject.getTechnicalLabel() + '.' + artifactId;
                }
            } // else { //just use the name of label.
            return artifactId;
        }
        return null;
    }

    /**
     * @return "<jobVersion>-<projectName>".
     */
    public static String getJobVersion(Property property) {
        if (property != null) {
            ProjectManager pManager = ProjectManager.getInstance();
            Project currentProject = pManager.getCurrentProject();
            String version = property.getVersion();

            if (FLAG_VERSION_WITH_CLASSIFIER) { // add project name for version
                if (currentProject != null) {
                    version = version + '-' + currentProject.getTechnicalLabel();
                }
            } // else { //just use the job version
            return version;
        }
        return null;
    }

    public static String getJobVersion(JobInfo jobInfo) {
        if (jobInfo != null) {
            ProjectManager pManager = ProjectManager.getInstance();
            Project currentProject = pManager.getCurrentProject();
            String version = jobInfo.getJobVersion();

            if (FLAG_VERSION_WITH_CLASSIFIER) { // add project name for version
                if (currentProject != null) {
                    version = version + '-' + currentProject.getTechnicalLabel();
                }
            } // else { //just use the job version
            return version;
        }
        return null;
    }

}
