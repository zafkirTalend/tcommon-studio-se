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
package org.talend.dataprofiler.core.ui.wizard;

import org.eclipse.core.runtime.IPath;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.talend.cwm.db.connection.ConnectionParameters;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * $Id: Step0WizardPage.java 7038 2007-11-15 14:05:48Z plegall $
 * 
 */
public class Step0WizardPage extends PropertiesWizardPage {

    public Step0WizardPage(ConnectionParameters property, IPath destinationPath, boolean readOnly,
            boolean editPath) {
        super("WizardPage", property, destinationPath, readOnly, editPath); //$NON-NLS-1$

        setTitle("title");
        setDescription("desc");
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.ui.wizards.PropertiesWizardPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(Composite parent) {
        Composite container = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout(2, false);
        container.setLayout(layout);

        super.createControl(container);

        setControl(container);
//        updateContent();
        addListeners();
        // setPageComplete(false);
        
        updatePageComplete();

    }

    /*
     * @see WizardPage#becomesVisible
     */
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
           this.nameText.setFocus();
        }
    }
}
