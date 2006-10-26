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
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.util.Assert;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLayoutData;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Widget;
import org.talend.commons.ui.swt.tableviewer.behavior.DefaultCellModifier;
import org.talend.commons.ui.swt.tableviewer.behavior.DefaultHeaderColumnSelectionListener;
import org.talend.commons.ui.swt.tableviewer.behavior.DefaultStructuredContentProvider;
import org.talend.commons.ui.swt.tableviewer.behavior.DefaultTableLabelProvider;
import org.talend.commons.ui.swt.tableviewer.behavior.ITableCellValueModifiedListener;
import org.talend.commons.ui.swt.tableviewer.behavior.TableViewerCreatorLayout;
import org.talend.commons.ui.swt.tableviewer.data.ModifiedObjectInfo;
import org.talend.commons.ui.swt.tableviewer.selection.ITableColumnSelectionListener;
import org.talend.commons.ui.swt.tableviewer.selection.MouseTableSelectionHelper;
import org.talend.commons.ui.swt.tableviewer.selection.SelectionHelper;
import org.talend.commons.ui.swt.tableviewer.sort.TableViewerCreatorSorter;
import org.talend.commons.ui.swt.tableviewer.tableeditor.TableEditorManager;
import org.talend.commons.ui.utils.TableUtils;
import org.talend.commons.ui.ws.WindowSystem;
import org.talend.commons.utils.EventUtil;
import org.talend.commons.utils.data.list.IListenableListListener;
import org.talend.commons.utils.data.list.ListenableList;
import org.talend.commons.utils.data.list.ListenableListEvent;

/**
 * A concrete Table viewer based on the JFace <code>TableViewer</code> and the SWT <code>Table</code> control.
 * <p>
 * This class is intended to make easier creation and use of a table.
 * 
 * For a basic usage you need create columns with <code>TableViewerCreatorColumn</code> and init a addedObjects of
 * objects. The addedObjects will be introspected to retrieve values of each its objects. Each object of the
 * addedObjects will represent one line in the Table.
 * 
 * The following functions are already implemented : sort, automatic resize of columns (see layoutMode), and basic
 * functions.
 * 
 * You can access directly to instanciated <code>TableViewer</code> and <code>Table</code> to manage, add custom
 * listeners or other usages.
 * 
 * Filtering is not currently implemented, but you can add manually yours.
 * 
 * <p>
 * You can add CellEditor using <code>TableViewerCreatorColumn</code> and <code>setCellEditor()</code> method.
 * According case you will have to override certain methods of <code>CellEditorValueAdapter</code>.
 * </p>
 * 
 * <p>
 * You can add TableEditor using <code>TableViewerCreatorColumn</code> and <code>setTableEditor()</code> method.
 * According case you will have to override certain methods of <code>TableEditorInitializer</code>.
 * </p>
 * 
 * Read the following steps to create a reflect table :
 * <p>
 * 1) instanciate <code>TableViewerCreator</code>
 * </p>
 * <p>
 * 2) configure, the methods below are used to configure internally style of <code>Table</code> object, so if you want
 * use these methods call them before <code>createTable</code> : <br/>- <code>setLineSelection</code> <br/>-
 * <code>setShowSelection</code> <br/>- <code>setCheckboxInFirstColumn</code> <br/>- <code>setBorderVisible</code>
 * <br/>- <code>setHorizontalScroll</code> <br/>- <code>setVerticalScroll</code> <br/>-
 * <code>setHeaderVisible</code> <br/>- <code>setLinesVisible</code>
 * </p>
 * 
 * <p>
 * 3) optionally call <code>createTable</code> if you need initialize <code>Table</code>'s children components
 * before <code>init</code> call.
 * </p>
 * <p>
 * 4) create <code>TableViewerCreatorColumn</code> columns and configure them. <BR/>Description of the main parameters :
 * <BR/> - <code>beanPropertyName</code> represents the property of each object of your addedObjects which will be
 * read (and write). <BR/> - <code>idProperty</code> (optional) represents the <b>unique id</b> of the column, it is
 * by default the value of <code>beanPropertyName</code>. If the unicity is not respected, a assertion is thrown.
 * <BR/> - set a <code>width</code> or a <code>weight</code> value. <BR/> - see others parameters in
 * <code>TableViewerCreatorColumn</code>
 * </p>
 * <p>
 * 5) set commons value for columns as you want, call them before <code>init</code> : <br/>-
 * <code>setAllColumnsMoveable</code> <br/>- <code>setAllColumnsResizable</code> <br/>-
 * <code>setAllColumnsSortable</code> <br/>
 * </p>
 * 
 * <p>
 * 6) call <code>init</code> method with your addedObjects of objects in parameter.
 * </p>
 * 
 * @see org.eclipse.jface.viewers#TableViewer
 * @see org.eclipse.swt.widgets#Table <br/>
 * 
 * <br/>
 * 
 * $Id$
 * 
 * @param <O> type of objects in the input list of <code>TableViewer</code>
 */
public class TableViewerCreator<O> {

    private static final String ID_MASKED_COLUMN = "__MASKED_COLUMN__";

    private Composite compositeParent;

    private List<TableViewerCreatorColumn> columns = new ArrayList<TableViewerCreatorColumn>();

