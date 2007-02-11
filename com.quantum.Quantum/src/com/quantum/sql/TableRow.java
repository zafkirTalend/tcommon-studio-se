package com.quantum.sql;

import com.quantum.model.Bookmark;
import com.quantum.model.Entity;
import com.quantum.util.StringMatrix;

public class TableRow {
	private String[] columnNames;
	private Bookmark bookmark;
	private String table;
    private Entity entity;
    private StringMatrix fullTableData;
    
    public TableRow(Entity entity, Bookmark bookmark, String table, StringMatrix tableData) {
        this.entity = entity;
        this.table = table;
        // tableData will contain the first row of the tableData, for compatibility reasons with older code
        // TODO: refactor the older code to allow for multiple selections
        this.columnNames = tableData.getHeader();
        this.bookmark = bookmark;
        this.fullTableData = tableData;
    }
	
	public int getColumnCount() {
		return columnNames.length;
	}
	
	public String[] getColumnNames() {
		return columnNames;
	}

	public String getTable() {
		return table;
	}

	public Bookmark getBookmark() {
		return this.bookmark;
	}

    public Entity getEntity() {
        return this.entity;
    }

	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}

	public String[] getTableData() {
		return getTableRow(0);
	}
	
	public String[] getTableRow(int i) {
		return fullTableData.getRow(i);
	}
	
	public StringMatrix getRowTableData() {
		return fullTableData;
	}

}
