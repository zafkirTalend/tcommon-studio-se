package com.quantum.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @author BC
 */
public class ForeignKeyImpl implements ForeignKey {
	
	private String name;
	private String localEntityName;
	private String localEntitySchema;
	private String foreignEntityName;
	private String foreignEntitySchema;
	private List foreignColumns = Collections.synchronizedList(new ArrayList());
	private List localColumns = Collections.synchronizedList(new ArrayList());
	private int deleteRule;
	private int updateRule;
	
	public void addColumns(String localColumnName, String foreignColumnName) {
		this.foreignColumns.add(foreignColumnName);
		this.localColumns.add(localColumnName);
	}

	public int getDeleteRule() {
		return this.deleteRule;
	}
	public void setDeleteRule(int deleteRule) {
		this.deleteRule = deleteRule;
	}
	public String getForeignEntityName() {
		return this.foreignEntityName;
	}
	public void setForeignEntityName(String foreignEntityName) {
		this.foreignEntityName = foreignEntityName;
	}
	public String getForeignEntitySchema() {
		return this.foreignEntitySchema;
	}
	public void setForeignEntitySchema(String foreignEntitySchema) {
		this.foreignEntitySchema = foreignEntitySchema;
	}
	public String getLocalEntityName() {
		return this.localEntityName;
	}
	public void setLocalEntityName(String localEntityName) {
		this.localEntityName = localEntityName;
	}
	public String getLocalEntitySchema() {
		return this.localEntitySchema;
	}
	public void setLocalEntitySchema(String localEntitySchema) {
		this.localEntitySchema = localEntitySchema;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getNumberOfColumns() {
		return this.localColumns.size();
	}
	
	public boolean equals(Object object) {
		if (this.getClass() != object.getClass()) {
			return false;
		} else {
			ForeignKeyImpl that = (ForeignKeyImpl) object;
			if (this.name == null && that.name != null) {
				return false;
			} else if (this.name != null && !this.name.equals(that.name)) {
				return false;
			} else if (this.foreignEntitySchema == null && that.foreignEntitySchema != null) {
				return false;
			} else if (this.foreignEntitySchema != null && !this.foreignEntitySchema.equals(that.foreignEntitySchema)) {
				return false;
			} else if (this.foreignEntityName == null && that.foreignEntityName != null) {
				return false;
			} else if (this.foreignEntityName != null && !this.foreignEntityName.equals(that.foreignEntityName)) {
				return false;
			} else if (this.localEntitySchema == null && that.localEntitySchema != null) {
				return false;
			} else if (this.localEntitySchema != null && !this.localEntitySchema.equals(that.foreignEntitySchema)) {
				return false;
			} else if (this.localEntityName == null && that.localEntityName != null) {
				return false;
			} else if (this.localEntityName != null && !this.localEntityName.equals(that.localEntityName)) {
				return false;
			} else if (this.deleteRule != that.deleteRule) {
				return false;
			} else if (this.localColumns.size() != that.localColumns.size() 
					|| this.foreignColumns.size() != that.foreignColumns.size()) {
				return false;
			} else {
				boolean result = true;
				for (int i = 0, length = this.localColumns.size(); i < length; i++) {
					Object localColumn = this.localColumns.get(i);
					result &= (localColumn != null && localColumn.equals(that.localColumns.get(i)));
					Object foreignColumn = this.foreignColumns.get(i);
					result &= (foreignColumn != null && foreignColumn.equals(that.foreignColumns.get(i)));
				}
				return result;
			}
			
		}
	}
	
	public int hashCode() {
		int hashCode = 57;
		if (this.name != null) {
			hashCode ^= this.name.hashCode();
		}
		if (this.foreignEntitySchema != null) {
			hashCode ^= this.foreignEntitySchema.hashCode();
		}
		if (this.foreignEntityName != null) {
			hashCode ^= this.foreignEntityName.hashCode();
		}
		if (this.localEntitySchema != null) {
			hashCode ^= this.localEntitySchema.hashCode();
		}
		if (this.localEntityName != null) {
			hashCode ^= this.localEntityName.hashCode();
		}
		hashCode ^= this.deleteRule;
		for (int i = 0, length = this.localColumns.size(); i < length; i++) {
			hashCode ^= this.localColumns.get(i).hashCode();
			hashCode ^= this.foreignColumns.get(i).hashCode();
		}
		return hashCode;
	}
	public String getLocalColumnName(int index) {
		return (String) this.localColumns.get(index);
	}

	public String getForeignColumnName(int index) {
		return (String) this.foreignColumns.get(index);
	}
	public String getLocalEntityQualifiedName() {
		return getLocalEntitySchema() == null ? getLocalEntityName() :
			getLocalEntitySchema() + "." + getLocalEntityName();
	}
	public String getForeignEntityQualifiedName() {
		return getForeignEntitySchema() == null ? getForeignEntityName() :
			getForeignEntitySchema() + "." + getForeignEntityName();
	}

	public int getUpdateRule() {
		return updateRule;
	}
	

	public void setUpdateRule(int updateRule) {
		this.updateRule = updateRule;
	}
	
}
