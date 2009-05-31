// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
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

/**
 * DOC bZhou class global comment. Detailled comment
 */
public final class ReponsitoryContextBridge {

    private static String projectName;

    private static String author;

    private static String defaultProjectName = "TOP_DEFAULT_PRJ";

    private ReponsitoryContextBridge() {

    }

    /**
     * Getter for projectName.
     * 
     * @return the projectName
     */
    public static String getProjectName() {
        return projectName == null ? defaultProjectName : projectName;
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
        return author == null ? "" : author;
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
}
