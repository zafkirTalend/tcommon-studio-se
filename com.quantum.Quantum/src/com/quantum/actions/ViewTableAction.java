package com.quantum.actions;

import java.sql.SQLException;

import com.quantum.ImageStore;
import com.quantum.Messages;
import com.quantum.adapters.DatabaseAdapter;
import com.quantum.model.Bookmark;
import com.quantum.model.Entity;
import com.quantum.sql.MultiSQLServer;
import com.quantum.sql.SQLResultSetCollection;
import com.quantum.sql.SQLResultSetResults;
import com.quantum.sql.SQLResults;
import com.quantum.ui.dialog.ConnectionUtil;
import com.quantum.ui.dialog.SQLExceptionDialog;
import com.quantum.view.bookmark.EntityNode;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.SelectionListenerAction;

/**
 * @author root
 * Implements action for "View Table"
*/
public class ViewTableAction extends SelectionListenerAction {
    private Entity entity;
    private ConnectionUtil connectionUtil = new ConnectionUtil();
	private final IViewPart view;
    
	public ViewTableAction(IViewPart view) {
        super(Messages.getString(ViewTableAction.class, "text"));
		this.view = view;
        setImageDescriptor(
        		ImageStore.getImageDescriptor(ImageStore.OPEN_TABLE));
	}

	public void run() {
	
		Bookmark bookmark = this.entity.getBookmark();
		DatabaseAdapter adapter = bookmark.getAdapter();
		String query = adapter.getTableQuery(entity.getQuotedTableName());
		
		try {
			SQLResults results = MultiSQLServer.getInstance().execute(
					bookmark, 
					this.connectionUtil.connect(bookmark, getShell()),
					this.entity,
					query);
			
			if (results != null && results.isResultSet()) {
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
	        	try
	        	{
	        		page.showView("com.quantum.view.tableview.TableView");
	    
	        	}catch(PartInitException e){
	        	}
	        	// This must be done after the showView, else the toolbar buttons won't be
	        	// active for the first opening (except if the view was already shown)
	        	SQLResultSetCollection.getInstance().addSQLResultSet((SQLResultSetResults) results);
				
			}
		} catch (SQLException e) {
			SQLExceptionDialog.openException(getShell(), bookmark, e);
		}
	}

	/**
	 * @return
	 */
	private Shell getShell() {
		return this.view.getSite().getShell();
	}

	public boolean updateSelection(IStructuredSelection selection) {
        boolean enabled = super.updateSelection(selection);
        enabled &= (selection.size() == 1);
        
        if (enabled) {
            Object object = selection.getFirstElement();
            if (object != null && object instanceof EntityNode) {
                EntityNode entityNode = (EntityNode) object;
                this.entity = entityNode.getEntity();
                enabled &= (entityNode.isTable() || entityNode.isView());
            }
        }
        return enabled;
	}
}
