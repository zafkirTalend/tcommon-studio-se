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

    private static String projectName;

    private static String author;

    private static String defaultProjectName = "TOP_DEFAULT_PRJ";

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
        if (project == null) {
            return projectName == null ? defaultProjectName : projectName;
        } else {
            return project.getLabel();
        }

    }

    /**
     * Sets the projectName.
     * 
     * @param projectName the projectName to set
     */
    public static void setProjectName(String projectName) {
        ReponsitoryContextBridge.projectName = projectName;
    }

    /**
     * Getter for author.
     * 
     * @return the author
     */
    public static String getAuthor() {
        if (user == null) {
            return author == null ? "" : author;
        } else {
            return user.getLogin();
        }
    }

    /**
     * Sets the author.
     * 
     * @param author the author to set
     */
    public static void setAuthor(String author) {
        ReponsitoryContextBridge.author = author;
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
    public static IProject getProject() {
        return ResourcesPlugin.getWorkspace().getRoot().getProject(getProjectName());
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
