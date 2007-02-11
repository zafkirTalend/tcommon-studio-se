package com.quantum.actions;

import java.sql.Connection;
import java.util.Iterator;

import com.quantum.ImageStore;
import com.quantum.Messages;
import com.quantum.model.Bookmark;
import com.quantum.ui.dialog.ConnectionUtil;
import com.quantum.view.bookmark.BookmarkNode;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.actions.SelectionListenerAction;

public class ConnectAction extends SelectionListenerAction {
	/**
     * @param text
     */
    public ConnectAction(IViewPart view) {
        super(Messages.getString(ConnectAction.class.getName() + ".text"));
        this.view = view;
        setImageDescriptor(
            ImageStore.getImageDescriptor(ImageStore.CONNECT));
    }

    private IViewPart view;
    private ConnectionUtil connectionUtil = new ConnectionUtil();

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		run();
	}

	/**
	 * @see org.eclipse.ui.IViewActionDelegate#init(IViewPart)
	 */
	public void init(IViewPart view) {
		this.view = view;
	}

	/**
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	public void run() {
        
        int bookmarks = 0;
        int errors = 0;
        String lastBookmarkName = null;
        for (Iterator i = getSelectedNonResources().iterator(); i.hasNext();) {
            Bookmark bookmark = ((BookmarkNode) i.next()).getBookmark();
            Connection connection = this.connectionUtil.connect(bookmark, getShell());
            if (connection == null) {
                errors++;
            }
            bookmarks++;
            lastBookmarkName = bookmark.getName();
        }
        
        if (bookmarks == 1 && errors == 0) {
            updateStatusLine(getMessage("singleSuccessMessage", 
                new Object[] {lastBookmarkName}));
        } else if (bookmarks == 1 && errors == 1) {
            updateStatusLine(getMessage("singleFailureMessage", 
                new Object[] {lastBookmarkName}));
        } else if (bookmarks > 1 && errors == 0) {
            updateStatusLine(getMessage("multiSuccessMessage", 
                new Object[] {String.valueOf(bookmarks)}));
        } else if (bookmarks > 1 && errors > 0) {
            updateStatusLine(getMessage("multiFailureMessage", 
            new Object[] {String.valueOf(bookmarks - errors), 
                String.valueOf(errors)}));
        }
	}
    
    private String getMessage(String key, Object[] arguments) {
        return Messages.getString(getClass().getName() + "." + key, arguments);
    }
    
    /**
     * Updates the message shown in the status line.
     *
     * @param message the message to display
     */
    protected void updateStatusLine(String message) {
        this.view.getViewSite().getActionBars().getStatusLineManager().setMessage(message);
    }
    
    protected Shell getShell() {
        return this.view.getSite().getShell();
    }

    /**
     * 
     */
    protected boolean updateSelection(IStructuredSelection selection) {
        boolean enabled = super.updateSelection(selection);
        for (Iterator i = selection.iterator();
            enabled && i.hasNext();
            ) {
            Object object = i.next();
            if (object instanceof BookmarkNode) {
                BookmarkNode node = (BookmarkNode) object;
                enabled &= !node.getBookmark().isConnected();
            } else {
                enabled = false;
            }
        }
        return enabled;
    }
}