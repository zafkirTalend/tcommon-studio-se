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

import org.eclipse.jface.wizard.Wizard;
import org.talend.cwm.management.connection.ConnectionParameters;
import org.talend.dataprofiler.core.ui.wizard.analysis.MetadataWizardPage;


/**
 * @author huangssssx
 *
 */
public class ConnectionWizard extends Wizard {

    private ConnectionParameters parameters;
    
    private MetadataWizardPage metadataPage;
    
    private ConnAnalysisPageStep0 page0;
    
    private ConnAnalysisPageStep1 page1;
    /**
     * 
     */
    public ConnectionWizard(ConnectionParameters parameters) {
        
        this.parameters = parameters;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        // TODO Auto-generated method stub
        return true;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#addPages()
     */
    @Override
    public void addPages() {
        
        metadataPage = new MetadataWizardPage(parameters, null, false, true);
//        page0 = new ConnAnalysisPageStep0(parameters);
        page1 = new ConnAnalysisPageStep1(parameters);
        
        addPage(metadataPage);
//        addPage(page0);
        addPage(page1);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#canFinish()
     */
    @Override
    public boolean canFinish() {
        // TODO Auto-generated method stub
        if (this.getContainer().getCurrentPage() != page1) {
            return false;
        }
        return super.canFinish();
    }
    
    

}
