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
package org.talend.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.talend.commons.CommonsPlugin;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.utils.workbench.resources.ResourceUtils;
import org.talend.core.CorePlugin;
import org.talend.core.context.Context;
import org.talend.core.context.RepositoryContext;
import org.talend.core.model.general.Project;
import org.talend.core.model.properties.ProjectReference;
import org.talend.repository.model.IProxyRepositoryFactory;

/**
 * ggu class global comment. Detailled comment
 */
public final class ProjectManager {

    private static ProjectManager singleton;

    private Project currentProject;

    private List<Project> referencedprojects = new ArrayList<Project>();

    private ProjectManager() {
        initCurrentProject();
    }

    public static synchronized ProjectManager getInstance() {
        if (singleton == null) {
            singleton = new ProjectManager();
        }
        return singleton;
    }

    private void initCurrentProject() {
        Context ctx = CorePlugin.getContext();
        if (ctx != null) {
            RepositoryContext repositoryContext = (RepositoryContext) ctx.getProperty(Context.REPOSITORY_CONTEXT_KEY);
            if (repositoryContext != null) {
                currentProject = repositoryContext.getProject();
                return;
            }
        }
        currentProject = null;
    }

    /**
     * 
     * retrieve the referenced projects of current project.
     */
    public void retrieveReferencedProjects() {
        referencedprojects.clear();
        IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
        if (factory != null) {
            List<org.talend.core.model.properties.Project> rProjects = factory.getReferencedProjects();
            if (rProjects != null) {
                for (org.talend.core.model.properties.Project p : rProjects) {
                    Project project = new Project(p);
                    referencedprojects.add(project);
                }
            }
        }
    }

    /**
     * return current project.
     * 
     */
    public Project getCurrentProject() {
        if (this.currentProject == null || CommonsPlugin.isHeadless()) {
            initCurrentProject();
        }
        return this.currentProject;

    }

    /**
     * 
     * return the referenced projects of current project.
     */
    public List<Project> getReferencedProjects() {
        if (this.referencedprojects.isEmpty() || CommonsPlugin.isHeadless()) {
            retrieveReferencedProjects();
        }
        return this.referencedprojects;
    }

    /**
     * 
     * return the referenced projects of the project.
     */
    @SuppressWarnings("unchecked")
    public List<Project> getReferencedProjects(Project project) {
        if (project != null) {

            if (project.equals(this.currentProject)) {
                // retrieveReferencedProjects();
                return this.referencedprojects;
            }

            List<Project> refProjects = new ArrayList<Project>();
            for (ProjectReference refProject : (List<ProjectReference>) project.getEmfProject().getReferencedProjects()) {
                refProjects.add(new Project(refProject.getReferencedProject()));
            }
            return refProjects;
        }
        return Collections.emptyList();
    }

    /**
     * 
     * return the project by object.
     */
    public org.talend.core.model.properties.Project getProject(EObject object) {
        if (object != null) {
            // if object is in current project, the rootObj will be self.
            EObject rootObj = EcoreUtil.getRootContainer(object);
            if (rootObj != null && rootObj instanceof org.talend.core.model.properties.Project) {
                return (org.talend.core.model.properties.Project) rootObj;
            }
        }
        // default
        return getCurrentProject().getEmfProject();
    }

    public IProject getResourceProject(org.talend.core.model.properties.Project project) {
        if (project != null) {
            try {
                return ResourceUtils.getProject(project.getTechnicalLabel());
            } catch (PersistenceException e) {
                //
            }
        }
        return ResourcesPlugin.getWorkspace().getRoot().getProject(getCurrentProject().getEmfProject().getTechnicalLabel());
    }

    public IProject getResourceProject(EObject object) {
        return getResourceProject(getProject(object));
    }
}
