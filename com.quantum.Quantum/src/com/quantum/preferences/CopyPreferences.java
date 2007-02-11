package com.quantum.preferences;


import com.quantum.QuantumPlugin;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class CopyPreferences 
	extends FieldEditorPreferencePage 
	implements IWorkbenchPreferencePage {

		public CopyPreferences() {
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
			StringFieldEditor copyColumnSeparator = new StringFieldEditor("copyColumnSeparator", "&Normal Copy Separator:", getFieldEditorParent());
			this.
			addField(copyColumnSeparator);
		}

}
