package com.quantum.properties;

import java.sql.SQLException;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.quantum.Messages;
import com.quantum.model.DataType;
import com.quantum.util.connection.NotConnectedException;

public class DataTypesPropertyPage extends TabularMetaDataPropertyPage {

	/**
     * @return
     */
    protected int getNumberOfColumns() {
        return 6;
    }

    /**
     * @return
     */
    protected ITableLabelProvider getLabelProvider() {
        return new ITableLabelProvider() {
        	public Image getColumnImage(Object element, int columnIndex) {
        		return null;
        	}
        	public String getColumnText(Object element, int columnIndex) {
        		String result = null;
        		if (element != null && element instanceof DataType) {
        			DataType dataType = (DataType) element;
        			switch (columnIndex) {
        			case 0:
        				result = dataType.getDatabaseTypeName();
        				break;
        			case 1:
        				result = dataType.getJavaNameType();
        				break;
        			case 2:
        				result = String.valueOf(dataType.getPrecision());
        				break;
        			case 3:
        				result = dataType.getLiteralPrefix();
        				break;
        			case 4:
        				result = dataType.getLiteralSuffix();
        				break;
        			case 5:
        				result = dataType.getCreateParameters();
        				break;
        			default:
        				result = null;
        			}
        		}
        		return result == null ? "" : result;
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
        };
    }

    /**
     * @return
     */
    protected IStructuredContentProvider getContentProvider() {
        return new IStructuredContentProvider() {
        	public Object[] getElements(Object inputElement) {
        		if (inputElement instanceof DataType[]) {
        			return (DataType[]) inputElement;
        		} else {
        			return null;
        		}
        	}
        	public void dispose() {
        	}
        	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        	}
        };
    }

    /**
     * @param bookmark
     * @return
     * @throws NotConnectedException
     * @throws SQLException
     */
    protected Object getInput() throws NotConnectedException, SQLException {
        return getBookmark().getDataTypes();
    }

    /**
     * @param composite
     */
    protected void createHeader(Composite composite) {
        Label label = new Label(composite, SWT.NONE);
        label.setText(Messages.getString(getClass(), "dataTypes"));
        GridData data = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING | GridData.VERTICAL_ALIGN_BEGINNING);
        data.horizontalSpan = 2;
        label.setLayoutData(data);
    }
}