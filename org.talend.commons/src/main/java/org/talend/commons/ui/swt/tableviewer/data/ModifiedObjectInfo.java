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
package org.talend.commons.ui.swt.tableviewer.data;

import org.talend.commons.ui.swt.tableviewer.TableViewerCreatorColumn;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 * @param <O> The object type which is modified in your list
 */
public class ModifiedObjectInfo<O> {

    private O currentModifiedBean;

    private TableViewerCreatorColumn currentModifiedColumn;

    private O previousModifiedBean;

    private TableViewerCreatorColumn previousModifiedColumn;

    private Object originalPropertyBeanValue;

    private Object previousPropertyBeanValue;

    private int currentModifiedIndex;

    private int previousModifiedIndex;
    
    public O getCurrentModifiedBean() {
        return currentModifiedBean;
    }

    public void setCurrentModifiedBean(O object) {
        this.currentModifiedBean = object;
    }

    public Object getOriginalPropertyBeanValue() {
        return this.originalPropertyBeanValue;
    }

    public void setOriginalPropertyBeanValue(Object originalPropertyBeanValue) {
        this.originalPropertyBeanValue = originalPropertyBeanValue;
    }

    public Object getPreviousPropertyBeanValue() {
        return this.previousPropertyBeanValue;
    }

    public void setPreviousPropertyBeanValue(Object previousPropertyBeanValue) {
        this.previousPropertyBeanValue = previousPropertyBeanValue;
    }

    public O getPreviousModifiedBean() {
        return this.previousModifiedBean;
    }

    public void setPreviousModifiedBean(O previousModifiedBean) {
        this.previousModifiedBean = previousModifiedBean;
    }

    public TableViewerCreatorColumn getPreviousModifiedColumn() {
        return this.previousModifiedColumn;
    }

    public void setPreviousModifiedColumn(TableViewerCreatorColumn previousModifiedColumn) {
        this.previousModifiedColumn = previousModifiedColumn;
    }

    public boolean isCurrentCell(Object bean, TableViewerCreatorColumn column) {
        if (bean == currentModifiedBean && column == currentModifiedColumn) {
            return true;
        }
        return false;
    }

    public TableViewerCreatorColumn getCurrentModifiedColumn() {
        return this.currentModifiedColumn;
    }

    public void setCurrentModifiedColumn(TableViewerCreatorColumn currentModifiedColumn) {
        this.currentModifiedColumn = currentModifiedColumn;
    }

    /**
     * DOC amaumont Comment method "setCurrentModifiedIndex".
     * @param i
     */
    public void setCurrentModifiedIndex(int currentModifiedIndex) {
        this.currentModifiedIndex = currentModifiedIndex;
    }

    
    public int getCurrentModifiedIndex() {
        return this.currentModifiedIndex;
    }

    
    public int getPreviousModifiedIndex() {
        return this.previousModifiedIndex;
    }

    
    public void setPreviousModifiedIndex(int previousModifiedIndex) {
        this.previousModifiedIndex = previousModifiedIndex;
    }

    
}