    private TableViewer tableViewer;

    private LINE_SELECTION lineSelection;

    private SHOW_SELECTION showSelection;

    
    /**
     * 
     * DOC amaumont MetadataEditorEvent class global comment. Detailled comment
     * <br/>
     *
     * $Id$
     *
     */
    public enum CELL_EDITOR_STATE {
        EDITING,
        APPLYING,
        CANCELING,
        ;
    }
    

    /**
     * @see Table#setLinesVisible(boolean)
     */
    private boolean linesVisible;

    /**
     * 
     */
    private Color emptyZoneColor;

    /**
     * 
     */
    private boolean emptyZoneLinesVisible;

    /**
     * SWT.BORDER style applied to <code>Table</code>.
     * 
     * @see SWT#CHECK
     */
    private boolean borderVisible;

    /**
     * @see Table#setHeaderVisible(boolean)
     */
    private boolean headerVisible;

    /**
     * SWT.CHECK style applied to <code>Table</code>.
     * 
     * @see SWT#CHECK
     */
    private boolean checkboxInFirstColumn;

    private boolean horizontalScroll;

    private boolean verticalScroll;

    private boolean allColumnsMoveable;

    private boolean allColumnsResizable;

    private boolean allColumnsSortable;

    private ICellModifier cellModifier;

    private ITableLabelProvider labelProvider;

    private IStructuredContentProvider contentProvider;

    private ControlListener tableParentResizedListener;

    private Layout layout;

    private int adjustWidthValue;

    private Map<String, TableViewerCreatorColumn> idToTableViewerCreatorColumn;

    private Table table;

    private TableViewerCreatorSorter tableViewerCreatorSorter;

    private LAYOUT_MODE layoutMode = LAYOUT_MODE.NONE;

    private boolean firstColumnMasked;

    private boolean initCalled;

    private TableEditorManager tableEditorManager;

    private ModifiedObjectInfo<O> modifiedObjectInfo;

    private TableViewerCreator<O> tableViewerCreator;

    private boolean firstVisibleColumnIsSelection;

    private SelectionHelper selectionHelper;

    private MouseTableSelectionHelper mouseTableSelectionHelper;

    private TableViewerCreatorColumn defaultOrderedColumn;

    private SORT defaultOrderBy;

    private Color bgColorSelectedLine;

    private Color fgColorSelectedLine;

    private Color bgColorSelectedLineWhenUnactive;

    private Color fgColorSelectedLineWhenUnactive;

    private Listener eraseItemListener;

    private boolean useCustomColoring;

    /**
     * Constructor.
     * 
     * @param compositeParent used to initialize <code>Table</code>.
     */
    public TableViewerCreator(Composite compositeParent) {
        super();
        tableViewerCreator = this;
        this.compositeParent = compositeParent;
    }

    /**
     * 
     * <p>
     * Create a new instance of <code>Table</code> with its <code>TableColumn</code> and create also a new instance
     * of <code>TableViewer</code> if these objects doesn't exist already.
     * </p>
     * 
     * <p>
     * Initialize the <code>TableViewer</code> with by default the <code>DefaultStructuredContentProvider</code>,
     * the <code>DefaultTableLabelProvider</code> and the <code>DefaultTableViewerSorter</code>.
     * </p>
     * 
     */
    public void init() {
        init(null);
    }

    @SuppressWarnings("unchecked")
    public void init(Collection collection) {
        if (collection != null) {
            init(new ArrayList<O>(collection));
        } else {
            init();
        }
    }

    /**
     * 
     * <p>
     * Create a new instance of <code>Table</code> with its <code>TableColumn</code>s and create also a new
     * instance of <code>TableViewer</code> if these objects doesn't exist already.
     * </p>
     * 
     * <p>
     * Initialize the <code>TableViewer</code> with by default the <code>DefaultStructuredContentProvider</code>,
     * the <code>DefaultTableLabelProvider</code> and the <code>DefaultTableViewerSorter</code>.
     * </p>
     * 
     * <p>
     * If you use the default ContentProvider, you must set as input an object which extends <code>Collection</code>.
     * </p>
     * 
     * 
     * <p>
     * <b> If it is the second or more time than you call this method, only the input object is loaded. Call methods of
     * <code>TableViewer</code> for others operations. </b>
     * </p>
     * 
     * @param input by default a object which extends <code>Collection</code>.
     */
    public void init(List list) {

        if (!initCalled) {
            initCalled = true;
            if (this.table == null) {
                createTable();
            }
            tableViewer = buildAndLayoutTable();
            attachContentProvider();
            attachLabelProvider();
            attachCellEditors();
            attachViewerSorter();
            addListeners();
        }
        // long time11 = System.currentTimeMillis();
        if (list != null) {
            if (tableEditorManager != null && list instanceof ListenableList) {
                ((ListenableList) list).addListener(new IListenableListListener() {

                    public void handleEvent(ListenableListEvent event) {
                        // we must refresh the table before creating the control to draw cells
                        getTableViewer().refresh();
                        tableEditorManager.init();
                    }

                });
            }
            tableViewer.setInput(list);
        }
        if (tableEditorManager != null) {
            tableEditorManager.init();
        }
    }

    public List getInputList() {
        return (List) tableViewer.getInput();
    }

