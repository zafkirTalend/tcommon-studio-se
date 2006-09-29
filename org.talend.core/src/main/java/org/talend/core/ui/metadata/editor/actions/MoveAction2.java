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
import java.util.HashSet;
import java.util.Set;

import org.talend.commons.utils.data.list.ListenableList;
import org.talend.core.model.action.IEventAction;
import org.talend.core.model.metadata.builder.connection.MetadataColumn;
import org.talend.core.model.metadata.editor.MetadataEditorEvent;
import org.talend.core.model.metadata.editor.MetadataEditorEvent.TYPE;
import org.talend.core.ui.metadata.editor.MetadataTableEditorView2;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class MoveAction2 extends MetadataEditorAction2 {

    /**
     * DOC amaumont MoveUpAction constructor comment.
     * 
     * @param metadatEditorView
     */
    public MoveAction2(MetadataTableEditorView2 metadatEditorView) {
        super(metadatEditorView);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.mapper.actions.metadataeditor.MetadataEditorAction#run(org.talend.designer.mapper.actions.IMapperEvent)
     */
    @Override
    public void run(IEventAction eventAction) {
        MetadataEditorEvent metadataEditorEvent = (MetadataEditorEvent) eventAction;
        if (metadataEditorEvent.type == TYPE.MOVE_UP || metadataEditorEvent.type == TYPE.MOVE_DOWN) {

            ListenableList<MetadataColumn> list = getMetadataEditor().getMetadataColumnList();
            Set<Integer> setIndicesSelectedMoved = new HashSet<Integer>();
            int increment;
            int startIndex;
            int endIndex;
            ArrayList<Integer> indexOrigin = new ArrayList<Integer>();
            ArrayList<Integer> indexDestination = new ArrayList<Integer>();
            if(metadataEditorEvent.type == TYPE.MOVE_UP) {
                increment =  -1;
                startIndex = 0;
                endIndex = metadataEditorEvent.entriesIndices.length;
                
            } else {
                increment =  1;
                startIndex = metadataEditorEvent.entriesIndices.length-1;
                endIndex = -1;
            }
            
            for (int i = startIndex ;  i != endIndex; i-=increment) {
                int indice = metadataEditorEvent.entriesIndices[i];
                int newIndice = indice + increment;
                if (newIndice < 0) {
                    newIndice = 0;
                }
                if (newIndice >= list.size()) {
                    newIndice = list.size() - 1;
                }
                if (!setIndicesSelectedMoved.contains(newIndice)) {
                    indexOrigin.add(indice);
                    indexDestination.add(newIndice);
                    setIndicesSelectedMoved.add(newIndice);
                } else {
                    setIndicesSelectedMoved.add(indice);
                }
            }

            list.swapElements(indexOrigin, indexDestination);
            
        } else {
            throw new IllegalArgumentException("The type (" + metadataEditorEvent.type + ") of event does not match with the action");
        }
    }
}
