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

import java.util.ArrayList;
import java.util.List;

import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.RepositoryObject;
import org.talend.commons.ui.swt.tableviewer.behavior.DefaultTableLabelProvider;

/**
 * Meta Data Table. Contains all the columns. <br/> $Id: MetadataTable.java,v 1.24.4.1 2006/09/05 13:38:25 mhelleboid
 * Exp $
 */
public class MetadataTable extends RepositoryObject implements IMetadataTable, Cloneable {

    private String tableName;

    private List<IMetadataColumn> listColumns = new ArrayList<IMetadataColumn>();

    private IMetadataConnection parent;

    DefaultTableLabelProvider a;

    private boolean readOnly = false;

    @Override
    public String toString() {
        StringBuffer toReturn = new StringBuffer(getTableName() + ":"); //$NON-NLS-1$
        for (IMetadataColumn cur : listColumns) {
            toReturn.append(cur.toString() + " "); //$NON-NLS-1$
        }
        return toReturn.toString();
    }

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

    public boolean sameMetadataAs(IMetadataTable other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof IMetadataTable)) {
            return false;
        }

        if (this.listColumns == null) {
            if (other.getListColumns() != null) {
                return false;
            }
        } else {
            if (listColumns.size() == other.getListColumns().size()) { // test if standard columns (no custom, or same
                                                                        // input / output)
                for (int i = 0; i < other.getListColumns().size(); i++) {
                    IMetadataColumn otherColumn = other.getListColumns().get(i);
                    IMetadataColumn myColumn = this.listColumns.get(i);
                    if (!otherColumn.sameMetacolumnAs(myColumn)) {
                        return false;
                    }
                }
            } else { // test for custom input / output
                int nbNotCustomOrigin = 0;
                int nbNotCustomOther = 0;
                for (IMetadataColumn column : listColumns) {
                    if (!column.isCustom()) {
                        nbNotCustomOrigin++;
                    }
                }
                for (IMetadataColumn column : other.getListColumns()) {
                    if (!column.isCustom()) {
                        nbNotCustomOther++;
                    }
                }
                if (nbNotCustomOrigin != nbNotCustomOther) {
                    return false;
                } else {
                    for (int i = 0; i < other.getListColumns().size(); i++) {
                        IMetadataColumn otherColumn = other.getListColumns().get(i);
                        if (!otherColumn.isCustom()) {
                            IMetadataColumn myColumn = this.listColumns.get(i);
                            if (!otherColumn.sameMetacolumnAs(myColumn)) {
                                return false;
                            }
                        }
                    }
                }
            }
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
    public String getVersion() {
        return getParent().getVersion();
    }

    public IMetadataColumn getColumn(String columnName) {
        for (int i = 0; i < listColumns.size(); i++) {
            IMetadataColumn column = listColumns.get(i);
            if (column.getLabel().equals(columnName)) {
                return column;
            }
        }
        return null;
    }

    public void sortCustomColumns() {
        List<IMetadataColumn> customColumns = new ArrayList<IMetadataColumn>();
        for (int i = 0; i < listColumns.size(); i++) {
            IMetadataColumn column = listColumns.get(i);
            if (column.isCustom()) {
                customColumns.add(column);
            }
        }
        listColumns.removeAll(customColumns);
        int nbDone = 0;
        while (nbDone < customColumns.size()) {
            boolean found = false;
            for (int i = 0; i < customColumns.size() && !found; i++) {
                IMetadataColumn column = customColumns.get(i);
                if (column.getCustomId() == nbDone) {
                    listColumns.add(column);
                    found = true;
                }
            }
            nbDone++;
        }
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }
}
