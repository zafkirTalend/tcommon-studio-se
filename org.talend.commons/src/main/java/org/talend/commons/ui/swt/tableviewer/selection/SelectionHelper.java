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
package org.talend.commons.ui.swt.tableviewer.selection;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator;



/**
 * DOC amaumont  class global comment. Detailled comment
 * <br/>
 *
 * $Id$
 *
 */
public class SelectionHelper {

    private TableViewerCreator tableViewerCreator;

    private ListenerList selectionListeners = new ListenerList();


    /**
     * DOC amaumont SelectionHelper constructor comment.
     * @param tableViewerCreator
     */
    public SelectionHelper(TableViewerCreator tableViewerCreator) {
        this.tableViewerCreator = tableViewerCreator;
        init();
    }

    /**
     * DOC amaumont Comment method "init".
     */
    private void init() {
        this.tableViewerCreator.getTable().addSelectionListener(new SelectionListener() {

            public void widgetDefaultSelected(SelectionEvent e) {
                
            }

            public void widgetSelected(SelectionEvent e) {
                
            }
            
        });
        
        this.tableViewerCreator.getTableViewer().addPostSelectionChangedListener(new ISelectionChangedListener() {

            public void selectionChanged(SelectionChangedEvent event) {
                
            }
            
        });
        
        this.tableViewerCreator.getTableViewer().addSelectionChangedListener(new ISelectionChangedListener() {
            
            public void selectionChanged(SelectionChangedEvent event) {
                
            }
            
        });
        
    }

    /**
     * 
     * DOC amaumont Comment method "setSelection".
     * @param selection
     * @param reveal
     * @see TableViewer#setSelection(ISelection, boolean)
     */
    public void setSelection(ISelection selection, boolean reveal) {
        this.tableViewerCreator.getTableViewer().setSelection(selection, reveal);
    }

    /**
     * 
     * DOC amaumont Comment method "setSelection".
     * @param selection
     * @see TableViewer#setSelection(ISelection)
     */
    public void setSelection(ISelection selection) {
        this.tableViewerCreator.getTableViewer().setSelection(selection);
    }

    /**
     * 
     * DOC amaumont Comment method "setSelection".
     * @param start
     * @param end
     * @see Table#setSelection(int, int)
     */
    public void setSelection(int start, int end) {
        this.tableViewerCreator.getTable().setSelection(start, end);
    }

    /**
     * 
     * DOC amaumont Comment method "setSelection".
     * @param index
     * @see Table#setSelection(int)
     */
    public void setSelection(int index) {
        this.tableViewerCreator.getTable().setSelection(index);
    }

    /**
     * 
     * DOC amaumont Comment method "setSelection".
     * @param indices
     * @see Table#setSelection(int[])
     */
    public void setSelection(int[] indices) {
        this.tableViewerCreator.getTable().setSelection(indices);
    }

    /**
     * 
     * DOC amaumont Comment method "setSelection".
     * @param item
     * @see Table#setSelection(TableItem)
     */
    public void setSelection(TableItem item) {
        this.tableViewerCreator.getTable().setSelection(item);
    }

    /**
     * 
     * DOC amaumont Comment method "setSelection".
     * @param items
     * @see Table#setSelection(TableItem[])
     */
    public void setSelection(TableItem[] items) {
        this.tableViewerCreator.getTable().setSelection(items);
    }

    /**
     * 
     * DOC amaumont Comment method "deselect".
     * @param start
     * @param end
     * @see Table#deselect(int, int)
     */
    public void deselect(int start, int end) {
        this.tableViewerCreator.getTable().deselect(start, end);
    }

    /**
     * 
     * DOC amaumont Comment method "deselect".
     * @param index
     * @see Table#deselect(int)
     */
    public void deselect(int index) {
        this.tableViewerCreator.getTable().deselect(index);
    }

    /**
     * 
     * DOC amaumont Comment method "deselect".
     * @param indices
     * @see Table#deselect(int[])
     */
    public void deselect(int[] indices) {
        this.tableViewerCreator.getTable().deselect(indices);
    }

    /**
     * 
     * DOC amaumont Comment method "deselectAll".
     * @see Table#deselectAll()
     */
    public void deselectAll() {
        this.tableViewerCreator.getTable().deselectAll();
    }

    /**
     * 
     * DOC amaumont Comment method "select".
     * @param start
     * @param end
     * @see Table#select(int, int)
     */
    public void select(int start, int end) {
        this.tableViewerCreator.getTable().select(start, end);
    }

    /**
     * 
     * DOC amaumont Comment method "select".
     * @param index
     * @see Table#select(int)
     */
    public void select(int index) {
        this.tableViewerCreator.getTable().select(index);
    }
    
    /**
     * 
     * DOC amaumont Comment method "select".
     * @param indices
     * @see Table#select(int[])
     */
    public void select(int[] indices) {
        this.tableViewerCreator.getTable().select(indices);
    }

    /**
     * 
     * DOC amaumont Comment method "selectAll".
     * @see Table#selectAll()
     */
    public void selectAll() {
        this.tableViewerCreator.getTable().selectAll();
    }

    
    
}
