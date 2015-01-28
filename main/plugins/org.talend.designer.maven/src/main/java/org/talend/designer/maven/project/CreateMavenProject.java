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
package org.talend.designer.maven.project;

import org.apache.maven.model.Model;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.project.ProjectImportConfiguration;
import org.talend.commons.utils.VersionUtils;
import org.talend.designer.maven.model.MavenConstants;
import org.talend.designer.maven.model.MavenSystemFolders;
import org.talend.designer.maven.model.ProjectSystemFolder;

/**
 * created by ggu on 22 Jan 2015 Detailled comment
 *
 */
public class CreateMavenProject {

    /* by default, the version is same as product */
    private String version = VersionUtils.getVersion();

    private String groupId, artifactId;

    private String name, desc;

    /* by default, the packaging is jar */
    private String packaging = MavenConstants.PACKAGING_JAR;

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return this.artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPackaging() {
        return this.packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    /**
     * 
     * According to setting, create Maven Model.
     * 
     */
    protected Model getModel() {
        Model model = new Model();
        model.setModelVersion(MavenConstants.POM_VERSION);

        model.setPackaging(this.getPackaging());

        model.setVersion(this.getVersion());
        model.setGroupId(this.getGroupId());
        model.setArtifactId(this.getArtifactId());

        if (this.getName() != null) {
            model.setName(this.getName());
        }

        if (this.getDesc() != null) {
            model.setDescription(this.getDesc());
        }
        return model;
    }

    /**
     * 
     * By default, create the all maven folders.
     * 
     */
    protected String[] getFolders() {
        ProjectSystemFolder[] mavenDirectories = MavenSystemFolders.ALL_DIRS;

        String[] directories = new String[mavenDirectories.length];
        for (int i = 0; i < directories.length; i++) {
            directories[i] = mavenDirectories[i].getPath();
        }

        return directories;
    }

    /**
     * 
     * By default, it's current workspace.
     * 
     */
    protected IPath getBaseLocation() {
        return ResourcesPlugin.getWorkspace().getRoot().getLocation();
    }

    /**
     * 
     * can do something before create operation.
     */
    protected void beforeCreate(IProgressMonitor monitor, Model model, IProject project) throws Exception {
        // nothing to do
    }

    /**
     * 
     * after create operation, can do something, like add some natures.
     */
    protected void afterCreate(IProgressMonitor monitor, IProject project) throws Exception {
        // nothing to do
    }

    public IProject createSourceProject(IProgressMonitor monitor) throws Exception {
        IProgressMonitor pMoniter = monitor;
        if (monitor == null) {
            pMoniter = new NullProgressMonitor();
        }
        final Model model = getModel();
        final ProjectImportConfiguration importConfiguration = new ProjectImportConfiguration();
        final IProject project = importConfiguration.getProject(ResourcesPlugin.getWorkspace().getRoot(), model);
        final IPath location = getBaseLocation();
        final String[] folders = getFolders();

        beforeCreate(monitor, model, project);

        MavenPlugin.getProjectConfigurationManager().createSimpleProject(project, location.append(project.getName()), model,
                folders, importConfiguration, pMoniter);

        afterCreate(monitor, project);

        return project;
    }
}
