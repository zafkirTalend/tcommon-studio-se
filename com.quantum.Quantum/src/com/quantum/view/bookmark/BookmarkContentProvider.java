package com.quantum.view.bookmark;

import java.sql.SQLException;

import com.quantum.ui.dialog.ExceptionDisplayDialog;
import com.quantum.ui.dialog.SQLExceptionDialog;
import com.quantum.util.connection.NotConnectedException;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewPart;

public class BookmarkContentProvider implements ITreeContentProvider {
	
	private final IViewPart view;

	public BookmarkContentProvider(IViewPart view) {
		this.view = view;
	}
	
    
	public Object[] getChildren(Object element) {
		if (element != null && element instanceof TreeNode) {
			TreeNode node = (TreeNode) element;
			try {
				return node.getChildren();
			} catch (NotConnectedException e) {
				ExceptionDisplayDialog.openError(getShell(), null, null, e);
			} catch (SQLException e) {
				SQLExceptionDialog.openException(getShell(), node.getBookmark(), e);
			}
		}
		return BookmarkListNode.EMPTY_ARRAY;
	}
	/**
	 * @return
	 */
	private Shell getShell() {
		return this.view.getSite().getShell();
	}

	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	public Object getParent(Object element) {
		if (element instanceof TreeNode) {
			TreeNode node = (TreeNode) element;
			return node.getParent();
		}
		return null;
	}

	public boolean hasChildren(Object element) {
		if (element instanceof TreeNode) {
			TreeNode node = (TreeNode) element;
			return node.hasChildren();
		}
		return false;
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}
	
	public void dispose() {
	}
}
