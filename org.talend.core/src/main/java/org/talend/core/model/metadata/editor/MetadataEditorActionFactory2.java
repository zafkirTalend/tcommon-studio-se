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

import org.talend.core.ui.metadata.editor.MetadataTableEditorView2;
import org.talend.core.ui.metadata.editor.actions.AddMetadataAction2;
import org.talend.core.ui.metadata.editor.actions.CopyMetadataAction2;
import org.talend.core.ui.metadata.editor.actions.MetadataEditorAction2;
import org.talend.core.ui.metadata.editor.actions.MoveAction2;
import org.talend.core.ui.metadata.editor.actions.RemoveMetadataAction2;

/**
 * DOC amaumont class global comment. Detailled comment TGU same purpose as MetadataEditorActionFactory but uses EMF
 * model directly
 * 
 * <br/>
 * 
 * $Id$
 * 
 */
public class MetadataEditorActionFactory2 {

    private static MetadataEditorActionFactory2 instance;

    public static MetadataEditorActionFactory2 getInstance() {
        if (instance == null) {
            instance = new MetadataEditorActionFactory2();
        }
        return instance;
    }

    public MetadataEditorAction2 getAction(MetadataTableEditorView2 metadatEditorView, MetadataEditorEvent event) {
        switch (event.type) {
        case ADD:
            return new AddMetadataAction2(metadatEditorView);
        case REMOVE:
            return new RemoveMetadataAction2(metadatEditorView);
        case COPY:
        case PASTE:
            return getCopyMetadatAction(metadatEditorView);
        case MOVE_UP:
        case MOVE_DOWN:
            return new MoveAction2(metadatEditorView);
        default:
            return null;
        }
    }

    private CopyMetadataAction2 copyAction;

    public CopyMetadataAction2 getCopyMetadatAction(MetadataTableEditorView2 metadatEditorView) {
        if (copyAction == null) {
            copyAction = new CopyMetadataAction2(metadatEditorView);
        } else if (copyAction.getCurrentTableEditor() != metadatEditorView) {
            copyAction = new CopyMetadataAction2(metadatEditorView);
        }
        return copyAction;
    }

}
