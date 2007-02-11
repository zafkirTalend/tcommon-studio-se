/*
 * Created on Oct 19, 2004
 */
package com.quantum.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.quantum.adapters.DatabaseAdapter;
import com.quantum.util.connection.NotConnectedException;

/**
 * @author lleavitt
 */
public class ProcedureImpl extends DatabaseObjectImpl implements Procedure {

    int     overload;
    Package pack;

    ProcedureArgument[] columns = null;

    public ProcedureImpl(Bookmark bookmark, String schema, Package pack, String name, int overload ) {
        super(bookmark, schema, name, DatabaseObject.PROCEDURE_TYPE );
        this.pack = pack;
        this.overload = overload;
    }


    public Package getPackage() {
        return pack;
    }

    public void setPackage( Package pack ) {
        this.pack = pack;
    }

    public int getOverload() {
        return overload;
    }

    
    private String getPackageName() {
        return pack == null ? null : pack.getName();
    }
    
    public ProcedureArgument getReturnType() {
        loadArguments();
        return columns[0];
    }

    /* (non-Javadoc)
     * @see com.quantum.model.Procedure#getArguments()
     */
    public ProcedureArgument[] getArguments() {
        loadArguments();
        return columns;
    }

    /* (non-Javadoc)
     * @see com.quantum.model.Procedure#getArgument(java.lang.String)
     */
    public ProcedureArgument getArgument(String name) {
        loadArguments();
        for( int i = 1; i < columns.length; i++ )
        {
            if( columns[i].getName().equals( name ) )
                return columns[i];
        }
        return null;
    }


    private void loadArguments()
    {
        if( columns == null )
        {
            List arguments = getArgumentsFromDatabase();
            columns = (ProcedureArgument[]) arguments.toArray( new ProcedureArgument[arguments.size()] ); 
        }
    }

    private List getArgumentsFromDatabase()
    {
        List list = new ArrayList();
        
		try {
			Connection con = getBookmark().getConnection();
			DatabaseAdapter adapter = getBookmark().getAdapter();
			Statement stmt = con.createStatement();

			try {
			    if( adapter != null && stmt != null )
			    {
			        String sql = adapter.getProcedureArgumentsQuery( getSchema(), getPackageName(), getName(), getOverload() );

					if (sql != null )
					{
						stmt.execute(sql);
						ResultSet set = stmt.getResultSet();
						try {
						    while( set.next() )
						    {
						        // ARGUMENT_NAME, POSITION, DATA_TYPE
						        ProcedureArgumentImpl p = 
						            new ProcedureArgumentImpl( 
						                set.getString(1),
						                set.getInt(2),
						                set.getString(3));
						        if( list.isEmpty() && p.getIndex() == 1 )
						            list.add( null );
						        list.add( p );
						    }
						} 
						finally {
							set.close();
						}
					}
			    }
			} finally {
				stmt.close();
			}
		} catch (NotConnectedException e) {
		} catch (SQLException e) {
		}
            
		return list;
    }
    
    public String getBody()
    {
		String body = "";
		try {
			Connection con = getBookmark().getConnection();
			DatabaseAdapter adapter = getBookmark().getAdapter();
			Statement stmt = con.createStatement();
			try {
			    if( adapter != null && stmt != null )
			    {
			        String sql = adapter.getProcedureBodyStatement( getSchema(), getPackageName(), getName(), getOverload() );
	
					if (sql != null )
					{
						stmt.execute(sql);
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
					}else{
                        // if it is not possible to retrieve the data as a query, the adapter may define to return the body/script directly
					    body = adapter.getBody(getBookmark(), getSchema(), getPackageName(), getName(), getOverload() );
                    }
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
