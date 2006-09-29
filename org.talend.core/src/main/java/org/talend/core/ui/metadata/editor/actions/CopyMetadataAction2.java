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
package org.talend.core.ui.metadata.editor.actions;

import java.util.ArrayList;
import java.util.List;

import org.talend.core.model.action.IEventAction;
import org.talend.core.model.metadata.builder.connection.MetadataColumn;
import org.talend.core.model.metadata.builder.connection.impl.ConnectionFactoryImpl;
import org.talend.core.model.metadata.editor.MetadataEditorEvent;
import org.talend.core.model.metadata.editor.MetadataEditorEvent.TYPE;
import org.talend.core.ui.metadata.editor.MetadataTableEditorView2;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class CopyMetadataAction2 extends MetadataEditorAction2 {

    private List<MetadataColumn> selectedColumns;

    /**
     * DOC amaumont AddMetadataAction constructor comment.
     * 
     * @param metadatEditorView
     */
    public CopyMetadataAction2(MetadataTableEditorView2 metadatEditorView) {
        super(metadatEditorView);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.mapper.actions.IMapperAction#run(org.talend.designer.mapper.actions.IMapperEvent)
     */
    public void run(IEventAction eventAction) {
        MetadataEditorEvent metadataEditorEvent = (MetadataEditorEvent) eventAction;

        List<MetadataColumn> columns = getMetadataEditor().getMetadataColumnList();

        if (metadataEditorEvent.type == TYPE.COPY) {
            selectedColumns = new ArrayList<MetadataColumn>();
            for (int columnSelectedId : metadataEditorEvent.entriesIndices) {
                selectedColumns.add(columns.get(columnSelectedId));
            }
        } else if (metadataEditorEvent.type == TYPE.PASTE) {
            if ((metadataEditorEvent.entriesIndices != null) && (metadataEditorEvent.entriesIndices.length > 0)) {
                int indice = metadataEditorEvent.entriesIndices[0];
                for (MetadataColumn column : selectedColumns) {
                    // create a new column as a copy of this column
                    MetadataColumn newColumnCopy = new ConnectionFactoryImpl().copy(column, indice + ""); // FIXME MHE
                    indice++;
                    newColumnCopy.setLabel(getMetadataEditor().getValidateColumnName(newColumnCopy.getLabel(), indice));
                    getMetadataEditor().add(newColumnCopy, indice);
                }
            }
        }
    }

    public MetadataTableEditorView2 getCurrentTableEditor() {
        return metadataTableEditorView;
    }
}
