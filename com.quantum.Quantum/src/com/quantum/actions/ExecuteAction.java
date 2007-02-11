package com.quantum.actions;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPartSite;

import com.quantum.ImageStore;
import com.quantum.Messages;
import com.quantum.model.Bookmark;
import com.quantum.sql.parser.SQLParser;
import com.quantum.sql.parser.SQLProvider;
import com.quantum.view.SQLQueryView;

/**
 * Executes a query from the QueryView
 *
 */
public class ExecuteAction extends BaseExecuteAction 
		implements IMenuCreator, BookmarkAction, PropertyChangeListener {
	
	private boolean autoCommit = true;
	private boolean canChangeAutoCommit = true;
	private final SQLProvider sqlProvider;
	private final IWorkbenchPartSite site;
	private final IActionBars actionBars;
	private final SQLQueryView view;

	public ExecuteAction(SQLProvider sqlProvider, SQLQueryView view) {
		this(sqlProvider, view.getViewSite().getActionBars(), view.getSite(), view);
	}	
	
	public ExecuteAction(SQLProvider sqlProvider, IEditorPart editor) {
		this(sqlProvider, editor.getEditorSite().getActionBars(), editor.getSite(), null);
		// In principle, editors won't change the autocommit setting of the connection
		// The dependencies created by the autocommit facility are annoying, some other structure is perhaps needed
		this.canChangeAutoCommit = false;
	}	
	
	private ExecuteAction(SQLProvider sqlProvider, IActionBars actionBars, IWorkbenchPartSite site, SQLQueryView view) {
		this.sqlProvider = sqlProvider;
		this.actionBars = actionBars;
		this.site = site;
		this.view = view;
		setActionDefinitionId("com.quantum.actions.ExecuteAction");
		setImageDescriptor(ImageStore.getImageDescriptor(ImageStore.PLAY));
		setText(Messages.getString(ExecuteAction.class, "textNoBookmark"));
		initTextAndToolTip();
		setMenuCreator(this);
		
		BookmarkSelectionUtil.getInstance().addPropertyChangeListener(this);
	}

	/**
	 * 
	 */
	private void initTextAndToolTip() {
		Bookmark lastUsedBookmark = BookmarkSelectionUtil.getInstance().getLastUsedBookmark();
		if (lastUsedBookmark == null) {
			setToolTipText(Messages.getString(ExecuteAction.class, "textNoBookmark"));
		} else {
			Object[] parameters = new Object[] { lastUsedBookmark.getName() };
			setToolTipText(Messages.getString(ExecuteAction.class, "text", parameters));
		}
	}

	protected Bookmark getBookmark() {
		Bookmark lastUsedBookmark = BookmarkSelectionUtil.getInstance().getLastUsedBookmark();
		return lastUsedBookmark == null ? super.getBookmark() : lastUsedBookmark;
	}
	protected void execute(Bookmark bookmark, Connection connection) 
			throws IOException, CoreException, SQLException {
		
		try {
			boolean changed = false;
			if (view != null) {
				autoCommit = view.isAutoCommit();
			}
			if (!bookmark.isIgnoreQueryViewSetting() && canChangeAutoCommit && connection.getAutoCommit() != this.autoCommit) {
				connection.setAutoCommit(this.autoCommit);
				changed = true;
			}
			
			super.execute(bookmark, connection);
			
			if (changed) {
				MessageDialog.openInformation(getShell(), 
						Messages.getString(ExecuteAction.class, "autocommitTitle"), 
						Messages.getString(ExecuteAction.class, "autocommitMessage", 
								new Object[] { bookmark.getName() }));
			}
		} finally {
			BookmarkSelectionUtil.getInstance().setLastUsedBookmark(bookmark);
		}
	}
	/**
	 * @return a List with the parsed queries from the query text. 
	 * 			If sendQueryAsIs is true, no parsing occurs and the query text is sent whole as the
	 * 			first list element.
	 */
	protected List getQueries(boolean sendQueryAsIs) {
		Vector queries = null;
		if (sendQueryAsIs) {
			queries = new Vector ();
			queries.add(this.sqlProvider.getQuery());
		} else {
			getStatusLineManager().setMessage(
					Messages.getString(ExecuteAction.class, "parsing")); //$NON-NLS-1$
			queries = SQLParser.parse(this.sqlProvider.getQuery());
		}
		return queries;
	}
	
	/**
	 * @return
	 */
	protected IStatusLineManager getStatusLineManager() {
		return this.actionBars.getStatusLineManager();
	}

	protected Shell getShell() {
		return this.site.getShell();
	}

	public void dispose() {
	    BookmarkSelectionUtil.getInstance().removePropertyChangeListener(this);
	}

	public Menu getMenu(Control parent) {
		return BookmarkSubActionFactory.populateDropDownMenu(this, new Menu(parent));
	}

	public Menu getMenu(Menu parent) {
		return BookmarkSubActionFactory.populateDropDownMenu(this, new Menu(parent));
	}

	public boolean isEnabled(Bookmark bookmark) {
		return true;
	}

    public void propertyChange(PropertyChangeEvent event) {
        if ("lastUsedBookmark".equals(event.getPropertyName())) {
            initTextAndToolTip();
        }
    }
}
