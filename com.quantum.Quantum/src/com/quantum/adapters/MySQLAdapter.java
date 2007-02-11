package com.quantum.adapters;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.quantum.actions.BookmarkSelectionUtil;
import com.quantum.model.Bookmark;
import com.quantum.util.connection.NotConnectedException;



public class MySQLAdapter extends GenericAdapter {

	/**
	 * @param type
	 */
	protected MySQLAdapter() {
		super(AdapterFactory.MYSQL);
	}
	public Map getDefaultConnectionParameters() {
		Map map = new HashMap();
		map.put("port", "3306");
		map.put("hostname", "localhost");
		return map;
	}
	
	public StatementExplainer getStatementExplainer() {
		return new SimpleStatementExplainer("EXPLAIN ");
	}

    public String getDefaultSchema(String userid) {
        String body = "";
        try {
            Connection con = BookmarkSelectionUtil.getInstance().getLastUsedBookmark().getConnection();
            Statement stmt = con.createStatement();
            try {
                stmt.execute("SELECT DATABASE();");
                ResultSet set = stmt.getResultSet();
                try {
                    while( set.next() )
                    {
                        body += set.getString(1);
                    }
                } 
                finally {
                    set.close();
                }
            } finally {
                stmt.close();
            }
        } catch (NotConnectedException e) {
        } catch (SQLException e) {
        }
        return body;
    }

    public String getTableExistsQuery(String name)
    {
        return "SELECT COUNT(*) FROM Information_Schema.TABLES WHERE TABLE_NAME = '"+ name + "'"; // this should include the database === schema
    }

    public String getColumnExistsQuery(String tname, String cname)
    {
        return "SELECT COUNT(*) FROM Information_Schema.COLUMNS WHERE TABLE_NAME = '" + tname + "' and COLUMN_NAME='"+ cname + "'"; // this should include the database === schema
    }

    public String getProceduresQuery( String schema, String pack ) {
        return "select routine_name, 0 from information_schema.routines where routine_schema = '" + schema + "' and routine_type='" + pack + "' order by routine_name" ;
    }
    
    public String getShowPackagesQuery(String userid) {
        return "SELECT DISTINCT routine_type from information_schema.ROUTINES ORDER BY ROUTINE_TYPE";
    }

    // Not supported yet...
    public String getProcedureArgumentsQuery( String schema, String pack, String proc, int overload ) {
        // we need three fields, but mysql returns all of them in the first, so we add two informative fields.. 
        // The second one has to be an int
        return "SELECT param_list, 0, comment FROM  mysql.proc WHERE db = '" + schema +"' AND name='" + proc + "'";
    }

    public String getProcedureBodyStatement(String schema, String pack, String name, int Overload)
    {
        return null;
    }

    public String getBody(Bookmark bm, String schema, String pack, String name, int Overload)
    {
        String query = "";
        if(pack.equals("PROCEDURE")){
            query = "SHOW CREATE PROCEDURE " + name;
        }else if(pack.equals("FUNCTION")){
            query = "SHOW CREATE FUNCTION " + name;
        }else{
            return "";
        }
   
        String body = "";
        try {
            Connection con = bm.getConnection();
            Statement stmt = con.createStatement();
            try {
                stmt.execute(query);
                ResultSet set = stmt.getResultSet();
                try {
                    while( set.next() )
                    {
                        body += set.getString(3);// depends on MySQL version?
                    }
                } 
                finally {
                    set.close();
                }
            } finally {
                stmt.close();
            }
        } catch (NotConnectedException e) {
        } catch (SQLException e) {
        }
        return body;

    }
}