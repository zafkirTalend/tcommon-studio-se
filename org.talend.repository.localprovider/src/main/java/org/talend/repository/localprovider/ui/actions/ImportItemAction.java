// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006-2007 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
//
// ============================================================================
package org.talend.repository.localprovider.ui.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.talend.commons.ui.image.EImage;
import org.talend.commons.ui.image.ImageProvider;
import org.talend.repository.localprovider.i18n.Messages;
import org.talend.repository.localprovider.ui.wizard.ImportItemWizard;
import org.talend.repository.model.RepositoryNode;
import org.talend.repository.model.RepositoryNode.ENodeType;
import org.talend.repository.ui.actions.AContextualAction;

/**
 */
public final class ImportItemAction extends AContextualAction implements IWorkbenchWindowActionDelegate {

    private static final String IMPORT_ITEM = Messages.getString("ImportItemAction.Label"); //$NON-NLS-1$

    public void init(TreeViewer viewer, IStructuredSelection selection) {
        boolean canWork = false;

        if (selection.size() == 1) {
            if (selection.getFirstElement() instanceof RepositoryNode) {
                RepositoryNode repositoryNode = (RepositoryNode) selection.getFirstElement();
                if (repositoryNode.getType().equals(ENodeType.SYSTEM_FOLDER)) {
                    canWork = true;
                }
            }
        }
        setEnabled(canWork);
    }

    public boolean isVisible() {
        return isEnabled();
    }

    public ImportItemAction() {
        super();
        this.setText(IMPORT_ITEM);
        this.setToolTipText(IMPORT_ITEM);
        this.setImageDescriptor(ImageProvider.getImageDesc(EImage.IMPORT_ICON));
    }

    public void run() {
        ImportItemWizard wizard = new ImportItemWizard();
        IWorkbench workbench = this.getViewPart().getViewSite().getWorkbenchWindow().getWorkbench();
        wizard.setWindowTitle(IMPORT_ITEM);
        wizard.init(workbench, (IStructuredSelection) this.getSelection());

        Shell activeShell = Display.getCurrent().getActiveShell();
        WizardDialog dialog = new WizardDialog(activeShell, wizard);
        if (dialog.open() == Window.OK) {
            refresh();
        }
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
}
