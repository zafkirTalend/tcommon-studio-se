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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.talend.commons.utils.data.list.ListenableListEvent.TYPE;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * @param <T> $Id$
 * 
 */
public class ListenableList<T> implements List<T> {

    private List<OrderableWrapper<IListenableListListener>> beforeListeners = new ArrayList<OrderableWrapper<IListenableListListener>>();

    private List<OrderableWrapper<IListenableListListener>> afterListeners = new ArrayList<OrderableWrapper<IListenableListListener>>();

    private List<T> list;

    /**
     * DOC amaumont ListenableList constructor comment.
     */
    public ListenableList(List<T> list) {
        if (list == null) {
            throw new IllegalArgumentException("list can't be null");
        }
        this.list = list;
    }

    public ListenableList() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.List#add(java.lang.Object)
     */
    public boolean add(T o) {
        int index = this.list.indexOf(o);
        fireAddedEvent(index, o, true);
        boolean returnValue = this.list.add(o);
        if (returnValue) {
            fireAddedEvent(index, o, false);
        }
        return returnValue;

    }

    /**
     * DOC amaumont Comment method "fireAddedEvent".
     * 
     * @param i
     * @param o
     * @param before TODO
     */
    private void fireAddedEvent(int i, T o, boolean before) {
        ArrayList<T> objects = new ArrayList<T>(1);
        objects.add(o);
        fireAddedEvent(i, objects, null, before);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.List#add(int, java.lang.Object)
     */
    public void add(int index, T element) {
        fireAddedEvent(index, element, true);
        this.list.add(index, element);
        fireAddedEvent(index, element, false);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.List#addAll(java.util.Collection)
     */
    @SuppressWarnings("unchecked")
    public boolean addAll(Collection<? extends T> c) {
        fireAddedEvent(this.list.size(), (Collection<T>) c, null, true);
        boolean returnValue = this.list.addAll(c);
        if (returnValue) {
            fireAddedEvent(this.list.size() - c.size(), (Collection<T>) c, null, false);
        }
        return returnValue;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.List#addAll(int, java.util.Collection)
     */
    @SuppressWarnings("unchecked")
    public boolean addAll(int index, Collection<? extends T> c) {
        fireAddedEvent(index, (Collection<T>) c, null, true);
        boolean returnValue = this.list.addAll(index, c);
        if (returnValue) {
            fireAddedEvent(index, (Collection<T>) c, null, false);
        }
        return returnValue;
    }

    /**
     * 
     * 
     */
    public void addAll(List<Integer> indices, Collection<? extends T> c) {

        fireAddedEvent(null, (Collection<T>) c, indices, true);
        Iterator<Integer> iterIndice = indices.iterator();
        for (T t : c) {
            Integer indice = null;
            if (iterIndice.hasNext()) {
                indice = iterIndice.next();
            }
            if (indice != null) {
                this.list.add(indice, t);
            } else {
                this.list.add(t);
            }
        }

        fireAddedEvent(null, (Collection<T>) c, indices, false);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.List#clear()
     */
    public void clear() {
        fireClearedEvent(true);
        this.list.clear();
        fireClearedEvent(false);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.List#contains(java.lang.Object)
     */
    public boolean contains(Object o) {
        return this.list.contains(o);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.List#containsAll(java.util.Collection)
     */
    public boolean containsAll(Collection<?> c) {
        return this.list.containsAll(c);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.List#get(int)
     */
    public T get(int index) {
        return this.list.get(index);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.List#indexOf(java.lang.Object)
     */
    public int indexOf(Object o) {
        return this.list.indexOf(o);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.List#isEmpty()
     */
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.List#iterator()
     */
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private Iterator<T> internalIterator = list.iterator();

            private T current = null;

            public boolean hasNext() {
                return internalIterator.hasNext();
            }

            public T next() {
                current = internalIterator.next();
                return current;
            }

            public void remove() {
                Integer indexBeforeRemove = list.indexOf(current);
                fireBeforeRemovedEvent(indexBeforeRemove);
                internalIterator.remove();
                fireRemovedEvent(indexBeforeRemove, current);
            }

        };
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.List#lastIndexOf(java.lang.Object)
     */
    public int lastIndexOf(Object o) {
        return this.list.lastIndexOf(o);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.List#listIterator()
     */
    public ListIterator<T> listIterator() {
        return new ListIterator<T>() {

            private ListIterator<T> internalListIterator = list.listIterator();

            private T current = null;

            public void add(T o) {
                internalListIterator.add(o);
                fireAddedEvent(internalListIterator.previousIndex(), o, false);
            }

            public boolean hasPrevious() {
                return internalListIterator.hasPrevious();
            }

            public int nextIndex() {
                return internalListIterator.nextIndex();
            }

            public T previous() {
                current = internalListIterator.previous();
                return current;
            }

            public int previousIndex() {
                return internalListIterator.previousIndex();
            }

            public void set(T o) {
                fireReplacedEvent(internalListIterator.previousIndex() + 1, current, o, true);
                internalListIterator.set(o);
                fireReplacedEvent(internalListIterator.previousIndex() + 1, current, o, false);
            }

            public boolean hasNext() {
                return internalListIterator.hasNext();
            }

            public T next() {
                current = internalListIterator.next();
                return current;
            }

            public void remove() {
                Integer indexBeforeRemove = internalListIterator.previousIndex() + 1;
                fireBeforeRemovedEvent(indexBeforeRemove);
                internalListIterator.remove();
                fireRemovedEvent(indexBeforeRemove, current);
            }

        };
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.List#listIterator(int)
     */
    public ListIterator<T> listIterator(int index) {
        return this.list.listIterator(index);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.List#remove(java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    public boolean remove(Object o) {
        int index = this.list.indexOf(o);
        boolean returnValue = this.list.remove(o);
        if (returnValue) {
            fireRemovedEvent(index, (T) o);
        }
        return returnValue;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.List#remove(int)
     */
    public T remove(int index) {
        fireBeforeRemovedEvent(index);
        T removedObject = this.list.remove(index);
        fireRemovedEvent(index, removedObject);
        return removedObject;
    }

    /**
     * DOC amaumont Comment method "fireRemovedEvent".
     * 
     * @param index
     * @param removedObject
     */
    private void fireRemovedEvent(int index, T removedObject) {
        if (afterListeners.size() != 0) {
            List<T> currentList = new ArrayList<T>(1);
            currentList.add((T) removedObject);
            fireRemovedEvent(index, currentList, null, false);
        }
    }

    private void fireBeforeRemovedEvent(int index) {
        if (beforeListeners.size() != 0) {
            T removingObject = this.list.get(index);
            List<T> currentList = new ArrayList<T>(1);
            currentList.add((T) removingObject);
            fireRemovedEvent(index, currentList, null, true);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.List#removeAll(java.util.Collection)
     */
    @SuppressWarnings("unchecked")
    public boolean removeAll(Collection<?> c) {

        List<Integer> indices = getIndices(c);

        fireRemovedEvent(null, new ArrayList(c), indices, true);
        boolean returnValue = this.list.removeAll(c);
        if (returnValue) {
            fireRemovedEvent(null, new ArrayList(c), indices, false);
        }
        return returnValue;
    }

    /**
     * DOC amaumont Comment method "getIndices".
     * 
     * @param c
     */
    private List<Integer> getIndices(Collection<?> c) {
        List<Integer> indices = new ArrayList<Integer>(c.size());
        for (Object bean : c) {
            int i = this.list.indexOf(bean);
            if (i != -1) {
                indices.add(i);
            } else {
                indices.add(null);
            }
        }
        return indices;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.List#retainAll(java.util.Collection)
     */
    public boolean retainAll(Collection<?> c) {
        List<T> all = new ArrayList<T>();
        all.addAll(this.list);
        boolean isListChanged = this.list.retainAll(c);
        if (isListChanged) {
            int size = all.size();
            List<T> removedObjects = new ArrayList<T>();
            for (int i = 0; i < size; i++) {
                T removedObject = all.get(i);
                if (this.list.indexOf(removedObject) < 0) {
                    removedObjects.add(removedObject);
                }
            }
            fireRemovedEvent(null, removedObjects, null, false);
        }
        return isListChanged;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.List#set(int, java.lang.Object)
     */
    public T set(int index, T element) {
        fireReplacedEvent(index, null, element, true);
        T replacedObject = this.list.set(index, element);
        fireReplacedEvent(index, replacedObject, element, false);
        return replacedObject;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.List#size()
     */
    public int size() {
        return this.list.size();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.List#subList(int, int)
     */
    public List<T> subList(int fromIndex, int toIndex) {
        return this.list.subList(fromIndex, toIndex);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.List#toArray()
     */
    public Object[] toArray() {
        return this.list.toArray();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.List#toArray(T[])
     */
    @SuppressWarnings("hiding")
    public <T> T[] toArray(T[] a) {
        return this.list.toArray(a);
    }

    public void swap(int index1, int index2) {

        ArrayList<Integer> indexList1 = new ArrayList<Integer>(1);
        ArrayList<Integer> indexList2 = new ArrayList<Integer>(1);
        if (beforeListeners.size() != 0) {
            fireSwapedEvent(indexList1, indexList2, new Object[] { this.list.get(index1), this.list.get(index2) }, true);
        }

        internalSwap(index1, index2);

        indexList1.add(index1);
        indexList2.add(index2);

        fireSwapedEvent(indexList1, indexList2, new Object[] { this.list.get(index1), this.list.get(index2) }, false);
    }

    private void internalSwap(int index1, int index2) {
        if (index1 == index2) {
            return;
        }
        // some list implementations don't allow null elements / duplicate elements so we can't swap elements in one
        // statement :(
        T temp1 = this.get(index1);
        T temp2 = this.get(index2);
        if (index1 > index2) {
            list.remove(index1);
            list.remove(index2);
            list.add(index2, temp1);
            list.add(index1, temp2);
        } else {
            list.remove(index2);
            list.remove(index1);
            list.add(index1, temp2);
            list.add(index2, temp1);
        }
    }

    public void swapElements(List<Integer> indicesOrigin, List<Integer> indicesTarget) {
        if (indicesOrigin.size() != indicesTarget.size()) {
            throw new IllegalArgumentException("indexOrigin and indexDestination must have same length");
        }
        ArrayList<T> swapedObjects = new ArrayList<T>();
        fireSwapedEvent(indicesOrigin, indicesTarget, null, true);
        int lstSize = indicesOrigin.size();
        for (int i = 0; i < lstSize; i++) {
            Integer idxOrigin = indicesOrigin.get(i);
            Integer idxDestination = indicesTarget.get(i);
            swapedObjects.add(list.get(idxOrigin));
            swapedObjects.add(list.get(idxDestination));
            internalSwap(idxOrigin, idxDestination);
        }
        fireSwapedEvent(indicesOrigin, indicesTarget, swapedObjects.toArray(), false);
    }

    public void swapElement(T object1, T object2) {
        swap(this.list.indexOf(object1), this.list.indexOf(object2));
    }

    /**
     * DOC amaumont Comment method "fireAddedListener".
     * 
     * @param index
     * @param addedObjects
     * @param indicesTarget TODO
     * @param before TODO
     * @param element
     */
    public void fireAddedEvent(Integer index, Collection<T> addedObjects, List<Integer> indicesTarget, boolean before) {
        ListenableListEvent<T> event = new ListenableListEvent<T>();
        event.type = TYPE.ADDED;
        event.index = index;
        event.indicesTarget = indicesTarget;
        event.addedObjects = addedObjects;
        event.source = this;
        event.beforeOperation = before;
        fireEvent(event);
    }

    /**
     * DOC amaumont Comment method "fireAddedListener".
     * 
     * @param index
     * @param before
     * @param removedObject
     * @param addedObjects
     */
    public void fireRemovedEvent(Integer index, List<T> removedObjects, List<Integer> indices, boolean before) {
        ListenableListEvent<T> event = new ListenableListEvent<T>();
        event.type = TYPE.REMOVED;
        event.index = index;
        event.indicesOrigin = indices;
        event.removedObjects = removedObjects;
        event.source = this;
        event.beforeOperation = before;
        fireEvent(event);
    }

    /**
     * DOC amaumont Comment method "fireAddedListener".
     * 
     * @param before
     * 
     * @param index
     * @param element
     */
    public void fireSwapedEvent(List<Integer> indexOrigin, List<Integer> indexTarget, Object[] swapedObjects, boolean before) {
        ListenableListEvent<T> event = new ListenableListEvent<T>();
        event.type = TYPE.SWAPED;
        event.indicesOrigin = indexOrigin;
        event.indicesTarget = indexTarget;
        event.swapedObjects = swapedObjects;
        event.beforeOperation = before;
        event.source = this;
        fireEvent(event);
    }

    /**
     * DOC amaumont Comment method "fireAddedListener".
     * 
     * @param index
     * @param index
     * @param before TODO
     * @param element
     */
    public void fireReplacedEvent(int index, T removedObject, T addedObject, boolean before) {
        ListenableListEvent<T> event = new ListenableListEvent<T>();
        event.type = TYPE.REPLACED;
        event.index = index;
        List<T> removeObjects = new ArrayList<T>(1);
        removeObjects.add(removedObject);
        event.removedObjects = removeObjects;

        List<T> addedObjects = new ArrayList<T>(1);
        addedObjects.add(addedObject);
        event.removedObjects = addedObjects;
        event.beforeOperation = before;

        fireEvent(event);
    }

    /**
     * DOC amaumont Comment method "fireClearedListener".
     * 
     * @param before TODO
     */
    public void fireClearedEvent(boolean before) {
        ListenableListEvent<T> event = new ListenableListEvent<T>();
        event.type = TYPE.CLEARED;
        event.source = this;
        event.beforeOperation = before;
        fireEvent(event);
    }

    /**
     * DOC amaumont Comment method "fireClearedListener".
     * 
     * @param before TODO
     */
    public void fireListRegisteredEvent(boolean before) {
        ListenableListEvent<T> event = new ListenableListEvent<T>();
        event.type = TYPE.LIST_REGISTERED;
        event.source = this;
        event.beforeOperation = before;
        fireEvent(event);
    }

    /**
     * DOC amaumont Comment method "fireEvent".
     * 
     * @param event
     */
    public void fireEvent(ListenableListEvent<T> event) {
        List<OrderableWrapper<IListenableListListener>> listeners = getCurrentListeners(event.beforeOperation);

        int size = listeners.size();
        for (int i = 0; i < size; i++) {
            listeners.get(i).getBean().handleEvent(event);
        }
    }

    public void addPostOperationListener(IListenableListListener listener) {
        addListener(listener, false);
    }

    public void addBeforeOperationListener(IListenableListListener listener) {
        addListener(listener, true);
    }

    public void addPostOperationListener(int orderCall, IListenableListListener listener) {
        addListener(orderCall, listener, false);
    }

    public void addBeforeOperationListener(int orderCall, IListenableListListener listener) {
        addListener(orderCall, listener, true);
    }

    /**
     * 
     * When you call this method priorityCalled is set to 50.
     * 
     * @param listener
     */
    private void addListener(IListenableListListener listener, boolean before) {
        addListener(50, listener, before);
    }

    /**
     * 
     * @param listener
     * @param orderCall listeners will be called in ascendant order of priorityCalled (low values in first, high values
     * in last). Can be negative.
     */
    private void addListener(int orderCall, IListenableListListener listener, boolean before) {
        OrderableWrapper<IListenableListListener> prioritizedListenerWrapper = new OrderableWrapper<IListenableListListener>(orderCall,
                listener);
        List<OrderableWrapper<IListenableListListener>> listeners = getCurrentListeners(before);
        listeners.add(prioritizedListenerWrapper);
        Collections.sort(listeners);
    }

    /**
     * DOC amaumont Comment method "getCurrentListeners".
     * 
     * @param before
     * @return
     */
    private List<OrderableWrapper<IListenableListListener>> getCurrentListeners(boolean before) {
        List<OrderableWrapper<IListenableListListener>> listeners;
        if (before) {
            listeners = beforeListeners;
        } else {
            listeners = afterListeners;
        }
        return listeners;
    }

    public void removeListener(IListenableListListener listener) {
        OrderableWrapper<IListenableListListener> prioritizedListenerWrapper = new OrderableWrapper<IListenableListListener>(0, listener);
        if (!afterListeners.remove(prioritizedListenerWrapper)) {
            beforeListeners.remove(prioritizedListenerWrapper);
        }
    }

    /**
     * Getter for list.
     * 
     * @return the list
     */
    public List<T> getOriginaList() {
        return this.list;
    }

    /**
     * 
     * Register list.
     * 
     * @param list
     */
    public void registerList(List<T> list) {
        fireListRegisteredEvent(true);
        this.list = list;
        fireListRegisteredEvent(false);
    }

    /**
     * DOC amaumont Comment method "isListRegistered".
     * 
     * @return true if list has been registered, else false
     */
    public boolean isListRegistered() {
        return list != null;
    }

}
