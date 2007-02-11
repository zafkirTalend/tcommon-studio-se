package com.quantum.actions;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.quantum.ImageStore;
import com.quantum.Messages;
import com.quantum.model.Bookmark;
import com.quantum.model.Entity;
import com.quantum.sql.MultiSQLServer;
import com.quantum.sql.SQLResultSetCollection;
import com.quantum.sql.SQLResultSetResults;
import com.quantum.ui.dialog.ConnectionUtil;
import com.quantum.ui.dialog.SQLExceptionDialog;
import com.quantum.view.bookmark.EntityNode;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.actions.SelectionListenerAction;

public class ViewTableDetailsAction extends SelectionListenerAction  {
	private IViewPart view;
    private ConnectionUtil connectionUtil = new ConnectionUtil();
	
	public ViewTableDetailsAction(IViewPart view) {
        super(Messages.getString(ViewTableDetailsAction.class, "text"));
        setImageDescriptor(ImageStore.getImageDescriptor(ImageStore.TABLE_DETAILS));
		this.view = view;
	}

	public void run() {
        Entity entity = getEntity();
        Connection connection = connectionUtil.getConnection(getBookmark(), getShell());
        if (connection != null) {
        	try {
	            SQLResultSetResults results = 
	            	MultiSQLServer.getInstance().getMetaData(entity, connection);
	            if (results != null) {
	                SQLResultSetCollection.getInstance().addSQLResultSet(results);
	            }
        	} catch (SQLException e) {
        		SQLExceptionDialog.openException(getShell(), 
        				entity == null ? null : entity.getBookmark(), e);
        	}
        }
	}
	public void selectionChanged(IAction action, ISelection selection) {
	}
    protected Bookmark getBookmark() {
        return getEntity().getBookmark();
    }
    protected Shell getShell() {
        return this.view.getViewSite().getShell();
    }
    protected Entity getEntity() {
        List list = getSelectedNonResources();
        return ((EntityNode) list.get(0)).getEntity();
    }

    /**
     * @see org.eclipse.ui.actions.SelectionListenerAction#updateSelection(org.eclipse.jface.viewers.IStructuredSelection)
     */
    protected boolean updateSelection(IStructuredSelection selection) {
        boolean enabled = super.updateSelection(selection);
        return enabled && selection.size() == 1;
    }

}
