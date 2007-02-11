package com.quantum.preferences;

import com.quantum.Messages;
import com.quantum.PluginPreferences;
import com.quantum.QuantumPlugin;
import com.quantum.util.versioning.VersioningHelper;

import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FontDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;


/**
 * A preference page for font and colour preferences for the SQL
 * Editor.  
 * 
 * @author Tom Schneider
 */
public class SQLEditorPreferences extends PreferencePage 
		implements IWorkbenchPreferencePage {

    private FontDialog fontDialog;
//    public static final String[] ALL_TYPES = { 
//        ALIAS_TYPE, TABLE_TYPE, VIEW_TYPE, SEQUENCE_TYPE, 
//        PACKAGE_TYPE, PROCEDURE_TYPE, COLUMN_TYPE };

    private ColorFieldEditor backgroundColorEditor;
    private ColorFieldEditor textColorEditor;
    private ColorFieldEditor keywordColorEditor;
    private ColorFieldEditor stringColorEditor;
    private ColorFieldEditor numericColorEditor;
    private ColorFieldEditor commentColorEditor;
    private ColorFieldEditor tableColorEditor;
    private ColorFieldEditor viewColorEditor;
    private ColorFieldEditor procedureColorEditor;
    private ColorFieldEditor columnColorEditor;
    private ColorFieldEditor variableColorEditor;

    private boolean textFlag;
    private boolean keywordFlag;
    private boolean stringFlag;
    private boolean numericFlag;
    private boolean commentFlag;
    private boolean tableFlag;
    private boolean viewFlag;
    private boolean procedureFlag;
    private boolean columnFlag;
    private boolean variableFlag;
    
    private Button boldText;
    private Button boldKeyword;
    private Button boldString;
    private Button boldNumeric;
    private Button boldComment;
    private Button boldTable;
    private Button boldView;
    private Button boldProcedure;
    private Button boldColumn;
    private Button boldVariable;
	
    private FontData fontData;
	private Label fontDisplay;
	
	public void init(IWorkbench workbench) {
		setPreferenceStore(QuantumPlugin.getDefault().getPreferenceStore());
	}

	protected void performDefaults() {
		fontData = PluginPreferences.getDefaultFont();
		updateFontDisplay();
		this.textFlag = false;
		this.keywordFlag = true;
		this.stringFlag = false;
		this.numericFlag = false;
		this.commentFlag = false;
		this.tableFlag = false;
		this.viewFlag = false;
		this.procedureFlag = false;
		this.columnFlag = false;
		this.variableFlag = false;
		updateFlags();
		backgroundColorEditor.loadDefault();
		textColorEditor.loadDefault();
		keywordColorEditor.loadDefault();
		stringColorEditor.loadDefault();
		commentColorEditor.loadDefault();
		numericColorEditor.loadDefault();
	    tableColorEditor.loadDefault();
	    viewColorEditor.loadDefault();
	    procedureColorEditor.loadDefault();
	    columnColorEditor.loadDefault();
	    variableColorEditor.loadDefault();
}
	/** 
	 * Save the preferences to the preference store.
	 */
	public boolean performOk() {
		PreferenceConverter.setValue(getPreferenceStore(), "quantum.font", fontData); //$NON-NLS-1$
		getPreferenceStore().setValue("quantum.text.bold", textFlag); //$NON-NLS-1$
		getPreferenceStore().setValue("quantum.keyword.bold", keywordFlag); //$NON-NLS-1$
		getPreferenceStore().setValue("quantum.string.bold", stringFlag); //$NON-NLS-1$
		getPreferenceStore().setValue("quantum.comment.bold", commentFlag); //$NON-NLS-1$
		getPreferenceStore().setValue("quantum.numeric.bold", numericFlag); //$NON-NLS-1$
		getPreferenceStore().setValue("quantum.table.bold", tableFlag);
		getPreferenceStore().setValue("quantum.view.bold", viewFlag);
		getPreferenceStore().setValue("quantum.procedure.bold", procedureFlag);
		getPreferenceStore().setValue("quantum.column.bold", columnFlag);
		getPreferenceStore().setValue("quantum.variable.bold", variableFlag);
		backgroundColorEditor.store();
		textColorEditor.store();
		keywordColorEditor.store();
		stringColorEditor.store();
		commentColorEditor.store();
		numericColorEditor.store();
	    tableColorEditor.store();
	    viewColorEditor.store();
	    procedureColorEditor.store();
	    columnColorEditor.store();
	    variableColorEditor.store();
		return super.performOk();
	}
	
	protected Control createContents(Composite parent) {
		Composite main = new Composite(parent, SWT.NULL);
		main.setLayout(new GridLayout());
		
        createFontSelectionArea(main);
        Label label = new Label(main, SWT.NONE);
        label.setText("");
        
		createColorSelectionArea(main);

		return main;
	}
	/**
     * @param main
     * @return
     */
    private void createColorSelectionArea(Composite parent) {
        Composite main = new Composite(parent, SWT.NULL);

		this.textFlag = getPreferenceStore().getBoolean("quantum.text.bold"); //$NON-NLS-1$
		this.keywordFlag = getPreferenceStore().getBoolean("quantum.keyword.bold"); //$NON-NLS-1$
		this.stringFlag = getPreferenceStore().getBoolean("quantum.string.bold"); //$NON-NLS-1$
		this.commentFlag = getPreferenceStore().getBoolean("quantum.comment.bold"); //$NON-NLS-1$
		this.numericFlag = getPreferenceStore().getBoolean("quantum.numeric.bold"); //$NON-NLS-1$
		this.tableFlag = getPreferenceStore().getBoolean("quantum.table.bold"); //$NON-NLS-1$
		this.procedureFlag = getPreferenceStore().getBoolean("quantum.procedure.bold"); //$NON-NLS-1$
		this.columnFlag = getPreferenceStore().getBoolean("quantum.column.bold"); //$NON-NLS-1$
		this.viewFlag = getPreferenceStore().getBoolean("quantum.view.bold"); //$NON-NLS-1$
		this.variableFlag= getPreferenceStore().getBoolean("quantum.variable.bold"); //$NON-NLS-1$

        GridLayout layout = new GridLayout();
		
		// the colour preference chooser really, really wants to take up a whole
		// row, so we need to pretend that our row is only two cells wide, then
		// pull a switcheroo later...
		layout.numColumns = 2;
		main.setLayout(layout);
		
		backgroundColorEditor =
			new ColorFieldEditor(
				PluginPreferences.BACKGROUND_COLOR,
				Messages.getString(getClass(), "backgroundColor"), //$NON-NLS-1$
				main);	

		Label emptyLabel = new Label(main, SWT.NULL);
		emptyLabel.setText("");
		
		createEmptyRow(main);
		
		this.textColorEditor =
			new ColorFieldEditor(
				PluginPreferences.TEXT_COLOR,
				Messages.getString(getClass(), "defaultTextColor"), //$NON-NLS-1$
				main);				

		this.boldText = new Button(main, SWT.CHECK);
		this.boldText.setSelection(textFlag);
		this.boldText.setText(Messages.getString(getClass(), "bold")); //$NON-NLS-1$
		this.boldText.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
			}
			public void widgetSelected(SelectionEvent e) {
				textFlag = boldText.getSelection();
			}
		});

		keywordColorEditor =
			new ColorFieldEditor(
				PluginPreferences.KEYWORD_COLOR,
				Messages.getString(getClass(), "keywordTextColor"), //$NON-NLS-1$
				main);

		boldKeyword = new Button(main, SWT.CHECK);
		boldKeyword.setSelection(keywordFlag);
		boldKeyword.setText(Messages.getString(getClass(), "bold")); //$NON-NLS-1$
		boldKeyword.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
			}
			public void widgetSelected(SelectionEvent e) {
				keywordFlag = boldKeyword.getSelection();
			}
		});

		commentColorEditor =
			new ColorFieldEditor(
				PluginPreferences.COMMENT_COLOR,
				Messages.getString(getClass(), "commentTextColor"), //$NON-NLS-1$
				main);

		boldComment = new Button(main, SWT.CHECK);
		boldComment.setSelection(commentFlag);
		boldComment.setText(Messages.getString(getClass(), "bold")); //$NON-NLS-1$
		boldComment.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
			}
			public void widgetSelected(SelectionEvent e) {
				commentFlag = boldComment.getSelection();
			}
		});

		stringColorEditor =
			new ColorFieldEditor(
				PluginPreferences.STRING_COLOR,
				Messages.getString(getClass(), "stringTextColor"), //$NON-NLS-1$
				main);

		boldString = new Button(main, SWT.CHECK);
		boldString.setSelection(stringFlag);
		boldString.setText(Messages.getString(getClass(), "bold")); //$NON-NLS-1$
		boldString.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
			}
			public void widgetSelected(SelectionEvent e) {
				stringFlag = boldString.getSelection();
			}
		});

		numericColorEditor =
			new ColorFieldEditor(
				PluginPreferences.NUMERIC_COLOR,
				Messages.getString(getClass(), "numericTextColor"), //$NON-NLS-1$
				main);

		boldNumeric = new Button(main, SWT.CHECK);
		boldNumeric.setSelection(numericFlag);
		boldNumeric.setText(Messages.getString(getClass(), "bold")); //$NON-NLS-1$
		boldNumeric.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
			}
			public void widgetSelected(SelectionEvent e) {
				numericFlag = boldNumeric.getSelection();
			}
		});

		tableColorEditor =
			new ColorFieldEditor(
				PluginPreferences.TABLE_COLOR,
				Messages.getString(getClass(), "tableTextColor"), //$NON-NLS-1$
				main);

		boldTable= new Button(main, SWT.CHECK);
		boldTable.setSelection(tableFlag);
		boldTable.setText(Messages.getString(getClass(), "bold")); //$NON-NLS-1$
		boldTable.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
			}
			public void widgetSelected(SelectionEvent e) {
				tableFlag = boldTable.getSelection();
			}
		});
		
		viewColorEditor =
			new ColorFieldEditor(
				PluginPreferences.VIEW_COLOR,
				Messages.getString(getClass(), "viewTextColor"), //$NON-NLS-1$
				main);

		boldView= new Button(main, SWT.CHECK);
		boldView.setSelection(viewFlag);
		boldView.setText(Messages.getString(getClass(), "bold")); //$NON-NLS-1$
		boldView.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
			}
			public void widgetSelected(SelectionEvent e) {
				viewFlag = boldView.getSelection();
			}
		});

		procedureColorEditor =
			new ColorFieldEditor(
				PluginPreferences.PROCEDURE_COLOR,
				Messages.getString(getClass(), "procedureTextColor"), //$NON-NLS-1$
				main);

		boldProcedure= new Button(main, SWT.CHECK);
		boldProcedure.setSelection(procedureFlag);
		boldProcedure.setText(Messages.getString(getClass(), "bold")); //$NON-NLS-1$
		boldProcedure.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
			}
			public void widgetSelected(SelectionEvent e) {
				procedureFlag = boldProcedure.getSelection();
			}
		});

		columnColorEditor =
			new ColorFieldEditor(
				PluginPreferences.COLUMN_COLOR,
				Messages.getString(getClass(), "columnTextColor"), //$NON-NLS-1$
				main);

		boldColumn= new Button(main, SWT.CHECK);
		boldColumn.setSelection(columnFlag);
		boldColumn.setText(Messages.getString(getClass(), "bold")); //$NON-NLS-1$
		boldColumn.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
			}
			public void widgetSelected(SelectionEvent e) {
				columnFlag = boldColumn.getSelection();
			}
		});

		variableColorEditor=
			new ColorFieldEditor(
				PluginPreferences.VARIABLE_COLOR,
				Messages.getString(getClass(), "variableTextColor"), //$NON-NLS-1$
				main);

		boldVariable= new Button(main, SWT.CHECK);
		boldVariable.setSelection(columnFlag);
		boldVariable.setText(Messages.getString(getClass(), "bold")); //$NON-NLS-1$
		boldVariable.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
			}
			public void widgetSelected(SelectionEvent e) {
				variableFlag= boldVariable.getSelection();
			}
		});
		
		//backgroundColorEditor.setPreferencePage(this);
		backgroundColorEditor.setPreferenceStore(getPreferenceStore());
		backgroundColorEditor.load();
		
		//textColorEditor.setPreferencePage(this);
		textColorEditor.setPreferenceStore(getPreferenceStore());
		textColorEditor.load();
		
		//keywordColorEditor.setPreferencePage(this);
		keywordColorEditor.setPreferenceStore(getPreferenceStore());
		keywordColorEditor.load();
		
		//commentColorEditor.setPreferencePage(this);
		commentColorEditor.setPreferenceStore(getPreferenceStore());
		commentColorEditor.load();
		
		//stringColorEditor.setPreferencePage(this);
		stringColorEditor.setPreferenceStore(getPreferenceStore());
		stringColorEditor.load();

		//numericColorEditor.setPreferencePage(this);
		numericColorEditor.setPreferenceStore(getPreferenceStore());
		numericColorEditor.load();
		
		//tableColorEditor.setPreferencePage(this);
		tableColorEditor.setPreferenceStore(getPreferenceStore());
		tableColorEditor.load();
		
		//viewColorEditor.setPreferencePage(this);
		viewColorEditor.setPreferenceStore(getPreferenceStore());
		viewColorEditor.load();
		
		//procedureColorEditor.setPreferencePage(this);
		procedureColorEditor.setPreferenceStore(getPreferenceStore());
		procedureColorEditor.load();

		//columnColorEditor.setPreferencePage(this);
		columnColorEditor.setPreferenceStore(getPreferenceStore());
		columnColorEditor.load();

		//variableColorEditor.setPreferencePage(this);
		variableColorEditor.setPreferenceStore(getPreferenceStore());
		variableColorEditor.load();
		
		// now for the switcheroo...
		// reset the number of columns to 3
		GridLayout innerLayout = new GridLayout();
		innerLayout.numColumns = 3;
		main.setLayout(innerLayout);
    }

    /**
     * @param main
     */
    private void createFontSelectionArea(Composite main) {
        Group group = new Group(main, SWT.NONE);
        group.setText(Messages.getString(getClass(), "font"));
        GridLayout innerLayout = new GridLayout();
		innerLayout.numColumns = 2;
		group.setLayout(innerLayout);
		group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		fontDisplay = new Label(group, SWT.NULL);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 2;
		fontDisplay.setLayoutData(data);
		
		fontData = PreferenceConverter.getFontData(getPreferenceStore(), "quantum.font"); //$NON-NLS-1$
		fontDialog = new FontDialog(getShell());
		Button fontButton = new Button(group, SWT.PUSH);
		fontButton.setText(Messages.getString(getClass(), "pickFont")); //$NON-NLS-1$
		fontButton.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
			}
			public void widgetSelected(SelectionEvent e) {
				if (fontData != null) {
                    VersioningHelper.setFont(fontDialog, new FontData[] { fontData} );
				}
				FontData data = fontDialog.open();
				if (data != null) {
					fontData = data;
					updateFontDisplay();
				}
			}
		});
		Button defaultButton = new Button(group, SWT.PUSH);
		defaultButton.setText(Messages.getString(getClass(), "defaultFont")); //$NON-NLS-1$
		defaultButton.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
			}
			public void widgetSelected(SelectionEvent e) {
				fontData = PluginPreferences.getDefaultFont();
				updateFontDisplay();
			}
		});

		updateFontDisplay();
    }

    /**
     * @param main
     */
    private void createEmptyRow(Composite main) {
        Label emptyLabel = new Label(main, SWT.NULL);
		emptyLabel.setText("");
		GridData gridData = new GridData();
		gridData.horizontalSpan = 4;
		emptyLabel.setLayoutData(gridData);
    }

    protected void updateFontDisplay() {
		if (fontData == null) {
			fontDisplay.setText(Messages.getString(getClass(), "default")); //$NON-NLS-1$
		} else {
			Object[] parameters = new Object[] { 
					fontData.getName(), new Integer(fontData.getHeight()) };
			String style = Messages.getString(getClass(), "regularFont", parameters); //$NON-NLS-1$
			if (fontData.getStyle() == SWT.BOLD) {
				style = Messages.getString(getClass(), "boldFont", parameters); //$NON-NLS-1$
			} else if (fontData.getStyle() == SWT.ITALIC) {
				style = Messages.getString(getClass(), "italicFont", parameters); //$NON-NLS-1$
			} else if (fontData.getStyle() == (SWT.BOLD | SWT.ITALIC)) {
				style = Messages.getString(getClass(), "boldItalicFont", parameters); //$NON-NLS-1$
			}
			fontDisplay.setText(style); //$NON-NLS-1$
		}
	}
	protected void updateFlags() {
		this.boldText.setSelection(this.textFlag);
		this.boldKeyword.setSelection(this.keywordFlag);
		this.boldString.setSelection(this.stringFlag);
		this.boldNumeric.setSelection(this.numericFlag);
		this.boldComment.setSelection(this.commentFlag);
		this.boldColumn.setSelection(this.columnFlag);
		this.boldProcedure.setSelection(this.procedureFlag);
		this.boldTable.setSelection(this.tableFlag);
		this.boldView.setSelection(this.viewFlag);
	}
}
