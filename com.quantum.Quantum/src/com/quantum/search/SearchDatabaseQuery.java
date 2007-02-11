package com.quantum.search;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.search.ui.ISearchQuery;
import org.eclipse.search.ui.ISearchResult;

import com.quantum.ImageStore;
import com.quantum.adapters.DatabaseAdapter;
import com.quantum.log.QuantumLog;
import com.quantum.model.Bookmark;
import com.quantum.model.Column;
import com.quantum.model.DatabaseObject;
import com.quantum.model.Entity;
import com.quantum.model.Procedure;
import com.quantum.sql.MultiSQLServer;
import com.quantum.sql.SQLStandardResultSetResults;
import com.quantum.sql.SQLTimestamp;
import com.quantum.sql.SQLResultSetResults.Row;
import com.quantum.ui.dialog.ConnectionUtil;
import com.quantum.util.QuantumUtil;
import com.quantum.util.connection.NotConnectedException;
import com.quantum.util.sql.TypesHelper;
import com.quantum.view.bookmark.EntityNode;
import com.quantum.view.bookmark.GroupNode;
import com.quantum.view.bookmark.PackageNode;
import com.quantum.view.bookmark.ProcedureNode;
import com.quantum.view.bookmark.QuickListNode;
import com.quantum.view.bookmark.SchemaNode;
import com.quantum.view.bookmark.TreeNode;

public class SearchDatabaseQuery implements ISearchQuery {

    private ConnectionUtil connectionUtil = new ConnectionUtil();
    private String likeSearch;
    private boolean exactly;
    private String toSearch;
    private String numberToSearch;
    private String dateToSearch;
    private String dateToSearch2;
    private int nItems;
    private boolean isLikeSearch;
    private boolean isNumberSearch;
    private boolean isTextSearch;
    private boolean isDateSearch;
    private List list;
    private ISearchResult r = null;
    
    public SearchDatabaseQuery(List list, String toSearch, String likeSearch,
            String numberToSearch, String dateToSearch, String dateToSearch2, boolean isLikeSearch, boolean isTextSearch,
            boolean isNumberSearch, boolean isDateSearch, boolean exactly, int nItems) {
        super();
        this.toSearch = toSearch;
        this.likeSearch = likeSearch;
        this.numberToSearch = numberToSearch;
        this.dateToSearch = dateToSearch;
        this.dateToSearch2 = dateToSearch2;
        this.isLikeSearch = isLikeSearch;
        this.isTextSearch = isTextSearch;
        this.isNumberSearch = isNumberSearch;
        this.isDateSearch = isDateSearch;
        this.exactly = exactly;
        this.list = list;
        this.nItems = nItems;
    }

    public IStatus run(IProgressMonitor monitor)
            throws OperationCanceledException {
        monitor.beginTask("Searching...", nItems);
        try {
            handleList(list, monitor);
        } catch (NotConnectedException e) {
        } catch (SQLException e) {
        } catch (Exception e) {
        } finally {
            monitor.done();
        }
        return new Status(IStatus.OK, "Quantum", IStatus.OK, "", null);
    }

    public String getLabel() {
        return null;
    }

    public boolean canRerun() {
        return false;
    }

    public boolean canRunInBackground() {
        return true;
    }

    public ISearchResult getSearchResult() {
        return r; // this is the hack that is necessary to get results...
    }

    private void handleEntity(Entity entity) {
        // a table or a view
        Connection connection = connectionUtil.getConnection(entity
                .getBookmark(), null);
        if (connection != null) {
            String select = null;
            String query = null;
            try {
                Row[] rows = MultiSQLServer.getInstance().getMetaData(entity,
                        connection).getRows();
                if (rows != null) {
                    select = "SELECT * "; // this will become a field
                    // list later on
                    query = makeQuery(rows, entity);
                    if (query.endsWith(" WHERE ") == false) {
                        SQLStandardResultSetResults results = (SQLStandardResultSetResults) MultiSQLServer
                                .getInstance().execute(entity.getBookmark(),
                                        connection, entity, select + query);
                        // we do not display the tables, instead we use our
                        // SQLSearchResultsView
                        addToSearchResults(entity, results, rows, connection);
                    }
                }
            } catch (SQLException e) {
                if(isDateSearch){
                    // replace {ts by {d
                    query = query.replaceAll("\\u007bts", "{d");
                    try {
                        Row[] rows2 = MultiSQLServer.getInstance().getMetaData(
                                entity, connection).getRows();
                        if (rows2 != null) {
                            select = "SELECT * "; // this will become a field
                            // list later on
                            SQLStandardResultSetResults results = (SQLStandardResultSetResults) MultiSQLServer
                                    .getInstance().execute(
                                            entity.getBookmark(), connection,
                                            entity, select + query);
                            // we do not display the tables, instead we use our
                            // SQLSearchResultsView
                            addToSearchResults(entity, results, rows2,
                                    connection);
                        }
                    } catch (Exception e2) {
                        // replace {d by {t
                        query = query.replaceAll("\\u007bd", "{t");
                        try {
                            Row[] rows3 = MultiSQLServer.getInstance().getMetaData(
                                    entity, connection).getRows();
                            if (rows3 != null) {
                                select = "SELECT * "; // this will become a field
                                // list later on
                                SQLStandardResultSetResults results = (SQLStandardResultSetResults) MultiSQLServer
                                        .getInstance().execute(
                                                entity.getBookmark(),
                                                connection, entity,
                                                select + query);
                                // we do not display the tables, instead we use
                                // our
                                // SQLSearchResultsView
                                addToSearchResults(entity, results, rows3,
                                        connection);
                            }
                        } catch (Exception e3) {
                            QuantumLog.getInstance().error(e3.getMessage());
                        }
                    }
                }else{
                    QuantumLog.getInstance().error(e.getMessage());
                }
            }
        }
    }

