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

import java.util.List;


/**
 * 
 * DOC amaumont TypesManager class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class Dbms {

    private String id;
    
    private String product;

    private String label;
    
    private String defaultDbType;

    private List<String> dbmsTypes;

    private List<MappingType> mappingTypes;

    /**
     * DOC amaumont Dbms constructor comment.
     * 
     * @param id
     * @param label
     * @param dbmsTypes
     * @param mappingTypes
     */
    public Dbms(String id, String label, List<String> dbmsTypes, List<MappingType> mappingTypes) {
        super();
        this.id = id;
        this.label = label;
        this.dbmsTypes = dbmsTypes;
        this.mappingTypes = mappingTypes;
    }

    /**
     * DOC amaumont Dbms constructor comment.
     * 
     * @param dbmsIdValue
     */
    protected Dbms(String id) {
        this.id = id;
    }

    /**
     * Getter for dbmsTypes.
     * 
     * @return the dbmsTypes
     */
    public List<String> getDbTypes() {
        return this.dbmsTypes;
    }

    /**
     * Sets the dbmsTypes.
     * 
     * @param dbmsTypes the dbmsTypes to set
     */
    protected void setDbmsTypes(List<String> dbmsTypes) {
        this.dbmsTypes = dbmsTypes;
    }

    /**
     * Getter for id.
     * 
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * Sets the id.
     * 
     * @param id the id to set
     */
    protected void setId(String id) {
        this.id = id;
    }

    /**
     * Getter for label.
     * 
     * @return the label
     */
    public String getLabel() {
        return this.label;
    }

    /**
     * Sets the label.
     * 
     * @param label the label to set
     */
    protected void setLabel(String label) {
        this.label = label;
    }

    /**
     * Getter for mappingTypes.
     * 
     * @return the mappingTypes
     */
    public List<MappingType> getMappingTypes() {
        return this.mappingTypes;
    }

    /**
     * Sets the mappingTypes.
     * 
     * @param mappingTypes the mappingTypes to set
     */
    protected void setMappingTypes(List<MappingType> mappingTypes) {
        this.mappingTypes = mappingTypes;
    }

    
    
    
    /**
     * Getter for product.
     * @return the product
     */
    public String getProduct() {
        return this.product;
    }

    
    /**
     * Sets the product.
     * @param product the product to set
     */
    protected void setProduct(String product) {
        this.product = product;
    }

    
    
    
    /**
     * Getter for defaultDbType.
     * @return the defaultDbType
     */
    public String getDefaultDbType() {
        return this.defaultDbType;
    }

    
    /**
     * Sets the defaultDbType.
     * @param defaultDbType the defaultDbType to set
     */
    protected void setDefaultDbType(String defaultDbType) {
        this.defaultDbType = defaultDbType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
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
        final Dbms other = (Dbms) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        } else if (!this.id.equals(other.id))
            return false;
        return true;
    }

    /**
         * toString method: creates a String representation of the object
         * @return the String representation
         * @author 
         */
        public String toString() {
            StringBuffer buffer = new StringBuffer();
            buffer.append("Dbms["); //$NON-NLS-1$
            buffer.append("product = ").append(product); //$NON-NLS-1$
            buffer.append(", id = ").append(id); //$NON-NLS-1$
            buffer.append(", label = ").append(label); //$NON-NLS-1$
            buffer.append(", defaultDbType = ").append(defaultDbType); //$NON-NLS-1$
            buffer.append(", dbmsTypes = ").append(dbmsTypes); //$NON-NLS-1$
            buffer.append(", mappingTypes = ").append(mappingTypes); //$NON-NLS-1$
            buffer.append("]"); //$NON-NLS-1$
            return buffer.toString();
        }

    
    
}
