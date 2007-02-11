package com.quantum.wizards;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;

/**
 * @author BC
 */
public class ExportBookmarkWizard extends Wizard implements IExportWizard {
    
    private ExportBookmarkWizardPage page = new ExportBookmarkWizardPage();

    public ExportBookmarkWizard() {
        setWindowTitle("Export Bookmarks");
    }

    public boolean performFinish() {
        return this.page.finish();
    }

    public void init(IWorkbench workbench, IStructuredSelection selection) {
    }

    /**
     * @see org.eclipse.jface.wizard.IWizard#addPages()
     */
    public void addPages() {
        super.addPages();
        addPage(this.page);
    }

}
