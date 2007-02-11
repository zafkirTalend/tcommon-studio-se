package com.quantum.adapters;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.quantum.Messages;
import com.quantum.actions.BookmarkSelectionUtil;
import com.quantum.model.Bookmark;
import com.quantum.model.Column;
import com.quantum.model.DataType;
import com.quantum.model.DatabaseObject;
import com.quantum.model.ForeignKey;
import com.quantum.model.Schema;
import com.quantum.model.TableMetadata;
import com.quantum.util.QuantumUtil;
import com.quantum.util.connection.NotConnectedException;
import com.quantum.util.sql.TypesHelper;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
//import com.quantum.sql.parser.SQLLexx;
//import com.quantum.sql.parser.MSSQLLexx;
/**
 * @author BC/JHvdV
 */
public class MSSQLServerAdapter extends DatabaseAdapter {

	/**
	 * @param type
	 */
	protected MSSQLServerAdapter() {
		super(AdapterFactory.MS_SQL_SERVER);
	}

	public Map getDefaultConnectionParameters() {
		Map map = new HashMap();
		map.put("port", "1433");
		map.put("hostname", "localhost");
		map.put("dbname", "master");
		return map;
	}

	public String getRemarksQuery(String tableName, String column) {
		String query = "SELECT cast(name as varchar(255)) + ' = ' + cast(value as varchar(255)) FROM ::fn_listextendedproperty (NULL, 'user', ";
		query += "'" + QuantumUtil.getTableName(tableName) + "', 'table', '" + tableName + "', 'column', '" + column + "'";
		return query;
	}

	public String getProceduresQuery( String schema, String pack ) {
		return "select name, 0 from " + schema + ".sysobjects where (xtype='" + pack + "') order by name" ;
	}
	public String getShowPackagesQuery(String userid) {
		return "SELECT DISTINCT xtype FROM " + userid + ".sysobjects where (xtype='P' or xtype = 'FN' or xtype = 'TF')";
	}

//	/**
//     * The default schema for SQL Server is the userid but if that is sa it is dbo.
//     * @see com.quantum.adapters.DatabaseAdapter#getDefaultSchema(java.lang.String)
//     */
//    public String getDefaultSchema(String userid) {
//    	if(userid.toLowerCase().equals("sa")){
//    		userid = "dbo";
//    	}
//        return super.getDefaultSchema(userid);
//    }

	/* (non-Javadoc)
	 * @see com.quantum.adapters.DatabaseAdapter#getProcedureArgumentsQuery(java.lang.String, java.lang.String, java.lang.String, int)
	 */
	public String getProcedureArgumentsQuery( String schema, String pack, String proc, int overload ) {
		if(pack.equals("P"))
		{
			return "select '@RETURN_VALUE', 0, 'int' " + 
			"UNION " +
			"select sc.name, sc.colorder,st.name from " + schema + ".syscolumns sc " +
			"inner join " + schema + ".sysobjects so on " +
			"    so.id = sc.id " +
			"inner join " + schema + ".systypes st on " +
			"    st.xtype = sc.xtype " +
			"where so.name =  '" + proc + "'" +
			"order by 2";
		}else{
			return 
			"select sc.name, sc.colorder,st.name from " + schema + ".syscolumns sc " +
			"inner join " + schema + ".sysobjects so on " +
			"    so.id = sc.id " +
			"inner join " + schema + ".systypes st on " +
			"    st.xtype = sc.xtype " +
			"where so.name =  '" + proc + "'" +
			"order by 2";
		}
	}
	/* (non-Javadoc)
	 * @see com.quantum.adapters.DatabaseAdapter#getCreateStatement(java.lang.String)
	 */
	public String getCreateStatement(String schema, String viewName) {
		// if you use INFORMATION_SCHEMA.VIEWS NULL is returned for view defs>4000 chars
		return 
			"SELECT sp.text from "+ schema + ".sysobjects so " +
			"inner join syscomments sp on " + 
			"	so.id = sp.id " + 
			"inner join sysusers su on " + 
			"	su.uid = so.uid " + 
			/* viewName must not include the schema*/
			"where so.name = '" + viewName + "' " + 
			"and su.name = '" + schema + "' " +
			"and xtype= 'V'";
	}

