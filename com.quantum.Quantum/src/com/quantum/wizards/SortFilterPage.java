package com.quantum.wizards;

import java.io.StringReader;
import java.sql.SQLException;

import antlr.Token;
import antlr.TokenStreamException;

import com.quantum.ImageStore;
import com.quantum.Messages;
import com.quantum.model.Column;
import com.quantum.model.Entity;
import com.quantum.sql.FilterSort;
import com.quantum.sql.SQLResultSetResults;
import com.quantum.sql.grammar.Sql92SelectLexer;
import com.quantum.ui.dialog.ConnectionUtil;
import com.quantum.ui.dialog.SQLExceptionDialog;
import com.quantum.util.connection.NotConnectedException;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class SortFilterPage extends WizardPage implements SQLPage {
	
	class SortFilterTableValues {
		private String colNames = null;
		private boolean isWhere = false;
		private String operators = "";
		private String values = null;
		private boolean isString = false;
		private boolean isOrderBy = false;
		private String sorter = "";
		
		/**
		 * @return Returns the isOrderBy.
		 */
		public boolean isOrderBy() {
			return isOrderBy;
		}
		/**
		 * @param isOrderBy The isOrderBy to set.
		 */
		public void setOrderBy(boolean isOrderBy) {
			this.isOrderBy = isOrderBy;
		}
		/**
		 * @return Returns the isString.
		 */
		public boolean isString() {
			return isString;
		}
		/**
		 * @param isString The isString to set.
		 */
		public void setString(boolean isString) {
			this.isString = isString;
		}
		/**
		 * @return Returns the isWhere.
		 */
		public boolean isWhere() {
			return isWhere;
		}
		/**
		 * @param isWhere The isWhere to set.
		 */
		public void setWhere(boolean isWhere) {
			this.isWhere = isWhere;
		}
		/**
		 * @return Returns the operators.
		 */
		public String getOperators() {
			return operators;
		}
		/**
		 * @param operators The operators to set.
		 */
		public void setOperators(String operators) {
			this.operators = operators;
		}
		/**
		 * @return Returns the sorter.
		 */
		public String getSorter() {
			return sorter;
		}
		/**
		 * @param sorter The sorter to set.
		 */
		public void setSorter(String sorter) {
			this.sorter = sorter;
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
	}

	class LabelProviderImpl implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			if (columnIndex == 0) {
		        return ((SortFilterTableValues)element).isWhere() ? imgCheck : imgUncheck;
		    } else if (columnIndex == 3) {
		        return ((SortFilterTableValues)element).isString() ? imgCheck : imgUncheck;
		    } else if (columnIndex == 4) {
			    return ((SortFilterTableValues)element).isOrderBy() ? imgCheck : imgUncheck;
		    } else {
		        return null;
		    }
		}
		public String getColumnText(Object element, int columnIndex) {
			String sReturn = "";			
			SortFilterTableValues sortFilterRow = (SortFilterTableValues)element;
						
			switch (columnIndex) {
				case 0: // isWhere
					sReturn = sortFilterRow.getColNames();
				    break;
				case 1: // operators
				    sReturn = sortFilterRow.getOperators();
				    break;
				case 2: // values
					sReturn = sortFilterRow.getValues();
					break;
				case 3: // isString
					break;
				case 4: // isOrderBy
					sReturn = sortFilterRow.getColNames();
					break;
				case 5: // sorter
					sReturn = sortFilterRow.getSorter();
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
			return sortFilterValues;
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
    		SortFilterTableValues sortFilterVal = (SortFilterTableValues)element;
    				
    		switch (colIndx) {
	    		case 0: // isWhere
	    		    rResult = new Boolean(sortFilterVal.isWhere());
				    break;
				case 1: // operators
					String sTemp = sortFilterVal.getOperators();
					int nIndex = comUI.getColumnNamesAsList(oper).indexOf(sTemp);					
				    rResult = new Integer(nIndex);
				    break;
				case 2: // values
					rResult = sortFilterVal.getValues();
					break;
				case 3: // isString
					rResult = new Boolean(sortFilterVal.isString());
					break;
				case 4: // isOrderBy
					rResult = new Boolean(sortFilterVal.isOrderBy());
					break;
				case 5: // sorter
					String sTemp2 = sortFilterVal.getSorter();
					int nIndx = comUI.getColumnNamesAsList(order).indexOf(sTemp2);					
				    rResult = new Integer(nIndx);
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
    		SortFilterTableValues sortFilterVal = (SortFilterTableValues)item.getData();
    		
    		switch (colIndx) {
    			case 0: // isWhere
    				sortFilterVal.setWhere(((Boolean)value).booleanValue());
    			    break;
    			case 1: // operators    				
    				int nIdx = ((Integer)value).intValue();
    				if (nIdx != -1)
    					sortFilterVal.setOperators(oper[nIdx]);
    				else
    					sortFilterVal.setOperators("");
    				
    			    break;
    			case 2: // values
    				sortFilterVal.setValues(value.toString());
    				break;
    			case 3: // isString
    				sortFilterVal.setString(((Boolean)value).booleanValue());
    				break;
    			case 4: // isOrderBy
    				sortFilterVal.setOrderBy(((Boolean)value).booleanValue());
    				break;
				case 5: // sorter
					int nId = ((Integer)value).intValue();
					if (nId != -1)
						sortFilterVal.setSorter(order[nId]);
					else 
						sortFilterVal.setSorter("");
					
					break;
    			default:
    			    break;
    		}
    		
    		updateView();    			    
		    updateQuery();
    	}
    }
	
	private SQLResultSetResults.Row row;
	private ConnectionUtil connectionUtil = new ConnectionUtil();
	CommonWizardUI comUI;
	
	SortFilterTableValues[] sortFilterValues;
	TableViewer tableViewer = null;
	static Image imgCheck = null;
	static Image imgUncheck = null;
	String columnNames[];
	Text query;
    private String queryText;
    private String table = null;
    
	FilterSort filterSort = new FilterSort();
	private SQLResultSetResults results;
	
	static {
	    imgCheck = ImageStore.getImage(ImageStore.CHECKED);
	    imgUncheck = ImageStore.getImage(ImageStore.UNCHECKED);
	}
	
	// constant declarations
	String[] colNames = new String[] { "Where", "Operator", "Values", "Is String", "Order By", "Sorter" };
	String[] oper = new String[] { "=", "<>", "<", ">" , "IS", "ISNOT", "LIKE"};
	String[] order = new String[] { "", "ASC", "DESC" };
	
	public SortFilterPage(String pageName) {
		super(pageName);
		setTitle(Messages.getString("com.quantum.view.tableview.TableViewActionGroup.filterSortTitle"));
		setDescription(Messages.getString("com.quantum.view.tableview.TableViewActionGroup.filterSortDesc"));
	}

	public void init(SQLResultSetResults results, SQLResultSetResults.Row row) {
		this.results = results;
		this.row = row;		
	}

	public void createControl(Composite parent) {
		System.out.println("page create control"); //$NON-NLS-1$
				
		Composite container = new Composite(parent, SWT.NULL);
		container.setLayout(new GridLayout());
		container.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.VERTICAL_ALIGN_BEGINNING));
		
		comUI = new CommonWizardUI();
		
		columnNames = this.results.getColumnNames();		
		int nLen = columnNames.length;
		sortFilterValues = new SortFilterTableValues[nLen];
		
		for (int nCtr = 0; nCtr < nLen; nCtr++) {
			sortFilterValues[nCtr] = new SortFilterTableValues();	
			sortFilterValues[nCtr].setColNames(columnNames[nCtr]);
			
			Object data = this.row == null ? null : this.row.get(nCtr+1);
			sortFilterValues[nCtr].setValues(data == null ? "" : data.toString());
		}
		
		createTable(container);
		
		//query = new Label(container, SWT.WRAP);
		query = new Text(container, SWT.V_SCROLL | SWT.MULTI | SWT.WRAP );
		query.setLayoutData(comUI.createGridData(1, 10, GridData.FILL));
		query.setVisible(true);

 		setControl(container);
        
        if(queryText!=null && queryText!="")
        {
            UpdateUI();
            updateView();
        }
		setPageComplete(true);
	}

    private void UpdateUI()
    {
        query.setText(queryText);
        // we should probably parse this...
        // baby steps... First lexx
        // we should qualify by alias or table, but not when they are already there.
        Sql92SelectLexer lexer = new Sql92SelectLexer(new StringReader(queryText));
        lexer.setTokenObjectClass("antlr.CommonToken");// to get the location of the token
        Token t;
        boolean lookingForRHS = false;
        SortFilterTableValues element = null;
        int colIdx = -1;
        try {
            t = lexer.nextToken();
            while(t.getType()!=Sql92SelectLexer.EOF)
            {
                switch(t.getType())
                {
                case Sql92SelectLexer.WHERE:
                    break;
                case Sql92SelectLexer.NonQuotedIdentifier:
                case Sql92SelectLexer.QuotedIdentifier:
                    boolean bFound = false;
                    for(int i=0; i < columnNames.length; i++){
                        if(columnNames[i].equalsIgnoreCase(t.getText())){
                            colIdx = i;
                            bFound = true;
                            break;
                        }
                    }
                    if(bFound)
                    {
                        if (lookingForRHS) {
                            sortFilterValues[colIdx].setValues(element.getValues());
                            sortFilterValues[colIdx].setOperators(element.getOperators());
                            lookingForRHS = false;
                            element = null;
                        }
                        sortFilterValues[colIdx].setWhere(true);
                    }else{
                        colIdx = -1;
                        // this could be an alias or table name...
                    }
                    break;
                case Sql92SelectLexer.Integer:
                case Sql92SelectLexer.Real:
                case Sql92SelectLexer.Number:
                    if (lookingForRHS) {
                        lookingForRHS = false;
                        if(colIdx != -1)
                        {
                            sortFilterValues[colIdx].setValues(t.getText());
                            sortFilterValues[colIdx].setString(false);                        }
                    } else {
                        element = new SortFilterTableValues();
                        element.setValues(t.getText());
                        element.setString(false);
                        colIdx = -1;
                    }
                    break;
                case Sql92SelectLexer.ASCIIStringLiteral:
                    String searchText = t.getText();
                    searchText = searchText.replaceAll("'", ""); // TODO: Is this specific for 1.4.2?
                    if (lookingForRHS) {
                        lookingForRHS = false;
                        if(colIdx != -1)
                        {
                            sortFilterValues[colIdx].setValues(searchText);
                            sortFilterValues[colIdx].setString(true);
                        }
                    } else {
                        element = new SortFilterTableValues();
                        element.setValues(searchText);
                        element.setString(true);
                        colIdx = -1;
                    }
                    break;
                case Sql92SelectLexer.ORDER:
                case Sql92SelectLexer.BY:
                case Sql92SelectLexer.ASC:
                case Sql92SelectLexer.DESC:
                    break;
                case Sql92SelectLexer.AND:
                case Sql92SelectLexer.OR:
                    // restart looking for operands...
                    break;
                case Sql92SelectLexer.IS:
                case Sql92SelectLexer.ASSIGNEQUAL:
                case Sql92SelectLexer.NOTEQUAL1:
                case Sql92SelectLexer.NOTEQUAL2:
                case Sql92SelectLexer.LIKE:
                case Sql92SelectLexer.GREATERTHAN:
                case Sql92SelectLexer.GREATERTHANOREQUALTO1:
                case Sql92SelectLexer.GREATERTHANOREQUALTO2:
                case Sql92SelectLexer.LESSTHAN:
                case Sql92SelectLexer.LESSTHANOREQUALTO1:
                case Sql92SelectLexer.LESSTHANOREQUALTO2:
                    lookingForRHS = true;
                    if(element == null){
                        if(colIdx != -1)
                        {
                            sortFilterValues[colIdx].setOperators(t.getText());
                        }
                    }else{
                        element.setOperators(t.getText());
                    }
                    break;
                case Sql92SelectLexer.DOT:
                    break;
                }
                t = lexer.nextToken();
            }
        } catch (TokenStreamException e) {
            // TODO Refactor...?? because all the model stuff looks the same...
        }
    }
	public void updateQuery() {
		query.setVisible(true);
		filterSort.clearFilters();
		
		int nLen = columnNames.length;
		for (int nCtr = 0; nCtr < nLen; nCtr++) {
			if (sortFilterValues[nCtr].isWhere()) {
				filterSort.addFilter(((table==null)?"":table) + columnNames[nCtr], sortFilterValues[nCtr].getOperators(), sortFilterValues[nCtr].getValues(), sortFilterValues[nCtr].isString());
			}
			if (sortFilterValues[nCtr].isOrderBy()) {
				filterSort.addSort(((table==null)?"":table) + columnNames[nCtr], sortFilterValues[nCtr].getSorter());
			} else {
				filterSort.removeSort(((table==null)?"":table) + columnNames[nCtr]);
			}
		}
		
		query.setText(filterSort.toString());
	}

	public boolean performFinish() {
        
        queryText = query.getText();
        
		this.results.setFilterSort(filterSort);
		try {
			this.results.refresh(this.connectionUtil.connect(
					this.results.getConnectable(), getShell()));
			return true;
		} catch (SQLException e) {
			SQLExceptionDialog.openException(getShell(), this.results.getConnectable(), e);
			return false;
		}
	}
	
	private void updateView() {
        this.tableViewer.update(sortFilterValues, null);
    }
	
	/**
	 * @param entity
	 * @param columnName
	 * @return
	 * @throws NotConnectedException
	 * @throws SQLException
	 */
	protected Column getColumn(Entity entity, String columnName)  {
		try {
			return entity == null ? null : entity.getColumn(columnName);
		} catch (NotConnectedException e) {
			return null;
		} catch (SQLException e) {
			return null;
		}
	}
	
	private void createTable(Composite composite) {
        System.out.println("Creating table...");        
        Table table = comUI.createTablePage(composite);    	

        comUI.createTableColumn(table, colNames[0], SWT.LEFT, 0, 150); // isWhere
        comUI.createTableColumn(table, colNames[1], SWT.CENTER, 1, 70); // operator
        comUI.createTableColumn(table, colNames[2], SWT.LEFT, 2, 150); // values
        comUI.createTableColumn(table, colNames[3], SWT.LEFT, 3, 70); // isString
        comUI.createTableColumn(table, colNames[4], SWT.LEFT, 4, 150); // isOrderBy
        comUI.createTableColumn(table, colNames[5], SWT.CENTER, 5, 70); // sorter
		this.tableViewer = new TableViewer(table);
		this.tableViewer.setColumnProperties(colNames);

		CellEditor[] editor = new CellEditor[colNames.length];
		
		editor[0] = new CheckboxCellEditor(table, SWT.NULL);
				
		editor[1] = new ComboBoxCellEditor(table, oper, SWT.READ_ONLY);
				
		editor[2] = new TextCellEditor(table);				
		
		editor[3] = new CheckboxCellEditor(table, SWT.NULL);
		
		editor[4] = new CheckboxCellEditor(table, SWT.NULL);
				
		editor[5] = new ComboBoxCellEditor(table, order, SWT.READ_ONLY);
				
		this.tableViewer.setCellEditors(editor);
		this.tableViewer.setLabelProvider(new LabelProviderImpl());
		this.tableViewer.setContentProvider(new ContentProviderImpl());
		this.tableViewer.setCellModifier(new CellModifierImpl());
		this.tableViewer.setInput(sortFilterValues);    	
    }
    
    public String getQuery()
    {
        return queryText;
    }
    
    public void setQuery(String query)
    {
        queryText = query;
    }
    
    public void setTable(String table)
    {
        // this can be an alias too
        this.table = table + ".";
    }
}
