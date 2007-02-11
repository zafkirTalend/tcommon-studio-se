package com.quantum.wizards;

import org.eclipse.jface.viewers.CellEditor;
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
import org.eclipse.swt.widgets.Text;

import com.quantum.ImageStore;
import com.quantum.Messages;

/** 
 * @author Elvin E. Ebora
 */
public class ViewRowPage extends BaseSQLPage implements SQLPage {
    
    class ViewRowTableValues {
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
		
		public ViewRowTableValues() {            
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
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			String sReturn = "";			
			ViewRowTableValues viewRow = (ViewRowTableValues)element;
			switch (columnIndex) {
				case 0:
				    sReturn = viewRow.getColNames();
				    break;
				case 1:
					sReturn = viewRow.getValues();
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
			return viewTable;
		}

		public void dispose() {}
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {}		
	}
        
    class CellModifierImpl implements ICellModifier {

    	public boolean canModify(Object element, String property) {
    		return true;
    	}
    	
    	public Object getValue(Object element, String property) {		

    		// Find the index of the column
    		int colIndx = comUI.getColumnNamesAsList(colNames).indexOf(property);		
    		 
    		Object rResult = null;
    		ViewRowTableValues viewVal = (ViewRowTableValues)element;
    				
    		switch (colIndx) {
	    		case 0:
	    		    rResult = viewVal.getColNames();
				    break;
				case 1:
					rResult = viewVal.getValues();
				    break;
	    		default:
    				break;
    		}
    		
    		return rResult;
    	}
    	
    	public void modify(Object element, String property, Object value) {
		    updateQuery();
    	}
    }
        
    
	String[] columnNames;
	String[] colNames;
	Table table;
	Text query;
	ViewRowTableValues[] viewTable = null;
	CommonWizardUI comUI;
	TableViewer tableViewer = null;	
	int numColumns = 0;
	static Image imgCheck = null;
	static Image imgUncheck = null;
	
	static {
	    imgCheck = ImageStore.getImage(ImageStore.CHECKED);
	    imgUncheck = ImageStore.getImage(ImageStore.UNCHECKED);
	}

	
	public ViewRowPage(String pageName) {
		super(pageName);		
		setTitle(Messages.getString("TableView.ViewRowTitle"));
		setDescription(Messages.getString("TableView.ViewRowDesc"));
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
		viewTable = new ViewRowTableValues[nLen];
		
		for (int nCtr=0; nCtr<nLen; nCtr++) {
		    viewTable[nCtr] = new ViewRowTableValues();
		    viewTable[nCtr].setColNames(columnNames[nCtr]);
		    Object data = this.row == null ? null : this.row.get(nCtr+1);
		    viewTable[nCtr].setValues(data == null ? "" : data.toString());
		}		
		
		createTable(container);				
		
		query = new Text(container, SWT.V_SCROLL | SWT.MULTI | SWT.WRAP | SWT.READ_ONLY);
		query.setLayoutData(comUI.createGridData(1, 20, GridData.FILL));
		query.setVisible(true);

 		setControl(container);
       
		setPageComplete(true);
	}
	public void updateQuery() {
		System.out.println("Updating query"); //$NON-NLS-1$
		this.query.setText(viewTable[this.table.getSelectionIndex()].getValues());		
	}
    /* (non-Javadoc)
     * @see com.quantum.wizards.BaseSQLPage#getQueryText()
     */
    protected String getQueryText() {
        return this.query.getText();
    }
    
    private void createTable(Composite composite) {
        System.out.println("Creating table...");
        table = comUI.createTablePage(composite);
    	colNames = new String[] { Messages.getString("InsertRowPage.ColumnName"), 
								  Messages.getString("InsertRowPage.Value")
								  };

    	comUI.createTableColumn(table, colNames[0], SWT.LEFT, 0, 200);
    	comUI.createTableColumn(table, colNames[1], SWT.LEFT, 1, 400);
       	
		this.tableViewer = new TableViewer(table);
		this.tableViewer.setColumnProperties(colNames);

		CellEditor[] editor = new CellEditor[colNames.length];
		TextCellEditor txtEditorField = new TextCellEditor(table, SWT.READ_ONLY);
		editor[0] = txtEditorField;
		
		TextCellEditor txtEditorValues = new TextCellEditor(table, SWT.READ_ONLY);
		editor[1] = txtEditorValues;

		this.tableViewer.setCellEditors(editor);
		this.tableViewer.setLabelProvider(new LabelProviderImpl());
		this.tableViewer.setContentProvider(new ContentProviderImpl());
		this.tableViewer.setCellModifier(new CellModifierImpl());
		this.tableViewer.setInput(viewTable);    	
    }                
}