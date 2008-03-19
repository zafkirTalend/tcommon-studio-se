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

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * DOC zqin class global comment. Detailled comment
 */
public class ModelSelectionDialog extends TitleAreaDialog {

    private int optionValue = -1;

    private Button buildIn, repository;

    public ModelSelectionDialog(Shell parentShell) {
        super(parentShell);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    @Override
    protected void configureShell(Shell newShell) {
        // TODO Auto-generated method stub
        super.configureShell(newShell);

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea(Composite parent) {

        setTitle("Select the model:");

        setMessage("switch to Built-In Mode, or update the repository connection, or cancel:");

        Composite comp = (Composite) super.createDialogArea(parent);

        buildIn = new Button(comp, SWT.RADIO);
        buildIn.setText("Change to Build-In Model");

        repository = new Button(comp, SWT.RADIO);
        repository.setText("Refresh the repository connection");

        comp.setLayout(new GridLayout());

        GridData data = new GridData();
        data.verticalSpan = 70;
        comp.setLayoutData(data);

        return comp;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.Dialog#okPressed()
     */
    @Override
    protected void okPressed() {
        // TODO Auto-generated method stub
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
