/*
 * Created on 11/08/2003
 *
 */
package com.quantum.view.bookmark;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import com.quantum.ImageStore;
import com.quantum.Messages;
import com.quantum.QuantumPlugin;
import com.quantum.model.Bookmark;
import com.quantum.model.xml.ModelToXMLConverter;
import com.quantum.ui.dialog.ExceptionDisplayDialog;
import com.quantum.ui.dialog.SQLExceptionDialog;
import com.quantum.util.QuantumUtil;
import com.quantum.util.connection.NotConnectedException;
import com.quantum.util.xml.XMLRenderer;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.actions.SelectionListenerAction;
import org.w3c.dom.Document;


final class CopyAction extends SelectionListenerAction {
    private BookmarkClipboard bookmarkClipboard;
    IViewPart view;
    
	/**
	 * @param BookmarkView
	 */
	public CopyAction(IViewPart view, BookmarkClipboard bookmarkClipboard, 
        ISelectionProvider selectionProvider) {
        super(Messages.getString(CopyAction.class.getName() + ".text")); //$NON-NLS-1$
        setImageDescriptor(ImageStore.getImageDescriptor(ImageStore.COPY));
        this.bookmarkClipboard = bookmarkClipboard;
        this.view = view;
        selectionProvider.addSelectionChangedListener(this);
	}
	public void run() {
        
        List list = getSelectedNonResources();
        // The first selecte element will define our type of copy
        Object selection = list.get(0);
        String copyText = null;
        
		if (selection instanceof BookmarkNode) {
            Bookmark bookmark = ((BookmarkNode) selection).getBookmark();
			this.bookmarkClipboard.setBookmark(bookmark);
			copyText = bookmark.getName();
			
		} else if (	selection instanceof DbObjectNode 
					|| selection instanceof GroupNode
					|| selection instanceof SchemaNode 
					|| selection instanceof QuickListNode ) {
			Document doc;
			try {
				doc = ModelToXMLConverter.getInstance().convertList(list, null, false);
			} catch (NotConnectedException e) {
	            ExceptionDisplayDialog.openError(view.getSite().getShell(), null, null, e);
	            e.printStackTrace();
				return;
			} catch (SQLException e) {
				SQLExceptionDialog.openException(view.getSite().getShell(), null, e);
				e.printStackTrace();
				return;
			}
			copyText = XMLRenderer.render(doc);
		} else if (selection instanceof ColumnNode) {
			Iterator iter = list.iterator();
			IPreferenceStore store = QuantumPlugin.getDefault().getPreferenceStore();
	
			while (iter.hasNext()) {
				Object current = iter.next();
				if (current instanceof ColumnNode) {
					ColumnNode column = (ColumnNode) current;
					if (column != null) {
						copyText += column.getName();
						if (iter.hasNext()) copyText += QuantumUtil.trasposeEscape(store.getString("copyColumnSeparator")); //$NON-NLS-1$
					}
				}
			}
		} else if (selection instanceof QueryNode) {
			// If it's a query Node, just copy the contents of the query
			copyText = ((QueryNode)selection).getQuery();
		}
		
		if (copyText != null) {
			QuantumPlugin.getDefault().getSysClip().setContents(
					new Object[] { copyText },
					new Transfer[] { TextTransfer.getInstance()});
		}
		
	}
    /* (non-Javadoc)
     * @see org.eclipse.ui.actions.SelectionListenerAction#updateSelection(org.eclipse.jface.viewers.IStructuredSelection)
     */
    protected boolean updateSelection(IStructuredSelection selection) {
        boolean enabled = super.updateSelection(selection);
        
        return enabled; 
    }

}