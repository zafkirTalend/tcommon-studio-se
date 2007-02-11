package com.quantum.properties;

import java.sql.SQLException;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.quantum.Messages;
import com.quantum.util.connection.NotConnectedException;

/**
 * @author BC
 */
public abstract class TabularMetaDataPropertyPage extends MetaDataPropertyPage {

	class BasicContentProvider implements IStructuredContentProvider {
	
		public Object[] getElements(Object inputElement) {
			return (inputElement instanceof Object[]) ? (Object[]) inputElement : null; 
		}
		public void dispose() {
		}
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}

	/**
	 * @param table
	 */
	protected void setColumnWidths(Table table) {
		for (int i = 0, length = table.getColumnCount(); i < length; i++) {
		    table.getColumn(i).pack();
		}
	}
	protected void createInformationArea(Composite composite) {
        if (getBookmark().isConnected()) {
			try {
				Object input = getInput();
	        	
	        	Table table = new Table(composite, SWT.SINGLE | SWT.FULL_SELECTION | SWT.BORDER);
	            table.setHeaderVisible(true);
	            GridData gridData = new GridData(GridData.FILL_BOTH);
                gridData.heightHint = 200;
                gridData.widthHint = 100;
	            table.setLayoutData(gridData);
	            for (int i = 0, length = getNumberOfColumns(); i < length; i++) {
	                TableColumn column = new TableColumn(table, SWT.NONE);
	                column.setText(Messages.getString(getClass(), "column" + i));
	            }
	        	
	        	TableViewer viewer = new TableViewer(table);
	            setTableContents(viewer, input);
	            setColumnWidths(table);
			} catch (NotConnectedException e) {
				createErrorMessage(composite, e);
			} catch (SQLException e) {
				createErrorMessage(composite, e);
			} catch (RuntimeException e) {
				createErrorMessage(composite, e);
			}
        }
	}

    private void setTableContents(TableViewer viewer, Object input) {
		viewer.setContentProvider(getContentProvider());
		viewer.setLabelProvider(getLabelProvider());
		viewer.setInput(input);
	}

    protected abstract Object getInput() throws NotConnectedException, SQLException;
    protected abstract int getNumberOfColumns();
    protected abstract ITableLabelProvider getLabelProvider();
    protected abstract IStructuredContentProvider getContentProvider();


}

