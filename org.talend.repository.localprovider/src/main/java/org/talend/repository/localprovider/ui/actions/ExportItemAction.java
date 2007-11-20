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

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.talend.commons.ui.image.EImage;
import org.talend.commons.ui.image.ImageProvider;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.repository.localprovider.i18n.Messages;
import org.talend.repository.model.ProxyRepositoryFactory;
import org.talend.repository.model.RepositoryNode;
import org.talend.repository.ui.actions.AContextualAction;

/**
 */
/**
 * DOC Administrator class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 1 2006-09-29 17:06:40 +0000 (ææäº, 29 ä¹æ 2006) nrousseau $
 * 
 */
public final class ExportItemAction extends AContextualAction implements IWorkbenchWindowActionDelegate {

    private static final String EXPORT_ITEM = Messages.getString("ExportItemAction.Label"); //$NON-NLS-1$

    private boolean visible;

    public void init(TreeViewer viewer, IStructuredSelection selection) {
        boolean visible = !selection.isEmpty();
        this.setText(null);
        if (ProxyRepositoryFactory.getInstance().isUserReadOnlyOnCurrentProject()) {
            visible = false;
        }
        for (Object object : (selection).toArray()) {
            if (visible) {
                RepositoryNode node = (RepositoryNode) object;
                if (node.getContentType() == ERepositoryObjectType.HTML_DOC) {
                    visible = false;
                    continue;
                }
            }
        }
    }

    public ExportItemAction() {
        super();
        this.setText(EXPORT_ITEM);
        this.setToolTipText(EXPORT_ITEM);
        this.setImageDescriptor(ImageProvider.getImageDesc(EImage.EXPORT_ICON));
    }

    public void run() {
        ExportItemWizard wizard = new ExportItemWizard();
        IWorkbench workbench = this.getViewPart().getViewSite().getWorkbenchWindow().getWorkbench();
        wizard.setWindowTitle(EXPORT_ITEM);
        wizard.init(workbench, (IStructuredSelection) this.getSelection());

        Shell activeShell = Display.getCurrent().getActiveShell();
        WizardDialog dialog = new WizardDialog(activeShell, wizard);
        dialog.open();
    }

    public void dispose() {
    }

    public void init(IWorkbenchWindow window) {
    }

    public void run(IAction action) {
        run();
    }

    public void selectionChanged(IAction action, ISelection selection) {
    }

    /**
     * Getter for visible.
     * 
     * @return the visible
     */
    @Override
    public boolean isVisible() {
        return this.visible;
    }

    /**
     * Sets the visible.
     * 
     * @param visible the visible to set
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
