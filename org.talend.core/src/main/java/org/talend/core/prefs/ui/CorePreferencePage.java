// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
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

import java.net.URL;
import java.util.Locale;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.talend.commons.utils.workbench.preferences.ComboFieldEditor;
import org.talend.core.CorePlugin;
import org.talend.core.i18n.Messages;
import org.talend.core.prefs.ITalendCorePrefConstants;

/**
 * DOC chuger class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class CorePreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    /**
     * Construct a new CorePreferencePage.
     */
    public CorePreferencePage() {
        super(GRID);

        // Set the preference store for the preference page.
        IPreferenceStore store = CorePlugin.getDefault().getPreferenceStore();
        setPreferenceStore(store);
    }

    /**
     * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
     */
    @Override
    protected void createFieldEditors() {
        DirectoryFieldEditor filePathTemp = new DirectoryFieldEditor(ITalendCorePrefConstants.FILE_PATH_TEMP, Messages
                .getString("CorePreferencePage.temporaryFiles"), getFieldEditorParent()); //$NON-NLS-1$
        addField(filePathTemp);

        FileFieldEditor perlInterpreter = new FileFieldEditor(ITalendCorePrefConstants.PERL_INTERPRETER, Messages
                .getString("CorePreferencePage.perlInterpreter"), true, getFieldEditorParent()); //$NON-NLS-1$
        addField(perlInterpreter);

        StringFieldEditor secondaryPerlInterpreter = new StringFieldEditor(
                ITalendCorePrefConstants.PERL_SECONDARY_INTERPRETER, Messages
                        .getString("CorePreferencePage.secondaryPerlInterpreter"), //$NON-NLS-1$
                getFieldEditorParent());
        addField(secondaryPerlInterpreter);

        FileFieldEditor javaInterpreter = new FileFieldEditor(ITalendCorePrefConstants.JAVA_INTERPRETER, Messages
                .getString("CorePreferencePage.javaInterpreter"), true, getFieldEditorParent()); //$NON-NLS-1$
        addField(javaInterpreter);

        IntegerFieldEditor previewLimit = new IntegerFieldEditor(ITalendCorePrefConstants.PREVIEW_LIMIT, Messages
                .getString("CorePreferencePage.previewLimit"), getFieldEditorParent(), 9); //$NON-NLS-1$
        previewLimit.setEmptyStringAllowed(false);
        previewLimit.setValidRange(1, 999999999);
        addField(previewLimit);

        //Adds a combo for language selection.
        String[][] entryNamesAndValues = { { Locale.ENGLISH.getDisplayLanguage(), Locale.ENGLISH.getLanguage() },
                { Locale.FRENCH.getDisplayLanguage(), Locale.FRENCH.getLanguage() },
                { Locale.CHINESE.getDisplayLanguage(), Locale.CHINESE.getLanguage() } };
        ComboFieldEditor languageSelectionEditor = new OneLineComboFieldEditor(
                ITalendCorePrefConstants.LANGUAGE_SELECTOR, Messages.getString("CorePreferencePage.LocalLanguage"), entryNamesAndValues, //$NON-NLS-1$
                getFieldEditorParent());
        addField(languageSelectionEditor);
        // ends
    }

    
    /* (non-Javadoc)
     * @see org.eclipse.jface.preference.FieldEditorPreferencePage#performOk()
     */
    @Override
    public boolean performOk() {
        URL url=Platform.getConfigurationLocation().getURL();
        System.out.println(url);
        
        
        return super.performOk();
    }
    /**
     * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
     */
    public void init(IWorkbench workbench) {
        // Do nothing
    }
}
