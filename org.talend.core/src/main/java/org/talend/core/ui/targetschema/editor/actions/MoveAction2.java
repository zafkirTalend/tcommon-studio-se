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
package org.talend.core.ui.targetschema.editor.actions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.talend.commons.utils.data.list.ListenableList;
import org.talend.core.model.action.IEventAction;
import org.talend.core.model.metadata.builder.connection.SchemaTarget;
import org.talend.core.model.targetschema.editor.TargetSchemaEditorEvent;
import org.talend.core.model.targetschema.editor.TargetSchemaEditorEvent.TYPE;
import org.talend.core.ui.targetschema.editor.TargetSchemaTableEditorView2;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class MoveAction2 extends TargetSchemaEditorAction2 {

    /**
     * DOC amaumont MoveUpAction constructor comment.
     * 
     * @param targetSchemaEditorView
     */
    public MoveAction2(TargetSchemaTableEditorView2 targetSchemaEditorView) {
        super(targetSchemaEditorView);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.mapper.actions.targetSchemaeditor.TargetSchemaEditorAction#run(org.talend.designer.mapper.actions.IMapperEvent)
     */
    @Override
    public void run(IEventAction eventAction) {
        TargetSchemaEditorEvent targetSchemaEditorEvent = (TargetSchemaEditorEvent) eventAction;
        if (targetSchemaEditorEvent.type == TYPE.MOVE_UP || targetSchemaEditorEvent.type == TYPE.MOVE_DOWN) {

            ListenableList<SchemaTarget> list = getTargetSchemaEditor().getSchemaTargetList();
            Set<Integer> setIndicesSelectedMoved = new HashSet<Integer>();
            int increment;
            int startIndex;
            int endIndex;
            ArrayList<Integer> indexOrigin = new ArrayList<Integer>();
            ArrayList<Integer> indexDestination = new ArrayList<Integer>();
            if (targetSchemaEditorEvent.type == TYPE.MOVE_UP) {
                increment = -1;
                startIndex = 0;
                endIndex = targetSchemaEditorEvent.entriesIndices.length;

            } else {
                increment = 1;
                startIndex = targetSchemaEditorEvent.entriesIndices.length - 1;
                endIndex = -1;
            }

            for (int i = startIndex; i != endIndex; i -= increment) {
                int indice = targetSchemaEditorEvent.entriesIndices[i];
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
            throw new IllegalArgumentException("The type (" + targetSchemaEditorEvent.type
                    + ") of event does not match with the action");
        }
    }
}
