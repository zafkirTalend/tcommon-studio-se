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
package org.talend.commons.utils.data.container;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IPath;

/**
 * PTODO SML Delete this class.
 * 
 * @param <K> - DOC SML
 * @param <V> - type the container manages
 * 
 * $Id$
 * 
 */
public class RootContainer<K, V> extends Container<K, V> {

    private static final String DEFAULT_NAME = "Root";

    private ContentList<K, V> rootMembers = new ContentList<K, V>();

    private Map<String, Object> properties = new Hashtable<String, Object>();

    public RootContainer() {
        this(DEFAULT_NAME);
    }

    /**
     * This only public constructor is used to instantiate the structure root. Sub-containers must be create using
     * addSubContainer method.
     * 
     * @param label - the label of the root
     */
    public RootContainer(String label) {
        super(label, null);
    }

    /**
     * Getter for rootMembers. Returns null for no root container.
     * 
     * @return the rootMembers
     */
    public ContentList<K, V> getAbsoluteMembers() {
        return this.rootMembers;
    }

    /**
     * Retrieve a member by its key in the entire structure.
     * 
     * @param key - the key of the member to retrieve
     * @return the member with this id
     */
    public V getAbsoluteMember(K key) {
        Content<K, V> object = rootMembers.getObject(key);
        if (object == null) {
            return null;
        }
        return object.getContent();
    }

    public boolean updateAbsoluteMember(K key, V value) {
        if (!contains(key)) {
            return false;
        } else {
            Content<K, V> object = rootMembers.getObject(key);
            object.setContent(value);
            return true;
        }
    }

    /**
     * DOC smallet Comment method "contains".
     * 
     * @param key
     */
    public boolean contains(K key) {
        return (getAbsoluteMember(key) == null);
    }

    /**
     * Returns the number of members in the entire structure.
     * 
     * @return the number of members
     */
    public int absoluteSize() {
        return rootMembers.size();
    }

    /**
     * Returns a set view of the ids contained in this structure.
     * 
     * @return a set view of the ids contained in this structure.
     */
    public Set absoluteKeySet() {
        return rootMembers.keySet();
    }

    /**
     * Allows parent retriving on a member. Returns null il this child cannot be found in the structure.
     * 
     * @param child - the child to retrieve parent
     * @return the parent container of the child
     */
    public Container<K, V> getParent(Object object) {
        Content<K, V> cont = rootMembers.getObject(object);
        if (cont == null) {
            return null;
        }
        return cont.getParent();
    }

    public Container<K, V> getAbsoluteContainer(IPath path) {
        Container<K, V> toReturn = this;
        for (int i = 0; i < path.segmentCount(); i++) {
            toReturn = toReturn.getSubContainer(path.segment(i));
            if (toReturn == null) {
                return null;
            }
        }
        return toReturn;
    }

    /**
     * Getter for properties.
     * 
     * @return the properties
     */
    public Map<String, Object> getProperties() {
        return this.properties;
    }

    /**
     * Sets the properties.
     * 
     * @param properties the properties to set
     */
    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

}
