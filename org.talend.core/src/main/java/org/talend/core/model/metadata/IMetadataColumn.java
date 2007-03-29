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

    public boolean sameMetacolumnAs(IMetadataColumn metaColumn);
    
    public boolean isReadOnly();
    
    public void setReadOnly(boolean readOnly);
    
    public boolean isCustom();
    
    public void setCustom(boolean custom);
    
    public void setCustomId(int customId); // only for custom columns to sort them
    
    public int getCustomId(); // only for custom columns to sort them
}
