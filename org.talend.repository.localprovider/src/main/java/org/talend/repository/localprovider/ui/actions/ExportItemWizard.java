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

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;

/**
 */
public class ExportItemWizard extends Wizard implements IImportWizard {

    private ExportItemWizardPage mainPage;

    public ExportItemWizard() {
        super();
    }

    public void addPages() {
        super.addPages();
        mainPage = new ExportItemWizardPage(getWindowTitle()); //$NON-NLS-1$
        addPage(mainPage);
    }

    public void init(IWorkbench workbench, IStructuredSelection selection) {
    }

    public boolean performCancel() {
        return mainPage.performCancel();
    }

    public boolean performFinish() {
        return mainPage.performFinish();
    }

}
