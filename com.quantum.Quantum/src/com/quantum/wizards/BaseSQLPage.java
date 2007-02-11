package com.quantum.wizards;

import java.sql.SQLException;

import com.quantum.adapters.DatabaseAdapter;
import com.quantum.model.Bookmark;
import com.quantum.model.Column;
import com.quantum.model.Entity;
import com.quantum.sql.MultiSQLServer;
import com.quantum.sql.SQLResultSetResults;
import com.quantum.sql.SQLResults;
import com.quantum.ui.dialog.ConnectionUtil;
import com.quantum.ui.dialog.SQLExceptionDialog;
import com.quantum.util.connection.NotConnectedException;

import org.eclipse.jface.wizard.WizardPage;

/**
 * @author BC Holmes
 */
public abstract class BaseSQLPage extends WizardPage implements SQLPage {

    protected SQLResultSetResults.Row row;
	protected SQLResultSetResults results;
	private ConnectionUtil connectionUtil = new ConnectionUtil();

    public BaseSQLPage(String pageName) {
        super(pageName);
    }
    public boolean performFinish() {
		Bookmark bookmark = (Bookmark) this.results.getConnectable();
		try {
			bookmark.addQuery(getQueryText());
			SQLResults sqlResults = MultiSQLServer.getInstance().execute(bookmark, 
					this.connectionUtil.getConnection(bookmark, getShell()), getQueryText());
			return sqlResults == null ? false : true;
		} catch (SQLException e) {
			SQLExceptionDialog.openException(getShell(), bookmark, e);
			return false;
		}
	}
    
    protected abstract String getQueryText();
    protected void appendColumn(StringBuffer whereClause, Entity entity, String columnName, DatabaseAdapter adapter, String value) {
    	
    	Bookmark bookmark = (Bookmark) this.results.getConnectable();
		
        if (adapter != null && entity != null && getColumn(entity, columnName) != null) {
            Column column = getColumn(entity, columnName);
        	whereClause.append(adapter.quote(bookmark, value, column.getType(), column.getTypeName()));
        } else {
        	whereClause.append(value);
        }
    }
    /**
	 * @param entity
	 * @param columnName
	 * @return
	 * @throws NotConnectedException
	 * @throws SQLException
	 */
	protected Column getColumn(Entity entity, String columnName)  {
		try {
			return entity == null ? null : entity.getColumn(columnName);
		} catch (NotConnectedException e) {
			return null;
		} catch (SQLException e) {
			return null;
		}
	}
	public void init(SQLResultSetResults results, SQLResultSetResults.Row row) {
    	this.results = results;
		this.row = row;
    }
}
