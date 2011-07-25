package org.talend.core.runtime;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.talend.core.prefs.ITalendCorePrefConstants;

public class MetadataPrecisionPage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    public MetadataPrecisionPage() {
        setPreferenceStore(CoreRuntimePlugin.getInstance().getPreferenceStore());
    }

    @Override
    protected void createFieldEditors() {
        addField(new BooleanFieldEditor(ITalendCorePrefConstants.FORBIDDEN_MAPPING_LENGTH_PREC_LOGIC,
                "Forbidden mappingfile length and precision logic", getFieldEditorParent()));

    }

    public void init(IWorkbench workbench) {
        // TODO Auto-generated method stub

    }

}
