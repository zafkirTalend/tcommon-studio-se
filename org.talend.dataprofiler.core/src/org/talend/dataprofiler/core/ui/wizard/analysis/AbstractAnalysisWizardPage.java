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

import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;
import org.talend.dq.analysis.parameters.AnalysisParameter;


/**
 * @author huangssssx
 *
 */
public abstract class AbstractAnalysisWizardPage extends WizardPage {

    private boolean canFinishEarly = false, hasPages = true;
    
    private static AnalysisParameter connectionParams;
    /**
     * @param pageName
     */
    public AbstractAnalysisWizardPage() {
        super("Wizard Page");
    }
    
    /**
     * Makes the next page visible.
     */
    public void advanceToNextPageOrFinish() {
            if (canFlipToNextPage()) {
                getContainer().showPage(getNextPage());
            } else if (isCanFinishEarly()) {
                if (getWizard().performFinish()) {
                    ((WizardDialog) getContainer()).close();
                }
            }
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.WizardPage#canFlipToNextPage()
     */
    @Override
    public boolean canFlipToNextPage() {
        // TODO Auto-generated method stub
        if (hasPages) {
            return super.canFlipToNextPage();
        } else {
            return false;
        }
        
    }
    
    /**
     * @return the canFinishEarly
     */
    public boolean isCanFinishEarly() {
        return this.canFinishEarly;
    }

    
    /**
     * @param canFinishEarly the canFinishEarly to set
     */
    public void setCanFinishEarly(boolean canFinishEarly) {
        this.canFinishEarly = canFinishEarly;
    }
    /**
     * @return the hasPages
     */
    public boolean isHasPages() {
        return this.hasPages;
    }

    
    /**
     * @param hasPages the hasPages to set
     */
    public void setHasPages(boolean hasPages) {
        this.hasPages = hasPages;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    public abstract void createControl(Composite parent);

    
    /**
     * @return the connectionParams
     */
    public static AnalysisParameter getConnectionParams() {
        return AbstractAnalysisWizardPage.connectionParams;
    }

    
    /**
     * @param connectionParams the connectionParams to set
     */
    public synchronized void setConnectionParams(AnalysisParameter connectionParams) {
        AbstractAnalysisWizardPage.connectionParams = connectionParams;
    }

}
