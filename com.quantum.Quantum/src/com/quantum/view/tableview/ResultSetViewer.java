package com.quantum.view.tableview;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.quantum.ImageStore;
import com.quantum.Messages;
import com.quantum.sql.SQLResultSetResults;
import com.quantum.sql.Scrollable;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

/**
 * @author BC
 */
public class ResultSetViewer implements PropertyChangeListener {
	
	class LabelProviderImpl implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			Object value = null;
			if (element instanceof SQLResultSetResults.Row) {
				value = ((SQLResultSetResults.Row) element).get(columnIndex+1);
			}
			return value == null ? "" : value.toString();
		}
		public void addListener(ILabelProviderListener listener) {
		}
		public void dispose() {
		}
		public boolean isLabelProperty(Object element, String property) {
			return false;
		}
		public void removeListener(ILabelProviderListener listener) {
		}
	}
	
	class ContentProviderImpl implements IStructuredContentProvider {
		public Object[] getElements(Object inputElement) {
			if (inputElement instanceof SQLResultSetResults) {
				return ((SQLResultSetResults) inputElement).getRows();
			} else {
				return null;
			}
		}

		public void dispose() {
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
		
	}
	
	private final SQLResultSetResults resultSet;
	private TableViewer tableViewer;
	private TabItem tabItem;
	private final TableView tableView;
	
	public ResultSetViewer(TableView tableView, SQLResultSetResults resultSet) {
		this.tableView = tableView;
		this.resultSet = resultSet;
		
		createControl();
		
		this.resultSet.addPropertyChangeListener(this);
	}
	
	public TabItem getTabItem() {
		return this.tabItem;
	}
	
	public Table getTable() {
		return this.tableViewer.getTable();
	}
		
	protected void createControl() {
    	this.tabItem = new TabItem(this.tableView.tabs, SWT.NONE);
    	
    	int index = this.tableView.tabs.getItems().length;    	
    	Composite composite = new Composite(this.tableView.tabs, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginWidth = 5;
		layout.marginHeight = 5;
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		if (this.resultSet.getEntity() == null) {
			this.tabItem.setImage(ImageStore.getImage(ImageStore.SCRIPT));
			this.tabItem.setText(this.resultSet.getConnectable().getDisplayName());
			this.tabItem.setToolTipText(this.resultSet.getQuery());
		} else if (this.resultSet.isMetaData()) {
			this.tabItem.setImage(ImageStore.getImage(ImageStore.TABLE_DETAILS));
			this.tabItem.setText(this.resultSet.getConnectable().getDisplayName() + ":" + 
					this.resultSet.getEntity().getQualifiedName());
			tabItem.setToolTipText(this.resultSet.getEntity().getQualifiedName());
		} else {
			this.tabItem.setImage(ImageStore.getImage(ImageStore.TABLE));
			this.tabItem.setText(this.resultSet.getConnectable().getDisplayName() + ":" + 
					this.resultSet.getEntity().getQualifiedName());
			this.tabItem.setToolTipText(this.resultSet.getEntity().getQualifiedName());
		}
		
		createTable(composite);
		this.tabItem.setControl(composite);
		initializePopUpMenu();

		this.tableView.tabs.setSelection(index-1);
	}


	/**
	 * @param tabItem
	 * @param composite
	 */
	private void createTable(Composite composite) {
		final Table table = new Table(composite,  SWT.FULL_SELECTION | SWT.MULTI | SWT.BORDER);
    	table.setLayout(new GridLayout());
    	table.setLayoutData(new GridData(GridData.FILL_BOTH));
    	table.addMouseListener(this.tableView.actionGroup.mouseDblClk);
    	
		addColumnsToTable(table);
		this.tableViewer = new TableViewer(table);
		this.tableViewer.setLabelProvider(new LabelProviderImpl());
		this.tableViewer.setContentProvider(new ContentProviderImpl());
		this.tableViewer.setInput(this.resultSet);

		packColumns(table);
	}

	/**
	 * @param table
	 */
	private void packColumns(final Table table) {
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumn(i).pack();
		}
	}

	/**
	 * @param table
	 * @return
	 */
	private int addColumnsToTable(final Table table) {
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		int columnCount = this.resultSet.getColumnCount();
		for (int i = 0; i < columnCount; i++) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setText(this.resultSet.getColumnName(i+1).toString());
		}
		return columnCount;
	}

	public void propertyChange(PropertyChangeEvent event) {
		if ("rows".equals(event.getPropertyName())) {
			this.tableViewer.refresh();
		} else if ("columns".equals(event.getPropertyName())) {
			Table table = this.tableViewer.getTable();
			TableColumn[] columns = table.getColumns();
			for (int i = 0, length = columns == null ? 0 : columns.length; i < length; i++) {
				columns[i].dispose();
			}
			addColumnsToTable(table);
			this.tableViewer.setInput(this.resultSet);
			packColumns(table);
			table.layout();
		}
		updateStatusLine();
	}
	
	public void dispose() {
		this.resultSet.removePropertyChangeListener(this);
		this.tabItem.dispose();
	}

	protected SQLResultSetResults getResultSet() {
		return this.resultSet;
	}
	
	private void initializePopUpMenu() {
        MenuManager manager = new MenuManager();
        manager.setRemoveAllWhenShown(true);
        manager.addMenuListener(new IMenuListener() {
            public void menuAboutToShow(IMenuManager menuManager) {
                ResultSetViewer.this.tableView.actionGroup.fillContextMenu(menuManager);
            }
        });
        Menu contextMenu = manager.createContextMenu(this.tableViewer.getControl());
        this.tableViewer.getControl().setMenu(contextMenu);
        // register the menu to the site so that we can allow 
        // actions to be plugged in
        this.tableView.getSite().registerContextMenu(manager, this.tableView);
	}

	/**
	 * 
	 */
	protected void updateStatusLine() {
		
		if (this == this.tableView.getSelectedResultSetViewer()) {
			IStatusLineManager statusLine = this.tableView.getViewSite().getActionBars().getStatusLineManager();
			if (this.resultSet != null && this.resultSet instanceof Scrollable) {
				Scrollable scrollable = (Scrollable) this.resultSet;
				int start = scrollable.getStart();
				int end = scrollable.getEnd();
				int last = scrollable.getLast();
				
				statusLine.setMessage(
						Messages.getString(getClass(), "position", 
								new String[] { 
									String.valueOf(start), 
									String.valueOf(end),
									last < 0 
										? Messages.getString(getClass(), "unknown")
										: String.valueOf(last)}));
			} else {
				statusLine.setMessage("");
			}
		}
	}
	
	protected ISelection getSelection() {
		return this.tableViewer.getSelection();
	}
}
