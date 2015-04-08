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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.embedder.MavenModelManager;
import org.talend.core.model.process.IProcess;
import org.talend.core.model.process.JobInfo;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.utils.JavaResourcesHelper;
import org.talend.designer.maven.model.TalendMavenContants;
import org.talend.designer.maven.utils.JobUtils;
import org.talend.designer.maven.utils.PomManager;
import org.talend.designer.maven.utils.TalendCodeProjectUtil;
import org.talend.designer.runprocess.IProcessor;
import org.talend.designer.runprocess.ProcessorException;

/**
 * created by ycbai on 2015年4月2日 Detailled comment
 *
 */
public class MavenPomManager {

    private final IProcessor processor;

    private Set<JobInfo> clonedJobInfos = new HashSet<JobInfo>();

    public MavenPomManager(IProcessor processor) {
        this.processor = processor;
    }

    public void updateDependencies(IFile pomFile, IProgressMonitor progressMonitor) throws ProcessorException {
        try {
            MavenModelManager mavenModelManager = MavenPlugin.getMavenModelManager();
            Model model = mavenModelManager.readMavenModel(pomFile);
            List<Dependency> dependencies = model.getDependencies();
            if (dependencies == null) {
                dependencies = new ArrayList<Dependency>();
                model.setDependencies(dependencies);
            }
            List<String> oldDependencyIds = new ArrayList<String>();
            for (Dependency dependency : dependencies) {
                oldDependencyIds.add(dependency.getArtifactId());
            }
            dependencies.clear();
            List<String> newDependencyIds = new ArrayList<String>();
            // Dependency routinesDependency = new Dependency();
            // final Model routinesModel = TalendCodeProjectUtil.getRoutinesTempalteModel();
            // // update the routine artifact.
            // routinesDependency.setVersion(routinesModel.getVersion());
            // routinesDependency.setGroupId(routinesModel.getGroupId());
            // routinesDependency.setArtifactId(routinesModel.getArtifactId());
            // dependencies.add(routinesDependency);
            // newDependencyIds.add(routinesDependency.getArtifactId());
            // Dependency junitDependency = new Dependency();
            // final Model junitModel = TalendCodeProjectUtil.getJunitTempalteModel();
            // junitDependency.setVersion(junitModel.getVersion());
            // junitDependency.setGroupId(junitModel.getGroupId());
            // junitDependency.setArtifactId(junitModel.getArtifactId());
            // junitDependency.setScope(TalendMavenContants.DEFAULT_JUNIT_ARTIFACT_SCOPE);
            // dependencies.add(junitDependency);
            // newDependencyIds.add(junitDependency.getArtifactId());
            // add the job modules.
            Set<String> neededLibraries = processor.getNeededLibraries();
            IFolder libFolder = processor.getTalendJavaProject().getLibFolder();
            if (!libFolder.isSynchronized(IResource.DEPTH_ONE)) {
                libFolder.refreshLocal(IResource.DEPTH_ONE, progressMonitor);
            }
            Set<String> existingJars = new HashSet<String>();
            for (IResource resource : libFolder.members()) {
                existingJars.add(resource.getName());
            }
            for (String lib : neededLibraries) {
                if (!existingJars.contains(lib)) {
                    continue;
                }
                String name = new Path(lib).removeFileExtension().toString();
                Dependency dependency = new Dependency();
                // TODO, if change the scope to other, not system. will change this.
                String group = name;
                String artifact = name;
                String version = TalendMavenContants.DEFAULT_VERSION;
                if (TalendCodeProjectUtil.stripVersion) {
                    // TODO because system scope, so the name is final file name. and have contained the version in file
                    // name.
                    // artifact=name;
                }
                dependency.setGroupId(group);
                dependency.setArtifactId(artifact);
                dependency.setVersion(version);
                dependency.setScope("system"); //$NON-NLS-1$
                dependency.setSystemPath("${system.lib.path}/" + lib); //$NON-NLS-1$
                dependencies.add(dependency);
                newDependencyIds.add(dependency.getArtifactId());
            }
            final Set<JobInfo> clonedChildrenJobInfors = getClonedJobInfos();
            // add children jars to build
            for (JobInfo child : clonedChildrenJobInfors) {
                Dependency dependency = new Dependency();
                final String childJobName = JavaResourcesHelper.escapeFileName(child.getJobName());
                String artifact = childJobName;
                if (TalendCodeProjectUtil.stripVersion) { // in order to keep with version for jar always.
                    // must add the version for artifact
                    artifact = JavaResourcesHelper.getJobJarName(childJobName, child.getJobVersion());
                }
                dependency.setGroupId(generateGroupId(child));
                dependency.setArtifactId(artifact);
                dependency.setVersion(child.getJobVersion());
                dependencies.add(dependency);
                newDependencyIds.add(dependency.getArtifactId());
            }
            boolean changed = oldDependencyIds.retainAll(newDependencyIds);
            if (!changed) {
                changed = newDependencyIds.retainAll(oldDependencyIds);
            }
            if (changed) {
                PomManager.savePom(progressMonitor, model, pomFile);
            }
        } catch (Exception e) {
            throw new ProcessorException(e);
        }
    }

    protected String generateGroupId(final JobInfo jobInfo) {
        ProcessItem processItem = jobInfo.getProcessItem();
        if (processItem != null) {
            String componentsType = null;
            IProcess process = jobInfo.getProcess();
            if (process != null) {
                componentsType = process.getComponentsType();
            }
            final String projectFolderName = JavaResourcesHelper.getProjectFolderName(processItem);
            return generateGroupId(projectFolderName, componentsType);
        } else { // return one default one.
            return generateGroupId(null, null);
        }
    }

    protected String generateGroupId(String projectFolderName, String type) {
        String groupId = JavaResourcesHelper.getGroupName(projectFolderName);
        if (type != null) {
            groupId += '.' + type.toLowerCase();
        }
        return groupId;
    }

    private Set<JobInfo> getClonedJobInfos() {
        if (clonedJobInfos.isEmpty()) {
            clonedJobInfos = JobUtils.getClonedChildrenJobInfos(processor);
        }
        return clonedJobInfos;
    }

}
