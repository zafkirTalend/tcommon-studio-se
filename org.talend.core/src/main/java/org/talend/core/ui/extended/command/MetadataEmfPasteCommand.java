// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
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
     * 
     * @param extendedTable
     * @param validAssignableType
     * @param indexStartAdd
     */
    public MetadataEmfPasteCommand(ExtendedTableModel extendedTable, Integer indexStartAdd) {
        super(extendedTable, indexStartAdd);
    }

    /**
     * DOC amaumont MetadataPasteCommand constructor comment.
     * 
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
                MetadataColumn newColumnCopy = new ConnectionFactoryImpl()
                        .copy(metadataColumn, nextGeneratedColumnName);
                newColumnCopy.setLabel(nextGeneratedColumnName);
                list.add(newColumnCopy);
            }
        }
        return list;
    }

}
