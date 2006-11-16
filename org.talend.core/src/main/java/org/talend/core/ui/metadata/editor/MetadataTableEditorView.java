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
package org.talend.core.ui.metadata.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreatorColumn;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator.CELL_EDITOR_STATE;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator.LAYOUT_MODE;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator.LINE_SELECTION;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator.SHOW_SELECTION;
import org.talend.commons.ui.swt.tableviewer.behavior.CellEditorValueAdapter;
import org.talend.commons.ui.swt.tableviewer.behavior.IColumnImageProvider;
import org.talend.commons.ui.swt.tableviewer.celleditor.DialogErrorForCellEditorListener;
import org.talend.commons.ui.swt.tableviewer.selection.ILineSelectionListener;
import org.talend.commons.ui.swt.tableviewer.selection.LineSelectionEvent;
import org.talend.commons.ui.swt.tableviewer.selection.SelectionHelper;
import org.talend.commons.ui.swt.tableviewer.tableeditor.CheckboxTableEditorContent;
import org.talend.commons.utils.data.bean.IBeanPropertyAccessors;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.MetadataColumn;
import org.talend.core.model.metadata.MetadataTalendType;
import org.talend.core.model.metadata.editor.MetadataEditorEvent;
import org.talend.core.model.metadata.editor.MetadataTableEditor;
import org.talend.core.model.metadata.editor.MetadataEditorEvent.TYPE;
import org.talend.core.ui.EImage;
import org.talend.core.ui.ImageProvider;

/**
 * MetadataTableEditorView2 must be used.
 * 
 * $Id$
 * 
 */
@Deprecated
public class MetadataTableEditorView {

    private Label nameLabel;

    private TableViewerCreator<IMetadataColumn> tableViewerCreator;

    private Composite composite;

    private MetadataTableEditor metadataTableEditor;

    private boolean executeSelectionEvent = true;

    public static final String ID_COLUMN_NAME = "ID_COLUMN_NAME";

    private MetadataToolbarEditorView metadataToolbarEditorView;

    private IBeanPropertyAccessors<IMetadataColumn, Boolean> keyAccesor;

    private boolean readOnly;

    /**
     * 
     * Use SWT.READ_ONLY to create editor in read only mode.
     * @param parent
     * @param style
     * @param metadataTableEditor
     * @param displayToolbar
     */
    public MetadataTableEditorView(Composite parent, int style, MetadataTableEditor metadataTableEditor, boolean displayToolbar) {
        this(parent, style, displayToolbar);
        setMetadataTableEditor(metadataTableEditor);
    }

    /**
     * 
     * Use SWT.READ_ONLY to create editor in read only mode.
     * @param parent
     * @param style
     * @param displayToolbar
     */
    public MetadataTableEditorView(Composite parent, int style, boolean displayToolbar) {
        super();
        composite = new Composite(parent, style);
        GridLayout layout = new GridLayout();
        composite.setLayout(layout);

        readOnly = (style & SWT.READ_ONLY) != 0;
        
        createComponents(displayToolbar);
        addListeners();
    }

    /**
     * DOC amaumont Comment method "addListeners".
     */
    private void addListeners() {
    }

    private void createComponents(boolean displayToolbar) {
        nameLabel = new Label(composite, SWT.NONE);
        nameLabel.setText("");
        nameLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        addMetadataTable();
        if (displayToolbar) {
            addMetadataToolbar();
        }

    }

    /**
     * DOC amaumont Comment method "addMetadataToolbar".
     */
    private void addMetadataToolbar() {
        metadataToolbarEditorView = new MetadataToolbarEditorView(composite, SWT.NONE, this);

    }

    private void addMetadataTable() {
        tableViewerCreator = new TableViewerCreator<IMetadataColumn>(composite);
        tableViewerCreator.setHeaderVisible(true);
        tableViewerCreator.setAllColumnsResizable(true);
        tableViewerCreator.setBorderVisible(true);
        tableViewerCreator.setLinesVisible(true);
        tableViewerCreator.setShowSelection(SHOW_SELECTION.FULL);
        tableViewerCreator.setLineSelection(LINE_SELECTION.MULTI);
        tableViewerCreator.setLayoutMode(LAYOUT_MODE.CONTINUOUS);
        tableViewerCreator.setFirstVisibleColumnIsSelection(true);
        tableViewerCreator.setUseCustomColoring(true);
        tableViewerCreator.setFirstColumnMasked(true);

        Table table = initLineSelectionListeners();

        
        
        initColumns();
        table.setLayoutData(new GridData(GridData.FILL_BOTH));
    }

