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

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.talend.cwm.management.connection.ConnectionParameters;
import org.talend.dataquality.analysis.AnalysisType;

import sun.awt.image.ImageWatched;


/**
 * @author zqin
 *
 */
public class DynamicAnalysisWizard extends Wizard {

    private ConnectionParameters connectionParams;
    
    private boolean creation;
    /**
     * 
     */
    public DynamicAnalysisWizard(ConnectionParameters connectionParams, boolean creation) {
        
        this.connectionParams = connectionParams;
        this.creation = creation;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        
        //open the related editor
        
        return true;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#addPages()
     */
    @Override
    public void addPages() {
        
        if (AnalysisType.getByName(connectionParams.getConnectionTypeForANA()) == AnalysisType.CONNECTION) {
           for (WizardPage page : DnamicAnalysisFactory.getConnectionPages(connectionParams)) {
               addPage(page);
           }
        }
        
        //deal with other cases here
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#canFinish()
     */
    @Override
    public boolean canFinish() {
        // TODO Auto-generated method stub
        return super.canFinish();
    }

}
