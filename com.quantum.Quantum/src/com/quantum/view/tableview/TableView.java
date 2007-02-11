package com.quantum.view.tableview;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.quantum.QuantumPlugin;
import com.quantum.sql.SQLResultSetCollection;
import com.quantum.sql.SQLResultSetResults;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.part.ViewPart;

/**
 * The Table View. Displays tables and Queries.
 */
public class TableView extends ViewPart implements ISelectionProvider, PropertyChangeListener {

	public static final String NUMBER_OF_ROWS_PREFERENCE_NAME = 
    	TableView.class.getName() + ".numberOfRows";
	public static final String MAXIMUM_CHARS_CELL_PREFERENCE_NAME = 
    	TableView.class.getName() + ".maximumCharsCell";

	protected TabFolder tabs = null;
	
	private Set listeners = Collections.synchronizedSet(new HashSet());
	
	protected List resultSetViewers = Collections.synchronizedList(new ArrayList());
	protected TableViewActionGroup actionGroup;
 
	/**
	 * Generic contructor
	 */
	public TableView() {
		SQLResultSetCollection.getInstance().addPropertyChangeListener(this);
	}


	public void setFocus() {
	}
	
	public void dispose() {
		SQLResultSetCollection.getInstance().removePropertyChangeListener(this);
		super.dispose();
	}
	
    /**
     * Gets the instance of the TableView.  This view can appear on multiple 
     * perspectives, but tabs within the view are shared no matter which perspective
     * is open.
     * 
     * @return the TableView instance.
     */
	public static TableView getInstance() {
		return (TableView) QuantumPlugin.getDefault().getView("com.quantum.view.tableview.TableView");
	}
	
	public void createPartControl(Composite parent) {

        this.tabs = new TabFolder(parent, SWT.NONE);
		this.tabs.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				fireSelectionChangedEvent();
			}
		});

        initActions();

		SQLResultSetResults[] resultSets = SQLResultSetCollection.getInstance().getResultSets();
		for (int i = 0, length = resultSets == null ? 0 : resultSets.length; i < length; i++) {
			this.resultSetViewers.add(new ResultSetViewer(this, resultSets[i]));
		}
	}
	
	public void initActions() {
		
        this.actionGroup = new TableViewActionGroup(this);

        IActionBars actionBars = getViewSite().getActionBars();
        this.actionGroup.fillActionBars(actionBars);
	}

	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		this.listeners.add(listener);
	}
	
	protected void fireSelectionChangedEvent() {
		ISelection selection = getSelection();
		for (Iterator i = this.listeners.iterator(); i.hasNext();) {
			ISelectionChangedListener listener = (ISelectionChangedListener) i.next();
			listener.selectionChanged(new SelectionChangedEvent(this, selection));
		}
		
		ResultSetViewer results = getSelectedResultSetViewer();
		if (results != null) {
			results.updateStatusLine();
		} else {
			getViewSite().getActionBars().getStatusLineManager().setMessage("");
		}
	}

	public ISelection getSelection() {
		SQLResultSetResults selection = getSelectedResultSet();
		return selection == null 
			? new StructuredSelection() 
			: new StructuredSelection(selection);
	}

	/**
	 * Return selected (active) resultset from the Table view
	 * @return SQLResultSetResults or null if none
	 */
	public SQLResultSetResults getSelectedResultSet() {
		ResultSetViewer viewer = getSelectedResultSetViewer();
		return viewer == null ? null : viewer.getResultSet();
	}

	protected ResultSetViewer getSelectedResultSetViewer() {
		ResultSetViewer selection = null;
		int index = this.tabs.getSelectionIndex();
		if (index >= 0) {
			TabItem item = this.tabs.getItem(index);
			for (Iterator i = this.resultSetViewers.iterator(); 
					selection == null && i.hasNext();) {
				ResultSetViewer viewer = (ResultSetViewer) i.next();
				if (item == viewer.getTabItem()) {
					selection = viewer;
				}
			}
		}
		return selection;
	}
	
	public void removeSelectionChangedListener(ISelectionChangedListener listener) {
		this.listeners.remove(listener);
	}

	public void setSelection(ISelection selection) {
	}

	public void propertyChange(PropertyChangeEvent event) {
		if ("resultSets".equals(event.getPropertyName())) {
			SQLResultSetResults selection = getSelectedResultSet();
			
			Collection additions = getAddedResultSets();
			for (Iterator i = additions.iterator(); i.hasNext();) {
				SQLResultSetResults results = (SQLResultSetResults) i.next();
				this.resultSetViewers.add(new ResultSetViewer(this, results));
			}
			
			Collection deletions = getRemovedResultSets(); 
			for (Iterator i = deletions.iterator(); i.hasNext();) {
				SQLResultSetResults results = (SQLResultSetResults) i.next();
				
				ResultSetViewer viewer = findViewerFor(results);
				this.resultSetViewers.remove(viewer);
				viewer.dispose();
			}
			
			SQLResultSetResults newSelection = getSelectedResultSet();
			if (selection != null && newSelection == null) {
				fireSelectionChangedEvent();
			} else if (selection == null && newSelection != null) {
				fireSelectionChangedEvent();
			} else if (selection != null && !selection.equals(newSelection)) {
				fireSelectionChangedEvent();
			}
		}
	}
	
	/**
	 * @return
	 */
	private Collection getRemovedResultSets() {
		SQLResultSetResults[] results = SQLResultSetCollection.getInstance().getResultSets();
		Collection collection = (results == null) 
				? new ArrayList() 
				: new ArrayList(Arrays.asList(results));
		Collection visible = getResultSets();
		visible.removeAll(collection);
		return visible;
	}

	private Collection getAddedResultSets() {
		SQLResultSetResults[] results = SQLResultSetCollection.getInstance().getResultSets();
		Collection collection = (results == null) 
				? new ArrayList() 
				: new ArrayList(Arrays.asList(results));
		collection.removeAll(getResultSets());
		return collection;
	}
	
	private Collection getResultSets() {
		List list = new ArrayList();
		for (Iterator i = this.resultSetViewers.iterator(); i.hasNext();) {
			ResultSetViewer viewer = (ResultSetViewer) i.next();
			list.add(viewer.getResultSet());
		}
		return list;
	}
	
	public ResultSetViewer findViewerFor(SQLResultSetResults results) {
		ResultSetViewer viewer = null;
		for (Iterator i = this.resultSetViewers.iterator(); viewer == null && i.hasNext();) {
			ResultSetViewer temp = (ResultSetViewer) i.next();
			if (results != null && results.equals(temp.getResultSet())) {
				viewer = temp;
			}
		}
		return viewer;
	}
	
	public TabFolder getTabFolder()  {
		return this.tabs;
	}
}