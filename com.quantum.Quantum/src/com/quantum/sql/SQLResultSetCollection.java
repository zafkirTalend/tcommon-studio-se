package com.quantum.sql;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;

import com.quantum.QuantumPlugin;
import com.quantum.view.SQLQueryView;
import com.quantum.view.tableview.ResultSetViewer;
import com.quantum.view.tableview.TableView;

/**
 * @author BC
 */
public class SQLResultSetCollection {

	private static final SQLResultSetCollection instance = new SQLResultSetCollection();
	
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	
	private List results = Collections.synchronizedList(new ArrayList());
	
	private SQLResultSetCollection() {
	}
	public static SQLResultSetCollection getInstance() {
		return SQLResultSetCollection.instance;
	}
	
	public void addPropertyChangeListener(PropertyChangeListener arg0) {
		this.propertyChangeSupport.addPropertyChangeListener(arg0);
	}
	public void removePropertyChangeListener(PropertyChangeListener arg0) {
		this.propertyChangeSupport.removePropertyChangeListener(arg0);
	}
	
	public void addSQLResultSet(SQLResultSetResults resultSet) {
		// check if resultSet is already in the collection									
		SQLResultSetResults[] tempSQL = getResultSets();						
		int iLength = tempSQL.length;
		boolean bExist = false;
		int nIndex = 0;
		int nScript = -1;
		for(nIndex=0; nIndex<iLength; nIndex++) {			
			if ((tempSQL[nIndex].getEntity() == (resultSet).getEntity())) {
				if (tempSQL[nIndex].getEntity() == null) { // check for scripts
					nScript = nIndex; // get last script found
				}
				if ((tempSQL[nIndex].getConnectable() == (resultSet).getConnectable()) &&
				  (tempSQL[nIndex].getQuery().equalsIgnoreCase((resultSet).getQuery()))) {
					bExist = true;
					break;
				} 
			}
		}
		
		// check pin tab option for queries
		if (!bExist) {
            // JHvdV: only check this when the view is present
            // do not show the view when the user is involved with the editor.
            IViewPart tableView = null;
            IWorkbenchPage page = QuantumPlugin.getDefault().getActivePage();
            tableView =  page.findView("com.quantum.view.sqlqueryview");
            if (tableView != null){
    			SQLQueryView sqlView = SQLQueryView.getInstance();
    			if ((sqlView != null) && (sqlView.isPinTab()) && ((resultSet).getEntity() == null)) {
    				if (nScript != -1) {
    					bExist = true; // set to true to avoid creating a new one
    					nIndex = nScript; // set the script index we found above
    				} // else create a new tab since none is existing yet for scripts				
    			}
            }
		}
		
		// add only if not yet existing
		if (!bExist) {
			this.results.add(resultSet);
			this.propertyChangeSupport.firePropertyChange("resultSets", null, resultSet);
		} else {										
			TableView tView = TableView.getInstance();
			ResultSetViewer rsViewer = tView.findViewerFor(tempSQL[nIndex]);
			TabFolder tab = tView.getTabFolder();
			
			// refresh results before displaying		
			this.results.set(this.results.indexOf(tempSQL[nIndex]), resultSet);
			this.propertyChangeSupport.firePropertyChange("resultSets", tempSQL[nIndex], resultSet);
			
			tab.setSelection(tab.indexOf(rsViewer.getTabItem()));
		}
	}

	public void removeSQLResultSet(SQLResultSetResults resultSet) {
		if (this.results.remove(resultSet)) {
			this.propertyChangeSupport.firePropertyChange("resultSets", resultSet, null);
		}
	}
		
	public void removeAllSQLResultSet() {
		if (this.results.size() > 0) {
			this.results.clear();
		}
		this.propertyChangeSupport.firePropertyChange("resultSets", null, null);
	}
	
	public SQLResultSetResults[] getResultSets() {
		return (SQLResultSetResults[]) this.results.toArray(
				new SQLResultSetResults[this.results.size()]);
	}
}