    /**
     * 
     * Instantiate the <code>Table</code> with the <code>compositeParent</code> as parent, with pre-configured
     * styles and options.
     * 
     * @return
     */
    public Table createTable() {
        if (this.table != null) {
            this.table.dispose();
        }
        this.table = new Table(compositeParent, checkTableStyles());
        new TableEditor(table);
        tableViewer = new TableViewer(table);
        setTablePreferences();

        initCellModifier();
        
        return table;
    }

    /**
     * DOC amaumont Comment method "initCellModifier".
     */
    protected void initCellModifier() {
        if (cellModifier == null) {
            cellModifier = new DefaultCellModifier(this);
        }
        tableViewer.setCellModifier(cellModifier);
    }

    protected void setTablePreferences() {
        table.setHeaderVisible(headerVisible);
        table.setLinesVisible(linesVisible);

        if (this.emptyZoneColor != null || !emptyZoneLinesVisible) {
            Listener paintListener = new Listener() {

                public void handleEvent(Event event) {
                    GC gc = event.gc;
                    Rectangle area = table.getClientArea();

                    Color foregroundColor = gc.getBackground();
                    if (emptyZoneColor != null) {
                        foregroundColor = emptyZoneColor;
                    }
                    gc.setForeground(foregroundColor);
                    int starty = table.getHeaderHeight() + table.getItemCount() * table.getItemHeight();
                    if (starty < area.height) {
                        gc.fillRectangle(0, starty, area.width, area.height);
                    }
                    TableColumn[] tableColumns = table.getColumns();
                    int widthColumns = 0;
                    for (int i = 0; i < tableColumns.length; i++) {
                        widthColumns += tableColumns[i].getWidth();
                    }
                    if (widthColumns < area.width) {
                        gc.fillRectangle(widthColumns + 1, 0, area.width, area.height);
                    }

                }
            };
            table.addListener(SWT.Paint, paintListener);
        }

        if (useCustomColoring) {
            setUseCustomColoring(true);
        }

        if (this.firstVisibleColumnIsSelection) {
            this.mouseTableSelectionHelper = new MouseTableSelectionHelper(this);
        }

    }

    /**
     * 
     * Initiate the style intended for instanciate <code>Table</code>.
     * 
     * @return int style
     */
    protected int checkTableStyles() {
        int style = SWT.NONE;
        if (lineSelection != null) {
            style |= lineSelection.getSwtStyle();
        }
        if (showSelection != null) {
            style |= showSelection.getSwtStyle();
        }
        if (checkboxInFirstColumn) {
            style |= SWT.CHECK;
        }
        if (borderVisible) {
            style |= SWT.BORDER;
        }
        if (horizontalScroll) {
            style |= SWT.H_SCROLL;
        }
        if (verticalScroll) {
            style |= SWT.V_SCROLL;
        }
        return style;
    }

    protected void addListeners() {

    }

    
    /**
     * 
     * DOC amaumont Comment method "addPaintListener".
     */
    protected void addEraseItemListener() {

        if (eraseItemListener != null) {
            return;
        }

        eraseItemListener = new Listener() {

            public void handleEvent(Event event) {

//                System.out.println("EraseItem event.detail=" + EventUtil.getEventNameFromDetail(event.detail) + "event.widget="
//                        + event.widget.hashCode());
//                System.out.println(event);
                TableItem tableItem = (TableItem) event.item;

                boolean selectedState = (event.detail & SWT.SELECTED) != 0;
                boolean focusedState = table.isFocusControl();

                if (selectedState || event.detail == 22) {

                    GC gc = event.gc;

                    Rectangle rect = event.getBounds();

                    Color previousBackground = gc.getBackground();
                    Color previousForeground = gc.getForeground();

                    Color bgColor = null;
                    Color fgColor = null;
                    if (focusedState) {
                        if (bgColorSelectedLine != null) {
                            bgColor = bgColorSelectedLine;
                        } else {
                            bgColor = table.getDisplay().getSystemColor(SWT.COLOR_LIST_SELECTION);
                        }
                        if (fgColorSelectedLine != null) {
                            fgColor = fgColorSelectedLine;
                        } else {
                            fgColor = table.getDisplay().getSystemColor(SWT.COLOR_BLACK);
                        }
                    } else {
                        if (bgColorSelectedLineWhenUnactive != null) {
                            bgColor = bgColorSelectedLineWhenUnactive;
                        } else {
                            bgColor = table.getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND);
                        }
                        if (fgColorSelectedLineWhenUnactive != null) {
                            fgColor = fgColorSelectedLineWhenUnactive;
                        } else {
                            fgColor = table.getDisplay().getSystemColor(SWT.COLOR_BLACK);
                        }
                    }
                    gc.setBackground(bgColor);
                    gc.setForeground(fgColor);

                    gc.fillRectangle(rect);

                    gc.setBackground(previousBackground);
                    gc.setForeground(previousForeground);

                    event.detail &= ~SWT.SELECTED;

                } else if (event.detail == 24 || event.detail == 22) {
                    /**
                     * To color cells or rows selected by using
                     * org.eclipse.swt.widgets.TableItem#setBackground(int,Color) or
                     * org.eclipse.swt.widgets.TableItem#setBackground(Color)
                     */

                    // System.out.println("#########################" +event.detail);
                    GC gc = event.gc;

                    Rectangle rect = event.getBounds();

                    int columnIndex = TableUtils.getColumnIndex(table, new Point(event.x, event.y));
                    Color currentBackgroundColumn = tableItem.getBackground(columnIndex);

                    Color parentBg = tableItem.getParent().getBackground();
                    // System.out.println("currentBackgroundColumn="+currentBackgroundColumn);
                    // System.out.println("tableItem.getBackground()="+tableItem.getBackground());
                    if (currentBackgroundColumn == parentBg && tableItem.getBackground() != null) {
                        currentBackgroundColumn = tableItem.getBackground();
                    }

                    Color background = gc.getBackground();

                    gc.setBackground(currentBackgroundColumn);

                    gc.fillRectangle(rect);

                    gc.setBackground(background);

                }

            }

        };
        table.addListener(SWT.EraseItem, eraseItemListener);

