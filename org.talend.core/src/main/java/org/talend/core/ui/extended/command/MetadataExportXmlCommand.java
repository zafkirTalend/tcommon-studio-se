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
package org.talend.core.ui.extended.command;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.ui.command.CommonCommand;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.MetadataSchema;
import org.talend.core.model.metadata.editor.MetadataTableEditor;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class MetadataExportXmlCommand extends CommonCommand {

    private File file;

    private MetadataTableEditor extendedTableModel;

    /**
     * DOC amaumont MetadataPasteCommand constructor comment.
     * 
     * @param extendedTableModel
     * @param extendedTable
     * @param validAssignableType
     * @param indexStartAdd
     */
    public MetadataExportXmlCommand(MetadataTableEditor extendedTableModel, File file) {
        super();
        this.file = file;
        this.extendedTableModel = extendedTableModel;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.ui.command.CommonCommand#execute()
     */
    @Override
    public void execute() {
        try {
            file.createNewFile();
            if (file != null) {
                if (extendedTableModel != null) {
                    IMetadataTable currentTable = extendedTableModel.getMetadataTable();
                    // get all the columns from the table
                    if(currentTable != null) {
                        MetadataSchema.saveMetadataColumnToFile(file, currentTable);
                    }
                }
            }
        } catch (IOException e) {
            ExceptionHandler.process(e);
        } catch (ParserConfigurationException e) {
            ExceptionHandler.process(e);
        }

    }

}
