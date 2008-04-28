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
package org.talend.dataprofiler.core.manager;

import org.eclipse.jface.preference.IPreferenceStore;
import org.talend.dataprofiler.core.CorePlugin;
import org.talend.dataprofiler.core.pref.PrefParameterBean;
import org.talend.dataprofiler.core.pref.PreferenceConstant;

/**
 * This class can get the parameter value from eclipse preference.
 */
public final class PrefParameterSetManager {

    private static PrefParameterSetManager instance;

    private IPreferenceStore preferenceStore;

    private PrefParameterSetManager() {
        preferenceStore = CorePlugin.getDefault().getPreferenceStore();
    }

    public static PrefParameterSetManager getInstance() {
        if (instance == null) {
            instance = new PrefParameterSetManager();
            return instance;
        }
        return instance;
    }

    public PrefParameterBean getPrefParameterBean() {
        PrefParameterBean prefParameterBean = new PrefParameterBean();
        prefParameterBean.setReportEnable(preferenceStore.getBoolean(PreferenceConstant.REPORTINGENABLE));
        return prefParameterBean;
    }

    public void savePreParameterBean(PrefParameterBean prefBean) {
        preferenceStore.setValue(PreferenceConstant.REPORTINGENABLE, prefBean.isReportEnable());
    }

}
