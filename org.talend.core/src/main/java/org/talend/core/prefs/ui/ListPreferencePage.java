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
package org.talend.core.prefs.ui;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.List;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.talend.core.CorePlugin;
import org.talend.core.i18n.Messages;

/**
 * Preference page for a simple list of String data. <br/>
 * 
 * $Id$
 * 
 */
public abstract class ListPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

    /** List of data. */
    protected List dataList;

    /** Remove button. */
    private Button removeBtn;

    /**
     * Constructs a new ListPreferencePage.
     */
    public ListPreferencePage() {
        super();
    }

    /**
     * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createContents(Composite parent) {
        Composite container = new Composite(parent, SWT.NONE);

        GridLayout layout = new GridLayout(2, false);
        container.setLayout(layout);

        dataList = new List(container, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.MULTI);
        dataList.setLayoutData(new GridData(GridData.FILL_BOTH));

        removeBtn = new Button(container, SWT.PUSH);
        removeBtn.setText(Messages.getString("ListPreferencePage.RemoveButtonName")); //$NON-NLS-1$
        removeBtn.setEnabled(false);
        removeBtn.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));

        fillContents();
        addListeners();

        return container;
    }

    protected abstract String[] getDataSaved();

    protected String[] getDataToSave() {
        return dataList.getItems();
    }

    private void fillContents() {
        dataList.setItems(getDataSaved());
    }

    private void addListeners() {
        dataList.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                int[] deletableIndices = getDeletableIndices(dataList.getSelectionIndices());
                removeBtn.setEnabled(deletableIndices.length > 0);
            }
        });

        removeBtn.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                int[] deletableIndices = getDeletableIndices(dataList.getSelectionIndices());
                dataList.remove(deletableIndices);
                removeBtn.setEnabled(false);
            }
        });
    }

    /**
     * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
     */
    public void init(IWorkbench workbench) {
        // Initialize the preference store we wish to use
        setPreferenceStore(CorePlugin.getDefault().getPreferenceStore());
    }

    protected abstract int[] getDeletableIndices(int[] indices);

}
