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
package org.talend.commons.ui.swt.extended.table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.talend.commons.ui.swt.tableviewer.IModifiedBeanListenable;
import org.talend.commons.ui.swt.tableviewer.IModifiedBeanListener;
import org.talend.commons.utils.data.list.IListenableListListener;
import org.talend.commons.utils.data.list.ListenableList;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 * @param <B> Type of beans
 */
public class ExtendedTableModel<B> extends AbstractExtendedControlModel {

    private ListenableList<B> beansList = new ListenableList<B>();

    private IModifiedBeanListenable<B> modifiedBeanListenable;

    public ExtendedTableModel(String name) {
        super(name);
    }

    /**
     * DOC amaumont AbstractMacroTable constructor comment.
     */
    public ExtendedTableModel() {
        super();
    }

    /**
     * DOC amaumont Comment method "add".
     * 
     * @param bean
     * @param index can be null
     */
    public void add(B bean, Integer index) {
        if (index == null || index < 0 || index > this.beansList.size() - 1) {
            this.beansList.add(bean);
        } else {
            this.beansList.add(index, bean);
        }
    }

    public void add(B bean) {
        this.beansList.add(bean);
    }

    /**
     * DOC amaumont Comment method "add".
     * 
     * @param beans
     * @param index can be null
     */
    public void addAll(Integer index, List<B> beans) {
        if (index == null || index < 0 || index > this.beansList.size() - 1) {
            this.beansList.addAll(beans);
        } else {
            this.beansList.addAll(index, beans);
        }
    }

    /**
     * DOC amaumont Comment method "add".
     * 
     * @param beans
     * @param index can be null
     */
    public void addAll(List<Integer> indicesWhereAdd, List<B> beans) {
        this.beansList.addAll(indicesWhereAdd, beans);
    }

    /**
     * DOC amaumont Comment method "add".
     * 
     * @param beans
     * @param index can be null
     */
    public void addAll(List<B> beans) {
        addAll((Integer) null, beans);
    }

    public void registerDataList(List<B> list) {
        this.beansList.registerList(list);
    }

    public List<B> getBeansList() {
        return this.beansList;
    }

    /**
     * DOC amaumont Comment method "remove".
     * 
     * @param bean
     */
    public boolean remove(B bean) {
        return this.beansList.remove(bean);
    }

    /**
     * DOC amaumont Comment method "remove".
     * 
     * @param i
     */
    public B remove(int index) {
        return this.beansList.remove(index);
    }

    /**
     * @param c
     * @return
     * @see org.talend.commons.utils.data.list.ListenableList#removeAll(java.util.Collection)
     */
    public boolean removeAll(Collection<B> c) {
        return this.beansList.removeAll(c);
    }

    public void removeAll() {
        this.beansList.clear();
    }

    /**
     * DOC amaumont Comment method "remove".
     * 
     * @param indexArray
     */
    public List<B> remove(int[] indexArray) {
        ArrayList<B> objectsToRemove = new ArrayList<B>(indexArray.length);
        for (int i = 0; i < indexArray.length; i++) {
            objectsToRemove.add(beansList.get(indexArray[i]));
        }
        beansList.removeAll(objectsToRemove);
        return objectsToRemove;
    }

    /**
     * @param listener
     * @see org.talend.commons.utils.data.list.ListenableList#addAfterListener(org.talend.commons.utils.data.list.IListenableListListener)
     */
    public void addAfterOperationListListener(IListenableListListener listener) {
        this.beansList.addAfterListener(listener);
    }

    /**
     * @param orderCall
     * @param listener
     * @see org.talend.commons.utils.data.list.ListenableList#addAfterListener(int,
     * org.talend.commons.utils.data.list.IListenableListListener)
     */
    public void addAfterListener(int orderCall, IListenableListListener listener) {
        this.beansList.addAfterListener(orderCall, listener);
    }

    /**
     * @param listener
     * @see org.talend.commons.utils.data.list.ListenableList#addBeforeListener(org.talend.commons.utils.data.list.IListenableListListener)
     */
    public void addBeforeOperationListListener(IListenableListListener listener) {
        this.beansList.addBeforeListener(listener);
    }

    /**
     * @param orderCall
     * @param listener
     * @see org.talend.commons.utils.data.list.ListenableList#addBeforeListener(int,
     * org.talend.commons.utils.data.list.IListenableListListener)
     */
    public void addBeforeOperationListListener(int orderCall, IListenableListListener listener) {
        this.beansList.addBeforeListener(orderCall, listener);
    }

    public void removeModifiedListListener(IListenableListListener listenableListListener) {
        this.beansList.removeListener(listenableListListener);
    }

    public void addModifiedBeanListener(IModifiedBeanListener<B> modifiedBeanListener) {
        if (this.modifiedBeanListenable != null) {
            this.modifiedBeanListenable.addModifiedBeanListener(modifiedBeanListener);
        } else {
            throw new IllegalStateException("ModifiedBeanListenable object must be set before use this method");
        }
    }

    public void removeModifiedBeanListener(IModifiedBeanListener<B> modifiedBeanListener) {
        if (this.modifiedBeanListenable != null) {
            this.modifiedBeanListenable.removeModifiedBeanListener(modifiedBeanListener);
        } else {
            throw new IllegalStateException("ModifiedBeanListenable object must be set before use this method");
        }
    }

    public void setModifiedBeanListenable(IModifiedBeanListenable<B> modifiedBeanListenable) {
        this.modifiedBeanListenable = modifiedBeanListenable;
    }

    public boolean isDataRegistered() {
        return beansList.isListRegistered();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.ui.swt.extended.macrotable.AbstractExtendedControlModel#release()
     */
    @Override
    public void release() {
    }

    /**
     * DOC amaumont Comment method "getBeanCount".
     * 
     * @return
     */
    public int getBeanCount() {
        if (beansList.isListRegistered()) {
            return beansList.size();
        }
        return 0;
    }

}
