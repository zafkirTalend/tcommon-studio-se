package com.quantum.actions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import com.quantum.ImageStore;
import com.quantum.Messages;
import com.quantum.QuantumPlugin;
import com.quantum.log.QuantumLog;
import com.quantum.model.Bookmark;
import com.quantum.model.BookmarkCollection;
import com.quantum.sql.MultiSQLServer;
import com.quantum.sql.SQLResultSetCollection;
import com.quantum.sql.SQLResultSetResults;
import com.quantum.sql.SQLResults;
import com.quantum.sql.SQLUpdateResults;
import com.quantum.ui.dialog.ConnectionUtil;
import com.quantum.ui.dialog.ExceptionDisplayDialog;
import com.quantum.ui.dialog.SQLExceptionDialog;
import com.quantum.ui.dialog.SimpleSelectionDialog;
import com.quantum.view.tableview.TableView;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.SelectionListenerAction;

/**
 * @author BC
 */
public abstract class BaseExecuteAction extends SelectionListenerAction {
	
	class Metrics {
		int resultCount = 0;
		int resultUpdateCount = 0;
		int errorCount = 0;
		int resultsDisplayed = 0;
		double queryDuration= 0.0;
		
		public void measure(SQLResults results) {
			if (results == null) {
				errorCount++;
			} else {
				queryDuration += results.getTime()/1000.0; 
				resultCount++;
				if (results.isResultSet()) {
					resultsDisplayed++;
				} else {
					resultUpdateCount += ((SQLUpdateResults) results).getUpdateCount();
				}
			}
		}
		
		public boolean hasErrors() {
			return this.errorCount > 0;
		}
		
		Object[] getResults() {
			return new Object[] {
				new Integer(this.resultCount),
				new Integer(this.resultUpdateCount),
				new Integer(this.resultsDisplayed),
				new Integer(this.errorCount),
				new Double(this.queryDuration),
			};
		}
	}
    
    private ConnectionUtil connectionUtil = new ConnectionUtil();
	private static IPreferenceStore store = QuantumPlugin.getDefault().getPreferenceStore(); 
	private boolean useJobs = store.getBoolean(com.quantum.PluginPreferences.GLOBAL_USE_JOBS);
	
    protected abstract Shell getShell();
    
    protected Connection getConnection(Bookmark bookmark) {
        return this.connectionUtil.getConnection(bookmark, getShell());
    }

	String execute1 = Messages.getString(BaseExecuteAction.class, "execute1");
	String execute2 = Messages.getString(BaseExecuteAction.class, "execute2");
	
	ExecuteSQL sqlJob = null;
	
	protected BaseExecuteAction(String text) {
		super(text);
		if (useJobs) {
			sqlJob = new ExecuteSQL();
		}
	}
	
	protected BaseExecuteAction() {
		this(null);
	}
	
	public void run() {
		Bookmark bookmark = getBookmark();
		if (bookmark != null) {
           	execute(bookmark);
		}
	}

	/**
	 * @param bookmark
	 */
	public void execute(Bookmark bookmark) {
		if (bookmark != null) {
	        try {
				useJobs = store.getBoolean(com.quantum.PluginPreferences.GLOBAL_USE_JOBS);
				getStatusLineManager().setErrorMessage(null);
				Connection connection = getConnection(bookmark);
				if (connection != null) {
					execute(bookmark, connection);
				}
	        } catch (IOException e) {
	            ExceptionDisplayDialog.openError(getShell(), null, null, e);
	        } catch (SQLException e) {
	            SQLExceptionDialog.openException(getShell(), bookmark, e);
	        } catch (CoreException e) {
	            ErrorDialog.openError(getShell(), null, null, e.getStatus());
	        }
		}
	}

	/**
	 * @param bookmark
	 * @param connection
	 * @throws IOException
	 * @throws CoreException
	 */
	protected void execute(Bookmark bookmark, Connection connection) 
			throws IOException, CoreException, SQLException {
		getStatusLineManager().setMessage(execute1);
		MultiSQLServer server = MultiSQLServer.getInstance();

		Metrics metrics = new Metrics();

		List queries = getQueries(bookmark.isSendQueryAsIs());
		
		// If the user wants to use jobs for its queries, simply schedule the request
		// This is known to give problems in some cases or with some JDBC drivers, that's not clear
		if (useJobs) {
			sqlJob.schedule(bookmark, connection, server, queries);
		// else, make a synchronous execution
		} else {
			IProgressMonitor progressBar = getStatusLineManager().getProgressMonitor();
			progressBar.beginTask("queries", queries.size());
			for (int i = 0; i < queries.size(); i++) {
				getStatusLineManager().setMessage((i % 2 == 0) ? execute1 : execute2); 
				String query = (String) queries.get(i);
				// Strip the newlines if the bookmark asks for it
				if (bookmark.isStripNewline()) {
					query = query.replaceAll( "\r\n", " ");
					query = query.replaceAll( "\n", " ");
					query = query.replaceAll( "\r", " ");
				}
				System.out.println(">" + query + "<"); //$NON-NLS-1$ //$NON-NLS-2$
				if (query != null && query.trim().length() > 0) {
					SQLResults results = getSQLResults(bookmark, connection, server, query);
					metrics.measure(results);
					if (results != null) {
						bookmark.addQuery(query);
						if (results.isResultSet()) {
							SQLResultSetCollection.getInstance().addSQLResultSet(
									(SQLResultSetResults) results);
							activateTableView();
						}
					}
				}
				progressBar.worked(i);
			}
			progressBar.done();
			
			displayFinalStatusMessage(metrics);
		}
	}

