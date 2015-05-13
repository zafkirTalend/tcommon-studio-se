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
import org.talend.commons.utils.resource.FileExtensions;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.ILibraryManagerService;
import org.talend.core.runtime.process.ITalendProcessJavaProject;
import org.talend.designer.maven.model.MavenConstants;
import org.talend.designer.maven.model.TalendMavenContants;
import org.talend.designer.maven.pom.PomUtil;
import org.talend.designer.maven.template.MavenTemplateConstants;
import org.talend.designer.maven.template.MavenTemplateManager;
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
                PomUtil.getPomFileName(TalendMavenContants.DEFAULT_ROUTINES_ARTIFACT_ID));
        // generate new one
        CreateMavenTemplatePom createTemplatePom = new CreateMavenRoutinePom(routinesPomFile);
        createTemplatePom.setOverwrite(overwrite);
        createTemplatePom.create(null);
    }

    /**
     * 
     * sync the bat/sh/jobInfo/project_pom to resources template folder.
     */
    public void syncTemplates(boolean overwrite) throws Exception {
        IFolder templateFolder = codeProject.getTemplatesFolder();

        IFile shFile = templateFolder.getFile(MavenTemplateConstants.JOB_RUN_SH_TEMPLATE_FILE_NAME);
        MavenTemplateManager.copyTemplate(shFile.getName(), shFile, overwrite);

        IFile batFile = templateFolder.getFile(MavenTemplateConstants.JOB_RUN_BAT_TEMPLATE_FILE_NAME);
        MavenTemplateManager.copyTemplate(batFile.getName(), batFile, overwrite);

        IFile infoFile = templateFolder.getFile(MavenTemplateConstants.JOB_INFO_TEMPLATE_FILE_NAME);
        MavenTemplateManager.copyTemplate(infoFile.getName(), infoFile, overwrite);

        // IFile projectFile = templateFolder.getFile(MavenTemplateConstants.PROJECT_TEMPLATE_FILE_NAME);
        // MavenTemplateManager.copyTemplate(projectFile.getName(), projectFile, overwrite);

        templateFolder.refreshLocal(IResource.DEPTH_ONE, null);
    }

    /**
     * 
     * create project pom
     */
    public void createProjectPom() throws Exception {
        IFile projectPomFile = codeProject.getProjectPom();
        File pPomFile = projectPomFile.getLocation().toFile();
        if (!pPomFile.exists()) {
            // synch the templates first.
            syncTemplates(false);
            //
            IFolder templateFolder = codeProject.getTemplatesFolder();
            IFile projectTemplateFile = templateFolder.getFile(MavenTemplateConstants.PROJECT_TEMPLATE_FILE_NAME);
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
        final String routinesPomFileName = PomUtil.getPomFileName(TalendMavenContants.DEFAULT_ROUTINES_ARTIFACT_ID);
        File[] pomFiles = codeProject.getProject().getLocation().toFile().listFiles(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                // pom_xxx.xml
                return name.startsWith(MavenConstants.POM_NAME + '_') && name.endsWith(MavenConstants.XML_EXT)
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
                    return name.startsWith(MavenConstants.ASSEMBLY_NAME + '_') && name.endsWith(MavenConstants.XML_EXT);
                }
            });
            deleteFiles(assembliesFiles);
        }

        // delete all target
        FilesUtils.deleteFile(codeProject.getOutputFolder().getParent().getLocation().toFile(), true);

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
            final Model routinesModel = PomUtil.getRoutinesTempalteModel();
            modules.add(PomUtil.getPomFileName(routinesModel.getArtifactId()));

            // only add the routines dependencies.
            CreateMavenRoutinePom.addDependencies(projModel);

            // synch the routines jar to .Java/lib to resolve the dependencies error for pom.
            if (GlobalServiceRegister.getDefault().isServiceRegistered(ILibraryManagerService.class)) {
                ILibraryManagerService libManagerService = (ILibraryManagerService) GlobalServiceRegister.getDefault()
                        .getService(ILibraryManagerService.class);
                List<String> jarsNeedRetrieve = new ArrayList<String>();
                for (Dependency d : projModel.getDependencies()) {
                    if (d.getScope() != null && d.getScope().equals("system")) { // only for system scope
                        jarsNeedRetrieve.add(d.getArtifactId() + FileExtensions.JAR_FILE_SUFFIX); // jar name
                    }
                }
                libManagerService.retrieve(jarsNeedRetrieve, codeProject.getLibFolder().getLocation().toPortableString(), false);
            }

            PomUtil.savePom(monitor, projModel, projectPomFile);
        }

        // try to compile it.
        codeProject.buildModules(MavenConstants.GOAL_COMPILE, null);
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
