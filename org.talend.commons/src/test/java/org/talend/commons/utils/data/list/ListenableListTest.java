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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.talend.commons.utils.data.list.ListenableListEvent.TYPE.ADDED;
import static org.talend.commons.utils.data.list.ListenableListEvent.TYPE.CLEARED;
import static org.talend.commons.utils.data.list.ListenableListEvent.TYPE.REMOVED;
import static org.talend.commons.utils.data.list.ListenableListEvent.TYPE.REPLACED;
import static org.talend.commons.utils.data.list.ListenableListEvent.TYPE.SWAPED;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.junit.Before;
import org.junit.Test;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class ListenableListTest {

    /**
     * 
     */
    private static final int A_3 = 3;

    private ListenableList<String> listenedList;

    private boolean added;

    private boolean cleared;

    private boolean removed;

    private boolean swaped;

    private boolean replaced;

    protected ListenableListEvent currentEvent;

    private IListenableListListener listener;

    /**
     * DOC amaumont Comment method "init".
     */
    @Before
    public void init() {

        ArrayList<String> list = new ArrayList<String>();
        listenedList = new ListenableList<String>(list);

    }

    /**
     * Test method for {@link org.talend.commons.utils.data.list.ListenableList#ListenableList(java.util.List)}.
     */
    @Test
    public void testListenableList() {

    }

    /**
     * Test method for {@link org.talend.commons.utils.data.list.ListenableList#add(java.lang.Object)}.
     */
    @Test
    public void testAddT() {
        assertFalse(added);
        listenedList.add("A");
        assertFalse(added);
        assertEquals("A", listenedList.get(0));
        initListener();
        assertFalse(added);
        listenedList.add("B");
        assertEquals(currentEvent.index, 1);
        assertTrue(currentEvent.addedObjects.contains("B"));
        assertEquals("B", listenedList.get(1));
        assertTrue(added);
        added = false;
        listenedList.add("C");
        assertEquals("C", listenedList.get(2));
        assertTrue(added);
    }

    /**
     * Test method for {@link org.talend.commons.utils.data.list.ListenableList#add(int, java.lang.Object)}.
     */
    @Test
    public void testAddIntT() {
        assertFalse(added);
        listenedList.add(0, "A");
        assertFalse(added);
        assertEquals("A", listenedList.get(0));
        initListener();
        assertFalse(added);
        listenedList.add(1, "B");
        assertEquals("B", listenedList.get(1));
        assertTrue(added);
        added = false;
        listenedList.add(2, "C");
        assertEquals("C", listenedList.get(2));
        assertTrue(added);
    }

    /**
     * Test method for {@link org.talend.commons.utils.data.list.ListenableList#addAll(java.util.Collection)}.
     */
    @Test
    public void testAddAllCollectionOfQextendsT() {
        listenedList.add(0, "A");
        assertFalse(added);
        ArrayList<String> listToAdd = new ArrayList<String>();
        listToAdd.add("B");
        listToAdd.add("C");

        initListener();
        assertFalse(added);
        listenedList.addAll(listToAdd);
        assertEquals("B", listenedList.get(1));
        assertTrue(added);
        added = false;
        listenedList.addAll(listToAdd);
        assertEquals("B", listenedList.get(A_3));
        assertTrue(added);
    }

    /**
     * Test method for {@link org.talend.commons.utils.data.list.ListenableList#addAll(int, java.util.Collection)}.
     */
    @Test
    public void testAddAllIntCollectionOfQextendsT() {
        listenedList.add(0, "A");
        assertFalse(added);
        ArrayList<String> listToAdd = new ArrayList<String>();
        listToAdd.add("B");
        listToAdd.add("C");

        initListener();
        assertFalse(added);
        listenedList.addAll(0, listToAdd);
        assertEquals("A", listenedList.get(2));
        assertTrue(added);
        added = false;
        listenedList.addAll(1, listToAdd);
        assertEquals("B", listenedList.get(1));
        assertTrue(added);
    }

    /**
     * Test method for {@link org.talend.commons.utils.data.list.ListenableList#clear()}.
     */
    @Test
    public void testClear() {
        listenedList.add(0, "A");
        assertFalse(added);
        ArrayList<String> listToAdd = new ArrayList<String>();
        listToAdd.add("B");
        listToAdd.add("C");
        listenedList.clear();
        assertFalse(cleared);

        initListener();
        assertFalse(cleared);
        listenedList.addAll(listToAdd);
        assertFalse(cleared);
        listenedList.clear();
        assertTrue(cleared);
        cleared = false;
        listenedList.addAll(listToAdd);
        listenedList.clear();
        assertTrue(cleared);
    }

    /**
     * Test method for {@link org.talend.commons.utils.data.list.ListenableList#contains(java.lang.Object)}.
     */
    @Test
    public void testContains() {
        listenedList.add("A");
        listenedList.add("B");
        assertTrue(listenedList.contains("B"));
    }

    /**
     * Test method for {@link org.talend.commons.utils.data.list.ListenableList#containsAll(java.util.Collection)}.
     */
    @Test
    public void testContainsAll() {
        listenedList.add("A");
        ArrayList<String> listToAdd = new ArrayList<String>();
        listToAdd.add("B");
        listToAdd.add("C");
        listenedList.addAll(listToAdd);
        listenedList.add("D");
        assertTrue(listenedList.containsAll(listToAdd));
    }

    /**
     * Test method for {@link org.talend.commons.utils.data.list.ListenableList#get(int)}.
     */
    @Test
    public void testGet() {
        listenedList.add("A");
        listenedList.add("B");
        assertTrue("B".equals(listenedList.get(1)));
    }

    /**
     * Test method for {@link org.talend.commons.utils.data.list.ListenableList#indexOf(java.lang.Object)}.
     */
    @Test
    public void testIndexOf() {
        listenedList.add("A");
        listenedList.add("B");
        assertTrue(listenedList.indexOf("B") == 1);
    }

    /**
     * Test method for {@link org.talend.commons.utils.data.list.ListenableList#isEmpty()}.
     */
    @Test
    public void testIsEmpty() {
        assertTrue(listenedList.isEmpty());
    }

    /**
     * Test method for {@link org.talend.commons.utils.data.list.ListenableList#iterator()}.
     */
    @Test
    public void testIterator() {
        listenedList.add("A");
        listenedList.add("B");
        listenedList.add("C");
        Iterator<String> iterator = listenedList.iterator();
        assertFalse(removed);
        for (int i = 0; iterator.hasNext(); i++) {
            assertEquals(listenedList.get(i), iterator.next());
            if (i == 1) {
                iterator.remove();
                i--;
                assertFalse(removed);
            }
        }
        initListener();
        for (int i = 0; iterator.hasNext(); i++) {
            iterator.next();
            if (i == 1) {
                iterator.remove();
                assertTrue(removed);
            }
        }
    }

    /**
     * Test method for {@link org.talend.commons.utils.data.list.ListenableList#lastIndexOf(java.lang.Object)}.
     */
    @Test
    public void testLastIndexOf() {
        listenedList.add("A");
        listenedList.add("B");
        listenedList.add("C");
        assertEquals(1, listenedList.indexOf("B"));
    }

    /**
     * Test method for {@link org.talend.commons.utils.data.list.ListenableList#listIterator()}.
     */
    @Test
    public void testListIterator() {
        listenedList.add("A");
        listenedList.add("B");
        listenedList.add("C");
        ListIterator<String> listIterator = listenedList.listIterator();
        assertFalse(removed);
        assertTrue(listIterator.hasNext());
        assertEquals(listenedList.get(0), listIterator.next());
        assertEquals(listenedList.get(1), listIterator.next());
        listIterator.remove();
        assertFalse(removed);

        initListener();

        assertTrue(listIterator.hasPrevious());

        assertEquals(listIterator.next(), "C");

        assertFalse(added);
        listIterator.add("D");
        assertEquals(listIterator.previousIndex(), 2);
        assertEquals(listIterator.nextIndex(), A_3);
        assertTrue(currentEvent.addedObjects.contains("D"));
        assertEquals(currentEvent.index, 2);
        assertTrue(added);

        assertFalse(listIterator.hasNext());
        assertTrue(listIterator.hasPrevious());
        assertEquals(listIterator.previous(), "D");

        assertFalse(removed);
        listIterator.remove();
        assertTrue(currentEvent.removedObjects.contains("D"));
        assertEquals(currentEvent.index, 2);
        assertTrue(removed);

        assertFalse(listIterator.hasNext());
        assertTrue(listIterator.hasPrevious());
        assertEquals(listIterator.previous(), "C");

        assertFalse(replaced);
        listIterator.set("E");
        assertTrue(currentEvent.removedObjects.contains("C"));
        assertTrue(currentEvent.addedObjects.contains("E"));
        assertEquals(currentEvent.index, 1);
        assertTrue(replaced);
    }

    /**
     * Test method for {@link org.talend.commons.utils.data.list.ListenableList#listIterator(int)}.
     */
    @Test
    public void testListIteratorInt() {
        listenedList.add("A");
        listenedList.add("B");
        listenedList.add("C");
        ListIterator<String> listIterator = listenedList.listIterator(1);
        assertEquals(listIterator.previousIndex(), 0);
        assertEquals(listIterator.nextIndex(), 1);
    }

    /**
     * Test method for {@link org.talend.commons.utils.data.list.ListenableList#remove(java.lang.Object)}.
     */
    @Test
    public void testRemoveObject() {
        listenedList.add("A");
        listenedList.add("B");
        listenedList.add("C");

        assertFalse(removed);
        listenedList.remove("B");
        assertFalse(removed);
        listenedList.add("D");

        initListener();

        assertFalse(removed);
        listenedList.remove("D");
        assertTrue(removed);

        assertTrue(currentEvent.removedObjects.contains("D"));
        assertEquals(currentEvent.index, 2);

    }

    /**
     * Test method for {@link org.talend.commons.utils.data.list.ListenableList#remove(int)}.
     */
    @Test
    public void testRemoveInt() {
        listenedList.add("A");
        listenedList.add("B");
        listenedList.add("C");

        assertFalse(removed);
        listenedList.remove(1);
        assertFalse(removed);
        listenedList.add("D");

        initListener();

        assertFalse(removed);
        listenedList.remove(2);
        assertTrue(removed);

        assertTrue(currentEvent.removedObjects.contains("D"));
        assertEquals(currentEvent.index, 2);

    }

    /**
     * Test method for {@link org.talend.commons.utils.data.list.ListenableList#removeAll(java.util.Collection)}.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testRemoveAll() {
        listenedList.add("A");
        listenedList.add("B");
        listenedList.add("C");
        listenedList.add("D");

        assertFalse(removed);
        List<String> objectsToRemove = new ArrayList<String>();
        objectsToRemove.add("B");
        objectsToRemove.add("D");

        listenedList.removeAll(objectsToRemove);
        assertFalse(removed);
        listenedList.add(1, "B");
        listenedList.add("D");

        initListener();

        assertFalse(removed);
        listenedList.removeAll(objectsToRemove);
        assertTrue(removed);

        Iterator<String> iterator = currentEvent.removedObjects.iterator();
        assertEquals(iterator.next(), "B");
        assertEquals(iterator.next(), "D");
    }

    /**
     * Test method for {@link org.talend.commons.utils.data.list.ListenableList#retainAll(java.util.Collection)}.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testRetainAll() {
        listenedList.add("A");
        listenedList.add("B");
        listenedList.add("C");
        listenedList.add("D");

        assertFalse(removed);
        List<String> objectsToKeep = new ArrayList<String>();
        objectsToKeep.add("B");
        objectsToKeep.add("D");

        listenedList.retainAll(objectsToKeep);
        assertFalse(removed);
        listenedList.add(0, "A");
        listenedList.add(2, "C");

        initListener();

        assertFalse(removed);
        listenedList.retainAll(objectsToKeep);
        assertTrue(removed);

        Iterator<String> iterator = currentEvent.removedObjects.iterator();
        assertEquals(iterator.next(), "A");
        assertEquals(iterator.next(), "C");
    }

    /**
     * Test method for {@link org.talend.commons.utils.data.list.ListenableList#set(int, java.lang.Object)}.
     */
    @Test
    public void testSet() {
        listenedList.add("A");
        listenedList.add("B");
        listenedList.add("C");

        assertFalse(replaced);
        listenedList.set(1, "D");
        assertFalse(replaced);

        initListener();

        assertFalse(replaced);
        listenedList.set(1, "E");
        assertTrue(replaced);
        assertTrue(currentEvent.removedObjects.contains("D"));
        assertTrue(currentEvent.addedObjects.contains("E"));
        assertEquals(currentEvent.index, 1);

    }

    /**
     * Test method for {@link org.talend.commons.utils.data.list.ListenableList#size()}.
     */
    @Test
    public void testSize() {
        listenedList.add("A");
        listenedList.add("B");
        listenedList.add("C");
        assertEquals(listenedList.size(), A_3);
    }

    /**
     * Test method for {@link org.talend.commons.utils.data.list.ListenableList#subList(int, int)}.
     */
    @Test
    public void testSubList() {
        listenedList.add("A");
        listenedList.add("B");
        listenedList.add("C");
        listenedList.add("D");

        List<String> list = listenedList.subList(1, A_3);

        assertEquals(list.get(0), "B");
        assertEquals(list.get(1), "C");

    }

    /**
     * Test method for {@link org.talend.commons.utils.data.list.ListenableList#toArray()}.
     */
    @Test
    public void testToArray() {
        listenedList.add("A");
        listenedList.add("B");
        Object[] array = (Object[]) listenedList.toArray();
        assertEquals(array[0], "A");
        assertEquals(array[1], "B");

    }

    /**
     * Test method for {@link org.talend.commons.utils.data.list.ListenableList#toArray(T[])}.
     */
    @Test
    public void testToArrayTArray() {
        listenedList.add("A");
        listenedList.add("B");
        String[] array = (String[]) listenedList.toArray(new String[0]);
        assertEquals(array[0], "A");
        assertEquals(array[1], "B");
    }

    /**
     * Test method for {@link org.talend.commons.utils.data.list.ListenableList#swap(int, int)}.
     */
    @Test
    public void testSwapIntInt() {
        listenedList.add("A");
        listenedList.add("B");
        listenedList.add("C");
        assertFalse(swaped);
        listenedList.swap(0, 2);
        assertFalse(swaped);
        assertEquals(listenedList.get(0), "C");
        assertEquals(listenedList.get(2), "A");

        initListener();

        assertFalse(swaped);
        listenedList.swap(0, 2);
        assertTrue(swaped);
        assertEquals(listenedList.get(0), "A");
        assertEquals(listenedList.get(2), "C");

    }

    /**
     * Test method for
     * {@link org.talend.commons.utils.data.list.ListenableList#swapElement(java.lang.Object, java.lang.Object)}.
     */
    @Test
    public void testSwapObjectObject() {
        listenedList.add("A");
        listenedList.add("B");
        listenedList.add("C");
        assertFalse(swaped);
        listenedList.swapElement("A", "C");
        assertFalse(swaped);
        assertEquals(listenedList.get(0), "C");
        assertEquals(listenedList.get(2), "A");

        initListener();

        assertFalse(swaped);
        listenedList.swapElement("A", "C");
        assertTrue(swaped);
        assertEquals(listenedList.get(0), "A");
        assertEquals(listenedList.get(2), "C");
    }

    /**
     * Test method for
     * {@link org.talend.commons.utils.data.list.ListenableList#addListener(org.talend.commons.utils.data.list.IListenableListListener)}.
     */
    @Test
    public void testAddListener() {
        assertFalse(added);
        listenedList.add("A");
        assertFalse(added);

        initListener();

        assertFalse(added);
        listenedList.add("B");
        assertTrue(added);
        added = false;

        listenedList.removeListener(listener);

        assertFalse(added);
        listenedList.add("C");
        assertFalse(added);

    }

    /**
     * Test method for
     * {@link org.talend.commons.utils.data.list.ListenableList#removeListener(org.talend.commons.utils.data.list.IListenableListListener)}.
     */
    @Test
    public void testRemoveListener() {
        assertFalse(added);
        listenedList.add("A");
        assertFalse(added);

        initListener();

        assertFalse(added);
        listenedList.add("B");
        assertTrue(added);
        added = false;

        listenedList.removeListener(listener);

        assertFalse(added);
        listenedList.add("C");
        assertFalse(added);
    }

    private void initListener() {
        listener = new IListenableListListener() {

            public void handleEvent(ListenableListEvent event) {
                switch (event.type) {
                case ADDED:
                    added = true;
                    break;
                case CLEARED:
                    cleared = true;
                    break;
                case REMOVED:
                    removed = true;
                    break;
                case REPLACED:
                    replaced = true;
                    break;
                case SWAPED:
                    swaped = true;
                    break;
                default:
                    fail("event type unknown");
                }
                currentEvent = event;

            }

        };
        listenedList.addPostOperationListener(listener);

    }
}
