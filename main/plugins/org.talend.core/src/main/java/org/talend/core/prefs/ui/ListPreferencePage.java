// ============================================================================
//
// Copyright (C) 2006-2014 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
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
