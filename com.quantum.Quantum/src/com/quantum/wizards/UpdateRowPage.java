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
public class UpdateRowPage extends BaseSQLPage implements SQLPage {
    
    class UpdateRowTableValues {
        private String sColNames = null;
        private String sOldValue = null;
        private boolean bPrimary = false;
        private String sNewValue = null;
        private boolean bSetValue = false;
		private boolean noQuoteKey = false;
		private boolean noQuote = false;
	       
        
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
		
		public UpdateRowTableValues() {
        }
        /**
         * @return Returns the bSetValue.
         */
        public boolean isBSetValue() {
            return bSetValue;
        }
        /**
         * @param setValue The bSetValue to set.
         */
        public void setBSetValue(boolean setValue) {
            bSetValue = setValue;
        }
        /**
         * @return Returns the bPrimary.
         */
        public boolean isBPrimary() {
            return bPrimary;
        }
        /**
         * @param where The bPrimary to set.
         */
        public void setBPrimary(boolean where) {
            bPrimary = where;
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
         * @return Returns the sNewValue.
         */
        public String getSNewValue() {
            return sNewValue;
        }
        /**
         * @param newValue The sNewValue to set.
         */
        public void setSNewValue(String newValue) {
            sNewValue = newValue;
        }
        /**
         * @return Returns the sOldValue.
         */
        public String getSOldValue() {
            return sOldValue;
        }
        /**
         * @param oldValue The sOldValue to set.
         */
        public void setSOldValue(String oldValue) {
            sOldValue = oldValue;
        }
    }
    
