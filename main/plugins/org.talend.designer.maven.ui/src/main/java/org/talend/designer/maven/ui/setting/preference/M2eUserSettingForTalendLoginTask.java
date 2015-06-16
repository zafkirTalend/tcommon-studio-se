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
package org.talend.designer.maven.ui.setting.preference;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.m2e.core.MavenPlugin;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.core.runtime.projectsetting.IProjectSettingTemplateConstants;
import org.talend.designer.maven.DesignerMavenPlugin;
import org.talend.designer.maven.template.MavenTemplateManager;
import org.talend.designer.maven.ui.DesignerMavenUiPlugin;
import org.talend.login.AbstractLoginTask;
import org.talend.utils.io.FilesUtils;

/**
 * DOC ggu class global comment. Detailled comment
 */
public class M2eUserSettingForTalendLoginTask extends AbstractLoginTask {

    private static final String MAVEN_SETTING_HAVE_SET = "Maven_Setting_have_set"; //$NON-NLS-1$

    @Override
    public boolean isCommandlineTask() {
        return true; // also enable support for commandline, so set true.
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.login.ILoginTask#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        IPreferenceStore mvnUiStore = DesignerMavenUiPlugin.getDefault().getPreferenceStore();
        boolean set = mvnUiStore.getBoolean(MAVEN_SETTING_HAVE_SET);
        try {
            IPath userSettingsPath = new Path(Platform.getConfigurationLocation().getURL().getPath())
                    .append(IProjectSettingTemplateConstants.MAVEN_USER_SETTING_TEMPLATE_FILE_NAME);
            File userSettingsFile = userSettingsPath.toFile();
            if (!set) {// first time to set
                if (!userSettingsFile.exists()) {
                    InputStream inputStream = MavenTemplateManager.getBundleTemplateStream(DesignerMavenPlugin.PLUGIN_ID,
                            IProjectSettingTemplateConstants.PATH_RESOURCES_TEMPLATES + '/'
                                    + IProjectSettingTemplateConstants.MAVEN_USER_SETTING_TEMPLATE_FILE_NAME);
                    if (inputStream != null) {
                        FilesUtils.copyFile(inputStream, userSettingsFile);
                    }
                }
                // re-check
                if (userSettingsFile.exists()) {
                    MavenPlugin.getMavenConfiguration().setUserSettingsFile(userSettingsPath.toString());
                    mvnUiStore.setValue(MAVEN_SETTING_HAVE_SET, true);
                }
            } else if (!userSettingsFile.exists()) { // not first time, but deleted by manually.
                // FIXME, try use system default one?
                MavenPlugin.getMavenConfiguration().setUserSettingsFile(null);
            }
        } catch (Exception e) {
            ExceptionHandler.process(e);
        }
    }

}
