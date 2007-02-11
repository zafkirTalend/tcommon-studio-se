package com.quantum.sql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import com.quantum.QuantumPlugin;
import com.quantum.log.QuantumLog;
import com.quantum.model.Entity;
import com.quantum.util.QuantumUtil;
import com.quantum.util.connection.Connectable;
import com.quantum.util.connection.JDBCUtil;
import com.quantum.util.sql.SQLInstructionBuilder;
import com.quantum.view.tableview.TableView;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;



/**
 * MultiSQLServer is a Singleton, used  as a interface with the sql drivers.
 * Use MultiSQLServer.getInstance() to get the object.
 */
public class MultiSQLServer {

	private IPropertyChangeListener listener = new IPropertyChangeListener() {
		public void propertyChange(PropertyChangeEvent event) {
			if (TableView.NUMBER_OF_ROWS_PREFERENCE_NAME.equals(event.getProperty())) {
				setNumberOfRowsFromPreferences();
			}
		}
	};
	
	private static MultiSQLServer instance = null;
	private int numberOfRows = 200;
    private MultiSQLServer() {
	       setNumberOfRowsFromPreferences();
	       QuantumPlugin.getDefault().getPreferenceStore().addPropertyChangeListener(this.listener);
    }
    public synchronized static MultiSQLServer getInstance() {
        if (instance == null) {
            instance = new MultiSQLServer();
        }
        return instance;
    }

    public void commit(Connection con) throws SQLException {
        try {
            con.commit();
        } catch (SQLException e) {
            QuantumLog.getInstance().error("Error commiting.", e); //$NON-NLS-1$
            throw e;
        }
    }

    public void rollback(Connection con) throws SQLException {
        try {
            con.rollback();
        } catch (SQLException e) {
            QuantumLog.getInstance().error("Error rolling back.", e); //$NON-NLS-1$
            throw e;
        }
    }

    public void setAutoCommit(Connection con, boolean enabled) {
        QuantumLog log = QuantumLog.getInstance();
        try {
            if (con != null) {
                con.setAutoCommit(enabled);
            } else {
                log.warn("Please connect before setting autocommit"); //$NON-NLS-1$
            }
        } catch (SQLException e) {
            log.error("Error setting autocommit", e); //$NON-NLS-1$
        }
    }

	public SQLResults execute(Connectable connectable, Connection con, Entity entity, String s)
			throws SQLException {
		return execute(connectable, con, entity, s, numberOfRows);
	}

	public SQLResults execute(Connectable connectable, Connection con, String s)
			throws SQLException {
		return execute(connectable, con, null, s, numberOfRows);
	}
	
	public SQLResultSetResults getMetaData(Entity entity, Connection connection) throws SQLException {
		String query = SQLInstructionBuilder.buildSelectAllColumnsNoRows(entity);
		SQLResultSetResults results = null;
		if (connection != null) {
			Statement statement = connection.createStatement();
			try {
				ResultSet set = statement.executeQuery(query);
				try {
					results = SQLMetaDataResults.create(entity.getBookmark(), set, query, entity);
				} finally {
					set.close();
				}
			} finally {
				statement.close();
			}
		}
		return results;
	}
	
	public SQLResults execute(Connectable connectable, Connection con, String sql,
			int numberOfRowsPerPage) throws SQLException {
		return execute(connectable, con, null, sql, numberOfRowsPerPage);
	}

	
	public SQLResults execute(
			Connectable connectable, 
			Connection con,
			Entity entity, 
			String sql,
			int numberOfRowsPerPage)
			throws SQLException {

		long startTime = System.currentTimeMillis();
		QuantumLog log = QuantumLog.getInstance();
		log.info("SQL (" + connectable.getDisplayName() + ") [" + sql + "]"); //$NON-NLS-1$ //$NON-NLS-2$
		Statement statement = con.createStatement();
		try {
			SQLResults results;
			if (statement.execute(sql)) {
				ResultSet set = statement.getResultSet();
				// Here would be the place to get more resultsets if the JDBC driver supports it.
				//boolean moreSets = statement.getMoreResults();
				//log.info(moreSets ? "more" : "nomore");
				try {
					results = SQLStandardResultSetResults.create(set, connectable, sql, entity, numberOfRowsPerPage);
				} finally {
					set.close();
				}
			} else {
				int updates = statement.getUpdateCount();
				results = new SQLUpdateResults(updates);
			}
			log.info("Success: result set displayed"); //$NON-NLS-1$
			if (results != null) {
				results.setTime(System.currentTimeMillis() - startTime);
			}
			results.setQuery(sql);
			return results;
		} finally {
			statement.close();
		}
	}
	
    /**
	 * @param connection
	 * @return
	 * @throws SQLException
	 */
	public String[] getMatchingColumnNames(Connection connection, String schemaName, String tableName, String columnPrefix, boolean qualified)
			throws SQLException {
		Vector temp = new Vector();
		DatabaseMetaData metaData = connection.getMetaData();
		
		if (!metaData.supportsSchemasInTableDefinitions()) schemaName = null;
		ResultSet resultSet = metaData.getColumns(null, schemaName, tableName, (columnPrefix == null) ? null : columnPrefix + "%");
		String columnName = "";
		try {
			while (resultSet.next()) {	
				if (qualified) {
					columnName = resultSet.getString(JDBCUtil.COLUMN_METADATA_TABLE_NAME).trim();
					if (!columnName.equals(""))
						columnName += ".";
				}
				columnName += resultSet.getString(JDBCUtil.COLUMN_METADATA_COLUMN_NAME).trim();
				temp.add(columnName);
				columnName = "";
			}
		} finally {
			resultSet.close();
		}
		return (String[]) (temp.toArray(new String[temp.size()]));
	}
	/**
	 * @param connection
	 * @param schemaName
	 * @param namePrefix
	 * @param types
	 * @param qualified
	 * @return
	 * @throws SQLException 
	 */
	public String[] getMatchingTableNames(Connection connection, String schemaName, String namePrefix, String[] types, boolean qualified) 
	throws SQLException {
        Vector tables = new Vector();
		DatabaseMetaData metaData = connection.getMetaData();
        ResultSet set = null;
		if (types == null) types = new String[] {"TABLE", "VIEW" };
		String regexp = (namePrefix != null) ? namePrefix + "%" : "%";
		if (!metaData.supportsSchemasInTableDefinitions()) schemaName = null;
	    set = metaData.getTables(null, schemaName, regexp, types );
		
        while (set.next()) {
            String tempSchema = set.getString(JDBCUtil.TABLE_METADATA_TABLE_SCHEM);
            tempSchema = (tempSchema == null) ? "" : tempSchema.trim();
            String tableName = set.getString(JDBCUtil.TABLE_METADATA_TABLE_NAME);
            tableName = (tableName == null) ? "" : tableName.trim();

            tables.add ( QuantumUtil.getQualifiedName(tempSchema, tableName) );
        }
        set.close();

		return (String[]) tables.toArray(new String[tables.size()]);
	}
	
    private void setNumberOfRowsFromPreferences() {
        IPreferenceStore store = QuantumPlugin.getDefault().getPreferenceStore();
        numberOfRows = store.getInt(TableView.NUMBER_OF_ROWS_PREFERENCE_NAME);
    }

}