    private String makeQuery(Row[] rows, Entity entity)
    {
        // build a query based on the user input
        String query = " FROM " + entity.getQuotedTableName() + " WHERE ";
        for (int i = 0; i < rows.length; i++) {
            query = query
                    + addToClause((String) rows[i].get(1), (String) rows[i]
                            .get(2), (Integer) rows[i].get(7), entity);
        }
        if (query.endsWith(" OR ")) {
            query = query.substring(0, query.length() - 4);
        }
        return query;
    }

    private String addToClause(String columnName, String typeName,
            Integer type, Entity entity)
    {
        int iType;
        String operator = "=";

        iType = type.intValue();
        String clause = "";
        Bookmark bookmark = entity.getBookmark();
        if (bookmark == null)
            return "";
        DatabaseAdapter adapter = bookmark.getAdapter();
        if (adapter == null)
            return "";

        // If we are searching for text and the type of the column is not text,
        // go back.
        // Some databases allow LIKE searching in numeric columns, but we'll leave that for the moment
        if ((isTextSearch || isLikeSearch) && !TypesHelper.isText(iType))
            return "";
        // If we are searching for a number and the type of the column is not
        // numeric, go back
        if (isNumberSearch && !TypesHelper.isNumeric(iType))
            return "";
        // If we are searching for a date and the type of the column is not
        // time related, then skip
        if(isDateSearch && !TypesHelper.isDate(iType))
            return "";
        
        columnName = bookmark.filterQuoteName(columnName);
        // if we are going to search with "=",check that the column type is
        // fully searchable,
        short searchable = adapter.typeSearchable(bookmark, typeName);
        // If we are searching wirh a regexp, or the column type is only
        // searchable with LIKE, we'll use LIKE
        if (isLikeSearch || searchable == DatabaseMetaData.typePredChar) {
            operator = " LIKE ";
        } else {
            // if the type cannot be searched at all, this has no place in the
            // WHERE clause
            if (searchable == DatabaseMetaData.typePredNone)
                return "";
        }
        if (operator.equals(" LIKE ")) {
            // if we are going to use LIKE, check that the type allows LIKE
            // searchs.
            if (searchable != DatabaseMetaData.typePredChar
                    && searchable != DatabaseMetaData.typeSearchable)
                return "";
        }
        if (isTextSearch || isLikeSearch) {
            // We use the escape function LCASE
            if (!exactly) {
                if (bookmark.isStringFunction("LCASE")) {
                    clause = "( {fn LCASE("
                            + bookmark.filterQuoteName(columnName) + ")}";
                }
            } else {
                clause = "(" + bookmark.filterQuoteName(columnName);
            }
            // The operator can be "=" or "LIKE"
            clause += operator;

            clause += adapter.quote(bookmark, (isLikeSearch ? likeSearch
                    : toSearch), iType, typeName);
        } else if (isNumberSearch){
            clause = "(" + bookmark.filterQuoteName(columnName) + "="
                    + numberToSearch; // when a text search is asked we end up here somehow.
        } else if (isDateSearch){
            // we start with a timestamp escape sequence and change those to date or time when the
            // conversion throws an exception.
            if(dateToSearch2.equals("")){
                // exact match
                clause = "(" + bookmark.filterQuoteName(columnName) + operator;
                clause += "{ts " + adapter.quote(bookmark, dateToSearch, iType, typeName) + "}";
            }else{
                // between
                clause = "(" + bookmark.filterQuoteName(columnName) + ">=";
                clause += "{ts " + adapter.quote(bookmark, dateToSearch, iType, typeName) + "}";
                clause += " AND " + bookmark.filterQuoteName(columnName) + "<=";
                clause += "{ts " + adapter.quote(bookmark, dateToSearch2, iType, typeName) + "}";
            }
        }
        clause += ") OR ";
        return clause;
    }