	private SQLResults getSQLResults(Bookmark bookmark, Connection connection, MultiSQLServer server, String query) {
			SQLResults results = null;
			try {
				results = server.execute(bookmark, connection, query);
			} catch (SQLException e) {
				QuantumLog.getInstance().error(	"Error Executing: " + query + ":" + e.toString(), e); //$NON-NLS-1$ //$NON-NLS-2$
				SQLExceptionDialog.openException(getShell(), bookmark, e);
			}
			return results;
		}
	

	/**
	 * 
	 */
	private void activateTableView() {
		TableView.getInstance();
	}

	/**
	 * @param sendQueryAsIs true if the query is not to be parsed, and sent as-is to the JDBC driver
	 * @return
	 * @throws CoreException
	 * @throws IOException
	 */
	protected abstract List getQueries(boolean sendQueryAsIs) throws IOException, CoreException;

	/**
	 * @return
	 */
	protected abstract IStatusLineManager getStatusLineManager();

	private void displayFinalStatusMessage(Metrics metrics) {
		String message = Messages.getString(
			BaseExecuteAction.class, 
			"done", //$NON-NLS-1$ 
			metrics.getResults()); 
		if (metrics.hasErrors()) {
			getStatusLineManager().setErrorMessage(
					ImageStore.getImage(ImageStore.STOP), message);
		} else {
			getStatusLineManager().setMessage(message);
		}
	}

	protected Bookmark getBookmark() {
		Bookmark bookmark = null;
		SimpleSelectionDialog dialog = new SimpleSelectionDialog(
				getShell(), 
				Messages.getString(BaseExecuteAction.class, "selectBookmark"), 
				BookmarkCollection.getInstance().getBookmarks(),
				ImageStore.getImage(ImageStore.BOOKMARK));
		if (SimpleSelectionDialog.OK == dialog.open()) {
			IStructuredSelection selection = dialog.getSelection();
			
			bookmark = (Bookmark) selection.getFirstElement();
		}
		return bookmark;
	}
	
	class ExecuteSQL extends Job {
		
		Bookmark bookmark;
		Connection connection;
		MultiSQLServer server;
		List queries;
		List resultsList = new ArrayList(0);
		Metrics metrics = new Metrics();
		SQLException e = null;

		public ExecuteSQL() {
			super("Executing sql...");
			this.setUser(true);
		}

		public void schedule(Bookmark bookmark, Connection connection,
				MultiSQLServer server, List querys) {
			this.bookmark = bookmark;
			this.connection = connection;
			this.server = server;
			this.queries = querys;

			this.schedule();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.IProgressMonitor)
		 */
		protected IStatus run(IProgressMonitor monitor) {
			resultsList.clear();
			e = null;
			metrics = new Metrics();

			monitor.beginTask("queries", queries.size());

			IStatus stats = Status.OK_STATUS;
			SQLResults results = null;

			try {
				for (int i = 0; i < queries.size(); i++) {
					String query = (String) queries.get(i);
					monitor.setTaskName(query);
					if ((query != null) && (query.trim().length() > 0)) {
						results = server.execute(bookmark, connection, query);
						metrics.measure(results);
						if ((results != null) && (results.isResultSet())) {
							resultsList.add(results);
						}
					}
					monitor.worked(i);
				}
			} catch (SQLException eSQL) {
				e = eSQL;
				stats = Status.CANCEL_STATUS;
				PlatformUI.getWorkbench().getDisplay().asyncExec(
						new Runnable() {
							public void run() {
								QuantumLog
										.getInstance()
										.error(
												"Error Executing: " + e.getSQLState() + ":" + e.toString(), e); //$NON-NLS-1$ //$NON-NLS-2$
								SQLExceptionDialog.openException(getShell(),
										bookmark, e);
							}
						});
			}
			monitor.done();
			PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
				public void run() {
					Iterator i = resultsList.iterator();
					System.out.println("ExecuteSQL.run()::asyncExec ");
					while (i.hasNext()) {
						SQLResults results = (SQLResults) i.next();
						SQLResultSetCollection.getInstance().addSQLResultSet(
								(SQLResultSetResults) results);
						bookmark.addQuery(results.getQuery());
					}
					System.out.println("ExecuteSQL.run()::asyncExec table to front");
					TableView.getInstance();// show the view if not present
					displayFinalStatusMessage(metrics);
				}
			});
			return stats;
		}

		private void displayFinalStatusMessage(Metrics metrics) {

			String message = Messages.getString(BaseExecuteAction.class,
					"done", //$NON-NLS-1$ 
					metrics.getResults());
			if (metrics.hasErrors()) {
				getStatusLineManager().setErrorMessage(
						ImageStore.getImage(ImageStore.STOP), message);
			} else {
				getStatusLineManager().setMessage(message);
			}
		}

	}

}
