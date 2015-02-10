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

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.utils.VersionUtils;
import org.talend.core.model.process.IContext;
import org.talend.core.model.process.IProcess;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.properties.Project;
import org.talend.core.model.properties.Property;
import org.talend.core.model.repository.SVNConstant;
import org.talend.core.model.utils.JavaResourcesHelper;
import org.talend.core.runtime.process.JobInfoProperties;
import org.talend.designer.maven.model.MavenConstants;
import org.talend.designer.maven.model.TalendMavenContants;
import org.talend.designer.runprocess.IProcessor;
import org.talend.repository.ProjectManager;

/**
 * created by ggu on 4 Feb 2015 Detailled comment
 *
 */
public class CreateJobTemplateMavenPom extends CreateTemplateMavenPom {

    private final IProcessor jobProcessor;

    private boolean applyContextToChild, addStat;

    private String windowsClasspath, unixClasspath;

    public CreateJobTemplateMavenPom(IProcessor jobProcessor, IFile pomFile, String templatePomFile) {
        super(pomFile, templatePomFile);
        this.jobProcessor = jobProcessor;
        Assert.isNotNull(jobProcessor);
    }

    public IProcessor getJobProcessor() {
        return this.jobProcessor;
    }

    public boolean isApplyContextToChild() {
        return this.applyContextToChild;
    }

    public void setApplyContextToChild(boolean applyContextToChild) {
        this.applyContextToChild = applyContextToChild;
    }

    public boolean isAddStat() {
        return this.addStat;
    }

    public void setAddStat(boolean addStat) {
        this.addStat = addStat;
    }

    public String getWindowsClasspath() {
        return this.windowsClasspath;
    }

    public void setWindowsClasspath(String windowsClasspath) {
        this.windowsClasspath = windowsClasspath;
    }

    public String getUnixClasspath() {
        return this.unixClasspath;
    }

    public void setUnixClasspath(String unixClasspath) {
        this.unixClasspath = unixClasspath;
    }

    @Override
    protected Model createModel() {
        Model model = super.createModel();
        //
        final IProcessor jProcessor = getJobProcessor();
        final IProcess process = jProcessor.getProcess();

        model.setGroupId(generateGroupId(jProcessor));
        model.setArtifactId(JavaResourcesHelper.escapeFileName(process.getName()));
        model.setVersion(process.getVersion());

        addProperties(model);
        addDependencies(model);

        return model;
    }

    /**
     * 
     * According to the process, generate the groud id, like org.talend.process.di.demo.
     */
    protected String generateGroupId(final IProcessor jProcessor) {
        final Property property = jProcessor.getProperty();
        final IProcess process = jProcessor.getProcess();

        String groupId = JavaResourcesHelper.getGroupName(JavaResourcesHelper.getProjectFolderName(property.getItem()));

        if (process.getComponentsType() != null) {
            groupId += '.' + process.getComponentsType().toLowerCase();
        }
        return groupId;
    }

