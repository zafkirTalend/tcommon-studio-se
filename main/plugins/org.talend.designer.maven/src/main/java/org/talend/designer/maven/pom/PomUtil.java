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

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.Parent;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.m2e.core.MavenPlugin;
import org.talend.commons.utils.VersionUtils;
import org.talend.core.model.general.Project;
import org.talend.core.model.process.IProcess;
import org.talend.core.model.process.JobInfo;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.properties.Property;
import org.talend.core.model.utils.JavaResourcesHelper;
import org.talend.designer.maven.model.MavenConstants;
import org.talend.designer.maven.model.TalendMavenContants;
import org.talend.designer.runprocess.IProcessor;
import org.talend.repository.ProjectManager;

/**
 * created by ggu on 6 Feb 2015 Detailled comment
 *
 */
public class PomUtil {

    public static void savePom(IProgressMonitor monitor, Model model, IFile pomFile) throws Exception {
        if (monitor == null) {
            monitor = new NullProgressMonitor();
        }
        /*
         * need find one way to do overwrite.
         */
        // IModelManager modelManager = StructuredModelManager.getModelManager();
        // IStructuredModel sModel = modelManager.getModelForRead(pomFile);
        // IDOMModel domModel = (IDOMModel)
        // modelManager.getModelForEdit(sModel.getStructuredDocument());
        // ElementValueProvider privider = new ElementValueProvider(PomEdits.ARTIFACT_ID);
        // Element el = privider.get(domModel.getDocument());
        // PomEdits.setText(el, model.getArtifactId());
        // sModel.save();

        pomFile.delete(true, monitor);
        MavenPlugin.getMavenModelManager().createMavenModel(pomFile, model);
    }

    /**
     * 
     * just unify for routines with template.
     */
    private static Model templateRoutinesModel, templateCodeProjectMOdel;

    public static Model getRoutinesTempalteModel() {
        if (templateRoutinesModel == null) {
            templateRoutinesModel = new Model();

            templateRoutinesModel.setGroupId(TalendMavenContants.DEFAULT_ROUTINES_GROUP_ID);
            templateRoutinesModel.setArtifactId(TalendMavenContants.DEFAULT_ROUTINES_ARTIFACT_ID);
            templateRoutinesModel.setVersion(PomUtil.getDefaultMavenVersion());
        }
        return templateRoutinesModel;
    }

    public static Model getCodeProjectTempalteModel() {
        if (templateCodeProjectMOdel == null) {
            templateCodeProjectMOdel = new Model();

            templateCodeProjectMOdel.setGroupId(TalendMavenContants.DEFAULT_CODE_PROJECT_GROUP_ID);
            templateCodeProjectMOdel.setArtifactId(TalendMavenContants.DEFAULT_CODE_PROJECT_ARTIFACT_ID);
            templateCodeProjectMOdel.setVersion(PomUtil.getDefaultMavenVersion());

        }
        return templateCodeProjectMOdel;
    }

    public static Model getJunitTempalteModel() {
        Model junitModel = new Model();
        junitModel.setGroupId(TalendMavenContants.DEFAULT_JUNIT_ARTIFACT_GROUP);
        junitModel.setArtifactId(TalendMavenContants.DEFAULT_JUNIT_ARTIFACT_ID);
        junitModel.setVersion(TalendMavenContants.DEFAULT_JUNIT_ARTIFACT_VERSION);
        return junitModel;
    }

    public static boolean isJunitArtifact(String artifactId) {
        if (TalendMavenContants.DEFAULT_JUNIT_ARTIFACT_ID.equals(artifactId)) {
            return true;
        }
        return false;
    }

    /**
     * 
     * something like org.talend.demo.
     */
    public static String getCurProjectGroup() {
        final Project currentProject = ProjectManager.getInstance().getCurrentProject();
        return JavaResourcesHelper.getGroupName(currentProject.getTechnicalLabel());
    }

    /**
     * 
     * get the pom name, if name is null, return default one "pom.xml", else will be "pom_<name>.xml"
     */
    public static String getPomFileName(String name) {
        String pomFileName = MavenConstants.POM_FILE_NAME;
        if (name != null && name.length() > 0) {
            pomFileName = MavenConstants.POM_NAME + '_' + name + MavenConstants.XML_EXT;
        }
        return pomFileName;
    }

