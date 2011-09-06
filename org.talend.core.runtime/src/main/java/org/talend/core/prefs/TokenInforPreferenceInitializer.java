// ============================================================================
//
// Copyright (C) 2006-2011 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.prefs;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.talend.core.runtime.CoreRuntimePlugin;

/**
 * ggu class global comment. Detailled comment
 */
public class TokenInforPreferenceInitializer extends AbstractPreferenceInitializer {

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
     */
    @Override
    public void initializeDefaultPreferences() {
        IPreferenceStore preferenceStore = CoreRuntimePlugin.getInstance().getPreferenceStore();
        preferenceStore.setDefault(ITalendCorePrefConstants.ACTIVE_TOKEN_INFORS, true);
        preferenceStore.setDefault(ITalendCorePrefConstants.ACTIVE_TOKEN_INFORS_TIMES, 20);

    }

}
