// ============================================================================
//
// Copyright (C) 2006-2010 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.mdm.ui.wizard.concept;

import org.eclipse.swt.widgets.Composite;
import org.talend.core.model.metadata.builder.connection.MetadataTable;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.repository.mdm.i18n.Messages;
import org.talend.repository.model.RepositoryNode;
import org.talend.repository.ui.swt.utils.AbstractForm;

/**
 * DOC hwang class global comment. Detailled comment
 */
public class MDMInOutSchemaWizardPage extends AbstractRetrieveConceptPage {

    // private MDMSchemaForm mdmSchemaForm;
    private AbstractMDMFileStepForm xsdFileForm;

    private boolean isRepositoryObjectEditable;

    private MetadataTable metadataTable;

    /**
     * DOC Administrator MDMSchemaWizardPage constructor comment.
     * 
     * @param pageName
     */
    protected MDMInOutSchemaWizardPage(RepositoryNode node, ConnectionItem connectionItem, MetadataTable metadataTable,
            boolean isRepositoryObjectEditable, boolean creation) {
        super(node, connectionItem, creation);
        this.metadataTable = metadataTable;
        this.isRepositoryObjectEditable = isRepositoryObjectEditable;
        this.setTitle(Messages.getString("SetConceptNamePage.MDM_Concept")); //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl(Composite parent) {
        if (getConcept() != null) {
            if (getConcept().isInputModel()) {
                if (getPreviousPage() instanceof SetConceptNamePage) {
                    xsdFileForm = new MDMXSDFileForm(parent, connectionItem, metadataTable, getConcept(), this);
                }
            } else {
                xsdFileForm = new MDMOutputSchemaForm(parent, connectionItem, metadataTable, getConcept(), this, creation);
            }
            xsdFileForm.setReadOnly(!isRepositoryObjectEditable);

            AbstractForm.ICheckListener listener = new AbstractForm.ICheckListener() {

                public void checkPerformed(final AbstractForm source) {
                    if (source.isStatusOnError()) {
                        MDMInOutSchemaWizardPage.this.setPageComplete(false);
                        setErrorMessage(source.getStatus());
                    } else {
                        MDMInOutSchemaWizardPage.this.setPageComplete(true);
                        setErrorMessage(null);
                        setMessage(source.getStatus(), source.getStatusLevel());
                    }
                }
            };
            xsdFileForm.setListener(listener);
            this.setPageComplete(false);
            setControl(xsdFileForm);
        }
    }

    // public void setConceptName(String name) {
    // xsdFileForm.setConceptName(name);
    // }
    //
    public void createMetadataTable() {
        xsdFileForm.createTable();
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            ((CreateConceptWizard) getWizard()).setCurrentPage(this);
        }
    }
}
