package com.quantum.model;

/**
 * @author BC
 */
public interface Index {
    public String getName();
    public int getNumberOfColumns();
    public String getColumnName(int ordinalPosition);
    public Entity getParentEntity();
    public boolean isAscending(int ordinalPosition);
    public boolean isDescending(int ordinalPosition);
    public boolean isUnique();
}
