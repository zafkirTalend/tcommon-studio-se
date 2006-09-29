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
 * Definition of a column in the Meta Data. <br/>
 * 
 * $Id$
 * 
 */
public class MetadataColumn implements IMetadataColumn, Cloneable {

    private String label = "";

    private boolean key = false;

    private String type = "";

    private String talendType = "";

    private String dbms = "";

    private boolean nullable = false;

    private Integer length;

    private Integer precision;

    private String defaut = "";

    private String comment = "";

    
    public MetadataColumn() {
        super();
    }

    /**
     * copy all fields.
     * @param metadataColumn
     */
    public MetadataColumn(IMetadataColumn metadataColumn) {
        this.label = metadataColumn.getLabel();
        this.key = metadataColumn.isKey();
        try {
            this.talendType = metadataColumn.getTalendType();
        } catch (NoClassDefFoundError e) {
            // should never happend when product run
            e.printStackTrace();
        }
        this.dbms = metadataColumn.getDbms();
        this.nullable = metadataColumn.isNullable();
        this.length = metadataColumn.getLength();
        this.precision = metadataColumn.getPrecision();
        this.defaut = metadataColumn.getDefault();
        this.comment = metadataColumn.getComment();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.model.metadata.IMetadataColumn#getLabel()
     */
    public String getLabel() {
        return this.label;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.model.metadata.IMetadataColumn#setLabel(java.lang.String)
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.model.metadata.IMetadataColumn#isKey()
     */
    public boolean isKey() {
        return this.key;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.model.metadata.IMetadataColumn#setKey(boolean)
     */
    public void setKey(boolean key) {
        this.key = key;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.model.metadata.IMetadataColumn#getType()
     */
    public String getType() {
        return this.type;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.model.metadata.IMetadataColumn#setType(java.lang.String)
     */
    public void setType(String type) {
        this.type = type;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.metadata.IMetadataColumn#getTalendType()
     */
    public String getTalendType() {
        if ((talendType == null) || (talendType.compareTo("") == 0)) {
            this.talendType = MetadataTalendType.loadTalendType(this.type, this.dbms, false);
        }
        return talendType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.metadata.IMetadataColumn#setTalendType(java.lang.String)
     */
    public void setTalendType(String talendType) {
        this.talendType = talendType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.metadata.IMetadataColumn#getDbms()
     */
    public String getDbms() {
        return this.dbms;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.metadata.IMetadataColumn#setDbms(java.lang.String)
     */
    public void setDbms(String dbms) {
        this.dbms = dbms;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.model.metadata.IMetadataColumn#getLength()
     */
    public Integer getLength() {
        return this.length;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.model.metadata.IMetadataColumn#setLength(Integer)
     */
    public void setLength(Integer length) {
        this.length = length;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.model.metadata.IMetadataColumn#isNullable()
     */
    public boolean isNullable() {
        return this.nullable;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.model.metadata.IMetadataColumn#setNullable(boolean)
     */
    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.model.metadata.IMetadataColumn#getPrecision()
     */
    public Integer getPrecision() {
        return this.precision;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.model.metadata.IMetadataColumn#setPrecision(Integer)
     */
    public void setPrecision(Integer precision) {
        this.precision = precision;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.model.metadata.IMetadataColumn#getDefault()
     */
    public String getDefault() {
        return this.defaut;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.model.metadata.IMetadataColumn#setDefault(java.lang.String)
     */
    public void setDefault(String defaut) {
        this.defaut = defaut;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.model.metadata.IMetadataColumn#getComment()
     */
    public String getComment() {
        return this.comment;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.model.metadata.IMetadataColumn#setComment(java.lang.String)
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    public IMetadataColumn clone() {
        IMetadataColumn clonedMetacolumn = null;
        try {
            clonedMetacolumn = (IMetadataColumn) super.clone();
        } catch (CloneNotSupportedException e) {
            // nothing
        }
        return clonedMetacolumn;
    }

    public boolean sameMetacolumnAs(IMetadataColumn metaColumn) {
        if (this == metaColumn)
            return true;
        if (metaColumn == null)
            return false;
        if (getClass() != metaColumn.getClass())
            return false;
        final MetadataColumn other = (MetadataColumn) metaColumn;
        if (this.defaut == null) {
            if (other.defaut != null)
                return false;
        } else if (!this.defaut.equals(other.defaut))
            return false;
        if (this.key != other.key)
            return false;
        if (this.label == null) {
            if (other.label != null)
                return false;
        } else if (!this.label.equals(other.label))
            return false;
        if (this.length == null) {
            if (other.length != null)
                return false;
        } else if (!this.length.equals(other.length))
            return false;
        if (this.nullable != other.nullable)
            return false;
        if (this.precision == null) {
            if (other.precision != null)
                return false;
        } else if (!this.precision.equals(other.precision))
            return false;
        if (this.talendType == null) {
            if (other.talendType != null)
                return false;
        } else if (!this.talendType.equals(other.talendType))
            return false;
        return true;
    }
}
