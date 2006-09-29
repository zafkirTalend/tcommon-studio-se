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
        REPLACED;
    }

    private Integer index;

    private List<Integer> indexOrigin;

    private List<Integer> indexDestination;

    private Object[] swapedObjects;

    private ListenableList<E> source;

    private Collection<E> addedObjects;

    private Collection<E> removedObjects;

    private TYPE type;

    /**
     * Getter for addedObjects.
     * 
     * @return the addedObjects
     */
    public Collection<E> getAddedObjects() {
        return this.addedObjects;
    }

    /**
     * Sets the addedObjects.
     * 
     * @param addedObjects the addedObjects to set
     */
    public void setAddedObjects(Collection<E> addedObjects) {
        this.addedObjects = addedObjects;
    }

    /**
     * Getter for index.
     * 
     * @return the index
     */
    public Integer getIndex() {
        return this.index;
    }

    /**
     * Sets the index.
     * 
     * @param index the index to set
     */
    public void setIndex(Integer index) {
        this.index = index;
    }

    /**
     * Getter for indexDestination.
     * 
     * @return the indexDestination
     */
    public List<Integer> getIndexDestination() {
        return this.indexDestination;
    }

    /**
     * Sets the indexDestination.
     * 
     * @param indexDestination the indexDestination to set
     */
    public void setIndexDestination(List<Integer> indexDestination) {
        this.indexDestination = indexDestination;
    }

    /**
     * Getter for indexOrigin.
     * 
     * @return the indexOrigin
     */
    public List<Integer> getIndexOrigin() {
        return this.indexOrigin;
    }

    /**
     * Sets the indexOrigin.
     * 
     * @param indexOrigin the indexOrigin to set
     */
    public void setIndexOrigin(List<Integer> indexOrigin) {
        this.indexOrigin = indexOrigin;
    }

    /**
     * Getter for removedObjects.
     * 
     * @return the removedObjects
     */
    public Collection<E> getRemovedObjects() {
        return this.removedObjects;
    }

    /**
     * Sets the removedObjects.
     * 
     * @param removedObjects the removedObjects to set
     */
    public void setRemovedObjects(Collection<E> removedObjects) {
        this.removedObjects = removedObjects;
    }

    /**
     * Getter for source.
     * 
     * @return the source
     */
    public ListenableList<E> getSource() {
        return this.source;
    }

    /**
     * Sets the source.
     * 
     * @param source the source to set
     */
    public void setSource(ListenableList<E> source) {
        this.source = source;
    }

    /**
     * Getter for swapedObjects.
     * 
     * @return the swapedObjects
     */
    public Object[] getSwapedObjects() {
        return this.swapedObjects;
    }

    /**
     * Sets the swapedObjects.
     * 
     * @param swapedObjects the swapedObjects to set
     */
    public void setSwapedObjects(Object[] swapedObjects) {
        this.swapedObjects = swapedObjects;
    }

    /**
     * Getter for type.
     * 
     * @return the type
     */
    public TYPE getType() {
        return this.type;
    }

    /**
     * Sets the type.
     * 
     * @param type the type to set
     */
    public void setType(TYPE type) {
        this.type = type;
    }
}
