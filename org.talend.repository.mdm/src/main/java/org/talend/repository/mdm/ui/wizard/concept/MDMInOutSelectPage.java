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

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.repository.model.RepositoryNode;

/**
 * DOC wchen class global comment. Detailled comment
 */
public class MDMInOutSelectPage extends AbstractRetrieveConceptPage {

    private Label label;

    private Button inputModeButton;

    private Button outputModeButton;

    /**
     * DOC wchen MDMInOutSelectPage constructor comment.
     * 
     * @param pageName
     * @param title
     * @param titleImage
     */
    public MDMInOutSelectPage(RepositoryNode node, ConnectionItem connectionItem, boolean creation) {
        super(node, connectionItem, creation);
        this.setTitle("MDM Model");
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl(Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.marginWidth = 10;
        layout.marginHeight = 10;
        layout.numColumns = 1;
        composite.setLayout(layout);

        label = new Label(composite, SWT.NONE);
        GridData labelData = new GridData();
        labelData.verticalSpan = 8;
        label.setLayoutData(labelData);
        label.setText("Select one model to create MDM metadata");

        // boolean inputModel = ((MDMConnection) connectionItem.getConnection()).isInputModel();
        getConcept();
        boolean isInputModel = concept.isInputModel();
        inputModeButton = new Button(composite, SWT.RADIO);
        inputModeButton.setText("Input MDM");
        inputModeButton.setSelection(isInputModel);
        inputModeButton.setEnabled(creation);

        outputModeButton = new Button(composite, SWT.RADIO);
        outputModeButton.setText("Output MDM");
        outputModeButton.setSelection(!isInputModel);
        outputModeButton.setEnabled(creation);
        setControl(composite);
        addListeners();

    }

    private void addListeners() {
        inputModeButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                if (concept != null) {
                    concept.setInputModel(true);
                }
            }

        });
        outputModeButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                if (concept != null) {
                    concept.setInputModel(false);
                }
            }

        });
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            ((CreateConceptWizard) getWizard()).setCurrentPage(this);
        }
    }

}
