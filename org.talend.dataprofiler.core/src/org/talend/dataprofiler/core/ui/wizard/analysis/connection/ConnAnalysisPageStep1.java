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
package org.talend.dataprofiler.core.ui.wizard.analysis.connection;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.talend.cwm.management.connection.ConnectionParameters;
import org.talend.dataquality.analysis.AnalysisType;


/**
 * @author zqin
 *
 */
public class ConnAnalysisPageStep1 extends WizardPage {

    private String defaultInfor = "Set filter on tables and / or views if needed.\n" +
    		"By default, all tables and views will be used in the analysis.\n" +
    		"Separate several filters with comma ','";
    
    private Text tableFilter;
    
    private Text viewFilter;
    
    private ConnectionParameters connectionParams;
    /**
     * @param pageName
     */
    public ConnAnalysisPageStep1(ConnectionParameters connectionParams) {
        super("WizardPage"); 
        setTitle("New Analysis");
        setMessage("Add the filters for Connection Analysis");
        
        this.connectionParams = connectionParams;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl(Composite parent) {
        
        Composite container = new Composite(parent, SWT.NONE);
        container.setLayout(new GridLayout());  

        GridData gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.heightHint = 70;
        Label inforLabel = new Label(container, SWT.BORDER);
        inforLabel.setText(defaultInfor);
        inforLabel.setLayoutData(gd);
        
        Composite subContainer = new Composite(container, SWT.NONE);
        GridLayout subLayout = new GridLayout(2, false);
        subContainer.setLayout(subLayout);
        subContainer.setLayoutData(new GridData(GridData.FILL_BOTH));

        Label labelTable = new Label(subContainer, SWT.NONE);
        labelTable.setText("Table name filter");
        tableFilter = new Text(subContainer, SWT.BORDER);
        tableFilter.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        tableFilter.setText("%");
        
        Label labelView = new Label(subContainer, SWT.NONE);
        labelView.setText("View name filter");
        viewFilter = new Text(subContainer, SWT.BORDER);
        viewFilter.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        viewFilter.setText("%");
        
        setControl(container);
    }

}
