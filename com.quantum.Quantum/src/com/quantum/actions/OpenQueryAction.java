package com.quantum.actions;

import com.quantum.Messages;
import com.quantum.model.Bookmark;
import com.quantum.view.SQLQueryView;
import com.quantum.view.bookmark.QueryNode;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.actions.SelectionListenerAction;

/**
 * @author BC
 */
public class OpenQueryAction extends SelectionListenerAction {

    /**
     * @param text
     */
    public OpenQueryAction(IViewPart viewPart) {
        super(Messages.getString(OpenQueryAction.class.getName() + ".text"));
    }

    protected boolean updateSelection(IStructuredSelection selection) {
        boolean enabled = super.updateSelection(selection);
        enabled &= (selection.size() == 1 &&
            selection.getFirstElement() instanceof QueryNode);
        return enabled;
    }
    public void run() {
        SQLQueryView queryView = SQLQueryView.getInstance();
        QueryNode queryNode = getQueryNode();
        if (queryNode == null) return;
        Bookmark bookmark = queryNode.getBookmark();
        BookmarkSelectionUtil.getInstance().setLastUsedBookmark(bookmark);
        if (queryView != null) {
            queryView.setQuery(getQuery());
        }
    }
    
    private QueryNode getQueryNode() {
        if (isEnabled()) {
            return (QueryNode) getSelectedNonResources().get(0);
        } else {
            return null;
        }
    }
    
    private String getQuery() {
        if (isEnabled()) {
            QueryNode node = (QueryNode) getSelectedNonResources().get(0);
            String query = node.getQuery();
            return query == null || query.trim().endsWith(";") ? query : (query + ";");
        } else {
            return null;
        }
    }
}
