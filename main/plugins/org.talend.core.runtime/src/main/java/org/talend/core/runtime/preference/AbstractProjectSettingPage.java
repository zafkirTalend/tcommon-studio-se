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

import org.eclipse.jface.preference.FieldEditorPreferencePage;

/**
 * DOC ggu class global comment. Detailled comment
 */
public abstract class AbstractProjectSettingPage extends FieldEditorPreferencePage {

    private final ProjectPreferenceManager projectPreferenceManager;

    public AbstractProjectSettingPage() {
        super();
        // create project preference manager
        this.projectPreferenceManager = new ProjectPreferenceManager(getPreferenceName());
        // set the project preference
        setPreferenceStore(this.projectPreferenceManager.getPreferenceStore());
    }

    protected ProjectPreferenceManager getProjectPreferenceManager() {
        return projectPreferenceManager;
    }

    protected abstract String getPreferenceName();
}
