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
package org.talend.dataprofiler.core.tdq.ui.pref;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.talend.dataprofiler.core.CorePlugin;
import org.talend.dataprofiler.core.tdq.Activator;
import org.talend.dataprofiler.core.tdq.manager.PrefParameterSetManager;
import org.talend.dataprofiler.core.tdq.pref.PrefParameterBean;
import org.talend.dataprofiler.core.ui.views.DQRespositoryView;
import org.talend.dataprofiler.core.ui.views.filters.ReportingFilter;

/**
 * DOC rli class global comment. Detailled comment
 */
public class DataprofilerSettingPage extends PreferencePage implements IWorkbenchPreferencePage {

    private Button reportingEableButton;

    private PrefParameterBean prefParameterBean;

    public void init(IWorkbench workbench) {
        setPreferenceStore(Activator.getDefault().getPreferenceStore());
        prefParameterBean = PrefParameterSetManager.getInstance().getPrefParameterBean();

    }

    @Override
    protected Control createContents(Composite parent) {
        Composite top = new Composite(parent, SWT.NONE);
        top.setLayout(new GridLayout());
        createSettingsComp(top);
        return top;
    }

    public Composite createSettingsComp(Composite parent) {
        Composite comp = new Composite(parent, SWT.NONE);
        comp.setLayout(new GridLayout());
        GridDataFactory.fillDefaults().grab(true, true).span(1, 1).applyTo(comp);
        reportingEableButton = new Button(comp, SWT.CHECK);
        GridDataFactory.fillDefaults().applyTo(reportingEableButton);
        reportingEableButton.setText("Display the report folder");
        reportingEableButton.setSelection(prefParameterBean.isReportEnable());
        return comp;
    }

    public boolean performOk() {
        doSave();
        IViewPart findView = CorePlugin.getDefault().findView(DQRespositoryView.ID);
        if (findView != null) {
            DQRespositoryView viewer = (DQRespositoryView) findView;
            if (this.prefParameterBean.isReportEnable()) {
                viewer.removeViewerFilter(ReportingFilter.FILTER_ID);
            } else {
                viewer.addViewerFilter(ReportingFilter.FILTER_ID);
            }
            viewer.getCommonViewer().refresh();
        }
        return true;
    }

    /**
     * DOC rli Comment method "doSave".
     */
    private void doSave() {
        prefParameterBean.setReportEnable(reportingEableButton.getSelection());
        PrefParameterSetManager.getInstance().savePreParameterBean(this.prefParameterBean);
    }
}
