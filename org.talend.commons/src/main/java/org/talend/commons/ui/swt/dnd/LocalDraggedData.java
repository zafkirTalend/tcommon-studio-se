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
package org.talend.commons.ui.swt.dnd;

import java.util.ArrayList;
import java.util.List;

/**
 * bqian A data container for LocalDataTransfer.<br/>
 * 
 * $Id$
 * 
 */
public class LocalDraggedData {

    private List<Object> transferableEntryList = new ArrayList<Object>();

    /**
     * @param o
     * @return
     * @see java.util.List#add(java.lang.Object)
     */
    public boolean add(Object o) {
        return this.transferableEntryList.add(o);
    }

    /**
     * Getter for transferableEntryList.
     * 
     * @return the transferableEntryList
     */
    public List<Object> getTransferableEntryList() {
        return this.transferableEntryList;
    }
}
