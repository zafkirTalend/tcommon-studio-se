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
package org.talend.designer.maven.ui.projectsetting;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.talend.core.runtime.preference.AbstractProjectSettingPage;
import org.talend.designer.maven.ui.DesignerMavenUiPlugin;

/**
 * DOC ggu class global comment. Detailled comment
 */
public abstract class AbstractMavenScriptProjectSettingPage extends AbstractProjectSettingPage {

    private StyledText scriptTxt;

    private boolean isDefaultPresentedForScriptTxt = false;

    public AbstractMavenScriptProjectSettingPage() {
        super();

        this.setPreferenceStore(MavenProjectSettingPreferenceManager.getInstance().getProjectPreferenceManager()
                .getPreferenceStore());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.runtime.preference.AbstractProjectSettingPage#getPreferenceName()
     */
    @Override
    protected String getPreferenceName() {
        return DesignerMavenUiPlugin.PLUGIN_ID;
    }

    protected abstract String getPreferenceKey();

    protected abstract String getHeadTitle();

    @Override
    protected Control createContents(Composite p) {
        Composite parent = (Composite) super.createContents(p);

        Label scriptLabel = new Label(parent, SWT.NONE);
        scriptLabel.setText(getHeadTitle());

        scriptTxt = new StyledText(parent, SWT.MULTI | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
        GridData layoutData = new GridData(GridData.FILL_BOTH);
        layoutData.heightHint = 280;
        layoutData.minimumHeight = 280;
        layoutData.widthHint = 500;
        layoutData.minimumWidth = 500;
        scriptTxt.setLayoutData(layoutData);
        scriptTxt.setText(getPreferenceStore().getString(getPreferenceKey()));

        return parent;
    }

    @Override
    protected void createFieldEditors() {
        //
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.preference.FieldEditorPreferencePage#performDefaults()
     */
    @Override
    protected void performDefaults() {
        super.performDefaults();
        if (scriptTxt != null && !scriptTxt.isDisposed()) {
            isDefaultPresentedForScriptTxt = true;
            scriptTxt.setText(getPreferenceStore().getDefaultString(getPreferenceKey()));
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.preference.FieldEditorPreferencePage#performOk()
     */
    @Override
    public boolean performOk() {
        boolean ok = super.performOk();
        if (scriptTxt != null && !scriptTxt.isDisposed()) {
            if (isDefaultPresentedForScriptTxt) {
                getPreferenceStore().setToDefault(getPreferenceKey());
            } else {
                getPreferenceStore().setValue(getPreferenceKey(), scriptTxt.getText());
            }
            isDefaultPresentedForScriptTxt = false;
        }
        return ok;
    }
}