	public String getTriggersStatement(String schema, String name) {
		return "SELECT so.name, '', '', '', '', '', '', '', '', '', sp.text from sysobjects so  " +
			"inner join " + schema + ".syscomments sp on  " +
			"	so.id = sp.id  " +
			"inner join " + schema + ".sysusers su on  " +
			"	su.uid = so.uid " +
			"inner join " + schema + ".sysobjects t on " +
			"	so.parent_obj = t.id " +
			"where t.name = '" + name + "' " +
			"and su.name = '" + schema +"' " +
			"and so.xtype= 'TR'";
	}
	
	public String getChecksStatement(String schema, String objectName) {
		return "select so.name, sc.text from syscomments sc " +
			"inner join " + schema + ".sysobjects so " +
			"on sc.id = so.id " +
			"inner join " + schema + ".sysobjects t " +
			"on so.parent_obj = t.id " +
			"inner join " + schema + ".sysusers su " +
			"on so.uid = su.uid " +
			"where so.xtype = 'C' " +
			"and t.name = '" + objectName + "' " +
			"and su.name = '" + schema + "'" ;
	}

	public String getProcedureBodyStatement(String schema, String pack, String name, int Overload)
	{
		// MSSQL Server has no packages
		// Overload is an attribute as yet not understood
    	String query = 
			"SELECT sp.text from " + schema + ".sysobjects so " +
			"inner join " + schema + ".syscomments sp on " + 
			"	so.id = sp.id " + 
			"inner join " + schema + ".sysusers su on " + 
			"	su.uid = so.uid " + 
			/* viewName must not include the schema*/
			"where so.name = '" + name + "' " + 
			"and su.name = '" + schema + "' " +
			"and xtype= '" + pack + "'";
		return query;
	}
	
	public String getObjectType(Bookmark bm, String object)
	{
		String query = "SELECT xtype FROM sysobjects WHERE NAME= '" + object + "'";
		try
		{
			Connection con = bm.getConnection();
			Statement stmt = con.createStatement();
			if (this != null && stmt != null && query != null) {
				try {
					stmt.execute(query);
					ResultSet set = stmt.getResultSet();
					try {
						while (set.next()) {
							String ObjectXType = (set.getString(1)).trim();
							if(ObjectXType.equals("C")){
								return DatabaseObject.CHECK_TYPE;
							}else if(ObjectXType.equals("P") || ObjectXType.equals("TF") || ObjectXType.equals("FN")){
								return DatabaseObject.PROCEDURE_TYPE;
							}else if(ObjectXType.equals("S")){
								return DatabaseObject.TABLE_TYPE;
							}else if(ObjectXType.equals("U")){
								return DatabaseObject.TABLE_TYPE;
							}else if(ObjectXType.equals("V")){
								return DatabaseObject.VIEW_TYPE;
							}
						}
					} finally {
						set.close();
					}
				} finally {
					stmt.close();
				}
			}
		}catch (NotConnectedException e){			
		}catch (SQLException e){
		}
		// is it a column?
		query = "SELECT COUNT(*) FROM syscolumns where name = '" + object + "'";
		try
		{
			Connection con = bm.getConnection();
			Statement stmt = con.createStatement();
			if (this != null && stmt != null && query != null) {
				try {
					stmt.execute(query);
					ResultSet set = stmt.getResultSet();
					try {
						while (set.next()) {
							return (set.getInt(1)>0)?DatabaseObject.COLUMN_TYPE:"";
						}
					} finally {
						set.close();
					}
				} finally {
					stmt.close();
				}
			}
		}catch (NotConnectedException e){			
		}catch (SQLException e){
		}
		return "";
	}
//	public SQLLexx getLexer()
//	{
//		return new MSSQLLexx();
//	}
	public String getTableExistsQuery(String name)
	{
		return "SELECT COUNT(*) FROM SYSOBJECTS WHERE (XTYPE ='U' OR XTYPE='V') AND NAME = '"+ name + "'";
	}
	