    /**
     * DOC amaumont Comment method "initLineSelectionListeners".
     * 
     * @return
     */
    private Table initLineSelectionListeners() {
        final Table table = tableViewerCreator.createTable();
        final SelectionHelper selectionHelper = tableViewerCreator.getSelectionHelper();
        final ILineSelectionListener beforeLineSelectionListener = new ILineSelectionListener() {

            public void handle(LineSelectionEvent e) {
                if (e.selectionByMethod && !selectionHelper.isMouseSelectionning()) {
                    executeSelectionEvent = false;
                } else {
                    executeSelectionEvent = true;
                }
            }
        };
        final ILineSelectionListener afterLineSelectionListener = new ILineSelectionListener() {

            public void handle(LineSelectionEvent e) {
                executeSelectionEvent = true;
            }
        };
        selectionHelper.addBeforeSelectionListener(beforeLineSelectionListener);
        selectionHelper.addAfterSelectionListener(afterLineSelectionListener);

        DisposeListener disposeListener = new DisposeListener() {

            public void widgetDisposed(DisposeEvent e) {
                selectionHelper.removeBeforeSelectionListener(beforeLineSelectionListener);
                selectionHelper.removeAfterSelectionListener(afterLineSelectionListener);
                table.removeDisposeListener(this);
            }
        };
        table.addDisposeListener(disposeListener);

        table.addListener(SWT.KeyDown, new Listener() {

            public void handleEvent(Event event) {
                if (event.character == '\u0001') { // CTRL + A
                    table.selectAll();
                }
            }

        });

        return table;
    }

