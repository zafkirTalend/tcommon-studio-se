package com.quantum.actions;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.quantum.ImageStore;
import com.quantum.Messages;
import com.quantum.model.Bookmark;
import com.quantum.ui.dialog.SQLExceptionDialog;
import com.quantum.view.bookmark.BookmarkNode;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.actions.SelectionListenerAction;

/**
 * Disconnects from the database
 * 
 * @author root
 */
public class DisconnectAction extends SelectionListenerAction {
    private IViewPart view;
    private List selections = new Vector();

    /**
     * @param text
     */
    public DisconnectAction(IViewPart view) {
        super(Messages.getString(DisconnectAction.class.getName() + ".text"));
        this.view = view;
        setImageDescriptor(
        		ImageStore.getImageDescriptor(ImageStore.DISCONNECT));
    }


    public void run() {
        for (Iterator i = this.selections.iterator(); i.hasNext(); ) {
            Bookmark bookmark = (Bookmark) i.next();
            try {
                bookmark.disconnect();
            } catch (SQLException e) {
                SQLExceptionDialog.openException(
                		this.view.getViewSite().getShell(), bookmark, e);
            }
        }
        updateStatusLine(getMessage("message"));
    }

    private String getMessage(String key) {
        return Messages.getString(getClass().getName() + "." + key);
    }
    
    /**
     * Updates the message shown in the status line.
     *
     * @param message the message to display
     */
    protected void updateStatusLine(String message) {
        this.view.getViewSite().getActionBars().getStatusLineManager().setMessage(message);
    }
    

    /**
     * 
     */
    public boolean updateSelection(IStructuredSelection selection) {
        boolean enabled = super.updateSelection(selection);
        this.selections.clear();
        for (Iterator i = selection.iterator(); enabled && i.hasNext(); ) {
            Object object = i.next();
            if (object instanceof BookmarkNode) {
                BookmarkNode node = (BookmarkNode) object;
                this.selections.add(node.getBookmark());
                enabled &= node.getBookmark().isConnected();
            } else {
                enabled = false;
            }
        }
        return enabled;
    }
}
