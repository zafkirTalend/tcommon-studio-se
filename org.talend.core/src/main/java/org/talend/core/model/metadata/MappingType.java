// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
//
// ============================================================================
package org.talend.core.model.metadata;



/**
 * 
 * DOC amaumont TypesManager class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class MappingType {

    private String dbType;

    private String talendTypeName;

    private Boolean nullable;

    private Boolean defaultSelected;

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.dbType == null) ? 0 : this.dbType.hashCode());
        result = prime * result + ((this.defaultSelected == null) ? 0 : this.defaultSelected.hashCode());
        result = prime * result + ((this.talendTypeName == null) ? 0 : this.talendTypeName.hashCode());
        result = prime * result + ((this.nullable == null) ? 0 : this.nullable.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final MappingType other = (MappingType) obj;

        if (this.dbType != null && !this.dbType.equals(other.dbType))
            return false;

        if (this.defaultSelected != null && !this.defaultSelected.equals(other.defaultSelected))
            return false;

        if (this.talendTypeName != null && !this.talendTypeName.equals(other.talendTypeName))
            return false;

        if (this.nullable != null && !this.nullable.equals(other.nullable))
            return false;
        return true;
    }

    /**
     * Getter for dbmsType.
     * 
     * @return the dbmsType
     */
    public String getDbType() {
        return this.dbType;
    }

    /**
     * Sets the dbmsType.
     * 
     * @param dbmsType the dbmsType to set
     */
    public void setDbType(String dbmsType) {
        this.dbType = dbmsType;
    }

    /**
     * Getter for defaultSelected.
     * 
     * @return the defaultSelected
     */
    public Boolean getDefaultSelected() {
        return this.defaultSelected;
    }

    /**
     * Sets the defaultSelected.
     * 
     * @param defaultSelected the defaultSelected to set
     */
    public void setDefaultSelected(Boolean defaultSelected) {
        this.defaultSelected = defaultSelected;
    }

    /**
     * Getter for languageType.
     * 
     * @return the languageType
     */
    public String getTalendTypeName() {
        return this.talendTypeName;
    }

    /**
     * Sets the languageType.
     * 
     * @param talendTypeName the languageType to set
     */
    public void setTalendTypeName(String talendTypeName) {
        this.talendTypeName = talendTypeName;
    }

    /**
     * Getter for nullable.
     * 
     * @return the nullable
     */
    public Boolean getNullable() {
        return this.nullable;
    }

    /**
     * Sets the nullable.
     * 
     * @param nullable the nullable to set
     */
    public void setNullable(Boolean nullable) {
        this.nullable = nullable;
    }

    /**
         * toString method: creates a String representation of the object
         * @return the String representation
         * @author 
         */
        public String toString() {
            StringBuffer buffer = new StringBuffer();
            buffer.append("MappingType[");
            buffer.append("dbmsType = ").append(dbType);
            buffer.append(", talendType = ").append(talendTypeName);
            buffer.append(", defaultSelected = ").append(defaultSelected);
            buffer.append(", nullable = ").append(nullable);
            buffer.append("]");
            return buffer.toString();
        }

    
    
}

