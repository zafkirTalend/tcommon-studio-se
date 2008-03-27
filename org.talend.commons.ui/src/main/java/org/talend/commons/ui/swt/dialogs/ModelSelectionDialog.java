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
package org.talend.commons.ui.swt.dialogs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.SelectionDialog;
import org.talend.commons.ui.i18n.Messages;

/**
 * DOC zqin class global comment. Detailled comment
 */
public class ModelSelectionDialog extends SelectionDialog {

    private static final String TITLE = Messages.getString("ModelSelectionDialog.Title"); //$NON-NLS-1$

    private static final String MESSAGE = Messages.getString("ModelSelectionDialog.Message"); //$NON-NLS-1$

    private int optionValue = -1;

    private Button buildIn, repository;

    public ModelSelectionDialog(Shell parentShell) {
        super(parentShell);
        setHelpAvailable(false);
        setTitle(TITLE);
        setMessage(MESSAGE);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        Composite composite = (Composite) super.createDialogArea(parent);
        createMessageArea(composite);
        Label titleBarSeparator = new Label(composite, SWT.HORIZONTAL | SWT.SEPARATOR);
        titleBarSeparator.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        createOptionArea(composite);
        return composite;
    }

    protected Control createOptionArea(Composite composite) {
        Composite inner = new Composite(composite, SWT.NONE);
        GridLayout gridLayout = new GridLayout();
        gridLayout.marginHeight = 0;
        inner.setLayout(gridLayout);
        GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
        gridData.minimumWidth = 300;
        inner.setLayoutData(gridData);

        Group group = new Group(inner, SWT.NONE);
        group.setText(Messages.getString("ModelSelectionDialog.Option")); //$NON-NLS-1$
        gridLayout = new GridLayout();
        gridLayout.horizontalSpacing = 10;
        group.setLayout(gridLayout);
        group.setLayoutData(gridData);

        buildIn = new Button(group, SWT.RADIO);
        buildIn.setText(Messages.getString("ModelSelectionDialog.BuiltIn")); //$NON-NLS-1$

        repository = new Button(group, SWT.RADIO);
        repository.setText(Messages.getString("ModelSelectionDialog.Update")); //$NON-NLS-1$
        return inner;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.Dialog#okPressed()
     */
    @Override
    protected void okPressed() {
        if (buildIn.getSelection())
            setOptionValue(0);
        if (repository.getSelection())
            setOptionValue(1);

        super.okPressed();
    }

    /**
     * Getter for optionValue.
     * 
     * @return the optionValue
     */
    public int getOptionValue() {
        return this.optionValue;
    }

    /**
     * Sets the optionValue.
     * 
     * @param optionValue the optionValue to set
     */
    public void setOptionValue(int optionValue) {
        this.optionValue = optionValue;
    }

}
