// ============================================================================
//
// Copyright (C) 2006-2013 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.librariesmanager.ui.prefs;

import org.eclipse.jface.preference.IPreferenceStore;
import org.talend.core.language.ECodeLanguage;
import org.talend.librariesmanager.ui.LibManagerUiPlugin;
import org.talend.librariesmanager.ui.i18n.Messages;

/**
 * created by Administrator on 2013-3-7 Detailled comment
 * 
 */
public class PreferencesUtilities {

    public static final String EXTERNAL_LIB_PATH_MODE_SINGLE = "externalLibPathModeSingle"; //$NON-NLS-1$

    public static final String EXTERNAL_LIB_PATH = "externalLibPath"; //$NON-NLS-1$

    public static final String EXTERNAL_LIB_PATH_JAVA = "externalLibPathJava"; //$NON-NLS-1$

    public static final String EXTERNAL_LIB_PATH_PERL = "externalLibPathPerl"; //$NON-NLS-1$

    public static IPreferenceStore getPreferenceStore() {
        return LibManagerUiPlugin.getDefault().getPreferenceStore();
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
}