    class LabelProviderImpl implements ITableLabelProvider {                
		public Image getColumnImage(Object element, int columnIndex) {
		    if (columnIndex == 2) {
		        return ((UpdateRowTableValues)element).isBPrimary() ? imgCheck : imgUncheck;
		    } else if (columnIndex == 4) {
		        return ((UpdateRowTableValues)element).isBSetValue() ? imgCheck : imgUncheck;
			} else if (columnIndex == 5) {
		        return ((UpdateRowTableValues)element).isNoQuote() ? imgCheck : imgUncheck;
			} else if (columnIndex == 6) {
		        return ((UpdateRowTableValues)element).isNoQuoteKey() ? imgCheck : imgUncheck;
		    } else {
		        return null;
		    }
		}
		public String getColumnText(Object element, int columnIndex) {
			String sReturn = "";			
			UpdateRowTableValues updateRow = (UpdateRowTableValues)element;
			switch (columnIndex) {
				case 0: // column names
				    sReturn = updateRow.getSColNames();
				    break;
				case 1: // old values
				    sReturn = updateRow.getSOldValue();
				    break;
				case 2: // set checkbox
				    break;
				case 3: // new value
				    sReturn = updateRow.getSNewValue();
				    break;
				case 4: // set value checkbox
				    break;				    
				case 5: // no quote checkbox
				    break;				    
				case 6: // no quote key checkbox
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
			return updateTable;
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
    		UpdateRowTableValues updateVal = (UpdateRowTableValues)element;
    				
    		switch (colIndx) {
	    		case 0: // column names
	    		    rResult = updateVal.getSColNames();
				    break;
				case 1: // old values
				    rResult = updateVal.getSOldValue();
				    break;
				case 2: // where checkbox
				    rResult = new Boolean(updateVal.isBPrimary());
				    break;
				case 3: // new value
				    rResult = updateVal.getSNewValue();
				    break;
				case 4: // set value checkbox
				    rResult = new Boolean(updateVal.isBSetValue());
				    break;				    
				case 5: // no quote checkbox
				    rResult = new Boolean(updateVal.isNoQuote());
				    break;				    
				case 6: // no quote key checkbox
				    rResult = new Boolean(updateVal.isNoQuoteKey());
				    break;				    
				default:
				    break;
    		}
    		
    		return rResult;
    	}
    	
    	public void modify(Object element, String property, Object value) {
    		int colIndx = comUI.getColumnNamesAsList(colNames).indexOf(property);
    		
    		TableItem item = (TableItem) element;
    		UpdateRowTableValues updateVal = (UpdateRowTableValues)item.getData();
    		
    		switch (colIndx) {
	    		case 0: // column names	    		    
				    break;
				case 1: // old values						    
				    break;
				case 2: // where checkbox
				    updateVal.setBPrimary(((Boolean)value).booleanValue());
				    break;
				case 3: // new value
					if (!updateVal.getSNewValue().equals(value.toString())) {
						updateVal.setSNewValue(value.toString());
						updateVal.setBSetValue(true);
						updateVal.setBPrimary(true);
					}									    				    				   
				    break;
				case 4: // set value checkbox
				    updateVal.setBSetValue(((Boolean)value).booleanValue());
				    break;				    
				case 5: // no quote checkbox
				    updateVal.setNoQuote(((Boolean)value).booleanValue());
				    break;				    
				case 6: // no quote key checkbox
				    updateVal.setNoQuoteKey(((Boolean)value).booleanValue());
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
	UpdateRowTableValues[] updateTable = null;
	CommonWizardUI comUI;
	TableViewer tableViewer = null;
	static Image imgCheck = null;
	static Image imgUncheck = null;
	
	static {
	    imgCheck = ImageStore.getImage(ImageStore.CHECKED);
	    imgUncheck = ImageStore.getImage(ImageStore.UNCHECKED);
	}
	
	public UpdateRowPage(String pageName) {
		super(pageName);
		setTitle(Messages.getString("TableView.UpdateRowTitle"));
		setDescription(Messages.getString("TableView.UpdateRowDesc"));
	}

	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		container.setLayout(new GridLayout());
		container.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.VERTICAL_ALIGN_BEGINNING));
		
		Entity entity = this.results.getEntity();
		
		comUI = new CommonWizardUI();
		
		// init values to be displayed on the table
		columnNames = this.results.getColumnNames();
		int nLen = columnNames.length;
		updateTable = new UpdateRowTableValues[nLen];
		
		for(int nCtr=0; nCtr<nLen; nCtr++) {
		    updateTable[nCtr] = new UpdateRowTableValues();
		    updateTable[nCtr].setSColNames(columnNames[nCtr]);
		    Object data = this.row == null ? null : this.row.get(nCtr+1);
		    updateTable[nCtr].setSOldValue(data == null ? "" : data.toString());
		    Column column = (entity == null) ? null : getColumn(entity, columnNames[nCtr]);
			if (column != null && column.isPrimaryKey()) {
			    updateTable[nCtr].setBPrimary(true);
			}
		    updateTable[nCtr].setSNewValue(data == null ? "" : data.toString());
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
		query.setVisible(true);
		
		StringMatrix columns = new StringMatrix();
		List noQuote = new ArrayList();
		List noQuoteKey = new ArrayList();
		for (int i = 0; i < columnNames.length; i++) {
			if (updateTable[i].isBSetValue()) {
				columns.addHeader( updateTable[i].getSColNames() );
				columns.add( updateTable[i].getSNewValue(), 0 );
				
				noQuote.add(new Boolean(updateTable[i].isNoQuote()));
			}
		}
		StringMatrix key = new StringMatrix();
		for (int i = 0; i < columnNames.length; i++) {
			if (updateTable[i].isBPrimary()) {
			    key.addHeader( updateTable[i].getSColNames() );
			    // It's an old value because it't the key.
			    key.add( updateTable[i].getSOldValue() , 0 );
				
				noQuoteKey.add(new Boolean(updateTable[i].isNoQuoteKey()));
			}
		}
		Bookmark bookmark = (Bookmark) this.results.getConnectable();
		DatabaseAdapter adapter = bookmark.getAdapter();
		this.query.setText(adapter.buildUpdate(this.results.getEntity(), columns, key, noQuote, noQuoteKey));
	}

    /* (non-Javadoc)
     * @see com.quantum.wizards.BaseSQLPage#getQueryText()
     */
    protected String getQueryText() {
        return query.getText();
    }
        
    private void updateView() {
        this.tableViewer.update(updateTable, null);
    }

    private void createTable(Composite composite) {
        Table table = comUI.createTablePage(composite);
    	colNames = new String[] { Messages.getString("UpdateRowPage.ColumnName"),
								  Messages.getString("UpdateRowPage.OldValue"),
								  "Where", 
								  Messages.getString("UpdateRowPage.NewValue"),
								  Messages.getString("UpdateRowPage.SetValue"),	
								  "No Quote",
								  "Key No Quote"};

    	comUI.createTableColumn(table, colNames[0], SWT.LEFT, 0, 150);
    	comUI.createTableColumn(table, colNames[1], SWT.LEFT, 1, 200);
    	comUI.createTableColumn(table, colNames[2], SWT.CENTER, 2, 60);
    	comUI.createTableColumn(table, colNames[3], SWT.LEFT, 3, 200);
		comUI.createTableColumn(table, colNames[4], SWT.CENTER, 4, 70);
		comUI.createTableColumn(table, colNames[5], SWT.CENTER, 5, 50);
		comUI.createTableColumn(table, colNames[6], SWT.CENTER, 6, 50);
		this.tableViewer = new TableViewer(table);
		this.tableViewer.setColumnProperties(colNames);

		CellEditor[] editor = new CellEditor[colNames.length];
		TextCellEditor txtEditorField = new TextCellEditor(table, SWT.READ_ONLY);
		editor[0] = txtEditorField;
		
		TextCellEditor txtEditorFieldOld = new TextCellEditor(table, SWT.READ_ONLY);
		editor[1] = txtEditorFieldOld;
		
		editor[2] = new CheckboxCellEditor(table, SWT.NULL);
		
		TextCellEditor txtEditorValues = new TextCellEditor(table);
		editor[3] = txtEditorValues;
		
		editor[4] = new CheckboxCellEditor(table, SWT.NULL);
		editor[5] = new CheckboxCellEditor(table, SWT.NULL);
		editor[6] = new CheckboxCellEditor(table, SWT.NULL);
		
		this.tableViewer.setCellEditors(editor);
		this.tableViewer.setLabelProvider(new LabelProviderImpl());
		this.tableViewer.setContentProvider(new ContentProviderImpl());
		this.tableViewer.setCellModifier(new CellModifierImpl());
		this.tableViewer.setInput(updateTable);    	
    }        
}