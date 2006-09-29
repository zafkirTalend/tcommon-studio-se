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

import java.util.List;


/**
 * DOC amaumont  class global comment. Detailled comment
 * <br/>
 *
 * $Id$
 *
 */
public final class ListUtils {
    
    private ListUtils() {
        // do not use
    }
    
    /**
     * Swap object1 with object2 in the list.
     * @param list
     * @param object1
     * @param object2
     */
    public static void swap(List list, Object object1, Object object2) {
        int indexObject1 = list.indexOf(object1);
        int indexObject2 = list.indexOf(object2);
        list.set(indexObject2, object1);
        list.set(indexObject1, object2);
    }
    
}
