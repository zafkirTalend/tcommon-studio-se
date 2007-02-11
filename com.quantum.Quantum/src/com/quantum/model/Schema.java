package com.quantum.model;

import com.quantum.util.Displayable;

/**
 * @author BC
 */
public class Schema implements Comparable, Displayable {
    
    private String name;
    private String displayName;
    private boolean isDefault;
    private boolean exists = true;
    
    public Schema() {
    }

    public Schema(String name, String displayName, boolean isDefault) {
        this.name = name;
        this.displayName = displayName;
        this.isDefault = isDefault;
    }
    
    public Schema(String name) {
        this(name, name, false);
    }
    
    /**
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param string
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(Object o) {
        Schema that = (Schema) o;
        if (that.isDefault() == this.isDefault()) {
            return this.getDisplayName().compareTo(that.getDisplayName());
        } else {
            return that.isDefault() ? 1 : -1;
        }
    }
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()) {
            return false;
        } else {
            Schema that = (Schema) obj;
            if (this.name == null && !(that.name == null)) {
                return false;
            } else if (this.name != null && !this.name.equals(that.name)) {
                return false;
            } else if (this.displayName == null && !(that.displayName == null)) {
                return false;
            } else if (this.displayName == null && !this.displayName.equals(that.displayName)) {
                return false;
            } else {
                return true;
            }
        }
    }
    public int hashCode() {
        int hashCode = 51;
        if (this.name != null) {
            hashCode ^= this.name.hashCode();
        }
        if (this.displayName != null) {
            hashCode ^= this.displayName.hashCode();
        }
        
        return hashCode;
    }
    
    /**
     * @return
     */
    public boolean isDefault() {
        return this.isDefault;
    }

    /**
     * @param b
     */
    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * @return
     */
    public String getDisplayName() {
        return this.displayName;
    }

    /**
     * @param string
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean exists() {
    	return this.exists;
    }
    
    void setExists(boolean exists) {
    	this.exists = exists;
    }
    
    public String toString() {
    	return this.displayName == null ? this.name : this.displayName;
    }
}
