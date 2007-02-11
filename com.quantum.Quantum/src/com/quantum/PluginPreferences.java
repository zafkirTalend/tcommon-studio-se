package com.quantum;

import com.quantum.log.QuantumLog;
import com.quantum.view.tableview.TableView;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;


/**
 * @author BC Holmes
 */
public class PluginPreferences {
	
	public static final String NUMERIC_COLOR = "quantum.numeric.color";
	public static final String STRING_COLOR = "quantum.string.color";
	public static final String COMMENT_COLOR = "quantum.comment.color";
	public static final String KEYWORD_COLOR = "quantum.keyword.color";
	public static final String TEXT_COLOR = "quantum.text.color";
	public static final String BACKGROUND_COLOR = "quantum.background.color";
	public static final String TABLE_COLOR= "quantum.table.color";
	public static final String COLUMN_COLOR= "quantum.column.color";
	public static final String PROCEDURE_COLOR= "quantum.procedure.color";
	public static final String VIEW_COLOR= "quantum.view.color";
	public static final String VARIABLE_COLOR= "quantum.variable.color";
	
	public static final String EXPORT_CONFIRM_OVERWRITE = "com.quantum.export.confirmOverwrite";
	public static final String GLOBAL_USE_JOBS = "com.quantum.global.useJobs";
	
	/**
	 * Initializes the preference store default values to the defaults set by quantum
	 * @param store The preference store to initialize
	 */
	public static void initialize(IPreferenceStore store) {
		initializeColors(store);
		
		store.setDefault("quantum.text.bold", false); //$NON-NLS-1$
		store.setDefault("quantum.keyword.bold", true); //$NON-NLS-1$
		store.setDefault("quantum.string.bold", false); //$NON-NLS-1$
		store.setDefault("quantum.comment.bold", false); //$NON-NLS-1$
		store.setDefault("quantum.numeric.bold", false); //$NON-NLS-1$
		store.setDefault("quantum.table.bold", false); //$NON-NLS-1$
		store.setDefault("quantum.view.bold", false); //$NON-NLS-1$
		store.setDefault("quantum.column.bold", false); //$NON-NLS-1$
		store.setDefault("quantum.procedure.bold", false); //$NON-NLS-1$
		store.setDefault("quantum.variable.bold", false); //$NON-NLS-1$
//		store.setDefault("quantum.table.italic", false); //$NON-NLS-1$
//		store.setDefault("quantum.view.italic", false); //$NON-NLS-1$
		PreferenceConverter.setDefault(store, "quantum.font", //$NON-NLS-1$
				JFaceResources.getTextFont().getFontData()); 
		store.setDefault("com.quantum.model.Bookmark.queryHistorySize", 20); //$NON-NLS-1$
		store.setDefault(QuantumLog.NUMBER_OF_ENTRIES_PREFERENCE_NAME, 300);
		store.setDefault(TableView.NUMBER_OF_ROWS_PREFERENCE_NAME, 200);
		store.setDefault(TableView.MAXIMUM_CHARS_CELL_PREFERENCE_NAME, 2048);
		store.setDefault(EXPORT_CONFIRM_OVERWRITE, true);
		store.setDefault(GLOBAL_USE_JOBS, false);
		}

	/**
	 * @param store
	 */
	private static void initializeColors(IPreferenceStore store) {
		PreferenceConverter.setDefault(store, BACKGROUND_COLOR, new RGB(255, 255, 255));
		PreferenceConverter.setDefault(store, TEXT_COLOR, new RGB(0, 0, 0));
		PreferenceConverter.setDefault(store, KEYWORD_COLOR, new RGB(126, 0, 75));
		PreferenceConverter.setDefault(store, COMMENT_COLOR, new RGB(88, 148, 64));
		PreferenceConverter.setDefault(store, STRING_COLOR, new RGB(0, 0, 255));
		PreferenceConverter.setDefault(store, NUMERIC_COLOR, new RGB(255, 0, 0));
		PreferenceConverter.setDefault(store, TABLE_COLOR, new RGB(255, 0, 0));
		PreferenceConverter.setDefault(store, VIEW_COLOR, new RGB(255, 0, 0));
		PreferenceConverter.setDefault(store, COLUMN_COLOR, new RGB(255, 0, 0));
		PreferenceConverter.setDefault(store, PROCEDURE_COLOR, new RGB(255, 0, 0));
		PreferenceConverter.setDefault(store, VARIABLE_COLOR, new RGB(255, 0, 0));
	}

	/**
	 * 
	 */
	public static FontData getDefaultFont() {
	    FontData[] temp =  JFaceResources.getTextFont().getFontData();
	    return temp == null || temp.length == 0 ? null : temp[0];
	}

}
