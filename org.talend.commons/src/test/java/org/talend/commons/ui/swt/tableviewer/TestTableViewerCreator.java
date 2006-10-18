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

import java.util.ArrayList;
import java.util.Random;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreatorColumn;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator.LAYOUT_MODE;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator.LINE_SELECTION;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator.SHOW_SELECTION;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator.SORT;
import org.talend.commons.ui.swt.tableviewer.behavior.CellEditorValueAdapter;
import org.talend.commons.ui.swt.tableviewer.tableeditor.TableEditorContent;
import org.talend.commons.ui.swt.tableviewer.tableeditor.TableEditorManager;
import org.talend.commons.utils.DataObject;
import org.talend.commons.utils.data.bean.IBeanPropertyAccessors;

/**
 * 
 * Test class of <code>TableViewerCreator</code>. <br/>
 * 
 * $Id$
 * 
 */
public final class TestTableViewerCreator {
    /**
     * Default Constructor.
     * Must not be used.
     */
    private TestTableViewerCreator() {
    }

    private static ArrayList<DataObject> list;

    public static void main(String[] args) {
        Display display = new Display();
        final Shell shell1 = new Shell(display);
        shell1.setLayout(new FillLayout());

        TableViewerCreator tableViewerCreator = new TableViewerCreator(shell1);
        tableViewerCreator.setHeaderVisible(true);
        tableViewerCreator.setBorderVisible(true);
        tableViewerCreator.setLinesVisible(true);
        tableViewerCreator.setShowSelection(SHOW_SELECTION.FULL);
        tableViewerCreator.setLineSelection(LINE_SELECTION.MULTI);
        tableViewerCreator.setLayoutMode(LAYOUT_MODE.DEFAULT);
        tableViewerCreator.setFirstVisibleColumnIsSelection(true);

        // tableEditor.setCheckboxInFirstColumn(true);

        final Table table = tableViewerCreator.createTable();

        TableViewerCreatorColumn column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle("Selection");
        column.setResizable(true);
        column.setModifiable(true);
        // column.setInitWeight(1);
        column.setWidth(20);

        column = new TableViewerCreatorColumn(tableViewerCreator);
        TableViewerCreatorColumn nameColumn = column;
        column.setTitle("Name");
        column.setResizable(true);
        column.setModifiable(true);
        column.setSortable(true);
        column.setOrderWithIgnoreCase(true);
        column.setMinimumWidth(10);
        column.setWeight(20);
        column.setBeanPropertyAccessors(new IBeanPropertyAccessors<DataObject, String>() {

            public String get(DataObject bean) {
                return bean.getLibelle();
            }

            public void set(DataObject bean, String value) {
                bean.setLibelle(value);
            }

        });

        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle("Integer Null Value");
        column.setModifiable(true);
        column.setSortable(true);
        column.setResizable(true);
        column.setBeanPropertyAccessors(new IBeanPropertyAccessors<DataObject, Integer>() {

            public Integer get(DataObject bean) {
                return bean.getIntegerValue2();
            }

            public void set(DataObject bean, Integer value) {
                bean.setIntegerValue2(value);
            }

        });
        column.setWidth(150);
        final String[] valueSet = new String[] { "xxx", "yyy", "zzz" };
        column.setCellEditor(new ComboBoxCellEditor(table, valueSet), new CellEditorValueAdapter() {

            public String getColumnText(CellEditor cellEditor, Object value) {
                String[] items = ((ComboBoxCellEditor) cellEditor).getItems();
                int index = (Integer) value;
                if (index >= 0 && index < items.length) {
                    return items[index];
                } else {
                    return "";
                }
            }
        });

        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle("Id");
        column.setModifiable(false);
        column.setResizable(true);
        column.setSortable(true);
        column.setMoveable(true);
        column.setWeight(80);
        column.setBeanPropertyAccessors(new IBeanPropertyAccessors<DataObject, Integer>() {

            public Integer get(DataObject bean) {
                return bean.getIntegerValue2();
            }

            public void set(DataObject bean, Integer value) {
                bean.setIntegerValue2(value);
            }

        });
        column.setTableEditorContent(new TableEditorContent() {

            public TableEditor createTableEditor(Table table) {
                TableEditor tableEditor = new TableEditor(table);
                return tableEditor;
            }

            public Control initialize(Table table, TableEditor tableEditor, TableViewerCreatorColumn currentColumn,
                    Object currentRowObject, Object currentCellValue) {
                Button button = new Button(table, SWT.PUSH);
                // Set attributes of the button
                button.setText(String.valueOf(currentCellValue));
                button.computeSize(SWT.DEFAULT, table.getItemHeight());

                // Set attributes of the editor
                tableEditor.grabHorizontal = true;
                tableEditor.minimumHeight = button.getSize().y;
                tableEditor.minimumWidth = button.getSize().x;
                return button;
            }

        });

        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle("Id2");
        column.setModifiable(false);
        column.setResizable(true);
        column.setSortable(true);
        column.setWidth(50);
        column.setBeanPropertyAccessors(new IBeanPropertyAccessors<DataObject, Integer>() {

            public Integer get(DataObject bean) {
                return bean.getIntegerValue2();
            }

            public void set(DataObject bean, Integer value) {
                bean.setIntegerValue2(value);
            }

        });
//        column.setTableEditorContent(new TableEditorContent() {
//
//            public TableEditor createTableEditor(Table table) {
//                TableEditor tableEditor = new TableEditor(table);
//                return tableEditor;
//            }
//
//            public Control initialize(Table table, TableEditor tableEditor, TableViewerCreatorColumn currentColumn,
//                    Object currentRowObject, Object currentCellValue) {
//                Composite composite = new Composite(table, SWT.PUSH);
//                // Set attributes of the button
//                composite.setBackground(new Color(null, 255, 0, 0));
//                composite.setSize(100 * ((Integer) currentCellValue).intValue() / 100, table.getItemHeight());
//
//                // Set attributes of the editor
//                // tableEditor.grabHorizontal = true;
//                tableEditor.minimumHeight = composite.getSize().y;
//                tableEditor.horizontalAlignment = SWT.LEFT;
//                tableEditor.minimumWidth = composite.getSize().x;
//                return composite;
//            }
//
//        });

//        Listener eraseItemListener = new Listener() {
//
//            public void handleEvent(Event event) {
//
//                if ((event.detail & SWT.SELECTED) != 0) {
//
//                    GC gc = event.gc;
//                    
//
//                    Rectangle rect = event.getBounds();
//
//                    Color background = gc.getBackground();
//
//                    gc.setBackground(table.getDisplay().getSystemColor(SWT.COLOR_RED));
//
//                    // TODO: uncomment to see selection on linux gtk
//
//                    // ((TableItem)event.item).setBackground(null);
//
//                    gc.fillRectangle(rect);
//
//                    gc.setBackground(background);
//
//                    event.detail &= ~SWT.SELECTED;
//
//                }
//
//            }
//
//        };
//        table.addListener(SWT.EraseItem, eraseItemListener);

        
        tableViewerCreator.setDefaultSort(nameColumn, SORT.DESC);
        
        list = new ArrayList<DataObject>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            DataObject listObject2 = new DataObject();
            listObject2.setPrimaryIntegerValue(i);
            listObject2.setIntegerValue2(random.nextInt(100));
            list.add(listObject2);
        }
        tableViewerCreator.init(list);

        shell1.setSize(800, 500);
        shell1.open();

        while (!shell1.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
    }

}
