/*
 * Created on 06.02.2005
 *
 * The methods in this class are meant to be used by external plug-ins.
 * So the definitions and interfaces are to be maintained as far as possible
 * Changes done to the underlying functions should ideally not affect them. 
 * In general, Quantum code should not use these functions but the underlying ones.
 * 
 * It's generaly safer to maintain compatibility, that the external plugins don't use
 * internal Quantum objects, so if possible generic java types should be asked for and returned.
 */
package com.quantum;

import java.sql.Connection;
import java.sql.SQLException;

import com.quantum.adapters.DatabaseAdapter;
import com.quantum.model.Bookmark;
import com.quantum.model.BookmarkCollection;
import com.quantum.model.Schema;
import com.quantum.sql.MultiSQLServer;
import com.quantum.sql.SQLResultSetCollection;
import com.quantum.sql.SQLResultSetResults;
import com.quantum.sql.SQLResults;
import com.quantum.ui.dialog.ConnectionUtil;
import com.quantum.util.connection.NotConnectedException;
import com.quantum.util.xml.XMLRenderer;
import com.quantum.view.SQLQueryView;

import org.eclipse.swt.widgets.Shell;
import org.w3c.dom.Node;

/**
 * @author Julen
 *
 */
public class ExternalInterface {

    /**
	 * @return An array of strings with a list of the names of the bookmarks 
	 * 			defined in the Quantum Plugin, in no particular order. There
	 * 			should be no duplicates, although case-sensitiveness apply.
	 */
	public static String[] getBookmarkNames() {
		return BookmarkCollection.getInstance().getBookmarkNames();		
    }
	
	/**
	 * @param bookmarkName The name of the bookmark
	 * @return true if the Bookmark name exists in Quantum, false if not. Search is case-sensitive.
	 */
	public static boolean isBookmarkInQuantum(String bookmarkName) {
		return (BookmarkCollection.getInstance().find(bookmarkName) != null);
	}
	
	/**
	 * This function tells us if the bookmark is connected. A non connected bookmark cannot give
	 * us information like the table names.
	 * @param bookmarkName The name of the bookmark
	 * @return true if the bookmark is connected, false if not.
	 */
	public static boolean isBookmarkConnected(String bookmarkName) {
		Bookmark bookmark = BookmarkCollection.getInstance().find(bookmarkName);
		if (bookmark == null) return false;
		return bookmark.isConnected();
	}
	
	/**
	 * @param bookmarkName	The name of the bookmark
	 * @param shell	A shell object to communicate with the user interface. 
	 * 				That's needed because the connection can ask the user for a password.
	 * @return	The new connection if successful, null if not.
	 */
	public static Connection connectBookmark(String bookmarkName, Shell shell) {
		Bookmark bookmark = BookmarkCollection.getInstance().find(bookmarkName);
		if (bookmark == null) return null;
		ConnectionUtil connectionUtil = new ConnectionUtil();
        return connectionUtil.connect(bookmark, shell);
	}
	
