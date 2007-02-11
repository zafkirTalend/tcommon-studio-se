package com.quantum.sql;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.quantum.model.Entity;
import com.quantum.util.connection.Connectable;

/**
 * @author Tom Schneider
 * @author BC Holmes
 */
public abstract class SQLResultSetResults extends SQLResults {
	
	
	class ColumnArrayComparator implements Comparator {

		public int compare(Object arg0, Object arg1) {
			return compare((Column[]) arg0, (Column[]) arg1);
		}
		public int compare(Column[] columns0, Column[] columns1) {
			
			if (columns0 == null && columns1 == null) {
				return 0;
			} else if (columns0 == null) {
				return -1;
			} else if (columns1 == null) {
				return 1;
			} else if (columns0.length < columns1.length) {
				return -1;
			} else if (columns0.length > columns1.length) {
				return 1;
			} else {
				int result = 0;
				for (int i = 0, length = columns1 == null ? 0 : columns1.length; 
						result == 0 && i < length; i++) {
					result = compare(columns0[i], columns1[i]);
				}
				return result;
			}
		}
		/**
		 * @param columns0
		 * @param columns1
		 * @param result
		 * @param i
		 * @return
		 */
		private int compare(Column column0, Column column1) {

			if (column0 == null && column1 == null) {
				return 0;
			} else if (column0 == null) {
				return -1;
			} else if (column1 == null) {
				return 1;
			} else if (column0.getName() == null) {
				return -1;
			} else if (column1.getName() == null) {
				return 1;
			} else if (column0.getName() != null && column1.getName() != null
					&& column0.getName().compareTo(column1.getName()) != 0) {
				return column0.getName().compareTo(column1.getName());
			} else if (column0.getTypeDescription() == null) {
				return -1;
			} else if (column1.getTypeDescription() == null) {
				return 1;
			} else if (column0.getTypeDescription() != null && column1.getTypeDescription() != null
					&& column0.getTypeDescription().compareTo(column1.getTypeDescription()) != 0) {
				return column0.getTypeDescription().compareTo(column1.getTypeDescription());
			} else {
				return column0.getSize() - column1.getSize();
			}
		}
	}
	
	public class Row {
		private final List elements;

		Row(List elements) {
			this.elements = elements;
		}
		
		public Object get(int columnNumber) {
			return (columnNumber > this.elements.size() || columnNumber <= 0) 
					? null 
					: this.elements.get(columnNumber - 1);
		}
		
		public String[] getAsStringArray() {
			String[] resultArray = new String[this.elements.size()];
			int i = 0;
			for (Iterator iter = this.elements.iterator(); iter.hasNext() ; ) {
				String element = iter.next().toString();
				resultArray[i] = element;
				i++;
			}
			return resultArray;
		}
		
		public SQLResultSetResults getResultSet() {
			return SQLResultSetResults.this;
		}
	}
	
	public class Column {
		private final String name;
		private final int type;
		private final int size;
		private final String typeDescription;

		Column(String name, String typeDescription, int type, int size) {
			this.name = name;
			this.typeDescription = typeDescription;
			this.type = type;
			this.size = size;
		}
		public String getName() {
			return this.name;
		}
		public int getSize() {
			return this.size;
		}
		public int getType() {
			return this.type;
		}
		public String getTypeDescription() {
			return this.typeDescription;
		}
	}
	
	private List rows = Collections.synchronizedList(new ArrayList());
	private List columns = Collections.synchronizedList(new ArrayList());
	private String query;
	private Connectable connectable;
	private final Entity entity;
	private String encoding = "";
	
	private FilterSort filterSort = null;
	
	/**
	 * @param entity
	 * @param query2
	 */
	public SQLResultSetResults(String query, Connectable connectable, Entity entity) {
		this.query = query;
		this.connectable = connectable;
		this.entity = entity;
		if (entity != null && entity.getBookmark() != null) {
			setEncoding(entity.getBookmark().getDefaultEncoding());
		}
	}
	public String getColumnName(int columnNumber) {
		Column column = getColumn(columnNumber);
		return column == null ? "" : column.getName();
			
	}
	/**
	 * @param columnNumber
	 * @return
	 */
	protected Column getColumn(int columnNumber) {
		return columnNumber <= this.columns.size() 
					? (Column) this.columns.get(columnNumber-1)
					: null;
	}
	public Column[] getColumns() {
		return (Column[]) this.columns.toArray(new Column[this.columns.size()]);
	}
	protected void setColumns(Column[] columns) {
		Column[] original = getColumns();
		if (new ColumnArrayComparator().compare(original, columns) != 0) {
			this.columns.clear();
			this.columns.addAll(Arrays.asList(columns));
			this.propertyChangeSupport.firePropertyChange("columns", original, columns);
		}
	}
	public Object getElement(int column, int row) {
		return ((Row) rows.get(row - 1)).get(column);
	}
	public int getColumnCount() {
		if (columns.size() > 0) {
			return columns.size();
		}
		return 0;
	}
	public int getRowCount() {
		return rows.size();
	}
	public String[] getColumnNames() {
		List names = new ArrayList();
		for (Iterator i = this.columns.iterator(); i.hasNext();) {
			Column column = (Column) i.next();
			names.add(column.getName());
		}
		return (String[]) names.toArray(new String[names.size()]);
	}
	/**
	 * Returns the resultSet.
	 * @return boolean
	 */
	public boolean isResultSet() {
		return true;
	}
	public Row[] getRows() {
		return (Row[]) rows.toArray(new Row[this.rows.size()]);
	}
	public Iterator getRowIterator() {
		List list = new ArrayList(rows);
		return list.iterator();
	}
	 
	/**
	 * Returns the query.
	 * @return String
	 */
	public String getQuery() {
		return this.query;
	}
	public String getFilteredQuery() {
		if (this.filterSort == null) {
			return this.query;
		} else {
			return this.query + this.filterSort.toString();
		}
	}
	public Connectable getConnectable() {
		return this.connectable;
	}

	public Entity getEntity() {
		return this.entity;
	}
	/**
	 * Returns the resultSet.
	 * @return boolean
	 */
	public boolean isMetaData() {
		return false;
	}
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.propertyChangeSupport.addPropertyChangeListener(listener);
	}
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		this.propertyChangeSupport.removePropertyChangeListener(listener);
	}
	protected abstract void parseResultSet(ResultSet set) throws SQLException;
	
	public void refresh(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		try {
			ResultSet resultSet = statement.executeQuery(getFilteredQuery());
			try {
				parseResultSet(resultSet);
			} finally {
				resultSet.close();
			}
		} finally {
			statement.close();
		}
	}
	
	protected void setRows(Row[] rows) {
		Row[] original = getRows();
		this.rows.clear();
		if (rows != null) {
			this.rows.addAll(Arrays.asList(rows));
		}
		this.propertyChangeSupport.firePropertyChange("rows", original, getRows());
	}
	public String getEncoding() {
		return this.encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	public FilterSort getFilterSort() {
		return this.filterSort;
	}
	public void setFilterSort(FilterSort filterSort) {
		this.filterSort = filterSort;
	}
	
	public String[][] getDataAsStrings() {
		String[][] data = new String[rows.size()][columns.size()];
		for (int i = 0; i < rows.size(); i++) {
			Row row = (Row) rows.get(i);
			String[] rowData = row.getAsStringArray();
			for (int j = 0; j < rowData.length; j++) {
				data[i][j] = rowData[j];
			}
		}
		return data;
	}
}
