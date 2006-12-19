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
package org.talend.core.model.components;

import java.util.List;

import org.talend.core.model.metadata.ColumnNameChanged;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.MetadataTool;
import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.INode;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class IODataComponent {

    private IConnection connection;

    private IMetadataTable newMetadataTable;

    private List<ColumnNameChanged> columnNameChanged = null;

    /**
     * DOC smallet IODataComponent constructor comment.
     * 
     * @param connection
     */
    public IODataComponent(IConnection connection) {
        super();
        this.connection = connection;
        this.newMetadataTable = connection.getMetadataTable().clone();
    }

    public IODataComponent(IConnection connection, IMetadataTable clonedTable) {
        super();
        this.connection = connection;
        this.newMetadataTable = clonedTable;
    }

    @Override
    public String toString() {
        return "Connection=[" + connection + "], Table=[" + newMetadataTable + "]";
    }

    public String getName() {
        return connection.getName();
    }

    public EConnectionType getConnectionType() {
        return connection.getLineStyle();
    }

    public IMetadataTable getTable() {
        return newMetadataTable;
    }

    public void setTable(IMetadataTable table) {
        this.newMetadataTable = table;
    }

    public INode getTarget() {
        return connection.getTarget();
    }

    public INode getSource() {
        return connection.getSource();
    }

    public boolean hasChanged() {
        return !newMetadataTable.sameMetadataAs(connection.getMetadataTable());
    }

    public List<ColumnNameChanged> getColumnNameChanged() {
        if (columnNameChanged == null) {
            columnNameChanged = MetadataTool.getColumnNameChanged(connection.getMetadataTable(), newMetadataTable);
        }
        return columnNameChanged;
    }

    public void setColumnNameChanged(List<ColumnNameChanged> columnNameChanged) {
        this.columnNameChanged = columnNameChanged;
    }
}
