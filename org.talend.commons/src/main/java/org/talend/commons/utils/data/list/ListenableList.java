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

    private List<IListenableListListener> listeners = new ArrayList<IListenableListListener>();

    private List<T> list;

    /**
     * DOC amaumont ListenableList constructor comment.
     */
    public ListenableList(List<T> list) {
        this.list = list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.List#add(java.lang.Object)
     */
    public boolean add(T o) {
        boolean returnValue = this.list.add(o);
        if (returnValue) {
            fireAddedEvent(this.list.indexOf(o), o);
        }
        return returnValue;

    }

    /**
     * DOC amaumont Comment method "fireAddedEvent".
     * 
     * @param i
     * @param o
     */
    private void fireAddedEvent(int i, T o) {
        ArrayList<T> objects = new ArrayList<T>(1);
        objects.add(o);
        fireAddedEvent(i, objects);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.List#add(int, java.lang.Object)
     */
    public void add(int index, T element) {
        this.list.add(index, element);
        fireAddedEvent(index, element);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.List#addAll(java.util.Collection)
     */
    @SuppressWarnings("unchecked")
    public boolean addAll(Collection< ? extends T> c) {
        boolean returnValue = this.list.addAll(c);
        if (returnValue) {
            fireAddedEvent(this.list.size() - c.size(), (Collection<T>) c);
        }
        return returnValue;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.List#addAll(int, java.util.Collection)
     */
    @SuppressWarnings("unchecked")
    public boolean addAll(int index, Collection< ? extends T> c) {
        boolean returnValue = this.list.addAll(index, c);
        if (returnValue) {
            fireAddedEvent(index, (Collection<T>) c);
        }
        return returnValue;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.List#clear()
     */
    public void clear() {
        this.list.clear();
        fireClearedEvent();
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
    public boolean containsAll(Collection< ? > c) {
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
                fireAddedEvent(internalListIterator.previousIndex(), o);
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
                internalListIterator.set(o);
                fireReplacedEvent(internalListIterator.previousIndex() + 1, current, o);
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
        List<T> currentList = new ArrayList<T>(1);
        currentList.add((T) removedObject);
        fireRemovedEvent(index, currentList);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.List#removeAll(java.util.Collection)
     */
    @SuppressWarnings("unchecked")
    public boolean removeAll(Collection< ? > c) {
        boolean returnValue = this.list.removeAll(c);
        if (returnValue) {
            fireRemovedEvent(null, new ArrayList(c));
        }
        return returnValue;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.List#retainAll(java.util.Collection)
     */
    public boolean retainAll(Collection< ? > c) {
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
            fireRemovedEvent(null, removedObjects);
        }
        return isListChanged;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.List#set(int, java.lang.Object)
     */
    public T set(int index, T element) {
        T replacedObject = this.list.set(index, element);
        fireReplacedEvent(index, replacedObject, element);
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
        internalSwap(index1, index2);

        ArrayList<Integer> indexList1 = new ArrayList<Integer>(1);
        indexList1.add(index1);
        ArrayList<Integer> indexList2 = new ArrayList<Integer>(1);
        indexList2.add(index2);

        fireSwapedEvent(indexList1, indexList2, new Object[] { this.list.get(index1), this.list.get(index2) });
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

    public void swapElements(List<Integer> indexOrigin, List<Integer> indexDestination) {
        if (indexOrigin.size() != indexDestination.size()) {
            throw new IllegalArgumentException("indexOrigin and indexDestination must have same length");
        }
        ArrayList<T> swapedObjects = new ArrayList<T>();
        try {

            int lstSize = indexOrigin.size();
            for (int i = 0; i < lstSize; i++) {
                Integer idxOrigin = indexOrigin.get(i);
                Integer idxDestination = indexDestination.get(i);
                swapedObjects.add(list.get(idxOrigin));
                swapedObjects.add(list.get(idxDestination));
                internalSwap(idxOrigin, idxDestination);
            }
        } catch (RuntimeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        fireSwapedEvent(indexOrigin, indexDestination, swapedObjects.toArray());
    }

    public void swapElement(Object object1, Object object2) {
        swap(this.list.indexOf(object1), this.list.indexOf(object2));
    }

    /**
     * DOC amaumont Comment method "fireAddedListener".
     * 
     * @param index
     * @param element
     * @param addedObjects
     */
    public void fireAddedEvent(Integer index, Collection<T> addedObjects) {
        ListenableListEvent<T> event = new ListenableListEvent<T>();
        event.setType(TYPE.ADDED);
        event.setIndex(index);
        event.setAddedObjects(addedObjects);
        event.setSource(this);
        fireEvent(event);
    }

    /**
     * DOC amaumont Comment method "fireAddedListener".
     * 
     * @param index
     * @param removedObject
     * @param addedObjects
     */
    public void fireRemovedEvent(Integer index, List<T> removedObjects) {
        ListenableListEvent<T> event = new ListenableListEvent<T>();
        event.setType(TYPE.REMOVED);
        event.setIndex(index);
        event.setRemovedObjects(removedObjects);
        event.setSource(this);
        fireEvent(event);
    }

    /**
     * DOC amaumont Comment method "fireAddedListener".
     * 
     * @param index
     * @param element
     */
    public void fireSwapedEvent(List<Integer> indexOrigin, List<Integer> indexDestination, Object[] swapedObjects) {
        ListenableListEvent<T> event = new ListenableListEvent<T>();
        event.setType(TYPE.SWAPED);
        event.setIndexOrigin(indexOrigin);
        event.setIndexDestination(indexDestination);
        event.setSwapedObjects(swapedObjects);
        

        event.setSource(this);
        fireEvent(event);
    }

    /**
     * DOC amaumont Comment method "fireAddedListener".
     * 
     * @param index
     * @param index
     * @param element
     */
    public void fireReplacedEvent(int index, T removedObject, T addedObject) {
        ListenableListEvent<T> event = new ListenableListEvent<T>();
        event.setType(TYPE.REPLACED);
        event.setIndex(index);
        List<T> removeObjects = new ArrayList<T>(1);
        removeObjects.add(removedObject);
        event.setRemovedObjects(removeObjects);
        
        List<T> addedObjects = new ArrayList<T>(1);
        addedObjects.add(addedObject);
        event.setRemovedObjects(addedObjects);
        
        fireEvent(event);
    }

    /**
     * DOC amaumont Comment method "fireClearedListener".
     */
    public void fireClearedEvent() {
        ListenableListEvent<T> event = new ListenableListEvent<T>();
        event.setType(TYPE.CLEARED);
        event.setSource(this);
        fireEvent(event);
    }

    /**
     * DOC amaumont Comment method "fireEvent".
     * 
     * @param event
     */
    public void fireEvent(ListenableListEvent<T> event) {
        int size = listeners.size();
        for (int i = 0; i < size; i++) {
            listeners.get(i).handleEvent(event);
        }
    }

    public void addListener(IListenableListListener listener) {
        listeners.add(listener);
    }

    public void removeListener(IListenableListListener listener) {
        listeners.remove(listener);
    }

}