    /**
     * get the assembly name, if name is null, return default one "assembly.xml", else will be "assembly_<name>.xml"
     */
    public static String getAssemblyFileName(String name) {
        String assemblyFileName = MavenConstants.ASSEMBLY_FILE_NAME;
        if (name != null && name.length() > 0) {
            assemblyFileName = MavenConstants.ASSEMBLY_NAME + '_' + name + MavenConstants.XML_EXT;
        }
        return assemblyFileName;
    }

    /**
     * 
     * DOC ggu Comment method "getDefaultMavenVersion".
     * 
     * @return 6.0.0, without classifier.
     */
    public static String getDefaultMavenVersion() {
        String version = VersionUtils.getVersion();
        try {
            org.osgi.framework.Version v = new org.osgi.framework.Version(version);
            // only get major.minor.micro
            org.osgi.framework.Version simpleVersion = new org.osgi.framework.Version(v.getMajor(), v.getMinor(), v.getMicro());
            version = simpleVersion.toString();
        } catch (IllegalArgumentException e) {
            version = TalendMavenContants.DEFAULT_VERSION;
        }
        return version;
    }

    /**
     * 
     * DOC ggu Comment method "checkParent". make sure the parent are unified.
     * 
     * @param curModel
     * @param curPomFile
     */
    public static void checkParent(Model curModel, IFile curPomFile) {
        Parent parent = curModel.getParent();
        if (parent == null) {
            parent = new Parent();
            curModel.setParent(parent);
        }
        Model codeProjectTempalteModel = null;
        try {
            IFile projectPomFile = curPomFile.getParent().getFile(new Path(MavenConstants.POM_FILE_NAME));
            if (projectPomFile.exists()) {
                codeProjectTempalteModel = MavenPlugin.getMavenModelManager().readMavenModel(projectPomFile);
            }
        } catch (CoreException e) {
            //
        }
        if (codeProjectTempalteModel == null) {
            /*
             * same CreateMavenCodeProject.covertJavaProjectToPom
             */
            codeProjectTempalteModel = PomUtil.getCodeProjectTempalteModel();
        }
        parent.setGroupId(codeProjectTempalteModel.getGroupId());
        parent.setArtifactId(codeProjectTempalteModel.getArtifactId());
        parent.setVersion(codeProjectTempalteModel.getVersion());

        parent.setRelativePath("./" + MavenConstants.POM_FILE_NAME);

    }

    /**
     * DOC ggu Comment method "createModuleSystemScopeDependency".
     * 
     * @return
     */
    public static Dependency createModuleSystemScopeDependency(String groupId, String module, String version) {
        if (module == null) {
            return null;
        }
        String artifactId = new Path(module).removeFileExtension().toString();

        Dependency dependency = new Dependency();

        dependency.setGroupId(groupId == null ? TalendMavenContants.DEFAULT_LIB_GROUP_ID : groupId);
        dependency.setArtifactId(artifactId);
        dependency.setVersion(version == null ? TalendMavenContants.DEFAULT_LIB_VERSION : version);

        // FIXME, if system scope, can't work for the assembly with dependencySets at all.
        // dependency.setScope("system");
        // dependency.setSystemPath("${system.lib.path}/" + module);

        return dependency;
    }

    /**
     * 
     * According to the process, generate the groud id, like org.talend.process.di.demo.
     */
    public static String generateGroupId(final IProcessor jProcessor) {
        final Property property = jProcessor.getProperty();
        final IProcess process = jProcessor.getProcess();

        final String projectFolderName = JavaResourcesHelper.getProjectFolderName(property.getItem());
        return generateGroupId(projectFolderName, process.getComponentsType());
    }

    public static String generateGroupId(String projectFolderName, String type) {
        String groupId = JavaResourcesHelper.getGroupName(projectFolderName);

        if (type != null) {
            groupId += '.' + type.toLowerCase();
        }
        return groupId;
    }

    public static String generateGroupId(final JobInfo jobInfo) {
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
}
