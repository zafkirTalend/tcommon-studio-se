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
package org.talend.core.prefs;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.talend.core.runtime.CoreRuntimePlugin;

/**
 * ggu class global comment. Detailled comment
 */
public class TokenInforPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    public TokenInforPreferencePage() {
        setPreferenceStore(CoreRuntimePlugin.getInstance().getPreferenceStore());
    }

    public void init(IWorkbench workbench) {

    }

    @Override
    protected void createFieldEditors() {
        addField(new BooleanFieldEditor(ITalendCorePrefConstants.ACTIVE_TOKEN_INFORS, "Active token information function",
                getFieldEditorParent()));
        addField(new IntegerFieldEditor(ITalendCorePrefConstants.ACTIVE_TOKEN_INFORS_TIMES, "How long will send the token(days)",
                getFieldEditorParent()));
    }
}
