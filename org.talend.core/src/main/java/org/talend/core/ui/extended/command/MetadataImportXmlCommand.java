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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.gef.commands.Command;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.ui.swt.extended.table.ExtendedTableModel;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.MetadataSchema;
import org.xml.sax.SAXException;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class MetadataImportXmlCommand extends Command {

    private File file;
    private ExtendedTableModel extendedTableModel;
    private List<IMetadataColumn> removed;
    private List<IMetadataColumn> added;

    /**
     * DOC amaumont MetadataPasteCommand constructor comment.
     * @param extendedTableModel 
     * @param extendedTable
     * @param validAssignableType
     * @param indexStartAdd
     */
    public MetadataImportXmlCommand(ExtendedTableModel extendedTableModel, File file) {
        super();
        this.file = file;
        this.extendedTableModel = extendedTableModel;
    }

    /* (non-Javadoc)
     * @see org.talend.commons.ui.command.CommonCommand#execute()
     */
    @Override
    public void execute() {
        try {
            removed =  new ArrayList<IMetadataColumn>(extendedTableModel.getBeansList());
            extendedTableModel.removeAll(removed);
            added = MetadataSchema.initializeColumns(file);
            extendedTableModel.addAll(added);

        } catch (ParserConfigurationException e) {
            ExceptionHandler.process(e);
        } catch (SAXException e) {
            ExceptionHandler.process(e);
        } catch (IOException e) {
            ExceptionHandler.process(e);
        }
    }

    
    /**
     * Getter for extendedTableModel.
     * @return the extendedTableModel
     */
    public ExtendedTableModel getExtendedTableModel() {
        return this.extendedTableModel;
    }

    

}
