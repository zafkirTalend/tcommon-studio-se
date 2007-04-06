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
package org.talend.migrationtool.model.summary;

import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.talend.core.model.migration.IProjectMigrationTask;
import org.talend.migrationtool.i18n.Messages;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 */
public class SummaryDialog extends TitleAreaDialog {

    private static final int DIALOG_HEIGHT = 350;

    private List<IProjectMigrationTask> tasks;

    public SummaryDialog(Shell parentShell, List<IProjectMigrationTask> tasks) {
        super(parentShell);
        this.tasks = tasks;
        setHelpAvailable(false);
    }

    @Override
    protected Control createContents(Composite parent) {
        Control control = super.createContents(parent);
        this.setMessage(Messages.getString("migrationTasksRecapDialog.description")); //$NON-NLS-1$
        return control;
    }

    @Override
    protected Control createDialogArea(final Composite parent) {
        int height = DIALOG_HEIGHT - getTitleImageLabel().getSize().y;
        return new SummaryComposite(parent, SWT.NONE, tasks, height);
    }

    @Override
    protected void configureShell(final Shell newShell) {
        super.configureShell(newShell);
        newShell.setText(Messages.getString("migrationTasksRecapDialog.title")); //$NON-NLS-1$
        newShell.setSize(600, DIALOG_HEIGHT);
    }

    protected void createButtonsForButtonBar(Composite parent) {
        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
    }
}
