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
package org.talend.core.model.utils;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.talend.commons.utils.generation.JavaUtils;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.model.general.Project;
import org.talend.core.model.process.IContext;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.Property;
import org.talend.core.runtime.process.ITalendProcessJavaProject;
import org.talend.core.ui.branding.IBrandingService;
import org.talend.designer.runprocess.IRunProcessService;
import org.talend.repository.ProjectManager;
import org.talend.repository.model.RepositoryNode;

/**
 * An util tools for java version resources. Detailled comment <br/>
 * 
 */
/**
 * bqian An util tools for java version resources. <br/>
 * 
 */
public class JavaResourcesHelper {

    /**
     * Gets the specific resource in the java project.
     * 
     * @param name
     * @param projectName
     * 
     * @return
     */
    public static IResource getSpecificResourceInJavaProject(IPath path) throws CoreException {
        if (GlobalServiceRegister.getDefault().isServiceRegistered(IRunProcessService.class)) {
            IRunProcessService service = (IRunProcessService) GlobalServiceRegister.getDefault().getService(
                    IRunProcessService.class);
            ITalendProcessJavaProject talendProcessJavaProject = service.getTalendProcessJavaProject();
            if (talendProcessJavaProject != null) {
                IProject project = talendProcessJavaProject.getProject();
                IResource resource = project.findMember(path);
                return resource;
            }
        }
        return null;
    }

    /**
     * Gets current project name.
     * 
     * @return
     */
    public static String getCurrentProjectName() {
        ProjectManager pManager = ProjectManager.getInstance();
        Project project = pManager.getCurrentProject();
        String name = project.getTechnicalLabel().toLowerCase();
        return name;
    }

    public static String getJobFolderName(String jobName, String version) {
        String newJobName = escapeFileName(jobName).toLowerCase();
        if (version != null) {
            newJobName += '_' + version.replace(".", "_"); //$NON-NLS-1$ //$NON-NLS-2$ 
        }
        return newJobName;
    }

    public static String escapeFileName(final String fileName) {
        return fileName != null ? fileName.replace(" ", "_") : ""; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }

    /**
     * even project is "Test", will return "test".
     */
    public static String getProjectFolderName(Property property) {
        if (property == null) {
            return ProjectManager.getInstance().getCurrentProject().getTechnicalLabel().toLowerCase();
        }
        return getProjectFolderName(property.getItem());
    }

    /**
     * even project is "Test", will return "test".
     */
    public static String getProjectFolderName(Item item) {
        ProjectManager pManager = ProjectManager.getInstance();
        org.talend.core.model.properties.Project p = pManager.getProject(item);
        String projectFolderName = p.getTechnicalLabel();
        projectFolderName = projectFolderName.toLowerCase();
        return projectFolderName;
    }

    /**
     * project name is "Test". will return "org.talend.test".
     * 
     * If other branding, like "Camel", will return "org.camel.test".
     */
    public static String getGroupName(String projectName) {

        String itemGroupPrefixName = "org."; //$NON-NLS-1$
        String corporationName = null;
        IBrandingService service = (IBrandingService) GlobalServiceRegister.getDefault().getService(IBrandingService.class);
        if (service != null) {
            corporationName = service.getCorporationName();
            if (corporationName != null) {
                corporationName = corporationName.trim();
            }
        }
        if (corporationName == null || corporationName.length() == 0) {
            corporationName = "talend"; //$NON-NLS-1$
        }
        itemGroupPrefixName += corporationName;

        if (projectName != null) {
            itemGroupPrefixName += '.' + projectName;
        }
        return itemGroupPrefixName.trim().toLowerCase();
    }

    /**
     * return the getGroupName with project "Test", and item name "TestJob".
     * 
     * something like: "org.talend.test.testjob".
     */
    public static String getGroupItemName(String projectName, String itemName) {
        if (itemName == null) {
            return null;
        }
        return getGroupName(projectName) + '.' + getJobFolderName(itemName, null);
    }

    /**
     * if project "Test" and item "TestJob 0.1" , will return "test.testjob_0_1"
     * 
     */
    public static String getJobClassPackageName(Item processItem) {
        Property itemProperty = processItem.getProperty();
        String packageName = getProjectFolderName(processItem) + '.'
                + getJobFolderName(itemProperty.getLabel(), itemProperty.getVersion());
        return packageName;
    }

    /**
     * if project "Test" and item "TestJob 0.1" , will return "test.testjob_0_1.TestJob"
     * 
     */
    public static String getJobPackagedClass(Item processItem, boolean filenameFromLabel) {
        String jobName = filenameFromLabel ? escapeFileName(processItem.getProperty().getLabel()) : processItem.getProperty()
                .getId();
        return getJobClassPackageName(processItem) + '.' + jobName;
    }

    public static String getJobClassName(Item processItem) {
        return getJobPackagedClass(processItem, true);
    }

    public static String getJobClassName(RepositoryNode jobNode) {
        return getJobClassName(jobNode.getObject().getProperty().getItem());
    }

    /**
     * if project "Test" and item "TestJob 0.1" , will return "test/testjob_0_1".
     * 
     */
    public static String getJobClassPackageFolder(Item processItem) {
        return getJobClassPackageName(processItem).replace('.', '/');
    }

    /**
     * project is test. job is "TestJob 0.1".
     * 
     * will return test/testjob_0_1/TestJob.java
     */
    public static String getJobClassFilePath(Item processItem, boolean filenameFromLabel) {
        return getJobPackagedClass(processItem, filenameFromLabel).replace('.', '/') + JavaUtils.JAVA_EXTENSION;
    }

    /**
     * like Default.properties
     */
    public static String getJobContextName(IContext c) {
        return escapeFileName(c.getName()) + JavaUtils.JAVA_CONTEXT_EXTENSION;
    }
}
