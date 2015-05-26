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

import org.talend.core.runtime.preference.AbstractProjectSettingPage;
import org.talend.designer.maven.ui.DesignerMavenUiPlugin;

/**
 * DOC ggu class global comment. Detailled comment
 */
public class EmptyMavenSettingPreferencePage extends AbstractProjectSettingPage {

    public EmptyMavenSettingPreferencePage() {
        super();

        noDefaultAndApplyButton();
    }

    @Override
    protected String getPreferenceName() {
        return DesignerMavenUiPlugin.PLUGIN_ID;
    }

    @Override
    protected void createFieldEditors() {

    }

}