	/**
	 * Returns an array of strings with a list of objects of the database refered by a bookmark. You should check that the bookmark is 
	 * connected using the <code>isBookmarkConnected()</code> function, as nothing will be returned if not.
	 * @param bookmarkName		The name of the bookmark 
	 * @param schema			The name of the schema to limit the search. Null if we want all the schemas.
	 * @param types				The types to query the database for. Usually "TABLE", "VIEW", "SEQUENCE", "PROCEDURE"
	 * @param tableNamePrefix	An optional prefix to select the tables. Null if we want all.
	 * @return An array of Strings with the names of the objects. Null if the bookmarkString does not match any bookmark, or other errors.
	 */
	public static String[] getDatabaseObjectNames(String bookmarkName, String schema, String[] types, String tableNamePrefix) {
		Bookmark bookmark = BookmarkCollection.getInstance().find(bookmarkName);
		if (bookmark == null || !bookmark.isConnected()) return null;
		try {
			bookmark.getObjectsForSchema(new Schema(schema), types[0]);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @param schemaName	Optional schema name. If null, search all schemas. If empty string, search tables without schema.
	 * @param bookmarkName	Bookmark name. The name of an existing and connected bookmark in Quantum. 
	 * 						If not connected throws NotConnectedException.
	 * 						If not existing returns null.
	 * @param columnPrefix	Optional column prefix. Will return only those columns that begin with the prefix.
	 * 						If null or empty string return all columns
	 * @param tableName		Optional table name. If null will return all columns.  
	 * @param qualified		true if the column names must be returned qualified by its table name, in the form "table.column".
	 * @return				An array of the names of the columns that match the columnPrefix, in the specified table and schema if not null.
	 * 						null if the bookmark does not exist, empty array if there are no columns that match the conditions
	 * @throws NotConnectedException 	If the bookmark is not connected
	 */
	public static String[] getMatchingColumnNames(String schemaName, String bookmarkName, String columnPrefix , String tableName, boolean qualified )
	throws NotConnectedException{
		Bookmark bookmark = BookmarkCollection.getInstance().find(bookmarkName);
		if (bookmark == null) return null;
		Connection connection = bookmark.getConnection();
		try {
			return MultiSQLServer.getInstance().getMatchingColumnNames(connection, schemaName, tableName, columnPrefix, qualified);
		} catch (SQLException e) {
		}
		return new String[0];
	}

	/**
	 * @param schemaName	Optional schema name. If null, search all schemas. If empty string, search tables without schema.
	 * @param bookmarkName	Bookmark name. The name of an existing and connected bookmark in Quantum. 
	 * 						If not connected throws NotConnectedException.
	 * 						If not existing returns null.
	 * @param namePrefix	Optional column prefix. Will return only those columns that begin with the prefix.
	 * 						If null or empty string return all columns
	 * @param types			String array with the types to select. Typical types are "TABLE", "VIEW". If null, it's same as
	 * 						using <code>new String[] {"TABLE", "VIEW"}</code> 
	 * @param qualified		true if the table names must be returned qualified by its schema name, in the form "schema.table".
	 * @return				An array of the names of the tables that match the namePrefix, in the specified schema if not null.
	 * 						null if the bookmark does not exist, empty array if there are no tables that match the conditions
	 * @throws NotConnectedException	If the bookmark is not connected
	 */
	public static String[] getMatchingTableNames(String schemaName, String bookmarkName, String namePrefix , String[] types, boolean qualified  )
	throws NotConnectedException{
		Bookmark bookmark = BookmarkCollection.getInstance().find(bookmarkName);
		if (bookmark == null) return null;
		Connection connection = bookmark.getConnection();
		try {
			return MultiSQLServer.getInstance().getMatchingTableNames(connection, schemaName, namePrefix, types, qualified);
		} catch (SQLException e) {
		}
		return new String[0];
	}

	/**
	 * Displays a table or view in the Table View of Quantum
	 * @param bookmarkName	The name of the bookmark where the table or view is
	 * @param tableName		the name of the table or view
	 * @throws NotConnectedException 	If the bookmark is not connected.
	 * @throws SQLException 	If any error accessing the database
	 */
	public static void displayTable(String bookmarkName, String tableName) throws NotConnectedException, SQLException {
		Bookmark bookmark = BookmarkCollection.getInstance().find(bookmarkName);
		if (bookmark == null) return;
		Connection connection = bookmark.getConnection();
		DatabaseAdapter adapter = bookmark.getAdapter();
		String query = adapter.getTableQuery(bookmark.filterQuoteName(tableName));
		
		SQLResults results = MultiSQLServer.getInstance().execute(
				bookmark, 
				connection,
				null,
				query);
		
		if (results != null && results.isResultSet()) {
			SQLResultSetCollection.getInstance().addSQLResultSet((SQLResultSetResults) results);
		}
		
		
	}
	
	/**
	 * @param node	The org.w3c.dom.Node to be converted to a string
	 * @return	A string with the pretty-printed text of the XML Node
	 */
	public static String xmlToString(Node node) {
		return XMLRenderer.render(node);
	}
	
	/**
	 * @return The number of result sets displayed in the Table View
	 */
	public static int getNumberOfResultSets() {
		return SQLResultSetCollection.getInstance().getResultSets().length;
	}
	
	/**
	 * @param i	The index (from 0 to getNumberOfResultSets()-1) of the result set we want the data from
	 * @return	A two-dimensional array of Strings holding the data displayed in the Table View for that result set.
	 * 			null if the param i is out of bounds.
	 * 			The resulting matrix has the data stored like data[row][column], both 0-based.
	 * 			For example, to get the value of the second column of the tenth row or the first displayed recordset, we could use:
	 * 		
	 * 				String[][] data = ExternalInterface.getDataFromResultSet(0):
	 * 				String value = data[9][1];
	 * 
	 * 			Remember that the data returned is the data displayed, so if you cannot see the data, it won't be available.
	 * 			To get all the data from a recordset, use the "Show All Table Rows" from the Table View, and then get the string array
	 */
	public static String[][] getDataFromResultSet(int i) {
		SQLResultSetResults[] resultSets =  SQLResultSetCollection.getInstance().getResultSets();
		if ( i < 0 || i >= resultSets.length) {
			return null;
		} else {
			return resultSets[i].getDataAsStrings();
		}
	}
	
	/**
	 * Returns the selected text in the query view, or all the text if there is nothing selected.
	 * Note that if the query view is no open in the workspace, this function will open it, which is
	 * possibly a significant side effect.
	 * @return The text in the query view. Only the selected text if there is something selected.
	 * 			
	 */
	public static String getTextFromQueryView() {
		String result = "";
		SQLQueryView queryView = SQLQueryView.getInstance();
		if (queryView != null) {
			result = queryView.getQuery();
		}
		return result;	
	}
}