    /**
     * 
     * Add the properties for job.
     */
    @SuppressWarnings("nls")
    protected void addProperties(Model model) {
        Properties properties = model.getProperties();
        if (properties == null) {
            properties = new Properties();
            model.setProperties(properties);
        }
        final IProcessor jProcessor = getJobProcessor();
        final IProcess process = jProcessor.getProcess();
        final IContext context = jProcessor.getContext();
        final Property property = jProcessor.getProperty();

        // same as JavaProcessor.initCodePath
        String jobClassPackageFolder = JavaResourcesHelper.getJobClassPackageFolder(property.getItem());
        String jobClassPackage = JavaResourcesHelper.getJobClassPackageName(property.getItem());
        String jobFolderName = JavaResourcesHelper.getJobFolderName(property.getLabel(), property.getVersion());

        Project project = ProjectManager.getInstance().getProject(property);
        if (project == null) { // current project
            project = ProjectManager.getInstance().getCurrentProject().getEmfProject();
        }
        String mainProjectBranch = ProjectManager.getInstance().getMainProjectBranch(project);
        if (mainProjectBranch == null) {
            mainProjectBranch = SVNConstant.NAME_TRUNK;
        }

        JobInfoProperties jobInfoProp = new JobInfoProperties((ProcessItem) property.getItem(), context.getName(),
                isApplyContextToChild(), isAddStat());

        checkPomProperty(properties, "talend.job.path", "@JobPath@", jobClassPackageFolder);
        checkPomProperty(properties, "talend.job.package", "@JobPackage@", jobClassPackage);
        checkPomProperty(properties, "talend.job.jarName", "@JobJarName@", jobFolderName);
        /*
         * for jobInfo.properties
         * 
         * should be same as JobInfoBuilder
         */
        checkPomProperty(properties, "talend.project.name", "@ProjectName@",
                jobInfoProp.getProperty(JobInfoProperties.PROJECT_NAME, project.getTechnicalLabel()));
        checkPomProperty(properties, "talend.project.id", "@ProjectId@",
                jobInfoProp.getProperty(JobInfoProperties.PROJECT_ID, String.valueOf(project.getId())));
        checkPomProperty(properties, "talend.project.branch", "@ProjectBranch@",
                jobInfoProp.getProperty(JobInfoProperties.BRANCH, mainProjectBranch));
        checkPomProperty(properties, "talend.job.name", "@JobName@", "${project.artifactId}");
        checkPomProperty(properties, "talend.job.version", "@JobVersion@", "${project.version}");
        checkPomProperty(properties, "talend.job.date", "@JobDate@",
                jobInfoProp.getProperty(JobInfoProperties.DATE, JobInfoProperties.DATAFORMAT.format(new Date())));
        checkPomProperty(properties, "talend.job.context", "@JobContext@",
                jobInfoProp.getProperty(JobInfoProperties.CONTEXT_NAME, context.getName()));
        checkPomProperty(properties, "talend.job.id", "@JobId@",
                jobInfoProp.getProperty(JobInfoProperties.JOB_ID, process.getId()));
        // checkPomProperty(properties, "talend.job.class", "@JobClass@", jProcessor.getMainClass());//
        checkPomProperty(properties, "talend.job.class", "@JobClass@", "${talend.job.package}.${talend.job.name}");
        checkPomProperty(properties, "talend.job.stat", "@JobStat@",
                jobInfoProp.getProperty(JobInfoProperties.ADD_STATIC_CODE, Boolean.FALSE.toString()));
        checkPomProperty(properties, "talend.job.applyContextToChildren", "@JobApplyContextToChildren@",
                jobInfoProp.getProperty(JobInfoProperties.APPLY_CONTEXY_CHILDREN, Boolean.FALSE.toString()));
        checkPomProperty(properties, "talend.product.version", "@ProductVersion@",
                jobInfoProp.getProperty(JobInfoProperties.COMMANDLINE_VERSION, VersionUtils.getVersion()));

        /*
         * for bat/sh in assembly
         */
        checkPomProperty(properties, "talend.job.bat.classpath", "@JobBatClasspath@", this.getWindowsClasspath());
        checkPomProperty(properties, "talend.job.sh.classpath", "@JobShClasspath@", this.getUnixClasspath());

        /*
         * build properties
         */
        checkPomProperty(properties, "build.java.level", "@BuildJavaLevel@", TalendMavenContants.DEFAULT_JDK_VERSION);
        checkPomProperty(properties, "build.encoding", "@BuildEncoding@", TalendMavenContants.DEFAULT_ENCODING);
    }

    protected void checkPomProperty(Properties properties, String key, String var, String value) {
        Object v = properties.get(key);
        if (v != null) {
            if (v.equals(value)) { // same
                // nothing to do
            } else if (v.equals(var)) {// if var expression. just replace it.
                properties.put(key, value);
            } else {// replace var, if existed.
                properties.put(key, v.toString().replace(var, value));
            }
        } else { // set new value directly.
            properties.put(key, value);
        }
    }

    /**
     * add dependencies for pom.
     */
    protected void addDependencies(Model model) {
        List<Dependency> dependencies = model.getDependencies();
        // add the job modules.
        Set<String> neededLibraries = getJobProcessor().getNeededLibraries();
        for (String lib : neededLibraries) {
            String name = new Path(lib).removeFileExtension().toString();

            Dependency dependency = new Dependency();
            dependency.setGroupId(name);
            dependency.setArtifactId(name);
            dependency.setVersion(TalendMavenContants.DEFAULT_VERSION);
            dependency.setScope("system"); //$NON-NLS-1$
            dependency.setSystemPath("${system.lib.path}/" + lib); //$NON-NLS-1$

            dependencies.add(dependency);
        }
    }

    @Override
    public void create(IProgressMonitor monitor) throws Exception {
        super.create(monitor);
        generateAssemblyFile();
    }

    protected void generateAssemblyFile() {
        IFile assemblyFile = getPomFile().getParent().getFile(new Path(MavenConstants.ASSEMBLY_FILE_NAME));
        try {
            MavenTemplateManager
                    .copyTemplate(MavenTemplateConstants.JOB_ASSEMBLY_TEMPLATE_FILE_NAME, assemblyFile, isOverwrite());
        } catch (IOException e) {
            ExceptionHandler.process(e);
        }
    }

}
