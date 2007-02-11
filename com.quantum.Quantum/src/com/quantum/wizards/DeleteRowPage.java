package com.quantum.wizards;

import java.util.ArrayList;
import java.util.List;

import com.quantum.ImageStore;
import com.quantum.Messages;
import com.quantum.adapters.DatabaseAdapter;
import com.quantum.model.Bookmark;
import com.quantum.model.Column;
import com.quantum.model.Entity;
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
public class DeleteRowPage extends BaseSQLPage implements SQLPage {
    
    class DeleteRowPageValues {
        private String sColNames = null;
        private String sValues = null;
        private boolean bPrimary = false;
		private boolean noQuoteKey = false;
		
        
        public DeleteRowPageValues() {            
        }
	      /**
		 * @return Returns if the key value is NOT to be quoted.
		 */
		public boolean isNoQuoteKey() {
			return noQuoteKey;
		}
		/**
		 * @param noQuoteKey if the key value is NOT to be quoted.
		 */
		public void setNoQuoteKey(boolean noQuoteKey) {
			this.noQuoteKey = noQuoteKey;
		}
		/**
         * @return Returns the bPrimary.
         */
        public boolean isBPrimary() {
            return bPrimary;
        }
        /**
         * @param primary The bPrimary to set.
         */
        public void setBPrimary(boolean primary) {
            bPrimary = primary;
        }
        /**
         * @return Returns the sColNames.
         */
        public String getSColNames() {
            return sColNames;
        }
        /**
         * @param colNames The sColNames to set.
         */
        public void setSColNames(String colNames) {
            sColNames = colNames;
        }
        /**
         * @return Returns the sValues.
         */
        public String getSValues() {
            return sValues;
        }
        /**
         * @param values The sValues to set.
         */
        public void setSValues(String values) {
            sValues = values;
        }
    }
    
