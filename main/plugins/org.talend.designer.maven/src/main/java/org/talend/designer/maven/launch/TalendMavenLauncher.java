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
package org.talend.designer.maven.launch;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IDebugEventSetListener;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.RuntimeProcess;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.debug.ui.RefreshTab;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.m2e.actions.MavenLaunchConstants;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.internal.IMavenConstants;
import org.eclipse.m2e.core.project.IMavenProjectFacade;
import org.eclipse.m2e.core.project.IMavenProjectRegistry;
import org.eclipse.m2e.core.project.ResolverConfiguration;
import org.eclipse.osgi.util.NLS;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.designer.maven.model.MavenConstants;

/**
 * created by ggu on 13 Mar 2015 Detailled comment
 *
 * most codes are copied from @see ExecutePomAction. just in order to set the debug is in foreground.
 */
@SuppressWarnings("restriction")
public class TalendMavenLauncher {

    private IFile launcherPomFile;

    private String goals;

    private String launcherMode = ILaunchManager.RUN_MODE;

    public TalendMavenLauncher(IFile pomFile, String goals) {
        super();
        Assert.isNotNull(pomFile);
        Assert.isNotNull(goals);
        this.launcherPomFile = pomFile;
        this.goals = goals;
    }

    public TalendMavenLauncher(IFile pomFile) {
        this(pomFile, MavenConstants.GOAL_COMPILE);
    }

    private ILaunchConfiguration createLaunchConfiguration(IContainer basedir, String goal) {
        try {
            ILaunchManager launchManager = DebugPlugin.getDefault().getLaunchManager();
            ILaunchConfigurationType launchConfigurationType = launchManager
                    .getLaunchConfigurationType(MavenLaunchConstants.LAUNCH_CONFIGURATION_TYPE_ID);

            String launchSafeGoalName = goal.replace(':', '-');

            ILaunchConfigurationWorkingCopy workingCopy = launchConfigurationType.newInstance(
                    null, //
                    NLS.bind(org.eclipse.m2e.internal.launch.Messages.ExecutePomAction_executing, launchSafeGoalName, basedir
                            .getLocation().toString().replace('/', '-')));
            workingCopy.setAttribute(MavenLaunchConstants.ATTR_POM_DIR, basedir.getLocation().toOSString());
            workingCopy.setAttribute(MavenLaunchConstants.ATTR_GOALS, goal);
            workingCopy.setAttribute(IDebugUIConstants.ATTR_PRIVATE, true);
            workingCopy.setAttribute(RefreshTab.ATTR_REFRESH_SCOPE, "${project}"); //$NON-NLS-1$
            workingCopy.setAttribute(RefreshTab.ATTR_REFRESH_RECURSIVE, true);

            setProjectConfiguration(workingCopy, basedir);

            IPath path = getJREContainerPath(basedir);
            if (path != null) {
                workingCopy.setAttribute(IJavaLaunchConfigurationConstants.ATTR_JRE_CONTAINER_PATH, path.toPortableString());
            }

            // TODO when launching Maven with debugger consider to add the following property
            // -Dmaven.surefire.debug="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=8000 -Xnoagent -Djava.compiler=NONE"

            return workingCopy;
        } catch (CoreException ex) {
            ExceptionHandler.process(ex);
        }
        return null;
    }

    private void setProjectConfiguration(ILaunchConfigurationWorkingCopy workingCopy, IContainer basedir) {
        IMavenProjectRegistry projectManager = MavenPlugin.getMavenProjectRegistry();
        IFile pomFile = basedir.getFile(new Path(IMavenConstants.POM_FILE_NAME));
        IMavenProjectFacade projectFacade = projectManager.create(pomFile, false, new NullProgressMonitor());
        if (projectFacade != null) {
            ResolverConfiguration configuration = projectFacade.getResolverConfiguration();

            String selectedProfiles = configuration.getSelectedProfiles();
            if (selectedProfiles != null && selectedProfiles.length() > 0) {
                workingCopy.setAttribute(MavenLaunchConstants.ATTR_PROFILES, selectedProfiles);
            }
        }
    }

    // TODO ideally it should use MavenProject, but it is faster to scan IJavaProjects
    private IPath getJREContainerPath(IContainer basedir) throws CoreException {
        IProject project = basedir.getProject();
        if (project != null && project.hasNature(JavaCore.NATURE_ID)) {
            IJavaProject javaProject = JavaCore.create(project);
            IClasspathEntry[] entries = javaProject.getRawClasspath();
            for (IClasspathEntry entry : entries) {
                if (JavaRuntime.JRE_CONTAINER.equals(entry.getPath().segment(0))) {
                    return entry.getPath();
                }
            }
        }
        return null;
    }

    public void execute() {
        if (!launcherPomFile.exists()) {
            return;
        }
        // non-pom file
        if (!MavenConstants.POM_FILE_NAME.equals(launcherPomFile.getName())) {
            return;
        }
        /*
         * use the ExecutePomAction directly.
         */
        // ExecutePomAction exePomAction = new ExecutePomAction();
        // exePomAction.setInitializationData(null, null, MavenConstants.GOAL_COMPILE);
        // exePomAction.launch(new StructuredSelection(launcherPomFile), ILaunchManager.RUN_MODE);

        /*
         * use launch way
         */
        try {
            ILaunchConfiguration launchConfiguration = createLaunchConfiguration(launcherPomFile.getParent(), goals);
            if (launchConfiguration instanceof ILaunchConfigurationWorkingCopy) {
                ILaunchConfigurationWorkingCopy copiedConfig = (ILaunchConfigurationWorkingCopy) launchConfiguration;
                copiedConfig.setAttribute(DebugPlugin.ATTR_CAPTURE_OUTPUT, true);
            }
            TalendLauncherHelper launcherHelper = new TalendLauncherHelper(launchConfiguration);

            DebugUITools.buildAndLaunch(launchConfiguration, launcherMode, new NullProgressMonitor());

            launcherHelper.waitFinish();

        } catch (CoreException e) {
            ExceptionHandler.process(e);
        }
        //
    }

    /**
     * 
     * created by ggu on 16 Mar 2015 Detailled comment
     *
     */
    static class TalendLauncherHelper implements IDebugEventSetListener {

        private ILaunchConfiguration launchConfig;

        private boolean launchFinished = false;

        public TalendLauncherHelper(ILaunchConfiguration launchConfig) {
            super();
            this.launchConfig = launchConfig;
            DebugPlugin.getDefault().addDebugEventListener(this);
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.debug.core.IDebugEventSetListener#handleDebugEvents(org.eclipse.debug.core.DebugEvent[])
         */
        @Override
        public void handleDebugEvents(DebugEvent[] events) {
            // launchConfig.getType();
            for (DebugEvent event : events) {
                Object source = event.getSource();
                if (source instanceof RuntimeProcess
                        && ((RuntimeProcess) source).getLaunch().getLaunchConfiguration() == launchConfig
                        && event.getKind() == DebugEvent.TERMINATE) {
                    DebugPlugin.getDefault().removeDebugEventListener(this);
                    launchFinished = true;
                    break;
                }
            }

        }

        public void waitFinish() {
            try {
                while (!launchFinished) {
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                ExceptionHandler.process(e);
            }
        }
    }
}
