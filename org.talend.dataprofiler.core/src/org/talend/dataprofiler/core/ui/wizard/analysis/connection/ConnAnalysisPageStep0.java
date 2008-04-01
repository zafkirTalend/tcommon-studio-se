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

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;
import org.talend.cwm.management.connection.ConnectionParameters;


/**
 * @author zqin
 *
 */
public class ConnAnalysisPageStep0 extends WizardPage {

    private ConnectionParameters connectionParams;
    /**
     * @param pageName
     */
    public ConnAnalysisPageStep0(ConnectionParameters connectionParams) {
        super("WizardPage"); 
        setTitle("New Analysis");
        setMessage("choose a connection to analysis");
        
        this.connectionParams = connectionParams;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl(Composite parent) {
        // TODO Auto-generated method stub

    }

}
