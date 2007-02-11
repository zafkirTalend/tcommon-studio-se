package com.quantum.actions;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import com.quantum.ImageStore;
import com.quantum.Messages;
import com.quantum.ui.dialog.ExceptionDisplayDialog;
import com.quantum.ui.dialog.SQLExceptionDialog;
import com.quantum.util.connection.NotConnectedException;
import com.quantum.view.bookmark.TreeNode;

import org.eclipse.ui.IViewPart;
import org.eclipse.ui.actions.SelectionListenerAction;

/**
 * @author root
 */
public class RefreshBookmarkAction extends SelectionListenerAction {
    private IViewPart view;
	public RefreshBookmarkAction(IViewPart view) {
        super(Messages.getString(RefreshBookmarkAction.class, "text"));
		this.view = view;
        setImageDescriptor(ImageStore.getImageDescriptor(ImageStore.REFRESH));
	}

	public void run() {
		List list = getSelectedNonResources();
        for (Iterator i = list.iterator(); i.hasNext(); ) {
            Object object = i.next();
            if (object != null && object instanceof TreeNode) {
            	try {
            		((TreeNode) object).reload();
            	} catch (NotConnectedException e) {
            		handleException(e);
            	} catch (SQLException e) {
            		handleException(e);
            	}
            }
        }
	}

	/**
	 * @param e
	 */
	private void handleException(Throwable t) {
		if (t instanceof SQLException) {
			SQLExceptionDialog.openException(
					this.view.getSite().getShell(), null, (SQLException) t);
		} else {
			ExceptionDisplayDialog.openError(
					this.view.getSite().getShell(), null, null, t);
		}
	}
}
