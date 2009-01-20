package org.talend.librariesmanager.prefs;

import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.core.CorePlugin;
import org.talend.librariesmanager.i18n.Messages;

public class LibrariesMainPreferencesPage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    private static final String SINGLE_MODE = "true"; //$NON-NLS-1$

    private static final String SEPARATE_MODE = "false"; //$NON-NLS-1$

    // private String oldPath;

    private DirectoryFieldEditor externalLibrariesPath;

    private DirectoryFieldEditor externalLibrariesPathJava;

    private DirectoryFieldEditor externalLibrariesPathPerl;

    private RadioGroupFieldEditor choice;

    public LibrariesMainPreferencesPage() {
        super(GRID);
    }

    @Override
    protected void createFieldEditors() {
        choice = new RadioGroupFieldEditor(PreferencesUtilities.EXTERNAL_LIB_PATH_MODE_SINGLE, Messages
                .getString("MainPreferencesPage.folderSelectionMode"), 1, new String[][] { //$NON-NLS-1$
                { Messages.getString("MainPreferencesPage.sameRootFolder"), SINGLE_MODE }, //$NON-NLS-1$
                { Messages.getString("MainPreferencesPage.separateRootFolder"), SEPARATE_MODE } }, //$NON-NLS-1$
                getFieldEditorParent());

        externalLibrariesPath = new DirectoryFieldEditor(PreferencesUtilities.EXTERNAL_LIB_PATH, Messages
                .getString("MainPreferencesPage.externalLibPath"), getFieldEditorParent()); //$NON-NLS-1$
        addField(externalLibrariesPath);
        externalLibrariesPathJava = new DirectoryFieldEditor(PreferencesUtilities.EXTERNAL_LIB_PATH_JAVA, Messages
                .getString("MainPreferencesPage.externalLibPathJava"), getFieldEditorParent()); //$NON-NLS-1$
        addField(externalLibrariesPath);
        externalLibrariesPathPerl = new DirectoryFieldEditor(PreferencesUtilities.EXTERNAL_LIB_PATH_PERL, Messages
                .getString("MainPreferencesPage.externalLibPathPerl"), getFieldEditorParent()); //$NON-NLS-1$

        addField(choice);
        addField(externalLibrariesPath);
        addField(externalLibrariesPathJava);
        addField(externalLibrariesPathPerl);

        activateFields(getPreferenceStore().getBoolean(PreferencesUtilities.EXTERNAL_LIB_PATH_MODE_SINGLE));
    }

    @Override
    protected void initialize() {
        super.initialize();
        choice.setPropertyChangeListener(new MyListener());
    }

    private class MyListener implements IPropertyChangeListener {

        public void propertyChange(PropertyChangeEvent event) {
            activateFields(event.getNewValue().equals(SINGLE_MODE));
        }
    }

    private void activateFields(boolean singleMode) {
        externalLibrariesPath.setEnabled(singleMode, getFieldEditorParent());
        externalLibrariesPathJava.setEnabled(!singleMode, getFieldEditorParent());
        externalLibrariesPathPerl.setEnabled(!singleMode, getFieldEditorParent());
    }

    public void init(IWorkbench workbench) {
        setPreferenceStore(PreferencesUtilities.getPreferenceStore());

        // oldPath=PreferencesUtilities.getLibrariesPath();
    }

    @Override
    public boolean performOk() {
        boolean toReturn = super.performOk();

        // final String newPath = PreferencesUtilities.getLibrariesPath();
        if (toReturn) {// && !oldPath.equals(newPath)) {
            try {
                CorePlugin.getDefault().getLibrariesService().syncLibraries();
            } catch (Exception e) {
                ExceptionHandler.process(e);
                return false;
            }
        }

        return toReturn;

    }

}
