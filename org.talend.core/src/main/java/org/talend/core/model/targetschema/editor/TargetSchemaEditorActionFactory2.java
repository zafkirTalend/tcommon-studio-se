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
package org.talend.core.model.targetschema.editor;

import org.talend.core.ui.targetschema.editor.TargetSchemaTableEditorView2;
import org.talend.core.ui.targetschema.editor.actions.AddTargetSchemaAction2;
import org.talend.core.ui.targetschema.editor.actions.CopyTargetSchemaAction2;
import org.talend.core.ui.targetschema.editor.actions.MoveAction2;
import org.talend.core.ui.targetschema.editor.actions.RemoveTargetSchemaAction2;
import org.talend.core.ui.targetschema.editor.actions.TargetSchemaEditorAction2;

/**
 * DOC cantoine class global comment. Detailled comment TGU same purpose as MetadataEditorActionFactory but uses EMF
 * model directly
 * 
 * <br/>
 * 
 * $Id$
 * 
 */
public class TargetSchemaEditorActionFactory2 {

    private static TargetSchemaEditorActionFactory2 instance;

    public static TargetSchemaEditorActionFactory2 getInstance() {
        if (instance == null) {
            instance = new TargetSchemaEditorActionFactory2();
        }
        return instance;
    }

    public TargetSchemaEditorAction2 getAction(TargetSchemaTableEditorView2 targetSchemaEditorView, TargetSchemaEditorEvent event) {
        switch (event.type) {
        case ADD:
            return new AddTargetSchemaAction2(targetSchemaEditorView);
        case REMOVE:
            return new RemoveTargetSchemaAction2(targetSchemaEditorView);
        case COPY:
        case PASTE:
            return getCopyTargetSchemaAction(targetSchemaEditorView);
        case MOVE_UP:
        case MOVE_DOWN:
            return new MoveAction2(targetSchemaEditorView);
        default:
            return null;
        }
    }

    private CopyTargetSchemaAction2 copyAction;

    public CopyTargetSchemaAction2 getCopyTargetSchemaAction(TargetSchemaTableEditorView2 targetSchemaEditorView) {
        if (copyAction == null) {
            copyAction = new CopyTargetSchemaAction2(targetSchemaEditorView);
        } else if (copyAction.getCurrentTableEditor() != targetSchemaEditorView) {
            copyAction = new CopyTargetSchemaAction2(targetSchemaEditorView);
        }
        return copyAction;
    }

}
