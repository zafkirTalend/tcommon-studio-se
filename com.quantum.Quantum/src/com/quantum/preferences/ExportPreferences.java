package com.quantum.preferences;

import com.quantum.PluginPreferences;
import com.quantum.QuantumPlugin;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class ExportPreferences extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public ExportPreferences() {
		super(FieldEditorPreferencePage.GRID);
	
		// Set the preference store for the preference page.
		IPreferenceStore store =
			QuantumPlugin.getDefault().getPreferenceStore();
		setPreferenceStore(store);
	}

	public void init(IWorkbench workbench) {
		this.workbench = workbench;
	}

	IWorkbench workbench;
	
	protected void createFieldEditors() {
		BooleanFieldEditor confirmOverwrite = new BooleanFieldEditor(PluginPreferences.EXPORT_CONFIRM_OVERWRITE, 
												"&Ask for confirmation on overwrite ?", getFieldEditorParent());
		addField(confirmOverwrite);
	}


}
