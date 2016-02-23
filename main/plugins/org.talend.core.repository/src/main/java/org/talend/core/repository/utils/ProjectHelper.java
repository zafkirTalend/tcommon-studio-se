// ============================================================================
//
// Copyright (C) 2006-2016 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.repository.utils;

import java.util.regex.Pattern;

import org.talend.core.language.ECodeLanguage;
import org.talend.core.model.general.Project;
import org.talend.core.model.properties.User;
import org.talend.repository.model.RepositoryConstants;

/**
 * ggu class global comment. Detailled comment
 */
public class ProjectHelper {

    public static Project createProject(String projectName, String projectDescription, String projectLanguage,
            String projectAuthor, String projectAuthorPass) {
        return createProject(projectName, projectDescription, projectLanguage, projectAuthor, projectAuthorPass, true);
    }

    public static Project createProject(String projectName, String projectDescription, String projectLanguage,
            String projectAuthor, String projectAuthorPass, boolean encrypt) {
        return createProject(projectName, projectDescription, projectLanguage, projectAuthor, projectAuthorPass, null, null,
                encrypt);
    }

    public static Project createProject(String projectName, String projectDescription, String projectLanguage, User authoer) {
        Project newProject = org.talend.core.runtime.project.ProjectHelper.createProject(projectName, projectDescription);
        newProject.setAuthor(authoer); // set in project to record
        return newProject;
    }

    public static Project createProject(String projectName, String projectDescription, ECodeLanguage language, User authoer) {
        Project newProject = org.talend.core.runtime.project.ProjectHelper.createProject(projectName, projectDescription);
        newProject.setAuthor(authoer); // set in project to record
        return newProject;
    }

    public static Project createProject(String projectName, String projectDescription, String projectLanguage,
            String projectAuthor, String projectAuthorPass, String projectAuthorFirstname, String projectAuthorLastname) {
        return createProject(projectName, projectDescription, projectLanguage, projectAuthor, projectAuthorPass,
                projectAuthorFirstname, projectAuthorLastname, true);
    }

    public static Project createProject(String projectName, String projectDescription, String projectLanguage,
            String projectAuthor, String projectAuthorPass, String projectAuthorFirstname, String projectAuthorLastname,
            boolean encrypt) {

        Project newProject = org.talend.core.runtime.project.ProjectHelper.createProject(projectName, projectDescription);
        User newUser = createUser(projectAuthor, projectAuthorPass, projectAuthorFirstname, projectAuthorLastname, encrypt);

        newProject.setAuthor(newUser); // set in project to record

        return newProject;
    }

    public static User createUser(String projectAuthor, String projectAuthorPass, String projectAuthorFirstname,
            String projectAuthorLastname) {
        return createUser(projectAuthor, projectAuthorPass, projectAuthorFirstname, projectAuthorLastname, true);
    }

    public static User createUser(String projectAuthor, String projectAuthorPass, String projectAuthorFirstname,
            String projectAuthorLastname, boolean encrypt) {
        return org.talend.core.runtime.project.ProjectHelper.createUser(projectAuthor, projectAuthorPass, projectAuthorFirstname,
                projectAuthorLastname, encrypt);
    }

    public static String generateSandbocProjectName(String login) {

        if (login != null && Pattern.matches(RepositoryConstants.MAIL_PATTERN, login.trim())) {
            int at = login.indexOf('@');
            if (at > -1) {
                String mailName = login.substring(0, at);
                if (mailName.length() > 0) {
                    StringBuffer sb = new StringBuffer();
                    sb.append("Sandbox_"); //$NON-NLS-1$
                    sb.append(mailName);
                    sb.append("_project"); //$NON-NLS-1$
                    return sb.toString();
                }
            }
        }
        return null;
    }
}
