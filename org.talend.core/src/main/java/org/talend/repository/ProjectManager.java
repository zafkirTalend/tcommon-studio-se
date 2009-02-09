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
import org.talend.core.GlobalServiceRegister;
import org.talend.core.PluginChecker;
import org.talend.core.context.Context;
import org.talend.core.context.RepositoryContext;
import org.talend.core.model.general.Project;
import org.talend.core.model.properties.ProjectReference;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryObject;
import org.talend.core.model.repository.RepositoryManager;
import org.talend.core.ui.IReferencedProjectService;
import org.talend.repository.model.IProxyRepositoryFactory;
import org.talend.repository.model.RepositoryNode;
import org.talend.repository.model.nodes.IProjectRepositoryNode;

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
        initCurrentProject();
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

    /**
     * 
     * ggu Comment method "isInCurrentMainProject".
     * 
     * check the EObject in current main project.
     */
    public boolean isInCurrentMainProject(EObject object) {
        if (object != null) {
            org.talend.core.model.properties.Project project = getProject(object);
            if (project != null) {
                return project.getTechnicalLabel().equals(getCurrentProject().getEmfProject().getTechnicalLabel());
            }
        }
        return false;
    }

    /**
     * 
     * ggu Comment method "isInCurrentMainProject".
     * 
     * check the node in current main project.
     */
    public boolean isInCurrentMainProject(RepositoryNode node) {
        if (node != null) {
            if (PluginChecker.isRefProjectLoaded()) {
                IReferencedProjectService service = (IReferencedProjectService) GlobalServiceRegister.getDefault().getService(
                        IReferencedProjectService.class);
                if (service != null && service.isMergeRefProject()) {
                    IRepositoryObject object = node.getObject();
                    if (object == null) {
                        return true;
                    }
                    org.talend.core.model.properties.Project emfProject = getProject(object.getProperty().getItem());
                    org.talend.core.model.properties.Project curProject = getCurrentProject().getEmfProject();
                    return emfProject.equals(curProject);

                } else {
                    IProjectRepositoryNode root = node.getRoot();
                    if (root != null) {
                        Project project = root.getProject();
                        if (project != null) {
                            return project.equals(getCurrentProject());
                        } else {
                            return true;
                        }
                    }
                }
                // refplugin is not loaded
            } else {

                IProjectRepositoryNode root = node.getRoot();
                if (root != null) {
                    Project project = root.getProject();
                    if (project != null) {
                        return project.getTechnicalLabel().equals(getCurrentProject().getTechnicalLabel());
                    } else {
                        return true;
                    }
                }

            }
        }
        return false;
    }

    public static IProjectRepositoryNode researchProjectNode(Project project) {
        IProjectRepositoryNode root = (IProjectRepositoryNode) RepositoryManager.getRepositoryView().getRoot();
        if (project == null || root.getProject().equals(project)) {
            return root;
        }
        RepositoryNode refRoot = root.getRootRepositoryNode(ERepositoryObjectType.REFERENCED_PROJECTS);
        if (refRoot != null) {
            for (RepositoryNode node : refRoot.getChildren()) {
                if (node instanceof IProjectRepositoryNode) {
                    IProjectRepositoryNode pNode = (IProjectRepositoryNode) node;
                    if (pNode.getProject().getTechnicalLabel().equals(project.getTechnicalLabel())) {
                        return pNode;
                    }
                }
            }
        }
        return null;
    }
}
