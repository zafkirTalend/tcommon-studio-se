package com.quantum.actions;

import java.sql.Connection;
import java.util.List;

import com.quantum.adapters.DatabaseAdapter;
import com.quantum.model.Bookmark;
import com.quantum.sql.SQLResultSetCollection;
import com.quantum.sql.SQLResultSetResults;
import com.quantum.sql.SQLResults;
import com.quantum.ui.dialog.ConnectionUtil;
import com.quantum.view.ViewHelper;
import com.quantum.view.bookmark.EntityNode;
import com.quantum.view.bookmark.TreeNode;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.actions.SelectionListenerAction;

/**
 * @author BC
 */
public abstract class BaseSequenceAction extends SelectionListenerAction {
    
    protected IViewPart view;
    private ConnectionUtil connectionUtil = new ConnectionUtil();

    protected BaseSequenceAction(String text, IViewPart view) {
        super(text);
        this.view = view;
    }

    protected Bookmark getBookmark() {
        TreeNode node = getEntityNode();
        return node.getBookmark();
    }
    
    protected Connection getConnection() {
        return connectionUtil.getConnection(getBookmark(), getShell());
    }

    protected EntityNode getEntityNode() {
        List list = getSelectedNonResources();
        return (EntityNode) list.get(0);
    }

    protected Shell getShell() {
        return this.view.getViewSite().getShell();
    }
    
    protected boolean updateSelection(IStructuredSelection selection) {
        boolean enabled = super.updateSelection(selection);
        return enabled && selection.size() == 1 
            && (selection.getFirstElement() instanceof EntityNode) 
            && ((EntityNode) selection.getFirstElement()).isSequence();
    }

	public void run() {
		EntityNode sequence = getEntityNode();
		String name = null;
		if (sequence.isSequence()) {
			Bookmark bookmark = sequence.getBookmark();
			DatabaseAdapter adapter = bookmark.getAdapter();
			if (adapter == null) return;
			name = sequence.getName();
			String query = getQuery(sequence, name, adapter);
			
			SQLResults results = ViewHelper.tryGetResults(view, bookmark, getConnection(), query);
			if (results != null && results.isResultSet()) 
				SQLResultSetCollection.getInstance().addSQLResultSet((SQLResultSetResults) results);
		}
	}

	/**
	 * @param sequence
	 * @param name
	 * @param adapter
	 * @return
	 */
	protected abstract String getQuery(EntityNode sequence, String name, DatabaseAdapter adapter);
}
