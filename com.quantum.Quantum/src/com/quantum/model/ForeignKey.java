package com.quantum.model;

/**
 * @author BC
 */
public interface ForeignKey {
    public String getName();
    public String getLocalEntityName();
    public String getLocalEntitySchema();
    public String getLocalEntityQualifiedName();
    public String getForeignEntityName();
    public String getForeignEntitySchema();
    public String getForeignEntityQualifiedName();
    public int getNumberOfColumns();
    public String getLocalColumnName(int index);
    public String getForeignColumnName(int index);
    public int getUpdateRule();
	public int getDeleteRule();
}
