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

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.repository.mdm.i18n.Messages;
import org.talend.repository.model.RepositoryNode;
import org.talend.repository.ui.swt.utils.AbstractForm;

/**
 * DOC hwang class global comment. Detailled comment
 */
public class SetConceptNamePage extends WizardPage {

    private RepositoryNode node;

    private ConnectionItem connectionItem;

    private SetNameForm form;

    private String[] existingNames;

    private boolean isRepositoryObjectEditable;

    protected SetConceptNamePage(RepositoryNode node, ConnectionItem connectionItem, boolean isRepositoryObjectEditable,
            String[] existingNames) {
        super(Messages.getString("SetConceptNamePage.Talend")); //$NON-NLS-1$
        this.setTitle(Messages.getString("SetConceptNamePage.MDM_Concept")); //$NON-NLS-1$
        this.node = node;
        this.connectionItem = connectionItem;
        this.existingNames = existingNames;
        this.isRepositoryObjectEditable = isRepositoryObjectEditable;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl(Composite parent) {
        form = new SetNameForm(parent, connectionItem, existingNames);
        form.setReadOnly(!isRepositoryObjectEditable);

        AbstractForm.ICheckListener listener = new AbstractForm.ICheckListener() {

            public void checkPerformed(final AbstractForm source) {
                if (source.isStatusOnError()) {
                    SetConceptNamePage.this.setPageComplete(false);
                    setErrorMessage(source.getStatus());
                } else {
                    SetConceptNamePage.this.setPageComplete(isRepositoryObjectEditable);
                    setErrorMessage(null);
                    setMessage(source.getStatus(), source.getStatusLevel());
                }
            }
        };
        form.setListener(listener);
        setControl(form);
        if (connectionItem.getProperty().getLabel() != null && !connectionItem.getProperty().getLabel().equals("")) { //$NON-NLS-1$
            form.checkFieldsValue();
        }
    }

    public String getName() {
        return form.getName();
    }

}
