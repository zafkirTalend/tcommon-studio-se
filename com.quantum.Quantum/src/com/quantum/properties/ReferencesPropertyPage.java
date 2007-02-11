package com.quantum.properties;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;

import com.quantum.ImageStore;
import com.quantum.Messages;
import com.quantum.model.ForeignKey;
import com.quantum.util.connection.NotConnectedException;

public class ReferencesPropertyPage extends EntityMetaDataPropertyPage {
	
	class ColumnDetailsAdaptor {
		private final int columnNumber;
		private final ForeignKey foreignKey;

		public ColumnDetailsAdaptor(ForeignKey foreignKey, int columnNumber) {
			this.foreignKey = foreignKey;
			this.columnNumber = columnNumber;
		}
		public int getColumnNumber() {
			return this.columnNumber;
		}
		public ForeignKey getForeignKey() {
			return this.foreignKey;
		}
	}
    
	class TableDetailsAdaptor {
		private final ForeignKey foreignKey;

		public TableDetailsAdaptor(ForeignKey foreignKey) {
			this.foreignKey = foreignKey;
		}
		public ForeignKey getForeignKey() {
			return this.foreignKey;
		}
	}
    
	/**
	 * @throws SQLException
	 * @throws NotConnectedException
     * 
     */
    protected Object getInput() throws NotConnectedException, SQLException {
        return getEntity().getReferences();
    }

    /**
     * @return
     */
    protected ITableLabelProvider getLabelProvider() {
        return new ITableLabelProvider() {

			public Image getColumnImage(Object element, int columnIndex) {
				if ((columnIndex == 1 || columnIndex == 2) 
						&& element instanceof ColumnDetailsAdaptor) {
					return ImageStore.getImage(ImageStore.COLUMN);
				} else if ((columnIndex == 1 || columnIndex == 2) 
						&& element instanceof TableDetailsAdaptor) {
					return ImageStore.getImage(ImageStore.TABLE);
				} else if ((columnIndex == 0) 
						&& element instanceof TableDetailsAdaptor) {
					return ImageStore.getImage(ImageStore.FOREIGNKEY);
				} else {
					return null;
				}
			}
			public String getColumnText(Object element, int columnIndex) {
				String label = null;
				if (element instanceof TableDetailsAdaptor) {
					label = getColumnText((TableDetailsAdaptor) element,  columnIndex);
				} else if (element instanceof ColumnDetailsAdaptor) {
						label = getColumnText((ColumnDetailsAdaptor) element,  columnIndex);
				}
				
				return label == null ? "" : label;
			}
			private String getColumnText(TableDetailsAdaptor adaptor,  int columnIndex) {
				switch (columnIndex) {
				case 0:
					return adaptor.getForeignKey().getName();
				case 1:
					return adaptor.getForeignKey().getLocalEntityQualifiedName();
				case 2:
					return adaptor.getForeignKey().getForeignEntityQualifiedName();
				case 3:
					ForeignKey key = adaptor.getForeignKey();
					if (key.getDeleteRule() == DatabaseMetaData.importedKeyCascade) {
						return Messages.getString(
								ReferencesPropertyPage.class, "importedKeyCascade");
					} else if (key.getDeleteRule() == DatabaseMetaData.importedKeyNoAction) {
							return Messages.getString(
									ReferencesPropertyPage.class, "importedKeyNoAction");
					} else if (key.getDeleteRule() == DatabaseMetaData.importedKeyRestrict) {
						return Messages.getString(
								ReferencesPropertyPage.class, "importedKeyRestrict");
					} else if (key.getDeleteRule() == DatabaseMetaData.importedKeySetDefault) {
						return Messages.getString(
								ReferencesPropertyPage.class, "importedKeySetDefault");
					} else if (key.getDeleteRule() == DatabaseMetaData.importedKeySetNull) {
						return Messages.getString(
								ReferencesPropertyPage.class, "importedKeySetNull");
					} else {
						return null;
					}
				default:
					return null;
				}
			}
			private String getColumnText(ColumnDetailsAdaptor adaptor,  int columnIndex) {
				switch (columnIndex) {
				case 1:
					return adaptor.getForeignKey().getLocalColumnName(adaptor.getColumnNumber());
				case 2:
					return adaptor.getForeignKey().getForeignColumnName(adaptor.getColumnNumber());
				default:
					return null;
				}
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
				List list = new ArrayList();
				if (inputElement instanceof ForeignKey[]) {
					ForeignKey[] keys = (ForeignKey[]) inputElement;
					for (int i = 0, length = keys == null ? 0
							: keys.length; i < length; i++) {
						list.add(new TableDetailsAdaptor(keys[i]));
						for (int j = 0, columns = keys[i].getNumberOfColumns(); j < columns; j++) {
							list.add(new ColumnDetailsAdaptor(keys[i], j));
						}
					}
				}
				return list.toArray();
			}
			public void dispose() {
			}
			public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			}
		};
    }

    /* (non-Javadoc)
     * @see com.quantum.properties.EntityMetaDataPropertyPage#getNumberOfColumns()
     */
    protected int getNumberOfColumns() {
        return 4;
    }
}