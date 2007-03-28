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
package org.talend.core.ui.extended.command;

import java.util.ArrayList;
import java.util.List;

import org.talend.commons.ui.swt.advanced.dataeditor.commands.ExtendedTablePasteCommand;
import org.talend.commons.ui.swt.extended.table.ExtendedTableModel;
import org.talend.core.model.metadata.builder.connection.MetadataColumn;
import org.talend.core.model.metadata.builder.connection.impl.ConnectionFactoryImpl;
import org.talend.core.model.metadata.editor.MetadataEmfTableEditor;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class MetadataEmfPasteCommand extends ExtendedTablePasteCommand {

    /**
     * DOC amaumont MetadataPasteCommand constructor comment.
     * @param extendedTable
     * @param validAssignableType
     * @param indexStartAdd
     */
    public MetadataEmfPasteCommand(ExtendedTableModel extendedTable, Integer indexStartAdd) {
        super(extendedTable, indexStartAdd);
    }

    /**
     * DOC amaumont MetadataPasteCommand constructor comment.
     * @param extendedTable
     * @param instanceOfType
     */
    public MetadataEmfPasteCommand(ExtendedTableModel extendedTable) {
        super(extendedTable);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.ui.swt.advanced.dataeditor.commands.ExtendedTablePasteCommand#createPastableBeansList(java.util.List)
     */
    @Override
    public List createPastableBeansList(ExtendedTableModel extendedTable, List copiedObjectsList) {
        ArrayList list = new ArrayList();
        int indice = 1;
        MetadataEmfTableEditor tableEditor = (MetadataEmfTableEditor) extendedTable;
        for (Object current : copiedObjectsList) {
            if (current instanceof MetadataColumn) {
                // create a new column as a copy of this column
                MetadataColumn metadataColumn = (MetadataColumn) current;
                String nextGeneratedColumnName = tableEditor.getNextGeneratedColumnName(metadataColumn.getLabel());
                MetadataColumn newColumnCopy = new ConnectionFactoryImpl().copy(metadataColumn, nextGeneratedColumnName);
                newColumnCopy.setLabel(nextGeneratedColumnName);
                list.add(newColumnCopy);
            }
        }
        return list;
    }

}
