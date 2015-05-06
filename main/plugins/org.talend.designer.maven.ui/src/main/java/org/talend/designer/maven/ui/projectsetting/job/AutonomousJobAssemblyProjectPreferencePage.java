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
package org.talend.designer.maven.ui.projectsetting.job;

import org.talend.designer.maven.ui.projectsetting.AbstractMavenScriptProjectSettingPage;
import org.talend.designer.maven.ui.projectsetting.IProjectSettingPreferenceConstants;

/**
 * DOC ggu class global comment. Detailled comment
 */
public class AutonomousJobAssemblyProjectPreferencePage extends AbstractMavenScriptProjectSettingPage {

    @Override
    protected String getPreferenceKey() {
        return IProjectSettingPreferenceConstants.MAVEN_SCRIPT_AUTONOMOUSJOB_ASSEMBLY_TEMPLATE;
    }

    @Override
    protected String getHeadTitle() {
        return "Assembly script";
    }

}
