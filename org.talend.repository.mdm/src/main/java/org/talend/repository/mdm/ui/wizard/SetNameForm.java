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

import java.util.regex.Pattern;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.talend.commons.ui.swt.formtools.LabelledText;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.repository.mdm.i18n.Messages;
import org.talend.repository.ui.swt.utils.AbstractForm;

/**
 * DOC hwang class global comment. Detailled comment
 */
public class SetNameForm extends AbstractForm {

    private LabelledText nameText;

    private String conceptName;

    private String[] existingNames;

    /**
     * DOC Administrator SetNameForm constructor comment.
     * 
     * @param parent
     * @param style
     * @param existingNames
     */
    protected SetNameForm(Composite parent, ConnectionItem connectionItem, String[] existingNames) {
        super(parent, SWT.NONE, existingNames);
        this.connectionItem = connectionItem;
        this.existingNames = existingNames;
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
        Group mdmParameterGroup = new Group(this, SWT.NULL);
        GridLayout layoutGroup = new GridLayout();
        layoutGroup.numColumns = 2;
        mdmParameterGroup.setLayout(layoutGroup);

        GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
        mdmParameterGroup.setLayoutData(gridData);

        nameText = new LabelledText(mdmParameterGroup, Messages.getString("SetNameForm.NAME"), true); //$NON-NLS-1$
        checkFieldsValue();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.ui.swt.utils.AbstractForm#addFieldsListeners()
     */
    @Override
    protected void addFieldsListeners() {
        nameText.addModifyListener(new ModifyListener() {

            public void modifyText(ModifyEvent e) {
                String textValue = nameText.getText();
                boolean canContinue = true;
                String pattern = "^[a-zA-Z]+[a-zA-Z0-9\\_]*(\\{CURRENT_TABLE\\})[a-zA-Z0-9\\_]*$";//$NON-NLS-1$
                String pattern1 = "^[a-zA-Z]+[a-zA-Z0-9\\_]*$";//$NON-NLS-1$     
                if (!Pattern.matches(pattern, textValue) && !Pattern.matches(pattern1, textValue)) {
                    canContinue = false;
                    updateStatus(IStatus.ERROR, Messages.getString("SetNameForm.NAME_ILLEGAL")); //$NON-NLS-1$
                }
                if (canContinue) {
                    for (int i = 0; i < existingNames.length; i++) {
                        if (nameText.getText().equals(existingNames[i])) {
                            updateStatus(IStatus.ERROR, Messages.getString("SetNameForm.NAME_EXIST")); //$NON-NLS-1$
                            canContinue = false;
                            break;
                        }
                    }
                }
                if (canContinue) {
                    checkFieldsValue();
                }
            }

        });
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
        if (nameText == null || nameText.getCharCount() == 0) {
            updateStatus(IStatus.ERROR, Messages.getString("SetNameForm.NAME_CANNOT_BE_NULL")); //$NON-NLS-1$
            return false;
        }
        updateStatus(IStatus.OK, null);
        conceptName = nameText.getText();
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.ui.swt.utils.AbstractForm#initialize()
     */
    @Override
    protected void initialize() {
        // TODO Auto-generated method stub

    }

    protected String getName() {
        return conceptName;
    }

}
