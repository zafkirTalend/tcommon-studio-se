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
package org.talend.designer.maven.pom;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.embedder.MavenModelManager;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.ILibraryManagerService;
import org.talend.core.model.process.JobInfo;
import org.talend.designer.maven.model.MavenConstants;
import org.talend.designer.runprocess.IProcessor;
import org.talend.designer.runprocess.ProcessorException;

/**
 * created by ycbai on 2015年4月2日 Detailled comment
 *
 */
public class MavenPomManager {

    private final IProcessor processor;

    public MavenPomManager(IProcessor processor) {
        this.processor = processor;
    }

    public boolean updateProcessorDependencies(IProgressMonitor progressMonitor, Model model) throws ProcessorException {
        try {
            List<Dependency> neededDependencies = new ArrayList<Dependency>();

            // add the job modules.
            Set<String> neededLibraries = processor.getNeededLibraries();

            List<String> existingJars = new ArrayList<String>();
            if (GlobalServiceRegister.getDefault().isServiceRegistered(ILibraryManagerService.class)) {
                ILibraryManagerService libService = (ILibraryManagerService) GlobalServiceRegister.getDefault().getService(
                        ILibraryManagerService.class);
                Set<String> list = libService.list(progressMonitor);
                if (list != null) {
                    existingJars.addAll(list);
                }
            }

            for (String lib : neededLibraries) {
                if (!existingJars.contains(lib)) {
                    continue;
                }

                Dependency dependency = PomUtil.createModuleSystemScopeDependency(null, lib, null);
                if (dependency != null) {
                    neededDependencies.add(dependency);
                }
            }
            return updateDependencies(progressMonitor, model, neededDependencies, true);

        } catch (Exception e) {
            throw new ProcessorException(e);
        }
    }

    /**
     * 
     * DOC ggu Comment method "updateDependencies". add the job Needed Libraries for current model.
     * 
     * @param model the job of pom model
     * @param fresh if true, will remove old dependencies, else will add the new dependencies in the head.
     * @return if there are some changes, will return true
     */
    public boolean updateDependencies(IProgressMonitor progressMonitor, Model model, List<Dependency> neededDependencies,
            boolean fresh) throws ProcessorException {
        boolean changed = false;
        try {
            List<Dependency> existedDependencies = model.getDependencies();
            if (existedDependencies == null) {
                existedDependencies = new ArrayList<Dependency>();
                model.setDependencies(existedDependencies);
            }
            // record existed list
            Map<String, Dependency> existedDependenciesMap = new LinkedHashMap<String, Dependency>();
            if (!fresh) { // just in order to make the performance better.
                for (Dependency dependency : existedDependencies) {
                    // need remove the old non-existed dependencies, else won't compile the project.
                    // if (!PomUtil.isAvailable(dependency)) {
                    // continue;
                    // }
                    existedDependenciesMap.put(PomUtil.generateMvnUrl(dependency), dependency);
                }
            }
            // clear all of existed list
            existedDependencies.clear();

            for (Dependency dependency : neededDependencies) {
                Dependency cloneDependency = dependency.clone();
                // FIXME, need check the new dependency existed or not? or just let the compile error for m2?
                // if (!PomUtil.isAvailable(cloneDependency)) {
                // continue;
                // }
                existedDependencies.add(cloneDependency); // add the needed in the head.

                if (fresh) {
                    changed = true; // after added, true always
                } else {
                    // remove it in old list.
                    String mvnUrl = PomUtil.generateMvnUrl(dependency);
                    Dependency existedDependency = existedDependenciesMap.remove(mvnUrl);
                    if (existedDependency != null) { // existed before.
                        // nothing to do.
                    } else { // added new
                        changed = true;
                    }
                }
            }

            if (!fresh) {
                // add the left dependencies.
                existedDependencies.addAll(existedDependenciesMap.values());
            }
        } catch (Exception e) {
            throw new ProcessorException(e);
        }
        return changed;
    }

    public void updateProjectDependencies(IProgressMonitor progressMonitor, IFile jobPomFile) throws ProcessorException {
        try {
            IProject codeProject = this.processor.getTalendJavaProject().getProject();
            IFile projectPomFile = codeProject.getFile(MavenConstants.POM_FILE_NAME);

            MavenModelManager mavenModelManager = MavenPlugin.getMavenModelManager();
            Model projectModel = mavenModelManager.readMavenModel(projectPomFile);

            // add the modules
            List<String> modules = projectModel.getModules();
            modules.clear(); // clean all?
            final Model routinesModel = PomUtil.getRoutinesTempalteModel();
            modules.add(PomUtil.getPomFileName(routinesModel.getArtifactId()));
            for (JobInfo childJob : this.processor.getBuildChildrenJobs()) {
                modules.add(PomUtil.getPomFileName(childJob.getJobName()));
            }
            modules.add(PomUtil.getPomFileName(this.processor.getProperty().getLabel()));

            // check the dependencies
            if (jobPomFile.getLocation().toFile().exists()) {
                if (!jobPomFile.exists()) {
                    jobPomFile.getParent().refreshLocal(IResource.DEPTH_ONE, progressMonitor);
                }
                Model jobModel = mavenModelManager.readMavenModel(jobPomFile);

                // fresh is false, make sure all jobs can be compile ok
                updateDependencies(progressMonitor, projectModel, jobModel.getDependencies(), false);
            }

            PomUtil.savePom(progressMonitor, projectModel, projectPomFile);

            codeProject.refreshLocal(IResource.DEPTH_ONE, progressMonitor);
        } catch (Exception e) {
            throw new ProcessorException(e);
        }
    }

}
