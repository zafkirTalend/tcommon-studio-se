package com.quantum.view.bookmark;

import java.util.List;

import com.quantum.ImageStore;
import com.quantum.Messages;
import com.quantum.QuantumPlugin;
import com.quantum.model.Bookmark;
import com.quantum.model.BookmarkCollection;

import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.actions.SelectionListenerAction;


final class PasteAction extends SelectionListenerAction {
//	private IViewPart view;
    private BookmarkClipboard bookmarkClipboard;
    /**
     * @param BookmarkView
     */
    public PasteAction(IViewPart view, BookmarkClipboard bookmarkClipboard, 
        ISelectionProvider selectionProvider) {
    	super(Messages.getString(PasteAction.class.getName() + ".text"));
    	setImageDescriptor(ImageStore.getImageDescriptor(ImageStore.PASTE));
    	selectionProvider.addSelectionChangedListener(this);
    	this.bookmarkClipboard = bookmarkClipboard;
//    	this.view = view;
    }
    public void run() {
    	List selection = getSelectedNonResources();
    	
    	// If the selection is a schema node, the user probably wants to create a new database objects in that schema
    	// based on the XML on the clipboard
   	// If the selection is not a group of tables, then we suppose we are pasting a bookmark
    	if (this.bookmarkClipboard.getBookmark() != null) {
    		Bookmark bookmark = new Bookmark(this.bookmarkClipboard.getBookmark());
    		String copyName = BookmarkCollection.getInstance().getCopyName(bookmark.getName());
    		bookmark.setName(copyName); //$NON-NLS-1$
    		BookmarkCollection.getInstance().addBookmark(bookmark);
    	}
    }
    
 	/* (non-Javadoc)
     * @see org.eclipse.ui.actions.SelectionListenerAction#updateSelection(org.eclipse.jface.viewers.IStructuredSelection)
     */
    protected boolean updateSelection(IStructuredSelection selection) {
        boolean enabled = super.updateSelection(selection);
        	
        if (selection.size() == 1 && selection.getFirstElement() instanceof SchemaNode )
		{
        	TextTransfer transfer = TextTransfer.getInstance();
        	String xmlMetaData = (String) QuantumPlugin.getDefault().getSysClip().getContents(transfer);
        	enabled = (xmlMetaData != null && xmlMetaData.length() > 0);
		}
        else {
        	enabled &= this.bookmarkClipboard.getBookmark() != null;
        }
        
        return enabled;
    }

}