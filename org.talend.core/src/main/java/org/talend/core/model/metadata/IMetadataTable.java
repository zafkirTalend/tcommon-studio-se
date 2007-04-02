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

import java.util.List;

import org.talend.core.model.repository.IRepositoryObject;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public interface IMetadataTable extends IRepositoryObject {

    public String getTableName();

    public void setTableName(String tableName);

    public IMetadataColumn getColumn(String columnName);

    public List<IMetadataColumn> getListColumns();

    public void setListColumns(List<IMetadataColumn> listColumns);

    public IMetadataTable clone();

    public IMetadataConnection getParent();

    public void setParent(IMetadataConnection metadataConnection);

    public boolean sameMetadataAs(IMetadataTable meta);
    
    public boolean sameMetadataAs(IMetadataTable other, int options);

    public void sortCustomColumns();
    
    public boolean isReadOnly();
    
    public void setReadOnly(boolean readOnly);
}
