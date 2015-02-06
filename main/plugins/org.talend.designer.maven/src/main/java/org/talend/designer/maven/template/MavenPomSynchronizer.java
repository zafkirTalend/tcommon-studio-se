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
package org.talend.designer.maven.template;

import java.util.ArrayList;
import java.util.List;

import org.apache.maven.model.Model;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.embedder.MavenModelManager;
import org.talend.commons.utils.generation.JavaUtils;
import org.talend.core.runtime.process.ITalendProcessJavaProject;
import org.talend.designer.maven.model.MavenConstants;
import org.talend.designer.maven.model.TalendMavenContants;
import org.talend.designer.maven.utils.PomManager;

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
        IFolder routinesSrcFolder = codeProject.getSrcFolder().getFolder(JavaUtils.JAVA_ROUTINES_DIRECTORY);
        IFile routinesPomFile = routinesSrcFolder.getFile(MavenConstants.POM_FILE_NAME);
        if (overwrite || !routinesPomFile.exists()) {// generate new one
            CreateTemplateMavenPom createTemplatePom = new CreateTemplateMavenPom(routinesPomFile,
                    MavenTemplateConstants.ROUTINGS_TEMPLATE_FILE_NAME);
            createTemplatePom.setGroupId(TalendMavenContants.DEFAULT_GROUP_ID);
            createTemplatePom.setArtifactId(TalendMavenContants.DEFAULT_ROUTINES_ARTIFACT_ID);
            createTemplatePom.setVersion(TalendMavenContants.DEFAULT_VERSION);
            createTemplatePom.setOverwrite(false); // don't overwrite.
            createTemplatePom.create(null);
        }
    }

    /**
     * 
     * sync the bat/sh/jobInfo to resources template folder.
     */
    public void syncTemplates(boolean overwrite) throws Exception {
        IFolder templateFolder = codeProject.getResourceSubFolder(null, MavenTemplateConstants.TEMPLATE_PATH);

        IFile shFile = templateFolder.getFile(MavenTemplateConstants.JOB_RUN_SH_TEMPLATE_FILE_NAME);
        MavenTemplateManager.copyTemplate(shFile.getName(), shFile, overwrite);

        IFile batFile = templateFolder.getFile(MavenTemplateConstants.JOB_RUN_BAT_TEMPLATE_FILE_NAME);
        MavenTemplateManager.copyTemplate(batFile.getName(), batFile, overwrite);

        IFile infoFile = templateFolder.getFile(MavenTemplateConstants.JOB_INFO_TEMPLATE_FILE_NAME);
        MavenTemplateManager.copyTemplate(infoFile.getName(), infoFile, overwrite);

    }

    /**
     * 
     * add the job to the pom modules list of project.
     */
    public void addJobModuleInProject(String jobPomPath) throws Exception {
        if (jobPomPath == null) {
            return;
        }
        IFile projectPomFile = codeProject.getProject().getFile(MavenConstants.POM_FILE_NAME);
        if (projectPomFile.exists()) {
            MavenModelManager mavenModelManager = MavenPlugin.getMavenModelManager();
            Model projModel = mavenModelManager.readMavenModel(projectPomFile);
            List<String> modules = projModel.getModules();
            if (modules == null) {
                modules = new ArrayList<String>();
                projModel.setModules(modules);
            }
            boolean found = false;
            for (String m : modules) {
                if (m.equals(jobPomPath)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                modules.add(jobPomPath);
            }

            // save pom.
            PomManager.savePom(null, projModel, projectPomFile);
        }
    }
}
