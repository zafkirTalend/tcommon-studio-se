/*
 * Created on 08.02.2005
 *
 */
package com.quantum.adapters;


/**
 * @author Julen
 * 
 * This adapter IS NOT a descendent of DatabaseAdapter, because Access has no jdbc driver,
 * ( it uses the JDBC-ODBC bridge ). It's just a convenience class to group some functions
 * to grab Access metadata. It's grouped with the other adapters because the functionality
 * is the same. 
 * This work is stopped, as I cannot access the system tables though ODBC
 */
public class MsAccessAdapter  {
	
//	public static String getCreateStatement(Entity entity) {
//		try {
//			long id = getInternalID(entity);
//		} catch (NotConnectedException e) {
//			return null;
//		} catch (SQLException e) {
//			return null;
//		}
//		return null;
//	}
//	
//	private static long getInternalID(Entity entity) throws SQLException, NotConnectedException {
//		Connection con = entity.getBookmark().getConnection();
//		Statement stmt = con.createStatement();
//		// We shouldn't worry about schemas, in MS Access
//		String query = "SELECT Id FROM MSysObjects WHERE Name = '" + entity.getName() + "'";
//		if ( stmt != null && query != null) {
//			stmt.execute(query);
//			ResultSet set = stmt.getResultSet();
//			try {
//				if (set.next()) {
//					return set.getLong(1);
//				}
//			} finally {
//				set.close();
//			}
//		}
//		// If not found, we throw a exception so the calling functions knows we haven't found the id
//		throw new SQLException();
//
//	}
}
