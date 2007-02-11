package com.quantum.preferences;


import com.quantum.Messages;
import com.quantum.PluginPreferences;
import com.quantum.QuantumPlugin;
import com.quantum.log.QuantumLog;
import com.quantum.view.tableview.TableView;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * PreferencePage for the global options on QuantumDB
 * @author panic
 *
 */
public class GlobalPreferences 
extends FieldEditorPreferencePage 
implements IWorkbenchPreferencePage {

	public GlobalPreferences() {
		super(FieldEditorPreferencePage.GRID);
		setPreferenceStore(QuantumPlugin.getDefault().getPreferenceStore());
	}

	public void init(IWorkbench workbench) {
	}

	/**
     * @param composite
     */
    private void createLoggingGroup(Composite composite) {
        Group group = new Group(composite, SWT.NONE);
        group.setText(Messages.getString(getClass(), "logging"));
        GridLayout innerLayout = new GridLayout();
		innerLayout.numColumns = 2;
		group.setLayout(innerLayout);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 2;
        group.setLayoutData(gridData);
		
        IntegerFieldEditor numberOfEntries = new IntegerFieldEditor(
            	QuantumLog.NUMBER_OF_ENTRIES_PREFERENCE_NAME,
            	Messages.getString(getClass(), QuantumLog.NUMBER_OF_ENTRIES_PREFERENCE_NAME), 
                        group);
        addField(numberOfEntries);
        SeverityFieldEditor logLevel = new SeverityFieldEditor(
            	QuantumLog.LOG_LEVEL_PREFERENCE_NAME,
            	Messages.getString(getClass(), QuantumLog.LOG_LEVEL_PREFERENCE_NAME), 
                        group);
        addField(logLevel);
        
        // the field editor thinks that it knows best about how to lay out these fields.
        // After it's done monkeying around with the layout, let's change it back to
        // the way we want it.
        innerLayout = new GridLayout();
		innerLayout.numColumns = 2;
		innerLayout.marginHeight = 5;
		innerLayout.marginWidth = 5;
		group.setLayout(innerLayout);
    }

    protected void createFieldEditors() {
		BooleanFieldEditor useJobs = new BooleanFieldEditor(PluginPreferences.GLOBAL_USE_JOBS, 
				"&Run queries in separate threads ?", getFieldEditorParent());
		
		addField(useJobs);

        IntegerFieldEditor queryHistorySize = new IntegerFieldEditor(
            "com.quantum.model.Bookmark.queryHistorySize", 
            Messages.getString(getClass().getName() + "." 
                + "com.quantum.model.Bookmark.queryHistorySize"), 
            getFieldEditorParent());
        queryHistorySize.setValidRange(0, Integer.MAX_VALUE);
        this.addField(queryHistorySize);
        
        StringFieldEditor defaultBookmarksDir = new StringFieldEditor(
        	"com.quantum.plugin.defaultBookmarksFile",
        	Messages.getString(getClass().getName() + "." 
                    + "com.quantum.plugin.defaultBookmarksFile"), 
                    getFieldEditorParent());    
        this.addField(defaultBookmarksDir);

		IntegerFieldEditor tableViewNumberRows = new IntegerFieldEditor(
				 TableView.NUMBER_OF_ROWS_PREFERENCE_NAME,
		        	Messages.getString(getClass().getName() + "." 
		                    + "com.quantum.tableView.numberOfRows"), 
		                    getFieldEditorParent());
		 tableViewNumberRows.setValidRange(0, Integer.MAX_VALUE);
		 this.addField(tableViewNumberRows);
				
		 IntegerFieldEditor tableViewMaximumCharsCell = new IntegerFieldEditor(
				 TableView.MAXIMUM_CHARS_CELL_PREFERENCE_NAME,
		        	Messages.getString(getClass().getName() + "." 
		                    + "com.quantum.tableView.maximumCharsCell"), 
		                    getFieldEditorParent());
		 tableViewMaximumCharsCell.setValidRange(512, Integer.MAX_VALUE);
		 this.addField(tableViewMaximumCharsCell);
		
		 createLoggingGroup(getFieldEditorParent());

	}
}
