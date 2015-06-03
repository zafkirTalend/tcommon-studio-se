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

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.embedder.MavenModelManager;
import org.talend.commons.runtime.utils.io.FileCopyUtils;
import org.talend.core.runtime.process.ITalendProcessJavaProject;
import org.talend.core.runtime.projectsetting.IProjectSettingPreferenceConstants;
import org.talend.core.runtime.projectsetting.IProjectSettingTemplateConstants;
import org.talend.designer.maven.model.TalendMavenConstants;
import org.talend.designer.maven.template.MavenTemplateManager;
import org.talend.designer.maven.tools.creator.CreateMavenBundleTemplatePom;
import org.talend.designer.maven.tools.creator.CreateMavenRoutinePom;
import org.talend.designer.maven.utils.PomUtil;
import org.talend.utils.io.FilesUtils;

/**
 * created by ggu on 2 Feb 2015 Detailled comment
 *
 */
public class MavenPomSynchronizer {

    private final ITalendProcessJavaProject codeProject;

    public MavenPomSynchronizer(ITalendProcessJavaProject codeProject) {
        super();
        this.codeProject = codeProject;
    }

    /**
     * generate routine pom.
     */
    public void syncRoutinesPom(boolean overwrite) throws Exception {
        // pom_routines.xml
        IFile routinesPomFile = codeProject.getProject().getFile(
                PomUtil.getPomFileName(TalendMavenConstants.DEFAULT_ROUTINES_ARTIFACT_ID));
        // generate new one
        CreateMavenBundleTemplatePom createTemplatePom = new CreateMavenRoutinePom(routinesPomFile);
        createTemplatePom.setOverwrite(overwrite);
        createTemplatePom.create(null);
    }

    /**
     * 
     * sync the bat/sh/jobInfo to resources template folder.
     */
    public void syncTemplates(boolean overwrite) throws Exception {
        IFolder templateFolder = codeProject.getTemplatesFolder();

        IFile shFile = templateFolder.getFile(IProjectSettingTemplateConstants.JOB_RUN_SH_TEMPLATE_FILE_NAME);
        IFile batFile = templateFolder.getFile(IProjectSettingTemplateConstants.JOB_RUN_BAT_TEMPLATE_FILE_NAME);
        IFile infoFile = templateFolder.getFile(IProjectSettingTemplateConstants.JOB_INFO_TEMPLATE_FILE_NAME);

        String shContent = MavenTemplateManager.getProjectSettingValue(IProjectSettingPreferenceConstants.TEMPLATE_SH);
        String batContent = MavenTemplateManager.getProjectSettingValue(IProjectSettingPreferenceConstants.TEMPLATE_BAT);
        String jobInfoContent = MavenTemplateManager.getProjectSettingValue(IProjectSettingPreferenceConstants.TEMPLATE_JOB_INFO);

        MavenTemplateManager.saveContent(shFile, shContent, overwrite);
        MavenTemplateManager.saveContent(batFile, batContent, overwrite);
        MavenTemplateManager.saveContent(infoFile, jobInfoContent, overwrite);

        templateFolder.refreshLocal(IResource.DEPTH_ONE, null);
    }

    /**
     * 
     * create project pom
     * 
     * @deprecated should use the CreateMavenCodeProject
     */
    public void createProjectPom() throws Exception {
        IFile projectPomFile = codeProject.getProjectPom();
        File pPomFile = projectPomFile.getLocation().toFile();
        if (!pPomFile.exists()) {
            // synch the templates first.
            syncTemplates(false);
            //
            IFolder templateFolder = codeProject.getTemplatesFolder();
            IFile projectTemplateFile = templateFolder.getFile(IProjectSettingTemplateConstants.PROJECT_TEMPLATE_FILE_NAME);
            FileCopyUtils.copy(projectTemplateFile.getLocation().toFile().toString(), pPomFile.toString());

            // refresh
            codeProject.getProject().refreshLocal(IResource.DEPTH_ONE, null);
        }
    }

