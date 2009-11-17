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

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Tree;
import org.talend.commons.ui.swt.formtools.Form;
import org.talend.commons.ui.swt.formtools.LabelledCombo;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.repository.ui.swt.utils.AbstractForm;
import org.talend.repository.ui.wizards.metadata.connection.files.xml.TreePopulator;

/**
 * DOC hwang class global comment. Detailled comment
 */
public class MDMSchemaForm extends AbstractForm {

    private LabelledCombo conceptCombo;

    private transient Tree availableXmlTree;

    private TreePopulator treePopulator;

    private ConnectionItem connectionItem;

    private List<String> concepts;

    /**
     * DOC Administrator MDMSchemaForm constructor comment.
     * 
     * @param parent
     * @param style
     */
    protected MDMSchemaForm(Composite parent, int style, ConnectionItem connectionItem, List<String> concepts) {
        super(parent, style);
        this.connectionItem = connectionItem;
        this.concepts = concepts;
        setupForm(false);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.ui.swt.utils.AbstractForm#adaptFormToReadOnly()
     */
    @Override
    protected void adaptFormToReadOnly() {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.ui.swt.utils.AbstractForm#addFields()
     */
    @Override
    protected void addFields() {
        // Group mdmParameterGroup = new Group(this, SWT.NULL);
        Group mdmParameterGroup = Form.createGroup(this, 1, "", 100);//$NON-NLS-1$
        mdmParameterGroup.setText("");
        GridLayout layoutGroup = new GridLayout();
        layoutGroup.numColumns = 2;
        mdmParameterGroup.setLayout(layoutGroup);

        GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
        mdmParameterGroup.setLayoutData(gridData);

        conceptCombo = new LabelledCombo(mdmParameterGroup, "Concept", "", concepts, true);

        mdmParameterGroup = Form.createGroup(this, 1, "", 220);//$NON-NLS-1$
        Composite compositeFileViewer = Form.startNewDimensionnedGridLayout(mdmParameterGroup, 1, 300, 220);

        gridData = new GridData(GridData.FILL_BOTH);
        gridData.minimumWidth = 300;
        // gridData.minimumHeight = 150;

        availableXmlTree = new Tree(compositeFileViewer, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
        availableXmlTree.setLayoutData(gridData);

        // if (!isInWizard()) {
        // // Composite BottomButton
        // Composite compositeBottomButton = Form.startNewGridLayout(this, 2, false, SWT.CENTER, SWT.CENTER);
        //
        // // Button Cancel
        //            cancelButton = new UtilsButton(compositeBottomButton, Messages.getString("CommonWizard.cancel"), //$NON-NLS-1$
        // WIDTH_BUTTON_PIXEL, HEIGHT_BUTTON_PIXEL);
        // }
        addUtilsButtonListeners();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.ui.swt.utils.AbstractForm#addFieldsListeners()
     */
    @Override
    protected void addFieldsListeners() {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.ui.swt.utils.AbstractForm#addUtilsButtonListeners()
     */
    @Override
    protected void addUtilsButtonListeners() {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.ui.swt.utils.AbstractForm#checkFieldsValue()
     */
    @Override
    protected boolean checkFieldsValue() {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.ui.swt.utils.AbstractForm#initialize()
     */
    @Override
    protected void initialize() {
        this.treePopulator = new TreePopulator(availableXmlTree);

    }

}
