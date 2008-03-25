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
package org.talend.dataprofiler.core.ui.wizard.analysis;

import org.eclipse.core.runtime.IPath;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.talend.cwm.management.connection.ConnectionParameters;
import org.talend.dataprofiler.core.ui.wizard.PropertiesWizardPage;


/**
 * @author huangssssx
 *
 */
public class AnalysisWizardPageStep1 extends PropertiesWizardPage {

    private Text typeText;

    public AnalysisWizardPageStep1(ConnectionParameters property, IPath destinationPath, boolean readOnly, boolean editPath){
        super("WizardPage", property, destinationPath, readOnly, editPath);
        
        setTitle("New Analysis");
        setDescription("Add a Analysis in the repository Optionnaly the analysis can be added on a report");
    }

    /* (non-Javadoc)
     * @see org.talend.dataprofiler.core.ui.wizard.PropertiesWizardPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(Composite parent) {
        // TODO Auto-generated method stub
        Composite container = new Composite(parent, SWT.NONE);
        GridLayout gdLayout = new GridLayout(2, false);
        container.setLayout(gdLayout);
        
        super.createControl(container);
        
        Label typeLabel = new Label(container, SWT.NONE);
        typeLabel.setText("Type");
        
        typeText = new Text(container, SWT.BORDER);
        
        GridData dataForTypeText = new GridData();
        dataForTypeText.widthHint = 150;
        typeText.setLayoutData(dataForTypeText);
        typeText.setEnabled(false);
        
        setControl(container);
        addListeners();
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.DialogPage#setVisible(boolean)
     */
    @Override
    public void setVisible(boolean visible) {
        // TODO Auto-generated method stub
        super.setVisible(visible);
    }        
    
    public String getName(){
        return this.nameText.getText();
    }
}
