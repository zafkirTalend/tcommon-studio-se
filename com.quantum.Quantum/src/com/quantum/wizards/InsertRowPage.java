package com.quantum.wizards;

import java.util.ArrayList;
import java.util.List;

import com.quantum.ImageStore;
import com.quantum.Messages;
import com.quantum.adapters.DatabaseAdapter;
import com.quantum.model.Bookmark;
import com.quantum.util.StringMatrix;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

/**
 * @author BC Holmes
 * @author Elvin E. Ebora
 */
public class InsertRowPage extends BaseSQLPage implements SQLPage {
    
    class InsertRowTableValues {
        private String colNames = null;
        private String values 	= null;
        private boolean defaultVal = false;
		private boolean noQuote = false;
        
		/**
		 * @return Returns if the column value is NOT to be quoted.
		 */
		public boolean isNoQuote() {
			return noQuote;
		}
		
		/**
		 * @param noQuote if the column value is NOT to be quoted.
		 */
		public void setNoQuote(boolean noQuote) {
			this.noQuote = noQuote;
		}
		
		public InsertRowTableValues() {            
        }
        /**
         * @return Returns the colNames.
         */
        public String getColNames() {
            return colNames;
        }
        /**
         * @param colNames The colNames to set.
         */
        public void setColNames(String colNames) {
            this.colNames = colNames;
        }
        /**
         * @return Returns the values.
         */
        public String getValues() {
            return values;
        }
        /**
         * @param values The values to set.
         */
        public void setValues(String values) {
            this.values = values;
        }
		/**
		 * @return Returns if the column must be left to its default Value.
		 */
		public boolean isDefaultVal() {
			return defaultVal;
		}
		/**
		 * @param defaultVal Sets if the column must be left to its default Value
		 */
		public void setDefaultVal(boolean defaultVal) {
			this.defaultVal = defaultVal;
		}
    }
    
