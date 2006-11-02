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

import org.talend.core.model.action.IEventAction;
import org.talend.core.model.metadata.editor.MetadataEditorEvent;
import org.talend.core.ui.metadata.editor.MetadataTableEditorView;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class RemoveMetadataAction extends MetadataEditorAction {

    /**
     * DOC amaumont AddMetadataAction constructor comment.
     * 
     * @param metadataEditorView
     */
    public RemoveMetadataAction(MetadataTableEditorView metadataEditorView) {
        super(metadataEditorView);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.mapper.actions.IMapperAction#run(org.talend.designer.mapper.actions.IMapperEvent)
     */
    public void run(IEventAction eventAction) {
        MetadataEditorEvent metadataEditorEvent = (MetadataEditorEvent) eventAction;
        if (metadataEditorEvent.entries != null) {

            getMetadataTableEditor().remove(metadataEditorEvent.entriesIndices);
            if (metadataEditorEvent.entriesIndices.length > 0) {
                int[] selection = new int[] { metadataEditorEvent.entriesIndices[0] };
                metadataTableEditorView.getTableViewerCreator().getSelectionHelper().setSelection(selection);
            }
        }
    }

}
