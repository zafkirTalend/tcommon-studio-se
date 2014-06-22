// ============================================================================
//
// Copyright (C) 2006-2013 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.items.importexport.ui.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;
import org.talend.commons.ui.runtime.image.EImage;
import org.talend.commons.ui.runtime.image.ImageProvider;
import org.talend.core.GlobalServiceRegister;
import org.talend.repository.items.importexport.ui.i18n.Messages;
import org.talend.repository.items.importexport.ui.wizard.imports.ImportItemsWizard;
import org.talend.repository.model.IProxyRepositoryService;
import org.talend.repository.ui.actions.AContextualAction;

/**
 */
public final class ImportItemsAction extends AContextualAction implements IWorkbenchWindowActionDelegate {

    private IStructuredSelection structureSelection;

    public ImportItemsAction() {
        super();
        this.setText(Messages.getString("ImportItemsAction_title")); //$NON-NLS-1$
        this.setToolTipText(this.getText());
        this.setImageDescriptor(ImageProvider.getImageDesc(EImage.IMPORT_ICON));
    }

    @Override
    public void run(IAction action) {
        doRun();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.ui.actions.AContextualAction#getSelection()
     */
    @Override
    public IStructuredSelection getSelection() {
        return this.structureSelection;
    }

    @Override
    protected void doRun() {
        if (GlobalServiceRegister.getDefault().isServiceRegistered(IProxyRepositoryService.class)) {
            IProxyRepositoryService service = (IProxyRepositoryService) GlobalServiceRegister.getDefault().getService(
                    IProxyRepositoryService.class);
            if (service.getProxyRepositoryFactory().isUserReadOnlyOnCurrentProject()) {
                return;
            }
        }
        if (getSelection() == null) {
            return;
        }

        ImportItemsWizard wizard = new ImportItemsWizard();
        wizard.setWindowTitle(this.getText());
        wizard.init(PlatformUI.getWorkbench(), getSelection());

        WizardDialog dialog = new WizardDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell(), wizard);
        if (dialog.open() == Window.OK) {
            // refresh();
        }
    }

    @Override
    public void init(IWorkbenchWindow window) {

    }

    @Override
    public void init(TreeViewer viewer, IStructuredSelection selection) {
        setSelection(this, selection);
    }

    @Override
    public void selectionChanged(IAction action, ISelection selection) {
        if (selection instanceof IStructuredSelection) {
            setSelection(action, (IStructuredSelection) selection);
        } else {
            setSelection(action, null);
        }
    }

    private void setSelection(IAction action, IStructuredSelection selection) {
        this.structureSelection = selection;
        // action.setEnabled(this.structureSelection != null && !this.structureSelection.isEmpty());
    }

    @Override
    public void dispose() {
        //
    }
}
