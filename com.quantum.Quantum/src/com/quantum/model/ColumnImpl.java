package com.quantum.model;

import com.quantum.util.sql.TypesHelper;

/**
 * @author BC
 */
public class ColumnImpl implements Column, Comparable {

    private long size;
    private boolean nullable;
    private int primaryKeyOrder;
    private String name;
    private Entity entity;
    private int numberOfFractionalDigits;
    private String typeName;
    private int type;
    private int position;
    private String remarks;
    private String defaultValue;
    
    public ColumnImpl(Entity entity, String name, String typeName, int type, 
        long size, int numberOfFractionalDigits, boolean nullable, int position,
        String defaultValue, String remarks) {
           
        this.entity = entity;
        this.name = name;
        this.typeName = typeName;
        this.type = type;
        this.size = size;
        this.numberOfFractionalDigits = numberOfFractionalDigits;
        this.nullable = nullable;
        this.position = position;
        this.remarks = remarks;
        this.defaultValue = defaultValue;
        this.primaryKeyOrder = 0;
    }

    /**
     * @see com.quantum.model.Column#getPrimaryKeyOrder()
     */
    public int getPrimaryKeyOrder() {
        return this.primaryKeyOrder;
    }

    /**
     * @see com.quantum.model.Column#isPrimaryKey()
     */
    public boolean isPrimaryKey() {
        return getPrimaryKeyOrder() > 0;
    }

    /**
     * @see com.quantum.model.Column#getName()
     */
    public String getName() {
        return this.name;
    }

    /**
     * @see com.quantum.model.Column#getTypeName()
     */
    public String getTypeName() {
        return this.typeName;
    }

    /**
     * @see com.quantum.model.Column#isReal()
     */
    public boolean isReal() {
        return TypesHelper.isReal(this.type);
    }

    /**
     * @see com.quantum.model.Column#isNullable()
     */
    public boolean isNullable() {
        return this.nullable;
    }

    /**
     * @see com.quantum.model.Column#isNumeric()
     */
    public boolean isNumeric() {
        return TypesHelper.isNumeric(this.type);
    }

    /**
     * @see com.quantum.model.Column#getType()
     */
    public int getType() {
        return this.type;
    }

    /**
     * @see com.quantum.model.Column#getParentEntity()
     */
    public Entity getParentEntity() {
        return this.entity;
    }

    /**
     * @param i
     */
    public void setPrimaryKeyOrder(int i) {
        this.primaryKeyOrder = i;
    }

    /**
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(Object o) {
        ColumnImpl that = (ColumnImpl) o;
        if (this.isPrimaryKey() && that.isPrimaryKey()) {
            return this.primaryKeyOrder - that.primaryKeyOrder;
        } else if (this.isPrimaryKey()) {
            return -1;
        } else if (that.isPrimaryKey()) {
            return 1;
        } else {
            return this.position - that.position;
        }
    }
    /**
     * @return The size of the column
     */
    public long getSize() {
        return size;
    }

    /**
     * @return The precision of the column (number of fractional digits)
     */
    public int getNumberOfFractionalDigits() {
        return numberOfFractionalDigits;
    }

    /**
     * @return the remarks for the column, or empty string if none.
     */
    public String getRemarks() {
        return this.remarks == null ? "" : this.remarks;
    }

	/**
	 * @return Returns the position of the column in its container (table or view usually)
	 */
	public int getPosition() {
		return position;
	}
	/**
	 * @return Returns the default Value. Null if not set.
	 */
	public String getDefaultValue() {
		return defaultValue;
	}
}
