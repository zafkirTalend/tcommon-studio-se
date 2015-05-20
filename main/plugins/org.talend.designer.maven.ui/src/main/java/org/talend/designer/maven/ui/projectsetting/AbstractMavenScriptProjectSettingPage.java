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

import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.talend.core.runtime.CoreRuntimePlugin;
import org.talend.core.runtime.preference.AbstractProjectSettingPage;
import org.talend.designer.maven.ui.DesignerMavenUiPlugin;
import org.talend.repository.model.IProxyRepositoryFactory;

/**
 * DOC ggu class global comment. Detailled comment
 */
public abstract class AbstractMavenScriptProjectSettingPage extends AbstractProjectSettingPage {

    private String headerMessage;

    private StyledText scriptTxt;

    private boolean isDefaultPresentedForScriptTxt = false;

    private boolean readonly;

    public AbstractMavenScriptProjectSettingPage() {
        super();

        IProxyRepositoryFactory factory = CoreRuntimePlugin.getInstance().getProxyRepositoryFactory();
        readonly = factory.isUserReadOnlyOnCurrentProject();

    }

    protected boolean isReadonly() {
        return readonly;
    }

    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.runtime.preference.AbstractProjectSettingPage#initStore()
     */
    @Override
    protected void initStore() {
        this.setPreferenceStore(DesignerMavenUiPlugin.getDefault().getProjectPreferenceManager().getPreferenceStore());
    }

    public void load() throws IOException {
        // nothing to do
    }

    public void save() throws IOException {
        // nothing to do
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

    protected String getHeaderMessages() {
        if (headerMessage == null) {
            return getHeadTitle();
        }
        return headerMessage;
    }

    public void setHeaderMessage(String headerMessage) {
        this.headerMessage = headerMessage;
    }

    protected abstract String getPreferenceKey();

    protected abstract String getHeadTitle();

    @Override
    protected Control createContents(Composite p) {
        Composite parent = (Composite) super.createContents(p);

        Label scriptLabel = new Label(parent, SWT.NONE);
        String headerMessages = getHeaderMessages();
        scriptLabel.setText(headerMessages);
        // FIXME, If the message can't be displayed complectly. try to set the tool tip
        if (headerMessages.length() > 50) { // simply first, just set the length greater than 50
            scriptLabel.setToolTipText(headerMessages);
        }

        int style = SWT.MULTI | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL;
        if (isReadonly()) {
            style |= SWT.READ_ONLY;
        }
        scriptTxt = new StyledText(parent, style);
        GridData layoutData = new GridData(GridData.FILL_BOTH);
        layoutData.heightHint = 280;
        layoutData.minimumHeight = 280;
        layoutData.widthHint = 500;
        layoutData.minimumWidth = 500;
        scriptTxt.setLayoutData(layoutData);
        scriptTxt.setText(getScriptContent());

        return parent;
    }

    protected String getScriptContent() {
        return getPreferenceStore().getString(getPreferenceKey());
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