    class LabelProviderImpl implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
		    if (columnIndex == 1) {
		        return ((InsertRowTableValues)element).isDefaultVal() ? imgCheck : imgUncheck;
		    } else if (columnIndex == 2) {
		        return ((InsertRowTableValues)element).isNoQuote() ? imgCheck : imgUncheck;
		    } else {
		        return null;
		    }
		}
		public String getColumnText(Object element, int columnIndex) {
			String sReturn = "";			
			InsertRowTableValues insertRow = (InsertRowTableValues)element;
			switch (columnIndex) {
				case 0:
				    sReturn = insertRow.getColNames();
				    break;
				case 1: // set checkbox
				    break;
				case 2: // set checkbox
				    break;
				case 3:
				    sReturn = insertRow.getValues();
				    break;
				default:
				    break;
			}
			return sReturn;
		}
		public void addListener(ILabelProviderListener listener) {}
		public void dispose() {}
		public boolean isLabelProperty(Object element, String property) {
			return false;
		}
		public void removeListener(ILabelProviderListener listener) {}
	}
	
    class ContentProviderImpl implements IStructuredContentProvider {
		public Object[] getElements(Object inputElement) {
			return insertTable;
		}

		public void dispose() {}
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {}		
	}
        
    class CellModifierImpl implements ICellModifier {

    	public boolean canModify(Object element, String property) {
    		return true;
    	}
    	
    	public Object getValue(Object element, String property) {		
    		System.out.println("getValue called");
    	
    		// Find the index of the column
    		int colIndx = comUI.getColumnNamesAsList(colNames).indexOf(property);
    		System.out.println("colIndx : " + colIndx);		
    		 
    		Object rResult = null;
    		InsertRowTableValues insertVal = (InsertRowTableValues)element;
    				
    		switch (colIndx) {
	    		case 0:
	    		    rResult = insertVal.getColNames();
				    break;
				case 1: // checkbox
				    rResult = new Boolean(insertVal.isDefaultVal());
				    break;
				case 2: // checkbox
				    rResult = new Boolean(insertVal.isNoQuote());
				    break;
				case 3:
				    rResult = insertVal.getValues();
				    break;
	    		default:
    				rResult = "";
    				break;
    		}
    		
    		return rResult;
    	}
    	
    	public void modify(Object element, String property, Object value) {
    		int colIndx = comUI.getColumnNamesAsList(colNames).indexOf(property);
    		
    		TableItem item = (TableItem) element;
    		InsertRowTableValues insertVal = (InsertRowTableValues)item.getData();
    		
    		switch (colIndx) {
    			case 0: // field names
    			    break;
				case 1: // checkbox
				    insertVal.setDefaultVal(((Boolean)value).booleanValue());
				    break;
				case 2: // checkbox
				    insertVal.setNoQuote(((Boolean)value).booleanValue());
				    break;
    			case 3: // field values
    			    insertVal.setValues(value.toString());
    			    break;
    			default:
    			    break;
    		}
		    updateView();    			    
		    updateQuery();
    	}
    }
    
	String[] columnNames;
	String[] colNames;
	Text query;
	InsertRowTableValues[] insertTable = null;
	CommonWizardUI comUI;
	TableViewer tableViewer = null;	
	int numColumns = 0;
	static Image imgCheck = null;
	static Image imgUncheck = null;
	
	static {
	    imgCheck = ImageStore.getImage(ImageStore.CHECKED);
	    imgUncheck = ImageStore.getImage(ImageStore.UNCHECKED);
	}

	
	public InsertRowPage(String pageName) {
		super(pageName);	
		setTitle(Messages.getString("TableView.InsertRowTitle"));
		setDescription(Messages.getString("TableView.InsertRowDesc"));
	}

	public void createControl(Composite parent) {
		System.out.println("page create control"); //$NON-NLS-1$
		Composite container = new Composite(parent, SWT.NULL);
		container.setLayout(new GridLayout());
		container.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.VERTICAL_ALIGN_BEGINNING));
		
		comUI = new CommonWizardUI();

		// init values to be displayed on the table
		columnNames = this.results.getColumnNames();
		int nLen = columnNames.length;
		insertTable = new InsertRowTableValues[nLen];
		
		for (int nCtr=0; nCtr<nLen; nCtr++) {
		    insertTable[nCtr] = new InsertRowTableValues();
		    insertTable[nCtr].setColNames(columnNames[nCtr]);
		    insertTable[nCtr].setValues("");
		}		
		
		createTable(container);				
		
		//query = new Label(container, SWT.WRAP);
		query = new Text(container, SWT.V_SCROLL | SWT.MULTI | SWT.WRAP | SWT.READ_ONLY);
		query.setLayoutData(comUI.createGridData(1, 10, GridData.FILL));
		query.setVisible(false);

 		setControl(container);
        //updateQuery();
       
		setPageComplete(true);
	}
	public void updateQuery() {
		System.out.println("Updating query"); //$NON-NLS-1$
		query.setVisible(true);
		
		StringMatrix columns = new StringMatrix();
		List noQuote = new ArrayList();
		for (int i = 0; i < columnNames.length; i++) {
			if (!insertTable[i].isDefaultVal()) {
				columns.addHeader( insertTable[i].getColNames() );
		    	columns.add( insertTable[i].getValues() , 0 );
				
				noQuote.add(new Boolean(insertTable[i].isNoQuote()));
			}
		}
		Bookmark bookmark = (Bookmark) this.results.getConnectable();
		DatabaseAdapter adapter = bookmark.getAdapter();
		this.query.setText(adapter.buildInsert(this.results.getEntity(), columns, noQuote));
	}
    /* (non-Javadoc)
     * @see com.quantum.wizards.BaseSQLPage#getQueryText()
     */
    protected String getQueryText() {
        return this.query.getText();
    }
    
    private void createTable(Composite composite) {
        System.out.println("Creating table...");
        Table table = comUI.createTablePage(composite);
    	colNames = new String[] { Messages.getString("InsertRowPage.ColumnName"), 
    							  Messages.getString("InsertRowPage.DefaultValue"), 
    							  Messages.getString("InsertRowPage.NoQuote"),
								  Messages.getString("InsertRowPage.Value")
								  };

    	comUI.createTableColumn(table, colNames[0], SWT.LEFT, 0, 200);
		comUI.createTableColumn(table, colNames[1], SWT.CENTER, 1, 40);
		comUI.createTableColumn(table, colNames[2], SWT.CENTER, 2, 40);
    	comUI.createTableColumn(table, colNames[3], SWT.LEFT, 3, 500);
       	
		this.tableViewer = new TableViewer(table);
		this.tableViewer.setColumnProperties(colNames);

		CellEditor[] editor = new CellEditor[colNames.length];
		TextCellEditor txtEditorField = new TextCellEditor(table, SWT.READ_ONLY);
		editor[0] = txtEditorField;
		
		TextCellEditor txtEditorValues = new TextCellEditor(table);
		
		editor[1] = new CheckboxCellEditor(table, SWT.NULL);				
		editor[2] = new CheckboxCellEditor(table, SWT.NULL);				

		editor[3] = txtEditorValues;

		this.tableViewer.setCellEditors(editor);
		this.tableViewer.setLabelProvider(new LabelProviderImpl());
		this.tableViewer.setContentProvider(new ContentProviderImpl());
		this.tableViewer.setCellModifier(new CellModifierImpl());
		this.tableViewer.setInput(insertTable);    	
    }        
        
    private void updateView() {
        this.tableViewer.update(insertTable, null);
    }
}