    private void handleList(List list, IProgressMonitor pm)
            throws NotConnectedException, SQLException, InterruptedException {
        // Iterate the given list
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            Object object = iter.next();
            // If it's a DbObject, we can directly extract the metadata
            if (object instanceof EntityNode) {
                Entity entity = ((EntityNode) object).getEntity();
                pm.subTask(entity.getName());
                handleEntity(entity);
                if (pm.isCanceled()) {
                    throw new InterruptedException();
                }
                pm.worked(1);
            }else if (object instanceof ProcedureNode) {
                ProcedureNode p = (ProcedureNode) object;
                pm.subTask(p.getName());
                handleProcedure(p.getProcedure());
                if (pm.isCanceled()) {
                    throw new InterruptedException();
                }
                pm.worked(1);
            } else if (object instanceof GroupNode
                    || object instanceof SchemaNode
                    || object instanceof QuickListNode
                    || object instanceof PackageNode) {
                // if it's a group of nodes, iterate the group of nodes
                TreeNode groupNode = (TreeNode) object;
                Object[] children = groupNode.getChildren();
                for (int i = 0; i < children.length; i++) {
                    if (children[i] instanceof EntityNode) {
                        Entity entity = ((EntityNode) children[i]).getEntity();
                        pm.subTask(entity.getName());
                        handleEntity(entity);
                        if (pm.isCanceled()) {
                            throw new InterruptedException();
                        }
                        pm.worked(1);
                    } else if (children[i] instanceof ProcedureNode) {
                        ProcedureNode p = (ProcedureNode) children[i];
                        pm.subTask(p.getName());
                        handleProcedure(p.getProcedure());
                        pm.worked(1);
                    } else if (children[i] instanceof PackageNode) {
                        handleList(Arrays.asList(new PackageNode[] { (PackageNode) children[i] }), pm);
                    } else if (children[i] instanceof GroupNode) {
                        // If it's another group, recurse on it
                        handleList(Arrays.asList(new GroupNode[] { (GroupNode) children[i] }), pm);
                    }
                }
            }
        }
    }

    private void handleProcedure(Procedure p)
    {
        // Option: if they hit, change to sql files and report on the search result page.
        // Other option: only check those that are already saved as files.
        // When to clean up?
        
        // hit count is intesting...
        String s = p.getBody();
        if (isLikeSearch) {
            // probably change SQL regex to something more basic...
            if (s.matches(likeSearch)) {
                SearchResults.getInstance().addToResults("Procedure",
                        p.getQualifiedName(), ImageStore.PROCEDURE,
                        (DatabaseObject) p, 1);
            }
        } else if (isTextSearch || isNumberSearch) {
            int offset = 0, cnt = 0, answer;
            while (offset < s.length()) {
                answer = s.indexOf(toSearch, offset);
                if (answer != -1) {
                    cnt++;
                    offset = answer + 1;
                }else{
                    break;
                }
            }
            if (cnt > 0) {
                SearchResults.getInstance().addToResults("Procedure",
                        p.getQualifiedName(), ImageStore.PROCEDURE,
                        (DatabaseObject) p, cnt);
            }
        }
    }
    
    private void addToSearchResults(DatabaseObject dbo, SQLStandardResultSetResults results,
            Row[] rows, Connection connection) {
        if ((results != null && results.isResultSet())) {
            Integer type;
            int iType;
            // Now loop over the results to find the column containing the hit
            int nCols;
            int nRows;
            int i, j;
            String value;
            boolean ok = true;
            while (ok) {
                nCols = results.getColumnCount();
                nRows = results.getRowCount();
                for (i = 0; i < nRows; i++) {
                    for (j = 0; j < nCols; j++) {
                        value = results.getElement(j + 1, i + 1).toString();
                        type = (Integer) rows[j].get(7);// from the metadata
                        // results
                        iType = type.intValue();
                        if (isTextSearch||isLikeSearch) {
                            if (TypesHelper.isText(iType)) {
                                if (isLikeSearch) {
                                    if (value
                                            .matches(QuantumUtil
                                                    .convertLikeToJavaRegexp(likeSearch))) {
                                        String primaryKey = getPrimaryKey(dbo.getBookmark(),
                                                results, i + 1, iType);
                                        SearchResults
                                                .getInstance()
                                                .addToResults(
                                                        results
                                                                .getEntity()
                                                                .getQualifiedName(),
                                                        results
                                                                .getColumnName(j + 1),
                                                        value, primaryKey, dbo);
                                    }
                                } else {
                                    if (!exactly)
                                        value = value.toLowerCase();
                                    if (value.equals(toSearch)) {
                                        String primaryKey = getPrimaryKey(dbo.getBookmark(),
                                                results, i + 1, iType);
                                        SearchResults
                                                .getInstance()
                                                .addToResults(
                                                        results
                                                                .getEntity()
                                                                .getQualifiedName(),
                                                        results
                                                                .getColumnName(j + 1),
                                                        value, primaryKey, dbo);
                                    }
                                }
                            }
                        } else if(isNumberSearch){
                            if (TypesHelper.isNumeric(iType)) {
                                if (value.equals(numberToSearch)) {
                                    String primaryKey = getPrimaryKey(dbo.getBookmark(),results,
                                            i + 1, iType);
                                    SearchResults.getInstance().addToResults(
                                            results.getEntity()
                                                    .getQualifiedName(),
                                            results.getColumnName(j + 1),
                                            value, primaryKey, dbo);
                                }
                            }
                        } else if(isDateSearch){
                            if(TypesHelper.isDate(iType)){
                                if(dateToSearch2.equals("")){ 
                                    if(value.equals(dateToSearch)){
                                        String primaryKey = getPrimaryKey(dbo.getBookmark(),results,
                                                i + 1, iType);
                                        SearchResults.getInstance().addToResults(
                                                results.getEntity()
                                                        .getQualifiedName(),
                                                results.getColumnName(j + 1),
                                                value, primaryKey, dbo);
                                    }
                                }else{
                                    // between
                                    // so now we come to the point where our database values need to be compared
                                    // to the strings are user so painstakingly entered.
                                    // It was already some kind of miracle that the exact match with millis worked... 
                                    Timestamp t=null;
                                    try{
                                        // where do the millis go...?
                                        t = (Timestamp)((SQLTimestamp) results.getElement(j + 1, i + 1)).getDate();
                                    }catch(Exception e3)
                                    {}
                                    // try timestamps first
                                    Timestamp t1 = new Timestamp(0);
                                    Timestamp t2 = new Timestamp(0);
                                    try{
                                        t1 = Timestamp.valueOf(dateToSearch);
                                        if(!dateToSearch2.equals("")){
                                            try
                                            {
                                                t2 = Timestamp.valueOf(dateToSearch2);
                                                if(t.getTime()>=t1.getTime() && t.getTime()<=t2.getTime()){
                                                    String primaryKey = getPrimaryKey(dbo.getBookmark(),results,
                                                            i + 1, iType);
                                                    SearchResults.getInstance().addToResults(
                                                            results.getEntity()
                                                                    .getQualifiedName(),
                                                            results.getColumnName(j + 1),
                                                            value, primaryKey, dbo);
                                                }
                                            }catch(Exception e2){
                                                int qq=3;
                                            }
                                        }
                                    }catch(Exception e){
                                        // and if that fails try dates. With this we maybe do not the radio buttons...
                                        Date d1 = new Date(0);
                                        Date d2 = new Date(0);
                                        try{
                                            d1 = Date.valueOf(dateToSearch);
                                            if(!dateToSearch2.equals("")){
                                                try
                                                {
                                                    d2 = Date.valueOf(dateToSearch2);
                                                    if(t.getTime()>=d1.getTime() && t.getTime()<=d2.getTime()){
                                                        String primaryKey = getPrimaryKey(dbo.getBookmark(),results,
                                                                i + 1, iType);
                                                        SearchResults.getInstance().addToResults(
                                                                results.getEntity()
                                                                        .getQualifiedName(),
                                                                results.getColumnName(j + 1),
                                                                value, primaryKey, dbo);
                                                    }
                                                }catch(Exception e2){
                                                }
                                            }
                                        }catch(Exception e4){
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (results.hasMore()) {
                    try {
                        results.nextPage(connection);
                        ok = true;
                    } catch (SQLException e) {
                        ok = false;
                    }
                } else {
                    ok = false;
                }
            }
        }
    }

    private String getPrimaryKey(Bookmark bookmark, SQLStandardResultSetResults results, int row, int iType) {
        String PK = "";
        try {
            Column[] pks = results.getEntity().getPrimaryKeyColumns();
            if (pks.length != 0) {
                // if there are no primary keys we cannot distinguish between
                // rows
                for (int i = 0; i < pks.length; i++) {
                    PK = PK
                            + bookmark.filterQuoteName(pks[i].getName())
                            + "="
                            + bookmark.getAdapter().quote(bookmark, results.getElement(pks[i].getPosition(), row).toString(), iType, null)
                                     + " AND ";
                }
                if (PK.endsWith(" AND ")) {
                    PK = PK.substring(0, PK.length() - 5);
                }
            }
        } catch (NotConnectedException e) {
        } catch (SQLException e) {
        } catch (Exception e) {
        }
        PK = "(" + PK + ")";

        return PK;
    }
}
