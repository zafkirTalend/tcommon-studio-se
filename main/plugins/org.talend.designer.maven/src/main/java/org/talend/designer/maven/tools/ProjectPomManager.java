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
package org.talend.designer.maven.tools;

import java.util.ArrayList;
import java.util.List;

import org.apache.maven.model.Model;
import org.apache.maven.model.Repository;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.embedder.MavenModelManager;
import org.talend.designer.maven.model.TalendMavenConstants;
import org.talend.designer.maven.tools.repo.LocalRepositoryManager;
import org.talend.designer.maven.utils.PomUtil;
import org.talend.designer.runprocess.ProcessorException;

/**
 * DOC ggu class global comment. Detailled comment
 */
public class ProjectPomManager {

    private IFile projectPomFile;

    public ProjectPomManager(IProject project) {
        super();
        Assert.isNotNull(project);
        this.projectPomFile = project.getFile(TalendMavenConstants.POM_FILE_NAME);
    }

    public void updateProjectPom(IProgressMonitor progressMonitor, List<String> modulesList, IFile jobPomFile)
            throws ProcessorException {
        try {

            MavenModelManager mavenModelManager = MavenPlugin.getMavenModelManager();
            Model projectModel = mavenModelManager.readMavenModel(projectPomFile);

            // check the modules
            List<String> modules = projectModel.getModules();
            if (modules == null) {
                modules = new ArrayList<String>();
                projectModel.setModules(modules);
            } else if (!modules.isEmpty()) {
                modules.clear(); // clean all?
            }
            final Model routinesModel = PomUtil.getRoutinesTempalteModel();
            String routinesModule = PomUtil.getPomFileName(routinesModel.getArtifactId());
            modules.add(routinesModule);

            if (modulesList != null) {
                if (modulesList.contains(routinesModule)) {
                    modulesList.remove(routinesModule); // make sure no duplicated.
                }
                modules.addAll(modulesList);
            }

            // check repository
            updateProjectRepositories(progressMonitor, projectModel);

            // check the dependencies
            if (jobPomFile.getLocation().toFile().exists()) {
                if (!jobPomFile.exists()) {
                    jobPomFile.getParent().refreshLocal(IResource.DEPTH_ONE, progressMonitor);
                }
                Model jobModel = mavenModelManager.readMavenModel(jobPomFile);

                // fresh is false, make sure all jobs can be compile ok
                ProcessorDependenciesManager.updateDependencies(progressMonitor, projectModel, jobModel.getDependencies(), false);
            }

            PomUtil.savePom(progressMonitor, projectModel, projectPomFile);

            // install custom jar to m2/repo
            PomUtil.installDependencies(projectModel.getDependencies());
        } catch (Exception e) {
            throw new ProcessorException(e);
        }
    }

    protected void updateProjectRepositories(IProgressMonitor progressMonitor, Model projectModel) throws ProcessorException {
        // set repositories.
        List<Repository> repositories = projectModel.getRepositories();
        if (repositories == null) {
            repositories = new ArrayList<Repository>();
            projectModel.setRepositories(repositories);
        }
        Repository localRep = null;
        for (Repository r : repositories) {
            if (r.getId().equals(LocalRepositoryManager.REPO_ID)) {
                localRep = r;
                break;
            }
        }
        if (LocalRepositoryManager.ENABLE_LOCAL_REPO) {
            if (localRep == null) { // not existed, add one
                Repository localRepo = new Repository();

                localRepo.setId(LocalRepositoryManager.REPO_ID);
                localRepo.setUrl("file://${basedir}/" + LocalRepositoryManager.REPO_FOLDER);

                repositories.add(localRepo);
            }
        } else {
            if (localRep != null) { // existed, delete it
                repositories.remove(localRep);
            }
        }

    }
}
