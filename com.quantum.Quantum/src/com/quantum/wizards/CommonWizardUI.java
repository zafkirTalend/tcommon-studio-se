package com.quantum.wizards;

import java.util.Arrays;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

/**
 * @author Elvin E. Ebora
 */

public class CommonWizardUI {

	/**
	 * constructor
	 */
	public CommonWizardUI() {}
	
	/**
	 * Creates a standard Table UI for wizard implementation
	 * @param composite
	 * @return Table
	 */
	protected Table createTablePage(Composite composite) {
	    int style = SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.HIDE_SELECTION;
		Table table = new Table(composite, style);
    	table.setHeaderVisible(true);
    	table.setLinesVisible(true);
    	table.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.VERTICAL_ALIGN_BEGINNING));
    	return table;
	}
	
	/**
	 * Creates a standard TableColumn UI for wizard implementation
	 * @param table
	 * @param colName
	 * @param style
	 * @param pos
	 * @param width
	 */
	protected void createTableColumn(Table table, String colName, int style, int pos, int width) {
        TableColumn column = new TableColumn(table, style, pos);
		column.setText(colName);		
		column.setWidth(width);
    }
	
	/**
	 * Creates a standard GridData UI for wizard implementation
	 * @param horzSpan
	 * @param alignment
	 * @return GridData
	 */
	protected GridData createGridData(int horzSpan, int vertSpan, int alignment) {
		GridData gridData = new GridData();
 		gridData.horizontalSpan = horzSpan;
 		gridData.verticalSpan = vertSpan;
 		gridData.horizontalAlignment = alignment;
		gridData.verticalAlignment = alignment;
		return gridData;
	}
	
	/**
	 * Returns a List implementation of an array of string input
	 * @param columnNames
	 * @return java.util.List
	 */
	protected java.util.List getColumnNamesAsList(String[] columnNames) {
		return Arrays.asList(columnNames);	
	}
}