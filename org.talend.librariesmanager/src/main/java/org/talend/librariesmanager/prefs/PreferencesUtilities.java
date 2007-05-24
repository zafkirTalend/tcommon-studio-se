// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006-2007 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
//
// ============================================================================
package org.talend.librariesmanager.prefs;

import org.eclipse.jface.preference.IPreferenceStore;
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
}
