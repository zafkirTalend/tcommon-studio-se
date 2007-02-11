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
public class PackageImpl 
extends DatabaseObjectImpl implements Package {
    
    Procedure[] procedures = null;
    
    public PackageImpl(Bookmark bookmark, String schema, String name ) {
        super(bookmark, schema, name, DatabaseObject.PACKAGE_TYPE );
    }

    public Procedure[] getProcedures() {
        loadProcedures();
        return procedures;
    }

    private void loadProcedures()
    {
        if( procedures == null )
        {
            List procs = getProcsFromDatabase();
            procedures = (Procedure[]) procs.toArray( new Procedure[procs.size()] ); 
        }
    }

    private List getProcsFromDatabase()
    {
        List list = new ArrayList();
        
		try {
			Connection con = getBookmark().getConnection();
			DatabaseAdapter adapter = getBookmark().getAdapter();
			Statement stmt = con.createStatement();

			try {
			    if( adapter != null && stmt != null )
			    {
			        String sql = adapter.getProceduresQuery( getSchema(), getName() );
			    
					if (sql != null )
					{
						stmt.execute(sql);
						ResultSet set = stmt.getResultSet();
						try {
						    while( set.next() )
						    {
						        // OBJECT_NAME, nvl( OVERLOAD, 0 )
						        ProcedureImpl p = 
						            new ProcedureImpl(
						                    this.getBookmark(),
						                    this.getSchema(),
						                    this, 
						                    set.getString(1),
						                    set.getInt(2) );
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

}
