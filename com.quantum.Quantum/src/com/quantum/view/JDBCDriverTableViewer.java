package com.quantum.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.quantum.ImageStore;
import com.quantum.Messages;
import com.quantum.adapters.AdapterFactory;
import com.quantum.model.BookmarkCollection;
import com.quantum.model.JDBCDriver;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;


/**
 * @author BC
 */
public class JDBCDriverTableViewer implements PropertyChangeListener, ISelectionProvider {

	public class LabelProviderImpl implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			if (columnIndex == 0) {
				return ImageStore.getImage(ImageStore.DRIVER);
			} else {
				return null;
			}
		}
		public String getColumnText(Object element, int columnIndex) {
			String result = null;
			switch (columnIndex) {
			case 0: 
				result = ((JDBCDriver) element).getName();
				break;
			case 1: 
				result = ((JDBCDriver) element).getClassName();
				break;
			case 2: 
				String type = ((JDBCDriver) element).getType();
				result = AdapterFactory.getInstance().getAdapter(type).getDisplayName();
				break;
			case 3: 
				result = ((JDBCDriver) element).getVersion();
				break;
			case 4: 
				result = ((JDBCDriver) element).getJarFilePath();
				break;
			default:
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
	}
	
	public class ContentProviderImpl implements IStructuredContentProvider {
		public void dispose() {
		}
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
		public Object[] getElements(Object inputElement) {
			if (inputElement == BookmarkCollection.getInstance()) {
				return BookmarkCollection.getInstance().getJDBCDrivers();
			} else {
				return null;
			}
		}
	}
	
	private TableViewer tableViewer;
	private List listeners = Collections.synchronizedList(new ArrayList());
	
	public JDBCDriverTableViewer(Composite container) {
		Table table = new Table(container, SWT.SINGLE | SWT.FULL_SELECTION | SWT.BORDER);
        table.setHeaderVisible(true);
        for (int i = 0, length = 5; i < length; i++) {
            TableColumn column = new TableColumn(table, SWT.NONE);
            column.setText(Messages.getString(getClass(), "driverColumn" + i));
        }
		
        for (int i = 0, length = table.getColumnCount(); i < length; i++) {
            table.getColumn(i).pack();
        }
		
		this.tableViewer = new TableViewer(table);
		this.tableViewer.setContentProvider(new ContentProviderImpl());
		this.tableViewer.setLabelProvider(new LabelProviderImpl());
		this.tableViewer.setColumnProperties(new String[] { "name", "className", "driverType", "version", "jarFileName" });
		this.tableViewer.setInput(BookmarkCollection.getInstance());
		
		this.tableViewer.addSelectionChangedListener(new ISelectionChangedListener(){
			public void selectionChanged(SelectionChangedEvent event) {
				fireSelectionChangedEvent(event.getSelection());
			}
		});
		
		BookmarkCollection.getInstance().addPropertyChangeListener(this);
		registerWithAllDrivers();
	}
	/**
	 * 
	 */
	private void registerWithAllDrivers() {
		JDBCDriver[] drivers = BookmarkCollection.getInstance().getJDBCDrivers();
		for (int i = 0, length = drivers == null ? 0 : drivers.length; i < length; i++) {
			drivers[i].removePropertyChangeListener(this);
			drivers[i].addPropertyChangeListener(this);
		}
	}
	/**
	 * @return
	 */
	public Control getControl() {
		return this.tableViewer.getControl();
	}
	public void propertyChange(PropertyChangeEvent event) {
		if ("drivers".equals(event.getPropertyName())) {
			registerWithAllDrivers();
			if (!this.tableViewer.getControl().isDisposed()) {
				this.tableViewer.refresh();
			}
		} else if (event.getSource() instanceof JDBCDriver) {
			if (!this.tableViewer.getControl().isDisposed()) {
				this.tableViewer.refresh(event.getSource());
			}
		}
	}
	
	public void dispose() {
		JDBCDriver[] drivers = BookmarkCollection.getInstance().getJDBCDrivers();
		for (int i = 0, length = drivers == null ? 0 : drivers.length; i < length; i++) {
			drivers[i].removePropertyChangeListener(this);
		}
		BookmarkCollection.getInstance().removePropertyChangeListener(this);
	}
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		if (!this.listeners.contains(listener)) {
			this.listeners.add(listener);
		}
	}
	public ISelection getSelection() {
		return this.tableViewer == null ? null : this.tableViewer.getSelection();
	}
	public void removeSelectionChangedListener(ISelectionChangedListener listener) {
		this.listeners.remove(listener);
	}
	public void setSelection(ISelection selection) {
	}
	/**
	 * @param selection
	 */
	protected void fireSelectionChangedEvent(ISelection selection) {
		for (Iterator i = this.listeners.iterator(); selection != null && i.hasNext();) {
			ISelectionChangedListener listener = (ISelectionChangedListener) i.next();
			listener.selectionChanged(new SelectionChangedEvent(this, selection));
		}
	}
}
