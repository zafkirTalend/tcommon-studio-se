package com.quantum.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.quantum.adapters.DatabaseAdapter;
import com.quantum.util.connection.NotConnectedException;

class SequenceImpl extends EntityImpl implements Sequence {
	private boolean metadataLoaded = false;
	private String minValue;
	private String maxValue;
	private String initialValue;
	private String incrementBy;
	private boolean cicled;
	private boolean ordered;
	
	public SequenceImpl(Bookmark bookmark, String schema, String name, boolean isSynonym) {
        super(bookmark, schema, name, SEQUENCE_TYPE, isSynonym);
    }
	/* (non-Javadoc)
	 * @see com.quantum.model.SequenceMetadata#getMinValue()
	 */
	public String getMinValue() throws NotConnectedException, SQLException {
		if (!metadataLoaded) {
			loadMetadata();
		}
		return minValue;
	}
	/* (non-Javadoc)
	 * @see com.quantum.model.SequenceMetadata#getMaxValue()
	 */
	public String getMaxValue() throws NotConnectedException, SQLException {
		if (!metadataLoaded) {
			loadMetadata();
		}
		return maxValue;
	}
	/* (non-Javadoc)
	 * @see com.quantum.model.SequenceMetadata#getInitialValue()
	 */
	public String getInitialValue() throws NotConnectedException, SQLException {
		if (!metadataLoaded) {
			loadMetadata();
		}
		return initialValue;
	}
	/* (non-Javadoc)
	 * @see com.quantum.model.SequenceMetadata#getIncrementBy()
	 */
	public String getIncrementBy() throws NotConnectedException, SQLException {
		if (!metadataLoaded) {
			loadMetadata();
		}
		return incrementBy;
	}
	/* (non-Javadoc)
	 * @see com.quantum.model.SequenceMetadata#isCycled()
	 */
	public boolean isCycled() throws NotConnectedException, SQLException {
		if (!metadataLoaded) {
			loadMetadata();
		}
		return cicled;
	}
	/* (non-Javadoc)
	 * @see com.quantum.model.SequenceMetadata#isOrdered()
	 */
	public boolean isOrdered() throws NotConnectedException, SQLException {
		if (!metadataLoaded) {
			loadMetadata();
		}
		return ordered;
	}
	/**
	 * @throws NotConnectedException 
	 * @throws SQLException 
	 * 
	 */
	private void loadMetadata() throws NotConnectedException, SQLException {
		Connection connection = getBookmark().getConnection();
        DatabaseAdapter adapter = getBookmark().getAdapter();
		Statement stmt = connection.createStatement();
		try {
			if (adapter != null && stmt != null 
					&& adapter.getSequenceMetadataQuery(getSchema(), getName()) != null) {
			
				stmt.execute(adapter.getSequenceMetadataQuery(getSchema(), getName()));
				ResultSet set = stmt.getResultSet();
				try {
					if (set.next()) {
						minValue = set.getString(1);
						maxValue = set.getString(2);
						initialValue = set.getString(3);
						incrementBy = set.getString(4);
						String temp = set.getString(5);
						cicled = (temp != null) ? temp.toUpperCase().equals("TRUE") : false;
						temp = set.getString(6);
						ordered = (temp != null) ? temp.toUpperCase().equals("TRUE") : false;
					}
				} finally {
					set.close();
				}
			}
		} finally {
			stmt.close();
		}
		metadataLoaded = true;
	}
}