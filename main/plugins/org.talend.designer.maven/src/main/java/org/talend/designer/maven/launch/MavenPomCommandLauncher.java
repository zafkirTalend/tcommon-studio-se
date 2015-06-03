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

import org.apache.maven.model.Model;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.embedder.MavenModelManager;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.designer.maven.model.TalendMavenConstants;

/**
 * created by ggu on 13 Mar 2015 Detailled comment
 *
 */

public class MavenPomCommandLauncher extends MavenCommandLauncher {

    private final IFile launcherPomFile;

    public MavenPomCommandLauncher(IFile pomFile, String goals) {
        super(goals);
        Assert.isNotNull(pomFile);
        this.launcherPomFile = pomFile;
    }

    /*
     * shouldn't use clean goal, because the routines and some other(children) jobs use same output target/classes, if
     * add clean will clean the before build classes.
     */
    public MavenPomCommandLauncher(IFile pomFile) {
        this(pomFile, /* MavenConstants.GOAL_CLEAN+' '+ */TalendMavenConstants.GOAL_COMPILE);
    }

    @Override
    protected ILaunchConfiguration createLaunchConfiguration() {
        return createLaunchConfiguration(launcherPomFile.getParent(), getGoals());
    }

    @Override
    public void execute(IProgressMonitor monitor) throws Exception {
        if (!launcherPomFile.exists()) {
            return;
        }
        if (getGoals().contains(TalendMavenConstants.GOAL_PACKAGE)) {
            // clean all dependencies from pom.xml
            try {
                MavenModelManager mavenModelManager = MavenPlugin.getMavenModelManager();
                Model model;
                model = mavenModelManager.readMavenModel(launcherPomFile);
                model.getDependencies().clear();
                if (launcherPomFile.exists()) {
                    launcherPomFile.delete(true, null);
                }
                MavenPlugin.getMavenModelManager().createMavenModel(launcherPomFile, model);
            } catch (CoreException e) {
                ExceptionHandler.process(e);
            }
        }
        // non-pom file
        if (!TalendMavenConstants.POM_FILE_NAME.equals(launcherPomFile.getName())) {
            return;
        }
        super.execute(monitor);
    }

}
