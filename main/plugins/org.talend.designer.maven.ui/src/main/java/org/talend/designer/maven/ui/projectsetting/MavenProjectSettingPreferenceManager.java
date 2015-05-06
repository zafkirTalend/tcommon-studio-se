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
package org.talend.designer.maven.ui.projectsetting;

import org.talend.core.runtime.preference.ProjectPreferenceManager;
import org.talend.designer.maven.ui.DesignerMavenUiPlugin;

/**
 * DOC ggu class global comment. Detailled comment
 */
public final class MavenProjectSettingPreferenceManager {

    private static final MavenProjectSettingPreferenceManager INSTANCE = new MavenProjectSettingPreferenceManager();

    private ProjectPreferenceManager mavenManager;

    private MavenProjectSettingPreferenceManager() {
        //
    }

    public static MavenProjectSettingPreferenceManager getInstance() {
        return INSTANCE;
    }

    public synchronized ProjectPreferenceManager getProjectPreferenceManager() {
        if (mavenManager == null) {
            synchronized (MavenProjectSettingPreferenceManager.class) {
                if (mavenManager == null) {
                    // in order to make sure this Initializer to start, must set the qualifier is current bundle name.
                    mavenManager = new ProjectPreferenceManager(DesignerMavenUiPlugin.PLUGIN_ID);
                }
            }
        }
        return mavenManager;
    }
}
