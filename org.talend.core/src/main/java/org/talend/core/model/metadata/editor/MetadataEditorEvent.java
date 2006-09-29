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

import java.util.ArrayList;
import java.util.List;

import org.talend.core.model.action.IEventAction;


/**
 * DOC amaumont  class global comment. Detailled comment
 * <br/>
 *
 * $Id$
 *
 */
public class MetadataEditorEvent implements IEventAction {

    /**
     * 
     * DOC amaumont MetadataEditorEvent class global comment. Detailled comment
     * <br/>
     *
     * $Id$
     *
     */
    public enum TYPE {
        NEW_METADATA_TABLE,
        ADD,
        REMOVE,
        MOVE_UP,
        MOVE_DOWN,
        COPY,
        PASTE, 
        CUT, 
        METADATA_NAME_VALUE_CHANGED,
        METADATA_KEY_VALUE_CHANGED,
        ;
    }

    public TYPE type;
    
    public List<Object> entries = new ArrayList<Object>();
    
    public int[] entriesIndices = new int[0];
    
    public int indexStartInsert = -1;
    
    public Object previousValue;
    
    public Object newValue;

    
    
    public MetadataEditorEvent() {
        super();
    }



    public MetadataEditorEvent(TYPE type) {
        super();
        this.type = type;
    }
    

    
}
