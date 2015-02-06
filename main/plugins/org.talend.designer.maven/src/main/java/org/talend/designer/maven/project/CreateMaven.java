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
import org.eclipse.core.runtime.IProgressMonitor;
import org.talend.commons.utils.VersionUtils;
import org.talend.designer.maven.model.MavenConstants;

/**
 * created by ggu on 2 Feb 2015 Detailled comment
 *
 */
public abstract class CreateMaven {

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
    protected Model createModel() {
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
     * Create the pom resource.
     */
    public abstract void create(IProgressMonitor monitor) throws Exception;
}
