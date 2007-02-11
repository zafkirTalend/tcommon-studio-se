package com.quantum.properties;

import java.sql.SQLException;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import com.quantum.ImageStore;
import com.quantum.Messages;
import com.quantum.model.Column;
import com.quantum.util.connection.NotConnectedException;

public class ColumnPropertyPage extends EntityMetaDataPropertyPage {
    
    /**
     * @return
     * @throws NotConnectedException
     * @throws SQLException
     */
    protected Object getInput() throws NotConnectedException, SQLException {
        return getEntity().getColumns();
    }

    /**
     * @return
     */
    protected ITableLabelProvider getLabelProvider() {
        return new ITableLabelProvider() {

			public Image getColumnImage(Object element, int columnIndex) {
				if (columnIndex == 0 && element instanceof Column) {
					return ((Column) element).isPrimaryKey() 
							? ImageStore.getImage(ImageStore.KEYCOLUMN) 
							: ImageStore.getImage(ImageStore.COLUMN);
				} else {
					return null;
				}
			}
			public String getColumnText(Object element, int columnIndex) {
				String label = null;
				if (element instanceof Column) {
					Column column = (Column) element;
					switch (columnIndex) {
					case 0: 
						label = column.getName();
						break;
					case 1: 
						label = column.getTypeName();
						break;
					case 2: 
						label = String.valueOf(column.getSize());
						break;
					case 3: 
						label = String.valueOf(column.getNumberOfFractionalDigits());
						break;
					case 4: 
						label = column.isPrimaryKey() 
							? Messages.getString(ColumnPropertyPage.class, "true") 
							: Messages.getString(ColumnPropertyPage.class, "false");
						break;
					case 5: 
						label = column.isNullable() 
							? Messages.getString(ColumnPropertyPage.class, "true") 
							: Messages.getString(ColumnPropertyPage.class, "false");
						break;
					case 6: 
						label = column.getRemarks();
						break;
					default:
					}
				}
				
				return label == null ? "" : label;
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
        return new BasicContentProvider();
    }

    protected int getNumberOfColumns() {
        return 7;
    }
}