// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006-2007 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.util.Assert;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLayoutData;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
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
import org.talend.commons.i18n.internal.Messages;
import org.talend.commons.ui.swt.extended.table.ModifyBeanValueCommand;
import org.talend.commons.ui.swt.tableviewer.behavior.DefaultCellModifier;
import org.talend.commons.ui.swt.tableviewer.behavior.DefaultHeaderColumnSelectionListener;
import org.talend.commons.ui.swt.tableviewer.behavior.DefaultStructuredContentProvider;
import org.talend.commons.ui.swt.tableviewer.behavior.DefaultTableLabelProvider;
import org.talend.commons.ui.swt.tableviewer.behavior.ITableCellValueModifiedListener;
import org.talend.commons.ui.swt.tableviewer.behavior.TableViewerCreatorLayout;
import org.talend.commons.ui.swt.tableviewer.data.AccessorUtils;
import org.talend.commons.ui.swt.tableviewer.data.ModifiedObjectInfo;
import org.talend.commons.ui.swt.tableviewer.selection.ITableColumnSelectionListener;
import org.talend.commons.ui.swt.tableviewer.selection.MouseTableSelectionHelper;
import org.talend.commons.ui.swt.tableviewer.selection.SelectionHelper;
import org.talend.commons.ui.swt.tableviewer.sort.TableViewerCreatorSorter;
import org.talend.commons.ui.swt.tableviewer.tableeditor.TableEditorManager;
import org.talend.commons.ui.utils.TableUtils;
import org.talend.commons.ui.ws.WindowSystem;
import org.talend.commons.utils.data.list.ListenableList;
import org.talend.commons.utils.threading.AsynchronousThreading;

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
 * @param <B> type of objects in the input list of <code>TableViewer</code>
 */
public class TableViewerCreator<B> implements IModifiedBeanListenable<B> {

    private static Logger log = Logger.getLogger(TableViewerCreator.class);

    private static final String ID_MASKED_COLUMN = "__MASKED_COLUMN__";

    private Composite compositeParent;

    private List<TableViewerCreatorColumn> columns = new ArrayList<TableViewerCreatorColumn>();

    private TableViewer tableViewer;

    private LINE_SELECTION lineSelection = LINE_SELECTION.MULTI;

    private SHOW_ROW_SELECTION showLineSelection = SHOW_ROW_SELECTION.FULL;

    /*
     * The list of listeners who wish to be notified when something significant happens with the proposals.
     */
    private ListenerList modifiedBeanListeners = new ListenerList();

    private CommandStack commandStack;

    /**
     * 
     * DOC amaumont MetadataEditorEvent class global comment. Detailled comment <br/>
     * 
     * $Id$
     * 
     */
    public enum CELL_EDITOR_STATE {
        EDITING,
        APPLYING,
        CANCELING;
    }

    /**
     * @see Table#setLinesVisible(boolean)
     */
    private boolean linesVisible = true;

    /**
     * 
     */
    private Color emptyZoneColor;

    /**
     * SWT.BORDER style applied to <code>Table</code>.
     * 
     * @see SWT#CHECK
     */
    private boolean borderVisible;

    /**
     * @see Table#setHeaderVisible(boolean)
     */
    private boolean headerVisible = true;

    /**
     * SWT.CHECK style applied to <code>Table</code>.
     * 
     * @see SWT#CHECK
     */
    private boolean checkboxInFirstColumn;

    private boolean horizontalScroll;

    private boolean verticalScroll;

    private boolean columnsMoveableByDefault;

    private boolean columnsResizableByDefault;

    private boolean columnsSortableByDefault;

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

    private ModifiedObjectInfo<B> modifiedObjectInfo;

    private boolean firstVisibleColumnIsSelection;

    private SelectionHelper selectionHelper;

    private MouseTableSelectionHelper mouseTableSelectionHelper;

    private TableViewerCreatorColumn defaultOrderedColumn;

    private SORT defaultOrderBy;

    private Color backgroundColor;

    private Color foregroundColor;

    private Color bgColorSelectedLine;

    private Color fgColorSelectedLine;

    private Color bgColorSelectedLineWhenUnactive;

    private Color fgColorSelectedLineWhenUnactive;

    private Listener eraseItemListener;

