package com.quantum.preferences;


import com.quantum.QuantumPlugin;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class DatabasePreferences 
	extends FieldEditorPreferencePage 
	implements IWorkbenchPreferencePage {

		public DatabasePreferences() {
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
			BooleanFieldEditor getComments = new BooleanFieldEditor("getComments", "Get &Remarks for Columns", getFieldEditorParent());
			BooleanFieldEditor getSynonyms = new BooleanFieldEditor("getSynonyms", "Get &Synonyms from Database", getFieldEditorParent());
			addField(getComments);
			addField(getSynonyms);
		}

}
