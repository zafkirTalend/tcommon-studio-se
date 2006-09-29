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

/**
 * PTODO SML Delete this class.<br/>
 * 
 * @param <K> - DOC SML
 * @param <V> - type the container manages
 * 
 * $Id$
 * 
 */
public class Content<K, V> {

    private Container<K, V> parent;

    private V content;

    private K key;

    /**
     * DOC smallet Content constructor comment.
     * 
     * @param parent
     * @param content
     * @param key
     */
    public Content(Container<K, V> parent, V content, K key) {
        super();
        this.parent = parent;
        this.content = content;
        this.key = key;
    }

    /**
     * Getter for content.
     * 
     * @return the content
     */
    public V getContent() {
        return this.content;
    }

    /**
     * Sets the content.
     * 
     * @param content the content to set
     */
    public void setContent(V content) {
        this.content = content;
    }

    /**
     * Getter for key.
     * 
     * @return the key
     */
    public Object getKey() {
        return this.key;
    }

    /**
     * Sets the key.
     * 
     * @param key the key to set
     */
    public void setKey(K key) {
        this.key = key;
    }

    /**
     * Getter for parent.
     * 
     * @return the parent
     */
    public Container<K, V> getParent() {
        return this.parent;
    }

    /**
     * Sets the parent.
     * 
     * @param parent the parent to set
     */
    public void setParent(Container<K, V> parent) {
        this.parent = parent;
    }

}
