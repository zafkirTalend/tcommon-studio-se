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

import java.util.ArrayList;
import java.util.List;

import org.talend.core.model.general.Version;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.RepositoryObject;

/**
 * Meta Data Table. Contains all the columns. <br/> $Id: MetadataTable.java,v 1.24.4.1 2006/09/05 13:38:25 mhelleboid
 * Exp $
 */
public class MetadataTable extends RepositoryObject implements IMetadataTable, Cloneable {

    private String tableName;

    private List<IMetadataColumn> listColumns = new ArrayList<IMetadataColumn>();

    private IMetadataConnection parent;

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.model.metadata.IMetadataTable#getTableName()
     */
    public String getTableName() {
        return this.tableName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.model.metadata.IMetadataTable#setTableName(java.lang.String)
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.model.metadata.IMetadataTable#getListColumns()
     */
    public List<IMetadataColumn> getListColumns() {
        return this.listColumns;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.model.metadata.IMetadataTable#setListColumns(Hashtable)
     */
    public void setListColumns(List<IMetadataColumn> listColumns) {
        this.listColumns = listColumns;
    }

    public IMetadataTable clone() {
        IMetadataTable clonedMetadata = null;
        try {
            clonedMetadata = (IMetadataTable) super.clone();
            List<IMetadataColumn> clonedMetaColumns = new ArrayList<IMetadataColumn>();
            clonedMetadata.setListColumns(clonedMetaColumns);
            for (int i = 0; i < listColumns.size(); i++) {
                clonedMetaColumns.add(listColumns.get(i).clone());
            }
        } catch (CloneNotSupportedException e) {
            // nothing
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clonedMetadata;
    }

    public boolean sameMetadataAs(IMetadataTable meta) {
        if (this == meta) {
            return true;
        }
        if (meta == null) {
            return false;
        }
        if (getClass() != meta.getClass()) {
            return false;
        }
        final MetadataTable other = (MetadataTable) meta;
        if (this.listColumns == null) {
            if (other.listColumns != null) {
                return false;
            }
        } else if (this.listColumns.size() != other.listColumns.size()) {
            return false;
        } else {
            for (int i = 0; i < other.listColumns.size(); i++) {
                IMetadataColumn otherColumn = other.listColumns.get(i);
                IMetadataColumn myColumn = this.listColumns.get(i);
                if (!otherColumn.sameMetacolumnAs(myColumn)) {
                    return false;
                }
            }
        }

        if (this.tableName == null) {
            if (other.tableName != null) {
                return false;
            }
        } else if (!this.tableName.equals(other.tableName)) {
            return false;
        }
        return true;
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
        return ERepositoryObjectType.METADATA_CON_TABLE;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.repository.IRepositoryObject#getVersion()
     */
    public Version getVersion() {
        return getParent().getVersion();
    }
}
