// ============================================================================
//
// Copyright (C) 2006-2010 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.commons.bridge;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.talend.core.model.properties.Project;
import org.talend.core.model.properties.User;

/**
 * DOC bZhou class global comment. Detailled comment
 */
public final class ReponsitoryContextBridge {

    public static final String PROJECT_DEFAULT_NAME = "TOP_DEFAULT_PRJ";

    private static Project project;

    private static User user;

    private ReponsitoryContextBridge() {

    }

    /**
     * Getter for projectName.
     * 
     * @return the projectName
     */
    public static String getProjectName() {
        return isDefautProject() ? PROJECT_DEFAULT_NAME : project.getLabel();
    }

    /**
     * Getter for author.
     * 
     * @return the author
     */
    public static String getAuthor() {
        return isDefautProject() ? "" : project.getAuthor().getLogin();
    }

    /**
     * DOC bZhou Comment method "getRootProject".
     * 
     * @return
     */
    public static IProject getRootProject() {
        return ResourcesPlugin.getWorkspace().getRoot().getProject(getProjectName());
    }

    /**
     * DOC bZhou Comment method "initialized".
     * 
     * @param proj
     * @param user
     */
    public static void initialized(Project aProject, User aUser) {
        project = aProject;
        user = aUser;
    }

    /**
     * Getter for project.
     * 
     * @return the project
     */
    public static Project getProject() {
        return project;
    }

    /**
     * Getter for user.
     * 
     * @return the user
     */
    public static User getUser() {
        return user;
    }

    /**
     * DOC bZhou Comment method "isDefautProject".
     * 
     * @return
     */
    public static boolean isDefautProject() {
        return project == null;
    }
}
