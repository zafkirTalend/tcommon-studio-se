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

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;
import org.talend.dataprofiler.core.ImageLib;
import org.talend.dq.analysis.parameters.AnalysisParameter;

/**
 * @author zqin
 *
 */
public class CreateNewAnalysisWizard extends Wizard {
    
    private IWorkbench workbench;

    private Object selection;
    
    private boolean creation;

    private AnalysisParameter connectionParams;
    
    private NewWizardSelectionPage mainPage;
    
    public CreateNewAnalysisWizard(IWorkbench workbench, boolean creation, IStructuredSelection selection,
            String[] existingNames) {
        this.creation = creation;
        this.init(workbench, selection);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        
        // if we're finishing from the main page then perform finish on the selected wizard.
        if (getContainer().getCurrentPage() == mainPage) {
            if (mainPage.isCanFinishEarly()) {
                IWizard wizard = mainPage.getSelectedWizard();
                wizard.setContainer(getContainer());
                return wizard.performFinish();
            }
        }
        return true;
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
     */
    public void init(IWorkbench workbench, IStructuredSelection selection) {
        this.workbench = workbench;
        this.selection = selection;
        
        mainPage = new NewWizardSelectionPage();
        mainPage.setConnectionParams(new AnalysisParameter());
 
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#addPages()
     */
    @Override
    public void addPages() {

        setWindowTitle("Create New Analysis");
        setDefaultPageImageDescriptor(ImageLib.getImageDescriptor(ImageLib.REFRESH_IMAGE));

        if (creation) {
            addPage(mainPage);
        } else {
            Wizard wizard = WizardFactory.createConnectionWizard();
            wizard.addPages();
            
            IWizardPage[] pages = wizard.getPages();
            
            for (IWizardPage page : pages) {
                addPage(page);
            }
        }
        
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#canFinish()
     */
    @Override
    public boolean canFinish() {
        // TODO Auto-generated method stub
        if (mainPage != null) {
            return mainPage.isCanFinishEarly();
        }
        
        return super.canFinish();
    }
    
    
}
