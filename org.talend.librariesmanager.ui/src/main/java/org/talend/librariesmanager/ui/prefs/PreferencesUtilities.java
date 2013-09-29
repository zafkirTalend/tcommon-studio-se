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
import org.talend.librariesmanager.prefs.LibrariesManagerUtils;
import org.talend.librariesmanager.ui.LibManagerUiPlugin;

/**
 * created by Administrator on 2013-3-7 Detailled comment
 * 
 */
public class PreferencesUtilities {

    public static final String EXTERNAL_LIB_PATH = "externalLibPath"; //$NON-NLS-1$

    public static IPreferenceStore getPreferenceStore() {
        return LibManagerUiPlugin.getDefault().getPreferenceStore();
    }

    public static String getLibrariesPath(ECodeLanguage language) {
        String libPath = System.getProperty(LibrariesManagerUtils.TALEND_LIBRARY_PATH);
        if (libPath != null) {
            return libPath;
        }

        return getPreferenceStore().getString(EXTERNAL_LIB_PATH);
    }
}
