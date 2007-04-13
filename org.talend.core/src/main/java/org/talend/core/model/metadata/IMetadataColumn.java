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
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public interface IMetadataColumn {

    public static final int OPTIONS_NONE = 0;

    public static final int OPTIONS_IGNORE_KEY = 1;

    public static final int OPTIONS_IGNORE_NULLABLE = 2;

    public static final int OPTIONS_IGNORE_COMMENT = 4;

    public static final int OPTIONS_IGNORE_PATTERN = 8;

    public static final int OPTIONS_IGNORE_TYPE = 16;

    public static final int OPTIONS_IGNORE_LENGTH = 32;

    public static final int OPTIONS_IGNORE_PRECISION = 64;

    public static final int OPTIONS_IGNORE_DEFAULT = 128;

    public static final int OPTIONS_IGNORE_TALENDTYPE = 256;

    public static final int OPTIONS_IGNORE_LABEL = 256;

    public static final int OPTIONS_IGNORE_ALL = OPTIONS_IGNORE_LABEL | OPTIONS_IGNORE_TALENDTYPE
            | OPTIONS_IGNORE_DEFAULT | OPTIONS_IGNORE_PRECISION | OPTIONS_IGNORE_LENGTH | OPTIONS_IGNORE_TYPE
            | OPTIONS_IGNORE_PATTERN | OPTIONS_IGNORE_COMMENT | OPTIONS_IGNORE_NULLABLE | OPTIONS_IGNORE_KEY;

    public int getId();

    public void setId(int i);

    public String getLabel();

    public void setLabel(String label);

    public boolean isKey();

    public void setKey(boolean key);

    public String getType();

    public void setType(String type);

    public String getTalendType();

    public void setTalendType(String talendType);

    public String getPattern();

    public void setPattern(String pattern);

    public String getDbms();

    public void setDbms(String dbms);

    public Integer getLength();

    public void setLength(Integer length);

    public boolean isNullable();

    public void setNullable(boolean nullable);

    public Integer getPrecision();

    public void setPrecision(Integer precision);

    public String getDefault();

    public void setDefault(String defaut);

    public String getComment();

    public void setComment(String comment);

    public IMetadataColumn clone();

    public IMetadataColumn clone(boolean withCustoms);

    public boolean sameMetacolumnAs(IMetadataColumn metaColumn);

    public boolean sameMetacolumnAs(IMetadataColumn other, int options);

    public boolean isReadOnly();

    public void setReadOnly(boolean readOnly);

    public boolean isCustom();

    public void setCustom(boolean custom);

    public void setCustomId(int customId); // only for custom columns to sort them

    public int getCustomId(); // only for custom columns to sort them
}
