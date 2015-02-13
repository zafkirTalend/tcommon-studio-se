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

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.Plugin;
import org.apache.maven.model.PluginExecution;
import org.codehaus.plexus.util.xml.Xpp3Dom;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.utils.VersionUtils;
import org.talend.commons.utils.generation.JavaUtils;
import org.talend.commons.utils.resource.FileExtensions;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.model.process.IContext;
import org.talend.core.model.process.IProcess;
import org.talend.core.model.process.JobInfo;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.properties.Project;
import org.talend.core.model.properties.Property;
import org.talend.core.model.repository.IRepositoryViewObject;
import org.talend.core.model.repository.SVNConstant;
import org.talend.core.model.utils.JavaResourcesHelper;
import org.talend.core.runtime.CoreRuntimePlugin;
import org.talend.core.runtime.process.JobInfoProperties;
import org.talend.designer.core.IDesignerCoreService;
import org.talend.designer.maven.model.MavenConstants;
import org.talend.designer.maven.model.MavenSystemFolders;
import org.talend.designer.maven.model.TalendMavenContants;
import org.talend.designer.maven.utils.TalendCodeProjectUtil;
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

    private Set<JobInfo> clonedJobInfos = new HashSet<JobInfo>();

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

    private Set<JobInfo> getClonedJobInfos() {
        if (this.clonedJobInfos.isEmpty()) {
            Set<JobInfo> buildChildrenJobs = getJobProcessor().getBuildChildrenJobs();
            for (JobInfo jobInfo : buildChildrenJobs) {
                JobInfo newJobInfo = new JobInfo(jobInfo.getJobId(), jobInfo.getContextName(), jobInfo.getJobVersion());

                newJobInfo.setJobName(jobInfo.getJobName());
                newJobInfo.setApplyContextToChildren(jobInfo.isApplyContextToChildren());
                newJobInfo.setContext(jobInfo.getContext());
                newJobInfo.setProjectFolderName(jobInfo.getProjectFolderName());
                newJobInfo.setProcessItem(jobInfo.getProcessItem());

                ProcessItem processItem = newJobInfo.getProcessItem();
                if (processItem == null) {
                    try {
                        final IRepositoryViewObject obj = CoreRuntimePlugin.getInstance().getProxyRepositoryFactory()
                                .getSpecificVersion(jobInfo.getJobId(), jobInfo.getJobVersion(), true);
                        if (obj != null) {
                            final Item item = obj.getProperty().getItem();
                            if (item instanceof ProcessItem) {
                                processItem = (ProcessItem) item;
                                newJobInfo.setProcessItem(processItem);
                            }
                        }
                    } catch (PersistenceException e) {
                        //
                    }

                }

                if (processItem != null) {
                    IProcess process = jobInfo.getProcess();
                    // get the type
                    if (process == null && GlobalServiceRegister.getDefault().isServiceRegistered(IDesignerCoreService.class)) {
                        IDesignerCoreService service = (IDesignerCoreService) GlobalServiceRegister.getDefault().getService(
                                IDesignerCoreService.class);
                        process = service.getProcessFromItem(processItem);
                        newJobInfo.setProcess(process);
                    }
                    final String projectFolderName = JavaResourcesHelper.getProjectFolderName(processItem);
                    newJobInfo.setProjectFolderName(projectFolderName);
                }
                clonedJobInfos.add(newJobInfo);
            }
        }
        return this.clonedJobInfos;
    }

    @SuppressWarnings("nls")
    @Override
    protected Model createModel() {
        Model model = super.createModel();
        //
        final IProcessor jProcessor = getJobProcessor();
        final IProcess process = jProcessor.getProcess();

        model.setGroupId(generateGroupId(jProcessor));
        final String jobName = JavaResourcesHelper.escapeFileName(process.getName());
        String artifact = jobName;
        if (TalendCodeProjectUtil.stripVersion) { // in order to keep with version for jar always.
            artifact = JavaResourcesHelper.getJobJarName(jobName, process.getVersion());
        }
        model.setArtifactId(artifact);
        model.setVersion(process.getVersion());

        addProperties(model);
        addDependencies(model);
        if (TalendCodeProjectUtil.stripVersion) {
            // will same as artifact id
            model.getBuild().setFinalName("${project.artifactId}");

            // also need change the finalName for assembly.
            final Map<String, Plugin> pluginsAsMap = model.getBuild().getPluginsAsMap();
            for (String key : pluginsAsMap.keySet()) {
                final Plugin plugin = pluginsAsMap.get(key);
                if ("maven-assembly-plugin".equals(plugin.getArtifactId())
                        && "org.apache.maven.plugins".equals(plugin.getGroupId())) {
                    final List<PluginExecution> executions = plugin.getExecutions();
                    for (PluginExecution execution : executions) {
                        final Object configuration = execution.getConfiguration();
                        if (configuration instanceof Xpp3Dom) {
                            final Xpp3Dom finalNameElem = ((Xpp3Dom) configuration).getChild("finalName");
                            finalNameElem.setValue("${project.artifactId}");
                        }
                    }
                    break; // only deal for assembly.
                }
            }
        }
        return model;
    }

    /**
     * 
     * According to the process, generate the groud id, like org.talend.process.di.demo.
     */
    protected String generateGroupId(final IProcessor jProcessor) {
        final Property property = jProcessor.getProperty();
        final IProcess process = jProcessor.getProcess();

        final String projectFolderName = JavaResourcesHelper.getProjectFolderName(property.getItem());
        return generateGroupId(projectFolderName, process.getComponentsType());
    }

    protected String generateGroupId(String projectFolderName, String type) {
        String groupId = JavaResourcesHelper.getGroupName(projectFolderName);

        if (type != null) {
            groupId += '.' + type.toLowerCase();
        }
        return groupId;
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
        if (TalendCodeProjectUtil.stripVersion) {
            checkPomProperty(properties, "talend.job.name", null, // keep empty, in order to replace it.
                    jobInfoProp.getProperty(JobInfoProperties.JOB_NAME, property.getLabel()));
        } else {
            checkPomProperty(properties, "talend.job.name", "@JobName@", "${project.artifactId}");
        }
        checkPomProperty(properties, "talend.job.version", "@JobVersion@", "${project.version}");
        checkPomProperty(properties, "talend.job.date", "@JobDate@",
                jobInfoProp.getProperty(JobInfoProperties.DATE, JobInfoProperties.DATAFORMAT.format(new Date())));
        checkPomProperty(properties, "talend.job.context", "@JobContext@",
                jobInfoProp.getProperty(JobInfoProperties.CONTEXT_NAME, context.getName()));
        checkPomProperty(properties, "talend.job.id", "@JobId@",
                jobInfoProp.getProperty(JobInfoProperties.JOB_ID, process.getId()));
        if (TalendCodeProjectUtil.stripVersion) {
            checkPomProperty(properties, "talend.job.class", null, // keep empty, in order to replace it.
                    jProcessor.getMainClass());
        } else {
            checkPomProperty(properties, "talend.job.class", "@JobClass@", "${talend.job.package}.${talend.job.name}");
        }
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
            } else if (var == null || var.trim().length() == 0) { // just replace it for new value.
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
        if (dependencies == null) {
            dependencies = new ArrayList<Dependency>();
            model.setDependencies(dependencies);
        }
        // check the routine.
        if (!dependencies.isEmpty()) {
            Dependency routinesDependency = null;
            final Model routinesModel = TalendCodeProjectUtil.getRoutinesTempalteModel();

            String artifact = TalendMavenContants.DEFAULT_ROUTINES_ARTIFACT_ID;
            if (TalendCodeProjectUtil.stripVersion) { // in order to keep with version for jar always.
                artifact = JavaResourcesHelper.getJobJarName(TalendMavenContants.DEFAULT_ROUTINES_ARTIFACT_ID,
                        JavaUtils.ROUTINE_JAR_DEFAULT_VERSION);
            }

            for (Dependency d : dependencies) {
                if (TalendMavenContants.DEFAULT_ROUTINES_ARTIFACT_ID.equals(d.getArtifactId())
                        || artifact.equals(d.getArtifactId())) {
                    routinesDependency = d;
                    break;
                }
            }
            if (routinesDependency == null) {
                routinesDependency = new Dependency();
                routinesDependency.setGroupId(routinesModel.getGroupId());
                routinesDependency.setVersion(routinesModel.getVersion());
                dependencies.add(routinesDependency);
            }
            // update the routine artifact.
            routinesDependency.setArtifactId(routinesModel.getArtifactId());

        }

        // add the job modules.
        Set<String> neededLibraries = getJobProcessor().getNeededLibraries();
        for (String lib : neededLibraries) {
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

            // add children resources in assembly.
            addChildrenJobsInAssembly(assemblyFile);
        } catch (Exception e) {
            ExceptionHandler.process(e);
        }
    }

    @SuppressWarnings("nls")
    protected void addChildrenJobsInAssembly(IFile assemblyFile) throws Exception {
        if (!assemblyFile.exists()) {
            return;
        }
        final File file = assemblyFile.getLocation().toFile();
        // assemblyFile.get
        SAXReader reader = new SAXReader();

        Document document = reader.read(file);
        //
        Element filesElem = getElement(document, "files");
        Element fileSetElem = getElement(document, "fileSets");

        final Set<JobInfo> clonedChildrenJobInfors = getClonedJobInfos();
        for (JobInfo child : clonedChildrenJobInfors) {
            // same as JavaProcessor.getExportJarsStr, the jar with version always.
            String childJobJarName = JavaResourcesHelper.getJobJarName(child.getJobName(), child.getJobVersion());
            // child jar
            addAssemblyFiles(filesElem, "${project.build.directory}/lib/" + childJobJarName + FileExtensions.JAR_FILE_SUFFIX,
                    "${project.artifactId}", null, null, null, false,
                    "Copy the job jar from lib for child job " + child.getJobName());
            // TODO, exclude the child jar in lib. type="lib"

            // child sources.
            final String projectRootPath = "${project.root.path}";
            final String outFolder = "${project.artifactId}";
            String jobClassPackageFolder = JavaResourcesHelper.getJobClassPackageFolder(child.getProcessItem());
            String jobClassProjectFolder = MavenSystemFolders.JAVA.getPath() + '/' + jobClassPackageFolder;
            addAssemblyFileSets(fileSetElem, projectRootPath + '/' + jobClassProjectFolder, outFolder + '/'
                    + jobClassProjectFolder, false, Arrays.asList(new String[] { "*/**" }), null, null, null, null, false,
                    "add source,contexts,pom, assembly for child job " + child.getJobName());
            // conext resources

            String contextRootFolder = "${project.resources.path}/" + jobClassPackageFolder + '/'
                    + JavaUtils.JAVA_CONTEXTS_DIRECTORY;
            addAssemblyFileSets(fileSetElem, contextRootFolder, outFolder + '/' + MavenSystemFolders.RESOURCES.getPath() + '/'
                    + jobClassPackageFolder + '/' + JavaUtils.JAVA_CONTEXTS_DIRECTORY, false,
                    Arrays.asList(new String[] { "*.properties" }), null, null, null, null, false,
                    "add the child contexts file to resources for " + child.getJobName());
            // context for running
            addAssemblyFileSets(fileSetElem, contextRootFolder, outFolder + '/' + jobClassPackageFolder + '/'
                    + JavaUtils.JAVA_CONTEXTS_DIRECTORY, false, Arrays.asList(new String[] { "*.properties" }), null, null, null,
                    null, false, "add the child contexts files to run for " + child.getJobName());

        }

        XMLWriter writer = null;
        try {
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding(TalendMavenContants.DEFAULT_ENCODING);
            writer = new XMLWriter(new FileWriter(file), format);
            writer.write(document);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    private Element getElement(Document document, String elemName) {
        final Element rootElement = document.getRootElement();
        Element fileSetsElem = rootElement.element(elemName);
        if (fileSetsElem == null) {
            fileSetsElem = rootElement.addElement(elemName);
        }
        return fileSetsElem;
    }

    @SuppressWarnings("nls")
    private void addAssemblyFiles(Element filesElem, String source, String outputDirectory, String destName, String fileMode,
            String lineEnding, boolean filtered, String comment) {
        Assert.isNotNull(filesElem);
        Assert.isNotNull(source);
        Assert.isNotNull(outputDirectory);

        Element fileEle = filesElem.addElement("file");

        if (comment != null) {
            fileEle.addComment(comment);
        }

        fileEle.addElement("source").setText(source);
        fileEle.addElement("outputDirectory").setText(outputDirectory);
        if (destName != null) { // if not set, will be same as source
            fileEle.addElement("destName").setText(destName);
        }
        if (fileMode != null) {
            fileEle.addElement("fileMode").setText(fileMode);
        }
        if (lineEnding != null) {
            fileEle.addElement("lineEnding").setText(lineEnding);
        }
        if (filtered) { // by default is false,
            fileEle.addElement("filtered").setText(Boolean.TRUE.toString());
        }
    }

    @SuppressWarnings("nls")
    private void addAssemblyFileSets(Element fileSetsElem, String directory, String outputDirectory, boolean useDefaultExcludes,
            List<String> includes, List<String> excludes, String fileMode, String directoryMode, String lineEnding,
            boolean filtered, String comment) {
        Assert.isNotNull(fileSetsElem);
        Assert.isNotNull(outputDirectory);
        Assert.isNotNull(directory);

        Element fileSetEle = fileSetsElem.addElement("fileSet");

        if (comment != null) {
            fileSetEle.addComment(comment);
        }

        fileSetEle.addElement("outputDirectory").setText(outputDirectory);
        fileSetEle.addElement("directory").setText(directory);
        if (useDefaultExcludes) { // false by default
            fileSetEle.addElement("useDefaultExcludes").setText(Boolean.TRUE.toString());
        }

        if (includes != null && !includes.isEmpty()) {
            Element includesEle = fileSetEle.addElement("includes");
            for (String in : includes) {
                includesEle.addElement("include").setText(in);
                ;
            }
        }

        if (excludes != null && !excludes.isEmpty()) {
            Element excludesEle = fileSetEle.addElement("excludes");
            for (String ex : excludes) {
                excludesEle.addElement("exclude").setText(ex);
                ;
            }
        }
        if (fileMode != null) {
            fileSetEle.addElement("fileMode").setText(fileMode);
        }
        if (directoryMode != null) {
            fileSetEle.addElement("directoryMode").setText(directoryMode);
        }

        if (lineEnding != null) {
            fileSetEle.addElement("lineEnding").setText(lineEnding);
        }
        if (filtered) { // by default is false,
            fileSetEle.addElement("filtered").setText(Boolean.TRUE.toString());
        }
    }
}