        // Listener paintListener = new Listener() {
        //
        // public void handleEvent(Event event) {
        // System.out.println("PaintItem event.detail=" + EventUtil.getEventNameFromDetail(event.detail) +
        // "event.widget="
        // + event.widget.hashCode());
        //
        // TableItem tableItem = (TableItem) event.item;
        //
        // if (event.detail == 24) {
        // /**
        // * To color cells or rows selected by using
        // * org.eclipse.swt.widgets.TableItem#setBackground(int,Color) or
        // * org.eclipse.swt.widgets.TableItem#setBackground(Color)
        // */
        //
        // // System.out.println("#########################" +event.detail);
        // GC gc = event.gc;
        //
        // Rectangle rect = event.getBounds();
        //
        // int columnIndex = TableUtils.getColumnIndex(table, new Point(event.x, event.y));
        // Color currentBackgroundColumn = tableItem.getBackground(columnIndex);
        // Color parentBg = tableItem.getParent().getBackground();
        // if(currentBackgroundColumn == parentBg && tableItem.getBackground() != null) {
        // currentBackgroundColumn = tableItem.getBackground();
        // }
        //                    
        // Color background = gc.getBackground();
        //
        // gc.setBackground(currentBackgroundColumn);
        //
        // // TODO: uncomment to see selection on linux gtk
        //
        // // ((TableItem)event.item).setBackground(null);
        //
        // gc.fillRectangle(rect);
        //
        // gc.setBackground(background);
        //
        // }
        //
        // }
        //
        // };
        // table.addListener(SWT.PaintItem, paintListener);
        //
        // Listener measureItemListener = new Listener() {
        //
        // public void handleEvent(Event event) {
        // System.out.println("MeasureItem event.detail=" + EventUtil.getEventNameFromDetail(event.detail) +
        // "event.widget="
        // + event.widget.hashCode());
        // }
        //
        // };
        //
        // table.addListener(SWT.MeasureItem, measureItemListener);

    }

    protected void attachViewerSorter() {

        if (this.tableViewerCreatorSorter == null) {
            this.tableViewerCreatorSorter = new TableViewerCreatorSorter();
            if (defaultOrderedColumn != null && defaultOrderBy != null && defaultOrderedColumn.isSortable()) {
                this.tableViewerCreatorSorter.prepareSort(this, defaultOrderedColumn, defaultOrderBy);
            }
        }
        tableViewer.setSorter((ViewerSorter) this.tableViewerCreatorSorter);
    }

    protected void attachLabelProvider() {
        if (this.labelProvider == null) {
            this.labelProvider = new DefaultTableLabelProvider(this);
        }
        tableViewer.setLabelProvider(this.labelProvider);
    }

    protected void attachContentProvider() {
        if (this.contentProvider == null) {
            this.contentProvider = new DefaultStructuredContentProvider(this);
        }
        tableViewer.setContentProvider(this.contentProvider);
    }

    protected TableViewer buildAndLayoutTable() {
        if (this.layoutMode == LAYOUT_MODE.DEFAULT || this.layoutMode == LAYOUT_MODE.FILL_HORIZONTAL
                || this.layoutMode == LAYOUT_MODE.CONTINUOUS) {
            TableViewerCreatorLayout currentTableLayout = new TableViewerCreatorLayout(tableViewerCreator, 50);
            currentTableLayout.setWidthAdjustValue(this.adjustWidthValue);
            currentTableLayout.setFillHorizontal(this.layoutMode == LAYOUT_MODE.FILL_HORIZONTAL);
            currentTableLayout.setContinuousLayout(this.layoutMode == LAYOUT_MODE.FILL_HORIZONTAL
                    || this.layoutMode == LAYOUT_MODE.CONTINUOUS);
            this.layout = currentTableLayout;
        }

        idToTableViewerCreatorColumn = new HashMap<String, TableViewerCreatorColumn>(columns.size());

        if (firstColumnMasked || columns.size() == 0) {
            TableViewerCreatorColumn maskedTableViewerCreatorColumn = new TableViewerCreatorColumn();
            maskedTableViewerCreatorColumn.setId(ID_MASKED_COLUMN);
            columns.add(0, maskedTableViewerCreatorColumn);
        }

        int size = columns.size();
        for (int i = 0; i < size; i++) {
            final TableViewerCreatorColumn column = columns.get(i);
            column.setIndex(i);
            TableColumn tableColumn = column.getTableColumn();
            if (WindowSystem.isGTK() && column.getWidth() == 0 && column.getWeight() == 0) {
                // bug with GTK for cell edition when width == 0
                column.setWidth(1);
            }
            initColumnLayout(column);
            if (tableColumn == null) {
                tableColumn = createTableColumn(column);
                column.setTableColumn(tableColumn);
            } else {
                Assert.isTrue(tableColumn.getParent() == this.table, "The TableColumn of TableEditorColumn with idProperty '"
                        + column.getId() + "' has not the correct Table parent");
            }
            Assert.isTrue(idToTableViewerCreatorColumn.get(column.getId()) == null,
                    "You must change the idProperty of one of your column, the idProperty must be unique for each column for one Table.");

            idToTableViewerCreatorColumn.put(column.getId(), column);
        }
        if (layout != null) {
            table.setLayout(layout);
            table.layout();
        }
        return tableViewer;
    }

    /**
     * DOC amaumont Comment method "initColumnLayout".
     * 
     * @param column
     */
    protected void initColumnLayout(final TableViewerCreatorColumn column) {
        ColumnLayoutData columnLayoutData = null;
        if (column.getWeight() > 0) {
            columnLayoutData = new ColumnWeightData(column.getWeight(), column.getMinimumWidth(), column.isResizable());
        } else {
            columnLayoutData = new ColumnPixelData(column.getWidth(), column.isResizable(), false);
        }
        if (layout instanceof TableViewerCreatorLayout) {
            ((TableViewerCreatorLayout) layout).addColumnData(columnLayoutData);
        } else if (layout instanceof TableLayout) {
            ((TableLayout) layout).addColumnData(columnLayoutData);
        }
    }

    /**
     * Create a <code>TableColumn</code> and intialize it from <code>TableViewerCreatorColumn</code>'s properties.
     * 
     * @param column
     */
    protected TableColumn createTableColumn(final TableViewerCreatorColumn column) {
        boolean isMaskedColumn = false;
        if (column.getId().equals(ID_MASKED_COLUMN)) {
            isMaskedColumn = true;
        }
        TableColumn tableColumn;
        tableColumn = new TableColumn(this.table, column.getAlignment().getSwtAlignment());
        if (isMaskedColumn) {
            tableColumn.setMoveable(false);
            tableColumn.setResizable(false);
        } else {
            tableColumn.setImage(column.getImageHeader());
            tableColumn.setText(column.getTitle() != null ? column.getTitle() : column.getId());
            tableColumn.setToolTipText(column.getToolTipHeader());

            if (column.getTableEditorContent() != null) {
                if (tableEditorManager == null) {
                    tableEditorManager = new TableEditorManager(this);
                }
            }

            if (this.allColumnsMoveable) {
                tableColumn.setMoveable(true);
            } else {
                tableColumn.setMoveable(column.isMoveable());
            }
            if (this.allColumnsResizable) {
                tableColumn.setResizable(true);
            } else {
                tableColumn.setResizable(column.isResizable());
            }
            if (this.allColumnsSortable) {
                column.setSortable(true);
            }
            if (column.isSortable()) {
                ITableColumnSelectionListener columnSelectionListener = null;
                if (column.getTableColumnSelectionListener() == null) {
                    columnSelectionListener = new DefaultHeaderColumnSelectionListener(column, this);
                    column.setTableColumnSelectionListener(columnSelectionListener);
                } else {
                    columnSelectionListener = column.getTableColumnSelectionListener();
                }
                tableColumn.addSelectionListener(columnSelectionListener);
            }
        }
        return tableColumn;
    }

    protected void attachCellEditors() {
        String[] properties = new String[columns.size()];
        CellEditor[] cellEditors = new CellEditor[columns.size()];
        int size = columns.size();
        for (int i = 0; i < size; i++) {
            TableViewerCreatorColumn column = columns.get(i);
            properties[i] = column.getId();
            cellEditors[i] = column.getCellEditor();
        }
        tableViewer.setColumnProperties(properties);
        tableViewer.setCellEditors(cellEditors);
    }

    public TableViewerCreatorColumn getColumn(String idProperty) {
        return idToTableViewerCreatorColumn.get(idProperty);
    }

    public boolean isHeaderVisible() {
        if (table != null) {
            return table.getHeaderVisible();
        }
        return headerVisible;
    }

    public void setHeaderVisible(boolean headerVisible) {
        if (table != null) {
            table.setHeaderVisible(headerVisible);
        } else {
            this.headerVisible = headerVisible;
        }
    }

    public void addColumn(TableViewerCreatorColumn tableEditorColumn) {
        columns.add(tableEditorColumn);
    }

    /**
     * Line selection mode for SWT Table. <br/>
     * 
     * @see SWT.SINGLE
     * @see SWT.MULTI
     * 
     */
    public enum LINE_SELECTION implements ISwtStyle {
        SINGLE(SWT.SINGLE),
        MULTI(SWT.MULTI);

        private int swtStyle = SWT.NONE;

        /**
         * 
         * DOC amaumont LINE_SELECTION constructor comment.
         * 
         * @param swtStyle
         */
        LINE_SELECTION(int swtStyle) {
            this.swtStyle = swtStyle;
        }

        public int getSwtStyle() {
            return swtStyle;
        }
    };

    /**
     * Show selection mode of a <code>Table</code>'s row. <br/>
     * 
     * @see SWT.FULL_SELECTION
     * @see SWT.HIDE_SELECTION
     * 
     */
    public enum SHOW_SELECTION implements ISwtStyle {
        FULL(SWT.FULL_SELECTION),
        HIDE(SWT.HIDE_SELECTION);

        private int swtStyle = SWT.NONE;

        SHOW_SELECTION(int swtStyle) {
            this.swtStyle = swtStyle;
        }

        public int getSwtStyle() {
            return swtStyle;
        }
    }

    /**
     * Layout mode of the <code>TableViewer</code>.
     * 
     */
    public enum LAYOUT_MODE {
        /**
         * Default layout based on <code>TableLayout</code> behavior :
         * <p>- Use width and weight to initialize columns size, but don't resize columns when table is resized
         * </p>
         */
        DEFAULT(),

        /**
         * <p>- Use width and weight to initialize columns size, but don't resize columns when table is resized
         * </p>
         * <p>- Fill all empty space at initialization
         * </p>
         * <p>- Change columns size dynamically when table is resized (only if weight is set in column)
         * </p>
         */
        CONTINUOUS,

        /**
         * <p>- Use width and weight to initialize columns size, but don't resize columns when table is resized
         * </p>
         * <p>- Fill all empty space at initialization
         * </p>
         * <p>- Change columns size dynamically when table is resized (only if weight is set in column)
         * </p>
         * <p>- Fill empty space with last column when columns are resized
         * </p>
         */
        FILL_HORIZONTAL,

        NONE(), ;

        LAYOUT_MODE() {
        }
    };

    public boolean isCheckboxInFirstColumn() {
        return this.checkboxInFirstColumn;
    }

    public void setCheckboxInFirstColumn(boolean checkboxInFirstColumn) {
        this.checkboxInFirstColumn = checkboxInFirstColumn;
    }

    public List<TableViewerCreatorColumn> getColumns() {
        return (List<TableViewerCreatorColumn>) Collections.unmodifiableList(columns);
    }

    public void setColumns(List<TableViewerCreatorColumn> columns) {
        this.columns = columns;
    }

    public LINE_SELECTION getLineSelection() {
        return lineSelection;
    }

    public void setLineSelection(LINE_SELECTION lineSelection) {
        this.lineSelection = lineSelection;
    }

    /**
     * 
     * 
     * @see Table#getLinesVisible()
     */
    public boolean isLinesVisible() {
        if (table != null) {
            return table.getLinesVisible();
        }
        return this.linesVisible;
    }

    /**
     * @see Table#setLinesVisible(boolean)
     */
    public void setLinesVisible(boolean linesVisible) {
        if (table != null) {
            table.setLinesVisible(linesVisible);
        } else {
            this.linesVisible = linesVisible;
        }
    }

    public SHOW_SELECTION getShowSelection() {
        return showSelection;
    }

    public void setShowSelection(SHOW_SELECTION showSelection) {
        this.showSelection = showSelection;
    }

    /**
     * 
     * The <code>TableViewer</code> is instanciate at same time than <code>Table</code>.
     * 
     * @return
     */
    public TableViewer getTableViewer() {
        return tableViewer;
    }

    public void setTableViewer(TableViewer tableViewer) {
        this.tableViewer = tableViewer;
    }

    public boolean isBorderVisible() {
        return borderVisible;
    }

    public void setBorderVisible(boolean borderVisible) {
        this.borderVisible = borderVisible;
    }

    public boolean isHorizontalScroll() {
        return horizontalScroll;
    }

    public void setHorizontalScroll(boolean horizontalScroll) {
        this.horizontalScroll = horizontalScroll;
    }

    public boolean isVerticalScroll() {
        return verticalScroll;
    }

    public void setVerticalScroll(boolean verticalScroll) {
        this.verticalScroll = verticalScroll;
    }

    public ICellModifier getCellModifier() {
        return cellModifier;
    }

    public void setCellModifier(ICellModifier cellModifier) {
        this.cellModifier = cellModifier;
    }

    public ITableLabelProvider getLabelProvider() {
        return labelProvider;
    }

    public void setLabelProvider(ITableLabelProvider tableLabelProvider) {
        this.labelProvider = tableLabelProvider;
    }

    public IStructuredContentProvider getContentProvider() {
        return contentProvider;
    }

    public void setContentProvider(IStructuredContentProvider contentProvider) {
        this.contentProvider = contentProvider;
    }

    public Table getTable() {
        return table;
    };

    /**
     * Sort mode. <br/>
     * 
     */
    public enum SORT {
        ASC,
        DESC,
        NONE,
    }

    public ControlListener getTableParentResizedListener() {
        return this.tableParentResizedListener;
    }

    public void setTableParentResizedListener(ControlListener tableParentResizedListener) {
        this.tableParentResizedListener = tableParentResizedListener;
    }

    public void packColumns() {
        for (int i = 0, n = table.getColumnCount(); i < n; i++) {
            table.getColumn(i).pack();
        }
    }

    public boolean isAllColumnsMoveable() {
        return this.allColumnsMoveable;
    }

    public void setAllColumnsMoveable(boolean allColumnsMoveable) {
        this.allColumnsMoveable = allColumnsMoveable;
    }

    public boolean isAllColumnsResizable() {
        return this.allColumnsResizable;
    }

    public void setAllColumnsResizable(boolean allColumnsResizable) {
        this.allColumnsResizable = allColumnsResizable;
    }

    public boolean isAllColumnsSortable() {
        return this.allColumnsSortable;
    }

    public void setAllColumnsSortable(boolean allColumnsSortable) {
        this.allColumnsSortable = allColumnsSortable;
    }

    public int getAdjustWidthValue() {
        return this.adjustWidthValue;
    }

    /**
     * Call this method before {@link #init(List)}.
     * 
     * @param defaultOrderedColumn
     * @param defaultOrderBy
     */
    public void setDefaultSort(TableViewerCreatorColumn defaultOrderedColumn, SORT defaultOrderBy) {
        this.defaultOrderedColumn = defaultOrderedColumn;
        this.defaultOrderBy = defaultOrderBy;
    }

    /**
     * Change sort properties and refresh table. You can call this method if you have already call init(List).
     * 
     * @param orderedColumn
     * @param orderBy
     */
    public void setSort(TableViewerCreatorColumn orderedColumn, SORT orderBy) {
        if (this.tableViewerCreatorSorter != null) {
            this.tableViewerCreatorSorter.prepareSort(this, orderedColumn, orderBy);
            this.tableViewer.refresh();
        }
    }

    public void setAdjustWidthValue(int adjustWidthValue) {
        this.adjustWidthValue = adjustWidthValue;
    }

    public void setTableViewerCreatorSorter(TableViewerCreatorSorter tableViewerCreatorSorter) {
        this.tableViewerCreatorSorter = tableViewerCreatorSorter;
    }

    public TableViewerCreatorSorter getTableViewerCreatorSorter() {
        return this.tableViewerCreatorSorter;
    }

    public LAYOUT_MODE getLayoutMode() {
        return this.layoutMode;
    }

    public void setLayoutMode(LAYOUT_MODE layoutMode) {
        this.layoutMode = layoutMode;
    }

    public void layout() {
        if (table.isDisposed()) {
            return;
        }
        Layout currentLayout = table.getLayout();
        if (currentLayout instanceof TableViewerCreatorLayout) {
            ((TableViewerCreatorLayout) currentLayout).forceLayout(table);
        } else if (currentLayout instanceof TableLayout) {
            ((TableLayout) currentLayout).layout(table, true);
        }
    }

    public Layout getLayout() {
        return this.layout;
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    /**
     * This method is useful for mask first column on a Windows Table because the first column display a blank space at
     * left border.
     * 
     * @param firstColumnMasked
     */
    public void setFirstColumnMasked(boolean firstColumnMasked) {
        this.firstColumnMasked = firstColumnMasked;
    }

    public boolean isFirstColumnMasked() {
        return this.firstColumnMasked;
    }

    /**
     * DOC amaumont Comment method "getModifiedObjectInfo".
     * 
     * @return always a instance of ModifiedObjectInfo
     */
    public ModifiedObjectInfo<O> getModifiedObjectInfo() {
        if (this.modifiedObjectInfo == null) {
            this.modifiedObjectInfo = new ModifiedObjectInfo<O>();
        }
        return this.modifiedObjectInfo;
    }

    public Color getEmptyZoneColor() {
        return this.emptyZoneColor;
    }

    public void setEmptyColor(Color emptyZoneColor) {
        this.emptyZoneColor = emptyZoneColor;
    }

    protected boolean isEmptyZoneLinesVisible() {
        return this.emptyZoneLinesVisible;
    }

    protected void setEmptyZoneLinesVisible(boolean emptyZoneLinesVisible) {
        this.emptyZoneLinesVisible = emptyZoneLinesVisible;
    }

    public void refreshTableEditorControls() {
        tableEditorManager.refresh();
    }

    /**
     * Setter for firstVisibleColumnIsSelection.
     * 
     * @param firstVisibleColumnIsSelection
     */
    public void setFirstVisibleColumnIsSelection(boolean firstVisibleColumnIsSelection) {
        this.firstVisibleColumnIsSelection = firstVisibleColumnIsSelection;
    }

    /**
     * Getter for firstVisibleColumnIsSelection.
     * 
     * @return the firstVisibleColumnIsSelection
     */
    public boolean isFirstVisibleColumnIsSelection() {
        return this.firstVisibleColumnIsSelection;
    }

    public SelectionHelper getSelectionHelper() {
        if (this.selectionHelper == null) {
            this.selectionHelper = new SelectionHelper(this, mouseTableSelectionHelper);
        }
        return this.selectionHelper;
    }

    /**
     * You must use DefaultCellModifier or a class which extends it to use this method. You can call this method only if
     * you have already called createTable().
     * 
     * 
     * @param tableCellValueModifiedListener
     * @throws UnsupportedOperationException if current CellModifier is not DefaultCellModifier or a class which extends
     * it @
     */
    public void addCellValueModifiedListener(ITableCellValueModifiedListener tableCellValueModifiedListener) {
        if (this.cellModifier == null) {
            throw new IllegalStateException("You can call this method only if you have already called createTable()");
        }
        if (this.cellModifier instanceof DefaultCellModifier) {
            ((DefaultCellModifier) this.cellModifier).addCellEditorAppliedListener(tableCellValueModifiedListener);
        } else {
            throw new UnsupportedOperationException("The current CellModifier does'nt support this operation. \n Use '"
                    + DefaultCellModifier.class + "' or a class which extends it to use this feature");
        }
    }

    /**
     * You must use DefaultCellModifier or a class which extends it to use this method. You can call this method only if
     * you have already called createTable().
     * 
     * @param tableCellValueModifiedListener
     * @throws UnsupportedOperationException if current CellModifier is not DefaultCellModifier or a class which extends
     * it
     */
    public void removeCellValueModifiedListener(ITableCellValueModifiedListener tableCellValueModifiedListener) {
        if (this.cellModifier == null) {
            throw new IllegalStateException("You can call this method only if you have already called createTable()");
        }
        if (this.cellModifier instanceof DefaultCellModifier) {
            ((DefaultCellModifier) this.cellModifier).removeCellEditorAppliedListener(tableCellValueModifiedListener);
        } else {
            throw new UnsupportedOperationException("The current CellModifier does'nt support this operation. \n Use '"
                    + DefaultCellModifier.class + "' or a class which extends it to use this feature");
        }
    }

    /**
     * <p>
     * Use custom coloring allow by default to correct bad rendering of transparent images in Table and for first
     * column.
     * </p>
     * <p>
     * Note : only available on Win32
     * </p>
     * Warnings :
     * <ul>
     * <li> automatic tooltip which appears when a cell is too small to display its content won't work. </li>
     * <li> automatic tooltip behavior can't be found again if you unactive custom coloring due to Table._addListener()
     * and Table.removeListener(). </li>
     * </ul>
     * 
     * @param useCustomColoring
     */
    public void setUseCustomColoring(boolean useCustomColoring) {
        if(WindowSystem.isWIN32()) {
            this.useCustomColoring = useCustomColoring;
            if (table != null) {
                if (useCustomColoring) {
                    addEraseItemListener();
                } else {
                    removeEraseItemListener();
                }
            }
        }
    }

    /**
     * DOC amaumont Comment method "removePaintListener".
     */
    protected void removeEraseItemListener() {
        if (eraseItemListener != null) {
            table.removeListener(SWT.EraseItem, eraseItemListener);
            eraseItemListener = null;
        }
    }

    /**
     * <p>
     * Note : only available on Win32
     * </p>
     * 
     * @return the color set by user (not the default color of selection)
     */
    public Color getBgColorSelectedLine() {
        return this.bgColorSelectedLine;
    }

    /**
     * 
     * <p>
     * Note : only available on Win32
     * </p>
     * 
     * @param lineSelectionBackgroundColor
     * @see TableViewerCreator#setUseCustomColoring(boolean)
     */
    public void setBgColorSelectedLine(Color lineSelectionBackgroundColor) {
        this.bgColorSelectedLine = lineSelectionBackgroundColor;
    }

    /**
     * <p>
     * Note : only available on Win32
     * </p>
     * 
     * @return the color set by user (not the default color of selection)
     */
    public Color getFgColorSelectedLine() {
        return this.fgColorSelectedLine;
    }

    /**
     * 
     * <p>
     * Note : only available on Win32
     * </p>
     * 
     * @param lineSelectionForegroundColor
     * @see TableViewerCreator#setUseCustomColoring(boolean)
     */
    public void setFgColorSelectedLine(Color lineSelectionForegroundColor) {
        this.fgColorSelectedLine = lineSelectionForegroundColor;
    }

    /**
     * <p>
     * Note : only available on Win32
     * </p>
     * 
     * @return the color set by user (not the default color of selection)
     */
    public Color getBgColorSelectedLineWhenUnactive() {
        return this.bgColorSelectedLineWhenUnactive;
    }

    /**
     * 
     * <p>
     * Note : only available on Win32
     * </p>
     * 
     * @param bgColorSelectedLineWhenUnactive
     * @see TableViewerCreator#setUseCustomColoring(boolean)
     */
    public void setBgColorSelectedLineWhenUnactive(Color bgColorSelectedLineWhenUnactive) {
        this.bgColorSelectedLineWhenUnactive = bgColorSelectedLineWhenUnactive;
    }

    /**
     * <p>
     * Note : only available on Win32
     * </p>
     * 
     * @return the color set by user (not the default color of selection)
     */
    public Color getFgColorSelectedLineWhenUnactive() {
        return this.fgColorSelectedLineWhenUnactive;
    }

    /**
     * 
     * <p>
     * Note : only available on Win32
     * </p>
     * 
     * @param fgColorSelectedLineWhenUnactive
     * @see TableViewerCreator#setUseCustomColoring(boolean)
     */
    public void setFgColorSelectedLineWhenUnactive(Color fgColorSelectedLineWhenUnactive) {
        this.fgColorSelectedLineWhenUnactive = fgColorSelectedLineWhenUnactive;
    }

}
