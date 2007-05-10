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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.Locale;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.adaptor.EclipseStarter;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.epic.perleditor.IEpicService;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.utils.workbench.preferences.ComboFieldEditor;
import org.talend.core.CorePlugin;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.i18n.Messages;
import org.talend.core.prefs.ITalendCorePrefConstants;
import org.talend.designer.codegen.ICodeGeneratorService;

/**
 * DOC chuger class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class CorePreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    private OneLineComboFieldEditor languageSelectionEditor;

    private FileFieldEditorWithListeners perlInterpreter;

    /**
     * DOC yzhang CorePreferencePage class global comment. Detailled comment <br/>
     * 
     * $Id$
     * 
     */
    public class FileFieldEditorWithListeners extends FileFieldEditor {

        IEpicService service = (IEpicService) GlobalServiceRegister.getDefault().getService(IEpicService.class);;

        /**
         * DOC yzhang CorePreferencePage.FileFieldEditorWithListeners constructor comment.
         */
        public FileFieldEditorWithListeners() {
        }

        /**
         * DOC yzhang FileFieldEditorWithListeners constructor comment.
         * 
         * @param name
         * @param labelText
         * @param enforceAbsolute
         * @param parent
         */
        public FileFieldEditorWithListeners(String name, String labelText, boolean enforceAbsolute, Composite parent) {
            super(name, labelText, enforceAbsolute, parent);
        }

        /**
         * DOC yzhang Comment method "addModifyListener".
         * 
         * @param listener
         */
        public void notifyModificationToEpic() {
            service.setEpicPerlExecutableText(getTextControl().getText());
        }

    }

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

        this.perlInterpreter = new FileFieldEditorWithListeners(ITalendCorePrefConstants.PERL_INTERPRETER, Messages
                .getString("CorePreferencePage.perlInterpreter"), true, getFieldEditorParent()); //$NON-NLS-1$
        addField(perlInterpreter);

        FileFieldEditor javaInterpreter = new FileFieldEditor(ITalendCorePrefConstants.JAVA_INTERPRETER, Messages
                .getString("CorePreferencePage.javaInterpreter"), true, getFieldEditorParent()); //$NON-NLS-1$
        addField(javaInterpreter);

        IntegerFieldEditor previewLimit = new IntegerFieldEditor(ITalendCorePrefConstants.PREVIEW_LIMIT, Messages
                .getString("CorePreferencePage.previewLimit"), getFieldEditorParent(), 9); //$NON-NLS-1$
        previewLimit.setEmptyStringAllowed(false);
        previewLimit.setValidRange(1, 999999999);
        addField(previewLimit);

        // Adds a combo for language selection.
        String[][] entryNamesAndValues = { { Locale.ENGLISH.getDisplayLanguage(), Locale.ENGLISH.getLanguage() },
                { Locale.FRENCH.getDisplayLanguage(), Locale.FRENCH.getLanguage() },
                { Locale.CHINESE.getDisplayLanguage(), Locale.CHINESE.getLanguage() } };
        languageSelectionEditor = new OneLineComboFieldEditor(ITalendCorePrefConstants.LANGUAGE_SELECTOR, Messages
                .getString("CorePreferencePage.LocalLanguage"), entryNamesAndValues, //$NON-NLS-1$
                getFieldEditorParent());

        addField(languageSelectionEditor);
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
            this.perlInterpreter.notifyModificationToEpic();
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

}