    /**
     * 
     * add the job to the pom modules list of project.
     */
    public void addChildModules(boolean removeOld, String... childModules) throws Exception {
        IFile projectPomFile = codeProject.getProjectPom();
        // check and create pom
        createProjectPom();

        MavenModelManager mavenModelManager = MavenPlugin.getMavenModelManager();
        Model projModel = mavenModelManager.readMavenModel(projectPomFile);
        List<String> modules = projModel.getModules();
        if (modules == null) {
            modules = new ArrayList<String>();
            projModel.setModules(modules);
        }

        boolean modifed = false;
        if (removeOld || childModules == null || childModules.length == 0) { // clean the modules
            if (!modules.isEmpty()) {
                modules.clear();
                modifed = true;
            }
        }

        final Iterator<String> iterator = modules.iterator();
        while (iterator.hasNext()) {
            String module = iterator.next();
            if (ArrayUtils.contains(childModules, module)) {
                iterator.remove(); // remove the exised one
            }
        }

        if (childModules != null) {
            // according to the arrays order to add the modules.
            for (String module : childModules) {
                if (module.length() > 0) {
                    modules.add(module);
                    modifed = true;
                }
            }
        }

        if (modifed) {
            // save pom.
            PomUtil.savePom(null, projModel, projectPomFile);
        }
        // refresh
        codeProject.getProject().refreshLocal(IResource.DEPTH_ONE, null);
    }

    /**
     * 
     * Clean the pom_xxx.xml and assembly_xxx.xml and target folder, also clean the module and dependencies.
     */
    public void cleanMavenFiles(IProgressMonitor monitor) throws Exception {
        // remove all job poms
        final String routinesPomFileName = PomUtil.getPomFileName(TalendMavenConstants.DEFAULT_ROUTINES_ARTIFACT_ID);
        File[] pomFiles = codeProject.getProject().getLocation().toFile().listFiles(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                // pom_xxx.xml
                return name.startsWith(TalendMavenConstants.POM_NAME + '_') && name.endsWith(TalendMavenConstants.XML_EXT)
                        && !name.equals(routinesPomFileName);
            }
        });
        deleteFiles(pomFiles);

        // delete all assemblies/
        IFolder assembliesFolder = codeProject.getAssembliesFolder();
        if (assembliesFolder != null && assembliesFolder.exists()) {
            File[] assembliesFiles = assembliesFolder.getLocation().toFile().listFiles(new FilenameFilter() {

                @Override
                public boolean accept(File dir, String name) {
                    // assembly_xxx.xml
                    return name.startsWith(TalendMavenConstants.ASSEMBLY_NAME + '_')
                            && name.endsWith(TalendMavenConstants.XML_EXT);
                }
            });
            deleteFiles(assembliesFiles);
        }

        // delete all target
        FilesUtils.deleteFile(codeProject.getOutputFolder().getParent().getLocation().toFile(), true);

        // if routine is not exsited, gernerate it.
        syncRoutinesPom(false);

        // clean up the dependencies and module for pom.xml
        IFile projectPomFile = codeProject.getProjectPom();
        File pPomFile = projectPomFile.getLocation().toFile();
        if (pPomFile.exists()) {
            MavenModelManager mavenModelManager = MavenPlugin.getMavenModelManager();
            Model projModel = mavenModelManager.readMavenModel(projectPomFile);
            // clear and only add routine pom.
            List<String> modules = projModel.getModules();
            if (modules != null) {
                modules.clear();
            }
            IFile routinesPomFile = codeProject.getProject().getFile(
                    PomUtil.getPomFileName(TalendMavenConstants.DEFAULT_ROUTINES_ARTIFACT_ID));
            Model routinesModel = PomUtil.getRoutinesTempalteModel();
            if (routinesPomFile.exists()) {
                routinesModel = mavenModelManager.readMavenModel(routinesPomFile);
            }
            modules.add(routinesPomFile.getName());

            // only add the routines dependencies.
            List<Dependency> routinesDependencies = routinesModel.getDependencies();
            ProcessorDependenciesManager.updateDependencies(null, projModel, routinesDependencies, true);

            PomUtil.savePom(monitor, projModel, projectPomFile);

            // install custom jar to m2/repo
            PomUtil.installDependencies(routinesDependencies);
        }

        // try to compile it.
        codeProject.buildModules(TalendMavenConstants.GOAL_COMPILE, null, monitor);
    }

    private void deleteFiles(File[] files) {
        if (files != null) {
            for (File f : files) {
                if (f.isFile()) {
                    f.delete();
                }
            }
        }
    }
}
