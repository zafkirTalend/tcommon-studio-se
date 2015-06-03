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
package org.talend.designer.maven.template;

import java.util.Map;

import org.talend.designer.maven.utils.PomUtil;

/**
 * DOC ggu class global comment. Detailled comment
 */
public enum ETalendMavenVariables {
    ProductVersion,

    ProjectGroupId,
    ProjectArtifactId,
    ProjectVersion,

    ProjectName,
    ProjectId,
    ProjectBranch,

    RoutinesGroupId,
    RoutinesArtifactId,
    RoutinesVersion,

    JobGroupId,
    JobArtifactId,
    JobVersion,

    JobId,
    JobName,
    JobType,
    JobPath,
    JobPackage,
    JobDate,
    JobContext,
    JobStat,
    JobClass,
    JobApplyContextToChildren,
    JobBatClasspath,
    JobShClasspath,
    JobScriptAddition,

    //
    ;

    public String getName() {
        return name();
    }

    public String getExpression() {
        return '@' + getName() + '@';
    }

    public static String replaceVariables(String originalContent, Map<ETalendMavenVariables, String> variablesValuesMap) {
        if (originalContent == null || originalContent.trim().length() == 0 || variablesValuesMap.isEmpty()) {
            return originalContent;
        }
        String content = originalContent;
        for (ETalendMavenVariables var : variablesValuesMap.keySet()) {
            content = PomUtil.replaceVariable(content, var.getExpression(), variablesValuesMap.get(var));
        }
        return content;
    }
}
