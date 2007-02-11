package com.quantum.properties;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;

import com.quantum.ImageStore;
import com.quantum.Messages;
import com.quantum.model.Index;
import com.quantum.util.connection.NotConnectedException;

public class IndexPropertyPage extends EntityMetaDataPropertyPage {
	
	private final class TableLabelProviderImpl extends LabelProvider 
			implements ITableLabelProvider {
        public Image getColumnImage(Object element, int columnIndex) {
        	if (columnIndex == 2 && element instanceof IndexAdaptor) {
        		return ImageStore.getImage(ImageStore.COLUMN);
        	} else {
        		return null;
        	}
        }

        public String getColumnText(Object element, int columnIndex) {
        	String label = null;
        	if (element instanceof IndexAdaptor) {
        		IndexAdaptor index = (IndexAdaptor) element;
        		switch (columnIndex) {
        		case 0:
        			if (index.getColumnNumber() == 0) {
        				label = index.getIndex().getName();
        			}
        			break;
        		case 1:
        		    if (index.getColumnNumber() == 0) {
        				label = index.getIndex().isUnique() 
        						? Messages.getString(IndexPropertyPage.class, "true") 
        						: Messages.getString(IndexPropertyPage.class, "false");
        		    }
        		    break;
        		case 2: 
        			label = index.getIndex().getColumnName(index.getColumnNumber());
        			break;
        		case 3: 
        			if (index.getIndex().isAscending(index.getColumnNumber())) {
        				label = Messages.getString(IndexPropertyPage.class, "ascending");
        			} else if (index.getIndex().isDescending(index.getColumnNumber())) {
        				label = Messages.getString(IndexPropertyPage.class, "descending");
        			}
        			break;
        		default:
        		}
        	}
        	
        	return label == null ? "" : label;
        }
    }

    class IndexAdaptor {
		private final Index index;
		private final int columnNumber;
		public IndexAdaptor(Index index, int columnNumber) {
			this.index = index;
			this.columnNumber = columnNumber;
		}
		public Index getIndex() {
			return this.index;
		}
		public int getColumnNumber() {
			return this.columnNumber;
		}
	}
    
	/**
     * @return
     */
    protected int getNumberOfColumns() {
        return 4;
    }

    /**
     * @return
     */
    protected ITableLabelProvider getLabelProvider() {
        return new TableLabelProviderImpl();
    }

    /**
     * @return
     */
    protected IStructuredContentProvider getContentProvider() {
        return new IStructuredContentProvider() {
			public Object[] getElements(Object inputElement) {
				if (inputElement instanceof Index[]) {
					Index[] indeces = (Index[]) inputElement;
					List list = new ArrayList();
					for (int i = 0, length = indeces == null ? 0 : indeces.length; i < length; i++) {
						for (int j = 0, columns = indeces[i].getNumberOfColumns(); j < columns; j++) {
							list.add(new IndexAdaptor(indeces[i], j));
						}
					}
					return list.toArray();
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

    protected Object getInput() throws NotConnectedException, SQLException {
        return getEntity().getIndexes();
    }
}