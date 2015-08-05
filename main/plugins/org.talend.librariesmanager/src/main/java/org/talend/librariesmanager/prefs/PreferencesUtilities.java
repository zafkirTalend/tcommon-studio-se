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
package org.talend.librariesmanager.prefs;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.core.language.ECodeLanguage;
import org.talend.librariesmanager.Activator;
import org.talend.librariesmanager.i18n.Messages;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 */
public class PreferencesUtilities {

    public static final String EXTERNAL_LIB_PATH_MODE_SINGLE = "externalLibPathModeSingle"; //$NON-NLS-1$

    public static final String EXTERNAL_LIB_PATH = "externalLibPath"; //$NON-NLS-1$

    public static final String EXTERNAL_LIB_PATH_JAVA = "externalLibPathJava"; //$NON-NLS-1$

    public static final String EXTERNAL_LIB_PATH_PERL = "externalLibPathPerl"; //$NON-NLS-1$

    public static IPreferenceStore getPreferenceStore() {
        return Activator.getDefault().getPreferenceStore();
    }

    public static String getLibrariesPath(ECodeLanguage language) {
        // TDI-17414:commandline workspace no need to use .JavaLibs to store Talend libraries.
        // if (CommonsPlugin.isStoreLibsInWorkspace()) {
        // return getWorkSpaceLibPath(language);
        // }
        boolean singleMode = getPreferenceStore().getBoolean(EXTERNAL_LIB_PATH_MODE_SINGLE);
        switch (language) {
        case JAVA:
            if (singleMode) {
                return getPreferenceStore().getString(EXTERNAL_LIB_PATH) + "/java"; //$NON-NLS-1$
            } else {
                return getPreferenceStore().getString(EXTERNAL_LIB_PATH_JAVA);
            }
        case PERL:
            if (singleMode) {
                return getPreferenceStore().getString(EXTERNAL_LIB_PATH) + "/perl"; //$NON-NLS-1$
            } else {
                return getPreferenceStore().getString(EXTERNAL_LIB_PATH_PERL);
            }
        default:
            throw new IllegalArgumentException(Messages.getString("PreferencesUtilities.unknowLanguage")); //$NON-NLS-1$
        }
    }

    private static String getWorkSpaceLibPath(ECodeLanguage language) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("."); //$NON-NLS-1$
        stringBuilder.append(language.getCaseName());
        stringBuilder.append("libs"); //$NON-NLS-1$
        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(stringBuilder.toString());
        if (!project.exists()) {
            NullProgressMonitor monitor = new NullProgressMonitor();
            try {
                project.create(monitor);
                project.open(monitor);
            } catch (CoreException e) {
                // e.printStackTrace();
                ExceptionHandler.process(e);
            }
        }
        return project.getLocation().toOSString();
    }
}
