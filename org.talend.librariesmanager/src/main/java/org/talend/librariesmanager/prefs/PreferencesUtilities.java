// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//   
// ============================================================================
package org.talend.librariesmanager.prefs;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.talend.commons.CommonsPlugin;
import org.talend.core.language.ECodeLanguage;
import org.talend.librariesmanager.Activator;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 */
public class PreferencesUtilities {

    public static final String EXTERNAL_LIB_PATH_MODE_SINGLE = "externalLibPathModeSingle";

    public static final String EXTERNAL_LIB_PATH = "externalLibPath";

    public static final String EXTERNAL_LIB_PATH_JAVA = "externalLibPathJava";

    public static final String EXTERNAL_LIB_PATH_PERL = "externalLibPathPerl";

    public static IPreferenceStore getPreferenceStore() {
        return Activator.getDefault().getPreferenceStore();
    }

    public static String getLibrariesPath(ECodeLanguage language) {
        if (CommonsPlugin.isHeadless()) {
            return getHeadlessLibPath(language);
        }
        boolean singleMode = getPreferenceStore().getBoolean(EXTERNAL_LIB_PATH_MODE_SINGLE);
        switch (language) {
        case JAVA:
            if (singleMode) {
                return getPreferenceStore().getString(EXTERNAL_LIB_PATH) + "/java";
            } else {
                return getPreferenceStore().getString(EXTERNAL_LIB_PATH_JAVA);
            }
        case PERL:
            if (singleMode) {
                return getPreferenceStore().getString(EXTERNAL_LIB_PATH) + "/perl";
            } else {
                return getPreferenceStore().getString(EXTERNAL_LIB_PATH_PERL);
            }
        default:
            throw new IllegalArgumentException("Unknown language");
        }
    }

    private static String getHeadlessLibPath(ECodeLanguage language) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(".");
        stringBuilder.append(language.getCaseName());
        stringBuilder.append("libs");
        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(stringBuilder.toString());
        if (!project.exists()) {
            NullProgressMonitor monitor = new NullProgressMonitor();
            try {
                project.create(monitor);
                project.open(monitor);
            } catch (CoreException e) {
                e.printStackTrace();
            }
        }
        return project.getLocation().toOSString();
    }
}
