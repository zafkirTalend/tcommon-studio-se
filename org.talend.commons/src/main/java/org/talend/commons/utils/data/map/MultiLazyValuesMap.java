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
package org.talend.commons.utils.data.map;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 */
public abstract class MultiLazyValuesMap implements Map {

    private Map map;

    /**
     * DOC amaumont CollectionsMap constructor comment.
     */
    public MultiLazyValuesMap(Map map) {
        super();
        this.map = map;
    }

    public void clear() {
        map.clear();
    }

    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    public Set<java.util.Map.Entry> entrySet() {
        return map.entrySet();
    }

    public Object get(Object key) {
        return map.get(key);
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public Set keySet() {
        return map.keySet();
    }

    public Object put(Object key, Object value) {
        Object v = map.get(key);
        if (v != null) {
            if (v instanceof List) {
                ((List) v).add(value);
            } else {
                Collection list = instanciateNewCollection();
                list.add(v);
                list.add(value);
                map.put(key, list);
            }
        } else {
            return map.put(key, value);
        }
        return v;
    }

    /**
     * DOC amaumont Comment method "instanciateNewList".
     * 
     * @return
     */
    public abstract Collection instanciateNewCollection();

    public void putAll(Map t) {
        map.putAll(t);
    }

    public Object remove(Object key) {
        return map.remove(key);
    }

    public int size() {
        return map.size();
    }

    public Collection values() {
        return map.values();
    }

    public Object removeValue(Object key, Object value) {
        Object v = map.get(key);
        if (v != null) {
            if (v instanceof List) {
                ((List) v).remove(value);
                return value;
            } else if (value.equals(v)) {
                remove(key);
                return value;
            }
            return null;
        }
        return null;
    }

    public Collection getCollection(Object key) {
        Object v = map.get(key);
        if (v != null) {
            if (v instanceof List) {
                return (Collection) v;
            } else {
                Collection list = instanciateNewCollection();
                list.add(v);
                map.put(key, list);
                return list;
            }
        } else {
            Collection list = instanciateNewCollection();
            map.put(key, list);
            return list;
        }
    }

}