    private boolean useCustomItemColoring;

    private List list;

    private boolean readOnly;

    /**
     * Constructor.
     * 
     * @param compositeParent used to initialize <code>Table</code>.
     */
    public TableViewerCreator(Composite compositeParent) {
        super();
        this.compositeParent = compositeParent;
        this.emptyZoneColor = compositeParent.getDisplay().getSystemColor(SWT.COLOR_WHITE);

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
            attachLabelProvider();
            attachViewerSorter();
            buildAndLayoutTable();
            attachContentProvider();
            attachCellEditors();
            addListeners();
        }
        this.list = list;
        setInputList(list);
        if (tableEditorManager != null) {
            tableEditorManager.init();
        }
    }

    @SuppressWarnings("unchecked")
    public List<B> getInputList() {
        return (List<B>) tableViewer.getInput();
    }

    public void setInputList(List list) {
        this.list = list;
        tableViewer.setInput(list);
        refreshTableEditorControls();
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

        // new TableEditor(table);
        tableViewer = new TableViewer(table) {

            /*
             * (non-Javadoc)
             * 
             * @see org.eclipse.jface.viewers.TableViewer#add(java.lang.Object)
             */
            @Override
            public void add(Object element) {
                super.add(element);
                refreshTableEditorControls();
            }

            /*
             * (non-Javadoc)
             * 
             * @see org.eclipse.jface.viewers.TableViewer#add(java.lang.Object[])
             */
            @Override
            public void add(Object[] elements) {
                super.add(elements);
                refreshTableEditorControls();
            }

            /*
             * (non-Javadoc)
             * 
             * @see org.eclipse.jface.viewers.TableViewer#remove(java.lang.Object[])
             */
            @Override
            public void remove(Object[] elements) {
                // removeEraseListener();
                super.remove(elements);
                // addEraseItemListener();
                refreshTableEditorControls();
            }

            /*
             * (non-Javadoc)
             * 
             * @see org.eclipse.jface.viewers.TableViewer#replace(java.lang.Object, int)
             */
            @Override
            public void replace(Object element, int index) {
                super.replace(element, index);
                refreshTableEditorControls();
            }

            /*
             * (non-Javadoc)
             * 
             * @see org.eclipse.jface.viewers.StructuredViewer#refresh()
             */
            @Override
            public void refresh() {
                boolean itemHasBeenRemoved = getInputList() != null && getInputList().size() < table.getItemCount();
                if (itemHasBeenRemoved) {
                    // table.removeListener(SWT.EraseItem, eraseItemListener);
                }
                super.refresh();
                if (itemHasBeenRemoved) {
                    // table.addListener(SWT.EraseItem, eraseItemListener);
                }
                refreshTableEditorControls();
            }

            /*
             * (non-Javadoc)
             * 
             * @see org.eclipse.jface.viewers.StructuredViewer#refresh(boolean)
             */
            @Override
            public void refresh(boolean updateLabels) {
                boolean itemHasBeenRemoved = getInputList() != null && getInputList().size() < table.getItemCount();
                if (itemHasBeenRemoved) {
                    // table.removeListener(SWT.EraseItem, eraseItemListener);
                }
                super.refresh(updateLabels);
                if (itemHasBeenRemoved) {
                    // table.addListener(SWT.EraseItem, eraseItemListener);
                }
                refreshTableEditorControls();
            }

            /*
             * (non-Javadoc)
             * 
             * @see org.eclipse.jface.viewers.StructuredViewer#refresh(java.lang.Object, boolean)
             */
            @Override
            public void refresh(Object element, boolean updateLabels) {
                super.refresh(element, updateLabels);
                // refreshTableEditorControls();
                // refreshTableEditorControls();
            }

            /*
             * (non-Javadoc)
             * 
             * @see org.eclipse.jface.viewers.StructuredViewer#refresh(java.lang.Object)
             */
            @Override
            public void refresh(Object element) {
                // if (eraseItemListener != null) {
                // table.removeListener(SWT.EraseItem, eraseItemListener);
                // }
                super.refresh(element);
                // if (eraseItemListener != null) {
                // table.addListener(SWT.EraseItem, eraseItemListener);
                // }
                // refreshTableEditorControls();
            }

        };
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

        if (this.emptyZoneColor != null && !WindowSystem.isGTK()) {
            Listener paintListener = new Listener() {

                public void handleEvent(Event event) {
                    GC gc = event.gc;
                    Rectangle area = table.getClientArea();

                    Color previousBgColor = gc.getBackground();

                    gc.setBackground(emptyZoneColor);
                    int starty = table.getHeaderHeight() + table.getItemCount() * table.getItemHeight()
                            - table.getVerticalBar().getSelection() * table.getItemHeight();

                    // System.out.println(starty + " < " + area.height);
                    // System.out.println("area.width= " + area.width);
                    if (starty < area.height) {
                        // System.out.println("0, starty, area.width, area.height");
                        // gc.setBackground(gc.getDevice().getSystemColor(SWT.COLOR_BLUE));
                        gc.fillRectangle(0, starty, area.width, area.height);
                        // gc.setBackground(gc.getDevice().getSystemColor(SWT.COLOR_RED));
                    }
                    TableColumn[] tableColumns = table.getColumns();
                    int widthColumns = 0;
                    for (int i = 0; i < tableColumns.length; i++) {
                        widthColumns += tableColumns[i].getWidth();
                    }
                    if (widthColumns < area.width) {
                        // System.out.println("widthColumns + 1, 0, area.width, area.height");
                        gc.fillRectangle(widthColumns + 1, 0, area.width, area.height);
                    }

                    gc.setBackground(previousBgColor);

                }
            };
            table.addListener(SWT.Paint, paintListener);
        }

        setBackgroundColor(backgroundColor != null ? backgroundColor : table.getDisplay().getSystemColor(
              SWT.COLOR_WHITE));
        setForegroundColor(foregroundColor != null ? foregroundColor : table.getDisplay().getSystemColor(
                SWT.COLOR_BLACK));

        if (useCustomItemColoring) {
            setUseCustomItemColoring(true);
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
        if (showLineSelection != null) {
            style |= showLineSelection.getSwtStyle();
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

        if (useCustomItemColoring) {
            addEraseItemListener();
        }

        // table.addControlListener(new ControlListener() {
        //
        // public void controlMoved(ControlEvent e) {
        // System.out.println("Control moved");
        // table.setVisible(false);
        // }
        //
        // public void controlResized(ControlEvent e) {
        // System.out.println("Control resized");
        // // table.setVisible(false);
        // // if (tableEditorManager != null) {
        // // tableEditorManager.redrawControls();
        // // }
        // }
        //
        // });

    }

    /**
     * 
     * This method initialize erase listener to go round the following SWT bug
     * https://bugs.eclipse.org/bugs/show_bug.cgi?id=50163 "Table doesn't respect transparency in column images when
     * using a different row background color" .
     * 
     * Unfortunately, use this listener implies that : - automatic tooltip in Table doesn't work anymore - bug when
     * {@link TableViewer#refresh()} or {@link TableViewer#refresh(boolean)} are called and possibly others...
     * 
     */
    protected void createEraseItemListener() {

        if (eraseItemListener != null) {
            return;
        }

        eraseItemListener = new Listener() {

            public void handleEvent(Event event) {

                // System.out.println(event);
                // if(true) {
                // return;
                // }

                // System.out.println("EraseItem event.detail=" + EventUtil.getEventNameFromDetail(event.detail) +
                // "event.widget="
                // + event.widget.hashCode());
                TableItem tableItem = (TableItem) event.item;

                if (table.isDisposed() || tableItem.isDisposed()) {
                    return;
                }

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

                } else if (event.detail == 24 || event.detail == 28) {
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
        idToTableViewerCreatorColumn = new HashMap<String, TableViewerCreatorColumn>(columns.size());

        if (this.layoutMode == LAYOUT_MODE.DEFAULT || this.layoutMode == LAYOUT_MODE.FILL_HORIZONTAL
                || this.layoutMode == LAYOUT_MODE.CONTINUOUS) {
            TableViewerCreatorLayout currentTableLayout = new TableViewerCreatorLayout(this);
            currentTableLayout.setWidthAdjustValue(this.adjustWidthValue);
            currentTableLayout.setFillHorizontal(this.layoutMode == LAYOUT_MODE.FILL_HORIZONTAL);
            currentTableLayout.setContinuousLayout(this.layoutMode == LAYOUT_MODE.FILL_HORIZONTAL
                    || this.layoutMode == LAYOUT_MODE.CONTINUOUS);
            this.layout = currentTableLayout;
        }

        if (firstColumnMasked || columns.size() == 0) {
            TableViewerCreatorColumn maskedTableViewerCreatorColumn = new TableViewerCreatorColumn();
            maskedTableViewerCreatorColumn.setId(ID_MASKED_COLUMN);
            columns.add(0, maskedTableViewerCreatorColumn);
        }

        int size = columns.size();
        Layout tempLayout = table.getLayout();
        table.setLayout(null);
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
                Assert.isTrue(tableColumn.getParent() == this.table, Messages.getString(
                        "TableViewerCreator.TableColumn.AssertMsg", column.getId())); //$NON-NLS-1$
            }
            Assert.isTrue(idToTableViewerCreatorColumn.get(column.getId()) == null, Messages
                    .getString("TableViewerCreator.IdProperty.AssertMsg")); //$NON-NLS-1$

            idToTableViewerCreatorColumn.put(column.getId(), column);
        }

        if (layout != null) {
            table.setLayout(layout);
        } else {
            table.setLayout(tempLayout);
        }
        // table.layout();
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

            tableColumn.setMoveable(column.isMoveable());
            tableColumn.setResizable(column.isResizable());
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

            if (column.getCellEditor() != null && column.getBeanPropertyAccessors() == null) {
                log.warn("The column '" + column.getId() + "' ('" + column.getTitle()
                        + "') has a CellEditor set but does'nt have a IBeanPropertyAccessors !");
            }

        }
        tableViewer.setColumnProperties(properties);
        tableViewer.setCellEditors(cellEditors);
    }

    public TableViewerCreatorColumn getColumn(String idProperty) {
        return idToTableViewerCreatorColumn.get(idProperty);
    }

    /**
     * Unlike <code>Table</code> header is visible by default.
     * 
     * @return true if table has header visible
     */
    public boolean isHeaderVisible() {
        if (table != null) {
            return table.getHeaderVisible();
        }
        return headerVisible;
    }

    /**
     * Unlike <code>Table</code> header is visible by default.
     * 
     * @param headerVisible
     */
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
        /**
         * Only one line is selectionnable.
         */
        SINGLE(SWT.SINGLE),
        /**
         * All line are selectionnable.
         */
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
    public enum SHOW_ROW_SELECTION implements ISwtStyle {
        /**
         * Show selection for full row.
         */
        FULL(SWT.FULL_SELECTION),
        /**
         * Don't show selection.
         */
        HIDE(SWT.HIDE_SELECTION);

        private int swtStyle = SWT.NONE;

        SHOW_ROW_SELECTION(int swtStyle) {
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
         * Layout mode. Default layout based on <code>TableLayout</code> behavior :
         * <p>- Use width and weight to initialize columns size, but don't resize columns when table is resized
         * </p>
         */
        DEFAULT(),

        /**
         * Layout mode.
         * <p>- Use width and weight to initialize columns size, but don't resize columns when table is resized
         * </p>
         * <p>- Fill all empty space at initialization
         * </p>
         * <p>- Change columns size dynamically when table is resized (only if weight is set in column)
         * </p>
         */
        CONTINUOUS,

        /**
         * Layout mode.
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

        NONE();

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

    /**
     * 
     * <code>LINE_SELECTION.MULTI</code> is the default value.
     * 
     * @param lineSelection
     */
    public void setLineSelection(LINE_SELECTION lineSelection) {
        this.lineSelection = lineSelection;
    }

    /**
     * Unlike <code>Table</code> lines are visible by default.
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
     * Unlike <code>Table</code> lines are visible by default.
     * 
     * @see Table#setLinesVisible(boolean)
     */
    public void setLinesVisible(boolean linesVisible) {
        if (table != null) {
            table.setLinesVisible(linesVisible);
        } else {
            this.linesVisible = linesVisible;
        }
    }

    /**
     * By default <code>showSelection</code> has <code>SHOW_SELECTION.FULL</code> value.
     * 
     * @return
     */
    public SHOW_ROW_SELECTION getShowLineSelection() {
        return showLineSelection;
    }

    /**
     * 
     * By default <code>showSelection</code> has <code>SHOW_SELECTION.FULL</code> value.
     * 
     * @param showLineSelection
     */
    public void setShowLineSelection(SHOW_ROW_SELECTION showLineSelection) {
        this.showLineSelection = showLineSelection;
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

    /**
     * @return
     */
    public boolean isHorizontalScroll() {
        return horizontalScroll;
    }

    /**
     * Note: has no effects for Windows sytem, scrollbar are always visible. Call this method before call createTable().
     * 
     * @param horizontalScroll
     */
    public void setHorizontalScroll(boolean horizontalScroll) {
        this.horizontalScroll = horizontalScroll;
    }

    /**
     * @return
     */
    public boolean isVerticalScroll() {
        return verticalScroll;
    }

    /**
     * Note: has no effects for Windows sytem, scrollbar are always visible. Call this method before call createTable().
     * 
     * @param verticalScroll
     */
    public void setVerticalScroll(boolean verticalScroll) {
        this.verticalScroll = verticalScroll;
    }

    public ICellModifier getCellModifier() {
        if (tableViewer != null && tableViewer.getCellModifier() != null) {
            return tableViewer.getCellModifier();
        }
        return cellModifier;
    }

    public void setCellModifier(ICellModifier cellModifier) {
        if (tableViewer != null && tableViewer.getCellModifier() != cellModifier) {
            tableViewer.setCellModifier(cellModifier);
        }
        this.cellModifier = cellModifier;
    }

    public IBaseLabelProvider getLabelProvider() {
        if (tableViewer != null && tableViewer.getLabelProvider() != null) {
            return tableViewer.getLabelProvider();
        }
        return labelProvider;
    }

    public void setLabelProvider(ITableLabelProvider tableLabelProvider) {
        if (tableViewer != null && tableViewer.getLabelProvider() != tableLabelProvider) {
            tableViewer.setLabelProvider(tableLabelProvider);
        }
        this.labelProvider = tableLabelProvider;
    }

    public IContentProvider getContentProvider() {
        if (tableViewer != null && tableViewer.getContentProvider() != null) {
            return tableViewer.getContentProvider();
        }
        return contentProvider;
    }

    public void setContentProvider(IStructuredContentProvider contentProvider) {
        if (tableViewer != null && tableViewer.getContentProvider() != contentProvider) {
            tableViewer.setContentProvider(contentProvider);
        }
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

    public boolean isColumnsMoveableByDefault() {
        return this.columnsMoveableByDefault;
    }

    public void setColumnsMoveableByDefault(boolean allColumnsMoveable) {
        this.columnsMoveableByDefault = allColumnsMoveable;
    }

    public boolean isColumnsResizableByDefault() {
        return this.columnsResizableByDefault;
    }

    public void setColumnsResizableByDefault(boolean allColumnsResizable) {
        this.columnsResizableByDefault = allColumnsResizable;
    }

    public boolean isColumnsSortableByDefault() {
        return this.columnsSortableByDefault;
    }

    public void setColumnsSortableByDefault(boolean allColumnsSortable) {
        this.columnsSortableByDefault = allColumnsSortable;
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
        if (table == null) {
            throw new IllegalStateException(Messages.getString("TableViewerCreator.Table.BeNull")); //$NON-NLS-1$
        }
        if (table.isDisposed()) {
            return;
        }
        table.layout();
        // Layout currentLayout = table.getLayout();
        // if (currentLayout instanceof TableViewerCreatorLayout) {
        // ((TableViewerCreatorLayout) currentLayout).forceLayout(table);
        // } else if (currentLayout instanceof TableLayout) {
        // ((TableLayout) currentLayout).layout(table, true);
        // }
    }

    public Layout getLayout() {
        return this.layout;
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    /**
     * This method is useful for mask first column on a Windows Table because the first column display a blank space at
     * left border. By default first column is masked.
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
    public ModifiedObjectInfo<B> getModifiedObjectInfo() {
        if (this.modifiedObjectInfo == null) {
            this.modifiedObjectInfo = new ModifiedObjectInfo<B>();
        }
        return this.modifiedObjectInfo;
    }

    public Color getEmptyZoneColor() {
        return this.emptyZoneColor;
    }

    public void setBgColorForEmptyArea(Color emptyZoneColor) {
        this.emptyZoneColor = emptyZoneColor;
    }

    public void refreshTableEditorControls() {
        if (tableEditorManager != null) {
            new AsynchronousThreading(0, true, table.getDisplay(), new Runnable() {

                public void run() {
                    if (tableEditorManager != null) {
                        tableEditorManager.refresh();
                        tableEditorManager.redrawControls();
                    }
                }

            }).start();
        }
    }

    public void redrawTableEditorControls() {
        if (tableEditorManager != null) {
            tableEditorManager.redrawControls();
        }
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
            throw new IllegalStateException(Messages.getString("TableViewerCreator.CallMethod.ErrorMsg")); //$NON-NLS-1$
        }
        if (this.cellModifier instanceof DefaultCellModifier) {
            ((DefaultCellModifier) this.cellModifier).addCellEditorAppliedListener(tableCellValueModifiedListener);
        } else {
            throw new UnsupportedOperationException(Messages.getString(
                    "TableViewerCreator.CellModifier.ExError", DefaultCellModifier.class)); //$NON-NLS-1$
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
            throw new IllegalStateException(Messages.getString("TableViewerCreator.CallMethod.ErrorMsg")); //$NON-NLS-1$
        }
        if (this.cellModifier instanceof DefaultCellModifier) {
            ((DefaultCellModifier) this.cellModifier).removeCellEditorAppliedListener(tableCellValueModifiedListener);
        } else {
            throw new UnsupportedOperationException(Messages.getString(
                    "TableViewerCreator.CellModifier.ExError", DefaultCellModifier.class)); //$NON-NLS-1$
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
    public void setUseCustomItemColoring(boolean useCustomColoring) {
        if (WindowSystem.isWIN32()) {
            this.useCustomItemColoring = useCustomColoring;
            if (table != null) {
                if (useCustomColoring) {
                    createEraseItemListener();
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
     * Get background color of selected line.
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
     * Set background color of selected line.
     * <p>
     * Note : only available on Win32
     * </p>
     * 
     * @param lineSelectionBackgroundColor
     * @see TableViewerCreator#setUseCustomItemColoring(boolean)
     */
    public void setBgColorSelectedLine(Color lineSelectionBackgroundColor) {
        this.bgColorSelectedLine = lineSelectionBackgroundColor;
    }

    /**
     * Get foreground color of selected line.
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
     * Set foreground color of selected line.
     * <p>
     * Note : only available on Win32
     * </p>
     * 
     * @param lineSelectionForegroundColor
     * @see TableViewerCreator#setUseCustomItemColoring(boolean)
     */
    public void setFgColorSelectedLine(Color lineSelectionForegroundColor) {
        this.fgColorSelectedLine = lineSelectionForegroundColor;
    }

    /**
     * Get background color of selected line if Table is unactive.
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
     * Set background color of selected line if Table is unactive.
     * <p>
     * Note : only available on Win32
     * </p>
     * 
     * @param bgColorSelectedLineWhenUnactive
     * @see TableViewerCreator#setUseCustomItemColoring(boolean)
     */
    public void setBgColorSelectedLineWhenUnactive(Color bgColorSelectedLineWhenUnactive) {
        this.bgColorSelectedLineWhenUnactive = bgColorSelectedLineWhenUnactive;
    }

    /**
     * Get foreground color of selected line if Table is unactive.
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
     * Set foreground color of selected line if Table is unactive.
     * <p>
     * Note : only available on Win32
     * </p>
     * 
     * @param fgColorSelectedLineWhenUnactive
     * @see TableViewerCreator#setUseCustomItemColoring(boolean)
     */
    public void setFgColorSelectedLineWhenUnactive(Color fgColorSelectedLineWhenUnactive) {
        this.fgColorSelectedLineWhenUnactive = fgColorSelectedLineWhenUnactive;
    }

    /**
     * Change value in model and refresh auomatically the <code>TableViewer</code> if <code>Table</code> is not
     * disposed.
     * 
     * @param currentRowObject
     * @param createNewCommand TODO
     * @param beanPropertyAccessors
     * @param b
     */
    @SuppressWarnings("unchecked")
    public void setBeanValue(TableViewerCreatorColumn column, B currentRowObject, Object value, boolean createNewCommand) {
        boolean listened = modifiedBeanListeners.size() != 0;

        Object previousValue = AccessorUtils.get(currentRowObject, column);

        // System.out.println();
        // System.out.println("previousValue="+previousValue);
        // System.out.println("value="+value);

        if (value == null && previousValue != null || value != null && !value.equals(previousValue)) {

            AccessorUtils.set(column, currentRowObject, value);

            // System.out.println("Set : " + "currentRowObject=" + currentRowObject + " value="+ value );
            if (getTable() != null && !getTable().isDisposed()) {
                // System.out.println(currentRowObject + " refreshed");
                tableViewer.refresh(currentRowObject);
            }

            ListenableList<B> extendedList = new ListenableList<B>(this.list);
            extendedList.setUseEquals(false);

            ModifiedBeanEvent<B> event = new ModifiedBeanEvent<B>();
            event.bean = (B) currentRowObject;
            event.column = column;
            event.index = extendedList.indexOf(currentRowObject);
            event.newValue = value;
            event.previousValue = previousValue;
            if (listened) {
                fireModifiedBeanEvent(event);
            }
            if (createNewCommand && this.commandStack != null) {
                ModifyBeanValueCommand<B> modifyBeanValueCommand = new ModifyBeanValueCommand<B>(event);
                this.commandStack.execute(modifyBeanValueCommand);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public B getBeanValue(TableViewerCreatorColumn column, Object currentRowObject) {
        return (B) AccessorUtils.get(currentRowObject, column);
    }

    public void addModifiedBeanListener(IModifiedBeanListener<B> modifiedBeanListener) {
        this.modifiedBeanListeners.add(modifiedBeanListener);
    }

    public void removeModifiedBeanListener(IModifiedBeanListener<B> modifiedBeanListener) {
        this.modifiedBeanListeners.remove(modifiedBeanListener);
    }

    @SuppressWarnings("unchecked")
    protected void fireModifiedBeanEvent(ModifiedBeanEvent<B> event) {
        // In all cases, notify listeners of an accepted proposal.
        final Object[] listenerArray = modifiedBeanListeners.getListeners();
        for (int i = 0; i < listenerArray.length; i++) {
            ((IModifiedBeanListener<B>) listenerArray[i]).handleEvent(event);
        }
    }

    /**
     * Getter for compositeParent.
     * 
     * @return the compositeParent
     */
    public Composite getCompositeParent() {
        return this.compositeParent;
    }

    // /**
    // * DOC amaumont Comment method "removeEraseListener".
    // */
    // private void removeEraseListener() {
    // if (eraseItemListener != null) {
    // table.removeListener(SWT.EraseItem, eraseItemListener);
    // }
    // }

    /**
     * DOC amaumont Comment method "addEraseListener".
     */
    private void addEraseItemListener() {
        if (useCustomItemColoring) {
            createEraseItemListener();
        }
        if (eraseItemListener != null) {
            table.addListener(SWT.EraseItem, eraseItemListener);
        }
    }

    /**
     * Getter for commandStackAdapter.
     * 
     * @return the commandStackAdapter
     */
    public CommandStack getCommandStack() {
        return this.commandStack;
    }

    /**
     * Sets the commandStackAdapter.
     * 
     * @param commandStack the commandStackAdapter to set
     */
    public void setCommandStack(CommandStack commandStack) {
        this.commandStack = commandStack;
    }

    /**
     * Getter for readOnly.
     * 
     * @return the readOnly
     */
    public boolean isReadOnly() {
        return this.readOnly;
    }

    /**
     * Sets the readOnly.
     * 
     * @param readOnly the readOnly to set
     */
    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public Color getBackgroundColor() {
        return this.backgroundColor;
    }

    public void setBackgroundColor(Color bgColor) {
        this.backgroundColor = bgColor;
        if (table != null) {
            table.setBackground(bgColor);
        }
    }

    public Color getForegroundColor() {
        return this.foregroundColor;
    }

    public void setForegroundColor(Color fgColor) {
        this.foregroundColor = fgColor;
        if (table != null) {
            table.setForeground(fgColor);
        }
    }

}