	public String getColumnExistsQuery(String tname, String cname)
	{
		return "SELECT COUNT(*) FROM SYSCOLUMNS sc INNER JOIN SYSOBJECTS so ON sc.id = so.id WHERE sc.NAME = '" + cname + "' and so.NAME='"+ tname + "'";
	}
	public String getTablesForColumnQuery(String columnName)
	{
		return "select o.name from syscolumns c inner join sysobjects o on c.id = o.id where c.name = '" + columnName + "'";
	}
    public  String buildForeignKeys(Bookmark bookmark, TableMetadata metadata, Schema schema, boolean absoluteReference) 
     throws NotConnectedException, SQLException {
        ForeignKey[] keys = metadata.getImportedKeys();
        if (keys == null || keys.length == 0) {
            return "";
        }
        String foreignKey = "";
        String eol = System.getProperty("line.separator");
        for (int i = 0; i < keys.length; i++) {
            ForeignKey key = keys[i];
            String localTableName = key.getLocalEntityName();
            // If the referenced table is from the same schema, and it's not an absolute reference
            // , we change it to the selected new schema
            String foreignTableName =  key.getForeignEntityName();
            foreignKey += "ALTER TABLE " + bookmark.filterQuoteName(foreignTableName) + eol +
            "   ADD CONSTRAINT " + key.getName() + " FOREIGN KEY ( ";
            for (int j = 0; j < key.getNumberOfColumns(); j++) {
                if (j > 0) {
                    foreignKey += ", ";
                }
                foreignKey += key.getForeignColumnName(j);
            }
            foreignKey += " )" + eol;
            foreignKey += "   REFERENCES " + localTableName + "( ";
            for (int j = 0; j < key.getNumberOfColumns(); j++) {
                if (j > 0) {
                    foreignKey += ", ";
                }
                foreignKey += key.getLocalColumnName(j);
            }
            foreignKey += " );" + eol;
            
        }
        return foreignKey;
    }
    public  String buildPrimaryKey(Bookmark bookmark, String tableName, TableMetadata metadata) throws NotConnectedException, SQLException {
        Column[] columns = metadata.getPrimaryKeyColumns();
        if (columns == null || columns.length == 0) {
            return "";
        }
        String eol = System.getProperty("line.separator");
        String primaryKey = "ALTER TABLE " + bookmark.filterQuoteName(tableName) + eol +
                            "   ADD CONSTRAINT " + QuantumUtil.getTableName(tableName) + "_PK PRIMARY KEY ( ";
        for (int i = 0; i < columns.length; i++) {
            Column column = columns[i];
            if (i > 0) {
                primaryKey += ", ";
            }
            primaryKey += column.getName();
        }
        primaryKey += " );" + eol;
        
        return primaryKey;
    }

    public String buildAlterTable(Bookmark bm, String tableName, String operation, String columnName, com.quantum.editors.graphical.model.Column c) {
        String query = "ALTER TABLE " + tableName + " ";
        Bookmark bookmark = BookmarkSelectionUtil.getInstance().getLastUsedBookmark();
        DataType[] types = null;
//        int javaType = 0;
//        try {
//            types = bookmark.getDataTypes();
//            for (int i = 0; i < types.length; i++) {
//                if(types[i].getDatabaseTypeName().equalsIgnoreCase(c.getType())){
//                    javaType = types[i].getJavaType();
//                    break;
//                }
//            }
//        } catch (NotConnectedException e) {
//        } catch (SQLException e) {
//        }

        if(operation.equalsIgnoreCase("ADD")){
            query += operation + " " + columnName + " " + c.getType() + TypesHelper.formatType(c.getJavaType(), c.getSize(), c.getPrecision());
            if(c.isNullable()){
                query += " NULL";
            }else{
                query += " NOT NULL";
            }
            if(c.isPrimaryKey()){
                query += " CONSTRAINT " + /* no specified name: system will assign one */ " PRIMARY KEY"; 
            }
            // in the future checks and other constraints may be added
        }else if(operation.equalsIgnoreCase("DROP")){
            query += operation + " " + "COLUMN " + columnName;
        }else if(operation.equalsIgnoreCase("ALTER")){
            query += operation + " " + "COLUMN " + columnName + " " + c.getType() + TypesHelper.formatType(c.getJavaType(), c.getSize(), c.getPrecision());
            if(c.isNullable()){
                query += " NULL";
            }else{
                query += " NOT NULL";
            }
            if(c.isPrimaryKey()){
                query += " CONSTRAINT " + /* no specified name: system will assign one */ " PRIMARY KEY"; 
            }
            // in the future checks and other constraints may be added
        }
        return query;
    }
    /**
     * Quotes a string according to the type of the column 
     * @param string to be quoted
     * @param type according to java.sql.Types
     * @return
     */
    public String quote(Bookmark bookmark, String string, int type, String typeString) {
        if (type == java.sql.Types.DATE || type == java.sql.Types.TIMESTAMP) {
            string = string.trim();
            // I want to keep the millis
            return "'" + string + "'";
        }
        // use the default (upper type)
        return super.quote(bookmark, string, type, typeString);
    }
}
