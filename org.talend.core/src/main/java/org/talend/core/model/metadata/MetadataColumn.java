// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006-2007 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
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

    private static int nextId = 0;

    private int id;

    private String label = ""; //$NON-NLS-1$

    private boolean key = false;

    private String type = ""; //$NON-NLS-1$

    private String talendType = ""; //$NON-NLS-1$

    private String dbms = ""; //$NON-NLS-1$

    private boolean nullable = false;

    private Integer length;

    private Integer precision;

    private String defaut = ""; //$NON-NLS-1$

    private String comment = ""; //$NON-NLS-1$

    private String pattern = ""; //$NON-NLS-1$

    private boolean custom = false;

    private boolean readOnly = false;

    private int customId = 0;

    public MetadataColumn() {
        super();
        this.id = getNewId();
    }

    /**
     * copy all fields.
     * 
     * @param metadataColumn
     */
    public MetadataColumn(IMetadataColumn metadataColumn) {
        this();

        this.setLabel(metadataColumn.getLabel());
        this.key = metadataColumn.isKey();
        try {
            this.setTalendType(metadataColumn.getTalendType());
        } catch (NoClassDefFoundError e) {
            // should never happend when product run
            e.printStackTrace();
        }

        this.setType(metadataColumn.getType());
        setDbms(metadataColumn.getDbms());

        this.nullable = metadataColumn.isNullable();
        this.length = metadataColumn.getLength();
        this.precision = metadataColumn.getPrecision();

        setDefault(metadataColumn.getDefault());
        setComment(metadataColumn.getComment());
    }

    private static synchronized int getNewId() {
        return nextId++;
    }

    @Override
    public String toString() {
        return getLabel();
    }

    /**
     * Getter for id.
     * 
     * @return the id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets the id.
     * 
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.model.metadata.IMetadataColumn#getLabel()
     */
    public String getLabel() {
        return this.label;
    }

    // /**
    // * Check the input String is empty or not.
    // *
    // * @param input
    // * @return
    // */
    // private boolean isNull(String input) {
    // return input == null;
    // }

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
        if ((talendType == null) || (talendType.compareTo("") == 0)) { //$NON-NLS-1$
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

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.metadata.IMetadataColumn#getPattern()
     */
    public String getPattern() {
        return this.pattern;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.metadata.IMetadataColumn#setPattern(java.lang.String)
     */
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public IMetadataColumn clone() {
        IMetadataColumn clonedMetacolumn = null;
        try {
            clonedMetacolumn = (IMetadataColumn) super.clone();
            clonedMetacolumn.setCustom(false);
            clonedMetacolumn.setReadOnly(false);
        } catch (CloneNotSupportedException e) {
            // nothing
        }
        return clonedMetacolumn;
    }

    public boolean sameMetacolumnAs(IMetadataColumn other, int options) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof IMetadataColumn)) {
            return false;
        }
        if ((options & OPTIONS_IGNORE_COMMENT) == 0) {
            if (!sameStringValue(this.comment, other.getComment())) {
                return false;
            }
        }
        if ((options & OPTIONS_IGNORE_KEY) == 0) {
            if (this.key != other.isKey()) {
                return false;
            }
        }
        if (!sameStringValue(this.label, other.getLabel())) {
            return false;
        }
        if (!sameStringValue(this.pattern, other.getPattern())) {
            return false;
        }
        if (!sameIntegerValue(this.length, other.getLength())) {
            return false;
        }
        if ((options & OPTIONS_IGNORE_NULLABLE) == 0) {
            if (this.nullable != other.isNullable()) {
                return false;
            }
        }
        if (!sameIntegerValue(this.precision, other.getPrecision())) {
            return false;
        }
        if (!sameStringValue(this.defaut, other.getDefault())) {
            return false;
        }
        if (!sameStringValue(this.talendType, other.getTalendType())) {
            return false;
        }
        if (!sameStringValue(this.type, other.getType())) {
            return false;
        }
        return true;
    }

    public boolean sameMetacolumnAs(IMetadataColumn other) {
        return sameMetacolumnAs(other, OPTIONS_NONE);
    }

    private boolean sameStringValue(String value1, String value2) {
        if (value1 == null) {
            if (value2 == null) {
                return true;
            } else {
                return value2.equals("");
            }
        } else {
            if (value1.equals("") && value2 == null) {
                return true;
            } else {
                return value1.equals(value2);
            }
        }
    }

    private boolean sameIntegerValue(Integer value1, Integer value2) {
        if (value1 == null) {
            if (value2 == null) {
                return true;
            } else {
                return value2 == -1;
            }
        } else {
            if ((value1 == -1) && value2 == null) {
                return true;
            } else {
                return value1.equals(value2);
            }
        }
    }

    /**
     * Getter for custom.
     * 
     * @return the custom
     */
    public boolean isCustom() {
        return custom;
    }

    /**
     * Sets the custom.
     * 
     * @param custom the custom to set
     */
    public void setCustom(boolean custom) {
        this.custom = custom;
    }

    /**
     * Getter for readOnly.
     * 
     * @return the readOnly
     */
    public boolean isReadOnly() {
        return readOnly;
    }

    /**
     * Sets the readOnly.
     * 
     * @param readOnly the readOnly to set
     */
    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public int getCustomId() {
        return customId;
    }

    public void setCustomId(int customId) {
        this.customId = customId;
    }
}
