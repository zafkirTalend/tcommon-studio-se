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
package org.talend.commons.utils.data.list;

import java.util.Collection;
import java.util.List;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * @param <E> $Id$
 * 
 */
public class ListenableListEvent<E> {

    /**
     * 
     * DOC amaumont ListenableListEvent class global comment. Detailled comment <br/>
     * 
     * $Id$
     * 
     */
    public enum TYPE {
        ADDED,
        REMOVED,
        CLEARED,
        SWAPED,
        LIST_REGISTERED,
        REPLACED;
    }

    public Integer index;

    public List<Integer> indicesOrigin;

    public List<Integer> indicesTarget;

    public Object[] swapedObjects;

    public ListenableList<E> source;

    public Collection<E> addedObjects;

    public Collection<E> removedObjects;

    public TYPE type;

    /**
     * Value is true if event is fired before execution of operation, else false if event is fired after execution of
     * operation.
     */
    public boolean beforeOperation;

}
