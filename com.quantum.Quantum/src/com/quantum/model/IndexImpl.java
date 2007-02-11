package com.quantum.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author BC
 */
/*package*/ class IndexImpl implements Index {
    
    private String name;
    private List columnNames = Collections.synchronizedList(new ArrayList()); 
    private Entity entity;
    private List ascending = Collections.synchronizedList(new ArrayList());
    private boolean unique = false;
    
    IndexImpl(Entity entity, String name, boolean unique) {
            
        this.name = name;
        this.entity = entity;
        this.unique = unique;
    }
    
    void addColumn(String columnName, Boolean ascending) {
        this.columnNames.add(columnName);
        this.ascending.add(ascending);
    }

    public String getName() {
        return this.name;
    }

    /**
     * @see com.quantum.model.Index#getNumberOfColumns()
     */
    public int getNumberOfColumns() {
        return this.columnNames.size();
    }

    /**
     * @see com.quantum.model.Index#getColumnName(int)
     */
    public String getColumnName(int ordinalPosition) {
        return (String) this.columnNames.get(ordinalPosition);
    }

    /**
     * @see com.quantum.model.Index#getParentEntity()
     */
    public Entity getParentEntity() {
        return this.entity;
    }

    /**
     * @see com.quantum.model.Index#isAscending()
     */
    public boolean isAscending(int ordinalPosition) {
        Boolean ascending = (Boolean) this.ascending.get(ordinalPosition);
        return Boolean.TRUE.equals(ascending);
    }

    public boolean isUnique() {
        return this.unique;
    }

    /**
     * @see com.quantum.model.Index#isDescending()
     */
    public boolean isDescending(int ordinalPosition) {
        Boolean ascending = (Boolean) this.ascending.get(ordinalPosition);
        return Boolean.FALSE.equals(ascending);
    }

}
