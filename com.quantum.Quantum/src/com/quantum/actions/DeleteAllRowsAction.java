package com.quantum.actions;

import java.sql.SQLException;

import com.quantum.Messages;
import com.quantum.model.Table;
import com.quantum.ui.dialog.ExceptionDisplayDialog;
import com.quantum.ui.dialog.SQLExceptionDialog;
import com.quantum.util.connection.ConnectionException;
import com.quantum.view.bookmark.EntityNode;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.actions.SelectionListenerAction;

/**
 * @author root
 *
 */
public class DeleteAllRowsAction extends SelectionListenerAction {
    private IViewPart view;
    
	/**
	 * @see org.eclipse.ui.IViewActionDelegate#init(IViewPart)
	 */
	public DeleteAllRowsAction(IViewPart view) {
        super(Messages.getString(DeleteAllRowsAction.class.getName() + ".text"));
		this.view = view;
	}

	public void run() {
        Table table = getTable();
        try {
			if (table != null) {
				boolean flag = MessageDialog.openConfirm(
                    view.getSite().getShell(), 
                    Messages.getString(DeleteAllRowsAction.class.getName() + ".confirmTitle"),
                    Messages.getString(DeleteAllRowsAction.class.getName() + ".confirmText",  
                    new Object[] { table.getQualifiedName() }));
				if (flag) {
					table.deleteAllRows();
				}
			}
        } catch (SQLException e) {
            SQLExceptionDialog.openException(getShell(), 
            		table == null ? null : table.getBookmark(), e);
        } catch (ConnectionException e) {
            ExceptionDisplayDialog.openError(getShell(), 
                Messages.getString("ExecuteAgainstAction.title"), 
                Messages.getString("ExecuteAgainstAction.ConnectionException"), e);
        }
	}

    private Table getTable() {
        EntityNode node = (EntityNode) getSelectedNonResources().get(0);
        return node == null ? null : (Table) node.getEntity();
    }
    
    

    protected Shell getShell() {
        return this.view.getViewSite().getShell();
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.actions.SelectionListenerAction#updateSelection(org.eclipse.jface.viewers.IStructuredSelection)
     */
    protected boolean updateSelection(IStructuredSelection selection) {
        boolean enabled = super.updateSelection(selection);
        return enabled && selection.size() == 1 && 
            (selection.getFirstElement() instanceof EntityNode) &&
            ((EntityNode) selection.getFirstElement()).isTable();
    }

}
