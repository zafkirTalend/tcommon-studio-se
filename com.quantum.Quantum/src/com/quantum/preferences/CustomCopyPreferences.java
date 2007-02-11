package com.quantum.preferences;


import com.quantum.QuantumPlugin;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class CustomCopyPreferences 
	extends FieldEditorPreferencePage 
	implements IWorkbenchPreferencePage {

		public CustomCopyPreferences() {
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
			StringFieldEditor customCopyName = new StringFieldEditor("customCopyName1", "&Name:", getFieldEditorParent());
			addField(customCopyName);
			StringFieldEditor customCopyTableItem = new StringFieldEditor("customCopyTableItem1", "&Table Item:", getFieldEditorParent());
			addField(customCopyTableItem);
			StringFieldEditor customCopyTableSeparator = new StringFieldEditor("customCopyTableSeparator1", "&Table Separator:", getFieldEditorParent());
			addField(customCopyTableSeparator);
			StringFieldEditor customCopyColumnItem = new StringFieldEditor("customCopyColumnItem1", "&Column Item:", getFieldEditorParent());
			addField(customCopyColumnItem);
			StringFieldEditor customCopyColumnSeparator = new StringFieldEditor("customCopyColumnSeparator1", "&Column Separator:", getFieldEditorParent());
			addField(customCopyColumnSeparator);
			StringFieldEditor customCopyTemplate = new StringFieldEditor("customCopyTemplate1", "&Template:", getFieldEditorParent());
			addField(customCopyTemplate);
		}

}
