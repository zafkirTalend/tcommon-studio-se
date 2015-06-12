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
package org.talend.designer.maven.tools.creator;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.utils.VersionUtils;
import org.talend.core.model.process.IContext;
import org.talend.core.model.process.IProcess;
import org.talend.core.model.process.JobInfo;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.properties.Project;
import org.talend.core.model.properties.Property;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.SVNConstant;
import org.talend.core.model.utils.JavaResourcesHelper;
import org.talend.core.runtime.process.JobInfoProperties;
import org.talend.core.runtime.projectsetting.IProjectSettingTemplateConstants;
import org.talend.designer.maven.template.ETalendMavenVariables;
import org.talend.designer.maven.tools.MavenPomSynchronizer;
import org.talend.designer.maven.tools.ProcessorDependenciesManager;
import org.talend.designer.maven.utils.PomIdsHelper;
import org.talend.designer.maven.utils.PomUtil;
import org.talend.designer.runprocess.IProcessor;
import org.talend.designer.runprocess.ProcessorException;
import org.talend.repository.ProjectManager;

/**
 * created by ggu on 4 Feb 2015 Detailled comment
 *
 */
public class CreateMavenTestPom extends CreateMavenBundleTemplatePom {

    private final IProcessor jobProcessor;

    private Set<JobInfo> clonedJobInfos = new HashSet<JobInfo>();

    private final ProcessorDependenciesManager processorDependenciesManager;

    public CreateMavenTestPom(IProcessor jobProcessor, IFile pomFile) {
        super(pomFile, IProjectSettingTemplateConstants.POM_TEST_TEMPLATE_FILE_NAME);
        Assert.isNotNull(jobProcessor);
        this.jobProcessor = jobProcessor;
        this.processorDependenciesManager = new ProcessorDependenciesManager(jobProcessor);
    }

    public IProcessor getJobProcessor() {
        return this.jobProcessor;
    }

    @Override
    protected Model createModel() {
        Model model = super.createModel();
        //
        final IProcessor jProcessor = getJobProcessor();
        final IProcess process = jProcessor.getProcess();

        final String jobName = JavaResourcesHelper.escapeFileName(process.getName());
        String artifact = jobName;

        model.setArtifactId(artifact);
        model.setVersion(process.getVersion());

        Property property = jProcessor.getProperty();
        if (property != null && property.getItem() != null) {
            model.setGroupId(PomIdsHelper.getTestGroupId(property));
            ERepositoryObjectType itemType = ERepositoryObjectType.getItemType(property.getItem());
            if (itemType != null) {
                model.setName(jobName + '(' + itemType.getLabel() + ')');
            }
        }

        PomUtil.checkParent(model, this.getPomFile());

        addDependencies(model);

        return model;
    }

    /**
     * 
     * Add the properties for job.
     */
    @Override
    @SuppressWarnings("nls")
    protected void addProperties(Model model) {
        super.addProperties(model);

        Properties properties = model.getProperties();

        final IProcessor jProcessor = getJobProcessor();
        final IProcess process = jProcessor.getProcess();
        final IContext context = jProcessor.getContext();
        final Property property = jProcessor.getProperty();

        // same as JavaProcessor.initCodePath
        String jobClassPackageFolder = JavaResourcesHelper.getJobClassPackageFolder(property.getItem(), true);

        Project project = ProjectManager.getInstance().getProject(property);
        if (project == null) { // current project
            project = ProjectManager.getInstance().getCurrentProject().getEmfProject();
        }
        String mainProjectBranch = ProjectManager.getInstance().getMainProjectBranch(project);
        if (mainProjectBranch == null) {
            mainProjectBranch = SVNConstant.NAME_TRUNK;
        }

        JobInfoProperties jobInfoProp = new JobInfoProperties((ProcessItem) property.getItem(), context.getName(), false, false);

        checkPomProperty(properties, "talend.job.path", ETalendMavenVariables.JobPath, jobClassPackageFolder);

        /*
         * for jobInfo.properties
         * 
         * should be same as JobInfoBuilder
         */
        checkPomProperty(properties, "talend.project.name", ETalendMavenVariables.ProjectName,
                jobInfoProp.getProperty(JobInfoProperties.PROJECT_NAME, project.getTechnicalLabel()));
        checkPomProperty(properties, "talend.project.id", ETalendMavenVariables.ProjectId,
                jobInfoProp.getProperty(JobInfoProperties.PROJECT_ID, String.valueOf(project.getId())));
        checkPomProperty(properties, "talend.project.branch", ETalendMavenVariables.ProjectBranch,
                jobInfoProp.getProperty(JobInfoProperties.BRANCH, mainProjectBranch));
        checkPomProperty(properties, "talend.job.name", ETalendMavenVariables.JobName,
                jobInfoProp.getProperty(JobInfoProperties.JOB_NAME, property.getLabel()));
        checkPomProperty(properties, "talend.job.version", ETalendMavenVariables.JobVersion,
                jobInfoProp.getProperty(JobInfoProperties.JOB_VERSION, process.getVersion()));
        checkPomProperty(properties, "talend.job.date", ETalendMavenVariables.JobDate,
                jobInfoProp.getProperty(JobInfoProperties.DATE, JobInfoProperties.DATAFORMAT.format(new Date())));
        checkPomProperty(properties, "talend.job.context", ETalendMavenVariables.JobContext,
                "--context=" + jobInfoProp.getProperty(JobInfoProperties.CONTEXT_NAME, context.getName()));
        checkPomProperty(properties, "talend.job.id", ETalendMavenVariables.JobId,
                jobInfoProp.getProperty(JobInfoProperties.JOB_ID, process.getId()));
        checkPomProperty(properties, "talend.job.class", ETalendMavenVariables.JobClass, jProcessor.getMainClass());
        // checkPomProperty(properties, "talend.job.class", ETalendMavenVariables.JobClass,
        // "${talend.job.package}.${talend.job.name}");
        checkPomProperty(properties, "talend.product.version", ETalendMavenVariables.ProductVersion,
                jobInfoProp.getProperty(JobInfoProperties.COMMANDLINE_VERSION, VersionUtils.getVersion()));

    }

    /**
     * add dependencies for pom.
     */
    protected void addDependencies(Model model) {
        try {
            processorDependenciesManager.updateDependencies(null, model);

            // add children jobs in dependencies
            final List<Dependency> dependencies = model.getDependencies();
            String parentId = getJobProcessor().getProperty().getId();
            final Set<JobInfo> clonedChildrenJobInfors = getJobProcessor().getBuildChildrenJobs();
            for (JobInfo jobInfo : clonedChildrenJobInfors) {
                if (jobInfo.getFatherJobInfo() != null && jobInfo.getFatherJobInfo().getJobId().equals(parentId)) {
                    Dependency d = PomUtil.createDependency(model.getGroupId(), jobInfo.getJobName(), jobInfo.getJobVersion(),
                            null);
                    dependencies.add(d);
                }
            }

        } catch (ProcessorException e) {
            ExceptionHandler.process(e);
        }
    }

    @Override
    public void create(IProgressMonitor monitor) throws Exception {
        super.create(monitor);
        // generate routines
        MavenPomSynchronizer pomSync = new MavenPomSynchronizer(this.getJobProcessor().getTalendJavaProject());
        pomSync.syncRoutinesPom(true);

        // refresh
        getPomFile().getParent().refreshLocal(IResource.DEPTH_ONE, monitor);
    }
}
