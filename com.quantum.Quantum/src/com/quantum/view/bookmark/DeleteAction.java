package com.quantum.view.bookmark;

import java.util.Iterator;
import java.util.List;

import com.quantum.ImageStore;
import com.quantum.Messages;
import com.quantum.model.BookmarkCollection;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.actions.SelectionListenerAction;

/**
 * @author root
 *
 */
public class DeleteAction extends SelectionListenerAction {
    
    private IViewPart view;
    
	public DeleteAction(IViewPart view, ISelectionProvider selectionProvider) {
        super(Messages.getString(DeleteAction.class.getName() + ".text"));
        setImageDescriptor(ImageStore.getImageDescriptor(ImageStore.DELETE));
		this.view = view;
		selectionProvider.addSelectionChangedListener(this);
	}

	public void run() {
		if (isEachSelectionInstanceOf(getSelectedNonResources().iterator(), BookmarkNode.class)) {
			deleteBookmarks();
		} else if (isEachSelectionInstanceOf(getSelectedNonResources().iterator(), QueryNode.class)) {
			deleteQueries();
		}
	}

	/**
	 * 
	 */
	private void deleteQueries() {
		List nodes = getSelectedNonResources();
		for (Iterator i = nodes.iterator(); i.hasNext();) {
		    QueryNode queryNode = (QueryNode) i.next();
		    if (queryNode != null) {
		        queryNode.getBookmark().removeQuery(queryNode.getQuery());
		    }
		}
	}

	/**
	 * 
	 */
	private void deleteBookmarks() {
		List bookmarkNodes = getSelectedNonResources();
		boolean flag = MessageDialog.openConfirm(view.getSite().getShell(), 
				Messages.getString(getClass(), "confirmTitle"), 
				Messages.getString(getClass(), "confirmText")); 

		for (Iterator i = bookmarkNodes.iterator(); flag && i.hasNext();) {
		    BookmarkNode bookmarkNode = (BookmarkNode) i.next();
		    if (bookmarkNode != null) {
		        BookmarkCollection.getInstance().removeBookmark(bookmarkNode.getBookmark());
		    }
		}
	}

	public boolean updateSelection(IStructuredSelection selection) {
        return isEachSelectionInstanceOf(selection.iterator(), BookmarkNode.class) || 
        		isEachSelectionInstanceOf(selection.iterator(), QueryNode.class);
	}

	/**
	 * @param selection
	 * @param enabled
	 * @param selectionClass
	 * @return
	 */
	private boolean isEachSelectionInstanceOf(Iterator selection, Class selectionClass) {
		boolean result = true;
		for (Iterator i = selection; result && i.hasNext(); ) {
            result &= (selectionClass.isInstance(i.next()));
        }
		return result;
	}

}
