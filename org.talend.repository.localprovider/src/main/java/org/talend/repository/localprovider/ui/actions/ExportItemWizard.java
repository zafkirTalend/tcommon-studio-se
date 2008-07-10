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
package org.talend.repository.localprovider.ui.actions;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;

/**
 */
public class ExportItemWizard extends Wizard implements IImportWizard {

    private ExportItemWizardPage mainPage;

    private IStructuredSelection selection;

    public ExportItemWizard() {
        super();
    }

    @Override
    public void addPages() {
        super.addPages();
        mainPage = new ExportItemWizardPage(getWindowTitle(), selection);
        addPage(mainPage);
    }

    public void init(IWorkbench workbench, IStructuredSelection selection) {
        this.selection = selection;
    }

    @Override
    public boolean performCancel() {
        return mainPage.performCancel();
    }

    @Override
    public boolean performFinish() {
        return mainPage.performFinish();
    }

}
