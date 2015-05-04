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
package org.talend.core.runtime.preference;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.osgi.service.prefs.BackingStoreException;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.utils.workbench.resources.ResourceUtils;
import org.talend.core.model.general.Project;
import org.talend.repository.ProjectManager;

/**
 * DOC ggu class global comment. Detailled comment
 */
public final class ProjectPreferenceManager {

    /**
     * copied from EclipsePreferences.
     */
    public static final String DEFAULT_PREFERENCES_DIRNAME = ".settings"; //$NON-NLS-1$

    public static final String PREFS_FILE_EXTENSION = "prefs"; //$NON-NLS-1$

    private String qualifier;

    private IProject project;

    private ProjectScope projectScope;

    public ProjectPreferenceManager(String fileName) {
        this(ProjectManager.getInstance().getCurrentProject(), fileName);

    }

    public ProjectPreferenceManager(Project p, String fileName) {
        super();
        try {
            Assert.isNotNull(p);
            IProject project = ResourceUtils.getProject(p);
            init(project, fileName);
        } catch (PersistenceException e) {
            ExceptionHandler.process(e);
        }

    }

    public ProjectPreferenceManager(IProject project, String fileName) {
        super();
        init(project, fileName);
    }

    private void init(IProject project, String fileName) {
        Assert.isNotNull(fileName);
        Assert.isNotNull(project);
        this.qualifier = fileName;
        this.project = project;
        this.projectScope = new ProjectScope(project);
    }

    private String getQualifier() {
        return qualifier;
    }

    private IProject getProject() {
        return project;
    }

    private ProjectScope getProjectScope() {
        return projectScope;
    }

    private IPath getLocation() {
        return getProjectScope().getLocation().append(getQualifier()).addFileExtension(PREFS_FILE_EXTENSION);
    }

    private IEclipsePreferences getQulifierPreference() {
        return getProjectScope().getNode(getQualifier());
    }

    /**
     * Check the .settings/<qualifier>.prefs file is existed in project or not.
     */
    public boolean exist() {
        return getLocation().toFile().exists();
    }

    public void setValue(String key, String value) {
        IEclipsePreferences qulifierPreference = getQulifierPreference();
        qulifierPreference.put(key, value);
    }

    public void setValue(String key, int value) {
        IEclipsePreferences qulifierPreference = getQulifierPreference();
        qulifierPreference.putInt(key, value);
    }

    public void setValue(String key, boolean value) {
        IEclipsePreferences qulifierPreference = getQulifierPreference();
        qulifierPreference.putBoolean(key, value);
    }

    public String getValue(String key) {
        IEclipsePreferences qulifierPreference = getQulifierPreference();
        return qulifierPreference.get(key, null);
    }

    public int getInt(String key) {
        IEclipsePreferences qulifierPreference = getQulifierPreference();
        return qulifierPreference.getInt(key, -1);
    }

    public boolean getBoolean(String key) {
        IEclipsePreferences qulifierPreference = getQulifierPreference();
        return qulifierPreference.getBoolean(key, false);
    }

    /**
     * Save the configurations.
     */
    public void save() {
        try {
            IEclipsePreferences qulifierPreference = getQulifierPreference();
            qulifierPreference.flush();
        } catch (BackingStoreException e) {
            ExceptionHandler.process(e);
        }
    }

}
