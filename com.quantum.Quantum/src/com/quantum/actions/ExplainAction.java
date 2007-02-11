package com.quantum.actions;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

import com.quantum.Messages;
import com.quantum.adapters.StatementExplainer;
import com.quantum.model.Bookmark;
import com.quantum.sql.SQLResultSetCollection;
import com.quantum.sql.SQLResultSetResults;
import com.quantum.sql.parser.SQL;
import com.quantum.sql.parser.SQLParser;
import com.quantum.sql.parser.SQLProvider;
import com.quantum.ui.dialog.ConnectionUtil;
import com.quantum.ui.dialog.SQLExceptionDialog;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;


/**
 * @author BC Holmes
 */
public class ExplainAction extends Action implements IMenuCreator, BookmarkAction {
    
    class SQLImpl implements SQL {
        private final String sqlCommand;
        public SQLImpl(String sqlCommand) {
            this.sqlCommand = sqlCommand;
        }
        public String getCommand() {
            String temp = this.sqlCommand.toUpperCase().trim();
            return temp.indexOf(' ') >= 0 ? temp.substring(temp.indexOf(' ')) : temp;
        }
        public String toString() {
            return this.sqlCommand;
        }
		public boolean isComment() {
			return false;
		}
    }

	private final ConnectionUtil connectionUtil = new ConnectionUtil();
	private final SQLProvider sqlProvider;
	private final IWorkbenchPart workbenchPart;
	
	public ExplainAction(SQLProvider sqlProvider, IWorkbenchPart workbenchPart) {
		this.sqlProvider = sqlProvider;
		this.workbenchPart = workbenchPart;
		setText(Messages.getString(ExplainAction.class, "text"));
		setMenuCreator(this);
	}
	public Menu getMenu(Menu parent) {
		return BookmarkSubActionFactory.populateDropDownMenu(this, new Menu(parent));
	}

	public void dispose() {
		
	}

	public Menu getMenu(Control parent) {
		return null;
	}

	public void execute(Bookmark bookmark) {
		if (isEnabled(bookmark)) {
			StatementExplainer explainer = bookmark.getAdapter().getStatementExplainer();
			Connection connection = this.connectionUtil.getConnection(bookmark, getShell());
			
			Vector vector = SQLParser.parse(this.sqlProvider.getQuery());
			for (Iterator i = vector.iterator(); i.hasNext();) {
				String sqlString = (String) i.next();
				
				try {
				    SQLImpl sql = new SQLImpl(sqlString);
					SQLResultSetResults results = explainer.explain(bookmark, connection, sql);
					SQLResultSetCollection.getInstance().addSQLResultSet(results);
				} catch (SQLException e) {
					SQLExceptionDialog.openException(getShell(), bookmark, e);
				}
			}
		}
	}
	/**
	 * @return
	 */
	private Shell getShell() {
		return this.workbenchPart.getSite().getShell();
	}
	public boolean isEnabled(Bookmark bookmark) {
		return bookmark.getAdapter().isExplainSupported();
	}
}
