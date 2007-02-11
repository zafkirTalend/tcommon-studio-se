package com.quantum.sql;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.preference.IPreferenceStore;

import com.quantum.QuantumPlugin;
import com.quantum.model.Entity;
import com.quantum.util.connection.Connectable;
import com.quantum.util.sql.TypesHelper;
import com.quantum.view.tableview.TableView;


/**
 * @author BC
 */
public class SQLStandardResultSetResults extends SQLResultSetResults implements Scrollable {

	/**
	 * Some columns -- especially BLOBS and CLOBS can be very wide.  This variable
	 * sets the maximum width of the column.
	 */
	
    private int maxColumnWidth = 2048;
	
	private boolean hasMore = false;
	private int start = 1;
	private int numberOfRowsPerPage;
	private int totalNumberOfRows = -1;

	private boolean fullMode = false;
	
	/**
	 * @param bookmark
	 * @param query
	 * @param encoding
	 * @param numberOfRowsPerPage
	 */
	protected SQLStandardResultSetResults(
			Connectable connectable, String query, Entity entity,
			int numberOfRowsPerPage) {
		super(query, connectable, entity);
		IPreferenceStore store = QuantumPlugin.getDefault().getPreferenceStore();
	 	
		this.numberOfRowsPerPage = numberOfRowsPerPage;
		this.maxColumnWidth = store.getInt(TableView.MAXIMUM_CHARS_CELL_PREFERENCE_NAME);
	}

	static SQLResultSetResults create(
			ResultSet set, Connectable connectable, 
			String query, Entity entity, int numberOfRows) throws SQLException {

		SQLStandardResultSetResults results = new SQLStandardResultSetResults(
				connectable, query, entity, numberOfRows);
		
		results.parseResultSet(set);
		
		return results;
	}
	
	/**
	 * @param set
	 * @throws SQLException
	 */
	protected void parseResultSet(ResultSet set) throws SQLException {
		int rowCount = 1;
		
		ResultSetMetaData metaData = set.getMetaData();
		int columnCount = metaData.getColumnCount();
		List columns = new ArrayList();
		for (int i = 1; i <= columnCount; i++) {
			columns.add(new Column(
					metaData.getColumnName(i), 
					metaData.getColumnTypeName(i),
					metaData.getColumnType(i),
					metaData.getColumnDisplaySize(i)));
		}
		setColumns((Column[]) columns.toArray(new Column[columns.size()]));
		
		boolean exitEarly = false;
		int firstRow = showAllRecords() ? 0 : this.start;
		int lastRow = showAllRecords() ? Integer.MAX_VALUE : this.start + this.numberOfRowsPerPage - 1;
		List rowList = new ArrayList();
		while (set.next()) {
			boolean disable = this.start < 1 || lastRow < 1;
			if (disable || ((rowCount >= firstRow) && (rowCount <= lastRow))) {
				List row = new ArrayList();
				for (int i = 1, length = columns.size(); i <= length; i++) {
					Object value = null;
					try {
						int type = metaData.getColumnType(i);
						if (type == TypesHelper.BIGINT) {
							value = set.getBigDecimal(i);
						} else if (type == TypesHelper.DECIMAL
								|| type == TypesHelper.NUMERIC) {
							value = set.getBigDecimal(i);
						} else if (type == TypesHelper.INTEGER) {
							value = new Long(set.getLong(i));
						} else if (type == TypesHelper.SMALLINT) {
							value = new Integer(set.getInt(i));
						} else if (type == TypesHelper.DATE) {
							value = new SQLDate(set.getDate(i), set.getString(i));
						} else if (type == TypesHelper.DOUBLE) {
							value = set.getBigDecimal(i);
						} else if (type == TypesHelper.FLOAT) {
							value = set.getBigDecimal(i);
						} else if (type == TypesHelper.TIMESTAMP) {
							value = new SQLTimestamp(set.getTimestamp(i), set.getString(i));
						} else if (type == TypesHelper.TIME) {
							value = new SQLDate(set.getTime(i), set.getString(i));
						} else if (type == TypesHelper.BOOLEAN ||
								type == TypesHelper.BOOLEAN) {
							value = (set.getBoolean(i) ? Boolean.TRUE : Boolean.FALSE);
						} else if (type == TypesHelper.LONGVARBINARY
								|| type == TypesHelper.VARBINARY
								|| type == java.sql.Types.BLOB	) {
							value = getEncodedStringFromBinaryStream(set, getEncoding(), i);
						} else	if (getColumn(i).getSize() < maxColumnWidth) {
							value = getEncodedString(set, getEncoding(), i);
						} else {		
							if ("".equals(getEncoding())) { //$NON-NLS-1$
								value = getStringFromCharacterSteam(set, i);
							} else {
								value = getEncodedStringFromBinaryStream(set, getEncoding(), i);
							}
						}
					} catch (IOException e) {
						value = set.getString(i);
					} catch (RuntimeException e) {
						// hack for mysql which doesn't implement
						// character streams
						value = set.getString(i);
					}
					if (value == null && !set.wasNull()) {
						value = set.getString(i);
					}
					row.add(value == null || set.wasNull() ? SQLNull.SQL_NULL : value); 
				}
				rowList.add(new Row(row));
			}
			rowCount++;
			if (!disable && (rowCount > lastRow)) {
				exitEarly = true;
				break;
			}
		}
		if (exitEarly) {
			this.hasMore = set.next();
		} else {
			this.totalNumberOfRows = Math.max(0, rowCount-1);
			this.hasMore = false;
		}
		setRows((Row[]) rowList.toArray(new Row[rowList.size()]));
	}


