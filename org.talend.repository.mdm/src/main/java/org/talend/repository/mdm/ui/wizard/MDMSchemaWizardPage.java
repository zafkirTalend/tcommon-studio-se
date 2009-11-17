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
import org.talend.repository.ui.swt.utils.AbstractForm;

/**
 * DOC hwang class global comment. Detailled comment
 */
public class MDMSchemaWizardPage extends WizardPage {

    // private MDMSchemaForm mdmSchemaForm;
    private MDMXSDFileForm xsdFileForm;

    private ConnectionItem connectionItem;

    /**
     * DOC Administrator MDMSchemaWizardPage constructor comment.
     * 
     * @param pageName
     */
    protected MDMSchemaWizardPage(ConnectionItem connectionItem, String pageName) {
        super(pageName);
        this.connectionItem = connectionItem;
        this.setTitle(Messages.getString("SetConceptNamePage.MDM_Concept")); //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl(Composite parent) {
        xsdFileForm = new MDMXSDFileForm(parent, connectionItem);
        xsdFileForm.setReadOnly(false);

        AbstractForm.ICheckListener listener = new AbstractForm.ICheckListener() {

            public void checkPerformed(final AbstractForm source) {
                if (source.isStatusOnError()) {
                    MDMSchemaWizardPage.this.setPageComplete(false);
                    setErrorMessage(source.getStatus());
                } else {
                    MDMSchemaWizardPage.this.setPageComplete(true);
                    setErrorMessage(null);
                    setMessage(source.getStatus(), source.getStatusLevel());
                }
            }
        };
        xsdFileForm.setListener(listener);
        setControl(xsdFileForm);
    }

    public void setConceptName(String name) {
        xsdFileForm.setConceptName(name);
    }

    public void createMetadataTable() {
        xsdFileForm.createTable();
    }

}
