package com.quantum.sql.parser;

import com.quantum.model.Entity;


/**
 * Drop a table, view or sequence.
 * 
 * @author BC Holmes
 */
public class DropEntityStatement implements SQLStatement {

	private String tableName;
	private String type = Entity.TABLE_TYPE;
	private String dependentRule = "";
	
	/**
	 * @see com.quantum.sql.parser.SQL#getCommand()
	 */
	public String getCommand() {
		return "DROP " + getType().toUpperCase();
	}
	
	public String getTableName() {
		return this.tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public String toString() {
		return getExecutableForm();
	}
	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDependentRule() {
		return this.dependentRule;
	}
	public void setDependentRule(String dependentRule) {
		this.dependentRule = dependentRule;
	}
	public boolean isComment() {
		return false;
	}

	public String getExecutableForm() {
		return getCommand() + " " + getTableName() + " " + this.dependentRule;
	}
}
