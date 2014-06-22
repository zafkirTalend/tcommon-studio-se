// ============================================================================
//
// Copyright (C) 2006-2011 Talend Inc. - www.talend.com
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

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.core.CorePlugin;

/**
 * Preference for the Metadata Talend type files.
 * 
 * $Id: MetadataTalendTypePreferencePage.java 2738 2007-04-26 13:12:27Z cantoine $
 * 
 */
public class MetadataTalendTypePreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    /**
     * MetadataTalendTypePreferencePage.
     * 
     * $Id: SpagoBiPreferencePage.java 2738 2007-04-26 13:12:27Z cantoine $
     * 
     */

    public MetadataTalendTypePreferencePage() {
        super(FLAT);
    }

    @Override
    protected IPreferenceStore doGetPreferenceStore() {
        // return preferenceStore;
        return CorePlugin.getDefault().getPreferenceStore();
    }

    @Override
    protected void createFieldEditors() {
        addField(new MetadataTalendTypeEditor(MetadataTalendTypeEditor.ID, "Talend Type Mapping Files", getFieldEditorParent())); //$NON-NLS-1$
    }

    @Override
    protected void initialize() {
        try {
            super.initialize();
        } catch (RuntimeException e) {
            // e.printStackTrace();
            ExceptionHandler.process(e);
            setErrorMessage(e.getMessage());
        }
    }

    @Override
    protected void checkState() {
        if (getErrorMessage() == null) {
            super.checkState();
        } else {
            setValid(false);
        }
    }

    public void init(IWorkbench workbench) {
    }

}
