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

/**
 * DOC ggu class global comment. Detailled comment <br/>
 * 
 * org.talend.core.model.metadata.builder.connection.MetadataColumn<br/>
 * 
 * org.talend.core.model.metadata.IMetadataColumn<br/>
 * 
 * @param <E> Type of beans, MetadataColumn and IMetadataColumn
 * 
 * 
 */
public class MetadataColumnsAndDbmsId<E> {

    private List<E> metadataColumns;

    private String dbmsId;

    /**
     * DOC ggu MetadataColumnsAndDbmsId constructor comment.
     * 
     * @param metadataColumns
     * @param dbmsId
     */
    public MetadataColumnsAndDbmsId(List<E> metadataColumns, String dbmsId) {
        super();
        this.metadataColumns = metadataColumns;
        this.dbmsId = dbmsId;
    }

    /**
     * Getter for metadataColumns.
     * 
     * @return the metadataColumns
     */
    public List<E> getMetadataColumns() {
        return metadataColumns;
    }

    /**
     * Getter for dbmsId.
     * 
     * @return the dbmsId
     */
    public String getDbmsId() {
        return dbmsId;
    }

}
