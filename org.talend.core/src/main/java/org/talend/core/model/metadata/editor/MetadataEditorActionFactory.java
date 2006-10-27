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
package org.talend.core.model.metadata.editor;

import org.talend.core.ui.metadata.editor.MetadataTableEditorView;
import org.talend.core.ui.metadata.editor.actions.AddMetadataAction;
import org.talend.core.ui.metadata.editor.actions.CopyPasteMetadataAction;
import org.talend.core.ui.metadata.editor.actions.MetadataEditorAction;
import org.talend.core.ui.metadata.editor.actions.MoveAction;
import org.talend.core.ui.metadata.editor.actions.RemoveMetadataAction;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class MetadataEditorActionFactory {

    private static MetadataEditorActionFactory instance;

    public static MetadataEditorActionFactory getInstance() {
        if (instance == null) {
            instance = new MetadataEditorActionFactory();
        }
        return instance;
    }

    public MetadataEditorAction getAction(MetadataTableEditorView metadatEditorView, MetadataEditorEvent event) {
        MetadataEditorAction action = null;
        switch (event.type) {
        case ADD:
            action = new AddMetadataAction(metadatEditorView);
            break;

        case REMOVE:
            action = new RemoveMetadataAction(metadatEditorView);
            break;

        case MOVE_UP:
        case MOVE_DOWN:
            action = new MoveAction(metadatEditorView);
            break;

        case COPY:
        case PASTE:
            action = getCopyMetadatAction(metadatEditorView);
            break;

        default:
            break;
        }
        return action;
    }

    private CopyPasteMetadataAction copyAction;

    public CopyPasteMetadataAction getCopyMetadatAction(MetadataTableEditorView metadatEditorView) {
        if (copyAction == null) {
            copyAction = new CopyPasteMetadataAction(metadatEditorView);
        } else if (copyAction.getMetadataTableEditorView() != metadatEditorView) {
            copyAction = new CopyPasteMetadataAction(metadatEditorView);
        }
        return copyAction;
    }
}
