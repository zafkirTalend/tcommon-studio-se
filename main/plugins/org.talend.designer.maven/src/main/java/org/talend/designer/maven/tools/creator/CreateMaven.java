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

import java.util.Properties;

import org.apache.maven.model.Model;
import org.eclipse.core.runtime.IProgressMonitor;
import org.talend.commons.utils.VersionUtils;
import org.talend.designer.maven.model.TalendMavenConstants;

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
    private String packaging = TalendMavenConstants.PACKAGING_JAR;

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
        model.setModelVersion(TalendMavenConstants.POM_VERSION);
        setAttributes(model);
        addProperties(model);
        return model;
    }

    protected void setAttributes(Model model) {
        if (this.getGroupId() != null) {
            model.setGroupId(this.getGroupId());
        }
        if (this.getArtifactId() != null) {
            model.setArtifactId(this.getArtifactId());
        }
        if (this.getVersion() != null) {
            model.setVersion(this.getVersion());
        }
        if (this.getPackaging() != null) {
            model.setPackaging(this.getPackaging());
        }
        if (this.getName() != null) {
            model.setName(this.getName());
        }

        if (this.getDesc() != null) {
            model.setDescription(this.getDesc());
        }

    }

    @SuppressWarnings("nls")
    protected void addProperties(Model model) {
        // set the properties
        Properties p = model.getProperties();
        if (p == null) {
            p = new Properties();
            model.setProperties(p);
        }
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
     * 
     * Create the pom resource.
     */
    public abstract void create(IProgressMonitor monitor) throws Exception;
}
