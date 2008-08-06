// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
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
import org.talend.core.CorePlugin;
import org.talend.core.context.Context;
import org.talend.core.context.RepositoryContext;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.model.general.Project;
import org.talend.core.model.properties.Item;

/**
 * bqian An util tools for perl version resources. <br/>
 * 
 */
public class PerlResourcesHelper {

    private static final String FILE_SUFFIX = ".pl";

    public static final boolean USE_VERSIONING = true;

    /**
     * Gets the specific resource in the perl project.
     * 
     * @param name
     * @param projectName
     * 
     * @return
     */
    public static IResource getSpecificResourceInPerlProject(IPath path) throws CoreException {
        IProject project = CorePlugin.getDefault().getRunProcessService().getProject(ECodeLanguage.PERL);
        IResource resource = project.findMember(path);
        return resource;
    }

    /**
     * Gets current project name.
     * 
     * @return
     */
    public static String getCurrentProjectName() {
        RepositoryContext repositoryContext = (RepositoryContext) CorePlugin.getContext().getProperty(
                Context.REPOSITORY_CONTEXT_KEY);
        Project project = repositoryContext.getProject();

        String name = project.getLabel();
        name = name.replaceAll(" ", "_"); //$NON-NLS-1$ //$NON-NLS-2$
        name = name.replaceAll("-", "_"); //$NON-NLS-1$ //$NON-NLS-2$
        name = name.toUpperCase();

        return name;
    }

    /**
     * ftang Comment method "escapeSpace".
     * 
     * @param name
     * @return
     */
    public static String escapeSpace(String name) {
        return name != null ? name.replace(" ", "") : ""; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }

    public static String getRootProjectName(Item item) {
        org.talend.core.model.properties.Project p = CorePlugin.getDefault().getProxyRepositoryFactory().getProject(item);
        String projectFolderName = p.getTechnicalLabel();
        return projectFolderName;
    }

    public static String getRootProjectName(String name) {
        return name.toUpperCase();
    }

    public static String getJobFileName(String projectName, String jobName, String version) {
        String jobFileName = projectName + ".job_" + escapeSpace(jobName); //$NON-NLS-1$
        if (USE_VERSIONING) {
            jobFileName += "_" + version;
        }
        jobFileName += FILE_SUFFIX;

        return jobFileName;
    }

    public static String getJobFileName(String jobName, String version) {
        return getJobFileName(getCurrentProjectName(), jobName, version);
    }

    public static String getContextFileName(String projectName, String jobName, String version, String context) {
        String contextFileName = projectName + ".job_" + escapeSpace(jobName); //$NON-NLS-1$
        if (USE_VERSIONING) {
            contextFileName += "_" + version;
        }
        contextFileName += "_" + escapeSpace(context);
        contextFileName += FILE_SUFFIX;

        return contextFileName;
    }

}
