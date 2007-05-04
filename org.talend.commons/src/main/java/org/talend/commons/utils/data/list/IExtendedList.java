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
package org.talend.commons.utils.data.list;

import java.util.Collection;
import java.util.List;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 * @param <T> type of beans in list
 */
public interface IExtendedList<T> extends List<T> {

    public void swap(int index1, int index2);

    public void swapElements(List<Integer> indicesOrigin, List<Integer> indicesTarget);

    public void swapElement(T object1, T object2);

    /**
     * Getter for useEquals.
     * 
     * @return the useEquals
     */
    public boolean isUseEquals();

    /**
     * Sets the useEquals.
     * 
     * @param useEquals the useEquals to set
     */
    public void setUseEquals(boolean useEquals);

    /**
     * 
     * 
     */
    @SuppressWarnings("unchecked")
    public void addAll(List<Integer> indices, Collection<? extends T> c);

}