	/**
	 * @param set
	 * @param encoding
	 * @param columnNumber
	 * @throws SQLException
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	private String getEncodedStringFromBinaryStream(ResultSet set, String encoding, int columnNumber) 
			throws SQLException, IOException, UnsupportedEncodingException {
		InputStream binaryStream = set.getBinaryStream(columnNumber);
		if (binaryStream != null) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try {
				for (int c = binaryStream.read(), count = 0; 
					c >= 0 && count <= maxColumnWidth; 
					c = binaryStream.read(), count++) {
					baos.write(c);
				}
			} finally {
				binaryStream.close();
			}
			if ("".equals(encoding))
				return new String(baos.toByteArray());
			else
				return new String(baos.toByteArray(), encoding);
		} else {
			return null;
		}
	}


	/**
	 * @param set
	 * @param columnNumber
	 * @throws SQLException
	 * @throws IOException
	 */
	private String getStringFromCharacterSteam(ResultSet set, int columnNumber) 
			throws SQLException, IOException {
		Reader reader = set.getCharacterStream(columnNumber);
		if (reader != null) {
			StringBuffer buffer = new StringBuffer();
			int retVal = reader.read();
			int count = 0;
			while (retVal >= 0) {
				buffer.append((char) retVal);
				retVal = reader.read();
				count++;
				if (count > maxColumnWidth) {
					buffer.append("...>>>"); //$NON-NLS-1$
					break;
				}
			}
			reader.close();
			return buffer.toString();
		} else {
			return null;
		}
	}

	/**
	 * @param set
	 * @param encoding
	 * @param index
	 * @return
	 * @throws SQLException
	 */
	private String getEncodedString(ResultSet set, String encoding, int index) 
			throws SQLException {
		try {
			if (encoding == null || encoding.trim().length() == 0) {
				return set.getString(index); 
			}
			byte[] colBytes = set.getBytes(index);
			if (colBytes == null) return null;
			else return new String(colBytes, encoding);
		} catch (UnsupportedEncodingException e) {
			return set.getString(index);
		}
	}

	public boolean hasMore() {
		return this.hasMore;
	}
	
	public int getTotalNumberOfRows() {
		return this.totalNumberOfRows;
	}
	public void nextPage(Connection connection) throws SQLException {
		if (hasNextPage()) {
			this.start += this.numberOfRowsPerPage;
			refresh(connection);
		}
	}

	/* (non-Javadoc)
	 * @see com.quantum.sql.Scrollable#previousPage(java.sql.Connection)
	 */
	public void previousPage(Connection connection) throws SQLException {
		if (hasPreviousPage()) {
			this.start = Math.max(1, this.start - this.numberOfRowsPerPage);
			refresh(connection);
		}
	}

	/* (non-Javadoc)
	 * @see com.quantum.sql.Scrollable#hasNextPage()
	 */
	public boolean hasNextPage() {
		return this.hasMore && !showAllRecords();
	}

	/* (non-Javadoc)
	 * @see com.quantum.sql.Scrollable#hasPreviousPage()
	 */
	public boolean hasPreviousPage() {
		return this.start > 1 && !showAllRecords();
	}

	public void setFullMode(boolean fullMode) {
		this.fullMode = fullMode;
	}
	public boolean isFullMode() {
		return this.fullMode;
	}

	/* (non-Javadoc)
	 * @see com.quantum.sql.Scrollable#getStart()
	 */
	public int getStart() {
		return getRowCount() == 0 ? 0 : (showAllRecords() ? 1 : this.start);
	}

	/* (non-Javadoc)
	 * @see com.quantum.sql.Scrollable#getEnd()
	 */
	public int getEnd() {
		return showAllRecords() 
			? getRowCount() 
			: this.start + getRowCount() - 1;
	}

	/* (non-Javadoc)
	 * @see com.quantum.sql.Scrollable#getLast()
	 */
	public int getLast() {
		return this.totalNumberOfRows;
	}
	
	
	public void setFilterSort(FilterSort filterSort) {
		super.setFilterSort(filterSort);
		this.start = 1;
	}
	
	private boolean showAllRecords() {
		return (this.numberOfRowsPerPage == 0 || this.fullMode);
	}
}
