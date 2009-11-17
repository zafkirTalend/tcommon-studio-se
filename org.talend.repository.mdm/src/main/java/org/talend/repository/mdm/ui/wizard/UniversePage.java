// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.mdm.ui.wizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.mdm.webservice.XtentisBindingStub;
import org.talend.repository.mdm.i18n.Messages;
import org.talend.repository.ui.swt.utils.AbstractForm;

/**
 * DOC Hwang class global comment. Detailled comment
 */
public class UniversePage extends WizardPage {

    private UniverseForm universeForm = null;

    // private MDMWizardPage mdmWizardPage;

    private List<String> list = new ArrayList<String>();

    private ConnectionItem connectionItem;

    /**
     * DOC Administrator UniseverPage constructor comment.
     * 
     * @param pageName
     */
    protected UniversePage(ConnectionItem connectionItem, String pageName) {
        super(pageName);
        this.connectionItem = connectionItem;
        this.setTitle(Messages.getString("UniversePage.Talend_MDM")); //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl(Composite parent) {
        universeForm = new UniverseForm(parent, SWT.NONE, null, connectionItem);
        universeForm.setReadOnly(false);// (!isRepositoryObjectEditable);

        AbstractForm.ICheckListener listener = new AbstractForm.ICheckListener() {

            public void checkPerformed(final AbstractForm source) {
                if (source.isStatusOnError()) {
                    UniversePage.this.setPageComplete(false);
                    setErrorMessage(source.getStatus());
                } else {
                    UniversePage.this.setPageComplete(true);// (isRepositoryObjectEditable);
                    setErrorMessage(null);
                    setMessage(source.getStatus(), source.getStatusLevel());
                }
            }
        };
        universeForm.setListener(listener);
        setControl(universeForm);
        //        if (connectionItem.getProperty().getLabel() != null && !connectionItem.getProperty().getLabel().equals("")) { //$NON-NLS-1$
        // universeForm.checkFieldsValue();
        // }
    }

    public List<String> getList() {
        return universeForm.getList();
    }

    public void setList(List<String> list) {
        universeForm.setList(list);
        // this.list = list;
    }

    public void setUserName(String userName) {
        universeForm.setUserName(userName);
    }

    public void setStub(XtentisBindingStub stub) {
        universeForm.setStub(stub);
    }

    public void refreshCombo(List<String> list) {
        universeForm.refreshCombo(list);
    }

}
