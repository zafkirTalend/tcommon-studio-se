// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the  agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//   
// ============================================================================
package org.talend.core.prefs.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.adaptor.EclipseStarter;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.epic.core.preferences.PerlMainPreferencePage;
import org.epic.perleditor.PerlEditorPlugin;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.core.CorePlugin;
import org.talend.core.i18n.Messages;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.language.LanguageManager;
import org.talend.core.prefs.ITalendCorePrefConstants;
import org.talend.core.utils.XmlArray;

/**
 * DOC chuger class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class CorePreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    private OneLineComboFieldEditor languageSelectionEditor;

    private List<FieldEditor> fields = new ArrayList<FieldEditor>();

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
                .getString("CorePreferencePage.perlInterpreter"), true, getFieldEditorParent()) {//$NON-NLS-1$

            protected boolean checkState() {
                if (LanguageManager.getCurrentLanguage().equals(ECodeLanguage.JAVA)) {
                    return true;
                }

                return super.checkState();
            }

        };
        addField(perlInterpreter);

        FileFieldEditor javaInterpreter = new FileFieldEditor(ITalendCorePrefConstants.JAVA_INTERPRETER, Messages
                .getString("CorePreferencePage.javaInterpreter"), true, getFieldEditorParent()){; //$NON-NLS-1$
                protected boolean checkState() {
                    if (LanguageManager.getCurrentLanguage().equals(ECodeLanguage.PERL)) {
                        return true;
                    }

                    return super.checkState();
                }
        };
            
        addField(javaInterpreter);

        IntegerFieldEditor previewLimit = new IntegerFieldEditor(ITalendCorePrefConstants.PREVIEW_LIMIT, Messages
                .getString("CorePreferencePage.previewLimit"), getFieldEditorParent(), 9); //$NON-NLS-1$
        previewLimit.setEmptyStringAllowed(false);
        previewLimit.setValidRange(1, 999999999);
        addField(previewLimit);

        // Adds a combo for language selection.
        String[][] entryNamesAndValues = { { Locale.ENGLISH.getDisplayLanguage(), Locale.ENGLISH.getLanguage() },
                { Locale.FRENCH.getDisplayLanguage(), Locale.FRENCH.getLanguage() },
                { Locale.CHINESE.getDisplayLanguage(), Locale.CHINESE.getLanguage() },
                { Locale.GERMAN.getDisplayLanguage(), Locale.GERMAN.getLanguage() } };
        languageSelectionEditor = new OneLineComboFieldEditor(ITalendCorePrefConstants.LANGUAGE_SELECTOR, Messages
                .getString("CorePreferencePage.LocalLanguage"), entryNamesAndValues, //$NON-NLS-1$
                getFieldEditorParent());

        addField(languageSelectionEditor);

        if (LanguageManager.getCurrentLanguage() == ECodeLanguage.JAVA) {
            BooleanFieldEditor runInMultiThread = new BooleanFieldEditor(ITalendCorePrefConstants.RUN_IN_MULTI_THREAD, Messages
                    .getString("CorePreferencePage.runInMultiThread"), //$NON-NLS-1$
                    getFieldEditorParent());

            addField(runInMultiThread);

        }
        // ends
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.preference.FieldEditorPreferencePage#performOk()
     */
    @Override
    public boolean performOk() {
        boolean ok = super.performOk();
        saveLanguageType();
        if (ok) {
            if (LanguageManager.getCurrentLanguage().equals(ECodeLanguage.PERL)) {
                String perlInterpreter = getPreferenceStore().getString(ITalendCorePrefConstants.PERL_INTERPRETER);
                PerlEditorPlugin.getDefault().setExecutablePreference("\"" + perlInterpreter + "\"");
                PerlMainPreferencePage.refreshExecutableTextValue("\"" + perlInterpreter + "\"");
            }
            XmlArray.setLimitToDefault();
        }
        return ok;
    }

    private void saveLanguageType() {
        FileInputStream fin = null;
        FileOutputStream fout = null;
        try {
            URL url = Platform.getConfigurationLocation().getURL();
            log(url.getFile());
            Properties p = new Properties();
            // load the file configuration/config.ini
            File iniFile = new File(url.getFile(), "config.ini"); //$NON-NLS-1$
            fin = new FileInputStream(iniFile);
            p.load(fin);

            String languageType = getPreferenceStore().getString(ITalendCorePrefConstants.LANGUAGE_SELECTOR);

            if (languageType.equals(p.getProperty(EclipseStarter.PROP_NL))) {
                return;
            }

            p.setProperty(EclipseStarter.PROP_NL, languageType);
            fout = new FileOutputStream(iniFile);
            p.store(fout, "#Configuration File"); //$NON-NLS-1$
            fout.flush();

        } catch (Exception e) {
            ExceptionHandler.process(e);
        } finally {
            if (fin != null) {
                try {
                    fin.close();
                } catch (Exception e) {
                    // do nothing
                }

            }
            if (fout != null) {
                try {
                    fout.close();
                } catch (Exception e) {
                    // do nothing
                }

            }
        }
    }

    private void log(String s) {
        log.log(Level.INFO, s);
    }

    private static Logger log = Logger.getLogger(CorePreferencePage.class);

    /**
     * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
     */
    public void init(IWorkbench workbench) {
        // Do nothing
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.preference.FieldEditorPreferencePage#addField(org.eclipse.jface.preference.FieldEditor)
     */
    @Override
    protected void addField(FieldEditor editor) {
        super.addField(editor);
        fields.add(editor);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.preference.FieldEditorPreferencePage#checkState()
     */
    @Override
    protected void checkState() {
        super.checkState();
        int size = fields.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                FieldEditor editor = fields.get(i);
                if (!editor.isValid()) {
                    editor.setFocus();
                }
            }
        }
    }

}