    /**
     * 
     * DOC amaumont Comment method "initColumns".
     * 
     * @param table
     */
    private void initColumns() {

        String[] arrayTalendTypes = new String[0];
        try {
            arrayTalendTypes = MetadataTalendType.loadTalendTypes("TALENDDEFAULT", false);
        } catch (NoClassDefFoundError e) {
            // shouln't be happend
            e.printStackTrace();
        } catch (ExceptionInInitializerError e) {
            // shouln't be happend
            e.printStackTrace();
        }

        CellEditorValueAdapter intValueAdapter = new CellEditorValueAdapter() {

            public Object getOriginalTypedValue(final CellEditor cellEditor, Object value) {
                try {
                    return new Integer(value.toString());
                } catch (Exception ex) {
                    return null;
                }
            }

            public Object getCellEditorTypedValue(final CellEditor cellEditor, Object value) {
                if (value != null) {
                    return String.valueOf(value);
                }
                return "";
            }
        };

        // comboValueAdapter
        CellEditorValueAdapter comboValueAdapter = new CellEditorValueAdapter() {

            public Object getOriginalTypedValue(final CellEditor cellEditor, Object value) {
                String[] items = ((ComboBoxCellEditor) cellEditor).getItems();
                int i = new Integer(value.toString());
                if (i >= 0) {
                    return items[i];
                } else {
                    return "";
                }
            }

            public Object getCellEditorTypedValue(final CellEditor cellEditor, Object value) {
                String[] items = ((ComboBoxCellEditor) cellEditor).getItems();
                for (int i = 0; i < items.length; i++) {
                    if (items[i] == value) {
                        return i;
                    }
                }
                return -1;
            }
        };

        // //////////////////////////////////////////////////////////////////////////////////////

        TableViewerCreatorColumn column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle("");
        column.setDefaultInternalValue("");
        column.setWidth(15);

        // //////////////////////////////////////////////////////////////////////////////////////

        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setId(ID_COLUMN_NAME);
        column.setTitle("Column");
        column.setBeanPropertyAccessors(new IBeanPropertyAccessors<IMetadataColumn, String>() {

            public String get(IMetadataColumn bean) {
                return bean.getLabel();
            }

            public void set(IMetadataColumn bean, String value) {
                bean.setLabel(value);
            }

        });
        final Image imageKey = ImageProvider.getImage(EImage.KEY_ICON);
        final Image imageEmpty = org.talend.core.ui.ImageProvider.getImage(EImage.EMPTY);
        column.setImageProvider(new IColumnImageProvider() {

            public Image getImage(Object element) {
                IMetadataColumn metadataColumn = (MetadataColumn) element;
                if (metadataColumn.isKey()) {
                    return imageKey;
                } else {
                    return imageEmpty;
                }
            }

        });
        column.setWeight(20);
        column.setModifiable(true);
        column.setMinimumWidth(45);
        if(!readOnly) {
            final TextCellEditor cellEditor = new TextCellEditor(tableViewerCreator.getTable());
            cellEditor.addListener(new DialogErrorForCellEditorListener(cellEditor, column) {
                
                @Override
                public void newValidValueApplied(String previousValue, String newValue, CELL_EDITOR_STATE state) {
                    Object currentModifiedObject = tableViewerCreator.getModifiedObjectInfo().getCurrentModifiedBean();
                    ArrayList modifiedObjectList = new ArrayList(1);
                    modifiedObjectList.add(currentModifiedObject);
                    MetadataEditorEvent event = new MetadataEditorEvent(MetadataEditorEvent.TYPE.METADATA_NAME_VALUE_CHANGED);
                    event.entries = modifiedObjectList;
                    event.previousValue = previousValue;
                    event.newValue = newValue;
                    event.state = state;
                    metadataTableEditor.fireEvent(event);
                }
                
                @Override
                public String validateValue(String newValue, int beanPosition) {
                    return metadataTableEditor.validateColumnName(newValue, beanPosition);
                }
                
            });
            column.setCellEditor(cellEditor);
        }

        // //////////////////////////////////////////////////////////////////////////////////////

        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle("Key");
        this.keyAccesor = new IBeanPropertyAccessors<IMetadataColumn, Boolean>() {

            public Boolean get(IMetadataColumn bean) {
                return new Boolean(bean.isKey());
            }

            public void set(IMetadataColumn bean, Boolean value) {
                bean.setKey(value);
                if (metadataTableEditor != null) {
                    tableViewerCreator.getTableViewer().refresh(bean);
                    MetadataEditorEvent metadataEditorEvent = new MetadataEditorEvent(TYPE.METADATA_KEY_VALUE_CHANGED);
                    metadataEditorEvent.entries.add(bean);
                    metadataEditorEvent.entriesIndices = new int[] { metadataTableEditor.getMetadataColumnList().indexOf(bean) };
                    metadataTableEditor.fireEvent(metadataEditorEvent);
                }
            }

        };
        column.setBeanPropertyAccessors(this.keyAccesor);
        column.setWidth(35);
        column.setDisplayedValue("");
        CheckboxTableEditorContent checkboxTableEditorContent = new CheckboxTableEditorContent(readOnly);
        column.setTableEditorContent(checkboxTableEditorContent);

        // //////////////////////////////////////////////////////////////////////////////////////

        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle("Type");
        column.setBeanPropertyAccessors(new IBeanPropertyAccessors<IMetadataColumn, String>() {

            public String get(IMetadataColumn bean) {
                return bean.getTalendType();
            }

            public void set(IMetadataColumn bean, String value) {
                bean.setTalendType(value);
            }

        });
        column.setModifiable(true);
        column.setWeight(10);
        column.setMinimumWidth(30);
        if(!readOnly) {
            column.setCellEditor(new ComboBoxCellEditor(tableViewerCreator.getTable(), arrayTalendTypes), comboValueAdapter);
        }

        // //////////////////////////////////////////////////////////////////////////////////////

        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle("Length");
        column.setBeanPropertyAccessors(new IBeanPropertyAccessors<IMetadataColumn, Integer>() {

            public Integer get(IMetadataColumn bean) {
                return bean.getLength();
            }

            public void set(IMetadataColumn bean, Integer value) {
                bean.setLength(value);
            }

        });
        column.setModifiable(true);
        column.setWidth(55);
        if(!readOnly) {
            column.setCellEditor(new TextCellEditor(tableViewerCreator.getTable()), intValueAdapter);
        }

        // //////////////////////////////////////////////////////////////////////////////////////

        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle("Precision");
        column.setBeanPropertyAccessors(new IBeanPropertyAccessors<IMetadataColumn, Integer>() {

            public Integer get(IMetadataColumn bean) {
                return bean.getPrecision();
            }

            public void set(IMetadataColumn bean, Integer value) {
                bean.setPrecision(value);
            }

        });
        column.setModifiable(true);
        column.setWidth(60);
        if(!readOnly) {
            column.setCellEditor(new TextCellEditor(tableViewerCreator.getTable()), intValueAdapter);
        }

        // //////////////////////////////////////////////////////////////////////////////////////

        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle("Nullable");
        column.setBeanPropertyAccessors(new IBeanPropertyAccessors<IMetadataColumn, Boolean>() {

            public Boolean get(IMetadataColumn bean) {
                return new Boolean(bean.isNullable());
            }

            public void set(IMetadataColumn bean, Boolean value) {
                bean.setNullable(value);
            }

        });
        column.setModifiable(true);
        column.setWidth(56);
        column.setDisplayedValue("");
        column.setTableEditorContent(new CheckboxTableEditorContent(readOnly));

        // //////////////////////////////////////////////////////////////////////////////////////

        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle("Default");
        column.setBeanPropertyAccessors(new IBeanPropertyAccessors<IMetadataColumn, String>() {

            public String get(IMetadataColumn bean) {
                return bean.getDefault();
            }

            public void set(IMetadataColumn bean, String value) {
                bean.setDefault(value);
            }

        });
        column.setWeight(10);
        column.setModifiable(true);
        column.setMinimumWidth(30);
        if(!readOnly) {
            column.setCellEditor(new TextCellEditor(tableViewerCreator.getTable()));
        }

        // //////////////////////////////////////////////////////////////////////////////////////

        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle("Comment");
        column.setBeanPropertyAccessors(new IBeanPropertyAccessors<IMetadataColumn, String>() {

            public String get(IMetadataColumn bean) {
                return bean.getComment();
            }

            public void set(IMetadataColumn bean, String value) {
                bean.setComment(value);
            }

        });
        column.setWeight(30);
        column.setModifiable(true);
        column.setMinimumWidth(20);
        if(!readOnly) {
            column.setCellEditor(new TextCellEditor(tableViewerCreator.getTable()));
        }

    }

