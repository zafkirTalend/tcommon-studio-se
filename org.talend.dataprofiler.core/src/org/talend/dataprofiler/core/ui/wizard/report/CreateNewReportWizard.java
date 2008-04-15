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
package org.talend.dataprofiler.core.ui.wizard.report;

import org.eclipse.jface.wizard.Wizard;
import org.talend.dataprofiler.core.ui.wizard.analysis.MetadataWizardPage;


/**
 * DOC zqin  class global comment. Detailled comment
 */
public class CreateNewReportWizard extends Wizard {

    /**
     * DOC zqin CreateNewReportWizard constructor comment.
     */
    public CreateNewReportWizard() {
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#addPages()
     */
    @Override
    public void addPages() {
        
        try {
            NewReportMetadataWizardPage metadataPage = new NewReportMetadataWizardPage();
            NewReportParameterWizardPage parameterPage = new NewReportParameterWizardPage();
            
            addPage(metadataPage);
            addPage(parameterPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}
