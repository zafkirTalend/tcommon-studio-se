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
package org.talend.dataprofiler.core.tdq.service;

import org.talend.dataprofiler.core.service.IViewerFilterService;
import org.talend.dataprofiler.core.tdq.Activator;
import org.talend.dataprofiler.core.tdq.pref.PreferenceConstant;
import org.talend.dataprofiler.core.ui.views.filters.ReportingFilter;


/**
 * DOC rli  class global comment. Detailled comment
 */
public class ReportingFilterService implements IViewerFilterService {

    /* (non-Javadoc)
     * @see org.talend.dataprofiler.core.service.IViewerFilterService#getViwerFilterId()
     */
    public int getViwerFilterId() {
        return ReportingFilter.FILTER_ID;
    }

    /* (non-Javadoc)
     * @see org.talend.dataprofiler.core.service.IViewerFilterService#isAddOrDel()
     */
    public boolean isAddOrDel() {
        boolean isReportingEnable = Activator.getDefault().getPreferenceStore().getBoolean(PreferenceConstant.REPORTINGENABLE);
        return !isReportingEnable;
    }

}
