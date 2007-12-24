// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.commons.ui.swt.extended.table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.talend.commons.ui.i18n.Messages;
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
        init();
    }

    /**
     * DOC amaumont Comment method "init".
     */
    private void init() {
        beansList.setUseEquals(false);
    }

    /**
     * Create a new empty list automatically.
     */
    public ExtendedTableModel() {
        super();
        init();
        registerDataList(new ArrayList<B>());
    }

    /**
     * Use the given list to load data.
     * 
     * @param name
     * @param beansList
     */
    public ExtendedTableModel(String name, List<B> dataList) {
        super(name);
        init();
        registerDataList(dataList);
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

    public void setUseEquals(boolean useEquals) {
        beansList.setUseEquals(useEquals);
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
     * @param object1
     * @param object2
     * @see org.talend.commons.utils.data.list.ListenableList#swapElement(java.lang.Object, java.lang.Object)
     */
    public void swapElement(B object1, B object2) {
        this.beansList.swapElement(object1, object2);
    }

    /**
     * @param indicesOrigin
     * @param indicesTarget
     * @see org.talend.commons.utils.data.list.ListenableList#swapElements(java.util.List, java.util.List)
     */
    public void swapElements(List<Integer> indicesOrigin, List<Integer> indicesTarget) {
        this.beansList.swapElements(indicesOrigin, indicesTarget);
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
     * @see org.talend.commons.utils.data.list.ListenableList#addPostOperationListener(org.talend.commons.utils.data.list.IListenableListListener)
     */
    public void addAfterOperationListListener(IListenableListListener listener) {
        this.beansList.addPostOperationListener(listener);
    }

    /**
     * @param orderCall
     * @param listener
     * @see org.talend.commons.utils.data.list.ListenableList#addPostOperationListener(int,
     * org.talend.commons.utils.data.list.IListenableListListener)
     */
    public void addAfterOperationListListener(int orderCall, IListenableListListener listener) {
        this.beansList.addPostOperationListener(orderCall, listener);
    }

    /**
     * @param listener
     * @see org.talend.commons.utils.data.list.ListenableList#addBeforeOperationListener(org.talend.commons.utils.data.list.IListenableListListener)
     */
    public void addBeforeOperationListListener(IListenableListListener listener) {
        this.beansList.addBeforeOperationListener(listener);
    }

    /**
     * @param orderCall
     * @param listener
     * @see org.talend.commons.utils.data.list.ListenableList#addBeforeOperationListener(int,
     * org.talend.commons.utils.data.list.IListenableListListener)
     */
    public void addBeforeOperationListListener(int orderCall, IListenableListListener listener) {
        this.beansList.addBeforeOperationListener(orderCall, listener);
    }

    public void removeModifiedListListener(IListenableListListener listenableListListener) {
        this.beansList.removeListener(listenableListListener);
    }

    public void addModifiedBeanListener(IModifiedBeanListener<B> modifiedBeanListener) {
        if (this.modifiedBeanListenable != null) {
            this.modifiedBeanListenable.addModifiedBeanListener(modifiedBeanListener);
        } else {
            throw new IllegalStateException(Messages.getString("ExtendedTableModel.ModifiedObject.Error")); //$NON-NLS-1$
        }
    }

    public void removeModifiedBeanListener(IModifiedBeanListener<B> modifiedBeanListener) {
        if (this.modifiedBeanListenable != null) {
            this.modifiedBeanListenable.removeModifiedBeanListener(modifiedBeanListener);
        } else {
            throw new IllegalStateException(Messages.getString("ExtendedTableModel.ModifiedObject.Error")); //$NON-NLS-1$
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

    /**
     * DOC amaumont Comment method "createBeanInstance".
     */
    public B createBeanInstance() {
        return null;
    }

}
