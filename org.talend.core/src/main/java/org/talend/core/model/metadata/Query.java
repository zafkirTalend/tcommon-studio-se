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

import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.RepositoryObject;

/**
 * Meta Data Table. Contains all the columns. <br/> $Id: MetadataTable.java,v 1.24.4.1 2006/09/05 13:38:25 mhelleboid
 * Exp $
 */
public class Query extends RepositoryObject implements Cloneable {

    private String queryName;

    private IMetadataConnection parent;

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.model.metadata.IMetadataTable#getTableName()
     */
    public String getTableName() {
        return this.queryName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.model.metadata.IMetadataTable#setTableName(java.lang.String)
     */
    public void setTableName(String tableName) {
        this.queryName = tableName;
    }

    /**
     * Getter for parent.
     * 
     * @return the parent
     */
    public IMetadataConnection getParent() {
        return this.parent;
    }

    /**
     * Sets the parent.
     * 
     * @param parent the parent to set
     */
    public void setParent(IMetadataConnection parent) {
        this.parent = parent;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.repository.IRepositoryObject#getType()
     */
    public ERepositoryObjectType getType() {
        return ERepositoryObjectType.METADATA_CON_QUERY;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.repository.IRepositoryObject#getVersion()
     */
    public String getVersion() {
        return getParent().getVersion();
    }

}
