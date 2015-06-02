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

import org.talend.core.model.general.Project;
import org.talend.core.model.utils.JavaResourcesHelper;
import org.talend.designer.maven.model.TalendMavenConstants;
import org.talend.repository.ProjectManager;

/**
 * DOC ggu class global comment. Detailled comment
 */
public class PomIdsHelper {

    /**
     * something like "org.talend.code.<ProjectName>".
     * 
     * for example, project name is Test, return "org.talend.code.test".
     * 
     * If projectName is null, will return default one "org.talend.code" only.
     */
    public static String getDefaultGroupId() {
        final Project currentProject = ProjectManager.getInstance().getCurrentProject();
        if (currentProject != null) {
            String technicalLabel = currentProject.getTechnicalLabel();
            return JavaResourcesHelper.getGroupName(TalendMavenConstants.DEFAULT_CODE + '.' + technicalLabel);
        }

        return JavaResourcesHelper.getGroupName(TalendMavenConstants.DEFAULT_CODE);
    }

    /**
     * something like "code.<ProjectName>".
     * 
     * for example, project name is Test, return "code.Test".
     * 
     * If projectName is null, will return default one "code.Master" only.
     */
    public static String getDefaultProjectArtifactId() {
        final Project currentProject = ProjectManager.getInstance().getCurrentProject();
        if (currentProject != null) {
            String technicalLabel = currentProject.getTechnicalLabel();
            return TalendMavenConstants.DEFAULT_CODE + '.' + technicalLabel;
        }
        return TalendMavenConstants.DEFAULT_CODE_PROJECT_ARTIFACT_ID;
    }

    /**
     * something like "org.talend.job.<ProjectName>".
     * 
     * for example, project name is Test, return "org.talend.job.Test".
     * 
     * If projectName is null, will return default one "org.talend.job" only.
     */
    public static String getJobGroupId(String projectName) {
        if (projectName != null) {
            return JavaResourcesHelper.getGroupName(TalendMavenConstants.DEFAULT_JOB + '.' + projectName);
        }
        return TalendMavenConstants.DEFAULT_JOB_GROUP_ID;
    }

    public static String getDefaultJobGroupId() {
        final Project currentProject = ProjectManager.getInstance().getCurrentProject();
        return getJobGroupId(currentProject != null ? currentProject.getTechnicalLabel() : null);
    }

}
