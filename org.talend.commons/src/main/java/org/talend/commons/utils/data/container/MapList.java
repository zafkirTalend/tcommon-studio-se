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
package org.talend.commons.utils.data.container;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * @param <K> DOC smallet
 * @param <V> DOC smallet
 * 
 * $Id$
 * 
 */
public class MapList<K, V> extends HashMap<K, List<V>> implements Map<K, List<V>> {

    private static final long serialVersionUID = -6734316976728141405L;

    @Override
    public List<V> get(Object arg0) {
        List<V> list = super.get(arg0);
        if (list == null) {
            list = new ArrayList<V>();
            put((K) arg0, list);
        }
        return list;
    }

    public boolean put(K key, V value) {
        return get(key).add(value);
    }

    public void clear(K key) {
        get(key).clear();
    }

    public boolean removeAll(K key, Collection<V> c) {
        return get(key).removeAll(c);
    }
}