    public MetadataTableEditor getMetadataTableEditor() {
        return this.metadataTableEditor;
    }

    public void setMetadataTableEditor(MetadataTableEditor metadataTableEditor) {
        this.metadataTableEditor = metadataTableEditor;
        if (metadataTableEditor == null) {
            nameLabel.setText("");
            executeSelectionEvent = false;
            tableViewerCreator.init(new ArrayList());
            executeSelectionEvent = true;
            tableViewerCreator.layout();
        } else {
            nameLabel.setText(metadataTableEditor.getTitleName());
            executeSelectionEvent = false;
            tableViewerCreator.init(metadataTableEditor.getMetadataColumnList());
            executeSelectionEvent = true;
            tableViewerCreator.layout();
        }
    }

    public TableViewerCreator getTableViewerCreator() {
        return this.tableViewerCreator;
    }

    /**
     * DOC amaumont Comment method "setTableSelection".
     * 
     * @param selectionIndices
     */
    public void setTableSelection(int[] selectionIndices, boolean executeSelectionEvent) {
        this.executeSelectionEvent = executeSelectionEvent;
        this.tableViewerCreator.getTable().setSelection(selectionIndices);
        this.executeSelectionEvent = true;

    }

    public boolean isExecuteSelectionEvent() {
        return this.executeSelectionEvent;
    }

    public void setExecuteSelectionEvent(boolean executeSelectionEvent) {
        this.executeSelectionEvent = executeSelectionEvent;
    }

    /**
     * DOC ocarbone Comment method "setGridDataSize".
     * 
     * @param minimumWidth
     * @param minimumHeight
     */
    public void setGridDataSize(final int minimumWidth, final int minimumHeight) {
        this.composite.setSize(minimumWidth, minimumHeight);

        GridData gridData = new GridData(GridData.FILL_BOTH);
        gridData.minimumWidth = minimumWidth;
        gridData.minimumHeight = minimumHeight;
        this.composite.setLayoutData(gridData);

    }

    public void setReadOnly(boolean readOnly) {
        if (metadataToolbarEditorView != null) {
            metadataToolbarEditorView.setReadOnly(readOnly);
        }
        
        List<TableViewerCreatorColumn> columns = this.tableViewerCreator.getColumns();
        for (TableViewerCreatorColumn column : columns) {
            column.setModifiable(!readOnly);
        }
//        this.tableViewerCreator.getTable().setEnabled(!b);
//        this.tableViewerCreator.getTable().getVerticalBar().setEnabled(true);
//        this.tableViewerCreator.getTable().getHorizontalBar().setEnabled(true);
    }

    public MetadataToolbarEditorView getMetadataToolbarEditorView() {
        return this.metadataToolbarEditorView;
    }

    public void setMetadataToolbarEditorView(MetadataToolbarEditorView metadataToolbarEditorView) {
        this.metadataToolbarEditorView = metadataToolbarEditorView;
    }

    
    /**
     * Getter for composite.
     * @return the composite
     */
    public Composite getComposite() {
        return this.composite;
    }

    
    
}
