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
package org.talend.commons.ui.swt.tableviewer;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class MouseTableSelectionHelper {

    private Table table;

    private boolean firstColumnMasked;

    private boolean draggingOnSelectionColumn;

    private int itemIndexAtDraggingStart;

    private Point cursorPositionAtMouseDown;

    private Cursor tableCursor;

    private Image imageSelectionCursor;

    private int[] selectionnableColumns;

    /**
     * DOC amaumont MouseTableSelectionHelper constructor comment.
     * 
     * @param table
     */
    public MouseTableSelectionHelper(Table table, boolean firstColumnMasked, int[] selectionnableColumns) {
        this.table = table;
        this.firstColumnMasked = firstColumnMasked;
        this.selectionnableColumns = selectionnableColumns;
        init();
    }

    /**
     * DOC amaumont MouseTableSelectionHelper constructor comment.
     * 
     * @param table
     */
    public MouseTableSelectionHelper(Table table, boolean firstColumnMasked) {
        this.table = table;
        this.firstColumnMasked = firstColumnMasked;
        init();
    }
    
    /**
     * DOC amaumont Comment method "init".
     */
    private void init() {

        final Listener storeCursorPositionListener = new Listener() {

            public void handleEvent(Event event) {
                cursorPositionAtMouseDown = new Point(event.x, event.y);
            }

        };
        table.addListener(SWT.MouseDown, storeCursorPositionListener);

        Listener dragDetectlistener = new Listener() {

            public void handleEvent(Event event) {

                int columnIndex = getColumnIndex(cursorPositionAtMouseDown);
                
//                for (int i = 0; i < selectionnableColumns.length; i++) {
//                    int selectionnableColumnIndex = selectionnableColumns[i];
//                    
//                }
                
                if (columnIndex == 0 && !firstColumnMasked || columnIndex == 1 && firstColumnMasked) {
                    draggingOnSelectionColumn = true;
                    itemIndexAtDraggingStart = getItemIndex(cursorPositionAtMouseDown);
                    setTableCursor(true);
                }
            }

        };
        table.addListener(SWT.DragDetect, dragDetectlistener);

        final Listener resetDraggingListener = new Listener() {

            public void handleEvent(Event event) {
                draggingOnSelectionColumn = false;
                setTableCursor(false);
            }

        };

//        table.addListener(SWT.MouseDown, resetDraggingListener);
        table.addListener(SWT.FocusOut, resetDraggingListener);
        table.getDisplay().addFilter(SWT.MouseUp, resetDraggingListener);

        table.addDisposeListener(new DisposeListener() {

            public void widgetDisposed(DisposeEvent e) {
                table.removeListener(SWT.MouseDown, storeCursorPositionListener);
                table.removeListener(SWT.MouseDown, resetDraggingListener);
                table.removeListener(SWT.FocusOut, resetDraggingListener);
                table.removeDisposeListener(this);
                table.getDisplay().removeFilter(SWT.MouseUp, resetDraggingListener);
                if (tableCursor != null) {
                    tableCursor.dispose();
                }
                if (imageSelectionCursor != null) {
                    imageSelectionCursor.dispose();
                }

            }

        });

        Listener mouseMoveListener = new Listener() {

            public void handleEvent(Event event) {

                if (draggingOnSelectionColumn) {
                    Point pointCursor = new Point(event.x, event.y);

                    int currentItemIndexUnderCursor = getItemIndex(pointCursor);
                    if (currentItemIndexUnderCursor != -1) {
                        int indexStart = 0;
                        int indexEnd = 0;
                        if (currentItemIndexUnderCursor >= itemIndexAtDraggingStart) {
                            indexStart = itemIndexAtDraggingStart;
                            indexEnd = currentItemIndexUnderCursor;
                        } else {
                            indexStart = currentItemIndexUnderCursor;
                            indexEnd = itemIndexAtDraggingStart;
                        }
                        
                        int countSelected = indexEnd - indexStart + 1;
                        int[] selection = new int[countSelected];
                        for (int i = 0; i < countSelected; i++) {
                            selection[i] = i + indexStart;
                        }
                        table.setSelection(selection);
                    }
                }
            }

        };
        table.addListener(SWT.MouseMove, mouseMoveListener);

    }

    /**
     * DOC amaumont Comment method "setTableCursor".
     */
    protected void setTableCursor(boolean cursorSelection) {

        Cursor cursor = null;
        if (cursorSelection) {
            if (imageSelectionCursor == null) {
                ImageDescriptor imageDescriptor = ImageDescriptor.createFromFile(MouseTableSelectionHelper.class, "/icons/right_arrow.ico");
                imageSelectionCursor = imageDescriptor.createImage();
            }
            cursor = new Cursor(table.getDisplay(), imageSelectionCursor.getImageData(), 15, 9);
        } else {
            cursor = new Cursor(table.getDisplay(), 0);
        }
        if (this.tableCursor != null) {
            this.tableCursor.dispose();
        }
        this.tableCursor = cursor;
        table.setCursor(this.tableCursor);
    }

    public int getColumnIndex(Point pointCursor) {
        // searching current column index
        int currentColumnIndex = 0;
        TableColumn[] columns = table.getColumns();
        for (int i = 0, width = 0; i < columns.length; i++) {
            TableColumn column = columns[i];
            int widthColumn = column.getWidth();
            if (pointCursor.x >= width && pointCursor.x <= width + widthColumn) {
                currentColumnIndex = i;
                break;
            }
            width += widthColumn;
        }

        return currentColumnIndex;
    }

    public int getItemIndex(Point pointCursor) {
        // searching current item index
        TableItem tableItemUnderCursor = table.getItem(pointCursor);
        TableItem[] tableItems = table.getItems();
        int currentItemIndex = -1;
        for (int i = 0; i < tableItems.length; i++) {
            if (tableItemUnderCursor == tableItems[i]) {
                currentItemIndex = i;
                break;
            }
        }
        return currentItemIndex;
    }

}