    class LabelProviderImpl implements ITableLabelProvider {                
		public Image getColumnImage(Object element, int columnIndex) {
		    if (columnIndex == 2) {
		        return ((DeleteRowPageValues)element).isBPrimary() ? imgCheck : imgUncheck;
		    } else if (columnIndex == 3) {
			        return ((DeleteRowPageValues)element).isNoQuoteKey() ? imgCheck : imgUncheck;
		    } else {
		        return null;
		    }
		}
		public String getColumnText(Object element, int columnIndex) {
			String sReturn = "";			
			DeleteRowPageValues deleteRow = (DeleteRowPageValues)element;
			switch (columnIndex) {
				case 0: // column names
				    sReturn = deleteRow.getSColNames();
				    break;
				case 1: // values
				    sReturn = deleteRow.getSValues();
				    break;
				case 2: // set checkbox
				    break;
				case 3: // set no quote key checkbox
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
			return deleteTable;
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
    		DeleteRowPageValues deleteVal = (DeleteRowPageValues)element;
    				
    		switch (colIndx) {
	    		case 0: // column names
	    		    rResult = deleteVal.getSColNames();
				    break;
				case 1: // values
				    rResult = deleteVal.getSValues();
				    break;
				case 2: // checkbox
				    rResult = new Boolean(deleteVal.isBPrimary());
				    break;
				case 3: // checkbox
				    rResult = new Boolean(deleteVal.isNoQuoteKey());
				    break;
				default:
				    break;
    		}
    		
    		return rResult;
    	}
    	
    	public void modify(Object element, String property, Object value) {
    		int colIndx = comUI.getColumnNamesAsList(colNames).indexOf(property);
    		
    		TableItem item = (TableItem) element;
    		DeleteRowPageValues deleteVal = (DeleteRowPageValues)item.getData();
    		
    		switch (colIndx) {
	    		case 0: // column names	    		    
				    break;
				case 1: // values		
				    deleteVal.setSValues(value.toString());
				    break;
				case 2: // checkbox
				    deleteVal.setBPrimary(((Boolean)value).booleanValue());
				    break;
				case 3: // checkbox
				    deleteVal.setNoQuoteKey(((Boolean)value).booleanValue());
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
	DeleteRowPageValues[] deleteTable = null;
	CommonWizardUI comUI;
	TableViewer tableViewer = null;
	static Image imgCheck = null;
	static Image imgUncheck = null;
	
	static {
	    imgCheck = ImageStore.getImage(ImageStore.CHECKED);
	    imgUncheck = ImageStore.getImage(ImageStore.UNCHECKED);
	}

	public DeleteRowPage(String pageName) {
		super(pageName);
		setTitle(Messages.getString("TableView.DeleteRowTitle"));
		setDescription(Messages.getString("TableView.DeleteRowDesc"));
	}
	
	public void createControl(Composite parent) {
		System.out.println("page create control"); //$NON-NLS-1$
		Composite container = new Composite(parent, SWT.NULL);
		container.setLayout(new GridLayout());
		container.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.VERTICAL_ALIGN_BEGINNING));

		Entity entity = this.results.getEntity();
		
		comUI = new CommonWizardUI();
		
		// init values to be displayed on the table
		columnNames = this.results.getColumnNames();
		int nLen = columnNames.length;
		deleteTable = new DeleteRowPageValues[nLen];
		
		for(int nCtr=0; nCtr<nLen; nCtr++) {
		    deleteTable[nCtr] = new DeleteRowPageValues();
		    deleteTable[nCtr].setSColNames(columnNames[nCtr]);
		    Object data = this.row == null ? null : this.row.get(nCtr+1);
		    deleteTable[nCtr].setSValues(data == null ? "" : data.toString());
		    Column column = (entity == null) ? null : getColumn(entity, columnNames[nCtr]);
			if (column != null && column.isPrimaryKey()) {
			    deleteTable[nCtr].setBPrimary(true);
			}		    
		}
		
		createTable(container);
		
		//query = new Label(container, SWT.WRAP);
		query = new Text(container, SWT.V_SCROLL | SWT.MULTI | SWT.WRAP | SWT.READ_ONLY);
		query.setLayoutData(comUI.createGridData(1, 10, GridData.FILL));
		query.setVisible(false);

 		setControl(container);
        updateQuery();
       
		setPageComplete(true);
	}
	public void updateQuery() {
		System.out.println(Messages.getString("DeleteRowPage.UpdatingQuery")); //$NON-NLS-1$
		query.setVisible(true);
		
		StringMatrix key = new StringMatrix();
		List noQuoteKey = new ArrayList();
		for (int i = 0; i < columnNames.length; i++) {
			if (deleteTable[i].isBPrimary()) {
			    key.addHeader( deleteTable[i].getSColNames() );
			    key.add( deleteTable[i].getSValues() , 0 );
				
				noQuoteKey.add(new Boolean(deleteTable[i].isNoQuoteKey()));
			}
		}
		Bookmark bookmark = (Bookmark) this.results.getConnectable();
		DatabaseAdapter adapter = bookmark.getAdapter();
		this.query.setText(adapter.buildDelete(this.results.getEntity(), key, noQuoteKey));
	}
	
    protected String getQueryText() {
        return query.getText();
    }
    
    private void updateView() {
        this.tableViewer.update(deleteTable, null);
    }

    private void createTable(Composite composite) {
        System.out.println("Creating table...");
        Table table = comUI.createTablePage(composite);
    	colNames = new String[] { Messages.getString("DeleteRowPage.ColumnName"),
								  Messages.getString("DeleteRowPage.Value"), 
								  "Where",
								  "No Quote"};

    	comUI.createTableColumn(table, colNames[0], SWT.LEFT, 0, 150);
    	comUI.createTableColumn(table, colNames[1], SWT.LEFT, 1, 300);
		comUI.createTableColumn(table, colNames[2], SWT.CENTER, 2, 60);
		comUI.createTableColumn(table, colNames[3], SWT.CENTER, 3, 60);
		this.tableViewer = new TableViewer(table);
		this.tableViewer.setColumnProperties(colNames);

		CellEditor[] editor = new CellEditor[colNames.length];
		TextCellEditor txtEditorField = new TextCellEditor(table, SWT.READ_ONLY);
		editor[0] = txtEditorField;
		
		TextCellEditor txtEditorFieldValue = new TextCellEditor(table);		
		editor[1] = txtEditorFieldValue;
		
		editor[2] = new CheckboxCellEditor(table, SWT.NULL);				
		
		editor[3] = new CheckboxCellEditor(table, SWT.NULL);				
		
		this.tableViewer.setCellEditors(editor);
		this.tableViewer.setLabelProvider(new LabelProviderImpl());
		this.tableViewer.setContentProvider(new ContentProviderImpl());
		this.tableViewer.setCellModifier(new CellModifierImpl());
		this.tableViewer.setInput(deleteTable);    	
    }
}